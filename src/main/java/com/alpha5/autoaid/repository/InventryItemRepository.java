package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventryItemRepository extends JpaRepository<InventoryItem,Long> {

    InventoryItem findByItemName(String itemName);
//    InventoryItem findAllByOrderByStatusDesc();
    List<InventoryItem> findAllByItemNameIsContaining(String itemName);
    InventoryItem findByItemNo(long id);

    @Query(value = "SELECT * FROM inventory_item WHERE stock<=reorder_level" , nativeQuery = true)
    List<InventoryItem> getLowStockItems();


}
