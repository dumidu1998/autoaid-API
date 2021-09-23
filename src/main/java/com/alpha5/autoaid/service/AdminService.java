package com.alpha5.autoaid.service;


import com.alpha5.autoaid.dto.request.*;
import com.alpha5.autoaid.dto.response.*;
import com.alpha5.autoaid.enums.*;
import com.alpha5.autoaid.model.*;
import com.alpha5.autoaid.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {
    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private RepairRepository repairRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private ServiceEntryRepository serviceEntryRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private SpecialItemRequestRepository specialItemRequestRepository;

    //returns false if member exists
    public boolean checkStaffMemberExists(long staffId){
        if(staffRepository.findByStaffId(staffId)!=null){
            return false;
        }else return true;
    }

    public boolean checkUserNameExists(String username){
        if (userRepository.findByUserName(username)!=null){
            return true;
        }else return false;
    }
    public boolean checkEmailExists(String email){
        if (userRepository.findByEmail(email)!=null){
            return true;
        }else return false;
    }
    public boolean checkContactExists(String contact){
        if (userRepository.findByContactNo(contact)!=null){
            return true;
        }else return false;
    }

    public boolean checkUserNameExistsInOtherUsers(String username, long staffId){
        if (staffRepository.findByUserData_UserName(username)==staffRepository.findByStaffId(staffId)){
            return false;
        }else if (userRepository.findByUserName(username)!=null){
            return true;
        }else return false;
    }

    public boolean checkEmailExistsInOtherUsers(String email, long staffId){
        if (staffRepository.findByUserData_Email(email)==staffRepository.findByStaffId(staffId)){
            return false;
        }else if (userRepository.findByEmail(email)!=null){
            return true;
        }else return false;
    }

    public boolean checkContactExistsInOtherUsers(String contact, long staffId){
        if (staffRepository.findByUserData_ContactNo(contact)==staffRepository.findByStaffId(staffId)){
            return false;
        }else if (userRepository.findByContactNo(contact)!=null){
            return true;
        }else return false;
    }

    public boolean checkIfSectionExists(String sectionName){
        if(sectionRepository.findBySectionName(sectionName) != null){
            return true;
        }else return false;
    }
    public boolean checkIfRepairSubCatExists(AddRepairSubCatRequest addRepairSubCatRequest){
        Section sectionRetrieved=sectionRepository.findBySectionName(addRepairSubCatRequest.getSectionName());
        if (subCategoryRepository.findBySubCatNameAndSection(addRepairSubCatRequest.getSubCatName(),sectionRetrieved)!=null){
            return true;
        }else return false;
    }

    public boolean checkIfSlotExists(String slotName){
        Slot slot=slotRepository.findBySlotName(slotName);
        if(slot!=null){
            return true;
        }else return false;
    }

    public List<String> getSectionList(){
        List<String> sectionNameList=  sectionRepository.findAll().stream().map(section -> section.getSectionName()).collect(Collectors.toList());
        return sectionNameList;
    }

    //------------------Staff Add------------------//
    public AddStaffRespond insertStaff(AddStaffRequest addStaffRequest){
    //object 2
        UserData userData = new UserData();
        Staff staff =new Staff();
        //assign values to user data
        userData.setPassword(passwordEncoder.encode("Staff123"));
        userData.setAddress(addStaffRequest.getAddress());
        userData.setCity(addStaffRequest.getCity());
        userData.setContactNo(addStaffRequest.getContactNum());
        userData.setEmail(addStaffRequest.getEmail());
        userData.setUserName(addStaffRequest.getUserName());
        userData.setUserType(addStaffRequest.getUserType());
        userData.setUserStatus(UserStatus.ACTIVATED);
        //assign values ti staff

        UserData newuserdata = userRepository.save(userData);

        staff.setFirstName(addStaffRequest.getFirstName());
        staff.setLastName(addStaffRequest.getLastName());
        staff.setUserData(newuserdata);

        Staff savedstaff=staffRepository.save(staff);

        AddStaffRespond addStaffRespond =new AddStaffRespond();
        addStaffRespond.setFisrtName(savedstaff.getFirstName());
        addStaffRespond.setSid(savedstaff.getStaffId());

        return addStaffRespond;
    }

    //------------------Staff Handling NavBar Data------------------//
    public List<StaffListRespond> getStaffList(UserType userType){
        List<UserData> admins= userRepository.findAllByUserType(userType);
        List<StaffListRespond> outlist=new ArrayList<StaffListRespond>();
        for (UserData entity: admins){
            Staff staffMember= staffRepository.findByUserData(entity);
            StaffListRespond staffList=new StaffListRespond();
            staffList.setFirstName(staffMember.getFirstName());
            staffList.setLastname(staffMember.getLastName());
            staffList.setId(staffMember.getStaffId());
            outlist.add(staffList);
        }
        return outlist;
    }

    //-------------------get nxt staff Id to the form ------------------//
    public long getNewStaffId(){
        long getNewId=staffRepository.getMaxStaffId();
        return getNewId+1;
    }

    public AdminGetAssignedLeadTechResponse getTech(String sectionName){
        AdminGetAssignedLeadTechResponse technician=new AdminGetAssignedLeadTechResponse();
        try {
            Staff staff= sectionRepository.findBySectionName(sectionName).getStaff();
            technician.setTechId(staff.getStaffId());
            technician.setFirstName(staff.getFirstName());
            technician.setLastname(staff.getLastName());
        }catch (Exception e){
            technician=null;
        }
        return technician;
    }

    public void setTech(long techId,String sectionName){
        Staff staff=staffRepository.findByStaffId(techId);
        Section section=sectionRepository.findBySectionName(sectionName);
        section.setStaff(staff);
        sectionRepository.save(section);
    }
    public void slotDeactivate(String slotName){
        Slot slot=slotRepository.findBySlotName(slotName);
        slot.setStatus(SlotStatus.NOTAVAILABLE);
        slotRepository.save(slot);
    }
    public void slotActivate(String slotName){
        Slot slot=slotRepository.findBySlotName(slotName);
        slot.setStatus(SlotStatus.AVAILABLE);
        slotRepository.save(slot);
    }
    public String activeStatus(String slotName){
        Slot slot=slotRepository.findBySlotName(slotName);
        if(slot.getStatus().equals(SlotStatus.NOTAVAILABLE)){
            return "ACTIVATE";
        }else{
            return "DEACTIVATE";
        }
    }
    public boolean checkSlotAvailable(String slotName){
        Slot slot=slotRepository.findBySlotName(slotName);
        if(slot.getStatus().equals(SlotStatus.AVAILABLE)){
            return true;
        }else return false;
    }
    public boolean checkSlotNotAvailable(String slotName){
        Slot slot=slotRepository.findBySlotName(slotName);
        if(slot.getStatus().equals(SlotStatus.NOTAVAILABLE)){
            return true;
        }else return false;
    }

    //-------------------get nxt staff Mem Info ------------------//
    public GetStaffMemInfoRespond getStaffMemInfo(long sid){
        Staff memInfo = staffRepository.findByStaffId(sid);
        GetStaffMemInfoRespond response =new GetStaffMemInfoRespond();
        response.setStaffId(memInfo.getStaffId());
        response.setFirstName(memInfo.getFirstName());
        response.setLastName(memInfo.getLastName());

        UserData user_data= memInfo.getUserData();
        response.setAddress(user_data.getAddress());
        response.setCity(user_data.getCity());
        response.setEmail(user_data.getEmail());
        response.setUserType(user_data.getUserType());
        response.setUserStatus(user_data.getUserStatus());

        response.setContactNum(user_data.getContactNo());
        response.setUserName(user_data.getUserName());
        return response;
    }

//-----------XXXX--------get nxt staff Mem Info -------XXX-----------//

    public void addSection(AddSectionRequest addSectionRequest){
            Section newSec= new Section();
            newSec.setSectionName(addSectionRequest.getSectionName());
            sectionRepository.save(newSec);
    }


    public void addRepairSubCategory(AddRepairSubCatRequest addRepairSubCatRequest){
        SubCategory newSubCategory=new SubCategory();
        Section subCatSection=sectionRepository.findBySectionName(addRepairSubCatRequest.getSectionName());
        newSubCategory.setSection(subCatSection);
        newSubCategory.setSubCatName(addRepairSubCatRequest.getSubCatName());
        newSubCategory.setTime(addRepairSubCatRequest.getTime());

        subCategoryRepository.save(newSubCategory);
    }

    public void addSlot(AddSlotRequest addSlotRequest){
        Section section=sectionRepository.findBySectionId(addSlotRequest.getSectionId());
        Slot slot=new Slot();
        slot.setSlotName(addSlotRequest.getSlotName());
        slot.setSection(section);
        slot.setStatus(SlotStatus.AVAILABLE);

        slotRepository.save(slot);
    }

    public void updateStaff(UpdateStaffRequest updateStaffRequest){
        Staff staffMember= staffRepository.findByStaffId(updateStaffRequest.getStaffId());
        UserData userData= staffMember.getUserData();
        System.out.println(userData.getContactNo());

        // add data to userData
        userData.setEmail(updateStaffRequest.getEmail());
        userData.setContactNo(updateStaffRequest.getContactNum());
        userData.setUserType(updateStaffRequest.getUserType());
        userData.setUserName(updateStaffRequest.getUserName());
        userData.setAddress(updateStaffRequest.getAddress());
        userData.setCity(updateStaffRequest.getCity());
        if(updateStaffRequest.isPassword()){
            userData.setPassword(passwordEncoder.encode("Staff123"));
        }
        userRepository.save(userData);

        // add data to staff member
        staffMember.setFirstName(updateStaffRequest.getFirstName());
        staffMember.setLastName(updateStaffRequest.getLastName());
        staffMember.setUserData(userData);

        staffRepository.save(staffMember);
    }
    //Activate or deactivate staff accounts
    public String accountActivation(StaffAccountActivateRequest staffAccountActivateRequest){
        String response;
        UserData userData=staffRepository.findByStaffId(staffAccountActivateRequest.getStaffId()).getUserData();
        if(staffAccountActivateRequest.getUserStatus()==UserStatus.ACTIVATED){
            userData.setUserStatus(UserStatus.DEACTIVATED);
            response="User Deactivated";
            userRepository.save(userData);
        }else if(staffAccountActivateRequest.getUserStatus()==UserStatus.DEACTIVATED){
            userData.setUserStatus(UserStatus.ACTIVATED);
            response="User Activated";
            userRepository.save(userData);
        }else {
            response="User Status Unidentified";
        }
        return response;
    }

    public List<AdminGetSectionResponse> getAllSectionsDetails(){
        List<AdminGetSectionResponse> adminGetSectionResponses=new ArrayList<>();
        List<Section> sections=sectionRepository.findAll();
        for(Section section:sections){
            AdminGetSectionResponse adminGetSectionResponse=new AdminGetSectionResponse();
            int numOfSlots=slotRepository.getTotalCount(section.getSectionId());
            int freeSlots=slotRepository.getFreeSlotCount(section.getSectionId());
            int notAvailSlots=slotRepository.getNotAvailSlots(section.getSectionId());
            String staffName="";
            try {
                staffName=section.getStaff().getFirstName()+" "+section.getStaff().getLastName();
            }catch  (Exception e){
                staffName="UNASSIGNED";
            }
            adminGetSectionResponse.setSectionName(section.getSectionName());
            adminGetSectionResponse.setNumberOfSlots(numOfSlots);
            adminGetSectionResponse.setFreeSlots(freeSlots);
            adminGetSectionResponse.setOccupiedSlots(numOfSlots-notAvailSlots);
            adminGetSectionResponse.setTechnicianName(staffName);
            adminGetSectionResponse.setSectionId(section.getSectionId());

            adminGetSectionResponses.add(adminGetSectionResponse);
        }

        return adminGetSectionResponses;
    }
    public List<AdminGetSlotDetailsResponse> getSlotsDetails(String sectionName){
        List<AdminGetSlotDetailsResponse> adminGetSlotDetailsResponses=new ArrayList<>();
        List<Slot> slots=slotRepository.findAllBySection_SectionName(sectionName);

        for (Slot slot:slots){
            AdminGetSlotDetailsResponse adminGetSlotDetailsResponse=new AdminGetSlotDetailsResponse();
            String vehicleNumber="";
            String technicianName="";
            try {
                List<ServiceEntry> entryList = serviceEntryRepository.findAllBySlot_SlotID(slot.getSlotID())
                        .stream()
                        .filter(serviceEntry -> serviceEntry.getServiceEntryStatus().equals(ServiceEntryStatus.ONGOING) || serviceEntry.getServiceEntryStatus().equals(ServiceEntryStatus.ASSIGNED))
                        .collect(Collectors.toList());
                vehicleNumber=entryList.stream()
                        .map(serviceEntry -> serviceEntry.getRepair().getVehicle().getVehicleNumber()).findFirst().get();
                technicianName=entryList.stream()
                        .map(serviceEntry -> serviceEntry.getStaff().getFirstName()+" "+serviceEntry.getStaff().getLastName())
                        .findFirst().get();
            }catch (Exception e){
                vehicleNumber=null;
                technicianName=null;
            }
//            try {
//                technicianName=;
//                        slot.getStaff().getFirstName()+" "+slot.getStaff().getLastName();
//            }catch (Exception e){
//                technicianName=null;
//            }
            adminGetSlotDetailsResponse.setSlotId(slot.getSlotID());
            adminGetSlotDetailsResponse.setSlotName(slot.getSlotName());
            adminGetSlotDetailsResponse.setSlotStatus(slot.getStatus());
            adminGetSlotDetailsResponse.setAssignedVehicle(vehicleNumber);
            adminGetSlotDetailsResponse.setAssignedTechnicianName(technicianName);

            adminGetSlotDetailsResponses.add(adminGetSlotDetailsResponse);
        }

        return adminGetSlotDetailsResponses;
    }

    public List<AdminSectionsOngoingVehicleResponse> getOngoingVehicles(String sectionName){
        List<AdminSectionsOngoingVehicleResponse> adminSectionsOngoingVehicleResponses=new ArrayList<>();
        List<Slot> slots=slotRepository.findAllBySection_SectionName(sectionName)
                .stream()
                .filter(slot -> !(slot.getStatus().equals(SlotStatus.AVAILABLE)||slot.getStatus().equals(SlotStatus.NOTAVAILABLE)))
                .collect(Collectors.toList());

        for (Slot slot:slots){
            AdminSectionsOngoingVehicleResponse adminSectionsOngoingVehicleResponse=new AdminSectionsOngoingVehicleResponse();
            String vin="";
            String customerName="";
            String contactNo="";
            String vehicleNo="";

            try {
                vin=serviceEntryRepository.findAllBySlot_SlotID(slot.getSlotID())
                        .stream()
                        .filter(serviceEntry -> serviceEntry.getServiceEntryStatus().equals(ServiceEntryStatus.ONGOING)||serviceEntry.getServiceEntryStatus().equals(ServiceEntryStatus.ASSIGNED))
                        .map(serviceEntry -> serviceEntry.getRepair().getVehicle().getVin()).findFirst().get();
            }catch (Exception e){
                vin=null;
            }
            try {
                Vehicle vehicle=vehicleRepository.findByVin(vin);
                customerName=vehicle.getCustomer().getFirstName()+" "+vehicle.getCustomer().getLastName();
                contactNo=vehicle.getCustomer().getUserData().getContactNo();
                vehicleNo=vehicle.getVehicleNumber();

            }catch (Exception e){
                customerName=vehicleNo=contactNo=null;
            }
            adminSectionsOngoingVehicleResponse.setVin(vin);
            adminSectionsOngoingVehicleResponse.setVehicleNumber(vehicleNo);
            adminSectionsOngoingVehicleResponse.setRepairStatus(serviceEntryRepository.findAllBySlot_SlotID(slot.getSlotID())
                                                            .stream()
                                                            .filter(serviceEntry -> serviceEntry.getServiceEntryStatus().equals(ServiceEntryStatus.ONGOING)||serviceEntry.getServiceEntryStatus().equals(ServiceEntryStatus.ASSIGNED))
                                                            .map(serviceEntry -> serviceEntry.getRepair().getStatus()).findFirst().get());
            adminSectionsOngoingVehicleResponse.setCustomerName(customerName);
            adminSectionsOngoingVehicleResponse.setContactNo(contactNo);
            adminSectionsOngoingVehicleResponses.add(adminSectionsOngoingVehicleResponse);
        }

        return adminSectionsOngoingVehicleResponses;
    }

    public List<TransactionResponse> getTransactions() {
        List<TransactionResponse> response = new ArrayList<>();
        Date dt=new Date();

        List<Invoice> invoices = invoiceRepository.findAllByInvoiceDateBetween(new Date(dt.getTime() - (1000 * 60 * 60 * 24)),new Date());

        for(Invoice invoice:invoices){
            TransactionResponse newdata = new TransactionResponse();
            newdata.setAmount(invoice.getAmount());
            newdata.setVehicleNo(invoice.getRepair().getVehicle().getVehicleNumber());

            response.add(newdata);
        }

        return response;
    }

    public List<SpecialRequestResponse> getspecialrequests() {
        List<SpecialRequestResponse> response = new ArrayList<>();

        List<SpecialItemRequest> list = specialItemRequestRepository.findAllByStatus(SpecialItemRequestStatus.REQUESTED);
        for(SpecialItemRequest request:list){
            SpecialRequestResponse out = new SpecialRequestResponse();

            out.setItemName(request.getItemName());
            out.setQty(request.getQuantity());
            out.setRequestId(request.getSpecialRequestId());
            out.setVehicleNo(request.getRepair().getVehicle().getVehicleNumber());
            out.setTechieName(request.getStaff().getFirstName()+" "+request.getStaff().getLastName());
            out.setSectionName(null);

            response.add(out);

        }

        return response;
    }

    public boolean approveRejectRequest(ApproveRejectRequest req) {
        SpecialItemRequest item = specialItemRequestRepository.findBySpecialRequestId(req.getRequestId());

        item.setPrice(req.getPrice());
        item.setStatus(req.getStatus());
        item.setApprovedDateTime(new Date());
        try{
            specialItemRequestRepository.save(item);
            return true;
        }catch (Exception e) {

            return false;
        }

    }

    public SummaryResponse getStatistics() {

        Date d1= new Date();
        int old =  customerRepository.findAllByRegisteredDateBefore(new Date(d1.getTime() - (1000 * 60 * 60 * 24*7))).size();
        int now =  customerRepository.findAllByRegisteredDateBefore(new Date()).size();
        System.out.println(old);
        System.out.println(now);
        double grow=0;
        if(old==0){
            grow = 0;
        }else {
            grow = (double) (now - old) / old * 100;
        }
        int repairsold=repairRepository.findAllByRepairCompletedDateIsBefore(new Date(d1.getTime() - (1000 * 60 * 60 * 24*7))).size();
        int repairsnew=repairRepository.findAllByRepairCompletedDateIsBefore(new Date()).size();

        System.out.println("repairsnew = " + repairsnew);
        System.out.println("repairsnew = " + repairsold);
        if(repairsold==0){repairsold=1;}

        BigDecimal monthsales = invoiceRepository.findsuminmonth();

        System.out.println(monthsales);

        int employees = staffRepository.findAll().size();

        System.out.println(employees);


        SummaryResponse response = new SummaryResponse(now,grow,repairsnew,(double)((repairsnew-repairsold)/repairsold)*100,monthsales,employees);

        return response;
    }
}

