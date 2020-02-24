package com.example.netflix_project.src.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.netflix_project.R;
import com.example.netflix_project.src.main.home.Adapter.MoviesAdapter;
import com.example.netflix_project.src.main.home.MovieInterface.MovieSimilarView;
import com.example.netflix_project.src.main.interfaces.MovieInfoView;
import com.example.netflix_project.src.main.mainpage.BottomNavigationHelper;
import com.example.netflix_project.src.main.models.Movie;
import com.example.netflix_project.src.main.models.MovieService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

import java.util.List;

public class MovieInfoActivity extends Fragment implements MovieInfoView, MovieSimilarView {
    public static int movie_no;
    private ImageView mPoster;
    private TextView mTitle, mRelease, mRunningTime, mOverview, mCast, mDirector;
    private String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w500";
    private View view;
    private RecyclerView mSimilarRecycler;
    private MovieService movieService;
    private MoviesAdapter mMovieAdapter;


    private BottomNavigationView bottomNavigationView;
    BottomNavigationHelper bottomNavigationHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_info_movie,container,false);

        //하단바
        bottomNavigationView=view.findViewById(R.id.movie_menu_bottom_navi);
        bottomNavigationHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);

        Intent intent=getActivity().getIntent();
        movie_no=intent.getIntExtra("movie_no",0);

        mTitle=view.findViewById(R.id.movie_title_detail);
        mCast=view.findViewById(R.id.movie_detail_cast);
        mDirector=view.findViewById(R.id.movie_detail_director);
        mOverview=view.findViewById(R.id.movie_detail_overview);
       mRelease=view.findViewById(R.id.movie_detail_release_date);
       mRunningTime=view.findViewById(R.id.movie_detail_running_time);
        mPoster=view.findViewById(R.id.youtube_view);

        //비슷한 영화 추천
        mSimilarRecycler=view.findViewById(R.id.movie_similar_recycler);
        mSimilarRecycler.setLayoutManager(new GridLayoutManager(getContext(),3));


        getMovies();
        return view;
    }

    private void getMovies() {

        //info
        movieService =new MovieService((MovieInfoView) this);
        movieService.getMovieDetail();
        //similar
        movieService=new MovieService((MovieSimilarView) this);
        movieService.getSimilarMovie(movie_no);

    }


    @Override
    public void onMovieDetailSuccess(Movie movies) {

        mTitle.setText(movies.getTitle());
        mCast.setText("출연: "+movies.getCast());
        mDirector.setText("감독: "+movies.getDirector());
        mOverview.setText(movies.getOverview());
        mRelease.setText(movies.getRelease()+"");
        mRunningTime.setText(movies.getDuration());

        Glide.with(getContext())
                .load(IMAGE_BASE_URL + movies.getPosterUrl())
                .apply(RequestOptions.placeholderOf(R.color.colorNetflicBack))
                .into(mPoster);
    }

    @Override
    public void onMovieSimilarSuccess(List<Movie> movies) {
        mMovieAdapter=new MoviesAdapter(getContext(),movies);
        mSimilarRecycler.setAdapter(mMovieAdapter);
    }

    @Override
    public void onError() {
        Toast.makeText(getContext(), " getString(R.string.network_error", Toast.LENGTH_LONG).show();
    }
}

