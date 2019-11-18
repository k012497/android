package com.example.viewpagertest;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    // 프래그먼트 교체를 보여주는 역할
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return Fragment1.newInstance();
            case 1: return Fragment2.newInstance();
            case 2: return Fragment3.newInstance();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    // 상단의 탭레이아웃 제목에 대한 텍스트
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "1";
            case 1: return "2";
            case 2: return "3";
            default: return null;
        }
    }
}
