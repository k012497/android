package com.example.musicplayerproject;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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
    static Button btnAdd;

    private String selectedMP3;
    private static final String MP3_PATH = Environment.getExternalStorageDirectory().getPath() + "/";
    private ArrayList<MusicItemDTO> mp3List = new ArrayList<MusicItemDTO>();

    private MusicItemDAO mDAO;

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
        btnAdd = view.findViewById(R.id.btnAdd);

        // read mp3 files from SDCard
        loadFromSDCard();

        // set Adapter
        ArrayAdapter<MusicItemDTO> adapter = new ArrayAdapter<MusicItemDTO>(context, android.R.layout.simple_selectable_list_item, mp3List);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(adapter);
        listView.setItemChecked(0, true);

        // initial setting
        selectedMP3 = mp3List.get(0).getPath();
        mDAO = new MusicItemDAO(container.getContext());

        btnAdd.setOnClickListener(this);
        listView.setOnItemClickListener(this);

        return view;
    }

    // when click ListView
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (selectedMP3 == null) return;

        // 현재 재생중이면 정지 후 재생
        if(MyPlaylistActivity.mediaPlayer.isPlaying()) {
            Log.d("isplaying", "sdf");
            MyPlaylistActivity.mediaPlayer.stop();
            MyPlaylistActivity.mediaPlayer.reset();
        }

//        if(selectedMP3.equals(mp3List.get(position).getPath())) return; //같은 곡을 클릭했을 경우 정지만
        selectedMP3 = mp3List.get(position).getPath();
        try {
            MyPlaylistActivity.mediaPlayer.setDataSource(selectedMP3);
            MyPlaylistActivity.mediaPlayer.prepare(); // 외부파일을 가져오기 위해 준비
            MyPlaylistActivity.mediaPlayer.start();
        } catch (IOException e) {
            Log.e("btnPlay", "exception");
            return;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                if (mDAO.isExist(selectedMP3) != null) {
                    mDAO.toastDisplay("이미 추가된 곡입니다");
                    break;
                }

                if (MyPlaylistActivity.mediaPlayer.isPlaying()) {
                    Log.d("isplaying", "sdf");
                    MyPlaylistActivity.mediaPlayer.stop();
                    MyPlaylistActivity.mediaPlayer.reset();
                }

                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                View dialogView = LayoutInflater.from(context).inflate(R.layout.add_song, null);
                dialog.setView(dialogView);
                dialog.setTitle("Enter extra info");

                dialog.setPositiveButton("ADD TO MY LIST", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (spinner_genre.getSelectedItem().toString().equals("")
                                || spinner_album.getSelectedItem().toString().equals("")) {
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
        }
    }

//    public String trimFileName() {
//        String extractedName;
//        // .확장자명 잘라내기
//        int idx = selectedMP3.indexOf(".");
//        // . 앞부분을 추출
//        extractedName = selectedMP3.substring(0, idx);
//
//        // _를 공백으로 바꿈
//        String tempName[] = extractedName.split("_");
//        extractedName = "";
//        for(int i = 0 ; i < tempName.length ; i++) {
//            extractedName += (tempName[i] + " ");
//        }
//        return extractedName;
//    }


    private void loadFromSDCard(){
        mp3List.removeAll(mp3List);
        //가져오고 싶은 컬럼명 (id, 제목, 가수, 앨범아트, 재생시간, 경로)
        ContentResolver contentResolver = context.getContentResolver();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";
        Cursor cursor = contentResolver.query(uri, null, selection, null, sortOrder);
        cursor.moveToFirst();
        Log.d("song", cursor.getCount()+"");
        System.out.println("음악파일 개수 = " + cursor.getCount());
        if (cursor != null && cursor.getCount() > 0) {
            MusicItemDTO song = new MusicItemDTO();
            do {
                song.setId(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
                song.setAlbumArt(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)));
                song.setTitle(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
                song.setSinger(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
                song.setDuration(cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)));
                song.setPath(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
                Log.d("song", song.getTitle());
                // Save to audioList
                mp3List.add(song);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

}