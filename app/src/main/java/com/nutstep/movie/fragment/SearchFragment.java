package com.nutstep.movie.fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nutstep.movie.ChangeListenner;
import com.nutstep.movie.R;
import com.nutstep.movie.RecyclerItemClickListener;
import com.nutstep.movie.adapter.SearchViewAdapter;
import com.nutstep.movie.dao.MovieList;
import com.nutstep.movie.manager.HttpManager;
import com.nutstep.movie.utils.Utils;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment {

    RecyclerView recyclerViewSearch;
    SearchViewAdapter searchViewAdapter;
    public SearchFragment() {
        super();
    }

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
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
        recyclerViewSearch = (RecyclerView) rootView.findViewById(R.id.recylerview_search);
        searchViewAdapter = new SearchViewAdapter(getContext());
        recyclerViewSearch.setAdapter(searchViewAdapter);
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
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

    public void searchToken(String query)
    {
        HttpManager.getInstance().searchMoive(query).enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                if(response.isSuccess())
                {
                    if(searchViewAdapter!=null) {
                        searchViewAdapter.updateList(response.body().getData());
                        searchViewAdapter.notifyDataSetChanged();
                    }
                }
                else
                {
                    try {
                        showToast(response.errorBody().string().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                showToast(t.getMessage());
            }
        });
    }


    public void showToast(String toast)
    {
        Toast.makeText(getContext(),toast,Toast.LENGTH_SHORT).show();
    }

}
