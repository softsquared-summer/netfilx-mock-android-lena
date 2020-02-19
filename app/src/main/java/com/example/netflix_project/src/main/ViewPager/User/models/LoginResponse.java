package com.example.netflix_project.src.main.ViewPager.User.models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {


    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

}
