package org.wordpress.android.ui.reader.services.search;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PersistableBundle;
import androidx.annotation.NonNull;
import org.wordpress.android.util.AppLog;
import static org.wordpress.android.JobServiceId.JOB_READER_SEARCH_SERVICE_ID;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderSearchServiceStarter {

    public static final String ARG_QUERY = "query";

    public static final String ARG_OFFSET = "offset";

    public static void startService(Context context, @NonNull String query, int offset) {
        if (!ListenerUtil.mutListener.listen(19456)) {
            if ((ListenerUtil.mutListener.listen(19447) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(19446) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(19445) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(19444) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(19443) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.O))))))) {
                Intent intent = new Intent(context, ReaderSearchService.class);
                if (!ListenerUtil.mutListener.listen(19453)) {
                    intent.putExtra(ARG_QUERY, query);
                }
                if (!ListenerUtil.mutListener.listen(19454)) {
                    intent.putExtra(ARG_OFFSET, offset);
                }
                if (!ListenerUtil.mutListener.listen(19455)) {
                    context.startService(intent);
                }
            } else {
                // it's preferable to use it only since enforcement in API 26 to not break any old behavior
                ComponentName componentName = new ComponentName(context, ReaderSearchJobService.class);
                PersistableBundle extras = new PersistableBundle();
                if (!ListenerUtil.mutListener.listen(19448)) {
                    extras.putString(ARG_QUERY, query);
                }
                if (!ListenerUtil.mutListener.listen(19449)) {
                    extras.putInt(ARG_OFFSET, offset);
                }
                JobInfo jobInfo = new JobInfo.Builder(JOB_READER_SEARCH_SERVICE_ID, componentName).setRequiresCharging(false).setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY).setOverrideDeadline(// if possible, try to run right away
                0).setExtras(extras).build();
                JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
                int resultCode = jobScheduler.schedule(jobInfo);
                if (!ListenerUtil.mutListener.listen(19452)) {
                    if (resultCode == JobScheduler.RESULT_SUCCESS) {
                        if (!ListenerUtil.mutListener.listen(19451)) {
                            AppLog.i(AppLog.T.READER, "reader search job service > job scheduled");
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(19450)) {
                            AppLog.e(AppLog.T.READER, "reader search job service > job could not be scheduled");
                        }
                    }
                }
            }
        }
    }
}
