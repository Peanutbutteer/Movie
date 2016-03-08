package com.nutstep.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nutstep.movie.ChangeListenner;
import com.nutstep.movie.R;
import com.nutstep.movie.activity.MovieDetailActivity;
import com.nutstep.movie.dao.Movie;
import com.nutstep.movie.viewholder.SearchViewHolder;

import java.util.List;

/**
 * Created by peanutbutteer on 3/7/2016 AD.
 */
public class SearchViewAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    private List<Movie> list;
    private ChangeListenner changeListenner;
    private Context context;
    public SearchViewAdapter(Context context) {
        this.context = context;
        try {
            changeListenner = (ChangeListenner) context;
        } catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    public void updateList(List<Movie> list)
    {
        this.list = list;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutInflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search,parent,false);
        return new SearchViewHolder(layoutInflater);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, final int position) {
        final Movie movie = list.get(position);
        holder.changeTitleText(movie.getTitle());
        holder.setBtnToQueryListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeListenner.setSearchQuery(movie.getTitle());
            }
        });
        holder.setBtnShowDetailLintener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra("id",list.get(position).getImdbId());
                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return (list!=null)?list.size():0;
    }
}
