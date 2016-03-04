package com.nutstep.movie.manager;

import android.content.Context;


public class Contextor {

    private static Contextor instance;

    public static Contextor getInstance() {
        if (instance == null)
            instance = new Contextor();
        return instance;
    }

    private Context mContext;

    private Contextor() {
    }

    public Context getContext() {
        return mContext;
    }

    private void init(Context mContext)
    {
        this.mContext = mContext;
    }
}
