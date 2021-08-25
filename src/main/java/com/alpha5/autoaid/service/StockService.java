package com.alpha5.autoaid.service;

import com.alpha5.autoaid.dto.request.AddItem;
import com.alpha5.autoaid.dto.request.AddItemCategory;
import com.alpha5.autoaid.dto.response.AddItemRespond;
import com.alpha5.autoaid.dto.response.InventryStockRespond;
import com.alpha5.autoaid.enums.InventoryStatus;
import com.alpha5.autoaid.model.InventoryItem;
import com.alpha5.autoaid.model.ItemCategory;
import com.alpha5.autoaid.repository.InventryItemRepository;
import com.alpha5.autoaid.repository.ItemCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StockService {

    @Autowired
    private InventryItemRepository inventryItemRepository;

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    public InventryStockRespond inventryItemStock(String itemName){
        InventoryItem item = inventryItemRepository.findByItemName(itemName);

        if(item == null){
            throw new RuntimeException("Item Not Available");
        }else{
            InventryStockRespond response = new InventryStockRespond();
            response.setItemNo(item.getItemNo());
            response.setStock(item.getStock());
            response.setPrice(item.getPrice());

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

        AddItemRespond respond = new AddItemRespond(addeditem.getItemName());

        return respond;
    }

    public ItemCategory findByCategoryId(long categoryId){
        ItemCategory itemCategory = itemCategoryRepository.findByCategoryId(categoryId);
        if(itemCategory == null) return null;
        else{
            return itemCategory;
        }
    }
}
