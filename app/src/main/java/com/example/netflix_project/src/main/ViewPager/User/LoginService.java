package com.example.netflix_project.src.main.ViewPager.User;

import android.view.View;
import android.widget.Toast;

import com.example.netflix_project.src.main.MainActivity;
import com.example.netflix_project.src.main.ViewPager.User.interfaces.LoginActivityView;
import com.example.netflix_project.src.main.ViewPager.User.interfaces.UserApi;
import com.example.netflix_project.src.main.ViewPager.User.models.LoginResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.netflix_project.src.ApplicationClass.getService;

public class LoginService {

//    public static final String BaseUrl = "http://lena.seohyunguk.me/";
//    private static Retrofit retrofit = null;
//    private static UserApi userApi = null;
//
//    public static UserApi getService() {
//
//        if (userApi == null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BaseUrl)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//            userApi = retrofit.create(UserApi.class);
//        }
//
//        return userApi;
//
//    }

    private LoginActivityView mLoginActivityView;

    public LoginService(final LoginActivityView loginActivityView){
        this.mLoginActivityView=loginActivityView;
    }

    public void postSign(String email, String password, String name){

        //reauest Body로 보낼 Map<Sring,String>정의
        HashMap<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("id", email);
        requestBody.put("pw", password);
        requestBody.put("name",name);

        getService().postSignUp(requestBody).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse result=response.body();

                if(result==null){
                    mLoginActivityView.validateFailure(null);
                    return;
                }

                mLoginActivityView.validateSuccess(result.getMessage());
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

         getService().postLogin(requestBody).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse result=response.body();

                if(result==null){
                    mLoginActivityView.validateFailure(null);
                    return;
                }

                mLoginActivityView.validateSuccess(result.getMessage());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mLoginActivityView.validateFailure(null);
            }
        });



    }



}


