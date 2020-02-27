package com.example.muthobank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.muthobank.adapter.TransactionsListAdapter;
import com.example.muthobank.api.ApiInterface;
import com.example.muthobank.api.ApiUtils;
import com.example.muthobank.app.Constants;
import com.example.muthobank.app.SharedPreferenceManager;
import com.example.muthobank.model.TransactionsPostModel;
import com.example.muthobank.send_money.SendMoney_page_one;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePage extends AppCompatActivity {

    private LinearLayout sendMoneyBtn, cardDetailsBtn;
    private String card_number, bank_number;
    private String amount;
    private TextView _gAmount;
    private SharedPreferenceManager preferenceManager;
    private List<TransactionsPostModel> transactionsPostModels;
    private RecyclerView recyclerView;
    private TransactionsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        preferenceManager = new SharedPreferenceManager(getApplicationContext());
        preferenceManager.checkLogin();


//        Bundle b = getIntent().getExtras();
//        _gAmount = findViewById(R.id.tv_amount);
//
//        if (b != null) {
//            amount = getIntent().getStringExtra("AMOUNT");
//            card_number = getIntent().getStringExtra("CARD_NUMBER");
//            bank_number = getIntent().getStringExtra("BANK_ACCOUNT");
//            _gAmount.setText("$"+amount);
//        }


        transactionsPostModels = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new TransactionsListAdapter(getApplicationContext(),transactionsPostModels);
        recyclerView.setAdapter(adapter);


        sendMoneyBtn = findViewById(R.id.send_money_btn);
        cardDetailsBtn = findViewById(R.id.card_details_btn);
        _gAmount = findViewById(R.id.tv_amount);

        _gAmount.setText("$"+preferenceManager.getAmount(SharedPreferenceManager.KEY_AMOUNT,0));


        sendMoneyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), SendMoney_page_one.class));
            }
        });

        cardDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), CardInfo.class));
            }
        });

        getTransactions();

    }



    private void getTransactions(){

        ApiInterface apiInterface = ApiUtils.getApiInterface();
        Call<List<TransactionsPostModel>> call = apiInterface.getTransactions(4);
        call.enqueue(new Callback<List<TransactionsPostModel>>() {
            @Override
            public void onResponse(Call<List<TransactionsPostModel>> call, Response<List<TransactionsPostModel>> response) {

                if (response.isSuccessful()) {

                    Toast.makeText(HomePage.this, "Successful!", Toast.LENGTH_SHORT).show();

                    for(TransactionsPostModel model: response.body()) {
//                        System.out.println(model.toString());
                        Log.i("values", "onResponse: "+model.getAccountHolderName());
                        transactionsPostModels.add(model);
                        adapter.notifyDataSetChanged();
                        Log.i("values", "below onResponse: "+response.body().toString());
                    }


//                    String val = response.body().toString();
//                    Type type = new TypeToken<List<TransactionsPostModel>>(){}.getType();
//                    transactionsPostModels = getTransactionListFromJson(val,type);
//                    Log.i("values", "onResponse: "+transactionsPostModels.toString());


                }
            }

            @Override
            public void onFailure(Call<List<TransactionsPostModel>> call, Throwable t) {

                Toast.makeText(HomePage.this, "Error!", Toast.LENGTH_SHORT).show();
                Log.i("data", "onResponse: "+t.getMessage());

            }
        });

    }


//    public static <T> List<T> getTransactionListFromJson(String jsonString, Type type) {
//        if (!isValid(jsonString)) {
//            return null;
//        }
//        return new Gson().fromJson(jsonString, type);
//    }
//
//    public static boolean isValid(String json) {
//        try {
//            new JsonParser().parse(json);
//            return true;
//        } catch (JsonSyntaxException jse) {
//            return false;
//        }
//    }


}
