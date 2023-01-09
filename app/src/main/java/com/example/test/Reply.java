package com.example.test;
import android.view.View;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
public class Reply {
    @SerializedName("id")
    public String ID;

    @SerializedName("userID")
    public String userID;

    @SerializedName("timestamp")
    public String timestamp;

    @SerializedName("script")
    public String script;
}
