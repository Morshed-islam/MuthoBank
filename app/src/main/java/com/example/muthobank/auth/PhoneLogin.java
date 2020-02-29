package com.example.muthobank.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.muthobank.HomePage;
import com.example.muthobank.R;
import com.example.muthobank.api.ApiInterface;
import com.example.muthobank.api.ApiUtils;
import com.example.muthobank.app.SharedPreferenceManager;
import com.example.muthobank.model.LoginPostModel;
import com.example.muthobank.model.LoginResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.ybs.countrypicker.CountryPicker;
import com.ybs.countrypicker.CountryPickerListener;

import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhoneLogin extends AppCompatActivity {

    private String phoneVerificationId;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            verificationCallbacks;
    private PhoneAuthProvider.ForceResendingToken resendToken;
    private FirebaseAuth fbAuth;
    EditText phoneText, digit1, digit2, digit3, digit4, digit5, digit6;
    TextView countrytxt, countrycodetxt, sendtotxt;
    RelativeLayout select_country;
    ViewFlipper viewFlipper;
    String phoneNumber;
//    public IOSDialog iosDialog;

    private ProgressDialog mRegProgress;

    SharedPreferenceManager preferenceManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login);
        fbAuth = FirebaseAuth.getInstance();

        preferenceManager = new SharedPreferenceManager(getApplicationContext());
        mRegProgress = new ProgressDialog(this);

        phoneText = findViewById(R.id.phonetxt);

        select_country = findViewById(R.id.select_country);
        select_country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Opencountry();
            }
        });

        countrytxt = findViewById(R.id.countrytxt);
        countrycodetxt = findViewById(R.id.countrycodetxt);

        sendtotxt = findViewById(R.id.sendtotxt);

        viewFlipper = findViewById(R.id.viewfillper);
        codefill();


    }


    public void codefill() {
        digit1 = findViewById(R.id.digitone);
        digit2 = findViewById(R.id.digittwo);
        digit3 = findViewById(R.id.digitthree);
        digit4 = findViewById(R.id.digitfour);
        digit5 = findViewById(R.id.digitfive);
        digit6 = findViewById(R.id.digitsix);

        digit1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (digit1.getText().toString().length() == 0) {
                    digit2.requestFocus();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        digit2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (digit2.getText().toString().length() == 0) {
                    digit3.requestFocus();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        digit3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (digit3.getText().toString().length() == 0) {
                    digit4.requestFocus();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        digit4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (digit4.getText().toString().length() == 0) {
                    digit5.requestFocus();
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        digit5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (digit5.getText().toString().length() == 0) {
                    digit6.requestFocus();
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    String country_iso_code = "US";
    @SuppressLint("WrongConstant")
    public void Opencountry() {
        final CountryPicker picker = CountryPicker.newInstance("Select Country");
        picker.setListener(new CountryPickerListener() {
            @Override
            public void onSelectCountry(String name, String code, String dialCode, int flagDrawableResID) {
                // Implement your code here
                countrytxt.setText(name);
                countrycodetxt.setText(dialCode);
                picker.dismiss();
                country_iso_code = code;
            }
        });
        picker.setStyle(R.style.countrypicker_style, R.style.countrypicker_style);
        picker.show(getSupportFragmentManager(), "Select Country");
    }

    public void Nextbtn(View view) {
        phoneNumber = countrycodetxt.getText().toString() + phoneText.getText().toString();
        Send_Number_tofirebase(phoneNumber);
    }

    public void Send_Number_tofirebase(String phoneNumber) {
        setUpVerificatonCallbacks();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                verificationCallbacks);
    }


    private void setUpVerificatonCallbacks() {
//        iosDialog.show();
        verificationCallbacks =
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential credential) {

//                        iosDialog.cancel();
                        signInWithPhoneAuthCredential(credential);

                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
//                        iosDialog.cancel();
                        Log.d("responce", e.toString());
                        Toast.makeText(PhoneLogin.this, "Enter Correct Number.", Toast.LENGTH_SHORT).show();
                        if (e instanceof FirebaseAuthInvalidCredentialsException) {
                            // Invalid request
                        } else if (e instanceof FirebaseTooManyRequestsException) {
                            // SMS quota exceeded
                        }
                    }

                    @Override
                    public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {

//                        iosDialog.cancel();

                        phoneVerificationId = verificationId;
                        resendToken = token;
                        sendtotxt.setText("Send to ( " + phoneNumber + " )");
//                        iosDialog.cancel();
//                        viewFlipper.setInAnimation(PhoneLogin.this, R.anim.in_from_right);
//                        viewFlipper.setOutAnimation(Login_Phone_A.this, R.anim.out_to_left);
                        viewFlipper.setDisplayedChild(1);

                    }
                };
    }


    public void verifyCode(View view) {
        String code = "" + digit1.getText().toString() + digit2.getText().toString() + digit3.getText().toString() + digit4.getText().toString() + digit5.getText().toString() + digit6.getText().toString();
        if (!code.equals("")) {
//            iosDialog.show();
            PhoneAuthCredential credential =
                    PhoneAuthProvider.getCredential(phoneVerificationId, code);
            signInWithPhoneAuthCredential(credential);
        } else {
            Toast.makeText(this, "Enter the Correct verification Code", Toast.LENGTH_SHORT).show();
        }

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        fbAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            //TODO get the user info to know that user is already login or not
                            Get_User_info();
                            Toast.makeText(PhoneLogin.this, "Done!", Toast.LENGTH_SHORT).show();

                        } else {
                            if (task.getException() instanceof
                                    FirebaseAuthInvalidCredentialsException) {
//                                iosDialog.cancel();
                            }
                        }
                    }
                });
    }

    private void Get_User_info() {


        //TODO
//        final String inputPhone = _gPhone.getText().toString().trim();
//        final int inputPassword = Integer.parseInt(_gPass.getText().toString().trim());
//
//        if (TextUtils.isEmpty(inputPhone) || TextUtils.isEmpty(String.valueOf(inputPassword))) {
//            // username / password doesn't match
//            Toast.makeText(this, "Login failed.. Input Phone/Pass", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        mRegProgress.setTitle("Logging.....");
//        mRegProgress.setMessage("Please wait while we login your account !");
//        mRegProgress.setCanceledOnTouchOutside(false);
//        mRegProgress.show();
//
//        LoginPostModel loginPostModel = new LoginPostModel(inputPhone, inputPassword);
//
//        ApiInterface apiInterface = ApiUtils.getApiInterface();
//
//        Call<LoginResponse> call = apiInterface.postUserToLog(loginPostModel);
//
//        call.enqueue(new Callback<LoginResponse>() {
//            @Override
//            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//
//                if (response.isSuccessful()) {
//                    Log.d("values", "onResponse: " + response.body().getSuccess());
//                    int amount = response.body().getAmount();
////                    Toast.makeText(Login.this, ""+amount, Toast.LENGTH_SHORT).show();
//                    mRegProgress.dismiss();
//                    preferenceManager.createLoginSession(inputPhone, inputPassword, response.body().getCardNumber(), response.body().getBankAccount(),
//                            response.body().getName(), response.body().getValid_date());
//                    preferenceManager.putAmount(SharedPreferenceManager.KEY_AMOUNT, amount);
//                    preferenceManager.putID(SharedPreferenceManager.KEY_ID, response.body().getId());
//                    preferenceManager.putPin(SharedPreferenceManager.KEY_PASS, inputPassword);
//
//                    Intent nextToHome = new Intent(getApplicationContext(), HomePage.class);
//                    nextToHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////                    nextToHome.putExtra("AMOUNT",String.valueOf(amount));
////                    nextToHome.putExtra("CARD_NUMBER",response.body().getCardNumber());
////                    nextToHome.putExtra("BANK_ACCOUNT",response.body().getBankAccount());
//                    startActivity(nextToHome);
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<LoginResponse> call, Throwable t) {
//
//                Toast.makeText(Login.this, "Server Error!", Toast.LENGTH_SHORT).show();
//                mRegProgress.dismiss();
//
//            }
//        });



    }


    public void resendCode(View view) {

        String phoneNumber = phoneText.getText().toString();

        setUpVerificatonCallbacks();

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                this,
                verificationCallbacks,
                resendToken);
    }

    public void Goback_1(View view) {
        finish();
    }

    public void Goback(View view) {
//        viewFlipper.setInAnimation(Login_Phone_A.this, R.anim.in_from_left);
//        viewFlipper.setOutAnimation(Login_Phone_A.this, R.anim.out_to_right);
        viewFlipper.setDisplayedChild(0);
    }

}
