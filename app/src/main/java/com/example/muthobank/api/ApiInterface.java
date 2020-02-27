package com.example.muthobank.api;

import com.example.muthobank.model.LoginPostModel;
import com.example.muthobank.model.LoginResponse;
import com.example.muthobank.model.RegPostResponse;
import com.example.muthobank.model.RegistrationPostModel;
import com.example.muthobank.model.SendMoneyPostModel;
import com.example.muthobank.model.SendMoneyResponse;
import com.example.muthobank.model.TransactionsPostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @POST(HttpParams.API_GET_USER_LOGIN)
    Call<LoginResponse> postUserToLog(@Body LoginPostModel loginPostModel);

    @POST(HttpParams.API_REGISTRATION)
    Call<RegPostResponse> postReg(@Body RegistrationPostModel postModel);

    @POST(HttpParams.API_SEND_MONEY)
    Call<SendMoneyResponse> postSendMoney(@Body SendMoneyPostModel postModel);

    @GET("cash_out/{customer_id}")
    Call<List<TransactionsPostModel>> getTransactions(@Path("customer_id") int customer_id);

}
