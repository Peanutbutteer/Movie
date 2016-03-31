package com.nutstep.movie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nutstep.movie.R;
import com.nutstep.movie.viewholder.CastPictureViewHolder;

/**
 * Created by peanutbutteer on 3/24/2016 AD.
 */
public class CastPictureAdapter extends RecyclerView.Adapter<CastPictureViewHolder> {
    private Context mContext;
    public CastPictureAdapter(Context context) {
        mContext = context;
    }

    @Override
    public CastPictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_detail_cast,parent,false);
        return new CastPictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CastPictureViewHolder holder, int position) {
        holder.setImageCast(mContext,"https://image.tmdb.org/t/p/w396/sntIBTOYJMKDN8rwYPtBnVMh8ld.jpg");
        holder.setTextCast("Leonardo DiCaprio");
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
