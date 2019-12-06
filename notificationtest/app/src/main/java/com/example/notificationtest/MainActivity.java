package com.example.notificationtest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
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


public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    final static String TAG = "MainActivity";

    // 알람 시간
    private Calendar calendar;

    private TimePicker timePicker;
    private Button btnCalendar, btnAlarm, btnEdit;
    private Switch switchAlarm;

    static int alarmId = 0;
    ArrayList<Integer> alarmList = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("쏘영이의 실험실");

        this.calendar = Calendar.getInstance();
        // 현재 날짜 표시
        displayDate();

        btnCalendar = findViewById(R.id.btnCalendar);
        btnAlarm = findViewById(R.id.btnAlarm);
        btnEdit = findViewById(R.id.btnEdit);
        switchAlarm = findViewById(R.id.switchAlarm);
        timePicker = findViewById(R.id.timePicker);

        btnCalendar.setOnClickListener(mClickListener);

        btnAlarm.setOnClickListener(mClickListener);
        btnEdit.setOnClickListener(mClickListener);

        switchAlarm.setChecked(true);
        switchAlarm.setOnCheckedChangeListener(this);
    }

    /* 날짜 표시 */
    private void displayDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        ((TextView) findViewById(R.id.txtDate)).setText(format.format(this.calendar.getTime()));
    }

    /* DatePickerDialog 호출 */
    private void showDatePicker() {
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // 알람 날짜 설정
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DATE, dayOfMonth);

                // 날짜 표시
                displayDate();
            }
        }, this.calendar.get(Calendar.YEAR), this.calendar.get(Calendar.MONTH), this.calendar.get(Calendar.DAY_OF_MONTH));

        dialog.show();
    }

    /* 알람 등록 */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setAlarm() {


        // 알람 시간 설정
        this.calendar.set(Calendar.HOUR_OF_DAY, this.timePicker.getHour());
        this.calendar.set(Calendar.MINUTE, this.timePicker.getMinute());
        this.calendar.set(Calendar.SECOND, 0);

        // 현재일보다 이전이면 등록 실패
        if (this.calendar.before(Calendar.getInstance())) {
            Toast.makeText(this, "알람시간이 현재시간보다 이전일 수 없습니다.", Toast.LENGTH_LONG).show();
            return;
        }

        // 이미 등록된 알람이 있을 시 (수정 등록 시) 삭제
        for(int id : alarmList){
            if(id == alarmId){
                deleteAlarm(this, alarmId);
                break;
            }
        }

        // Receiver 설정
        Intent intent = new Intent(this, AlarmReceiver.class);
//        alarmId = (int) System.currentTimeMillis();
        alarmId = 1234;
        intent.putExtra("name", ""+ alarmId);
        intent.setAction("on");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, alarmId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // 알람 설정
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            Log.d("alarmManager", alarmManager.toString());
        alarmManager.set(AlarmManager.RTC_WAKEUP, this.calendar.getTimeInMillis(), pendingIntent);
        alarmList.add(alarmId);

        // Toast 보여주기 (알람 시간 표시)
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Toast.makeText(this, "Alarm : " + format.format(calendar.getTime()), Toast.LENGTH_LONG).show();
    }

    private void deleteAlarm(Context context, int alarmId) {
        Log.d("off", alarmId + " 삭제");
//        alarmList.remove(alarmId);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.setAction("off");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, alarmId, intent, 0);
        alarmManager.cancel(pendingIntent);

    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnCalendar:
                    // 달력
                    showDatePicker();
                    break;

                case R.id.btnAlarm:
                    // 알람 등록
                    setAlarm();
                    break;

                case R.id.btnEdit:
                    // 알람 수정
//                    editAlarm();
                    break;
            }
        }
    };

    /* 알림 수정 */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void editAlarm() {
//        // 알람 시간 설정
//        this.calendar.set(Calendar.HOUR_OF_DAY, this.timePicker.getHour());
//        this.calendar.set(Calendar.MINUTE, this.timePicker.getMinute());
//        this.calendar.set(Calendar.SECOND, 0);
//
//        // 현재일보다 이전이면 등록 실패
//        if (this.calendar.before(Calendar.getInstance())) {
//            Toast.makeText(this, "알람시간이 현재시간보다 이전일 수 없습니다.", Toast.LENGTH_LONG).show();
//            return;
//        }
//
//        // Receiver 설정
//        Intent intent = new Intent(this, AlarmReceiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        // 알람 설정
////        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        alarmManager.set(AlarmManager.RTC_WAKEUP, this.calendar.getTimeInMillis(), pendingIntent);
//
//        // Toast 보여주기 (알람 시간 표시)
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//        Toast.makeText(this, "Alarm : " + format.format(calendar.getTime()), Toast.LENGTH_LONG).show();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("switch", b);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, this.calendar.getTimeInMillis(), pendingIntent);
        Toast.makeText(this, String.valueOf(b), Toast.LENGTH_SHORT).show();
    }
}