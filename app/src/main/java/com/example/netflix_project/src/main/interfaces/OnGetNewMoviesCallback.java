package com.example.netflix_project.src.main.interfaces;


import com.example.netflix_project.src.main.models.Movie;

import java.util.List;

public interface OnGetNewMoviesCallback {

    void onNewMovieSuccess(List<Movie> movies);

    void onError();
}
