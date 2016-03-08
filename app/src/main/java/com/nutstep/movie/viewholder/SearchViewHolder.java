package com.nutstep.movie.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nutstep.movie.R;

/**
 * Created by peanutbutteer on 3/7/2016 AD.
 */
public class SearchViewHolder extends RecyclerView.ViewHolder {
    TextView textTitle;
    ImageView btnToQuery;
    LinearLayout btnShowDetail;
    public SearchViewHolder(View itemView) {
        super(itemView);
        textTitle = (TextView) itemView.findViewById(R.id.text_title);
        btnShowDetail = (LinearLayout) itemView.findViewById(R.id.btn_show_detail);
        btnToQuery = (ImageView) itemView.findViewById(R.id.btn_toquery);
    }

    public void changeTitleText(String title)
    {
        textTitle.setText(title);
    }

    public void setBtnToQueryListener(View.OnClickListener listener)
    {
        btnToQuery.setClickable(true);
        btnToQuery.setOnClickListener(listener);
    }

    public void setBtnShowDetailLintener(View.OnClickListener listener)
    {
        btnShowDetail.setOnClickListener(listener);
    }
}
