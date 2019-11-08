package com.example.customimageview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnPrev, btnNext;
    private MyImageView myImageView;
    private File[] imageFile;
    TextView currentPage, totalPage;
    int currentPoint = 0;
    String strName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        myImageView = findViewById(R.id.myImageView);
        currentPage = findViewById(R.id.currentPage);
        totalPage = findViewById(R.id.totalPage);

        initImageView();

        currentPage.setText(String.valueOf(currentPoint + 1));
        totalPage.setText(" / " + String.valueOf(imageFile.length));

        btnPrev.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    private void initImageView() {
        imageFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures").listFiles();
        myImageView.setSrc(imageFile[currentPoint].toString().trim());
        myImageView.invalidate();
        toastFileNameAndSetPage();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPrev :
                setPreviousImage();
                break;
            case R.id.btnNext :
                setNextImage();
                break;
        }
    }

    private void setNextImage() {
        currentPoint++;
        currentPoint = (currentPoint > imageFile.length - 1 ? 0 : currentPoint);
        myImageView.setSrc(imageFile[currentPoint].toString().trim());
        myImageView.invalidate();
        toastFileNameAndSetPage();
    }

    private void setPreviousImage() {
        currentPoint--;
        currentPoint = (currentPoint < 0 ? imageFile.length - 1 : currentPoint);
        myImageView.setSrc(imageFile[currentPoint].toString().trim());
        myImageView.invalidate();
        toastFileNameAndSetPage();
    }

    public void toastFileNameAndSetPage(){
        currentPage.setText(String.valueOf(currentPoint + 1));
        strName = myImageView.getSrc().toString().trim();
        Toast.makeText(this, strName, Toast.LENGTH_SHORT).show();
    }
}