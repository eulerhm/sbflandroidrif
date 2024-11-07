package net.programmierecke.radiodroid2;

import android.util.Log;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Vector;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class RadioBrowserServerManager {

    static String currentServer = null;

    static String[] serverList = null;

    /**
     * Blocking: do dns request do get a list of all available servers
     */
    private static String[] doDnsServerListing() {
        if (!ListenerUtil.mutListener.listen(4970)) {
            Log.d("DNS", "doDnsServerListing()");
        }
        Vector<String> listResult = new Vector<String>();
        try {
            // add all round robin servers one by one to select them separately
            InetAddress[] list = InetAddress.getAllByName("all.api.radio-browser.info");
            if (!ListenerUtil.mutListener.listen(4977)) {
                {
                    long _loopCounter60 = 0;
                    for (InetAddress item : list) {
                        ListenerUtil.loopListener.listen("_loopCounter60", ++_loopCounter60);
                        // do not use original variable, it could fall back to "all.api.radio-browser.info"
                        String currentHostAddress = item.getHostAddress();
                        InetAddress new_item = InetAddress.getByName(currentHostAddress);
                        if (!ListenerUtil.mutListener.listen(4972)) {
                            Log.i("DNS", "Found: " + new_item.toString() + " -> " + new_item.getCanonicalHostName());
                        }
                        String name = item.getCanonicalHostName();
                        if (!ListenerUtil.mutListener.listen(4976)) {
                            if ((ListenerUtil.mutListener.listen(4973) ? (!name.equals("all.api.radio-browser.info") || !name.equals(currentHostAddress)) : (!name.equals("all.api.radio-browser.info") && !name.equals(currentHostAddress)))) {
                                if (!ListenerUtil.mutListener.listen(4974)) {
                                    Log.i("DNS", "Added entry: '" + name + "'");
                                }
                                if (!ListenerUtil.mutListener.listen(4975)) {
                                    listResult.add(name);
                                }
                            }
                        }
                    }
                }
            }
        } catch (UnknownHostException e) {
            if (!ListenerUtil.mutListener.listen(4971)) {
                e.printStackTrace();
            }
        }
        if (!ListenerUtil.mutListener.listen(4985)) {
            if ((ListenerUtil.mutListener.listen(4982) ? (listResult.size() >= 0) : (ListenerUtil.mutListener.listen(4981) ? (listResult.size() <= 0) : (ListenerUtil.mutListener.listen(4980) ? (listResult.size() > 0) : (ListenerUtil.mutListener.listen(4979) ? (listResult.size() < 0) : (ListenerUtil.mutListener.listen(4978) ? (listResult.size() != 0) : (listResult.size() == 0))))))) {
                if (!ListenerUtil.mutListener.listen(4983)) {
                    // should we inform people that their internet provider is not able to do reverse lookups? (= is shit)
                    Log.w("DNS", "Fallback to de1.api.radio-browser.info because dns call did not work.");
                }
                if (!ListenerUtil.mutListener.listen(4984)) {
                    listResult.add("de1.api.radio-browser.info");
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(4986)) {
            Log.d("DNS", "doDnsServerListing() Found servers: " + listResult.size());
        }
        return listResult.toArray(new String[0]);
    }

    /**
     * Blocking: return current cached server list. Generate list if still null.
     */
    public static String[] getServerList(boolean forceRefresh) {
        if (!ListenerUtil.mutListener.listen(4995)) {
            if ((ListenerUtil.mutListener.listen(4993) ? ((ListenerUtil.mutListener.listen(4992) ? (serverList == null && (ListenerUtil.mutListener.listen(4991) ? (serverList.length >= 0) : (ListenerUtil.mutListener.listen(4990) ? (serverList.length <= 0) : (ListenerUtil.mutListener.listen(4989) ? (serverList.length > 0) : (ListenerUtil.mutListener.listen(4988) ? (serverList.length < 0) : (ListenerUtil.mutListener.listen(4987) ? (serverList.length != 0) : (serverList.length == 0))))))) : (serverList == null || (ListenerUtil.mutListener.listen(4991) ? (serverList.length >= 0) : (ListenerUtil.mutListener.listen(4990) ? (serverList.length <= 0) : (ListenerUtil.mutListener.listen(4989) ? (serverList.length > 0) : (ListenerUtil.mutListener.listen(4988) ? (serverList.length < 0) : (ListenerUtil.mutListener.listen(4987) ? (serverList.length != 0) : (serverList.length == 0)))))))) && forceRefresh) : ((ListenerUtil.mutListener.listen(4992) ? (serverList == null && (ListenerUtil.mutListener.listen(4991) ? (serverList.length >= 0) : (ListenerUtil.mutListener.listen(4990) ? (serverList.length <= 0) : (ListenerUtil.mutListener.listen(4989) ? (serverList.length > 0) : (ListenerUtil.mutListener.listen(4988) ? (serverList.length < 0) : (ListenerUtil.mutListener.listen(4987) ? (serverList.length != 0) : (serverList.length == 0))))))) : (serverList == null || (ListenerUtil.mutListener.listen(4991) ? (serverList.length >= 0) : (ListenerUtil.mutListener.listen(4990) ? (serverList.length <= 0) : (ListenerUtil.mutListener.listen(4989) ? (serverList.length > 0) : (ListenerUtil.mutListener.listen(4988) ? (serverList.length < 0) : (ListenerUtil.mutListener.listen(4987) ? (serverList.length != 0) : (serverList.length == 0)))))))) || forceRefresh))) {
                if (!ListenerUtil.mutListener.listen(4994)) {
                    serverList = doDnsServerListing();
                }
            }
        }
        return serverList;
    }

    /**
     * Blocking: return current selected server. Select one, if there is no current server.
     */
    public static String getCurrentServer() {
        if (!ListenerUtil.mutListener.listen(5005)) {
            if (currentServer == null) {
                String[] serverList = getServerList(false);
                if (!ListenerUtil.mutListener.listen(5004)) {
                    if ((ListenerUtil.mutListener.listen(5000) ? (serverList.length >= 0) : (ListenerUtil.mutListener.listen(4999) ? (serverList.length <= 0) : (ListenerUtil.mutListener.listen(4998) ? (serverList.length < 0) : (ListenerUtil.mutListener.listen(4997) ? (serverList.length != 0) : (ListenerUtil.mutListener.listen(4996) ? (serverList.length == 0) : (serverList.length > 0))))))) {
                        Random rand = new Random();
                        if (!ListenerUtil.mutListener.listen(5002)) {
                            currentServer = serverList[rand.nextInt(serverList.length)];
                        }
                        if (!ListenerUtil.mutListener.listen(5003)) {
                            Log.d("SRV", "Selected new default server: " + currentServer);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(5001)) {
                            Log.e("SRV", "no servers found");
                        }
                    }
                }
            }
        }
        return currentServer;
    }

    /**
     * Set new server as current
     */
    public static void setCurrentServer(String newServer) {
        if (!ListenerUtil.mutListener.listen(5006)) {
            currentServer = newServer;
        }
    }

    /**
     * Construct full url from server and path
     */
    public static String constructEndpoint(String server, String path) {
        return "https://" + server + "/" + path;
    }
}
