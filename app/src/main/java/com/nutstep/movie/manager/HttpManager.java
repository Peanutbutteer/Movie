package com.nutstep.movie.manager;

import android.content.Context;

import com.nutstep.movie.dao.MovieList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by peanutbutteer on 3/8/2016 AD.
 */
public class HttpManager {
    private static HttpManager instance;
    private Context context;
    private Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.omdbapi.com").addConverterFactory(GsonConverterFactory.create()).build();
    private MovieService service = retrofit.create(MovieService.class);
    public static HttpManager getInstance()
    {
        if(instance==null) instance = new HttpManager();
        return instance;
    }

    private HttpManager()
    {
        context = Contextor.getInstance().getContext();
    }

    public Call<MovieList>  searchMoive(String title)
    {
       return service.movieSearchList(title);
    }

}
