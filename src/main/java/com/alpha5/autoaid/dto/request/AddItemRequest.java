package com.alpha5.autoaid.dto.request;

import com.alpha5.autoaid.enums.ItemRequestStatus;

public class AddItemRequest {
    private int quantity;
    private ItemRequestStatus status;
    private long staffId;
    private long repairId;
    private long itemNo;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ItemRequestStatus getStatus() {
        return status;
    }

    public void setStatus(ItemRequestStatus status) {
        this.status = status;
    }

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    public long getRepairId() {
        return repairId;
    }

    public void setRepairId(long repairId) {
        this.repairId = repairId;
    }

    public long getItemNo() {
        return itemNo;
    }

    public void setItemNo(long itemNo) {
        this.itemNo = itemNo;
    }
}
