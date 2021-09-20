package com.alpha5.autoaid.dto.response;

import java.math.BigDecimal;

public class MaterialList {
    private String itemName;
    private int qty;
    private BigDecimal unitPrice;
    private BigDecimal total;

    public MaterialList(String itemName, int qty, BigDecimal unitPrice, BigDecimal total) {
        this.itemName = itemName;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.total = total;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
