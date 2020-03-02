package com.example.muthobank.send_money;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.muthobank.R;
import com.example.muthobank.app.SharedPreferenceManager;

import java.util.HashMap;

public class SendMoney_page_three extends AppCompatActivity {

    private SharedPreferenceManager preferenceManager;
    private TextView _availableBalance;
    private EditText _amount;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_money_page_three);

        preferenceManager= new SharedPreferenceManager(getApplicationContext());
        _amount = findViewById(R.id.tv_page_three_amount);
        _availableBalance = findViewById(R.id.tv_page_three_available_amount);

        String curencyType_fromPageTwo = getIntent().getStringExtra("PAGE_THREE_CURRENCY_TYPE");
        final String holderName_fromPageTwo =getIntent().getStringExtra("PAGE_THREE_HOLDER_NAME");
        final String bankNumber_fromPageTwo =getIntent().getStringExtra("PAGE_THREE_BANK_ACCOUNT");

//        HashMap<String, String> getData = preferenceManager.getSendMoneyDetails();

//        String getting_amount = getData.get(SharedPreferenceManager.KEY_SEND_CURRENCY);
        String getting_available_balance = String.valueOf(preferenceManager.getAmount(SharedPreferenceManager.KEY_AMOUNT,0));
//        _amount.setText("");
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

                if (_amount.getText().toString().isEmpty()){
                    Toast.makeText(SendMoney_page_three.this, "Enter your Amount Please!", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (!_amount.getText().toString().isEmpty()){

                    int ed_amount = Integer.parseInt(_amount.getText().toString());
                    if (ed_amount < 5) {
                        Toast.makeText(SendMoney_page_three.this, "Minimum Sending balance 5", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    int amount = preferenceManager.getAmount(SharedPreferenceManager.KEY_AMOUNT, 0);
                    if (ed_amount > amount) {
                        Toast.makeText(SendMoney_page_three.this, "You have not Enough balance", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    preferenceManager.createSendMoneySession(ed_amount, bankNumber_fromPageTwo, holderName_fromPageTwo);
                    startActivity(new Intent(getApplicationContext(),SendMoney_page_four.class));
                }

            }
        });

    }
}
