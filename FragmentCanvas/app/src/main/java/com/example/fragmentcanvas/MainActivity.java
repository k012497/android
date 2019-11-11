package com.example.fragmentcanvas;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btCanvasGeometry, btCanvasBlurMask, btCanvasColorMatrix;

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btCanvasGeometry = findViewById(R.id.btCanvasGeometry);
        btCanvasBlurMask = findViewById(R.id.btCanvasBlurMask);
        btCanvasColorMatrix = findViewById(R.id.btCanvasColorMatrix);

        btCanvasGeometry.setOnClickListener(this);
        btCanvasBlurMask.setOnClickListener(this);
        btCanvasColorMatrix.setOnClickListener(this);

        btCanvasGeometry.callOnClick();

    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = null;
        switch (v.getId()) {
            case R.id.btCanvasGeometry :
                fragment = new Fragment1Activity();
                break;
            case R.id.btCanvasBlurMask :
                fragment = new Fragment2Activity();
                break;
            case R.id.btCanvasColorMatrix :
                fragment = new Fragment3Activity();
                break;
        }

        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}