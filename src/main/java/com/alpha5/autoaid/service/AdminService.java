package com.alpha5.autoaid.service;


import com.alpha5.autoaid.dto.response.*;
import com.alpha5.autoaid.enums.StaffRole;
import com.alpha5.autoaid.model.Staff;


import com.alpha5.autoaid.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;

import javax.swing.text.SimpleAttributeSet;
import java.awt.font.TextHitInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.SocketHandler;

@Service
public class AdminService {
    @Autowired
    private StaffRepository staffRepository;
    //-----------_______________________--------------------ADD to TO Real ------------____________________-----//
    //------------------Staff Handling NavBar Data------------------//
    public List<AdminListRespond> getAdmins(){
        List<Staff> admins= staffRepository.findAllByRole(StaffRole.ADMIN);
        List<AdminListRespond> outlist=new ArrayList<AdminListRespond>();
        for (Staff entity: admins){
            AdminListRespond adminList=new AdminListRespond();
            adminList.setFirstName(entity.getFirstName());
            adminList.setLastname(entity.getLastName());
            adminList.setId(entity.getStaffId());
            outlist.add(adminList);
        }
        return outlist;
    }

}

