package com.example.fragmentviewtest;

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

    Button flBtnName;
    View view;

    public FragmentActivity1() {
    }

    //setContentView와 마찬가지로 inflate기능을 함
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1, container, false);
        flBtnName = view.findViewById(R.id.f1BtnName);
        flBtnName.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.f1BtnName:
                toastDisplay("hey");
            break;
        }
    }

    private void toastDisplay(String message) {
        Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
