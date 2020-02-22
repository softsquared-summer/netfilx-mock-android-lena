package com.example.netflix_project.src.main.home.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.netflix_project.R;
import com.example.netflix_project.src.main.home.MovieGenreActivity;
import com.example.netflix_project.src.main.models.Genre;

import java.util.List;

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.GenreViewHolder> {
    private List<Genre> allGenres;
    private Context mContext;
    public GenresAdapter(Context mContext, List<Genre> allGenres) {
        this.allGenres = allGenres;
        this.mContext=mContext;
    }

    @Override
    public GenreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.genre_list_item, parent, false);
        return new GenreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final GenreViewHolder holder, final int position) {
        holder.bind(allGenres.get(position));

        holder.mGenreList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, MovieGenreActivity.class);
                intent.putExtra("genre_no",allGenres.get(position).getNo());
                intent.putExtra("genre",allGenres.get(position).getDescription());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allGenres.size();
    }

    class GenreViewHolder extends RecyclerView.ViewHolder {
        TextView mGenreList;


        public GenreViewHolder(View itemView) {
            super(itemView);
            mGenreList = itemView.findViewById(R.id.genre_tv_item);

        }

        public void bind(Genre genre) {


            mGenreList.setText(genre.getDescription());

        }


    }
}
