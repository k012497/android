package com.example.dynamictablayouttest;

import android.app.TabActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTabHost;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Fragment1Activity extends Fragment {
    View view;
    FragmentTabHost tabHost;
//    OnFragmentInteractionListener mListener;

    private TextView textView;
    ArrayList<String> list = new ArrayList<String>();

    RecyclerView rvFridge;
    RecyclerAdapter recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.refrigerator_list, null, false);


        tabHost = view.findViewById(android.R.id.tabhost);
        tabHost.setup(getActivity(), getChildFragmentManager(), android.R.id.tabcontent);

        rvFridge = view.findViewById(R.id.rvFridge);
        layoutManager = new LinearLayoutManager(container.getContext());
        rvFridge.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerAdapter(list);
        rvFridge.setAdapter(recyclerAdapter);


        final Bundle bundle1 = new Bundle(1);
        ArrayList<String> list1 = new ArrayList<String>();
        list1.add("123");
        list1.add("456");
        list1.add("789");
        bundle1.putStringArrayList("test1", list1);

        final Bundle bundle2 = new Bundle(1);
        final ArrayList<String> list2 = new ArrayList<String>();
        list2.add("abc");
        list2.add("def");
        list2.add("ghi");
        bundle2.putStringArrayList("test1", list2);


        tabHost.addTab(tabHost.newTabSpec("first").setIndicator("1"), RefrigeratorFragment.class, bundle1);
        tabHost.addTab(tabHost.newTabSpec("first").setIndicator("2"), RefrigeratorFragment.class, bundle2);
//        tabHost.addTab(tabHost.newTabSpec("first").setIndicator("1"), RefrigeratorFragment.class, null);
//        tabHost.addTab(tabHost.newTabSpec("first").setIndicator("2"), RefrigeratorFragment.class, null);

//        tabHost.setCurrentTab(1);
        Log.d("RefrigeratorFragment", tabHost.getCurrentTab() + "번 탭 선택중");
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                Fragment fgActivity = new RefrigeratorFragment();

                switch (tabHost.getCurrentTab()){
                    case 0:
//                        list1.clear();
//                        list1.add("abc");
//                        list1.add("def");
//                        list1.add("ghi");
//                        RefrigeratorFragment.recyclerAdapter.notifyDataSetChanged();
                        Log.d("RefrigeratorFragment",tabHost.getCurrentTab()+"?!");
//                        mListener.onFragmentInteraction(bundle1);

                    case 1:
//                        list1.clear();
//                        list1.add("32523");
//                        RefrigeratorFragment.recyclerAdapter.notifyDataSetChanged();
                        Log.d("RefrigeratorFragment",tabHost.getCurrentTab()+"?!");
//                        mListener.onFragmentInteraction(bundle2);
                }

                ft.replace(R.id.screen1, fgActivity);
                ft.commit();
            }
        });

        return view;
    }

    class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder>{
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

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        if(context instanceof OnFragmentInteractionListener){
//            // 이 컨텍스트 속에 리스너가 들어있냐, 즉 자식이냐
//            mListener = (OnFragmentInteractionListener) context; // MainActivity(자식)의 객체를 가져옴 - 부모로 형변환
//        } else {
//            throw new RuntimeException(context.toString() + "OnFragmentInteractionListener을 구현하라");
//        }
//    }

//    public interface OnFragmentInteractionListener{
//        void onFragmentInteraction(Bundle bundle); // 추상메소드
//    }

}
