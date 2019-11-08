package com.example.mydiary2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.annotation.RequiresApi;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePicker.OnDateChangedListener, View.OnClickListener {

    Button btnEdit, btnSave, btnExit, btnDelete;
    DatePicker datePicker;
    EditText editText;
    String fileName;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEdit = findViewById(R.id.btnEdit);
        btnSave = findViewById(R.id.btnSave);
        datePicker = findViewById(R.id.datePicker);
        editText = findViewById(R.id.editText);
        btnExit = findViewById(R.id.btnExit);
        btnDelete = findViewById(R.id.btnDelete);

        // 현재 날짜로 세팅
        currentDateSetting();

        // 날짜 선택 시 이벤트 처리
        datePicker.setOnDateChangedListener(this);

        // 일기 저장 이벤트
        btnEdit.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void currentDateSetting() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        datePicker.init(year, month -1, day, null);
        onDateChanged(datePicker, year, month -1, day);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEdit: case R.id.btnSave:
                saveDiary();
                break;
            case R.id.btnDelete:
                toastDisplay("try to delete");
                deleteDiary();
                break;
        }
    }

    private void deleteDiary() {
        File file = new File(getFilesDir().getAbsolutePath() + "/" + fileName);
        if (file.exists()) {
            file.delete();
            toastDisplay(fileName + "삭제 완료");
        } else {
            toastDisplay("삭제 실패 ");
        }
    }

    public void saveDiary(){
        try {
            // 텍스트 내용을 파일로 저장하기
            FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
            String string = editText.getText().toString().trim();
            if(string.length() >= 1){
                fos.write(string.getBytes());
                toastDisplay("저장이 완료되었습니다! " + fileName);
            } else {
                toastDisplay("내용을 입력하세요");
            }
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 날짜 선택 시
    @Override
    public void onDateChanged(DatePicker view, int year, int month, int day) {
        try {
            // 저장된 파일 내용 불러오기
            fileName = String.valueOf(year) + "_" + String.valueOf(month + 1) + "_" + String.valueOf(day) + ".txt";
            FileInputStream fis = openFileInput(fileName);
            byte[] data = new byte[fis.available()];
            fis.read(data);

            editText.setText(new String(data));

            // 저장 버튼 비활성화
            btnSave.setEnabled(false);
            btnEdit.setEnabled(true);

        } catch (Exception e) {
            // 저장된 파일이 없을 경우
            editText.setText("");
            editText.setHint("no data on that day! :(");

            // 수정 버튼 비활성화
            btnSave.setEnabled(true);
            btnEdit.setEnabled(false);

            e.printStackTrace();
        }
    }

    private void toastDisplay(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

}

