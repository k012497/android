package com.example.customimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyImageView extends View {

    private String src;

    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(!src.isEmpty()){
            Bitmap bitmap = BitmapFactory.decodeFile(src);
            canvas.scale(1,1,0,0);
            int centerX = (canvas.getWidth()  - bitmap.getWidth()) /2;
            int centerY = (canvas.getHeight() - bitmap.getHeight()) /2;
            canvas.drawBitmap(bitmap, centerX, centerY, null);
            bitmap.recycle();
        }
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}