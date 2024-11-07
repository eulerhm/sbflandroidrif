package org.wordpress.android.ui.uploads;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.editor.AztecEditorFragment;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.generated.MediaActionBuilder;
import org.wordpress.android.fluxc.generated.PostActionBuilder;
import org.wordpress.android.fluxc.generated.UploadActionBuilder;
import org.wordpress.android.fluxc.model.CauseOfOnPostChanged;
import org.wordpress.android.fluxc.model.CauseOfOnPostChanged.RemoteAutoSavePost;
import org.wordpress.android.fluxc.model.MediaModel;
import org.wordpress.android.fluxc.model.MediaModel.MediaUploadState;
import org.wordpress.android.fluxc.model.PostImmutableModel;
import org.wordpress.android.fluxc.model.PostModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.MediaStore;
import org.wordpress.android.fluxc.store.MediaStore.OnMediaUploaded;
import org.wordpress.android.fluxc.store.PostStore;
import org.wordpress.android.fluxc.store.PostStore.OnPostChanged;
import org.wordpress.android.fluxc.store.PostStore.OnPostUploaded;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.store.UploadStore;
import org.wordpress.android.fluxc.store.UploadStore.ClearMediaPayload;
import org.wordpress.android.ui.media.services.MediaUploadReadyListener;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.ui.notifications.SystemNotificationsTracker;
import org.wordpress.android.ui.posts.PostUtils;
import org.wordpress.android.ui.posts.PostUtilsWrapper;
import org.wordpress.android.ui.prefs.AppPrefs;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.DateTimeUtils;
import org.wordpress.android.util.FluxCUtils;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.StringUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.WPMediaUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class UploadService extends Service {

    private static final String KEY_CHANGE_STATUS_TO_PUBLISH = "shouldPublish";

    private static final String KEY_SHOULD_RETRY = "shouldRetry";

    private static final String KEY_MEDIA_LIST = "mediaList";

    private static final String KEY_UPLOAD_MEDIA_FROM_EDITOR = "mediaFromEditor";

    private static final String KEY_LOCAL_POST_ID = "localPostId";

    private static final String KEY_SHOULD_TRACK_ANALYTICS = "shouldTrackPostAnalytics";

    @Nullable
    private static UploadService sInstance;

    private MediaUploadHandler mMediaUploadHandler;

    private PostUploadHandler mPostUploadHandler;

    private PostUploadNotifier mPostUploadNotifier;

    // we hold this reference here for the success notification for Media uploads
    private List<MediaModel> mMediaBatchUploaded = new ArrayList<>();

    // for media that the user actively cancelled uploads for
    private static HashSet<String> mUserDeletedMediaItemIds = new HashSet<>();

    @Inject
    Dispatcher mDispatcher;

    @Inject
    MediaStore mMediaStore;

    @Inject
    PostStore mPostStore;

    @Inject
    SiteStore mSiteStore;

    @Inject
    UploadStore mUploadStore;

    @Inject
    SystemNotificationsTracker mSystemNotificationsTracker;

    @Inject
    PostUtilsWrapper mPostUtilsWrapper;

    @Inject
    SelectedSiteRepository mSelectedSiteRepository;

    @Override
    public void onCreate() {
        if (!ListenerUtil.mutListener.listen(24675)) {
            super.onCreate();
        }
        if (!ListenerUtil.mutListener.listen(24676)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(24677)) {
            AppLog.i(T.MAIN, "UploadService > Created");
        }
        if (!ListenerUtil.mutListener.listen(24678)) {
            mDispatcher.register(this);
        }
        if (!ListenerUtil.mutListener.listen(24679)) {
            sInstance = this;
        }
        if (!ListenerUtil.mutListener.listen(24681)) {
            if (mMediaUploadHandler == null) {
                if (!ListenerUtil.mutListener.listen(24680)) {
                    mMediaUploadHandler = new MediaUploadHandler();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24683)) {
            if (mPostUploadNotifier == null) {
                if (!ListenerUtil.mutListener.listen(24682)) {
                    mPostUploadNotifier = new PostUploadNotifier(getApplicationContext(), this, mSystemNotificationsTracker);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24685)) {
            if (mPostUploadHandler == null) {
                if (!ListenerUtil.mutListener.listen(24684)) {
                    mPostUploadHandler = new PostUploadHandler(mPostUploadNotifier);
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        if (!ListenerUtil.mutListener.listen(24688)) {
            if (mMediaUploadHandler != null) {
                if (!ListenerUtil.mutListener.listen(24686)) {
                    mMediaUploadHandler.cancelInProgressUploads();
                }
                if (!ListenerUtil.mutListener.listen(24687)) {
                    mMediaUploadHandler.unregister();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24691)) {
            if (mPostUploadHandler != null) {
                if (!ListenerUtil.mutListener.listen(24689)) {
                    mPostUploadHandler.cancelInProgressUploads();
                }
                if (!ListenerUtil.mutListener.listen(24690)) {
                    mPostUploadHandler.unregister();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24692)) {
            // Update posts with any completed AND failed uploads in our post->media map
            doFinalProcessingOfPosts(null, null);
        }
        if (!ListenerUtil.mutListener.listen(24694)) {
            {
                long _loopCounter376 = 0;
                for (PostModel pendingPost : mUploadStore.getPendingPosts()) {
                    ListenerUtil.loopListener.listen("_loopCounter376", ++_loopCounter376);
                    if (!ListenerUtil.mutListener.listen(24693)) {
                        cancelQueuedPostUpload(pendingPost);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24695)) {
            mDispatcher.unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(24696)) {
            sInstance = null;
        }
        if (!ListenerUtil.mutListener.listen(24697)) {
            AppLog.i(T.MAIN, "UploadService > Destroyed");
        }
        if (!ListenerUtil.mutListener.listen(24698)) {
            super.onDestroy();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!ListenerUtil.mutListener.listen(24703)) {
            // Skip this request if no items to upload were given
            if ((ListenerUtil.mutListener.listen(24700) ? (intent == null && ((ListenerUtil.mutListener.listen(24699) ? (!intent.hasExtra(KEY_MEDIA_LIST) || !intent.hasExtra(KEY_LOCAL_POST_ID)) : (!intent.hasExtra(KEY_MEDIA_LIST) && !intent.hasExtra(KEY_LOCAL_POST_ID))))) : (intent == null || ((ListenerUtil.mutListener.listen(24699) ? (!intent.hasExtra(KEY_MEDIA_LIST) || !intent.hasExtra(KEY_LOCAL_POST_ID)) : (!intent.hasExtra(KEY_MEDIA_LIST) && !intent.hasExtra(KEY_LOCAL_POST_ID))))))) {
                if (!ListenerUtil.mutListener.listen(24701)) {
                    AppLog.e(T.MAIN, "UploadService > Killed and restarted with an empty intent");
                }
                if (!ListenerUtil.mutListener.listen(24702)) {
                    stopServiceIfUploadsComplete();
                }
                return START_NOT_STICKY;
            }
        }
        if (!ListenerUtil.mutListener.listen(24705)) {
            if (intent.hasExtra(KEY_MEDIA_LIST)) {
                if (!ListenerUtil.mutListener.listen(24704)) {
                    unpackMediaIntent(intent);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24707)) {
            if (intent.hasExtra(KEY_LOCAL_POST_ID)) {
                if (!ListenerUtil.mutListener.listen(24706)) {
                    unpackPostIntent(intent);
                }
            }
        }
        return START_REDELIVER_INTENT;
    }

    private void unpackMediaIntent(@NonNull Intent intent) {
        // add new media
        @SuppressWarnings("unchecked")
        List<MediaModel> mediaList = (List<MediaModel>) intent.getSerializableExtra(KEY_MEDIA_LIST);
        if (!ListenerUtil.mutListener.listen(24722)) {
            if ((ListenerUtil.mutListener.listen(24708) ? (mediaList != null || !mediaList.isEmpty()) : (mediaList != null && !mediaList.isEmpty()))) {
                if (!ListenerUtil.mutListener.listen(24711)) {
                    if (!intent.getBooleanExtra(KEY_UPLOAD_MEDIA_FROM_EDITOR, false)) {
                        if (!ListenerUtil.mutListener.listen(24709)) {
                            // it might be a separate action (user is editing a Post and including media there)
                            PostUploadNotifier.cancelFinalNotificationForMedia(this, mSiteStore.getSiteByLocalId(mediaList.get(0).getLocalSiteId()));
                        }
                        if (!ListenerUtil.mutListener.listen(24710)) {
                            // add these media items so we can use them in WRITE POST once they end up loading successfully
                            mMediaBatchUploaded.addAll(mediaList);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(24712)) {
                    // if this media belongs to some post, register such Post
                    registerPostModelsForMedia(mediaList, intent.getBooleanExtra(KEY_SHOULD_RETRY, false));
                }
                ArrayList<MediaModel> toBeUploadedMediaList = new ArrayList<>();
                if (!ListenerUtil.mutListener.listen(24717)) {
                    {
                        long _loopCounter377 = 0;
                        for (MediaModel media : mediaList) {
                            ListenerUtil.loopListener.listen("_loopCounter377", ++_loopCounter377);
                            MediaModel localMedia = mMediaStore.getMediaWithLocalId(media.getId());
                            boolean notUploadedYet = (ListenerUtil.mutListener.listen(24714) ? (localMedia != null || ((ListenerUtil.mutListener.listen(24713) ? (localMedia.getUploadState() == null && MediaUploadState.fromString(localMedia.getUploadState()) != MediaUploadState.UPLOADED) : (localMedia.getUploadState() == null || MediaUploadState.fromString(localMedia.getUploadState()) != MediaUploadState.UPLOADED)))) : (localMedia != null && ((ListenerUtil.mutListener.listen(24713) ? (localMedia.getUploadState() == null && MediaUploadState.fromString(localMedia.getUploadState()) != MediaUploadState.UPLOADED) : (localMedia.getUploadState() == null || MediaUploadState.fromString(localMedia.getUploadState()) != MediaUploadState.UPLOADED)))));
                            if (!ListenerUtil.mutListener.listen(24716)) {
                                if (notUploadedYet) {
                                    if (!ListenerUtil.mutListener.listen(24715)) {
                                        toBeUploadedMediaList.add(media);
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(24719)) {
                    {
                        long _loopCounter378 = 0;
                        for (MediaModel media : toBeUploadedMediaList) {
                            ListenerUtil.loopListener.listen("_loopCounter378", ++_loopCounter378);
                            if (!ListenerUtil.mutListener.listen(24718)) {
                                mMediaUploadHandler.upload(media);
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(24721)) {
                    if (!toBeUploadedMediaList.isEmpty()) {
                        if (!ListenerUtil.mutListener.listen(24720)) {
                            mPostUploadNotifier.addMediaInfoToForegroundNotification(toBeUploadedMediaList);
                        }
                    }
                }
            }
        }
    }

    private void registerPostModelsForMedia(List<MediaModel> mediaList, boolean isRetry) {
        if (!ListenerUtil.mutListener.listen(24729)) {
            if ((ListenerUtil.mutListener.listen(24723) ? (mediaList != null || !mediaList.isEmpty()) : (mediaList != null && !mediaList.isEmpty()))) {
                Set<PostModel> postsToRefresh = PostUtils.getPostsThatIncludeAnyOfTheseMedia(mPostStore, mediaList);
                if (!ListenerUtil.mutListener.listen(24725)) {
                    {
                        long _loopCounter379 = 0;
                        for (PostImmutableModel post : postsToRefresh) {
                            ListenerUtil.loopListener.listen("_loopCounter379", ++_loopCounter379);
                            if (!ListenerUtil.mutListener.listen(24724)) {
                                // If the post is already registered, the new media will be added to its list
                                mUploadStore.registerPostModel(post, mediaList);
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(24728)) {
                    if (isRetry) {
                        if (!ListenerUtil.mutListener.listen(24726)) {
                            // Bump analytics
                            AnalyticsTracker.track(AnalyticsTracker.Stat.NOTIFICATION_UPLOAD_MEDIA_ERROR_RETRY);
                        }
                        if (!ListenerUtil.mutListener.listen(24727)) {
                            // send event so Editors can handle clearing Failed statuses properly if Post is being edited right now
                            EventBus.getDefault().post(new UploadService.UploadMediaRetryEvent(mediaList));
                        }
                    }
                }
            }
        }
    }

    private void unpackPostIntent(@NonNull Intent intent) {
        PostModel post = mPostStore.getPostByLocalPostId(intent.getIntExtra(KEY_LOCAL_POST_ID, 0));
        if (!ListenerUtil.mutListener.listen(24750)) {
            if (post != null) {
                boolean shouldTrackAnalytics = intent.getBooleanExtra(KEY_SHOULD_TRACK_ANALYTICS, false);
                if (!ListenerUtil.mutListener.listen(24731)) {
                    if (shouldTrackAnalytics) {
                        if (!ListenerUtil.mutListener.listen(24730)) {
                            mPostUploadHandler.registerPostForAnalyticsTracking(post.getId());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(24732)) {
                    // i.e. dismiss success or error notification for the post.
                    mPostUploadNotifier.cancelFinalNotification(this, post);
                }
                if (!ListenerUtil.mutListener.listen(24735)) {
                    // analytics before starting the upload process.
                    if (intent.getBooleanExtra(KEY_CHANGE_STATUS_TO_PUBLISH, false)) {
                        SiteModel site = mSiteStore.getSiteByLocalId(post.getLocalSiteId());
                        if (!ListenerUtil.mutListener.listen(24733)) {
                            makePostPublishable(post, site);
                        }
                        if (!ListenerUtil.mutListener.listen(24734)) {
                            PostUtils.trackSavePostAnalytics(post, site);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(24742)) {
                    if (intent.getBooleanExtra(KEY_SHOULD_RETRY, false)) {
                        if (!ListenerUtil.mutListener.listen(24741)) {
                            if ((ListenerUtil.mutListener.listen(24736) ? (AppPrefs.isAztecEditorEnabled() && AppPrefs.isGutenbergEditorEnabled()) : (AppPrefs.isAztecEditorEnabled() || AppPrefs.isGutenbergEditorEnabled()))) {
                                if (!ListenerUtil.mutListener.listen(24739)) {
                                    if (!NetworkUtils.isNetworkAvailable(this)) {
                                        if (!ListenerUtil.mutListener.listen(24738)) {
                                            rebuildNotificationError(post, getString(R.string.no_network_message));
                                        }
                                        return;
                                    }
                                }
                                boolean postHasGutenbergBlocks = PostUtils.contentContainsGutenbergBlocks(post.getContent());
                                if (!ListenerUtil.mutListener.listen(24740)) {
                                    retryUpload(post, !postHasGutenbergBlocks);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(24737)) {
                                    ToastUtils.showToast(this, R.string.retry_needs_aztec);
                                }
                            }
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(24745)) {
                    // or it's a failed one the user is actively retrying.
                    if ((ListenerUtil.mutListener.listen(24743) ? (isThisPostTotallyNewOrFailed(post) || !PostUploadHandler.isPostUploadingOrQueued(post)) : (isThisPostTotallyNewOrFailed(post) && !PostUploadHandler.isPostUploadingOrQueued(post)))) {
                        if (!ListenerUtil.mutListener.listen(24744)) {
                            mPostUploadNotifier.addPostInfoToForegroundNotification(post, null);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(24749)) {
                    if (!getAllFailedMediaForPost(post).isEmpty()) {
                        boolean postHasGutenbergBlocks = PostUtils.contentContainsGutenbergBlocks(post.getContent());
                        if (!ListenerUtil.mutListener.listen(24748)) {
                            retryUpload(post, !postHasGutenbergBlocks);
                        }
                    } else if (hasPendingOrInProgressMediaUploadsForPost(post)) {
                        // If the post is already registered, the new media will be added to its list
                        List<MediaModel> activeMedia = MediaUploadHandler.getPendingOrInProgressMediaUploadsForPost(post);
                        if (!ListenerUtil.mutListener.listen(24747)) {
                            mUploadStore.registerPostModel(post, activeMedia);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(24746)) {
                            mPostUploadHandler.upload(post);
                        }
                    }
                }
            }
        }
    }

    public static void cancelFinalNotification(Context context, PostImmutableModel post) {
        if (!ListenerUtil.mutListener.listen(24751)) {
            // i.e. dismiss success or error notification for the post.
            PostUploadNotifier.cancelFinalNotification(context, post);
        }
    }

    public static void cancelFinalNotificationForMedia(Context context, SiteModel site) {
        if (!ListenerUtil.mutListener.listen(24752)) {
            PostUploadNotifier.cancelFinalNotificationForMedia(context, site);
        }
    }

    /**
     * Do not use this method unless the user explicitly confirmed changes - eg. clicked on publish button or
     * similar.
     */
    private void makePostPublishable(@NonNull PostModel post, SiteModel site) {
        if (!ListenerUtil.mutListener.listen(24753)) {
            PostUtils.preparePostForPublish(post, site);
        }
        if (!ListenerUtil.mutListener.listen(24754)) {
            mDispatcher.dispatch(PostActionBuilder.newUpdatePostAction(post));
        }
    }

    private boolean isThisPostTotallyNewOrFailed(PostImmutableModel post) {
        // uploading).
        return (ListenerUtil.mutListener.listen(24756) ? (!mUploadStore.isRegisteredPostModel(post) && ((ListenerUtil.mutListener.listen(24755) ? (mUploadStore.isFailedPost(post) && mUploadStore.isPendingPost(post)) : (mUploadStore.isFailedPost(post) || mUploadStore.isPendingPost(post))))) : (!mUploadStore.isRegisteredPostModel(post) || ((ListenerUtil.mutListener.listen(24755) ? (mUploadStore.isFailedPost(post) && mUploadStore.isPendingPost(post)) : (mUploadStore.isFailedPost(post) || mUploadStore.isPendingPost(post))))));
    }

    public static Intent getRetryUploadServiceIntent(Context context, @NonNull PostImmutableModel post, boolean trackAnalytics) {
        Intent intent = new Intent(context, UploadService.class);
        if (!ListenerUtil.mutListener.listen(24757)) {
            intent.putExtra(KEY_LOCAL_POST_ID, post.getId());
        }
        if (!ListenerUtil.mutListener.listen(24758)) {
            intent.putExtra(KEY_SHOULD_TRACK_ANALYTICS, trackAnalytics);
        }
        if (!ListenerUtil.mutListener.listen(24759)) {
            intent.putExtra(KEY_SHOULD_RETRY, true);
        }
        return intent;
    }

    /**
     * Do not use this method unless the user explicitly confirmed changes - eg. clicked on publish button or
     * similar.
     *
     * The only valid use-case for this method I can think of right know is when we want to put the intent into a
     * PendingIntent - eg. publish action on a notification. If you want to start the upload right away use
     * UploadUtils.publishPost(..) instead.
     */
    public static Intent getPublishPostServiceIntent(Context context, @NonNull PostImmutableModel post, boolean trackAnalytics) {
        Intent intent = new Intent(context, UploadService.class);
        if (!ListenerUtil.mutListener.listen(24760)) {
            intent.putExtra(KEY_LOCAL_POST_ID, post.getId());
        }
        if (!ListenerUtil.mutListener.listen(24761)) {
            intent.putExtra(KEY_SHOULD_TRACK_ANALYTICS, trackAnalytics);
        }
        if (!ListenerUtil.mutListener.listen(24762)) {
            intent.putExtra(KEY_CHANGE_STATUS_TO_PUBLISH, true);
        }
        return intent;
    }

    public static Intent getUploadMediaServiceIntent(Context context, @NonNull ArrayList<MediaModel> mediaList, boolean isRetry) {
        Intent intent = new Intent(context, UploadService.class);
        if (!ListenerUtil.mutListener.listen(24763)) {
            intent.putExtra(UploadService.KEY_MEDIA_LIST, mediaList);
        }
        if (!ListenerUtil.mutListener.listen(24764)) {
            intent.putExtra(KEY_SHOULD_RETRY, isRetry);
        }
        return intent;
    }

    /**
     * Adds a post to the queue.
     * @param postId
     * @param isFirstTimePublish true when its status changes from local draft or remote draft to published.
     */
    public static void uploadPost(Context context, int postId, boolean isFirstTimePublish) {
        Intent intent = new Intent(context, UploadService.class);
        if (!ListenerUtil.mutListener.listen(24765)) {
            intent.putExtra(KEY_LOCAL_POST_ID, postId);
        }
        if (!ListenerUtil.mutListener.listen(24766)) {
            intent.putExtra(KEY_SHOULD_TRACK_ANALYTICS, isFirstTimePublish);
        }
        if (!ListenerUtil.mutListener.listen(24767)) {
            context.startService(intent);
        }
    }

    public static void uploadMedia(Context context, @NonNull MediaModel media) {
        ArrayList<MediaModel> list = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(24768)) {
            list.add(media);
        }
        if (!ListenerUtil.mutListener.listen(24769)) {
            uploadMedia(context, list);
        }
    }

    public static void uploadMedia(Context context, @NonNull ArrayList<MediaModel> mediaList) {
        if (!ListenerUtil.mutListener.listen(24770)) {
            if (context == null) {
                return;
            }
        }
        Intent intent = new Intent(context, UploadService.class);
        if (!ListenerUtil.mutListener.listen(24771)) {
            intent.putExtra(UploadService.KEY_MEDIA_LIST, mediaList);
        }
        if (!ListenerUtil.mutListener.listen(24772)) {
            context.startService(intent);
        }
    }

    public static void uploadMediaFromEditor(Context context, @NonNull ArrayList<MediaModel> mediaList) {
        if (!ListenerUtil.mutListener.listen(24773)) {
            if (context == null) {
                return;
            }
        }
        Intent intent = new Intent(context, UploadService.class);
        if (!ListenerUtil.mutListener.listen(24774)) {
            intent.putExtra(UploadService.KEY_MEDIA_LIST, mediaList);
        }
        if (!ListenerUtil.mutListener.listen(24775)) {
            intent.putExtra(UploadService.KEY_UPLOAD_MEDIA_FROM_EDITOR, true);
        }
        if (!ListenerUtil.mutListener.listen(24776)) {
            context.startService(intent);
        }
    }

    /**
     * Returns true if the passed post is either currently uploading or waiting to be uploaded.
     * Except for legacy mode, a post counts as 'uploading' if the post content itself is being uploaded - a post
     * waiting for media to finish uploading counts as 'waiting to be uploaded' until the media uploads complete.
     */
    public static boolean isPostUploadingOrQueued(PostImmutableModel post) {
        UploadService instance = sInstance;
        if (!ListenerUtil.mutListener.listen(24778)) {
            if ((ListenerUtil.mutListener.listen(24777) ? (instance == null && post == null) : (instance == null || post == null))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(24779)) {
            // First check for posts uploading or queued inside the PostUploadManager
            if (PostUploadHandler.isPostUploadingOrQueued(post)) {
                return true;
            }
        }
        // Then check the list of posts waiting for media to complete
        return instance.mUploadStore.isPendingPost(post);
    }

    public static boolean isPostQueued(PostImmutableModel post) {
        // Check for posts queued inside the PostUploadManager
        return (ListenerUtil.mutListener.listen(24780) ? (sInstance != null || PostUploadHandler.isPostQueued(post)) : (sInstance != null && PostUploadHandler.isPostQueued(post)));
    }

    /**
     * Returns true if the passed post is currently uploading.
     * Except for legacy mode, a post counts as 'uploading' if the post content itself is being uploaded - a post
     * waiting for media to finish uploading counts as 'waiting to be uploaded' until the media uploads complete.
     */
    public static boolean isPostUploading(PostImmutableModel post) {
        return (ListenerUtil.mutListener.listen(24781) ? (sInstance != null || PostUploadHandler.isPostUploading(post)) : (sInstance != null && PostUploadHandler.isPostUploading(post)));
    }

    public static void cancelQueuedPostUploadAndRelatedMedia(Context context, PostModel post) {
        if (!ListenerUtil.mutListener.listen(24788)) {
            if (post != null) {
                if (!ListenerUtil.mutListener.listen(24785)) {
                    if (sInstance != null) {
                        if (!ListenerUtil.mutListener.listen(24783)) {
                            PostUploadNotifier.cancelFinalNotification(sInstance, post);
                        }
                        if (!ListenerUtil.mutListener.listen(24784)) {
                            sInstance.mPostUploadNotifier.removePostInfoFromForegroundNotification(post, sInstance.mMediaStore.getMediaForPost(post));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(24782)) {
                            PostUploadNotifier.cancelFinalNotification(context, post);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(24786)) {
                    cancelQueuedPostUpload(post);
                }
                if (!ListenerUtil.mutListener.listen(24787)) {
                    EventBus.getDefault().post(new PostEvents.PostMediaCanceled(post));
                }
            }
        }
    }

    public static void cancelQueuedPostUpload(PostModel post) {
        if (!ListenerUtil.mutListener.listen(24791)) {
            if ((ListenerUtil.mutListener.listen(24789) ? (sInstance != null || post != null) : (sInstance != null && post != null))) {
                if (!ListenerUtil.mutListener.listen(24790)) {
                    // Mark the post as CANCELLED in the UploadStore
                    sInstance.mDispatcher.dispatch(UploadActionBuilder.newCancelPostAction(post));
                }
            }
        }
    }

    public static PostModel updatePostWithCurrentlyCompletedUploads(PostModel post) {
        if (!ListenerUtil.mutListener.listen(24800)) {
            if ((ListenerUtil.mutListener.listen(24792) ? (post != null || sInstance != null) : (post != null && sInstance != null))) {
                // updates in one go and save only once
                MediaUploadReadyListener processor = new MediaUploadReadyProcessor();
                Set<MediaModel> completedMedia = sInstance.mUploadStore.getCompletedMediaForPost(post);
                if (!ListenerUtil.mutListener.listen(24796)) {
                    {
                        long _loopCounter380 = 0;
                        for (MediaModel media : completedMedia) {
                            ListenerUtil.loopListener.listen("_loopCounter380", ++_loopCounter380);
                            if (!ListenerUtil.mutListener.listen(24795)) {
                                if (media.getMarkedLocallyAsFeatured()) {
                                    if (!ListenerUtil.mutListener.listen(24794)) {
                                        post = updatePostWithNewFeaturedImg(post, media.getMediaId());
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(24793)) {
                                        post = updatePostWithMediaUrl(post, media, processor);
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(24799)) {
                    if ((ListenerUtil.mutListener.listen(24797) ? (completedMedia != null || !completedMedia.isEmpty()) : (completedMedia != null && !completedMedia.isEmpty()))) {
                        // finally remove all completed uploads for this post, as they've been taken care of
                        ClearMediaPayload clearMediaPayload = new ClearMediaPayload(post, completedMedia);
                        if (!ListenerUtil.mutListener.listen(24798)) {
                            sInstance.mDispatcher.dispatch(UploadActionBuilder.newClearMediaForPostAction(clearMediaPayload));
                        }
                    }
                }
            }
        }
        return post;
    }

    public static PostModel updatePostWithCurrentlyFailedUploads(PostModel post) {
        if (!ListenerUtil.mutListener.listen(24804)) {
            if ((ListenerUtil.mutListener.listen(24801) ? (post != null || sInstance != null) : (post != null && sInstance != null))) {
                // updates in one go and save only once
                MediaUploadReadyListener processor = new MediaUploadReadyProcessor();
                Set<MediaModel> failedMedia = sInstance.mUploadStore.getFailedMediaForPost(post);
                if (!ListenerUtil.mutListener.listen(24803)) {
                    {
                        long _loopCounter381 = 0;
                        for (MediaModel media : failedMedia) {
                            ListenerUtil.loopListener.listen("_loopCounter381", ++_loopCounter381);
                            if (!ListenerUtil.mutListener.listen(24802)) {
                                post = updatePostWithFailedMedia(post, media, processor);
                            }
                        }
                    }
                }
            }
        }
        return post;
    }

    public static boolean hasInProgressMediaUploadsForPost(PostImmutableModel postModel) {
        return (ListenerUtil.mutListener.listen(24805) ? (postModel != null || MediaUploadHandler.hasInProgressMediaUploadsForPost(postModel.getId())) : (postModel != null && MediaUploadHandler.hasInProgressMediaUploadsForPost(postModel.getId())));
    }

    public static boolean hasPendingMediaUploadsForPost(PostImmutableModel postModel) {
        return (ListenerUtil.mutListener.listen(24806) ? (postModel != null || MediaUploadHandler.hasPendingMediaUploadsForPost(postModel.getId())) : (postModel != null && MediaUploadHandler.hasPendingMediaUploadsForPost(postModel.getId())));
    }

    public static boolean hasPendingOrInProgressMediaUploadsForPost(PostImmutableModel postModel) {
        return (ListenerUtil.mutListener.listen(24807) ? (postModel != null || MediaUploadHandler.hasPendingOrInProgressMediaUploadsForPost(postModel.getId())) : (postModel != null && MediaUploadHandler.hasPendingOrInProgressMediaUploadsForPost(postModel.getId())));
    }

    public static MediaModel getPendingOrInProgressFeaturedImageUploadForPost(PostImmutableModel postModel) {
        return MediaUploadHandler.getPendingOrInProgressFeaturedImageUploadForPost(postModel);
    }

    public static List<MediaModel> getPendingOrInProgressMediaUploadsForPost(PostImmutableModel post) {
        return MediaUploadHandler.getPendingOrInProgressMediaUploadsForPost(post);
    }

    public static float getMediaUploadProgressForPost(PostModel postModel) {
        UploadService instance = sInstance;
        if (!ListenerUtil.mutListener.listen(24809)) {
            if ((ListenerUtil.mutListener.listen(24808) ? (postModel == null && instance == null) : (postModel == null || instance == null))) {
                // If the UploadService isn't running, there's no progress for this post
                return 0;
            }
        }
        Set<MediaModel> pendingMediaList = instance.mUploadStore.getUploadingMediaForPost(postModel);
        if (!ListenerUtil.mutListener.listen(24810)) {
            if (pendingMediaList.size() == 0) {
                return 1;
            }
        }
        float overallProgress = 0;
        if (!ListenerUtil.mutListener.listen(24812)) {
            {
                long _loopCounter382 = 0;
                for (MediaModel pendingMedia : pendingMediaList) {
                    ListenerUtil.loopListener.listen("_loopCounter382", ++_loopCounter382);
                    if (!ListenerUtil.mutListener.listen(24811)) {
                        overallProgress += getUploadProgressForMedia(pendingMedia);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24813)) {
            overallProgress /= pendingMediaList.size();
        }
        return overallProgress;
    }

    public static float getUploadProgressForMedia(MediaModel mediaModel) {
        UploadService instance = sInstance;
        if (!ListenerUtil.mutListener.listen(24815)) {
            if ((ListenerUtil.mutListener.listen(24814) ? (mediaModel == null && instance == null) : (mediaModel == null || instance == null))) {
                // If the UploadService isn't running, there's no progress for this media
                return 0;
            }
        }
        float uploadProgress = instance.mUploadStore.getUploadProgressForMedia(mediaModel);
        if (!ListenerUtil.mutListener.listen(24817)) {
            // If this is a video and video optimization is enabled, include the optimization progress in the outcome
            if ((ListenerUtil.mutListener.listen(24816) ? (mediaModel.isVideo() || WPMediaUtils.isVideoOptimizationEnabled()) : (mediaModel.isVideo() && WPMediaUtils.isVideoOptimizationEnabled()))) {
                return MediaUploadHandler.getOverallProgressForVideo(mediaModel.getId(), uploadProgress);
            }
        }
        return uploadProgress;
    }

    @NonNull
    public static Set<MediaModel> getPendingMediaForPost(PostModel postModel) {
        UploadService instance = sInstance;
        if (!ListenerUtil.mutListener.listen(24819)) {
            if ((ListenerUtil.mutListener.listen(24818) ? (postModel == null && instance == null) : (postModel == null || instance == null))) {
                return Collections.emptySet();
            }
        }
        return instance.mUploadStore.getUploadingMediaForPost(postModel);
    }

    public static boolean isPendingOrInProgressMediaUpload(@NonNull MediaModel media) {
        return MediaUploadHandler.isPendingOrInProgressMediaUpload(media.getId());
    }

    /**
     * Rechecks all media in the MediaStore marked UPLOADING/QUEUED against the UploadingService to see
     * if it's actually uploading or queued and change it accordingly, to recover from an inconsistent state
     */
    public static void sanitizeMediaUploadStateForSite(@NonNull MediaStore mediaStore, @NonNull Dispatcher dispatcher, @NonNull SiteModel site) {
        List<MediaModel> uploadingMedia = mediaStore.getSiteMediaWithState(site, MediaUploadState.UPLOADING);
        List<MediaModel> queuedMedia = mediaStore.getSiteMediaWithState(site, MediaUploadState.QUEUED);
        if (!ListenerUtil.mutListener.listen(24821)) {
            if ((ListenerUtil.mutListener.listen(24820) ? (uploadingMedia.isEmpty() || queuedMedia.isEmpty()) : (uploadingMedia.isEmpty() && queuedMedia.isEmpty()))) {
                return;
            }
        }
        List<MediaModel> uploadingOrQueuedMedia = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(24822)) {
            uploadingOrQueuedMedia.addAll(uploadingMedia);
        }
        if (!ListenerUtil.mutListener.listen(24823)) {
            uploadingOrQueuedMedia.addAll(queuedMedia);
        }
        if (!ListenerUtil.mutListener.listen(24827)) {
            {
                long _loopCounter383 = 0;
                for (final MediaModel media : uploadingOrQueuedMedia) {
                    ListenerUtil.loopListener.listen("_loopCounter383", ++_loopCounter383);
                    if (!ListenerUtil.mutListener.listen(24826)) {
                        if (!UploadService.isPendingOrInProgressMediaUpload(media)) {
                            if (!ListenerUtil.mutListener.listen(24824)) {
                                // it is NOT being uploaded or queued in the actual UploadService, mark it failed
                                media.setUploadState(MediaUploadState.FAILED);
                            }
                            if (!ListenerUtil.mutListener.listen(24825)) {
                                dispatcher.dispatch(MediaActionBuilder.newUpdateMediaAction(media));
                            }
                        }
                    }
                }
            }
        }
    }

    private static synchronized PostModel updatePostWithNewFeaturedImg(PostModel post, Long remoteMediaId) {
        if (!ListenerUtil.mutListener.listen(24838)) {
            if ((ListenerUtil.mutListener.listen(24828) ? (post != null || remoteMediaId != null) : (post != null && remoteMediaId != null))) {
                boolean changesConfirmed = post.contentHashcode() == post.getChangesConfirmedContentHashcode();
                if (!ListenerUtil.mutListener.listen(24829)) {
                    post.setFeaturedImageId(remoteMediaId);
                }
                if (!ListenerUtil.mutListener.listen(24830)) {
                    post.setIsLocallyChanged(true);
                }
                if (!ListenerUtil.mutListener.listen(24835)) {
                    post.setDateLocallyChanged(DateTimeUtils.iso8601UTCFromTimestamp((ListenerUtil.mutListener.listen(24834) ? (System.currentTimeMillis() % 1000) : (ListenerUtil.mutListener.listen(24833) ? (System.currentTimeMillis() * 1000) : (ListenerUtil.mutListener.listen(24832) ? (System.currentTimeMillis() - 1000) : (ListenerUtil.mutListener.listen(24831) ? (System.currentTimeMillis() + 1000) : (System.currentTimeMillis() / 1000)))))));
                }
                if (!ListenerUtil.mutListener.listen(24837)) {
                    if (changesConfirmed) {
                        if (!ListenerUtil.mutListener.listen(24836)) {
                            /*
                 * We are replacing local featured image with a remote version. We need to make sure
                 * to retain the confirmation state.
                 */
                            post.setChangesConfirmedContentHashcode(post.contentHashcode());
                        }
                    }
                }
            }
        }
        return post;
    }

    private static synchronized PostModel updatePostWithMediaUrl(PostModel post, MediaModel media, MediaUploadReadyListener processor) {
        if (!ListenerUtil.mutListener.listen(24852)) {
            if ((ListenerUtil.mutListener.listen(24841) ? ((ListenerUtil.mutListener.listen(24840) ? ((ListenerUtil.mutListener.listen(24839) ? (media != null || post != null) : (media != null && post != null)) || processor != null) : ((ListenerUtil.mutListener.listen(24839) ? (media != null || post != null) : (media != null && post != null)) && processor != null)) || sInstance != null) : ((ListenerUtil.mutListener.listen(24840) ? ((ListenerUtil.mutListener.listen(24839) ? (media != null || post != null) : (media != null && post != null)) || processor != null) : ((ListenerUtil.mutListener.listen(24839) ? (media != null || post != null) : (media != null && post != null)) && processor != null)) && sInstance != null))) {
                boolean changesConfirmed = post.contentHashcode() == post.getChangesConfirmedContentHashcode();
                // obtain site url used to generate attachment page url
                SiteModel site = sInstance.mSiteStore.getSiteByLocalId(media.getLocalSiteId());
                if (!ListenerUtil.mutListener.listen(24842)) {
                    // actually replace the media ID with the media uri
                    processor.replaceMediaFileWithUrlInPost(post, String.valueOf(media.getId()), FluxCUtils.mediaFileFromMediaModel(media), site);
                }
                if (!ListenerUtil.mutListener.listen(24844)) {
                    // we changed the post, so let’s mark this down
                    if (!post.isLocalDraft()) {
                        if (!ListenerUtil.mutListener.listen(24843)) {
                            post.setIsLocallyChanged(true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(24849)) {
                    post.setDateLocallyChanged(DateTimeUtils.iso8601UTCFromTimestamp((ListenerUtil.mutListener.listen(24848) ? (System.currentTimeMillis() % 1000) : (ListenerUtil.mutListener.listen(24847) ? (System.currentTimeMillis() * 1000) : (ListenerUtil.mutListener.listen(24846) ? (System.currentTimeMillis() - 1000) : (ListenerUtil.mutListener.listen(24845) ? (System.currentTimeMillis() + 1000) : (System.currentTimeMillis() / 1000)))))));
                }
                if (!ListenerUtil.mutListener.listen(24851)) {
                    if (changesConfirmed) {
                        if (!ListenerUtil.mutListener.listen(24850)) {
                            /*
                 * We are replacing image local path with a url. We need to make sure to retain the confirmation
                 * state.
                 */
                            post.setChangesConfirmedContentHashcode(post.contentHashcode());
                        }
                    }
                }
            }
        }
        return post;
    }

    private static synchronized PostModel updatePostWithFailedMedia(PostModel post, MediaModel media, MediaUploadReadyListener processor) {
        if (!ListenerUtil.mutListener.listen(24865)) {
            if ((ListenerUtil.mutListener.listen(24854) ? ((ListenerUtil.mutListener.listen(24853) ? (media != null || post != null) : (media != null && post != null)) || processor != null) : ((ListenerUtil.mutListener.listen(24853) ? (media != null || post != null) : (media != null && post != null)) && processor != null))) {
                boolean changesConfirmed = post.contentHashcode() == post.getChangesConfirmedContentHashcode();
                if (!ListenerUtil.mutListener.listen(24855)) {
                    // actually mark the media failed within the Post
                    processor.markMediaUploadFailedInPost(post, String.valueOf(media.getId()), FluxCUtils.mediaFileFromMediaModel(media));
                }
                if (!ListenerUtil.mutListener.listen(24857)) {
                    // we changed the post, so let’s mark this down
                    if (!post.isLocalDraft()) {
                        if (!ListenerUtil.mutListener.listen(24856)) {
                            post.setIsLocallyChanged(true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(24862)) {
                    post.setDateLocallyChanged(DateTimeUtils.iso8601UTCFromTimestamp((ListenerUtil.mutListener.listen(24861) ? (System.currentTimeMillis() % 1000) : (ListenerUtil.mutListener.listen(24860) ? (System.currentTimeMillis() * 1000) : (ListenerUtil.mutListener.listen(24859) ? (System.currentTimeMillis() - 1000) : (ListenerUtil.mutListener.listen(24858) ? (System.currentTimeMillis() + 1000) : (System.currentTimeMillis() / 1000)))))));
                }
                if (!ListenerUtil.mutListener.listen(24864)) {
                    if (changesConfirmed) {
                        if (!ListenerUtil.mutListener.listen(24863)) {
                            /*
                 * We are updating media upload status, but we don't make any undesired changes to the post. We need to
                 * make sure to retain the confirmation state.
                 */
                            post.setChangesConfirmedContentHashcode(post.contentHashcode());
                        }
                    }
                }
            }
        }
        return post;
    }

    private synchronized void stopServiceIfUploadsComplete() {
        if (!ListenerUtil.mutListener.listen(24866)) {
            stopServiceIfUploadsComplete(null, null);
        }
    }

    private synchronized void stopServiceIfUploadsComplete(Boolean isError, PostModel post) {
        if (!ListenerUtil.mutListener.listen(24868)) {
            if ((ListenerUtil.mutListener.listen(24867) ? (mPostUploadHandler != null || mPostUploadHandler.hasInProgressUploads()) : (mPostUploadHandler != null && mPostUploadHandler.hasInProgressUploads()))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(24871)) {
            if ((ListenerUtil.mutListener.listen(24869) ? (mMediaUploadHandler != null || mMediaUploadHandler.hasInProgressUploads()) : (mMediaUploadHandler != null && mMediaUploadHandler.hasInProgressUploads()))) {
                return;
            } else {
                if (!ListenerUtil.mutListener.listen(24870)) {
                    verifyMediaOnlyUploadsAndNotify();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24872)) {
            if (doFinalProcessingOfPosts(isError, post)) {
                // when more Posts have been re-enqueued, don't stop the service just yet.
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(24873)) {
            if (!mUploadStore.getPendingPosts().isEmpty()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(24874)) {
            AppLog.i(T.MAIN, "UploadService > Completed");
        }
        if (!ListenerUtil.mutListener.listen(24875)) {
            stopSelf();
        }
    }

    private void verifyMediaOnlyUploadsAndNotify() {
        if (!ListenerUtil.mutListener.listen(24884)) {
            // check if all are successful uploads, then notify the user about it
            if (!mMediaBatchUploaded.isEmpty()) {
                ArrayList<MediaModel> standAloneMediaItems = new ArrayList<>();
                if (!ListenerUtil.mutListener.listen(24880)) {
                    {
                        long _loopCounter384 = 0;
                        for (MediaModel media : mMediaBatchUploaded) {
                            ListenerUtil.loopListener.listen("_loopCounter384", ++_loopCounter384);
                            // we need to obtain the latest copy from the Store, as it's got the remote mediaId field
                            MediaModel currentMedia = mMediaStore.getMediaWithLocalId(media.getId());
                            if (!ListenerUtil.mutListener.listen(24879)) {
                                if ((ListenerUtil.mutListener.listen(24877) ? ((ListenerUtil.mutListener.listen(24876) ? (currentMedia != null || currentMedia.getLocalPostId() == 0) : (currentMedia != null && currentMedia.getLocalPostId() == 0)) || MediaUploadState.fromString(currentMedia.getUploadState()) == MediaUploadState.UPLOADED) : ((ListenerUtil.mutListener.listen(24876) ? (currentMedia != null || currentMedia.getLocalPostId() == 0) : (currentMedia != null && currentMedia.getLocalPostId() == 0)) && MediaUploadState.fromString(currentMedia.getUploadState()) == MediaUploadState.UPLOADED))) {
                                    if (!ListenerUtil.mutListener.listen(24878)) {
                                        standAloneMediaItems.add(currentMedia);
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(24883)) {
                    if (!standAloneMediaItems.isEmpty()) {
                        SiteModel site = mSiteStore.getSiteByLocalId(standAloneMediaItems.get(0).getLocalSiteId());
                        if (!ListenerUtil.mutListener.listen(24881)) {
                            mPostUploadNotifier.updateNotificationSuccessForMedia(standAloneMediaItems, site);
                        }
                        if (!ListenerUtil.mutListener.listen(24882)) {
                            mMediaBatchUploaded.clear();
                        }
                    }
                }
            }
        }
    }

    private PostModel updateOnePostModelWithCompletedAndFailedUploads(PostModel postModel) {
        PostModel updatedPost = updatePostWithCurrentlyCompletedUploads(postModel);
        if (!ListenerUtil.mutListener.listen(24885)) {
            // also do the same now with failed uploads
            updatedPost = updatePostWithCurrentlyFailedUploads(updatedPost);
        }
        if (!ListenerUtil.mutListener.listen(24887)) {
            // finally, save the PostModel
            if (updatedPost != null) {
                if (!ListenerUtil.mutListener.listen(24886)) {
                    mDispatcher.dispatch(PostActionBuilder.newUpdatePostAction(updatedPost));
                }
            }
        }
        return updatedPost;
    }

    private boolean mediaBelongsToAPost(MediaModel media) {
        PostModel postToCancel = mPostStore.getPostByLocalPostId(media.getLocalPostId());
        return ((ListenerUtil.mutListener.listen(24888) ? (postToCancel != null || mUploadStore.isRegisteredPostModel(postToCancel)) : (postToCancel != null && mUploadStore.isRegisteredPostModel(postToCancel))));
    }

    /*
        returns true if Post canceled
        returns false if Post can't be found or is not registered in the UploadStore
     */
    private boolean cancelPostUploadMatchingMedia(@NonNull MediaModel media, String errorMessage, boolean showError) {
        PostModel postToCancel = mPostStore.getPostByLocalPostId(media.getLocalPostId());
        if (!ListenerUtil.mutListener.listen(24889)) {
            if (postToCancel == null) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(24890)) {
            if (!mUploadStore.isRegisteredPostModel(postToCancel)) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(24893)) {
            if ((ListenerUtil.mutListener.listen(24891) ? (PostUploadHandler.isPostUploadingOrQueued(postToCancel) || !PostUtils.isPostCurrentlyBeingEdited(postToCancel)) : (PostUploadHandler.isPostUploadingOrQueued(postToCancel) && !PostUtils.isPostCurrentlyBeingEdited(postToCancel)))) {
                if (!ListenerUtil.mutListener.listen(24892)) {
                    // post is not being edited and is currently queued, update the count on the foreground notification
                    mPostUploadNotifier.incrementUploadedPostCountFromForegroundNotification(postToCancel);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24898)) {
            if ((ListenerUtil.mutListener.listen(24894) ? (showError && mUploadStore.isFailedPost(postToCancel)) : (showError || mUploadStore.isFailedPost(postToCancel)))) {
                // the user actively cancelled it. No need to show an error then.
                String message = UploadUtils.getErrorMessage(this, postToCancel.isPage(), errorMessage, true);
                SiteModel site = mSiteStore.getSiteByLocalId(postToCancel.getLocalSiteId());
                if (!ListenerUtil.mutListener.listen(24897)) {
                    if (site != null) {
                        if (!ListenerUtil.mutListener.listen(24896)) {
                            mPostUploadNotifier.updateNotificationErrorForPost(postToCancel, site, message, mUploadStore.getFailedMediaForPost(postToCancel).size());
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(24895)) {
                            AppLog.e(T.POSTS, "Trying to update notifications with missing site");
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24899)) {
            mPostUploadHandler.unregisterPostForAnalyticsTracking(postToCancel.getId());
        }
        if (!ListenerUtil.mutListener.listen(24900)) {
            EventBus.getDefault().post(new PostEvents.PostUploadCanceled(postToCancel));
        }
        return true;
    }

    private void rebuildNotificationError(PostModel post, String errorMessage) {
        Set<MediaModel> failedMedia = mUploadStore.getFailedMediaForPost(post);
        if (!ListenerUtil.mutListener.listen(24901)) {
            mPostUploadNotifier.setTotalMediaItems(post, failedMedia.size());
        }
        SiteModel site = mSiteStore.getSiteByLocalId(post.getLocalSiteId());
        if (!ListenerUtil.mutListener.listen(24904)) {
            if (site != null) {
                if (!ListenerUtil.mutListener.listen(24903)) {
                    mPostUploadNotifier.updateNotificationErrorForPost(post, site, errorMessage, 0);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(24902)) {
                    AppLog.e(T.POSTS, "Trying to rebuild notification error without a site");
                }
            }
        }
    }

    private void aztecRegisterFailedMediaForThisPost(PostModel post) {
        // on Retry.
        List<String> mediaIds = AztecEditorFragment.getMediaMarkedFailedInPostContent(this, post.getContent());
        if (!ListenerUtil.mutListener.listen(24915)) {
            if ((ListenerUtil.mutListener.listen(24905) ? (mediaIds != null || !mediaIds.isEmpty()) : (mediaIds != null && !mediaIds.isEmpty()))) {
                ArrayList<MediaModel> mediaList = new ArrayList<>();
                if (!ListenerUtil.mutListener.listen(24911)) {
                    {
                        long _loopCounter385 = 0;
                        for (String mediaId : mediaIds) {
                            ListenerUtil.loopListener.listen("_loopCounter385", ++_loopCounter385);
                            MediaModel media = mMediaStore.getMediaWithLocalId(StringUtils.stringToInt(mediaId));
                            if (!ListenerUtil.mutListener.listen(24910)) {
                                if (media != null) {
                                    if (!ListenerUtil.mutListener.listen(24906)) {
                                        mediaList.add(media);
                                    }
                                    if (!ListenerUtil.mutListener.listen(24909)) {
                                        // in the Post body anyway. So let's fix that now.
                                        if (media.getLocalPostId() == 0) {
                                            if (!ListenerUtil.mutListener.listen(24907)) {
                                                media.setLocalPostId(post.getId());
                                            }
                                            if (!ListenerUtil.mutListener.listen(24908)) {
                                                mDispatcher.dispatch(MediaActionBuilder.newUpdateMediaAction(media));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(24914)) {
                    if (!mediaList.isEmpty()) {
                        if (!ListenerUtil.mutListener.listen(24912)) {
                            // given we found failed media within this Post, let's also cancel the media error
                            mPostUploadNotifier.cancelFinalNotificationForMedia(this, mSiteStore.getSiteByLocalId(post.getLocalSiteId()));
                        }
                        if (!ListenerUtil.mutListener.listen(24913)) {
                            // now we have a list. Let' register this list.
                            mUploadStore.registerPostModel(post, mediaList);
                        }
                    }
                }
            }
        }
    }

    private void retryUpload(PostModel post, boolean processWithAztec) {
        if (!ListenerUtil.mutListener.listen(24916)) {
            if (mUploadStore.isPendingPost(post)) {
                // the post will be uploaded ignoring its media (we could upload content with paths to local storage).
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(24917)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.NOTIFICATION_UPLOAD_POST_ERROR_RETRY);
        }
        if (!ListenerUtil.mutListener.listen(24919)) {
            if (processWithAztec) {
                if (!ListenerUtil.mutListener.listen(24918)) {
                    aztecRegisterFailedMediaForThisPost(post);
                }
            }
        }
        List<MediaModel> mediaToRetry = getAllFailedMediaForPost(post);
        if (!ListenerUtil.mutListener.listen(24935)) {
            if (!mediaToRetry.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(24924)) {
                    {
                        long _loopCounter386 = 0;
                        // reset these media items to QUEUED
                        for (MediaModel media : mediaToRetry) {
                            ListenerUtil.loopListener.listen("_loopCounter386", ++_loopCounter386);
                            if (!ListenerUtil.mutListener.listen(24922)) {
                                media.setUploadState(MediaUploadState.QUEUED);
                            }
                            if (!ListenerUtil.mutListener.listen(24923)) {
                                mDispatcher.dispatch(MediaActionBuilder.newUpdateMediaAction(media));
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(24929)) {
                    if (processWithAztec) {
                        boolean changesConfirmed = post.contentHashcode() == post.getChangesConfirmedContentHashcode();
                        // do the same within the Post content itself
                        String postContentWithRestartedUploads = AztecEditorFragment.restartFailedMediaToUploading(this, post.getContent());
                        if (!ListenerUtil.mutListener.listen(24925)) {
                            post.setContent(postContentWithRestartedUploads);
                        }
                        if (!ListenerUtil.mutListener.listen(24927)) {
                            if (changesConfirmed) {
                                if (!ListenerUtil.mutListener.listen(24926)) {
                                    /*
                     * We are updating media upload status, but we don't make any undesired changes to the post. We
                     * need to make sure to retain the confirmation state.
                     */
                                    post.setChangesConfirmedContentHashcode(post.contentHashcode());
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(24928)) {
                            mDispatcher.dispatch(PostActionBuilder.newUpdatePostAction(post));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(24931)) {
                    {
                        long _loopCounter387 = 0;
                        // retry uploading media items
                        for (MediaModel media : mediaToRetry) {
                            ListenerUtil.loopListener.listen("_loopCounter387", ++_loopCounter387);
                            if (!ListenerUtil.mutListener.listen(24930)) {
                                mMediaUploadHandler.upload(media);
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(24932)) {
                    // If the post is already registered, the new media will be added to its list
                    mUploadStore.registerPostModel(post, mediaToRetry);
                }
                if (!ListenerUtil.mutListener.listen(24933)) {
                    mPostUploadNotifier.addPostInfoToForegroundNotification(post, mediaToRetry);
                }
                if (!ListenerUtil.mutListener.listen(24934)) {
                    // send event so Editors can handle clearing Failed statuses properly if Post is being edited right now
                    EventBus.getDefault().post(new UploadService.UploadMediaRetryEvent(mediaToRetry));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(24920)) {
                    mPostUploadNotifier.addPostInfoToForegroundNotification(post, null);
                }
                if (!ListenerUtil.mutListener.listen(24921)) {
                    // retry uploading the Post
                    mPostUploadHandler.upload(post);
                }
            }
        }
    }

    private List<MediaModel> getAllFailedMediaForPost(PostModel postModel) {
        Set<MediaModel> failedMedia = mUploadStore.getFailedMediaForPost(postModel);
        return filterOutRecentlyDeletedMedia(failedMedia);
    }

    private List<MediaModel> filterOutRecentlyDeletedMedia(Set<MediaModel> failedMedia) {
        List<MediaModel> mediaToRetry = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(24938)) {
            {
                long _loopCounter388 = 0;
                for (MediaModel mediaModel : failedMedia) {
                    ListenerUtil.loopListener.listen("_loopCounter388", ++_loopCounter388);
                    String mediaIdToCompare = String.valueOf(mediaModel.getId());
                    if (!ListenerUtil.mutListener.listen(24937)) {
                        if (!mUserDeletedMediaItemIds.contains(mediaIdToCompare)) {
                            if (!ListenerUtil.mutListener.listen(24936)) {
                                mediaToRetry.add(mediaModel);
                            }
                        }
                    }
                }
            }
        }
        return mediaToRetry;
    }

    /**
     * Has lower priority than the UploadHandlers, which ensures that the handlers have already received and
     * processed this OnMediaUploaded event. This means we can safely rely on their internal state being up to date.
     */
    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN, priority = 7)
    public void onMediaUploaded(OnMediaUploaded event) {
        if (!ListenerUtil.mutListener.listen(24939)) {
            if (event.media == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(24959)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(24947)) {
                    if ((ListenerUtil.mutListener.listen(24944) ? (event.media.getLocalPostId() >= 0) : (ListenerUtil.mutListener.listen(24943) ? (event.media.getLocalPostId() <= 0) : (ListenerUtil.mutListener.listen(24942) ? (event.media.getLocalPostId() < 0) : (ListenerUtil.mutListener.listen(24941) ? (event.media.getLocalPostId() != 0) : (ListenerUtil.mutListener.listen(24940) ? (event.media.getLocalPostId() == 0) : (event.media.getLocalPostId() > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(24945)) {
                            AppLog.w(T.MAIN, "UploadService > Media upload failed for post " + event.media.getLocalPostId() + " : " + event.error.type + ": " + event.error.message);
                        }
                        String errorMessage = UploadUtils.getErrorMessageFromMediaError(this, event.media, event.error);
                        if (!ListenerUtil.mutListener.listen(24946)) {
                            cancelPostUploadMatchingMedia(event.media, errorMessage, true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(24957)) {
                    if (!mediaBelongsToAPost(event.media)) {
                        if (!ListenerUtil.mutListener.listen(24948)) {
                            // this media item doesn't belong to a Post
                            mPostUploadNotifier.incrementUploadedMediaCountFromProgressNotification(event.media.getId());
                        }
                        // the user actively cancelled it. No need to show an error then.
                        String message = UploadUtils.getErrorMessageFromMediaError(this, event.media, event.error);
                        // if media has a local site id, use that. If not, default to currently selected site.
                        int siteLocalId = (ListenerUtil.mutListener.listen(24953) ? (event.media.getLocalSiteId() >= 0) : (ListenerUtil.mutListener.listen(24952) ? (event.media.getLocalSiteId() <= 0) : (ListenerUtil.mutListener.listen(24951) ? (event.media.getLocalSiteId() < 0) : (ListenerUtil.mutListener.listen(24950) ? (event.media.getLocalSiteId() != 0) : (ListenerUtil.mutListener.listen(24949) ? (event.media.getLocalSiteId() == 0) : (event.media.getLocalSiteId() > 0)))))) ? event.media.getLocalSiteId() : mSelectedSiteRepository.getSelectedSiteLocalId(true);
                        SiteModel selectedSite = mSiteStore.getSiteByLocalId(siteLocalId);
                        List<MediaModel> failedStandAloneMedia = getRetriableStandaloneMedia(selectedSite);
                        if (!ListenerUtil.mutListener.listen(24955)) {
                            if (failedStandAloneMedia.isEmpty()) {
                                if (!ListenerUtil.mutListener.listen(24954)) {
                                    // notification for this particular media item travelling in event.media
                                    failedStandAloneMedia.add(event.media);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(24956)) {
                            mPostUploadNotifier.updateNotificationErrorForMedia(failedStandAloneMedia, selectedSite, message);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(24958)) {
                    stopServiceIfUploadsComplete();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(24971)) {
            if (event.canceled) {
                if (!ListenerUtil.mutListener.listen(24961)) {
                    // remove this media item from the progress notification
                    if (sInstance != null) {
                        if (!ListenerUtil.mutListener.listen(24960)) {
                            sInstance.mPostUploadNotifier.removeOneMediaItemInfoFromForegroundNotification();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(24969)) {
                    if ((ListenerUtil.mutListener.listen(24966) ? (event.media.getLocalPostId() >= 0) : (ListenerUtil.mutListener.listen(24965) ? (event.media.getLocalPostId() <= 0) : (ListenerUtil.mutListener.listen(24964) ? (event.media.getLocalPostId() < 0) : (ListenerUtil.mutListener.listen(24963) ? (event.media.getLocalPostId() != 0) : (ListenerUtil.mutListener.listen(24962) ? (event.media.getLocalPostId() == 0) : (event.media.getLocalPostId() > 0))))))) {
                        if (!ListenerUtil.mutListener.listen(24967)) {
                            AppLog.i(T.MAIN, "UploadService > Upload cancelled for post with id " + event.media.getLocalPostId() + " - a media upload for this post has been cancelled, id: " + event.media.getId());
                        }
                        if (!ListenerUtil.mutListener.listen(24968)) {
                            cancelPostUploadMatchingMedia(event.media, getString(R.string.error_media_canceled), false);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(24970)) {
                    stopServiceIfUploadsComplete();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(24977)) {
            if (event.completed) {
                if (!ListenerUtil.mutListener.listen(24974)) {
                    if (event.media.getLocalPostId() != 0) {
                        if (!ListenerUtil.mutListener.listen(24973)) {
                            AppLog.i(T.MAIN, "UploadService > Processing completed media with id " + event.media.getId() + " and local post id " + event.media.getLocalPostId());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(24975)) {
                    mPostUploadNotifier.incrementUploadedMediaCountFromProgressNotification(event.media.getId());
                }
                if (!ListenerUtil.mutListener.listen(24976)) {
                    stopServiceIfUploadsComplete();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(24972)) {
                    // Progress update
                    mPostUploadNotifier.updateNotificationProgressForMedia(event.media, event.progress);
                }
            }
        }
    }

    /*
     * This method will make sure to keep the bodies of all Posts registered (*) in the UploadStore
     * up-to-date with their corresponding media item upload statuses (i.e. marking them failed or
     * successfully uploaded in the actual Post content to reflect what the UploadStore says).
     *
     * Finally, it will either cancel the Post upload from the queue and create an error notification
     * for the user if there are any failed media items for such a Post, or upload the Post if it's
     * in good shape.
     *
     * This method returns:
     * - `false` if all registered posts have no in-progress items, and at least one or more retriable
     * (failed) items are found in them (this, in other words, means all registered posts are found
     * in a `finalized` state other than "UPLOADED").
     * - `true` if at least one registered Post is found that is in good conditions to be uploaded.
     *
     *
     * (*)`Registered` posts are posts that had media in them and are waiting to be uploaded once
     * their corresponding associated media is uploaded first.
     */
    private boolean doFinalProcessingOfPosts(Boolean isError, PostModel post) {
        if (!ListenerUtil.mutListener.listen(24994)) {
            {
                long _loopCounter389 = 0;
                // This done for pending as well as cancelled and failed posts
                for (PostModel postModel : mUploadStore.getAllRegisteredPosts()) {
                    ListenerUtil.loopListener.listen("_loopCounter389", ++_loopCounter389);
                    if (!ListenerUtil.mutListener.listen(24979)) {
                        if (mPostUtilsWrapper.isPostCurrentlyBeingEdited(postModel)) {
                            if (!ListenerUtil.mutListener.listen(24978)) {
                                // upload state. In case of the post still being edited we cancel any ongoing upload post action.
                                mDispatcher.dispatch(UploadActionBuilder.newCancelPostAction(post));
                            }
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(24993)) {
                        if (!UploadService.hasPendingOrInProgressMediaUploadsForPost(postModel)) {
                            // Replace local with remote media in the post content
                            PostModel updatedPost = updateOnePostModelWithCompletedAndFailedUploads(postModel);
                            if (!ListenerUtil.mutListener.listen(24992)) {
                                if (updatedPost != null) {
                                    // here let's check if there are any failed media
                                    Set<MediaModel> failedMedia = mUploadStore.getFailedMediaForPost(postModel);
                                    if (!ListenerUtil.mutListener.listen(24991)) {
                                        if (!failedMedia.isEmpty()) {
                                            if (!ListenerUtil.mutListener.listen(24984)) {
                                                // but tell the user about the error
                                                cancelQueuedPostUpload(postModel);
                                            }
                                            if (!ListenerUtil.mutListener.listen(24988)) {
                                                // update error notification for Post, unless the media is in the user-deleted media set
                                                if (!isAllFailedMediaUserDeleted(failedMedia)) {
                                                    SiteModel site = mSiteStore.getSiteByLocalId(postModel.getLocalSiteId());
                                                    String message = UploadUtils.getErrorMessage(this, postModel.isPage(), getString(R.string.error_generic_error), true);
                                                    if (!ListenerUtil.mutListener.listen(24987)) {
                                                        if (site != null) {
                                                            if (!ListenerUtil.mutListener.listen(24986)) {
                                                                mPostUploadNotifier.updateNotificationErrorForPost(postModel, site, message, 0);
                                                            }
                                                        } else {
                                                            if (!ListenerUtil.mutListener.listen(24985)) {
                                                                AppLog.e(T.POSTS, "Error notification cannot be updated without a post");
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(24989)) {
                                                mPostUploadHandler.unregisterPostForAnalyticsTracking(postModel.getId());
                                            }
                                            if (!ListenerUtil.mutListener.listen(24990)) {
                                                EventBus.getDefault().post(new PostEvents.PostUploadCanceled(postModel));
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(24982)) {
                                                // Do not re-enqueue a post that has already failed
                                                if ((ListenerUtil.mutListener.listen(24981) ? ((ListenerUtil.mutListener.listen(24980) ? (isError != null || isError) : (isError != null && isError)) || mUploadStore.isFailedPost(updatedPost)) : ((ListenerUtil.mutListener.listen(24980) ? (isError != null || isError) : (isError != null && isError)) && mUploadStore.isFailedPost(updatedPost)))) {
                                                    continue;
                                                }
                                            }
                                            if (!ListenerUtil.mutListener.listen(24983)) {
                                                // e.g. what if the post has local media URLs but no pending media uploads?
                                                mPostUploadHandler.upload(updatedPost);
                                            }
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isAllFailedMediaUserDeleted(Set<MediaModel> failedMediaSet) {
        if (!ListenerUtil.mutListener.listen(25005)) {
            if ((ListenerUtil.mutListener.listen(24995) ? (failedMediaSet != null || failedMediaSet.size() == mUserDeletedMediaItemIds.size()) : (failedMediaSet != null && failedMediaSet.size() == mUserDeletedMediaItemIds.size()))) {
                int numberOfMatches = 0;
                if (!ListenerUtil.mutListener.listen(24998)) {
                    {
                        long _loopCounter390 = 0;
                        for (MediaModel media : failedMediaSet) {
                            ListenerUtil.loopListener.listen("_loopCounter390", ++_loopCounter390);
                            String mediaIdToCompare = String.valueOf(media.getId());
                            if (!ListenerUtil.mutListener.listen(24997)) {
                                if (mUserDeletedMediaItemIds.contains(mediaIdToCompare)) {
                                    if (!ListenerUtil.mutListener.listen(24996)) {
                                        numberOfMatches++;
                                    }
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(25004)) {
                    if ((ListenerUtil.mutListener.listen(25003) ? (numberOfMatches >= mUserDeletedMediaItemIds.size()) : (ListenerUtil.mutListener.listen(25002) ? (numberOfMatches <= mUserDeletedMediaItemIds.size()) : (ListenerUtil.mutListener.listen(25001) ? (numberOfMatches > mUserDeletedMediaItemIds.size()) : (ListenerUtil.mutListener.listen(25000) ? (numberOfMatches < mUserDeletedMediaItemIds.size()) : (ListenerUtil.mutListener.listen(24999) ? (numberOfMatches != mUserDeletedMediaItemIds.size()) : (numberOfMatches == mUserDeletedMediaItemIds.size()))))))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void setDeletedMediaItemIds(List<String> mediaIds) {
        if (!ListenerUtil.mutListener.listen(25006)) {
            mUserDeletedMediaItemIds.clear();
        }
        if (!ListenerUtil.mutListener.listen(25007)) {
            mUserDeletedMediaItemIds.addAll(mediaIds);
        }
    }

    private List<MediaModel> getRetriableStandaloneMedia(SiteModel selectedSite) {
        // get all retriable media ? To retry or not to retry, that is the question
        List<MediaModel> failedStandAloneMedia = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(25011)) {
            if (selectedSite != null) {
                List<MediaModel> failedMedia = mMediaStore.getSiteMediaWithState(selectedSite, MediaUploadState.FAILED);
                if (!ListenerUtil.mutListener.listen(25010)) {
                    {
                        long _loopCounter391 = 0;
                        // only take into account those media items that do not belong to any Post
                        for (MediaModel media : failedMedia) {
                            ListenerUtil.loopListener.listen("_loopCounter391", ++_loopCounter391);
                            if (!ListenerUtil.mutListener.listen(25009)) {
                                if (media.getLocalPostId() == 0) {
                                    if (!ListenerUtil.mutListener.listen(25008)) {
                                        failedStandAloneMedia.add(media);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return failedStandAloneMedia;
    }

    /**
     * Has lower priority than the PostUploadHandler, which ensures that the handler has already received and
     * processed this OnPostUploaded event. This means we can safely rely on its internal state being up to date.
     */
    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN, priority = 7)
    public void onPostUploaded(OnPostUploaded event) {
        if (!ListenerUtil.mutListener.listen(25012)) {
            stopServiceIfUploadsComplete(event.isError(), event.post);
        }
    }

    /**
     * Has lower priority than the PostUploadHandler, which ensures that the handler has already received and
     * processed this OnPostChanged event. This means we can safely rely on its internal state being up to date.
     */
    @Subscribe(threadMode = ThreadMode.MAIN, priority = 7)
    public void onPostChanged(OnPostChanged event) {
        if (!ListenerUtil.mutListener.listen(25014)) {
            if (event.causeOfChange instanceof CauseOfOnPostChanged.RemoteAutoSavePost) {
                PostModel post = mPostStore.getPostByLocalPostId(((RemoteAutoSavePost) event.causeOfChange).getLocalPostId());
                if (!ListenerUtil.mutListener.listen(25013)) {
                    stopServiceIfUploadsComplete(event.isError(), post);
                }
            }
        }
    }

    public static class UploadErrorEvent {

        public final PostModel post;

        public final List<MediaModel> mediaModelList;

        public final String errorMessage;

        public UploadErrorEvent(PostModel post, String errorMessage) {
            this.post = post;
            this.mediaModelList = null;
            this.errorMessage = errorMessage;
        }

        public UploadErrorEvent(List<MediaModel> mediaModelList, String errorMessage) {
            this.post = null;
            this.mediaModelList = mediaModelList;
            this.errorMessage = errorMessage;
        }
    }

    public static class UploadMediaSuccessEvent {

        public final List<MediaModel> mediaModelList;

        public final String successMessage;

        public UploadMediaSuccessEvent(List<MediaModel> mediaModelList, String successMessage) {
            this.mediaModelList = mediaModelList;
            this.successMessage = successMessage;
        }
    }

    public static class UploadMediaRetryEvent {

        public final List<MediaModel> mediaModelList;

        UploadMediaRetryEvent(List<MediaModel> mediaModelList) {
            this.mediaModelList = mediaModelList;
        }
    }
}
