package com.alpha5.autoaid.dto.request;

public class ServiceEntryInstance {
    private long sub_cat_id;
    private String description;
    private String time;
    private String price;

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
