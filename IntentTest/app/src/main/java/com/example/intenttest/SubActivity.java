package com.example.intenttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnClose;
    int result = 0, num1 = 0, num2 = 0, operator = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        btnClose = findViewById(R.id.btnClose);
        btnClose.setOnClickListener(this);

        Intent intent = getIntent();
        num1 = intent.getIntExtra("number1",0);
        num2 = intent.getIntExtra("number2",0);
        operator = intent.getIntExtra("operator", 0);
        switch (operator) {
            case 1:
                result = num1 + num2;
                break;
            case 2:
                result = num1 - num2;
                break;
            case 3:
                result = num1 * num2;
                break;
            case 4:
                result = num1 / num2;
                break;
        }

    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.putExtra("result", result);
        setResult(1001, intent);
        finish();
    }
}
