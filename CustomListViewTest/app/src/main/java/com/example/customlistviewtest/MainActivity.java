package com.example.customlistviewtest;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<ListItemVO> list = new ArrayList<ListItemVO>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        list.add(new ListItemVO(R.drawable.dog, "강아지", "멍멍"));
        list.add(new ListItemVO(R.drawable.gorilla, "망아지", "알알"));
        list.add(new ListItemVO(R.drawable.rabbit, "송아지","깔깔"));
        list.add(new ListItemVO(R.drawable.dog, "강아지","왈왈"));
        list.add(new ListItemVO(R.drawable.dog, "망아지","알알"));
        list.add(new ListItemVO(R.drawable.dog, "송아지","알알"));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(R.layout.list_item, list);
        recyclerView.setAdapter(recyclerAdapter);

    }

    public class RecyclerAdapter extends RecyclerView.Adapter<MainActivity.CustomHolderView>{
        int layout = 0;
        ArrayList<ListItemVO> list = new ArrayList<ListItemVO>();

        public RecyclerAdapter(int layout, ArrayList<ListItemVO> list) {
            Log.d("MainActivity", "make Adapter");
            this.layout = layout;
            this.list = list;
        }

        @NonNull
        @Override
        public CustomHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
            CustomHolderView customHolderView = new CustomHolderView(view);

            Log.d("MainActivity", "createViewHolder");

            return customHolderView;
        }

        @Override
        public void onBindViewHolder(@NonNull final MainActivity.CustomHolderView holder, final int position) {
            holder.imageView.setImageResource(list.get(position).getImageResId());
            holder.textView1.setText(list.get(position).getStringData1());
            holder.textView2.setText(list.get(position).getStringData2());
            holder.itemView.setTag(position);
        }

        @Override
        public int getItemCount() {
            Log.d("MainActivity", "getCount");
            return list != null ? list.size() : 0;
        }
    }

    public class CustomHolderView extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView1, textView2;

        public CustomHolderView(@NonNull View itemView) {
            super(itemView);
            Log.d("MainActivity", "make CustomHolderView");
            imageView = itemView.findViewById(R.id.imageView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
        }
    }
}