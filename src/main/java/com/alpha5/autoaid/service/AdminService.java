package com.alpha5.autoaid.service;

import com.alpha5.autoaid.model.Staff;
import com.alpha5.autoaid.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private StaffRepository staffRepository;

    private String staffRegister(Staff staff){
        if(staffRepository.findByContactNoAndEmail(staff.getContactNo(),staff.getEmail())!=null){
            throw new RuntimeException("User email and contact already taken");
        }
        return "Done";
    }
}
