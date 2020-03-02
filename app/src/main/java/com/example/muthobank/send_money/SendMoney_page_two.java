package com.example.muthobank.send_money;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.muthobank.R;
import com.example.muthobank.api.ApiInterface;
import com.example.muthobank.api.ApiUtils;
import com.example.muthobank.app.SharedPreferenceManager;
import com.example.muthobank.model.SendMoneyPostModel;
import com.example.muthobank.model.SendMoneyResponse;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendMoney_page_two extends AppCompatActivity {

    private static final String TAG = "SEND_MONEY";
    private EditText  _senderHolderName, _senderBankAccount;

    private Spinner _senderCurrencyType;
    private SharedPreferenceManager preferenceManager;
    private DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_money_page_two);

        preferenceManager = new SharedPreferenceManager(getApplicationContext());
        myRef = FirebaseDatabase.getInstance().getReference("MuthoBank");
        
        initViews();

        final HashMap<String, String> user = preferenceManager.getUserDetails();
        final String bankNumCheck  = user.get(SharedPreferenceManager.KEY_BANK_ACCOUNT);


        findViewById(R.id.back_to_page_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pageOne = new Intent(getApplicationContext(), SendMoney_page_one.class);
                pageOne.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(pageOne);

            }
        });

        findViewById(R.id.new_recipient_continue_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String mCurreency = _senderCurrency.getText().toString();
                String mCurrencyType = _senderCurrencyType.getSelectedItem().toString();
                String mBankNumber = _senderBankAccount.getText().toString();
                String mHolderName = _senderHolderName.getText().toString();

                if ( TextUtils.isEmpty(mBankNumber) || TextUtils.isEmpty(mHolderName)
                    || _senderCurrencyType == null && _senderCurrencyType.getSelectedItem() ==null) {

                    Toast.makeText(SendMoney_page_two.this, "All Field Required!", Toast.LENGTH_SHORT).show();
                    return;
                }else {

                    if (bankNumCheck.equals(mBankNumber)){
                        Toast.makeText(SendMoney_page_two.this, "You can not send money own Bank Account!", Toast.LENGTH_SHORT).show();
                        return;
                    }else {

                        Intent next = new Intent(getApplicationContext(), SendMoney_page_three.class);

                        next.putExtra("PAGE_THREE_CURRENCY_TYPE",mCurrencyType);
                        next.putExtra("PAGE_THREE_HOLDER_NAME",mHolderName);
                        next.putExtra("PAGE_THREE_BANK_ACCOUNT",mBankNumber);
                        startActivity(next);

                    }


                    //TODO have to do this part
//                    bankAccountExist(ed_amount,mBankNumber,mHolderName);
                }

                //TODO have to do this part
//                preferenceManager.createSendMoneySession(ed_amount, mBankNumber, mHolderName);

//

            }
        });
    }

    private void initViews() {

//        _senderCurrency = findViewById(R.id.sender_currency);
        _senderCurrencyType = findViewById(R.id.sender_currency_type);
        _senderHolderName = findViewById(R.id.sender_holder_name);
        _senderBankAccount = findViewById(R.id.sender_bank_number);

    }


    private void bankAccountExist(final int ed_amount, final String bank_number, final String mHolderName) {

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot val : dataSnapshot.getChildren()) {
                    if (!val.getKey().contains(bank_number)) {
                        Toast.makeText(SendMoney_page_two.this, "Bank Number Does not Exists!", Toast.LENGTH_SHORT).show();
                    }else {
                        if (val.getKey().contains(bank_number)){
                            preferenceManager.createSendMoneySession(ed_amount, bank_number, mHolderName);
                            startActivity(new Intent(getApplicationContext(), SendMoney_page_three.class));
                            Log.d(TAG, "Value is: " + val.getValue());
                        }

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }


}
