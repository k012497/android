package com.example.singerdatabasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "groupDB"; // .db 써도 되고 안 써도 되고
    private static final int VERSION = 1; // .db 써도 되고 안 써도 되고


    // DB 생성
    public MyDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    // Table 생성
    @Override
    public void onCreate(SQLiteDatabase db) {
        String str = "CREATE TABLE groupTBL (" +
                "gName CHAR(20) PRIMARY KEY, gNumber Integer);";
        db.execSQL(str);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS groupTBL");
        onCreate(db); // 다시 생성
    }
}
