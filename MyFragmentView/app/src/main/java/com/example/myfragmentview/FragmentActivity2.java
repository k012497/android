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

public class FragmentActivity2 extends Fragment implements View.OnClickListener {
    Button btn2nd;
    View view;

    public FragmentActivity2() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment2, container, false);
        btn2nd = view.findViewById(R.id.btn2nd);
        btn2nd.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        view.setBackgroundColor(Color.LTGRAY);
        Toast.makeText(getContext(), "2번", Toast.LENGTH_SHORT).show();
    }
}
