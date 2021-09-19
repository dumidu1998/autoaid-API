package com.alpha5.autoaid.controller;

import com.alpha5.autoaid.dto.response.ExpenseResponse;
import com.alpha5.autoaid.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/customer")
@RestController
public class CustomerController {

    @Autowired
    VehicleService vehicleService;

    @GetMapping("expenses/{id}") //userid is comming to here
    public ExpenseResponse getSummary(@PathVariable("id") long id) {
        return vehicleService.getSummaryByCustomer(id);
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

    


}
