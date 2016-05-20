package com.nutstep.movie.viewholder;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nutstep.movie.R;

/**
 * Created by peanutbutteer on 3/29/2016 AD.
 */
public class MovieSearchListViewHolder extends RecyclerView.ViewHolder
{
    private TextView textTitle;
    private ImageView imagePoster;
    private LinearLayout card;
    public MovieSearchListViewHolder(View itemView) {
        super(itemView);

        textTitle = (TextView) itemView.findViewById(R.id.text_title);
        imagePoster = (ImageView) itemView.findViewById(R.id.image_poster);
        card = (LinearLayout) itemView.findViewById(R.id.card_movie);
    }

    public void setImagePoster(Context context, String path)
    {
        if(path.contains("image.tmdb.org"))
        {
            Log.d("Poster",path);
           path = path.replace("w92","w1000");
        }
        Glide.with(context).load(path).centerCrop().into(imagePoster);
    }

    public void setTextTitle(String title)
    {
        textTitle.setText(title);
    }

    public void addOpenClickDetail(View.OnClickListener listener)
    {
        card.setOnClickListener(listener);
    }
}
