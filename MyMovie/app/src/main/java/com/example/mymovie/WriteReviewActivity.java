package com.example.mymovie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

public class WriteReviewActivity extends AppCompatActivity implements View.OnClickListener {

    RatingBar ratingBar;
    Button btnSave, btnCancel;
    EditText edtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_review);

        ratingBar = findViewById(R.id.ratingBar);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
        edtContent = findViewById(R.id.edtContent);

        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btnSave:
                intent.putExtra("key", "value");
                setResult(Activity.RESULT_OK, intent);
                finish();
                break;

            case R.id.btnCancel:
                intent.putExtra("key", "value");
                setResult(Activity.RESULT_CANCELED, intent);
                finish();
                break;

        }
    }
}
