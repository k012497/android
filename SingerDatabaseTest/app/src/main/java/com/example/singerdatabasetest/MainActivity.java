package com.example.singerdatabasetest;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MyDBHelper myDBHelper;
    private SQLiteDatabase sqLiteDatabase;
    private EditText edtName, edtNumber;
    private Button btnAdd, btnReset, btnSearch, btnEdit, btnDelete, btnSort;

    private TextView tvName, tvCount;
    private String strName, strCount, group;
    private RecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<GroupItem> items = new ArrayList<GroupItem>();

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("아이돌 그룹 ㅋㅅㅋ");

        edtName = findViewById(R.id.edtName);
        edtNumber = findViewById(R.id.edtNumber);
        recyclerView = findViewById(R.id.recyclerView);

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

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);

        btnDelete.setEnabled(false);

        btnSearch.callOnClick();
    }

    private void selectAllItems(String sql) {
        items.clear();
        sqLiteDatabase = myDBHelper.getReadableDatabase();
        Cursor cursor; // record set
        cursor = sqLiteDatabase.rawQuery(sql, null);

        Log.d("selectAllItems", "1");
        while (cursor.moveToNext()) {
            strName = cursor.getString(0);
            strCount = cursor.getString(1);
            items.add(new GroupItem(strName, Integer.parseInt(strCount)));
        }

        cursor.close();
        sqLiteDatabase.close();
        Log.d("selectAllItems", String.valueOf(items.size()));
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
                recyclerView.removeAllViews();
                break;

            case R.id.btnSearch:
                recyclerView.removeAllViews();
                selectAllItems("SELECT * FROM groupTBL;");
                adapter.notifyDataSetChanged();
                break;

            case R.id.btnEdit:
                sqLiteDatabase = myDBHelper.getWritableDatabase();
                group = edtName.getText().toString().trim();
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
                recyclerView.removeAllViews();
                selectAllItems("SELECT * FROM groupTBL ORDER BY gName;");
                adapter.notifyDataSetChanged();

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

    private class RecyclerAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<CustomHolder>{

        @NonNull
        @Override
        public CustomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_item, parent, false);
            CustomHolder customHolderView = new CustomHolder(view);

            return customHolderView;
        }

        @Override
        public void onBindViewHolder(@NonNull CustomHolder holder, final int position) {
            tvName.setText(items.get(position).getName());
            tvCount.setText(String.valueOf(items.get(position).getHeadCount()));

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
                @Override
                public void onClick(View v) {
                    sqLiteDatabase = myDBHelper.getWritableDatabase();
                    group = edtName.getText().toString().trim();
                    if(group.equals("")){
                        toastDisplay("값을 입력해주세요");
                    }else if(isExist(group)){
                        isExist(edtName.getText().toString().trim());
                        String str = "UPDATE groupTBL SET gNumber = " + edtNumber.getText().toString().trim() +
                                " WHERE gName = '" + group + "';";
                        sqLiteDatabase.execSQL(str);
                        toastDisplay("수정 완료 :)");
                    }else{
                        toastDisplay("존재하지 않는 그룹명입니다.");
                    }
                    sqLiteDatabase.close();
                    btnSearch.callOnClick();
                }
            });

            tvName.setOnLongClickListener(new View.OnLongClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
                @Override
                public boolean onLongClick(View v) {
                    Log.d("longclick - name", tvName.getText().toString());
                    Log.d("longclick - position", String.valueOf(position));
                    Log.d("longclick - item", items.get(position).getName());
                    sqLiteDatabase = myDBHelper.getWritableDatabase();
                    String group = items.get(position).getName();

                    if(group.equals("")){
                        toastDisplay("값을 입력해주세요");
                        return false;
                    }else {
                        if(!isExist(group)){
                            toastDisplay("존재하지 않는 그룹명입니다." + isExist(group));
                        }else{
                            sqLiteDatabase.execSQL("DELETE FROM groupTBL WHERE gName = '"
                                    + group + "';");
                            items.remove(position);
                            toastDisplay(group + " 삭제 완료 :)");
                        }
                        sqLiteDatabase.close();
                    }

                    adapter.notifyItemRemoved(position);
                    return true;
                }
            });
        }

        @Override
        public int getItemCount() {
            int size = 0;
            Cursor cursor;
            sqLiteDatabase = myDBHelper.getReadableDatabase();
            cursor = sqLiteDatabase.rawQuery("SELECT count(*) FROM groupTBL;", null);
            while (cursor.moveToNext()) {
                size = cursor.getInt(0);
            }
            return size == items.size()? size : 0;
        }

    }

    private class CustomHolder extends RecyclerView.ViewHolder{

        public CustomHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvCount = itemView.findViewById(R.id.tvCount);
        }

    }
}
