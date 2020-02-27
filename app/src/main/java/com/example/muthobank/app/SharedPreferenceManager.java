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
    public static final String KEY_ID = "id";
    public static final String KEY_PASS = "pass";
    public static final String KEY_AMOUNT = "amount";
    public static final String KEY_CARD_NUMBER = "card";
    public static final String KEY_BANK_ACCOUNT = "banak_account";
    public static final String KEY_NAME = "name";
    public static final String KEY_VALID_DATE = "valid_date";

    //send money key
    public static final String KEY_SEND_CURRENCY = "currency";
    public static final String KEY_SEND_HOLDER_NAME = "holder_name";
    public static final String KEY_SEND_BANK_NUMBER = "holder_bank";
    public static final String KEY_SEND_GREETINGS = "greetings";


    // Constructor
    public SharedPreferenceManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    /**
     * Create login session
     */
    public void createLoginSession(String phone, int pass, String card, String bank, String name, String valid_date) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing phone in pref
        editor.putString(KEY_PHONE, phone);
        // Storing pass in pref
        editor.putInt(KEY_PASS, pass);
//        editor.putInt(KEY_AMOUNT, amount);
//        editor.putInt(KEY_ID, id);
        editor.putString(KEY_CARD_NUMBER, card);
        editor.putString(KEY_BANK_ACCOUNT, bank);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_VALID_DATE, valid_date);

        // commit changes
        editor.commit();
    }


    //-------------------------------------send money ------------------------------
    public void createSendMoneySession(int currency, String bankNumber, String holderName) {

        editor.putString(KEY_SEND_CURRENCY, String.valueOf(currency));
        editor.putString(KEY_SEND_BANK_NUMBER, bankNumber);
        editor.putString(KEY_SEND_HOLDER_NAME, holderName);
        editor.commit();

    }

    public HashMap<String, String> getSendMoneyDetails(){
        HashMap<String, String> sendDetails = new HashMap<String, String>();

//        sendDetails.put(KEY_SEND_CURRENCY, String.valueOf(pref.getInt(KEY_CARD_NUMBER, 0)));
        sendDetails.put(KEY_SEND_CURRENCY, pref.getString(KEY_SEND_CURRENCY, ""));
        sendDetails.put(KEY_SEND_BANK_NUMBER, pref.getString(KEY_SEND_BANK_NUMBER, ""));
        sendDetails.put(KEY_SEND_HOLDER_NAME, pref.getString(KEY_SEND_HOLDER_NAME, ""));

        // return data
        return sendDetails;
    }


    public void putGreetings(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    public String getGreetings(String key, String defValue) {
        return pref.getString(key, defValue);
    }



    public void putPin(String key, int value) {
        editor.putInt(key, value);
        editor.apply();
    }

    public int getPin(String key, int defValue) {
        return pref.getInt(key, defValue);
    }


    //login-----------------------------------------------
    public void checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
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
     */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_CARD_NUMBER, pref.getString(KEY_CARD_NUMBER, ""));
        user.put(KEY_NAME, pref.getString(KEY_NAME, ""));
        user.put(KEY_VALID_DATE, pref.getString(KEY_VALID_DATE, ""));
        user.put(KEY_BANK_ACCOUNT, pref.getString(KEY_BANK_ACCOUNT, ""));


        // return user
        return user;
    }


    public void putCardNumber(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    public String getCardNumber(String key, String defValue) {
        return pref.getString(key, defValue);
    }


    public void putID(String key, int value) {
        editor.putInt(key, value);
        editor.apply();
    }

    public int getID(String key, int defValue) {
        return pref.getInt(key, defValue);
    }


    public void putAmount(String key, int value) {
        editor.putInt(key, value);
        editor.apply();
    }

    public int getAmount(String key, int defValue) {
        return pref.getInt(key, defValue);
    }


    public void logoutUser() {
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
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }
}
