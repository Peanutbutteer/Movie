package com.nutstep.movie;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by peanutbutteer on 3/23/2016 AD.
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int mSpaceHeight;


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = mSpaceHeight;
    }

    public SpaceItemDecoration(int mSpaceHeight) {
        super();
        this.mSpaceHeight = mSpaceHeight;
    }


}
