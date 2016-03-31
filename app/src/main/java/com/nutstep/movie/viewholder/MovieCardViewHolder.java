package com.nutstep.movie.viewholder;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nutstep.movie.R;

/**
 * Created by peanutbutteer on 3/29/2016 AD.
 */
public class MovieCardViewHolder extends RecyclerView.ViewHolder
{
    private TextView textTitle,textScore,textGenre;
    private ImageView imagePoster;
    private CardView card;
    public MovieCardViewHolder(View itemView) {
        super(itemView);

        textTitle = (TextView) itemView.findViewById(R.id.text_title);
        imagePoster = (ImageView) itemView.findViewById(R.id.image_poster);
        textGenre = (TextView) itemView.findViewById(R.id.text_genre);
        textScore = (TextView) itemView.findViewById(R.id.text_score);
        card = (CardView) itemView.findViewById(R.id.cardPoster);
    }

    public void setImagePoster(Context context, String path)
    {
        Glide.with(context).load("https://image.tmdb.org/t/p/w185/"+path).into(imagePoster);
    }

    public void setTextTitle(String title)
    {
        textTitle.setText(title);
    }

    public void setTextGenre(String genre)
    {
        textGenre.setText(genre);
    }

    public void setTextScore(String text)
    {
        textScore.setText(text);
    }

    public void addOpenClickDetail(View.OnClickListener listener)
    {
        card.setOnClickListener(listener);
    }
}
