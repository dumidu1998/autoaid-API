package com.alpha5.autoaid.service;

import com.alpha5.autoaid.dto.request.*;
import com.alpha5.autoaid.dto.response.AddItemRespond;
import com.alpha5.autoaid.dto.response.AdminGetAssignedLeadTechResponse;
import com.alpha5.autoaid.dto.response.GetNextRepairResponse;
import com.alpha5.autoaid.dto.response.technician.GetEntryListResponse;
import com.alpha5.autoaid.dto.response.technician.GetUpcomingRepairResponse;
import com.alpha5.autoaid.enums.InventoryStatus;
import com.alpha5.autoaid.enums.ItemRequestStatus;
import com.alpha5.autoaid.enums.RepairStatus;
import com.alpha5.autoaid.enums.ServiceEntryStatus;
import com.alpha5.autoaid.enums.SlotStatus;
import com.alpha5.autoaid.model.*;
import com.alpha5.autoaid.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TechnicianService {

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private ServiceEntryRepository serviceEntryRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private RepairRepository repairRepository;

    @Autowired
    private ItemRequestRepository itemRequestRepository;

    @Autowired
    private InventryItemRepository inventryItemRepository;
    @Autowired
    private VehicleRepository vehicleRepository;

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
    public String getSection(long userId){
        try {
            return (sectionRepository.findByStaff(staffRepository.findByUserData_Id(userId)).getSectionName());
        }catch (Exception e){
            return null;
        }
    }
    public void assignTechnician(long techId,long repair,String section){
        Staff staff=staffRepository.findByStaffId(techId);
        List<ServiceEntry> serviceEntryList = serviceEntryRepository.findAllByRepair_RepairIdAndSubCategory_Section_SectionName(repair, section);
        serviceEntryList.forEach(serviceEntry -> serviceEntry.getEntryId());
        for(ServiceEntry serviceEntry:serviceEntryList){
            serviceEntry.setStaff(staff);
            serviceEntryRepository.save(serviceEntry);
        }
    }
    public AdminGetAssignedLeadTechResponse getTech(long repairId,String sectionName){
        AdminGetAssignedLeadTechResponse technician=new AdminGetAssignedLeadTechResponse();
        try {
            Staff staff= serviceEntryRepository.findAllByRepair_RepairIdAndSubCategory_Section_SectionName(repairId,sectionName)
                    .stream()
                    .map(serviceEntry -> serviceEntry.getStaff())
                    .findFirst()
                    .get();
            technician.setTechId(staff.getStaffId());
            technician.setFirstName(staff.getFirstName());
            technician.setLastname(staff.getLastName());
        }catch (Exception e){
            technician=null;
        }
        return technician;
    }

    public void completeSubCat(SubCatCompleteRequest subCatCompleteRequest) {
        Date date=new Date();
        ServiceEntry serviceEntry = serviceEntryRepository.findByRepair_RepairIdAndSubCategory_SubCatId(subCatCompleteRequest.getRepairId(), subCatCompleteRequest.getSubCatId());
        if(serviceEntry.getServiceEntryStatus().equals(ServiceEntryStatus.COMPLETED)){
            serviceEntry.setServiceEntryStatus(ServiceEntryStatus.ONGOING);
            serviceEntry.setCompletedTime(null);
        }else{
            serviceEntry.setServiceEntryStatus(ServiceEntryStatus.COMPLETED);
            serviceEntry.setCompletedTime(date);
        }
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

    public List<GetEntryListResponse> getEntryList(long repairId, String sectionName){
        List<GetEntryListResponse> getEntryListResponses=new ArrayList<>();
        List<ServiceEntry> entryList = serviceEntryRepository.findAllByRepair_RepairIdAndSubCategory_Section_SectionName(repairId, sectionName);
        for(ServiceEntry serviceEntry:entryList){
            GetEntryListResponse getEntryListResponse=new GetEntryListResponse();
            getEntryListResponse.setSubCatName(serviceEntry.getSubCategory().getSubCatName());
            getEntryListResponse.setEstimatedTime(serviceEntry.getEstimatedTime());
            getEntryListResponse.setDescription(serviceEntry.getDescription());
            getEntryListResponse.setSubCatId(serviceEntry.getSubCategory().getSubCatId());
            getEntryListResponse.setServiceEntryStatus(serviceEntry.getServiceEntryStatus());
            getEntryListResponses.add(getEntryListResponse);
        }
        return getEntryListResponses;
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
//                    serviceEntry.setStaff(newSlot.getStaff());
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
        System.out.println("+++++++++++++++++++++++++++++");
        serviceEntriesOfRepair.forEach(serviceEntry -> serviceEntry.getEntryId());

        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date=new Date();
        System.out.println(dateFormat.format(date));
        for (ServiceEntry serviceEntry:serviceEntriesOfRepair){
            serviceEntry.setServiceEntryStatus(ServiceEntryStatus.ONGOING);
            serviceEntry.setAssignedTime(date);
            serviceEntryRepository.save(serviceEntry);
        }
        slot.setStatus(SlotStatus.ONPROCESS);
        slotRepository.save(slot);
    }
    public List<GetUpcomingRepairResponse> getUpcomingRepairs(String sectionName) {
        //check if section has available slots
        List<Slot> availableList = slotRepository.findAllBySection_SectionNameAndStatusIs(sectionName, SlotStatus.AVAILABLE);
        Slot availableSlot=null;
        if(availableList.isEmpty()) {
            availableSlot=null;
        }else{ availableSlot = availableList.stream().findFirst().get();}

        List<GetUpcomingRepairResponse> getUpcomingRepairResponses = new ArrayList<>();

        //get pending repairs on section
        List<Long> repairIdList = serviceEntryRepository.findAllBySlot_Section_SectionNameAndServiceEntryStatusIs(sectionName, ServiceEntryStatus.PENDING)
                .stream()
                .map(serviceEntry -> serviceEntry.getRepair().getRepairId())
                .distinct()
                .collect(Collectors.toList());
        //check if slot has ongoing repairs

        if(repairIdList.isEmpty()){
            return null;
        }else {
            for (long repairId:repairIdList){
                GetUpcomingRepairResponse getUpcomingRepairResponse=new GetUpcomingRepairResponse();
                Repair repair=repairRepository.findByRepairId(repairId);
                List<ServiceEntry> entriesByIdAndSection = serviceEntryRepository.findAllByRepair_RepairIdAndSubCategory_Section_SectionName(repairId, sectionName);
                Long currentSlotId = entriesByIdAndSection
                        .stream()
                        .map(serviceEntry -> serviceEntry.getSlot().getSlotID())
                        .findFirst()
                        .get();
                getUpcomingRepairResponse.setRepairId(repair.getRepairId());
                getUpcomingRepairResponse.setVehicleNumber(repair.getVehicle().getVehicleNumber());
                getUpcomingRepairResponse.setVin(repair.getVehicle().getVin());

                if(availableSlot ==null){
                    getUpcomingRepairResponse.setBtnAct(false);
                }else{
                    for(ServiceEntry serviceEntry:entriesByIdAndSection){
                        serviceEntry.setSlot(availableSlot);
                        serviceEntryRepository.save(serviceEntry);
                    }
                    getUpcomingRepairResponse.setBtnAct(true);
                }

                getUpcomingRepairResponses.add(getUpcomingRepairResponse);
            }
            return getUpcomingRepairResponses;
        }
    }

    public void createItemRequest(AddItemRequest addItemRequest) {
        ItemRequest request = new ItemRequest();
        request.setIssuedDateTime(new Date());
        request.setQuantity(addItemRequest.getQuantity());
        request.setStatus(ItemRequestStatus.REQUESTED);
        request.setInvItem(inventryItemRepository.findByItemNo(addItemRequest.getItemNo()));
        request.setRepair(repairRepository.findByRepairId(addItemRequest.getRepairId()));
        request.setStaff(staffRepository.findByStaffId(addItemRequest.getStaffId()));

        itemRequestRepository.save(request);
    }

    public List<GetUpcomingRepairResponse> getOngoingRepairs(long userid) {
        List<GetUpcomingRepairResponse> getUpcomingRepairResponses = new ArrayList<>();
        Section section = sectionRepository.findByStaff_UserData_id(userid);
        List<Repair> repairs = repairRepository.findDistinctByServiceEntries_ServiceEntryStatusAndServiceEntries_SubCategory_Section(ServiceEntryStatus.ONGOING,section);
        for(Repair repair : repairs) {
            GetUpcomingRepairResponse d = new GetUpcomingRepairResponse();
            d.setRepairId(repair.getRepairId());
            d.setVin(repair.getVehicle().getVin());
            d.setVehicleNumber(repair.getVehicle().getVehicleNumber());
            d.setBtnAct(true);
            getUpcomingRepairResponses.add(d);
        }
        return getUpcomingRepairResponses;
    }

    public Vehicle getVehicleDetails(long repairid) {
        return repairRepository.findByRepairId(repairid).getVehicle();
    }
    public void updateRepairStatus(long repairid){
        Date date=new Date();
        Repair repair = repairRepository.findByRepairId(repairid);
        repair.setStatus(RepairStatus.COMPLETED);
        repair.setRepairCompletedDate(date);
        repairRepository.save(repair);
    }
}