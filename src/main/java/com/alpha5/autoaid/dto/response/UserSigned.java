package com.alpha5.autoaid.dto.response;

import com.alpha5.autoaid.enums.UserType;

public class UserSigned {

    private Long id;
    private String username;
    private String email;
    private UserType userType;
    private String token;

    public UserSigned() {
    }

    public UserSigned(Long id, String username, String email, String token) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.token=token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
