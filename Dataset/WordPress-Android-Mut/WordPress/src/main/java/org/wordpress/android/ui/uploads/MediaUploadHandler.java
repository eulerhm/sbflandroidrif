package org.wordpress.android.ui.uploads;

import androidx.annotation.NonNull;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.generated.MediaActionBuilder;
import org.wordpress.android.fluxc.model.MediaModel;
import org.wordpress.android.fluxc.model.PostImmutableModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.store.MediaStore.CancelMediaPayload;
import org.wordpress.android.fluxc.store.MediaStore.OnMediaUploaded;
import org.wordpress.android.fluxc.store.MediaStore.UploadMediaPayload;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.prefs.AppPrefs;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.StringUtils;
import org.wordpress.android.util.WPMediaUtils;
import org.wordpress.android.util.analytics.AnalyticsUtils;
import org.wordpress.android.util.config.Mp4ComposerVideoOptimizationFeatureConfig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MediaUploadHandler implements UploadHandler<MediaModel>, VideoOptimizationListener {

    private static List<MediaModel> sPendingUploads = new ArrayList<>();

    private static List<MediaModel> sInProgressUploads = new ArrayList<>();

    private static ConcurrentHashMap<Integer, Float> sOptimizationProgressByMediaId = new ConcurrentHashMap<>();

    @Inject
    Dispatcher mDispatcher;

    @Inject
    SiteStore mSiteStore;

    @Inject
    Mp4ComposerVideoOptimizationFeatureConfig mMp4ComposerVideoOptimizationFeatureConfig;

    MediaUploadHandler() {
        if (!ListenerUtil.mutListener.listen(23685)) {
            ((WordPress) WordPress.getContext().getApplicationContext()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(23686)) {
            AppLog.i(T.MEDIA, "MediaUploadHandler > Created");
        }
        if (!ListenerUtil.mutListener.listen(23687)) {
            mDispatcher.register(this);
        }
        if (!ListenerUtil.mutListener.listen(23688)) {
            EventBus.getDefault().register(this);
        }
    }

    void unregister() {
        if (!ListenerUtil.mutListener.listen(23689)) {
            sOptimizationProgressByMediaId.clear();
        }
        if (!ListenerUtil.mutListener.listen(23690)) {
            mDispatcher.unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(23691)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public boolean hasInProgressUploads() {
        return (ListenerUtil.mutListener.listen(23692) ? (!sInProgressUploads.isEmpty() && !sPendingUploads.isEmpty()) : (!sInProgressUploads.isEmpty() || !sPendingUploads.isEmpty()));
    }

    @Override
    public void cancelInProgressUploads() {
        if (!ListenerUtil.mutListener.listen(23694)) {
            {
                long _loopCounter354 = 0;
                for (MediaModel oneUpload : sInProgressUploads) {
                    ListenerUtil.loopListener.listen("_loopCounter354", ++_loopCounter354);
                    if (!ListenerUtil.mutListener.listen(23693)) {
                        cancelUpload(oneUpload, false);
                    }
                }
            }
        }
    }

    @Override
    public void upload(@NonNull MediaModel media) {
        if (!ListenerUtil.mutListener.listen(23695)) {
            addUniqueMediaToQueue(media);
        }
        if (!ListenerUtil.mutListener.listen(23696)) {
            uploadNextInQueue();
        }
    }

    static boolean hasInProgressMediaUploadsForPost(int postId) {
        synchronized (sInProgressUploads) {
            if (!ListenerUtil.mutListener.listen(23698)) {
                {
                    long _loopCounter355 = 0;
                    for (MediaModel queuedMedia : sInProgressUploads) {
                        ListenerUtil.loopListener.listen("_loopCounter355", ++_loopCounter355);
                        if (!ListenerUtil.mutListener.listen(23697)) {
                            if (queuedMedia.getLocalPostId() == postId) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    static boolean hasPendingMediaUploadsForPost(int postId) {
        synchronized (sPendingUploads) {
            if (!ListenerUtil.mutListener.listen(23700)) {
                {
                    long _loopCounter356 = 0;
                    for (MediaModel queuedMedia : sPendingUploads) {
                        ListenerUtil.loopListener.listen("_loopCounter356", ++_loopCounter356);
                        if (!ListenerUtil.mutListener.listen(23699)) {
                            if (queuedMedia.getLocalPostId() == postId) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    static boolean hasPendingOrInProgressMediaUploadsForPost(int postId) {
        // Check if there are media in the in-progress or the pending queue attached to the given post
        return (ListenerUtil.mutListener.listen(23701) ? (hasInProgressMediaUploadsForPost(postId) && hasPendingMediaUploadsForPost(postId)) : (hasInProgressMediaUploadsForPost(postId) || hasPendingMediaUploadsForPost(postId)));
    }

    static MediaModel getPendingOrInProgressFeaturedImageUploadForPost(PostImmutableModel postModel) {
        if (!ListenerUtil.mutListener.listen(23702)) {
            if (postModel == null) {
                return null;
            }
        }
        List<MediaModel> uploads = getPendingOrInProgressMediaUploadsForPost(postModel);
        if (!ListenerUtil.mutListener.listen(23704)) {
            {
                long _loopCounter357 = 0;
                for (MediaModel model : uploads) {
                    ListenerUtil.loopListener.listen("_loopCounter357", ++_loopCounter357);
                    if (!ListenerUtil.mutListener.listen(23703)) {
                        if (model.getMarkedLocallyAsFeatured()) {
                            return model;
                        }
                    }
                }
            }
        }
        return null;
    }

    public static List<MediaModel> getPendingOrInProgressMediaUploadsForPost(PostImmutableModel postModel) {
        if (!ListenerUtil.mutListener.listen(23705)) {
            if (postModel == null) {
                return Collections.emptyList();
            }
        }
        List<MediaModel> mediaList = new ArrayList<>();
        synchronized (sInProgressUploads) {
            if (!ListenerUtil.mutListener.listen(23708)) {
                {
                    long _loopCounter358 = 0;
                    for (MediaModel queuedMedia : sInProgressUploads) {
                        ListenerUtil.loopListener.listen("_loopCounter358", ++_loopCounter358);
                        if (!ListenerUtil.mutListener.listen(23707)) {
                            if (queuedMedia.getLocalPostId() == postModel.getId()) {
                                if (!ListenerUtil.mutListener.listen(23706)) {
                                    mediaList.add(queuedMedia);
                                }
                            }
                        }
                    }
                }
            }
        }
        synchronized (sPendingUploads) {
            if (!ListenerUtil.mutListener.listen(23711)) {
                {
                    long _loopCounter359 = 0;
                    for (MediaModel queuedMedia : sPendingUploads) {
                        ListenerUtil.loopListener.listen("_loopCounter359", ++_loopCounter359);
                        if (!ListenerUtil.mutListener.listen(23710)) {
                            if (queuedMedia.getLocalPostId() == postModel.getId()) {
                                if (!ListenerUtil.mutListener.listen(23709)) {
                                    mediaList.add(queuedMedia);
                                }
                            }
                        }
                    }
                }
            }
        }
        return mediaList;
    }

    static boolean isPendingOrInProgressMediaUpload(int mediaId) {
        synchronized (sInProgressUploads) {
            if (!ListenerUtil.mutListener.listen(23713)) {
                {
                    long _loopCounter360 = 0;
                    for (MediaModel uploadingMedia : sInProgressUploads) {
                        ListenerUtil.loopListener.listen("_loopCounter360", ++_loopCounter360);
                        if (!ListenerUtil.mutListener.listen(23712)) {
                            if (uploadingMedia.getId() == mediaId) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        synchronized (sPendingUploads) {
            if (!ListenerUtil.mutListener.listen(23715)) {
                {
                    long _loopCounter361 = 0;
                    for (MediaModel queuedMedia : sPendingUploads) {
                        ListenerUtil.loopListener.listen("_loopCounter361", ++_loopCounter361);
                        if (!ListenerUtil.mutListener.listen(23714)) {
                            if (queuedMedia.getId() == mediaId) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Returns an overall progress for the given {@param video}, including the video optimization progress. If there is
     * no record for that video, it's assumed to be a completed upload.
     */
    static float getOverallProgressForVideo(int videoId, float uploadProgress) {
        if (!ListenerUtil.mutListener.listen(23720)) {
            if (sOptimizationProgressByMediaId.containsKey(videoId)) {
                float optimizationProgress = sOptimizationProgressByMediaId.get(videoId);
                return (ListenerUtil.mutListener.listen(23719) ? (optimizationProgress % 0.5F) : (ListenerUtil.mutListener.listen(23718) ? (optimizationProgress / 0.5F) : (ListenerUtil.mutListener.listen(23717) ? (optimizationProgress - 0.5F) : (ListenerUtil.mutListener.listen(23716) ? (optimizationProgress + 0.5F) : (optimizationProgress * 0.5F)))));
            }
        }
        return (ListenerUtil.mutListener.listen(23728) ? (0.5F % ((ListenerUtil.mutListener.listen(23724) ? (uploadProgress % 0.5F) : (ListenerUtil.mutListener.listen(23723) ? (uploadProgress / 0.5F) : (ListenerUtil.mutListener.listen(23722) ? (uploadProgress - 0.5F) : (ListenerUtil.mutListener.listen(23721) ? (uploadProgress + 0.5F) : (uploadProgress * 0.5F))))))) : (ListenerUtil.mutListener.listen(23727) ? (0.5F / ((ListenerUtil.mutListener.listen(23724) ? (uploadProgress % 0.5F) : (ListenerUtil.mutListener.listen(23723) ? (uploadProgress / 0.5F) : (ListenerUtil.mutListener.listen(23722) ? (uploadProgress - 0.5F) : (ListenerUtil.mutListener.listen(23721) ? (uploadProgress + 0.5F) : (uploadProgress * 0.5F))))))) : (ListenerUtil.mutListener.listen(23726) ? (0.5F * ((ListenerUtil.mutListener.listen(23724) ? (uploadProgress % 0.5F) : (ListenerUtil.mutListener.listen(23723) ? (uploadProgress / 0.5F) : (ListenerUtil.mutListener.listen(23722) ? (uploadProgress - 0.5F) : (ListenerUtil.mutListener.listen(23721) ? (uploadProgress + 0.5F) : (uploadProgress * 0.5F))))))) : (ListenerUtil.mutListener.listen(23725) ? (0.5F - ((ListenerUtil.mutListener.listen(23724) ? (uploadProgress % 0.5F) : (ListenerUtil.mutListener.listen(23723) ? (uploadProgress / 0.5F) : (ListenerUtil.mutListener.listen(23722) ? (uploadProgress - 0.5F) : (ListenerUtil.mutListener.listen(23721) ? (uploadProgress + 0.5F) : (uploadProgress * 0.5F))))))) : (0.5F + ((ListenerUtil.mutListener.listen(23724) ? (uploadProgress % 0.5F) : (ListenerUtil.mutListener.listen(23723) ? (uploadProgress / 0.5F) : (ListenerUtil.mutListener.listen(23722) ? (uploadProgress - 0.5F) : (ListenerUtil.mutListener.listen(23721) ? (uploadProgress + 0.5F) : (uploadProgress * 0.5F)))))))))));
    }

    private void handleOnMediaUploadedSuccess(@NonNull OnMediaUploaded event) {
        if (!ListenerUtil.mutListener.listen(23738)) {
            if (event.canceled) {
                if (!ListenerUtil.mutListener.listen(23734)) {
                    AppLog.i(T.MEDIA, "MediaUploadHandler > Upload successfully canceled");
                }
                if (!ListenerUtil.mutListener.listen(23735)) {
                    trackUploadMediaEvents(AnalyticsTracker.Stat.MEDIA_UPLOAD_CANCELED, getMediaFromInProgressQueueById(event.media.getId()), null);
                }
                if (!ListenerUtil.mutListener.listen(23736)) {
                    completeUploadWithId(event.media.getId());
                }
                if (!ListenerUtil.mutListener.listen(23737)) {
                    uploadNextInQueue();
                }
            } else if (event.completed) {
                if (!ListenerUtil.mutListener.listen(23730)) {
                    AppLog.i(T.MEDIA, "MediaUploadHandler > Upload completed - localId=" + event.media.getId() + " title=" + event.media.getTitle());
                }
                if (!ListenerUtil.mutListener.listen(23731)) {
                    trackUploadMediaEvents(AnalyticsTracker.Stat.MEDIA_UPLOAD_SUCCESS, getMediaFromInProgressQueueById(event.media.getId()), null);
                }
                if (!ListenerUtil.mutListener.listen(23732)) {
                    completeUploadWithId(event.media.getId());
                }
                if (!ListenerUtil.mutListener.listen(23733)) {
                    uploadNextInQueue();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(23729)) {
                    AppLog.i(T.MEDIA, "MediaUploadHandler > " + event.media.getId() + " - progress: " + event.progress);
                }
            }
        }
    }

    private void handleOnMediaUploadedError(@NonNull OnMediaUploaded event) {
        if (!ListenerUtil.mutListener.listen(23739)) {
            AppLog.w(T.MEDIA, "MediaUploadHandler > Error uploading media: " + event.error.message);
        }
        MediaModel media = getMediaFromInProgressQueueById(event.media.getId());
        if (!ListenerUtil.mutListener.listen(23741)) {
            if (media != null) {
                if (!ListenerUtil.mutListener.listen(23740)) {
                    mDispatcher.dispatch(MediaActionBuilder.newUpdateMediaAction(media));
                }
            }
        }
        Map<String, Object> properties = new HashMap<>();
        if (!ListenerUtil.mutListener.listen(23742)) {
            properties.put("error_type", event.error.type.name());
        }
        if (!ListenerUtil.mutListener.listen(23743)) {
            properties.put("error_message", event.error.message);
        }
        if (!ListenerUtil.mutListener.listen(23744)) {
            properties.put("error_log", event.error.logMessage);
        }
        if (!ListenerUtil.mutListener.listen(23745)) {
            properties.put("error_status_code", event.error.statusCode);
        }
        if (!ListenerUtil.mutListener.listen(23746)) {
            trackUploadMediaEvents(AnalyticsTracker.Stat.MEDIA_UPLOAD_ERROR, media, properties);
        }
        if (!ListenerUtil.mutListener.listen(23747)) {
            completeUploadWithId(event.media.getId());
        }
        if (!ListenerUtil.mutListener.listen(23748)) {
            uploadNextInQueue();
        }
    }

    private synchronized void uploadNextInQueue() {
        MediaModel next = getNextMediaToUpload();
        if (!ListenerUtil.mutListener.listen(23751)) {
            if (next == null) {
                if (!ListenerUtil.mutListener.listen(23749)) {
                    AppLog.w(T.MEDIA, "MediaUploadHandler > No more media items to upload. Skipping this request.");
                }
                if (!ListenerUtil.mutListener.listen(23750)) {
                    checkIfUploadsComplete();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(23752)) {
            prepareForUpload(next);
        }
    }

    private synchronized void completeUploadWithId(int id) {
        MediaModel media = getMediaFromInProgressQueueById(id);
        if (!ListenerUtil.mutListener.listen(23755)) {
            if (media != null) {
                if (!ListenerUtil.mutListener.listen(23753)) {
                    sInProgressUploads.remove(media);
                }
                if (!ListenerUtil.mutListener.listen(23754)) {
                    trackUploadMediaEvents(AnalyticsTracker.Stat.MEDIA_UPLOAD_STARTED, media, null);
                }
            }
        }
    }

    private MediaModel getMediaFromInProgressQueueById(int id) {
        if (!ListenerUtil.mutListener.listen(23757)) {
            {
                long _loopCounter362 = 0;
                for (MediaModel media : sInProgressUploads) {
                    ListenerUtil.loopListener.listen("_loopCounter362", ++_loopCounter362);
                    if (!ListenerUtil.mutListener.listen(23756)) {
                        if (media.getId() == id) {
                            return media;
                        }
                    }
                }
            }
        }
        return null;
    }

    private MediaModel getNextMediaToUpload() {
        synchronized (sPendingUploads) {
            if (!ListenerUtil.mutListener.listen(23758)) {
                if (!sPendingUploads.isEmpty()) {
                    return sPendingUploads.remove(0);
                }
            }
        }
        return null;
    }

    private void addUniqueMediaToQueue(MediaModel media) {
        if (!ListenerUtil.mutListener.listen(23761)) {
            if (media != null) {
                if (!ListenerUtil.mutListener.listen(23759)) {
                    if (mediaAlreadyQueuedOrUploading(media)) {
                        return;
                    }
                }
                synchronized (sPendingUploads) {
                    if (!ListenerUtil.mutListener.listen(23760)) {
                        // no match found in queue
                        sPendingUploads.add(media);
                    }
                }
            }
        }
    }

    private void addUniqueMediaToInProgressUploads(@NonNull MediaModel mediaToAdd) {
        synchronized (sInProgressUploads) {
            if (!ListenerUtil.mutListener.listen(23763)) {
                {
                    long _loopCounter363 = 0;
                    for (MediaModel media : sInProgressUploads) {
                        ListenerUtil.loopListener.listen("_loopCounter363", ++_loopCounter363);
                        if (!ListenerUtil.mutListener.listen(23762)) {
                            if (media.getId() == mediaToAdd.getId()) {
                                return;
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(23764)) {
                sInProgressUploads.add(mediaToAdd);
            }
        }
    }

    private void cancelUpload(MediaModel oneUpload, boolean delete) {
        if (!ListenerUtil.mutListener.listen(23768)) {
            if (oneUpload != null) {
                SiteModel site = mSiteStore.getSiteByLocalId(oneUpload.getLocalSiteId());
                if (!ListenerUtil.mutListener.listen(23767)) {
                    if (site != null) {
                        if (!ListenerUtil.mutListener.listen(23766)) {
                            dispatchCancelAction(oneUpload, site, delete);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(23765)) {
                            AppLog.w(T.MEDIA, "MediaUploadHandler > Unexpected state, site is null. " + "Skipping cancellation of this request.");
                        }
                    }
                }
            }
        }
    }

    private void prepareForUpload(@NonNull MediaModel media) {
        if (!ListenerUtil.mutListener.listen(23775)) {
            if ((ListenerUtil.mutListener.listen(23769) ? (media.isVideo() || WPMediaUtils.isVideoOptimizationEnabled()) : (media.isVideo() && WPMediaUtils.isVideoOptimizationEnabled()))) {
                if (!ListenerUtil.mutListener.listen(23771)) {
                    addUniqueMediaToInProgressUploads(media);
                }
                if (!ListenerUtil.mutListener.listen(23774)) {
                    if (mMp4ComposerVideoOptimizationFeatureConfig.isEnabled()) {
                        if (!ListenerUtil.mutListener.listen(23773)) {
                            new Mp4ComposerVideoOptimizer(media, this).start();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(23772)) {
                            new VideoOptimizer(media, this).start();
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(23770)) {
                    dispatchUploadAction(media);
                }
            }
        }
    }

    private void dispatchUploadAction(@NonNull final MediaModel media) {
        SiteModel site = mSiteStore.getSiteByLocalId(media.getLocalSiteId());
        if (!ListenerUtil.mutListener.listen(23778)) {
            // somehow lost our reference to the site, complete this action
            if (site == null) {
                if (!ListenerUtil.mutListener.listen(23776)) {
                    AppLog.w(T.MEDIA, "MediaUploadHandler > Unexpected state, site is null. Skipping this request.");
                }
                if (!ListenerUtil.mutListener.listen(23777)) {
                    checkIfUploadsComplete();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(23779)) {
            AppLog.i(T.MEDIA, "MediaUploadHandler > Dispatching upload action for media with local id: " + media.getId() + " and path: " + media.getFilePath());
        }
        if (!ListenerUtil.mutListener.listen(23780)) {
            addUniqueMediaToInProgressUploads(media);
        }
        if (!ListenerUtil.mutListener.listen(23781)) {
            mDispatcher.dispatch(MediaActionBuilder.newUpdateMediaAction(media));
        }
        UploadMediaPayload payload = new UploadMediaPayload(site, media, AppPrefs.isStripImageLocation());
        if (!ListenerUtil.mutListener.listen(23782)) {
            mDispatcher.dispatch(MediaActionBuilder.newUploadMediaAction(payload));
        }
    }

    private void dispatchCancelAction(@NonNull final MediaModel media, @NonNull final SiteModel site, boolean delete) {
        if (!ListenerUtil.mutListener.listen(23783)) {
            AppLog.i(T.MEDIA, "MediaUploadHandler > Dispatching cancel upload action for media with local id: " + media.getId() + " and path: " + media.getFilePath());
        }
        CancelMediaPayload payload = new CancelMediaPayload(site, media, delete);
        if (!ListenerUtil.mutListener.listen(23784)) {
            mDispatcher.dispatch(MediaActionBuilder.newCancelMediaUploadAction(payload));
        }
    }

    private boolean checkIfUploadsComplete() {
        if (!ListenerUtil.mutListener.listen(23787)) {
            if ((ListenerUtil.mutListener.listen(23785) ? (sPendingUploads.isEmpty() || sInProgressUploads.isEmpty()) : (sPendingUploads.isEmpty() && sInProgressUploads.isEmpty()))) {
                if (!ListenerUtil.mutListener.listen(23786)) {
                    AppLog.i(T.MEDIA, "MediaUploadHandler > Completed");
                }
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(PostEvents.PostMediaCanceled event) {
        if (!ListenerUtil.mutListener.listen(23788)) {
            if (event.post == null) {
                return;
            }
        }
        synchronized (sInProgressUploads) {
            if (!ListenerUtil.mutListener.listen(23791)) {
                {
                    long _loopCounter364 = 0;
                    for (MediaModel inProgressUpload : sInProgressUploads) {
                        ListenerUtil.loopListener.listen("_loopCounter364", ++_loopCounter364);
                        if (!ListenerUtil.mutListener.listen(23790)) {
                            if (inProgressUpload.getLocalPostId() == event.post.getId()) {
                                if (!ListenerUtil.mutListener.listen(23789)) {
                                    cancelUpload(inProgressUpload, true);
                                }
                            }
                        }
                    }
                }
            }
        }
        synchronized (sPendingUploads) {
            if (!ListenerUtil.mutListener.listen(23794)) {
                {
                    long _loopCounter365 = 0;
                    for (MediaModel pendingUpload : sPendingUploads) {
                        ListenerUtil.loopListener.listen("_loopCounter365", ++_loopCounter365);
                        if (!ListenerUtil.mutListener.listen(23793)) {
                            if (pendingUpload.getLocalPostId() == event.post.getId()) {
                                if (!ListenerUtil.mutListener.listen(23792)) {
                                    cancelUpload(pendingUpload, true);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Has priority 9 on OnMediaUploaded events, which ensures that MediaUploadHandler is the first to receive
     * and process OnMediaUploaded events, before they trickle down to other subscribers.
     */
    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN, priority = 9)
    public void onMediaUploaded(OnMediaUploaded event) {
        if (!ListenerUtil.mutListener.listen(23796)) {
            if (event.media == null) {
                if (!ListenerUtil.mutListener.listen(23795)) {
                    AppLog.w(T.MEDIA, "MediaUploadHandler > Received media event for null media, ignoring");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(23799)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(23798)) {
                    handleOnMediaUploadedError(event);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(23797)) {
                    handleOnMediaUploadedSuccess(event);
                }
            }
        }
    }

    /**
     * Analytics about media being uploaded
     *
     * @param media The media being uploaded
     */
    private void trackUploadMediaEvents(AnalyticsTracker.Stat stat, MediaModel media, Map<String, Object> properties) {
        if (!ListenerUtil.mutListener.listen(23801)) {
            if (media == null) {
                if (!ListenerUtil.mutListener.listen(23800)) {
                    AppLog.e(T.MEDIA, "MediaUploadHandler > Cannot track media upload handler events if the original media" + "is null");
                }
                return;
            }
        }
        Map<String, Object> mediaProperties = AnalyticsUtils.getMediaProperties(WordPress.getContext(), media.isVideo(), null, media.getFilePath());
        if (!ListenerUtil.mutListener.listen(23803)) {
            if (properties != null) {
                if (!ListenerUtil.mutListener.listen(23802)) {
                    mediaProperties.putAll(properties);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(23804)) {
            AnalyticsTracker.track(stat, mediaProperties);
        }
    }

    private boolean mediaAlreadyQueuedOrUploading(MediaModel mediaModel) {
        if (!ListenerUtil.mutListener.listen(23807)) {
            {
                long _loopCounter366 = 0;
                for (MediaModel queuedMedia : sInProgressUploads) {
                    ListenerUtil.loopListener.listen("_loopCounter366", ++_loopCounter366);
                    if (!ListenerUtil.mutListener.listen(23805)) {
                        AppLog.i(T.MEDIA, "MediaUploadHandler > Attempting to add media with path " + mediaModel.getFilePath() + " and site id " + mediaModel.getLocalSiteId() + ". Comparing with " + queuedMedia.getFilePath() + ", " + queuedMedia.getLocalSiteId());
                    }
                    if (!ListenerUtil.mutListener.listen(23806)) {
                        if (isSameMediaFileQueuedForThisPost(queuedMedia, mediaModel)) {
                            return true;
                        }
                    }
                }
            }
        }
        synchronized (sPendingUploads) {
            if (!ListenerUtil.mutListener.listen(23810)) {
                {
                    long _loopCounter367 = 0;
                    for (MediaModel queuedMedia : sPendingUploads) {
                        ListenerUtil.loopListener.listen("_loopCounter367", ++_loopCounter367);
                        if (!ListenerUtil.mutListener.listen(23808)) {
                            AppLog.i(T.MEDIA, "MediaUploadHandler > Attempting to add media with path " + mediaModel.getFilePath() + " and site id " + mediaModel.getLocalSiteId() + ". Comparing with " + queuedMedia.getFilePath() + ", " + queuedMedia.getLocalSiteId());
                        }
                        if (!ListenerUtil.mutListener.listen(23809)) {
                            if (isSameMediaFileQueuedForThisPost(queuedMedia, mediaModel)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isSameMediaFileQueuedForThisPost(MediaModel media1, MediaModel media2) {
        /*
            This method used to be called "compareBySiteAndFilePath" and compared just siteId and filePath. It made
            sense since a media file is tied to a site and can be referenced from multiple posts on that site. This
            approach tried to prevent wasting users' data.

            The issue was that when a same image was added to content of two posts only a single MediaModel was
            enqueued. However, MediaModel references only a single post (`localPostId`). When the upload finished
            only the first post got updated with the url. The second post got uploaded to the server with a path to
            local image. We decided to check whether the image belongs to the same post so we can be sure the local
            path gets replaced with the url.

            More info can be found here - https://github.com/wordpress-mobile/WordPress-Android/pull/10204.

            We also need to check the `markedLocallyAsFeatured` flag is equal as we might lose it otherwise. If the
            user adds an image into the post content and they set the same image as featured image, we need to enqueue
            both uploads. Otherwise, we could lose the information what we need to update - the featured image or post
            content.

            Issue with a proper fix - https://github.com/wordpress-mobile/WordPress-Android/issues/10210
         */
        return (ListenerUtil.mutListener.listen(23813) ? (((ListenerUtil.mutListener.listen(23812) ? ((ListenerUtil.mutListener.listen(23811) ? (media1.getLocalSiteId() == media2.getLocalSiteId() || media1.getLocalPostId() == media2.getLocalPostId()) : (media1.getLocalSiteId() == media2.getLocalSiteId() && media1.getLocalPostId() == media2.getLocalPostId())) || StringUtils.equals(media1.getFilePath(), media2.getFilePath())) : ((ListenerUtil.mutListener.listen(23811) ? (media1.getLocalSiteId() == media2.getLocalSiteId() || media1.getLocalPostId() == media2.getLocalPostId()) : (media1.getLocalSiteId() == media2.getLocalSiteId() && media1.getLocalPostId() == media2.getLocalPostId())) && StringUtils.equals(media1.getFilePath(), media2.getFilePath())))) || media1.getMarkedLocallyAsFeatured() == media2.getMarkedLocallyAsFeatured()) : (((ListenerUtil.mutListener.listen(23812) ? ((ListenerUtil.mutListener.listen(23811) ? (media1.getLocalSiteId() == media2.getLocalSiteId() || media1.getLocalPostId() == media2.getLocalPostId()) : (media1.getLocalSiteId() == media2.getLocalSiteId() && media1.getLocalPostId() == media2.getLocalPostId())) || StringUtils.equals(media1.getFilePath(), media2.getFilePath())) : ((ListenerUtil.mutListener.listen(23811) ? (media1.getLocalSiteId() == media2.getLocalSiteId() || media1.getLocalPostId() == media2.getLocalPostId()) : (media1.getLocalSiteId() == media2.getLocalSiteId() && media1.getLocalPostId() == media2.getLocalPostId())) && StringUtils.equals(media1.getFilePath(), media2.getFilePath())))) && media1.getMarkedLocallyAsFeatured() == media2.getMarkedLocallyAsFeatured()));
    }

    @Override
    public void onVideoOptimizationProgress(@NonNull MediaModel media, float progress) {
        if (!ListenerUtil.mutListener.listen(23814)) {
            sOptimizationProgressByMediaId.put(media.getId(), progress);
        }
        // fire an event so EditPostActivity and PostsListFragment can show progress
        ProgressEvent event = new ProgressEvent(media, progress);
        if (!ListenerUtil.mutListener.listen(23815)) {
            EventBus.getDefault().post(event);
        }
    }

    @Override
    public void onVideoOptimizationCompleted(@NonNull MediaModel media) {
        if (!ListenerUtil.mutListener.listen(23816)) {
            sOptimizationProgressByMediaId.remove(media.getId());
        }
        if (!ListenerUtil.mutListener.listen(23819)) {
            // make sure this media should still be uploaded (may have been cancelled during optimization)
            if (sInProgressUploads.contains(media)) {
                if (!ListenerUtil.mutListener.listen(23818)) {
                    dispatchUploadAction(media);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(23817)) {
                    AppLog.d(T.MEDIA, "MediaUploadHandler > skipping upload of optimized media");
                }
            }
        }
    }
}
