package com.example.netflix_project.src.main.interfaces;


import com.example.netflix_project.src.main.models.MovieResponse;

import java.util.List;

public interface OnGetMoviesCallback {

    void onSuccess(List<MovieResponse> movies);

    void onError();
}
