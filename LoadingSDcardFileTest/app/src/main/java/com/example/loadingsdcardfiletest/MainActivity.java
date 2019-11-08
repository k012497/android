package com.example.loadingsdcardfiletest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    Button btnFileList;
    EditText edtFileList;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        btnFileList = findViewById(R.id.btnFileList);
        edtFileList = findViewById(R.id.edtFileList);

        btnFileList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = Environment.getExternalStorageDirectory().getAbsolutePath();
                File[] fileList = new File(path).listFiles();
                String strTitle;

                for(File file : fileList){
                    if(file.isDirectory()){
                        strTitle = "<DIRECTORY> " + file.toString();
                    } else {
                        strTitle = "<FILE> " + file.toString();
                    }

                    edtFileList.setText(edtFileList.getText() + "\n" + strTitle);
                }
            }
        });


    }
}