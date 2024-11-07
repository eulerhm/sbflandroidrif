package org.wordpress.android.ui.reader.services.update;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PersistableBundle;
import org.wordpress.android.util.AppLog;
import java.util.EnumSet;
import static org.wordpress.android.JobServiceId.JOB_READER_UPDATE_SERVICE_ID;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/*
 * this class provides a way to decide which kind of Service to start, depending on the platform we're running on
 */
public class ReaderUpdateServiceStarter {

    public static final String ARG_UPDATE_TASKS = "update_tasks";

    public static void startService(Context context, EnumSet<ReaderUpdateLogic.UpdateTask> tasks) {
        if (!ListenerUtil.mutListener.listen(19589)) {
            if ((ListenerUtil.mutListener.listen(19579) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(19578) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(19577) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(19576) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(19575) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.O))))))) {
                if (!ListenerUtil.mutListener.listen(19586)) {
                    if ((ListenerUtil.mutListener.listen(19585) ? ((ListenerUtil.mutListener.listen(19584) ? (context == null && tasks == null) : (context == null || tasks == null)) && tasks.size() == 0) : ((ListenerUtil.mutListener.listen(19584) ? (context == null && tasks == null) : (context == null || tasks == null)) || tasks.size() == 0))) {
                        return;
                    }
                }
                Intent intent = new Intent(context, ReaderUpdateService.class);
                if (!ListenerUtil.mutListener.listen(19587)) {
                    intent.putExtra(ARG_UPDATE_TASKS, tasks);
                }
                if (!ListenerUtil.mutListener.listen(19588)) {
                    context.startService(intent);
                }
            } else {
                // it's preferable to use it only since enforcement in API 26 to not break any old behavior
                ComponentName componentName = new ComponentName(context, ReaderUpdateJobService.class);
                PersistableBundle extras = new PersistableBundle();
                if (!ListenerUtil.mutListener.listen(19580)) {
                    extras.putIntArray(ARG_UPDATE_TASKS, getIntArrayFromEnumSet(tasks));
                }
                JobInfo jobInfo = new JobInfo.Builder(JOB_READER_UPDATE_SERVICE_ID, componentName).setRequiresCharging(false).setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY).setOverrideDeadline(// if possible, try to run right away
                0).setExtras(extras).build();
                JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
                int resultCode = jobScheduler.schedule(jobInfo);
                if (!ListenerUtil.mutListener.listen(19583)) {
                    if (resultCode == JobScheduler.RESULT_SUCCESS) {
                        if (!ListenerUtil.mutListener.listen(19582)) {
                            AppLog.i(AppLog.T.READER, "reader service > job scheduled");
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(19581)) {
                            AppLog.e(AppLog.T.READER, "reader service > job could not be scheduled");
                        }
                    }
                }
            }
        }
    }

    private static int[] getIntArrayFromEnumSet(EnumSet<ReaderUpdateLogic.UpdateTask> enumSet) {
        int[] ordinals2 = new int[enumSet.size()];
        int index = 0;
        if (!ListenerUtil.mutListener.listen(19591)) {
            {
                long _loopCounter312 = 0;
                for (ReaderUpdateLogic.UpdateTask e : enumSet) {
                    ListenerUtil.loopListener.listen("_loopCounter312", ++_loopCounter312);
                    if (!ListenerUtil.mutListener.listen(19590)) {
                        ordinals2[index++] = e.ordinal();
                    }
                }
            }
        }
        return ordinals2;
    }
}
