package com.example.soyoung.newssonoti;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    // 알람시간
    private Calendar calendar;
    private TimePicker timePicker;
    private TextView txtDate;
    private TextView[] tvAlarm = new TextView[3];
    private Integer[] tvAlarmId = new Integer[]{R.id.tvAlarm1, R.id.tvAlarm2, R.id.tvAlarm3};
    private static final String TAG = "MainActivity";
    private ArrayList<PendingIntent> intentArray = new ArrayList<>();
    private AlarmManager alarmManager;
    private int index;
    private Switch switchAlarm;
    private final String PREFERENCE = "com.example.newssonoti";

//    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("소영이의 실험실");

        calendar = Calendar.getInstance();
        Log.d(TAG, "Calendar.getInstance() : " + calendar);

        switchAlarm = findViewById(R.id.switchAlarm);
        Button btnCalendar = findViewById(R.id.btnCalendar);
        Button btnAlarm = findViewById(R.id.btnAlarm);
        Button btnCancel = findViewById(R.id.btnCancel);
        txtDate = findViewById(R.id.txtDate);
        for (int i = 0; i < tvAlarmId.length; i++) {
            tvAlarm[i] = findViewById(tvAlarmId[i]);
        }
        timePicker = findViewById(R.id.timePicker);

        // 현재 날짜 표시
        displayDate();

        btnCalendar.setOnClickListener(this);
        tvAlarm[0].setOnClickListener(this);
        tvAlarm[1].setOnClickListener(this);
        tvAlarm[2].setOnClickListener(this);
        btnCancel.setOnClickListener(this);
//        btnAlarm.setOnClickListener(this);


        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();

        switchAlarm.setOnCheckedChangeListener(this);
        if (sharedPreferences.getBoolean("switch", false) == true) {
            switchAlarm.setChecked(true);
            Log.d(TAG, "true");
        } else if (sharedPreferences.getBoolean("switch", false) == false) {
            switchAlarm.setChecked(false);
            Log.d(TAG, "false");
        }

    }

    // 날짜 표시
    private void displayDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        txtDate.setText(String.valueOf(format.format(calendar.getTime())));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCalendar:
                // 달력
                showDatePicker();
                break;
            case R.id.tvAlarm1:
                // 알람 등록
                index = 0;
                setAlarm();
                break;
            case R.id.tvAlarm2:
                // 알람 등록
                index = 1;
                setAlarm();
                break;
            case R.id.tvAlarm3:
                // 알람 등록
                index = 2;
                setAlarm();
                break;
            case R.id.btnCancel:
                // 알람 취소
                cancelAlarm();

        }
    }

    // 알람 취소
    private void cancelAlarm() {
        if (intentArray.size() > 0) {
            for (int i = 0; i < intentArray.size(); i++) {
                alarmManager.cancel(intentArray.get(i));
            }
            intentArray.clear();
        }
        toastDisplay("알람이 취소되었습니다.");
    }

    // 알람 등록
    private void setAlarm() {
        // 알람 시간 설정
        // api 버전별 설정
        if (Build.VERSION.SDK_INT < 23) {
            int getHour = timePicker.getCurrentHour();
            int getMinute = timePicker.getCurrentMinute();
            calendar.set(Calendar.HOUR_OF_DAY, getHour);
            calendar.set(Calendar.MINUTE, getMinute);
            calendar.set(Calendar.SECOND, 0);
        } else {
            int getHour = timePicker.getHour();
            int getMinute = timePicker.getMinute();
            calendar.set(Calendar.HOUR_OF_DAY, getHour);
            calendar.set(Calendar.MINUTE, getMinute);
            calendar.set(Calendar.SECOND, 0);
        }

        // 현재보다 이전이면 등록 못하도록
        if (calendar.before(Calendar.getInstance())) {
            toastDisplay("알람시간이 현재시간보다 이전일 수 없습니다.");
            return;
        }

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //알람 시간 표시
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        //다중 알람 설정
        // i는 리퀘스트 코드로 쓴다
        switch (index){
            case 0:
                Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
                intent.putExtra("text", "1st text");
                intent.putExtra("id", 11111);
                intent.putExtra("switch", switchAlarm.isChecked());
                PendingIntent pender = PendingIntent.getBroadcast(MainActivity.this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pender);
                tvAlarm[index].setText("Alarm: " + String.valueOf(format.format(calendar.getTime())));
                toastDisplay("Alarm: " + String.valueOf(format.format(calendar.getTime())));
                break;
            case 1:
                Intent intent2 = new Intent(MainActivity.this, AlarmReceiver.class);
                intent2.putExtra("text", "2nd text");
                intent2.putExtra("id", 22222);
                PendingIntent pender2 = PendingIntent.getBroadcast(MainActivity.this, 2, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pender2);
                tvAlarm[index].setText("Alarm: " + String.valueOf(format.format(calendar.getTime())));
                toastDisplay("Alarm: " + String.valueOf(format.format(calendar.getTime())));
                break;
            case 2:
                Intent intent3 = new Intent(MainActivity.this, AlarmReceiver.class);
                intent3.putExtra("text", "3rd text");
                intent3.putExtra("id", 33333);
                PendingIntent pender3 = PendingIntent.getBroadcast(MainActivity.this, 3, intent3, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pender3);
                tvAlarm[index].setText("Alarm: " + String.valueOf(format.format(calendar.getTime())));
                toastDisplay("Alarm: " + String.valueOf(format.format(calendar.getTime())));
                break;
        }
//        for (int i = 0; i< 3 ; i++) {
//            Intent intent = new Intent(this, AlarmReceiver.class);
//            // Receiver 설정
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, i, intent, 0);
//            // 알람 설정
//            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
//            intentArray.add(pendingIntent);
//        }

    }


    private void toastDisplay(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    // DatePickerDialog 호출
    private void showDatePicker() {
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // 알림 날짜 설정
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DATE, dayOfMonth);

                // 날짜 표시
                displayDate();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }   // end of showDataPicker

//    @RequiresApi(api = Build.VERSION_CODES.O)
//    @Override
//    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

//        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        Intent intent = new Intent(this, AlarmReceiver.class);
//
//        if (isChecked == true) {
//            editor.putBoolean("switch", true);
//            editor.commit();
//            intent.putExtra("switch", true);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//            alarmManager.set(AlarmManager.RTC_WAKEUP, this.calendar.getTimeInMillis(), pendingIntent);
//            Toast.makeText(this, String.valueOf(isChecked), Toast.LENGTH_SHORT).show();
//            Log.d("스위치", "on");
//        } else {
//            editor.putBoolean("switch", false);
//            editor.commit();
//            intent.putExtra("switch", false);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//            alarmManager.set(AlarmManager.RTC_WAKEUP, this.calendar.getTimeInMillis(), pendingIntent);
//            Toast.makeText(this, String.valueOf(isChecked), Toast.LENGTH_SHORT).show();
//            Log.d("스위치", "off");
//        }

//        Intent intent = new Intent(this, AlarmReceiver.class);
//        intent.putExtra("switch", isChecked);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        alarmManager.set(AlarmManager.RTC_WAKEUP, this.calendar.getTimeInMillis(), pendingIntent);
//        Toast.makeText(this, String.valueOf(isChecked), Toast.LENGTH_SHORT).show();

//    }

//    @Override
//    public void onPointerCaptureChanged(boolean isChecked) {
//
//    }



    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

        Log.d(TAG, "change 리스너");

        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

            if (isChecked == true) {
                editor.putBoolean("switch", true);
                editor.commit();
            } else if (isChecked == false) {
                editor.putBoolean("switch", false);
                editor.commit();
            }

            Intent intent = new Intent(this, AlarmReceiver.class);
            intent.putExtra("switch", isChecked);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//            alarmManager.set(AlarmManager.RTC_WAKEUP, this.calendar.getTimeInMillis(), pendingIntent);
            Toast.makeText(this, String.valueOf(isChecked), Toast.LENGTH_SHORT).show();
    }
}