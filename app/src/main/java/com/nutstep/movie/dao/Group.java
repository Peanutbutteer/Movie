
package com.nutstep.movie.dao;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Group {

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

}
