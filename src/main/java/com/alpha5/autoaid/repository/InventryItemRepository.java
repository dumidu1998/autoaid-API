package com.alpha5.autoaid.repository;

import com.alpha5.autoaid.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventryItemRepository extends JpaRepository<InventoryItem,Long> {

    InventoryItem findByItemName(String itemName);

}
