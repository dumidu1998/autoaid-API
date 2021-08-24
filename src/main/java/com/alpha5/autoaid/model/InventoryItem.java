package com.alpha5.autoaid.model;

import com.alpha5.autoaid.enums.InventoryStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
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

    @Column(nullable = false, precision = 10,scale = 2)
    private BigDecimal price;

    @Column(nullable = false, precision = 10,scale = 2)
    private BigDecimal stock;

    @Enumerated(EnumType.STRING)
    private InventoryStatus status;

    @Column(nullable = false, precision = 10,scale = 2)
    private BigDecimal reorderLevel;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }

    public BigDecimal getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(BigDecimal reorderLevel) {
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
