package net.programmierecke.radiodroid2.station.live.metadata.lastfm;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.google.gson.Gson;
import net.programmierecke.radiodroid2.BuildConfig;
import net.programmierecke.radiodroid2.station.live.metadata.TrackMetadata;
import net.programmierecke.radiodroid2.station.live.metadata.TrackMetadataCallback;
import net.programmierecke.radiodroid2.station.live.metadata.lastfm.data.Image;
import net.programmierecke.radiodroid2.station.live.metadata.lastfm.data.LfmTrackMetadata;
import net.programmierecke.radiodroid2.station.live.metadata.lastfm.data.Track;
import net.programmierecke.radiodroid2.utils.RateLimiter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class LfmMetadataSearcher {

    private static final String API_GET_TRACK_METADATA = "http://ws.audioscrobbler.com/2.0/?method=track.getInfo&api_key=%s&artist=%s&track=%s&format=json";

    private OkHttpClient httpClient;

    private Gson gson = new Gson();

    private RateLimiter rateLimiter = new RateLimiter(4, 60 * 1000);

    public LfmMetadataSearcher(OkHttpClient httpClient) {
        if (!ListenerUtil.mutListener.listen(2398)) {
            this.httpClient = httpClient;
        }
    }

    /**
     * Some station add to track suffix or prefix, like:
     * "Cool track [Bad station name]"
     * "Cool track (Bad station name)"
     * This makes such track unsearchable, try to remove these modifications and search track without them.
     * It has obvious drawback - some tracks have brackets in their name and such operation could
     * rarely lead to displaying wrong info if original name was unsearchable.
     * <p>
     * Also some stations have different suffix/prefix for different tracks so we cannot be smart and
     * devise from several tracks' names the scheme.
     *
     * @param track Track name as station sent to us
     * @return - null if nothing changed
     * - track name without additional station's suffix/prefix
     */
    private String tryNormalizeTrack(@NonNull final String track) {
        String normalizedTrack = track.replaceAll("\\(.*\\)", "").replaceAll("\\[.*\\]", "").replaceAll("\\*.*\\*", "").trim();
        return normalizedTrack.equals(track) ? null : normalizedTrack;
    }

    public void fetchTrackMetadata(String LastFMApiKey, String artist, @NonNull final String track, @NonNull final TrackMetadataCallback trackMetadataCallback) {
        if (!ListenerUtil.mutListener.listen(2401)) {
            if ((ListenerUtil.mutListener.listen(2399) ? (LastFMApiKey.isEmpty() && TextUtils.isEmpty(track)) : (LastFMApiKey.isEmpty() || TextUtils.isEmpty(track)))) {
                if (!ListenerUtil.mutListener.listen(2400)) {
                    trackMetadataCallback.onFailure(TrackMetadataCallback.FailureType.UNRECOVERABLE);
                }
                return;
            }
        }
        final String trimmedArtist = artist.trim();
        final String trimmedTrack = track.trim();
        if (!ListenerUtil.mutListener.listen(2404)) {
            // We want to rate limit calls to Last.fm API to prevent exceeding unknown limits.
            if (rateLimiter.allowed()) {
                if (!ListenerUtil.mutListener.listen(2403)) {
                    httpClient.newCall(buildRequest(LastFMApiKey, trimmedArtist, trimmedTrack)).enqueue(new MetadataCallback(trackMetadataCallback, LastFMApiKey, trimmedArtist, trimmedTrack));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(2402)) {
                    trackMetadataCallback.onFailure(TrackMetadataCallback.FailureType.RECOVERABLE);
                }
            }
        }
    }

    private Request buildRequest(String LastFMApiKey, String artist, String track) {
        HttpUrl url = HttpUrl.parse(String.format(API_GET_TRACK_METADATA, LastFMApiKey, artist, track));
        Request.Builder requestBuilder = new Request.Builder().url(url).get();
        return requestBuilder.build();
    }

    private class MetadataCallback implements Callback {

        private final TrackMetadataCallback trackMetadataCallback;

        private final String artist;

        private final String track;

        private final String LastFMApiKey;

        public MetadataCallback(TrackMetadataCallback trackMetadataCallback, String LastFMApiKey, String artist, String track) {
            this.trackMetadataCallback = trackMetadataCallback;
            this.track = track;
            this.artist = artist;
            this.LastFMApiKey = LastFMApiKey;
        }

        @Override
        public void onFailure(Call call, IOException e) {
            if (!ListenerUtil.mutListener.listen(2405)) {
                trackMetadataCallback.onFailure(TrackMetadataCallback.FailureType.RECOVERABLE);
            }
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            try {
                LfmTrackMetadata lfmTrackMetadata = gson.fromJson(response.body().charStream(), LfmTrackMetadata.class);
                TrackMetadata trackMetadata = new TrackMetadata();
                Track trackData = lfmTrackMetadata.getTrack();
                if (!ListenerUtil.mutListener.listen(2416)) {
                    if (trackData == null) {
                        String normalizedTrack = tryNormalizeTrack(track);
                        if (!ListenerUtil.mutListener.listen(2415)) {
                            if ((ListenerUtil.mutListener.listen(2412) ? (normalizedTrack != null || (ListenerUtil.mutListener.listen(2411) ? (normalizedTrack.length() >= 3) : (ListenerUtil.mutListener.listen(2410) ? (normalizedTrack.length() <= 3) : (ListenerUtil.mutListener.listen(2409) ? (normalizedTrack.length() < 3) : (ListenerUtil.mutListener.listen(2408) ? (normalizedTrack.length() != 3) : (ListenerUtil.mutListener.listen(2407) ? (normalizedTrack.length() == 3) : (normalizedTrack.length() > 3))))))) : (normalizedTrack != null && (ListenerUtil.mutListener.listen(2411) ? (normalizedTrack.length() >= 3) : (ListenerUtil.mutListener.listen(2410) ? (normalizedTrack.length() <= 3) : (ListenerUtil.mutListener.listen(2409) ? (normalizedTrack.length() < 3) : (ListenerUtil.mutListener.listen(2408) ? (normalizedTrack.length() != 3) : (ListenerUtil.mutListener.listen(2407) ? (normalizedTrack.length() == 3) : (normalizedTrack.length() > 3))))))))) {
                                if (!ListenerUtil.mutListener.listen(2414)) {
                                    httpClient.newCall(buildRequest(LastFMApiKey, artist, normalizedTrack)).enqueue(new MetadataCallback(trackMetadataCallback, LastFMApiKey, artist, normalizedTrack));
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(2413)) {
                                    trackMetadataCallback.onFailure(TrackMetadataCallback.FailureType.UNRECOVERABLE);
                                }
                            }
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(2418)) {
                    if (trackData.getArtist() != null) {
                        if (!ListenerUtil.mutListener.listen(2417)) {
                            trackMetadata.setArtist(trackData.getArtist().getName());
                        }
                    }
                }
                List<TrackMetadata.AlbumArt> albumArts = new ArrayList<>();
                if (!ListenerUtil.mutListener.listen(2419)) {
                    trackMetadata.setAlbumArts(albumArts);
                }
                if (!ListenerUtil.mutListener.listen(2429)) {
                    if (trackData.getAlbum() != null) {
                        if (!ListenerUtil.mutListener.listen(2420)) {
                            trackMetadata.setAlbum(trackData.getAlbum().getTitle());
                        }
                        List<Image> images = lfmTrackMetadata.getTrack().getAlbum().getImage();
                        if (!ListenerUtil.mutListener.listen(2427)) {
                            {
                                long _loopCounter37 = 0;
                                for (Image img : images) {
                                    ListenerUtil.loopListener.listen("_loopCounter37", ++_loopCounter37);
                                    TrackMetadata.AlbumArtSize artSize = TrackMetadata.AlbumArtSize.SMALL;
                                    if (!ListenerUtil.mutListener.listen(2425)) {
                                        switch(img.getSize()) {
                                            case "small":
                                                if (!ListenerUtil.mutListener.listen(2421)) {
                                                    artSize = TrackMetadata.AlbumArtSize.SMALL;
                                                }
                                                break;
                                            case "medium":
                                                if (!ListenerUtil.mutListener.listen(2422)) {
                                                    artSize = TrackMetadata.AlbumArtSize.MEDIUM;
                                                }
                                                break;
                                            case "large":
                                                if (!ListenerUtil.mutListener.listen(2423)) {
                                                    artSize = TrackMetadata.AlbumArtSize.LARGE;
                                                }
                                                break;
                                            case "extralarge":
                                                if (!ListenerUtil.mutListener.listen(2424)) {
                                                    artSize = TrackMetadata.AlbumArtSize.EXTRA_LARGE;
                                                }
                                                break;
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(2426)) {
                                        albumArts.add(new TrackMetadata.AlbumArt(artSize, img.getText()));
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(2428)) {
                            Collections.sort(albumArts, new Comparator<TrackMetadata.AlbumArt>() {

                                @Override
                                public int compare(TrackMetadata.AlbumArt o1, TrackMetadata.AlbumArt o2) {
                                    return o2.size.compareTo(o1.size);
                                }
                            });
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(2430)) {
                    trackMetadata.setTrack(lfmTrackMetadata.getTrack().getName());
                }
                if (!ListenerUtil.mutListener.listen(2431)) {
                    trackMetadataCallback.onSuccess(trackMetadata);
                }
            } catch (Exception ex) {
                if (!ListenerUtil.mutListener.listen(2406)) {
                    trackMetadataCallback.onFailure(TrackMetadataCallback.FailureType.UNRECOVERABLE);
                }
            }
        }
    }
}
