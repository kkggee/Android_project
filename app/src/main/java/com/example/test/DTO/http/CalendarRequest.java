package com.example.test.DTO.http;

import com.google.gson.annotations.SerializedName;

public class CalendarRequest {
    @SerializedName("user_id")
    public String userId;

//    @SerializedName("date")
//    public String date;
//
//    @SerializedName("start_time")
//    public String startTime;
//
//    @SerializedName("descript")
//    public String descript;

    public String getUserId() { return userId; }

//    public String getDate() { return date; }
//
//    public String getStartTime() { return startTime; }
//
//    public String getDescript() { return descript; }

    public void setUserId(String userId) { this.userId = userId; }
//    public void setDate(String date) { this.date = date; }
//    public void setStartTime(String startTime) { this.startTime = startTime; }
//    public void setDescript(String descript) { this.descript = descript; }

    public CalendarRequest(String userId) {
        this.userId = userId;
//        this.date = date;
//        this.startTime = startTime;
//        this.descript = descript;
    }
}
