package com.example.bundletest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("myBundle");
        Toast.makeText(getApplicationContext(), bundle.getIntegerArrayList("array").size() +"", Toast.LENGTH_SHORT).show();

    }
}
