package com.example.reservationtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    Chronometer chronometer;
    Button btnStart, btnEnd;
    RadioButton rdoCalendar, rdoTime;
    CalendarView calendarView; TimePicker timePicker;
    TextView tvYear, tvMonth, tvDay, tvHour, tvMinute, tvMessage;
    int year, month, dayOfMonth, hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("시간예약 APP");

        chronometer = findViewById(R.id.chronometer);
        btnStart = findViewById(R.id.btnStart);
        btnEnd = findViewById(R.id.btnEnd);
        rdoCalendar = findViewById(R.id.rdoCalendar);
        rdoTime = findViewById(R.id.rdoTime);
        calendarView = findViewById(R.id.calendarView);
        timePicker = findViewById(R.id.timePicker);
        tvYear = findViewById(R.id.tvYear);
        tvMonth = findViewById(R.id.tvMonth);
        tvDay = findViewById(R.id.tvDay);
        tvHour = findViewById(R.id.tvHour);
        tvMinute = findViewById(R.id.tvMinute);
        tvMessage = findViewById(R.id.tvMessage);

        timePicker.setVisibility(View.INVISIBLE);
        calendarView.setVisibility(View.INVISIBLE);
        rdoCalendar.setClickable(false);
        rdoTime.setClickable(false);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rdoCalendar.setClickable(true);
                rdoTime.setClickable(true);
            }
        });

        // 1. 라디오 버튼 이벤트 처리
        rdoCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.setVisibility(View.VISIBLE);
                timePicker.setVisibility(View.INVISIBLE);
            }
        });

        rdoTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker.setVisibility(View.VISIBLE);
                calendarView.setVisibility(View.INVISIBLE);
            }
        });

        // 2. 예약 시작 버튼 이벤트 처리
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 시간이 남아있을 때 초기화
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                chronometer.setTextColor(Color.RED);
            }
        });

        // 3. 캘린더뷰를 선택했을 때 현재 년도, 월, 일 값을 각각 받아줌
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int y, int m, int d) {
                year = y;
                month = m;
                dayOfMonth = d;
            }
        }); // getDate()로 합쳐져서 받음

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int h, int m) {
                hour = h;
                minute = m;
            }
        }); //getHour(), getMinute()있음. 근데 일단 seton~

        // 4. 예약 완료 이벤트 처리
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // chronometer 멈추고 세팅
                chronometer.stop();
                tvYear.setText(String.valueOf(year) + "년 ");
                tvMonth.setText(String.valueOf(month + 1) + "월 ");
                tvDay.setText(String.valueOf(dayOfMonth) + "일 ");
                tvHour.setText(String.valueOf(hour) + "시 ");
                tvMinute.setText(String.valueOf(minute) + "분 ");
                tvMessage.setText("예약 완료 !");
            }
        });
    }
}
