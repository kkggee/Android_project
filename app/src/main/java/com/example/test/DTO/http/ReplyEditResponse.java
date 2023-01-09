package com.example.test.DTO.http;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReplyEditResponse {
//    @SerializedName("script")
//    public String script;
//
//    @SerializedName("timestamp")
//    public String timestamp;

    @SerializedName("result")
    boolean result;

    @SerializedName("code")
    public int code;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result){
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

//    public void setScript(String script) { this.script = script; }
//
//    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
//
//    public String getScript() { return script; }
//
//    public String getTimestamp() { return timestamp; }

}
