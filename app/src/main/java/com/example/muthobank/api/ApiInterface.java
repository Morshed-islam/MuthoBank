package com.example.muthobank.api;

import com.example.muthobank.model.LoginPostModel;
import com.example.muthobank.model.LoginResponse;
import com.example.muthobank.model.RegPostResponse;
import com.example.muthobank.model.RegistrationPostModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST(HttpParams.API_GET_USER_LOGIN)
    Call<LoginResponse> postUserToLog(@Body LoginPostModel loginPostModel);

    @POST(HttpParams.API_REGISTRATION)
    Call<RegPostResponse> postReg(@Body RegistrationPostModel postModel);
}
