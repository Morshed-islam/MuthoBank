package com.example.muthobank.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.muthobank.R;
import com.example.muthobank.api.ApiInterface;
import com.example.muthobank.api.ApiUtils;
import com.example.muthobank.model.RegPostResponse;
import com.example.muthobank.model.RegistrationPostModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    private ImageView backToLogin;
    private Button continueBtn;
    private EditText _firstName, _lastName, _emailAddress, _day, _month, _year, _edPassword, _phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        backToLogin = findViewById(R.id.back_to_login);
        initViews();

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToLogin = new Intent(getApplicationContext(), Login.class);
                backToLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(backToLogin);
            }
        });


        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signUp();
            }


        });


    }

    private void initViews() {

        _firstName = findViewById(R.id.ed_first_name);
        _lastName = findViewById(R.id.ed_last_name);
        _emailAddress = findViewById(R.id.ed_email);
        _day = findViewById(R.id.ed_day);
        _month = findViewById(R.id.ed_month);
        _year = findViewById(R.id.ed_year);
        _phone = findViewById(R.id.ed_phone);
        _edPassword = findViewById(R.id.ed_password);
        continueBtn = findViewById(R.id.btn_continue);

    }


    private void signUp() {

        String inputFirstName = _firstName.getText().toString().trim();
        String inputLastName = _lastName.getText().toString().trim();
        String inputEmail = _emailAddress.getText().toString().trim();
        String inputPhone = _phone.getText().toString().trim();
        String inputDay = _day.getText().toString().trim();
        String inputMonth = _month.getText().toString().trim();
        String inputYear = _year.getText().toString().trim();

        if (TextUtils.isEmpty(inputFirstName) || TextUtils.isEmpty(inputLastName)|| TextUtils.isEmpty(inputEmail)|| TextUtils.isEmpty(inputPhone)
                || TextUtils.isEmpty(inputDay)|| TextUtils.isEmpty(inputMonth)|| TextUtils.isEmpty(inputYear)) {
            // username / password doesn't match
            Toast.makeText(this, "Login failed.. Required All Field!", Toast.LENGTH_SHORT).show();
            return;
        }

        String dateOfBirth = inputDay + "/" + inputMonth + "/" + inputYear;

        String inputPass = _edPassword.getText().toString().trim();

        RegistrationPostModel registrationPostModel = new RegistrationPostModel(inputFirstName, inputLastName, inputEmail, inputPhone, dateOfBirth, inputPass);

        ApiInterface apiInterface = ApiUtils.getApiInterface();
        Call<RegPostResponse> call = apiInterface.postReg(registrationPostModel);

        call.enqueue(new Callback<RegPostResponse>() {
            @Override
            public void onResponse(Call<RegPostResponse> call, Response<RegPostResponse> response) {

                if (response.isSuccessful()) {

                    Toast.makeText(SignUp.this, "" + response.body().getSuccess(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<RegPostResponse> call, Throwable t) {

                Toast.makeText(SignUp.this, "error", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
