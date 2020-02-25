package com.example.muthobank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.muthobank.app.SharedPreferenceManager;

public class CardInfo extends AppCompatActivity {
    private TextView _gCardName,_gCardNumber,_gCardValid;
    private Button _logOut;
    private SharedPreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cards);

        preferenceManager = new SharedPreferenceManager(getApplicationContext());
        initViews();

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

    }
}
