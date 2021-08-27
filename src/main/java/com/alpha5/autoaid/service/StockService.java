package com.alpha5.autoaid.service;

import com.alpha5.autoaid.dto.response.InventryStockRespond;
import com.alpha5.autoaid.model.InventoryItem;
import com.alpha5.autoaid.repository.InventryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    @Autowired
    private InventryItemRepository inventryItemRepository;

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

    @Autowired
    private InventryItemRepository inventryItemRepository;

    public

}
