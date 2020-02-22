package com.example.netflix_project.src.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.netflix_project.R;
import com.example.netflix_project.src.BaseActivity;
import com.example.netflix_project.src.main.interfaces.OnGetMoviesDetailCallback;
import com.example.netflix_project.src.main.models.Movie;
import com.example.netflix_project.src.main.models.MoviesRepository;

import java.util.List;

public class MovieDetail extends BaseActivity implements OnGetMoviesDetailCallback {
    public static int movie_no;
    ImageView mPoster;
    TextView mTitle, mRelease, mRunningTime, mOverview, mCast, mDirector;
    private String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w500";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        Intent intent=getIntent();
        movie_no=intent.getIntExtra("movie_no",0);

        mTitle=findViewById(R.id.movie_title_detail);
        mCast=findViewById(R.id.movie_detail_cast);
        mDirector=findViewById(R.id.movie_detail_director);
        mOverview=findViewById(R.id.movie_detail_overview);
       mRelease=findViewById(R.id.movie_detail_release_date);
       mRunningTime=findViewById(R.id.movie_detail_running_time);
        mPoster=findViewById(R.id.youtube_view);

        getMovies();
    }

    private void getMovies() {

        MoviesRepository moviesRepository=new MoviesRepository(this);
        moviesRepository.getMovieDetail();

    }


    @Override
    public void onMovieDetailSuccess(Movie movies) {

        mTitle.setText(movies.getTitle());
        mCast.setText("출연: "+movies.getCast());
        mDirector.setText("감독: "+movies.getDirector());
        mOverview.setText(movies.getOverview());
        mRelease.setText(movies.getRelease()+"");
        mRunningTime.setText(movies.getDuration());

        Glide.with(getApplicationContext())
                .load(IMAGE_BASE_URL + movies.getPosterUrl())
                .apply(RequestOptions.placeholderOf(R.color.colorNetflicBack))
                .into(mPoster);
    }

    @Override
    public void onError() {
        showError();
    }
}

