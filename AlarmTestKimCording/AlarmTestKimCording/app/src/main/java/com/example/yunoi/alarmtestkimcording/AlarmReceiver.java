package com.example.yunoi.alarmtestkimcording;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import static android.app.Notification.VISIBILITY_PUBLIC;

public class AlarmReceiver extends BroadcastReceiver {

    private static final String TAG = "AlarmReceiver";

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("InvalidWakeLockTag")
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent rIntent = new Intent(context, AlarmService.class);
        PendingIntent pend = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            context.startForegroundService(rIntent);
            //NotificationManager 안드로이드 상태바에 메세지를 던지기위한 서비스 불러오고
            NotificationManager notificationmanager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
            Notification.Builder builder = new Notification.Builder(context);
            builder.setSmallIcon(R.drawable.ic_launcher_background).setWhen(System.currentTimeMillis())
                    .setContentTitle("청소하실 시간입니다!").setContentText("목록을 확인해 주세요")
                    .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE).setContentIntent(pendingIntent).setAutoCancel(true);
            WakeLocker.acquire(context);
            notificationmanager.notify(1, builder.build());
            Log.d(TAG, "help");

        } else {
            context.startService(rIntent);
            String text = intent.getStringExtra("text");
            int id = intent.getIntExtra("id", 0);
            NotificationManager mNotificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.ic_launcher_background)
                            .setContentTitle(text)
                            .setContentText(text)
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setCategory(NotificationCompat.CATEGORY_REMINDER)
                            .setFullScreenIntent(pend, true)
                            .setContentIntent(pend)
                            .setAutoCancel(true)
                            .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                            .setVisibility(VISIBILITY_PUBLIC);

            WakeLocker.acquire(context);
            mNotificationManager.notify(id, mBuilder.build());
            Log.d(TAG, "help");

            //NotificationManager 안드로이드 상태바에 메세지를 던지기위한 서비스 불러오고
//            NotificationManager notificationmanager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
//            Notification.Builder builder = new Notification.Builder(context);
//            builder.setSmallIcon(R.drawable.ic_launcher_background).setWhen(System.currentTimeMillis())
//                    .setContentTitle("청소하실 시간입니다!").setContentText("목록을 확인해 주세요")
//                    .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE).setContentIntent(pendingIntent).setAutoCancel(true);
//
//            WakeLocker.acquire(context);
//            notificationmanager.notify(1, builder.build());

        }

        WakeLocker.release();
    }
}
