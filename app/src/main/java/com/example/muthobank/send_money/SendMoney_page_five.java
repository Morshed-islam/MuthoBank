package com.example.muthobank.send_money;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.muthobank.HomePage;
import com.example.muthobank.R;

public class SendMoney_page_five extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_money_page_five);

        findViewById(R.id.back_to_page_four).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pageFour = new Intent(getApplicationContext(), SendMoney_page_four.class);
                pageFour.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(pageFour);

            }
        });

        findViewById(R.id.page_five_sendBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), HomePage.class));

                ViewDialog viewDialog = new ViewDialog();
                viewDialog.showDialog(SendMoney_page_five.this);
            }
        });
    }



    public class ViewDialog {

        public void showDialog(Activity activity){
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.payment_succes_dialog);

            Button dialogButton = dialog.findViewById(R.id.okay_dialog_btn);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();

                    Intent goToHome = new Intent(getApplicationContext(),HomePage.class);
                    goToHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(goToHome);
                    finish();
                }
            });

            dialog.show();

        }
    }
}
