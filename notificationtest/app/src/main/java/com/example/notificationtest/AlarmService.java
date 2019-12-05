package com.example.notificationtest;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class AlarmService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        createNotification();

        return START_NOT_STICKY;
    }

    private void createNotification() {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel");

        builder.setSmallIcon(R.drawable.fridge);
        builder.setContentTitle("유통기한 알림!");
        builder.setContentText(" '우유' 의 유통기한이 1일 남았어요.");
        builder.setAutoCancel(true);         //클릭하면 자동으로 알림 삭제

//        builder.setColor(Color.RED);

        //클릭했을 때 시작할 액티비티에게 전달하는 Intent 객체 생성
        Intent intent= new Intent(this, FoodAndDateActivity.class);

        //클릭할 때까지 액티비티 실행을 보류하고 있는 PendingIntent 객체 생성
        PendingIntent pending= PendingIntent.getActivity(this, 0, intent, 0);

        builder.setContentIntent(pending);   //PendingIntent 설정

        // 알림 표시
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(new NotificationChannel("channel", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT));
        }

        // id 값은 정의해야하는 각 알림의 고유한 int 값
        notificationManager.notify(0, builder.build());
        startForeground(0, builder.build());
    }

}
