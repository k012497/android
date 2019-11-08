package com.example.sdcardreadtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnReader;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReader = findViewById(R.id.btnReader);
        editText = findViewById(R.id.editText);

        // 사용자에게 허락 요청을 함
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                MODE_PRIVATE);

        btnReader.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        try {
            FileInputStream fis = new FileInputStream("/sdcard/raw_text.txt");
            byte[] data = new byte[fis.available()];
            fis.read(data);
            editText.setText(new String(data));
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
