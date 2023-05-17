package net.programmierecke.radiodroid2.station.live.metadata.lastfm.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class Image {

    @SerializedName("#text")
    @Expose
    private String text;

    @SerializedName("size")
    @Expose
    private String size;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if (!ListenerUtil.mutListener.listen(2380)) {
            this.text = text;
        }
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        if (!ListenerUtil.mutListener.listen(2381)) {
            this.size = size;
        }
    }
}
