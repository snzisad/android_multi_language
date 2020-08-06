package com.example.languagetest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;

public class BlankFragment2 extends Fragment {
    TextView tv_price;
    Button btn_lang;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv_price = view.findViewById(R.id.tv_price);
        btn_lang = view.findViewById(R.id.btn_lang);


        Typeface english_typeface = ResourcesCompat.getFont(getActivity(), R.font.times_new_roman);
        Typeface bangla_typeface = ResourcesCompat.getFont(getActivity(), R.font.kalpurush_ansi);


        sharedPreferences = getActivity().getSharedPreferences("LANG_SETTINGS", MODE_PRIVATE);
        String lang_code = sharedPreferences.getString("LANG", "en");

        if(lang_code.equals("bn")){
            tv_price.setTypeface(bangla_typeface);
        }
        else{
            tv_price.setTypeface(english_typeface);
        }

        btn_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LanguageActivity.class));
            }
        });
    }
}