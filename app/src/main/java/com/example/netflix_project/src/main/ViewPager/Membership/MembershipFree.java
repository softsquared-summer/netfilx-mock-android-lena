package com.example.netflix_project.src.main.ViewPager.Membership;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.netflix_project.R;
import com.example.netflix_project.src.BaseActivity;

public class MembershipFree extends BaseActivity {

    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.membership_free);

        mToolbar=findViewById(R.id.membership_toolbar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        View viewToolbar = getLayoutInflater().inflate(R.layout.actionbar_white, null);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setCustomView(viewToolbar, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));


    }

    public void onClickNext(View view){
        Intent intent=new Intent(getApplicationContext(),MembershipSelect.class);
        startActivity(intent);

    }
}
