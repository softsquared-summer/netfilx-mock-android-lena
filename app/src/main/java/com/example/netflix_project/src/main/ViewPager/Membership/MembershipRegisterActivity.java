package com.example.netflix_project.src.main.ViewPager.Membership;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.example.netflix_project.R;
import com.example.netflix_project.src.BaseActivity;
import com.example.netflix_project.src.main.ViewPager.SignUp.SignUp;

import java.util.zip.Inflater;

public class MembershipRegisterActivity extends BaseActivity {

    Toolbar mToolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        //툴바 커스텀
        mToolbar=findViewById(R.id.membership_toolbar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        View viewToolbar = getLayoutInflater().inflate(R.layout.actionbar_white, null);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setCustomView(viewToolbar, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));

    }

    public void onClickSignUp(View view){
        Intent intent=new Intent(getApplicationContext(), SignUp.class);
        startActivity(intent);
    }
}
