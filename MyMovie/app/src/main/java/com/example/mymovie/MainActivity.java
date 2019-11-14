package com.example.mymovie;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private View navView;
    NavigationView navigationView;
    DrawerLayout drawerLayout;

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("debug", "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView.setCheckedItem(R.id.nav_list);
        this.InitializeLayout();

        // 이렇게 말고 자동으로 메뉴 선택돼서 첫 화면 뜨게하는 법?
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fragmentActivity = null;
        fragmentActivity = new FilmListActivity();
        ft.replace(R.id.frameLayout, fragmentActivity);
        ft.commit();

//        navView = findViewById(R.id.nav_list);
//        navView.callOnClick();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setIcon(R.drawable.ic_hamburger_menu);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("debug", "start()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("debug", "resume()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("debug", "restart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("debug", "stop()");
//        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("debug", "pause()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("debug", "destroy()");
    }

    public void InitializeLayout() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment fragmentActivity = null;
                switch (menuItem.getItemId()) {
                    case R.id.nav_list:
                            fragmentActivity = new FilmListActivity();
                            ft.replace(R.id.frameLayout, fragmentActivity);
                            ft.commit();
                        Toast.makeText(getApplicationContext(), "SelectedItem 1", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_api:
                        Toast.makeText(getApplicationContext(), "SelectedItem 2", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_booking:
                        Toast.makeText(getApplicationContext(), "SelectedItem 3", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_setting:
                        Toast.makeText(getApplicationContext(), "SelectedItem 4", Toast.LENGTH_SHORT).show();
                        break;
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}