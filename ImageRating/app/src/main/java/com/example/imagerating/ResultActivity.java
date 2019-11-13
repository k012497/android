package com.example.imagerating;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnBack;
    TextView[] tv = new TextView[9];
    private Integer[] tvId = {R.id.tv01, R.id.tv02, R.id.tv03, R.id.tv04, R.id.tv05, R.id.tv06, R.id.tv07, R.id.tv08, R.id.tv09};
    RatingBar[] rBar = new RatingBar[9];
    private Integer[] rBarId = {R.id.rBar01, R.id.rBar02, R.id.rBar03, R.id.rBar04, R.id.rBar05, R.id.rBar06, R.id.rBar07, R.id.rBar08, R.id.rBar09};

    int[] count = new int[9];
    String[] ivName = new String[9];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();
        count = intent.getIntArrayExtra("count");
        ivName = intent.getStringArrayExtra("ivName");

        for(int i = 0 ; i < tvId.length ; i++){
            tv[i] = findViewById(tvId[i]);
            tv[0].setText(ivName[i]);
            rBar[i] = findViewById(rBarId[i]);
            rBar[i].setRating(count[i]);
        }

        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("message", "다 끝났어요");
        setResult(RESULT_OK, intent);
        finish();
    }
}
