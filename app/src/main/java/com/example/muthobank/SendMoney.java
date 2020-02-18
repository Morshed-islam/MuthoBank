package com.example.muthobank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SendMoney extends AppCompatActivity {

    private ImageView backToHome;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);

        backToHome= findViewById(R.id.back_to_home);
        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent backHome = new Intent(getApplicationContext(),HomePage.class);
                backHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(backHome);

            }
        });
    }
}
