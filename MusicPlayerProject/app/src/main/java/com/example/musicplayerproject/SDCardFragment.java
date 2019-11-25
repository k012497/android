package com.example.musicplayerproject;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SDCardFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    Context context;
    View view;

    private TextView tvSelectedSong;
    private Spinner spinner_genre, spinner_album;
    private ListView listView;
    static Button btnPlay, btnStop, btnAdd;
    static MediaPlayer mediaPlayer;

    private String selectedMP3;
    private static final String MP3_PATH = Environment.getExternalStorageDirectory().getPath() + "/";
    private ArrayList<String> mp3List = new ArrayList<String>();

    private MusicItemDAO mDAO;
    private MyDBHelper myDBHelper;

    public static SDCardFragment newInstance(){
        SDCardFragment fragment1 = new SDCardFragment();
        return fragment1;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sdcard_fragment, container, false);
        context = container.getContext();

        listView = view.findViewById(R.id.listView);
        btnPlay = view.findViewById(R.id.btnPlay);
        btnStop = view.findViewById(R.id.btnStop);
        btnAdd = view.findViewById(R.id.btnAdd);

        loadFromSDCard();
        myDBHelper = new MyDBHelper(container.getContext());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_single_choice, mp3List);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(adapter);
        listView.setItemChecked(0, true);

        // initial setting
        selectedMP3 = mp3List.get(0);
        btnStop.setEnabled(false);
        btnStop.setTextColor(Color.DKGRAY);
        mDAO = new MusicItemDAO(container.getContext());

        btnPlay.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        listView.setOnItemClickListener(this);

        return view;
    }



    private void loadFromSDCard() {
        // SDcard의 파일을 읽어서 리스트뷰에 출력
        mp3List = new ArrayList<String>();
        File[] listFiles = new File(MP3_PATH).listFiles();
        String fileName, extendName;
        for(File file : listFiles){
            fileName = file.getName(); // 파일명 또는 디렉토리명
            extendName = fileName.substring(fileName.length() - 3); // 확장자명 가져오기 (마지막 3글자 가져오기)
            // 확장명이 mp3 또는 mp4인 경우만 추가
            if(extendName.equals("mp3") || extendName.equals("mp4")) mp3List.add(fileName);
        }
        
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAdd:
                if(mDAO.isExist(selectedMP3) != null) {
                    mDAO.toastDisplay("이미 추가된 곡입니다");
                    break;
                }
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                View dialogView = LayoutInflater.from(context).inflate(R.layout.add_song, null);
                dialog.setView(dialogView);
                dialog.setTitle("Enter extra info");

                spinner_genre = dialogView.findViewById(R.id.spinner_genre);
                spinner_album = dialogView.findViewById(R.id.spinner_album);
                tvSelectedSong = dialogView.findViewById(R.id.tvSelectedSong);

                tvSelectedSong.setText(trimFileName());


                dialog.setPositiveButton("ADD TO MY LIST", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(spinner_genre.getSelectedItem().toString().equals("")
                                ||  spinner_album.getSelectedItem().toString().equals("")) {
                            mDAO.toastDisplay("장르와 앨범을 선택해주세요");
                            return;
                        }

                        MusicItemDAO mDAO = new MusicItemDAO(context);
                        mDAO.insert(selectedMP3, "혁오",
                                spinner_genre.getSelectedItem().toString(), spinner_album.getSelectedItem().toString());
                        mDAO.toastDisplay(selectedMP3 + "을 추가하였습니다!");
                    }
                });

                dialog.setNegativeButton("BACK", null);
                dialog.show();
                break;

            case R.id.btnPlay:
                try {
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(MP3_PATH + selectedMP3);
                    mediaPlayer.prepare(); // 외부파일을 가져오기 위해 준비
                    mediaPlayer.start();
                    btnPlay.setTextColor(Color.DKGRAY);
                    btnStop.setTextColor(Color.WHITE);
                    btnPlay.setEnabled(false);
                    btnStop.setEnabled(true);
                } catch (IOException e) {
                    Log.e("btnPlay", "exception");
                }
                break;

            case R.id.btnStop:
                mediaPlayer.stop();
                mediaPlayer.reset();
                btnPlay.setTextColor(Color.WHITE);
                btnStop.setTextColor(Color.DKGRAY);
                btnStop.setEnabled(false);
                btnPlay.setEnabled(true);
                break;
        }
    }

    public String trimFileName() {
        String extractedName;
        // .확장자명 잘라내기
        int idx = selectedMP3.indexOf(".");
        // . 앞부분을 추출
        extractedName = selectedMP3.substring(0, idx);

        // _를 공백으로 바꿈
        String tempName[] = extractedName.split("_");
        extractedName = "";
        for(int i = 0 ; i < tempName.length ; i++)
        {
            extractedName += (tempName[i] + " ");
        }

        return extractedName;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectedMP3 = mp3List.get(position);
    }
}
