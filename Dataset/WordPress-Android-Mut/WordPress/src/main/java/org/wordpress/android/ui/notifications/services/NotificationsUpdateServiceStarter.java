package org.wordpress.android.ui.notifications.services;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PersistableBundle;
import org.wordpress.android.ui.notifications.NotificationsListFragment;
import org.wordpress.android.util.AppLog;
import static org.wordpress.android.JobServiceId.JOB_NOTIFICATIONS_UPDATE_SERVICE_ID;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class NotificationsUpdateServiceStarter {

    public static final String IS_TAPPED_ON_NOTIFICATION = "is-tapped-on-notification";

    public static void startService(Context context) {
        if (!ListenerUtil.mutListener.listen(8798)) {
            if (context == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(8799)) {
            startService(context, null);
        }
    }

    public static void startService(Context context, String noteId) {
        if (!ListenerUtil.mutListener.listen(8800)) {
            if (context == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(8816)) {
            if ((ListenerUtil.mutListener.listen(8805) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(8804) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(8803) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(8802) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(8801) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.O))))))) {
                Intent intent = new Intent(context, NotificationsUpdateService.class);
                if (!ListenerUtil.mutListener.listen(8814)) {
                    if (noteId != null) {
                        if (!ListenerUtil.mutListener.listen(8812)) {
                            intent.putExtra(NotificationsListFragment.NOTE_ID_EXTRA, noteId);
                        }
                        if (!ListenerUtil.mutListener.listen(8813)) {
                            intent.putExtra(IS_TAPPED_ON_NOTIFICATION, true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(8815)) {
                    context.startService(intent);
                }
            } else {
                // it's preferable to use it only since enforcement in API 26 to not break any old behavior
                ComponentName componentName = new ComponentName(context, NotificationsUpdateJobService.class);
                PersistableBundle extras = new PersistableBundle();
                if (!ListenerUtil.mutListener.listen(8808)) {
                    if (noteId != null) {
                        if (!ListenerUtil.mutListener.listen(8806)) {
                            extras.putString(NotificationsListFragment.NOTE_ID_EXTRA, noteId);
                        }
                        if (!ListenerUtil.mutListener.listen(8807)) {
                            extras.putBoolean(IS_TAPPED_ON_NOTIFICATION, true);
                        }
                    }
                }
                JobInfo jobInfo = new JobInfo.Builder(JOB_NOTIFICATIONS_UPDATE_SERVICE_ID, componentName).setRequiresCharging(false).setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY).setOverrideDeadline(// if possible, try to run right away
                0).setExtras(extras).build();
                JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
                int resultCode = jobScheduler.schedule(jobInfo);
                if (!ListenerUtil.mutListener.listen(8811)) {
                    if (resultCode == JobScheduler.RESULT_SUCCESS) {
                        if (!ListenerUtil.mutListener.listen(8810)) {
                            AppLog.i(AppLog.T.READER, "notifications update job service > job scheduled");
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(8809)) {
                            AppLog.e(AppLog.T.READER, "notifications update job service > job could not be scheduled");
                        }
                    }
                }
            }
        }
    }
}
