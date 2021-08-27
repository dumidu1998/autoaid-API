package com.alpha5.autoaid.controller;

import com.alpha5.autoaid.dto.request.AddInventryItemRequest;
import com.alpha5.autoaid.dto.response.AddInventryItemResponed;
import com.alpha5.autoaid.dto.response.InventryStockRespond;
import com.alpha5.autoaid.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins ="http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/inventry") // this...........................................................
public class StockController {

    @Autowired
    StockService stockService;


    @GetMapping("/itemName/{itemName}")
    public InventryStockRespond getItemByName(@PathVariable String itemName){
        InventryStockRespond response = stockService.inventryItemStock(itemName) ;
        return response;
    }

    @PostMapping("/addInventryItem")
    public AddInventryItemResponed addInventryItem(@RequestBody AddInventryItemRequest addInventryItemRequest){

        InventryStockRespond response = stockService.

    }

}
