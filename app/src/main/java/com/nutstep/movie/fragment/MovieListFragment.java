package com.nutstep.movie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nutstep.movie.R;
import com.nutstep.movie.adapter.MovieGridsAdapter;
import com.nutstep.movie.dao.Intheater;
import com.nutstep.movie.manager.HttpManager;
import com.nutstep.movie.manager.LocalStoreageManager;
import com.nutstep.movie.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieListFragment extends Fragment {
    MovieGridsAdapter viewAdapter;
    RecyclerView recyclerViewMovie;

    final public static int MODE_IN_THEATER = 1;
    final public static int MODE_COMING = 2;

    public MovieListFragment() {
        super();
    }

    public static MovieListFragment newInstance() {
        MovieListFragment fragment = new MovieListFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_moive_list, container, false);
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


        if(getArguments().getInt("mode",0)==MODE_COMING)
        {
            loadUpComing();
        }
        else
        {
            loadInTheater();
        }

        viewAdapter = new MovieGridsAdapter(getContext());
        recyclerViewMovie = (RecyclerView) rootView.findViewById(R.id.recylerview_movie_list);
        recyclerViewMovie.setAdapter(viewAdapter);
        recyclerViewMovie.setLayoutManager(new GridLayoutManager(getContext(),3));




        if (savedInstanceState == null) {
        }


    }

    private void loadInTheater() {

        HttpManager.getInstance().getMovieIntheater().enqueue(callback);
    }

    private void loadUpComing(){
        HttpManager.getInstance().getMovieComing().enqueue(callback);
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

    Callback<Intheater> callback = new Callback<Intheater>() {
        @Override
        public void onResponse(Call<Intheater> call, Response<Intheater> response) {
            if (response.isSuccess()) {
                viewAdapter.setMovies(response.body().getResults());
                viewAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onFailure(Call<Intheater> call, Throwable t) {
            Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

}
