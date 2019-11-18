package com.example.recyclerviewtest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {
    //context는 제공됨 (onCreateViewHolder -> ViewGroup으로 제공)
    private int layout;
    private ArrayList<MainItem> list = new ArrayList<MainItem>();

    public MainAdapter(int layout, ArrayList<MainItem> list) {
        this.layout = layout;
        this.list = list;
    }

    // getView 분업 - viewHolder 화면을 객체화해서 리턴
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        // 해당된 viewHolder의 아이디를 찾는다.
        CustomViewHolder customViewHolder = new CustomViewHolder(view);
        return customViewHolder; // getView에서의 리턴값과 같
    }

    // getView 분업 - 매치 시키기
    @Override
    public void onBindViewHolder(@NonNull final MainAdapter.CustomViewHolder customViewHolder, final int position) {
        customViewHolder.ivProfile.setImageResource(list.get(position).getProfile());
        customViewHolder.tvName.setText(list.get(position).getName());
        customViewHolder.tvContent.setText(list.get(position).getContent());
        customViewHolder.itemView.setTag(position);

        customViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentName = customViewHolder.tvName.getText().toString().trim();
                Toast.makeText(v.getContext(), currentName, Toast.LENGTH_SHORT).show();
            }
        });

        customViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(v.getContext(), "delete " + list.get(position).getName(), Toast.LENGTH_SHORT).show();
                list.remove(position);
                notifyDataSetChanged();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
//            return list.size(); 대신 고급 ver
        return list != null ? list.size() : 0;
    }

    // getView 분업 - 아이디 찾기
    class CustomViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivProfile;
        private TextView tvName;
        private TextView tvContent;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfile = itemView.findViewById(R.id.ivProfile);
            tvName = itemView.findViewById(R.id.tvName);
            tvContent = itemView.findViewById(R.id.tvContent);
        }
    }
}
