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

        if ((!availSlots.isEmpty())) {
            //filter which has no pending repairs
            List<Stream<Long>> pendingRepairIdList = availSlots.stream()
                    .map(slot -> serviceEntryRepository.findAllBySlotAndServiceEntryStatusIs(slot, ServiceEntryStatus.PENDING)
                            .stream().map(serviceEntry -> serviceEntry.getRepair().getRepairId()))
                    .distinct()
                    .collect(Collectors.toList());
            if (pendingRepairIdList.isEmpty()) {
                //find first added repair of pending list of that section/ then assign newly available slot to that pending entries and return with btn status activate
            } else {
                //get first added repair from repair table and return with btn status activate
                System.out.println("repair list is not empty");
                pendingRepairIdList.forEach(longStream -> System.out.println(longStream));
            }


        } else {
            long nextRepairId=findFirstAddedRepairForSection(section);
            getNextRepairResponse.setRepairId(nextRepairId);
            getNextRepairResponse.setBtnStatus("Deactivate");

            return getNextRepairResponse;
        }
        return null;
    }

    public long findFirstAddedRepairForSection(String sectionName) {
        List<Slot> workingSlots = slotRepository.findAllBySection_SectionNameAndStatusIsNot(sectionName, SlotStatus.NOTAVAILABLE);
        List<Long> repairIdList=new ArrayList<>();
        for (Slot slot : workingSlots) {
            List<Long> slotRepairIdList = serviceEntryRepository.findAllBySlotAndServiceEntryStatusIs(slot, ServiceEntryStatus.PENDING).stream()
                    .map(serviceEntry -> serviceEntry.getRepair().getRepairId())
                    .distinct()
                    .collect(Collectors.toList());

            slotRepairIdList.forEach(aLong -> repairIdList.add(aLong));
        }
        //oldest id from the list
        Long firstRepairId = repairIdList.stream().sorted().findFirst().get();

        return firstRepairId;
    }
}