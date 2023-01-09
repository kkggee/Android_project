package com.example.test.DTO.http;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("userId")
    public String userId;
    @SerializedName("userIcon")
    public String userIcon;
    @SerializedName("code")
    public int code;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
