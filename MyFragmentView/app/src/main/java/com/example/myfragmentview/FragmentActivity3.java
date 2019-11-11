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

public class FragmentActivity3 extends Fragment implements View.OnClickListener {
    Button btn3rd;
    View view;

    public FragmentActivity3() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment3, container, false);
        btn3rd = view.findViewById(R.id.btn3rd);
        btn3rd.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        view.setBackgroundColor(Color.GRAY);
        Toast.makeText(getContext(), "3번", Toast.LENGTH_SHORT).show();
    }
}
