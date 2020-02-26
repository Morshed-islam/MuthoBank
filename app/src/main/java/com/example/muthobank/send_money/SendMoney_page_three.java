package com.example.muthobank.send_money;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.muthobank.R;

public class SendMoney_page_three extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_money_page_three);

        findViewById(R.id.back_to_page_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pageTwo = new Intent(getApplicationContext(), SendMoney_page_two.class);
                pageTwo.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(pageTwo);

            }
        });

        findViewById(R.id.page_three_continue_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SendMoney_page_four.class));
            }
        });
    }
}
