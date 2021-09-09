package com.alpha5.autoaid.service;

import com.alpha5.autoaid.dto.request.RepairCompletedRequest;
import com.alpha5.autoaid.dto.request.SubCatCompleteRequest;
import com.alpha5.autoaid.enums.ServiceEntryStatus;
import com.alpha5.autoaid.enums.SlotStatus;
import com.alpha5.autoaid.model.ServiceEntry;
import com.alpha5.autoaid.model.Slot;
import com.alpha5.autoaid.repository.RepairRepository;
import com.alpha5.autoaid.repository.ServiceEntryRepository;
import com.alpha5.autoaid.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public boolean checkEntryExists(SubCatCompleteRequest subCatCompleteRequest){
        ServiceEntry serviceEntry=serviceEntryRepository.findByRepair_RepairIdAndSubCategory_SubCatId(subCatCompleteRequest.getRepairId(),subCatCompleteRequest.getSubCatId());
        if(serviceEntry!=null){
            return true;
        }else return false;
    }
    public boolean checkWhetherNoneCompletedEntries(long repairId){
        List<ServiceEntry> serviceEntries=serviceEntryRepository.findAllByRepair_RepairId(repairId);
        List<ServiceEntry> entryListofRepair = serviceEntries.stream()
                .filter(serviceEntry -> !(serviceEntry.getServiceEntryStatus().equals(ServiceEntryStatus.COMPLETED)))
                .collect(Collectors.toList());
        if (entryListofRepair.isEmpty()){
            return false;
//            returns false if list is empty
        }else {
            return true;
        }
    }

    public void completeSubCat(SubCatCompleteRequest subCatCompleteRequest){
        ServiceEntry serviceEntry=serviceEntryRepository.findByRepair_RepairIdAndSubCategory_SubCatId(subCatCompleteRequest.getRepairId(),subCatCompleteRequest.getSubCatId());
        serviceEntry.setServiceEntryStatus(ServiceEntryStatus.COMPLETED);
        serviceEntryRepository.save(serviceEntry);
    }

    public boolean checkWhetherAllEntriesCompleted(RepairCompletedRequest repairCompletedRequest){
        List<ServiceEntry> serviceEntries=serviceEntryRepository.findAllByRepair_RepairIdAndSubCategory_Section_SectionName(repairCompletedRequest.getRepairId(),repairCompletedRequest.getSectionName());
        List<ServiceEntry> notCompletedList = serviceEntries.stream()
                .filter(serviceEntry -> !(serviceEntry.getServiceEntryStatus().equals(ServiceEntryStatus.COMPLETED)))
                .collect(Collectors.toList());
        if (notCompletedList.isEmpty()){
            return true;
        }else return false;
    }
    public void completeRepair(RepairCompletedRequest repairCompletedRequest){
        List<ServiceEntry> serviceEntries=serviceEntryRepository.findAllByRepair_RepairIdAndSubCategory_Section_SectionName(repairCompletedRequest.getRepairId(),repairCompletedRequest.getSectionName());
        Slot slot = serviceEntries.stream()
                .map(serviceEntry -> serviceEntry.getSlot())
                .iterator().next();
//        System.out.println(slot.getSlotName());
        slot.setStatus(SlotStatus.AVAILABLE);
        slotRepository.save(slot);
        getNextSlot(repairCompletedRequest.getRepairId());
    }

    public void getNextSlot(long repairId){

    }

}
