package com.example.nestedfragmenttest;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ParentFragment extends Fragment {

    public static final String TAG_CHILD = "TAG_CHILD";
    public static final String KEY_NUMBER = "KEY_NUMBER";
    private int mNumber = 0;

    private FragmentManager.OnBackStackChangedListener mListener = new FragmentManager.OnBackStackChangedListener() {
        @Override
        public void onBackStackChanged() {
            FragmentManager fragmentManager = getChildFragmentManager();
            int count = 0;
            for (Fragment f : fragmentManager.getFragments()) {
                if (f != null) {
                    count++;
                }
            }
            mNumber = count;
        }
    };

    public static ParentFragment getInstance() {
        return new ParentFragment();
    }

    public ParentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parent, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager childFragment = getChildFragmentManager();
                childFragment.beginTransaction()
                        .add(R.id.fragment_container, ChildFragment.getInstance(mNumber))
                        .addToBackStack(null)
                        .commit();
            }
        });

        view.findViewById(R.id.remove_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mNumber == 0) {
                    return;
                }
                FragmentManager childFragmentManager = getChildFragmentManager();
                childFragmentManager.popBackStack();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            mNumber = savedInstanceState.getInt(KEY_NUMBER, 0);
        }

        FragmentManager childFragmentManager = getChildFragmentManager();
        Fragment fragment = childFragmentManager.findFragmentByTag(TAG_CHILD);
        Log.d("ParentFragment", "onActivityCreated childFragment=" + fragment + ", mNumber=" + mNumber);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = childFragmentManager.beginTransaction();
            transaction.add(R.id.fragment_container, ChildFragment.getInstance(mNumber), TAG_CHILD);
            transaction.commit();
        }
        childFragmentManager.addOnBackStackChangedListener(mListener);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("ParentFragment", "onSaveInstanceState mNumber=" + mNumber);
        outState.putInt(KEY_NUMBER, mNumber);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("ParentFragment", "onDestroy");
    }

}