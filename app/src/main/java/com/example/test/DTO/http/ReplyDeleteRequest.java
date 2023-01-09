package com.example.test.DTO.http;

import com.google.gson.annotations.SerializedName;

public class ReplyDeleteRequest {
    @SerializedName("reply_id")
    public String replyId;

    public String getReplyId() { return replyId; }

    public void setReplyId(String replyId) { this.replyId = replyId; }

    public ReplyDeleteRequest(String replyId) {
        this.replyId = replyId;
    }
}
