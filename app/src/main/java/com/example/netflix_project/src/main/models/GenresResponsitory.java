package com.example.netflix_project.src.main.models;

import androidx.annotation.NonNull;

import com.example.netflix_project.src.main.interfaces.OnGetGenresCallback;
import com.example.netflix_project.src.main.interfaces.OnGetMoviesCallback;
import com.example.netflix_project.src.main.interfaces.OnGetNewMoviesCallback;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.netflix_project.src.ApplicationClass.getMovieService;

public class GenresResponsitory {

    private OnGetGenresCallback onGetGenresCallback;

    public GenresResponsitory(final OnGetGenresCallback onGetGenresCallback){
     this.onGetGenresCallback=onGetGenresCallback;
    }

    //장르 리스트 조회

    public void getGenreList (){
        getMovieService().getGenresList()
                .enqueue(new Callback<GenresResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<GenresResponse> call, @NonNull Response<GenresResponse> response) {
                        if (response.isSuccessful()) {
                            GenresResponse genresResponse = response.body();
                            if (genresResponse != null && genresResponse.getGenres() != null) {
                                onGetGenresCallback.onSuccess(genresResponse.getGenres());
                            } else {
                                onGetGenresCallback.onError();
                            }
                        } else {
                            onGetGenresCallback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<GenresResponse> call, Throwable t) {
                        onGetGenresCallback.onError();
                    }
                });
    }


}
