package com.alpha5.autoaid.dto.request;

import com.alpha5.autoaid.enums.UserType;

public class GetNameOfLoggedUserRequest {
    private long userId;
    private UserType userType;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
