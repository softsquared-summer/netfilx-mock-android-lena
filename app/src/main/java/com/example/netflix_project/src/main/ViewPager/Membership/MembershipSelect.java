package com.example.netflix_project.src.main.ViewPager.Membership;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.netflix_project.R;
import com.google.android.material.tabs.TabLayout;

public class MembershipSelect extends AppCompatActivity {

    public static TabHost tabHost;

    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.membership_select);

        //툴바 커스텀
        mToolbar=findViewById(R.id.membership_toolbar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        View viewToolbar = getLayoutInflater().inflate(R.layout.actionbar_white, null);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setCustomView(viewToolbar, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));

        TabHost tabHost=findViewById(R.id.tabhost);
        tabHost.setup();

       // 탭에 이미지 넣어주기
        ImageView mTabWidget1=new ImageView(this);
        mTabWidget1.setImageResource(R.drawable.membership_tab_1);

        ImageView mTabWidget2=new ImageView(this);
        mTabWidget2.setImageResource(R.drawable.membership_tab_2);

        ImageView mTabWidget3=new ImageView(this);
        mTabWidget3.setImageResource(R.drawable.membership_tab_3);

        TabHost.TabSpec tabSpec1=tabHost.newTabSpec("basic");
        tabSpec1.setContent(R.id.basic_tab);
        tabSpec1.setIndicator(mTabWidget1);


        TabHost.TabSpec tabSpec2=tabHost.newTabSpec("standard");
        tabSpec2.setContent(R.id.standard_tab);
        tabSpec2.setIndicator(mTabWidget2);


        TabHost.TabSpec tabSpec3=tabHost.newTabSpec("premium");
        tabSpec3.setContent(R.id.premium_tab);
        tabSpec3.setIndicator(mTabWidget3);

        tabHost.addTab(tabSpec1);
        tabHost.addTab(tabSpec2);
        tabHost.addTab(tabSpec3);

        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height=metrics.heightPixels;
        int width=metrics.widthPixels;
        tabHost.getTabWidget().getChildAt(0).getLayoutParams().height=(height*15)/130;
        tabHost.getTabWidget().getChildAt(1).getLayoutParams().height=(height*15)/130;
        tabHost.getTabWidget().getChildAt(2).getLayoutParams().height=(height*15)/130;


//        final TabHost tabHost=getTabHost();
//        TabHost.TabSpec spec;
//        tabHost.getTabWidget().setDividerDrawable(null);
//
//        //탭에 이미지 넣어주기
//        ImageView mTabWidget1=new ImageView(this);
//        mTabWidget1.setImageResource(R.drawable.membership_tab_1);
//
//        ImageView mTabWidget2=new ImageView(this);
//        mTabWidget2.setImageResource(R.drawable.membership_tab_2);
//
//        ImageView mTabWidget3=new ImageView(this);
//        mTabWidget3.setImageResource(R.drawable.membership_tab_3);
//
//        //탭 선택시 화면이동
//        spec =tabHost.newTabSpec("basic").setIndicator(mTabWidget1)
//                .setContent(new Intent(this,Tab1.class));
//        tabHost.addTab(spec);
//
//        spec =tabHost.newTabSpec("standard").setIndicator(mTabWidget2)
//                .setContent(new Intent(this,Tab2.class));
//        tabHost.addTab(spec);
//
//        spec =tabHost.newTabSpec("premium").setIndicator(mTabWidget3)
//                .setContent(new Intent(this,Tab3.class));
//        tabHost.addTab(spec);
//
//        tabHost.setCurrentTab(0);
//
//        //배경색 설정
//        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
//            @Override
//            public void onTabChanged(String tabId) {
//                for(int i=0; i<tabHost.getTabWidget().getChildCount();i++){
//                    tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.WHITE);
//                }
//                tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab())
//                        .setBackgroundColor(Color.WHITE);
//            }
//        });
//
//
//        //탭 높이 설정
//        DisplayMetrics metrics=new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        int height=metrics.heightPixels;
//        int width=metrics.widthPixels;
//        tabHost.getTabWidget().getChildAt(0).getLayoutParams().height=(height*15)/150;
//        tabHost.getTabWidget().getChildAt(1).getLayoutParams().height=(height*15)/150;
//        tabHost.getTabWidget().getChildAt(2).getLayoutParams().height=(height*15)/150;
//

    }
}
