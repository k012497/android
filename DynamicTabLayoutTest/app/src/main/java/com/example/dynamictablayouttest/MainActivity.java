package com.example.dynamictablayouttest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomMenu;
    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomMenu = findViewById(R.id.bottomMenu);
        frameLayout = findViewById(R.id.frameLayout);

        bottomMenu.setSelectedItemId(R.id.action_3);
        changeFragment(1);


        // bottomMenu를 변경을 때 Fragment
        bottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.action_1: changeFragment(1); break;
                    case R.id.action_2: changeFragment(2); break;
                    case R.id.action_3: changeFragment(3); break;
                }
                return true;
            }
        });

    }

    private void changeFragment(int position) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        switch (position){
            case 1:
//                Fragment fg = new RefrigeratorFragment();
//                fg.setArguments(bundle);
//                fragmentTransaction.replace(R.id.frameLayout, new Fragment1Activity());
                break;
//            case 2: fragmentTransaction.replace(R.id.frameLayout, new Fragment2Activity()); break;
            case 3: fragmentTransaction.replace(R.id.frameLayout, new FragmentTest(this)); break;
        }

        fragmentTransaction.commit();
    }

//    @Override
//    public void onFragmentInteraction(Bundle bundle) {
//        this.bundle = bundle;
//        changeFragment(1);
//    }
}