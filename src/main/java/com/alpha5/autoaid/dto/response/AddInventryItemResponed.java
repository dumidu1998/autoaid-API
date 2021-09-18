package com.alpha5.autoaid.dto.response;

import com.alpha5.autoaid.enums.InventoryStatus;

import java.math.BigDecimal;

public class AddInventryItemResponed {

    private long itemNo;

    private String itemName;

    private BigDecimal price;

    private BigDecimal stock;

    private InventoryStatus status;

    private BigDecimal reorderLevel;

    public long getItemNo() {
        return itemNo;
    }

    public void setItemNo(long itemNo) {
        this.itemNo = itemNo;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }


    public InventoryStatus getStatus() {
        return status;
    }

    public void setStatus(InventoryStatus status) {
        this.status = status;
    }


}
