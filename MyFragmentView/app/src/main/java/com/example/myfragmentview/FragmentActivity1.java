package com.example.myfragmentview;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentActivity1 extends Fragment implements View.OnClickListener {
    Button btn1st;
    View view;

    public FragmentActivity1() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1, container, false);
        btn1st = view.findViewById(R.id.btn1st);
        btn1st.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        view.setBackgroundColor(Color.BLACK);
        Toast.makeText(getContext(), "1번", Toast.LENGTH_SHORT).show();
    }
}
