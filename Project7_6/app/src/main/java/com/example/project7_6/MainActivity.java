package com.example.project7_6;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup rdoAnimal;
    Button btnShow;
    ImageView imageView;
    View dialogView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rdoAnimal = findViewById(R.id.rdoAnimal);
        btnShow = findViewById(R.id.btnShow);
        btnShow = findViewById(R.id.btnShow);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView = View.inflate(MainActivity.this, R.layout.animal_dialog,null);
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("ANIMAL");
                dialog.setView(dialogView);

                imageView = dialogView.findViewById(R.id.imageView);

                switch (rdoAnimal.getCheckedRadioButtonId()){
                    case R.id.rdoGorilla : imageView.setImageResource(R.drawable.gorilla); dialog.setTitle("GORILLA"); break;
                    case R.id.rdoRabbit : imageView.setImageResource(R.drawable.rabbit); dialog.setTitle("RABBIT"); break;
                    case R.id.rdoPanda : imageView.setImageResource(R.drawable.panda); dialog.setTitle("PANDA"); break;
                }

                dialog.setNegativeButton("close", null);
                dialog.show();
            }
        });

    }

}
