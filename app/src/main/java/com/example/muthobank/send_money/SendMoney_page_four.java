package com.example.muthobank.send_money;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.muthobank.HomePage;
import com.example.muthobank.R;
import com.example.muthobank.app.SharedPreferenceManager;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.HashMap;

public class SendMoney_page_four extends AppCompatActivity {

    private SharedPreferenceManager preferenceManager;
    private TextView _toolbarAmount;
    private EditText _greetings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_money_page_four);

        _toolbarAmount = findViewById(R.id.tv_page_four_toolbar);
        _greetings = findViewById(R.id.tv_page_four_greetings);
        preferenceManager = new SharedPreferenceManager(getApplicationContext());


        HashMap<String, String> getData = preferenceManager.getSendMoneyDetails();
        String getting_amount = "" + getData.get(SharedPreferenceManager.KEY_SEND_CURRENCY);
        String getting_holder_name = getData.get(SharedPreferenceManager.KEY_SEND_HOLDER_NAME);

//
//        DecimalFormat formatter = new DecimalFormat("##,##,###");
//        try {
////            String getting_amount = ""+getData.get(SharedPreferenceManager.KEY_SEND_CURRENCY);
////            String getting_holder_name = getData.get(SharedPreferenceManager.KEY_SEND_HOLDER_NAME);
//            String amt = "" + getData.get(SharedPreferenceManager.KEY_SEND_CURRENCY);
//            Object obj = formatter.parse(amt);
//            String _mAmount = formatter.format(obj);
//            _toolbarAmount.setText("$" + _mAmount);
//
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


//        _toolbarAmount.setText("$"+toolbar_amount+" For "+toolbar_name);
        _toolbarAmount.setText("$"+getting_amount+" For "+getting_holder_name);


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

                String mGretings = _greetings.getText().toString();

                if (TextUtils.isEmpty(mGretings)) {
                    Toast.makeText(SendMoney_page_four.this, "Message Field Required", Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    preferenceManager.putGreetings(SharedPreferenceManager.KEY_SEND_GREETINGS, mGretings);
                    startActivity(new Intent(getApplicationContext(), SendMoney_page_five.class));
                }


            }
        });
    }
}
