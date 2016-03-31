package com.nutstep.movie.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nutstep.movie.R;

/**
 * Created by peanutbutteer on 3/23/2016 AD.
 */
public class PhotoMovieViewHolder extends RecyclerView.ViewHolder {
    ImageView imageMovie;
    public PhotoMovieViewHolder(View itemView) {
        super(itemView);
        imageMovie = (ImageView) itemView.findViewById(R.id.image_in_movie);
    }

    public void setImageMovie(Context context,String url){
        Glide.with(context).load(url).centerCrop().into(imageMovie);
    }
}
