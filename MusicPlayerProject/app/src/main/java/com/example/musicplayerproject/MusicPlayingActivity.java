package com.example.musicplayerproject;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MusicPlayingActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView ivAlbum;
    TextView tvTitle, tvSinger;
    ImageButton ibtClose, ibtPlay, ibtPause, ibtStop;
    SeekBar seekBar;
    boolean paused = false;

    String selectedTitle;
    static MediaPlayer mediaPlayer;
    private static final String MP3_PATH = Environment.getExternalStorageDirectory().getPath() + "/";

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_playing);

        ivAlbum = findViewById(R.id.ivAlbum);
        tvTitle = findViewById(R.id.tvTitle);
        tvSinger = findViewById(R.id.tvSinger);
        seekBar = findViewById(R.id.seekBar);
        ibtPlay = findViewById(R.id.ibtPlay);
        ibtPause = findViewById(R.id.ibtPause);
        ibtStop = findViewById(R.id.ibtStop);

        setText();

        ibtClose.setOnClickListener(this);
        ibtPlay.setOnClickListener(this);
        ibtPause.setOnClickListener(this);
        ibtStop.setOnClickListener(this);

        ibtPlay.callOnClick();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibtPlay:
                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(MP3_PATH + selectedTitle);
                    mediaPlayer.prepare();
                    mediaPlayer.start();

                    buttonSetEnabled(false, true, true);

                    startUiThread();

                } catch (IOException e) {
                    Log.e("btnPlay onClick", e.toString());
                }
                break;
            case R.id.ibtPause:
                if(paused){
                    // 일시정지중일 때
                    mediaPlayer.start();
                    paused = false;
                    ibtPause.setImageResource(R.drawable.pause);

                    startUiThread();

                } else {
                    // 일시정지중이 아닐 때
                    mediaPlayer.pause();
                    paused = true;
                    ibtPause.setImageResource(R.drawable.pause_click);
                }
                buttonSetEnabled(false, true, true);
                break;

            case R.id.ibtStop:
                mediaPlayer.stop();
                mediaPlayer.reset();
                seekBar.setProgress(0);
                ibtPause.setImageResource(R.drawable.pause);
                paused = false;
                buttonSetEnabled(true, false, false);
                break;
        }
    }

    public void setText() {
        Intent intent = getIntent();
        selectedTitle = intent.getStringExtra("title");

        String extractedName;
        // .확장자명 잘라내기
        int idx = selectedTitle.indexOf(".");
        // . 앞부분을 추출
        extractedName = selectedTitle.substring(0, idx);

        // _를 공백으로 바꿈
        String tempName[] = extractedName.split("_");
        extractedName = "";
        for(int i = 0 ; i < tempName.length ; i++)
        {
            extractedName += (tempName[i] + " ");
        }

        tvTitle.setText(extractedName);
        tvSinger.setText(intent.getStringExtra("singer"));
    }

    public void buttonSetEnabled(boolean play, boolean pause, boolean stop){
        ibtPlay.setEnabled(play);
        ibtPause.setEnabled(pause);
        ibtStop.setEnabled(stop);
    }

    public void startUiThread(){
        Thread thread = new Thread(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                if(mediaPlayer == null){
                    return;
                }
                // 작업스레드 내에서 UI객체를 변경하기 위해
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        seekBar.setMax(mediaPlayer.getDuration());
                        seekBar.setMin(0);
                    }
                });

                while(mediaPlayer.isPlaying()){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ivAlbum.setRotation((float) Math.random()*360);
                            seekBar.setProgress(mediaPlayer.getCurrentPosition());
                        }
                    }); // end of runOnUiThread
                    SystemClock.sleep(100);

                }// end of while
            }
        };
        thread.start();
    }
}
