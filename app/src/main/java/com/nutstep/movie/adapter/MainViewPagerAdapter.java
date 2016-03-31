package com.nutstep.movie.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nutstep.movie.fragment.MovieListFragment;

/**
 * Created by peanutbutteer on 3/4/2016 AD.
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {


    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            MovieListFragment movieListFragment = MovieListFragment.newInstance();
            return movieListFragment;
        }
        if (position == 1) {
            MovieListFragment movieListFragment = MovieListFragment.newInstance();
            return movieListFragment;
        }
        return MovieListFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "In Theater";
            case 1:
                return "Coming Soon";
            default:
                return super.getPageTitle(position);
        }
    }
}
