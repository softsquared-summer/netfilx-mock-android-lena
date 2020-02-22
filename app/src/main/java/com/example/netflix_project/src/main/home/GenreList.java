package com.example.netflix_project.src.main.home;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netflix_project.R;
import com.example.netflix_project.src.BaseActivity;
import com.example.netflix_project.src.main.home.Adapter.GenresAdapter;
import com.example.netflix_project.src.main.interfaces.OnGetGenresCallback;
import com.example.netflix_project.src.main.models.Genre;
import com.example.netflix_project.src.main.models.GenresResponsitory;

import java.util.List;

public class GenreList extends BaseActivity implements OnGetGenresCallback {

    private RecyclerView mGenreRecyclerView;
    private GenresAdapter mGenresAdapter;
    private ImageView mCloseList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genre_select);


        mCloseList=findViewById(R.id.genre_iv_close);
        mCloseList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //--------장르 리스트
        mGenreRecyclerView=findViewById(R.id.genre_select_recycler);
        mGenreRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        getGenre();
    }

    public  void getGenre(){

        GenresResponsitory genresResponsitory=new GenresResponsitory(this);
        genresResponsitory.getGenreList();
    }

    @Override
    public void onSuccess(List<Genre> genres) {

        mGenresAdapter=new GenresAdapter(getApplicationContext(),genres);
        mGenreRecyclerView.setAdapter(mGenresAdapter);


    }

    @Override
    public void onError() {

        showError();

    }
}
