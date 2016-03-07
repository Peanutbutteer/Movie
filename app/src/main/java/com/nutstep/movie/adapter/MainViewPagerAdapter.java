package com.nutstep.movie.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
        return new TimelineFragment();
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: return "Timeline";
            case 1: return "Watch";
            case 2: return "Watchlist";
            case 3: return "Profile";
            default: return super.getPageTitle(position);
        }
    }
}
