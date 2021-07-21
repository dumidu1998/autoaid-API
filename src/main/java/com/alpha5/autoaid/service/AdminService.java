package com.alpha5.autoaid.service;


import com.alpha5.autoaid.dto.response.*;
import com.alpha5.autoaid.enums.UserType;
import com.alpha5.autoaid.model.Staff;


import com.alpha5.autoaid.model.UserData;
import com.alpha5.autoaid.repository.StaffRepository;
import com.alpha5.autoaid.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private UserRepository userRepository;
    //-----------_______________________--------------------ADD to TO Real ------------____________________-----//
    //------------------Staff Handling NavBar Data------------------//
    public List<AdminListRespond> getAdmins(){
        List<UserData> admins= userRepository.findAllByUserType(UserType.ADMIN);
        List<AdminListRespond> outlist=new ArrayList<AdminListRespond>();
        for (UserData entity: admins){
            Staff staffMember= new Staff();
            AdminListRespond adminList=new AdminListRespond();
            adminList.setFirstName(staffMember.getFirstName());
            adminList.setLastname(staffMember.getLastName());
            adminList.setId(staffMember.getStaffId());
            outlist.add(adminList);
        }
        return outlist;
    }

}

