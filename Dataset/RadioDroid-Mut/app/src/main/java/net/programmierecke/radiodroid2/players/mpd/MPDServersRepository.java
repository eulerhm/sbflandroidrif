package net.programmierecke.radiodroid2.players.mpd;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.preference.PreferenceManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * MPD servers repository which is serialized into preferences.
 * It is NOT thread safe.
 * In future should be backed up by database.
 */
public class MPDServersRepository {

    private List<MPDServerData> servers;

    private final MutableLiveData<List<MPDServerData>> serversLiveData = new MutableLiveData<>();

    private int lastServerId = -1;

    private final Context context;

    public MPDServersRepository(Context context) {
        this.context = context;
        if (!ListenerUtil.mutListener.listen(1203)) {
            servers = getMPDServers(context);
        }
        if (!ListenerUtil.mutListener.listen(1211)) {
            {
                long _loopCounter21 = 0;
                for (MPDServerData server : servers) {
                    ListenerUtil.loopListener.listen("_loopCounter21", ++_loopCounter21);
                    if (!ListenerUtil.mutListener.listen(1210)) {
                        if ((ListenerUtil.mutListener.listen(1208) ? (server.id >= lastServerId) : (ListenerUtil.mutListener.listen(1207) ? (server.id <= lastServerId) : (ListenerUtil.mutListener.listen(1206) ? (server.id < lastServerId) : (ListenerUtil.mutListener.listen(1205) ? (server.id != lastServerId) : (ListenerUtil.mutListener.listen(1204) ? (server.id == lastServerId) : (server.id > lastServerId))))))) {
                            if (!ListenerUtil.mutListener.listen(1209)) {
                                lastServerId = server.id;
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1212)) {
            serversLiveData.setValue(servers);
        }
    }

    public LiveData<List<MPDServerData>> getAllServers() {
        return serversLiveData;
    }

    public void addServer(@NonNull MPDServerData mpdServerData) {
        if (!ListenerUtil.mutListener.listen(1213)) {
            mpdServerData.id = ++lastServerId;
        }
        if (!ListenerUtil.mutListener.listen(1214)) {
            servers.add(mpdServerData);
        }
        if (!ListenerUtil.mutListener.listen(1215)) {
            saveMPDServers(servers, context);
        }
        if (!ListenerUtil.mutListener.listen(1216)) {
            serversLiveData.postValue(servers);
        }
    }

    public boolean isEmpty() {
        if (!ListenerUtil.mutListener.listen(1218)) {
            if (serversLiveData != null) {
                List<MPDServerData> list = serversLiveData.getValue();
                if (!ListenerUtil.mutListener.listen(1217)) {
                    if (list != null) {
                        return list.size() == 0;
                    }
                }
            }
        }
        return true;
    }

    public void removeServer(@NonNull MPDServerData mpdServerData) {
        boolean changed = false;
        if (!ListenerUtil.mutListener.listen(1227)) {
            {
                long _loopCounter22 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(1226) ? (i >= servers.size()) : (ListenerUtil.mutListener.listen(1225) ? (i <= servers.size()) : (ListenerUtil.mutListener.listen(1224) ? (i > servers.size()) : (ListenerUtil.mutListener.listen(1223) ? (i != servers.size()) : (ListenerUtil.mutListener.listen(1222) ? (i == servers.size()) : (i < servers.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter22", ++_loopCounter22);
                    if (!ListenerUtil.mutListener.listen(1221)) {
                        if (servers.get(i).id == mpdServerData.id) {
                            if (!ListenerUtil.mutListener.listen(1219)) {
                                servers.remove(i);
                            }
                            if (!ListenerUtil.mutListener.listen(1220)) {
                                changed = true;
                            }
                            break;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1230)) {
            if (changed) {
                if (!ListenerUtil.mutListener.listen(1228)) {
                    saveMPDServers(servers, context);
                }
                if (!ListenerUtil.mutListener.listen(1229)) {
                    serversLiveData.postValue(servers);
                }
            }
        }
    }

    public void resetAllConnectionStatus() {
        if (!ListenerUtil.mutListener.listen(1232)) {
            {
                long _loopCounter23 = 0;
                for (MPDServerData serverData : servers) {
                    ListenerUtil.loopListener.listen("_loopCounter23", ++_loopCounter23);
                    if (!ListenerUtil.mutListener.listen(1231)) {
                        serverData.connected = false;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1233)) {
            serversLiveData.postValue(serversLiveData.getValue());
        }
    }

    public void updatePersistentData(@NonNull MPDServerData mpdServerData) {
        boolean changed = false;
        if (!ListenerUtil.mutListener.listen(1243)) {
            {
                long _loopCounter24 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(1242) ? (i >= servers.size()) : (ListenerUtil.mutListener.listen(1241) ? (i <= servers.size()) : (ListenerUtil.mutListener.listen(1240) ? (i > servers.size()) : (ListenerUtil.mutListener.listen(1239) ? (i != servers.size()) : (ListenerUtil.mutListener.listen(1238) ? (i == servers.size()) : (i < servers.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter24", ++_loopCounter24);
                    MPDServerData data = servers.get(i);
                    if (!ListenerUtil.mutListener.listen(1237)) {
                        if ((ListenerUtil.mutListener.listen(1234) ? (data.id == mpdServerData.id || !data.contentEquals(mpdServerData)) : (data.id == mpdServerData.id && !data.contentEquals(mpdServerData)))) {
                            if (!ListenerUtil.mutListener.listen(1235)) {
                                servers.set(i, mpdServerData);
                            }
                            if (!ListenerUtil.mutListener.listen(1236)) {
                                changed = true;
                            }
                            break;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1246)) {
            if (changed) {
                if (!ListenerUtil.mutListener.listen(1244)) {
                    saveMPDServers(servers, context);
                }
                if (!ListenerUtil.mutListener.listen(1245)) {
                    serversLiveData.postValue(serversLiveData.getValue());
                }
            }
        }
    }

    public void updateRuntimeData(@NonNull MPDServerData mpdServerData) {
        boolean changed = false;
        if (!ListenerUtil.mutListener.listen(1256)) {
            {
                long _loopCounter25 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(1255) ? (i >= servers.size()) : (ListenerUtil.mutListener.listen(1254) ? (i <= servers.size()) : (ListenerUtil.mutListener.listen(1253) ? (i > servers.size()) : (ListenerUtil.mutListener.listen(1252) ? (i != servers.size()) : (ListenerUtil.mutListener.listen(1251) ? (i == servers.size()) : (i < servers.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter25", ++_loopCounter25);
                    MPDServerData data = servers.get(i);
                    if (!ListenerUtil.mutListener.listen(1250)) {
                        if ((ListenerUtil.mutListener.listen(1247) ? (data.id == mpdServerData.id || !data.contentEquals(mpdServerData)) : (data.id == mpdServerData.id && !data.contentEquals(mpdServerData)))) {
                            if (!ListenerUtil.mutListener.listen(1248)) {
                                servers.set(i, mpdServerData);
                            }
                            if (!ListenerUtil.mutListener.listen(1249)) {
                                changed = true;
                            }
                            break;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1258)) {
            if (changed) {
                if (!ListenerUtil.mutListener.listen(1257)) {
                    serversLiveData.postValue(serversLiveData.getValue());
                }
            }
        }
    }

    private static List<MPDServerData> getMPDServers(Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String serversFromPrefs = sharedPref.getString("mpd_servers", "");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<MPDServerData>>() {
        }.getType();
        List<MPDServerData> serversList = gson.fromJson(serversFromPrefs, type);
        return serversList != null ? serversList : new ArrayList<>();
    }

    private static void saveMPDServers(List<MPDServerData> servers, Context context) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new Gson();
        String serversJson = gson.toJson(servers);
        if (!ListenerUtil.mutListener.listen(1259)) {
            editor.putString("mpd_servers", serversJson);
        }
        if (!ListenerUtil.mutListener.listen(1260)) {
            editor.apply();
        }
    }
}
