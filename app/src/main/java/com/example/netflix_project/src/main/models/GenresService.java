package com.example.netflix_project.src.main.models;

import androidx.annotation.NonNull;

import com.example.netflix_project.src.main.interfaces.GenreView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.netflix_project.src.ApplicationClass.getMovieService;

public class GenresService {

    private GenreView genreView;

    public GenresService(final GenreView genreView){
     this.genreView = genreView;
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
                                genreView.onSuccess(genresResponse.getGenres());
                            } else {
                                genreView.onError();
                            }
                        } else {
                            genreView.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<GenresResponse> call, Throwable t) {
                        genreView.onError();
                    }
                });
    }


}
