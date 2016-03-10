package com.nutstep.movie.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nutstep.movie.R;
import com.nutstep.movie.dao.Movie;
import com.nutstep.movie.viewholder.ReadyWatchViewHolder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by peanutbutteer on 3/8/2016 AD.
 */
public class ReadyWatchViewAdapter extends RecyclerView.Adapter<ReadyWatchViewHolder> {
    List<Movie> moiveList = new ArrayList<Movie>();

    @Override
    public ReadyWatchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_list, parent, false);
        return new ReadyWatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReadyWatchViewHolder holder, int position) {
        Movie movie = moiveList.get(position);
        holder.setTextTitle(movie.getTitle());
        holder.setTextDirector(movie.getDirector());
        holder.setTextRuntime(movie.getRuntime());
        holder.setTextScore(movie.getImdbRating());

    }

    @Override
    public int getItemCount() {
        return moiveList.size();
    }

    public void insertItemToList(Movie movie) {
        this.moiveList.add(movie);
    }

    public void removeItemFromList(String id) {
        int i=0;
        Iterator<Movie> iter = moiveList.iterator();
        while(iter.hasNext()) {
            Movie movie = iter.next();
            if(id.equals(movie.getImdbId()))
            {
                iter.remove();
                this.notifyDataSetChanged();
            }
        }
    }
}
