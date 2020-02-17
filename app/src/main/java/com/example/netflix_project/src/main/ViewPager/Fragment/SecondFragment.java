package com.example.netflix_project.src.main.ViewPager.Fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.netflix_project.R;

public class SecondFragment extends Fragment {
    Toolbar mToolbar;

    // Store instance variables
    public static  SecondFragment newInstance() {
        SecondFragment fragment = new  SecondFragment();
        return fragment;
    }
    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        //툴바
        mToolbar=view.findViewById(R.id.first_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);


//        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
//        View viewToolbar = getActivity().getLayoutInflater().inflate(R.layout.actionbar, null);
//        actionBar.setDisplayShowCustomEnabled(true);
//        actionBar.setDisplayShowTitleEnabled(false);
//
//        //툴바 커스텀
//        actionBar.setCustomView(viewToolbar, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));

        return view;
    }
}

