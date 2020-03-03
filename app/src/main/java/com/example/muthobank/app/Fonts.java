package com.example.muthobank.app;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;


public class Fonts {
    public static void customFont(TextView tv, Context context){
        Typeface custom_font = Typeface.createFromAsset(context.getAssets(),  "fonts/lao_sangam_mn.ttf");
        tv.setTypeface(custom_font);
    }
}
