package com.example.netflix_project.src.main.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netflix_project.R;
import com.example.netflix_project.src.main.home.Adapter.MoviesAdapter;
import com.example.netflix_project.src.main.home.Adapter.PreviewAdapter;
import com.example.netflix_project.src.main.interfaces.NewMovieView;
import com.example.netflix_project.src.main.models.Movie;
import com.example.netflix_project.src.main.models.MovieService;
import com.example.netflix_project.src.main.interfaces.MovieView;

import com.example.netflix_project.src.main.models.Trailer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends Fragment implements MovieView, NewMovieView {

    private View view;
    private RecyclerView moviesList, mPreviewRecyclerView, mNewMvRecycler;
    private MoviesAdapter adapter, mAdapterNew;
    private PreviewAdapter mPreviewAdapter;
    private MovieService movieService;
    //youtube
    private YouTubePlayerView mTubePlayerView;
    ArrayList<Trailer> mTrailerList;
    private static final String API_KEY = "AIzaSyCaZ_fSo1DIF7EV2tD6uwbKWtTDr7iI10c";
    private static String VIDEO_ID="xitHF0IPJSQ";

    private ImageView mMainPoster;
    private Button mMovieBtn;
    private TextView mMovie;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.frag_home,container,false);


        //----------------영화 click------------------
        mMovieBtn=view.findViewById(R.id.home_btn_movie);
        mMovie=view.findViewById(R.id.MOVIE);
        mMovieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), MovieActivity.class);;
                startActivity(intent);

            }
        });

        //----------------메인 영화 뷰 -----------------
        mMainPoster=view.findViewById(R.id.home_iv_main_poster);
        mMainPoster.setImageResource(R.drawable.poster_sample);

        //----------------미리보기----------------------
        mPreviewRecyclerView=view.findViewById(R.id.home_recycler_view);
        mPreviewRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),1,GridLayoutManager.HORIZONTAL,false));

        //-----------한국 드라마 & 버라이어티--------------
        moviesList = view.findViewById(R.id.movies_list);
        moviesList.setLayoutManager(new GridLayoutManager(getContext(), 1,GridLayoutManager.HORIZONTAL, false));

        //-------------신규 콘텐츠-------------------

        mNewMvRecycler=view.findViewById(R.id.home_recycler_new);
        mNewMvRecycler.setLayoutManager(new GridLayoutManager(getContext(), 1,GridLayoutManager.HORIZONTAL, false));

        //----------------YOUTUBE VIEW-------------------
        mTrailerList=new ArrayList<>();

        getMovies();


        return view;
    }

    private void getMovies() {

        MovieService movieService =new MovieService(this,this);
        movieService.getPopularMovies(-1);
        movieService.getNewMovies();

    }

    private void showError() {
        Toast.makeText(getContext(), " getString(R.string.network_error", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onPopularMovieSuccess(List<Movie> movies) {
        adapter = new MoviesAdapter(getContext(),movies);
        mPreviewAdapter=new PreviewAdapter(getContext(),movies);
        moviesList.setAdapter(adapter);
        mPreviewRecyclerView.setAdapter(mPreviewAdapter);
    }

    @Override
    public void onNewMovieSuccess(List<Movie> movies) {
        mAdapterNew=new MoviesAdapter(getContext(),movies);
        mNewMvRecycler.setAdapter(mAdapterNew);
    }


    @Override
    public void onError() {

        showError();
    }
}
