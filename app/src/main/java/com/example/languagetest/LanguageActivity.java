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

        Typeface english_typeface = ResourcesCompat.getFont(this, R.font.times_new_roman);
        Typeface bangla_typeface = ResourcesCompat.getFont(this, R.font.kalpurush_ansi);

        rb_en.setTypeface(english_typeface);
        rb_bn.setTypeface(bangla_typeface);


        if(lang_code.equals("bn")){
            rb_bn.setChecked(true);
        }
        else{
            rb_en.setChecked(true);
        }

        btn_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String local_code = "";
                if(rb_en.isChecked()){
                    local_code = "en";
                }
                else{
                    local_code = "bn";
                }

                LocaleManager.setLocal(LanguageActivity.this, local_code);
                SharedPreferences.Editor editor =  getSharedPreferences("LANG_SETTINGS", MODE_PRIVATE).edit();
                editor.putString("LANG", local_code);
                editor.apply();

                startActivity(new Intent(LanguageActivity.this, MainActivity.class));
                finish();
            }
        });
    }

}