package com.example.viewpagertest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {
    private TextView textView;
    View view;

    //싱글톤과 비슷
    public static Fragment1 newInstance(){
        Fragment1 fragment1 = new Fragment1();
        return fragment1;
    }

    // 콜백함수로 불러짐
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1, container, false);
//        textView = view.findViewById(R.id.textView);

        return view;
    }
}
