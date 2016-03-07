package com.nutstep.movie.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by peanutbutteer on 3/8/2016 AD.
 */
public class Movie {
    @SerializedName("Title") private String title;
    @SerializedName("Year") private String year;
    @SerializedName("Poster") private String poster;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
