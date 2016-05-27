package com.nutstep.movie.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nutstep.movie.R;
import com.nutstep.movie.dao.v2.MovieShowTime;

import java.util.List;

/**
 * Created by peanutbutteer on 5/20/2016 AD.
 */

public class TheaterSearchViewHolder extends RecyclerView.ViewHolder {
    private TextView textTheaterName,textDistance,textShowtime;
    private CardView cardView;
    public TheaterSearchViewHolder(View itemView) {
        super(itemView);
        textTheaterName = (TextView) itemView.findViewById(R.id.text_theater_name);
        textDistance = (TextView) itemView.findViewById(R.id.text_distance);
        textShowtime = (TextView) itemView.findViewById(R.id.text_showtime);
        cardView = (CardView) itemView.findViewById(R.id.card);
    }

    public void setTextTheaterName(String name){
        textTheaterName.setText(name);
    }

    public void setTextDistance(double distance){
        textDistance.setText(String.valueOf((int)distance)+"KM.");
    }

    public void setTextShowtime(String showtime){
        textShowtime.setText(showtime);
    }

    public void setItemClick(View.OnClickListener listener){
        cardView.setOnClickListener(listener);
    }

}
