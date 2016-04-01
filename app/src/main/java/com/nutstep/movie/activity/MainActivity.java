package com.nutstep.movie.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.nutstep.movie.ChangeListenner;
import com.nutstep.movie.R;
import com.nutstep.movie.fragment.MainFragment;
import com.nutstep.movie.fragment.SearchFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ChangeListenner {
    MaterialSearchView searchView;
    SearchFragment searchFragment;
    FrameLayout frameLayout;
   // LinearLayout bottomBar
    DrawerLayout mDrawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initInstance();

        if(savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction().add(R.id.content_containner, MainFragment.newInstance()).commit();
        }
    }

    private void initInstance() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        searchFragment = SearchFragment.newInstance();
        frameLayout = (FrameLayout) findViewById(R.id.toolbar_container);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open_drawer,R.string.close_drawer);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if(item.getItemId()==R.id.nav_movie)
                {
                    return true;
                }
                if(item.getItemId()==R.id.nav_random)
                {
                    Intent intent = new Intent(MainActivity.this,RandomActivity.class);
                    startActivity(intent);

                    return false;
                }
                mDrawerLayout.closeDrawer(navigationView);
                return false;
            }
        });
        navigationView.setCheckedItem(R.id.nav_movie);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
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
                getSupportFragmentManager().beginTransaction().add(R.id.content_containner, searchFragment, "SearchFragment").addToBackStack(null).commit();
            }

            @Override
            public void onSearchViewClosed() {
                if(getSupportFragmentManager().findFragmentByTag("SearchFragment")!=null) {
                    getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentByTag("SearchFragment")).commit();
                   }
            }
        });
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!query.isEmpty()) {
                    searchFragment.searchToken(query);
                    hideKeyboard();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(!newText.isEmpty())searchFragment.searchToken(newText);
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void hideKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        }
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
        searchView.setQuery(query, false);
    }

    /*************
     * Listener
     *************/


}