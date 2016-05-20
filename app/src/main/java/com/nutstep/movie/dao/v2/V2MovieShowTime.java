package com.nutstep.movie.dao.v2;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by peanutbutteer on 5/18/2016 AD.
 */
public class V2MovieShowTime {
    @SerializedName("count")
    private int count;
    @SerializedName("next")
    private String next;
    private String previous;
    private List<MovieShowTime> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<MovieShowTime> getResults() {
        return results;
    }

    public void setResults(List<MovieShowTime> results) {
        this.results = results;
    }
}
