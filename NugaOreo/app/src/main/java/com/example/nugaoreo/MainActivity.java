package com.example.nugaoreo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtMessage;
    Button btnShow, btnHome;
    RadioButton rdoRest, rdoMap;
    ImageView ivShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtMessage = findViewById(R.id.edtMessage);
        btnShow = findViewById(R.id.btnShow);
        btnHome = findViewById(R.id.btnHome);
        rdoRest = findViewById(R.id.rdoRest);
        rdoMap = findViewById(R.id.rdoMap);
        ivShow = findViewById(R.id.ivShow);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = edtMessage.getText().toString().trim();
                ToastDisplay(message);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "http://" + edtMessage.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(message));
                startActivity(intent);
            }
        });

        rdoRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivShow.setImageResource(R.drawable.restaurant);
            }
        });

        rdoMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivShow.setImageResource(R.drawable.map);
            }
        });


    }// end of onCreate

    public void ToastDisplay(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
