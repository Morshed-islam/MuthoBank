package com.example.muthobank.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegPostResponse {
    @SerializedName("success")
    @Expose
    private String success;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
