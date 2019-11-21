package com.example.musicplayerproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 0;
    private static final String DB_NAME = "musicDB";

    public MyDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String str = "CREATE TABLE musicTBL (" +
                "songNo Integer PRIMARY KEY, title CHAR(20), singer CHAR(20), genre CHAR(20), countClicked Integer, image Integer);";
        db.execSQL(str);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS musicTBL");
        onCreate(db);
    }
}
