package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnSub, btnMul, btnDiv, btnRem;
    TextView textView;
    EditText edt1, edt2;
    double result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        btnRem = findViewById(R.id.btnRem);
        textView = findViewById(R.id.textView);
        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt1.getText().toString().isEmpty() || edt2.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "숫자를 입력해주세요", Toast.LENGTH_LONG).show();
                else {
                    result = Double.parseDouble(edt1.getText().toString()) +
                            Double.parseDouble(edt2.getText().toString());
                    textView.setText("계산결과 = " + result);
                }
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt1.getText().toString().isEmpty() || edt2.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "숫자를 입력해주세요", Toast.LENGTH_LONG).show();
                else {
                    result = Double.parseDouble(edt1.getText().toString()) -
                            Double.parseDouble(edt2.getText().toString());
                    textView.setText("계산결과 = " + result);
                }

            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt1.getText().toString().isEmpty() || edt2.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "숫자를 입력해주세요", Toast.LENGTH_LONG).show();
                else {
                    result = Double.parseDouble(edt1.getText().toString()) *
                            Double.parseDouble(edt2.getText().toString());
                    textView.setText("계산결과 = " + result);
                }

            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt1.getText().toString().isEmpty() || edt2.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "숫자를 입력해주세요", Toast.LENGTH_LONG).show();
                else if(edt2.getText().toString().equals("0")) {
                    Toast.makeText(getApplicationContext(), "0으로 나눌 수 없습니다.", Toast.LENGTH_LONG).show();
                } else {
                        result = Double.parseDouble(edt1.getText().toString()) /
                                Double.parseDouble(edt2.getText().toString());
                        textView.setText("계산결과 = " + Math.round(result*100)/100.0);
                }
            }
        });

        btnRem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt1.getText().toString().isEmpty() || edt2.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "숫자를 입력해주세요", Toast.LENGTH_LONG).show();
                else {
                    result = Double.parseDouble(edt1.getText().toString()) %
                            Double.parseDouble(edt2.getText().toString());
                    textView.setText("계산결과 = " + result);
                }
            }
        });

    }
}
