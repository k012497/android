package com.example.functiontest3;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Fragment1 extends Fragment {


    private View view;
    private ArrayList<com.example.functiontest3.MainData> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MainAdapter mainAdapter;
    public boolean removeMode;

    private OnFragmentInteractionListener mListener;// 객체참조변수
    // customViewHolder 에 들어가있는 아이들
    public ImageView imgProfile;
    public TextView txtName;
    public TextView txtContent;
    public ImageView delete, open;
    public CheckBox checkBox;
    public LinearLayout llContainer;
//    public boolean showCheckState = false;

    // 체크박스 값 저장
    int i = 0;
    ArrayList<Integer> checkedList = new ArrayList<Integer>(); // 현재 체크된 체크박스의 index모음 - delete 시 사용
    ArrayList<CheckBox> checkBoxes = new ArrayList<CheckBox>(); // 현재 리사이클러뷰에 있는 아이템의 체크박스 모음 - Visiblity 관리용
    Menu menu;


    private static final String TAG = "MainActivity";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        linearLayoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        list = new ArrayList<>();
        mainAdapter = new MainAdapter(R.layout.recyclerview_item, list);
        recyclerView.setAdapter(mainAdapter);

        setHasOptionsMenu(true);
        return view;
    }



    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (removeMode) {
            this.menu = menu;
            inflater.inflate(R.menu.remove_mode_menu, menu);
        } else {
            inflater.inflate(R.menu.manage_food_menu, menu);
            menu.getItem(0).setIcon(R.drawable.add);
            menu.getItem(1).setIcon(R.drawable.delete);
            menu.getItem(2).setIcon(R.drawable.search);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add:
                // 아이템 추가 인텐트번
                Log.d("메뉴클릭", "action_add");

                MainData mainData = new MainData(R.mipmap.ic_launcher, i++ + "", "졸리다");
                list.add(mainData);
                mainAdapter.notifyDataSetChanged();
                break;

            case R.id.action_remove:
                // 삭제 모드로 전환
                // - 달린 아이템들
                Log.d("메뉴클릭", "action_remove");
                removeMode = true;
                getActivity().invalidateOptionsMenu();

                for(CheckBox checkBox : checkBoxes){
                    checkBox.setVisibility(View.VISIBLE);
                }
//                showCheckState = true;

                break;

            case R.id.action_search:
                // 해당 냉장고속 재료 검색
                Log.d("메뉴클릭", "action_search");
                break;

            case R.id.action_done:
                // 삭제 모드 해제
                Log.d("메뉴클릭", "action_done");
                removeMode = false;
                getActivity().invalidateOptionsMenu();
                for(CheckBox checkBox : checkBoxes){
                    checkBox.setVisibility(View.INVISIBLE);
                    checkBox.setChecked(false);
                }
//                showCheckState = false;
                break;

            case R.id.action_delete:
                // 선택한 목록 삭제
                Log.d("메뉴클릭", "action_delete");

                for(int position : checkedList){
                    Log.d("delete", position+"번 삭제");
                    // 어댑터에 연결된 데이터에서 제거
                    list.remove(position);
                    // 체크박스 리스트에서 제거
                    // DB에서도 제거
                }
                checkedList.clear();
                checkBoxes.clear();
                recyclerView.removeAllViews();
                mainAdapter.notifyDataSetChanged();

                // 자동으로 완료
                menu.performIdentifierAction(R.id.action_done, 0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {

        //1. context : 제공이 된다.(OnCreateViewHolder 에서 제공이 되는데, ViewGroup 으로 제공이 된다.)
        private int layout; // 부분화면 객체를 생성자로 받는것이다.
        private ArrayList<MainData> list = new ArrayList<>();

        public MainAdapter(int layout, ArrayList<MainData> list) {
            this.layout = layout;
            this.list = list;
        }


        @NonNull //viewHolder 에 있는 화면을 객체화해서 해당된 viewHolder 를 리턴한다.
        @Override //getView 와 같다.
        public MainAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            // 레이아웃을 인플레이터 해서 메모리에 로딩을 한다.
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(layout, viewGroup, false);

            // 해당된 viewHolde r의 아이디를 찾는다.
            CustomViewHolder viewHolder = new CustomViewHolder(view);
            return viewHolder;// getView 에서의 리턴값과 같다.
        }

        @Override // 이곳에서 바인딩을 해준다. 바인딩을 해주면 이 내용이 CustomViewHolder 에 들어온다.
        public void onBindViewHolder(@NonNull final MainAdapter.CustomViewHolder customViewHolder, final int position) {

            imgProfile.setImageResource(list.get(position).getImgProfile());
            txtName.setText(list.get(position).getTxtName());
            txtContent.setText(list.get(position).getTxtContent());
            customViewHolder.itemView.setTag(position);

            customViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), list.get(position).getTxtName() + " 선택", Toast.LENGTH_SHORT).show();
                }
            });


            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    list.remove(position);
                    notifyDataSetChanged();

                    String currentName = txtName.getText().toString().trim();
                    Toast.makeText(v.getContext(), currentName + " 삭제 완료", Toast.LENGTH_SHORT).show();
                }
            });

            open.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String currentName = txtName.getText().toString().trim();

                    Bundle bundle = new Bundle(1);
                    bundle.putString("name", currentName);
                    mListener.onFragmentInteraction(bundle);


                    Toast.makeText(v.getContext(), currentName, Toast.LENGTH_SHORT).show();
                }
            });

            // 체크박스 리스트에 담아놓기 - Visibility 관리용
            checkBoxes.add(checkBox);
            Log.d("checkBoxes에 add중", list.get(position).getTxtName()+"번 만들어짐");

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        checkedList.add(position);
                        Log.d("onCheckedChanged", position+"번 체크 설정");
                    } else {
                        checkedList.remove(position);
                        Log.d("onCheckedChanged", position+"번 체크 해제");
                    }
                }
            });

        }

        @Override //getCount = list 의 사이즈를 준다.
        public int getItemCount() {
            return (list != null) ? (list.size()) : (0);
            //리스트 값이 null 이 아니면 사이즈를 주고 null 이라면 0을 준다.
        }

        //아까의 getView 를 분업화, inflater 와 binding, fidViewId 를 분업화.
        //그리고 매칭은 BindViewHolder 에서 시킨다.
        //현재 HolderView 가 객체화 되면 이곳에서 findViewId를 하게 된다.
        public class CustomViewHolder extends RecyclerView.ViewHolder {

            //itemView 에 viewHolder가 객체가 된 레이아웃 인플레이터가 전달이 된 상태이다. = 주소가 전달이 된다.
            public CustomViewHolder(@NonNull View itemView) {

                super(itemView);//여기에 레이아웃 인플레이터가 전달이 된 상태이다.
                imgProfile = itemView.findViewById(R.id.imgProfile);
                txtName = itemView.findViewById(R.id.txtName);
                txtContent = itemView.findViewById(R.id.txtContent);
                delete = itemView.findViewById(R.id.delete);
                open = itemView.findViewById(R.id.open);
                checkBox = itemView.findViewById(R.id.checkBox);
                llContainer = view.findViewById(R.id.llContainer);
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            // 이 컨텍스트 속에 리스너가 들어있냐, 즉 자식이냐
            mListener = (OnFragmentInteractionListener) context; // MainActivity(자식)의 객체를 가져옴 - 부모로 형변환
        } else {
            throw new RuntimeException(context.toString() + "OnFragmentInteractionListener을 구현하라");
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Bundle bundle); // 추상메소드
    }
}

