package com.example.fourbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    // 변수 선언 - xml 화면 위젯의 아이디와 일치 시키기
    Button btnNate, btnPhone, btnGallery, btnExit;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 화면 객체를 찾아서 가져온다. - 형변환한다
        btnNate = (Button)findViewById(R.id.btnNate);
        btnPhone = findViewById(R.id.btnPhone);
        btnGallery = findViewById(R.id.btnGallery);
        btnExit = findViewById(R.id.btnExit);

        btnNate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //인텐트 기술 - 내부적으로 화면에 보일 내용을 미리 정의된 것(전화거는 화면, 갤러리 화면 등) 필요하면 불러다 쓰는 것
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.nate.com"));
                startActivity(intent); //새로운 화면을 보여주는 것
            }
        });

        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:/010-9678-2724"));
                startActivity(intent);
            }
        });

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://media/internal/images/media"));
                startActivity(intent);
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
