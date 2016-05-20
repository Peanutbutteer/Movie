package com.nutstep.movie.manager;

import android.content.Context;

import com.google.gson.Gson;
import com.nutstep.movie.dao.Casts;
import com.nutstep.movie.dao.DistanceMatrix;
import com.nutstep.movie.dao.Intheater;
import com.nutstep.movie.dao.Movie;
import com.nutstep.movie.dao.MovieList;
import com.nutstep.movie.dao.Photos;
import com.nutstep.movie.dao.Result;
import com.nutstep.movie.dao.SearchMovie;
import com.nutstep.movie.dao.Theater;
import com.nutstep.movie.dao.v2.V2MovieList;
import com.nutstep.movie.dao.v2.V2MovieShowTime;

import okhttp3.ResponseBody;
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
    private Retrofit retrofitEveryDay = new Retrofit.Builder().baseUrl("http://showtimes.everyday.in.th/api/v2/").addConverterFactory(GsonConverterFactory.create()).build();
    private Retrofit retrofitTheater = new Retrofit.Builder().baseUrl("http://peanut.esy.es").addConverterFactory(GsonConverterFactory.create()).build();
    private Retrofit retrofitGoogle = new Retrofit.Builder().baseUrl("https://maps.googleapis.com/maps/api/").addConverterFactory(GsonConverterFactory.create()).build();
    private MovieService service = retrofit.create(MovieService.class);
    private EveryDayService everyDayService = retrofitEveryDay.create(EveryDayService.class);
    private EveryDayService theaterService = retrofitTheater.create(EveryDayService.class);
    private GoogleMapService googleMapService = retrofitGoogle.create(GoogleMapService.class);
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

    public Call<V2MovieList> getNowShowing() {
        return everyDayService.nowShowing();
    }

    public Call<Theater[]> getTheater(String id) {
        return theaterService.allTheater(id);
    }

    public Call<V2MovieShowTime> getShowtime(String id){
        return everyDayService.getShowtime(id);
    }

    public Call<DistanceMatrix> getDistance(String org, String des) {
        return googleMapService.getDistance(org,des);
    }
}
