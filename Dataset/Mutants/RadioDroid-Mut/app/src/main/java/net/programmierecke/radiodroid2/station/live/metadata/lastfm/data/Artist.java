package net.programmierecke.radiodroid2.station.live.metadata.lastfm.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class Artist {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("mbid")
    @Expose
    private String mbid;

    @SerializedName("url")
    @Expose
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!ListenerUtil.mutListener.listen(2377)) {
            this.name = name;
        }
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        if (!ListenerUtil.mutListener.listen(2378)) {
            this.mbid = mbid;
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        if (!ListenerUtil.mutListener.listen(2379)) {
            this.url = url;
        }
    }
}
