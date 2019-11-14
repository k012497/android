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

        list.add(new ListItemVO(R.drawable.dog, "강아지"));
        list.add(new ListItemVO(R.drawable.dog, "망아지"));
        list.add(new ListItemVO(R.drawable.dog, "송아지"));
        list.add(new ListItemVO(R.drawable.dog, "강아지"));
        list.add(new ListItemVO(R.drawable.dog, "망아지"));
        list.add(new ListItemVO(R.drawable.dog, "송아지"));

        listView = findViewById(R.id.listView);

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

            ImageView imageView = view.findViewById(R.id.imageView);
            TextView textView = view.findViewById(R.id.textView);

            ListItemVO data = list.get(position);

            imageView.setImageResource(data.getImageResId());
            textView.setText(data.getStringData());

            return view;
        }
    }

}
