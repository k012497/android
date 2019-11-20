package com.example.singerdatabasetest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
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
    private Button btnAdd, btnReset, btnSearch, btnEdit, btnDelete, btnSort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("아이돌 그룹 ㅋㅅㅋ");

        edtName = findViewById(R.id.edtName);
        edtNumber = findViewById(R.id.edtNumber);
        edtNameData = findViewById(R.id.edtNameData);
        edtNumberData = findViewById(R.id.edtNumberData);
        btnAdd = findViewById(R.id.btnAdd);
        btnReset = findViewById(R.id.btnReset);
        btnSearch = findViewById(R.id.btnSearch);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);
        btnSort = findViewById(R.id.btnSort);

        myDBHelper = new MyDBHelper(this);

        btnAdd.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnSort.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
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
                btnSearch.callOnClick();
                break;

            case R.id.btnReset:
                sqLiteDatabase = myDBHelper.getWritableDatabase();
                myDBHelper.onUpgrade(sqLiteDatabase, 1, 2);
                // db를 닫는다
                sqLiteDatabase.close();
                btnSearch.callOnClick();
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

            case R.id.btnEdit:
                sqLiteDatabase = myDBHelper.getWritableDatabase();
                String group = edtName.getText().toString().trim();
                if(group.equals("")){
                    toastDisplay("값을 입력해주세요");
                }else if(isExist(group)){
                    isExist(edtName.getText().toString().trim());
                    str = "UPDATE groupTBL SET gNumber = " + edtNumber.getText().toString().trim() +
                            " WHERE gName = '" + group + "';";
                    sqLiteDatabase.execSQL(str);
                    toastDisplay("수정 완료 :)");
                }else{
                    toastDisplay("존재하지 않는 그룹명입니다.");
                    break;
                }
                sqLiteDatabase.close();
                btnSearch.callOnClick();
                break;

            case R.id.btnDelete:
                sqLiteDatabase = myDBHelper.getWritableDatabase();
                group = edtName.getText().toString().trim();

                if(group.equals("")){
                    toastDisplay("값을 입력해주세요");
                    break;
                }else {
                    if(!isExist(group)){
                        toastDisplay("존재하지 않는 그룹명입니다." + isExist(group));
                    }else{
                        sqLiteDatabase.execSQL("DELETE FROM groupTBL WHERE gName = '"
                                + group + "';");
                        toastDisplay("삭제 완료 :)");
                    }
                    sqLiteDatabase.close();
                }

                btnSearch.callOnClick();
                break;

            case R.id.btnSort:
                sqLiteDatabase = myDBHelper.getReadableDatabase();
                cursor = sqLiteDatabase.rawQuery("SELECT * FROM groupTBL ORDER BY gName;", null);

                strNames = "그룹이름" + "\r\n" + "--------" + "\r\n";
                strNumbers = "인원" + "\r\n" + "--------" + "\r\n";

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

    private boolean isExist(String gName) {
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM groupTBL WHERE gName = '" + gName + "';", null);

        if(cursor.getCount() == 0){
            cursor.close();
            return false;
        } else {
            cursor.close();
            return true;
        }
    }

    public void toastDisplay(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
