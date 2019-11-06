package com.example.dialogtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.accessibilityservice.AccessibilityService;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnDialog, btnRadio, btnCheck;
    TextView tvAnimal;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDialog = findViewById(R.id.btnDialog);
        tvAnimal = findViewById(R.id.tvAnimal);
        btnRadio = findViewById(R.id.btnRadio);
        btnCheck = findViewById(R.id.btnCheck);
        final String[] animalArray = new String[]{"gorilla", "rabbit", "puppy"};
        final boolean[] checkArray = new boolean[]{false, false, false};

        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("what's your FAVORITE?");
                dialog.setIcon(R.mipmap.heart);

                dialog.setItems(animalArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvAnimal.setText(animalArray[which]);
                    }
                });

                dialog.setNegativeButton("close", null);

                dialog.show();
            }
        });

        btnRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("what's your FAVORITE?");
                dialog.setIcon(R.mipmap.heart);

                dialog.setSingleChoiceItems(animalArray, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvAnimal.setText(animalArray[which]);
                    }
                });

                dialog.setNegativeButton("close", null);

                dialog.show();
            }
        });

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("what's your FAVORITE?");
                dialog.setIcon(R.mipmap.heart);

                dialog.setMultiChoiceItems(animalArray, checkArray, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                      tvAnimal.setText(animalArray[which]);

                        if(!flag){
                            tvAnimal.setText("");
                        }

                        String string = null;
                        string = tvAnimal.getText().toString();
                        if(checkArray[which] == true){
                            tvAnimal.setText(string + "\n" + animalArray[which]);
                        } else if (checkArray[which] == false) {
                            string = string.replace("\n" + animalArray[which].toString(), "");
                            tvAnimal.setText(string);
                        }

                        flag = true;
                    }
                });

                dialog.setNegativeButton("close", null);
                dialog.show();
            }
        });
    }
}
