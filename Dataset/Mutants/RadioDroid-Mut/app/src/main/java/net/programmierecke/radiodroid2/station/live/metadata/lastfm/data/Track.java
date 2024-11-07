package net.programmierecke.radiodroid2.station.live.metadata.lastfm.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class Track {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("mbid")
    @Expose
    private String mbid;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("duration")
    @Expose
    private String duration;

    @SerializedName("streamable")
    @Expose
    private Streamable streamable;

    @SerializedName("listeners")
    @Expose
    private String listeners;

    @SerializedName("playcount")
    @Expose
    private String playcount;

    @SerializedName("artist")
    @Expose
    private Artist artist;

    @SerializedName("album")
    @Expose
    private Album album;

    @SerializedName("toptags")
    @Expose
    private Toptags toptags;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!ListenerUtil.mutListener.listen(2388)) {
            this.name = name;
        }
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        if (!ListenerUtil.mutListener.listen(2389)) {
            this.mbid = mbid;
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        if (!ListenerUtil.mutListener.listen(2390)) {
            this.url = url;
        }
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        if (!ListenerUtil.mutListener.listen(2391)) {
            this.duration = duration;
        }
    }

    public Streamable getStreamable() {
        return streamable;
    }

    public void setStreamable(Streamable streamable) {
        if (!ListenerUtil.mutListener.listen(2392)) {
            this.streamable = streamable;
        }
    }

    public String getListeners() {
        return listeners;
    }

    public void setListeners(String listeners) {
        if (!ListenerUtil.mutListener.listen(2393)) {
            this.listeners = listeners;
        }
    }

    public String getPlaycount() {
        return playcount;
    }

    public void setPlaycount(String playcount) {
        if (!ListenerUtil.mutListener.listen(2394)) {
            this.playcount = playcount;
        }
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        if (!ListenerUtil.mutListener.listen(2395)) {
            this.artist = artist;
        }
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        if (!ListenerUtil.mutListener.listen(2396)) {
            this.album = album;
        }
    }

    public Toptags getToptags() {
        return toptags;
    }

    public void setToptags(Toptags toptags) {
        if (!ListenerUtil.mutListener.listen(2397)) {
            this.toptags = toptags;
        }
    }
}
