package com.alpha5.autoaid.controller;

import com.alpha5.autoaid.dto.request.VehicleDetailsAutofillRequest;
import com.alpha5.autoaid.dto.response.VehicleDetailsAutofillResponse;
import com.alpha5.autoaid.model.Vehicle;
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

    @PostMapping("/get vehicle")
    public VehicleDetailsAutofillResponse getVehicleDetails(@RequestBody VehicleDetailsAutofillRequest vehicleDetailsAutofillRequest){
        VehicleDetailsAutofillResponse response= serviceAdvisorService.autoFillVehicleDetails(vehicleDetailsAutofillRequest);
        return response;

    }
    @PostMapping("/register vehicle")
    public String addNewVehicle(@RequestBody Vehicle vehicle){
        String response=serviceAdvisorService.registerNewVehicle(vehicle);
        return response;
    }
}
