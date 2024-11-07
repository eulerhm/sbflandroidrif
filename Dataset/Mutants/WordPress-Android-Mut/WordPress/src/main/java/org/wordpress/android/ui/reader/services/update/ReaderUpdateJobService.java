package org.wordpress.android.ui.reader.services.update;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import org.wordpress.android.WordPress;
import org.wordpress.android.ui.reader.services.ServiceCompletionListener;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.LocaleManager;
import java.util.EnumSet;
import static org.wordpress.android.ui.reader.services.update.ReaderUpdateServiceStarter.ARG_UPDATE_TASKS;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderUpdateJobService extends JobService implements ServiceCompletionListener {

    private ReaderUpdateLogic mReaderUpdateLogic;

    @Override
    protected void attachBaseContext(Context newBase) {
        if (!ListenerUtil.mutListener.listen(19457)) {
            super.attachBaseContext(LocaleManager.setLocale(newBase));
        }
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        if (!ListenerUtil.mutListener.listen(19458)) {
            AppLog.i(AppLog.T.READER, "reader job service > started");
        }
        if (!ListenerUtil.mutListener.listen(19463)) {
            if ((ListenerUtil.mutListener.listen(19459) ? (params.getExtras() != null || params.getExtras().containsKey(ARG_UPDATE_TASKS)) : (params.getExtras() != null && params.getExtras().containsKey(ARG_UPDATE_TASKS)))) {
                int[] tmp = (int[]) params.getExtras().get(ARG_UPDATE_TASKS);
                EnumSet<ReaderUpdateLogic.UpdateTask> tasks = EnumSet.noneOf(ReaderUpdateLogic.UpdateTask.class);
                if (!ListenerUtil.mutListener.listen(19461)) {
                    {
                        long _loopCounter308 = 0;
                        for (int i : tmp) {
                            ListenerUtil.loopListener.listen("_loopCounter308", ++_loopCounter308);
                            if (!ListenerUtil.mutListener.listen(19460)) {
                                tasks.add(ReaderUpdateLogic.UpdateTask.values()[i]);
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(19462)) {
                    mReaderUpdateLogic.performTasks(tasks, params);
                }
            }
        }
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        if (!ListenerUtil.mutListener.listen(19464)) {
            AppLog.i(AppLog.T.READER, "reader job service > stopped");
        }
        if (!ListenerUtil.mutListener.listen(19465)) {
            jobFinished(params, false);
        }
        return false;
    }

    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(19466)) {
            super.onCreate();
        }
        if (!ListenerUtil.mutListener.listen(19467)) {
            mReaderUpdateLogic = new ReaderUpdateLogic(this, (WordPress) getApplication(), this);
        }
        if (!ListenerUtil.mutListener.listen(19468)) {
            AppLog.i(AppLog.T.READER, "reader job service > created");
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(19469)) {
            AppLog.i(AppLog.T.READER, "reader job service > destroyed");
        }
        if (!ListenerUtil.mutListener.listen(19470)) {
            super.onDestroy();
        }
    }

    @Override
    public void onCompleted(Object companion) {
        if (!ListenerUtil.mutListener.listen(19471)) {
            AppLog.i(AppLog.T.READER, "reader job service > all tasks completed");
        }
        if (!ListenerUtil.mutListener.listen(19472)) {
            jobFinished((JobParameters) companion, false);
        }
    }
}
