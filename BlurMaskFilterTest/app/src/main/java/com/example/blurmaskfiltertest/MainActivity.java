package com.example.blurmaskfiltertest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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
            invalidate();
            return true;
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Bitmap picture = BitmapFactory.decodeResource(getResources(),
                    R.drawable.charlotte_ager2);

            int picX = (this.getWidth() - picture.getWidth()) / 2;
            int picY = (this.getHeight() - picture.getHeight()) / 2;

            Paint paint = new Paint();
            BlurMaskFilter bMask;

            switch (count){
                case 1:
                    bMask = new BlurMaskFilter(30, BlurMaskFilter.Blur.NORMAL);
                    paint.setMaskFilter(bMask);
                    canvas.drawBitmap(picture, picX, picY, paint);
                    break;
                case 2:
                    bMask = new BlurMaskFilter(30, BlurMaskFilter.Blur.INNER);
                    paint.setMaskFilter(bMask);
                    canvas.drawBitmap(picture, picX, picY, paint);
                    break;
                case 3:
                    bMask = new BlurMaskFilter(30, BlurMaskFilter.Blur.OUTER);
                    paint.setMaskFilter(bMask);
                    canvas.drawBitmap(picture, picX, picY, paint);
                    break;
                case 4:
                    bMask = new BlurMaskFilter (30, BlurMaskFilter.Blur.SOLID);
                    paint.setMaskFilter(bMask);
                    canvas.drawBitmap(picture, picX, picY, paint);
                    break;
            }
            picture.recycle();

        }
    }
}
