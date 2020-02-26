package com.example.muthobank.send_money;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.muthobank.R;
import com.example.muthobank.app.SharedPreferenceManager;

import java.util.HashMap;

public class SendMoney_page_three extends AppCompatActivity {

    private SharedPreferenceManager preferenceManager;
    private TextView _amount,_availableBalance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_money_page_three);

        preferenceManager= new SharedPreferenceManager(getApplicationContext());
        _amount = findViewById(R.id.tv_page_three_amount);
        _availableBalance = findViewById(R.id.tv_page_three_available_amount);


        HashMap<String, String> getData = preferenceManager.getSendMoneyDetails();

        String getting_amount = getData.get(SharedPreferenceManager.KEY_SEND_CURRENCY);
        String getting_available_balance = String.valueOf(preferenceManager.getAmount(SharedPreferenceManager.KEY_AMOUNT,0));
        _amount.setText("$"+getting_amount);
        _availableBalance.setText("Available Balance: $"+getting_available_balance);

        findViewById(R.id.back_to_page_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pageTwo = new Intent(getApplicationContext(), SendMoney_page_two.class);
                pageTwo.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(pageTwo);

            }
        });

        findViewById(R.id.page_three_continue_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                startActivity(new Intent(getApplicationContext(),SendMoney_page_four.class));
            }
        });
    }
}
