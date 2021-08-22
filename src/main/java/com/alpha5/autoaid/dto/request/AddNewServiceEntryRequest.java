package com.alpha5.autoaid.dto.request;

public class AddNewServiceEntryRequest {
    private long sub_cat_id;
    private String description;
    private long staff_id;
    private long repair_id;

    public long getSub_cat_id() {
        return sub_cat_id;
    }

    public void setSub_cat_id(long sub_cat_id) {
        this.sub_cat_id = sub_cat_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(long staff_id) {
        this.staff_id = staff_id;
    }

    public long getRepair_id() {
        return repair_id;
    }

    public void setRepair_id(long repair_id) {
        this.repair_id = repair_id;
    }
}
