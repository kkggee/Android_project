package com.example.test;

import android.view.View;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Post {
    @SerializedName("id")
    public String id;

    @SerializedName("userid")
    public String userid;

    @SerializedName("title")
    public String title;

    @SerializedName("date")
    public String date;

    @SerializedName("post")
    public String post;

}
