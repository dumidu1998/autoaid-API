package com.alpha5.autoaid.controller;

import com.alpha5.autoaid.dto.response.ExpenseResponse;
import com.alpha5.autoaid.model.Vehicle;
import com.alpha5.autoaid.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/customer")
@RestController
public class CustomerController {

    @Autowired
    VehicleService vehicleService;

    @GetMapping("/{id}")
    public List<Vehicle> getVehicles(@PathVariable("id") long id) {
        return vehicleService.getVehicleById(id);
    }

    @GetMapping("byemail/{email}")
    public List<Vehicle> getVehicles(@PathVariable("email") String email) {
        return vehicleService.getVehicleByEmail(email);
    }

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

}
