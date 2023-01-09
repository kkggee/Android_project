package com.example.test.DTO.http;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PostGetResponse {
    @SerializedName("title")
    public String title;

    @SerializedName("script")
    public String script;

    @SerializedName("date")
    public String date;

    @SerializedName("time")
    public String time;

    @SerializedName("price")
    public String price;

    @SerializedName("tags")
    public ArrayList<String> tags;

    @SerializedName("comments")
    public ArrayList<String> comments;

    @SerializedName("userID")
    public String userID;

    @SerializedName("code")
    public int code;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    public void setUserID(String userID) { this.userID = userID; }

    public String getUserID() { return userID; }

    public ArrayList<String> getComments() {
        return comments;
    }

    public String getTitle() {
        return title;
    }

    public String getScript() {
        return script;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getPrice() {
        return price;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
