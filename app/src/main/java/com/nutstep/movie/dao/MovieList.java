package com.nutstep.movie.dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by peanutbutteer on 3/8/2016 AD.
 */
public class MovieList {
    @SerializedName("Search") private List<Movie> data;

    public List<Movie> getData() {
        return data;
    }

    public void setData(List<Movie> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @SerializedName("totalResults") private int total;
}
