package com.example.netflix_project.src.main.models;

import androidx.annotation.NonNull;

import com.example.netflix_project.src.main.MovieDetail;
import com.example.netflix_project.src.main.ViewPager.User.interfaces.LoginActivityView;
import com.example.netflix_project.src.main.home.MovieGenreActivity;
import com.example.netflix_project.src.main.interfaces.NetflixApi;
import com.example.netflix_project.src.main.interfaces.OnGetGenreMoviesCallback;
import com.example.netflix_project.src.main.interfaces.OnGetGenreNewMoviesCallback;
import com.example.netflix_project.src.main.interfaces.OnGetGenrePopularMoviesCallback;
import com.example.netflix_project.src.main.interfaces.OnGetMoviesCallback;
import com.example.netflix_project.src.main.interfaces.OnGetMoviesDetailCallback;
import com.example.netflix_project.src.main.interfaces.OnGetNewMoviesCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.netflix_project.src.ApplicationClass.getMovieService;

public class MoviesRepository {

    private OnGetMoviesCallback onGetMoviesCallback;
    private OnGetNewMoviesCallback onGetNewMoviesCallback;
    private OnGetMoviesDetailCallback onGetMoviesDetailCallback;
    private OnGetGenreMoviesCallback onGetGenreMoviesCallback;
    private OnGetGenrePopularMoviesCallback onGetGenrePopularMoviesCallback;
    private OnGetGenreNewMoviesCallback onGetGenreNewMoviesCallback;
    MovieDetail movieDetail;
    public MoviesRepository(final OnGetMoviesCallback onGetMoviesCallback, OnGetNewMoviesCallback onGetNewMoviesCallback ){
        this.onGetMoviesCallback=onGetMoviesCallback;
        this.onGetNewMoviesCallback=onGetNewMoviesCallback;
    }

    public MoviesRepository(final OnGetMoviesDetailCallback onGetMoviesDetailCallback){
        this.onGetMoviesDetailCallback=onGetMoviesDetailCallback;
    }

    public MoviesRepository(final OnGetGenreMoviesCallback onGetGenreMoviesCallback){
        this.onGetGenreMoviesCallback=onGetGenreMoviesCallback;
    }

    public MoviesRepository(final OnGetGenreNewMoviesCallback onGetGenreNewMoviesCallback){
        this.onGetGenreNewMoviesCallback=onGetGenreNewMoviesCallback;
    }

    public MoviesRepository(final OnGetGenrePopularMoviesCallback onGetGenrePopularMoviesCallback){
        this.onGetGenrePopularMoviesCallback=onGetGenrePopularMoviesCallback;
    }

    //인기있는 영화
    public void getPopularMovies() {
        getMovieService().getPopularMovie()
                .enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                        if (response.isSuccessful()) {
                            MoviesResponse moviesResponse = response.body();
                            if (moviesResponse != null && moviesResponse.getMovies() != null) {
                             onGetMoviesCallback.onPopularMovieSuccess(moviesResponse.getMovies());
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


    //신규 영화
    public void getNewMovies() {
        getMovieService().getNewMovie()
                .enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                        if (response.isSuccessful()) {
                            MoviesResponse moviesResponse = response.body();
                            if (moviesResponse != null && moviesResponse.getMovies() != null) {
                                onGetNewMoviesCallback.onNewMovieSuccess(moviesResponse.getMovies());
                            } else {
                                onGetNewMoviesCallback.onError();
                            }
                        } else {
                            onGetNewMoviesCallback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
                        onGetNewMoviesCallback.onError();
                    }
                });
    }

    //영화 디테일

    public void getMovieDetail() {
        int n=movieDetail.movie_no;
        getMovieService().getMovieInfo(n)
                .enqueue(new Callback<MovieDetailResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MovieDetailResponse> call, @NonNull Response<MovieDetailResponse> response) {
                        if (response.isSuccessful()) {
                            MovieDetailResponse moviesDetailResponse = response.body();
                            if (moviesDetailResponse != null && moviesDetailResponse.getMovies() != null) {
                                onGetMoviesDetailCallback.onMovieDetailSuccess(moviesDetailResponse.getMovies());
                            } else {
                                onGetMoviesDetailCallback.onError();
                            }
                        } else {
                            onGetMoviesDetailCallback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieDetailResponse> call, Throwable t) {
                        onGetMoviesDetailCallback.onError();
                    }
                });
    }

    //장르별 영화
    public void getMovieGenre() {
        int n= MovieGenreActivity.num;
        getMovieService().getGenresMvList(n)
                .enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                        if (response.isSuccessful()) {
                            MoviesResponse moviesResponse = response.body();
                            if (moviesResponse != null && moviesResponse.getMovies() != null) {
                                onGetGenreMoviesCallback.onGenreMovieSuccess(moviesResponse.getMovies());
                            } else {
                                onGetGenreMoviesCallback.onError();
                            }
                        } else {
                            onGetGenreMoviesCallback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
                        onGetGenreMoviesCallback.onError();
                    }
                });
    }

    //장르별 인기영화
    public void getPopularGenreMovie() {
        int n= MovieGenreActivity.num;
        getMovieService().getGenreSelectPopularMvList(n)
                .enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                        if (response.isSuccessful()) {
                            MoviesResponse moviesResponse = response.body();
                            if (moviesResponse != null && moviesResponse.getMovies() != null) {
                                onGetGenrePopularMoviesCallback.onGenrePopularMovieSuccess(moviesResponse.getMovies());
                            } else {
                                onGetGenrePopularMoviesCallback.onError();
                            }
                        } else {
                            onGetGenrePopularMoviesCallback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
                        onGetGenrePopularMoviesCallback.onError();
                    }
                });
    }

    //장르별 신규영화

    public void getNewGenreMovie() {
        int n= MovieGenreActivity.num;
        getMovieService().getGenreSelectMvNewList(n)
                .enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                        if (response.isSuccessful()) {
                            MoviesResponse moviesResponse = response.body();
                            if (moviesResponse != null && moviesResponse.getMovies() != null) {
                                onGetGenreNewMoviesCallback.onGenreNewMovieSuccess(moviesResponse.getMovies());
                            } else {
                                onGetGenreNewMoviesCallback.onError();
                            }
                        } else {
                            onGetGenreNewMoviesCallback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
                        onGetGenreNewMoviesCallback.onError();
                    }
                });
    }


}
