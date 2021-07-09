package com.alpha5.autoaid.dto.request;

public class AddRepairSubCatRequest {

    //should be string since it gets other option String
    private String subCatName;
    private int time;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getSubCatName() {
        return subCatName;
    }

    public void setSubCatName(String subCatName) {
        this.subCatName = subCatName;
    }
}
