package com.example.mycalendartest2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private final static String TAG = "MainActivity";

    GridView gvCalendar;
    Button btnPrevious, btnNext;
    TextView tvYearMonth, tvDateSelected;
    EditText edtEvent;
    private CalendarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("생일 축하 달력 🎊");

        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
        tvYearMonth = findViewById(R.id.tvYearMonth);
        gvCalendar = findViewById(R.id.gvCalendar);

        adapter = new CalendarAdapter(this);
        gvCalendar.setAdapter(adapter);

        setYearMonth();

        btnNext.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);
        gvCalendar.setOnItemClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPrevious:
                adapter.setPreviousMonth();
                adapter.notifyDataSetChanged();
                setYearMonth();
                break;
            case R.id.btnNext:
                adapter.setNextMonth();;
                adapter.notifyDataSetChanged();
                setYearMonth();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        MonthItem item = adapter.items[position];
        int day = item.getDayValue();
        if(day == 0) return;
        String currentDate = adapter.currentYear+"년 " + (adapter.currentMonth+1) + "월 " + day + "일";

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_event_dialog, null);
        final EditText edtEvent = view.findViewById(R.id.edtEvent);
        TextView tvDateSelected = view.findViewById(R.id.tvDateSelected);
        tvDateSelected.setText(currentDate);


        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("생일 추가하기");
        dialog.setView(view);
        dialog.setPositiveButton("등록", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                adapter.items[position].setEvent(edtEvent.getText().toString());
                adapter.items[position].setMark(true);
                adapter.notifyDataSetChanged();
            }
        });
        dialog.setNegativeButton("취소", null);
        dialog.show();

        Log.d(TAG, currentDate);
    }

    private void setYearMonth() {
        String yearMonth = adapter.currentYear+"년 " + (adapter.currentMonth+1) + "월";
        tvYearMonth.setText(yearMonth);
    }
}
