package com.alpha5.autoaid.service;

import com.alpha5.autoaid.dto.request.RepairCompletedRequest;
import com.alpha5.autoaid.dto.request.SubCatCompleteRequest;
import com.alpha5.autoaid.dto.request.TechnicianRepairAcceptanceRequest;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TechnicianService {

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
        Date date=new Date();
        ServiceEntry serviceEntry = serviceEntryRepository.findByRepair_RepairIdAndSubCategory_SubCatId(subCatCompleteRequest.getRepairId(), subCatCompleteRequest.getSubCatId());
        serviceEntry.setServiceEntryStatus(ServiceEntryStatus.COMPLETED);
        serviceEntry.setCompletedTime(date);
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
    }

    public GetNextRepairResponse getNextRepair(String section) {

        GetNextRepairResponse getNextRepairResponse=new GetNextRepairResponse();
        //check if there are Available slots
        List<Slot> availSlots = slotRepository.findAllBySection_SectionNameAndStatusIs(section, SlotStatus.AVAILABLE);
        System.out.println("Available Slots");
        availSlots.forEach(slot -> System.out.println(slot.getSlotName()));

        if ((!availSlots.isEmpty())) {
            //which means has available slots
            try {
                //if there is a repair in pending for that slots / if it's get repair / btn activate
                long nextRepairId = findFirstAddedRepairForSection(availSlots);
                getNextRepairResponse.setRepairId(nextRepairId);
                getNextRepairResponse.setBtnStatus("Activate");

                return getNextRepairResponse;
            }
            catch (Exception e){
                //if pending repairs are not in that slot / then get next pending repair of section

                List<Long> slotRepairIdList = serviceEntryRepository.findAllBySlot_Section_SectionNameAndServiceEntryStatusIs(section, ServiceEntryStatus.PENDING).stream()
                        .map(serviceEntry -> serviceEntry.getRepair().getRepairId())
                        .distinct()
                        .collect(Collectors.toList());

                //oldest id from the list
                long nextRepairId = slotRepairIdList.stream().sorted().findFirst().get();
                getNextRepairResponse.setRepairId(nextRepairId);
                getNextRepairResponse.setBtnStatus("Activated");

                //get available slot to repair service entries
                Slot newSlot=availSlots.stream().findFirst().get();

                // save new slot in database
                List<ServiceEntry> serviceEntriesRepair=serviceEntryRepository.findAllByRepair_RepairIdAndSubCategory_Section_SectionName(nextRepairId,section);
                serviceEntriesRepair.forEach(serviceEntry -> {
                    serviceEntry.setSlot(newSlot);
                    serviceEntry.setStaff(newSlot.getStaff());
                    serviceEntryRepository.save(serviceEntry);
                });

//                System.out.println(newSlot.getSlotID());

                return getNextRepairResponse;
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
                throw new RuntimeException("No pending repairs to this sections");
            }
        }
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
        long firstRepairId = repairIdList.stream().sorted().findFirst().get();

        return firstRepairId;
    }

    public void acceptRepair(TechnicianRepairAcceptanceRequest technicianRepairAcceptanceRequest){
        List<ServiceEntry> serviceEntriesOfRepair=serviceEntryRepository.findAllByRepair_RepairIdAndSubCategory_Section_SectionName(technicianRepairAcceptanceRequest.getRepairId(),technicianRepairAcceptanceRequest.getSectionName());
        Slot slot=serviceEntriesOfRepair.stream().findFirst().get().getSlot();

        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date=new Date();
        System.out.println(dateFormat.format(date));
        for (ServiceEntry serviceEntry:serviceEntriesOfRepair){
            serviceEntry.setServiceEntryStatus(ServiceEntryStatus.ASSIGNED);
            serviceEntry.setAssignedTime(date);
            serviceEntryRepository.save(serviceEntry);
        }
        slot.setStatus(SlotStatus.ONPROCESS);
        slotRepository.save(slot);
    }
}