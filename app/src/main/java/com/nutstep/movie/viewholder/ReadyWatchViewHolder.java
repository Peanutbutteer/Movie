package com.nutstep.movie.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nutstep.movie.R;

/**
 * Created by peanutbutteer on 3/8/2016 AD.
 */
public class ReadyWatchViewHolder extends RecyclerView.ViewHolder {
    private TextView textTitle,textScore,textRuntime,textDirector;
    private ImageView imagePoster;
    public ReadyWatchViewHolder(View itemView) {
        super(itemView);
        textTitle = (TextView) itemView.findViewById(R.id.text_title);
        textScore = (TextView) itemView.findViewById(R.id.text_score);
        textRuntime = (TextView) itemView.findViewById(R.id.text_runtime);
        textDirector = (TextView) itemView.findViewById(R.id.text_director);
    }

    public void setTextTitle(String textTitle) {
        this.textTitle.setText(textTitle);
    }

    public void setTextScore(String textScore)
    {
        this.textScore.setText(textScore);
    }
    public void setTextRuntime(String textRuntime)
    {
        this.textRuntime.setText(textRuntime);
    }

    public void setTextDirector(String textDirector)
    {
        this.textDirector.setText(textDirector);
    }
}
