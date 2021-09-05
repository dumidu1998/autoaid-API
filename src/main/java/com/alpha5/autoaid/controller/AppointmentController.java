package com.alpha5.autoaid.controller;

import com.alpha5.autoaid.dto.request.AddAppointment;
import com.alpha5.autoaid.model.AppointmentSlot;
import com.alpha5.autoaid.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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
        boolean success =appointmentService.addAppointment(addAppointment);
        if(success) {
            return ResponseEntity.ok().body("Appointment Placed Successfully");
        }else{
            return ResponseEntity.badRequest().body("Error!!");
        }
    }

    @GetMapping("/getslotsfromdate/{date}")
    ResponseEntity getSlotsFromDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        List<AppointmentSlot> slots = appointmentService.getFreeSlotsByDate(date);
        return ResponseEntity.ok().body(appointmentService.getFreeSlotsByDate(date));
    }

    @GetMapping("/getadvisorfromdate/{date}")
    ResponseEntity getAdvisorFromDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return ResponseEntity.ok().body(appointmentService.getServiceAdvisorFromDate(date));
    }

//    @GetMapping("/getupcommingAppointments/{id}")
//    ResponseEntity getUpcoming(@PathVariable long id){
//        return ResponseEntity.ok().body(appointmentService.getUpcomingAppointments(id));
//    }


}
