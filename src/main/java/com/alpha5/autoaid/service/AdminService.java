package com.alpha5.autoaid.service;


import com.alpha5.autoaid.dto.request.*;
import com.alpha5.autoaid.dto.response.AddStaffRespond;
import com.alpha5.autoaid.dto.response.StaffListRespond;
import com.alpha5.autoaid.dto.response.GetStaffMemInfoRespond;
import com.alpha5.autoaid.enums.SlotStatus;
import com.alpha5.autoaid.enums.UserStatus;
import com.alpha5.autoaid.model.*;
import com.alpha5.autoaid.enums.UserType;
import com.alpha5.autoaid.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private SlotRepository slotRepository;

    public boolean checkStaffMemberExists(long staffId){
        if(staffRepository.findByStaffId(staffId)!=null){
            return false;
        }else return true;
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
        long getnewid=staffRepository.getMaxStaffId();
        return getnewid+1;
    }

    //-------------------get nxt staff Mem Info ------------------//
    public GetStaffMemInfoRespond getStaffMemInfo(long sid){
        Staff memInfo = staffRepository.findByStaffId(sid);
        GetStaffMemInfoRespond response =new GetStaffMemInfoRespond();
        response.setFirstName(memInfo.getFirstName());
        response.setLastName(memInfo.getLastName());

        UserData user_data= memInfo.getUserData();
        response.setAddress(user_data.getAddress());
        response.setCity(user_data.getCity());
        response.setEmail(user_data.getEmail());
//        response.setPassword(user_data.getPassword());
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

        // add data to userData
        userData.setEmail(updateStaffRequest.getEmail());
        userData.setContactNo(updateStaffRequest.getContactNum());
        userData.setUserType(updateStaffRequest.getUserType());
        userData.setUserName(updateStaffRequest.getUserName());
        userData.setAddress(updateStaffRequest.getAddress());
        userData.setCity(updateStaffRequest.getCity());
        if(updateStaffRequest.isChangePassword()){
            userData.setPassword(passwordEncoder.encode("Staff123"));
        }
        userRepository.save(userData);

        // add data to staff member
        staffMember.setFirstName(updateStaffRequest.getFirstName());
        staffMember.setLastName(updateStaffRequest.getLastName());
        staffMember.setUserData(userData);

        staffRepository.save(staffMember);
    }
}

