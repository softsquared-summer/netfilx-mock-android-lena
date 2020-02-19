package com.example.netflix_project.src.main.ViewPager.User;

import com.example.netflix_project.src.main.ViewPager.User.interfaces.UserApi;

import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginService {

    public static final String BaseUrl = "http://lena.seohyunguk.me/";
    private static Retrofit retrofit = null;
    private static UserApi userApi = null;

    public static UserApi getService() {

        if (userApi == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            userApi = retrofit.create(UserApi.class);
        }

        return userApi;

    }
}


