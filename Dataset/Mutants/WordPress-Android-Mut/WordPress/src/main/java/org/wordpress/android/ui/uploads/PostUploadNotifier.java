package org.wordpress.android.ui.uploads;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SparseArrayCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;
import org.greenrobot.eventbus.EventBus;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.fluxc.model.MediaModel;
import org.wordpress.android.fluxc.model.PostImmutableModel;
import org.wordpress.android.fluxc.model.PostModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.model.post.PostStatus;
import org.wordpress.android.push.NotificationType;
import org.wordpress.android.push.NotificationsProcessingService;
import org.wordpress.android.ui.RequestCodes;
import org.wordpress.android.ui.media.MediaBrowserActivity;
import org.wordpress.android.ui.notifications.SystemNotificationsTracker;
import org.wordpress.android.ui.pages.PagesActivity;
import org.wordpress.android.ui.posts.EditPostActivity;
import org.wordpress.android.ui.posts.PostUtils;
import org.wordpress.android.ui.posts.PostsListActivity;
import org.wordpress.android.ui.posts.PostsListActivityKt;
import org.wordpress.android.ui.prefs.AppPrefs;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.SystemServiceFactory;
import org.wordpress.android.util.WPMeShortlinks;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.wordpress.android.push.NotificationsProcessingService.ARG_NOTIFICATION_TYPE;
import static org.wordpress.android.ui.pages.PagesActivityKt.EXTRA_PAGE_REMOTE_ID_KEY;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

class PostUploadNotifier {

    private final Context mContext;

    private final UploadService mService;

    private final NotificationManager mNotificationManager;

    private final SystemNotificationsTracker mSystemNotificationsTracker;

    private final NotificationCompat.Builder mNotificationBuilder;

    private static final int BASE_MEDIA_ERROR_NOTIFICATION_ID = 72000;

    private enum PagesOrPostsType {

        POST, PAGE, POSTS, PAGES, PAGES_OR_POSTS
    }

    // for the live UploadService instance
    private static NotificationData sNotificationData;

    private class NotificationData {

        int mNotificationId;

        int mTotalMediaItems;

        int mCurrentMediaItem;

        int mTotalPostItems;

        int mTotalPageItemsIncludedInPostCount;

        int mCurrentPostItem;

        final SparseArrayCompat<Float> mediaItemToProgressMap = new SparseArrayCompat<>();

        final List<PostImmutableModel> mUploadedPostsCounted = new ArrayList<>();
    }

    PostUploadNotifier(Context context, UploadService service, SystemNotificationsTracker systemNotificationsTracker) {
        // Add the uploader to the notification bar
        mContext = context;
        mService = service;
        mSystemNotificationsTracker = systemNotificationsTracker;
        if (!ListenerUtil.mutListener.listen(24082)) {
            sNotificationData = new NotificationData();
        }
        mNotificationManager = (NotificationManager) SystemServiceFactory.get(mContext, Context.NOTIFICATION_SERVICE);
        mNotificationBuilder = new NotificationCompat.Builder(mContext.getApplicationContext(), context.getString(R.string.notification_channel_transient_id));
        if (!ListenerUtil.mutListener.listen(24083)) {
            mNotificationBuilder.setSmallIcon(android.R.drawable.stat_sys_upload).setColor(context.getResources().getColor(R.color.primary_50)).setOnlyAlertOnce(true);
        }
    }

    private void updateForegroundNotification(@Nullable PostImmutableModel post) {
        if (!ListenerUtil.mutListener.listen(24084)) {
            updateNotificationBuilder(post);
        }
        if (!ListenerUtil.mutListener.listen(24085)) {
            updateNotificationProgress();
        }
    }

    private void updateNotificationBuilder(@Nullable PostImmutableModel post) {
        if (!ListenerUtil.mutListener.listen(24130)) {
            // set the Notification's title and prepare the Notifications message text, i.e. "1/3 Posts, 4/17 media items"
            if ((ListenerUtil.mutListener.listen(24096) ? ((ListenerUtil.mutListener.listen(24090) ? (sNotificationData.mTotalMediaItems >= 0) : (ListenerUtil.mutListener.listen(24089) ? (sNotificationData.mTotalMediaItems <= 0) : (ListenerUtil.mutListener.listen(24088) ? (sNotificationData.mTotalMediaItems < 0) : (ListenerUtil.mutListener.listen(24087) ? (sNotificationData.mTotalMediaItems != 0) : (ListenerUtil.mutListener.listen(24086) ? (sNotificationData.mTotalMediaItems == 0) : (sNotificationData.mTotalMediaItems > 0)))))) || (ListenerUtil.mutListener.listen(24095) ? (sNotificationData.mTotalPostItems >= 0) : (ListenerUtil.mutListener.listen(24094) ? (sNotificationData.mTotalPostItems <= 0) : (ListenerUtil.mutListener.listen(24093) ? (sNotificationData.mTotalPostItems > 0) : (ListenerUtil.mutListener.listen(24092) ? (sNotificationData.mTotalPostItems < 0) : (ListenerUtil.mutListener.listen(24091) ? (sNotificationData.mTotalPostItems != 0) : (sNotificationData.mTotalPostItems == 0))))))) : ((ListenerUtil.mutListener.listen(24090) ? (sNotificationData.mTotalMediaItems >= 0) : (ListenerUtil.mutListener.listen(24089) ? (sNotificationData.mTotalMediaItems <= 0) : (ListenerUtil.mutListener.listen(24088) ? (sNotificationData.mTotalMediaItems < 0) : (ListenerUtil.mutListener.listen(24087) ? (sNotificationData.mTotalMediaItems != 0) : (ListenerUtil.mutListener.listen(24086) ? (sNotificationData.mTotalMediaItems == 0) : (sNotificationData.mTotalMediaItems > 0)))))) && (ListenerUtil.mutListener.listen(24095) ? (sNotificationData.mTotalPostItems >= 0) : (ListenerUtil.mutListener.listen(24094) ? (sNotificationData.mTotalPostItems <= 0) : (ListenerUtil.mutListener.listen(24093) ? (sNotificationData.mTotalPostItems > 0) : (ListenerUtil.mutListener.listen(24092) ? (sNotificationData.mTotalPostItems < 0) : (ListenerUtil.mutListener.listen(24091) ? (sNotificationData.mTotalPostItems != 0) : (sNotificationData.mTotalPostItems == 0))))))))) {
                if (!ListenerUtil.mutListener.listen(24129)) {
                    // check if special case for ONE media item
                    if ((ListenerUtil.mutListener.listen(24124) ? (sNotificationData.mTotalMediaItems >= 1) : (ListenerUtil.mutListener.listen(24123) ? (sNotificationData.mTotalMediaItems <= 1) : (ListenerUtil.mutListener.listen(24122) ? (sNotificationData.mTotalMediaItems > 1) : (ListenerUtil.mutListener.listen(24121) ? (sNotificationData.mTotalMediaItems < 1) : (ListenerUtil.mutListener.listen(24120) ? (sNotificationData.mTotalMediaItems != 1) : (sNotificationData.mTotalMediaItems == 1))))))) {
                        if (!ListenerUtil.mutListener.listen(24127)) {
                            mNotificationBuilder.setContentTitle(buildNotificationTitleForMedia());
                        }
                        if (!ListenerUtil.mutListener.listen(24128)) {
                            mNotificationBuilder.setContentText(buildNotificationSubtitleForMedia());
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(24125)) {
                            mNotificationBuilder.setContentTitle(buildNotificationTitleForMixedContent());
                        }
                        if (!ListenerUtil.mutListener.listen(24126)) {
                            mNotificationBuilder.setContentText(buildNotificationSubtitleForMedia());
                        }
                    }
                }
            } else if ((ListenerUtil.mutListener.listen(24107) ? ((ListenerUtil.mutListener.listen(24101) ? (sNotificationData.mTotalMediaItems >= 0) : (ListenerUtil.mutListener.listen(24100) ? (sNotificationData.mTotalMediaItems <= 0) : (ListenerUtil.mutListener.listen(24099) ? (sNotificationData.mTotalMediaItems > 0) : (ListenerUtil.mutListener.listen(24098) ? (sNotificationData.mTotalMediaItems < 0) : (ListenerUtil.mutListener.listen(24097) ? (sNotificationData.mTotalMediaItems != 0) : (sNotificationData.mTotalMediaItems == 0)))))) || (ListenerUtil.mutListener.listen(24106) ? (sNotificationData.mTotalPostItems >= 0) : (ListenerUtil.mutListener.listen(24105) ? (sNotificationData.mTotalPostItems <= 0) : (ListenerUtil.mutListener.listen(24104) ? (sNotificationData.mTotalPostItems < 0) : (ListenerUtil.mutListener.listen(24103) ? (sNotificationData.mTotalPostItems != 0) : (ListenerUtil.mutListener.listen(24102) ? (sNotificationData.mTotalPostItems == 0) : (sNotificationData.mTotalPostItems > 0))))))) : ((ListenerUtil.mutListener.listen(24101) ? (sNotificationData.mTotalMediaItems >= 0) : (ListenerUtil.mutListener.listen(24100) ? (sNotificationData.mTotalMediaItems <= 0) : (ListenerUtil.mutListener.listen(24099) ? (sNotificationData.mTotalMediaItems > 0) : (ListenerUtil.mutListener.listen(24098) ? (sNotificationData.mTotalMediaItems < 0) : (ListenerUtil.mutListener.listen(24097) ? (sNotificationData.mTotalMediaItems != 0) : (sNotificationData.mTotalMediaItems == 0)))))) && (ListenerUtil.mutListener.listen(24106) ? (sNotificationData.mTotalPostItems >= 0) : (ListenerUtil.mutListener.listen(24105) ? (sNotificationData.mTotalPostItems <= 0) : (ListenerUtil.mutListener.listen(24104) ? (sNotificationData.mTotalPostItems < 0) : (ListenerUtil.mutListener.listen(24103) ? (sNotificationData.mTotalPostItems != 0) : (ListenerUtil.mutListener.listen(24102) ? (sNotificationData.mTotalPostItems == 0) : (sNotificationData.mTotalPostItems > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(24119)) {
                    // check if special case for ONE Post
                    if ((ListenerUtil.mutListener.listen(24114) ? (sNotificationData.mTotalPostItems >= 1) : (ListenerUtil.mutListener.listen(24113) ? (sNotificationData.mTotalPostItems <= 1) : (ListenerUtil.mutListener.listen(24112) ? (sNotificationData.mTotalPostItems > 1) : (ListenerUtil.mutListener.listen(24111) ? (sNotificationData.mTotalPostItems < 1) : (ListenerUtil.mutListener.listen(24110) ? (sNotificationData.mTotalPostItems != 1) : (sNotificationData.mTotalPostItems == 1))))))) {
                        if (!ListenerUtil.mutListener.listen(24117)) {
                            mNotificationBuilder.setContentTitle(buildNotificationTitleForPost(post));
                        }
                        if (!ListenerUtil.mutListener.listen(24118)) {
                            mNotificationBuilder.setContentText(buildNotificationSubtitleForPost(post));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(24115)) {
                            mNotificationBuilder.setContentTitle(buildNotificationTitleForMixedContent());
                        }
                        if (!ListenerUtil.mutListener.listen(24116)) {
                            mNotificationBuilder.setContentText(buildNotificationSubtitleForPosts());
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(24108)) {
                    // mixed content (Post/Pages and media) is being uploaded
                    mNotificationBuilder.setContentTitle(buildNotificationTitleForMixedContent());
                }
                if (!ListenerUtil.mutListener.listen(24109)) {
                    mNotificationBuilder.setContentText(buildNotificationSubtitleForMixedContent());
                }
            }
        }
    }

    private synchronized void startOrUpdateForegroundNotification(@Nullable PostImmutableModel post) {
        boolean isTotalPostsAndMediaItemsCountZero = (ListenerUtil.mutListener.listen(24141) ? ((ListenerUtil.mutListener.listen(24135) ? (sNotificationData.mTotalPostItems >= 0) : (ListenerUtil.mutListener.listen(24134) ? (sNotificationData.mTotalPostItems <= 0) : (ListenerUtil.mutListener.listen(24133) ? (sNotificationData.mTotalPostItems > 0) : (ListenerUtil.mutListener.listen(24132) ? (sNotificationData.mTotalPostItems < 0) : (ListenerUtil.mutListener.listen(24131) ? (sNotificationData.mTotalPostItems != 0) : (sNotificationData.mTotalPostItems == 0)))))) || (ListenerUtil.mutListener.listen(24140) ? (sNotificationData.mTotalMediaItems >= 0) : (ListenerUtil.mutListener.listen(24139) ? (sNotificationData.mTotalMediaItems <= 0) : (ListenerUtil.mutListener.listen(24138) ? (sNotificationData.mTotalMediaItems > 0) : (ListenerUtil.mutListener.listen(24137) ? (sNotificationData.mTotalMediaItems < 0) : (ListenerUtil.mutListener.listen(24136) ? (sNotificationData.mTotalMediaItems != 0) : (sNotificationData.mTotalMediaItems == 0))))))) : ((ListenerUtil.mutListener.listen(24135) ? (sNotificationData.mTotalPostItems >= 0) : (ListenerUtil.mutListener.listen(24134) ? (sNotificationData.mTotalPostItems <= 0) : (ListenerUtil.mutListener.listen(24133) ? (sNotificationData.mTotalPostItems > 0) : (ListenerUtil.mutListener.listen(24132) ? (sNotificationData.mTotalPostItems < 0) : (ListenerUtil.mutListener.listen(24131) ? (sNotificationData.mTotalPostItems != 0) : (sNotificationData.mTotalPostItems == 0)))))) && (ListenerUtil.mutListener.listen(24140) ? (sNotificationData.mTotalMediaItems >= 0) : (ListenerUtil.mutListener.listen(24139) ? (sNotificationData.mTotalMediaItems <= 0) : (ListenerUtil.mutListener.listen(24138) ? (sNotificationData.mTotalMediaItems > 0) : (ListenerUtil.mutListener.listen(24137) ? (sNotificationData.mTotalMediaItems < 0) : (ListenerUtil.mutListener.listen(24136) ? (sNotificationData.mTotalMediaItems != 0) : (sNotificationData.mTotalMediaItems == 0))))))));
        if (!ListenerUtil.mutListener.listen(24142)) {
            if (isTotalPostsAndMediaItemsCountZero) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(24143)) {
            updateNotificationBuilder(post);
        }
        if (!ListenerUtil.mutListener.listen(24152)) {
            if ((ListenerUtil.mutListener.listen(24148) ? (sNotificationData.mNotificationId >= 0) : (ListenerUtil.mutListener.listen(24147) ? (sNotificationData.mNotificationId <= 0) : (ListenerUtil.mutListener.listen(24146) ? (sNotificationData.mNotificationId > 0) : (ListenerUtil.mutListener.listen(24145) ? (sNotificationData.mNotificationId < 0) : (ListenerUtil.mutListener.listen(24144) ? (sNotificationData.mNotificationId != 0) : (sNotificationData.mNotificationId == 0))))))) {
                if (!ListenerUtil.mutListener.listen(24150)) {
                    sNotificationData.mNotificationId = (new Random()).nextInt();
                }
                if (!ListenerUtil.mutListener.listen(24151)) {
                    mService.startForeground(sNotificationData.mNotificationId, mNotificationBuilder.build());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(24149)) {
                    // service was already started, let's just modify the notification
                    doNotify(sNotificationData.mNotificationId, mNotificationBuilder.build(), null);
                }
            }
        }
    }

    void removePostInfoFromForegroundNotificationData(@NonNull PostImmutableModel post, @Nullable List<MediaModel> media) {
        if (!ListenerUtil.mutListener.listen(24161)) {
            if ((ListenerUtil.mutListener.listen(24157) ? (sNotificationData.mTotalPostItems >= 0) : (ListenerUtil.mutListener.listen(24156) ? (sNotificationData.mTotalPostItems <= 0) : (ListenerUtil.mutListener.listen(24155) ? (sNotificationData.mTotalPostItems < 0) : (ListenerUtil.mutListener.listen(24154) ? (sNotificationData.mTotalPostItems != 0) : (ListenerUtil.mutListener.listen(24153) ? (sNotificationData.mTotalPostItems == 0) : (sNotificationData.mTotalPostItems > 0))))))) {
                if (!ListenerUtil.mutListener.listen(24158)) {
                    sNotificationData.mTotalPostItems--;
                }
                if (!ListenerUtil.mutListener.listen(24160)) {
                    if (post.isPage()) {
                        if (!ListenerUtil.mutListener.listen(24159)) {
                            sNotificationData.mTotalPageItemsIncludedInPostCount--;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24163)) {
            if (media != null) {
                if (!ListenerUtil.mutListener.listen(24162)) {
                    removeMediaInfoFromForegroundNotification(media);
                }
            }
        }
    }

    // Post could have initial media, or not (nullable)
    void addPostInfoToForegroundNotification(@NonNull PostImmutableModel post, @Nullable List<MediaModel> media) {
        if (!ListenerUtil.mutListener.listen(24164)) {
            sNotificationData.mTotalPostItems++;
        }
        if (!ListenerUtil.mutListener.listen(24166)) {
            if (post.isPage()) {
                if (!ListenerUtil.mutListener.listen(24165)) {
                    sNotificationData.mTotalPageItemsIncludedInPostCount++;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24168)) {
            if (media != null) {
                if (!ListenerUtil.mutListener.listen(24167)) {
                    addMediaInfoToForegroundNotification(media);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24169)) {
            startOrUpdateForegroundNotification(post);
        }
    }

    void removePostInfoFromForegroundNotification(@NonNull PostImmutableModel post, @Nullable List<MediaModel> media) {
        if (!ListenerUtil.mutListener.listen(24170)) {
            removePostInfoFromForegroundNotificationData(post, media);
        }
        if (!ListenerUtil.mutListener.listen(24171)) {
            startOrUpdateForegroundNotification(post);
        }
    }

    void removeMediaInfoFromForegroundNotification(@NonNull List<MediaModel> mediaList) {
        if (!ListenerUtil.mutListener.listen(24179)) {
            if ((ListenerUtil.mutListener.listen(24176) ? (sNotificationData.mTotalMediaItems <= mediaList.size()) : (ListenerUtil.mutListener.listen(24175) ? (sNotificationData.mTotalMediaItems > mediaList.size()) : (ListenerUtil.mutListener.listen(24174) ? (sNotificationData.mTotalMediaItems < mediaList.size()) : (ListenerUtil.mutListener.listen(24173) ? (sNotificationData.mTotalMediaItems != mediaList.size()) : (ListenerUtil.mutListener.listen(24172) ? (sNotificationData.mTotalMediaItems == mediaList.size()) : (sNotificationData.mTotalMediaItems >= mediaList.size()))))))) {
                if (!ListenerUtil.mutListener.listen(24177)) {
                    sNotificationData.mTotalMediaItems -= mediaList.size();
                }
                if (!ListenerUtil.mutListener.listen(24178)) {
                    // update Notification now
                    updateForegroundNotification(null);
                }
            }
        }
    }

    void removeOneMediaItemInfoFromForegroundNotification() {
        if (!ListenerUtil.mutListener.listen(24187)) {
            if ((ListenerUtil.mutListener.listen(24184) ? (sNotificationData.mTotalMediaItems <= 1) : (ListenerUtil.mutListener.listen(24183) ? (sNotificationData.mTotalMediaItems > 1) : (ListenerUtil.mutListener.listen(24182) ? (sNotificationData.mTotalMediaItems < 1) : (ListenerUtil.mutListener.listen(24181) ? (sNotificationData.mTotalMediaItems != 1) : (ListenerUtil.mutListener.listen(24180) ? (sNotificationData.mTotalMediaItems == 1) : (sNotificationData.mTotalMediaItems >= 1))))))) {
                if (!ListenerUtil.mutListener.listen(24185)) {
                    sNotificationData.mTotalMediaItems--;
                }
                if (!ListenerUtil.mutListener.listen(24186)) {
                    // update Notification now
                    updateForegroundNotification(null);
                }
            }
        }
    }

    void addMediaInfoToForegroundNotification(@NonNull List<MediaModel> mediaList) {
        if (!ListenerUtil.mutListener.listen(24188)) {
            sNotificationData.mTotalMediaItems += mediaList.size();
        }
        if (!ListenerUtil.mutListener.listen(24190)) {
            {
                long _loopCounter373 = 0;
                // setup progresses for each media item
                for (MediaModel media : mediaList) {
                    ListenerUtil.loopListener.listen("_loopCounter373", ++_loopCounter373);
                    if (!ListenerUtil.mutListener.listen(24189)) {
                        setProgressForMediaItem(media.getId(), 0.0f);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24191)) {
            startOrUpdateForegroundNotification(null);
        }
    }

    void addMediaInfoToForegroundNotification(@NonNull MediaModel media) {
        if (!ListenerUtil.mutListener.listen(24192)) {
            sNotificationData.mTotalMediaItems++;
        }
        if (!ListenerUtil.mutListener.listen(24193)) {
            // setup progress for media item
            setProgressForMediaItem(media.getId(), 0.0f);
        }
        if (!ListenerUtil.mutListener.listen(24194)) {
            startOrUpdateForegroundNotification(null);
        }
    }

    void incrementUploadedPostCountFromForegroundNotification(@NonNull PostImmutableModel post) {
        if (!ListenerUtil.mutListener.listen(24195)) {
            incrementUploadedPostCountFromForegroundNotification(post, false);
        }
    }

    void incrementUploadedPostCountFromForegroundNotification(@NonNull PostImmutableModel post, boolean force) {
        if (!ListenerUtil.mutListener.listen(24198)) {
            // it needs to be cancelled).
            if ((ListenerUtil.mutListener.listen(24196) ? (!force || isPostAlreadyInPostCount(post)) : (!force && isPostAlreadyInPostCount(post)))) {
                return;
            } else {
                if (!ListenerUtil.mutListener.listen(24197)) {
                    addPostToPostCount(post);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24199)) {
            sNotificationData.mCurrentPostItem++;
        }
        if (!ListenerUtil.mutListener.listen(24201)) {
            // update Notification now
            if (!removeNotificationAndStopForegroundServiceIfNoItemsInQueue()) {
                if (!ListenerUtil.mutListener.listen(24200)) {
                    updateForegroundNotification(post);
                }
            }
        }
    }

    void incrementUploadedMediaCountFromProgressNotification(int mediaId) {
        if (!ListenerUtil.mutListener.listen(24202)) {
            sNotificationData.mCurrentMediaItem++;
        }
        if (!ListenerUtil.mutListener.listen(24204)) {
            if (!removeNotificationAndStopForegroundServiceIfNoItemsInQueue()) {
                if (!ListenerUtil.mutListener.listen(24203)) {
                    // update Notification now
                    updateForegroundNotification(null);
                }
            }
        }
    }

    private boolean removeNotificationAndStopForegroundServiceIfNoItemsInQueue() {
        if (!ListenerUtil.mutListener.listen(24220)) {
            if ((ListenerUtil.mutListener.listen(24215) ? ((ListenerUtil.mutListener.listen(24209) ? (sNotificationData.mCurrentPostItem >= sNotificationData.mTotalPostItems) : (ListenerUtil.mutListener.listen(24208) ? (sNotificationData.mCurrentPostItem <= sNotificationData.mTotalPostItems) : (ListenerUtil.mutListener.listen(24207) ? (sNotificationData.mCurrentPostItem > sNotificationData.mTotalPostItems) : (ListenerUtil.mutListener.listen(24206) ? (sNotificationData.mCurrentPostItem < sNotificationData.mTotalPostItems) : (ListenerUtil.mutListener.listen(24205) ? (sNotificationData.mCurrentPostItem != sNotificationData.mTotalPostItems) : (sNotificationData.mCurrentPostItem == sNotificationData.mTotalPostItems)))))) || (ListenerUtil.mutListener.listen(24214) ? (sNotificationData.mCurrentMediaItem >= sNotificationData.mTotalMediaItems) : (ListenerUtil.mutListener.listen(24213) ? (sNotificationData.mCurrentMediaItem <= sNotificationData.mTotalMediaItems) : (ListenerUtil.mutListener.listen(24212) ? (sNotificationData.mCurrentMediaItem > sNotificationData.mTotalMediaItems) : (ListenerUtil.mutListener.listen(24211) ? (sNotificationData.mCurrentMediaItem < sNotificationData.mTotalMediaItems) : (ListenerUtil.mutListener.listen(24210) ? (sNotificationData.mCurrentMediaItem != sNotificationData.mTotalMediaItems) : (sNotificationData.mCurrentMediaItem == sNotificationData.mTotalMediaItems))))))) : ((ListenerUtil.mutListener.listen(24209) ? (sNotificationData.mCurrentPostItem >= sNotificationData.mTotalPostItems) : (ListenerUtil.mutListener.listen(24208) ? (sNotificationData.mCurrentPostItem <= sNotificationData.mTotalPostItems) : (ListenerUtil.mutListener.listen(24207) ? (sNotificationData.mCurrentPostItem > sNotificationData.mTotalPostItems) : (ListenerUtil.mutListener.listen(24206) ? (sNotificationData.mCurrentPostItem < sNotificationData.mTotalPostItems) : (ListenerUtil.mutListener.listen(24205) ? (sNotificationData.mCurrentPostItem != sNotificationData.mTotalPostItems) : (sNotificationData.mCurrentPostItem == sNotificationData.mTotalPostItems)))))) && (ListenerUtil.mutListener.listen(24214) ? (sNotificationData.mCurrentMediaItem >= sNotificationData.mTotalMediaItems) : (ListenerUtil.mutListener.listen(24213) ? (sNotificationData.mCurrentMediaItem <= sNotificationData.mTotalMediaItems) : (ListenerUtil.mutListener.listen(24212) ? (sNotificationData.mCurrentMediaItem > sNotificationData.mTotalMediaItems) : (ListenerUtil.mutListener.listen(24211) ? (sNotificationData.mCurrentMediaItem < sNotificationData.mTotalMediaItems) : (ListenerUtil.mutListener.listen(24210) ? (sNotificationData.mCurrentMediaItem != sNotificationData.mTotalMediaItems) : (sNotificationData.mCurrentMediaItem == sNotificationData.mTotalMediaItems))))))))) {
                if (!ListenerUtil.mutListener.listen(24216)) {
                    mNotificationManager.cancel(sNotificationData.mNotificationId);
                }
                if (!ListenerUtil.mutListener.listen(24217)) {
                    // reset the notification id so a new one is generated next time the service is started
                    sNotificationData.mNotificationId = 0;
                }
                if (!ListenerUtil.mutListener.listen(24218)) {
                    resetNotificationCounters();
                }
                if (!ListenerUtil.mutListener.listen(24219)) {
                    mService.stopForeground(true);
                }
                return true;
            }
        }
        return false;
    }

    private void resetNotificationCounters() {
        if (!ListenerUtil.mutListener.listen(24221)) {
            sNotificationData.mCurrentPostItem = 0;
        }
        if (!ListenerUtil.mutListener.listen(24222)) {
            sNotificationData.mCurrentMediaItem = 0;
        }
        if (!ListenerUtil.mutListener.listen(24223)) {
            sNotificationData.mTotalMediaItems = 0;
        }
        if (!ListenerUtil.mutListener.listen(24224)) {
            sNotificationData.mTotalPostItems = 0;
        }
        if (!ListenerUtil.mutListener.listen(24225)) {
            sNotificationData.mTotalPageItemsIncludedInPostCount = 0;
        }
        if (!ListenerUtil.mutListener.listen(24226)) {
            sNotificationData.mediaItemToProgressMap.clear();
        }
        if (!ListenerUtil.mutListener.listen(24227)) {
            sNotificationData.mUploadedPostsCounted.clear();
        }
    }

    private boolean isPostAlreadyInPostCount(@NonNull PostImmutableModel post) {
        if (!ListenerUtil.mutListener.listen(24229)) {
            {
                long _loopCounter374 = 0;
                for (PostImmutableModel onePost : sNotificationData.mUploadedPostsCounted) {
                    ListenerUtil.loopListener.listen("_loopCounter374", ++_loopCounter374);
                    if (!ListenerUtil.mutListener.listen(24228)) {
                        if (onePost.getId() == post.getId()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private void addPostToPostCount(@NonNull PostImmutableModel post) {
        if (!ListenerUtil.mutListener.listen(24230)) {
            sNotificationData.mUploadedPostsCounted.add(post);
        }
    }

    // time
    static void cancelFinalNotification(Context context, @NonNull PostImmutableModel post) {
        if (!ListenerUtil.mutListener.listen(24232)) {
            if (context != null) {
                NotificationManager notificationManager = (NotificationManager) SystemServiceFactory.get(context, Context.NOTIFICATION_SERVICE);
                if (!ListenerUtil.mutListener.listen(24231)) {
                    notificationManager.cancel((int) getNotificationIdForPost(post));
                }
            }
        }
    }

    static void cancelFinalNotificationForMedia(Context context, @NonNull SiteModel site) {
        if (!ListenerUtil.mutListener.listen(24234)) {
            if (context != null) {
                NotificationManager notificationManager = (NotificationManager) SystemServiceFactory.get(context, Context.NOTIFICATION_SERVICE);
                if (!ListenerUtil.mutListener.listen(24233)) {
                    notificationManager.cancel((int) getNotificationIdForMedia(site));
                }
            }
        }
    }

    void updateNotificationSuccessForPost(@NonNull PostImmutableModel post, @NonNull SiteModel site, boolean isFirstTimePublish) {
        if (!ListenerUtil.mutListener.listen(24235)) {
            if (!WordPress.Companion.getAppIsInTheBackground()) {
                // only produce success notifications for the user if the app is in the background
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(24236)) {
            AppLog.d(AppLog.T.POSTS, "updateNotificationSuccessForPost");
        }
        // Get the shareableUrl
        String shareableUrl = WPMeShortlinks.getPostShortlink(site, post);
        if (!ListenerUtil.mutListener.listen(24239)) {
            if ((ListenerUtil.mutListener.listen(24237) ? (shareableUrl == null || !TextUtils.isEmpty(post.getLink())) : (shareableUrl == null && !TextUtils.isEmpty(post.getLink())))) {
                if (!ListenerUtil.mutListener.listen(24238)) {
                    shareableUrl = post.getLink();
                }
            }
        }
        // Notification builder
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(mContext.getApplicationContext(), mContext.getString(R.string.notification_channel_normal_id));
        String notificationTitle;
        String notificationMessage;
        String postTitle = TextUtils.isEmpty(post.getTitle()) ? mContext.getString(R.string.untitled) : post.getTitle();
        notificationTitle = "\"" + postTitle + "\" ";
        notificationMessage = site.getName();
        PostStatus status = PostStatus.fromPost(post);
        if (!ListenerUtil.mutListener.listen(24246)) {
            switch(status) {
                case DRAFT:
                    if (!ListenerUtil.mutListener.listen(24240)) {
                        notificationTitle += mContext.getString(R.string.draft_uploaded);
                    }
                    break;
                case SCHEDULED:
                    if (!ListenerUtil.mutListener.listen(24241)) {
                        notificationTitle += mContext.getString(post.isPage() ? R.string.page_scheduled : R.string.post_scheduled);
                    }
                    break;
                case PUBLISHED:
                    if (!ListenerUtil.mutListener.listen(24244)) {
                        if (post.isPage()) {
                            if (!ListenerUtil.mutListener.listen(24243)) {
                                notificationTitle += mContext.getString(isFirstTimePublish ? R.string.page_published : R.string.page_updated);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(24242)) {
                                notificationTitle += mContext.getString(isFirstTimePublish ? R.string.post_published : R.string.post_updated);
                            }
                        }
                    }
                    break;
                default:
                    if (!ListenerUtil.mutListener.listen(24245)) {
                        notificationTitle += mContext.getString(post.isPage() ? R.string.page_updated : R.string.post_updated);
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(24247)) {
            notificationBuilder.setSmallIcon(R.drawable.ic_app_white_24dp);
        }
        if (!ListenerUtil.mutListener.listen(24248)) {
            notificationBuilder.setColor(mContext.getResources().getColor(R.color.primary_50));
        }
        if (!ListenerUtil.mutListener.listen(24249)) {
            notificationBuilder.setContentTitle(notificationTitle);
        }
        if (!ListenerUtil.mutListener.listen(24250)) {
            notificationBuilder.setContentText(notificationMessage);
        }
        if (!ListenerUtil.mutListener.listen(24251)) {
            notificationBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(notificationMessage));
        }
        if (!ListenerUtil.mutListener.listen(24252)) {
            notificationBuilder.setOnlyAlertOnce(true);
        }
        if (!ListenerUtil.mutListener.listen(24253)) {
            notificationBuilder.setAutoCancel(true);
        }
        long notificationId = getNotificationIdForPost(post);
        NotificationType notificationType = NotificationType.POST_UPLOAD_SUCCESS;
        if (!ListenerUtil.mutListener.listen(24254)) {
            notificationBuilder.setDeleteIntent(NotificationsProcessingService.getPendingIntentForNotificationDismiss(mContext, (int) notificationId, notificationType));
        }
        Intent notificationIntent = getNotificationIntent(post, site);
        if (!ListenerUtil.mutListener.listen(24255)) {
            notificationIntent.putExtra(ARG_NOTIFICATION_TYPE, notificationType);
        }
        PendingIntent pendingIntentPost = PendingIntent.getActivity(mContext, (int) notificationId, notificationIntent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        if (!ListenerUtil.mutListener.listen(24256)) {
            notificationBuilder.setContentIntent(pendingIntentPost);
        }
        if (!ListenerUtil.mutListener.listen(24259)) {
            // Share intent - started if the user tap the share link button - only if the link exist
            if ((ListenerUtil.mutListener.listen(24257) ? (shareableUrl != null || PostStatus.fromPost(post) == PostStatus.PUBLISHED) : (shareableUrl != null && PostStatus.fromPost(post) == PostStatus.PUBLISHED))) {
                if (!ListenerUtil.mutListener.listen(24258)) {
                    notificationBuilder.addAction(R.drawable.ic_share_white_24dp, mContext.getString(R.string.share_action), getSharePendingIntent(post, shareableUrl));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24262)) {
            // add draft Publish action for drafts
            if ((ListenerUtil.mutListener.listen(24260) ? (PostStatus.fromPost(post) == PostStatus.DRAFT && PostStatus.fromPost(post) == PostStatus.PENDING) : (PostStatus.fromPost(post) == PostStatus.DRAFT || PostStatus.fromPost(post) == PostStatus.PENDING))) {
                Intent publishIntent = UploadService.getPublishPostServiceIntent(mContext, post, isFirstTimePublish);
                PendingIntent pendingIntent = PendingIntent.getService(mContext, 0, publishIntent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE);
                if (!ListenerUtil.mutListener.listen(24261)) {
                    notificationBuilder.addAction(R.drawable.ic_posts_white_24dp, mContext.getString(R.string.button_publish), pendingIntent);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24263)) {
            doNotify(notificationId, notificationBuilder.build(), notificationType);
        }
    }

    void updateNotificationSuccessForMedia(@NonNull List<MediaModel> mediaList, @NonNull SiteModel site) {
        if (!ListenerUtil.mutListener.listen(24266)) {
            // show the snackbar
            if ((ListenerUtil.mutListener.listen(24264) ? (mediaList != null || !mediaList.isEmpty()) : (mediaList != null && !mediaList.isEmpty()))) {
                String snackbarMessage = buildSnackbarSuccessMessageForMedia(mediaList.size());
                if (!ListenerUtil.mutListener.listen(24265)) {
                    EventBus.getDefault().postSticky(new UploadService.UploadMediaSuccessEvent(mediaList, snackbarMessage));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24267)) {
            if (!WordPress.Companion.getAppIsInTheBackground()) {
                // only produce success notifications for the user if the app is in the background
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(24268)) {
            AppLog.d(AppLog.T.MEDIA, "updateNotificationSuccessForMedia");
        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(mContext.getApplicationContext(), mContext.getString(R.string.notification_channel_normal_id));
        long notificationId = getNotificationIdForMedia(site);
        // Tap notification intent (open the media browser)
        Intent notificationIntent = new Intent(mContext, MediaBrowserActivity.class);
        if (!ListenerUtil.mutListener.listen(24269)) {
            notificationIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        if (!ListenerUtil.mutListener.listen(24270)) {
            notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if (!ListenerUtil.mutListener.listen(24271)) {
            notificationIntent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(24272)) {
            notificationIntent.setAction(String.valueOf(notificationId));
        }
        NotificationType notificationType = NotificationType.MEDIA_UPLOAD_SUCCESS;
        if (!ListenerUtil.mutListener.listen(24273)) {
            notificationIntent.putExtra(ARG_NOTIFICATION_TYPE, notificationType);
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, (int) notificationId, notificationIntent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);
        if (!ListenerUtil.mutListener.listen(24274)) {
            notificationBuilder.setSmallIcon(R.drawable.ic_app_white_24dp);
        }
        if (!ListenerUtil.mutListener.listen(24275)) {
            notificationBuilder.setColor(mContext.getResources().getColor(R.color.primary_50));
        }
        String notificationTitle = buildSuccessMessageForMedia(mediaList.size());
        String notificationMessage = TextUtils.isEmpty(site.getName()) ? mContext.getString(R.string.untitled) : site.getName();
        if (!ListenerUtil.mutListener.listen(24276)) {
            notificationBuilder.setContentTitle(notificationTitle);
        }
        if (!ListenerUtil.mutListener.listen(24277)) {
            notificationBuilder.setContentText(notificationMessage);
        }
        if (!ListenerUtil.mutListener.listen(24278)) {
            // notificationBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(newSuccessMessage));
            notificationBuilder.setContentIntent(pendingIntent);
        }
        if (!ListenerUtil.mutListener.listen(24279)) {
            notificationBuilder.setOnlyAlertOnce(true);
        }
        if (!ListenerUtil.mutListener.listen(24280)) {
            notificationBuilder.setAutoCancel(true);
        }
        if (!ListenerUtil.mutListener.listen(24281)) {
            notificationBuilder.setDeleteIntent(NotificationsProcessingService.getPendingIntentForNotificationDismiss(mContext, (int) notificationId, notificationType));
        }
        if (!ListenerUtil.mutListener.listen(24290)) {
            // Add WRITE POST action - only if there is media we can insert in the Post
            if ((ListenerUtil.mutListener.listen(24282) ? (mediaList != null || !mediaList.isEmpty()) : (mediaList != null && !mediaList.isEmpty()))) {
                ArrayList<MediaModel> mediaToIncludeInPost = new ArrayList<>(mediaList);
                Intent writePostIntent = new Intent(mContext, EditPostActivity.class);
                if (!ListenerUtil.mutListener.listen(24283)) {
                    writePostIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                }
                if (!ListenerUtil.mutListener.listen(24284)) {
                    writePostIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }
                if (!ListenerUtil.mutListener.listen(24285)) {
                    writePostIntent.putExtra(WordPress.SITE, site);
                }
                if (!ListenerUtil.mutListener.listen(24286)) {
                    writePostIntent.putExtra(EditPostActivity.EXTRA_IS_PAGE, false);
                }
                if (!ListenerUtil.mutListener.listen(24287)) {
                    writePostIntent.putExtra(EditPostActivity.EXTRA_INSERT_MEDIA, mediaToIncludeInPost);
                }
                if (!ListenerUtil.mutListener.listen(24288)) {
                    writePostIntent.setAction(String.valueOf(notificationId));
                }
                PendingIntent actionPendingIntent = PendingIntent.getActivity(mContext, RequestCodes.EDIT_POST, writePostIntent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE);
                if (!ListenerUtil.mutListener.listen(24289)) {
                    notificationBuilder.addAction(0, mContext.getString(R.string.media_files_uploaded_write_post), actionPendingIntent);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24291)) {
            doNotify(notificationId, notificationBuilder.build(), notificationType);
        }
    }

    public static long getNotificationIdForPost(PostImmutableModel post) {
        long postIdToUse = post.getRemotePostId();
        if (!ListenerUtil.mutListener.listen(24293)) {
            if (post.isLocalDraft()) {
                if (!ListenerUtil.mutListener.listen(24292)) {
                    postIdToUse = post.getId();
                }
            }
        }
        // a notice about the previous ones).
        return post.getLocalSiteId() + postIdToUse;
    }

    public static long getNotificationIdForMedia(SiteModel site) {
        if (site != null) {
            return BASE_MEDIA_ERROR_NOTIFICATION_ID + site.getId();
        } else {
            return BASE_MEDIA_ERROR_NOTIFICATION_ID;
        }
    }

    /*
     * This method will create an error notification with the description of the *final state* of the queue
     * for this Post (i.e. how many media items have been uploaded successfully and how many failed, as well
     * as the information for the Post itself if we couldn't upload it).
     *
     * In order to give the user a description of the *current state* of failed media items, you can pass a value
     * other than zero (0) in overrideMediaNotUploadedCount and this value will be shown instead.
     */
    void updateNotificationErrorForPost(@NonNull PostModel post, @NonNull SiteModel site, String errorMessage, int overrideMediaNotUploadedCount) {
        if (!ListenerUtil.mutListener.listen(24294)) {
            AppLog.d(AppLog.T.POSTS, "updateNotificationErrorForPost: " + errorMessage);
        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(mContext.getApplicationContext(), mContext.getString(R.string.notification_channel_normal_id));
        long notificationId = getNotificationIdForPost(post);
        Intent notificationIntent = getNotificationIntent(post, site);
        if (!ListenerUtil.mutListener.listen(24295)) {
            notificationIntent.setAction(String.valueOf(notificationId));
        }
        NotificationType notificationType = NotificationType.POST_UPLOAD_ERROR;
        if (!ListenerUtil.mutListener.listen(24296)) {
            notificationIntent.putExtra(ARG_NOTIFICATION_TYPE, notificationType);
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, (int) notificationId, notificationIntent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        if (!ListenerUtil.mutListener.listen(24297)) {
            notificationBuilder.setSmallIcon(android.R.drawable.stat_notify_error);
        }
        String postTitle = TextUtils.isEmpty(post.getTitle()) ? mContext.getString(R.string.untitled) : post.getTitle();
        String notificationTitle = String.format(mContext.getString(R.string.upload_failed_param), postTitle);
        String newErrorMessage = buildErrorMessageMixed(overrideMediaNotUploadedCount);
        String snackbarMessage = buildSnackbarErrorMessage(newErrorMessage, errorMessage);
        if (!ListenerUtil.mutListener.listen(24298)) {
            notificationBuilder.setContentTitle(notificationTitle);
        }
        if (!ListenerUtil.mutListener.listen(24299)) {
            notificationBuilder.setContentText(newErrorMessage);
        }
        if (!ListenerUtil.mutListener.listen(24300)) {
            notificationBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(newErrorMessage));
        }
        if (!ListenerUtil.mutListener.listen(24301)) {
            notificationBuilder.setContentIntent(pendingIntent);
        }
        if (!ListenerUtil.mutListener.listen(24302)) {
            notificationBuilder.setAutoCancel(true);
        }
        if (!ListenerUtil.mutListener.listen(24303)) {
            notificationBuilder.setOnlyAlertOnce(true);
        }
        if (!ListenerUtil.mutListener.listen(24304)) {
            notificationBuilder.setDeleteIntent(NotificationsProcessingService.getPendingIntentForNotificationDismiss(mContext, (int) notificationId, notificationType));
        }
        if (!ListenerUtil.mutListener.listen(24306)) {
            // Add RETRY action - only available on Aztec
            if (AppPrefs.isAztecEditorEnabled()) {
                Intent publishIntent = UploadService.getRetryUploadServiceIntent(mContext, post, PostUtils.isFirstTimePublish(post));
                PendingIntent actionPendingIntent = PendingIntent.getService(mContext, 0, publishIntent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE);
                if (!ListenerUtil.mutListener.listen(24305)) {
                    notificationBuilder.addAction(0, mContext.getString(R.string.retry), actionPendingIntent).setColor(mContext.getResources().getColor(R.color.accent));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24307)) {
            EventBus.getDefault().postSticky(new UploadService.UploadErrorEvent(post, snackbarMessage));
        }
        if (!ListenerUtil.mutListener.listen(24308)) {
            doNotify(notificationId, notificationBuilder.build(), notificationType);
        }
    }

    @NonNull
    private Intent getNotificationIntent(@NonNull PostImmutableModel post, @NonNull SiteModel site) {
        // Tap notification intent (open the post/page list)
        Intent notificationIntent;
        if (post.isPage()) {
            notificationIntent = new Intent(mContext, PagesActivity.class);
            if (!ListenerUtil.mutListener.listen(24310)) {
                notificationIntent.putExtra(EXTRA_PAGE_REMOTE_ID_KEY, post.getRemotePostId());
            }
            if (!ListenerUtil.mutListener.listen(24311)) {
                notificationIntent.putExtra(WordPress.SITE, site);
            }
        } else {
            notificationIntent = PostsListActivity.buildIntent(mContext, site);
            if (!ListenerUtil.mutListener.listen(24309)) {
                notificationIntent.putExtra(PostsListActivityKt.EXTRA_TARGET_POST_LOCAL_ID, post.getId());
            }
        }
        if (!ListenerUtil.mutListener.listen(24312)) {
            notificationIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        if (!ListenerUtil.mutListener.listen(24313)) {
            notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        return notificationIntent;
    }

    @Nullable
    private PendingIntent getSharePendingIntent(@NonNull PostImmutableModel post, String shareableUrl) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        if (!ListenerUtil.mutListener.listen(24314)) {
            shareIntent.setType("text/plain");
        }
        if (!ListenerUtil.mutListener.listen(24315)) {
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareableUrl);
        }
        if (!ListenerUtil.mutListener.listen(24316)) {
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, post.getTitle());
        }
        if (!ListenerUtil.mutListener.listen(24317)) {
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        TaskStackBuilder builder = TaskStackBuilder.create(mContext);
        if (!ListenerUtil.mutListener.listen(24318)) {
            builder.addNextIntentWithParentStack(shareIntent);
        }
        PendingIntent pendingIntent = builder.getPendingIntent(0, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        return pendingIntent;
    }

    void updateNotificationErrorForMedia(@NonNull List<MediaModel> mediaList, @NonNull SiteModel site, String errorMessage) {
        if (!ListenerUtil.mutListener.listen(24319)) {
            AppLog.d(AppLog.T.MEDIA, "updateNotificationErrorForMedia: " + errorMessage);
        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(mContext.getApplicationContext(), mContext.getString(R.string.notification_channel_normal_id));
        long notificationId = getNotificationIdForMedia(site);
        // Tap notification intent (open the media browser)
        Intent notificationIntent = new Intent(mContext, MediaBrowserActivity.class);
        if (!ListenerUtil.mutListener.listen(24320)) {
            notificationIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        }
        if (!ListenerUtil.mutListener.listen(24321)) {
            notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if (!ListenerUtil.mutListener.listen(24322)) {
            notificationIntent.putExtra(WordPress.SITE, site);
        }
        if (!ListenerUtil.mutListener.listen(24323)) {
            notificationIntent.setAction(String.valueOf(notificationId));
        }
        NotificationType notificationType = NotificationType.MEDIA_UPLOAD_ERROR;
        if (!ListenerUtil.mutListener.listen(24324)) {
            notificationIntent.putExtra(ARG_NOTIFICATION_TYPE, notificationType);
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, (int) notificationId, notificationIntent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);
        if (!ListenerUtil.mutListener.listen(24325)) {
            notificationBuilder.setSmallIcon(android.R.drawable.stat_notify_error);
        }
        String siteName = TextUtils.isEmpty(site.getName()) ? mContext.getString(R.string.untitled) : site.getName();
        String notificationTitle = String.format(mContext.getString(R.string.upload_failed_param), siteName);
        String newErrorMessage = buildErrorMessageForMedia(mediaList.size());
        String snackbarMessage = buildSnackbarErrorMessage(newErrorMessage, errorMessage);
        if (!ListenerUtil.mutListener.listen(24326)) {
            notificationBuilder.setContentTitle(notificationTitle);
        }
        if (!ListenerUtil.mutListener.listen(24327)) {
            notificationBuilder.setContentText(newErrorMessage);
        }
        if (!ListenerUtil.mutListener.listen(24328)) {
            notificationBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(newErrorMessage));
        }
        if (!ListenerUtil.mutListener.listen(24329)) {
            notificationBuilder.setContentIntent(pendingIntent);
        }
        if (!ListenerUtil.mutListener.listen(24330)) {
            notificationBuilder.setAutoCancel(true);
        }
        if (!ListenerUtil.mutListener.listen(24331)) {
            notificationBuilder.setOnlyAlertOnce(true);
        }
        if (!ListenerUtil.mutListener.listen(24332)) {
            notificationBuilder.setDeleteIntent(NotificationsProcessingService.getPendingIntentForNotificationDismiss(mContext, (int) notificationId, notificationType));
        }
        if (!ListenerUtil.mutListener.listen(24336)) {
            // Add RETRY action - only if there is media to retry
            if ((ListenerUtil.mutListener.listen(24333) ? (mediaList != null || !mediaList.isEmpty()) : (mediaList != null && !mediaList.isEmpty()))) {
                ArrayList<MediaModel> mediaListToRetry = new ArrayList<>();
                if (!ListenerUtil.mutListener.listen(24334)) {
                    mediaListToRetry.addAll(mediaList);
                }
                Intent publishIntent = UploadService.getUploadMediaServiceIntent(mContext, mediaListToRetry, true);
                PendingIntent actionPendingIntent = PendingIntent.getService(mContext, 1, publishIntent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE);
                if (!ListenerUtil.mutListener.listen(24335)) {
                    notificationBuilder.addAction(0, mContext.getString(R.string.retry), actionPendingIntent).setColor(mContext.getResources().getColor(R.color.accent));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24337)) {
            EventBus.getDefault().postSticky(new UploadService.UploadErrorEvent(mediaList, snackbarMessage));
        }
        if (!ListenerUtil.mutListener.listen(24338)) {
            doNotify(notificationId, notificationBuilder.build(), notificationType);
        }
    }

    private String buildErrorMessageMixed(int overrideMediaNotUploadedCount) {
        // i.e. "1 post, with 3 media files not uploaded (9 successfully uploaded)"
        String newErrorMessage = "";
        int postItemsNotUploaded = (ListenerUtil.mutListener.listen(24343) ? (sNotificationData.mTotalPostItems >= 0) : (ListenerUtil.mutListener.listen(24342) ? (sNotificationData.mTotalPostItems <= 0) : (ListenerUtil.mutListener.listen(24341) ? (sNotificationData.mTotalPostItems < 0) : (ListenerUtil.mutListener.listen(24340) ? (sNotificationData.mTotalPostItems != 0) : (ListenerUtil.mutListener.listen(24339) ? (sNotificationData.mTotalPostItems == 0) : (sNotificationData.mTotalPostItems > 0)))))) ? (ListenerUtil.mutListener.listen(24347) ? (sNotificationData.mTotalPostItems % getCurrentPostItem()) : (ListenerUtil.mutListener.listen(24346) ? (sNotificationData.mTotalPostItems / getCurrentPostItem()) : (ListenerUtil.mutListener.listen(24345) ? (sNotificationData.mTotalPostItems * getCurrentPostItem()) : (ListenerUtil.mutListener.listen(24344) ? (sNotificationData.mTotalPostItems + getCurrentPostItem()) : (sNotificationData.mTotalPostItems - getCurrentPostItem()))))) : 0;
        int mediaItemsNotUploaded = (ListenerUtil.mutListener.listen(24352) ? (overrideMediaNotUploadedCount >= 0) : (ListenerUtil.mutListener.listen(24351) ? (overrideMediaNotUploadedCount <= 0) : (ListenerUtil.mutListener.listen(24350) ? (overrideMediaNotUploadedCount < 0) : (ListenerUtil.mutListener.listen(24349) ? (overrideMediaNotUploadedCount != 0) : (ListenerUtil.mutListener.listen(24348) ? (overrideMediaNotUploadedCount == 0) : (overrideMediaNotUploadedCount > 0)))))) ? overrideMediaNotUploadedCount : (ListenerUtil.mutListener.listen(24356) ? (sNotificationData.mTotalMediaItems % getCurrentMediaItem()) : (ListenerUtil.mutListener.listen(24355) ? (sNotificationData.mTotalMediaItems / getCurrentMediaItem()) : (ListenerUtil.mutListener.listen(24354) ? (sNotificationData.mTotalMediaItems * getCurrentMediaItem()) : (ListenerUtil.mutListener.listen(24353) ? (sNotificationData.mTotalMediaItems + getCurrentMediaItem()) : (sNotificationData.mTotalMediaItems - getCurrentMediaItem())))));
        if (!ListenerUtil.mutListener.listen(24423)) {
            if ((ListenerUtil.mutListener.listen(24367) ? ((ListenerUtil.mutListener.listen(24361) ? (postItemsNotUploaded >= 0) : (ListenerUtil.mutListener.listen(24360) ? (postItemsNotUploaded <= 0) : (ListenerUtil.mutListener.listen(24359) ? (postItemsNotUploaded < 0) : (ListenerUtil.mutListener.listen(24358) ? (postItemsNotUploaded != 0) : (ListenerUtil.mutListener.listen(24357) ? (postItemsNotUploaded == 0) : (postItemsNotUploaded > 0)))))) || (ListenerUtil.mutListener.listen(24366) ? (mediaItemsNotUploaded >= 0) : (ListenerUtil.mutListener.listen(24365) ? (mediaItemsNotUploaded <= 0) : (ListenerUtil.mutListener.listen(24364) ? (mediaItemsNotUploaded < 0) : (ListenerUtil.mutListener.listen(24363) ? (mediaItemsNotUploaded != 0) : (ListenerUtil.mutListener.listen(24362) ? (mediaItemsNotUploaded == 0) : (mediaItemsNotUploaded > 0))))))) : ((ListenerUtil.mutListener.listen(24361) ? (postItemsNotUploaded >= 0) : (ListenerUtil.mutListener.listen(24360) ? (postItemsNotUploaded <= 0) : (ListenerUtil.mutListener.listen(24359) ? (postItemsNotUploaded < 0) : (ListenerUtil.mutListener.listen(24358) ? (postItemsNotUploaded != 0) : (ListenerUtil.mutListener.listen(24357) ? (postItemsNotUploaded == 0) : (postItemsNotUploaded > 0)))))) && (ListenerUtil.mutListener.listen(24366) ? (mediaItemsNotUploaded >= 0) : (ListenerUtil.mutListener.listen(24365) ? (mediaItemsNotUploaded <= 0) : (ListenerUtil.mutListener.listen(24364) ? (mediaItemsNotUploaded < 0) : (ListenerUtil.mutListener.listen(24363) ? (mediaItemsNotUploaded != 0) : (ListenerUtil.mutListener.listen(24362) ? (mediaItemsNotUploaded == 0) : (mediaItemsNotUploaded > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(24422)) {
                    switch(getPagesAndOrPostsType(postItemsNotUploaded)) {
                        case POST:
                            if (!ListenerUtil.mutListener.listen(24397)) {
                                newErrorMessage = ((ListenerUtil.mutListener.listen(24396) ? (mediaItemsNotUploaded >= 1) : (ListenerUtil.mutListener.listen(24395) ? (mediaItemsNotUploaded <= 1) : (ListenerUtil.mutListener.listen(24394) ? (mediaItemsNotUploaded > 1) : (ListenerUtil.mutListener.listen(24393) ? (mediaItemsNotUploaded < 1) : (ListenerUtil.mutListener.listen(24392) ? (mediaItemsNotUploaded != 1) : (mediaItemsNotUploaded == 1)))))) ? mContext.getString(R.string.media_file_post_singular_mixed_not_uploaded_one_file) : String.format(mContext.getString(R.string.media_file_post_singular_mixed_not_uploaded_files_plural), mediaItemsNotUploaded));
                            }
                            break;
                        case PAGE:
                            if (!ListenerUtil.mutListener.listen(24403)) {
                                newErrorMessage = ((ListenerUtil.mutListener.listen(24402) ? (mediaItemsNotUploaded >= 1) : (ListenerUtil.mutListener.listen(24401) ? (mediaItemsNotUploaded <= 1) : (ListenerUtil.mutListener.listen(24400) ? (mediaItemsNotUploaded > 1) : (ListenerUtil.mutListener.listen(24399) ? (mediaItemsNotUploaded < 1) : (ListenerUtil.mutListener.listen(24398) ? (mediaItemsNotUploaded != 1) : (mediaItemsNotUploaded == 1)))))) ? mContext.getString(R.string.media_file_page_singular_mixed_not_uploaded_one_file) : String.format(mContext.getString(R.string.media_file_page_singular_mixed_not_uploaded_files_plural), mediaItemsNotUploaded));
                            }
                            break;
                        case PAGES:
                            if (!ListenerUtil.mutListener.listen(24409)) {
                                newErrorMessage = ((ListenerUtil.mutListener.listen(24408) ? (mediaItemsNotUploaded >= 1) : (ListenerUtil.mutListener.listen(24407) ? (mediaItemsNotUploaded <= 1) : (ListenerUtil.mutListener.listen(24406) ? (mediaItemsNotUploaded > 1) : (ListenerUtil.mutListener.listen(24405) ? (mediaItemsNotUploaded < 1) : (ListenerUtil.mutListener.listen(24404) ? (mediaItemsNotUploaded != 1) : (mediaItemsNotUploaded == 1)))))) ? String.format(mContext.getString(R.string.media_file_pages_plural_mixed_not_uploaded_one_file), postItemsNotUploaded) : String.format(mContext.getString(R.string.media_file_pages_plural_mixed_not_uploaded_files_plural), postItemsNotUploaded, mediaItemsNotUploaded));
                            }
                            break;
                        case PAGES_OR_POSTS:
                            if (!ListenerUtil.mutListener.listen(24415)) {
                                newErrorMessage = ((ListenerUtil.mutListener.listen(24414) ? (mediaItemsNotUploaded >= 1) : (ListenerUtil.mutListener.listen(24413) ? (mediaItemsNotUploaded <= 1) : (ListenerUtil.mutListener.listen(24412) ? (mediaItemsNotUploaded > 1) : (ListenerUtil.mutListener.listen(24411) ? (mediaItemsNotUploaded < 1) : (ListenerUtil.mutListener.listen(24410) ? (mediaItemsNotUploaded != 1) : (mediaItemsNotUploaded == 1)))))) ? String.format(mContext.getString(R.string.media_file_pages_and_posts_mixed_not_uploaded_one_file), postItemsNotUploaded) : String.format(mContext.getString(R.string.media_file_pages_and_posts_mixed_not_uploaded_files_plural), postItemsNotUploaded, mediaItemsNotUploaded));
                            }
                            break;
                        case POSTS:
                        default:
                            if (!ListenerUtil.mutListener.listen(24421)) {
                                newErrorMessage = ((ListenerUtil.mutListener.listen(24420) ? (mediaItemsNotUploaded >= 1) : (ListenerUtil.mutListener.listen(24419) ? (mediaItemsNotUploaded <= 1) : (ListenerUtil.mutListener.listen(24418) ? (mediaItemsNotUploaded > 1) : (ListenerUtil.mutListener.listen(24417) ? (mediaItemsNotUploaded < 1) : (ListenerUtil.mutListener.listen(24416) ? (mediaItemsNotUploaded != 1) : (mediaItemsNotUploaded == 1)))))) ? String.format(mContext.getString(R.string.media_file_posts_plural_mixed_not_uploaded_one_file), postItemsNotUploaded) : String.format(mContext.getString(R.string.media_file_posts_plural_mixed_not_uploaded_files_plural), postItemsNotUploaded, mediaItemsNotUploaded));
                            }
                            break;
                    }
                }
            } else if ((ListenerUtil.mutListener.listen(24372) ? (postItemsNotUploaded >= 0) : (ListenerUtil.mutListener.listen(24371) ? (postItemsNotUploaded <= 0) : (ListenerUtil.mutListener.listen(24370) ? (postItemsNotUploaded < 0) : (ListenerUtil.mutListener.listen(24369) ? (postItemsNotUploaded != 0) : (ListenerUtil.mutListener.listen(24368) ? (postItemsNotUploaded == 0) : (postItemsNotUploaded > 0))))))) {
                if (!ListenerUtil.mutListener.listen(24391)) {
                    switch(getPagesAndOrPostsType(postItemsNotUploaded)) {
                        case POST:
                            if (!ListenerUtil.mutListener.listen(24386)) {
                                newErrorMessage = mContext.getString(R.string.media_file_post_singular_only_not_uploaded);
                            }
                            break;
                        case PAGE:
                            if (!ListenerUtil.mutListener.listen(24387)) {
                                newErrorMessage = mContext.getString(R.string.media_file_page_singular_only_not_uploaded);
                            }
                            break;
                        case PAGES:
                            if (!ListenerUtil.mutListener.listen(24388)) {
                                newErrorMessage = String.format(mContext.getString(R.string.media_file_pages_plural_only_not_uploaded), postItemsNotUploaded);
                            }
                            break;
                        case PAGES_OR_POSTS:
                            if (!ListenerUtil.mutListener.listen(24389)) {
                                newErrorMessage = String.format(mContext.getString(R.string.media_file_pages_and_posts_only_not_uploaded), postItemsNotUploaded);
                            }
                            break;
                        case POSTS:
                        default:
                            if (!ListenerUtil.mutListener.listen(24390)) {
                                newErrorMessage = String.format(mContext.getString(R.string.media_file_posts_plural_only_not_uploaded), postItemsNotUploaded);
                            }
                            break;
                    }
                }
            } else if ((ListenerUtil.mutListener.listen(24377) ? (mediaItemsNotUploaded >= 0) : (ListenerUtil.mutListener.listen(24376) ? (mediaItemsNotUploaded <= 0) : (ListenerUtil.mutListener.listen(24375) ? (mediaItemsNotUploaded < 0) : (ListenerUtil.mutListener.listen(24374) ? (mediaItemsNotUploaded != 0) : (ListenerUtil.mutListener.listen(24373) ? (mediaItemsNotUploaded == 0) : (mediaItemsNotUploaded > 0))))))) {
                if (!ListenerUtil.mutListener.listen(24385)) {
                    if ((ListenerUtil.mutListener.listen(24382) ? (mediaItemsNotUploaded >= 1) : (ListenerUtil.mutListener.listen(24381) ? (mediaItemsNotUploaded <= 1) : (ListenerUtil.mutListener.listen(24380) ? (mediaItemsNotUploaded > 1) : (ListenerUtil.mutListener.listen(24379) ? (mediaItemsNotUploaded < 1) : (ListenerUtil.mutListener.listen(24378) ? (mediaItemsNotUploaded != 1) : (mediaItemsNotUploaded == 1))))))) {
                        if (!ListenerUtil.mutListener.listen(24384)) {
                            newErrorMessage = mContext.getString(R.string.media_file_not_uploaded);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(24383)) {
                            newErrorMessage = String.format(mContext.getString(R.string.media_files_not_uploaded), mediaItemsNotUploaded);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24436)) {
            if ((ListenerUtil.mutListener.listen(24434) ? ((ListenerUtil.mutListener.listen(24428) ? (mediaItemsNotUploaded >= 0) : (ListenerUtil.mutListener.listen(24427) ? (mediaItemsNotUploaded <= 0) : (ListenerUtil.mutListener.listen(24426) ? (mediaItemsNotUploaded < 0) : (ListenerUtil.mutListener.listen(24425) ? (mediaItemsNotUploaded != 0) : (ListenerUtil.mutListener.listen(24424) ? (mediaItemsNotUploaded == 0) : (mediaItemsNotUploaded > 0)))))) || (ListenerUtil.mutListener.listen(24433) ? ((getCurrentMediaItem()) >= 0) : (ListenerUtil.mutListener.listen(24432) ? ((getCurrentMediaItem()) <= 0) : (ListenerUtil.mutListener.listen(24431) ? ((getCurrentMediaItem()) < 0) : (ListenerUtil.mutListener.listen(24430) ? ((getCurrentMediaItem()) != 0) : (ListenerUtil.mutListener.listen(24429) ? ((getCurrentMediaItem()) == 0) : ((getCurrentMediaItem()) > 0))))))) : ((ListenerUtil.mutListener.listen(24428) ? (mediaItemsNotUploaded >= 0) : (ListenerUtil.mutListener.listen(24427) ? (mediaItemsNotUploaded <= 0) : (ListenerUtil.mutListener.listen(24426) ? (mediaItemsNotUploaded < 0) : (ListenerUtil.mutListener.listen(24425) ? (mediaItemsNotUploaded != 0) : (ListenerUtil.mutListener.listen(24424) ? (mediaItemsNotUploaded == 0) : (mediaItemsNotUploaded > 0)))))) && (ListenerUtil.mutListener.listen(24433) ? ((getCurrentMediaItem()) >= 0) : (ListenerUtil.mutListener.listen(24432) ? ((getCurrentMediaItem()) <= 0) : (ListenerUtil.mutListener.listen(24431) ? ((getCurrentMediaItem()) < 0) : (ListenerUtil.mutListener.listen(24430) ? ((getCurrentMediaItem()) != 0) : (ListenerUtil.mutListener.listen(24429) ? ((getCurrentMediaItem()) == 0) : ((getCurrentMediaItem()) > 0))))))))) {
                if (!ListenerUtil.mutListener.listen(24435)) {
                    // some media items were uploaded successfully
                    newErrorMessage += String.format(mContext.getString(R.string.media_files_uploaded_successfully), sNotificationData.mCurrentMediaItem);
                }
            }
        }
        return newErrorMessage;
    }

    private String buildErrorMessageForMedia(int mediaItemsNotUploaded) {
        String newErrorMessage = "";
        if (!ListenerUtil.mutListener.listen(24457)) {
            if ((ListenerUtil.mutListener.listen(24441) ? (mediaItemsNotUploaded >= 0) : (ListenerUtil.mutListener.listen(24440) ? (mediaItemsNotUploaded <= 0) : (ListenerUtil.mutListener.listen(24439) ? (mediaItemsNotUploaded < 0) : (ListenerUtil.mutListener.listen(24438) ? (mediaItemsNotUploaded != 0) : (ListenerUtil.mutListener.listen(24437) ? (mediaItemsNotUploaded == 0) : (mediaItemsNotUploaded > 0))))))) {
                if (!ListenerUtil.mutListener.listen(24449)) {
                    if ((ListenerUtil.mutListener.listen(24446) ? (mediaItemsNotUploaded >= 1) : (ListenerUtil.mutListener.listen(24445) ? (mediaItemsNotUploaded <= 1) : (ListenerUtil.mutListener.listen(24444) ? (mediaItemsNotUploaded > 1) : (ListenerUtil.mutListener.listen(24443) ? (mediaItemsNotUploaded < 1) : (ListenerUtil.mutListener.listen(24442) ? (mediaItemsNotUploaded != 1) : (mediaItemsNotUploaded == 1))))))) {
                        if (!ListenerUtil.mutListener.listen(24448)) {
                            newErrorMessage += mContext.getString(R.string.media_file_not_uploaded);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(24447)) {
                            newErrorMessage += String.format(mContext.getString(R.string.media_files_not_uploaded), mediaItemsNotUploaded);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(24456)) {
                    if ((ListenerUtil.mutListener.listen(24454) ? (mediaItemsNotUploaded >= sNotificationData.mCurrentMediaItem) : (ListenerUtil.mutListener.listen(24453) ? (mediaItemsNotUploaded > sNotificationData.mCurrentMediaItem) : (ListenerUtil.mutListener.listen(24452) ? (mediaItemsNotUploaded < sNotificationData.mCurrentMediaItem) : (ListenerUtil.mutListener.listen(24451) ? (mediaItemsNotUploaded != sNotificationData.mCurrentMediaItem) : (ListenerUtil.mutListener.listen(24450) ? (mediaItemsNotUploaded == sNotificationData.mCurrentMediaItem) : (mediaItemsNotUploaded <= sNotificationData.mCurrentMediaItem))))))) {
                        if (!ListenerUtil.mutListener.listen(24455)) {
                            // some media items were uploaded successfully
                            newErrorMessage += " " + String.format(mContext.getString(R.string.media_files_uploaded_successfully), sNotificationData.mCurrentMediaItem);
                        }
                    }
                }
            }
        }
        return newErrorMessage;
    }

    private String buildSuccessMessageForMedia(int mediaItemsUploaded) {
        // all media items were uploaded successfully
        String successMessage = (ListenerUtil.mutListener.listen(24462) ? (mediaItemsUploaded >= 1) : (ListenerUtil.mutListener.listen(24461) ? (mediaItemsUploaded <= 1) : (ListenerUtil.mutListener.listen(24460) ? (mediaItemsUploaded > 1) : (ListenerUtil.mutListener.listen(24459) ? (mediaItemsUploaded < 1) : (ListenerUtil.mutListener.listen(24458) ? (mediaItemsUploaded != 1) : (mediaItemsUploaded == 1)))))) ? mContext.getString(R.string.media_file_uploaded) : String.format(mContext.getString(R.string.media_all_files_uploaded_successfully), mediaItemsUploaded);
        return successMessage;
    }

    private String buildSnackbarSuccessMessageForMedia(int mediaItemsUploaded) {
        String successMessage = "";
        if (!ListenerUtil.mutListener.listen(24476)) {
            if ((ListenerUtil.mutListener.listen(24467) ? (mediaItemsUploaded >= 0) : (ListenerUtil.mutListener.listen(24466) ? (mediaItemsUploaded <= 0) : (ListenerUtil.mutListener.listen(24465) ? (mediaItemsUploaded < 0) : (ListenerUtil.mutListener.listen(24464) ? (mediaItemsUploaded != 0) : (ListenerUtil.mutListener.listen(24463) ? (mediaItemsUploaded == 0) : (mediaItemsUploaded > 0))))))) {
                if (!ListenerUtil.mutListener.listen(24475)) {
                    if ((ListenerUtil.mutListener.listen(24472) ? (mediaItemsUploaded >= 1) : (ListenerUtil.mutListener.listen(24471) ? (mediaItemsUploaded <= 1) : (ListenerUtil.mutListener.listen(24470) ? (mediaItemsUploaded > 1) : (ListenerUtil.mutListener.listen(24469) ? (mediaItemsUploaded < 1) : (ListenerUtil.mutListener.listen(24468) ? (mediaItemsUploaded != 1) : (mediaItemsUploaded == 1))))))) {
                        if (!ListenerUtil.mutListener.listen(24474)) {
                            successMessage += mContext.getString(R.string.media_file_uploaded);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(24473)) {
                            successMessage += String.format(mContext.getString(R.string.media_files_uploaded), mediaItemsUploaded);
                        }
                    }
                }
            }
        }
        return successMessage;
    }

    private String buildSnackbarErrorMessage(String newErrorMessage, String detailErrorMessage) {
        // now append the detailed error message below
        String snackbarMessage = new String(newErrorMessage);
        if (!ListenerUtil.mutListener.listen(24484)) {
            if ((ListenerUtil.mutListener.listen(24481) ? (newErrorMessage.length() >= 0) : (ListenerUtil.mutListener.listen(24480) ? (newErrorMessage.length() <= 0) : (ListenerUtil.mutListener.listen(24479) ? (newErrorMessage.length() < 0) : (ListenerUtil.mutListener.listen(24478) ? (newErrorMessage.length() != 0) : (ListenerUtil.mutListener.listen(24477) ? (newErrorMessage.length() == 0) : (newErrorMessage.length() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(24483)) {
                    snackbarMessage += "\n" + detailErrorMessage;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(24482)) {
                    snackbarMessage = detailErrorMessage;
                }
            }
        }
        return snackbarMessage;
    }

    void updateNotificationProgressForMedia(MediaModel media, float progress) {
        if (!ListenerUtil.mutListener.listen(24496)) {
            if ((ListenerUtil.mutListener.listen(24495) ? ((ListenerUtil.mutListener.listen(24489) ? (sNotificationData.mTotalMediaItems >= 0) : (ListenerUtil.mutListener.listen(24488) ? (sNotificationData.mTotalMediaItems <= 0) : (ListenerUtil.mutListener.listen(24487) ? (sNotificationData.mTotalMediaItems > 0) : (ListenerUtil.mutListener.listen(24486) ? (sNotificationData.mTotalMediaItems < 0) : (ListenerUtil.mutListener.listen(24485) ? (sNotificationData.mTotalMediaItems != 0) : (sNotificationData.mTotalMediaItems == 0)))))) || (ListenerUtil.mutListener.listen(24494) ? (sNotificationData.mTotalPostItems >= 0) : (ListenerUtil.mutListener.listen(24493) ? (sNotificationData.mTotalPostItems <= 0) : (ListenerUtil.mutListener.listen(24492) ? (sNotificationData.mTotalPostItems > 0) : (ListenerUtil.mutListener.listen(24491) ? (sNotificationData.mTotalPostItems < 0) : (ListenerUtil.mutListener.listen(24490) ? (sNotificationData.mTotalPostItems != 0) : (sNotificationData.mTotalPostItems == 0))))))) : ((ListenerUtil.mutListener.listen(24489) ? (sNotificationData.mTotalMediaItems >= 0) : (ListenerUtil.mutListener.listen(24488) ? (sNotificationData.mTotalMediaItems <= 0) : (ListenerUtil.mutListener.listen(24487) ? (sNotificationData.mTotalMediaItems > 0) : (ListenerUtil.mutListener.listen(24486) ? (sNotificationData.mTotalMediaItems < 0) : (ListenerUtil.mutListener.listen(24485) ? (sNotificationData.mTotalMediaItems != 0) : (sNotificationData.mTotalMediaItems == 0)))))) && (ListenerUtil.mutListener.listen(24494) ? (sNotificationData.mTotalPostItems >= 0) : (ListenerUtil.mutListener.listen(24493) ? (sNotificationData.mTotalPostItems <= 0) : (ListenerUtil.mutListener.listen(24492) ? (sNotificationData.mTotalPostItems > 0) : (ListenerUtil.mutListener.listen(24491) ? (sNotificationData.mTotalPostItems < 0) : (ListenerUtil.mutListener.listen(24490) ? (sNotificationData.mTotalPostItems != 0) : (sNotificationData.mTotalPostItems == 0))))))))) {
                return;
            }
        }
        // progress event from FluxC after that. We just need to avoid re-adding the item to the map.
        Float currentProgress = sNotificationData.mediaItemToProgressMap.get(media.getId());
        if (!ListenerUtil.mutListener.listen(24509)) {
            // also, only set updates in increments of 5% per media item to avoid lots of notification updates
            if ((ListenerUtil.mutListener.listen(24506) ? (currentProgress != null || (ListenerUtil.mutListener.listen(24505) ? (progress >= ((ListenerUtil.mutListener.listen(24500) ? (currentProgress % 0.05f) : (ListenerUtil.mutListener.listen(24499) ? (currentProgress / 0.05f) : (ListenerUtil.mutListener.listen(24498) ? (currentProgress * 0.05f) : (ListenerUtil.mutListener.listen(24497) ? (currentProgress - 0.05f) : (currentProgress + 0.05f))))))) : (ListenerUtil.mutListener.listen(24504) ? (progress <= ((ListenerUtil.mutListener.listen(24500) ? (currentProgress % 0.05f) : (ListenerUtil.mutListener.listen(24499) ? (currentProgress / 0.05f) : (ListenerUtil.mutListener.listen(24498) ? (currentProgress * 0.05f) : (ListenerUtil.mutListener.listen(24497) ? (currentProgress - 0.05f) : (currentProgress + 0.05f))))))) : (ListenerUtil.mutListener.listen(24503) ? (progress < ((ListenerUtil.mutListener.listen(24500) ? (currentProgress % 0.05f) : (ListenerUtil.mutListener.listen(24499) ? (currentProgress / 0.05f) : (ListenerUtil.mutListener.listen(24498) ? (currentProgress * 0.05f) : (ListenerUtil.mutListener.listen(24497) ? (currentProgress - 0.05f) : (currentProgress + 0.05f))))))) : (ListenerUtil.mutListener.listen(24502) ? (progress != ((ListenerUtil.mutListener.listen(24500) ? (currentProgress % 0.05f) : (ListenerUtil.mutListener.listen(24499) ? (currentProgress / 0.05f) : (ListenerUtil.mutListener.listen(24498) ? (currentProgress * 0.05f) : (ListenerUtil.mutListener.listen(24497) ? (currentProgress - 0.05f) : (currentProgress + 0.05f))))))) : (ListenerUtil.mutListener.listen(24501) ? (progress == ((ListenerUtil.mutListener.listen(24500) ? (currentProgress % 0.05f) : (ListenerUtil.mutListener.listen(24499) ? (currentProgress / 0.05f) : (ListenerUtil.mutListener.listen(24498) ? (currentProgress * 0.05f) : (ListenerUtil.mutListener.listen(24497) ? (currentProgress - 0.05f) : (currentProgress + 0.05f))))))) : (progress > ((ListenerUtil.mutListener.listen(24500) ? (currentProgress % 0.05f) : (ListenerUtil.mutListener.listen(24499) ? (currentProgress / 0.05f) : (ListenerUtil.mutListener.listen(24498) ? (currentProgress * 0.05f) : (ListenerUtil.mutListener.listen(24497) ? (currentProgress - 0.05f) : (currentProgress + 0.05f))))))))))))) : (currentProgress != null && (ListenerUtil.mutListener.listen(24505) ? (progress >= ((ListenerUtil.mutListener.listen(24500) ? (currentProgress % 0.05f) : (ListenerUtil.mutListener.listen(24499) ? (currentProgress / 0.05f) : (ListenerUtil.mutListener.listen(24498) ? (currentProgress * 0.05f) : (ListenerUtil.mutListener.listen(24497) ? (currentProgress - 0.05f) : (currentProgress + 0.05f))))))) : (ListenerUtil.mutListener.listen(24504) ? (progress <= ((ListenerUtil.mutListener.listen(24500) ? (currentProgress % 0.05f) : (ListenerUtil.mutListener.listen(24499) ? (currentProgress / 0.05f) : (ListenerUtil.mutListener.listen(24498) ? (currentProgress * 0.05f) : (ListenerUtil.mutListener.listen(24497) ? (currentProgress - 0.05f) : (currentProgress + 0.05f))))))) : (ListenerUtil.mutListener.listen(24503) ? (progress < ((ListenerUtil.mutListener.listen(24500) ? (currentProgress % 0.05f) : (ListenerUtil.mutListener.listen(24499) ? (currentProgress / 0.05f) : (ListenerUtil.mutListener.listen(24498) ? (currentProgress * 0.05f) : (ListenerUtil.mutListener.listen(24497) ? (currentProgress - 0.05f) : (currentProgress + 0.05f))))))) : (ListenerUtil.mutListener.listen(24502) ? (progress != ((ListenerUtil.mutListener.listen(24500) ? (currentProgress % 0.05f) : (ListenerUtil.mutListener.listen(24499) ? (currentProgress / 0.05f) : (ListenerUtil.mutListener.listen(24498) ? (currentProgress * 0.05f) : (ListenerUtil.mutListener.listen(24497) ? (currentProgress - 0.05f) : (currentProgress + 0.05f))))))) : (ListenerUtil.mutListener.listen(24501) ? (progress == ((ListenerUtil.mutListener.listen(24500) ? (currentProgress % 0.05f) : (ListenerUtil.mutListener.listen(24499) ? (currentProgress / 0.05f) : (ListenerUtil.mutListener.listen(24498) ? (currentProgress * 0.05f) : (ListenerUtil.mutListener.listen(24497) ? (currentProgress - 0.05f) : (currentProgress + 0.05f))))))) : (progress > ((ListenerUtil.mutListener.listen(24500) ? (currentProgress % 0.05f) : (ListenerUtil.mutListener.listen(24499) ? (currentProgress / 0.05f) : (ListenerUtil.mutListener.listen(24498) ? (currentProgress * 0.05f) : (ListenerUtil.mutListener.listen(24497) ? (currentProgress - 0.05f) : (currentProgress + 0.05f))))))))))))))) {
                if (!ListenerUtil.mutListener.listen(24507)) {
                    setProgressForMediaItem(media.getId(), progress);
                }
                if (!ListenerUtil.mutListener.listen(24508)) {
                    updateNotificationProgress();
                }
            }
        }
    }

    private void updateNotificationProgress() {
        if (!ListenerUtil.mutListener.listen(24521)) {
            if ((ListenerUtil.mutListener.listen(24520) ? ((ListenerUtil.mutListener.listen(24514) ? (sNotificationData.mTotalMediaItems >= 0) : (ListenerUtil.mutListener.listen(24513) ? (sNotificationData.mTotalMediaItems <= 0) : (ListenerUtil.mutListener.listen(24512) ? (sNotificationData.mTotalMediaItems > 0) : (ListenerUtil.mutListener.listen(24511) ? (sNotificationData.mTotalMediaItems < 0) : (ListenerUtil.mutListener.listen(24510) ? (sNotificationData.mTotalMediaItems != 0) : (sNotificationData.mTotalMediaItems == 0)))))) || (ListenerUtil.mutListener.listen(24519) ? (sNotificationData.mTotalPostItems >= 0) : (ListenerUtil.mutListener.listen(24518) ? (sNotificationData.mTotalPostItems <= 0) : (ListenerUtil.mutListener.listen(24517) ? (sNotificationData.mTotalPostItems > 0) : (ListenerUtil.mutListener.listen(24516) ? (sNotificationData.mTotalPostItems < 0) : (ListenerUtil.mutListener.listen(24515) ? (sNotificationData.mTotalPostItems != 0) : (sNotificationData.mTotalPostItems == 0))))))) : ((ListenerUtil.mutListener.listen(24514) ? (sNotificationData.mTotalMediaItems >= 0) : (ListenerUtil.mutListener.listen(24513) ? (sNotificationData.mTotalMediaItems <= 0) : (ListenerUtil.mutListener.listen(24512) ? (sNotificationData.mTotalMediaItems > 0) : (ListenerUtil.mutListener.listen(24511) ? (sNotificationData.mTotalMediaItems < 0) : (ListenerUtil.mutListener.listen(24510) ? (sNotificationData.mTotalMediaItems != 0) : (sNotificationData.mTotalMediaItems == 0)))))) && (ListenerUtil.mutListener.listen(24519) ? (sNotificationData.mTotalPostItems >= 0) : (ListenerUtil.mutListener.listen(24518) ? (sNotificationData.mTotalPostItems <= 0) : (ListenerUtil.mutListener.listen(24517) ? (sNotificationData.mTotalPostItems > 0) : (ListenerUtil.mutListener.listen(24516) ? (sNotificationData.mTotalPostItems < 0) : (ListenerUtil.mutListener.listen(24515) ? (sNotificationData.mTotalPostItems != 0) : (sNotificationData.mTotalPostItems == 0))))))))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(24526)) {
            mNotificationBuilder.setProgress(100, (int) Math.ceil((ListenerUtil.mutListener.listen(24525) ? (getCurrentOverallProgress() % 100) : (ListenerUtil.mutListener.listen(24524) ? (getCurrentOverallProgress() / 100) : (ListenerUtil.mutListener.listen(24523) ? (getCurrentOverallProgress() - 100) : (ListenerUtil.mutListener.listen(24522) ? (getCurrentOverallProgress() + 100) : (getCurrentOverallProgress() * 100)))))), false);
        }
        if (!ListenerUtil.mutListener.listen(24527)) {
            doNotify(sNotificationData.mNotificationId, mNotificationBuilder.build(), null);
        }
    }

    private void setProgressForMediaItem(int mediaId, float progress) {
        if (!ListenerUtil.mutListener.listen(24528)) {
            sNotificationData.mediaItemToProgressMap.put(mediaId, progress);
        }
    }

    private float getCurrentOverallProgress() {
        int totalItemCount = (ListenerUtil.mutListener.listen(24532) ? (sNotificationData.mTotalPostItems % sNotificationData.mTotalMediaItems) : (ListenerUtil.mutListener.listen(24531) ? (sNotificationData.mTotalPostItems / sNotificationData.mTotalMediaItems) : (ListenerUtil.mutListener.listen(24530) ? (sNotificationData.mTotalPostItems * sNotificationData.mTotalMediaItems) : (ListenerUtil.mutListener.listen(24529) ? (sNotificationData.mTotalPostItems - sNotificationData.mTotalMediaItems) : (sNotificationData.mTotalPostItems + sNotificationData.mTotalMediaItems)))));
        float currentMediaProgress = getCurrentMediaProgress();
        float overAllProgress;
        overAllProgress = (ListenerUtil.mutListener.listen(24537) ? (sNotificationData.mTotalPostItems >= 0) : (ListenerUtil.mutListener.listen(24536) ? (sNotificationData.mTotalPostItems <= 0) : (ListenerUtil.mutListener.listen(24535) ? (sNotificationData.mTotalPostItems < 0) : (ListenerUtil.mutListener.listen(24534) ? (sNotificationData.mTotalPostItems != 0) : (ListenerUtil.mutListener.listen(24533) ? (sNotificationData.mTotalPostItems == 0) : (sNotificationData.mTotalPostItems > 0)))))) ? (ListenerUtil.mutListener.listen(24545) ? (((ListenerUtil.mutListener.listen(24541) ? (sNotificationData.mCurrentPostItem % sNotificationData.mTotalPostItems) : (ListenerUtil.mutListener.listen(24540) ? (sNotificationData.mCurrentPostItem * sNotificationData.mTotalPostItems) : (ListenerUtil.mutListener.listen(24539) ? (sNotificationData.mCurrentPostItem - sNotificationData.mTotalPostItems) : (ListenerUtil.mutListener.listen(24538) ? (sNotificationData.mCurrentPostItem + sNotificationData.mTotalPostItems) : (sNotificationData.mCurrentPostItem / sNotificationData.mTotalPostItems)))))) % totalItemCount) : (ListenerUtil.mutListener.listen(24544) ? (((ListenerUtil.mutListener.listen(24541) ? (sNotificationData.mCurrentPostItem % sNotificationData.mTotalPostItems) : (ListenerUtil.mutListener.listen(24540) ? (sNotificationData.mCurrentPostItem * sNotificationData.mTotalPostItems) : (ListenerUtil.mutListener.listen(24539) ? (sNotificationData.mCurrentPostItem - sNotificationData.mTotalPostItems) : (ListenerUtil.mutListener.listen(24538) ? (sNotificationData.mCurrentPostItem + sNotificationData.mTotalPostItems) : (sNotificationData.mCurrentPostItem / sNotificationData.mTotalPostItems)))))) / totalItemCount) : (ListenerUtil.mutListener.listen(24543) ? (((ListenerUtil.mutListener.listen(24541) ? (sNotificationData.mCurrentPostItem % sNotificationData.mTotalPostItems) : (ListenerUtil.mutListener.listen(24540) ? (sNotificationData.mCurrentPostItem * sNotificationData.mTotalPostItems) : (ListenerUtil.mutListener.listen(24539) ? (sNotificationData.mCurrentPostItem - sNotificationData.mTotalPostItems) : (ListenerUtil.mutListener.listen(24538) ? (sNotificationData.mCurrentPostItem + sNotificationData.mTotalPostItems) : (sNotificationData.mCurrentPostItem / sNotificationData.mTotalPostItems)))))) - totalItemCount) : (ListenerUtil.mutListener.listen(24542) ? (((ListenerUtil.mutListener.listen(24541) ? (sNotificationData.mCurrentPostItem % sNotificationData.mTotalPostItems) : (ListenerUtil.mutListener.listen(24540) ? (sNotificationData.mCurrentPostItem * sNotificationData.mTotalPostItems) : (ListenerUtil.mutListener.listen(24539) ? (sNotificationData.mCurrentPostItem - sNotificationData.mTotalPostItems) : (ListenerUtil.mutListener.listen(24538) ? (sNotificationData.mCurrentPostItem + sNotificationData.mTotalPostItems) : (sNotificationData.mCurrentPostItem / sNotificationData.mTotalPostItems)))))) + totalItemCount) : (((ListenerUtil.mutListener.listen(24541) ? (sNotificationData.mCurrentPostItem % sNotificationData.mTotalPostItems) : (ListenerUtil.mutListener.listen(24540) ? (sNotificationData.mCurrentPostItem * sNotificationData.mTotalPostItems) : (ListenerUtil.mutListener.listen(24539) ? (sNotificationData.mCurrentPostItem - sNotificationData.mTotalPostItems) : (ListenerUtil.mutListener.listen(24538) ? (sNotificationData.mCurrentPostItem + sNotificationData.mTotalPostItems) : (sNotificationData.mCurrentPostItem / sNotificationData.mTotalPostItems)))))) * totalItemCount))))) : 0;
        if (!ListenerUtil.mutListener.listen(24559)) {
            overAllProgress += (ListenerUtil.mutListener.listen(24550) ? (sNotificationData.mTotalMediaItems >= 0) : (ListenerUtil.mutListener.listen(24549) ? (sNotificationData.mTotalMediaItems <= 0) : (ListenerUtil.mutListener.listen(24548) ? (sNotificationData.mTotalMediaItems < 0) : (ListenerUtil.mutListener.listen(24547) ? (sNotificationData.mTotalMediaItems != 0) : (ListenerUtil.mutListener.listen(24546) ? (sNotificationData.mTotalMediaItems == 0) : (sNotificationData.mTotalMediaItems > 0)))))) ? (ListenerUtil.mutListener.listen(24558) ? (((ListenerUtil.mutListener.listen(24554) ? (sNotificationData.mCurrentMediaItem % sNotificationData.mTotalMediaItems) : (ListenerUtil.mutListener.listen(24553) ? (sNotificationData.mCurrentMediaItem * sNotificationData.mTotalMediaItems) : (ListenerUtil.mutListener.listen(24552) ? (sNotificationData.mCurrentMediaItem - sNotificationData.mTotalMediaItems) : (ListenerUtil.mutListener.listen(24551) ? (sNotificationData.mCurrentMediaItem + sNotificationData.mTotalMediaItems) : (sNotificationData.mCurrentMediaItem / sNotificationData.mTotalMediaItems)))))) % totalItemCount) : (ListenerUtil.mutListener.listen(24557) ? (((ListenerUtil.mutListener.listen(24554) ? (sNotificationData.mCurrentMediaItem % sNotificationData.mTotalMediaItems) : (ListenerUtil.mutListener.listen(24553) ? (sNotificationData.mCurrentMediaItem * sNotificationData.mTotalMediaItems) : (ListenerUtil.mutListener.listen(24552) ? (sNotificationData.mCurrentMediaItem - sNotificationData.mTotalMediaItems) : (ListenerUtil.mutListener.listen(24551) ? (sNotificationData.mCurrentMediaItem + sNotificationData.mTotalMediaItems) : (sNotificationData.mCurrentMediaItem / sNotificationData.mTotalMediaItems)))))) / totalItemCount) : (ListenerUtil.mutListener.listen(24556) ? (((ListenerUtil.mutListener.listen(24554) ? (sNotificationData.mCurrentMediaItem % sNotificationData.mTotalMediaItems) : (ListenerUtil.mutListener.listen(24553) ? (sNotificationData.mCurrentMediaItem * sNotificationData.mTotalMediaItems) : (ListenerUtil.mutListener.listen(24552) ? (sNotificationData.mCurrentMediaItem - sNotificationData.mTotalMediaItems) : (ListenerUtil.mutListener.listen(24551) ? (sNotificationData.mCurrentMediaItem + sNotificationData.mTotalMediaItems) : (sNotificationData.mCurrentMediaItem / sNotificationData.mTotalMediaItems)))))) - totalItemCount) : (ListenerUtil.mutListener.listen(24555) ? (((ListenerUtil.mutListener.listen(24554) ? (sNotificationData.mCurrentMediaItem % sNotificationData.mTotalMediaItems) : (ListenerUtil.mutListener.listen(24553) ? (sNotificationData.mCurrentMediaItem * sNotificationData.mTotalMediaItems) : (ListenerUtil.mutListener.listen(24552) ? (sNotificationData.mCurrentMediaItem - sNotificationData.mTotalMediaItems) : (ListenerUtil.mutListener.listen(24551) ? (sNotificationData.mCurrentMediaItem + sNotificationData.mTotalMediaItems) : (sNotificationData.mCurrentMediaItem / sNotificationData.mTotalMediaItems)))))) + totalItemCount) : (((ListenerUtil.mutListener.listen(24554) ? (sNotificationData.mCurrentMediaItem % sNotificationData.mTotalMediaItems) : (ListenerUtil.mutListener.listen(24553) ? (sNotificationData.mCurrentMediaItem * sNotificationData.mTotalMediaItems) : (ListenerUtil.mutListener.listen(24552) ? (sNotificationData.mCurrentMediaItem - sNotificationData.mTotalMediaItems) : (ListenerUtil.mutListener.listen(24551) ? (sNotificationData.mCurrentMediaItem + sNotificationData.mTotalMediaItems) : (sNotificationData.mCurrentMediaItem / sNotificationData.mTotalMediaItems)))))) * totalItemCount))))) : 0;
        }
        if (!ListenerUtil.mutListener.listen(24560)) {
            overAllProgress += currentMediaProgress;
        }
        return overAllProgress;
    }

    private float getCurrentMediaProgress() {
        float currentMediaProgress = 0.0f;
        int size = sNotificationData.mediaItemToProgressMap.size();
        if (!ListenerUtil.mutListener.listen(24571)) {
            {
                long _loopCounter375 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(24570) ? (i >= size) : (ListenerUtil.mutListener.listen(24569) ? (i <= size) : (ListenerUtil.mutListener.listen(24568) ? (i > size) : (ListenerUtil.mutListener.listen(24567) ? (i != size) : (ListenerUtil.mutListener.listen(24566) ? (i == size) : (i < size)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter375", ++_loopCounter375);
                    int key = sNotificationData.mediaItemToProgressMap.keyAt(i);
                    float itemProgress = sNotificationData.mediaItemToProgressMap.get(key);
                    if (!ListenerUtil.mutListener.listen(24565)) {
                        currentMediaProgress += ((ListenerUtil.mutListener.listen(24564) ? (itemProgress % size) : (ListenerUtil.mutListener.listen(24563) ? (itemProgress * size) : (ListenerUtil.mutListener.listen(24562) ? (itemProgress - size) : (ListenerUtil.mutListener.listen(24561) ? (itemProgress + size) : (itemProgress / size))))));
                    }
                }
            }
        }
        return currentMediaProgress;
    }

    private synchronized void doNotify(long id, Notification notification, NotificationType notificationType) {
        try {
            if (!ListenerUtil.mutListener.listen(24573)) {
                mNotificationManager.notify((int) id, notification);
            }
            if (!ListenerUtil.mutListener.listen(24575)) {
                if (notificationType != null) {
                    if (!ListenerUtil.mutListener.listen(24574)) {
                        mSystemNotificationsTracker.trackShownNotification(notificationType);
                    }
                }
            }
        } catch (RuntimeException runtimeException) {
            if (!ListenerUtil.mutListener.listen(24572)) {
                AppLog.e(T.POSTS, "doNotify failed; See issue #2858 / #3966", runtimeException);
            }
        }
    }

    void setTotalMediaItems(PostImmutableModel post, int totalMediaItems) {
        if (!ListenerUtil.mutListener.listen(24579)) {
            if (post != null) {
                if (!ListenerUtil.mutListener.listen(24576)) {
                    sNotificationData.mTotalPostItems = 1;
                }
                if (!ListenerUtil.mutListener.listen(24578)) {
                    if (post.isPage()) {
                        if (!ListenerUtil.mutListener.listen(24577)) {
                            sNotificationData.mTotalPageItemsIncludedInPostCount = 1;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24580)) {
            sNotificationData.mTotalMediaItems = totalMediaItems;
        }
    }

    private String buildNotificationTitleForPost(PostImmutableModel post) {
        String postTitle = ((ListenerUtil.mutListener.listen(24581) ? (post == null && TextUtils.isEmpty(post.getTitle())) : (post == null || TextUtils.isEmpty(post.getTitle())))) ? mContext.getString(R.string.untitled) : post.getTitle();
        return String.format(mContext.getString(R.string.uploading_post), postTitle);
    }

    private String buildNotificationTitleForMedia() {
        return mContext.getString(R.string.uploading_media);
    }

    private String buildNotificationTitleForMixedContent() {
        return mContext.getString(R.string.uploading_title);
    }

    private String buildNotificationSubtitleForPost(PostImmutableModel post) {
        String uploadingMessage = ((ListenerUtil.mutListener.listen(24582) ? (post != null || post.isPage()) : (post != null && post.isPage()))) ? mContext.getString(R.string.uploading_subtitle_pages_only_one) : mContext.getString(R.string.uploading_subtitle_posts_only_one);
        return uploadingMessage;
    }

    private String buildNotificationSubtitleForPosts() {
        int remaining = (ListenerUtil.mutListener.listen(24586) ? (sNotificationData.mTotalPostItems % getCurrentPostItem()) : (ListenerUtil.mutListener.listen(24585) ? (sNotificationData.mTotalPostItems / getCurrentPostItem()) : (ListenerUtil.mutListener.listen(24584) ? (sNotificationData.mTotalPostItems * getCurrentPostItem()) : (ListenerUtil.mutListener.listen(24583) ? (sNotificationData.mTotalPostItems + getCurrentPostItem()) : (sNotificationData.mTotalPostItems - getCurrentPostItem())))));
        PagesOrPostsType pagesAndOrPosts = getPagesAndOrPostsType(remaining);
        String strToUse;
        switch(pagesAndOrPosts) {
            case PAGES:
                strToUse = mContext.getString(R.string.uploading_subtitle_pages_only_plural);
                break;
            case PAGES_OR_POSTS:
                strToUse = mContext.getString(R.string.uploading_subtitle_pages_posts);
                break;
            case POSTS:
            default:
                strToUse = mContext.getString(R.string.uploading_subtitle_posts_only_plural);
                break;
        }
        return String.format(strToUse, remaining);
    }

    private PagesOrPostsType getPagesAndOrPostsType(int remaining) {
        PagesOrPostsType pagesAndOrPosts;
        if ((ListenerUtil.mutListener.listen(24603) ? ((ListenerUtil.mutListener.listen(24597) ? ((ListenerUtil.mutListener.listen(24591) ? (sNotificationData.mTotalPageItemsIncludedInPostCount >= 0) : (ListenerUtil.mutListener.listen(24590) ? (sNotificationData.mTotalPageItemsIncludedInPostCount <= 0) : (ListenerUtil.mutListener.listen(24589) ? (sNotificationData.mTotalPageItemsIncludedInPostCount < 0) : (ListenerUtil.mutListener.listen(24588) ? (sNotificationData.mTotalPageItemsIncludedInPostCount != 0) : (ListenerUtil.mutListener.listen(24587) ? (sNotificationData.mTotalPageItemsIncludedInPostCount == 0) : (sNotificationData.mTotalPageItemsIncludedInPostCount > 0)))))) || (ListenerUtil.mutListener.listen(24596) ? (sNotificationData.mTotalPostItems >= 0) : (ListenerUtil.mutListener.listen(24595) ? (sNotificationData.mTotalPostItems <= 0) : (ListenerUtil.mutListener.listen(24594) ? (sNotificationData.mTotalPostItems < 0) : (ListenerUtil.mutListener.listen(24593) ? (sNotificationData.mTotalPostItems != 0) : (ListenerUtil.mutListener.listen(24592) ? (sNotificationData.mTotalPostItems == 0) : (sNotificationData.mTotalPostItems > 0))))))) : ((ListenerUtil.mutListener.listen(24591) ? (sNotificationData.mTotalPageItemsIncludedInPostCount >= 0) : (ListenerUtil.mutListener.listen(24590) ? (sNotificationData.mTotalPageItemsIncludedInPostCount <= 0) : (ListenerUtil.mutListener.listen(24589) ? (sNotificationData.mTotalPageItemsIncludedInPostCount < 0) : (ListenerUtil.mutListener.listen(24588) ? (sNotificationData.mTotalPageItemsIncludedInPostCount != 0) : (ListenerUtil.mutListener.listen(24587) ? (sNotificationData.mTotalPageItemsIncludedInPostCount == 0) : (sNotificationData.mTotalPageItemsIncludedInPostCount > 0)))))) && (ListenerUtil.mutListener.listen(24596) ? (sNotificationData.mTotalPostItems >= 0) : (ListenerUtil.mutListener.listen(24595) ? (sNotificationData.mTotalPostItems <= 0) : (ListenerUtil.mutListener.listen(24594) ? (sNotificationData.mTotalPostItems < 0) : (ListenerUtil.mutListener.listen(24593) ? (sNotificationData.mTotalPostItems != 0) : (ListenerUtil.mutListener.listen(24592) ? (sNotificationData.mTotalPostItems == 0) : (sNotificationData.mTotalPostItems > 0)))))))) || (ListenerUtil.mutListener.listen(24602) ? (sNotificationData.mTotalPostItems >= sNotificationData.mTotalPageItemsIncludedInPostCount) : (ListenerUtil.mutListener.listen(24601) ? (sNotificationData.mTotalPostItems <= sNotificationData.mTotalPageItemsIncludedInPostCount) : (ListenerUtil.mutListener.listen(24600) ? (sNotificationData.mTotalPostItems < sNotificationData.mTotalPageItemsIncludedInPostCount) : (ListenerUtil.mutListener.listen(24599) ? (sNotificationData.mTotalPostItems != sNotificationData.mTotalPageItemsIncludedInPostCount) : (ListenerUtil.mutListener.listen(24598) ? (sNotificationData.mTotalPostItems == sNotificationData.mTotalPageItemsIncludedInPostCount) : (sNotificationData.mTotalPostItems > sNotificationData.mTotalPageItemsIncludedInPostCount))))))) : ((ListenerUtil.mutListener.listen(24597) ? ((ListenerUtil.mutListener.listen(24591) ? (sNotificationData.mTotalPageItemsIncludedInPostCount >= 0) : (ListenerUtil.mutListener.listen(24590) ? (sNotificationData.mTotalPageItemsIncludedInPostCount <= 0) : (ListenerUtil.mutListener.listen(24589) ? (sNotificationData.mTotalPageItemsIncludedInPostCount < 0) : (ListenerUtil.mutListener.listen(24588) ? (sNotificationData.mTotalPageItemsIncludedInPostCount != 0) : (ListenerUtil.mutListener.listen(24587) ? (sNotificationData.mTotalPageItemsIncludedInPostCount == 0) : (sNotificationData.mTotalPageItemsIncludedInPostCount > 0)))))) || (ListenerUtil.mutListener.listen(24596) ? (sNotificationData.mTotalPostItems >= 0) : (ListenerUtil.mutListener.listen(24595) ? (sNotificationData.mTotalPostItems <= 0) : (ListenerUtil.mutListener.listen(24594) ? (sNotificationData.mTotalPostItems < 0) : (ListenerUtil.mutListener.listen(24593) ? (sNotificationData.mTotalPostItems != 0) : (ListenerUtil.mutListener.listen(24592) ? (sNotificationData.mTotalPostItems == 0) : (sNotificationData.mTotalPostItems > 0))))))) : ((ListenerUtil.mutListener.listen(24591) ? (sNotificationData.mTotalPageItemsIncludedInPostCount >= 0) : (ListenerUtil.mutListener.listen(24590) ? (sNotificationData.mTotalPageItemsIncludedInPostCount <= 0) : (ListenerUtil.mutListener.listen(24589) ? (sNotificationData.mTotalPageItemsIncludedInPostCount < 0) : (ListenerUtil.mutListener.listen(24588) ? (sNotificationData.mTotalPageItemsIncludedInPostCount != 0) : (ListenerUtil.mutListener.listen(24587) ? (sNotificationData.mTotalPageItemsIncludedInPostCount == 0) : (sNotificationData.mTotalPageItemsIncludedInPostCount > 0)))))) && (ListenerUtil.mutListener.listen(24596) ? (sNotificationData.mTotalPostItems >= 0) : (ListenerUtil.mutListener.listen(24595) ? (sNotificationData.mTotalPostItems <= 0) : (ListenerUtil.mutListener.listen(24594) ? (sNotificationData.mTotalPostItems < 0) : (ListenerUtil.mutListener.listen(24593) ? (sNotificationData.mTotalPostItems != 0) : (ListenerUtil.mutListener.listen(24592) ? (sNotificationData.mTotalPostItems == 0) : (sNotificationData.mTotalPostItems > 0)))))))) && (ListenerUtil.mutListener.listen(24602) ? (sNotificationData.mTotalPostItems >= sNotificationData.mTotalPageItemsIncludedInPostCount) : (ListenerUtil.mutListener.listen(24601) ? (sNotificationData.mTotalPostItems <= sNotificationData.mTotalPageItemsIncludedInPostCount) : (ListenerUtil.mutListener.listen(24600) ? (sNotificationData.mTotalPostItems < sNotificationData.mTotalPageItemsIncludedInPostCount) : (ListenerUtil.mutListener.listen(24599) ? (sNotificationData.mTotalPostItems != sNotificationData.mTotalPageItemsIncludedInPostCount) : (ListenerUtil.mutListener.listen(24598) ? (sNotificationData.mTotalPostItems == sNotificationData.mTotalPageItemsIncludedInPostCount) : (sNotificationData.mTotalPostItems > sNotificationData.mTotalPageItemsIncludedInPostCount))))))))) {
            // we have both pages and posts
            pagesAndOrPosts = PagesOrPostsType.PAGES_OR_POSTS;
        } else if ((ListenerUtil.mutListener.listen(24608) ? (sNotificationData.mTotalPageItemsIncludedInPostCount >= 0) : (ListenerUtil.mutListener.listen(24607) ? (sNotificationData.mTotalPageItemsIncludedInPostCount <= 0) : (ListenerUtil.mutListener.listen(24606) ? (sNotificationData.mTotalPageItemsIncludedInPostCount < 0) : (ListenerUtil.mutListener.listen(24605) ? (sNotificationData.mTotalPageItemsIncludedInPostCount != 0) : (ListenerUtil.mutListener.listen(24604) ? (sNotificationData.mTotalPageItemsIncludedInPostCount == 0) : (sNotificationData.mTotalPageItemsIncludedInPostCount > 0))))))) {
            // we have only pages
            if ((ListenerUtil.mutListener.listen(24618) ? (remaining >= 1) : (ListenerUtil.mutListener.listen(24617) ? (remaining <= 1) : (ListenerUtil.mutListener.listen(24616) ? (remaining > 1) : (ListenerUtil.mutListener.listen(24615) ? (remaining < 1) : (ListenerUtil.mutListener.listen(24614) ? (remaining != 1) : (remaining == 1))))))) {
                // only one page
                pagesAndOrPosts = PagesOrPostsType.PAGE;
            } else {
                pagesAndOrPosts = PagesOrPostsType.PAGES;
            }
        } else {
            // we have only posts
            if ((ListenerUtil.mutListener.listen(24613) ? (remaining >= 1) : (ListenerUtil.mutListener.listen(24612) ? (remaining <= 1) : (ListenerUtil.mutListener.listen(24611) ? (remaining > 1) : (ListenerUtil.mutListener.listen(24610) ? (remaining < 1) : (ListenerUtil.mutListener.listen(24609) ? (remaining != 1) : (remaining == 1))))))) {
                // only one post
                pagesAndOrPosts = PagesOrPostsType.POST;
            } else {
                pagesAndOrPosts = PagesOrPostsType.POSTS;
            }
        }
        return pagesAndOrPosts;
    }

    private String buildNotificationSubtitleForMedia() {
        String uploadingMessage;
        if ((ListenerUtil.mutListener.listen(24623) ? (sNotificationData.mTotalMediaItems >= 1) : (ListenerUtil.mutListener.listen(24622) ? (sNotificationData.mTotalMediaItems <= 1) : (ListenerUtil.mutListener.listen(24621) ? (sNotificationData.mTotalMediaItems > 1) : (ListenerUtil.mutListener.listen(24620) ? (sNotificationData.mTotalMediaItems < 1) : (ListenerUtil.mutListener.listen(24619) ? (sNotificationData.mTotalMediaItems != 1) : (sNotificationData.mTotalMediaItems == 1))))))) {
            uploadingMessage = mContext.getString(R.string.uploading_subtitle_media_only_one);
        } else {
            uploadingMessage = String.format(mContext.getString(R.string.uploading_subtitle_media_only), (ListenerUtil.mutListener.listen(24627) ? (sNotificationData.mTotalMediaItems % getCurrentMediaItem()) : (ListenerUtil.mutListener.listen(24626) ? (sNotificationData.mTotalMediaItems / getCurrentMediaItem()) : (ListenerUtil.mutListener.listen(24625) ? (sNotificationData.mTotalMediaItems * getCurrentMediaItem()) : (ListenerUtil.mutListener.listen(24624) ? (sNotificationData.mTotalMediaItems + getCurrentMediaItem()) : (sNotificationData.mTotalMediaItems - getCurrentMediaItem()))))), sNotificationData.mTotalMediaItems);
        }
        return uploadingMessage;
    }

    private String buildNotificationSubtitleForMixedContent() {
        int remaining = (ListenerUtil.mutListener.listen(24631) ? (sNotificationData.mTotalPostItems % getCurrentPostItem()) : (ListenerUtil.mutListener.listen(24630) ? (sNotificationData.mTotalPostItems / getCurrentPostItem()) : (ListenerUtil.mutListener.listen(24629) ? (sNotificationData.mTotalPostItems * getCurrentPostItem()) : (ListenerUtil.mutListener.listen(24628) ? (sNotificationData.mTotalPostItems + getCurrentPostItem()) : (sNotificationData.mTotalPostItems - getCurrentPostItem())))));
        String uploadingMessage;
        if ((ListenerUtil.mutListener.listen(24636) ? (sNotificationData.mTotalMediaItems >= 1) : (ListenerUtil.mutListener.listen(24635) ? (sNotificationData.mTotalMediaItems <= 1) : (ListenerUtil.mutListener.listen(24634) ? (sNotificationData.mTotalMediaItems > 1) : (ListenerUtil.mutListener.listen(24633) ? (sNotificationData.mTotalMediaItems < 1) : (ListenerUtil.mutListener.listen(24632) ? (sNotificationData.mTotalMediaItems != 1) : (sNotificationData.mTotalMediaItems == 1))))))) {
            switch(getPagesAndOrPostsType(remaining)) {
                case PAGES:
                    uploadingMessage = String.format(mContext.getString(R.string.uploading_subtitle_mixed_pages_plural_media_one), remaining);
                    break;
                case PAGE:
                    uploadingMessage = mContext.getString(R.string.uploading_subtitle_mixed_page_singular_media_one);
                    break;
                case POST:
                    uploadingMessage = mContext.getString(R.string.uploading_subtitle_mixed_post_singular_media_one);
                    break;
                case PAGES_OR_POSTS:
                    uploadingMessage = String.format(mContext.getString(R.string.uploading_subtitle_mixed_pages_and_posts_plural_media_one), remaining);
                    break;
                case POSTS:
                default:
                    uploadingMessage = String.format(mContext.getString(R.string.uploading_subtitle_mixed_posts_plural_media_one), remaining);
                    break;
            }
        } else {
            switch(getPagesAndOrPostsType(remaining)) {
                case PAGES:
                    uploadingMessage = String.format(mContext.getString(R.string.uploading_subtitle_mixed_pages_plural_media_plural), remaining, (ListenerUtil.mutListener.listen(24640) ? (sNotificationData.mTotalMediaItems % getCurrentMediaItem()) : (ListenerUtil.mutListener.listen(24639) ? (sNotificationData.mTotalMediaItems / getCurrentMediaItem()) : (ListenerUtil.mutListener.listen(24638) ? (sNotificationData.mTotalMediaItems * getCurrentMediaItem()) : (ListenerUtil.mutListener.listen(24637) ? (sNotificationData.mTotalMediaItems + getCurrentMediaItem()) : (sNotificationData.mTotalMediaItems - getCurrentMediaItem()))))), sNotificationData.mTotalMediaItems);
                    break;
                case PAGE:
                    uploadingMessage = String.format(mContext.getString(R.string.uploading_subtitle_mixed_page_singular_media_plural), (ListenerUtil.mutListener.listen(24644) ? (sNotificationData.mTotalMediaItems % getCurrentMediaItem()) : (ListenerUtil.mutListener.listen(24643) ? (sNotificationData.mTotalMediaItems / getCurrentMediaItem()) : (ListenerUtil.mutListener.listen(24642) ? (sNotificationData.mTotalMediaItems * getCurrentMediaItem()) : (ListenerUtil.mutListener.listen(24641) ? (sNotificationData.mTotalMediaItems + getCurrentMediaItem()) : (sNotificationData.mTotalMediaItems - getCurrentMediaItem()))))), sNotificationData.mTotalMediaItems);
                    break;
                case POST:
                    uploadingMessage = String.format(mContext.getString(R.string.uploading_subtitle_mixed_post_singular_media_plural), (ListenerUtil.mutListener.listen(24648) ? (sNotificationData.mTotalMediaItems % getCurrentMediaItem()) : (ListenerUtil.mutListener.listen(24647) ? (sNotificationData.mTotalMediaItems / getCurrentMediaItem()) : (ListenerUtil.mutListener.listen(24646) ? (sNotificationData.mTotalMediaItems * getCurrentMediaItem()) : (ListenerUtil.mutListener.listen(24645) ? (sNotificationData.mTotalMediaItems + getCurrentMediaItem()) : (sNotificationData.mTotalMediaItems - getCurrentMediaItem()))))), sNotificationData.mTotalMediaItems);
                    break;
                case PAGES_OR_POSTS:
                    uploadingMessage = String.format(mContext.getString(R.string.uploading_subtitle_mixed_pages_and_posts_plural_media_plural), remaining, (ListenerUtil.mutListener.listen(24652) ? (sNotificationData.mTotalMediaItems % getCurrentMediaItem()) : (ListenerUtil.mutListener.listen(24651) ? (sNotificationData.mTotalMediaItems / getCurrentMediaItem()) : (ListenerUtil.mutListener.listen(24650) ? (sNotificationData.mTotalMediaItems * getCurrentMediaItem()) : (ListenerUtil.mutListener.listen(24649) ? (sNotificationData.mTotalMediaItems + getCurrentMediaItem()) : (sNotificationData.mTotalMediaItems - getCurrentMediaItem()))))), sNotificationData.mTotalMediaItems);
                    break;
                case POSTS:
                default:
                    uploadingMessage = String.format(mContext.getString(R.string.uploading_subtitle_mixed_posts_plural_media_plural), remaining, (ListenerUtil.mutListener.listen(24656) ? (sNotificationData.mTotalMediaItems % getCurrentMediaItem()) : (ListenerUtil.mutListener.listen(24655) ? (sNotificationData.mTotalMediaItems / getCurrentMediaItem()) : (ListenerUtil.mutListener.listen(24654) ? (sNotificationData.mTotalMediaItems * getCurrentMediaItem()) : (ListenerUtil.mutListener.listen(24653) ? (sNotificationData.mTotalMediaItems + getCurrentMediaItem()) : (sNotificationData.mTotalMediaItems - getCurrentMediaItem()))))), sNotificationData.mTotalMediaItems);
                    break;
            }
        }
        return uploadingMessage;
    }

    private int getCurrentPostItem() {
        int currentPostItem = (ListenerUtil.mutListener.listen(24661) ? (sNotificationData.mCurrentPostItem <= sNotificationData.mTotalPostItems) : (ListenerUtil.mutListener.listen(24660) ? (sNotificationData.mCurrentPostItem > sNotificationData.mTotalPostItems) : (ListenerUtil.mutListener.listen(24659) ? (sNotificationData.mCurrentPostItem < sNotificationData.mTotalPostItems) : (ListenerUtil.mutListener.listen(24658) ? (sNotificationData.mCurrentPostItem != sNotificationData.mTotalPostItems) : (ListenerUtil.mutListener.listen(24657) ? (sNotificationData.mCurrentPostItem == sNotificationData.mTotalPostItems) : (sNotificationData.mCurrentPostItem >= sNotificationData.mTotalPostItems)))))) ? (ListenerUtil.mutListener.listen(24665) ? (sNotificationData.mTotalPostItems % 1) : (ListenerUtil.mutListener.listen(24664) ? (sNotificationData.mTotalPostItems / 1) : (ListenerUtil.mutListener.listen(24663) ? (sNotificationData.mTotalPostItems * 1) : (ListenerUtil.mutListener.listen(24662) ? (sNotificationData.mTotalPostItems + 1) : (sNotificationData.mTotalPostItems - 1))))) : sNotificationData.mCurrentPostItem;
        return currentPostItem;
    }

    private int getCurrentMediaItem() {
        int currentMediaItem = (ListenerUtil.mutListener.listen(24670) ? (sNotificationData.mCurrentMediaItem <= sNotificationData.mTotalMediaItems) : (ListenerUtil.mutListener.listen(24669) ? (sNotificationData.mCurrentMediaItem > sNotificationData.mTotalMediaItems) : (ListenerUtil.mutListener.listen(24668) ? (sNotificationData.mCurrentMediaItem < sNotificationData.mTotalMediaItems) : (ListenerUtil.mutListener.listen(24667) ? (sNotificationData.mCurrentMediaItem != sNotificationData.mTotalMediaItems) : (ListenerUtil.mutListener.listen(24666) ? (sNotificationData.mCurrentMediaItem == sNotificationData.mTotalMediaItems) : (sNotificationData.mCurrentMediaItem >= sNotificationData.mTotalMediaItems)))))) ? (ListenerUtil.mutListener.listen(24674) ? (sNotificationData.mTotalMediaItems % 1) : (ListenerUtil.mutListener.listen(24673) ? (sNotificationData.mTotalMediaItems / 1) : (ListenerUtil.mutListener.listen(24672) ? (sNotificationData.mTotalMediaItems * 1) : (ListenerUtil.mutListener.listen(24671) ? (sNotificationData.mTotalMediaItems + 1) : (sNotificationData.mTotalMediaItems - 1))))) : sNotificationData.mCurrentMediaItem;
        return currentMediaItem;
    }
}
