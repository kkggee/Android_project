package com.example.test.DTO.http;

import com.google.gson.annotations.SerializedName;

public class NonRequest {
    @SerializedName("input_id")
    public String inputId;


    public String getInputId() { return inputId; }


    public void setInputId(String inputId) {
        this.inputId = inputId;
    }

    public NonRequest(String inputId) {
        this.inputId = inputId;
    }
}
