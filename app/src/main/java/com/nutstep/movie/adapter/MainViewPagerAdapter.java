package com.nutstep.movie.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nutstep.movie.fragment.MovieListFragment;
import com.nutstep.movie.fragment.ProfileFragment;
import com.nutstep.movie.fragment.TimelineFragment;

/**
 * Created by peanutbutteer on 3/4/2016 AD.
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {


    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        if (position == 1) {
            bundle.putInt("MODE", MovieListFragment.MODE_WATCH);
            MovieListFragment movieListFragment = MovieListFragment.newInstance();
            movieListFragment.setArguments(bundle);
            return movieListFragment;
        }
        if (position == 2) {
            bundle.putInt("MODE", MovieListFragment.MODE_WISH_LIST);
            MovieListFragment movieListFragment = MovieListFragment.newInstance();
            movieListFragment.setArguments(bundle);
            return movieListFragment;
        }
        if (position == 3) {
            return ProfileFragment.newInstance();
        }
        return TimelineFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Timeline";
            case 1:
                return "Watch";
            case 2:
                return "Watchlist";
            case 3:
                return "Profile";
            default:
                return super.getPageTitle(position);
        }
    }
}
