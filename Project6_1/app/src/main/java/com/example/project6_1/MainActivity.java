package com.example.project6_1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    Chronometer chronometer;
    RadioButton rdoCalendar, rdoTime;
    DatePicker datePicker; TimePicker timePicker;
    TextView tvYear, tvMonth, tvDay, tvHour, tvMinute, tvMessage;
    int year, month, dayOfMonth, hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("시간예약 APP");

        chronometer = findViewById(R.id.chronometer);
        rdoCalendar = findViewById(R.id.rdoCalendar);
        rdoTime = findViewById(R.id.rdoTime);
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);
        tvYear = findViewById(R.id.tvYear);
        tvMonth = findViewById(R.id.tvMonth);
        tvDay = findViewById(R.id.tvDay);
        tvHour = findViewById(R.id.tvHour);
        tvMinute = findViewById(R.id.tvMinute);
        tvMessage = findViewById(R.id.tvMessage);

        timePicker.setVisibility(View.INVISIBLE);
        datePicker.setVisibility(View.INVISIBLE);
        rdoCalendar.setVisibility(View.INVISIBLE);
        rdoTime.setVisibility(View.INVISIBLE);

        // 0. 크로노미터를 클릭하면 시작
        chronometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rdoCalendar.setVisibility(View.VISIBLE);
                rdoTime.setVisibility(View.VISIBLE);

                // 시간이 남아있을 때 초기화
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                chronometer.setTextColor(Color.RED);
            }
        });

        // 1. 라디오 버튼 이벤트 처리
        rdoCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.setVisibility(View.VISIBLE);
                timePicker.setVisibility(View.INVISIBLE);
            }
        });

        rdoTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker.setVisibility(View.VISIBLE);
                datePicker.setVisibility(View.INVISIBLE);
            }
        });

        // 3. 데이트 피커를 선택했을 때
        // c.f. 캘린더 뷰 ) 현재 년도, 월, 일 값을 각각 받아줌 -getDate()로 합쳐져서 받음
        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int y, int m, int d) {
                year = y;
                month = m;
                dayOfMonth = d;
            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int h, int m) {
                hour = h;
                minute = m;
            }
        }); //getHour(), getMinute()있음. 근데 일단 seton~

        // 4. 예약 완료 이벤트 처리
        tvYear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                chronometer.stop();
                tvYear.setText(String.valueOf(year) + "년 ");
                tvMonth.setText(String.valueOf(month + 1) + "월 ");
                tvDay.setText(String.valueOf(dayOfMonth) + "일 ");
                tvHour.setText(String.valueOf(hour) + "시 ");
                tvMinute.setText(String.valueOf(minute) + "분 ");
                tvMessage.setText("예약 완료 !");

                timePicker.setVisibility(View.INVISIBLE);
                datePicker.setVisibility(View.INVISIBLE);
                rdoCalendar.setVisibility(View.INVISIBLE);
                rdoTime.setVisibility(View.INVISIBLE);
                return false;
            }
        });

    }
}
