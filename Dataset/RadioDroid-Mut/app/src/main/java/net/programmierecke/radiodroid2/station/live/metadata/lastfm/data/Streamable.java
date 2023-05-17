package net.programmierecke.radiodroid2.station.live.metadata.lastfm.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class Streamable {

    @SerializedName("#text")
    @Expose
    private String text;

    @SerializedName("fulltrack")
    @Expose
    private String fulltrack;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if (!ListenerUtil.mutListener.listen(2383)) {
            this.text = text;
        }
    }

    public String getFulltrack() {
        return fulltrack;
    }

    public void setFulltrack(String fulltrack) {
        if (!ListenerUtil.mutListener.listen(2384)) {
            this.fulltrack = fulltrack;
        }
    }
}
