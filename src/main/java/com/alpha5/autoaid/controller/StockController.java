package com.alpha5.autoaid.controller;

import com.alpha5.autoaid.dto.request.*;
import com.alpha5.autoaid.dto.response.AddInventryItemResponed;
import com.alpha5.autoaid.dto.response.AddItemRespond;
import com.alpha5.autoaid.dto.response.InventryStockRespond;
import com.alpha5.autoaid.dto.response.ItemRequestRespond;
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

    @GetMapping("/itembyname/{itemName}")
    public ResponseEntity getItemByName(@PathVariable String itemName){
        InventryStockRespond response = stockService.getItemByName(itemName) ;
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/searchitembyname/{itemName}")
    public ResponseEntity searchItembyname(@PathVariable String itemName){
       List <InventryStockRespond> response = stockService.searchItem(itemName);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/itembyid/{itemId}")
    public ResponseEntity getItemById(@PathVariable long itemId){
        InventryStockRespond response = stockService.getItemById(itemId) ;
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/itemswithlowqty")
    public ResponseEntity getItemById(){
        List<InventryStockRespond> response = stockService.getlowstockitems() ;
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/items")
    public ResponseEntity getAllitems(){
        List<AddInventryItemResponed> response = stockService.getAllItems();
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/item")
    public ResponseEntity updateItem(@RequestBody UpdateItem updateItem){
        stockService.updateItem(updateItem);
        return ResponseEntity.ok().body("Updated Sucesssfully!!");
    }

    @PutMapping("/updateitemstatus/{itemId}")
    public ResponseEntity updateItemStatus(@PathVariable long itemId){
        stockService.updateItemStatus(itemId);
        return ResponseEntity.ok().body("Availability Updated Sucesssfully!!");
    }

    @GetMapping("/searchitembycategory/{categoryId}")
    public ResponseEntity getCategoryitems(@PathVariable long categoryId){
        if(categoryId == 0){
            List<AddInventryItemResponed> response = stockService.getAllItems();
            return ResponseEntity.ok().body(response);
        }else{
            List<AddInventryItemResponed> response = stockService.getCategoryitems(categoryId);
            return ResponseEntity.ok().body(response);
        }

    }
    @PostMapping("/updateStock")
    public ResponseEntity updateNewItemStock(@RequestBody AddItemNewStock addItemNewStock){
        boolean out=stockService.updateNewItemStock(addItemNewStock);
        if(out)
        return ResponseEntity.ok().body("New stock updated");
        else return ResponseEntity.badRequest().body("Error!!");
    }

    @GetMapping("/itemRequestAll")
    public ResponseEntity GetAllRequest(){
        List<ItemRequestRespond> response = stockService.allItemRequest();
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/approveItemRequest")
    public ResponseEntity approveRequest(@RequestBody ItemRequestApproveRequest itemRequestApproveRequest){
        boolean out=stockService.approveRequest(itemRequestApproveRequest);
        if(out)
            return ResponseEntity.ok().body("New stock updated");
        else return ResponseEntity.badRequest().body("Error!!");
    }

}
