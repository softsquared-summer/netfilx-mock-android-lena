package com.example.netflix_project.src.main.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netflix_project.R;
import com.example.netflix_project.src.BaseActivity;
import com.example.netflix_project.src.main.add.AddFrag;
import com.example.netflix_project.src.main.comingup.ComingUpFrag;
import com.example.netflix_project.src.main.download.DownloadFrag;
import com.example.netflix_project.src.main.home.Adapter.MoviesAdapter;
import com.example.netflix_project.src.main.home.Adapter.PreviewAdapter;
import com.example.netflix_project.src.main.interfaces.OnGetGenresCallback;
import com.example.netflix_project.src.main.interfaces.OnGetMoviesCallback;
import com.example.netflix_project.src.main.interfaces.OnGetNewMoviesCallback;
import com.example.netflix_project.src.main.mainpage.BottomNavigationHelper;
import com.example.netflix_project.src.main.models.Genre;
import com.example.netflix_project.src.main.models.Movie;
import com.example.netflix_project.src.main.models.MoviesRepository;
import com.example.netflix_project.src.main.search.SearchFrag;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MovieSelect extends BaseActivity implements OnGetMoviesCallback, OnGetNewMoviesCallback {

    TextView mGenreSelect;
    private RecyclerView mPopularmovieRecycler, mPreviewRecyclerView, mNewMovieRecycler;
    private MoviesAdapter adapter, mAdapterNew;
    private PreviewAdapter mPreviewAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_home_movie);

        //-------장르 리스트 보기---------------
        mGenreSelect=findViewById(R.id.home_movie_genre_list);
        mGenreSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),GenreList.class);
                startActivity(intent);
            }
        });

        //---------Movie RecyclerVIew --------------------

        mPopularmovieRecycler=findViewById(R.id.movie_select_popular_recycler);
        mPopularmovieRecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(),1,GridLayoutManager.HORIZONTAL,false));
        mNewMovieRecycler=findViewById(R.id.movie_select_now_list);
        mNewMovieRecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(),1,GridLayoutManager.HORIZONTAL,false));
        mPreviewRecyclerView=findViewById(R.id.movie_select_preview_recycler);
        mPreviewRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1,GridLayoutManager.HORIZONTAL,false));

        getMovies();

    }

    private void getMovies() {

        MoviesRepository moviesRepository=new MoviesRepository(this,this);
        moviesRepository.getPopularMovies();
        moviesRepository.getNewMovies();

    }

    @Override
    public void onPopularMovieSuccess(List<Movie> movies) {
        adapter = new MoviesAdapter(getApplicationContext(),movies);
        mPreviewAdapter=new PreviewAdapter(getApplicationContext(),movies);
        mPopularmovieRecycler.setAdapter(adapter);
        mPreviewRecyclerView.setAdapter(mPreviewAdapter);
    }

    @Override
    public void onNewMovieSuccess(List<Movie> movies) {
        mAdapterNew=new MoviesAdapter(getApplicationContext(),movies);
        mNewMovieRecycler.setAdapter(mAdapterNew);
    }

    @Override
    public void onError() {

        showError();
    }

    public void onClickMenu(View view){
        Intent intent=new Intent(getApplicationContext(),HomeMenu.class);
        startActivity(intent);
    }
}
