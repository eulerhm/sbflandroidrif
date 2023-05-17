package org.wordpress.android.util.analytics.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.analytics.service.InstallationReferrerServiceStarter;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class InstallationReferrerReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent receivedIntent) {
        if (!ListenerUtil.mutListener.listen(27096)) {
            AppLog.i(AppLog.T.UTILS, "installation referrer RECEIVER: received");
        }
        String referrer = receivedIntent.getStringExtra("referrer");
        if (!ListenerUtil.mutListener.listen(27097)) {
            InstallationReferrerServiceStarter.startService(context, referrer);
        }
    }
}
