package net.programmierecke.radiodroid2.station.live.metadata.lastfm.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class Tag {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("url")
    @Expose
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!ListenerUtil.mutListener.listen(2385)) {
            this.name = name;
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        if (!ListenerUtil.mutListener.listen(2386)) {
            this.url = url;
        }
    }
}
