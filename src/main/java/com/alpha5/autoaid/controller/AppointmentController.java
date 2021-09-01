package com.alpha5.autoaid.controller;

import com.alpha5.autoaid.dto.request.AddAppointment;
import com.alpha5.autoaid.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/appointment")
@RestController
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @GetMapping("/slots")
    ResponseEntity getVehicles() {
        return ResponseEntity.ok().body(appointmentService.getAllSlots());
    }

    @PostMapping("/addappointment")
    ResponseEntity addNewAppointment(@RequestBody AddAppointment addAppointment){
        boolean success =appointmentService.addApointment(addAppointment);
        if(success) {
            return ResponseEntity.ok().body("Appointment Placed Successfully");
        }else{
            return ResponseEntity.badRequest().body("Error!!");
        }
    }

}
