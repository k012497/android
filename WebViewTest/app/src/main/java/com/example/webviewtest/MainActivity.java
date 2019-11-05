package com.example.webviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edtURL;
    Button btnGo, btnBack;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtURL = findViewById(R.id.edtURL);
        btnGo = findViewById(R.id.btnGo);
        btnBack = findViewById(R.id.btnBack);
        webView = findViewById(R.id.webView);

        // WebView를 실행하려면 WebViewClient 클래스를 상속받아서 shoutOverrideLoading을 진행해야 함.
        webView.setWebViewClient(new MyWebViewClient()); // 브라우저 가져와서 url집어넣으려고 하는 것
        WebSettings webSettings = webView.getSettings(); // 웹 세팅
        webSettings.setBuiltInZoomControls(true);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("http://" + edtURL.getText().toString());
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.goBack();
            }
        });
    }

    class MyWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            return super.shouldOverrideUrlLoading(view, url);
        }
    }
}
