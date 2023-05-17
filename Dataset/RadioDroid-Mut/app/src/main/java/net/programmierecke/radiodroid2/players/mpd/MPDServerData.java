package net.programmierecke.radiodroid2.players.mpd;

import androidx.annotation.NonNull;
import java.util.HashMap;
import java.util.Map;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MPDServerData {

    public enum Status {

        Idle, Paused, Playing
    }

    // Persistent data
    public int id = -1;

    public String name;

    public String hostname;

    public int port;

    public String password;

    // Runtime status
    public boolean isReachable = false;

    public Status status = MPDServerData.Status.Idle;

    public int volume = 0;

    public boolean connected = false;

    public MPDServerData(@NonNull String name, @NonNull String hostname, int port, String password) {
        if (!ListenerUtil.mutListener.listen(1141)) {
            this.name = name;
        }
        if (!ListenerUtil.mutListener.listen(1142)) {
            this.hostname = hostname;
        }
        if (!ListenerUtil.mutListener.listen(1143)) {
            this.port = port;
        }
        if (!ListenerUtil.mutListener.listen(1144)) {
            this.password = password;
        }
    }

    public MPDServerData(MPDServerData other) {
        if (!ListenerUtil.mutListener.listen(1145)) {
            this.id = other.id;
        }
        if (!ListenerUtil.mutListener.listen(1146)) {
            this.name = other.name;
        }
        if (!ListenerUtil.mutListener.listen(1147)) {
            this.hostname = other.hostname;
        }
        if (!ListenerUtil.mutListener.listen(1148)) {
            this.password = other.password;
        }
        if (!ListenerUtil.mutListener.listen(1149)) {
            this.port = other.port;
        }
        if (!ListenerUtil.mutListener.listen(1150)) {
            this.isReachable = other.isReachable;
        }
        if (!ListenerUtil.mutListener.listen(1151)) {
            this.status = other.status;
        }
        if (!ListenerUtil.mutListener.listen(1152)) {
            this.volume = other.volume;
        }
        if (!ListenerUtil.mutListener.listen(1153)) {
            this.connected = other.connected;
        }
    }

    public void updateStatus(@NonNull String str) {
        Map<String, String> statusMap = new HashMap<>();
        String[] lines = str.split("\\R");
        if (!ListenerUtil.mutListener.listen(1161)) {
            {
                long _loopCounter20 = 0;
                for (String line : lines) {
                    ListenerUtil.loopListener.listen("_loopCounter20", ++_loopCounter20);
                    String[] keyAndValue = line.split(": ", 2);
                    if (!ListenerUtil.mutListener.listen(1160)) {
                        if ((ListenerUtil.mutListener.listen(1158) ? (keyAndValue.length >= 2) : (ListenerUtil.mutListener.listen(1157) ? (keyAndValue.length <= 2) : (ListenerUtil.mutListener.listen(1156) ? (keyAndValue.length > 2) : (ListenerUtil.mutListener.listen(1155) ? (keyAndValue.length < 2) : (ListenerUtil.mutListener.listen(1154) ? (keyAndValue.length != 2) : (keyAndValue.length == 2))))))) {
                            if (!ListenerUtil.mutListener.listen(1159)) {
                                statusMap.put(keyAndValue[0], keyAndValue[1]);
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1164)) {
            if (statusMap.containsKey("volume")) {
                if (!ListenerUtil.mutListener.listen(1163)) {
                    volume = Integer.parseInt(statusMap.get("volume"));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(1162)) {
                    volume = 0;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1169)) {
            if (statusMap.containsKey("state")) {
                String stateStr = statusMap.get("state");
                assert stateStr != null;
                if (!ListenerUtil.mutListener.listen(1168)) {
                    switch(stateStr) {
                        case "stop":
                            if (!ListenerUtil.mutListener.listen(1165)) {
                                status = Status.Idle;
                            }
                            break;
                        case "pause":
                            if (!ListenerUtil.mutListener.listen(1166)) {
                                status = Status.Paused;
                            }
                            break;
                        case "play":
                            if (!ListenerUtil.mutListener.listen(1167)) {
                                status = Status.Playing;
                            }
                            break;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1170)) {
            connected = true;
        }
    }

    public boolean contentEquals(MPDServerData o) {
        if (!ListenerUtil.mutListener.listen(1171)) {
            if (o == null)
                return false;
        }
        if (!ListenerUtil.mutListener.listen(1177)) {
            if ((ListenerUtil.mutListener.listen(1176) ? (id >= o.id) : (ListenerUtil.mutListener.listen(1175) ? (id <= o.id) : (ListenerUtil.mutListener.listen(1174) ? (id > o.id) : (ListenerUtil.mutListener.listen(1173) ? (id < o.id) : (ListenerUtil.mutListener.listen(1172) ? (id == o.id) : (id != o.id)))))))
                return false;
        }
        if (!ListenerUtil.mutListener.listen(1183)) {
            if ((ListenerUtil.mutListener.listen(1182) ? (port >= o.port) : (ListenerUtil.mutListener.listen(1181) ? (port <= o.port) : (ListenerUtil.mutListener.listen(1180) ? (port > o.port) : (ListenerUtil.mutListener.listen(1179) ? (port < o.port) : (ListenerUtil.mutListener.listen(1178) ? (port == o.port) : (port != o.port)))))))
                return false;
        }
        if (!ListenerUtil.mutListener.listen(1184)) {
            if (isReachable != o.isReachable)
                return false;
        }
        if (!ListenerUtil.mutListener.listen(1190)) {
            if ((ListenerUtil.mutListener.listen(1189) ? (volume >= o.volume) : (ListenerUtil.mutListener.listen(1188) ? (volume <= o.volume) : (ListenerUtil.mutListener.listen(1187) ? (volume > o.volume) : (ListenerUtil.mutListener.listen(1186) ? (volume < o.volume) : (ListenerUtil.mutListener.listen(1185) ? (volume == o.volume) : (volume != o.volume)))))))
                return false;
        }
        if (!ListenerUtil.mutListener.listen(1191)) {
            if (connected != o.connected)
                return false;
        }
        if (!ListenerUtil.mutListener.listen(1192)) {
            if (password != null ? !password.equals(o.password) : o.password != null)
                return false;
        }
        if (!ListenerUtil.mutListener.listen(1193)) {
            if (name != null ? !name.equals(o.name) : o.name != null)
                return false;
        }
        if (!ListenerUtil.mutListener.listen(1194)) {
            if (hostname != null ? !hostname.equals(o.hostname) : o.hostname != null)
                return false;
        }
        return status == o.status;
    }

    @Override
    public boolean equals(Object o) {
        if (!ListenerUtil.mutListener.listen(1195)) {
            if (this == o)
                return true;
        }
        if (!ListenerUtil.mutListener.listen(1197)) {
            if ((ListenerUtil.mutListener.listen(1196) ? (o == null && getClass() != o.getClass()) : (o == null || getClass() != o.getClass())))
                return false;
        }
        MPDServerData that = (MPDServerData) o;
        return (ListenerUtil.mutListener.listen(1202) ? (id >= that.id) : (ListenerUtil.mutListener.listen(1201) ? (id <= that.id) : (ListenerUtil.mutListener.listen(1200) ? (id > that.id) : (ListenerUtil.mutListener.listen(1199) ? (id < that.id) : (ListenerUtil.mutListener.listen(1198) ? (id != that.id) : (id == that.id))))));
    }

    @Override
    public int hashCode() {
        return id;
    }
}
