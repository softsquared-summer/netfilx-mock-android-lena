package com.example.netflix_project.src.main.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.netflix_project.R;
import com.example.netflix_project.src.BaseActivity;
import com.example.netflix_project.src.main.mainpage.MainPage;

public class HomeMenu extends BaseActivity {
    TextView mALL, mTV, mMovie, mContent, mClose;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_menu_select);

        mALL=findViewById(R.id.all);
        mTV=findViewById(R.id.TV);
        mContent=findViewById(R.id.CONTENT);
        mMovie=findViewById(R.id.MOVIE);

        mMovie.setTextSize(20);
        mMovie.setTextColor(Color.WHITE);
    }

    public void onClickAll(View view){

        Intent intent=new Intent(getApplicationContext(), MainPage.class);
        startActivity(intent);
        mALL.setTextSize(20);
        mALL.setTextColor(Color.WHITE);
        mMovie.setTextSize(15);
        mMovie.setTextColor(Color.parseColor("#999999"));

    }

    public void onClickMovie(View view){

        Intent intent=new Intent(getApplicationContext(),MovieSelect.class);
        startActivity(intent);
        mMovie.setTextSize(20);
        mMovie.setTextColor(Color.WHITE);

    }

    public void onClickClose(View view){
        onBackPressed();
    }
}
