package com.example.fcmpushtest;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;

// 메세지를 받기 위한 클래스
public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService{
    private static final String TAG ="FirebaseMessageServicie";
    private String msg, title;

    //firebase로부터 메시지를 받을 때 알려주는 핸들러함수
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "onMessageReceived");
        title = remoteMessage.getNotification().getTitle();
        msg = remoteMessage.getNotification().getBody();
//참고: FLAG_ACTIVITY_CLEAR_TOP
//스택에 기존에 사용하던 Activity가 있다면 그 위의 스택을 전부 제거해 주고 호출한다.
//0->1->2->3 일때 Activity 3에서 Activity 1을 호출할때 이 플래그를 설정하면
//0->1 만 남게 된다. (2, 3은 제거)  이때 Activity 1은 onDestory() -> onCreate()가 호출된다. //삭제하고 다시 만들게 된다.

        Intent intent=new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

//펜딩인텐트 (Pending Intent) 는 인텐트의 일종이다. 컴포넌트에서 다른 컴포넌트에게 //작업을 요청하는 인텐트를 사전에 생성시키고 만든다는 점과 "특정 시점"에 자신이 아닌 //다른 컴포넌트들이 펜딩인텐트를 사용하여 다른 컴포넌트에게 작업을 요청시키는 데 //사용된다는 점이 차이점이다.

//수행시킬 작업 및 인텐트(실행의도)와 및 그것을 수행하는 주체를 지정하기 위한 정보를 //명시 할 수 있는 기능의 클래스라고 보면 된다.
//A한테 이 B인텐트를 C시점에 실행하라고 해. 지금은 실행하지 말고.

//Activity를 시작하는(여기서는 Activity는 MainActivity 말함) 인텐트를 생성함.
        PendingIntent contentIntent=PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class),0);

//상태바 드래그시 보이는 구조
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher) //상태바드래그시보이는아이콘
                        .setContentTitle(title)  //firebase부터받은 타이틀내용
                        .setContentText(msg)  //firebase 부터받은 메세지내용
                        .setAutoCancel(true)
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))   //통지메세지가 올 때 사운드 소리
                        .setVibrate(new long[]{1,1000});  //바이브레이션 1초동안진동

        NotificationManager notificationManager =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
        builder.setContentIntent(contentIntent);

    }

//    private static final String TAG = "FirebaseMessagingService";
//    private String title, body;
//
//    @SuppressLint("LongLogTag")
//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//        Log.d(TAG, "onMessageReceived");
//
//        //remoteMessage에서 title, body를 가져옴
//        title = remoteMessage.getNotification().getTitle();
//        body = remoteMessage.getNotification().getBody();
//
//        //앱이 실행되도록 액티비티
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
//
//        //상태바에 메세지 날리기
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this).setSmallIcon(R.mipmap.dog).
//                setContentTitle(title).setContentText(body).setAutoCancel(true).
//                setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)).setVibrate(new long[]{1, 300});
//
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(0, builder.build());
//        builder.setContentIntent(pendingIntent);
//
//    }
}
