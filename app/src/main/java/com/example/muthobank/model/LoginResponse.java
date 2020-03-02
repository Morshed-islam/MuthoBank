package com.example.muthobank.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("card_number")
    @Expose
    private String cardNumber;
    @SerializedName("bank_account")
    @Expose
    private String bankAccount;
    @SerializedName("rest_amount")
    @Expose
    private Integer amount;

    @SerializedName("amount")
    @Expose
    private Integer fixedAmount;


    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("valid_date")
    @Expose
    private String valid_date;

    public LoginResponse() {
    }

    public Integer getFixedAmount() {
        return fixedAmount;
    }

    public void setFixedAmount(Integer fixedAmount) {
        this.fixedAmount = fixedAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValid_date() {
        return valid_date;
    }

    public void setValid_date(String valid_date) {
        this.valid_date = valid_date;
    }
}
