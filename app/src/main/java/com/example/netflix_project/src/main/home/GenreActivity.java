package com.example.netflix_project.src.main.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netflix_project.R;
import com.example.netflix_project.src.BaseActivity;
import com.example.netflix_project.src.main.home.Adapter.MoviesAdapter;
import com.example.netflix_project.src.main.home.Adapter.PreviewAdapter;
import com.example.netflix_project.src.main.interfaces.MovieGenreView;
import com.example.netflix_project.src.main.interfaces.NewGenreView;
import com.example.netflix_project.src.main.interfaces.PopularMovieView;
import com.example.netflix_project.src.main.models.Movie;
import com.example.netflix_project.src.main.models.MovieService;

import java.util.List;

public class GenreActivity extends Fragment implements PopularMovieView, NewGenreView, MovieGenreView
{

    private View view;
    private String genre;
    TextView mGenreList, mMenu;
    //리사이클러뷰
    private RecyclerView mPopularRecycler, mPreviewRecycler, mNewMvRecycler;
    private MoviesAdapter mAdapterPopular, mAdapterNew;
    private PreviewAdapter mPreviewAdapter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.movie,container,false);


        genre=GenrePageActivity.genre;

        mGenreList=view.findViewById(R.id.home_movie_genre_list);
        mGenreList.setText(genre);


        //-------미리보기---------
        mPreviewRecycler=view.findViewById(R.id.movie_select_preview_recycler);
        mPreviewRecycler.setLayoutManager(new GridLayoutManager(getContext(),1,GridLayoutManager.HORIZONTAL,false));

        //------장르별 인기있는 콘텐츠-------
        mPopularRecycler=view.findViewById(R.id.movie_select_popular_recycler);
        mPopularRecycler.setLayoutManager(new GridLayoutManager(getContext(),1,GridLayoutManager.HORIZONTAL,false));

        //-------장르별 새로운 콘텐츠-------
        mNewMvRecycler=view.findViewById(R.id.movie_select_now_list);
        mNewMvRecycler.setLayoutManager(new GridLayoutManager(getContext(),1,GridLayoutManager.HORIZONTAL,false));

        //-------장르 리스트 보기---------------
        mGenreList=view.findViewById(R.id.home_movie_genre_list);
        mGenreList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), GenreListActivity.class);
                startActivity(intent);
            }
        });

        //---------전체 메뉴-------------
        mMenu=view.findViewById(R.id.movie_menu_select);
        mMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), MenuActivity.class);
                startActivity(intent);
            }
        });
        getMovies();

        return  view;
    }


    private void getMovies() {

        MovieService movieService =new MovieService((MovieGenreView)this);
        MovieService moviesRepositoryPopular=new MovieService((PopularMovieView) this);
        MovieService moviesRepositoryNew=new MovieService((NewGenreView) this);
        movieService.getMovieGenre(GenrePageActivity.num);
        moviesRepositoryPopular.getPopularGenreMovie();
        moviesRepositoryNew.getNewGenreMovie();


    }

    @Override
    public void onGenreMovieSuccess(List<Movie> movies) {

        mPreviewAdapter=new PreviewAdapter(getContext(),movies);
        mPreviewRecycler.setAdapter(mPreviewAdapter);

    }

    @Override
    public void onGenreNewMovieSuccess(List<Movie> movies) {

        mAdapterNew=new MoviesAdapter(getContext(),movies);
        mNewMvRecycler.setAdapter(mAdapterNew);
    }

    @Override
    public void onGenrePopularMovieSuccess(List<Movie> movies) {

        mAdapterPopular=new MoviesAdapter(getContext(),movies);
        mPopularRecycler.setAdapter(mAdapterPopular);

    }

    @Override
    public void onError() {

        Toast.makeText(getContext(), " getString(R.string.network_error", Toast.LENGTH_LONG).show();
    }
//    public void onClickMenu(View view){
//        Intent intent=new Intent(getApplicationContext(), MenuActivity.class);
//        startActivity(intent);
//    }
}
