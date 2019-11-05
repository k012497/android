package com.example.project7_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    EditText edtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        edtText = findViewById(R.id.edtText);
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
            case R.id.itemSpain :
                imageView.setImageResource(R.drawable.home);
                break;
            case R.id.itemSlovenia :
                imageView.setImageResource(R.drawable.heart);
                break;
            case R.id.itemPoland :
                imageView.setImageResource(R.drawable.pol);
                break;
            case R.id.rotate :
                imageView.setRotation(Float.parseFloat(edtText.getText().toString()));
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
