package org.wordpress.android.util.analytics.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Background service to connect to Google Play Store's Install Referrer API to
 * securely retrieve referral content from Google Play.
 * This could be done on the app's main activity but, as we are going to trigger this data gathering from
 * a BroadcastReceiver, we need a Service / JobService to keep it alive while this happens, even if it needs
 * to happen only once.
 * see https://developer.android.com/google/play/installreferrer/library
 * https://developer.android.com/reference/android/content/Intent.html#ACTION_PACKAGE_FIRST_LAUNCH
 * https://developer.android.com/guide/components/broadcasts.html#receiving_broadcasts
 * https://developer.android.com/guide/components/broadcasts#effects-on-process-state
 */
public class InstallationReferrerService extends Service implements InstallationReferrerServiceLogic.ServiceCompletionListener {

    private InstallationReferrerServiceLogic mInstallationReferrerServiceLogic;

    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(27104)) {
            super.onCreate();
        }
        if (!ListenerUtil.mutListener.listen(27105)) {
            AppLog.i(T.UTILS, "installation referrer service created");
        }
        if (!ListenerUtil.mutListener.listen(27106)) {
            mInstallationReferrerServiceLogic = new InstallationReferrerServiceLogic(this, this);
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(27107)) {
            AppLog.i(T.UTILS, "installation referrer service destroyed");
        }
        if (!ListenerUtil.mutListener.listen(27108)) {
            mInstallationReferrerServiceLogic.onDestroy();
        }
        if (!ListenerUtil.mutListener.listen(27109)) {
            super.onDestroy();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!ListenerUtil.mutListener.listen(27110)) {
            AppLog.i(T.UTILS, "installation referrer service > task: " + startId + " started");
        }
        if (!ListenerUtil.mutListener.listen(27111)) {
            mInstallationReferrerServiceLogic.performTask(intent.getExtras(), startId);
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onCompleted(Object companion) {
        if (!ListenerUtil.mutListener.listen(27116)) {
            if (companion instanceof Integer) {
                if (!ListenerUtil.mutListener.listen(27114)) {
                    AppLog.i(T.UTILS, "installation referrer service > task: " + companion + " completed");
                }
                if (!ListenerUtil.mutListener.listen(27115)) {
                    stopSelf((Integer) companion);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(27112)) {
                    AppLog.i(T.UTILS, "installation referrer service > task: <not identified> completed");
                }
                if (!ListenerUtil.mutListener.listen(27113)) {
                    stopSelf();
                }
            }
        }
    }
}
