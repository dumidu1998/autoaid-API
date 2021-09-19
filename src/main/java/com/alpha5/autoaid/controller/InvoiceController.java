package com.alpha5.autoaid.controller;

import com.alpha5.autoaid.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @GetMapping("/byrepairid/{id}")//repair Id
    public ResponseEntity getRepairInvoice(@PathVariable long id){
        return ResponseEntity.ok(invoiceService.getInvoiceByRepairId(id));
    }

}
