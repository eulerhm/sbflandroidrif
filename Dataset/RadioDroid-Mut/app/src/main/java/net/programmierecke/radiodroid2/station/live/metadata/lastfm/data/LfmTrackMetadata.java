package net.programmierecke.radiodroid2.station.live.metadata.lastfm.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class LfmTrackMetadata {

    @SerializedName("track")
    @Expose
    private Track track;

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        if (!ListenerUtil.mutListener.listen(2382)) {
            this.track = track;
        }
    }
}
