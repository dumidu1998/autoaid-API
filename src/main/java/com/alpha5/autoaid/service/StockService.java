package com.alpha5.autoaid.service;

import com.alpha5.autoaid.dto.request.AddItem;
import com.alpha5.autoaid.dto.request.AddItemCategory;
import com.alpha5.autoaid.dto.request.AddItemNewStock;
import com.alpha5.autoaid.dto.request.UpdateItem;
import com.alpha5.autoaid.dto.response.AddInventryItemResponed;
import com.alpha5.autoaid.dto.response.AddItemRespond;
import com.alpha5.autoaid.dto.response.InventryStockRespond;
import com.alpha5.autoaid.enums.InventoryStatus;
import com.alpha5.autoaid.model.InventoryItem;
import com.alpha5.autoaid.model.ItemAdd;
import com.alpha5.autoaid.model.ItemCategory;
import com.alpha5.autoaid.repository.InventryItemRepository;
import com.alpha5.autoaid.repository.ItemAddRepository;
import com.alpha5.autoaid.repository.ItemCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {

    @Autowired
    private InventryItemRepository inventryItemRepository;

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;
    @Autowired
    private ItemAddRepository itemAddRepository;

    public InventryStockRespond getItemByName(String itemName){
        InventoryItem item = inventryItemRepository.findByItemName(itemName);

        if(item == null){
            throw new RuntimeException("Item Not Available");
        }else{
            InventryStockRespond response = new InventryStockRespond();
            response.setItemNo(item.getItemNo());
            response.setItemName(item.getItemName());
            response.setStock(item.getStock());
            response.setPrice(item.getPrice());
            response.setReorderLevel(item.getReorderLevel());


            return response;
        }
    }

    public void addCategory(AddItemCategory addItemCategory) {
        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setCategoryName(addItemCategory.getCategoryName());
        itemCategoryRepository.save(itemCategory);
    }

    public void updateCategory(ItemCategory itemCategory) {
        itemCategoryRepository.save(itemCategory);
    }

    public List<ItemCategory> getAllCategories() {
        return itemCategoryRepository.findAll();
    }

    public ItemCategory getCategoryById(long itemId) {
        return itemCategoryRepository.findByCategoryId(itemId);
    }

    public AddItemRespond addItem(AddItem addItem,ItemCategory itemCategory) {
        InventoryItem item = new InventoryItem();
        item.setItemName(addItem.getName());
        item.setPrice(addItem.getPrice());
        item.setReorderLevel(addItem.getReorderLevel());
        item.setStock(BigDecimal.valueOf(0.00));
        item.setStatus(InventoryStatus.AVAILABLE);
        item.setCategory(itemCategory);
        InventoryItem addeditem = inventryItemRepository.save(item);

        AddItemRespond respond = new AddItemRespond(addeditem.getItemNo(), addeditem.getItemName());

        return respond;
    }

    public ItemCategory findByCategoryId(long categoryId){
        ItemCategory itemCategory = itemCategoryRepository.findByCategoryId(categoryId);
        if(itemCategory == null) return null;
        else{
            return itemCategory;
        }
    }

    public List<InventryStockRespond> searchItem(String itemName) {
        List <InventoryItem> items = inventryItemRepository.findAllByItemNameIsContaining(itemName);
        List <InventryStockRespond> respond = new ArrayList<InventryStockRespond>();
        for(InventoryItem item:items){
            InventryStockRespond newItem = new InventryStockRespond();
            newItem.setItemNo(item.getItemNo());
            newItem.setItemName(item.getItemName());
            newItem.setStock(item.getStock());
            newItem.setPrice(item.getPrice());
            newItem.setReorderLevel(item.getReorderLevel());
            respond.add(newItem);
        }
        return respond;
    }


    public InventryStockRespond getItemById(long itemId) {
        InventoryItem item = inventryItemRepository.findByItemNo(itemId);
        InventryStockRespond respond = new InventryStockRespond();
        respond.setItemName(item.getItemName());
        respond.setItemNo(item.getItemNo());
        respond.setPrice(item.getPrice());
        respond.setReorderLevel(item.getReorderLevel());
        respond.setStock(item.getStock());
        respond.setCatName(item.getCategory().getCategoryName());

        return respond;
    }

    public List<InventryStockRespond> getlowstockitems() {
        List<InventoryItem> out = inventryItemRepository.getLowStockItems();
        List<InventryStockRespond> respond = new ArrayList<>();
        for(InventoryItem item:out){
            InventryStockRespond newItem = new InventryStockRespond();
            newItem.setItemNo(item.getItemNo());
            newItem.setItemName(item.getItemName());
            newItem.setStock(item.getStock());
            newItem.setPrice(item.getPrice());
            newItem.setReorderLevel(item.getReorderLevel());
            respond.add(newItem);
        }
        return respond;
    }

    public void updateItem(UpdateItem updateItem) {
        InventoryItem item = new InventoryItem();

        item.setItemNo(updateItem.getItemId());
        item.setItemName(updateItem.getItemName());
        item.setPrice(updateItem.getPrice());
        item.setStock(updateItem.getStock());
        item.setStatus(InventoryStatus.AVAILABLE);
        item.setReorderLevel(updateItem.getReorderLevel());
        item.setCategory(findByCategoryId(updateItem.getCategoryId()));

        inventryItemRepository.save(item);
    }

    public void updateItemStatus(long itemId) {
        InventoryItem item = inventryItemRepository.findByItemNo(itemId);
        item.setStatus(((item.getStatus().toString()=="AVAILABLE")?InventoryStatus.UNAVAILABLE:InventoryStatus.AVAILABLE));
        inventryItemRepository.save(item);
    }

    public boolean updateNewItemStock(AddItemNewStock addItemNewStock) {
        try {
            ItemAdd newItem = new ItemAdd();
            newItem.setBuyingPrice(addItemNewStock.getBuyingPrice());
            newItem.setQuantity(addItemNewStock.getStock());
            newItem.setItem(inventryItemRepository.findByItemNo(addItemNewStock.getItemNo()));
            itemAddRepository.save(newItem);

            InventoryItem item = inventryItemRepository.findByItemNo(addItemNewStock.getItemNo());
            item.setStock(item.getStock().add(addItemNewStock.getStock()));
            inventryItemRepository.save(item);
            return true;
        }catch(Exception e) {
            return false;
        }

    }

    public List<AddInventryItemResponed> getAllItems() {
       List<InventoryItem> all =  inventryItemRepository.findAll();
       List<AddInventryItemResponed> respond = new ArrayList<>();
        for(InventoryItem item : all){
            AddInventryItemResponed newitem = new AddInventryItemResponed();
            newitem.setItemName(item.getItemName());
            newitem.setItemNo(item.getItemNo());
            newitem.setStock(item.getStock());
            newitem.setPrice(item.getPrice());
            newitem.setReorderLevel(item.getReorderLevel());
            newitem.setStatus(item.getStatus());
            respond.add(newitem);
        }
        return respond;
    }

    public List<AddInventryItemResponed> getCategoryitems(long categoryId) {
        List<InventoryItem> categoryAll =  inventryItemRepository.findAllByCategory_CategoryId(categoryId);
        List<AddInventryItemResponed> respond = new ArrayList<>();
        for(InventoryItem item : categoryAll){
            AddInventryItemResponed newitem = new AddInventryItemResponed();
            newitem.setItemName(item.getItemName());
            newitem.setItemNo(item.getItemNo());
            newitem.setStock(item.getStock());
            newitem.setPrice(item.getPrice());
            newitem.setReorderLevel(item.getReorderLevel());
            newitem.setStatus(item.getStatus());
            respond.add(newitem);
        }
        return respond;
    }
}
