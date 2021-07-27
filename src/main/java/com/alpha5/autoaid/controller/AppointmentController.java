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

@RequestMapping("/appointment")
@RestController
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;

    @PostMapping("new")
    public ResponseEntity newAppointment(@RequestBody NewAppointmentRequest newAppointmentRequest){
       NewAppointmentRespond newAppointmentRespond =  appointmentService.newAppointment(newAppointmentRequest);
       return ResponseEntity.ok().body(newAppointmentRespond);
    }
}
