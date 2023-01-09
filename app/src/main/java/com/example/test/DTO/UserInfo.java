package com.example.test.DTO;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private String userID;
    private String userIcon;
    private String userTemp;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

}
