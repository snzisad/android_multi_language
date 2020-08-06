package com.example.languagetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
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
        loadLocal();
        setContentView(R.layout.activity_language);
        setTitle(getResources().getString(R.string.title_change_lang));

        rb_en = findViewById(R.id.rb_en);
        rb_bn = findViewById(R.id.rb_bn);
        btn_lang = findViewById(R.id.btn_lang);


        if(lang_code.equals("bn")){
            rb_bn.setChecked(true);
        }
        else{
            rb_en.setChecked(true);
        }

        btn_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rb_en.isChecked()){
                    setLocal("en");
                }
                else{
                    setLocal("bn");
                }
                Toast.makeText(LanguageActivity.this, R.string.language_changed_success, Toast.LENGTH_SHORT).show();

                LanguageActivity.this.recreate();
//                startActivity(new Intent(LanguageActivity.this, MainActivity.class));
//                finish();
            }
        });
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
        lang_code = sharedPreferences.getString("LANG", "en");
        setLocal(lang_code);
    }


}