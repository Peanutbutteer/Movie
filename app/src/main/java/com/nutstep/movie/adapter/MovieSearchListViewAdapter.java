package com.nutstep.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.nutstep.movie.ChangeListenner;
import com.nutstep.movie.R;
import com.nutstep.movie.activity.MovieDetailActivity;
import com.nutstep.movie.activity.TheaterFindActivity;
import com.nutstep.movie.dao.Result;
import com.nutstep.movie.dao.v2.Movie;
import com.nutstep.movie.viewholder.MovieSearchListViewHolder;
import com.nutstep.movie.viewholder.SearchViewHolder;

import java.util.List;

/**
 * Created by peanutbutteer on 3/7/2016 AD.
 */
public class MovieSearchListViewAdapter extends RecyclerView.Adapter<MovieSearchListViewHolder> {

    private List<Movie> list;
    private Context context;
    public MovieSearchListViewAdapter(Context context) {
        this.context = context;
    }

    public void updateList(List<Movie> list)
    {
        this.list = list;
    }

    @Override
    public MovieSearchListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutInflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_movie_list,parent,false);
        return new MovieSearchListViewHolder(layoutInflater);
    }

    @Override
    public void onBindViewHolder(MovieSearchListViewHolder holder, final int position) {
        final Movie movie = list.get(position);
        holder.setTextTitle(movie.getTitle());
        if(movie.getImages()!=null&&movie.getImages().size()>0) holder.setImagePoster(context,movie.getImages().get(0).getUrl());
        holder.addOpenClickDetail
                (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TheaterFindActivity.class);
                intent.putExtra("movie",new Gson().toJson(movie,Movie.class));
                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return (list!=null)?list.size():0;
    }
}
