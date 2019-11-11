package com.example.fragmentcanvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment2Activity extends Fragment {
    public final int INIT = 0, NORMAL = 1, INNER = 2, OUTER = 3, SOLID = 4;
    public int select = INIT;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentCanvas view = new FragmentCanvas(getActivity());
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
        menu.add(0,1,0,"Blur 1");
        menu.add(0,2,0,"Blur 2");
        menu.add(0,3,0,"Blur 3");
        menu.add(0,4,0,"Blur 4");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                select = NORMAL;
                break;
            case 2:
                select = INNER;
                break;
            case 3:
                select = OUTER;
                break;
            case 4:
                select = SOLID;
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public class FragmentCanvas extends View{
        public FragmentCanvas(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.image3);

            int picX = (this.getWidth() - picture.getWidth())/2;
            int picY = (this.getHeight() - picture.getWidth())/2;

            Paint paint = new Paint();
            BlurMaskFilter blurMaskFilter;

            switch (select) {
                case 0:
                    canvas.drawBitmap(picture,picX,picY,null);
                    break;
                case NORMAL :
                    blurMaskFilter = new BlurMaskFilter(30, BlurMaskFilter.Blur.NORMAL);
                    paint.setMaskFilter(blurMaskFilter);
                    canvas.drawBitmap(picture,picX,picY,paint);
                    break;
                case INNER :
                    blurMaskFilter = new BlurMaskFilter(30, BlurMaskFilter.Blur.INNER);
                    paint.setMaskFilter(blurMaskFilter);
                    canvas.drawBitmap(picture,picX,picY,paint);
                    break;
                case OUTER :
                    blurMaskFilter = new BlurMaskFilter(30, BlurMaskFilter.Blur.OUTER);
                    paint.setMaskFilter(blurMaskFilter);
                    canvas.drawBitmap(picture,picX,picY,paint);
                    break;
                case SOLID :
                    blurMaskFilter = new BlurMaskFilter(30, BlurMaskFilter.Blur.SOLID);
                    paint.setMaskFilter(blurMaskFilter);
                    canvas.drawBitmap(picture,picX,picY,paint);
                    break;
            }

            invalidate();
            picture.recycle();
        }
    }
}
