package com.example.innerfiletest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnMemoryWrite, btnMemoryRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMemoryRead = findViewById(R.id.btnMemoryRead);
        btnMemoryWrite = findViewById(R.id.btnMemoryWrite);

        btnMemoryRead.setOnClickListener(this);
        btnMemoryWrite.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnMemoryRead:
                fileInnerReader();
            break;
            case R.id.btnMemoryWrite:
                fileInnerWriter();
                break;
        }
    }

    // 내부 메모리에 파일을 쓰기
    private void fileInnerReader() {
        try {
            FileInputStream fis = openFileInput("file.txt");
            byte[] data = new byte[fis.available()];
            fis.read(data);
            toastDisplay(new String(data));//byte배열을 문자열로 바꾸기
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 내부 메모리의 파일을 읽기
    private void fileInnerWriter() {

        try {
            FileOutputStream fos = openFileOutput("file.txt", Context.MODE_PRIVATE);
            String string = "안녕!!!!!!";
            fos.write(string.getBytes());
            fos.close();
            toastDisplay("file.txt 생성 완료");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void toastDisplay(String str) {
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }
}
