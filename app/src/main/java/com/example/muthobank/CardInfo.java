package com.example.muthobank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.muthobank.app.SharedPreferenceManager;

import java.util.HashMap;

public class CardInfo extends AppCompatActivity {
    private TextView _gCardName, _gCardNumber, _gCardValid;
    private Button _logOut;
    private SharedPreferenceManager preferenceManager;
    private ImageView backToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cards);


        preferenceManager = new SharedPreferenceManager(getApplicationContext());
        initViews();

        HashMap<String, String> user = preferenceManager.getUserDetails();

        String card_number = user.get(SharedPreferenceManager.KEY_CARD_NUMBER);
        String name = user.get(SharedPreferenceManager.KEY_NAME);
        String valid_date = user.get(SharedPreferenceManager.KEY_VALID_DATE);
        String val = card_number.substring(11, 15);
        _gCardNumber.setText("**** **** **** " + val);
        _gCardName.setText("" + name);
        _gCardValid.setText("" + valid_date);
//        Toast.makeText(this, ""+valid_date, Toast.LENGTH_SHORT).show();

        findViewById(R.id.card_back_to_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(getApplicationContext(), HomePage.class);
                home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(home);
            }
        });


        _logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferenceManager.logoutUser();
            }
        });

    }

    private void initViews() {

        _gCardName = findViewById(R.id.tv_member_name);
        _gCardNumber = findViewById(R.id.tv_card_number);
        _gCardValid = findViewById(R.id.tv_validity);
        _logOut = findViewById(R.id.btn_log_out);
        backToHome = findViewById(R.id.card_back_to_home);

    }
}
