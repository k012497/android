package com.example.geometryimagetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

// 문제점 : count 초기값 0으로주면 이벤트 안 먹힘

public class MainActivity extends AppCompatActivity {

    static int count = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));

    }

    private static class MyGraphicView extends View {
        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    if(count == 4){
                        count = 1;
                    } else {
                        count++;
                    }
                    break;
            }
            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Bitmap picture = BitmapFactory.decodeResource(getResources(),
                    R.drawable.sunny);

            int cenX = this.getWidth() / 2;
            int cenY = this.getHeight() / 2;
            int picX = (this.getWidth() - picture.getWidth()) / 2;
            int picY = (this.getHeight() - picture.getHeight()) / 2;

//            canvas.scale(1,1,0,0);
//            canvas.drawBitmap(picture, cenX, cenY, null);


            switch (count){
                case 1:
                    canvas.rotate(45, cenX, cenY);
                    canvas.drawBitmap(picture, picX, picY, null);
                    invalidate();
                    break;
                case 2:
                     canvas.translate(-150, 200);
                     canvas.drawBitmap(picture, picX, picY, null);
                    invalidate();
                case 3:
                     canvas.scale(2, 2, cenX, cenY);
                     canvas.drawBitmap(picture, picX, picY, null);
                    invalidate();
                    break;
                case 4:
                     canvas.skew (0.3f, 0.3f);
                     canvas.drawBitmap(picture, picX, picY, null);
                    invalidate();
                    break;
            }

            picture.recycle();
        }
    }
}
