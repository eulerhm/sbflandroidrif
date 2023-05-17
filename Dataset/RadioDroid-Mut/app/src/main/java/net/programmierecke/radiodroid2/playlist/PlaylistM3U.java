package net.programmierecke.radiodroid2.playlist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PlaylistM3U {

    static final String COMMENTMARKER = "#";

    static final String EXTENDED = "#EXTM3U";

    String fullText;

    URL path;

    boolean extended = false;

    ArrayList<PlaylistM3UEntry> entries = new ArrayList<PlaylistM3UEntry>();

    String header = null;

    public PlaylistM3U(URL _path, String _fullText) {
        if (!ListenerUtil.mutListener.listen(1487)) {
            path = _path;
        }
        if (!ListenerUtil.mutListener.listen(1488)) {
            fullText = _fullText;
        }
        if (!ListenerUtil.mutListener.listen(1489)) {
            decode();
        }
    }

    void decode() {
        String[] lines = getLines();
        if (!ListenerUtil.mutListener.listen(1491)) {
            {
                long _loopCounter26 = 0;
                for (String line : lines) {
                    ListenerUtil.loopListener.listen("_loopCounter26", ++_loopCounter26);
                    try {
                        if (!ListenerUtil.mutListener.listen(1490)) {
                            decodeLine(line);
                        }
                    } catch (MalformedURLException e) {
                    }
                }
            }
        }
    }

    URL resolveToBase(String file) throws MalformedURLException {
        String oldPath = path.getPath();
        String filePath = getBasePath(oldPath) + "/" + file;
        return new URL(path.getProtocol(), path.getHost(), path.getPort(), filePath);
    }

    void decodeLine(String line) throws MalformedURLException {
        if (!ListenerUtil.mutListener.listen(1500)) {
            if (line.startsWith(EXTENDED)) {
                if (!ListenerUtil.mutListener.listen(1499)) {
                    extended = true;
                }
            } else if (line.startsWith(COMMENTMARKER)) {
                if (!ListenerUtil.mutListener.listen(1498)) {
                    if (extended) {
                        if (!ListenerUtil.mutListener.listen(1497)) {
                            header = line;
                        }
                    }
                }
            } else {
                String lineLower = line.toLowerCase();
                if (!ListenerUtil.mutListener.listen(1495)) {
                    if ((ListenerUtil.mutListener.listen(1492) ? (lineLower.startsWith("http://") && lineLower.startsWith("https://")) : (lineLower.startsWith("http://") || lineLower.startsWith("https://")))) {
                        if (!ListenerUtil.mutListener.listen(1494)) {
                            entries.add(new PlaylistM3UEntry(header, line));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(1493)) {
                            entries.add(new PlaylistM3UEntry(header, resolveToBase(line).toString()));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(1496)) {
                    header = null;
                }
            }
        }
    }

    String getBasePath(String fullPath) {
        final char pathSeparator = '/';
        int sep = fullPath.lastIndexOf(pathSeparator);
        return fullPath.substring(0, sep);
    }

    String[] getLines() {
        StringReader r = new StringReader(fullText);
        BufferedReader br = new BufferedReader(r);
        ArrayList<String> list = new ArrayList<String>();
        String line;
        try {
            if (!ListenerUtil.mutListener.listen(1502)) {
                {
                    long _loopCounter27 = 0;
                    while ((line = br.readLine()) != null) {
                        ListenerUtil.loopListener.listen("_loopCounter27", ++_loopCounter27);
                        if (!ListenerUtil.mutListener.listen(1501)) {
                            list.add(line);
                        }
                    }
                }
            }
        } catch (IOException e) {
        }
        return list.toArray(new String[0]);
    }

    public PlaylistM3UEntry[] getEntries() {
        return entries.toArray(new PlaylistM3UEntry[0]);
    }
}
