package com.nutstep.movie;

import android.app.Application;

import com.nutstep.movie.manager.Contextor;

/**
 * Created by peanutbutteer on 3/4/2016 AD.
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        Contextor.getInstance().init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}