package com.alpha5.autoaid.controller;

import com.alpha5.autoaid.dto.request.AddStaffRequest;
import com.alpha5.autoaid.dto.response.AddStaffRespond;
import com.alpha5.autoaid.dto.response.AdminListRespond;
import com.alpha5.autoaid.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin")
@RestController
public class AdminController {

    @Autowired
    AdminService adminService;



    //-----------_______________________--------------------ADD to TO Real ------------____________________-----//

    //------------------Staff Add------------------//
    @PostMapping("addstaff")
    public AddStaffRespond insertStaffDetails(@RequestBody AddStaffRequest addStaffRequest){
        return adminService.insertStaff(addStaffRequest);
    }
    //-------------XXX-----Staff Add-----XX-------------//


    //------------------Staff Handling NavBar Data------------------//
    @GetMapping("getadmins")
    public List <AdminListRespond> getAdmins(){
        return adminService.getAdmins();
    }

    //------------XXX------Staff Handling NavBar Data------XXX-------------//

}

