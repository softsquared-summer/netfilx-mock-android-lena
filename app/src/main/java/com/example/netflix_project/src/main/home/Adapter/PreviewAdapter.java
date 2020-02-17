package com.example.netflix_project.src.main.home.Adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.netflix_project.R;
import com.example.netflix_project.src.main.models.Genre;
import com.example.netflix_project.src.main.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class PreviewAdapter extends RecyclerView.Adapter<PreviewAdapter.MovieViewHolder> {
    private List<Movie> movies;
    private String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w500";

    public PreviewAdapter(List<Movie> movies) {
        this.movies = movies;

    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_preview_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView preview;
        TextView tv_preview_title;


        public MovieViewHolder(View itemView) {
            super(itemView);
            preview=itemView.findViewById(R.id.home_iv_preview);
          //  tv_preview_title=itemView.findViewById(R.id.home_iv_preview_title);
        }

        public void bind(Movie movie) {


            Glide.with(itemView)
                    .load(IMAGE_BASE_URL + movie.getPosterPath())
                    .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                    .apply(new RequestOptions().circleCrop())
                    .into(preview);

         //   tv_preview_title.setText(movie.getTitle());
        }


    }
}
