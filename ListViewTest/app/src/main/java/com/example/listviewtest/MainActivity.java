package com.example.listviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener, AdapterView.OnItemLongClickListener {
    ListView listView;
    EditText edtData;
    Button btnAdd;

    ArrayAdapter<String> adapter;
    ArrayList<String> items = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputArrayData();
        edtData = findViewById(R.id.edtData);
        listView = findViewById(R.id.listView);
        btnAdd = findViewById(R.id.btnAdd);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, items); // xml 새로 정의하지 않고 미리 만들어진 레이아웃 사용
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        btnAdd.setOnClickListener(this);

    }

    private void inputArrayData() {
        // 학습용 ! 복잡하게 해보기
        String[] name = {"sojin", "minji", "soyoung","sojin", "minji", "soyoung","sojin", "minji", "soyoung","sojin", "minji", "soyoung"};
        for(String string : name){
            items.add(string);
        }
    }

    // adapterView = ?, view = 선택한 아이템, position = 선택한 위치 = ArrayList 속 위치, id = 확장형 ?
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        CheckedTextView textView = view.findViewById(android.R.id.text1);
        Log.d("MainActivity", "위치 "+position+", "+"데이터 "+items.get(position) + " / " + textView.getText().toString());
    }

    @Override
    public void onClick(View v) {
        items.add(edtData.getText().toString().trim());
        listView.invalidate();
        Toast.makeText(getApplicationContext(), "completed!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        final String data = items.get(position);
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("Are you sure to delete " + data + "?");
        alert.setNegativeButton("no", null);
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                items.remove(data);
                adapter.notifyDataSetInvalidated();
            }
        });

        alert.show();

        return true;
    }
}
