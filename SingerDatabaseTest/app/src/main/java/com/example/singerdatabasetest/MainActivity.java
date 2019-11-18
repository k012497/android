package com.example.singerdatabasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MyDBHelper myDBHelper;
    private SQLiteDatabase sqLiteDatabase;
    private EditText edtName, edtNumber, edtNameData, edtNumberData;
    private Button btnAdd, btnReset, btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("아이돌 그룹 ㅋㅅ");

        edtName = findViewById(R.id.edtName);
        edtNumber = findViewById(R.id.edtNumber);
        edtNameData = findViewById(R.id.edtNameData);
        edtNumberData = findViewById(R.id.edtNumberData);
        btnAdd = findViewById(R.id.btnAdd);
        btnReset = findViewById(R.id.btnReset);
        btnSearch = findViewById(R.id.btnSearch);

        myDBHelper = new MyDBHelper(this);

        btnAdd.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAdd:
                sqLiteDatabase = myDBHelper.getWritableDatabase();
                String str = "INSERT INTO groupTBL values('" +
                        edtName.getText().toString().trim() + "', " +
                        edtNumber.getText().toString().trim() + ");";
                sqLiteDatabase.execSQL(str);
                Toast.makeText(getApplicationContext(), edtName.getText().toString()+"의 정보가 입력되었습니다 !", Toast.LENGTH_SHORT).show();
                sqLiteDatabase.close();
                Log.d("MainActivity", edtName.getText().toString()+"정보 입력완료 ");
                break;

            case R.id.btnReset:
                sqLiteDatabase = myDBHelper.getWritableDatabase();
                myDBHelper.onUpgrade(sqLiteDatabase, 1, 2);
                // db를 닫는다
                sqLiteDatabase.close();
                break;

            case R.id.btnSearch:
                sqLiteDatabase = myDBHelper.getReadableDatabase();
                Cursor cursor; // record set
                cursor = sqLiteDatabase.rawQuery("SELECT * FROM groupTBL;", null);

                String strNames = "그룹이름" + "\r\n" + "--------" + "\r\n";
                String strNumbers = "인원" + "\r\n" + "--------" + "\r\n";

                while (cursor.moveToNext()) {
                    strNames += cursor.getString(0) + "\r\n";
                    strNumbers += cursor.getString(1) + "\r\n";
                }

                edtNameData.setText(strNames);
                edtNumberData.setText(strNumbers);

                cursor.close();
                sqLiteDatabase.close();


                break;

        }
    }
}
