
package com.nutstep.movie.dao.v2;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Detail {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("director")
    @Expose
    private String director;
    @SerializedName("tagline")
    @Expose
    private String tagline;
    @SerializedName("cast")
    @Expose
    private String cast;
    @SerializedName("storyline")
    @Expose
    private String storyline;

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
     *     The director
     */
    public String getDirector() {
        return director;
    }

    /**
     * 
     * @param director
     *     The director
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * 
     * @return
     *     The tagline
     */
    public String getTagline() {
        return tagline;
    }

    /**
     * 
     * @param tagline
     *     The tagline
     */
    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    /**
     * 
     * @return
     *     The cast
     */
    public String getCast() {
        return cast;
    }

    /**
     * 
     * @param cast
     *     The cast
     */
    public void setCast(String cast) {
        this.cast = cast;
    }

    /**
     * 
     * @return
     *     The storyline
     */
    public String getStoryline() {
        return storyline;
    }

    /**
     * 
     * @param storyline
     *     The storyline
     */
    public void setStoryline(String storyline) {
        this.storyline = storyline;
    }

}
