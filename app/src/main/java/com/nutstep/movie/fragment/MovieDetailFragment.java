package com.nutstep.movie.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nutstep.movie.ChangeListenner;
import com.nutstep.movie.R;
import com.nutstep.movie.activity.MovieDetailActivity;
import com.nutstep.movie.adapter.CastPictureAdapter;
import com.nutstep.movie.adapter.PhotoMovieAdapter;
import com.nutstep.movie.dao.Casts;
import com.nutstep.movie.dao.Genre;
import com.nutstep.movie.dao.Movie;
import com.nutstep.movie.dao.Photos;
import com.nutstep.movie.dao.Result;
import com.nutstep.movie.dao.User;
import com.nutstep.movie.manager.HttpManager;
import com.nutstep.movie.manager.LocalStoreageManager;
import com.nutstep.movie.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieDetailFragment extends Fragment {
    int imdbId;
    TextView textScore, textPlot;
    Movie movie;
    RecyclerView recyclerViewMoivePicture, recyclerViewMoiveCast;
    PhotoMovieAdapter photoMovieAdapter;
    CastPictureAdapter castPictureAdapter;

    public MovieDetailFragment() {
        super();
    }

    ChangeListenner mChangeTitle;
    DetailFragment mDetailFragment;
    public interface DetailFragment{
        public void setImagePoster(String path);
        public void setGenre(String genre);
    }

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
            mDetailFragment = (DetailFragment) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
        imdbId = getArguments().getInt("id", 0);
        textScore = (TextView) rootView.findViewById(R.id.text_score);
        textPlot = (TextView) rootView.findViewById(R.id.text_plot);
        recyclerViewMoivePicture = (RecyclerView) rootView.findViewById(R.id.recylerview_movie_picture);
        recyclerViewMoiveCast = (RecyclerView) rootView.findViewById(R.id.recylerview_movie_cast);
        photoMovieAdapter = new PhotoMovieAdapter(getContext());
        recyclerViewMoivePicture.setAdapter(photoMovieAdapter);
        recyclerViewMoivePicture.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewMoivePicture.setNestedScrollingEnabled(false);
        castPictureAdapter = new CastPictureAdapter(getContext());
        recyclerViewMoiveCast.setAdapter(castPictureAdapter);
        recyclerViewMoiveCast.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewMoiveCast.setNestedScrollingEnabled(false);
        mChangeTitle.changeTitleBar("");
        loadMovieData(String.valueOf(imdbId));
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
        addLoadingScreen();
        HttpManager.getInstance().getMovieDatail(id).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccess()) {
                    movie = response.body();
                    mChangeTitle.changeTitleBar(movie.getTitle());
                    mDetailFragment.setImagePoster(movie.getBackdropPath());
                    textScore.setText(String.valueOf(movie.getVoteAverage()));
                    textPlot.setText(movie.getOverview());
                    String textGenre = getGenreString();
                    mDetailFragment.setGenre(textGenre);
                    removeLoadingScreen();
                }
                else
                {
                    try {
                        Toast.makeText(getContext(),response.errorBody().string(),Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @NonNull
            private String getGenreString() {
                String textGenre = "";
                for(Genre genre : movie.getGenres())
                {
                    textGenre += genre.getName()+",";
                }
                textGenre = textGenre.substring(0,textGenre.length()-1);
                return textGenre;
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


        HttpManager.getInstance().getMoviePhotos(id).enqueue(new Callback<Photos>() {
            @Override
            public void onResponse(Call<Photos> call, Response<Photos> response) {
                if(response.isSuccess()&&response.body().getBackdrops()!=null) {
                    photoMovieAdapter.setBackdropList(response.body().getBackdrops());
                    photoMovieAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Photos> call, Throwable t) {

            }
        });

        HttpManager.getInstance().getCast(id).enqueue(new Callback<Casts>() {
            @Override
            public void onResponse(Call<Casts> call, Response<Casts> response) {
                if(response.isSuccess()&&response.body().getCast()!=null) {
                    castPictureAdapter.setCastList(response.body().getCast());
                    castPictureAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Casts> call, Throwable t) {

            }
        });
    }

    LoadingScreenFragment loadingScreenFragment = LoadingScreenFragment.newInstance();

    public void removeLoadingScreen() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(loadingScreenFragment).commit();
    }

    public void addLoadingScreen() {
        getFragmentManager().beginTransaction().add(R.id.content_containner, loadingScreenFragment, "Loading").commit();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

}
