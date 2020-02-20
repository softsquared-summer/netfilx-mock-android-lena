package com.example.netflix_project.src.main.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.netflix_project.R;

public class PersonalCental extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_center_layout);

    }


    public void onButtonClickedHelp(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://help.netflix.com/legal/privacy"));
        startActivity(intent);
    }

    public void onButtonClickedPay(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.netflix.com/kr/Login?nextpage=https%3A%2F%2Fwww.netflix.com%2FYourAccountPayment"));
        startActivity(intent);
    }

    public void onButtonClickeRequest(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://help.netflix.com/en/titlerequest"));
        startActivity(intent);
    }


    public void onButtonClickePassword(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.netflix.com/kr/Login?nextpage=https%3A%2F%2Fwww.netflix.com%2Fpassword"));
        startActivity(intent);
    }

    public void onButtonClickeCancel(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.netflix.com/kr/Login?nextpage=https%3A%2F%2Fwww.netflix.com%2FCancelPlan"));
        startActivity(intent);
    }

    public void onButtonClickeTrouble(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://help.netflix.com/ko/troubleshooting"));
        startActivity(intent);
    }

    public void onButtonClickPrivacy(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://help.netflix.com/legal/privacy" +
                "\n"));
        startActivity(intent);
    }

    public void onButtonClickLegal(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://help.netflix.com/legal/termsofuse" +
                "\n"));
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right,R.anim.translate_right);
    }

    public void onBackClickP(View view){
        onBackPressed();
    }

}


