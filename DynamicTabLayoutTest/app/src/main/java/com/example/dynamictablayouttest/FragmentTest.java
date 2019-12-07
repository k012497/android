package com.example.dynamictablayouttest;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.aviran.cookiebar2.CookieBar;
import org.aviran.cookiebar2.OnActionClickListener;

import java.util.ArrayList;

public class FragmentTest extends Fragment implements View.OnClickListener {
    View view;
    Button btnSelect;
    RecyclerView rvFridge, rvFreezer, rvPantry;
    LinearLayoutManager layoutManagerC;
    RecyclerView.Adapter<ContentViewHolder> adapterC1, adapterC2, adapterC3;

    TextView tvFood;
    TextView tvName;
    Activity activity;

    // test1
    static ArrayList<String> foodList = new ArrayList<String>();

    //test2
    static ArrayList<String> foodList1 = new ArrayList<String>();
    static ArrayList<String> foodList2 = new ArrayList<String>();
    static ArrayList<String> foodList3 = new ArrayList<String>();
    static ArrayList<String> refrigeratorList = new ArrayList<String>();
    static Bundle bundle = new Bundle(1);

    Context context;
    boolean removeMode;

    public FragmentTest(Activity activity) {
        this.activity = activity;

    }

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.refrigerator, null, false);
        context = container.getContext();

        rvFreezer = view.findViewById(R.id.rvFreezer);
        rvFridge = view.findViewById(R.id.rvFridge);
        rvPantry = view.findViewById(R.id.rvPantry);
        btnSelect = view.findViewById(R.id.btnSelect);

        refrigeratorList.add("메인 냉장고");
        refrigeratorList.add("김치냉장고");

        bundle.putStringArrayList("fridge", foodList1);
        bundle.putStringArrayList("freezer", foodList2);
        bundle.putStringArrayList("pantry", foodList3);

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

        setContent();

        btnSelect.setOnClickListener(this);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        if(removeMode){
            inflater.inflate(R.menu.remove_mode_menu, menu);
        }else{
            inflater.inflate(R.menu.manage_food_menu, menu);
            menu.getItem(0).setIcon(R.drawable.add);
            menu.getItem(1).setIcon(R.drawable.remove);
            menu.getItem(2).setIcon(R.drawable.search);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                // 아이템 추가 인텐트
                Log.d("메뉴클릭", "action_add");
                break;

            case R.id.action_remove:
                // 삭제 모드로 전환
                // - 달린 아이템들
                Log.d("메뉴클릭", "action_remove");
                removeMode = true;
                getActivity().invalidateOptionsMenu();
                break;

            case R.id.action_search:
                // 해당 냉장고속 재료 검색
                Log.d("메뉴클릭", "action_search");
                break;

            case R.id.action_done:
                // 삭제 모드 해제
                Log.d("메뉴클릭","action_back");
                removeMode = false;
                getActivity().invalidateOptionsMenu();
                break;

            case R.id.action_delete:
                // 선택한 목록 삭제
                Log.d("메뉴클릭","action_delete");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setContent() {
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
        btnSelect.setText(refrigeratorList.get(0));
    }


    @Override
    public void onClick(final View v) {
        CookieBar.build(activity)
                .setCustomView(R.layout.cookie_lsit_view)
                .setCustomViewInitializer(new CookieBar.CustomViewInitializer() {
                    @Override
                    public void initView(View view) {
                        tvName = view.findViewById(R.id.tvName);

                        ListView listView = view.findViewById(R.id.listView);
                        ListViewAdapter listViewAdapter = new ListViewAdapter();
                        listView.setAdapter(listViewAdapter);
                    }
                })
                .setDuration(10000)
                .setAction("Close", new OnActionClickListener() {
                    @Override
                    public void onClick() {
                        CookieBar.dismiss(activity);
                    }
                })
                .setTitle("test")
                .setSwipeToDismiss(true)
                .setEnableAutoDismiss(true)
                .setCookiePosition(CookieBar.BOTTOM)
                .show();

//        popupMenu = new PopupMenu(context, v);
//        int i = 1;
//        for(String str : refrigeratorList){
//            popupMenu.getMenu().add(0, i++, 0, str);
//        }
//
//        PopupMenu.OnMenuItemClickListener listener = new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId()){
//                    case 1:
//                        Log.d("onClick", "메인냉장고");
//
//                        // 비우기
//                        foodList1.clear();
//                        foodList2.clear();
//                        foodList3.clear();
//                        rvFridge.removeAllViews();
//                        rvFreezer.removeAllViews();
//                        rvPantry.removeAllViews();
//
//                        // 새로운 값 받아오기
//                        // switch문 통합해서 DB에서 받으려면 id로 쿼리문실행해서 리스트에 add
//                        foodList1.add("메인 냉장실 aaa");
//                        foodList1.add("메인 냉장실 bbb");
//                        foodList1.add("메인 냉장실 ccc");
//                        foodList2.add("메인 냉동 1aaa");
//                        foodList2.add("메인 냉동 1bbb");
//                        foodList2.add("메인 냉동 1ccc");
//                        foodList3.add("메인 실온 참치");
//                        Log.d("메인냉장고 실온", foodList3.get(0));
//                        Log.d("메인냉장고 냉장실", foodList1.get(2));
//
//                        // notify
//                        adapterC1.notifyDataSetChanged();
//                        adapterC2.notifyDataSetChanged();
//                        adapterC3.notifyDataSetChanged();
//                        Log.d("메인냉장고 실온 notify", foodList3.get(0));
//                        Log.d("메인냉장고 냉장실 notify", foodList1.get(2));
//                        btnSelect.setText(item.getTitle().toString());
//
//                        break;
//
//                    case 2:
//                        Log.d("onClick", "김치냉장고");
//                        foodList1.clear();
//                        foodList2.clear();
//                        foodList3.clear();
//                        rvFridge.removeAllViews();
//                        rvFreezer.removeAllViews();
//                        rvPantry.removeAllViews();
//
//
//                        foodList1.add("김치 냉장 123");
//                        foodList1.add("김치 냉장 456");
//                        foodList2.add("김치 냉동 2");
//                        foodList2.add("김치 냉동 2df");
//                        foodList3.add("김치 실온 김치 된장");
//                        foodList3.add("김치 실온 김치 고추장");
//
//                        adapterC1.notifyDataSetChanged();
//                        adapterC2.notifyDataSetChanged();
//                        adapterC3.notifyDataSetChanged();
//                        btnSelect.setText(item.getTitle().toString());
//                        break;
//                }
//                return false;
//            }
//        };
//
//        popupMenu.setOnMenuItemClickListener(listener);
//
//
//
//        popupMenu.show();

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
//    class TitleAdpater extends RecyclerView.Adapter<TitleViewHolder>{
//        ArrayList<String> list = new ArrayList<String>();
//
//        public TitleAdpater(ArrayList<String> list) {
//            this.list = list;
//        }
//
//        @NonNull
//        @Override
//        public TitleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.refrigerator_item, null);
//            TitleViewHolder titleViewHolder = new TitleViewHolder(view);
//            return titleViewHolder;
//        }
//
//        @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
//        @Override
//        public void onBindViewHolder(@NonNull TitleViewHolder holder, final int position) {
//            tvName.setText(list.get(position));
//
//            tvName.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d("click", list.get(position));
//                    switch (list.get(position)){
//                        case "메인 냉장고":
//                            Log.d("onClick", "메인냉장고");
//
//                            // 비우기
//                            foodList1.clear();
//                            foodList2.clear();
//                            foodList3.clear();
//                            rvFridge.removeAllViews();
//                            rvFreezer.removeAllViews();
//                            rvPantry.removeAllViews();
//
//                            // 새로운 값 받아오기
//                            // switch문 통합해서 DB에서 받으려면 id로 쿼리문실행해서 리스트에 add
//                            foodList1.add("메인 냉장실 aaa");
//                            foodList1.add("메인 냉장실 bbb");
//                            foodList1.add("메인 냉장실 ccc");
//                            foodList2.add("메인 냉동 1aaa");
//                            foodList2.add("메인 냉동 1bbb");
//                            foodList2.add("메인 냉동 1ccc");
//                            foodList3.add("메인 실온 참치");
//                            Log.d("메인냉장고 실온", foodList3.get(0));
//                            Log.d("메인냉장고 냉장실", foodList1.get(2));
//
//                            // notify
//                            adapterC1.notifyDataSetChanged();
//                            adapterC2.notifyDataSetChanged();
//                            adapterC3.notifyDataSetChanged();
//                            Log.d("메인냉장고 실온 notify", foodList3.get(0));
//                            Log.d("메인냉장고 냉장실 notify", foodList1.get(2));
//                            break;
//
//                        case "김치냉장고":
//                            Log.d("onClick", "김치냉장고");
//                            foodList1.clear();
//                            foodList2.clear();
//                            foodList3.clear();
//                            rvFridge.removeAllViews();
//                            rvFreezer.removeAllViews();
//                            rvPantry.removeAllViews();
//
//
//                            foodList1.add("김치 냉장 123");
//                            foodList1.add("김치 냉장 456");
//                            foodList2.add("김치 냉동 2");
//                            foodList2.add("김치 냉동 2df");
//                            foodList3.add("김치 실온 김치 된장");
//                            foodList3.add("김치 실온 김치 고추장");
//
//                            adapterC1.notifyDataSetChanged();
//                            adapterC2.notifyDataSetChanged();
//                            adapterC3.notifyDataSetChanged();
//
//                            break;
//                    }
//                }
//            });
//
//        }
//
//        @Override
//        public int getItemCount() {
//            Log.d("getItemCount","냉장고 "+list.size()+"개");
//            return list.size();
//        }
//    }
//
//
//
//    class TitleViewHolder extends RecyclerView.ViewHolder{
//
//        public TitleViewHolder(@NonNull final View itemView) {
//            super(itemView);
//            tvName = itemView.findViewById(R.id.tvName);
//        }
//    }

////////////////////////////////////////////////////////////////////////////////////////////////////////

    class ListViewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return refrigeratorList.size();
        }

        @Override
        public Object getItem(int position) {
            return refrigeratorList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.refrigerator_item, null);

            tvName = convertView.findViewById(R.id.tvName);
            tvName.setText(refrigeratorList.get(position));

            tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (position){
                        case 0:
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

                    case 1:
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
                    btnSelect.setText(refrigeratorList.get(position));
                    CookieBar.dismiss(activity);
                }
            });

            return convertView;
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
//            Log.d("getCount", list.get(0)+list.size()+"개 만드는중");

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