
package com.nutstep.movie.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nutstep.movie.dao.v2.MovieShowTime;


public class Theater {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("group")
    @Expose
    private Group group;
    @SerializedName("showtimes")
    @Expose
    private String showtimes;
    @SerializedName("num_views")
    @Expose
    private Integer numViews;
    @SerializedName("today_screens")
    @Expose
    private Integer todayScreens;
    @SerializedName("min_screens")
    @Expose
    private Integer minScreens;
    @SerializedName("fav")
    @Expose
    private Boolean fav;
    @SerializedName("stars")
    @Expose
    private Integer stars;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("english")
    @Expose
    private String english;
    @SerializedName("thai")
    @Expose
    private String thai;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("tel")
    @Expose
    private String tel;

    private Double distance;

    public Double getDistance() {
        return distance;
    }

    private MovieShowTime showTime;

    public MovieShowTime getShowTime() {
        return showTime;
    }

    public void setShowTime(MovieShowTime showTime) {
        this.showTime = showTime;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    private String showTimeString;

    public String getShowTimeString() {
        return showTimeString;
    }

    public void setShowTimeString(String showTimeString) {
        this.showTimeString = showTimeString;
    }

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The group
     */
    public Group getGroup() {
        return group;
    }

    /**
     * 
     * @param group
     *     The group
     */
    public void setGroup(Group group) {
        this.group = group;
    }

    /**
     * 
     * @return
     *     The showtimes
     */
    public String getShowtimes() {
        return showtimes;
    }

    /**
     * 
     * @param showtimes
     *     The showtimes
     */
    public void setShowtimes(String showtimes) {
        this.showtimes = showtimes;
    }

    /**
     * 
     * @return
     *     The numViews
     */
    public Integer getNumViews() {
        return numViews;
    }

    /**
     * 
     * @param numViews
     *     The num_views
     */
    public void setNumViews(Integer numViews) {
        this.numViews = numViews;
    }

    /**
     * 
     * @return
     *     The todayScreens
     */
    public Integer getTodayScreens() {
        return todayScreens;
    }

    /**
     * 
     * @param todayScreens
     *     The today_screens
     */
    public void setTodayScreens(Integer todayScreens) {
        this.todayScreens = todayScreens;
    }

    /**
     * 
     * @return
     *     The minScreens
     */
    public Integer getMinScreens() {
        return minScreens;
    }

    /**
     * 
     * @param minScreens
     *     The min_screens
     */
    public void setMinScreens(Integer minScreens) {
        this.minScreens = minScreens;
    }

    /**
     * 
     * @return
     *     The fav
     */
    public Boolean getFav() {
        return fav;
    }

    /**
     * 
     * @param fav
     *     The fav
     */
    public void setFav(Boolean fav) {
        this.fav = fav;
    }

    /**
     * 
     * @return
     *     The stars
     */
    public Integer getStars() {
        return stars;
    }

    /**
     * 
     * @param stars
     *     The stars
     */
    public void setStars(Integer stars) {
        this.stars = stars;
    }

    /**
     * 
     * @return
     *     The code
     */
    public String getCode() {
        return code;
    }

    /**
     * 
     * @param code
     *     The code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 
     * @return
     *     The english
     */
    public String getEnglish() {
        return english;
    }

    /**
     * 
     * @param english
     *     The english
     */
    public void setEnglish(String english) {
        this.english = english;
    }

    /**
     * 
     * @return
     *     The thai
     */
    public String getThai() {
        return thai;
    }

    /**
     * 
     * @param thai
     *     The thai
     */
    public void setThai(String thai) {
        this.thai = thai;
    }

    /**
     * 
     * @return
     *     The website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * 
     * @param website
     *     The website
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * 
     * @return
     *     The location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * 
     * @param location
     *     The location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * 
     * @return
     *     The tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * 
     * @param tel
     *     The tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

}
