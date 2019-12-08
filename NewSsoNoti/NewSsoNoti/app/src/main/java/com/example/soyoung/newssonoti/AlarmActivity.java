package com.example.soyoung.newssonoti;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AlarmActivity extends AppCompatActivity implements View.OnClickListener {

    private MediaPlayer mediaPlayer;
    private Button btnClose;
    private Vibrator vibrator;
    private static final long[] vibratePattern = new long[]{500, 5000};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        setContentView(R.layout.activity_alarm);

        // 알람음 재생
        vibrator= (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        mediaPlayer = MediaPlayer.create(this, R.raw.alarm);
        mediaPlayer.start();
        vibrator.vibrate(vibratePattern, 0);
        btnClose = findViewById(R.id.btnClose);
        btnClose.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // MediaPlayer release
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnClose){
            //알람 종료
            close();
        }
    }

    // 알람 종료
    private void close() {
        if(mediaPlayer.isPlaying()){
            vibrator.cancel();
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        finish();
    }
}
