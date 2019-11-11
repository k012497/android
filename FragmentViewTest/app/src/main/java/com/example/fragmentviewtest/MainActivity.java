package com.example.fragmentviewtest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnMenu1, btnMenu2, btnMenu3, btnMenu4;

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMenu1 = findViewById(R.id.btnMenu1);
        btnMenu2 = findViewById(R.id.btnMenu2);
        btnMenu3 = findViewById(R.id.btnMenu3);
        btnMenu4 = findViewById(R.id.btnMenu4);

        btnMenu1.setOnClickListener(this);
        btnMenu2.setOnClickListener(this);
        btnMenu3.setOnClickListener(this);
        btnMenu4.setOnClickListener(this);

        btnMenu1.callOnClick(); // 맨 처음으로 1번 메뉴를 띄움
    }

    @Override
    public void onClick(View v) {
        //Fragment 가져오는 객체
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fgActivity = null;

        switch (v.getId()){
            case R.id.btnMenu1:
                fgActivity = new FragmentActivity1(); //얘를 객체화 시키면 생명주기 스타트! 그 속에서 inflate xml
                break;

            case R.id.btnMenu2:
                fgActivity= new FragmentActivity2();
                break;

            case R.id.btnMenu3:
                fgActivity= new FragmentActivity3();
                break;

            case R.id.btnMenu4:
                fgActivity= new FragmentActivity4();
                break;
        }

        ft.replace(R.id.frameLayout, fgActivity); //replace fgActivity1 with frameLayout
        ft.commit();

    }
}
