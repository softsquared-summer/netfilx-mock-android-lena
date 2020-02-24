package com.example.netflix_project.src.main.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.example.netflix_project.R;
import com.example.netflix_project.src.BaseActivity;
import com.example.netflix_project.src.main.ViewPager.ViewPagerActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler hd = new Handler();
        hd.postDelayed(new splashHandler(), 2000); // 2초 후에 hd handler 실행


    }

    private class splashHandler implements Runnable {
        public void run() {

            showProgressDialog();
            startActivity(new Intent(getApplication(), ViewPagerActivity.class));
            SplashActivity.this.finish();
        }
    }


}
