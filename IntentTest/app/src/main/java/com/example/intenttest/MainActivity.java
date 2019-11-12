package com.example.intenttest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnOpen;
    EditText editText1, editText2;
    RadioGroup rdoGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpen = findViewById(R.id.btnOpen);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        rdoGroup = findViewById(R.id.rdoGroup);

        btnOpen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int selectedButton = rdoGroup.getCheckedRadioButtonId();

        // 명시적 인텐트 - 값을 전달하지 않음
        Intent intent = new Intent(getApplicationContext(), SubActivity.class);

        // 명시적 인텐트 - 값을 전달함
        intent.putExtra("number1", Integer.parseInt(editText1.getText().toString().trim()));
        intent.putExtra("number2", Integer.parseInt(editText2.getText().toString().trim()));

        switch (selectedButton){
            case R.id.btnAdd:
                intent.putExtra("operator", 1);
                break;

            case R.id.btnSub:
                intent.putExtra("operator", 2);
                break;

            case R.id.btnMul:
                intent.putExtra("operator", 3);
                break;

            case R.id.btnDiv:
                intent.putExtra("operator", 4);
                break;
        }
        startActivityForResult(intent, 1000);

    }

    // resultcode를 받았을 때 콜백
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent returnIntent) {
        super.onActivityResult(requestCode, resultCode, returnIntent);

        if (requestCode == 1000 && resultCode == 1001){
            int result = returnIntent.getIntExtra("result", 0);
            Toast.makeText(getApplicationContext(), "계산 결과 : " + result, Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(getApplicationContext(), "이상해요 ,, ", Toast.LENGTH_SHORT).show();
        }
    }
}
