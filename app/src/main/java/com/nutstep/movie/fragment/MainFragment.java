package com.nutstep.movie.fragment;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.nutstep.movie.ChangeListenner;
import com.nutstep.movie.R;
import com.nutstep.movie.activity.MainActivity;
import com.nutstep.movie.adapter.MainViewPagerAdapter;


public class MainFragment extends Fragment {

    ViewPager viewPagerMain;

    final int HOME = 0;
    final int MOVIE = 1;
    ChangeListenner mChangeTitle;
    MainViewPagerAdapter pagerAdapter;
    TabLayout tabLayoutMenu;
    public MainFragment() {
        super();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mChangeTitle = (ChangeListenner) context;
        } catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    @SuppressWarnings("UnusedParameters")
    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        // Note: State of variable initialized here could not be saved
        //       in onSavedInstanceState

        tabLayoutMenu = (TabLayout) rootView.findViewById(R.id.tablayout_menu);
        viewPagerMain = (ViewPager) rootView.findViewById(R.id.viewpager_main);


        pagerAdapter = new MainViewPagerAdapter(getFragmentManager());
        viewPagerMain.setAdapter(pagerAdapter);
        tabLayoutMenu.setupWithViewPager(viewPagerMain);

       // tabLayoutMenu.setOnTabSelectedListener(onTabSelectedListener);



    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance (Fragment level's variables) State here
    }

    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance (Fragment level's variables) State here

    }



    /*************
     * Listener
     *************/


    TabLayout.OnTabSelectedListener onTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            viewPagerMain.setCurrentItem(tab.getPosition(),true);
                  mChangeTitle.changeTitleBar(tab.getText().toString());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

}
