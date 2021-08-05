package com.alpha5.autoaid.dto.request;

public class AddRepairSubCatRequest {

    //should be string since it gets other option String
    private String subCatName;
    private int time;
    private String sectionName;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSubCatName() {
        return subCatName;
    }

    public void setSubCatName(String subCatName) {
        this.subCatName = subCatName;
    }
}
