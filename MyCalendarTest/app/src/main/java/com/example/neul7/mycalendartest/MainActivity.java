package com.example.neul7.mycalendartest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnPrevious, btnNext;
    private TextView tvYearMonth;
    private GridView gvCalendar;
    private MonthAdapter monthAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
        tvYearMonth = findViewById(R.id.tvYearMonth);
        gvCalendar = findViewById(R.id.gvCalendar);

        // 1. 어댑터 생성
        monthAdapter = new MonthAdapter(this);
        gvCalendar.setAdapter(monthAdapter);

        btnPrevious.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        setYearMonth();

        gvCalendar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MonthItem currentItem = monthAdapter.items[position];
                int day = currentItem.getDayValue();
                String message =  monthAdapter.currentYear+"년"
                        +(monthAdapter.currentMonth+1)+"월" +day+"일"+" 고생하셨습니다ㅜ.ㅜ";

                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPrevious:
                monthAdapter.setPreviousMonth();
                monthAdapter.notifyDataSetChanged();
                setYearMonth();
                break;
            case R.id.btnNext:
                monthAdapter.setNextMonth();;
                monthAdapter.notifyDataSetChanged();
                setYearMonth();
                break;
        }
    }

    private void setYearMonth() {
        String yearMonth = monthAdapter.currentYear+"년"+(monthAdapter.currentMonth+1)+"월";
        tvYearMonth.setText(yearMonth);
    }

}
