package com.ichi2.anki.dialogs;

import android.content.res.Resources;
import android.os.Message;
import com.ichi2.anki.AnkiDroidApp;
import com.ichi2.anki.analytics.AnalyticsDialogFragment;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public abstract class AsyncDialogFragment extends AnalyticsDialogFragment {

    public abstract String getNotificationMessage();

    public abstract String getNotificationTitle();

    public Message getDialogHandlerMessage() {
        return null;
    }

    protected Resources res() {
        try {
            return AnkiDroidApp.getAppResources();
        } catch (Exception e) {
            if (!ListenerUtil.mutListener.listen(339)) {
                Timber.w(e, "AnkiDroidApp.getAppResources failure. Returning Fragment resources as fallback.");
            }
            return getResources();
        }
    }
}
