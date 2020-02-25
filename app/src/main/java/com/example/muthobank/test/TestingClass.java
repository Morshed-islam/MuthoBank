package com.example.muthobank.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.muthobank.R;
import com.example.muthobank.api.ApiInterface;
import com.example.muthobank.api.ApiUtils;
import com.example.muthobank.model.LoginPostModel;
import com.example.muthobank.model.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestingClass extends AppCompatActivity {

    private static final String TAG = "TEST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_class);

        findViewById(R.id.send_test_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();
            }
        });
    }


    private void login(){

        LoginPostModel model = new LoginPostModel("01832817105",1243);

        ApiInterface apiInterface = ApiUtils.getApiInterface();
        Call<LoginResponse> call = apiInterface.postUserToLog(model);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {

                    Toast.makeText(TestingClass.this, "Success!", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(TestingClass.this, "below Success!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t.getMessage());
                Toast.makeText(TestingClass.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
