package com.example.contextmenutest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    Button btnContextMenu, btnContextMenu2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.linearLayout);
        btnContextMenu = findViewById(R.id.btnContextMenu);
        btnContextMenu2 = findViewById(R.id.btnContextMenu2);

        // 1. 해당된 위젯을 선택해서 등록
        registerForContextMenu(btnContextMenu);
        registerForContextMenu(btnContextMenu2);


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = getMenuInflater();


        if (v == btnContextMenu){
            menuInflater.inflate(R.menu.context_menu, menu);
        }else if (v == btnContextMenu2){
            menuInflater.inflate(R.menu.context_menu, menu);
        }

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.contextRed:
                linearLayout.setBackgroundColor(Color.RED);
                break;

            case R.id.contextGreen:
                linearLayout.setBackgroundColor(Color.GREEN);
                break;

            case R.id.contextBlue:
                linearLayout.setBackgroundColor(Color.BLUE);
                break;
        }

        return true;
    }
}
