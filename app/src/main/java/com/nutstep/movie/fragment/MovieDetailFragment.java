package com.nutstep.movie.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.nutstep.movie.ChangeListenner;
import com.nutstep.movie.R;
import com.nutstep.movie.activity.MovieDetailActivity;
import com.nutstep.movie.dao.Movie;
import com.nutstep.movie.dao.User;
import com.nutstep.movie.manager.HttpManager;
import com.nutstep.movie.manager.LocalStoreageManager;
import com.nutstep.movie.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieDetailFragment extends Fragment {
    String imdbId;
    TextView textTitle, textScore, textRuntime, textPlot;
    ImageView imagePoster;
    ImageView imageStatus;
    Firebase ref = new Firebase(Utils.getInstance().getBaseUrl());
    Movie movie;
    int status=1;
    String uid = LocalStoreageManager.getInstance().getSharedPreferences().getString("uid","");
    public MovieDetailFragment() {
        super();
    }

    ChangeListenner mChangeTitle;

    public static MovieDetailFragment newInstance() {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mChangeTitle = (ChangeListenner) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
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
        View rootView = inflater.inflate(R.layout.fragment_movie_detail, container, false);
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
        imdbId = getArguments().getString("id", "");
        textTitle = (TextView) rootView.findViewById(R.id.text_title);
        textScore = (TextView) rootView.findViewById(R.id.text_score);
        textRuntime = (TextView) rootView.findViewById(R.id.text_runtime);
        textPlot = (TextView) rootView.findViewById(R.id.text_plot);
        imageStatus = (ImageView) rootView.findViewById(R.id.btn_add_to_watch);
        ref.child("readywatchlist").child(uid).child(imdbId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null)
                {
                    Toast.makeText(getActivity(),"Added to Ready!",Toast.LENGTH_SHORT).show();
                    imageStatus.setImageLevel(3);
                    status = 3;
                }
                }


            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        ref.child("watchlist").child(uid).child(imdbId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null)
                {
                    Toast.makeText(getActivity(),"Added to WatchList!",Toast.LENGTH_SHORT).show();
                    imageStatus.setImageLevel(2);
                    status = 2;
                }
            }


            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        imageStatus.setClickable(true);
        imageStatus.setOnClickListener(onClickListener);
        loadMovieData(imdbId);
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

    private void loadMovieData(String id) {
        final LoadingScreenFragment loadingScreenFragment = LoadingScreenFragment.newInstance();
        getFragmentManager().beginTransaction().add(R.id.content_containner, loadingScreenFragment, "Loading").commit();
        HttpManager.getInstance().getMovieDatail(id).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                movie = response.body();
                textTitle.setText(movie.getTitle());
                textScore.setText(movie.getImdbRating());
                textRuntime.setText(movie.getRuntime());
                textPlot.setText(movie.getPlot());
                mChangeTitle.changeTitleBar(movie.getTitle());
                getFragmentManager().beginTransaction().remove(loadingScreenFragment).commit();
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == imageStatus) {
                Map<String,Object> movie = new HashMap<String, Object>();
                movie.put(imdbId,true);
                if(status==1)
                {
                    ref.child("watchlist").child(uid).updateChildren(movie);
                }
                else if(status==2)
                {
                    ref.child("watchlist").child(uid).child(imdbId).setValue(null);
                    ref.child("readywatchlist").child(uid).updateChildren(movie);
                }
                else
                {
                    ref.child("readywatchlist").child(uid).child(imdbId).setValue(null);
                    status=1;
                    imageStatus.setImageLevel(1);
                }

            }
        }
    };

}
