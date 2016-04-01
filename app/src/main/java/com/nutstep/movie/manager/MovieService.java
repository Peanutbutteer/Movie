package com.nutstep.movie.manager;

import com.nutstep.movie.dao.Casts;
import com.nutstep.movie.dao.Intheater;
import com.nutstep.movie.dao.Movie;
import com.nutstep.movie.dao.MovieList;
import com.nutstep.movie.dao.Photos;
import com.nutstep.movie.dao.Result;
import com.nutstep.movie.dao.SearchMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by peanutbutteer on 3/8/2016 AD.
 */
public interface MovieService {
    @GET("?t=movie")
    Call<MovieList> movieSearchList(@Query("s") String title);
    @GET("movie/{id}?api_key=eff7e3120107959ee18d20eeaf5ea5fd")
    Call<Movie> movieDatail(@Path("id") String id);
    @GET("movie/now_playing?api_key=eff7e3120107959ee18d20eeaf5ea5fd")
    Call<Intheater> inTheater();
    @GET("movie/upcoming?api_key=eff7e3120107959ee18d20eeaf5ea5fd")
    Call<Intheater> upComing();
    @GET("search/movie?api_key=eff7e3120107959ee18d20eeaf5ea5fd")
    Call<SearchMovie> searchMovie(@Query("query") String query);
    @GET("movie/{id}/images?api_key=eff7e3120107959ee18d20eeaf5ea5fd")
    Call<Photos> moviePhoto(@Path("id") String id);
    @GET("movie/{id}/credits?api_key=eff7e3120107959ee18d20eeaf5ea5fd")
    Call<Casts> getCastDetail(@Path("id") String id);
    @GET("discover/movie?api_key=eff7e3120107959ee18d20eeaf5ea5fd&sort_by=popularity.desc")
    Call<Intheater> getRandomMovie(@Query("primary_release_date.gte") String yearGte,@Query("primary_release_date.lte") String yearLte,@Query("page") int page,@Query("with_genres") String genre);
}
