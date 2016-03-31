package com.nutstep.movie.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nutstep.movie.ChangeListenner;
import com.nutstep.movie.R;
import com.nutstep.movie.dao.Movie;
import com.nutstep.movie.fragment.MovieDetailFragment;
import com.nutstep.movie.manager.HttpManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity implements ChangeListenner,MovieDetailFragment.DetailFragment {

    ImageView imageBackdrop;
    ActionBar actionBar;
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
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        imageBackdrop = (ImageView) findViewById(R.id.image_backdrop);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
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

    @Override
    public void setImagePoster(String path) {
        Glide.with(this).load("https://image.tmdb.org/t/p/w1000/"+path).centerCrop().into(imageBackdrop);
    }
}
