package com.alpha5.autoaid.controller;

import com.alpha5.autoaid.dto.request.*;
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

    // auto fill vehicle details on the ve
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

    //add new aketchy account for customer
    // it doesnt check for existing contact since it was filtered early. So, add non existing contact
    @PostMapping("/customer/add new")
    public ResponseEntity addNewCustomerSketchy(@RequestBody AddSketchyCustomerRequest addSketchyCustomerRequest){
        serviceAdvisorService.addNewCustomerSketchy(addSketchyCustomerRequest);
        return ResponseEntity.ok().body("customer added Successfully");
    }

    @PostMapping("/add repair")
    public ResponseEntity addNewRepair(@RequestBody AddNewRepairsRequest addNewRepairsRequest){
        serviceAdvisorService.addNewRepair(addNewRepairsRequest);
        return ResponseEntity.ok().body("Repair Added Successfully");
    }

    @PostMapping("/add service entries")
    public ResponseEntity addServiceEntries(@RequestBody AddNewServiceEntryRequest[] addNewServiceEntryRequest){
        serviceAdvisorService.addNewServiceEntry(addNewServiceEntryRequest);
        return ResponseEntity.ok().body("Added to the DB");
    }


}
