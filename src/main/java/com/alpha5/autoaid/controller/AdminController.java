package com.alpha5.autoaid.controller;

import com.alpha5.autoaid.dto.request.*;
import com.alpha5.autoaid.dto.response.AddStaffRespond;
import com.alpha5.autoaid.dto.response.StaffListRespond;
import com.alpha5.autoaid.dto.response.GetStaffMemInfoRespond;
import com.alpha5.autoaid.enums.UserType;
import com.alpha5.autoaid.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity insertStaffDetails(@RequestBody AddStaffRequest addStaffRequest){
        String response;
        if(adminService.checkUserNameExists(addStaffRequest.getUserName())){
            response="Username Exists";
        }else if(adminService.checkEmailExists(addStaffRequest.getEmail())){
            response="Email Exists";
        }else if(adminService.checkContactExists(addStaffRequest.getContactNum())){
            response="Contact Number Exists";
        }else{
            return ResponseEntity.ok().body(adminService.insertStaff(addStaffRequest));
        }
       return ResponseEntity.badRequest().body(response);

    }
    //-------------XXX-----Staff Add-----XX-------------//


    //------------------Staff Handling NavBar Data------------------//
    @GetMapping("/getstaff/{user}")
    public ResponseEntity getAdmins(@PathVariable int user){
        UserType userType=null;
        switch (user){
            case 1:userType=UserType.ADMIN;
                break;
            case 2:userType=UserType.SERVICE_ADVISOR;
                break;
            case 3:userType=UserType.LEAD_TECHNICIAN;
                break;
            case 4:userType=UserType.CASHIER;
                break;
            case 5:userType=UserType.STOCK_KEEPER;
                break;
            case 6:userType=UserType.TECHNICIAN;
                break;
            default:
                ResponseEntity.badRequest().body("User Type Not Identified");
        }

        return ResponseEntity.ok().body( adminService.getStaffList(userType));
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

    @PostMapping("/add slot")
    public ResponseEntity addSlot(@RequestBody AddSlotRequest addSlotRequest){
        if(adminService.checkIfSlotExists(addSlotRequest.getSlotName())){
            return ResponseEntity.badRequest().body("Slot Exists");
        }else {
            adminService.addSlot(addSlotRequest);
            return ResponseEntity.ok().body("Slot Added");
        }
    }

    @PostMapping("/update/staff")
    public ResponseEntity updateStaffDetails(@RequestBody UpdateStaffRequest updateStaffRequest){
        String response;
        //check whether staff member exists
        if(adminService.checkStaffMemberExists(updateStaffRequest.getStaffId())) {
            response = "Staff Member Not exists";
        }else if(adminService.checkUserNameExistsInOtherUsers(updateStaffRequest.getUserName(),updateStaffRequest.getStaffId())){
            response= "Username is Already taken";
        }else if(adminService.checkEmailExistsInOtherUsers(updateStaffRequest.getEmail(),updateStaffRequest.getStaffId())){
            response= "Email is Already Exists";
        }else if(adminService.checkContactExistsInOtherUsers(updateStaffRequest.getContactNum(),updateStaffRequest.getStaffId())){
            response= "Change Contact Number";
        }else {
            adminService.updateStaff(updateStaffRequest);
            return ResponseEntity.ok().body("Staff Updated");

        }
        return ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/staff/activation")
    public ResponseEntity activateStaffAccount(@RequestBody StaffAccountActivateRequest staffAccountActivateRequest){
//        System.out.println(staffAccountActivateRequest.getStaffId());
        if(adminService.checkStaffMemberExists(staffAccountActivateRequest.getStaffId())){
            return ResponseEntity.badRequest().body("Member Not Exists");
        }else{
            return ResponseEntity.ok().body(adminService.accountActivation(staffAccountActivateRequest));
        }
    }

}

