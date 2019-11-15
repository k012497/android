package com.example.gridviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private GridView gridView;
    private ArrayList<FilmItem> items = new ArrayList<FilmItem>();
    LayoutInflater layoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("영화 포스터");

        gridView = findViewById(R.id.gridView);

        layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        insertListData();

        GridAdapter adapter = new GridAdapter();
        gridView.setAdapter(adapter);

    }

    private void insertListData() {
        int[] posterID = {R.drawable.mov01, R.drawable.mov02, R.drawable.mov03, R.drawable.mov04, R.drawable.mov05, R.drawable.mov06,
                R.drawable.mov07, R.drawable.mov08, R.drawable.mov09, R.drawable.mov10, R.drawable.mov11, R.drawable.mov12,
                R.drawable.mov13, R.drawable.mov14, R.drawable.mov15, R.drawable.mov16, R.drawable.mov17, R.drawable.mov18,
                R.drawable.mov19, R.drawable.mov20, R.drawable.mov21, R.drawable.mov22, R.drawable.mov23, R.drawable.mov24,
                R.drawable.mov25, R.drawable.mov26, R.drawable.mov27, R.drawable.mov28, R.drawable.mov29, R.drawable.mov30,
                R.drawable.mov31, R.drawable.mov32, R.drawable.mov33, R.drawable.mov34, R.drawable.mov35, R.drawable.mov36,
                R.drawable.mov37, R.drawable.mov38, R.drawable.mov39, R.drawable.mov40};
        String[] posterTitle = { "써니", "완득이", "괴물", "라디오스타", "비열한거리", "왕의남자",
                "아일랜드", "웰컴투동막골", "헬보이", "빽투더퓨처", "여인의 향기", "쥬라기 공원", "포레스트 검프", "라디오스타",
                "비열한거리", "왕의남자", "아일랜드", "웰컴투동막골", "헬보이", "빽투더퓨처", "써니", "완득이",
                "괴물", "라디오스타", "비열한거리", "왕의남자", "아일랜드", "웰컴투동막골", "헬보이",
                "빽투더퓨처", "써니", "완득이", "괴물", "라디오스타", "비열한거리", "왕의남자", "아일랜드",
                "웰컴투동막골", "헬보이", "빽투더퓨처" };


        for(int i = 0  ; i < 40 ; i++){
            items.add(new FilmItem( posterID[i], posterTitle[i]));
        }
    }

    public class GridAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View view, ViewGroup parent) {
            if(view == null){
                view = layoutInflater.inflate(R.layout.imageview, null);
            }

            ImageView imageView = view.findViewById(R.id.imageView);
            FilmItem filmData = items.get(position);
            imageView.setImageResource(filmData.getPosterId());

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View dialogView = View.inflate(MainActivity.this, R.layout.dialog_layout, null);
                    ImageView imageView = dialogView.findViewById(R.id.imageView);
                    TextView textView = dialogView.findViewById(R.id.textView);

                    FilmItem item = items.get(position);
                    imageView.setImageResource(item.getPosterId());
                    textView.setText(item.getTitle());

                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setView(dialogView);
                    dialog.setIcon(R.drawable.ic_list);
                    dialog.setTitle(item.getTitle() + " 크게 보기");
                    dialog.setPositiveButton("닫기", null);
                    dialog.show();
                }
            });

            return view;
        }
    }
}
