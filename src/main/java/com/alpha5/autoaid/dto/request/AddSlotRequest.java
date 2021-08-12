package com.alpha5.autoaid.dto.request;

import com.alpha5.autoaid.enums.SlotStatus;

public class AddSlotRequest {
    private String slotName;
    private long sectionId;

    public String getSlotName() {
        return slotName;
    }

    public void setSlotName(String slotName) {
        this.slotName = slotName;
    }

    public long getSectionId() {
        return sectionId;
    }

    public void setSectionId(long sectionId) {
        this.sectionId = sectionId;
    }
}
