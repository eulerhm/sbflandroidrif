package org.owntracks.android.ui.preferences.connection.dialog;

import android.content.Intent;
import org.owntracks.android.support.Preferences;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ConnectionHostHttpDialogViewModel extends BaseDialogViewModel {

    private String url;

    public ConnectionHostHttpDialogViewModel(Preferences preferences) {
        super(preferences);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    @Override
    public void load() {
        if (!ListenerUtil.mutListener.listen(1837)) {
            this.url = preferences.getUrl();
        }
    }

    @Override
    public void save() {
        if (!ListenerUtil.mutListener.listen(1838)) {
            Timber.v("saving url:%s", url);
        }
        if (!ListenerUtil.mutListener.listen(1839)) {
            preferences.setUrl(url);
        }
    }

    public String getUrlText() {
        return url;
    }

    public void setUrlText(String url) {
        if (!ListenerUtil.mutListener.listen(1840)) {
            this.url = url;
        }
    }
}
