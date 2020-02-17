package com.example.netflix_project.src.main.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.netflix_project.R;

import com.example.netflix_project.src.main.ViewPager.models.MyPagerAdapter;

import me.relex.circleindicator.CircleIndicator;

public class ViewPagerActivity extends AppCompatActivity {

    FragmentPagerAdapter adapterViewPager;
    private CircleIndicatorAni circleIndicator;
    Toolbar mToolbar;
    ViewPager mViewPager;
    ImageView mBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        //툴바
        mToolbar=findViewById(R.id.first_toolbar);
       setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        View viewToolbar = getLayoutInflater().inflate(R.layout.actionbar, null);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        //툴바 커스텀
        actionBar.setCustomView(viewToolbar, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));



       View  view = getLayoutInflater().inflate(R.layout.personal_center_layout, null);
        mBackButton=view.findViewById(R.id.personal_info_btn_back);
        mBackButton.setClickable(true);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

         mViewPager = (ViewPager) findViewById(R.id.viewpager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapterViewPager);

        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.view_pager_indicator);
        indicator.setViewPager(mViewPager);




    }

    public void onButtonClicked(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://help.netflix.com/legal/privacy"));
        startActivity(intent);
    }

    public void onPersonalCenterClick(View view){
        Intent intent=new Intent(getApplicationContext(),PersonalCental.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);


    }

    public void onLoginButtonClick(View view){
        Intent intent=new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

    }




}
