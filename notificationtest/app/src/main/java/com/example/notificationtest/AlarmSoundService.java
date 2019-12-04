package com.example.notificationtest;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class AlarmSoundService extends Service {

    public AlarmSoundService(){

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel");

        builder.setSmallIcon(R.drawable.heart)
                .setContentTitle("기한 만료 알림")
                .setContentText("기한 만료 1일 전입니다.")
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(new NotificationChannel("channel", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT));
        }

        // id 값은 정의해야하는 각 알림의 고유한 int 값
        notificationManager.notify(1, builder.build());

        Toast.makeText(this, "알림 생성 완료", Toast.LENGTH_LONG).show();
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
