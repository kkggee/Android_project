package com.example.test.DTO.http;

import com.google.gson.annotations.SerializedName;

public class PostGetRequest {
    @SerializedName("post_id")
    public String postId;

    public String getPostId() { return postId; }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public PostGetRequest(String postId) {
        this.postId = postId;
    }
}
