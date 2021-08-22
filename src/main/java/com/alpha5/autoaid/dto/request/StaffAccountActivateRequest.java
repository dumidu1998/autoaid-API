package com.alpha5.autoaid.dto.request;

import com.alpha5.autoaid.enums.UserStatus;

public class StaffAccountActivateRequest {
    private long staffId;
    private UserStatus userStatus;

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
}
