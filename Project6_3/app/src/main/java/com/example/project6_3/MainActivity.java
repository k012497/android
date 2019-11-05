package com.example.project6_3;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

    TabHost tabHost;
    TabHost.TabSpec tabScreen1, tabScreen2, tabScreen3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. tabHost를 가져옴
        tabHost = getTabHost();

        // 2. tabWidget에 제목을 표시한다.
        tabScreen1 = tabHost.newTabSpec("1ST").setIndicator("SPAIN");
        tabScreen1.setContent(R.id.spain); //content
        tabHost.addTab(tabScreen1);

        tabScreen2 = tabHost.newTabSpec("2ND").setIndicator("SLOVENIA");
        tabScreen2.setContent(R.id.slovenia); //content
        tabHost.addTab(tabScreen2);

        tabScreen3 = tabHost.newTabSpec("3RD").setIndicator("POLAND");
        tabScreen3.setContent(R.id.poland); //content
        tabHost.addTab(tabScreen3);

        // 3. 기본 탭 설정
        tabHost.setCurrentTab(0);
    }
}
