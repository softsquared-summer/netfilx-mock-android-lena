package com.example.netflix_project.src.main.ViewPager.User.interfaces;

import com.example.netflix_project.src.main.ViewPager.User.models.LoginResponse;
import com.example.netflix_project.src.main.ViewPager.User.models.UserResponse;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserApi {

    @GET("/user")
    Call<UserResponse> getLoginUser();


    @GET("/user/all")
    Call<UserResponse> getAllUser(
            @Query("id") String id,
            @Query("name") String name,
            @Query("pw") String pw
    );

    @FormUrlEncoded
    @Headers("Content-Type: application/json")
    @POST("/user")
    Call<LoginResponse> postSignUp(@Body RequestBody id, RequestBody pw, RequestBody name);


    @Headers("Content-Type: application/json")
    @POST("/user/token")
    Call<LoginResponse> postLogin(@Body Map<String, String> body);
    //response로 받은 값을 LoginResponse클래스로 받는다.


}
