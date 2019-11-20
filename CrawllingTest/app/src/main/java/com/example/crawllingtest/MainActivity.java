package com.example.crawllingtest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;
    String html;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        try {
            // 간략화된 GET, POST
            Document google1 = Jsoup.connect("http://www.google.com").get();
            Document google2 = Jsoup.connect("http://www.google.com").post();

            // Response로부터 Document 얻어오기
            Connection.Response response;

            Log.d("here", "here");


            response = Jsoup.connect("http://www.google.com")
                    .method(Connection.Method.GET)
                    .execute();


            Document document = response.parse();
            html = document.html();
            String text = document.text();

        } catch (IOException e) {
            Log.e("error", e.toString());
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), html, Toast.LENGTH_SHORT).show();
            }
        });
    }

}