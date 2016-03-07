package com.nutstep.movie.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.nutstep.movie.R;
import com.nutstep.movie.manager.Contextor;
import com.nutstep.movie.viewholder.TimelineViewHolder;

/**
 * Created by peanutbutteer on 3/4/2016 AD.
 */
public class TimelineViewAdapter extends RecyclerView.Adapter<TimelineViewHolder> {
    @Override
    public TimelineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View relativeLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timeline,parent,false);
        return new TimelineViewHolder(relativeLayout);
    }

    @Override
    public void onBindViewHolder(TimelineViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
