package com.alpha5.autoaid.controller;

import com.alpha5.autoaid.dto.response.*;
import com.alpha5.autoaid.model.Staff;
import com.alpha5.autoaid.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin")
@RestController
public class AdminController {

    @Autowired
    AdminService adminService;



    //-----------_______________________--------------------ADD to TO Real ------------____________________-----//
    //------------------Staff Handling NavBar Data------------------//
    @GetMapping("getadmins")
    public List <AdminListRespond> getAdmins(){
        return adminService.getAdmins();
    }

    //------------XXX------Staff Handling NavBar Data------XXX-------------//

}

