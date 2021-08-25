package com.alpha5.autoaid.controller;

import com.alpha5.autoaid.dto.request.AddItem;
import com.alpha5.autoaid.dto.request.AddItemCategory;
import com.alpha5.autoaid.dto.response.AddItemRespond;
import com.alpha5.autoaid.dto.response.InventryStockRespond;
import com.alpha5.autoaid.model.ItemCategory;
import com.alpha5.autoaid.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class StockController {

    @Autowired
    StockService stockService;



    @PostMapping("/category")
    public ResponseEntity addItemCategory(@RequestBody AddItemCategory addItemCategory){
        try{
            stockService.addCategory(addItemCategory);
            return ResponseEntity.ok().body("Item Category Added Successfully");
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Item Category Already Exist!");
        }
    }

    @PutMapping("/category")
    public ResponseEntity updateItemCategory(@RequestBody ItemCategory itemCategory){
        try{
            stockService.updateCategory(itemCategory);
            return ResponseEntity.ok().body("Item Category Updated Successfully");
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Item Already Exist!");
        }
    }

    @GetMapping("/categories")
    public ResponseEntity getAll(){
        List<ItemCategory> response = stockService.getAllCategories();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/category/{itemId}")
    public ResponseEntity getById(@PathVariable long itemId){
        try{
            ItemCategory response = stockService.getCategoryById(itemId);
            if(response!=null){
                return ResponseEntity.ok().body(response);
            }else{
                return ResponseEntity.badRequest().body("Invalid Item ID");
            }
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Error!!");
        }
    }

    @PostMapping("/item")
    public ResponseEntity addItem(@RequestBody AddItem addItem){
        try{
            if(stockService.findByCategoryId(addItem.getCategoryId())!=null) {
                AddItemRespond respond = stockService.addItem(addItem, stockService.findByCategoryId(addItem.getCategoryId()));
                return ResponseEntity.ok().body(respond);
            }else{
                return ResponseEntity.badRequest().body("Invalid Item Category!");
            }
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Item already Exist!");
        }
    }


    @GetMapping("/itemName/{itemName}")
    public ResponseEntity getItemByName(@PathVariable String itemName){
        InventryStockRespond response = stockService.inventryItemStock(itemName) ;
        return ResponseEntity.ok().body(response);
    }



}
