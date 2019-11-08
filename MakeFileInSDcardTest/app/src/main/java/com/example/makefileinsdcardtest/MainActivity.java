package com.example.makefileinsdcardtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnMake, btnDelete;
    String strSDpath;
    File myDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        btnMake = findViewById(R.id.btnMake);
        btnDelete = findViewById(R.id.btnDelete);

        strSDpath = Environment.getExternalStorageDirectory().getAbsolutePath();
        myDir = new File(strSDpath + "/mydir");

        btnMake.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnMake:
                if(!myDir.exists()){
                    myDir.mkdir();
                    Toast.makeText(this, "successfully making mydir!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "mydir alreay exists :(", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btnDelete:
                if(myDir.exists()){
                    myDir.delete();
                    Toast.makeText(this, "successfully deleting mydir!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "mydir doesn't exist :(", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
