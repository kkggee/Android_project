package com.example.test.DTO.http;

import com.google.gson.annotations.SerializedName;

public class ReplyEditRequest {
    @SerializedName("reply_id")
    public String replyId;

    @SerializedName("script")
    public String script;

    @SerializedName("timestamp")
    public String timestamp;

    public String getReplyId() { return replyId; }

    public String getScript() { return script; }

    public String getTimestamp() { return timestamp; }

    public void setReplyId(String replyId) { this.replyId = replyId; }

    public void setScript(String script) { this.script = script; }

    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }


    public ReplyEditRequest(String replyId, String script, String timestamp) {
        this.replyId = replyId;
        this.script = script;
        this.timestamp = timestamp;
    }
}
