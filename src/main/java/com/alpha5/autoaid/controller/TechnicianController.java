package com.alpha5.autoaid.controller;


import com.alpha5.autoaid.dto.request.*;
import com.alpha5.autoaid.dto.response.AdminGetAssignedLeadTechResponse;
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
            return ResponseEntity.ok().body("All entries Completed");
        }else
        return ResponseEntity.badRequest().body("Have pending entries");
    }
    @GetMapping("/repair/findnext/{repairId}")
    public ResponseEntity findNextRepair(@PathVariable long repairId){
        String response;
        if(technicianService.checkWhetherNoneCompletedEntries(repairId)){
            Slot slot=serviceAdvisorService.getNextSlot(repairId);
            response="Next Slot is "+slot.getSlotName();
        }else{
            technicianService.updateRepairStatus(repairId);
            response="all completed";
        }
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

    @GetMapping("/getEntryList/{repairId}/{section}")
    public ResponseEntity getEntryList(@PathVariable long repairId,@PathVariable String section){
        return ResponseEntity.ok().body(technicianService.getEntryList(repairId, section));
    }

    @GetMapping("/assignTech/{repairId}/{techId}/{section}")
    public ResponseEntity assignTech(@PathVariable long techId,@PathVariable long repairId,@PathVariable String section){
        technicianService.assignTechnician(techId,repairId,section);
        return ResponseEntity.ok().body("Success");
    }

    @GetMapping("/getTech/{repairId}/{section}")
    public ResponseEntity getTech(@PathVariable long repairId,@PathVariable String section){
        AdminGetAssignedLeadTechResponse technician = technicianService.getTech(repairId, section);
        if (technician == null) {
            return ResponseEntity.badRequest().body("Technician Not Assigned");
        } else {
            return ResponseEntity.ok().body(technician);

        }
    }

    @PostMapping("/createItemRequest")
    public ResponseEntity createItemRequest(@RequestBody AddItemRequest addItemRequest){
        try{
            technicianService.createItemRequest(addItemRequest);
            return ResponseEntity.ok().body("Item Request Added Successfully");
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Item Request Error!");
        }
    }

    @GetMapping("/ongoingrepairs/{userid}")//logged user id
    public ResponseEntity getOngoingRepairs(@PathVariable long userid){
        List<GetUpcomingRepairResponse> upcomingRepairs = technicianService.getOngoingRepairs(userid);
        if(upcomingRepairs==null){
            return ResponseEntity.badRequest().body("No Upcoming Repairs");
        }else {
            return ResponseEntity.ok().body(upcomingRepairs);
        }
    }

    @GetMapping("/vehiclebyid/{repairid}")//repairid
    public ResponseEntity getVehicleDetails(@PathVariable long repairid){
            return ResponseEntity.ok().body(technicianService.getVehicleDetails(repairid));
    }

}
