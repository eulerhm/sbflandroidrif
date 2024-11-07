package net.programmierecke.radiodroid2.playlist;

import android.util.Log;
import net.programmierecke.radiodroid2.BuildConfig;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PlaylistM3UEntry {

    static final String EXTINF = "#EXTINF:";

    static final String STREAMINF = "#EXT-X-STREAM-INF:";

    static final String STREAMINF_PROGRAM = "PROGRAM-ID=";

    static final String STREAMINF_BANDWIDTH = "BANDWIDTH=";

    static final String STREAMINF_CODECS = "CODECS=";

    static final String TAG = "M3U";

    String header;

    String content;

    int length = -1;

    String title = null;

    int bitrate = -1;

    int programid = -1;

    boolean isStreamInfo = false;

    public PlaylistM3UEntry(String _header, String _content) {
        if (!ListenerUtil.mutListener.listen(1503)) {
            header = _header;
        }
        if (!ListenerUtil.mutListener.listen(1504)) {
            content = _content;
        }
        if (!ListenerUtil.mutListener.listen(1505)) {
            decode();
        }
    }

    public PlaylistM3UEntry(String _content) {
        if (!ListenerUtil.mutListener.listen(1506)) {
            header = null;
        }
        if (!ListenerUtil.mutListener.listen(1507)) {
            content = _content;
        }
    }

    void decode() {
        if (!ListenerUtil.mutListener.listen(1508)) {
            if (header == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(1525)) {
            if (header.startsWith(EXTINF)) {
                if (!ListenerUtil.mutListener.listen(1518)) {
                    if (BuildConfig.DEBUG) {
                        if (!ListenerUtil.mutListener.listen(1517)) {
                            Log.d(TAG, "found EXTINF:" + header);
                        }
                    }
                }
                String attributes = header.substring(EXTINF.length());
                int sep = attributes.indexOf(",");
                String timeStr = attributes.substring(0, sep);
                if (!ListenerUtil.mutListener.listen(1519)) {
                    length = Integer.getInteger(timeStr, -1);
                }
                if (!ListenerUtil.mutListener.listen(1524)) {
                    title = attributes.substring((ListenerUtil.mutListener.listen(1523) ? (sep % 1) : (ListenerUtil.mutListener.listen(1522) ? (sep / 1) : (ListenerUtil.mutListener.listen(1521) ? (sep * 1) : (ListenerUtil.mutListener.listen(1520) ? (sep - 1) : (sep + 1))))));
                }
            } else if (header.startsWith(STREAMINF)) {
                if (!ListenerUtil.mutListener.listen(1510)) {
                    if (BuildConfig.DEBUG) {
                        if (!ListenerUtil.mutListener.listen(1509)) {
                            Log.d(TAG, "found STREAMINFO:" + header);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1511)) {
                    isStreamInfo = true;
                }
                String attributes = header.substring(STREAMINF.length());
                String[] attributesList = attributes.split(",");
                if (!ListenerUtil.mutListener.listen(1516)) {
                    {
                        long _loopCounter28 = 0;
                        for (String attr : attributesList) {
                            ListenerUtil.loopListener.listen("_loopCounter28", ++_loopCounter28);
                            if (!ListenerUtil.mutListener.listen(1513)) {
                                if (attr.startsWith(STREAMINF_BANDWIDTH)) {
                                    String paramStr = attr.substring(STREAMINF_BANDWIDTH.length());
                                    if (!ListenerUtil.mutListener.listen(1512)) {
                                        bitrate = Integer.getInteger(paramStr, -1);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(1515)) {
                                if (attr.startsWith(STREAMINF_PROGRAM)) {
                                    String paramStr = attr.substring(STREAMINF_PROGRAM.length());
                                    if (!ListenerUtil.mutListener.listen(1514)) {
                                        programid = Integer.getInteger(paramStr, -1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean getIsStreamInformation() {
        return isStreamInfo;
    }

    public int getBitrate() {
        return bitrate;
    }

    public int getLength() {
        return length;
    }

    public String getTitle() {
        return title;
    }

    public int getProgramId() {
        return programid;
    }

    public String getContent() {
        return content;
    }
}
