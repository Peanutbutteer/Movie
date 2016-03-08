package com.nutstep.movie.fragment;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nutstep.movie.ChangeListenner;
import com.nutstep.movie.R;
import com.nutstep.movie.dao.Movie;
import com.nutstep.movie.manager.HttpManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieDetailFragment extends Fragment {
    String imdbId;
    TextView textTitle,textScore,textRuntime,textPlot;
    ImageView imagePoster;
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
        textTitle = (TextView) rootView.findViewById(R.id.text_title);
        textScore = (TextView) rootView.findViewById(R.id.text_score);
        textRuntime = (TextView) rootView.findViewById(R.id.text_runtime);
        textPlot = (TextView) rootView.findViewById(R.id.text_plot);

        imdbId = getArguments().getString("id","");
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

    private void loadMovieData(String id)
    {
        final LoadingScreenFragment loadingScreenFragment = LoadingScreenFragment.newInstance();
        getFragmentManager().beginTransaction().add(R.id.content_containner,loadingScreenFragment,"Loading").commit();
        HttpManager.getInstance().getMovieDatail(id).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movie = response.body();
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

}
