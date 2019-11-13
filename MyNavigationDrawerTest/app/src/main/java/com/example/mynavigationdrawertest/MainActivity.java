package com.example.mynavigationdrawertest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    DrawerLayout mainDrawerLayout;
    Button btnMenu1, btnMenu2, btnMenu3;
    ImageButton ibClose;
    LinearLayout drawerMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainDrawerLayout = findViewById(R.id.mainDrawerLayout);
        ibClose = findViewById(R.id.ibClose);
        btnMenu1 = findViewById(R.id.btnMenu1);
        btnMenu2 = findViewById(R.id.btnMenu2);
        btnMenu3 = findViewById(R.id.btnMenu3);
        drawerMenu = findViewById(R.id.drawerMenu);

        ibClose.setOnClickListener(this);
        btnMenu1.setOnClickListener(this);
        btnMenu2.setOnClickListener(this);
        btnMenu3.setOnClickListener(this);
        drawerMenu.setOnTouchListener(this);

        mainDrawerLayout.setDrawerListener(listner);

    }

    DrawerLayout.DrawerListener listner = new DrawerLayout.DrawerListener(){

        // 슬라이딩 시작할 때 감지
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
        }

        // 메뉴를 열었을 때 감지
        @Override
        public void onDrawerOpened(@NonNull View drawerView) {
            Toast.makeText(MainActivity.this, "open menu", Toast.LENGTH_SHORT).show();
        }

        // 메뉴를 닫았을 때 감지
        @Override
        public void onDrawerClosed(@NonNull View drawerView) {
            Toast.makeText(MainActivity.this, "close menu", Toast.LENGTH_SHORT).show();
        }

        // 메뉴바 상태가 바뀌었을 때
        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };

    @Override
    public void onClick(View v) {
        //Fragment 가져오는 객체
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fgActivity = null;

        switch (v.getId()){
            case R.id.ibClose:
                mainDrawerLayout.closeDrawer(drawerMenu);
                return;
            case R.id.btnMenu1:
                fgActivity = new Fragment1Activity();
                mainDrawerLayout.closeDrawer(drawerMenu);
                break;
            case R.id.btnMenu2:
                fgActivity = new Fragment2Activity();
                mainDrawerLayout.closeDrawer(drawerMenu);
                break;
            case R.id.btnMenu3:
                fgActivity = new Fragment3Activity();
                mainDrawerLayout.closeDrawer(drawerMenu);
                break;
        }
        ft.replace(R.id.frameLayout, fgActivity);
        ft.commit();
    }

    // drawMenu를 터치했을 때 발생하고자하는 이벤트를 정의하면 된다.
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return true;
    }
}
