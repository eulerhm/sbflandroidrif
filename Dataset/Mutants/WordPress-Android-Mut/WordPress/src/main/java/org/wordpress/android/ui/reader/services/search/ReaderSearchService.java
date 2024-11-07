package org.wordpress.android.ui.reader.services.search;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import org.wordpress.android.ui.reader.services.ServiceCompletionListener;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.StringUtils;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderSearchService extends Service implements ServiceCompletionListener {

    private static final String ARG_QUERY = "query";

    private static final String ARG_OFFSET = "offset";

    private ReaderSearchLogic mReaderSearchLogic;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(19434)) {
            super.onCreate();
        }
        if (!ListenerUtil.mutListener.listen(19435)) {
            mReaderSearchLogic = new ReaderSearchLogic(this);
        }
        if (!ListenerUtil.mutListener.listen(19436)) {
            AppLog.i(AppLog.T.READER, "reader search service > created");
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(19437)) {
            AppLog.i(AppLog.T.READER, "reader search service > destroyed");
        }
        if (!ListenerUtil.mutListener.listen(19438)) {
            super.onDestroy();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!ListenerUtil.mutListener.listen(19439)) {
            if (intent == null) {
                return START_NOT_STICKY;
            }
        }
        String query = StringUtils.notNullStr(intent.getStringExtra(ARG_QUERY));
        int offset = intent.getIntExtra(ARG_OFFSET, 0);
        if (!ListenerUtil.mutListener.listen(19440)) {
            mReaderSearchLogic.startSearch(query, offset, null);
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onCompleted(Object companion) {
        if (!ListenerUtil.mutListener.listen(19441)) {
            AppLog.i(AppLog.T.READER, "reader search service > all tasks completed");
        }
        if (!ListenerUtil.mutListener.listen(19442)) {
            stopSelf();
        }
    }
}
