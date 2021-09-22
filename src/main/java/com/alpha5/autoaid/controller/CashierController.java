package com.alpha5.autoaid.controller;

import com.alpha5.autoaid.dto.request.AddInvoiceDataRequest;
import com.alpha5.autoaid.dto.request.UpdateItem;
import com.alpha5.autoaid.dto.response.AddInventryItemResponed;
import com.alpha5.autoaid.dto.response.CashierRepairDetailsRespond;
import com.alpha5.autoaid.model.Customer;
import com.alpha5.autoaid.model.Repair;
import com.alpha5.autoaid.service.CashierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cashier")

public class CashierController {

    @Autowired
    CashierService cashierService;

    @GetMapping("/cashierGetRepairInvoice/{id}")//repair Id
    public ResponseEntity cashierGetRepairInvoice(@PathVariable long id){
        return ResponseEntity.ok(cashierService.cashierGetRepairInvoice(id));
    }

    @GetMapping("/getAllRepairs")
    public ResponseEntity getAllRepairs(){
        List<CashierRepairDetailsRespond> response = cashierService.getAllRepairs();
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/getHandOverRepairs")
    public ResponseEntity getHandOverRepairs(){
        List<CashierRepairDetailsRespond> response = cashierService.getHandOverRepairs();
        return ResponseEntity.ok().body(response);
    }
    @PutMapping("/updateStatusPaid")
    public ResponseEntity updateStatusPaid(@RequestBody AddInvoiceDataRequest addInvoiceDataRequest){
        cashierService.updateStatusPaid(addInvoiceDataRequest);
        return ResponseEntity.ok().body("Status Updated Sucesssfully!!");
    }


}
