package com.example.mymovie;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

// listView height wrap_content로 하면 1개만 나옴

public class FilmDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnShowAll;
    ImageView ivThumbUp, ivThumbDown;
    TextView tvThumbUp, tvThumbDown, tvPlot;
    LinearLayout writeReview;
    ListView listView;

    int currentThumbUpCount;
    int currentThumbDownCount;
    boolean thumbUpSelected = false;
    boolean thumbDownSelected = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.film_details);

        ivThumbUp = findViewById(R.id.ivThumbUp);
        ivThumbDown = findViewById(R.id.ivThumbDown);
        tvThumbUp = findViewById(R.id.tvThumbUp);
        tvThumbDown = findViewById(R.id.tvThumbDown);
        tvPlot = findViewById(R.id.tvPlot);
        writeReview = findViewById(R.id.writeReview);
        btnShowAll = findViewById(R.id.btnShowAll);
        listView = findViewById(R.id.listView);

        ReviewAdapter adapter = new ReviewAdapter();
        adapter.addItem(new ReviewItem("k012497", "10분 전", 7, "그럭저럭 볼만해요", 1, R.drawable.user1));
        adapter.addItem(new ReviewItem("abc123", "1시간 전", 4, "별로 재미 없어여", 3, R.drawable.user1));
        adapter.addItem(new ReviewItem("yeahjinn", "1시간 전", 10, "김소진 살앙해", 3, R.drawable.user1));
        adapter.addItem(new ReviewItem("sooojinn", "1시간 전", 10, "김예진 살앙해", 3, R.drawable.user1));
        listView.setAdapter(adapter);

        setFilmInformation();

        ivThumbUp.setOnClickListener(this);
        ivThumbDown.setOnClickListener(this);
        writeReview.setOnClickListener(this);
        btnShowAll.setOnClickListener(this);

    }

    class ReviewAdapter extends BaseAdapter {

        ArrayList<ReviewItem> items = new ArrayList<ReviewItem>();
        @Override
        public int getCount() {
            // 최근 3개만 보여주기
            return 2;
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

    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()){
            case R.id.ivThumbUp:
                thumbUpSelected();
                break;
            case R.id.ivThumbDown:
                thumbDownSelected();
                break;
            case R.id.writeReview:
                //Intent를 이용해서 새로운 액티비티를 띄움
                intent = new Intent(getApplicationContext(), WriteReviewActivity.class);
                startActivityForResult(intent, 1);
                Toast.makeText(this, "리뷰를 작성합니다", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btnShowAll:
                intent = new Intent(getApplicationContext(), ShowReviewActivity.class);
                startActivityForResult(intent, 2);
                Toast.makeText(this, "리뷰를 모두 불러옵니다", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 1:
                String strSave = data.getStringExtra("save");
                Toast.makeText(this, "from WRITING review activity \n저장여부: " + strSave, Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "from SHOWING review activity", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    private void thumbUpSelected() {
        if (!thumbUpSelected) {
            // 안 눌렀을 경우
            ivThumbUp.setImageResource(R.drawable.ic_thumb_up_selected);
            currentThumbUpCount = Integer.parseInt(tvThumbUp.getText().toString().trim());
            tvThumbUp.setText(String.valueOf(++currentThumbUpCount));
            thumbUpSelected = true;
        } else {
            ivThumbUp.setImageResource(R.drawable.ic_thumb_up);
            currentThumbUpCount = Integer.parseInt(tvThumbUp.getText().toString().trim());
            tvThumbUp.setText(String.valueOf(--currentThumbUpCount));
            thumbUpSelected = false;
        }

    }

    private void thumbDownSelected() {
        if (!thumbDownSelected){
            // 안 눌렀을 경우
            ivThumbDown.setImageResource(R.drawable.ic_thumb_down_selected);
            currentThumbDownCount = Integer.parseInt(tvThumbDown.getText().toString().trim());
            tvThumbDown.setText(String.valueOf(++currentThumbDownCount));
            thumbDownSelected = true;
        } else {
            ivThumbDown.setImageResource(R.drawable.ic_thumb_down);
            currentThumbDownCount = Integer.parseInt(tvThumbDown.getText().toString().trim());
            tvThumbDown.setText(String.valueOf(--currentThumbDownCount));
            thumbDownSelected = false;
        }

    }

    private void setFilmInformation() {
        tvPlot.setText("군도, 백성을 구하라!\n" +
                " 양반과 탐관오리들의 착취가 극에 달했던 조선 철종 13년. 힘 없는 백성의 편이 되어 " +
                " 세상을 바로잡고자 하는 의적떼인 군도(群盜), 지리산 추설이 있었다.\n" +
                " \n" +
                " 쌍칼 도치 vs 백성의 적 조윤\n" +
                " 잦은 자연재해, 기근과 관의 횡포까지 겹쳐 백성들의 삶이 날로 피폐해 져 가는 사이, " +
                " 나주 대부호의 서자로 조선 최고의 무관 출신인 조윤은 극악한 수법으로 양민들을 수탈, " +
                " 삼남지방 최고의 대부호로 성장한다. 한편 소, 돼지를 잡아 근근이 살아가던 천한 백정 돌무치는 " +
                " 죽어도 잊지 못할 끔찍한 일을 당한 뒤 군도에 합류. 지리산 추설의 신 거성(新 巨星) 도치로 거듭난다.\n" +
                " \n" +
                " 뭉치면 백성, 흩어지면 도적!\n" +
                " 망할 세상을 뒤집기 위해, 백성이 주인인 새 세상을 향해 도치를 필두로 한 군도는 백성의 적, " +
                " 조윤과 한 판 승부를 시작하는데...");
    }
}
