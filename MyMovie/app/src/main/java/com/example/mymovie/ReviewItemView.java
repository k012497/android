package com.example.mymovie;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ReviewItemView extends LinearLayout {

    ImageView ivUser;

    TextView tvUserId;
    TextView tvRegisteTime;
    TextView tvReviewContent;
    TextView tvRecommendCount;
    RatingBar ratingBar;
    Button btnReport;

    public ReviewItemView(Context context) {
        super(context);
        init(context);
    }

    public ReviewItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    // 초기화를 위한 메서드

    private void init (Context context){
        // xml파일을 객체화
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.review_item, this, true);

        ivUser = findViewById(R.id.ivUser);
        tvUserId = findViewById(R.id.tvUserId);
        tvRegisteTime = findViewById(R.id.tvRegisteTime);
        tvReviewContent = findViewById(R.id.tvReviewContent);
        tvRecommendCount = findViewById(R.id.tvRecommendCount);
        ratingBar = findViewById(R.id.ratingBar);
        btnReport = findViewById(R.id.btnReport);
    }

    public void setImage(int resId) {
        ivUser.setImageResource(resId);
    }

    public void setId(String id) {
        tvUserId.setText(id);
    }

    public void setRegisteTime(String registeTime) {
        tvRegisteTime.setText(registeTime);
    }

    public void setRatingBar(float rating){
        ratingBar.setRating(rating/2);
    }
    public void setReviewContent(String content) {
        tvReviewContent.setText(content);
    }

    public void setRecommendCount(int recommendCount) {
        tvRecommendCount.setText(String.valueOf(recommendCount));
    }
}
