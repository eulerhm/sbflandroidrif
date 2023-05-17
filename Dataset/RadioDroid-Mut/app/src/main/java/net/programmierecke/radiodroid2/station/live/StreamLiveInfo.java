package net.programmierecke.radiodroid2.station.live;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.util.Map;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class StreamLiveInfo implements Parcelable {

    public StreamLiveInfo(Map<String, String> rawMetadata) {
        if (!ListenerUtil.mutListener.listen(2525)) {
            this.rawMetadata = rawMetadata;
        }
        if (!ListenerUtil.mutListener.listen(2537)) {
            if ((ListenerUtil.mutListener.listen(2526) ? (rawMetadata != null || rawMetadata.containsKey("StreamTitle")) : (rawMetadata != null && rawMetadata.containsKey("StreamTitle")))) {
                if (!ListenerUtil.mutListener.listen(2527)) {
                    title = rawMetadata.get("StreamTitle");
                }
                if (!ListenerUtil.mutListener.listen(2536)) {
                    if (!TextUtils.isEmpty(title)) {
                        String[] artistAndTrack = title.split(" - ", 2);
                        if (!ListenerUtil.mutListener.listen(2528)) {
                            artist = artistAndTrack[0];
                        }
                        if (!ListenerUtil.mutListener.listen(2535)) {
                            if ((ListenerUtil.mutListener.listen(2533) ? (artistAndTrack.length >= 2) : (ListenerUtil.mutListener.listen(2532) ? (artistAndTrack.length <= 2) : (ListenerUtil.mutListener.listen(2531) ? (artistAndTrack.length > 2) : (ListenerUtil.mutListener.listen(2530) ? (artistAndTrack.length < 2) : (ListenerUtil.mutListener.listen(2529) ? (artistAndTrack.length != 2) : (artistAndTrack.length == 2))))))) {
                                if (!ListenerUtil.mutListener.listen(2534)) {
                                    track = artistAndTrack[1];
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public boolean hasArtistAndTrack() {
        return !((ListenerUtil.mutListener.listen(2538) ? (artist.isEmpty() && track.isEmpty()) : (artist.isEmpty() || track.isEmpty())));
    }

    @NonNull
    public String getArtist() {
        return artist;
    }

    @NonNull
    public String getTrack() {
        return track;
    }

    public Map<String, String> getRawMetadata() {
        return rawMetadata;
    }

    private String title = "";

    private String artist = "";

    private String track = "";

    private Map<String, String> rawMetadata;

    protected StreamLiveInfo(Parcel in) {
        if (!ListenerUtil.mutListener.listen(2539)) {
            title = in.readString();
        }
        if (!ListenerUtil.mutListener.listen(2540)) {
            artist = in.readString();
        }
        if (!ListenerUtil.mutListener.listen(2541)) {
            track = in.readString();
        }
        if (!ListenerUtil.mutListener.listen(2542)) {
            in.readMap(rawMetadata, String.class.getClassLoader());
        }
    }

    public static final Creator<StreamLiveInfo> CREATOR = new Creator<StreamLiveInfo>() {

        @Override
        public StreamLiveInfo createFromParcel(Parcel in) {
            return new StreamLiveInfo(in);
        }

        @Override
        public StreamLiveInfo[] newArray(int size) {
            return new StreamLiveInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (!ListenerUtil.mutListener.listen(2543)) {
            parcel.writeString(title);
        }
        if (!ListenerUtil.mutListener.listen(2544)) {
            parcel.writeString(artist);
        }
        if (!ListenerUtil.mutListener.listen(2545)) {
            parcel.writeString(track);
        }
        if (!ListenerUtil.mutListener.listen(2546)) {
            parcel.writeMap(rawMetadata);
        }
    }
}
