package com.example.netflix_project.src.main.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netflix_project.R;
import com.example.netflix_project.src.main.MoviesAdapter;
import com.example.netflix_project.src.main.interfaces.OnGetGenresCallback;
import com.example.netflix_project.src.main.models.MoviesRepository;
import com.example.netflix_project.src.main.interfaces.OnGetMoviesCallback;
import com.example.netflix_project.src.main.models.Genre;
import com.example.netflix_project.src.main.models.Movie;

import java.util.List;

public class HomeFrag extends Fragment {

    private View view;
    private RecyclerView moviesList;
    private MoviesAdapter adapter;
    private MoviesRepository moviesRepository;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.frag_home,container,false);

        moviesRepository = MoviesRepository.getInstance();
        moviesList = view.findViewById(R.id.movies_list);
        moviesList.setLayoutManager(new GridLayoutManager(getContext(), 1,GridLayoutManager.HORIZONTAL, false));

        getGenres();
        return view;
    }

    private void getGenres() {
        moviesRepository.getGenres(new OnGetGenresCallback() {
            @Override
            public void onSuccess(List<Genre> genres) {
                getMovies(genres);
            }

            @Override
            public void onError() {
                showError();
            }
        });
    }

    private void getMovies(final List<Genre> genres) {
        moviesRepository.getMovies(new OnGetMoviesCallback() {
            @Override
            public void onSuccess(List<Movie> movies) {
                adapter = new MoviesAdapter(movies, genres);
                moviesList.setAdapter(adapter);
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
