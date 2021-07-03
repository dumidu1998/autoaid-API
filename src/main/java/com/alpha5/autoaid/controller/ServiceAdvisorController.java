package com.alpha5.autoaid.controller;

import com.alpha5.autoaid.dto.request.VehicleAddRequest;
import com.alpha5.autoaid.dto.response.VehicleAddResponse;
import com.alpha5.autoaid.service.ServiceAdvisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/advisor")
public class ServiceAdvisorController {

    @Autowired
    ServiceAdvisorService serviceAdvisorService;

    @PostMapping("/add vehicle")
    public VehicleAddResponse addVehicle(@RequestBody VehicleAddRequest vehicleAddRequest){
        VehicleAddResponse response= serviceAdvisorService.addvehicle(vehicleAddRequest);
        return response;

    }
}
