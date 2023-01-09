package com.example.test.DTO.http;

import com.google.gson.annotations.SerializedName;

public class TagRequest {
    @SerializedName("tag_name")
    public String tag_name;

    @SerializedName("color")
    public String color;

    public String getTag_name() { return tag_name; }

    public String getColor() { return color; }

    public void setTag_name(String tag_name) { this.tag_name = tag_name; }

    public void setColor(String color) { this.color = color; }

    public TagRequest(String tag_name, String color) {
        this.tag_name = tag_name;
        this.color = color;
    }
}
