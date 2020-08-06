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
        LocaleManager.loadLocal(this);
        setContentView(R.layout.activity_test);
        setTitle(getResources().getString(R.string.title_test));
    }
}