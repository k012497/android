package com.example.sharedpreferencetest;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    final static String SHARED_NAME = "SSO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_NAME,0);
        String editValue = sharedPreferences.getString("editText", "");
        editText.setText(editValue);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_NAME,0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("editText", editText.getText().toString().trim());
        editor.commit();
    }
}
