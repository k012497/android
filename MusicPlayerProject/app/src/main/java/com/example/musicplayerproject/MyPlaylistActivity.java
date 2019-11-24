package com.example.musicplayerproject;

import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;

import static com.example.musicplayerproject.MusicItemDAO.items;

public class MyPlaylistActivity extends Fragment {
    private View view;
    private Context context;

    private RecyclerView recyclerView;
    private ConstraintLayout music_playing;
    private TextView tvTitle, tvSinger, tvGenre, tvCountClicked, tvNowTitle, tvTitlePlaying, tvSingerPlaying;
    private LinearLayout linearLayout;
    private ImageView imageView, ivAlbum;
    private LinearLayout llButton;
    private ImageButton ibtPlay, ibtPause, ibtStop;
    private SeekBar seekBar;

    private SlidingDrawer slidingDrawer;
    private SlidingDrawer.OnDrawerCloseListener drawerClosed;
    private SlidingDrawer.OnDrawerOpenListener drawerOpened;

    private boolean paused = false;
    private int menuItemId = 1;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerViewAdapter adapter;

    private MediaPlayer mediaPlayer;
    private String extractedName, selectedTitle;
    private static final String MP3_PATH = Environment.getExternalStorageDirectory().getPath() + "/";


    public static MyPlaylistActivity newInstance(){
        MyPlaylistActivity fragment2 = new MyPlaylistActivity();
        return fragment2;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_playlist, container, false);
        context = container.getContext();

        music_playing = view.findViewById(R.id.music_playing);
        recyclerView = view.findViewById(R.id.recyclerView);
        llButton = view.findViewById(R.id.llButton);
        slidingDrawer = view.findViewById(R.id.slidingDrawer);
        tvNowTitle = view.findViewById(R.id.tvNowTitle);
        tvTitlePlaying = view.findViewById(R.id.tvTitlePlaying);
        tvSingerPlaying = view.findViewById(R.id.tvSingerPlaying);
        seekBar = view.findViewById(R.id.seekBar);
        ibtPlay = view.findViewById(R.id.ibtPlay);
        ibtStop = view.findViewById(R.id.ibtStop);
        ibtPause = view.findViewById(R.id.ibtPause);
//        ibtPrev = view.findViewById(R.id.ibtPrev);
//        ibtNext = view.findViewById(R.id.ibtNext);
//        ibtPlayOrPause = view.findViewById(R.id.ibtPlayOrPause);
        ivAlbum = view.findViewById(R.id.ivAlbum);

        // set Adapter for RecyclerView
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        setHasOptionsMenu(true);

        loadMyListData(1);

        // set drawer
        setDrawerOpendAndClosed();
        slidingDrawer.setOnDrawerOpenListener(drawerOpened);
        slidingDrawer.setOnDrawerCloseListener(drawerClosed);


        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.add(0,1,0,"등록 순");
        menu.add(0,2,0,"가나다 순");
        menu.add(0,3,0,"많이 들은 순");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 1:
                loadMyListData(1);
                break;
            case 2:
                loadMyListData(2);
                break;
            case 3:
                loadMyListData(3);
                break;
        }
        return true;
    }


    private void setDrawerOpendAndClosed() {
        drawerClosed = new SlidingDrawer.OnDrawerCloseListener() {
            @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
            @Override
            public void onDrawerClosed() {
                llButton.setVisibility(View.VISIBLE);
                tvNowTitle.setVisibility(View.VISIBLE);
            }
        };

        drawerOpened= new SlidingDrawer.OnDrawerOpenListener() {
            @Override
            public void onDrawerOpened() {
                llButton.setVisibility(View.INVISIBLE);
                tvNowTitle.setVisibility(View.INVISIBLE);
                music_playing.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return true;
                    }
                });
            }
        };
    }

    private void loadMyListData(int menu) {
        MusicItemDAO mDAO = new MusicItemDAO(context);
        switch (menu){
            case 1:
                recyclerView.removeAllViews();
                items = mDAO.selectAll();
                break;
            case 2:
                recyclerView.removeAllViews();
                items = mDAO.selectAllByTitle();
                break;
            case 3:
                recyclerView.removeAllViews();
                items = mDAO.selectAllByCount();
                break;
        }
//        adapter.notifyDataSetChanged();
    }

    public String trimFileName(String title) {
        // 확장자명 잘라내기
        // . 앞부분을 추출
        int idx = title.indexOf(".");
        extractedName = title.substring(0, idx);

        // _를 공백으로 바꿈
        String tempName[] = extractedName.split("_");
        extractedName = "";
        for(int i = 0 ; i < tempName.length ; i++)
        {
            extractedName += (tempName[i] + " ");
        }
        Log.d("trimFileName", title);

        return extractedName;
    }

    public void buttonSetEnabled(boolean play, boolean pause, boolean stop){
        ibtPlay.setEnabled(play);
        ibtPause.setEnabled(pause);
        ibtStop.setEnabled(stop);
    }

    private class RecyclerViewAdapter extends RecyclerView.Adapter<CustomViewHolder> implements View.OnClickListener {
        private int position;

        @NonNull
        @Override
        public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_item, parent, false);
            CustomViewHolder customViewHolder = new CustomViewHolder(view);
            return customViewHolder;
        }

        @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
        @Override
        public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position) {
            this.position = position;
            final MusicItemDTO music = items.get(position);
            final String musicTitle = trimFileName(music.getTitle());
            tvTitle.setText(musicTitle);
            tvSinger.setText(music.getSinger());
            tvGenre.setText(music.getGenre());
            tvCountClicked.setText(String.valueOf(music.getCountClicked()));

            ibtPlay.setOnClickListener(this);
            ibtPause.setOnClickListener(this);
            ibtStop.setOnClickListener(this);

            if(tvSingerPlaying.equals("-")){
                buttonSetEnabled(true, false, false);
            }

            switch (music.getAlbumArt()){
                case "hyukoh":
                    imageView.setImageResource(R.drawable.hyukoh);
                    break;

                case "panda":
                    imageView.setImageResource(R.drawable.panda_bear);
                    break;

                case "22":
                    imageView.setImageResource(R.drawable.twenty_two);
                    break;

                case "24":
                    imageView.setImageResource(R.drawable.twenty_four);
                    break;

                default:
                    imageView.setImageResource(R.drawable.default_album);
                    break;
            }

            // 리사이클러뷰 아이템 클릭 시
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
                @Override
                public void onClick(View v) {
                    slidingDrawer.open();
                    if(tvTitlePlaying.getText().toString().trim().equals(trimFileName(music.getTitle()).trim())){
                        // 이미 재생중인 노래를 선택한 경우
                        return;
                    }
                    // 클릭 수 증가
                    MusicItemDAO mDAO = new MusicItemDAO(context);
                    MusicItemDTO mvo = mDAO.isExist(music.getTitle());
                    int newCount = mvo.getCountClicked() + 1;
                    mDAO.updateCount(music.getTitle(), newCount);
                    tvCountClicked.setText(String.valueOf(newCount));
                    loadMyListData(menuItemId);

                    tvNowTitle.setText(music.getSinger() + " - " + musicTitle);

                    // 재생중인 노래가 있으면 멈추기
                    if (mediaPlayer != null && mediaPlayer.isPlaying()){
                        mediaPlayer.stop();
                    }

                    selectedTitle = music.getTitle();
                    tvTitlePlaying.setText(musicTitle);
                    tvSingerPlaying.setText(music.getSinger());
                    tvTitlePlaying.setSelected(true);

                    // SeekBar를 움직이면 해당구간이 재생됨
                    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            if (fromUser)
                                mediaPlayer.seekTo(progress);
                        }

                        @Override public void onStartTrackingTouch(SeekBar seekBar) {
                        }
                        @Override public void onStopTrackingTouch(SeekBar seekBar) {
                        }
                    });

                    ibtPlay.callOnClick();
                }
            });

            // 리사이클러뷰 아이템 롱클릭 시 - 삭제
            linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                    dialog.setTitle("Delete music");
                    dialog.setMessage("Are you sure to delete " + music.getTitle() + "?");
                    Log.d("longclick position", position+"");
                    Log.d("longclick position", items.get(position).getTitle());
                    dialog.setNegativeButton("delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MusicItemDAO mDAO = new MusicItemDAO(context);
                            mDAO.delete(music.getTitle());
                            items.remove(position);
                            adapter.notifyItemRemoved(position);
                        }
                    });
                    dialog.setPositiveButton("cancel", null);
                    dialog.show();
                    return true;
                }
            });
        }

        @Override
        public int getItemCount() {
            return items != null ? items.size() : 0;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ibtPlay:
                    if(selectedTitle == null) break;
                    mediaPlayer = new MediaPlayer();

                    // 끝까지 재생되었을 경우 다음곡 자동 재생
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
                        public void onCompletion(MediaPlayer mp) {
                            MusicItemDAO mDAO = new MusicItemDAO(context);
                            MusicItemDTO music = position < items.size() ? items.get(position + 1) : items.get(0);
                            selectedTitle = music.getTitle();
                            String musicTitle = trimFileName(music.getTitle());

                            // 재생중인 화면 타이틀, 가수 바꾸기
                            // 카운트 증가
                            int newCount = music.getCountClicked() + 1;
                            mDAO.updateCount(music.getTitle(), newCount);
                            tvCountClicked.setText(String.valueOf(newCount));
                            loadMyListData(menuItemId);

                            tvNowTitle.setText(music.getSinger() + " - " + musicTitle);

                            selectedTitle = music.getTitle();
                            tvTitlePlaying.setText(musicTitle);
                            tvSingerPlaying.setText(music.getSinger());
                            tvTitlePlaying.setSelected(true);

                            ibtPlay.callOnClick();
                        }
                    });

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
                        tvNowTitle.setSelected(true);
                        startUiThread();
                        paused = false;
                        ibtPause.setImageResource(R.drawable.pause);

                    } else {
                        // 일시정지중이 아닐 때
                        mediaPlayer.pause();
                        tvNowTitle.setSelected(false);
                        Toast.makeText(context, "paused", Toast.LENGTH_SHORT).show();
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
    }

    private class CustomViewHolder extends RecyclerView.ViewHolder{

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvSinger = itemView.findViewById(R.id.tvSinger);
            tvGenre = itemView.findViewById(R.id.tvGenre);
            tvCountClicked = itemView.findViewById(R.id.tvCountClicked);
            imageView = itemView.findViewById(R.id.imageView);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
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
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        seekBar.setMax(mediaPlayer.getDuration());
                        seekBar.setMin(0);
                    }
                });

                while(mediaPlayer.isPlaying()){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ivAlbum.setRotation((float) Math.random()*360);
                            seekBar.setProgress(mediaPlayer.getCurrentPosition());
                        }
                    }); // end of runOnUiThread
                    SystemClock.sleep(200);

                }// end of while
            }
        };
        thread.start();
    }
}
