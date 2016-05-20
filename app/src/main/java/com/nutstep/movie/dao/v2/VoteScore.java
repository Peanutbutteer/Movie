
package com.nutstep.movie.dao.v2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VoteScore {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("avg")
    @Expose
    private Integer avg;
    @SerializedName("score")
    @Expose
    private Integer score;

    /**
     * 
     * @return
     *     The total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * 
     * @param total
     *     The total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * 
     * @return
     *     The avg
     */
    public Integer getAvg() {
        return avg;
    }

    /**
     * 
     * @param avg
     *     The avg
     */
    public void setAvg(Integer avg) {
        this.avg = avg;
    }

    /**
     * 
     * @return
     *     The score
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 
     * @param score
     *     The score
     */
    public void setScore(Integer score) {
        this.score = score;
    }

}
