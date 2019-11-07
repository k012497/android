package com.example.mydiary;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePicker.OnDateChangedListener, View.OnClickListener {

    Button button;
    DatePicker datePicker;
    EditText editText;
    String fileName;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        datePicker = findViewById(R.id.datePicker);
        editText = findViewById(R.id.editText);

        // 현재 날짜로 세팅
        currentDateSetting();

        // 날짜 선택 시 이벤트 처리
        datePicker.setOnDateChangedListener(this);

        // 일기 저장 이벤트
        button.setOnClickListener(this);
    }

    private void currentDateSetting() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        datePicker.init(year, month - 1, day, null);
        onDateChanged(datePicker, year, month - 1, day);
    }

    // 날짜 선택 시
    @Override
    public void onDateChanged(DatePicker view, int year, int month, int day) {
        try {
            fileName = String.valueOf(year) + "_" + String.valueOf(month) + "_" + String.valueOf(day) + ".txt";

            FileInputStream fis = null;
            fis = openFileInput(fileName);
            byte[] diaryData = new byte[fis.available()];
            fis.read(diaryData);
            editText.setText(new String(diaryData));
            button.setText("edit");
        } catch (Exception e) {
            toastDisplay("no data on that day :(");
            button.setText("save");
            editText.setText("");
            editText.setHint("일기를 적어주세요");
            e.printStackTrace();
        }

    }

    // 수정/저장 버튼 클릭 시
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                saveDiary(); break;
        }
    }

    private void saveDiary() {
        try {
            FileOutputStream fos  = openFileOutput(fileName, Context.MODE_PRIVATE);
            String diaryData = editText.getText().toString();
            // 내용이 없을 경우는 저장하지 않음
            if(diaryData.trim().length() >= 1){
                fos.write(diaryData.getBytes());
                toastDisplay(fileName + " saved :)");
            }else{
                toastDisplay("content is empty !");
            }
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void toastDisplay(String string) {
        Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
    }
}
