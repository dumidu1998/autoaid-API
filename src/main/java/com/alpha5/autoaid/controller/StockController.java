package com.alpha5.autoaid.controller;

import com.alpha5.autoaid.dto.request.AddItemCategory;
import com.alpha5.autoaid.dto.response.InventryStockRespond;
import com.alpha5.autoaid.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class StockController {

    @Autowired
    StockService stockService;

    @GetMapping("/itemName/{itemName}")
    public ResponseEntity getItemByName(@PathVariable String itemName){
        InventryStockRespond response = stockService.inventryItemStock(itemName) ;
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/category")
    public ResponseEntity addItemCategory(@RequestBody AddItemCategory addItemCategory){
        try{
            stockService.addCategory(addItemCategory);
            return ResponseEntity.ok().body("Item Category Added Successfully");
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Item Category Already Exist!");
        }
    }


}
