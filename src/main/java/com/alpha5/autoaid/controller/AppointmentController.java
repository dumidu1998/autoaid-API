package com.alpha5.autoaid.controller;

import com.alpha5.autoaid.dto.request.CustomerSignUpRequest;
import com.alpha5.autoaid.dto.request.NewAppointmentRequest;
import com.alpha5.autoaid.dto.response.NewAppointmentRespond;
import com.alpha5.autoaid.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alpha5.autoaid.dto.request.AddAppointment;
import com.alpha5.autoaid.dto.response.StaffListRespond;
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
        if(slots.isEmpty()) {
            return ResponseEntity.badRequest().body("Sorry No any Free Slots Available on the Date you Selected!");
        }else{
            return ResponseEntity.ok().body(appointmentService.getFreeSlotsByDate(date));
        }
    }

    @GetMapping("/getadvisorfromdateandslot/{date}/{id}")
    ResponseEntity getAdvisorFromDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,@PathVariable long id) {
        List<StaffListRespond> result = appointmentService.getServiceAdvisorFromDate(date,id);
        if(result.size()==0){
            return ResponseEntity.badRequest().body("No Any Service Advisors Available Today!!");
        }else {
            return ResponseEntity.ok().body(appointmentService.getServiceAdvisorFromDate(date, id));
        }
    }

//    @GetMapping("/getupcommingAppointments/{id}")
//    ResponseEntity getUpcoming(@PathVariable long id){
//        return ResponseEntity.ok().body(appointmentService.getUpcomingAppointments(id));
//    }


}
