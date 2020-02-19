package com.example.muthobank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.muthobank.send_money.SendMoney_page_one;

public class HomePage extends AppCompatActivity {

    private LinearLayout sendMoneyBtn,cardDetailsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        sendMoneyBtn=findViewById(R.id.send_money_btn);
        cardDetailsBtn=findViewById(R.id.card_details_btn);


        sendMoneyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), SendMoney_page_one.class));
            }
        });



        cardDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),CardInfo.class));

            }
        });


    }
}
