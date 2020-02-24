package com.example.netflix_project.src.main.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netflix_project.R;
import com.example.netflix_project.src.BaseActivity;
import com.example.netflix_project.src.main.add.AddActivity;
import com.example.netflix_project.src.main.comingup.ComingUpActivity;
import com.example.netflix_project.src.main.download.DownloadActivity;
import com.example.netflix_project.src.main.home.Adapter.MoviesAdapter;
import com.example.netflix_project.src.main.home.Adapter.PreviewAdapter;
import com.example.netflix_project.src.main.interfaces.MovieGenreView;
import com.example.netflix_project.src.main.interfaces.NewGenreView;
import com.example.netflix_project.src.main.interfaces.PopularMovieView;
import com.example.netflix_project.src.main.mainpage.BottomNavigationHelper;
import com.example.netflix_project.src.main.models.Movie;
import com.example.netflix_project.src.main.models.MovieService;
import com.example.netflix_project.src.main.search.SearchActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

import java.util.List;

public class GenrePageActivity extends BaseActivity
{


    private BottomNavigationView bottomNavigationView;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    BottomNavigationHelper bottomNavigationHelper;
    private AddActivity mAddActivity;
    private GenreActivity mGenreActivity;
    private SearchActivity mSearchActivity;
    private DownloadActivity mDownloadActivity;
    private ComingUpActivity mComingupActivity;

    public static int num;
    public static String genre;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);

        Intent intent=getIntent();
        num=intent.getIntExtra("genre_no",1);
        genre=intent.getStringExtra("genre");


        bottomNavigationView = findViewById(R.id.menu_bottom_navi);
        bottomNavigationHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        setFrag(0);
                        break;
                    case R.id.action_search:
                        setFrag(1);
                        break;
                    case R.id.action_comigup:
                        setFrag(2);
                        break;
                    case R.id.action_download:
                        setFrag(3);
                        break;
                    case R.id.action_menu:
                        setFrag(4);
                        break;
                }
                return true;
            }
        });

        // 5개의 메뉴에 들어갈 Fragment들
        mGenreActivity = new GenreActivity();
        mAddActivity = new AddActivity();
        mComingupActivity = new ComingUpActivity();
        mDownloadActivity = new DownloadActivity();
        mSearchActivity = new SearchActivity();

        //첫 프래그먼트 화면을 HOME 으로 지정
        setFrag(0);
    }

    //프래그먼트 교체
    private void setFrag(int n) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        switch (n) {
            case 0:
                fragmentTransaction.replace(R.id.main_frame, mGenreActivity);
                fragmentTransaction.commit();
                break;
            case 1:
                fragmentTransaction.replace(R.id.main_frame, mSearchActivity);
                fragmentTransaction.commit();
                break;
            case 2:
                fragmentTransaction.replace(R.id.main_frame, mComingupActivity);
                fragmentTransaction.commit();
                break;
            case 3:
                fragmentTransaction.replace(R.id.main_frame, mDownloadActivity);
                fragmentTransaction.commit();
                break;
            case 4:
                fragmentTransaction.replace(R.id.main_frame, mAddActivity);
                fragmentTransaction.commit();
                break;

        }

    }


}
