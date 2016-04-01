package com.nutstep.movie.manager;

import android.content.Context;

import com.nutstep.movie.dao.Casts;
import com.nutstep.movie.dao.Intheater;
import com.nutstep.movie.dao.Movie;
import com.nutstep.movie.dao.MovieList;
import com.nutstep.movie.dao.Photos;
import com.nutstep.movie.dao.Result;
import com.nutstep.movie.dao.SearchMovie;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by peanutbutteer on 3/8/2016 AD.
 */
public class HttpManager {
    private static HttpManager instance;
    private Context context;
    private Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.themoviedb.org/3/").addConverterFactory(GsonConverterFactory.create()).build();
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

    public Call<SearchMovie>  searchMoive(String title)
    {
       return service.searchMovie(title);
    }

    public Call<Movie> getMovieDatail(String id)
    {
        return service.movieDatail(id);
    }

    public Call<Photos> getMoviePhotos(String id)
    {
        return service.moviePhoto(id);
    }

    public Call<Intheater> getMovieComing() {
        return service.upComing();
    }

    public Call<Intheater> getMovieIntheater() {
        return service.inTheater();
    }

    public Call<Casts> getCast(String id){
        return service.getCastDetail(id);
    }

    public Call<Intheater> getRandomMovie(String YearGte,String YearLte,int page,String genre){
        return service.getRandomMovie(YearGte,YearLte,page,genre);
    }

}
