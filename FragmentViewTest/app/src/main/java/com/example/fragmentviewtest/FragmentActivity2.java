package com.example.fragmentviewtest;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentActivity2 extends Fragment {
    private OnFragmentInteractionListener mListener;// 객체참조변수

    private  EditText edtName;
    View view;

    public FragmentActivity2() {
    }

    //setContentView와 마찬가지로 inflate기능을 함
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment2, container, false);
        Button btnSend = view.findViewById(R.id.btnSend);
        edtName = view.findViewById(R.id.edtName);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                Bundle bundle = new Bundle(1);
                bundle.putString("name", name);
                mListener.onFragmentInteraction(bundle);
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnFragmentInteractionListener){
            // 이 컨텍스트 속에 리스너가 들어있냐, 즉 자식이냐
            mListener = (OnFragmentInteractionListener) context; // MainActivity(자식)의 객체를 가져옴 - 부모로 형변환
        } else {
            throw new RuntimeException(context.toString() + "OnFragmentInteractionListener을 구현하라");
        }
    }

    public interface OnFragmentInteractionListener{
        void onFragmentInteraction(Bundle bundle); // 추상메소드
    }
}
