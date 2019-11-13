package com.example.imagerating2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {
    final static int SIZE = 9;

    TextView[] tv = new TextView[SIZE];
    int[] tvId = {R.id.tv01, R.id.tv02, R.id.tv03, R.id.tv04, R.id.tv05, R.id.tv06, R.id.tv07, R.id.tv08, R.id.tv09};
    RatingBar[] rBar = new RatingBar[SIZE];
    int[] rBarId = {R.id.rBar01, R.id.rBar02, R.id.rBar03, R.id.rBar04, R.id.rBar05, R.id.rBar06, R.id.rBar07, R.id.rBar08, R.id.rBar09};

    int[] count = new int[SIZE];
    int rank1Id = 0;
    String[] imageName = new String[SIZE];
    String rank1Name = "";

    Button btnBack;
    ImageView ivRank1;
    TextView tvRank1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        btnBack = findViewById(R.id.btnBack);
        ivRank1 = findViewById(R.id.ivRank1);
        tvRank1 = findViewById(R.id.tvRank1);

        Intent intent = getIntent();
        count = intent.getIntArrayExtra("count");
        imageName = intent.getStringArrayExtra("imageName");
        rank1Id = intent.getIntExtra("rank1Id", 0);
        rank1Name = intent.getStringExtra("rank1Name");

        ivRank1.setImageResource(rank1Id);
        tvRank1.setText(rank1Name);

        for(int i = 0 ; i < SIZE ; i++){
            tv[i] = findViewById(tvId[i]);
            tv[i].setText(imageName[i]);
            rBar[i] = findViewById(rBarId[i]);
            rBar[i].setRating(count[i]);
        }

        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBack:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                setResult(RESULT_OK);
                startActivity(intent);
                finish();
                break;
        }
    }
}
