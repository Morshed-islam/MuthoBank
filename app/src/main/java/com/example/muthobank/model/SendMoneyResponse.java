package com.example.muthobank.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendMoneyResponse {
    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("error")
    @Expose
    private String error;

    public SendMoneyResponse() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
