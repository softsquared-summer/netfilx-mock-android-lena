package com.example.netflix_project.src.main.models;

import androidx.annotation.NonNull;

import com.example.netflix_project.src.main.MovieInfoActivity;
import com.example.netflix_project.src.main.home.GenrePageActivity;
import com.example.netflix_project.src.main.home.MovieInterface.ActionMovieView;
import com.example.netflix_project.src.main.home.MovieInterface.ComedieMovieView;
import com.example.netflix_project.src.main.home.MovieInterface.HorrorMovieView;
import com.example.netflix_project.src.main.home.MovieInterface.InternationalMovieView;
import com.example.netflix_project.src.main.home.MovieInterface.MovieSimilarView;
import com.example.netflix_project.src.main.home.MovieInterface.RomanceMovieView;
import com.example.netflix_project.src.main.home.MovieInterface.SFMovieView;
import com.example.netflix_project.src.main.home.MovieInterface.ThrillerMovieView;
import com.example.netflix_project.src.main.interfaces.MovieGenreView;
import com.example.netflix_project.src.main.interfaces.NewGenreView;
import com.example.netflix_project.src.main.interfaces.PopularMovieView;
import com.example.netflix_project.src.main.interfaces.MovieView;
import com.example.netflix_project.src.main.interfaces.MovieInfoView;
import com.example.netflix_project.src.main.interfaces.NewMovieView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.netflix_project.src.ApplicationClass.getMovieService;

public class MovieService {

    //
    private MovieView movieView;
    private NewMovieView newMovieView;
    private MovieInfoView movieInfoView;
    private MovieGenreView movieGenreView;
    private PopularMovieView popularMovieView;
    private NewGenreView newGenreView;
    private HorrorMovieView horrorMovieView;
    private ActionMovieView actionMovieView;
    private InternationalMovieView internationalMovieView;
    private ThrillerMovieView thrillerMovieView;
    private RomanceMovieView romanceMovieView;
    private SFMovieView sfMovieView;
    private ComedieMovieView comedieMovieView;
    private MovieSimilarView movieSimilarView;

    MovieInfoActivity movieInfoActivity;

    int mGenreNo=GenrePageActivity.num;
    public static int mLastNo;
    int lastNo;

    public MovieService(final MovieView movieView, NewMovieView newMovieView){
        this.movieView = movieView;
        this.newMovieView = newMovieView;
    }

    public MovieService(final MovieInfoView movieInfoView){
        this.movieInfoView = movieInfoView;
    }

    public MovieService(final MovieGenreView movieGenreView){ this.movieGenreView = movieGenreView; }

    public MovieService(final NewGenreView newGenreView){
        this.newGenreView = newGenreView;
    }

    public MovieService(final PopularMovieView popularMovieView){ this.popularMovieView = popularMovieView; }

    public MovieService(final HorrorMovieView horrorMovieView){ this.horrorMovieView=horrorMovieView; }

    public MovieService(final ComedieMovieView comedieMovieView){this.comedieMovieView=comedieMovieView;}

    public MovieService(final RomanceMovieView romanceMovieView){this.romanceMovieView=romanceMovieView;}

    public MovieService(final ActionMovieView actionMovieView){this.actionMovieView=actionMovieView;}

    public MovieService(final SFMovieView sfMovieView){this.sfMovieView=sfMovieView;}

    public MovieService(final ThrillerMovieView thrillerMovieView){this.thrillerMovieView=thrillerMovieView;}

    public MovieService(final InternationalMovieView internationalMovieView){this.internationalMovieView = internationalMovieView;}

    public MovieService(final MovieSimilarView movieSimilarView){this.movieSimilarView=movieSimilarView;}


    //인기있는 영화
    public void getPopularMovies(int lastNo) {
        getMovieService().getPopularMovie(lastNo)
                .enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                        if (response.isSuccessful()) {
                            MoviesResponse moviesResponse = response.body();
                            if (moviesResponse != null && moviesResponse.getMovies() != null) {
                                int n=moviesResponse.getMovies().size();
                                mLastNo=moviesResponse.getMovies().get(n-1).getNo();

                                movieView.onPopularMovieSuccess(moviesResponse.getMovies());

                            } else {
                                movieView.onError();
                            }
                        } else {
                            movieView.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
                        movieView.onError();
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
                                newMovieView.onNewMovieSuccess(moviesResponse.getMovies());
                            } else {
                                newMovieView.onError();
                            }
                        } else {
                            newMovieView.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
                        newMovieView.onError();
                    }
                });
    }

    //영화 디테일

    public void getMovieDetail() {
        int n= movieInfoActivity.movie_no;
        getMovieService().getMovieInfo(n)
                .enqueue(new Callback<MovieInfoResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MovieInfoResponse> call, @NonNull Response<MovieInfoResponse> response) {
                        if (response.isSuccessful()) {
                            MovieInfoResponse moviesDetailResponse = response.body();
                            if (moviesDetailResponse != null && moviesDetailResponse.getMovies() != null) {
                                movieInfoView.onMovieDetailSuccess(moviesDetailResponse.getMovies());
                            } else {
                                movieInfoView.onError();
                            }
                        } else {
                            movieInfoView.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieInfoResponse> call, Throwable t) {
                        movieInfoView.onError();
                    }
                });
    }

    //장르별 영화
    public void getMovieGenre(int mGenreNo) {

        getMovieService().getGenresMvList(mGenreNo)
                .enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                        if (response.isSuccessful()) {
                            MoviesResponse moviesResponse = response.body();
                            if (moviesResponse != null && moviesResponse.getMovies() != null) {
                                movieGenreView.onGenreMovieSuccess(moviesResponse.getMovies());

                            } else {
                                movieGenreView.onError();
                            }
                        } else {
                            movieGenreView.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
                        movieGenreView.onError();
                    }
                });
    }



    //개별 장르 영화
    public void getGenreMovie(final int mGenreNo) {

        getMovieService().getGenresMvList(mGenreNo)
                .enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                        if (response.isSuccessful()) {
                            MoviesResponse moviesResponse = response.body();
                            if (moviesResponse != null && moviesResponse.getMovies() != null) {
                                if(mGenreNo==2)
                                comedieMovieView.onComedieSuccess(moviesResponse.getMovies());
                                if(mGenreNo==3)
                                    internationalMovieView.onInternationalSuccess(moviesResponse.getMovies());
                                if(mGenreNo==4)
                                    sfMovieView.onSFSuccess(moviesResponse.getMovies());
                                if(mGenreNo==5)
                                    thrillerMovieView.onThriSuccess(moviesResponse.getMovies());
                                if(mGenreNo==6)
                                    actionMovieView.onActionSuccess(moviesResponse.getMovies());
                                if(mGenreNo==9)
                                    horrorMovieView.onHorrorSuccess(moviesResponse.getMovies());
                                if(mGenreNo==13)
                                    romanceMovieView.onRomaceSuccess(moviesResponse.getMovies());
                            }

                            else {
                                movieGenreView.onError();
                            }
                        } else {
                            movieGenreView.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
                        movieGenreView.onError();
                    }
                });
    }


    //장르별 인기영화
    public void getPopularGenreMovie() {
        getMovieService().getGenreSelectPopularMvList(mGenreNo)
                .enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                        if (response.isSuccessful()) {
                            MoviesResponse moviesResponse = response.body();
                            if (moviesResponse != null && moviesResponse.getMovies() != null) {
                                popularMovieView.onGenrePopularMovieSuccess(moviesResponse.getMovies());

                            } else {
                                popularMovieView.onError();
                            }
                        } else {
                            popularMovieView.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
                        popularMovieView.onError();
                    }
                });
    }

    //장르별 신규영화

    public void getNewGenreMovie() {
        getMovieService().getGenreSelectMvNewList(mGenreNo)
                .enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                        if (response.isSuccessful()) {
                            MoviesResponse moviesResponse = response.body();
                            if (moviesResponse != null && moviesResponse.getMovies() != null) {
                                newGenreView.onGenreNewMovieSuccess(moviesResponse.getMovies());
                            } else {
                                newGenreView.onError();
                            }
                        } else {
                            newGenreView.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
                        newGenreView.onError();
                    }
                });
    }


    //비슷한 영화 추천
    public void getSimilarMovie(int contentNo) {
        getMovieService().getMovieSimilarContentsList(contentNo)
                .enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MoviesResponse> call, @NonNull Response<MoviesResponse> response) {
                        if (response.isSuccessful()) {
                            MoviesResponse moviesResponse = response.body();
                            if (moviesResponse != null && moviesResponse.getMovies() != null) {
                                movieSimilarView.onMovieSimilarSuccess(moviesResponse.getMovies());
                            } else {
                                movieSimilarView.onError();
                            }
                        } else {
                            movieSimilarView.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
                        newGenreView.onError();
                    }
                });
    }

}
