package com.example.neul7.mycalendartest;

import android.content.Context;
import android.graphics.Color;
import android.text.format.Time;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.Calendar;
import java.util.jar.Attributes;


/*
*         // 아직 분석안됐따...
        selectedPosition = -1;
*
* */


public class MonthAdapter extends BaseAdapter {
    public Context mContext;
    public MonthItem[] items; // ArrayList<MonthItem> <= 42ea
    public Calendar mCalendar;
    public int firstDay; // 예: 11월 1일 (금요일==>5)
    public int mStartDay; // Calendar.SUNDAY 현재 달력 시작 위치 요일(우리나라는 일요일)
    public int startDay; // Time...


    public int currentYear; // 현재 달력 년도
    public int currentMonth; // 현재 달력 월
    public int lastDay; // 현재 월의 마지막 날짜

    public int selectedPosition = -1; // 이건 뭘깡

    public MonthAdapter() {
    }

    public MonthAdapter(Context mContext) {
        this.mContext = mContext;
        // 달력을 계산할 내용으로 셋팅한다.
        init();
    }

//    public MonthAdapter(Context mContext, Attributes attributes) {
//        this.mContext = mContext;
//        init();
//    }

    private void init() {
        items = new MonthItem[42];
        // 현재 년,월,일,시간을 가져옴
        mCalendar = Calendar.getInstance();
        // 11월, 1~30일, 1일 => 몇요일(금)
        // 달력의 시작점. 우리나라는 일요일
        // 년,월,일, 월의 마지말 날짜(윤년ㅇㅋ), 1일의 요일, 달력 시작 요일(월,일,토 중 택일)
        recalculate();
        // MonthItem 배열에 넣어줘야함
        resetDayNumbers();
    }

    @Override
    public int getCount() {
        return 42;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MonthItemVIew itemView = null;
        if (itemView == null) {
            itemView = new MonthItemVIew(mContext);
        } else {
            itemView = (MonthItemVIew) convertView;
        }
        // 뷰 위젯에 속성을 부여하지 않아서 속성을 부여함..
        GridView.LayoutParams params = new GridView.LayoutParams(
                GridView.LayoutParams.MATCH_PARENT, 120
        );
        itemView.setItem(items[position]);
        itemView.setLayoutParams(params);
        itemView.setPadding(2,2,2,2);
        itemView.setGravity(Gravity.LEFT);

        int columnIndex = position % 7;
        if (columnIndex == 0) {
            itemView.setTextColor(Color.RED);
        } else {
            itemView.setTextColor(Color.BLACK);
        }
        return itemView;
    }

    private void recalculate() {
        // 현재 월과 날짜 11.27 를 기준으로 해서 11월 1일로 세팅
        mCalendar.set(Calendar.DAY_OF_MONTH, 1);
        // 11월 1일이 무슨 요일인지 체크 ( 금요일==>5) (일요일==>0)
        int dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);
        firstDay = getFirstDay(dayOfWeek);

        // 현재 시스템에 있는 달력의 첫날이 무슨요일로 시작하는지?
        // ex) 일요일 시작, 토요일 시작, 월요일 시작
        // Time.SUNDAY
        mStartDay = mCalendar.getFirstDayOfWeek();
        // 현재 년도
        currentYear = mCalendar.get(Calendar.YEAR);
        // 현재 월
        currentMonth = mCalendar.get(Calendar.MONTH);
        // 현재 월의 마지막 날짜
        lastDay = getLastDayOfMonth(currentYear, currentMonth);
        // getFirstDay(dayOfWeek); 우리가 계산하는 방식???
        startDay = getFirstDayOfWeek();
        resetDayNumbers();

    }

    // 각자 나라에 맞게 고쳐서 쓰시옹~~~
    private int getFirstDay(int dayOfWeek) {
        int result = 0;
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                result = 0;
                break;
            case Calendar.MONDAY:
                result = 1;
                break;
            case Calendar.TUESDAY:
                result = 2;
                break;
            case Calendar.WEDNESDAY:
                result = 3;
                break;
            case Calendar.THURSDAY:
                result = 4;
                break;
            case Calendar.FRIDAY:
                result = 5;
                break;
            case Calendar.SATURDAY:
                result = 6;
                break;
        }
        return result;
    }

    private int getLastDayOfMonth(int currentYear, int currentMonth) {
        switch (currentMonth + 1) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;

            case 4:
            case 6:
            case 9:
            case 11:
                return 30;

            default:
                if ((currentYear % 4 == 0) && (currentYear % 100 != 0) || (currentYear % 400 == 0)) {
                    return 29;
                } else {
                    return 28;
                }
        }
    }

    private int getFirstDayOfWeek() {
        int startDay = Calendar.getInstance().getFirstDayOfWeek();
        switch (startDay) {
            case Calendar.SATURDAY:
                return Time.SATURDAY;
            case Calendar.MONDAY:
                return Time.MONDAY;
            case Calendar.SUNDAY:
                return Time.SUNDAY;
        }
        return 0;
    }

    private void resetDayNumbers() {
        for (int i = 0; i < 42; i++) {
            int dayNumber = (i + 1) - firstDay;
            if (dayNumber < 1 || dayNumber > lastDay) {
                dayNumber = 0;
            }
            items[i] = new MonthItem(dayNumber);
        }
    }

    // 이전달 버튼
    public void setPreviousMonth() {
        // 현재가 11월 이라면 10월로 변경!
        mCalendar.add(Calendar.MONTH, -1);
        recalculate();
        resetDayNumbers();
        // 아직 분석안됐따...
        selectedPosition = -1;
    }

    // 다음달 버튼
    public void setNextMonth() {
        // 현재가 11월 이라면 12월로 변경!
        mCalendar.add(Calendar.MONTH, 1);
        recalculate();
        resetDayNumbers();
        // 아직 분석안됐따...
        selectedPosition = -1;
    }


}
