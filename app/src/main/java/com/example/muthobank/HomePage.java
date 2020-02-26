package com.example.muthobank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.muthobank.api.ApiInterface;
import com.example.muthobank.api.ApiUtils;
import com.example.muthobank.app.Constants;
import com.example.muthobank.app.SharedPreferenceManager;
import com.example.muthobank.model.TransactionsPostModel;
import com.example.muthobank.send_money.SendMoney_page_one;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePage extends AppCompatActivity {

    private LinearLayout sendMoneyBtn, cardDetailsBtn;
    private String card_number, bank_number;
    private String amount;
    private TextView _gAmount;
    private SharedPreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        preferenceManager = new SharedPreferenceManager(getApplicationContext());
        preferenceManager.checkLogin();


//        Bundle b = getIntent().getExtras();
//        _gAmount = findViewById(R.id.tv_amount);
//
//        if (b != null) {
//            amount = getIntent().getStringExtra("AMOUNT");
//            card_number = getIntent().getStringExtra("CARD_NUMBER");
//            bank_number = getIntent().getStringExtra("BANK_ACCOUNT");
//            _gAmount.setText("$"+amount);
//        }

        sendMoneyBtn = findViewById(R.id.send_money_btn);
        cardDetailsBtn = findViewById(R.id.card_details_btn);
        _gAmount = findViewById(R.id.tv_amount);

        _gAmount.setText("$"+preferenceManager.getAmount(SharedPreferenceManager.KEY_AMOUNT,0));


        sendMoneyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), SendMoney_page_one.class));
            }
        });

        cardDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), CardInfo.class));

            }
        });

        getTransactions();

    }



    private void getTransactions(){

        ApiInterface apiInterface = ApiUtils.getApiInterface();
        Call<List<TransactionsPostModel>> call = apiInterface.getTransactions(5);
        call.enqueue(new Callback<List<TransactionsPostModel>>() {
            @Override
            public void onResponse(Call<List<TransactionsPostModel>> call, Response<List<TransactionsPostModel>> response) {

                if (response.isSuccessful()) {

                    Toast.makeText(HomePage.this, "Successful!", Toast.LENGTH_SHORT).show();

                    Log.i("data", "onResponse: "+response.body());
                }
            }

            @Override
            public void onFailure(Call<List<TransactionsPostModel>> call, Throwable t) {

                Toast.makeText(HomePage.this, "Error!", Toast.LENGTH_SHORT).show();
                Log.i("data", "onResponse: "+t.getMessage());

            }
        });


    }
}
