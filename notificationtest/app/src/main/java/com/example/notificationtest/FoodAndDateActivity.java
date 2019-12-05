package com.example.notificationtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FoodAndDateActivity extends AppCompatActivity {

    private TextView txtFood, txtDate;
    private ImageView imageView;
    private Button btnKakao, btnMain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_and_date);

        txtFood = findViewById(R.id.txtFood);
        txtDate = findViewById(R.id.txtDate);
        imageView = findViewById(R.id.imageView);
        btnKakao = findViewById(R.id.btnKakao);
        btnMain = findViewById(R.id.btnMain);

        btnKakao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                shareKakao();
            }
        });

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //카카오톡공유
//    private void shareKakao() {
//
//        try{
//
//            final KakaoLink kakaoLink = KakaoLink.getKakaoLink(getApplicationContext());
//            final KakaoTalkLinkMessageBuilder kakaoTalkLinkMessageBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();
//
//            kakaoTalkLinkMessageBuilder.addText("내 냉장고에 우유가 있어!");
//
//            kakaoTalkLinkMessageBuilder.addImage(String.valueOf(imageView), 160, 160);
//
//            kakaoTalkLinkMessageBuilder.addAppButton("유통기한 확인하러 가기");
//
//            kakaoLink.sendMessage(kakaoTalkLinkMessageBuilder, this);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

}