package com.example.spinnertest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    ImageView imageView;
    TextView tvTitle;

    final String[] items = { "쿵푸팬더", "짱구는 못말려", "아저씨", "아바타", "대부", "국가대표",
            "토이스토리3", "마당을 나온 암탉", "죽은 시인의 사회", "서유기" };

    final Integer[] posterID = { R.drawable.mov21, R.drawable.mov22,
            R.drawable.mov23, R.drawable.mov24, R.drawable.mov25,
            R.drawable.mov26, R.drawable.mov27, R.drawable.mov28,
            R.drawable.mov29, R.drawable.mov30 };


    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        imageView = findViewById(R.id.imageView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>();
        spinner.setAdapter(adapter);


    }
    public class ArrayAdapter<String> extends BaseAdapter {



        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public Object getItem(int position) {
            return items[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.spinner_layout, null);

            tvTitle = view.findViewById(R.id.tvTitle);
            tvTitle.setText(items[position]);

            tvTitle.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    imageView.setImageResource(posterID[position]);
                    return false;
                }
            });

            return view;
        }
    }
}
