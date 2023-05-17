package org.owntracks.android.ui.preferences.connection.dialog;

import android.content.Intent;
import org.owntracks.android.support.Preferences;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ConnectionParametersViewModel extends BaseDialogViewModel {

    private boolean cleanSession;

    private String keepaliveText;

    private boolean keepaliveTextDirty;

    public ConnectionParametersViewModel(Preferences preferences) {
        super(preferences);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    @Override
    public void load() {
        if (!ListenerUtil.mutListener.listen(1886)) {
            this.cleanSession = preferences.getCleanSession();
        }
        if (!ListenerUtil.mutListener.listen(1887)) {
            this.keepaliveText = preferences.getKeepaliveWithHintSupport();
        }
    }

    @Override
    public void save() {
        if (!ListenerUtil.mutListener.listen(1888)) {
            preferences.setCleanSession(cleanSession);
        }
        if (!ListenerUtil.mutListener.listen(1891)) {
            if (keepaliveTextDirty) {
                try {
                    if (!ListenerUtil.mutListener.listen(1890)) {
                        preferences.setKeepalive(Integer.parseInt(keepaliveText));
                    }
                } catch (NumberFormatException e) {
                    if (!ListenerUtil.mutListener.listen(1889)) {
                        preferences.setKeepaliveDefault();
                    }
                }
            }
        }
    }

    public boolean isCleanSession() {
        return cleanSession;
    }

    public void setCleanSession(boolean cleanSession) {
        if (!ListenerUtil.mutListener.listen(1892)) {
            this.cleanSession = cleanSession;
        }
    }

    public String getKeepaliveText() {
        return keepaliveText;
    }

    public void setKeepaliveText(String keepaliveText) {
        if (!ListenerUtil.mutListener.listen(1893)) {
            this.keepaliveText = keepaliveText;
        }
        if (!ListenerUtil.mutListener.listen(1894)) {
            this.keepaliveTextDirty = true;
        }
    }
}
