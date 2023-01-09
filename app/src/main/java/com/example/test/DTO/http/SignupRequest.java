package com.example.test.DTO.http;

import com.google.gson.annotations.SerializedName;

public class SignupRequest {
    @SerializedName("input_id")
    public String inputId;

    @SerializedName("input_pw")
    public String inputPw;

    @SerializedName("input_tel")
    public String inputTel;

    public String getInputTel() {
        return inputTel;
    }

    public void setInputTel(String inputTel) {
        this.inputTel = inputTel;
    }


    public String getInputId() {
        return inputId;
    }

    public String getInputPw() {
        return inputPw;
    }

    public void setInputId(String inputId) {
        this.inputId = inputId;
    }

    public void setInputPw(String inputPw) {
        this.inputPw = inputPw;
    }

    public SignupRequest(String inputId, String inputPw, String inputTel) {
        this.inputId = inputId;
        this.inputPw = inputPw;
        this.inputTel = inputTel;
    }

    public SignupRequest() {
        this.inputId="";
        this.inputPw="";
        this.inputTel="";
    }
}
