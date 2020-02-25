package com.example.muthobank.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.muthobank.HomePage;
import com.example.muthobank.R;
import com.example.muthobank.api.ApiInterface;
import com.example.muthobank.api.ApiUtils;
import com.example.muthobank.app.Constants;
import com.example.muthobank.app.SharedPreferenceManager;
import com.example.muthobank.model.LoginPostModel;
import com.example.muthobank.model.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private TextView createNewAccount;
    private Button logInBtn;
    private EditText _gPhone, _gPass;
    private SharedPreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferenceManager = new SharedPreferenceManager(getApplicationContext());
        initViews();

        createNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUp.class));
            }
        });

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getUserCredential();
//                startActivity(new Intent(getApplicationContext(), HomePage.class));
            }
        });


    }

    private void initViews() {

        createNewAccount = findViewById(R.id.btn_create_new_account);
        logInBtn = findViewById(R.id.btn_log_in);
        _gPhone = findViewById(R.id.ed_login_phone);
        _gPass = findViewById(R.id.ed_login_pass);
    }


    private void getUserCredential() {

        //TODO
        final String inputPhone = _gPhone.getText().toString().trim();
        final int inputPassword = Integer.parseInt(_gPass.getText().toString().trim());

        if (TextUtils.isEmpty(inputPhone) || TextUtils.isEmpty(String.valueOf(inputPassword))) {
            // username / password doesn't match
            Toast.makeText(this, "Login failed.. Input Phone/Pass", Toast.LENGTH_SHORT).show();
            return;
        }

        LoginPostModel loginPostModel = new LoginPostModel(inputPhone, inputPassword);

        ApiInterface apiInterface = ApiUtils.getApiInterface();

        Call<LoginResponse> call = apiInterface.postUserToLog(loginPostModel);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()) {
                    Log.d("values", "onResponse: " + response.body().getSuccess());
                    int amount = response.body().getAmount();

                    preferenceManager.createLoginSession(inputPhone, inputPassword, amount, response.body().getCardNumber(), response.body().getBankAccount(),
                            response.body().getName(),response.body().getValid_date());
                    preferenceManager.putInt(SharedPreferenceManager.KEY_AMOUNT,amount);
//                    preferenceManager.putCardNumber(SharedPreferenceManager.KEY_CARD_NUMBER,response.body().getCardNumber());

                    Intent nextToHome = new Intent(getApplicationContext(), HomePage.class);
                    nextToHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    nextToHome.putExtra("AMOUNT",String.valueOf(amount));
//                    nextToHome.putExtra("CARD_NUMBER",response.body().getCardNumber());
//                    nextToHome.putExtra("BANK_ACCOUNT",response.body().getBankAccount());
                    startActivity(nextToHome);

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Toast.makeText(Login.this, "Server Error!", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
