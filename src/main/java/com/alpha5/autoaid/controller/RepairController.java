package com.alpha5.autoaid.controller;

import com.alpha5.autoaid.dto.request.GetNextSlotRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/repair")
public class RepairController {

    @PostMapping("/get next slot")
    public ResponseEntity getNextAvailableSlot(@RequestBody GetNextSlotRequest[] getNextSlotRequests){

        return ResponseEntity.ok().body("fine");
    }
}
