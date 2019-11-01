package com.example.calculator5_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edt1, edt2;
    Button btnAdd, btnSub, btnMul, btnDiv;
    TextView textView;

    Button[] bntNum = new Button[10];
    Integer[] valueID = {R.id.num0, R.id.num1, R.id.num2, R.id.num3, R.id.num4, R.id.num5,
    R.id.num6, R.id.num7, R.id.num8, R.id.num9};

    int i;
    String num1, num2;
    Integer result1;
    Double result2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        textView = findViewById(R.id.textView);
        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt1.getText().toString().equals("") || edt2.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "숫자를 입력하세요", Toast.LENGTH_LONG).show();
                    return;
                }
                result1 = Integer.parseInt(num1) + Integer.parseInt(num2);
                textView.setText("계산결과 : " + result1.toString());
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt1.getText().toString().equals("") || edt2.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "숫자를 입력하세요", Toast.LENGTH_LONG).show();
                    return;
                }
                result1 = Integer.parseInt(num1) - Integer.parseInt(num2);
                textView.setText("계산결과 : " + result1.toString());
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt1.getText().toString().equals("") || edt2.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "숫자를 입력하세요", Toast.LENGTH_LONG).show();
                    return;
                }
                result1 = Integer.parseInt(num1) * Integer.parseInt(num2);
                textView.setText("계산결과 : " + result1.toString());
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt1.getText().toString().equals("") || edt2.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "숫자를 입력하세요", Toast.LENGTH_LONG).show();
                    return;
                }
                result2 = Double.parseDouble(num1) / Double.parseDouble(num2);
                textView.setText("계산결과 : " + result2.toString());
            }
        });


        for (i = 0 ; i < valueID.length ; i++){
            bntNum[i] = findViewById(valueID[i]);
        }

        // 숫자 버튼 10개에 대해서 클릭이벤트 처리
        for (i = 0; i < valueID.length; i++) {
            final int index = i;

            bntNum[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 포커스가 되어 있는 에디트텍스트에 숫자 추가
                    if (edt1.isFocused() == true) {
                        num1 = edt1.getText().toString()
                                + bntNum[index].getText().toString();
                        edt1.setText(num1);
                    } else if (edt2.isFocused() == true) {
                        num2 = edt2.getText().toString()
                                + bntNum[index].getText().toString();
                        edt2.setText(num2);
                    } else {
                        Toast.makeText(getApplicationContext(), "입력 칸을 선택하세요", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
