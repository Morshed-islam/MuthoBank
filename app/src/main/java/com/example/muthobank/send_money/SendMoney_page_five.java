package com.example.muthobank.send_money;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.muthobank.HomePage;
import com.example.muthobank.R;
import com.example.muthobank.app.SharedPreferenceManager;

import java.util.HashMap;

public class SendMoney_page_five extends AppCompatActivity {

    private SharedPreferenceManager preferenceManager;
    private TextView _toolbarAmount,_holderName,_amount,_greetings;
    private EditText _edPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_money_page_five);

        preferenceManager = new SharedPreferenceManager(getApplicationContext());
        initViews();

        //set pref data to views
        HashMap<String, String> getData = preferenceManager.getSendMoneyDetails();
        String getting_amount = getData.get(SharedPreferenceManager.KEY_SEND_CURRENCY);
        String getting_holder_name = getData.get(SharedPreferenceManager.KEY_SEND_HOLDER_NAME);
        String greetings = preferenceManager.getGreetings(SharedPreferenceManager.KEY_SEND_GREETINGS,"");

        _toolbarAmount.setText("$"+getting_amount+" for "+getting_holder_name);
        _holderName.setText(getting_holder_name);
        _amount.setText(getting_amount);
        _greetings.setText(greetings);



        findViewById(R.id.back_to_page_four).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pageFour = new Intent(getApplicationContext(), SendMoney_page_four.class);
                pageFour.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(pageFour);

            }
        });

        findViewById(R.id.page_five_sendBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getPin = String.valueOf(preferenceManager.getPin(SharedPreferenceManager.KEY_PASS,0));

                String mPin = _edPin.getText().toString();
                if (TextUtils.isEmpty(mPin)){
                    Toast.makeText(SendMoney_page_five.this, "Enter your Pin!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (getPin.equals(mPin)){
                    ViewDialog viewDialog = new ViewDialog();
                    viewDialog.showDialog(SendMoney_page_five.this);
                }else {
                    Toast.makeText(SendMoney_page_five.this, "Incorrect Pin!", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    private void initViews() {

        _edPin = findViewById(R.id.tv_page_five_pin);
         _toolbarAmount= findViewById(R.id.tv_page_five_toolbar);
        _holderName= findViewById(R.id.tv_page_five_holder_name);
        _amount= findViewById(R.id.tv_page_five_amount);
        _greetings = findViewById(R.id.tv_page_five_greetings);

    }


    public class ViewDialog {

        public void showDialog(Activity activity){
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.payment_succes_dialog);

            Button dialogButton = dialog.findViewById(R.id.okay_dialog_btn);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();

                    Intent goToHome = new Intent(getApplicationContext(),HomePage.class);
                    goToHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(goToHome);
                    finish();
                }
            });

            dialog.show();

        }
    }
}
