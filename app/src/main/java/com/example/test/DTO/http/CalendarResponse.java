package com.example.test.DTO.http;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CalendarResponse {

    @SerializedName("result")
    boolean result;

    @SerializedName("code")
    public int code;

    @SerializedName("descript")
    public ArrayList<String> descript;

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

    public void setDescript(ArrayList<String> descript) { this.descript = descript; }

    public ArrayList<String> getDescript() { return descript; }

}
