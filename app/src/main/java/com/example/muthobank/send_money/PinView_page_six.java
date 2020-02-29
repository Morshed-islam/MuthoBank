package com.example.muthobank.send_money;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;
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

public class PinView_page_six extends AppCompatActivity {

    private static final String TAG = "PIN";
    private PinLockView mPinLockView;
    private IndicatorDots mIndicatorDots;

    private SharedPreferenceManager preferenceManager;

    private String getting_amount;
    private String getting_holder_name;
    private String greetings;
    private String customer_id;
    private String bank_number;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pin_view_page_six);

        preferenceManager = new SharedPreferenceManager(getApplicationContext());

        HashMap<String, String> getData = preferenceManager.getSendMoneyDetails();
        getting_amount = getData.get(SharedPreferenceManager.KEY_SEND_CURRENCY);
        getting_holder_name = getData.get(SharedPreferenceManager.KEY_SEND_HOLDER_NAME);
        bank_number = getData.get(SharedPreferenceManager.KEY_SEND_BANK_NUMBER);
        greetings = preferenceManager.getGreetings(SharedPreferenceManager.KEY_SEND_GREETINGS, "");
        customer_id = String.valueOf(preferenceManager.getID(SharedPreferenceManager.KEY_ID, 0));


        mPinLockView = (PinLockView) findViewById(R.id.pin_lock_view);
        mIndicatorDots = findViewById(R.id.indicator_dots);
        mPinLockView.setPinLockListener(mPinLockListener);

        mPinLockView.attachIndicatorDots(mIndicatorDots);
        mPinLockView.setPinLockListener(mPinLockListener);
        //mPinLockView.setCustomKeySet(new int[]{2, 3, 1, 5, 9, 6, 7, 0, 8, 4});
        //mPinLockView.enableLayoutShuffling();

        mPinLockView.setPinLength(4);
        mPinLockView.setTextColor(ContextCompat.getColor(this, R.color.black));

        mIndicatorDots.setIndicatorType(IndicatorDots.IndicatorType.FIXED);
        mPinLockView.setShowDeleteButton(true);


        findViewById(R.id.back_to_page_five).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pageFour = new Intent(getApplicationContext(), SendMoney_page_five.class);
                pageFour.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(pageFour);

            }
        });

    }



    private PinLockListener mPinLockListener = new PinLockListener() {
        @Override
        public void onComplete(String pin) {
            Log.d(TAG, "Pin complete: " + pin);
            String getPin = String.valueOf(preferenceManager.getPin(SharedPreferenceManager.KEY_PASS, 0));
            if(pin.equals(getPin)){
                postSendMoney(customer_id, getting_amount, getting_holder_name, bank_number, greetings);

            }else {
                Toast.makeText(PinView_page_six.this, "Incorrect Error!", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onEmpty() {
            Log.d(TAG, "Pin empty");
        }

        @Override
        public void onPinChange(int pinLength, String intermediatePin) {
            Log.d(TAG, "Pin changed, new length " + pinLength + " with intermediate pin " + intermediatePin);
        }
    };





    private void postSendMoney(String customerId, String amount, String holderName, String holderBank, String greet) {

        SendMoneyPostModel sendMoneyPostModel = new SendMoneyPostModel(customerId, amount, holderName, holderBank, greet);

        ApiInterface apiInterface = ApiUtils.getApiInterface();
        Call<SendMoneyResponse> call = apiInterface.postSendMoney(sendMoneyPostModel);

        call.enqueue(new Callback<SendMoneyResponse>() {
            @Override
            public void onResponse(Call<SendMoneyResponse> call, Response<SendMoneyResponse> response) {

                if (response.isSuccessful()) {

                    int amountCalculations = preferenceManager.getAmount(SharedPreferenceManager.KEY_AMOUNT, 0);
                    int substractions = (amountCalculations - Integer.parseInt(getting_amount));
                    preferenceManager.putAmount(SharedPreferenceManager.KEY_AMOUNT, substractions);
//                    Toast.makeText(PinView_page_six.this, "" + substractions, Toast.LENGTH_SHORT).show();
                    ViewDialog viewDialog = new ViewDialog();
                    viewDialog.showDialog(PinView_page_six.this, substractions);

                } else {

                    Toast.makeText(PinView_page_six.this, "bank Account not found", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SendMoneyResponse> call, Throwable t) {

            }
        });

    }



    public class ViewDialog {

        public void showDialog(Activity activity, int amount) {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.payment_succes_dialog);

            TextView dialogAmount = dialog.findViewById(R.id.dialog_amount);
            dialogAmount.setText("Your New Balance \n $" + amount);
            Button dialogButton = dialog.findViewById(R.id.okay_dialog_btn);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();

                    Intent goToHome = new Intent(getApplicationContext(), HomePage.class);
                    goToHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(goToHome);
                    finish();
                }
            });

            dialog.show();

        }
    }
}