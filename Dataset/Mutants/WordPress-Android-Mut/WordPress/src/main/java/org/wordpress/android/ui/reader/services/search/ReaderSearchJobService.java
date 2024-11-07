package org.wordpress.android.ui.reader.services.search;

import android.app.job.JobParameters;
import android.app.job.JobService;
import org.wordpress.android.ui.reader.services.ServiceCompletionListener;
import org.wordpress.android.util.AppLog;
import static org.wordpress.android.ui.reader.services.search.ReaderSearchServiceStarter.ARG_OFFSET;
import static org.wordpress.android.ui.reader.services.search.ReaderSearchServiceStarter.ARG_QUERY;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderSearchJobService extends JobService implements ServiceCompletionListener {

    private ReaderSearchLogic mReaderSearchLogic;

    @Override
    public boolean onStartJob(JobParameters params) {
        if (!ListenerUtil.mutListener.listen(19410)) {
            if ((ListenerUtil.mutListener.listen(19408) ? (params.getExtras() != null || params.getExtras().containsKey(ARG_QUERY)) : (params.getExtras() != null && params.getExtras().containsKey(ARG_QUERY)))) {
                String query = params.getExtras().getString(ARG_QUERY);
                int offset = params.getExtras().getInt(ARG_OFFSET, 0);
                if (!ListenerUtil.mutListener.listen(19409)) {
                    mReaderSearchLogic.startSearch(query, offset, params);
                }
            }
        }
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        if (!ListenerUtil.mutListener.listen(19411)) {
            jobFinished(params, false);
        }
        return false;
    }

    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(19412)) {
            super.onCreate();
        }
        if (!ListenerUtil.mutListener.listen(19413)) {
            mReaderSearchLogic = new ReaderSearchLogic(this);
        }
        if (!ListenerUtil.mutListener.listen(19414)) {
            AppLog.i(AppLog.T.READER, "reader search job service > created");
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(19415)) {
            AppLog.i(AppLog.T.READER, "reader search job service > destroyed");
        }
        if (!ListenerUtil.mutListener.listen(19416)) {
            super.onDestroy();
        }
    }

    @Override
    public void onCompleted(Object companion) {
        if (!ListenerUtil.mutListener.listen(19417)) {
            AppLog.i(AppLog.T.READER, "reader search job service > all tasks completed");
        }
        if (!ListenerUtil.mutListener.listen(19418)) {
            jobFinished((JobParameters) companion, false);
        }
    }
}
