package com.nutstep.movie.viewholder;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nutstep.movie.R;

/**
 * Created by peanutbutteer on 3/7/2016 AD.
 */
public class SearchViewHolder extends RecyclerView.ViewHolder {
    TextView textTitle,textYear,textGenre;
    RelativeLayout itemContainner;
    ImageView imagePoster;
    public SearchViewHolder(View itemView) {
        super(itemView);
        itemContainner = (RelativeLayout) itemView.findViewById(R.id.item_containner);
        textTitle = (TextView) itemView.findViewById(R.id.text_title);
        textYear = (TextView) itemView.findViewById(R.id.text_year);
        textGenre = (TextView) itemView.findViewById(R.id.text_genre);
        imagePoster = (ImageView) itemView.findViewById(R.id.image_poster);
    }

    public void changeTitleText(String title)
    {
        textTitle.setText(title);
    }

    public void changeYearText(String year)
    {
        textYear.setText(year);
    }

    public void changeGenreText(String text){
        textGenre.setText(text);
    }

    public void setImagePoster(Context context, String url)
    {
        Glide.with(context).load("https://image.tmdb.org/t/p/w1000/"+url).centerCrop().into(imagePoster);
    }

    public void setBtnShowDetailLintener(View.OnClickListener listener)
    {
        itemContainner.setOnClickListener(listener);
    }
}
