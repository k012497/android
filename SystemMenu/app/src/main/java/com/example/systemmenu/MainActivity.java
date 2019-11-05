package com.example.systemmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button btnMenu;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMenu = findViewById(R.id.btnMenu);
        linearLayout = findViewById(R.id.linearLayout);

    }

    // 1. 시스템 메뉴 선택
    //메뉴를 인플레이트하고 옵션 메뉴를 등록한다.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater(); // 메뉴 객체를 만듦
        menuInflater.inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // 2. 옵션 메뉴를 클릭하면 인식하는 함수
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.itemRed :
                linearLayout.setBackgroundColor(Color.RED);
                break;
            case R.id.itemGreen :
                linearLayout.setBackgroundColor(Color.GREEN);
                break;
            case R.id.itemBlue :
                linearLayout.setBackgroundColor(Color.BLUE);
                break;
            case R.id.subRotate :
                btnMenu.setRotation(btnMenu.getRotation() + 45);
                break;
            case R.id.subSize:
                btnMenu.setScaleX(2);
                btnMenu.setScaleY(2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
