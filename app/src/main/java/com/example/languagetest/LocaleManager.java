package com.example.languagetest;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;

import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class LocaleManager {

    public static void setLocal(Activity activity, String code){
        Locale locale = new Locale(code.toLowerCase());
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        }
        else{
            configuration.locale = locale;
        }
        activity.getBaseContext().getResources().updateConfiguration(configuration, activity.getBaseContext().getResources().getDisplayMetrics());
    }

    public static void loadLocal(Activity activity){
        SharedPreferences sharedPreferences = activity.getSharedPreferences("LANG_SETTINGS", MODE_PRIVATE);
        String lang_code = sharedPreferences.getString("LANG", "en");
        setLocal(activity, lang_code);
    }

}
