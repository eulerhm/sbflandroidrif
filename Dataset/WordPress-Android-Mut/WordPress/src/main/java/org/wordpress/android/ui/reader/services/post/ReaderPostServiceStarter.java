package org.wordpress.android.ui.reader.services.post;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PersistableBundle;
import org.wordpress.android.models.ReaderTag;
import org.wordpress.android.util.AppLog;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class ReaderPostServiceStarter {

    private static final int JOB_READER_POST_SERVICE_ID_TAG = 4001;

    private static final int JOB_READER_POST_SERVICE_ID_BLOG = 4002;

    private static final int JOB_READER_POST_SERVICE_ID_FEED = 4003;

    public static final String ARG_TAG = "tag";

    public static final String ARG_ACTION = "action";

    public static final String ARG_BLOG_ID = "blog_id";

    public static final String ARG_FEED_ID = "feed_id";

    public static final String ARG_TAG_PARAM_SLUG = "tag-slug";

    public static final String ARG_TAG_PARAM_DISPLAY_NAME = "tag-display-name";

    public static final String ARG_TAG_PARAM_TITLE = "tag-title";

    public static final String ARG_TAG_PARAM_ENDPOINT = "tag-endpoint";

    public static final String ARG_TAG_PARAM_TAGTYPE = "tag-type";

    public enum UpdateAction {

        // request the newest posts for this tag/blog/feed
        REQUEST_NEWER,
        // request fresh data and get rid of the rest
        REQUEST_REFRESH,
        // request posts older than the oldest existing one for this tag/blog/feed
        REQUEST_OLDER,
        // request posts older than the one with the gap marker for this tag
        REQUEST_OLDER_THAN_GAP
    }

    /*
     * update posts with the passed tag
     */
    public static void startServiceForTag(Context context, ReaderTag tag, UpdateAction action) {
        if (!ListenerUtil.mutListener.listen(19375)) {
            if ((ListenerUtil.mutListener.listen(19368) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(19367) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(19366) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(19365) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(19364) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.O))))))) {
                Intent intent = new Intent(context, ReaderPostService.class);
                if (!ListenerUtil.mutListener.listen(19372)) {
                    intent.putExtra(ARG_TAG, tag);
                }
                if (!ListenerUtil.mutListener.listen(19373)) {
                    intent.putExtra(ARG_ACTION, action);
                }
                if (!ListenerUtil.mutListener.listen(19374)) {
                    context.startService(intent);
                }
            } else {
                PersistableBundle extras = new PersistableBundle();
                if (!ListenerUtil.mutListener.listen(19369)) {
                    extras.putInt(ARG_ACTION, action.ordinal());
                }
                if (!ListenerUtil.mutListener.listen(19370)) {
                    putReaderTagExtras(extras, tag);
                }
                if (!ListenerUtil.mutListener.listen(19371)) {
                    doScheduleJobWithBundle(context, extras, JOB_READER_POST_SERVICE_ID_TAG + tag.getTagSlug().hashCode());
                }
            }
        }
    }

    /*
     * update posts in the passed blog
     */
    public static void startServiceForBlog(Context context, long blogId, UpdateAction action) {
        if (!ListenerUtil.mutListener.listen(19387)) {
            if ((ListenerUtil.mutListener.listen(19380) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(19379) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(19378) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(19377) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(19376) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.O))))))) {
                Intent intent = new Intent(context, ReaderPostService.class);
                if (!ListenerUtil.mutListener.listen(19384)) {
                    intent.putExtra(ARG_BLOG_ID, blogId);
                }
                if (!ListenerUtil.mutListener.listen(19385)) {
                    intent.putExtra(ARG_ACTION, action);
                }
                if (!ListenerUtil.mutListener.listen(19386)) {
                    context.startService(intent);
                }
            } else {
                PersistableBundle extras = new PersistableBundle();
                if (!ListenerUtil.mutListener.listen(19381)) {
                    extras.putLong(ARG_BLOG_ID, blogId);
                }
                if (!ListenerUtil.mutListener.listen(19382)) {
                    extras.putInt(ARG_ACTION, action.ordinal());
                }
                if (!ListenerUtil.mutListener.listen(19383)) {
                    doScheduleJobWithBundle(context, extras, JOB_READER_POST_SERVICE_ID_BLOG);
                }
            }
        }
    }

    /*
     * update posts in the passed feed
     */
    public static void startServiceForFeed(Context context, long feedId, UpdateAction action) {
        if (!ListenerUtil.mutListener.listen(19399)) {
            if ((ListenerUtil.mutListener.listen(19392) ? (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(19391) ? (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(19390) ? (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(19389) ? (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) : (ListenerUtil.mutListener.listen(19388) ? (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) : (Build.VERSION.SDK_INT < Build.VERSION_CODES.O))))))) {
                Intent intent = new Intent(context, ReaderPostService.class);
                if (!ListenerUtil.mutListener.listen(19396)) {
                    intent.putExtra(ARG_FEED_ID, feedId);
                }
                if (!ListenerUtil.mutListener.listen(19397)) {
                    intent.putExtra(ARG_ACTION, action);
                }
                if (!ListenerUtil.mutListener.listen(19398)) {
                    context.startService(intent);
                }
            } else {
                PersistableBundle extras = new PersistableBundle();
                if (!ListenerUtil.mutListener.listen(19393)) {
                    extras.putLong(ARG_FEED_ID, feedId);
                }
                if (!ListenerUtil.mutListener.listen(19394)) {
                    extras.putInt(ARG_ACTION, action.ordinal());
                }
                if (!ListenerUtil.mutListener.listen(19395)) {
                    doScheduleJobWithBundle(context, extras, JOB_READER_POST_SERVICE_ID_FEED);
                }
            }
        }
    }

    private static void doScheduleJobWithBundle(Context context, PersistableBundle extras, int jobId) {
        // it's preferable to use it only since enforcement in API 26 to not break any old behavior
        ComponentName componentName = new ComponentName(context, ReaderPostJobService.class);
        JobInfo jobInfo = new JobInfo.Builder(jobId, componentName).setRequiresCharging(false).setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY).setOverrideDeadline(// if possible, try to run right away
        0).setExtras(extras).build();
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        int resultCode = jobScheduler.schedule(jobInfo);
        if (!ListenerUtil.mutListener.listen(19402)) {
            if (resultCode == JobScheduler.RESULT_SUCCESS) {
                if (!ListenerUtil.mutListener.listen(19401)) {
                    AppLog.i(AppLog.T.READER, "reader post service > job scheduled");
                }
            } else {
                if (!ListenerUtil.mutListener.listen(19400)) {
                    AppLog.e(AppLog.T.READER, "reader post service > job could not be scheduled");
                }
            }
        }
    }

    private static void putReaderTagExtras(PersistableBundle extras, ReaderTag tag) {
        if (!ListenerUtil.mutListener.listen(19403)) {
            extras.putString(ARG_TAG_PARAM_SLUG, tag.getTagSlug());
        }
        if (!ListenerUtil.mutListener.listen(19404)) {
            extras.putString(ARG_TAG_PARAM_DISPLAY_NAME, tag.getTagDisplayName());
        }
        if (!ListenerUtil.mutListener.listen(19405)) {
            extras.putString(ARG_TAG_PARAM_TITLE, tag.getTagTitle());
        }
        if (!ListenerUtil.mutListener.listen(19406)) {
            extras.putString(ARG_TAG_PARAM_ENDPOINT, tag.getEndpoint());
        }
        if (!ListenerUtil.mutListener.listen(19407)) {
            extras.putInt(ARG_TAG_PARAM_TAGTYPE, tag.tagType.toInt());
        }
    }
}
