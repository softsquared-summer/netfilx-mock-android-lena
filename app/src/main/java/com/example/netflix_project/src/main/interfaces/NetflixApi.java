package com.example.netflix_project.src.main.interfaces;

import com.example.netflix_project.src.main.models.GenresResponse;
import com.example.netflix_project.src.main.models.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NetflixApi {

    //인기 영화 콘텐츠
    @GET("movie/list/popular")
    Call<MoviesResponse> getPopularMovie();

    //신규 영화 콘텐츠
    @GET("movie/list/newAdd")
    Call<MoviesResponse> getNewMovie();

    //장르 리스트 조회
    @GET("genre")
    Call<MoviesResponse> getGenresList();

    //영화 정보 상세 조회
    @GET("movie/{movieNo}")
    Call<MoviesResponse> getMovieInfo(
            @Path("movieNo") int movieNo

    );
    //영화 장르별 리스트 조회
    @GET("movie/{genreNo}/list")
    Call<MoviesResponse> getGenresMvList(
            @Path("genreNo") int genreNo

    );

    //장르 선택후 리스트 조회
    @GET("movie/{genreNo1}/list/{genreNo2}")
    Call<MoviesResponse> getGenreSelectList(
            @Path("genreNo1") int genreNo1,
            @Path("genreNo2") int genreNo2

    );

    //장르 선택 후 인기영화 리스트 조회
    @GET("movie/{genreNo}/popular")
    Call<MoviesResponse> getGenreSelectPopularMvList(
            @Path("genreNo") int genreNo

    );

    //장르 선택 후 신규영화 리스트 조회
    @GET("movie/{genreNo}/newAdd")
    Call<MoviesResponse> getGenreSelectMvNewList(
            @Path("genreNo") int genreNo

    );



}
