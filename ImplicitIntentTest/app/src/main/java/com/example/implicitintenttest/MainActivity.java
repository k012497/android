package com.example.implicitintenttest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnDial, btnHomePage, btnSearch, btnMap, btnGallery, btnMessage, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MainActivity", "1 onCreate()");

        btnDial = findViewById(R.id.btnDial);
        btnHomePage = findViewById(R.id.btnHomePage);
        btnSearch = findViewById(R.id.btnSearch);
        btnMap = findViewById(R.id.btnMap);
        btnGallery = findViewById(R.id.btnGallery);
        btnMessage = findViewById(R.id.btnMessage);
        btnExit = findViewById(R.id.btnExit);

        btnDial.setOnClickListener(this);
        btnHomePage.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        btnMap.setOnClickListener(this);
        btnGallery.setOnClickListener(this);
        btnMessage.setOnClickListener(this);
        btnExit.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity", "onstart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity", "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity", "onRestart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity", "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity", "onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MainActivity", "onRestart()");
    }

    @Override
    public void onClick(View v) {
        Uri uri = null;
        Intent intent = null;

        switch (v.getId()){
            case R.id.btnDial:
                uri = Uri.parse("tel:01033333333");
                intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
                break;

            case R.id.btnHomePage:
                uri = Uri.parse("http://www.google.com");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;

            case R.id.btnMap:
                uri = Uri.parse("http://maps.google.com/maps?q=37.562167, 127.035203");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;

            case R.id.btnSearch:
                intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, "하동진");
                startActivity(intent);
                break;

            case R.id.btnMessage:
                intent = new Intent(Intent.ACTION_SENDTO);
                intent.putExtra("sms_body", "hihi");
                intent.setData(Uri.parse(Uri.parse("smsto:") + Uri.encode("010-1234-1234")));
                startActivity(intent);
                break;

            case R.id.btnGallery:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
                break;

            case R.id.btnExit:
                finish();
                break;
        }
    }
}
