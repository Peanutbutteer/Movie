package com.nutstep.movie;

import android.app.Application;

import com.firebase.client.Firebase;
import com.nutstep.movie.manager.Contextor;

/**
 * Created by peanutbutteer on 3/4/2016 AD.
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        Contextor.getInstance().init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}