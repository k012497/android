package com.example.miniphotoshop;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton ibBright, ibDark, ibGray, ibRotate, ibZoomIn, ibZoomOut;
    LinearLayout llBitmap;
    MyGraphicView myGraphicView;

    float scaleX = 1.0f, scaleY = 1.0f;
    float color = 1.0f;
    float satur = 0.0f;
    float angle = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("mini photoshop 🎨");

        ibBright = findViewById(R.id.ibBright);
        ibDark = findViewById(R.id.ibDark);
        ibGray = findViewById(R.id.ibGray);
        ibRotate = findViewById(R.id.ibRotate);
        ibZoomIn = findViewById(R.id.ibZoomIn);
        ibZoomOut = findViewById(R.id.ibZoomOut);
        llBitmap = findViewById(R.id.llBitmap);

        myGraphicView = new MyGraphicView(this); // this =  context, 즉 R.layout.activity_main
        llBitmap.addView(myGraphicView); // 리니어레이아웃에 뷰로 작용

        ibBright.setOnClickListener(this);
        ibDark.setOnClickListener(this);
        ibGray.setOnClickListener(this);
        ibRotate.setOnClickListener(this);
        ibZoomIn.setOnClickListener(this);
        ibZoomOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibZoomIn:
                scaleX += 0.2f;
                scaleY += 0.2f;
                break;
            case R.id.ibZoomOut:
                scaleX -= 0.2f;
                scaleY -= 0.2f;
                break;
            case R.id.ibBright:
                color += 0.2f;
                break;
            case R.id.ibDark:
                color -= 0.2f;
                break;
            case R.id.ibGray:
                satur = (satur == 0 ? 1 : 0);
                break;
            case R.id.ibRotate:
                angle -= 10;
                break;
        }
        myGraphicView.invalidate();
    }

    private class MyGraphicView extends View {

        public MyGraphicView(Context context) {
            super(context);
        }

        public MyGraphicView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            // set scale
            int centX = this.getWidth() / 2;
            int centY = this.getHeight() / 2;
            canvas.scale(scaleX, scaleY, centX, centY);

            // set rotate
            canvas.rotate(angle, centX, centY);

            // set paint
            Paint paint = new Paint();

            // set bright/dark
            float[] array = {color, 0, 0, 0, 0, 0, color, 0 ,0 ,0, 0, 0, color, 0, 0, 0, 0, 0, 1, 0};
            ColorMatrix colorMatrix = new ColorMatrix(array);

            // set color filter (gray)
            if(satur == 0.0){
                colorMatrix.setSaturation(0.0f);
            }

            // set brightness
            paint.setColorFilter((new ColorMatrixColorFilter(colorMatrix)));

            // load image file(bitmap)
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lena256);

            // draw bitmp on canvas
            int x = (this.getWidth() - bitmap.getWidth()) / 2;
            int y = (this.getHeight() - bitmap.getHeight()) / 2;
            canvas.drawBitmap(bitmap, x, y, paint);

            // 메모리 버퍼 기능 - 이미지를 메모리에 한 번에 올리면 속도 걸리니까 조금조금씩 올림(메모리 올라가기 전 준비단계)
            bitmap.recycle();
        }
    }
}
