package com.example.notificationtest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePicker.OnDateChangedListener {

    // 알림생성버튼
    private Button btnCreate;
    // 알림제거버튼
    private Button btnRemove;
    // 알림예약버튼
    private Button btnAlarm;

    private DatePicker datePicker;
    private TextView textView;

    private int year, month, day;
    private AlarmManager alarm_manager;
//    private PendingIntent pendingIntent;

    private Context context;

    // Calendar 객체 생성
    private Calendar calendar = Calendar.getInstance();

    // 알람리시버 intent 생성
//    private final Intent my_intent = new Intent(this, AlarmReceiver.class);

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("알림 테스트");

        btnCreate = findViewById(R.id.btnCreate);
        btnRemove = findViewById(R.id.btnRemove);
        btnAlarm = findViewById(R.id.btnAlarm);
        datePicker = findViewById(R.id.datePicker);
        textView = findViewById(R.id.textView);

        this.context = this;

        // 알람매니저 설정
//        alarm_manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);


        // 날짜 선택하면 이벤트 처리
        datePicker.setOnDateChangedListener(this);

        // 알림 생성 이벤트
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {

                createNotification();
            }
        });

        // 알림 삭제 이벤트
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                removeNotification();
            }
        });

        // 알림 예약 이벤트
        btnAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                settingAlarmNotification();
            }
        });
    }


    //====================================================================================================//

    // 알림 예약 이벤트
    private void settingAlarmNotification() {

        // calendar 에 날짜 셋팅
//        calendar.set(Calendar.YEAR, datePicker.getYear());
//        calendar.set(Calendar.MONTH, datePicker.getMonth());
//        calendar.set(Calendar.DATE, datePicker.getDayOfMonth());
        calendar.set(Calendar.HOUR_OF_DAY, 22);
        calendar.set(Calendar.MINUTE, 50);
        calendar.set(Calendar.SECOND, 10);

        // 날짜 가져옴
        int settingYear = calendar.get(Calendar.YEAR);
        int settingMonth = calendar.get(Calendar.MONTH);
        int settingDate = calendar.get(Calendar.DATE);
        int settingHour = calendar.get(Calendar.HOUR_OF_DAY);
        int settingMinute = calendar.get(Calendar.MINUTE);

        settingMonth = settingMonth+1;

        Toast.makeText(MainActivity.this,"만료 기한: " + settingYear + "년 " + settingMonth + "월 " +
                settingDate + "일♡" + settingHour + "시 " + settingMinute + "분", Toast.LENGTH_LONG).show();

        // receiver 에 string 값 넘겨주기
//        my_intent.putExtra("state","alarm on");

//        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, my_intent,
//                PendingIntent.FLAG_UPDATE_CURRENT);
//
//        // 알람셋팅
//        alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
//                pendingIntent);

        Intent intent = new Intent("com.example.notificationtest.ALARM_START");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarm_manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        Log.d("ttt", "setting");


    }


    // 알림 생성 메소드
    private void createNotification() {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel");

        builder.setSmallIcon(R.drawable.heart);
        builder.setContentTitle("기한 만료 알림");
        builder.setContentText("기한 만료 1일 전입니다.");

//        builder.setColor(Color.RED);

        // 사용자가 탭을 클릭하면 자동 제거
        builder.setAutoCancel(true);

        // 알림 표시
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(new NotificationChannel("channel", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT));
        }

        // id 값은 정의해야하는 각 알림의 고유한 int 값
        notificationManager.notify(1, builder.build());

        toastDisplay("알림 생성 완료");
    }

    // 알림 제거 메소드
    private void removeNotification() {

        NotificationManagerCompat.from(this).cancel(1);
        toastDisplay("알림 제거 완료");
    }

    // 현재 날짜 가져오기
    private void pickCurrentDate() {

        try {
            // 현재 날짜 가져오기
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);

            // 데이트 피커를 누를 때 이벤트 // datePicker 속에 년, 월, 일 넣기
            datePicker.init(year, month, day, null);

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("tag", "날짜 가져오기 실패");
        }
    }

    // 날짜선택 이벤트 처리 메소드
    @Override
    public void onDateChanged(DatePicker datePicker, int year, int month, int day) {

        month = month+1;

        textView.setText(year + "년 " + month + "월 " + day + "일 선택♥");

    }

    // 토스트 메시지 메소드
    private void toastDisplay(String s) {
        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
    }
}