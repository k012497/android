package com.example.musicplayerproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MusicItemDAO extends MainActivity {
    private SQLiteDatabase sqLiteDatabase;
    private Context context;
    private MyDBHelper myDBHelper = null;

    public MusicItemDAO(Context context) {
        this.context = context;
        this.myDBHelper = new MyDBHelper(context);
    }

    public void insert(String title, String singer, String genre, String albumArt){

        sqLiteDatabase = myDBHelper.getWritableDatabase();
        String str = "INSERT INTO musicTBL values('" +
                title + "', '" +
                singer + "', '" + genre + "', 0, '" + albumArt +"');";
        sqLiteDatabase.execSQL(str);
        toastDisplay(title + " is added!");
        sqLiteDatabase.close();
        Log.d("MainActivity", title+"정보 입력완료 ");
        items.add(new MusicItemDTO(title, singer, genre, 0, albumArt));
    }

    public ArrayList<MusicItemDTO> selectAll(){
        items.clear();

        sqLiteDatabase = myDBHelper.getReadableDatabase();
        Cursor cursor; // record set
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM musicTBL;", null);

        String strTitle, strSinger, strGenre, strAlbum;
        int count;
        Log.d("selectAllItems", "1");
        while (cursor.moveToNext()) {
            strTitle = cursor.getString(0);
            strSinger = cursor.getString(1);
            strGenre = cursor.getString(2);
            count = cursor.getInt(3);
            strAlbum = cursor.getString(4);

            items.add(new MusicItemDTO(strTitle, strSinger, strGenre, count, strAlbum));
        }

        cursor.close();
        sqLiteDatabase.close();

        return items;
    }

    public ArrayList<MusicItemDTO> selectAllByCount(){
        items.clear();

        sqLiteDatabase = myDBHelper.getReadableDatabase();
        Cursor cursor; // record set
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM musicTBL ORDER BY countClicked DESC;", null);

        String strTitle, strSinger, strGenre, strAlbum;
        int count;
        Log.d("selectAllItems", "1");
        while (cursor.moveToNext()) {
            strTitle = cursor.getString(0);
            strSinger = cursor.getString(1);
            strGenre = cursor.getString(2);
            count = cursor.getInt(3);
            strAlbum = cursor.getString(4);

            items.add(new MusicItemDTO(strTitle, strSinger, strGenre, count, strAlbum));
        }

        cursor.close();
        sqLiteDatabase.close();

        return items;
    }

    public ArrayList<MusicItemDTO> selectAllByTitle(){
        items.clear();

        sqLiteDatabase = myDBHelper.getReadableDatabase();
        Cursor cursor; // record set
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM musicTBL ORDER BY title;", null);

        String strTitle, strSinger, strGenre, strAlbum;
        int count;
        Log.d("selectAllItems", "1");
        while (cursor.moveToNext()) {
            strTitle = cursor.getString(0);
            strSinger = cursor.getString(1);
            strGenre = cursor.getString(2);
            count = cursor.getInt(3);
            strAlbum = cursor.getString(4);

            items.add(new MusicItemDTO(strTitle, strSinger, strGenre, count, strAlbum));
        }

        cursor.close();
        sqLiteDatabase.close();

        return items;
    }

    public MusicItemDTO isExist(String title) {
        sqLiteDatabase = myDBHelper.getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM musicTBL WHERE title = '" + title + "';", null);

        String strTitle, strSinger, strGenre, strAlbum;
        MusicItemDTO music = null;
        int count;

        if(cursor.getCount() != 0){
            while (cursor.moveToNext()) {
                strTitle = cursor.getString(0);
                strSinger = cursor.getString(1);
                strGenre = cursor.getString(2);
                count = cursor.getInt(3);
                strAlbum = cursor.getString(4);

                music = new MusicItemDTO(strTitle, strSinger, strGenre, count, strAlbum);
            }
        }

        cursor.close();
        sqLiteDatabase.close();
        return music;
    }

    public void delete(String title){
        sqLiteDatabase = myDBHelper.getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM musicTBL WHERE title = '" + title + "';");
        toastDisplay("삭제 완료 :)");
        sqLiteDatabase.close();
    }

    public void updateCount(String title, int count){
        sqLiteDatabase = myDBHelper.getWritableDatabase();
        String str = "UPDATE musicTBL SET countClicked = " + count + " WHERE title = '" + title + "';";
        sqLiteDatabase.execSQL(str);
        sqLiteDatabase.close();
    }


    public void toastDisplay(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }



}
