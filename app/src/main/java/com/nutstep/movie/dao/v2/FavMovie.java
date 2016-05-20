
package com.nutstep.movie.dao.v2;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class FavMovie {

    @SerializedName("follow")
    @Expose
    private Boolean follow;
    @SerializedName("star")
    @Expose
    private Boolean star;
    @SerializedName("watched")
    @Expose
    private Boolean watched;

    /**
     * 
     * @return
     *     The follow
     */
    public Boolean getFollow() {
        return follow;
    }

    /**
     * 
     * @param follow
     *     The follow
     */
    public void setFollow(Boolean follow) {
        this.follow = follow;
    }

    /**
     * 
     * @return
     *     The star
     */
    public Boolean getStar() {
        return star;
    }

    /**
     * 
     * @param star
     *     The star
     */
    public void setStar(Boolean star) {
        this.star = star;
    }

    /**
     * 
     * @return
     *     The watched
     */
    public Boolean getWatched() {
        return watched;
    }

    /**
     * 
     * @param watched
     *     The watched
     */
    public void setWatched(Boolean watched) {
        this.watched = watched;
    }

}
