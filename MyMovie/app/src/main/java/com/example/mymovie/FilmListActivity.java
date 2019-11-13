package com.example.mymovie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class FilmListActivity extends Fragment implements View.OnClickListener {

    Button btnDetails;
    ImageView ivPoster;
    TextView tvCount;
    TextView tvRating;
    TextView tvTitle;
    TextView tvDateReleased;
    TextView tvBookingRate;

    View view;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    static ArrayList<FilmItem> filmItems = new ArrayList<FilmItem>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_view_pager, container, false);

        viewPager = view.findViewById(R.id.viewPager);
        filmItems.add(new FilmItem(1, "군도", "2014.1.1", "1.8%", "15세 관람가", R.drawable.image1));
        filmItems.add(new FilmItem(2, "공조", "2014.1.1", "1.8%", "15세 관람가", R.drawable.image2));
        filmItems.add(new FilmItem(3, "더킹", "2014.1.1", "1.8%", "15세 관람가", R.drawable.image3));
        filmItems.add(new FilmItem(4, "레지던스 이블", "2014.1.1", "1.8%", "15세 관람가", R.drawable.image4));
        filmItems.add(new FilmItem(5, "럭키", "2014.1.1", "1.8%", "15세 관람가", R.drawable.image5));
        filmItems.add(new FilmItem(6, "아수라", "2014.1.1", "1.8%", "15세 관람가", R.drawable.image6));

        // 주의!@ 어댑터 객체 만들기 전에 데이터를 넣어야 함.
        // 안 그러면 어댑터 만들어졌을 때 카운트랑 실제 데이터 개수가 맞지 않아서 예외 발생!
        viewPagerAdapter = new ViewPagerAdapter(getContext());
        viewPager.setAdapter(viewPagerAdapter);
        return view;
    }

    class ViewPagerAdapter extends PagerAdapter {


        private boolean doNotifyDataSetChangedOnce = false;
        Context mContext;

        public ViewPagerAdapter(Context context) {
            super();
            this.mContext = context;
        }

        // 화면에 표시할 페이지 뷰를 만드는 메서드
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            if(mContext != null){
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.film_list, container,false);

                btnDetails = view.findViewById(R.id.btnDetails);
                ivPoster = view.findViewById(R.id.ivPoster);
                tvCount = view.findViewById(R.id.tvCount);
                tvRating = view.findViewById(R.id.tvRating);
                tvTitle = view.findViewById(R.id.tvTitle);
                tvDateReleased = view.findViewById(R.id.tvDateReleased);
                tvBookingRate = view.findViewById(R.id.tvBookingRate);

                FilmItem filmItem = filmItems.get(position);
                tvTitle.setText(filmItem.getTitle());
                ivPoster.setImageResource(filmItem.getPosterImage());
                tvCount.setText(filmItem.getCount() + ". ");
                tvRating.setText(filmItem.getRating());
                tvDateReleased.setText(filmItem.getDateReleased());
                tvBookingRate.setText(filmItem.getBookingRate());

                btnDetails.setOnClickListener(FilmListActivity.this);

                // 뷰페이저에 추가.
                container.addView(view);
                doNotifyDataSetChangedOnce = true;
            }
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
            doNotifyDataSetChangedOnce = true;
        }

        @Override
        public int getCount() {
            if (doNotifyDataSetChangedOnce) {
                doNotifyDataSetChangedOnce = false;
                notifyDataSetChanged();
            }
            return filmItems.size();
        }

        // 뷰페이저 내부적으로 관리되는 키 객체(key object)와 페이지뷰가 연관되는지 여부를 확인
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return (view == (View)object);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), FilmDetailsActivity.class);
        startActivity(intent);
    }
}
