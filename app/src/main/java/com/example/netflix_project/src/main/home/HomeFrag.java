package com.example.netflix_project.src.main.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netflix_project.R;
import com.example.netflix_project.src.main.home.Adapter.MoviesAdapter;
import com.example.netflix_project.src.main.home.Adapter.PreviewAdapter;
import com.example.netflix_project.src.main.interfaces.OnGetGenresCallback;
import com.example.netflix_project.src.main.models.MoviesRepository;
import com.example.netflix_project.src.main.interfaces.OnGetMoviesCallback;
import com.example.netflix_project.src.main.models.Genre;
import com.example.netflix_project.src.main.models.Movie;
import com.example.netflix_project.src.main.models.Trailer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

public class HomeFrag extends Fragment {

    private View view;
    private RecyclerView moviesList, mPreviewRecyclerView;
    private MoviesAdapter adapter;
    private PreviewAdapter mPreviewAdapter;
    private MoviesRepository moviesRepository;
    //youtube
    private YouTubePlayerView mTubePlayerView;
    ArrayList<Trailer> mTrailerList;
    private static final String API_KEY = "AIzaSyCaZ_fSo1DIF7EV2tD6uwbKWtTDr7iI10c";
    private static String VIDEO_ID="xitHF0IPJSQ";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.frag_home,container,false);
        moviesRepository = MoviesRepository.getInstance();

        //----------------미리보기----------------------
        mPreviewRecyclerView=view.findViewById(R.id.home_recycler_view);
        mPreviewRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),1,GridLayoutManager.HORIZONTAL,false));

        //-----------한국 드라마 & 버라이어티--------------
        moviesList = view.findViewById(R.id.movies_list);
        moviesList.setLayoutManager(new GridLayoutManager(getContext(), 1,GridLayoutManager.HORIZONTAL, false));

        //----------------YOUTUBE VIEW-------------------
        mTrailerList=new ArrayList<>();





        getMovies();

        return view;
    }

    private void getGenres() {
        moviesRepository.getGenres(new OnGetGenresCallback() {
            @Override
            public void onSuccess(List<Genre> genres) {

            }

            @Override
            public void onError() {
                showError();
            }
        });
    }

    private void getMovies() {
        moviesRepository.getMovies(new OnGetMoviesCallback() {
            @Override
            public void onSuccess(List<Movie> movies) {
                adapter = new MoviesAdapter(movies);
                mPreviewAdapter=new PreviewAdapter(movies);
                moviesList.setAdapter(adapter);
                mPreviewRecyclerView.setAdapter(mPreviewAdapter);
            }

            @Override
            public void onError() {
                showError();
            }
        });
    }



    private void showError() {
        Toast.makeText(getContext(), "Please check your internet connection.", Toast.LENGTH_SHORT).show();
    }
}
