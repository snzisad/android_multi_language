package com.example.languagetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {
    RadioButton rb_en, rb_bn;
    Button btn_lang;
    SharedPreferences sharedPreferences;
    String lang_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocaleManager.loadLocal(this);
        setContentView(R.layout.activity_language);
        setTitle(getResources().getString(R.string.title_change_lang));

        rb_en = findViewById(R.id.rb_en);
        rb_bn = findViewById(R.id.rb_bn);
        btn_lang = findViewById(R.id.btn_lang);


        SharedPreferences sharedPreferences = getSharedPreferences("LANG_SETTINGS", MODE_PRIVATE);
        lang_code = sharedPreferences.getString("LANG", "en");

        if(lang_code.equals("bn")){
            rb_bn.setChecked(true);

            Typeface english_typeface = ResourcesCompat.getFont(this, R.font.times_new_roman);
            rb_en.setTypeface(english_typeface);
        }
        else{
            rb_en.setChecked(true);

            Typeface bangla_typeface = ResourcesCompat.getFont(this, R.font.kalpurush_ansi);
            rb_bn.setTypeface(bangla_typeface);
        }

        btn_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rb_en.isChecked()){
                    lang_code = "en";
                }
                else{
                    lang_code = "bn";
                }

                LocaleManager.setLocal(LanguageActivity.this, lang_code);
                SharedPreferences.Editor editor =  getSharedPreferences("LANG_SETTINGS", MODE_PRIVATE).edit();
                editor.putString("LANG", lang_code);
                editor.apply();

                startActivity(new Intent(LanguageActivity.this, MainActivity.class));
                finish();
            }
        });
    }

}