package com.example.netflix_project.src.main.interfaces;


import com.example.netflix_project.src.main.models.Movie;

import java.util.List;

public interface NewGenreView {

    void onGenreNewMovieSuccess(List<Movie> movies);

    void onError();
}
