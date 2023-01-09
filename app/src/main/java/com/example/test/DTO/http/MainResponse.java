package com.example.test.DTO.http;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MainResponse {
    @SerializedName("result")
    boolean result;

    @SerializedName("code")
    public int code;

    @SerializedName("postList")
    ArrayList<String> postList;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setPostList(ArrayList<String> postList) {
        this.postList = postList;
    }

    public ArrayList<String> getPostList() {
        return postList;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
