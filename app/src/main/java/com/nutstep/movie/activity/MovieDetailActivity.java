package com.nutstep.movie.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.nutstep.movie.ChangeListenner;
import com.nutstep.movie.R;
import com.nutstep.movie.dao.Movie;
import com.nutstep.movie.fragment.MovieDetailFragment;
import com.nutstep.movie.manager.HttpManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity implements ChangeListenner {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        initInstance();
        if(savedInstanceState==null)
        {
            MovieDetailFragment movieDetailFragment = MovieDetailFragment.newInstance();
            movieDetailFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.content_containner,movieDetailFragment).commit();
        }
    }


    private void initInstance() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


    @Override
    public void changeTitleBar(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void setSearchQuery(String query) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
