package org.wordpress.android.util.analytics.service;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PersistableBundle;
import org.wordpress.android.util.AppLog;
import static org.wordpress.android.JobServiceId.JOB_INSTALL_REFERRER_SERVICE_ID;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class InstallationReferrerServiceStarter {

    public static final String ARG_REFERRER = "arg_referrer";

    public static void startService(Context context, String referrer) {
        if (!ListenerUtil.mutListener.listen(27148)) {
            if (context == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(27162)) {
            if ((ListenerUtil.mutListener.listen(27153) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(27152) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(27151) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(27150) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(27149) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.O))))))) {
                Intent intent = new Intent(context, InstallationReferrerService.class);
                if (!ListenerUtil.mutListener.listen(27160)) {
                    if (referrer != null) {
                        if (!ListenerUtil.mutListener.listen(27159)) {
                            intent.putExtra(ARG_REFERRER, referrer);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(27161)) {
                    context.startService(intent);
                }
            } else {
                // let's stick to that version as well here.
                ComponentName componentName = new ComponentName(context, InstallationReferrerJobService.class);
                PersistableBundle extras = new PersistableBundle();
                if (!ListenerUtil.mutListener.listen(27155)) {
                    if (referrer != null) {
                        if (!ListenerUtil.mutListener.listen(27154)) {
                            extras.putString(ARG_REFERRER, referrer);
                        }
                    }
                }
                JobInfo jobInfo = new JobInfo.Builder(JOB_INSTALL_REFERRER_SERVICE_ID, componentName).setRequiresCharging(false).setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY).setOverrideDeadline(// if possible, try to run right away
                0).setExtras(extras).build();
                JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
                int resultCode = jobScheduler.schedule(jobInfo);
                if (!ListenerUtil.mutListener.listen(27158)) {
                    if (resultCode == JobScheduler.RESULT_SUCCESS) {
                        if (!ListenerUtil.mutListener.listen(27157)) {
                            AppLog.i(AppLog.T.UTILS, "installation referrer job service > job scheduled");
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(27156)) {
                            AppLog.e(AppLog.T.UTILS, "installation referrer job service > job could not be scheduled");
                        }
                    }
                }
            }
        }
    }
}
