package org.wordpress.android.ui.reader.services.update;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import org.wordpress.android.WordPress;
import org.wordpress.android.ui.reader.services.ServiceCompletionListener;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.LocaleManager;
import java.util.EnumSet;
import static org.wordpress.android.ui.reader.services.update.ReaderUpdateServiceStarter.ARG_UPDATE_TASKS;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderUpdateService extends Service implements ServiceCompletionListener {

    private ReaderUpdateLogic mReaderUpdateLogic;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        if (!ListenerUtil.mutListener.listen(19564)) {
            super.attachBaseContext(LocaleManager.setLocale(newBase));
        }
    }

    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(19565)) {
            super.onCreate();
        }
        if (!ListenerUtil.mutListener.listen(19566)) {
            mReaderUpdateLogic = new ReaderUpdateLogic(this, (WordPress) getApplication(), this);
        }
        if (!ListenerUtil.mutListener.listen(19567)) {
            AppLog.i(AppLog.T.READER, "reader service > created");
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(19568)) {
            AppLog.i(AppLog.T.READER, "reader service > destroyed");
        }
        if (!ListenerUtil.mutListener.listen(19569)) {
            super.onDestroy();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!ListenerUtil.mutListener.listen(19572)) {
            if ((ListenerUtil.mutListener.listen(19570) ? (intent != null || intent.hasExtra(ARG_UPDATE_TASKS)) : (intent != null && intent.hasExtra(ARG_UPDATE_TASKS)))) {
                // noinspection unchecked
                EnumSet<ReaderUpdateLogic.UpdateTask> tasks = (EnumSet<ReaderUpdateLogic.UpdateTask>) intent.getSerializableExtra(ARG_UPDATE_TASKS);
                if (!ListenerUtil.mutListener.listen(19571)) {
                    mReaderUpdateLogic.performTasks(tasks, null);
                }
            }
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onCompleted(Object companion) {
        if (!ListenerUtil.mutListener.listen(19573)) {
            AppLog.i(AppLog.T.READER, "reader service > all tasks completed");
        }
        if (!ListenerUtil.mutListener.listen(19574)) {
            stopSelf();
        }
    }
}
