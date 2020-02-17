package com.example.netflix_project.src.main.ViewPager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.netflix_project.R;
import com.example.netflix_project.src.main.ViewPager.Fragment.FirstFragment;
import com.example.netflix_project.src.main.ViewPager.Fragment.FourthFragment;
import com.example.netflix_project.src.main.ViewPager.Fragment.SecondFragment;
import com.example.netflix_project.src.main.ViewPager.Fragment.ThirdFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class ViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<String> mList;
    private LayoutInflater mInflater;

    public ViewPagerAdapter(Context context,List<String> stringList){
        super();
        mInflater=LayoutInflater.from(context);
        this.mList=stringList;
        mContext=context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view=mInflater.inflate(R.layout.fragment_first,null);

       // TextView textView=view.findViewById(R.id.pager_tv_first);
       // textView.setText("FRAGMENT PAGE"+position);
        container.addView(view);
        return view;


    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Fragment "+position;
    }



}
