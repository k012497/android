package com.example.musicplayerproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DB_NAME = "musicDB";

    public MyDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String str = "CREATE TABLE musicTBL (" +
                "id CHAR(30) PRIMARY KEY, title CHAR(60), singer CHAR(20), duration Long, albumArt CHAR(30), path CHAR(60));";
        db.execSQL(str);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS musicTBL");
        onCreate(db);
        Log.d("drop", "완");
    }
}
