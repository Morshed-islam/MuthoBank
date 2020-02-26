package com.example.muthobank.send_money;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.muthobank.R;
import com.example.muthobank.app.SharedPreferenceManager;

public class SendMoney_page_two extends AppCompatActivity {

    private EditText _senderCurrency,_senderHolderName,_senderBankAccount;
    private Spinner _senderCurrencyType;
    private SharedPreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_money_page_two);

        preferenceManager = new SharedPreferenceManager(getApplicationContext());
        initViews();

        findViewById(R.id.back_to_page_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pageOne = new Intent(getApplicationContext(), SendMoney_page_one.class);
                pageOne.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(pageOne);

            }
        });

        findViewById(R.id.new_recipient_continue_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String mCurreency = _senderCurrency.getText().toString();
                String mCurrencyType = _senderCurrencyType.getSelectedItem().toString();
                String mBankNumber = _senderBankAccount.getText().toString();
                String mHolderName= _senderHolderName.getText().toString();


                if (TextUtils.isEmpty(mCurreency) || TextUtils.isEmpty(mBankNumber) || TextUtils.isEmpty(mHolderName)){

                    Toast.makeText(SendMoney_page_two.this, "All Field Required!", Toast.LENGTH_SHORT).show();
                    return;
                }
                int ed_amount = Integer.parseInt(_senderCurrency.getText().toString());

                if (ed_amount < 5){
                    Toast.makeText(SendMoney_page_two.this, "Minimum Sending balance 5", Toast.LENGTH_SHORT).show();
                    return;
                }

                int amount = preferenceManager.getAmount(SharedPreferenceManager.KEY_AMOUNT,0);
                if (ed_amount > amount ){
                    Toast.makeText(SendMoney_page_two.this, "You have not Enough balance", Toast.LENGTH_SHORT).show();
                    return;
                }else {

                    preferenceManager.createSendMoneySession(ed_amount,mBankNumber,mHolderName);
                    startActivity(new Intent(getApplicationContext(),SendMoney_page_three.class));
                }



            }
        });
    }

    private void initViews() {

        _senderCurrency = findViewById(R.id.sender_currency);
        _senderCurrencyType= findViewById(R.id.sender_currency_type);
        _senderHolderName= findViewById(R.id.sender_holder_name);
        _senderBankAccount= findViewById(R.id.sender_bank_number);

    }
}
