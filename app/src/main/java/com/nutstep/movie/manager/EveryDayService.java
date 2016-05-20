package com.nutstep.movie.manager;

import com.nutstep.movie.dao.Theater;
import com.nutstep.movie.dao.v2.MovieShowTime;
import com.nutstep.movie.dao.v2.V2MovieList;
import com.nutstep.movie.dao.v2.V2MovieShowTime;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by peanutbutteer on 5/18/2016 AD.
 */
public interface EveryDayService {
    @GET("movie/?offset=0&limit=30&ordering=-release_date%2Cid&filter=nowshowing")
    Call<V2MovieList> nowShowing();
    @GET("theater0.php")
    Call<Theater[]> allTheater(@Query("id") String id);
    @GET("theater/{id}/showtimes")
    Call<V2MovieShowTime> getShowtime(@Path("id") String id);
}
