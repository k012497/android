package com.example.servicetest;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MusicService extends Service {
    private static final String TAG =  "MusicService";
    private MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // 생명주기 3가지 가져오기
    @Override
    public void onCreate() {
        Log.d(TAG,"Service onCreate()");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"Service onStartCommand()");
        mediaPlayer = MediaPlayer.create(this, R.raw.mikrokosmos);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"Service onDestroy()");
        mediaPlayer.stop();
        super.onDestroy();
    }
}
