package com.example.netflix_project.src.main.home.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.example.netflix_project.R;
import com.example.netflix_project.src.main.MovieInfoFrameActivity;
import com.example.netflix_project.src.main.models.Movie;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private List<Movie> movies;
    private Context mContext;
    private String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w500";

    public MoviesAdapter( Context mContext,List<Movie> movies) {
        this.movies = movies;
        this.mContext=mContext;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        holder.bind(movies.get(position));

        holder.poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, MovieInfoFrameActivity.class);
                intent.putExtra("movie_no",movies.get(position).getNo());
               mContext.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));



            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView poster;


        public MovieViewHolder(View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.item_movie_poster);

        }

        public void bind(Movie movie) {

            Glide.with(itemView)
                    .load(IMAGE_BASE_URL + movie.getPosterUrl())
                    .apply(RequestOptions.placeholderOf(R.color.colorNetflicBack))
                    .into(poster);




        }

//        private String getGenres(List<Integer> genreIds) {
//            List<String> movieGenres = new ArrayList<>();
//            for (Integer genreId : genreIds) {
//                for (Genre genre : allGenres) {
//                    if (genre.getId() == genreId) {
//                        movieGenres.add(genre.getName());
//                        break;
//                    }
//                }
//            }
//            return TextUtils.join(", ", movieGenres);
//        }
    }
}
