package com.example.netflix_project.src.main.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("no")
    @Expose
    private int no;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("director")
    @Expose
    private String director;

    @SerializedName("cast")
    @Expose
    private String cast;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("release")
    @Expose
    private int release;

    @SerializedName("rating")
    @Expose
    private String rating;

    @SerializedName("duration")
    @Expose
    private String duration;

    @SerializedName("posterUrl")
    @Expose
    private String posterUrl;


    @SerializedName("videoUrl")
    @Expose
    private String videoUrl;


    @SerializedName("type")
    @Expose
    private String type;




    public String getOverview() {
        return overview;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public int getNo() {
        return no;
    }

    public int getRelease() {
        return release;
    }

    public String getCast() {
        return cast;
    }

    public String getDuration() {
        return duration;
    }

    public String getRating() {
        return rating;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getType() {
        return type;
    }
}
