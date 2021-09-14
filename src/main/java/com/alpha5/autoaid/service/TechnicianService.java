package com.alpha5.autoaid.service;

import com.alpha5.autoaid.dto.request.RepairCompletedRequest;
import com.alpha5.autoaid.dto.request.SubCatCompleteRequest;
import com.alpha5.autoaid.dto.response.GetNextRepairResponse;
import com.alpha5.autoaid.enums.ServiceEntryStatus;
import com.alpha5.autoaid.enums.SlotStatus;
import com.alpha5.autoaid.model.ServiceEntry;
import com.alpha5.autoaid.model.Slot;
import com.alpha5.autoaid.repository.RepairRepository;
import com.alpha5.autoaid.repository.ServiceEntryRepository;
import com.alpha5.autoaid.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TechnicianService {
    @Autowired
    private RepairRepository repairRepository;

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private ServiceEntryRepository serviceEntryRepository;

    public boolean checkEntryExists(SubCatCompleteRequest subCatCompleteRequest) {
        ServiceEntry serviceEntry = serviceEntryRepository.findByRepair_RepairIdAndSubCategory_SubCatId(subCatCompleteRequest.getRepairId(), subCatCompleteRequest.getSubCatId());
        if (serviceEntry != null) {
            return true;
        } else return false;
    }

    public boolean checkWhetherNoneCompletedEntries(long repairId) {
        List<ServiceEntry> serviceEntries = serviceEntryRepository.findAllByRepair_RepairIdAndServiceEntryStatusIsNot(repairId, ServiceEntryStatus.COMPLETED);

        if (serviceEntries.isEmpty()) {
            return false;
//            returns false if list is empty
        } else {
            return true;
        }
    }

    public boolean checkWhetherAllSlotsNotDeactivated(String section){
        if(slotRepository.findAllBySection_SectionNameAndStatusIsNot(section,SlotStatus.NOTAVAILABLE).isEmpty()){
            //all slots are at in not available status
            return true;
        }else
            return false;
    }

    public void completeSubCat(SubCatCompleteRequest subCatCompleteRequest) {
        ServiceEntry serviceEntry = serviceEntryRepository.findByRepair_RepairIdAndSubCategory_SubCatId(subCatCompleteRequest.getRepairId(), subCatCompleteRequest.getSubCatId());
        serviceEntry.setServiceEntryStatus(ServiceEntryStatus.COMPLETED);
        serviceEntryRepository.save(serviceEntry);
    }

    public boolean checkWhetherAllEntriesCompleted(RepairCompletedRequest repairCompletedRequest) {
        List<ServiceEntry> serviceEntries = serviceEntryRepository.findAllByRepair_RepairIdAndSubCategory_Section_SectionName(repairCompletedRequest.getRepairId(), repairCompletedRequest.getSectionName());
        List<ServiceEntry> notCompletedList = serviceEntries.stream()
                .filter(serviceEntry -> !(serviceEntry.getServiceEntryStatus().equals(ServiceEntryStatus.COMPLETED)))
                .collect(Collectors.toList());
        if (notCompletedList.isEmpty()) {
            return true;
        } else return false;
    }

    public void completeRepair(RepairCompletedRequest repairCompletedRequest) {
        List<ServiceEntry> serviceEntries = serviceEntryRepository.findAllByRepair_RepairIdAndSubCategory_Section_SectionName(repairCompletedRequest.getRepairId(), repairCompletedRequest.getSectionName());
        Slot slot = serviceEntries.stream()
                .map(serviceEntry -> serviceEntry.getSlot())
                .iterator().next();
//        System.out.println(slot.getSlotName());
        slot.setStatus(SlotStatus.AVAILABLE);
        slotRepository.save(slot);
//        getNextSlot(repairCompletedRequest.getRepairId());
    }

    public GetNextRepairResponse getNextRepair(String section) {
        //check if there are Available slots
        //if any one has no pending repairs
        //find first added repair of pending list of that section/ then assign newly available slot to that pending entries and return with btn status activate
        //else
        //get first added repair from repair table and return with btn status activate
        //slot's are not in available but in process
        //find first added repair of pending list of that section and return with btn status deactivate

        GetNextRepairResponse getNextRepairResponse=new GetNextRepairResponse();
        //check if there are Available slots
        List<Slot> availSlots = slotRepository.findAllBySection_SectionNameAndStatusIs(section, SlotStatus.AVAILABLE);
        System.out.println("Availa Slots");

        availSlots.forEach(slot -> System.out.println(slot.getSlotName()));
        if ((!availSlots.isEmpty())) {
            //which means has availslots
            try {
                //if there is a repair in pending for that slots / if it's get repair / btn activate
                long nextRepairId = findFirstAddedRepairForSection(availSlots);
                getNextRepairResponse.setRepairId(nextRepairId);
                getNextRepairResponse.setBtnStatus("Activate");

                return getNextRepairResponse;
            }
            catch (Exception e){
                //if pending repairs are not in that slot
                System.out.println("pending repairs NO");
            }

        } else {
            List<Slot> workingSlots = slotRepository.findAllBySection_SectionNameAndStatusIsNot(section, SlotStatus.NOTAVAILABLE);
            try {
                long nextRepairId=findFirstAddedRepairForSection(workingSlots);
                getNextRepairResponse.setRepairId(nextRepairId);
                getNextRepairResponse.setBtnStatus("Deactivate");

                return getNextRepairResponse;
            }catch (Exception e){
                //if pending repairs are not in that slot
                System.out.println("No Pending Repairs");
            }

        }
        return null;
    }

    public long findFirstAddedRepairForSection(List<Slot> workingSlots) {

        List<Long> repairIdList=new ArrayList<>();
        for (Slot slot : workingSlots) {
            List<Long> slotRepairIdList = serviceEntryRepository.findAllBySlotAndServiceEntryStatusIs(slot, ServiceEntryStatus.PENDING).stream()
                    .map(serviceEntry -> serviceEntry.getRepair().getRepairId())
                    .distinct()
                    .collect(Collectors.toList());

            slotRepairIdList.forEach(aLong -> repairIdList.add(aLong));
        }
        //oldest id from the list
        System.out.println("List");
        repairIdList.forEach(aLong -> System.out.println(aLong));
        Long firstRepairId = repairIdList.stream().sorted().findFirst().get();

        return firstRepairId;
    }
}