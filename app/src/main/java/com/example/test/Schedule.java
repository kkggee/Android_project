package com.example.test;
import android.view.View;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Schedule {
    @SerializedName("start_time")
    public String startTime;

    @SerializedName("descript")
    public String descript;
}
