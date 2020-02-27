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
import com.example.muthobank.api.ApiInterface;
import com.example.muthobank.api.ApiUtils;
import com.example.muthobank.app.SharedPreferenceManager;
import com.example.muthobank.model.SendMoneyPostModel;
import com.example.muthobank.model.SendMoneyResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendMoney_page_five extends AppCompatActivity {

    private SharedPreferenceManager preferenceManager;
    private TextView _toolbarAmount,_holderName,_amount,_greetings;
    private EditText _edPin;
    private String getting_amount;
    private String getting_holder_name;
    private String greetings;
    private String customer_id;
    private String bank_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_money_page_five);

        preferenceManager = new SharedPreferenceManager(getApplicationContext());
        initViews();

        //set pref data to views
        HashMap<String, String> getData = preferenceManager.getSendMoneyDetails();
         getting_amount = getData.get(SharedPreferenceManager.KEY_SEND_CURRENCY);
         getting_holder_name = getData.get(SharedPreferenceManager.KEY_SEND_HOLDER_NAME);
         bank_number = getData.get(SharedPreferenceManager.KEY_SEND_BANK_NUMBER);
         greetings = preferenceManager.getGreetings(SharedPreferenceManager.KEY_SEND_GREETINGS,"");
         customer_id = String.valueOf(preferenceManager.getID(SharedPreferenceManager.KEY_ID,0));


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

                    postSendMoney(customer_id,getting_amount,getting_holder_name,bank_number,greetings);

                }else {
                    Toast.makeText(SendMoney_page_five.this, "Incorrect Pin!", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    private void postSendMoney(String customerId,String amount,String holderName,String holderBank,String greet) {

        SendMoneyPostModel sendMoneyPostModel = new SendMoneyPostModel(customerId,amount,holderName,holderBank,greet);

        ApiInterface apiInterface = ApiUtils.getApiInterface();
        Call<SendMoneyResponse> call = apiInterface.postSendMoney(sendMoneyPostModel);

        call.enqueue(new Callback<SendMoneyResponse>() {
            @Override
            public void onResponse(Call<SendMoneyResponse> call, Response<SendMoneyResponse> response) {

                if (response.isSuccessful()){

                    int amountCalculations = preferenceManager.getAmount(SharedPreferenceManager.KEY_AMOUNT,0);
                    int substractions =(amountCalculations- Integer.parseInt(getting_amount));
                    preferenceManager.putAmount(SharedPreferenceManager.KEY_AMOUNT,substractions);
                    Toast.makeText(SendMoney_page_five.this, ""+substractions, Toast.LENGTH_SHORT).show();
                    ViewDialog viewDialog = new ViewDialog();
                    viewDialog.showDialog(SendMoney_page_five.this,substractions);

                }else {

                    Toast.makeText(SendMoney_page_five.this, "bank Account not found", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SendMoneyResponse> call, Throwable t) {

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

        public void showDialog(Activity activity,int amount){
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.payment_succes_dialog);

            TextView dialogAmount = dialog.findViewById(R.id.dialog_amount);
            dialogAmount.setText("Your New Balance \n $"+amount);
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
