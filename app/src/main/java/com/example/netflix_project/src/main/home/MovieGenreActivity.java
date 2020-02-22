package com.example.netflix_project.src.main.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netflix_project.R;
import com.example.netflix_project.src.BaseActivity;
import com.example.netflix_project.src.main.home.Adapter.MoviesAdapter;
import com.example.netflix_project.src.main.home.Adapter.PreviewAdapter;
import com.example.netflix_project.src.main.interfaces.OnGetGenreMoviesCallback;
import com.example.netflix_project.src.main.interfaces.OnGetGenreNewMoviesCallback;
import com.example.netflix_project.src.main.interfaces.OnGetGenrePopularMoviesCallback;
import com.example.netflix_project.src.main.interfaces.OnGetMoviesDetailCallback;
import com.example.netflix_project.src.main.models.Movie;
import com.example.netflix_project.src.main.models.MoviesRepository;

import java.util.List;

public class MovieGenreActivity extends BaseActivity implements OnGetGenrePopularMoviesCallback, OnGetGenreNewMoviesCallback, OnGetGenreMoviesCallback
{

    public static int num;
    private String genre;
    private TextView mGenreSelect;

    //리사이클러뷰
    private RecyclerView mPopularRecycler, mPreviewRecycler, mNewMvRecycler;
    private MoviesAdapter mAdapterPopular, mAdapterNew;
    private PreviewAdapter mPreviewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_home_movie);

        Intent intent=getIntent();
        num=intent.getIntExtra("genre_no",1);
        genre=intent.getStringExtra("genre");

        mGenreSelect=findViewById(R.id.home_movie_genre_list);
        mGenreSelect.setText(genre);


        //-------미리보기---------
        mPreviewRecycler=findViewById(R.id.movie_select_preview_recycler);
        mPreviewRecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(),1,GridLayoutManager.HORIZONTAL,false));

        //------장르별 인기있는 콘텐츠-------
        mPopularRecycler=findViewById(R.id.movie_select_popular_recycler);
        mPopularRecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(),1,GridLayoutManager.HORIZONTAL,false));

        //-------장르별 새로운 콘텐츠-------
        mNewMvRecycler=findViewById(R.id.movie_select_now_list);
        mNewMvRecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(),1,GridLayoutManager.HORIZONTAL,false));

        //-------장르 리스트 보기---------------
        mGenreSelect=findViewById(R.id.home_movie_genre_list);
        mGenreSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),GenreList.class);
                startActivity(intent);
            }
        });

        getMovies();

    }


    private void getMovies() {

        MoviesRepository moviesRepository=new MoviesRepository((OnGetGenreMoviesCallback)this);
        MoviesRepository moviesRepositoryPopular=new MoviesRepository((OnGetGenrePopularMoviesCallback) this);
        MoviesRepository moviesRepositoryNew=new MoviesRepository((OnGetGenreNewMoviesCallback) this);
        moviesRepository.getMovieGenre();
        moviesRepositoryPopular.getPopularGenreMovie();
        moviesRepositoryNew.getNewGenreMovie();


    }

    @Override
    public void onGenreMovieSuccess(List<Movie> movies) {

        mPreviewAdapter=new PreviewAdapter(getApplicationContext(),movies);
        mPreviewRecycler.setAdapter(mPreviewAdapter);

    }

    @Override
    public void onGenreNewMovieSuccess(List<Movie> movies) {

        mAdapterNew=new MoviesAdapter(getApplicationContext(),movies);
        mNewMvRecycler.setAdapter(mAdapterNew);
    }

    @Override
    public void onGenrePopularMovieSuccess(List<Movie> movies) {

        mAdapterPopular=new MoviesAdapter(getApplicationContext(),movies);
        mPopularRecycler.setAdapter(mAdapterPopular);

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
