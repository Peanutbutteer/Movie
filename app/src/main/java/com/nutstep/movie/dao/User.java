package com.nutstep.movie.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by peanutbutteer on 3/9/2016 AD.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class User {
    String fullName;
    String location;
    private List<Movie> readyWatch;
    private List<Movie> watchList;

    public User(String fullName, String location) {
        this.fullName = fullName;
        this.location = location;
    }

    public User()
    {

    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) { this.location = location;
    }

    public List<Movie> getReadyWatch() {
        return readyWatch;
    }

    public void setReadyWatch(List<Movie> readyWatch) {
        this.readyWatch = readyWatch;
    }

    public List<Movie> getWatchList() {
        return watchList;
    }

    public void setWatchList(List<Movie> watchList) {
        this.watchList = watchList;
    }
}
