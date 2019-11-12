package com.example.drawingtest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    final static int LINE = 1, CIRCLE = 2, RECTANGLE = 3;
    static int curShape = LINE;
    static int curColor = Color.DKGRAY;

    // 도형을 저장할 리스트
    static List<MyShape> myshape = new ArrayList<MyShape>();
    static boolean isFinish = false; // ACTION_UP 여부

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
        setTitle("그림판");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, "선");
        menu.add(0, 2, 0, "원");
        menu.add(0, 3, 0, "사각형");
        SubMenu sMenu = menu.addSubMenu("CHANGE COLOR");
        sMenu.add(0, 4, 0, "RED");
        sMenu.add(0, 5, 0, "GREEN");
        sMenu.add(0, 6, 0, "BLUE");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case 1:
                curShape = LINE; // 선
                return true;
            case 2:
                curShape = CIRCLE; // 원
                return true;
            case 3:
                curShape = RECTANGLE; // 사각형
                return true;
            case 4:
                curColor = Color.RED;
                return true;
            case 5:
                curColor = Color.GREEN;
                return true;
            case 6:
                curColor = Color.BLUE;
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private static class MyGraphicView extends View {
        int startX = -1, startY = -1, stopX = -1, stopY = -1;

        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startX = (int) event.getX();
                    startY = (int) event.getY();
                    isFinish = false;
                    break;
                case MotionEvent.ACTION_MOVE:
                    stopX = (int) event.getX();
                    stopY = (int) event.getY();
                    isFinish = false;
                    this.invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    // 마우스가 Up되면 최종 적으로 배열에 넣는다.
                    MyShape shape = new MyShape();

                    shape.shapeType = curShape;
                    shape.startX = startX;
                    shape.startY = startY;
                    shape.stopX = stopX;
                    shape.stopY = stopY;
                    shape.color = curColor;

                    myshape.add(shape);
                    isFinish = true;
                    this.invalidate();
                    break;
            }
            return true;
        }

        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(8);
            paint.setStyle(Paint.Style.STROKE);

            // 배열의 도형을 모두 그린다.
            for (int i = 0; i < myshape.size(); i++) {
                MyShape shape = myshape.get(i);
                paint.setColor(shape.color);
                switch (shape.shapeType) {
                    case LINE:
                        canvas.drawLine(shape.startX, shape.startY, shape.stopX,
                                shape.stopY, paint);
                        break;
                    case CIRCLE:
                        int radius = (int) Math.sqrt(Math.pow(shape.stopX
                                - shape.startX, 2)
                                + Math.pow(shape.stopY - shape.startY, 2));
                        canvas.drawCircle(shape.startX, shape.startY, radius, paint);
                        break;
                    case RECTANGLE:
                        Rect rect = new Rect(shape.startX, shape.startY,
                                shape.stopX, shape.stopY);
                        canvas.drawRect(rect, paint);
                        break;
                }
            }

            // 그림이 아직 진행 중이면 (= 화면을 터치 중이면) 현재 진행중인 도형을 그린다.
            if (isFinish == false) {

                paint.setColor(curColor);

                switch (curShape) {
                    case LINE:
                        canvas.drawLine(startX, startY, stopX, stopY, paint);
                        break;
                    case CIRCLE:
                        int radius = (int) Math.sqrt(Math.pow(stopX - startX, 2)
                                + Math.pow(stopY - startY, 2));
                        canvas.drawCircle(startX, startY, radius, paint);
                        break;
                    case RECTANGLE:
                        Rect rect = new Rect(startX, startY, stopX, stopY);
                        canvas.drawRect(rect, paint);
                        break;
                }
            }
        }

    }

    // 도형 클래스
    private static class MyShape {
        int shapeType; // 도형 종류
        int startX, startY, stopX, stopY; // 도형의 2점
        int color; // 도형 색상
    }

}

//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.view.menu.SubMenuBuilder;
//
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.os.Bundle;
//import android.util.AttributeSet;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.MotionEvent;
//import android.view.SubMenu;
//import android.view.View;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MainActivity extends AppCompatActivity {
//
//    final int LINE = 1, CIRCLE = 2, RECTANGLE = 3;
//    int curShape = 1;
//    Paint paint = new Paint();
//
//    // 도형을 저장할 리스트
//    static List<MyShape> myShape = new ArrayList<MyShape>();
//    //ACTION_UP 여부
//    static boolean isFinish = false;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(new MyGraphicView(this));
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        menu.add(0, 1, 0, "Line");
//        menu.add(0, 2, 0, "Circle");
//        menu.add(0, 3, 0, "Rectangle");
//
//        SubMenu submenu = menu.addSubMenu("Change Color");
//        submenu.add(0, 4,0,"to RED");
//        submenu.add(0, 5,0,"to GREEN");
//        submenu.add(0, 6,0,"to BLUE");
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case 1:
//                curShape = LINE;
//                break;
//
//            case 2:
//                curShape = CIRCLE;
//                break;
//
//            case 3:
//                curShape = RECTANGLE;
//                break;
//
//            case 4:
//                paint.setColor(Color.RED);
//                break;
//
//            case 5:
//                paint.setColor(Color.GREEN);
//                break;
//
//            case 6:
//                paint.setColor(Color.BLUE);
//                break;
//        }
//
//        return true;
//    }
//
//    private class MyShape{
//        int startX, startY, stopX, stopY;
//        int shapeType;
//        int color;
//
//    }
//
//    private class MyGraphicView extends View {
//        int startX = -1, startY = -1, stopX = -1,stopY = -1;
//
//        public MyGraphicView(Context context) {
//            super(context);
//        }
//
////        public MyGraphicView(Context context, @Nullable AttributeSet attrs) {
////            super(context, attrs);
////        }
//
//        @Override
//        public boolean onTouchEvent(MotionEvent event) {
//            switch (event.getAction()){
//                case MotionEvent.ACTION_DOWN:
//                    startX = (int) event.getX();
//                    startY = (int) event.getY();
//                    isFinish = false;
//                    break;
//
//                case MotionEvent.ACTION_MOVE:
//                    stopX = (int) event.getX();
//                    stopY = (int) event.getY();
//                    isFinish = false;
//                    this.invalidate();
//                    break;
//
//                case MotionEvent.ACTION_UP:
//                    // 배열에 넣음
//                    MyShape shape = new MyShape();
//
//                    shape.shapeType = curShape;
//                    shape.startX = startX;
//                    shape.startY = startY;
//                    shape.stopX = stopX;
//                    shape.stopY = stopY;
//                    shape.color = paint.getColor();
//
//                    myShape.add(shape);
//                    isFinish = true;
//                    this.invalidate();
//                    break;
//            }
//            return true;
//        }
//
//        @Override
//        protected void onDraw(Canvas canvas) {
//            super.onDraw(canvas);
//
//            paint.setAntiAlias(true);
//            paint.setStrokeWidth(8);
//            paint.setStyle(Paint.Style.STROKE);
//
//            for(int i = 0 ; i < myShape.size() ; i++){
////                Toast.makeText(MainActivity.this, String.valueOf(myShape.size()), Toast.LENGTH_SHORT).show();
//                MyShape shape =  myShape.get(i);
//                paint.setColor(shape.color);
//
//                switch (shape.shapeType){
//                    case LINE:
//                        canvas.drawLine(startX, startY, stopX, stopY, paint);
//                        break;
//                    case CIRCLE:
//                        int radius = (int) Math.sqrt(Math.pow(stopX - startX, 2) + Math.pow(stopY - startY, 2));
//                        canvas.drawCircle(startX, startY, radius, paint);
//                        break;
//                    case RECTANGLE:
//                        canvas.drawRect(startX, startY, stopX, stopY, paint);
//                        break;
//                }
//            }
//
//            if(!isFinish){
//                switch (curShape){
//                    case LINE:
//                        canvas.drawLine(startX, startY, stopX, stopY, paint);
//                        break;
//                    case CIRCLE:
//                        int radius = (int) Math.sqrt(Math.pow(stopX - startX, 2) + Math.pow(stopY - startY, 2));
//                        canvas.drawCircle(startX, startY, radius, paint);
//                        break;
//                    case RECTANGLE:
//                        canvas.drawRect(startX, startY, stopX, stopY, paint);
//                        break;
//                }
//            }
//        }
//    }
//}
