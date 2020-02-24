package com.example.netflix_project.src.main.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netflix_project.R;
import com.example.netflix_project.src.main.home.Adapter.MoviesAdapter;
import com.example.netflix_project.src.main.home.Adapter.PreviewAdapter;
import com.example.netflix_project.src.main.home.MovieInterface.ActionMovieView;
import com.example.netflix_project.src.main.home.MovieInterface.ComedieMovieView;
import com.example.netflix_project.src.main.home.MovieInterface.HorrorMovieView;
import com.example.netflix_project.src.main.home.MovieInterface.InternationalMovieView;
import com.example.netflix_project.src.main.home.MovieInterface.RomanceMovieView;
import com.example.netflix_project.src.main.home.MovieInterface.SFMovieView;
import com.example.netflix_project.src.main.home.MovieInterface.ThrillerMovieView;
import com.example.netflix_project.src.main.interfaces.MovieGenreView;
import com.example.netflix_project.src.main.interfaces.MovieView;
import com.example.netflix_project.src.main.interfaces.NewMovieView;
import com.example.netflix_project.src.main.mainpage.BottomNavigationHelper;
import com.example.netflix_project.src.main.models.Movie;
import com.example.netflix_project.src.main.models.MovieService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

import java.util.ArrayList;
import java.util.List;

import static com.example.netflix_project.src.ApplicationClass.TAG;

public class HomeMovieActivity extends Fragment implements MovieView, NewMovieView, HorrorMovieView, ComedieMovieView, RomanceMovieView, InternationalMovieView, ActionMovieView, SFMovieView, ThrillerMovieView {

   final int COMEDIE=2, iNTERNATIONAL=3, SF=4, THRILLER=5,ACTION=6, HORROR=9, ROMANCE=13 ;

    private View view;
    TextView mGenreList, mMenu;
    private RecyclerView mPopularmovieRecycler, mPreviewRecyclerView, mNewMovieRecycler , mRomaceMovie, mHorrorMovie, mSfMovie;
    private RecyclerView minternationalMovie, mActionMovie, mComedieMovie, mThrillrerMovie ;
    private MoviesAdapter adapter, mAdapterNew;
    private PreviewAdapter mPreviewAdapter;
    private BottomNavigationView bottomNavigationView;
    BottomNavigationHelper bottomNavigationHelper;
    MovieService movieService;

    ProgressBar mProgressDialog;
    boolean first = true;
    int lastNo = -1;
    LinearLayoutManager manager;
    List<Movie> items = new ArrayList<>();
    Boolean isScrolling = true;


    int CurrentItem, totaltems, scrollOutItems;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.movie, container, false);


        //-------장르 리스트 보기---------------
        mGenreList = view.findViewById(R.id.home_movie_genre_list);
        mGenreList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GenreListActivity.class);
                startActivity(intent);
            }
        });

        //---------전체 메뉴-------------
        mMenu = view.findViewById(R.id.movie_menu_select);
        mMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MenuActivity.class);
                startActivity(intent);
            }
        });


        //---------Movie RecyclerVIew --------------------

       //인기 콘텐츠
        mPopularmovieRecycler = view.findViewById(R.id.movie_select_popular_recycler);
        mPopularmovieRecycler.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false));
        mPopularmovieRecycler.addOnScrollListener(onScrollListener);

        //지금뜨는 콘텐츠
        mNewMovieRecycler = view.findViewById(R.id.movie_select_now_list);
        mNewMovieRecycler.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false));

        //미리보기
        mPreviewRecyclerView = view.findViewById(R.id.movie_select_preview_recycler);
        mPreviewRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.HORIZONTAL, false));


        //호러
        mHorrorMovie=view.findViewById(R.id.movie_horror);
        mHorrorMovie.setLayoutManager(new GridLayoutManager(getContext(),1,GridLayoutManager.HORIZONTAL,false));

        //로맨스
        mRomaceMovie=view.findViewById(R.id.movie_romance);
        mRomaceMovie.setLayoutManager(new GridLayoutManager(getContext(),1,GridLayoutManager.HORIZONTAL,false));
        //sf
        mSfMovie=view.findViewById(R.id.movie_SF);
        mSfMovie.setLayoutManager(new GridLayoutManager(getContext(),1,GridLayoutManager.HORIZONTAL,false));

        //외국 영화
        minternationalMovie=view.findViewById(R.id.movie_international);
        minternationalMovie.setLayoutManager(new GridLayoutManager(getContext(),1,GridLayoutManager.HORIZONTAL,false));

        //코미디
        mComedieMovie=view.findViewById(R.id.movie_comedies);
        mComedieMovie.setLayoutManager(new GridLayoutManager(getContext(),1,GridLayoutManager.HORIZONTAL,false));

        //액션
        mActionMovie=view.findViewById(R.id.movie_action);
        mActionMovie.setLayoutManager(new GridLayoutManager(getContext(),1,GridLayoutManager.HORIZONTAL,false));

        //스릴러
        mThrillrerMovie=view.findViewById(R.id.movie_thriller);
        mThrillrerMovie.setLayoutManager(new GridLayoutManager(getContext(),1,GridLayoutManager.HORIZONTAL,false));

        //하단바
        bottomNavigationView = view.findViewById(R.id.movie_menu_bottom_navi);
        bottomNavigationHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);

        getMovies(lastNo);

        return view;

    }


    private void getMovies(int lastNo) {


        movieService = new MovieService(this, this);
        movieService.getPopularMovies(lastNo);
        movieService.getNewMovies();
        movieService = new MovieService((InternationalMovieView) this);
        movieService.getGenreMovie(iNTERNATIONAL);
        movieService=new MovieService((ComedieMovieView) this);
        movieService.getGenreMovie(COMEDIE);
        movieService=new MovieService((RomanceMovieView) this);
        movieService.getGenreMovie(ROMANCE);
        movieService=new MovieService((HorrorMovieView) this);
        movieService.getGenreMovie(HORROR);
        movieService=new MovieService((ThrillerMovieView) this);
        movieService.getGenreMovie(THRILLER);
        movieService=new MovieService((ActionMovieView) this);
        movieService.getGenreMovie(ACTION);
        movieService=new MovieService((SFMovieView) this);
        movieService.getGenreMovie(SF);
    }

    @Override
    public void onPopularMovieSuccess(List<Movie> movies) {

        lastNo = MovieService.mLastNo;
        items.addAll(movies);
        adapter = new MoviesAdapter(getContext(), items);
        mPreviewAdapter = new PreviewAdapter(getContext(), items);
        mPopularmovieRecycler.setAdapter(adapter);
        mPreviewRecyclerView.setAdapter(mPreviewAdapter);
    }

    @Override
    public void onNewMovieSuccess(List<Movie> movies) {

        mAdapterNew = new MoviesAdapter(getContext(), movies);
        mNewMovieRecycler.setAdapter(mAdapterNew);
    }


    @Override
    public void onActionSuccess(List<Movie> movies) {
        adapter=new MoviesAdapter(getContext(),movies);
        mActionMovie.setAdapter(adapter);

    }

    @Override
    public void onComedieSuccess(List<Movie> movies) {
        adapter=new MoviesAdapter(getContext(),movies);
        mComedieMovie.setAdapter(adapter);

    }

    @Override
    public void onHorrorSuccess(List<Movie> movies) {
        adapter=new MoviesAdapter(getContext(),movies);
        mHorrorMovie.setAdapter(adapter);
    }

    @Override
    public void onInternationalSuccess(List<Movie> movies) {
        adapter=new MoviesAdapter(getContext(),movies);
        minternationalMovie.setAdapter(adapter);
    }

    @Override
    public void onRomaceSuccess(List<Movie> movies) {
        adapter=new MoviesAdapter(getContext(),movies);
        mRomaceMovie.setAdapter(adapter);
    }

    @Override
    public void onSFSuccess(List<Movie> movies) {
        adapter=new MoviesAdapter(getContext(),movies);
        mSfMovie.setAdapter(adapter);
    }

    @Override
    public void onThriSuccess(List<Movie> movies) {

        adapter=new MoviesAdapter(getContext(),movies);
        mThrillrerMovie.setAdapter(adapter);
    }

    @Override
    public void onError() {

        Toast.makeText(getContext(), " getString(R.string.network_error", Toast.LENGTH_LONG).show();
    }


   // 무한 스크롤
    RecyclerView.OnScrollListener onScrollListener= new RecyclerView.OnScrollListener() {

       @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int lastVisibleItemPosition = ((GridLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
            int itemTotalCount = recyclerView.getAdapter().getItemCount() - 1;
            if (lastVisibleItemPosition == itemTotalCount) {
                Log.d(TAG, "last Position...");
                lastNo = MovieService.mLastNo;
                   // getMovies(lastNo);
            }
        }
    };

//    RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
//        @Override
//        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//            super.onScrollStateChanged(recyclerView, newState);
//            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
//                isScrolling = true;
//            }
//        }
//
//        @Override
//        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//            super.onScrolled(recyclerView, dx, dy);
//            if (dy > 0) {
//
//            int lastVisibleItemPosition = ((GridLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
//            int itemTotalCount = recyclerView.getAdapter().getItemCount() - 1;
//
//            if (lastVisibleItemPosition == itemTotalCount) {
//                    Log.i("scroll","마지막 아이템");
//                    lastNo = MovieService.mLastNo;
//                   isScrolling=false;
//                    getMovies(lastNo);
//                }
//            }
//        }
//
//
//    };
//
//    public void updateData(){
//
//
//    }

}

