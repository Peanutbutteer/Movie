package com.nutstep.movie.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.speech.RecognizerIntent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.nutstep.movie.ChangeListenner;
import com.nutstep.movie.R;
import com.nutstep.movie.adapter.MainViewPagerAdapter;
import com.nutstep.movie.fragment.MainFragment;
import com.nutstep.movie.fragment.SearchFragment;
import com.nutstep.movie.fragment.TimelineFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ChangeListenner {
    MaterialSearchView searchView;
    RelativeLayout contentContainner;
    MainFragment mainFragment;
    TimelineFragment timelineFragment;
    SearchFragment searchFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstance();
    }
    private void initInstance()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contentContainner = (RelativeLayout) findViewById(R.id.content_containner);


        mainFragment = MainFragment.newInstance();
        timelineFragment = TimelineFragment.newInstance();
        searchFragment = SearchFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.content_containner, mainFragment).commit();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);


        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                getSupportFragmentManager().beginTransaction().add(R.id.content_containner, searchFragment ).commit();
            }

            @Override
            public void onSearchViewClosed() {
                getSupportFragmentManager().beginTransaction().remove(searchFragment).commit();
            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchFragment.searchToken(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchFragment.searchToken(newText);
                return true;
            }
        });

        return true;
    }

    /***********
     * Method
     ***********/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    searchView.setQuery(searchWrd, false);
                }
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void changeTitleBar(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void setSearchQuery(String query) {
        searchView.setQuery(query,false);
    }

    /*************
     * Listener
     *************/




}