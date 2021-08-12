package com.alpha5.autoaid.controller;

import com.alpha5.autoaid.dto.request.AddRepairSubCatRequest;
import com.alpha5.autoaid.dto.request.AddSectionRequest;
import com.alpha5.autoaid.dto.request.AddStaffRequest;
import com.alpha5.autoaid.dto.response.AddStaffRespond;
import com.alpha5.autoaid.dto.response.AdminListRespond;
import com.alpha5.autoaid.dto.response.GetStaffMemInfoRespond;
import com.alpha5.autoaid.dto.response.*;
import com.alpha5.autoaid.model.Staff;
import com.alpha5.autoaid.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin")
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    //-----------_______________________--------------------ADD to TO Real ------------____________________-----//
    //------------------Staff Add------------------//
    @PostMapping("/addstaff")
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

    //------------------Get staff next id------------------//
    @GetMapping("getnextstaffid")
    public long getNextStaffId(){
        return adminService.getNewStaffId();
    }

    //------------XXX-----Get staff next id------XXX-------------//

    //------------------Get staff Mem Info when click the manage btn in satff list------------------//
    @GetMapping("getstaffmeminfo/{sid}")
    public GetStaffMemInfoRespond getStaffMemInfo(@PathVariable long sid){

        return adminService.getStaffMemInfo(sid);
    }

    //------------XXX-----Get staff Mem Info when click the manage btn in satff list------XXX-------------//

    @PostMapping("/addSection")
    public ResponseEntity addSection(@RequestBody AddSectionRequest addSectionRequest){
        if(adminService.checkIfSectionExists(addSectionRequest.getSectionName())){
            return ResponseEntity.badRequest().body("Section Already Exists");
        }else {
            adminService.addSection(addSectionRequest);
            return ResponseEntity.ok().body("Section Added Successfully");
        }
    }

    @PostMapping("/repair/addSubCategory")
    public ResponseEntity addRepairSubCat(@RequestBody AddRepairSubCatRequest addRepairSubCatRequest){

        if(adminService.checkIfRepairSubCatExists(addRepairSubCatRequest)){
            return ResponseEntity.badRequest().body("Sub category Exists");
        }else{
            adminService.addRepairSubCategory(addRepairSubCatRequest);
            return ResponseEntity.ok().body("Entered and Saved ");
        }

    }

}

