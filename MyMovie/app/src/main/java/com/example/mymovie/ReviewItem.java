package com.example.mymovie;

import android.widget.RatingBar;

public class ReviewItem {

    String id;
    String registeTime;
    float rating;
    String content;
    int recommendCount;
    int imageResource;

    public ReviewItem(String id, String registeTime, float rating, String content, int recommendCount, int imageResource) {
        this.id = id;
        this.registeTime = registeTime;
        this.rating = rating;
        this.content = content;
        this.recommendCount = recommendCount;
        this.imageResource = imageResource;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getId() {
        return id;
    }

    public String getRegisteTime() {
        return registeTime;
    }

    public float getRating() {
        return rating;
    }

    public String getContent() {
        return content;
    }

    public int getRecommendCount() {
        return recommendCount;
    }

}
