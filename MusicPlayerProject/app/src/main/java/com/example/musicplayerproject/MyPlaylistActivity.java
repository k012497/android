package com.example.musicplayerproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.musicplayerproject.MusicPlayingActivity.mediaPlayer;

public class MyPlaylistActivity extends Fragment implements View.OnTouchListener {
    View view;
    Context context;
    private RecyclerView recyclerView;
    private TextView tvTitle, tvSinger, tvGenre, tvCountClicked, tvNowTitle, tvNowSinger;
    private LinearLayout linearLayout;
    private ImageView imageView, ivAlbum;;
    private LinearLayout llButton;

    ImageButton ibtClose, ibtPlay, ibtPause, ibtStop;
    SeekBar seekBar;
    boolean paused = false;

    String selectedTitle;
    static MediaPlayer mediaPlayer;
    private static final String MP3_PATH = Environment.getExternalStorageDirectory().getPath() + "/";

    SlidingDrawer slidingDrawer;
    SlidingDrawer.OnDrawerCloseListener drawerClosed;
    SlidingDrawer.OnDrawerOpenListener drawerOpened;

    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter adapter;

    ArrayList<MusicItemDTO> items;
    String extractedName;

    public static MyPlaylistActivity newInstance(){
        MyPlaylistActivity fragment2 = new MyPlaylistActivity();
        return fragment2;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_playlist, container, false);
        context = container.getContext();

        recyclerView = view.findViewById(R.id.recyclerView);
        llButton = view.findViewById(R.id.llButton);
        slidingDrawer = view.findViewById(R.id.slidingDrawer);
        tvNowTitle = view.findViewById(R.id.tvNowTitle);

        // set Adapter for RecyclerView
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        loadMyListData(1);

        setDrawerOpendAndClosed();

        slidingDrawer.setOnDrawerOpenListener(drawerOpened);
        slidingDrawer.setOnDrawerCloseListener(drawerClosed);

        return view;
    }

    private void setDrawerOpendAndClosed() {
        drawerClosed = new SlidingDrawer.OnDrawerCloseListener() {
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
            }
        };
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
        adapter.notifyDataSetChanged();
    }

    public String trimFileName(String title) {
        // .확장자명 잘라내기
        int idx = title.indexOf(".");
        // . 앞부분을 추출
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

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    private class RecyclerViewAdapter extends RecyclerView.Adapter<CustomViewHolder>{

        @NonNull
        @Override
        public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_item, parent, false);
            CustomViewHolder customViewHolder = new CustomViewHolder(view);
            return customViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position) {
            final MusicItemDTO music = items.get(position);
            String musicTitle = trimFileName(music.getTitle());
            tvTitle.setText(musicTitle);
            tvSinger.setText(music.getSinger());
            tvGenre.setText(music.getGenre());
            tvCountClicked.setText(String.valueOf(music.getCountClicked()));

            switch (music.getAlbumArt()){
                case "hyukoh":
                    imageView.setImageResource(R.drawable.hyukoh);
                    break;

                default:
                    imageView.setImageResource(R.drawable.album);
                    break;
            }

            // 리사이클러뷰 아이템 클릭
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 클릭 수 증가
                    MusicItemDAO mDAO = new MusicItemDAO(context);
                    MusicItemDTO mvo = mDAO.isExist(music.getTitle());
                    int count = mvo.getCountClicked();
                    mDAO.updateCount(music.getTitle(), ++count);
                    tvCountClicked.setText(String.valueOf(count));

                    // 재생중인 노래가 있으면 멈추기
                    if(mediaPlayer != null && mediaPlayer.isPlaying()) mediaPlayer.stop();

                    slidingDrawer.open();

                    // 음악재생 Intent
//                    Intent intent = new Intent(context, MusicPlayingActivity.class);
//                    intent.putExtra("title", music.getTitle());
//                    intent.putExtra("singer", music.getSinger());
//                    startActivity(intent);

//                    Toast.makeText(MyPlaylistActivity.this, music.getTitle(), Toast.LENGTH_SHORT).show();

                }
            });

            // 리사이클러뷰 아이템 롱클릭 시
            linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                    dialog.setTitle("Delete music");
                    dialog.setMessage("Are you sure to delete " + music.getTitle() + "?");
                    dialog.setNegativeButton("delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MusicItemDAO mDAO = new MusicItemDAO(context);
                            mDAO.delete(music.getTitle());
                            items.remove(position);
                            adapter.notifyItemRemoved(position);
                        }
                    });
                    dialog.setPositiveButton("cancel", null); // 삭제하고 다시 select해오는 이벤트 작성
                    dialog.show();
                    return true;
                }
            });
        }
        @Override
        public int getItemCount() {
            return items != null ? items.size() : 0;
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

}
