package com.nutstep.movie.manager;

import com.nutstep.movie.dao.Movie;
import com.nutstep.movie.dao.MovieList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by peanutbutteer on 3/8/2016 AD.
 */
public interface MovieService {
    @GET("?t=movie")
    Call<MovieList> movieSearchList(@Query("s") String title);
    @GET("?")
    Call<Movie> movieDatail(@Query("i") String imdbID);
}
