package com.example.dynamictablayouttest;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTabHost;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.AbstractCollection;
import java.util.ArrayList;

public class MainTest extends Fragment {
    static RecyclerView rvFridge;
    static RecyclerAdapter recyclerAdapter;
    static RecyclerView.LayoutManager layoutManager;
    static TextView textView;

    static ArrayList<String> list = new ArrayList<String>();

    public MainTest() {
    }

    public MainTest(int contentLayoutId) {
        super(contentLayoutId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.refrigerator_list, null, false);
        rvFridge = view.findViewById(R.id.rvFridge);


        FragmentTabHost host = (FragmentTabHost) view.findViewById(android.R.id.tabhost);
        host.setup(container.getContext(), getChildFragmentManager(), android.R.id.tabcontent);

        TabHost.TabSpec tabSpec1 = host.newTabSpec("tab1");
        tabSpec1.setIndicator("1");
        Bundle bundle1 = new Bundle();
        ArrayList<String> str1 = new ArrayList<String>();
        str1.add("111");
        str1.add("222");
        str1.add("333");
//        bundle1.putString("name", "Tab1");
        bundle1.putStringArrayList("name", str1);
        host.addTab(tabSpec1, SampleFragment.class, bundle1);

        TabHost.TabSpec tabSpec2 = host.newTabSpec("tab2");

        tabSpec2.setIndicator("2");
        Bundle bundle2 = new Bundle();
        ArrayList<String> str2 = new ArrayList<String>();
        str2.add("aaa");
        str2.add("bbb");
//        str2.add("ccc");
//        bundle2.putString("name", "Tab2");
        bundle2.putStringArrayList("name", str2);
        host.addTab(tabSpec2, SampleFragment.class, bundle2);

        TabHost.TabSpec tabSpec3 = host.newTabSpec("tab3");

//        tabSpec3.setIndicator("3");
//        Bundle bundle3 = new Bundle();
//        bundle3.putString("name", "Tab3");
//        host.addTab(tabSpec3, SampleFragment.class, bundle3);



        return view;
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.refrigerator_list);
//        FragmentTabHost host = (FragmentTabHost) findViewById(android.R.id.tabhost);
//        host.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
//
//        TabHost.TabSpec tabSpec1 = host.newTabSpec("tab1");
//        Button button1 = new Button(this);
//        button1.setBackgroundResource(R.drawable.apple_logo);
//        tabSpec1.setIndicator(button1);
//        Bundle bundle1 = new Bundle();
//        bundle1.putString("name", "Tab1");
//        host.addTab(tabSpec1, SampleFragment.class, bundle1);
//
//        TabHost.TabSpec tabSpec2 = host.newTabSpec("tab2");
//        Button button2 = new Button(this);
//        button2.setBackgroundResource(R.drawable.apple_logo);
//        tabSpec2.setIndicator(button2);
//        Bundle bundle2 = new Bundle();
//        bundle2.putString("name", "Tab2");
//        host.addTab(tabSpec2, SampleFragment.class, bundle2);
//
//        TabHost.TabSpec tabSpec3 = host.newTabSpec("tab3");
//        Button button3 = new Button(this);
//        button3.setBackgroundResource(R.drawable.apple_logo);
//        tabSpec3.setIndicator(button3);
//        Bundle bundle3 = new Bundle();
//        bundle3.putString("name", "Tab3");
//        host.addTab(tabSpec3, SampleFragment.class, bundle3);
//    }

     static public class SampleFragment extends Fragment {
         @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.refrigerator_list, null, false);
//            TextView textView = view.findViewById(R.id.textView);
//            textView.setGravity(Gravity.CENTER);
//            textView.setText(getArguments().getString("name"));

//            list = getArguments().getStringArrayList("name");
//
//            TextView textView = new TextView(getActivity());
//            textView.setGravity(Gravity.CENTER);
//            textView.setText(list.get(0));

            layoutManager = new LinearLayoutManager(container.getContext());
            rvFridge.setLayoutManager(layoutManager);
            recyclerAdapter = new RecyclerAdapter();
            rvFridge.setAdapter(recyclerAdapter);


            return view;
        }

    }

    static class RecyclerAdapter extends RecyclerView.Adapter<RefrigeratorFragment.ViewHolder>{

        public RecyclerAdapter() {
        }

        @NonNull
        @Override
        public RefrigeratorFragment.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
            RefrigeratorFragment.ViewHolder viewHolder = new RefrigeratorFragment.ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RefrigeratorFragment.ViewHolder holder, int position) {
//            textView.setText(list.get(position));

        }

        @Override
        public int getItemCount() {
//            return 0;
            return list.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}