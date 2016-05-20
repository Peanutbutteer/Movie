
package com.nutstep.movie.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Location {

    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("long")
    @Expose
    private String _long;

    /**
     * 
     * @return
     *     The lat
     */
    public String getLat() {
        return lat;
    }

    /**
     * 
     * @param lat
     *     The lat
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     * 
     * @return
     *     The _long
     */
    public String getLong() {
        return _long;
    }

    /**
     * 
     * @param _long
     *     The long
     */
    public void setLong(String _long) {
        this._long = _long;
    }

}
