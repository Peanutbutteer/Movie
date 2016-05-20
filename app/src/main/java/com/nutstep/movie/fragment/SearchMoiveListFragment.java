package com.nutstep.movie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nutstep.movie.R;
import com.nutstep.movie.adapter.MovieSearchListViewAdapter;
import com.nutstep.movie.dao.v2.V2MovieList;
import com.nutstep.movie.manager.HttpManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchMoiveListFragment extends Fragment {


    @BindView(R.id.recylerview_movie_search_list)
    RecyclerView recylerviewMovieSearchList;
    MovieSearchListViewAdapter viewAdapter;
    LoadingScreenFragment loadingScreenFragment = LoadingScreenFragment.newInstance();
    public SearchMoiveListFragment() {
        super();
    }

    public static SearchMoiveListFragment newInstance() {
        SearchMoiveListFragment fragment = new SearchMoiveListFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_search_movie_list, container, false);
        ButterKnife.bind(this, rootView);
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
        recylerviewMovieSearchList = (RecyclerView) rootView.findViewById(R.id.recylerview_movie_search_list);
        viewAdapter = new MovieSearchListViewAdapter(getContext());
        recylerviewMovieSearchList.setAdapter(viewAdapter);
        recylerviewMovieSearchList.setLayoutManager(new GridLayoutManager(getContext(),2));

        addLoadingScreen();
        HttpManager.getInstance().getNowShowing().enqueue(new Callback<V2MovieList>() {
            @Override
            public void onResponse(Call<V2MovieList> call, Response<V2MovieList> response) {
                if(response.body()!=null&&response.body().getResults()!=null) {
                    viewAdapter.updateList(response.body().getResults());
                    viewAdapter.notifyDataSetChanged();
                    removeLoadingScreen();
                }

            }

            @Override
            public void onFailure(Call<V2MovieList> call, Throwable t) {
                Log.e(SearchMoiveListFragment.class.getSimpleName(),t.getMessage());
                removeLoadingScreen();
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

    public void removeLoadingScreen() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(loadingScreenFragment).commit();
    }

    public void addLoadingScreen() {
        getFragmentManager().beginTransaction().add(R.id.content_containner, loadingScreenFragment, "Loading").commit();
    }

}
