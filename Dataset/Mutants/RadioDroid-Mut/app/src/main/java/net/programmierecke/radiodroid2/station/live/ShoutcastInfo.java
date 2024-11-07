package net.programmierecke.radiodroid2.station.live;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import java.util.LinkedHashMap;
import java.util.Map;
import okhttp3.Response;
import static net.programmierecke.radiodroid2.Utils.parseIntWithDefault;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ShoutcastInfo implements Parcelable {

    public int metadataOffset;

    public int bitrate;

    // e.g.: ice-audio-info: ice-samplerate=44100;ice-bitrate=128;ice-channels=2
    public String audioInfo;

    public String audioDesc;

    // e.g.: icy-genre:Pop / Rock
    public String audioGenre;

    public String audioName;

    public String audioHomePage;

    // e.g.: Server: Icecast 2.3.2
    public String serverName;

    public boolean serverPublic;

    public int channels;

    public int sampleRate;

    public ShoutcastInfo() {
    }

    public ShoutcastInfo(IcyHeaders icyHeaders) {
        if (!ListenerUtil.mutListener.listen(2441)) {
            this.bitrate = icyHeaders.bitrate;
        }
        if (!ListenerUtil.mutListener.listen(2442)) {
            this.audioGenre = icyHeaders.genre;
        }
        if (!ListenerUtil.mutListener.listen(2443)) {
            this.serverPublic = icyHeaders.isPublic;
        }
        if (!ListenerUtil.mutListener.listen(2444)) {
            this.audioName = icyHeaders.name;
        }
        if (!ListenerUtil.mutListener.listen(2445)) {
            this.audioHomePage = icyHeaders.url;
        }
    }

    public static ShoutcastInfo Decode(Response response) {
        ShoutcastInfo info = new ShoutcastInfo();
        if (!ListenerUtil.mutListener.listen(2446)) {
            info.metadataOffset = parseIntWithDefault(response.header("icy-metaint"), 0);
        }
        if (!ListenerUtil.mutListener.listen(2447)) {
            info.bitrate = parseIntWithDefault(response.header("icy-br"), 0);
        }
        if (!ListenerUtil.mutListener.listen(2448)) {
            // e.g.: ice-audio-info: ice-samplerate=44100;ice-bitrate=128;ice-channels=2
            info.audioInfo = response.header("ice-audio-info");
        }
        if (!ListenerUtil.mutListener.listen(2449)) {
            info.audioDesc = response.header("icy-description");
        }
        if (!ListenerUtil.mutListener.listen(2450)) {
            // e.g.: icy-genre:Pop / Rock
            info.audioGenre = response.header("icy-genre");
        }
        if (!ListenerUtil.mutListener.listen(2451)) {
            info.audioName = response.header("icy-name");
        }
        if (!ListenerUtil.mutListener.listen(2452)) {
            info.audioHomePage = response.header("icy-url");
        }
        if (!ListenerUtil.mutListener.listen(2453)) {
            // e.g.: Server: Icecast 2.3.2
            info.serverName = response.header("Server");
        }
        if (!ListenerUtil.mutListener.listen(2459)) {
            info.serverPublic = (ListenerUtil.mutListener.listen(2458) ? (parseIntWithDefault(response.header("icy-pub"), 0) >= 0) : (ListenerUtil.mutListener.listen(2457) ? (parseIntWithDefault(response.header("icy-pub"), 0) <= 0) : (ListenerUtil.mutListener.listen(2456) ? (parseIntWithDefault(response.header("icy-pub"), 0) < 0) : (ListenerUtil.mutListener.listen(2455) ? (parseIntWithDefault(response.header("icy-pub"), 0) != 0) : (ListenerUtil.mutListener.listen(2454) ? (parseIntWithDefault(response.header("icy-pub"), 0) == 0) : (parseIntWithDefault(response.header("icy-pub"), 0) > 0))))));
        }
        if (!ListenerUtil.mutListener.listen(2490)) {
            if (info.audioInfo != null) {
                Map<String, String> audioInfoParams = splitAudioInfo(info.audioInfo);
                if (!ListenerUtil.mutListener.listen(2460)) {
                    info.channels = parseIntWithDefault(audioInfoParams.get("ice-channels"), 0);
                }
                if (!ListenerUtil.mutListener.listen(2467)) {
                    if ((ListenerUtil.mutListener.listen(2465) ? (info.channels >= 0) : (ListenerUtil.mutListener.listen(2464) ? (info.channels <= 0) : (ListenerUtil.mutListener.listen(2463) ? (info.channels > 0) : (ListenerUtil.mutListener.listen(2462) ? (info.channels < 0) : (ListenerUtil.mutListener.listen(2461) ? (info.channels != 0) : (info.channels == 0))))))) {
                        if (!ListenerUtil.mutListener.listen(2466)) {
                            info.channels = parseIntWithDefault(audioInfoParams.get("channels"), 0);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(2468)) {
                    info.sampleRate = parseIntWithDefault(audioInfoParams.get("ice-samplerate"), 0);
                }
                if (!ListenerUtil.mutListener.listen(2475)) {
                    if ((ListenerUtil.mutListener.listen(2473) ? (info.sampleRate >= 0) : (ListenerUtil.mutListener.listen(2472) ? (info.sampleRate <= 0) : (ListenerUtil.mutListener.listen(2471) ? (info.sampleRate > 0) : (ListenerUtil.mutListener.listen(2470) ? (info.sampleRate < 0) : (ListenerUtil.mutListener.listen(2469) ? (info.sampleRate != 0) : (info.sampleRate == 0))))))) {
                        if (!ListenerUtil.mutListener.listen(2474)) {
                            info.sampleRate = parseIntWithDefault(audioInfoParams.get("samplerate"), 0);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(2489)) {
                    if ((ListenerUtil.mutListener.listen(2480) ? (info.bitrate >= 0) : (ListenerUtil.mutListener.listen(2479) ? (info.bitrate <= 0) : (ListenerUtil.mutListener.listen(2478) ? (info.bitrate > 0) : (ListenerUtil.mutListener.listen(2477) ? (info.bitrate < 0) : (ListenerUtil.mutListener.listen(2476) ? (info.bitrate != 0) : (info.bitrate == 0))))))) {
                        if (!ListenerUtil.mutListener.listen(2481)) {
                            info.bitrate = parseIntWithDefault(audioInfoParams.get("ice-bitrate"), 0);
                        }
                        if (!ListenerUtil.mutListener.listen(2488)) {
                            if ((ListenerUtil.mutListener.listen(2486) ? (info.bitrate >= 0) : (ListenerUtil.mutListener.listen(2485) ? (info.bitrate <= 0) : (ListenerUtil.mutListener.listen(2484) ? (info.bitrate > 0) : (ListenerUtil.mutListener.listen(2483) ? (info.bitrate < 0) : (ListenerUtil.mutListener.listen(2482) ? (info.bitrate != 0) : (info.bitrate == 0))))))) {
                                if (!ListenerUtil.mutListener.listen(2487)) {
                                    info.bitrate = parseIntWithDefault(audioInfoParams.get("bitrate"), 0);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(2496)) {
            // info needs at least metadataoffset
            if ((ListenerUtil.mutListener.listen(2495) ? (info.metadataOffset >= 0) : (ListenerUtil.mutListener.listen(2494) ? (info.metadataOffset <= 0) : (ListenerUtil.mutListener.listen(2493) ? (info.metadataOffset > 0) : (ListenerUtil.mutListener.listen(2492) ? (info.metadataOffset < 0) : (ListenerUtil.mutListener.listen(2491) ? (info.metadataOffset != 0) : (info.metadataOffset == 0))))))) {
                return null;
            }
        }
        return info;
    }

    private static Map<String, String> splitAudioInfo(String audioInfo) {
        Map<String, String> params = new LinkedHashMap<>();
        String[] pairs = audioInfo.split(";");
        if (!ListenerUtil.mutListener.listen(2502)) {
            {
                long _loopCounter38 = 0;
                for (String pair : pairs) {
                    ListenerUtil.loopListener.listen("_loopCounter38", ++_loopCounter38);
                    int idx = pair.indexOf("=");
                    if (!ListenerUtil.mutListener.listen(2501)) {
                        params.put(pair.substring(0, idx), pair.substring((ListenerUtil.mutListener.listen(2500) ? (idx % 1) : (ListenerUtil.mutListener.listen(2499) ? (idx / 1) : (ListenerUtil.mutListener.listen(2498) ? (idx * 1) : (ListenerUtil.mutListener.listen(2497) ? (idx - 1) : (idx + 1)))))));
                    }
                }
            }
        }
        return params;
    }

    protected ShoutcastInfo(Parcel in) {
        if (!ListenerUtil.mutListener.listen(2503)) {
            metadataOffset = in.readInt();
        }
        if (!ListenerUtil.mutListener.listen(2504)) {
            bitrate = in.readInt();
        }
        if (!ListenerUtil.mutListener.listen(2505)) {
            audioInfo = in.readString();
        }
        if (!ListenerUtil.mutListener.listen(2506)) {
            audioDesc = in.readString();
        }
        if (!ListenerUtil.mutListener.listen(2507)) {
            audioGenre = in.readString();
        }
        if (!ListenerUtil.mutListener.listen(2508)) {
            audioName = in.readString();
        }
        if (!ListenerUtil.mutListener.listen(2509)) {
            audioHomePage = in.readString();
        }
        if (!ListenerUtil.mutListener.listen(2510)) {
            serverName = in.readString();
        }
        if (!ListenerUtil.mutListener.listen(2511)) {
            serverPublic = in.readByte() != 0;
        }
        if (!ListenerUtil.mutListener.listen(2512)) {
            channels = in.readInt();
        }
        if (!ListenerUtil.mutListener.listen(2513)) {
            sampleRate = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (!ListenerUtil.mutListener.listen(2514)) {
            dest.writeInt(metadataOffset);
        }
        if (!ListenerUtil.mutListener.listen(2515)) {
            dest.writeInt(bitrate);
        }
        if (!ListenerUtil.mutListener.listen(2516)) {
            dest.writeString(audioInfo);
        }
        if (!ListenerUtil.mutListener.listen(2517)) {
            dest.writeString(audioDesc);
        }
        if (!ListenerUtil.mutListener.listen(2518)) {
            dest.writeString(audioGenre);
        }
        if (!ListenerUtil.mutListener.listen(2519)) {
            dest.writeString(audioName);
        }
        if (!ListenerUtil.mutListener.listen(2520)) {
            dest.writeString(audioHomePage);
        }
        if (!ListenerUtil.mutListener.listen(2521)) {
            dest.writeString(serverName);
        }
        if (!ListenerUtil.mutListener.listen(2522)) {
            dest.writeByte((byte) (serverPublic ? 1 : 0));
        }
        if (!ListenerUtil.mutListener.listen(2523)) {
            dest.writeInt(channels);
        }
        if (!ListenerUtil.mutListener.listen(2524)) {
            dest.writeInt(sampleRate);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ShoutcastInfo> CREATOR = new Creator<ShoutcastInfo>() {

        @Override
        public ShoutcastInfo createFromParcel(Parcel in) {
            return new ShoutcastInfo(in);
        }

        @Override
        public ShoutcastInfo[] newArray(int size) {
            return new ShoutcastInfo[size];
        }
    };
}
