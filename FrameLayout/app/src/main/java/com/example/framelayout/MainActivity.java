package com.example.framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button[] button = new Button[3];
    int[] valueID = {R.id.btn1, R.id.btn2, R.id.btn3};

    LinearLayout layout1, layout2, layout3;
    Button btnToast1, btnToast2, btnToast3;
    EditText edt1, edt2, edt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout1 = findViewById(R.id.layout1);
        layout2 = findViewById(R.id.layout2);
        layout3 = findViewById(R.id.layout3);

        btnToast1 = findViewById(R.id.btnToast1);
        btnToast2 = findViewById(R.id.btnToast2);
        btnToast3 = findViewById(R.id.btnToast3);

        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        edt3 = findViewById(R.id.edt3);

        for(int i = 0; i < valueID.length ; i++){
            final int index = i;
            button[index] = findViewById(valueID[i]);
            button[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (index){
                        case 0:
                            layout1.setVisibility(View.VISIBLE);
                            layout2.setVisibility(View.INVISIBLE);
                            layout3.setVisibility(View.INVISIBLE);break;

                        case 1:
                            layout1.setVisibility(View.INVISIBLE);
                            layout2.setVisibility(View.VISIBLE);
                            layout3.setVisibility(View.INVISIBLE);break;
                        case 2:
                            layout1.setVisibility(View.INVISIBLE);
                            layout2.setVisibility(View.INVISIBLE);
                            layout3.setVisibility(View.VISIBLE);break;
                        default:
                            break;
                    }
                }
            });

            btnToast1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), edt1.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });

            btnToast2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), edt2.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });

            btnToast3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), edt3.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
