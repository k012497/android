package com.example.mp3playertest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnPlay, btnPause;
    private TextView tvPlaying;
    private ProgressBar progressBar;
    private ListView listView;
    private MediaPlayer mediaPlayer;
    private String selectedMP3;

    // SDcard
    private static final String MP3_PATH = Environment.getExternalStorageDirectory().getPath() + "/";
    ArrayList<String> mp3List = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("노래듣자");

        listView = findViewById(R.id.listView);
        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        tvPlaying = findViewById(R.id.tvPlaying);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.INVISIBLE);

        // 외부 저장장치 쓰기 권한 요청
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        // SDcard의 파일을 읽어서 리스트뷰에 출력
        mp3List = new ArrayList<String>();
        File[] listFiles = new File(MP3_PATH).listFiles();
        String fileName, extName;
        for(File file : listFiles){
            fileName = file.getName(); // 파일명 또는 디렉토리명
            extName = fileName.substring(fileName.length() - 3); // 확장자명 가져오기 (마지막 3글자 가져오기)
            // 확장명이 mp3인 경우만 추가
            if(extName.equals("mp3") || extName.equals("mp4")) mp3List.add(fileName);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, mp3List);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(adapter);
        listView.setItemChecked(0, true);

        selectedMP3 = mp3List.get(0);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedMP3 = mp3List.get(position);
            }
        });

        // 음악을 들을 곡을 선택한 미디어 플레이어의 객체를 만들기
//        mediaPlayer = MediaPlayer.create(this, R.raw.jannabi);

        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPlay:
                try {
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(MP3_PATH + selectedMP3);
                    mediaPlayer.prepare(); // 외부파일을 가져오기 위해 준비
                    mediaPlayer.start();
                    btnPlay.setClickable(false);
                    btnPause.setClickable(true);
                    tvPlaying.setText("now playing 🎵 " + selectedMP3);
                    progressBar.setVisibility(View.VISIBLE);
                } catch (IOException e) {
                    Log.e("btnPlay", "exception");
                }
                break;

            case R.id.btnPause:
                mediaPlayer.stop();
                mediaPlayer.reset();
                btnPause.setClickable(false);
                btnPlay.setClickable(true);
                tvPlaying.setText("- PASUED -");
                progressBar.setVisibility(View.INVISIBLE);
                break;
        }
    }
}
