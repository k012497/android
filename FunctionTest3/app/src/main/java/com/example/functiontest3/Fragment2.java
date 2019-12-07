package com.example.functiontest3;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {

    private View view;
    private TextView textView2;

@Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment2, container, false);
        textView2 = view.findViewById(R.id.textView2);

        Bundle bundle = getArguments();
        if(bundle != null){
            String name = bundle.getString("name");
            textView2.setText(name);

        }
        return view;
    }
}
