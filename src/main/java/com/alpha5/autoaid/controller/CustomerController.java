package com.alpha5.autoaid.controller;

import com.alpha5.autoaid.dto.request.CustomerProfileDetailsRequest;
import com.alpha5.autoaid.dto.request.CustomerProfileUpdateRequest;
import com.alpha5.autoaid.dto.response.ExpenseResponse;
import com.alpha5.autoaid.service.AdminService;
import com.alpha5.autoaid.service.AuthService;
import com.alpha5.autoaid.service.CustomerService;
import com.alpha5.autoaid.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/customer")
@RestController
public class CustomerController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AuthService authService;


    @GetMapping("expenses/{id}") //userid is comming to here
    public ResponseEntity getSummary(@PathVariable("id") long id) {
        try {
            ExpenseResponse expenseResponse = vehicleService.getSummaryByCustomer(id);
            return ResponseEntity.ok().body(expenseResponse);
        }catch (Exception e){
            return ResponseEntity.ok(new ExpenseResponse(0,0,0,0,0));
        }
    }

    @GetMapping("/vehicles/{email}")
    public ResponseEntity getVehiclesbyemail(@PathVariable String email){
        return ResponseEntity.ok(vehicleService.getVehicleByEmail(email));
    }

    @GetMapping("/vehiclesbyuserid/{id}")
    public ResponseEntity getVehiclesbyemail(@PathVariable long id){
        return ResponseEntity.ok(vehicleService.getVehicleByUserId(id));
    }

    @GetMapping("/vehicledetails/{id}")//vehicle Id
    public ResponseEntity getVehicleDetailsByVid(@PathVariable long id){
        return ResponseEntity.ok(vehicleService.getDetailsByVid(id));
    }

    @GetMapping("/vehicleexpenses/{id}")//vehicle Id
    public ResponseEntity getVehicleExpensesByVid(@PathVariable long id){
        try {
            ExpenseResponse expenseResponse = vehicleService.getExpensesByVid(id);
            return ResponseEntity.ok().body(expenseResponse);
        }catch (Exception e){
            return ResponseEntity.ok(new ExpenseResponse(0,0,0,0,0));
        }
    }

    @GetMapping("/vehicleservices/{id}")//vehicle Id
    public ResponseEntity getVehicleServicesByVid(@PathVariable long id){
        return ResponseEntity.ok(vehicleService.getCompletedRepairsByVid(id));
    }

    @GetMapping("/ongoingservices/{id}")//customer Id
    public ResponseEntity getongoingService(@PathVariable long id){
        return ResponseEntity.ok(vehicleService.getOngoingServices(id));
    }

    @GetMapping("/getdocid/{id}")//repair Id
    public ResponseEntity getDocId(@PathVariable long id){
        return ResponseEntity.ok(vehicleService.getDocId(id));
    }

    @GetMapping("/cusvehiclesbyuserid/{id}")
    public ResponseEntity getVehiclesbyuserid(@PathVariable long id){
        return ResponseEntity.ok(vehicleService.getCusVehicleByUserId(id));
    }
    @PostMapping("/getuserDetails")
    public ResponseEntity getUserDetails(@RequestBody CustomerProfileDetailsRequest customerProfileDetailsRequest){
        if(customerService.findByUserIdAndUsername(customerProfileDetailsRequest)){
            return ResponseEntity.ok().body(customerService.getProfileDetails(customerProfileDetailsRequest));
        }else{
            return ResponseEntity.badRequest().body("Invalid user");
        }
    }
    @PostMapping("/update/user")
    public ResponseEntity updateUserDetails(@RequestBody CustomerProfileUpdateRequest customerProfileUpdateRequest){
        String response=null;
        if(!(authService.checkIfUserIdExistsOnUserType(customerProfileUpdateRequest.getUserId(),customerProfileUpdateRequest.getUserType()))){
            response="Invalid User";
        }else if(customerService.checkEmailExistsInOtherUsers(customerProfileUpdateRequest.getEmail(),customerProfileUpdateRequest.getUserId())){
                response= "Email is Already Exists";
        }else if(customerService.checkContactExistsInOtherUsers(customerProfileUpdateRequest.getContactNo(),customerProfileUpdateRequest.getUserId())){
                response= "Change Contact Number";
        }else {
            customerService.updateUser(customerProfileUpdateRequest);
            return ResponseEntity.ok().body("Updated");
        }
        return ResponseEntity.badRequest().body(response);
    }

    @GetMapping("/getcharts/{id}")
    public ResponseEntity getCharts(@PathVariable long id){
        return ResponseEntity.ok(vehicleService.getcharts(id));
    }

    @GetMapping("/getcharts2/{id}")
    public ResponseEntity getCharts2(@PathVariable long id){
        return ResponseEntity.ok(vehicleService.getcharts2(id));
    }

}
