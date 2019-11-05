package com.example.tabhosttest;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;

@SuppressWarnings("deprecation") //나도 알고 있으니까 경고 주지마!
public class MainActivity extends TabActivity {

    TabHost tabHost;
    TabHost.TabSpec tabScreen1, tabScreen2, tabScreen3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. tabHost를 가져옴
        tabHost = getTabHost();

        // 2. tabWidget에 제목을 표시한다.s
        tabScreen1 = tabHost.newTabSpec("1ST").setIndicator("화면1"); //widget
        tabScreen1.setContent(R.id.screen1); //content
        tabHost.addTab(tabScreen1);

        tabScreen2 = tabHost.newTabSpec("2ND").setIndicator("화면2");
        tabScreen2.setContent(R.id.screen2); //content
        tabHost.addTab(tabScreen2);

        tabScreen3 = tabHost.newTabSpec("3RD").setIndicator("화면3");
        tabScreen3.setContent(R.id.screen3); //content
        tabHost.addTab(tabScreen3);

        // 3. 기본 탭 설정
        tabHost.setCurrentTab(0);

    }
}
