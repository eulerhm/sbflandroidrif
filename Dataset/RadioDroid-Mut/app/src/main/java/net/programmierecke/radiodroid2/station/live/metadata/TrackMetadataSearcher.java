package net.programmierecke.radiodroid2.station.live.metadata;

import androidx.annotation.NonNull;
import net.programmierecke.radiodroid2.station.live.metadata.lastfm.LfmMetadataSearcher;
import okhttp3.OkHttpClient;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TrackMetadataSearcher {

    private LfmMetadataSearcher lfmMetadataSearcher;

    public TrackMetadataSearcher(OkHttpClient httpClient) {
        if (!ListenerUtil.mutListener.listen(2439)) {
            lfmMetadataSearcher = new LfmMetadataSearcher(httpClient);
        }
    }

    public void fetchTrackMetadata(String LastFMApiKey, String artist, @NonNull String track, @NonNull TrackMetadataCallback trackMetadataCallback) {
        if (!ListenerUtil.mutListener.listen(2440)) {
            lfmMetadataSearcher.fetchTrackMetadata(LastFMApiKey, artist, track, trackMetadataCallback);
        }
    }
}
