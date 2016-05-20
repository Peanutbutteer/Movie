package com.nutstep.movie.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nutstep.movie.R;
import com.nutstep.movie.adapter.TheaterSearchListAdapter;
import com.nutstep.movie.dao.Distance;
import com.nutstep.movie.dao.DistanceMatrix;
import com.nutstep.movie.dao.Theater;
import com.nutstep.movie.dao.v2.Movie;
import com.nutstep.movie.dao.v2.MovieShowTime;
import com.nutstep.movie.dao.v2.V2MovieShowTime;
import com.nutstep.movie.manager.HttpManager;
import com.nutstep.movie.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TheaterFindActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    @BindView(R.id.text_title) TextView textView;
    @BindView(R.id.image_poster) ImageView imagePoster;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private LocationRequest mLocationRequest;
    private List<Theater> theaters;
    private int movieSelectId,theaterIndex;
    private TheaterSearchListAdapter theaterAdapter;
    private RecyclerView recyclerviewTheater;
    private Movie movie;
    @BindView(R.id.swipe_refresh) SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theater_find);
        ButterKnife.bind(this);
        recyclerviewTheater = (RecyclerView) findViewById(R.id.recylerview_theater);
        recyclerviewTheater.setLayoutManager(new LinearLayoutManager(this));
        theaterAdapter = new TheaterSearchListAdapter();
        recyclerviewTheater.setAdapter(theaterAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ReloadTheaterList();
            }
        });

        Bundle bundle = getIntent().getExtras();
        movie = new Gson().fromJson(bundle.getString("movie"),Movie.class);
        movieSelectId = movie.getId();

        textView.setText(movie.getTitle());
        Glide.with(this).load(movie.getImages().get(0).getUrl()).into(imagePoster);
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }


    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            ActivityCompat.requestPermissions(TheaterFindActivity.this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION},123);
            Log.d(this.getClass().getSimpleName(),"False");
            return;
        }
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationRequest,mLocationListener);
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            ReloadTheaterList();
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            mLastLocation = location;
            if (mLastLocation != null) {
                ReloadTheaterList();
            }
        }
    };

    private void ReloadTheaterList() {

        HttpManager.getInstance().getTheater(String.valueOf(movieSelectId)).enqueue(new Callback<Theater[]>() {
            @Override
            public void onResponse(Call<Theater[]> call, Response<Theater[]> response) {
                if(response.body()!=null) {

                    theaters = new ArrayList<Theater>(Arrays.asList(response.body()));
                    swipeRefreshLayout.setRefreshing(false);
                    for(Theater tha : theaters){
                        Location locationA = new Location("point A");

                        locationA.setLatitude(Double.valueOf(tha.getLocation().getLat()));
                        locationA.setLongitude(Double.valueOf(tha.getLocation().getLong()));
                        Double value = Double.valueOf(mLastLocation.distanceTo(locationA));
                        tha.setDistance((value/1000));
                    }

                    Collections.sort(theaters, new Comparator<Theater>() {
                        @Override
                        public int compare(Theater lhs, Theater rhs) {
                            if(lhs.getDistance()==null||rhs.getDistance()==null) return 0;
                            double distanceOne = lhs.getDistance();
                            double distanceTwo = rhs.getDistance();
                            if(distanceOne>distanceTwo){
                                return 1;
                            }else if(distanceOne==distanceTwo){
                                return 0;
                            }else return -1;
                        }
                    });

                    int showData = theaters.size()<10? theaters.size():10;
                    theaters = theaters.subList(0,showData);
                    theaterAdapter.addTheater(theaters);
                    theaterAdapter.notifyDataSetChanged();
                    for(theaterIndex = 0;theaterIndex<theaters.size();theaterIndex++){
                        String[] sp = theaters.get(theaterIndex).getShowtimes().split("/");
                        final Theater theater = theaters.get(theaterIndex);
                        String id = sp[6];

                        HttpManager.getInstance().getShowtime(id).enqueue(new Callback<V2MovieShowTime>() {
                            @Override
                            public void onResponse(Call<V2MovieShowTime> call, Response<V2MovieShowTime> response) {
                                if(response.body()!=null&&response.body().getCount()>0){
                                    List<MovieShowTime> list = response.body().getResults();
                                    for(MovieShowTime showtime : list){
                                        if(showtime.getMovieId()==movieSelectId){
                                            if(theater.getShowTime()!=null)
                                            {
                                                for(String time : showtime.getShowtimes()){
                                                    theater.getShowTime().getShowtimes().add(time);
                                                }
                                            }
                                            else {
                                                theater.setShowTime(showtime);
                                            }
                                            theaterAdapter.addTheater(theaters);
                                            theaterAdapter.notifyDataSetChanged();
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<V2MovieShowTime> call, Throwable t) {
                                Log.e(TheaterFindActivity.class.getSimpleName(),t.getMessage());
                            }
                        });
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Theater[]> call, Throwable t) {
                Log.d(TheaterFindActivity.class.getSimpleName(),t.getMessage());
            }
        });
    }

}
