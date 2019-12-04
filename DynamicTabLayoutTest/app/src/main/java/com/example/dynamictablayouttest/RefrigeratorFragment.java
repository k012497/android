package com.example.dynamictablayouttest;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RefrigeratorFragment extends Fragment {
    final static String TAG = "RefrigeratorFragment";
    View view;
    private static TextView textView;
    ArrayList<String> list = new ArrayList<String>();

    RecyclerView rvFridge;
    static RecyclerAdapter recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.refrigerator_list, container, false);
        rvFridge = view.findViewById(R.id.rvFridge);

        Bundle bundle = getArguments();
        if(bundle != null){
            list  = bundle.getStringArrayList("test1");
//            Log.d(TAG, "현재 탭은  ?!"+bundle.getInt("current tab"));
            Log.d(TAG, "getArguments() ");
        }

        layoutManager = new LinearLayoutManager(container.getContext());
        rvFridge.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerAdapter(list);
        rvFridge.setAdapter(recyclerAdapter);

        Log.d(TAG, "onCreateView");
        return view;
    }

    static class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder>{
        ArrayList<String> list = new ArrayList<>();

        public RecyclerAdapter(ArrayList<String> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            textView.setText(list.get(position));

        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
