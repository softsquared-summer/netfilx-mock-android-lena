package com.example.netflix_project.src.main.ViewPager.User.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UseJwtResponse {

    @SerializedName("results")
    @Expose
    private String jwt;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
