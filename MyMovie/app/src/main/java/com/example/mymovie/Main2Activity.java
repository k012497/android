package com.example.mymovie;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
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

public class Main2Activity extends AppCompatActivity {

    private View navView;
    NavigationView navigationView;
    DrawerLayout drawerLayout;

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
