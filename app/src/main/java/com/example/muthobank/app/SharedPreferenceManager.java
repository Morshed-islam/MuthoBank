package com.example.muthobank.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.muthobank.auth.Login;

import java.util.HashMap;

public class SharedPreferenceManager {

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "MUTHOBANK";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_PHONE = "phone";

    // Email address (make variable public to access from outside)
    public static final String KEY_PASS = "pass";
    public static final String KEY_AMOUNT  = "amount";
    public static final String KEY_CARD_NUMBER  = "card";
    public static final String KEY_BANK_ACCOUNT  = "banak_account";

    // Constructor
    public SharedPreferenceManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    /**
     * Create login session
     * */
    public void createLoginSession(String phone, int pass,int amount,String card,String bank){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing phone in pref
        editor.putString(KEY_PHONE, phone);
        // Storing pass in pref
        editor.putInt(KEY_PASS, pass);
        editor.putInt(KEY_AMOUNT, amount);
        editor.putString(KEY_CARD_NUMBER,card);
        editor.putString(KEY_BANK_ACCOUNT,bank);

        // commit changes
        editor.commit();
    }


    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, Login.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }

    /**
     * Get stored session data
     * */
//    public HashMap<String, String> getUserDetails(){
//        HashMap<String, String> user = new HashMap<String, String>();
//        // user name
//        user.put(KEY_PHONE, pref.getString(KEY_PHONE, null));
//        // user email id
//        user.put(KEY_PASS, pref.getString(KEY_PASS, null));
//        user.put(KEY_COMPANY_ID, pref.getString(KEY_COMPANY_ID, null));
//        user.put(KEY_EMPLOYEE_ID, pref.getString(KEY_EMPLOYEE_ID, null));
//
//        // return user
//        return user;
//    }

    public void  putInt(String key, int value){
        editor.putInt(key,value);
        editor.apply();
    }

    public int getInt(String key, int defValue){
        return pref.getInt(key,defValue);
    }


    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, Login.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);

    }



    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
