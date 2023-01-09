package com.example.test.DTO.http;

import com.google.gson.annotations.SerializedName;

public class PostResponse {
    @SerializedName("result")
    boolean result;

    @SerializedName("code")
    public static int code;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
