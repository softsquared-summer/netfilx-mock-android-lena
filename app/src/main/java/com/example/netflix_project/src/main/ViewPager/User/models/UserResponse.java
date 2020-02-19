package com.example.netflix_project.src.main.ViewPager.User.models;

import com.google.gson.annotations.SerializedName;

public class UserResponse {

    private String userID;
    private String userPW;

    public UserResponse(String userID, String userPW){
        this.userID=userID;
        this.userPW=userPW;
    }

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("pw")
    private String pw;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }



}

