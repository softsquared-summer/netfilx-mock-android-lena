package com.example.netflix_project.src.main.home.MovieInterface;


import com.example.netflix_project.src.main.models.Movie;

import java.util.List;

public interface ActionMovieView {

    void onActionSuccess(List<Movie> movies);

    void onError();
}
