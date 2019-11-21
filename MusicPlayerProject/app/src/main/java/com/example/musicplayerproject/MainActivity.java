package com.example.musicplayerproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView tvNowPlaying, tvTitle, tvSinger, tvGenre, tvCountClicked;
    private ImageView imageView;
    private SeekBar seekBar;

    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapter adapter;
    ArrayList<MusicItemDTO> items = new ArrayList<MusicItemDTO>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        tvNowPlaying = findViewById(R.id.tvNowPlaying);
        seekBar = findViewById(R.id.seekBar);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

    }

    private class RecyclerViewAdapter extends RecyclerView.Adapter<CustomViewHolder>{

        @NonNull
        @Override
        public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_item, parent);
            CustomViewHolder customViewHolder = new CustomViewHolder(view);
            return customViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
            tvTitle.setText(items.get(position).getTitle());
            tvSinger.setText(items.get(position).getSinger());
            tvGenre.setText(items.get(position).getGenre());
            tvCountClicked.setText(items.get(position).getCountClicked());
            imageView.setImageResource(items.get(position).getImageSource());
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
        }
    }

}
