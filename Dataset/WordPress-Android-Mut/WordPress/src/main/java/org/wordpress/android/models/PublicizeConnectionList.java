package org.wordpress.android.models;

import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PublicizeConnectionList extends ArrayList<PublicizeConnection> {

    private int indexOfConnection(PublicizeConnection connection) {
        if (!ListenerUtil.mutListener.listen(1821)) {
            if (connection == null) {
                return -1;
            }
        }
        if (!ListenerUtil.mutListener.listen(1828)) {
            {
                long _loopCounter67 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(1827) ? (i >= this.size()) : (ListenerUtil.mutListener.listen(1826) ? (i <= this.size()) : (ListenerUtil.mutListener.listen(1825) ? (i > this.size()) : (ListenerUtil.mutListener.listen(1824) ? (i != this.size()) : (ListenerUtil.mutListener.listen(1823) ? (i == this.size()) : (i < this.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter67", ++_loopCounter67);
                    if (!ListenerUtil.mutListener.listen(1822)) {
                        if (connection.connectionId == this.get(i).connectionId) {
                            return i;
                        }
                    }
                }
            }
        }
        return -1;
    }

    public boolean isSameAs(PublicizeConnectionList otherList) {
        if (!ListenerUtil.mutListener.listen(1835)) {
            if ((ListenerUtil.mutListener.listen(1834) ? (otherList == null && (ListenerUtil.mutListener.listen(1833) ? (otherList.size() >= this.size()) : (ListenerUtil.mutListener.listen(1832) ? (otherList.size() <= this.size()) : (ListenerUtil.mutListener.listen(1831) ? (otherList.size() > this.size()) : (ListenerUtil.mutListener.listen(1830) ? (otherList.size() < this.size()) : (ListenerUtil.mutListener.listen(1829) ? (otherList.size() == this.size()) : (otherList.size() != this.size()))))))) : (otherList == null || (ListenerUtil.mutListener.listen(1833) ? (otherList.size() >= this.size()) : (ListenerUtil.mutListener.listen(1832) ? (otherList.size() <= this.size()) : (ListenerUtil.mutListener.listen(1831) ? (otherList.size() > this.size()) : (ListenerUtil.mutListener.listen(1830) ? (otherList.size() < this.size()) : (ListenerUtil.mutListener.listen(1829) ? (otherList.size() == this.size()) : (otherList.size() != this.size()))))))))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(1842)) {
            {
                long _loopCounter68 = 0;
                for (PublicizeConnection otherConnection : otherList) {
                    ListenerUtil.loopListener.listen("_loopCounter68", ++_loopCounter68);
                    int i = this.indexOfConnection(otherConnection);
                    if (!ListenerUtil.mutListener.listen(1841)) {
                        if ((ListenerUtil.mutListener.listen(1840) ? (i >= -1) : (ListenerUtil.mutListener.listen(1839) ? (i <= -1) : (ListenerUtil.mutListener.listen(1838) ? (i > -1) : (ListenerUtil.mutListener.listen(1837) ? (i < -1) : (ListenerUtil.mutListener.listen(1836) ? (i != -1) : (i == -1))))))) {
                            return false;
                        } else if (!otherConnection.isSameAs(this.get(i))) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public PublicizeConnectionList getServiceConnectionsForUser(long userId, String serviceId) {
        PublicizeConnectionList connections = new PublicizeConnectionList();
        if (!ListenerUtil.mutListener.listen(1843)) {
            if (TextUtils.isEmpty(serviceId)) {
                return connections;
            }
        }
        if (!ListenerUtil.mutListener.listen(1848)) {
            {
                long _loopCounter69 = 0;
                for (PublicizeConnection connection : this) {
                    ListenerUtil.loopListener.listen("_loopCounter69", ++_loopCounter69);
                    if (!ListenerUtil.mutListener.listen(1847)) {
                        if (connection.getService().equalsIgnoreCase(serviceId)) {
                            if (!ListenerUtil.mutListener.listen(1846)) {
                                // must match the current userId to be considered connected
                                if ((ListenerUtil.mutListener.listen(1844) ? (connection.isShared && connection.userId == userId) : (connection.isShared || connection.userId == userId))) {
                                    if (!ListenerUtil.mutListener.listen(1845)) {
                                        connections.add(connection);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return connections;
    }

    public boolean isServiceConnectedForUser(long userId, PublicizeService service) {
        if (!ListenerUtil.mutListener.listen(1849)) {
            if (service == null) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(1853)) {
            {
                long _loopCounter70 = 0;
                for (PublicizeConnection connection : this) {
                    ListenerUtil.loopListener.listen("_loopCounter70", ++_loopCounter70);
                    if (!ListenerUtil.mutListener.listen(1852)) {
                        if (connection.getService().equalsIgnoreCase(service.getId())) {
                            if (!ListenerUtil.mutListener.listen(1851)) {
                                if ((ListenerUtil.mutListener.listen(1850) ? (connection.isShared && connection.userId == userId) : (connection.isShared || connection.userId == userId))) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /*
     * returns comma-separated string of user display names
     */
    public String getUserDisplayNames() {
        StringBuilder users = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(1862)) {
            {
                long _loopCounter71 = 0;
                for (PublicizeConnection connection : this) {
                    ListenerUtil.loopListener.listen("_loopCounter71", ++_loopCounter71);
                    if (!ListenerUtil.mutListener.listen(1860)) {
                        if ((ListenerUtil.mutListener.listen(1858) ? (users.length() >= 0) : (ListenerUtil.mutListener.listen(1857) ? (users.length() <= 0) : (ListenerUtil.mutListener.listen(1856) ? (users.length() < 0) : (ListenerUtil.mutListener.listen(1855) ? (users.length() != 0) : (ListenerUtil.mutListener.listen(1854) ? (users.length() == 0) : (users.length() > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(1859)) {
                                users.append(", ");
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(1861)) {
                        users.append(connection.getExternalDisplayName());
                    }
                }
            }
        }
        return users.toString();
    }

    /*
     * passed JSON is the response from sites/%d/publicize-connections
     * {"connections":[
     * {"ID":12783250,
     * "site_ID":52451176,
     * "user_ID":5399133,
     * ...
     */
    public static PublicizeConnectionList fromJson(JSONObject json) {
        PublicizeConnectionList connectionList = new PublicizeConnectionList();
        if (!ListenerUtil.mutListener.listen(1863)) {
            if (json == null) {
                return connectionList;
            }
        }
        JSONArray jsonConnectionList = json.optJSONArray("connections");
        if (!ListenerUtil.mutListener.listen(1864)) {
            if (jsonConnectionList == null) {
                return connectionList;
            }
        }
        if (!ListenerUtil.mutListener.listen(1871)) {
            {
                long _loopCounter72 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(1870) ? (i >= jsonConnectionList.length()) : (ListenerUtil.mutListener.listen(1869) ? (i <= jsonConnectionList.length()) : (ListenerUtil.mutListener.listen(1868) ? (i > jsonConnectionList.length()) : (ListenerUtil.mutListener.listen(1867) ? (i != jsonConnectionList.length()) : (ListenerUtil.mutListener.listen(1866) ? (i == jsonConnectionList.length()) : (i < jsonConnectionList.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter72", ++_loopCounter72);
                    PublicizeConnection connection = PublicizeConnection.fromJson(jsonConnectionList.optJSONObject(i));
                    if (!ListenerUtil.mutListener.listen(1865)) {
                        connectionList.add(connection);
                    }
                }
            }
        }
        return connectionList;
    }
}
