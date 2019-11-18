package com.example.mylistviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    View view;
    ListView listView;
    ArrayList<ListItem> items = new ArrayList<ListItem>();
    LayoutInflater layoutInflater = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertItems();
        listView = findViewById(R.id.listView);

        ListAdapter adapter = new ListAdapter();
        listView.setAdapter(adapter);

    }

    private void insertItems() {
        items.add(new ListItem("South Korea", "Seoul", R.drawable.korea_flag));
        items.add(new ListItem("South Korea", "Seoul", R.drawable.korea_flag));
        items.add(new ListItem("South Korea", "Seoul", R.drawable.korea_flag));
        items.add(new ListItem("South Korea", "Seoul", R.drawable.korea_flag));
        items.add(new ListItem("South Korea", "Seoul", R.drawable.korea_flag));
        items.add(new ListItem("South Korea", "Seoul", R.drawable.korea_flag));
        items.add(new ListItem("France", "Paris", R.drawable.france_flag));
        items.add(new ListItem("France", "Paris", R.drawable.france_flag));
        items.add(new ListItem("France", "Paris", R.drawable.france_flag));
        items.add(new ListItem("France", "Paris", R.drawable.france_flag));
    }

    class ListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return (items != null ? items.size() : 0);
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
        public View getView(int position, View convertView, ViewGroup parent) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_item, null);

            ImageView imageView = view.findViewById(R.id.imageView);
            TextView tvCountry = view.findViewById(R.id.tvCountry);
            TextView tvCapital = view.findViewById(R.id.tvCapital);

            ListItem item = items.get(position);
            imageView.setImageResource(item.getImageSouce());
            tvCountry.setText(item.getTitle());
            tvCapital.setText(item.getContent());

            return view;
        }
    }
}
