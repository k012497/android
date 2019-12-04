package com.example.bundletest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final static String TAG = "MainActivity";
    EditText edtName;
    Button btnSave;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate");
        edtName = findViewById(R.id.edtName);
        btnSave = findViewById(R.id.btnSave);

        Bundle bundle = new Bundle();

        bundle.putString("name", "croucs");
        bundle.putInt("age", 233);
        ArrayList<Integer> array = new ArrayList<>();
        array.add(1);
        array.add(2);
        array.add(3);
        //번드ㄹ택배 객체ArrayList 짐 넣고
        bundle.putIntegerArrayList("array", array);

        Intent intent = new Intent(MainActivity.this, SubActivity.class);
        intent.putExtra("data", array);
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    // 화면이 불러졌을 때
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState");
        savedInstanceState.getString("name");
    }

    // 액티비티가 삭제되었을때(방향 전환, 메모리 풀), 매개변수 하나짜리 가져오기!
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState"); // pause, stop, destroy중 언제 불러지는지 테스트중
        String name = edtName.getText().toString();
        outState.putString("name", name);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}
