package com.example.netflix_project.src.main.models;

import androidx.annotation.NonNull;

import com.example.netflix_project.src.main.ViewPager.User.interfaces.LoginActivityView;
import com.example.netflix_project.src.main.interfaces.NetflixApi;
import com.example.netflix_project.src.main.interfaces.OnGetMoviesCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.netflix_project.src.ApplicationClass.getMovieService;

public class MoviesRepository {

    private static final String BASE_URL = "http://lena.seohyunguk.me/";
    //private static final String LANGUAGE = "ko-KR";
   // private String API_KEY="7758c57b7f0f09632a680ee764656d45";
    private static MoviesRepository repository;

    private NetflixApi api;

//    private MoviesRepository(NetflixApi api) {
//        this.api = api;
//    }
//
//        public static MoviesRepository getInstance() {
//            if (repository == null) {
//                Retrofit retrofit = new Retrofit.Builder()
//                        .baseUrl(BASE_URL)
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build();
//
//                repository = new MoviesRepository(retrofit.create(NetflixApi.class));
//            }
//
//            return repository;
//    }


    private OnGetMoviesCallback onGetMoviesCallback;

    public MoviesRepository(final OnGetMoviesCallback onGetMoviesCallback ){
        this.onGetMoviesCallback=onGetMoviesCallback;
    }

    public void getMovies() {
        getMovieService().getPopularMovie()
                .enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                        if (response.isSuccessful()) {
                            MoviesResponse moviesResponse = response.body();
                            if (moviesResponse != null && moviesResponse.getMovies() != null) {
                              onGetMoviesCallback.onSuccess(moviesResponse.getMovies());
                            } else {
                                onGetMoviesCallback.onError();
                            }
                        } else {
                            onGetMoviesCallback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
                        onGetMoviesCallback.onError();
                    }
                });
    }
//        public void getGenres(final OnGetGenresCallback callback){
//            api.getGenres(API_KEY, LANGUAGE)
//                    .enqueue(new Callback<GenresResponse>() {
//                        @Override
//                        public void onResponse(Call<GenresResponse> call, Response<GenresResponse> response) {
//                            if (response.isSuccessful()) {
//                                GenresResponse genresResponse = response.body();
//                                if (genresResponse != null && genresResponse.getGenres() != null) {
//                                    callback.onSuccess(genresResponse.getGenres());
//                                } else {
//                                    callback.onError();
//                                }
//                            } else {
//                                callback.onError();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<GenresResponse> call, Throwable t) {
//                            callback.onError();
//                        }
//                    });
//        }
    }
