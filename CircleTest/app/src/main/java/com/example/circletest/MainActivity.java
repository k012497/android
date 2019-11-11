package com.example.circletest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    final static int LINE = 1, CIRCLE = 2;
    public int curShape = LINE; // 기본값을 라인으로 설정

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
    }

    //옵션 메뉴 등록
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, "선 그리기");
        menu.add(0, 2, 0, "원 그리기");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 1: curShape = LINE; break;
            case 2: curShape = CIRCLE; break;
        }
        return true;
    }

    private class MyGraphicView extends View {

        public float startX = -1, startY = -1, stopX = -1, stopY = -1;

        public MyGraphicView(Context context) {
            super(context);
        }

        public MyGraphicView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch(event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    startX = event.getX();
                    startY = event.getY();
                    break;
                case MotionEvent.ACTION_UP:
                    stopX = event.getX();
                    stopY = event.getY();
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE: break;
                case MotionEvent.ACTION_CANCEL: break;
            }
            return true;
        }

        //선 or 원 선택 정보(선이 디폴트) & 터치하는 좌표값 정보가 필요
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            // paint 속성만 정의
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setColor(Color.RED);

            //라인인지 원인지 선택
            switch (curShape){
                case LINE :
                    canvas.drawLine(startX, startX, stopX, stopY, paint);
                    break;
                case CIRCLE :
                    int radius = (int) Math.sqrt(Math.pow(stopX - startX, 2) + Math.pow(stopY - startY, 2));
                    canvas.drawCircle(startX, startX, radius, paint);
                    break;
            }

        }
    }
}
