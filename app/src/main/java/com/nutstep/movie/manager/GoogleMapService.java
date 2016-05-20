package com.nutstep.movie.manager;

import com.nutstep.movie.dao.DistanceMatrix;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by peanutbutteer on 5/20/2016 AD.
 */

public interface GoogleMapService {
    @GET("distancematrix/json?units=metric&key=AIzaSyAXewb4mazi7X8nYB7QuzaVwUYu3DQpgA4")
    Call<DistanceMatrix> getDistance(@Query("origins") String org, @Query("destinations")String des);
 }
