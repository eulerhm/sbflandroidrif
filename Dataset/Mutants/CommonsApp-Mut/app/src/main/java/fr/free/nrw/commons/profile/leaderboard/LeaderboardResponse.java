package fr.free.nrw.commons.profile.leaderboard;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * GSON Response Class for Leaderboard API response
 */
public class LeaderboardResponse {

    /**
     * Status Code returned from the API
     * Example value - 200
     */
    @SerializedName("status")
    @Expose
    private Integer status;

    /**
     * Username returned from the API
     * Example value - Syced
     */
    @SerializedName("username")
    @Expose
    private String username;

    /**
     * Category count returned from the API
     * Example value - 10
     */
    @SerializedName("category_count")
    @Expose
    private Integer categoryCount;

    /**
     * Limit returned from the API
     * Example value - 10
     */
    @SerializedName("limit")
    @Expose
    private int limit;

    /**
     * Avatar returned from the API
     * Example value - https://upload.wikimedia.org/wikipedia/commons/thumb/0/0a/Gnome-stock_person.svg/200px-Gnome-stock_person.svg.png
     */
    @SerializedName("avatar")
    @Expose
    private String avatar;

    /**
     * Offset returned from the API
     * Example value - 0
     */
    @SerializedName("offset")
    @Expose
    private int offset;

    /**
     * Duration returned from the API
     * Example value - yearly
     */
    @SerializedName("duration")
    @Expose
    private String duration;

    /**
     * Leaderboard list returned from the API
     * Example value - [{
     *             "username": "Fæ",
     *             "category_count": 107147,
     *             "avatar": "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0a/Gnome-stock_person.svg/200px-Gnome-stock_person.svg.png",
     *             "rank": 1
     *         }]
     */
    @SerializedName("leaderboard_list")
    @Expose
    private List<LeaderboardList> leaderboardList = null;

    /**
     * Category returned from the API
     * Example value - upload
     */
    @SerializedName("category")
    @Expose
    private String category;

    /**
     * Rank returned from the API
     * Example value - 1
     */
    @SerializedName("rank")
    @Expose
    private Integer rank;

    /**
     * @return the status code
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets the status code
     */
    public void setStatus(Integer status) {
        if (!ListenerUtil.mutListener.listen(5509)) {
            this.status = status;
        }
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username
     */
    public void setUsername(String username) {
        if (!ListenerUtil.mutListener.listen(5510)) {
            this.username = username;
        }
    }

    /**
     * @return the category count
     */
    public Integer getCategoryCount() {
        return categoryCount;
    }

    /**
     * Sets the category count
     */
    public void setCategoryCount(Integer categoryCount) {
        if (!ListenerUtil.mutListener.listen(5511)) {
            this.categoryCount = categoryCount;
        }
    }

    /**
     * @return the limit
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Sets the limit
     */
    public void setLimit(int limit) {
        if (!ListenerUtil.mutListener.listen(5512)) {
            this.limit = limit;
        }
    }

    /**
     * @return the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * Sets the avatar
     */
    public void setAvatar(String avatar) {
        if (!ListenerUtil.mutListener.listen(5513)) {
            this.avatar = avatar;
        }
    }

    /**
     * @return the offset
     */
    public int getOffset() {
        return offset;
    }

    /**
     * Sets the offset
     */
    public void setOffset(int offset) {
        if (!ListenerUtil.mutListener.listen(5514)) {
            this.offset = offset;
        }
    }

    /**
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Sets the duration
     */
    public void setDuration(String duration) {
        if (!ListenerUtil.mutListener.listen(5515)) {
            this.duration = duration;
        }
    }

    /**
     * @return the leaderboard list
     */
    public List<LeaderboardList> getLeaderboardList() {
        return leaderboardList;
    }

    /**
     * Sets the leaderboard list
     */
    public void setLeaderboardList(List<LeaderboardList> leaderboardList) {
        if (!ListenerUtil.mutListener.listen(5516)) {
            this.leaderboardList = leaderboardList;
        }
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category
     */
    public void setCategory(String category) {
        if (!ListenerUtil.mutListener.listen(5517)) {
            this.category = category;
        }
    }

    /**
     * @return the rank
     */
    public Integer getRank() {
        return rank;
    }

    /**
     * Sets the rank
     */
    public void setRank(Integer rank) {
        if (!ListenerUtil.mutListener.listen(5518)) {
            this.rank = rank;
        }
    }
}
