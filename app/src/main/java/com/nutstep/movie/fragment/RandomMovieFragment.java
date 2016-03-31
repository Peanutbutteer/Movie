package com.nutstep.movie.fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.SuperscriptSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.greenfrvr.hashtagview.HashtagView;
import com.nutstep.movie.R;

import java.util.ArrayList;
import java.util.List;


public class RandomMovieFragment extends Fragment {

    private BottomSheetBehavior bottomSheetBehavior;
    private HashtagView genreTagView;

    public RandomMovieFragment() {
        super();
    }

    public static RandomMovieFragment newInstance() {
        RandomMovieFragment fragment = new RandomMovieFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_random_movie, container, false);
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

        View view = rootView.findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(view);

        genreTagView = (HashtagView) rootView.findViewById(R.id.tag_movie_genre);


        class Genre{
            private int id;
            private String name;

            public Genre(int id, String name) {
                this.id = id;
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        List<Genre> lists = new ArrayList<Genre>();
        lists.add(new Genre(0,"action"));
        lists.add(new Genre(0,"sci-fi"));

        genreTagView.setData(lists, new HashtagView.DataStateTransform<Genre>() {
            @Override
            public CharSequence prepareSelected(Genre item) {
                return item.getName();
            }

            @Override
            public CharSequence prepare(Genre item) {
                String label = item.getName();
                SpannableString spannableString = new SpannableString(label);
                //spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#85F5F5F5")), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                //spannableString.setSpan(new SuperscriptSpan(), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                return spannableString;
            }


        }, new HashtagView.DataSelector<Genre>() {
            @Override
            public boolean preselect(Genre item) {
                return false;
            }
        });

        genreTagView.addOnTagSelectListener(new HashtagView.TagsSelectListener() {
            @Override
            public void onItemSelected(Object item, boolean selected) {
                if(selected)
                {
                    Toast.makeText(getContext(),((Genre)item).getName(),Toast.LENGTH_LONG).show();
                }
            }
        });
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


    public void openBottomSheet()
    {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetBehavior.setPeekHeight(320);
    }

}
