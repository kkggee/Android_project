package com.example.test.DTO.http;

import com.google.gson.annotations.SerializedName;

public class ReplyRequest {
    @SerializedName("input_id")
    public String inputId;

    @SerializedName("input_script")
    public String inputScript;

    //추가
    @SerializedName("input_post_id")
    public String inputPostId;

    @SerializedName("input_timestamp")
    public String inputTimeStamp;

    public String getInputId() {
        return inputId;
    }

    public String getInputScript() {
        return inputScript;
    }

    public String getInputPostId() { return inputPostId; }

    public String getInputTimeStamp() { return inputTimeStamp; }

    public void setInputId(String inputId) { this.inputId = inputId; }

    public void setInputScript(String inputScript) { this.inputScript = inputScript; }

    public void setInputPostId(String inputPostId) { this.inputPostId = inputPostId; }

    public void setInputTimeStamp(String inputTimeStamp) { this.inputTimeStamp = inputTimeStamp; }

    public ReplyRequest(String userId, String Script, String postId, String timestamp) {
        this.inputId = userId;
        this.inputScript = Script;
        this.inputPostId = postId;
        this.inputTimeStamp = timestamp;
    }

}
