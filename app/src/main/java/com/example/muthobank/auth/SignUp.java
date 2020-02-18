package com.example.muthobank.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.muthobank.R;

public class SignUp extends AppCompatActivity {

    private ImageView backToLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        backToLogin = findViewById(R.id.back_to_login);

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToLogin = new Intent(getApplicationContext(),Login.class);
                backToLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(backToLogin);
            }
        });
    }
}
