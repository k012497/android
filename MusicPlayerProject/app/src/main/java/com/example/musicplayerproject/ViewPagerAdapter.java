package com.example.musicplayerproject;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        Log.d("Main", "make Adapter");
    }

    // 프래그먼트 교체를 보여주는 역할
    @Override
    public Fragment getItem(int position) {
        Log.d("Main", "getItem");
        switch (position){
            case 0: return SDCardFragment.newInstance();
            case 1: return Fragment2.newInstance();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        Log.d("Main", "getCount");
        return 2;
    }

    // 상단의 탭레이아웃 제목에 대한 텍스트
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        Log.d("Main", "getPageTitle");
        switch (position){
            case 0: return "1";
            case 1: return "2";
            default: return null;
        }
    }
}