
package com.nutstep.movie.dao;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Photos {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("backdrops")
    @Expose
    private List<Backdrop> backdrops = new ArrayList<Backdrop>();
    @SerializedName("posters")
    @Expose
    private List<Poster> posters = new ArrayList<Poster>();

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The backdrops
     */
    public List<Backdrop> getBackdrops() {
        return backdrops;
    }

    /**
     * 
     * @param backdrops
     *     The backdrops
     */
    public void setBackdrops(List<Backdrop> backdrops) {
        this.backdrops = backdrops;
    }

    /**
     * 
     * @return
     *     The posters
     */
    public List<Poster> getPosters() {
        return posters;
    }

    /**
     * 
     * @param posters
     *     The posters
     */
    public void setPosters(List<Poster> posters) {
        this.posters = posters;
    }

}
