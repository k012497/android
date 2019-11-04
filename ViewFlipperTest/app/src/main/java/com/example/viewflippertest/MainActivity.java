package com.example.viewflippertest;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    Button btnStart, btnStop;
    ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        viewFlipper = findViewById(R.id.viewFlipper);

//        btnPrev.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewFlipper.showPrevious();
//            }
//        });
//
//        btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewFlipper.showNext();
//            }
//        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.startFlipping();
                viewFlipper.setFlipInterval(500);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.stopFlipping();
            }
        });

    }
}