package com.example.version4_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    Switch start;
    Button btnQuit, btnReset;
    ImageView imageView;
    RadioButton rdo1, rdo2, rdo3;
    RadioGroup rdoGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.start);
        btnQuit = findViewById(R.id.btnQuit);
        btnReset = findViewById(R.id.btnReset);
        imageView = findViewById(R.id.imageView);
        rdo1 = findViewById(R.id.rdo1);
        rdo2 = findViewById(R.id.rdo2);
        rdo3 = findViewById(R.id.rdo3);
        rdoGroup = findViewById(R.id.rdoGroup);

        rdoGroup.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.INVISIBLE);

        start.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    rdoGroup.setVisibility(View.VISIBLE);
                    imageView.setVisibility(View.VISIBLE);
                }else{
                    rdoGroup.setVisibility(View.INVISIBLE);
                    imageView.setVisibility(View.INVISIBLE);

                }
            }
        });

        rdo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.lollipop);
            }
        });

        rdo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.marshmallow);
            }
        });

        rdo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.nuga);
            }
        });

        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.setChecked(false);
                rdo1.setChecked(false);
                rdo2.setChecked(false);
                rdo3.setChecked(false);
                imageView.setImageResource(R.drawable.ic_launcher_background);
            }
        });
    }
}
