package org.owntracks.android.ui.preferences.connection.dialog;

import android.content.Intent;
import org.owntracks.android.support.Preferences;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ConnectionHostMqttDialogViewModel extends BaseDialogViewModel {

    private String host;

    private boolean hostDirty;

    private String port;

    private boolean portDirty;

    private boolean ws;

    private boolean wsDirty;

    private String clientId;

    private boolean clientIdDirty;

    public ConnectionHostMqttDialogViewModel(Preferences preferences) {
        super(preferences);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    @Override
    public void load() {
        if (!ListenerUtil.mutListener.listen(1841)) {
            this.host = preferences.getHost();
        }
        if (!ListenerUtil.mutListener.listen(1842)) {
            this.port = preferences.getPortWithHintSupport();
        }
        if (!ListenerUtil.mutListener.listen(1843)) {
            this.ws = preferences.getWs();
        }
        if (!ListenerUtil.mutListener.listen(1844)) {
            this.clientId = preferences.getClientId();
        }
    }

    @Override
    public void save() {
        if (!ListenerUtil.mutListener.listen(1845)) {
            Timber.v("saving host:%s, port:%s, ws:%s, clientId:%s", host, port, ws, clientId);
        }
        if (!ListenerUtil.mutListener.listen(1847)) {
            if (hostDirty) {
                if (!ListenerUtil.mutListener.listen(1846)) {
                    preferences.setHost(host);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1850)) {
            if (portDirty) {
                try {
                    if (!ListenerUtil.mutListener.listen(1849)) {
                        preferences.setPort(Integer.parseInt(port));
                    }
                } catch (NumberFormatException e) {
                    if (!ListenerUtil.mutListener.listen(1848)) {
                        preferences.setPortDefault();
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1852)) {
            if (wsDirty) {
                if (!ListenerUtil.mutListener.listen(1851)) {
                    preferences.setWs(ws);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(1854)) {
            if (clientIdDirty) {
                if (!ListenerUtil.mutListener.listen(1853)) {
                    preferences.setClientId(clientId);
                }
            }
        }
    }

    public String getHostText() {
        return preferences.getHost();
    }

    public void setHostText(String host) {
        if (!ListenerUtil.mutListener.listen(1855)) {
            this.host = host;
        }
        if (!ListenerUtil.mutListener.listen(1856)) {
            this.hostDirty = true;
        }
    }

    public String getPortText() {
        return port;
    }

    public void setPortText(String port) {
        if (!ListenerUtil.mutListener.listen(1857)) {
            this.port = port;
        }
        if (!ListenerUtil.mutListener.listen(1858)) {
            this.portDirty = true;
        }
    }

    public boolean isWs() {
        return ws;
    }

    public void setWs(boolean ws) {
        if (!ListenerUtil.mutListener.listen(1859)) {
            this.ws = ws;
        }
        if (!ListenerUtil.mutListener.listen(1860)) {
            this.wsDirty = true;
        }
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        if (!ListenerUtil.mutListener.listen(1861)) {
            this.clientId = clientId;
        }
        if (!ListenerUtil.mutListener.listen(1862)) {
            this.clientIdDirty = true;
        }
    }
}
