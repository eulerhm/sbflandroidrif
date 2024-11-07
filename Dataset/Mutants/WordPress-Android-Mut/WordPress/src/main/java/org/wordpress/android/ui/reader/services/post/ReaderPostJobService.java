package org.wordpress.android.ui.reader.services.post;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.PersistableBundle;
import org.greenrobot.eventbus.EventBus;
import org.wordpress.android.models.ReaderTag;
import org.wordpress.android.models.ReaderTagType;
import org.wordpress.android.ui.reader.ReaderEvents;
import org.wordpress.android.ui.reader.services.ServiceCompletionListener;
import org.wordpress.android.util.AppLog;
import static org.wordpress.android.ui.reader.services.post.ReaderPostServiceStarter.ARG_ACTION;
import static org.wordpress.android.ui.reader.services.post.ReaderPostServiceStarter.ARG_BLOG_ID;
import static org.wordpress.android.ui.reader.services.post.ReaderPostServiceStarter.ARG_FEED_ID;
import static org.wordpress.android.ui.reader.services.post.ReaderPostServiceStarter.ARG_TAG_PARAM_DISPLAY_NAME;
import static org.wordpress.android.ui.reader.services.post.ReaderPostServiceStarter.ARG_TAG_PARAM_ENDPOINT;
import static org.wordpress.android.ui.reader.services.post.ReaderPostServiceStarter.ARG_TAG_PARAM_SLUG;
import static org.wordpress.android.ui.reader.services.post.ReaderPostServiceStarter.ARG_TAG_PARAM_TAGTYPE;
import static org.wordpress.android.ui.reader.services.post.ReaderPostServiceStarter.ARG_TAG_PARAM_TITLE;
import static org.wordpress.android.ui.reader.services.post.ReaderPostServiceStarter.UpdateAction;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderPostJobService extends JobService implements ServiceCompletionListener {

    private ReaderPostLogic mReaderPostLogic;

    @Override
    public boolean onStartJob(JobParameters params) {
        if (!ListenerUtil.mutListener.listen(19203)) {
            AppLog.i(AppLog.T.READER, "reader post job service > started");
        }
        UpdateAction action;
        if (params.getExtras() != null) {
            if (params.getExtras().containsKey(ARG_ACTION)) {
                action = UpdateAction.values()[(Integer) params.getExtras().get(ARG_ACTION)];
            } else {
                action = UpdateAction.REQUEST_NEWER;
            }
            if (!ListenerUtil.mutListener.listen(19210)) {
                if (params.getExtras().containsKey(ARG_TAG_PARAM_SLUG)) {
                    ReaderTag tag = getReaderTagFromBundleParams(params.getExtras());
                    if (!ListenerUtil.mutListener.listen(19208)) {
                        EventBus.getDefault().post(new ReaderEvents.UpdatePostsStarted(action, tag));
                    }
                    if (!ListenerUtil.mutListener.listen(19209)) {
                        mReaderPostLogic.performTask(params, action, tag, -1, -1);
                    }
                } else if (params.getExtras().containsKey(ARG_BLOG_ID)) {
                    long blogId = params.getExtras().getLong(ARG_BLOG_ID, 0);
                    if (!ListenerUtil.mutListener.listen(19206)) {
                        EventBus.getDefault().post(new ReaderEvents.UpdatePostsStarted(action));
                    }
                    if (!ListenerUtil.mutListener.listen(19207)) {
                        mReaderPostLogic.performTask(params, action, null, blogId, -1);
                    }
                } else if (params.getExtras().containsKey(ARG_FEED_ID)) {
                    long feedId = params.getExtras().getLong(ARG_FEED_ID, 0);
                    if (!ListenerUtil.mutListener.listen(19204)) {
                        EventBus.getDefault().post(new ReaderEvents.UpdatePostsStarted(action));
                    }
                    if (!ListenerUtil.mutListener.listen(19205)) {
                        mReaderPostLogic.performTask(params, action, null, -1, feedId);
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        if (!ListenerUtil.mutListener.listen(19211)) {
            AppLog.i(AppLog.T.READER, "reader post job service > stopped");
        }
        if (!ListenerUtil.mutListener.listen(19212)) {
            jobFinished(params, false);
        }
        return false;
    }

    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(19213)) {
            super.onCreate();
        }
        if (!ListenerUtil.mutListener.listen(19214)) {
            mReaderPostLogic = new ReaderPostLogic(this);
        }
        if (!ListenerUtil.mutListener.listen(19215)) {
            AppLog.i(AppLog.T.READER, "reader post job service > created");
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(19216)) {
            AppLog.i(AppLog.T.READER, "reader post job service > destroyed");
        }
        if (!ListenerUtil.mutListener.listen(19217)) {
            super.onDestroy();
        }
    }

    @Override
    public void onCompleted(Object companion) {
        if (!ListenerUtil.mutListener.listen(19218)) {
            AppLog.i(AppLog.T.READER, "reader post job service > all tasks completed");
        }
        if (!ListenerUtil.mutListener.listen(19219)) {
            jobFinished((JobParameters) companion, false);
        }
    }

    private ReaderTag getReaderTagFromBundleParams(PersistableBundle bundle) {
        String slug = bundle.getString(ARG_TAG_PARAM_SLUG);
        String displayName = bundle.getString(ARG_TAG_PARAM_DISPLAY_NAME);
        String title = bundle.getString(ARG_TAG_PARAM_TITLE);
        String endpoint = bundle.getString(ARG_TAG_PARAM_ENDPOINT);
        int tagType = bundle.getInt(ARG_TAG_PARAM_TAGTYPE);
        ReaderTag tag = new ReaderTag(slug, displayName, title, endpoint, ReaderTagType.fromInt(tagType));
        return tag;
    }
}
