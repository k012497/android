package com.example.intenttest2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third);

        btnClose = findViewById(R.id.btnClose);
        btnClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
