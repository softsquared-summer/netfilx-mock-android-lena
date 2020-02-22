package com.example.netflix_project.src.main.ViewPager.User;

import com.example.netflix_project.src.main.ViewPager.User.interfaces.LoginActivityView;
import com.example.netflix_project.src.main.ViewPager.User.models.LoginResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.netflix_project.src.ApplicationClass.getService;

public class LoginService {

    private LoginActivityView mLoginActivityView;

    public LoginService(final LoginActivityView loginActivityView){
        this.mLoginActivityView=loginActivityView;
    }

    public void postSign(String email, String password, String name, String typeNo){

        //reauest Body로 보낼 Map<Sring,String>정의
        HashMap<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("id", email);
        requestBody.put("pw", password);
        requestBody.put("name",name);
        requestBody.put("typeNo",typeNo);

        getService().postSignUp(requestBody).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse result=response.body();

                if(result==null){
                    mLoginActivityView.validateFailure(null);
                    return;
                }

                mLoginActivityView.validateSuccess(result.getIsSuccess(),result.getMessage());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                mLoginActivityView.validateFailure(null);
            }
        });

    }

    public void postLogin(String email, String password){

        //reauest Body로 보낼 Map<Sring,String>정의
        HashMap<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("id", email);
        requestBody.put("pw", password);

        //여기에 다이얼로그ㅡ
         getService().postLogin(requestBody).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse result=response.body();

                if(result==null){
                    mLoginActivityView.validateFailure(null);
                    return;
                }

                mLoginActivityView.validateSuccess(result.getIsSuccess(),result.getMessage());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mLoginActivityView.validateFailure(null);
            }
        });



    }



}


