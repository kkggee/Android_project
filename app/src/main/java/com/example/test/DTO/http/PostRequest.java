package com.example.test.DTO.http;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

//PostRequest(title, script,date,time,price)

public class PostRequest {
    @SerializedName("input_title")
    public String inputTitle;

    @SerializedName("input_script")
    public String inputScript;

    @SerializedName("input_date")
    public String inputDate;

    @SerializedName("input_time")
    public String inputTime;

    @SerializedName("input_price")
    public String inputPrice;

     @SerializedName("input_tags")
     public ArrayList<String> inputTags;

    public String getInputTitle() {
        return inputTitle;
    }

    public String getInputScript() {
        return inputScript;
    }

    public String getInputDate() {
        return inputDate;
    }

    public String getInputTime() {
        return inputTime;
    }

    public String getInputPrice() {
        return inputPrice;
    }

    public ArrayList<String> getInputTags() {
        return inputTags;
    }


//    public String getInputScript(){ return inputDescript; }

    public void setInputTitle(String inputTitle) {
        this.inputTitle = inputTitle;
    }

    public void setInputScript(String inputScript) {
        this.inputScript = inputScript;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
    }

    public void setInputTime(String inputTime) {
        this.inputTime = inputTime;
    }

    public void setInputPrice(String inputPrice) {
        this.inputPrice = inputPrice;
    }

    public void setInputTags(ArrayList<String> inputTags) {
        this.inputTags = inputTags;
    }

    // public void setInputDescript(String inputDescript) { this.inputDescript = inputDescript; }

    public PostRequest(String Title, String Script, String date, String time, String price, ArrayList<String> tags) {
        this.inputTitle = Title;
        this.inputScript = Script;
        this.inputDate = date;
        this.inputTime = time;
        this.inputPrice = price;
        this.inputTags = tags;
    }
}
