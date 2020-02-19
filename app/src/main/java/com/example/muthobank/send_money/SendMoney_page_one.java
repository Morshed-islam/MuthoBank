package com.example.muthobank.send_money;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.muthobank.R;

public class SendMoney_page_one extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_new_contact);

        findViewById(R.id.add_new_recipient_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),SendMoney_page_two.class));
            }
        });
    }
}
