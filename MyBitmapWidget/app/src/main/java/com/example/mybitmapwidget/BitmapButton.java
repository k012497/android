package com.example.mybitmapwidget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatButton;

public class BitmapButton extends AppCompatButton {
    public BitmapButton(Context context) {
        super(context);
        init(context);
    }

    public BitmapButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        setBackgroundResource(R.drawable.title_bitmap_button_normal);

        //setTextSize(100); //픽셀단위로만 지정 가능
        float textSize = getResources().getDimension(R.dimen.text_size);
        setTextSize(textSize);

        setTextColor(Color.WHITE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action =  event.getAction();

        switch (action){
            case MotionEvent.ACTION_DOWN :
                setBackgroundResource(R.drawable.title_bitmap_button_clicked);
                break;

            case MotionEvent.ACTION_UP :
                setBackgroundResource(R.drawable.title_bitmap_button_normal);
                break;
        }

        invalidate(); // 버튼의 그래픽 다시 그리기

        return true;
    }

}
