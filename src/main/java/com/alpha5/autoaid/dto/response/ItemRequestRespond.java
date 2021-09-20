package com.alpha5.autoaid.dto.response;

import java.math.BigDecimal;

public class ItemRequestRespond {

    private long requestId;
    private int quantity;
    private long invItem;
    private long repairId;

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public long getInvItem() {
        return invItem;
    }

    public void setInvItem(long invItem) {
        this.invItem = invItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
