package com.alpha5.autoaid.controller;


import com.alpha5.autoaid.dto.request.RepairCompletedRequest;
import com.alpha5.autoaid.dto.request.SubCatCompleteRequest;
import com.alpha5.autoaid.model.Slot;
import com.alpha5.autoaid.service.ServiceAdvisorService;
import com.alpha5.autoaid.service.TechnicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/technician")
public class TechnicianController {

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
            response="all coompleted";
        return ResponseEntity.ok().body(response);
    }

    //TODO
    //find next repair ekedi added date eka consider krnna
    //repair acceptancy- if repair completed slot avaiable unama ekata next appointment set krna

}
