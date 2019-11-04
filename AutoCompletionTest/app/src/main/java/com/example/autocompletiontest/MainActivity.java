package com.example.autocompletiontest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView autoCompleteTextView;
    MultiAutoCompleteTextView multiAutoCompleteTextView;

    // 문자열 배열 생성 (원래 DB에서 가져와야 함)
    String[] data = {"CSI-뉴욕", "CSI-라스베가스", "CSI-마이애미", "Friends","Fringe", "Lost"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        multiAutoCompleteTextView = findViewById(R.id.multiAutoCompleteTextView);

        // 패턴을 인식하기 !
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, data);
        autoCompleteTextView.setAdapter(adapter);

        // 동시에 여러 개를 선택할 수 있도록 구분자를 연결함 (둘 이상을 선택해야하니까 콤마를 기준으로 자르기)
        MultiAutoCompleteTextView.CommaTokenizer tokenizer = new MultiAutoCompleteTextView.CommaTokenizer();
        multiAutoCompleteTextView.setTokenizer(tokenizer);
        multiAutoCompleteTextView.setAdapter(adapter);
    }
}
