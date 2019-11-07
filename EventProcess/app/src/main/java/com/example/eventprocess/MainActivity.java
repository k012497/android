package com.example.eventprocess;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnList, btnOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnList = findViewById(R.id.btnList);
        btnOption = findViewById(R.id.btnOption);

        btnList.setOnClickListener(this);
        btnOption.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnList:
                ArrayList<String> list = new ArrayList<String>();
                list.add("apple");
                list.add("strawberry");
                list.add("orange");
                list.add("watermelon");
                final String[] items = list.toArray(new String[list.size()]);

                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("목록 상자");
                dialog.setItems(items, null);
                dialog.show();
                break;

            case R.id.btnOption:
                showDialogOption();
                break;
        }
    }

    private void showDialogOption() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("apple");
        list.add("strawberry");
        list.add("orange");
        list.add("watermelon");
        final String[] items = list.toArray(new String[list.size()]);

        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("목록 상자");
        dialog.setSingleChoiceItems(items, -1,null);
        dialog.show();
    }
}