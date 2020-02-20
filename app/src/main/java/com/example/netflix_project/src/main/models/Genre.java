package com.example.netflix_project.src.main.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Genre {
    @SerializedName("no")
    @Expose
    private int no;

    @SerializedName("description")
    @Expose
    private String description;

    public int getNo() {
        return no;
    }

    public String getDescription() {
        return description;
    }
}