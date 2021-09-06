package com.alpha5.autoaid.dto.response;

public class AddItemRespond {
    private long itemId;
    private String name;

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public AddItemRespond(long itemId, String name) {
        this.itemId = itemId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
