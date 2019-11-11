package com.example.mymovie;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ShowReviewActivity extends AppCompatActivity {

    LinearLayout writeReview;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_review);

        writeReview = findViewById(R.id.writeReview);
        listView = findViewById(R.id.listView);

        ReviewAdapter adapter = new ReviewAdapter();
        adapter.addItem(new ReviewItem("k012497", "10분 전", 7, "그럭저럭 볼만해요", 1, R.drawable.user1));
        adapter.addItem(new ReviewItem("abc123", "1시간 전", 4, "별로 재미 없어여", 3, R.drawable.user1));
        adapter.addItem(new ReviewItem("yeahjinn", "1시간 전", 10, "김소진 살앙해", 3, R.drawable.user1));
        adapter.addItem(new ReviewItem("sooojinn", "1시간 전", 10, "김예진 살앙해", 3, R.drawable.user1));
        adapter.addItem(new ReviewItem("sooojinn", "1시간 전", 10, "김예진 살앙해", 3, R.drawable.user1));
        adapter.addItem(new ReviewItem("sooojinn", "1시간 전", 10, "김예진 살앙해", 3, R.drawable.user1));
        adapter.addItem(new ReviewItem("sooojinn", "1시간 전", 10, "김예진 살앙해", 3, R.drawable.user1));
        adapter.addItem(new ReviewItem("sooojinn", "1시간 전", 10, "김예진 살앙해", 3, R.drawable.user1));
        adapter.addItem(new ReviewItem("sooojinn", "1시간 전", 10, "김예진 살앙해", 3, R.drawable.user1));
        listView.setAdapter(adapter);

    }

    class ReviewAdapter extends BaseAdapter {

        ArrayList<ReviewItem> items = new ArrayList<ReviewItem>();
        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(ReviewItem item){
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ReviewItemView view = new ReviewItemView(getApplicationContext());

            ReviewItem item = items.get(position);
            view.setId(item.getId());
            view.setRegisteTime(item.getRegisteTime());
            view.setRecommendCount(item.getRecommendCount());
            view.setReviewContent(item.getContent());
            view.setRatingBar(item.getRating());
            view.setImage(item.getImageResource());

            return view;
        }
    }
}
