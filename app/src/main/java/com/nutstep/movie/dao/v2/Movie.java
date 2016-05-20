
package com.nutstep.movie.dao.v2;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("details")
    @Expose
    private List<Detail> details = new ArrayList<Detail>();
    @SerializedName("tags")
    @Expose
    private Object tags;
    @SerializedName("videos")
    @Expose
    private List<Object> videos = new ArrayList<Object>();
    @SerializedName("images")
    @Expose
    private List<Image> images = new ArrayList<Image>();
    @SerializedName("ratings")
    @Expose
    private List<Rating> ratings = new ArrayList<Rating>();
    @SerializedName("feeders")
    @Expose
    private List<Feeder> feeders = new ArrayList<Feeder>();
    @SerializedName("collections")
    @Expose
    private List<Collection> collections = new ArrayList<Collection>();
    @SerializedName("active_screens")
    @Expose
    private Integer activeScreens;
    @SerializedName("vote_score")
    @Expose
    private VoteScore voteScore;
    @SerializedName("fav_movie")
    @Expose
    private FavMovie favMovie;
    @SerializedName("stars")
    @Expose
    private Integer stars;
    @SerializedName("watches")
    @Expose
    private Integer watches;

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
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * 
     * @param language
     *     The language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * 
     * @return
     *     The releaseDate
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * 
     * @param releaseDate
     *     The release_date
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * 
     * @return
     *     The details
     */
    public List<Detail> getDetails() {
        return details;
    }

    /**
     * 
     * @param details
     *     The details
     */
    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    /**
     * 
     * @return
     *     The tags
     */
    public Object getTags() {
        return tags;
    }

    /**
     * 
     * @param tags
     *     The tags
     */
    public void setTags(Object tags) {
        this.tags = tags;
    }

    /**
     * 
     * @return
     *     The videos
     */
    public List<Object> getVideos() {
        return videos;
    }

    /**
     * 
     * @param videos
     *     The videos
     */
    public void setVideos(List<Object> videos) {
        this.videos = videos;
    }

    /**
     * 
     * @return
     *     The images
     */
    public List<Image> getImages() {
        return images;
    }

    /**
     * 
     * @param images
     *     The images
     */
    public void setImages(List<Image> images) {
        this.images = images;
    }

    /**
     * 
     * @return
     *     The ratings
     */
    public List<Rating> getRatings() {
        return ratings;
    }

    /**
     * 
     * @param ratings
     *     The ratings
     */
    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    /**
     * 
     * @return
     *     The feeders
     */
    public List<Feeder> getFeeders() {
        return feeders;
    }

    /**
     * 
     * @param feeders
     *     The feeders
     */
    public void setFeeders(List<Feeder> feeders) {
        this.feeders = feeders;
    }

    /**
     * 
     * @return
     *     The collections
     */
    public List<Collection> getCollections() {
        return collections;
    }

    /**
     * 
     * @param collections
     *     The collections
     */
    public void setCollections(List<Collection> collections) {
        this.collections = collections;
    }

    /**
     * 
     * @return
     *     The activeScreens
     */
    public Integer getActiveScreens() {
        return activeScreens;
    }

    /**
     * 
     * @param activeScreens
     *     The active_screens
     */
    public void setActiveScreens(Integer activeScreens) {
        this.activeScreens = activeScreens;
    }

    /**
     * 
     * @return
     *     The voteScore
     */
    public VoteScore getVoteScore() {
        return voteScore;
    }

    /**
     * 
     * @param voteScore
     *     The vote_score
     */
    public void setVoteScore(VoteScore voteScore) {
        this.voteScore = voteScore;
    }

    /**
     * 
     * @return
     *     The favMovie
     */
    public FavMovie getFavMovie() {
        return favMovie;
    }

    /**
     * 
     * @param favMovie
     *     The fav_movie
     */
    public void setFavMovie(FavMovie favMovie) {
        this.favMovie = favMovie;
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
     *     The watches
     */
    public Integer getWatches() {
        return watches;
    }

    /**
     * 
     * @param watches
     *     The watches
     */
    public void setWatches(Integer watches) {
        this.watches = watches;
    }

}
