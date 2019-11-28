package com.example.neul7.mycalendartest;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

// 달력 한칸의 부분화면 이라고 생각할것!
public class MonthItemVIew extends AppCompatTextView {
    private MonthItem item;


    public MonthItemVIew(Context context) {
        super(context);
    }

    public MonthItemVIew(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setBackgroundColor(Color.WHITE);
    }

    public MonthItem getItem() {
        return item;
    }

    public void setItem(MonthItem item) {
        this.item = item;
        int day = item.getDayValue();
        if (day != 0) {
            setText(String.valueOf(day));
        } else {
            setText("");
        }
    }
}
