package com.example.netflix_project.src.main.interfaces;


import com.example.netflix_project.src.main.models.Genre;

import java.util.List;

public interface GenreView {
    void onSuccess(List<Genre> genres);

    void onError();
}
