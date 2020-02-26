package com.example.muthobank.send_money;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.muthobank.HomePage;
import com.example.muthobank.R;

public class SendMoney_page_four extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_money_page_four);


        findViewById(R.id.back_to_page_three).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pageThree = new Intent(getApplicationContext(), SendMoney_page_three.class);
                pageThree.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(pageThree);

            }
        });

        findViewById(R.id.page_four_nextBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SendMoney_page_five.class));
            }
        });
    }
}
