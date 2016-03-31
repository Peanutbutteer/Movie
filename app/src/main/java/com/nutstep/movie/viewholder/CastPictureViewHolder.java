package com.nutstep.movie.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nutstep.movie.R;

/**
 * Created by peanutbutteer on 3/24/2016 AD.
 */
public class CastPictureViewHolder extends RecyclerView.ViewHolder {
    TextView textCast;
    ImageView imageCast;
    public CastPictureViewHolder(View itemView) {
        super(itemView);
        textCast = (TextView) itemView.findViewById(R.id.text_cast_name);
        imageCast = (ImageView) itemView.findViewById(R.id.image_cast);
    }
    public void setImageCast(Context context, String url)
    {
        Glide.with(context).load(url).centerCrop().into(imageCast);
    }
    public void setTextCast(String name){
        textCast.setText(name);
    }
}
