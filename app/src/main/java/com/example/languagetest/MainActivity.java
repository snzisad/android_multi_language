package com.example.languagetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocal();

        setContentView(R.layout.activity_main);
        setTitle(getResources().getString(R.string.title_home));

        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new BlankFragment1());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new BlankFragment2());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        btn1.callOnClick();
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