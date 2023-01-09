package com.example.test.DTO.http;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
public class TagListResponse {
    @SerializedName("result")
    boolean result;

    @SerializedName("code")
    public int code;

    @SerializedName("tagNameList")
    ArrayList<String> tagNameList;

    @SerializedName("tagColorList")
    ArrayList<String> tagColorList;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setTagNameList(ArrayList<String> tagNameList) {
        this.tagNameList = tagNameList;
    }


    public void setTagColorList(ArrayList<String> tagColorList) {
        this.tagColorList = tagColorList;
    }

    public ArrayList<String> getTagColorList() {
        return tagColorList;
    }


    public ArrayList<String> getTagNameList() {
        return tagNameList;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

