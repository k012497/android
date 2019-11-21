package com.example.mymp3playerthreadtest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.icu.text.SimpleDateFormat;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView listViewMP3;
    private Button btnPlay, btnPause, btnStop;
    private TextView tvPlaying, tvTime;
    private SeekBar seekBar;

    private MediaPlayer mediaPlayer;
    private ArrayList<String> list = new ArrayList<String>();
    private String selectedMP3;

    boolean PAUSED = false;

    private static final String MP3_PATH = Environment.getExternalStorageDirectory().getPath() + "/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewMP3 = findViewById(R.id.listViewMP3);
        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        btnStop = findViewById(R.id.btnStop);
        tvPlaying = findViewById(R.id.tvPlaying);
        tvTime = findViewById(R.id.tvTime);
        seekBar = findViewById(R.id.seekBar);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        File[] files = new File(MP3_PATH).listFiles();

        for(File file : files){
            String fileName = file.getName();
            if(fileName.length() >= 5  && fileName.substring(fileName.length() - 3).equals("mp3")) list.add(fileName);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, list);
        listViewMP3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listViewMP3.setAdapter(adapter);
        listViewMP3.setItemChecked(0, true);

        listViewMP3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedMP3 = list.get(position);
            }
        });


        // 초기값 세팅
        buttonSetClickable(true, false, false);
        seekBar.setProgress(0);
        tvTime.setText("진행시간 : 0");
        selectedMP3 = list.get(0);

        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnStop.setOnClickListener(this);

    } // end of onCreate

    private void buttonSetClickable(boolean a, boolean b, boolean c){
        btnPlay.setEnabled(a);
        btnPause.setEnabled(b);
        btnStop.setEnabled(c);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPlay:
                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(MP3_PATH + selectedMP3);
                    mediaPlayer.prepare();
                    mediaPlayer.start();

                    buttonSetClickable(false, true, true);
                    tvPlaying.setText("now Palying : " + selectedMP3);

                    Thread thread = new Thread(){
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");

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
                                        tvTime.setText(String.valueOf(mediaPlayer.getDuration()));
                                        seekBar.setMax(mediaPlayer.getDuration());
                                        seekBar.setMin(0);
                                    }
                                });

                                while(mediaPlayer.isPlaying()){
                                    // 작업스레드 내에서 UI객체를 변경하기 위해
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            tvTime.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                                            seekBar.setProgress(mediaPlayer.getCurrentPosition());
                                        }
                                    }); // end of runOnUiThread
                                    SystemClock.sleep(100);

                                }// end of while
                        }
                    };
                    thread.start();

                } catch (IOException e) {
                    Log.e("btnPlay onClick", e.toString());
                }
                break;

            case R.id.btnPause:
                if(PAUSED){
                    // 일시정지중일 때
                    mediaPlayer.start();
                    PAUSED = false;
                    btnPause.setText("PAUSE");

                } else {
                    // 일시정지중이 아닐 때
                    mediaPlayer.pause();
                    PAUSED = true;
                    btnPause.setText("CONT");
                }
                buttonSetClickable(false, true, true);
                break;

            case R.id.btnStop:
                mediaPlayer.stop();
                mediaPlayer.reset();
                buttonSetClickable(true, false, false);
                break;
        }
    }
}
