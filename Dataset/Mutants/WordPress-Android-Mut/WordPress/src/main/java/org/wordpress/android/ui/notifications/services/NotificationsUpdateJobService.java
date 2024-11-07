package org.wordpress.android.ui.notifications.services;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import org.wordpress.android.ui.notifications.NotificationsListFragment;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.LocaleManager;
import static org.wordpress.android.ui.notifications.services.NotificationsUpdateServiceStarter.IS_TAPPED_ON_NOTIFICATION;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NotificationsUpdateJobService extends JobService implements NotificationsUpdateLogic.ServiceCompletionListener {

    private NotificationsUpdateLogic mNotificationsUpdateLogic;

    @Override
    protected void attachBaseContext(Context newBase) {
        if (!ListenerUtil.mutListener.listen(8731)) {
            super.attachBaseContext(LocaleManager.setLocale(newBase));
        }
    }

    @TargetApi(22)
    @Override
    public boolean onStartJob(JobParameters params) {
        String noteId = null;
        boolean isStartedByTappingOnNotification = false;
        if (!ListenerUtil.mutListener.listen(8735)) {
            if ((ListenerUtil.mutListener.listen(8732) ? (params.getExtras() != null || params.getExtras().containsKey(NotificationsListFragment.NOTE_ID_EXTRA)) : (params.getExtras() != null && params.getExtras().containsKey(NotificationsListFragment.NOTE_ID_EXTRA)))) {
                if (!ListenerUtil.mutListener.listen(8733)) {
                    noteId = params.getExtras().getString(NotificationsListFragment.NOTE_ID_EXTRA);
                }
                if (!ListenerUtil.mutListener.listen(8734)) {
                    isStartedByTappingOnNotification = params.getExtras().getBoolean(IS_TAPPED_ON_NOTIFICATION, false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(8736)) {
            mNotificationsUpdateLogic.performRefresh(noteId, isStartedByTappingOnNotification, params);
        }
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        if (!ListenerUtil.mutListener.listen(8737)) {
            jobFinished(params, false);
        }
        return false;
    }

    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(8738)) {
            super.onCreate();
        }
        if (!ListenerUtil.mutListener.listen(8739)) {
            AppLog.i(AppLog.T.NOTIFS, "notifications update job service > created");
        }
        if (!ListenerUtil.mutListener.listen(8740)) {
            mNotificationsUpdateLogic = new NotificationsUpdateLogic(LocaleManager.getLanguage(this), this);
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(8741)) {
            AppLog.i(AppLog.T.NOTIFS, "notifications update job service > destroyed");
        }
        if (!ListenerUtil.mutListener.listen(8742)) {
            super.onDestroy();
        }
    }

    @Override
    public void onCompleted(Object companion) {
        if (!ListenerUtil.mutListener.listen(8743)) {
            AppLog.i(AppLog.T.NOTIFS, "notifications update job service > all tasks completed");
        }
        if (!ListenerUtil.mutListener.listen(8744)) {
            jobFinished((JobParameters) companion, false);
        }
    }
}
