package com.alpha5.autoaid.controller;


import com.alpha5.autoaid.dto.request.RepairCompletedRequest;
import com.alpha5.autoaid.dto.request.SubCatCompleteRequest;
import com.alpha5.autoaid.dto.request.TechnicianRepairAcceptanceRequest;
import com.alpha5.autoaid.dto.response.GetNextRepairResponse;
import com.alpha5.autoaid.dto.response.technician.GetUpcomingRepairResponse;
import com.alpha5.autoaid.enums.UserType;
import com.alpha5.autoaid.model.Slot;
import com.alpha5.autoaid.service.AuthService;
import com.alpha5.autoaid.service.ServiceAdvisorService;
import com.alpha5.autoaid.service.TechnicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/technician")
public class TechnicianController {

    @Autowired
    private AuthService authService;

    @Autowired
    private TechnicianService technicianService;

    @Autowired
    private ServiceAdvisorService serviceAdvisorService;

    @PostMapping("/complete/subcat")
    public ResponseEntity completeSubCat(@RequestBody SubCatCompleteRequest subCatCompleteRequest){
        if(technicianService.checkEntryExists(subCatCompleteRequest)){
            technicianService.completeSubCat(subCatCompleteRequest);
            return ResponseEntity.ok().body("Entry Updated");
        }
        return ResponseEntity.badRequest().body("Invalid Entry");
    }

    @PostMapping("/repair/complete")
    public ResponseEntity completeRepair(@RequestBody RepairCompletedRequest repairCompletedRequest){
        if(technicianService.checkWhetherAllEntriesCompleted(repairCompletedRequest)){
            technicianService.completeRepair(repairCompletedRequest);
            Slot slot=serviceAdvisorService.getNextSlot(repairCompletedRequest.getRepairId());
            return ResponseEntity.ok().body(slot);
        }else
        return ResponseEntity.badRequest().body("All entries Not Completed Yet");
    }
    @GetMapping("/repair/findnext/{repairId}")
    public ResponseEntity findNextRepair(@PathVariable long repairId){
        String response;
        if(technicianService.checkWhetherNoneCompletedEntries(repairId)){
            response="entries exists";
        }else
            response="all completed";
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/getnext/repair/{sectionName}")
    public ResponseEntity getNextRepair(@PathVariable String sectionName) {
        //check whether all slots not deactivated
        if (technicianService.checkWhetherAllSlotsNotDeactivated(sectionName)) {
            return ResponseEntity.badRequest().body("Slots are not working now !!");
        } else {
            GetNextRepairResponse getNextRepairResponse = technicianService.getNextRepair(sectionName);
            return ResponseEntity.ok().body(getNextRepairResponse);
        }
    }
    //repair acceptance when click go
    @PostMapping("/repair/acceptance")
    public ResponseEntity repairAcceptance(@RequestBody TechnicianRepairAcceptanceRequest technicianRepairAcceptanceRequest){
        technicianService.acceptRepair(technicianRepairAcceptanceRequest);
        return ResponseEntity.ok().body("Repair Assigned");
    }
    //get section on user Id
    @GetMapping("/getSection/{userId}")
    public ResponseEntity getSectionName(@PathVariable long userId){
        if(authService.checkIfUserIdExistsOnUserType(userId,UserType.LEAD_TECHNICIAN)){
            String sectionName=technicianService.getSection(userId);
            if(sectionName==null){
                return ResponseEntity.badRequest().body("Sections Not Assigned");
            }else {
                return ResponseEntity.ok().body(sectionName);
            }
        }
        return ResponseEntity.badRequest().body("Invalid user or Section");
    }
    @GetMapping("/getUpcoming/{sectionName}")
    public ResponseEntity getUpcoming(@PathVariable String sectionName){
        List<GetUpcomingRepairResponse> upcomingRepairs = technicianService.getUpcomingRepairs(sectionName);
        if(upcomingRepairs==null){
            return ResponseEntity.badRequest().body("No Upcoming Repairs");
        }else {
            return ResponseEntity.ok().body(upcomingRepairs);
        }
    }

}
