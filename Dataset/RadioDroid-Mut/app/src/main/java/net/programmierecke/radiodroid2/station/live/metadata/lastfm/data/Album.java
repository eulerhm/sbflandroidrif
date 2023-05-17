package net.programmierecke.radiodroid2.station.live.metadata.lastfm.data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class Album {

    @SerializedName("artist")
    @Expose
    private String artist;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("image")
    @Expose
    private List<Image> image = null;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        if (!ListenerUtil.mutListener.listen(2373)) {
            this.artist = artist;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (!ListenerUtil.mutListener.listen(2374)) {
            this.title = title;
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        if (!ListenerUtil.mutListener.listen(2375)) {
            this.url = url;
        }
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        if (!ListenerUtil.mutListener.listen(2376)) {
            this.image = image;
        }
    }
}
