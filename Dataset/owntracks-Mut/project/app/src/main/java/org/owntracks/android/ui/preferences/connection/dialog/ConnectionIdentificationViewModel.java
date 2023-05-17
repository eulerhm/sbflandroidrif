package org.owntracks.android.ui.preferences.connection.dialog;

import android.content.Intent;
import org.owntracks.android.support.Preferences;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ConnectionIdentificationViewModel extends BaseDialogViewModel {

    private String username;

    private boolean usernameDirty;

    private String password;

    private boolean passwordDirty;

    private String deviceId;

    private boolean deviceIdDirty;

    private String trackerId;

    private boolean trackerIdDirty;

    public ConnectionIdentificationViewModel(Preferences preferences) {
        super(preferences);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    @Override
    public void load() {
        if (!ListenerUtil.mutListener.listen(1863)) {
            this.username = preferences.getUsername();
        }
        if (!ListenerUtil.mutListener.listen(1864)) {
            this.password = preferences.getPassword();
        }
        if (!ListenerUtil.mutListener.listen(1865)) {
            this.deviceId = preferences.getDeviceId(false);
        }
        if (!ListenerUtil.mutListener.listen(1866)) {
            this.trackerId = preferences.getTrackerId(false);
        }
    }

    @Override
    public void save() {
        if (!ListenerUtil.mutListener.listen(1868)) {
            if (usernameDirty)
                if (!ListenerUtil.mutListener.listen(1867)) {
                    preferences.setUsername(username);
                }
        }
        if (!ListenerUtil.mutListener.listen(1870)) {
            if (passwordDirty)
                if (!ListenerUtil.mutListener.listen(1869)) {
                    preferences.setPassword(password);
                }
        }
        if (!ListenerUtil.mutListener.listen(1872)) {
            if (deviceIdDirty)
                if (!ListenerUtil.mutListener.listen(1871)) {
                    preferences.setDeviceId(deviceId);
                }
        }
        if (!ListenerUtil.mutListener.listen(1874)) {
            if (trackerIdDirty)
                if (!ListenerUtil.mutListener.listen(1873)) {
                    preferences.setTrackerId(trackerId);
                }
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (!ListenerUtil.mutListener.listen(1875)) {
            this.username = username;
        }
        if (!ListenerUtil.mutListener.listen(1876)) {
            this.usernameDirty = true;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (!ListenerUtil.mutListener.listen(1877)) {
            this.password = password;
        }
        if (!ListenerUtil.mutListener.listen(1878)) {
            this.passwordDirty = true;
        }
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        if (!ListenerUtil.mutListener.listen(1879)) {
            this.deviceId = deviceId;
        }
        if (!ListenerUtil.mutListener.listen(1880)) {
            this.deviceIdDirty = true;
        }
    }

    public String getTrackerId() {
        return trackerId;
    }

    public void setTrackerId(String trackerId) {
        if (!ListenerUtil.mutListener.listen(1881)) {
            this.trackerId = trackerId;
        }
        if (!ListenerUtil.mutListener.listen(1882)) {
            this.trackerIdDirty = true;
        }
    }
}
