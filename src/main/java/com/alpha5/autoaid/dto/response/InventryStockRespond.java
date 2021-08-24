package com.alpha5.autoaid.dto.response;

import java.math.BigDecimal;

public class InventryStockRespond {

    private long itemNo;
    private BigDecimal stock;
    private BigDecimal price;

    public long getItemNo() {
        return itemNo;
    }

    public void setItemNo(long itemNo) {
        this.itemNo = itemNo;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
