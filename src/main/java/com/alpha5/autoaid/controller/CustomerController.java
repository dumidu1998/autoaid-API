package com.alpha5.autoaid.controller;

import com.alpha5.autoaid.model.Vehicle;
import com.alpha5.autoaid.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Vehicle> getVehicles(@PathVariable("id") String ii){
        return vehicleService.getVehicleById(ii);
    }

}
