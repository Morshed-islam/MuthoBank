package com.example.muthobank.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.muthobank.R;
import com.example.muthobank.api.ApiInterface;
import com.example.muthobank.api.ApiUtils;
import com.example.muthobank.model.LoginPostModel;
import com.example.muthobank.model.LoginResponse;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestingClass extends AppCompatActivity {

    private static final String TAG = "TEST";


    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_class);

        myRef = FirebaseDatabase.getInstance().getReference("MuthoBank");

        findViewById(R.id.send_test_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                myRef.child("1723505040").setValue("1723505040");


                matchValueInDatabase();
//                login();
            }
        });
    }



//    private boolean bankExits;

    private void matchValueInDatabase(){

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for (DataSnapshot val : dataSnapshot.getChildren()){

                    if (val.getKey().contains("1723505080")){
                        Log.d(TAG, "Value is: " + val.getValue());
                    }

//                    Log.d(TAG, "Value is: " + val.toString());
//
                }

//                Object value = dataSnapshot.getValue(Object.class);
//                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }


    private void login(){

        LoginPostModel model = new LoginPostModel("01832817105",1243);

        ApiInterface apiInterface = ApiUtils.getApiInterface();
        Call<LoginResponse> call = apiInterface.postUserToLog(model);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {

                    Toast.makeText(TestingClass.this, "Success!", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(TestingClass.this, "below Success!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t.getMessage());
                Toast.makeText(TestingClass.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
