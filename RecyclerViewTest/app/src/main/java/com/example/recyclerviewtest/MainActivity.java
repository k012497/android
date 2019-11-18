package com.example.recyclerviewtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<MainItem> list = new ArrayList<MainItem>();
    private RecyclerView recyclerView;
    private Button btnAdd;
    private LinearLayoutManager linearLayoutManager;
    private MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        btnAdd = findViewById(R.id.btnAdd);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        mainAdapter = new MainAdapter(R.layout.list_item, list);
        recyclerView.setAdapter(mainAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainItem item1 = new MainItem(R.mipmap.ic_launcher, "안드로보이", "ㅎㅇㅎㅇ");
                MainItem item2 = new MainItem(R.drawable.star_with_eye, "별 ", "twinkle");
                list.add(item1);
                list.add(item2);
                mainAdapter.notifyDataSetChanged();
            }
        });
    }
}
