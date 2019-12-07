package com.example.functiontest3;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class

MainActivity extends AppCompatActivity implements Fragment1.OnFragmentInteractionListener{
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment4 fragment4;
    private Fragment5 fragment5;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigationView = findViewById(R.id.bottomMenu);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_1:
                        setFragment(0);

                        break;
                    case R.id.action_2:
                        setFragment(1);
                        fragment2.setArguments(bundle);

                        break;
                    case R.id.action_3:
                        setFragment(2);
                        break;
                    case R.id.action_4:
                        setFragment(3);
                        break;
                    case R.id.action_5:
                        setFragment(4);
                        break;
                }
                return true;

            }
        });
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();
        fragment5 = new Fragment5();
        setFragment(0);

    }
    private void setFragment(int i) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        switch (i) {
            case 0:
                fragmentTransaction.replace(R.id.frameLayout, fragment1);
                fragmentTransaction.commit();
                // commit() : 이게 반영되게끔 확정 지어주는 역할
                break;
            case 1:
                fragmentTransaction.replace(R.id.frameLayout, fragment2);
                fragmentTransaction.commit();
                break;
            case 2:
                fragmentTransaction.replace(R.id.frameLayout, fragment3);
                fragmentTransaction.commit();
                break;
            case 3:
                fragmentTransaction.replace(R.id.frameLayout, fragment4);
                fragmentTransaction.commit();
                break;
            case 4:
                fragmentTransaction.replace(R.id.frameLayout, fragment5);
                fragmentTransaction.commit();
                break;
        }

    }

    @Override
    public void onFragmentInteraction(Bundle bundle) { // 값이 매개변수로 전달되어 올 것!
        this.bundle = bundle;
        bottomNavigationView.setSelectedItemId(R.id.action_2);
    }


}