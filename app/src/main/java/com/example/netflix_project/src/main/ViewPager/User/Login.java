package com.example.netflix_project.src.main.ViewPager.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.netflix_project.R;
import com.example.netflix_project.src.BaseActivity;
import com.example.netflix_project.src.main.ViewPager.User.interfaces.LoginActivityView;
import com.example.netflix_project.src.main.mainpage.MainPageActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends BaseActivity implements LoginActivityView {

    TextInputEditText mEmail, mPassword;
    Button mLoginButton;
    TextView mHelpTv, mSingUpTv;
    TextInputLayout mEmailTextInput, mPassTextInput;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.netflix_login);

        mEmail=findViewById(R.id.login_et_email);
        mPassword=findViewById(R.id.login_et_pass);
        mHelpTv=findViewById(R.id.login_tv_help);
        mLoginButton=findViewById(R.id.login_btn_login);
        mSingUpTv=findViewById(R.id.login_tv_singup);
        mEmailTextInput=findViewById(R.id.login_text_input_email);
        mPassTextInput=findViewById(R.id.login_text_input_password);

        //------키보드 위에 레이아웃 올라오게 하기...근데 왜 안되징
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);


    }

    //이메일 형식 비었는지 패스워드 몇자 이상인지

    private void tryPostLogin(){

        final String email=mEmail.getText().toString();
        final String password=mPassword.getText().toString();
        showProgressDialog();
        LoginService loginService=new LoginService(this);
        loginService.postLogin(email,password);


    }
    private boolean validateEmail(){
        String emailInput=mEmailTextInput.getEditText().getText().toString().trim();
        if(emailInput.isEmpty()){
            mEmailTextInput.setError("죄송합니다 이 이메일 주소를 사용하는 계정을 찾을 수 없습니다.");
            return false;
        }
        else {
            mEmailTextInput.setError(null);
            return  true;
        }
    }

    private boolean validatePass(){
        String passInput=mPassTextInput.getEditText().getText().toString().trim();
        if(passInput.length()<4){
            mPassTextInput.setError("4-60자 사이여야 합니다.");
            return false;
        }
        else {
            mPassTextInput.setError(null);
            return  true;
        }
    }

    public void onLoginButtonClick(View view){
        Intent intent=new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

    }
    public void onConfirmClick(View view){
       validatePass();
       validateEmail();

       if(validateEmail()&&validatePass())
           tryPostLogin();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right,R.anim.translate_right);
    }

    public void onBackClick(View view){

        onBackPressed();
    }

    @Override
    public void validateSuccess(boolean success, String message) {
        if(success){
            Intent intent=new Intent(getApplicationContext(), MainPageActivity.class);
            startActivity(intent);
        }
        else
            showCustomToast(message);
    }

    @Override
    public void validateFailure(String message) {

        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }
}
