package com.example.myfragmentview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myfragmentview.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnMenu1, btnMenu2, btnMenu3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMenu1 = findViewById(R.id.btnMenu1);
        btnMenu2 = findViewById(R.id.btnMenu2);
        btnMenu3 = findViewById(R.id.btnMenu3);

        btnMenu1.setOnClickListener(this);
        btnMenu2.setOnClickListener(this);
        btnMenu3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //Fragment가져오는객체
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fgActivity = null;

        switch (v.getId()){
            case R.id.btnMenu1:
                fgActivity = new FragmentActivity1();
                break;

            case R.id.btnMenu2:
                fgActivity = new FragmentActivity2();
                break;

            case R.id.btnMenu3:
                fgActivity = new FragmentActivity3();
                break;
        }

        ft.replace(R.id.frameLayout, fgActivity);
        ft.commit();
    }
}
