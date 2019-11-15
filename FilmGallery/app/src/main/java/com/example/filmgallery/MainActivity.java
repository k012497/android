package com.example.filmgallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Gallery gallery;
    ImageView imageView;
    LayoutInflater inflater;
    ArrayList<FilmItem> items = new ArrayList<FilmItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("갤러리 영화 포스터");

        gallery = findViewById(R.id.gallery);
        imageView = findViewById(R.id.imageView);

        insertFilmItem();

        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        GalleryAdapter adapter = new GalleryAdapter(this);
        gallery.setAdapter(adapter);
    }

    private void insertFilmItem() {
        int[] posterID = {R.drawable.mov01, R.drawable.mov02, R.drawable.mov03, R.drawable.mov04, R.drawable.mov05, R.drawable.mov06,
                R.drawable.mov07, R.drawable.mov08, R.drawable.mov09, R.drawable.mov10, R.drawable.mov11, R.drawable.mov12,
                R.drawable.mov13, R.drawable.mov14, R.drawable.mov15, R.drawable.mov16, R.drawable.mov17, R.drawable.mov18,
                R.drawable.mov19, R.drawable.mov20, R.drawable.mov21, R.drawable.mov22, R.drawable.mov23, R.drawable.mov24,
                R.drawable.mov25, R.drawable.mov26, R.drawable.mov27, R.drawable.mov28, R.drawable.mov29, R.drawable.mov30};
        String[] filmTitle = { "써니", "완득이", "괴물", "라디오스타", "비열한거리", "왕의남자",
                "아일랜드", "웰컴투동막골", "헬보이", "빽투더퓨처", "여인의 향기", "쥬라기 공원", "포레스트 검프", "라디오스타",
                "비열한거리", "왕의남자", "아일랜드", "웰컴투동막골", "헬보이", "빽투더퓨처", "써니", "완득이",
                "괴물", "라디오스타", "비열한거리", "왕의남자", "아일랜드", "웰컴투동막골", "헬보이",
                "빽투더퓨처"};

        for(int i = 0; i < 30 ; i++){
            items.add(new FilmItem( posterID[i], filmTitle[i]));
        }
    }

    public class GalleryAdapter extends BaseAdapter {
        private Context context;

        public GalleryAdapter(Context context){
            this.context = context;
        }

        @Override
        public int getCount() {
            return items.size();
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
            final FilmItem item = items.get(position);

            ImageView imageview = new ImageView(context);
            imageview.setLayoutParams(new Gallery.LayoutParams(200, 300));
            imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageview.setPadding(5, 5, 5, 5);
            imageview.setImageResource(item.getPosterId());

            imageview.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    View toastView = inflater.inflate(R.layout.toast_layout, null);
                    TextView tvTitle = toastView.findViewById(R.id.tvTitle);
                    tvTitle.setText(item.getTitle());

                    Toast toast = new Toast(context);
                    toast.setView(toastView);
                    toast.show();

                    imageView.setImageResource(item.getPosterId());
                    return false;
                }
            });

//            imageview.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    View toastView = inflater.inflate(R.layout.toast_layout, null);
//                    TextView tvTitle = toastView.findViewById(R.id.tvTitle);
//                    tvTitle.setText(item.getTitle());
//
//                    Toast toast = new Toast(context);
//                    toast.setView(toastView);
//                    toast.show();
//
//                    imageView.setImageResource(item.getPosterId());
//                }
//            });

            return imageview;
        }
    }
}
