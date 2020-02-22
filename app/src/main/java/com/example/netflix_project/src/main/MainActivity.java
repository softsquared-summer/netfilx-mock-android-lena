package com.example.netflix_project.src.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.netflix_project.R;
import com.example.netflix_project.src.main.ViewPager.CircleIndicatorAni;
import com.example.netflix_project.src.main.ViewPager.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private List<String> numberList;

    private CircleIndicatorAni circleIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();
        init();
    }


    /**
     * 레이아웃 초기화
     */
    private void initLayout(){

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        circleIndicator = (CircleIndicatorAni) findViewById(R.id.circleAnimIndicator);
    }


    /**
     * 데이터 초기화
     */
    private void init(){

        //데이터 초기화
        initData();
        //ViewPager 초기화
        initViewPager();
    }


    /**
     * 데이터 초기화
     */
    private void initData(){

        numberList = new ArrayList<>();
        numberList.add("1");
        numberList.add("2");
        numberList.add("3");
        numberList.add("4");


    }


    /**
     * ViewPager 초기화
     */
    private void initViewPager(){

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getApplicationContext(),numberList);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(mOnPageChangeListener);


        //Indicator 초기화
        initIndicaotor();
    }


    /**
     * Indicator 초기화
     */
    private void initIndicaotor(){

        //원사이의 간격
        circleIndicator.setItemMargin(15);
        //애니메이션 속도
        circleIndicator.setAnimDuration(300);
        //indecator 생성
        circleIndicator.createDotPanel(numberList.size(), R.drawable.gray_radius , R.drawable.red_radius);
    }


    /**
     * ViewPager 전환시 호출되는 메서드
     */
    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        @Override
        public void onPageSelected(int position) {
            circleIndicator.selectDot(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };


}

