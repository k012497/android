package com.example.intenttest2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnOpen;
    RadioGroup rdoGroup;
    RadioButton rdo2nd, rdo3rd;
    int selectedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpen = findViewById(R.id.btnOpen);
        rdoGroup = findViewById(R.id.rdoGroup);
        rdo2nd = findViewById(R.id.rdo2nd);
        rdo3rd = findViewById(R.id.rdo3rd);

        btnOpen.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        selectedButton = rdoGroup.getCheckedRadioButtonId();

        switch (selectedButton){
            case R.id.rdo2nd :
                Toast.makeText(this, "222", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                break;
            case R.id.rdo3rd :
                Toast.makeText(this, "333", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
