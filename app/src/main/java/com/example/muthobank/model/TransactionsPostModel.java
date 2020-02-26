package com.example.muthobank.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionsPostModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("customer_id")
    @Expose
    private Integer customerId;
    @SerializedName("account_holder_name")
    @Expose
    private String accountHolderName;
    @SerializedName("holder_bank_number")
    @Expose
    private String holderBankNumber;
    @SerializedName("send_money")
    @Expose
    private String sendMoney;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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

    public String getSendMoney() {
        return sendMoney;
    }

    public void setSendMoney(String sendMoney) {
        this.sendMoney = sendMoney;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

//    @SerializedName("id")
//    @Expose
//    private Integer id;
//    @SerializedName("customer_id")
//    @Expose
//    private Integer customerId;
//    @SerializedName("account_holder_name")
//    @Expose
//    private String accountHolderName;
//    @SerializedName("holder_bank_number")
//    @Expose
//    private String holderBankNumber;
//    @SerializedName("send_money")
//    @Expose
//    private String sendMoney;
//    @SerializedName("created_at")
//    @Expose
//    private String createdAt;
//    @SerializedName("updated_at")
//    @Expose
//    private String updatedAt;
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public Integer getCustomerId() {
//        return customerId;
//    }
//
//    public void setCustomerId(Integer customerId) {
//        this.customerId = customerId;
//    }
//
//    public String getAccountHolderName() {
//        return accountHolderName;
//    }
//
//    public void setAccountHolderName(String accountHolderName) {
//        this.accountHolderName = accountHolderName;
//    }
//
//    public String getHolderBankNumber() {
//        return holderBankNumber;
//    }
//
//    public void setHolderBankNumber(String holderBankNumber) {
//        this.holderBankNumber = holderBankNumber;
//    }
//
//    public String getSendMoney() {
//        return sendMoney;
//    }
//
//    public void setSendMoney(String sendMoney) {
//        this.sendMoney = sendMoney;
//    }
//
//    public String getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(String createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public String getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(String updatedAt) {
//        this.updatedAt = updatedAt;
//    }

}
