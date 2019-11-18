package com.example.customlistviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<ListItemVO> list = new ArrayList<ListItemVO>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        list.add(new ListItemVO(R.drawable.dog, R.drawable.heart, "강아지", "멍멍"));
        list.add(new ListItemVO(R.drawable.gorilla, R.drawable.heart, "망아지", "알알"));
        list.add(new ListItemVO(R.drawable.rabbit, R.drawable.heart, "송아지","깔깔"));
        list.add(new ListItemVO(R.drawable.dog, R.drawable.heart, "강아지","왈왈"));
        list.add(new ListItemVO(R.drawable.dog, R.drawable.heart, "망아지","알알"));
        list.add(new ListItemVO(R.drawable.dog, R.drawable.heart, "송아지","알알"));

        ListAdapter adapter = new ListAdapter();
        listView.setAdapter(adapter);

    }

    private class ListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item, null);

            ImageView imageView1 = view.findViewById(R.id.imageView1);
            ImageView imageView2 = view.findViewById(R.id.imageView2);
            TextView textView1 = view.findViewById(R.id.textView1);
            TextView textView2 = view.findViewById(R.id.textView2);

            ListItemVO data = list.get(position);

            imageView1.setImageResource(data.getImageResId1());
            imageView2.setImageResource(data.getImageResId2());
            textView1.setText(data.getStringData1());
            textView2.setText(data.getStringData2());

            return view;
        }
    }

}
