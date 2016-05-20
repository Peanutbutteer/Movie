package com.nutstep.movie.dao.v2;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by peanutbutteer on 5/18/2016 AD.
 */
public class V2MovieList {
    @SerializedName("count")
    private int count;
    @SerializedName("next")
    private String next;
    private String previous;
    private List<Movie> results;

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

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
