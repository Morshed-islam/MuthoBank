package com.example.muthobank.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendMoneyPostModel {

    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("send_money")
    @Expose
    private String sendMoney;
    @SerializedName("account_holder_name")
    @Expose
    private String accountHolderName;
    @SerializedName("holder_bank_number")
    @Expose
    private String holderBankNumber;
    @SerializedName("greetings")
    @Expose
    private String greetings;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getSendMoney() {
        return sendMoney;
    }

    public void setSendMoney(String sendMoney) {
        this.sendMoney = sendMoney;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getHolderBankNumber() {
        return holderBankNumber;
    }

    public void setHolderBankNumber(String holderBankNumber) {
        this.holderBankNumber = holderBankNumber;
    }

    public String getGreetings() {
        return greetings;
    }

    public void setGreetings(String greetings) {
        this.greetings = greetings;
    }
}
