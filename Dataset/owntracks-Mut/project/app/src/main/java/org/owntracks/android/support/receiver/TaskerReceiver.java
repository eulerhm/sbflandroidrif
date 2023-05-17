package org.owntracks.android.support.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public final class TaskerReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
        if (!ListenerUtil.mutListener.listen(1026)) {
            Timber.v("Receiver Fired");
        }
    }
}
