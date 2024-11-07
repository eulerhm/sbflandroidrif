package org.wordpress.android.ui.reader.services.post;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import org.greenrobot.eventbus.EventBus;
import org.wordpress.android.models.ReaderTag;
import org.wordpress.android.ui.reader.ReaderEvents;
import org.wordpress.android.ui.reader.services.ServiceCompletionListener;
import org.wordpress.android.util.AppLog;
import static org.wordpress.android.ui.reader.services.post.ReaderPostServiceStarter.ARG_ACTION;
import static org.wordpress.android.ui.reader.services.post.ReaderPostServiceStarter.ARG_BLOG_ID;
import static org.wordpress.android.ui.reader.services.post.ReaderPostServiceStarter.ARG_FEED_ID;
import static org.wordpress.android.ui.reader.services.post.ReaderPostServiceStarter.ARG_TAG;
import static org.wordpress.android.ui.reader.services.post.ReaderPostServiceStarter.UpdateAction;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderPostService extends Service implements ServiceCompletionListener {

    private ReaderPostLogic mReaderPostLogic;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(19350)) {
            super.onCreate();
        }
        if (!ListenerUtil.mutListener.listen(19351)) {
            mReaderPostLogic = new ReaderPostLogic(this);
        }
        if (!ListenerUtil.mutListener.listen(19352)) {
            AppLog.i(AppLog.T.READER, "reader post service > created");
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(19353)) {
            AppLog.i(AppLog.T.READER, "reader post service > destroyed");
        }
        if (!ListenerUtil.mutListener.listen(19354)) {
            super.onDestroy();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!ListenerUtil.mutListener.listen(19355)) {
            if (intent == null) {
                return START_NOT_STICKY;
            }
        }
        UpdateAction action;
        if (intent.hasExtra(ARG_ACTION)) {
            action = (UpdateAction) intent.getSerializableExtra(ARG_ACTION);
        } else {
            action = UpdateAction.REQUEST_NEWER;
        }
        if (!ListenerUtil.mutListener.listen(19362)) {
            if (intent.hasExtra(ARG_TAG)) {
                ReaderTag tag = (ReaderTag) intent.getSerializableExtra(ARG_TAG);
                if (!ListenerUtil.mutListener.listen(19360)) {
                    EventBus.getDefault().post(new ReaderEvents.UpdatePostsStarted(action, tag));
                }
                if (!ListenerUtil.mutListener.listen(19361)) {
                    mReaderPostLogic.performTask(null, action, tag, -1, -1);
                }
            } else if (intent.hasExtra(ARG_BLOG_ID)) {
                long blogId = intent.getLongExtra(ARG_BLOG_ID, 0);
                if (!ListenerUtil.mutListener.listen(19358)) {
                    EventBus.getDefault().post(new ReaderEvents.UpdatePostsStarted(action));
                }
                if (!ListenerUtil.mutListener.listen(19359)) {
                    mReaderPostLogic.performTask(null, action, null, blogId, -1);
                }
            } else if (intent.hasExtra(ARG_FEED_ID)) {
                long feedId = intent.getLongExtra(ARG_FEED_ID, 0);
                if (!ListenerUtil.mutListener.listen(19356)) {
                    EventBus.getDefault().post(new ReaderEvents.UpdatePostsStarted(action));
                }
                if (!ListenerUtil.mutListener.listen(19357)) {
                    mReaderPostLogic.performTask(null, action, null, -1, feedId);
                }
            }
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onCompleted(Object companion) {
        if (!ListenerUtil.mutListener.listen(19363)) {
            stopSelf();
        }
    }
}
