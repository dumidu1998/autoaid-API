package com.alpha5.autoaid.controller;

import com.alpha5.autoaid.dto.request.AddVehicleRequest;
import com.alpha5.autoaid.dto.request.GetCustomerDetailsRequest;
import com.alpha5.autoaid.dto.request.VehicleDetailsAutofillRequest;
import com.alpha5.autoaid.dto.response.GetCustomerDetailsRespond;
import com.alpha5.autoaid.dto.response.VehicleDetailsAutofillResponse;
import com.alpha5.autoaid.model.UserData;
import com.alpha5.autoaid.service.ServiceAdvisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public VehicleDetailsAutofillResponse getVehicleDetails(@RequestBody VehicleDetailsAutofillRequest vehicleDetailsAutofillRequest) {
        VehicleDetailsAutofillResponse response = serviceAdvisorService.autoFillVehicleDetails(vehicleDetailsAutofillRequest);
        return response;

    }

    // add new vehicle which not exists
    // Customer contact number should pass
    @PostMapping("/vehicle/add new")
    public ResponseEntity addNewVehicle(@RequestBody AddVehicleRequest addVehicleRequest) {
        if(serviceAdvisorService.checkIfVehicleExists(addVehicleRequest.getVin())){
           return ResponseEntity.badRequest().body("Vehicle Already Exists");
        }else {
            serviceAdvisorService.registerNewVehicle(addVehicleRequest);
            return ResponseEntity.ok().body("vehicle Added Succesfully");
        }
    }
    @PostMapping("/getCustomer")
    public ResponseEntity getCustomerDetails(@RequestBody GetCustomerDetailsRequest getCustomerDetailsRequest){
        GetCustomerDetailsRespond respond=serviceAdvisorService.autoFillCustomerDetails(getCustomerDetailsRequest.getContactNumber());
        if(respond!=null){
            return ResponseEntity.ok().body(respond);
        }else return ResponseEntity.badRequest().body("User is Not There. Add New");
    }
}
