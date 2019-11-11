package com.example.fragmentcanvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
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

public class Fragment1Activity extends Fragment {
    public final int INIT = 0, ROTATE = 1, TRANS = 2, SCALE = 3, SKEW = 4;
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
        menu.add(0,1,0,"회전");
        menu.add(0,2,0,"이동");
        menu.add(0,3,0,"확대");
        menu.add(0,4,0,"기울이기");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                select = ROTATE;
                break;
            case 2:
                select = TRANS;
                break;
            case 3:
                select = SCALE;
                break;
            case 4:
                select = SKEW;
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

            int cenX = this.getWidth()/2;
            int cenY = this.getHeight()/2;
            int picX = (this.getWidth() - picture.getWidth())/2;
            int picY = (this.getHeight() - picture.getWidth())/2;

            switch (select) {
                case 0:
                    canvas.drawBitmap(picture,picX,picY,null);
                    break;
                case ROTATE :
                    canvas.rotate(45,cenX,cenY);
                    canvas.drawBitmap(picture,picX,picY,null);
                    break;
                case TRANS :
                    canvas.translate(-150,200);
                    canvas.drawBitmap(picture,picX,picY,null);
                    break;
                case SCALE :
                    canvas.scale(2,2,cenX,cenY);
                    canvas.drawBitmap(picture,picX, picY,null);
                    break;
                case SKEW :
                    canvas.skew(0.3f, 0.3f);
                    canvas.drawBitmap(picture, picX,picY,null);
                    break;
            }
            invalidate();
            picture.recycle();
        }
    }
}
