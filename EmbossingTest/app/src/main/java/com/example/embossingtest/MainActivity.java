package com.example.embossingtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

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
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            int cenX = this.getWidth() / 2;
            int cenY = this.getHeight() / 2;

            Paint paint = new Paint();
            paint.setColor(Color.GRAY);
            EmbossMaskFilter eMask;

            eMask = new EmbossMaskFilter(new float[] { 3, 3, 3 }, 0.5f, 5, 10);
            paint.setMaskFilter(eMask);
            canvas.drawCircle(cenX, 200, 150, paint);

            eMask = new EmbossMaskFilter(new float[] {10,3,3}, 0.5f, 5, 10);
            paint.setMaskFilter(eMask);
            canvas.drawCircle(cenX, 600, 150, paint);

            eMask = new EmbossMaskFilter(new float[] {3,10,3}, 0.5f, 5, 10);
            paint.setMaskFilter(eMask);
            canvas.drawCircle(cenX, 1000, 150, paint);

            eMask = new EmbossMaskFilter(new float[] {3,3,10}, 0.5f, 5, 10);
            paint.setMaskFilter(eMask);
            canvas.drawCircle(cenX, 1400, 150, paint);
        }
    }
}
