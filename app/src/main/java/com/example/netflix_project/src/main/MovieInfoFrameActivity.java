package com.example.netflix_project.src.main;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.netflix_project.R;
import com.example.netflix_project.src.BaseActivity;
import com.example.netflix_project.src.main.add.AddActivity;
import com.example.netflix_project.src.main.comingup.ComingUpActivity;
import com.example.netflix_project.src.main.download.DownloadActivity;
import com.example.netflix_project.src.main.home.HomeActivity;
import com.example.netflix_project.src.main.mainpage.BottomNavigationHelper;
import com.example.netflix_project.src.main.search.SearchActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

public class MovieInfoFrameActivity extends BaseActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    BottomNavigationHelper bottomNavigationHelper;
    private AddActivity mAddActivity;
    private MovieInfoActivity mMovieInfoActivity;
    private SearchActivity mSearchActivity;
    private DownloadActivity mDownloadActivity;
    private ComingUpActivity mComingupActivity;
    private HomeActivity mHomeActivity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);

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
        mMovieInfoActivity = new MovieInfoActivity();
        mAddActivity = new AddActivity();
        mComingupActivity = new ComingUpActivity();
        mDownloadActivity = new DownloadActivity();
        mSearchActivity = new SearchActivity();
        mHomeActivity=new HomeActivity();

        //첫 프래그먼트 화면을 HOME 으로 지정
        setFrag(0);
    }

    //프래그먼트 교체
    private void setFrag(int n) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        switch (n) {
            case 0:
                    fragmentTransaction.replace(R.id.main_frame, mMovieInfoActivity);
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