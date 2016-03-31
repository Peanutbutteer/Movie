package com.nutstep.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nutstep.movie.R;
import com.nutstep.movie.activity.MovieDetailActivity;
import com.nutstep.movie.dao.Intheater;
import com.nutstep.movie.dao.MovieIntheaterResult;
import com.nutstep.movie.viewholder.MovieCardViewHolder;

import java.util.List;

/**
 * Created by peanutbutteer on 3/29/2016 AD.
 */
public class MovieGridsAdapter extends RecyclerView.Adapter<MovieCardViewHolder> {

    private List<MovieIntheaterResult> movies;
    private Context context;

    public MovieGridsAdapter(Context context) {
        this.context = context;
    }

    public void setMovies(List<MovieIntheaterResult> list){
        this.movies = list;
    }

    @Override
    public MovieCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_poster,parent,false);
        return new MovieCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieCardViewHolder holder, final int position) {
        holder.setImagePoster(context,movies.get(position).getPosterPath());
        holder.setTextTitle(movies.get(position).getTitle());
        holder.setTextScore(String.valueOf(movies.get(position).getVoteAverage()));
        holder.addOpenClickDetail(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra("id",movies.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies!=null&&movies.size()!=0?movies.size():0;
    }
}
