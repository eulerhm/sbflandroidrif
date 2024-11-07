package net.programmierecke.radiodroid2.station.live.metadata.lastfm.data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class Toptags {

    @SerializedName("tag")
    @Expose
    private List<Tag> tag = null;

    public List<Tag> getTag() {
        return tag;
    }

    public void setTag(List<Tag> tag) {
        if (!ListenerUtil.mutListener.listen(2387)) {
            this.tag = tag;
        }
    }
}
