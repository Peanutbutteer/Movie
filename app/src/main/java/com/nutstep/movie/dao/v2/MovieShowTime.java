
package com.nutstep.movie.dao.v2;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MovieShowTime {

    @SerializedName("movie_id")
    @Expose
    private Integer movieId;
    @SerializedName("movie_title")
    @Expose
    private String movieTitle;
    @SerializedName("movie_poster")
    @Expose
    private String moviePoster;
    @SerializedName("movie")
    @Expose
    private String movie;
    @SerializedName("showtimes")
    @Expose
    private List<String> showtimes = new ArrayList<String>();
    @SerializedName("is_3d")
    @Expose
    private Boolean is3d;
    @SerializedName("technology")
    @Expose
    private String technology;
    @SerializedName("cinema")
    @Expose
    private String cinema;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("audio")
    @Expose
    private String audio;
    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("extra")
    @Expose
    private String extra;
    @SerializedName("updated_on")
    @Expose
    private String updatedOn;

    /**
     * 
     * @return
     *     The movieId
     */
    public Integer getMovieId() {
        return movieId;
    }

    /**
     * 
     * @param movieId
     *     The movie_id
     */
    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    /**
     * 
     * @return
     *     The movieTitle
     */
    public String getMovieTitle() {
        return movieTitle;
    }

    /**
     * 
     * @param movieTitle
     *     The movie_title
     */
    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    /**
     * 
     * @return
     *     The moviePoster
     */
    public String getMoviePoster() {
        return moviePoster;
    }

    /**
     * 
     * @param moviePoster
     *     The movie_poster
     */
    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    /**
     * 
     * @return
     *     The movie
     */
    public String getMovie() {
        return movie;
    }

    /**
     * 
     * @param movie
     *     The movie
     */
    public void setMovie(String movie) {
        this.movie = movie;
    }

    /**
     * 
     * @return
     *     The showtimes
     */
    public List<String> getShowtimes() {
        return showtimes;
    }

    /**
     * 
     * @param showtimes
     *     The showtimes
     */
    public void setShowtimes(List<String> showtimes) {
        this.showtimes = showtimes;
    }

    /**
     * 
     * @return
     *     The is3d
     */
    public Boolean getIs3d() {
        return is3d;
    }

    /**
     * 
     * @param is3d
     *     The is_3d
     */
    public void setIs3d(Boolean is3d) {
        this.is3d = is3d;
    }

    /**
     * 
     * @return
     *     The technology
     */
    public String getTechnology() {
        return technology;
    }

    /**
     * 
     * @param technology
     *     The technology
     */
    public void setTechnology(String technology) {
        this.technology = technology;
    }

    /**
     * 
     * @return
     *     The cinema
     */
    public String getCinema() {
        return cinema;
    }

    /**
     * 
     * @param cinema
     *     The cinema
     */
    public void setCinema(String cinema) {
        this.cinema = cinema;
    }

    /**
     * 
     * @return
     *     The date
     */
    public String getDate() {
        return date;
    }

    /**
     * 
     * @param date
     *     The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 
     * @return
     *     The audio
     */
    public String getAudio() {
        return audio;
    }

    /**
     * 
     * @param audio
     *     The audio
     */
    public void setAudio(String audio) {
        this.audio = audio;
    }

    /**
     * 
     * @return
     *     The caption
     */
    public String getCaption() {
        return caption;
    }

    /**
     * 
     * @param caption
     *     The caption
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * 
     * @return
     *     The extra
     */
    public String getExtra() {
        return extra;
    }

    /**
     * 
     * @param extra
     *     The extra
     */
    public void setExtra(String extra) {
        this.extra = extra;
    }

    /**
     * 
     * @return
     *     The updatedOn
     */
    public String getUpdatedOn() {
        return updatedOn;
    }

    /**
     * 
     * @param updatedOn
     *     The updated_on
     */
    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

}
