package com.example.fragmentcanvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
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

public class Fragment3Activity extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentCanvas view = new FragmentCanvas(getActivity());
        return view;
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

            float[] array = {2,0,0,0,-25, 0,2,0,0,-25, 0,0,2,0,-25, 0,0,0,1,0};

            ColorMatrix cm = new ColorMatrix(array);

            paint.setColorFilter(new ColorMatrixColorFilter(cm));
            canvas.drawBitmap(picture,picX,picY,paint);
            invalidate();
            picture.recycle();


        }
    }
}
