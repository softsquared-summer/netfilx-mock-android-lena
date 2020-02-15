package com.example.netflix_project.src.main.mainpage;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.netflix_project.R;
import com.example.netflix_project.src.main.add.AddFrag;
import com.example.netflix_project.src.main.comingup.ComingUpFrag;
import com.example.netflix_project.src.main.download.DownloadFrag;
import com.example.netflix_project.src.main.home.HomeFrag;
import com.example.netflix_project.src.main.search.SearchFrag;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

public class MainPage extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;

    private AddFrag mAddFrag;
    private HomeFrag mHomeFrag;
    private SearchFrag mSearchFrag;
    private DownloadFrag mDownloadFrag;
    private ComingUpFrag mComingupFrag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);

        bottomNavigationView=findViewById(R.id.menu_bottom_navi);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        BottomNavigationHelper.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
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
        mHomeFrag=new HomeFrag();
        mAddFrag=new AddFrag();
        mComingupFrag=new ComingUpFrag();
        mDownloadFrag=new DownloadFrag();
        mSearchFrag=new SearchFrag();

        //첫 프래그먼트 화면을 HOME 으로 지정
        setFrag(0);
    }

    //프래그먼트 교체
    private void setFrag(int n)
    {
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();

        switch (n){
            case 0:
                fragmentTransaction.replace(R.id.main_frame,mHomeFrag);
                fragmentTransaction.commit();
                break;
            case 1:
                fragmentTransaction.replace(R.id.main_frame,mSearchFrag);
                fragmentTransaction.commit();
                break;
            case 2:
                fragmentTransaction.replace(R.id.main_frame,mComingupFrag);
                fragmentTransaction.commit();
                break;
            case 3:
                fragmentTransaction.replace(R.id.main_frame,mDownloadFrag);
                fragmentTransaction.commit();
                break;
            case 4:
                fragmentTransaction.replace(R.id.main_frame,mAddFrag);
                fragmentTransaction.commit();
                break;

        }

    }
}
