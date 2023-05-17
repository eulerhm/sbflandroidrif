package org.wordpress.android.ui.notifications.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import org.wordpress.android.ui.notifications.NotificationsListFragment;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.LocaleManager;
import static org.wordpress.android.ui.notifications.services.NotificationsUpdateServiceStarter.IS_TAPPED_ON_NOTIFICATION;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NotificationsUpdateService extends Service implements NotificationsUpdateLogic.ServiceCompletionListener {

    private NotificationsUpdateLogic mNotificationsUpdateLogic;

    @Override
    protected void attachBaseContext(Context newBase) {
        if (!ListenerUtil.mutListener.listen(8788)) {
            super.attachBaseContext(LocaleManager.setLocale(newBase));
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(8789)) {
            super.onCreate();
        }
        if (!ListenerUtil.mutListener.listen(8790)) {
            AppLog.i(AppLog.T.NOTIFS, "notifications update service > created");
        }
        if (!ListenerUtil.mutListener.listen(8791)) {
            mNotificationsUpdateLogic = new NotificationsUpdateLogic(LocaleManager.getLanguage(this), this);
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(8792)) {
            AppLog.i(AppLog.T.NOTIFS, "notifications update service > destroyed");
        }
        if (!ListenerUtil.mutListener.listen(8793)) {
            super.onDestroy();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!ListenerUtil.mutListener.listen(8795)) {
            if (intent != null) {
                String noteId = intent.getStringExtra(NotificationsListFragment.NOTE_ID_EXTRA);
                boolean isStartedByTappingOnNotification = intent.getBooleanExtra(IS_TAPPED_ON_NOTIFICATION, false);
                if (!ListenerUtil.mutListener.listen(8794)) {
                    mNotificationsUpdateLogic.performRefresh(noteId, isStartedByTappingOnNotification, null);
                }
            }
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onCompleted(Object companion) {
        if (!ListenerUtil.mutListener.listen(8796)) {
            AppLog.i(AppLog.T.NOTIFS, "notifications update service > all tasks completed");
        }
        if (!ListenerUtil.mutListener.listen(8797)) {
            stopSelf();
        }
    }
}
