package com.nutstep.movie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nutstep.movie.R;
import com.nutstep.movie.dao.Backdrop;
import com.nutstep.movie.viewholder.PhotoMovieViewHolder;

import java.util.List;

/**
 * Created by peanutbutteer on 3/23/2016 AD.
 */
public class PhotoMovieAdapter extends RecyclerView.Adapter<PhotoMovieViewHolder> {
    Context mContext;
    private List<Backdrop> backdropList;
    @Override
    public PhotoMovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_detail_picture,parent,false);
        return new PhotoMovieViewHolder(view);
    }

    public PhotoMovieAdapter(Context context) {
        mContext = context;
    }

    @Override
    public void onBindViewHolder(PhotoMovieViewHolder holder, int position) {
        holder.setImageMovie(mContext,"https://image.tmdb.org/t/p/w500"+backdropList.get(position).getFilePath());
    }

    public void setBackdropList(List<Backdrop> list)
    {
        this.backdropList = list;
    }

    @Override
    public int getItemCount() {
        return backdropList!=null&&backdropList.size()>0? backdropList.size():0;
    }
}
