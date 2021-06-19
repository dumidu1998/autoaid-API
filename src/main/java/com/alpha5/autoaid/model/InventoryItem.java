package com.alpha5.autoaid.model;

import com.alpha5.autoaid.enums.InventoryStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventory_item")
public class InventoryItem {
    @Id
    @GeneratedValue
    private long itemNo;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int stock;

    @Enumerated(EnumType.STRING)
    private InventoryStatus status;

    @Column(nullable = false)
    private int reorderLevel;

    @ManyToOne
    @JoinColumn(name = "category_id")
    ItemCategory category;

    @OneToMany(targetEntity = ItemAdd.class, mappedBy = "item", cascade = CascadeType.ALL)
    private Set<ItemAdd> itemAdd;

    public long getItemNo() {
        return itemNo;
    }
    public void setItemNo(long itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public InventoryStatus getStatus() {
        return status;
    }

    public void setStatus(InventoryStatus status) {
        this.status = status;
    }

    public ItemCategory getCategory() {
        return category;
    }

    public void setCategory(ItemCategory category) {
        this.category = category;
    }
}
