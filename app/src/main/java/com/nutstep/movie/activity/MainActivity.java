package com.nutstep.movie.activity;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.provider.SearchRecentSuggestions;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.nutstep.movie.R;
import com.nutstep.movie.SuggestionProvider;
import com.nutstep.movie.adapter.MainViewPagerAdapter;
import com.nutstep.movie.adapter.TimelineViewAdapter;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayoutMenu;
    ViewPager viewPagerMain;
    SearchView searchView;
    final int HOME = 0;
    final int MOVIE = 1;
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

        tabLayoutMenu = (TabLayout) findViewById(R.id.tablayout_menu);
        viewPagerMain = (ViewPager) findViewById(R.id.viewpager_main);

        MainViewPagerAdapter pagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        viewPagerMain.setAdapter(pagerAdapter);
        tabLayoutMenu.setupWithViewPager(viewPagerMain);

        tabLayoutMenu.setOnTabSelectedListener(onTabSelectedListener);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);


        if(Intent.ACTION_SEARCH.equals(intent.getAction()))
        {
            String query = intent.getStringExtra(SearchManager.QUERY);
            SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                    SuggestionProvider.AUTHORITY, SuggestionProvider.MODE);
            suggestions.saveRecentQuery(query, null);
            Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
            searchView.setQuery(query,false);
            searchView.clearFocus();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        ComponentName componentName = new ComponentName(this,SearchActivity.class);
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return true;
            }
        });
       // searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
        searchView.setIconified(false);
        searchView.setQueryRefinementEnabled(true);



        return true;
    }

    /***********
     * Method
     ***********/


    private void changeTitleBar(String title) {
        getSupportActionBar().setTitle(title);
    }


    /*************
     * Listener
     *************/


    TabLayout.OnTabSelectedListener onTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            switch (tab.getPosition())
            {
                case HOME:
                    changeTitleBar("HOME");
                    break;
                case MOVIE:
                    changeTitleBar("MOVIE");
                    break;
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

}