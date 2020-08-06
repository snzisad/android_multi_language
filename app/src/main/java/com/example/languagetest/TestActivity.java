package com.example.languagetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;

import java.util.Locale;

public class TestActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocal();
        setContentView(R.layout.activity_test);
        setTitle(getResources().getString(R.string.title_test));
    }


    private void setLocal(String code){
        Locale locale = new Locale(code.toLowerCase());
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        }
        else{
            configuration.locale = locale;
        }
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());

        // save in shared preferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("LANG", code);
        editor.apply();
    }

    private void loadLocal(){
        sharedPreferences = getSharedPreferences("LANG_SETTINGS", MODE_PRIVATE);
        String lang_code = sharedPreferences.getString("LANG", "en");
        setLocal(lang_code);
    }
}