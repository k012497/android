package com.example.dynamictablayouttest;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentTest extends Fragment {
    View view;
    RecyclerView rvRefrigerator, rvFridge, rvFreezer, rvPantry;
    LinearLayoutManager layoutManagerT;
    LinearLayoutManager layoutManagerC;
    RecyclerView.Adapter<TitleViewHolder> adapterT;
    RecyclerView.Adapter<ContentViewHolder> adapterC1, adapterC2, adapterC3;

    TextView tvName, tvFood;

    // test1
    static ArrayList<String> foodList = new ArrayList<String>();

    //test2
    static ArrayList<String> foodList1 = new ArrayList<String>();
    static ArrayList<String> foodList2 = new ArrayList<String>();
    static ArrayList<String> foodList3 = new ArrayList<String>();
    static ArrayList<String> refrigeratorList = new ArrayList<String>();
    static Bundle bundle = new Bundle(1);


    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.refrigerator, null, false);

        rvRefrigerator = view.findViewById(R.id.rvRefrigerator);
        rvFreezer = view.findViewById(R.id.rvFreezer);
        rvFridge = view.findViewById(R.id.rvFridge);
        rvPantry = view.findViewById(R.id.rvPantry);

        foodList.add("123");
        foodList.add("456");
        foodList.add("789");

        foodList1.add("냉장 옥수수");
        foodList1.add("냉장 고구마");
        foodList1.add("냉장 치킨");

        foodList2.add("냉동 aaa");
        foodList2.add("냉동 bbb");
        foodList2.add("냉동 ccc");

        foodList3.add("실온 123");
        foodList3.add("실온 456");
        foodList3.add("실온 789");

        refrigeratorList.add("메인 냉장고");
        refrigeratorList.add("김치냉장고");

        bundle.putStringArrayList("fridge", foodList1);
        bundle.putStringArrayList("freezer", foodList2);
        bundle.putStringArrayList("pantry", foodList3);

        layoutManagerT = new LinearLayoutManager(container.getContext());
        layoutManagerT.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvRefrigerator.setLayoutManager(layoutManagerT);
        adapterT = new TitleAdpater(refrigeratorList);
        rvRefrigerator.setAdapter(adapterT);

///////////////////////////////////////
        layoutManagerC = new LinearLayoutManager(container.getContext());
        rvFridge.setLayoutManager(layoutManagerC);
        adapterC1 = new ContentAdapter(foodList1);
        rvFridge.setAdapter(adapterC1);

        layoutManagerC = new LinearLayoutManager(container.getContext());
        rvFreezer.setLayoutManager(layoutManagerC);
        adapterC2 = new ContentAdapter(foodList2);
        rvFreezer.setAdapter(adapterC2);

        layoutManagerC = new LinearLayoutManager(container.getContext());
        rvPantry.setLayoutManager(layoutManagerC);
        adapterC3 = new ContentAdapter(foodList3);
        rvPantry.setAdapter(adapterC3);

        return view;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    class TitleAdpater extends RecyclerView.Adapter<TitleViewHolder>{
        ArrayList<String> list = new ArrayList<String>();

        public TitleAdpater(ArrayList<String> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public TitleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.refrigerator_item, null);
            TitleViewHolder titleViewHolder = new TitleViewHolder(view);
            return titleViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull TitleViewHolder holder, final int position) {
            tvName.setText(list.get(position));

            tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("click", list.get(position));
                    switch (list.get(position)){
                        case "메인 냉장고":
                            Log.d("onClick", "메인냉장고");

                            // 비우기
                            foodList1.clear();
                            foodList2.clear();
                            foodList3.clear();
                            rvFridge.removeAllViews();
                            rvFreezer.removeAllViews();
                            rvPantry.removeAllViews();

                            // 새로운 값 받아오기
                            // switch문 통합해서 DB에서 받으려면 id로 쿼리문실행해서 리스트에 add
                            foodList1.add("메인 냉장실 aaa");
                            foodList1.add("메인 냉장실 bbb");
                            foodList1.add("메인 냉장실 ccc");
                            foodList2.add("메인 냉동 1aaa");
                            foodList2.add("메인 냉동 1bbb");
                            foodList2.add("메인 냉동 1ccc");
                            foodList3.add("메인 실온 참치");
                            Log.d("메인냉장고 실온", foodList3.get(0));
                            Log.d("메인냉장고 냉장실", foodList1.get(2));

                            // notify
                            adapterC1.notifyDataSetChanged();
                            adapterC2.notifyDataSetChanged();
                            adapterC3.notifyDataSetChanged();
                            Log.d("메인냉장고 실온 notify", foodList3.get(0));
                            Log.d("메인냉장고 냉장실 notify", foodList1.get(2));
                            break;

                        case "김치냉장고":
                            Log.d("onClick", "김치냉장고");
                            foodList1.clear();
                            foodList2.clear();
                            foodList3.clear();
                            rvFridge.removeAllViews();
                            rvFreezer.removeAllViews();
                            rvPantry.removeAllViews();


                            foodList1.add("김치 냉장 123");
                            foodList1.add("김치 냉장 456");
                            foodList2.add("김치 냉동 2");
                            foodList2.add("김치 냉동 2df");
                            foodList3.add("김치 실온 김치 된장");
                            foodList3.add("김치 실온 김치 고추장");

                            adapterC1.notifyDataSetChanged();
                            adapterC2.notifyDataSetChanged();
                            adapterC3.notifyDataSetChanged();

                            break;
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            Log.d("getItemCount","냉장고 "+list.size()+"개");
            return list.size();
        }
    }

    class TitleViewHolder extends RecyclerView.ViewHolder{

        public TitleViewHolder(@NonNull final View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////

    class ContentAdapter extends RecyclerView.Adapter<ContentViewHolder>{
        ArrayList<String> list = new ArrayList<String>();

        Bundle bundle;
        public ContentAdapter(ArrayList<String> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public ContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Log.d("onCreateView", (parent.getId() == R.id.rvFridge)+"");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        ContentViewHolder contentiewHolder = new ContentViewHolder(view);
        return contentiewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ContentViewHolder holder, int position) {
            tvFood.setText(list.get(position));
            Log.d("만들어지는중", list.get(0) + list.get(position));
        }

        @Override
        public int getItemCount() {
            Log.d("getCount", list.get(0)+list.size()+"개 만드는중");

            return list.size();
        }
    }

    class ContentViewHolder extends RecyclerView.ViewHolder{
        public ContentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFood = itemView.findViewById(R.id.tvFood);
        }
    }

}