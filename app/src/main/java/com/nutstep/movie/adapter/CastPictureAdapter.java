package com.nutstep.movie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nutstep.movie.R;
import com.nutstep.movie.dao.Cast;
import com.nutstep.movie.viewholder.CastPictureViewHolder;

import java.util.List;

/**
 * Created by peanutbutteer on 3/24/2016 AD.
 */
public class CastPictureAdapter extends RecyclerView.Adapter<CastPictureViewHolder> {
    private Context mContext;
    private List<Cast> castList;
    public CastPictureAdapter(Context context) {
        mContext = context;
    }

    @Override
    public CastPictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_detail_cast,parent,false);
        return new CastPictureViewHolder(view);
    }

    public void setCastList(List<Cast> list)
    {
        this.castList = list;
    }

    @Override
    public void onBindViewHolder(CastPictureViewHolder holder, int position) {
        holder.setImageCast(mContext,"https://image.tmdb.org/t/p/w396"+castList.get(position).getProfilePath());
        holder.setTextCast(castList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return (castList==null||castList.size()<=0)?0:castList.size();
    }
}
