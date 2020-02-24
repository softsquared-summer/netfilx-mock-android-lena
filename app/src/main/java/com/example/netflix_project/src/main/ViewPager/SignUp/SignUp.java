package com.example.netflix_project.src.main.ViewPager.SignUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.example.netflix_project.R;
import com.example.netflix_project.src.BaseActivity;
import com.example.netflix_project.src.main.ViewPager.User.Login;
import com.example.netflix_project.src.main.ViewPager.User.LoginService;
import com.example.netflix_project.src.main.ViewPager.User.interfaces.LoginActivityView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends BaseActivity implements LoginActivityView {

    Toolbar mToolbar;
    TextInputEditText mEmail, mPassword;
    Button mSingUpButton;
    TextInputLayout mEmailTextInput, mPassTextInput;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);

        mEmail=findViewById(R.id.sign_et_email);
        mPassword=findViewById(R.id.sign_et_pass);
        mSingUpButton=findViewById(R.id.sign_btn_signup);
        mEmailTextInput=findViewById(R.id.sign_text_input_email);
        mPassTextInput=findViewById(R.id.sign_text_input_password);



        //툴바 커스텀
        mToolbar=findViewById(R.id.membership_toolbar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        View viewToolbar = getLayoutInflater().inflate(R.layout.actionbar_white, null);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setCustomView(viewToolbar, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));

        //-----RETROFIT-------------



    }



    private void tryPostSign(){

        final String email=mEmail.getText().toString();
        final String password=mPassword.getText().toString();
        final String name="test";
        final String typeNo="1";

        LoginService loginService=new LoginService(this);
        loginService.postSign(email,password,name,typeNo);
    }
    @Override
    public void validateSuccess(boolean success,String message) {


        if (success) {
            showCustomToast(message);
            Intent intent=new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
        }
        else
            showCustomToast(message);
    }

    @Override
    public void validateFailure(String message) {

        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    public void onSignUpClick(View view){
        tryPostSign();
    }
}
