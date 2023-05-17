package org.wordpress.android.ui.uploads;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.Video;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.generated.MediaActionBuilder;
import org.wordpress.android.fluxc.generated.PostActionBuilder;
import org.wordpress.android.fluxc.model.MediaModel;
import org.wordpress.android.fluxc.model.MediaModel.MediaUploadState;
import org.wordpress.android.fluxc.model.PostImmutableModel;
import org.wordpress.android.fluxc.model.PostModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.model.post.PostStatus;
import org.wordpress.android.fluxc.store.MediaStore;
import org.wordpress.android.fluxc.store.MediaStore.UploadMediaPayload;
import org.wordpress.android.fluxc.store.PostStore;
import org.wordpress.android.fluxc.store.PostStore.OnPostUploaded;
import org.wordpress.android.fluxc.store.PostStore.RemotePostPayload;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.posts.PostUtils;
import org.wordpress.android.ui.prefs.AppPrefs;
import org.wordpress.android.ui.uploads.AutoSavePostIfNotDraftResult.FetchPostStatusFailed;
import org.wordpress.android.ui.uploads.AutoSavePostIfNotDraftResult.PostAutoSaveFailed;
import org.wordpress.android.ui.uploads.AutoSavePostIfNotDraftResult.PostAutoSaved;
import org.wordpress.android.ui.uploads.AutoSavePostIfNotDraftResult.PostIsDraftInRemote;
import org.wordpress.android.ui.uploads.PostEvents.PostUploadStarted;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.FluxCUtils;
import org.wordpress.android.util.MediaUtils;
import org.wordpress.android.util.SiteUtils;
import org.wordpress.android.util.SqlUtils;
import org.wordpress.android.util.analytics.AnalyticsUtils;
import org.wordpress.android.util.helpers.MediaFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class PostUploadHandler implements UploadHandler<PostModel>, OnAutoSavePostIfNotDraftCallback {

    private static ArrayList<PostModel> sQueuedPostsList = new ArrayList<>();

    private static Set<Integer> sFirstPublishPosts = new HashSet<>();

    private static PostModel sCurrentUploadingPost = null;

    private static Map<String, Object> sCurrentUploadingPostAnalyticsProperties;

    private PostUploadNotifier mPostUploadNotifier;

    private UploadPostTask mCurrentTask = null;

    private SparseArray<CountDownLatch> mMediaLatchMap = new SparseArray<>();

    @Inject
    Dispatcher mDispatcher;

    @Inject
    SiteStore mSiteStore;

    @Inject
    PostStore mPostStore;

    @Inject
    MediaStore mMediaStore;

    @Inject
    UiHelpers mUiHelpers;

    @Inject
    UploadActionUseCase mUploadActionUseCase;

    @Inject
    AutoSavePostIfNotDraftUseCase mAutoSavePostIfNotDraftUseCase;

    @Inject
    PostMediaHandler mPostMediaHandler;

    PostUploadHandler(PostUploadNotifier postUploadNotifier) {
        if (!ListenerUtil.mutListener.listen(23855)) {
            ((WordPress) WordPress.getContext().getApplicationContext()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(23856)) {
            AppLog.i(T.POSTS, "PostUploadHandler > Created");
        }
        if (!ListenerUtil.mutListener.listen(23857)) {
            mDispatcher.register(this);
        }
        if (!ListenerUtil.mutListener.listen(23858)) {
            mPostUploadNotifier = postUploadNotifier;
        }
    }

    void unregister() {
        if (!ListenerUtil.mutListener.listen(23859)) {
            mDispatcher.unregister(this);
        }
    }

    @Override
    public boolean hasInProgressUploads() {
        return (ListenerUtil.mutListener.listen(23860) ? (mCurrentTask != null && !sQueuedPostsList.isEmpty()) : (mCurrentTask != null || !sQueuedPostsList.isEmpty()));
    }

    @Override
    public void cancelInProgressUploads() {
        if (!ListenerUtil.mutListener.listen(23863)) {
            if (mCurrentTask != null) {
                if (!ListenerUtil.mutListener.listen(23861)) {
                    AppLog.i(T.POSTS, "PostUploadHandler > Cancelling current upload task");
                }
                if (!ListenerUtil.mutListener.listen(23862)) {
                    mCurrentTask.cancel(true);
                }
            }
        }
    }

    @Override
    public void upload(@NonNull PostModel post) {
        synchronized (sQueuedPostsList) {
            if (!ListenerUtil.mutListener.listen(23866)) {
                {
                    long _loopCounter368 = 0;
                    // for being uploaded
                    for (PostModel queuedPost : sQueuedPostsList) {
                        ListenerUtil.loopListener.listen("_loopCounter368", ++_loopCounter368);
                        if (!ListenerUtil.mutListener.listen(23865)) {
                            if (queuedPost.getId() == post.getId()) {
                                if (!ListenerUtil.mutListener.listen(23864)) {
                                    // we found an older version, so let's remove it and replace it with the newest copy
                                    sQueuedPostsList.remove(queuedPost);
                                }
                                break;
                            }
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(23867)) {
                sQueuedPostsList.add(post);
            }
        }
        if (!ListenerUtil.mutListener.listen(23868)) {
            uploadNextPost();
        }
    }

    void registerPostForAnalyticsTracking(int postId) {
        synchronized (sFirstPublishPosts) {
            if (!ListenerUtil.mutListener.listen(23869)) {
                sFirstPublishPosts.add(postId);
            }
        }
    }

    void unregisterPostForAnalyticsTracking(int postId) {
        synchronized (sFirstPublishPosts) {
            if (!ListenerUtil.mutListener.listen(23870)) {
                sFirstPublishPosts.remove(postId);
            }
        }
    }

    static boolean isPostUploadingOrQueued(PostImmutableModel post) {
        return (ListenerUtil.mutListener.listen(23872) ? (post != null || ((ListenerUtil.mutListener.listen(23871) ? (isPostUploading(post) && isPostQueued(post)) : (isPostUploading(post) || isPostQueued(post))))) : (post != null && ((ListenerUtil.mutListener.listen(23871) ? (isPostUploading(post) && isPostQueued(post)) : (isPostUploading(post) || isPostQueued(post))))));
    }

    static boolean isPostQueued(PostImmutableModel post) {
        if (!ListenerUtil.mutListener.listen(23873)) {
            if (post == null) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(23881)) {
            // Check the list of posts waiting to be uploaded
            if ((ListenerUtil.mutListener.listen(23878) ? (sQueuedPostsList.size() >= 0) : (ListenerUtil.mutListener.listen(23877) ? (sQueuedPostsList.size() <= 0) : (ListenerUtil.mutListener.listen(23876) ? (sQueuedPostsList.size() < 0) : (ListenerUtil.mutListener.listen(23875) ? (sQueuedPostsList.size() != 0) : (ListenerUtil.mutListener.listen(23874) ? (sQueuedPostsList.size() == 0) : (sQueuedPostsList.size() > 0))))))) {
                synchronized (sQueuedPostsList) {
                    if (!ListenerUtil.mutListener.listen(23880)) {
                        {
                            long _loopCounter369 = 0;
                            for (PostModel queuedPost : sQueuedPostsList) {
                                ListenerUtil.loopListener.listen("_loopCounter369", ++_loopCounter369);
                                if (!ListenerUtil.mutListener.listen(23879)) {
                                    if (queuedPost.getId() == post.getId()) {
                                        return true;
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

    static boolean isPostUploading(PostImmutableModel post) {
        return (ListenerUtil.mutListener.listen(23883) ? ((ListenerUtil.mutListener.listen(23882) ? (post != null || sCurrentUploadingPost != null) : (post != null && sCurrentUploadingPost != null)) || sCurrentUploadingPost.getId() == post.getId()) : ((ListenerUtil.mutListener.listen(23882) ? (post != null || sCurrentUploadingPost != null) : (post != null && sCurrentUploadingPost != null)) && sCurrentUploadingPost.getId() == post.getId()));
    }

    static boolean hasPendingOrInProgressPostUploads() {
        return (ListenerUtil.mutListener.listen(23884) ? (sCurrentUploadingPost != null && !sQueuedPostsList.isEmpty()) : (sCurrentUploadingPost != null || !sQueuedPostsList.isEmpty()));
    }

    private void uploadNextPost() {
        synchronized (sQueuedPostsList) {
            if (!ListenerUtil.mutListener.listen(23897)) {
                if (mCurrentTask == null) {
                    if (!ListenerUtil.mutListener.listen(23885)) {
                        // make sure nothing is running
                        sCurrentUploadingPost = null;
                    }
                    if (!ListenerUtil.mutListener.listen(23886)) {
                        sCurrentUploadingPostAnalyticsProperties = null;
                    }
                    if (!ListenerUtil.mutListener.listen(23896)) {
                        if ((ListenerUtil.mutListener.listen(23891) ? (sQueuedPostsList.size() >= 0) : (ListenerUtil.mutListener.listen(23890) ? (sQueuedPostsList.size() <= 0) : (ListenerUtil.mutListener.listen(23889) ? (sQueuedPostsList.size() < 0) : (ListenerUtil.mutListener.listen(23888) ? (sQueuedPostsList.size() != 0) : (ListenerUtil.mutListener.listen(23887) ? (sQueuedPostsList.size() == 0) : (sQueuedPostsList.size() > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(23893)) {
                                sCurrentUploadingPost = sQueuedPostsList.remove(0);
                            }
                            if (!ListenerUtil.mutListener.listen(23894)) {
                                mCurrentTask = new UploadPostTask();
                            }
                            if (!ListenerUtil.mutListener.listen(23895)) {
                                mCurrentTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, sCurrentUploadingPost);
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(23892)) {
                                AppLog.i(T.POSTS, "PostUploadHandler > Completed");
                            }
                        }
                    }
                }
            }
        }
    }

    private void finishUpload() {
        synchronized (sQueuedPostsList) {
            if (!ListenerUtil.mutListener.listen(23898)) {
                mCurrentTask = null;
            }
            if (!ListenerUtil.mutListener.listen(23899)) {
                sCurrentUploadingPost = null;
            }
            if (!ListenerUtil.mutListener.listen(23900)) {
                sCurrentUploadingPostAnalyticsProperties = null;
            }
        }
        if (!ListenerUtil.mutListener.listen(23901)) {
            uploadNextPost();
        }
    }

    private enum UploadPostTaskResult {

        PUSH_POST_DISPATCHED, ERROR, NOTHING_TO_UPLOAD, AUTO_SAVE_OR_UPDATE_DRAFT
    }

    @SuppressLint("StaticFieldLeak")
    private class UploadPostTask extends AsyncTask<PostModel, Boolean, UploadPostTaskResult> {

        private Context mContext;

        private PostModel mPost;

        private SiteModel mSite;

        private String mErrorMessage = "";

        private boolean mIsMediaError = false;

        private long mFeaturedImageID = -1;

        // Used for analytics
        private boolean mHasImage, mHasVideo, mHasCategory;

        @Override
        protected void onPostExecute(UploadPostTaskResult result) {
            if (!ListenerUtil.mutListener.listen(23909)) {
                switch(result) {
                    case ERROR:
                        if (!ListenerUtil.mutListener.listen(23902)) {
                            mPostUploadNotifier.incrementUploadedPostCountFromForegroundNotification(mPost);
                        }
                        if (!ListenerUtil.mutListener.listen(23905)) {
                            if (mSite != null) {
                                if (!ListenerUtil.mutListener.listen(23904)) {
                                    mPostUploadNotifier.updateNotificationErrorForPost(mPost, mSite, mErrorMessage, 0);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(23903)) {
                                    AppLog.e(T.POSTS, "Site cannot be null");
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(23906)) {
                            finishUpload();
                        }
                        break;
                    case NOTHING_TO_UPLOAD:
                        if (!ListenerUtil.mutListener.listen(23907)) {
                            // true and we'd end up with a dangling upload notification.
                            mPostUploadNotifier.incrementUploadedPostCountFromForegroundNotification(mPost, true);
                        }
                        if (!ListenerUtil.mutListener.listen(23908)) {
                            finishUpload();
                        }
                        break;
                    case PUSH_POST_DISPATCHED:
                        // will be handled in OnPostChanged
                        break;
                }
            }
        }

        @Override
        protected UploadPostTaskResult doInBackground(PostModel... posts) {
            if (!ListenerUtil.mutListener.listen(23910)) {
                mContext = WordPress.getContext();
            }
            if (!ListenerUtil.mutListener.listen(23911)) {
                mPost = posts[0];
            }
            if (!ListenerUtil.mutListener.listen(23912)) {
                mSite = mSiteStore.getSiteByLocalId(mPost.getLocalSiteId());
            }
            if (!ListenerUtil.mutListener.listen(23914)) {
                if (mSite == null) {
                    if (!ListenerUtil.mutListener.listen(23913)) {
                        mErrorMessage = mContext.getString(R.string.blog_not_found);
                    }
                    return UploadPostTaskResult.ERROR;
                }
            }
            if (!ListenerUtil.mutListener.listen(23916)) {
                if (TextUtils.isEmpty(mPost.getStatus())) {
                    if (!ListenerUtil.mutListener.listen(23915)) {
                        mPost.setStatus(PostStatus.PUBLISHED.toString());
                    }
                }
            }
            String content = mPost.getContent();
            if (!ListenerUtil.mutListener.listen(23924)) {
                // See: https://github.com/wordpress-mobile/WordPress-Android/issues/5009
                if ((ListenerUtil.mutListener.listen(23922) ? ((ListenerUtil.mutListener.listen(23921) ? (content.length() >= 0) : (ListenerUtil.mutListener.listen(23920) ? (content.length() <= 0) : (ListenerUtil.mutListener.listen(23919) ? (content.length() < 0) : (ListenerUtil.mutListener.listen(23918) ? (content.length() != 0) : (ListenerUtil.mutListener.listen(23917) ? (content.length() == 0) : (content.length() > 0)))))) || content.charAt(0) == '\u200B') : ((ListenerUtil.mutListener.listen(23921) ? (content.length() >= 0) : (ListenerUtil.mutListener.listen(23920) ? (content.length() <= 0) : (ListenerUtil.mutListener.listen(23919) ? (content.length() < 0) : (ListenerUtil.mutListener.listen(23918) ? (content.length() != 0) : (ListenerUtil.mutListener.listen(23917) ? (content.length() == 0) : (content.length() > 0)))))) && content.charAt(0) == '\u200B'))) {
                    if (!ListenerUtil.mutListener.listen(23923)) {
                        content = content.substring(1, content.length());
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(23925)) {
                content = processPostMedia(content);
            }
            if (!ListenerUtil.mutListener.listen(23926)) {
                mPost.setContent(content);
            }
            if (!ListenerUtil.mutListener.listen(23927)) {
                // If media file upload failed, let's stop here and prompt the user
                if (mIsMediaError) {
                    return UploadPostTaskResult.ERROR;
                }
            }
            if (!ListenerUtil.mutListener.listen(23934)) {
                if ((ListenerUtil.mutListener.listen(23932) ? (mPost.getCategoryIdList().size() >= 0) : (ListenerUtil.mutListener.listen(23931) ? (mPost.getCategoryIdList().size() <= 0) : (ListenerUtil.mutListener.listen(23930) ? (mPost.getCategoryIdList().size() < 0) : (ListenerUtil.mutListener.listen(23929) ? (mPost.getCategoryIdList().size() != 0) : (ListenerUtil.mutListener.listen(23928) ? (mPost.getCategoryIdList().size() == 0) : (mPost.getCategoryIdList().size() > 0))))))) {
                    if (!ListenerUtil.mutListener.listen(23933)) {
                        mHasCategory = true;
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(23936)) {
                // Track analytics only if the post is newly published
                if (sFirstPublishPosts.contains(mPost.getId())) {
                    if (!ListenerUtil.mutListener.listen(23935)) {
                        prepareUploadAnalytics(mPost.getContent());
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(23937)) {
                EventBus.getDefault().post(new PostUploadStarted(mPost));
            }
            RemotePostPayload payload = new RemotePostPayload(mPost, mSite);
            if (!ListenerUtil.mutListener.listen(23938)) {
                payload.isFirstTimePublish = sFirstPublishPosts.contains(mPost.getId());
            }
            if (!ListenerUtil.mutListener.listen(23947)) {
                switch(mUploadActionUseCase.getUploadAction(mPost)) {
                    case UPLOAD:
                        if (!ListenerUtil.mutListener.listen(23939)) {
                            AppLog.d(T.POSTS, "PostUploadHandler - UPLOAD. Post: " + mPost.getTitle());
                        }
                        if (!ListenerUtil.mutListener.listen(23940)) {
                            mDispatcher.dispatch(PostActionBuilder.newPushPostAction(payload));
                        }
                        break;
                    case UPLOAD_AS_DRAFT:
                        if (!ListenerUtil.mutListener.listen(23941)) {
                            mPost.setStatus(PostStatus.DRAFT.toString());
                        }
                        if (!ListenerUtil.mutListener.listen(23942)) {
                            AppLog.d(T.POSTS, "PostUploadHandler - UPLOAD_AS_DRAFT. Post: " + mPost.getTitle());
                        }
                        if (!ListenerUtil.mutListener.listen(23943)) {
                            mDispatcher.dispatch(PostActionBuilder.newPushPostAction(payload));
                        }
                        break;
                    case REMOTE_AUTO_SAVE:
                        if (!ListenerUtil.mutListener.listen(23944)) {
                            AppLog.d(T.POSTS, "PostUploadHandler - REMOTE_AUTO_SAVE. Post: " + mPost.getTitle());
                        }
                        if (!ListenerUtil.mutListener.listen(23945)) {
                            mAutoSavePostIfNotDraftUseCase.autoSavePostOrUpdateDraft(payload, PostUploadHandler.this);
                        }
                        return UploadPostTaskResult.AUTO_SAVE_OR_UPDATE_DRAFT;
                    case DO_NOTHING:
                        if (!ListenerUtil.mutListener.listen(23946)) {
                            AppLog.d(T.POSTS, "PostUploadHandler - DO_NOTHING. Post: " + mPost.getTitle());
                        }
                        // This branch takes care of this situations and simply ignores the second request.
                        return UploadPostTaskResult.NOTHING_TO_UPLOAD;
                }
            }
            return UploadPostTaskResult.PUSH_POST_DISPATCHED;
        }

        private boolean hasGallery() {
            Pattern galleryTester = Pattern.compile("\\[.*?gallery.*?\\]");
            Matcher matcher = galleryTester.matcher(mPost.getContent());
            return matcher.find();
        }

        private void prepareUploadAnalytics(String postContent) {
            // See https://github.com/wordpress-mobile/WordPress-Android/issues/7990
            synchronized (sQueuedPostsList) {
                if (!ListenerUtil.mutListener.listen(23948)) {
                    // Calculate the words count
                    sCurrentUploadingPostAnalyticsProperties = new HashMap<>();
                }
                if (!ListenerUtil.mutListener.listen(23949)) {
                    sCurrentUploadingPostAnalyticsProperties.put("word_count", AnalyticsUtils.getWordCount(mPost.getContent()));
                }
                // Add the editor source
                int siteLocalId = mPost.getLocalSiteId();
                if (!ListenerUtil.mutListener.listen(23957)) {
                    if ((ListenerUtil.mutListener.listen(23954) ? (siteLocalId >= -1) : (ListenerUtil.mutListener.listen(23953) ? (siteLocalId <= -1) : (ListenerUtil.mutListener.listen(23952) ? (siteLocalId > -1) : (ListenerUtil.mutListener.listen(23951) ? (siteLocalId < -1) : (ListenerUtil.mutListener.listen(23950) ? (siteLocalId == -1) : (siteLocalId != -1))))))) {
                        // Site found, use it
                        SiteModel selectedSite = mSiteStore.getSiteByLocalId(siteLocalId);
                        if (!ListenerUtil.mutListener.listen(23956)) {
                            // If saved site exist, then add info
                            if (selectedSite != null) {
                                if (!ListenerUtil.mutListener.listen(23955)) {
                                    sCurrentUploadingPostAnalyticsProperties.put("editor_source", // like so.
                                    PostUtils.contentContainsWPStoryGutenbergBlocks(mPost.getContent()) ? SiteUtils.WP_STORIES_CREATOR_NAME : (PostUtils.shouldShowGutenbergEditor(mPost.isLocalDraft(), mPost.getContent(), selectedSite) ? SiteUtils.GB_EDITOR_NAME : SiteUtils.AZTEC_EDITOR_NAME));
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(23959)) {
                    if (hasGallery()) {
                        if (!ListenerUtil.mutListener.listen(23958)) {
                            sCurrentUploadingPostAnalyticsProperties.put("with_galleries", true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(23961)) {
                    if (!mHasImage) {
                        // Check if there is a img tag in the post. Media added in any editor other than legacy.
                        String imageTagsPattern = "<img[^>]+src\\s*=\\s*[\"]([^\"]+)[\"][^>]*>";
                        Pattern pattern = Pattern.compile(imageTagsPattern);
                        Matcher matcher = pattern.matcher(postContent);
                        if (!ListenerUtil.mutListener.listen(23960)) {
                            mHasImage = matcher.find();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(23963)) {
                    if (mHasImage) {
                        if (!ListenerUtil.mutListener.listen(23962)) {
                            sCurrentUploadingPostAnalyticsProperties.put("with_photos", true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(23965)) {
                    if (!mHasVideo) {
                        // Check if there is a video tag in the post. Media added in any editor other than legacy.
                        String videoTagsPattern = "<video[^>]+src\\s*=\\s*[\"]([^\"]+)[\"][^>]*>|\\[wpvideo\\s+([^\\]]+)\\]";
                        Pattern pattern = Pattern.compile(videoTagsPattern);
                        Matcher matcher = pattern.matcher(postContent);
                        if (!ListenerUtil.mutListener.listen(23964)) {
                            mHasVideo = matcher.find();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(23967)) {
                    if (mHasVideo) {
                        if (!ListenerUtil.mutListener.listen(23966)) {
                            sCurrentUploadingPostAnalyticsProperties.put("with_videos", true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(23969)) {
                    if (mHasCategory) {
                        if (!ListenerUtil.mutListener.listen(23968)) {
                            sCurrentUploadingPostAnalyticsProperties.put("with_categories", true);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(23971)) {
                    if (!mPost.getTagNameList().isEmpty()) {
                        if (!ListenerUtil.mutListener.listen(23970)) {
                            sCurrentUploadingPostAnalyticsProperties.put("with_tags", true);
                        }
                    }
                }
            }
        }

        /**
         * Finds media in post content, uploads them, and returns the HTML to insert in the post
         */
        private String processPostMedia(String postContent) {
            String imageTagsPattern = "<img[^>]+android-uri\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
            Pattern pattern = Pattern.compile(imageTagsPattern);
            Matcher matcher = pattern.matcher(postContent);
            int totalMediaItems = 0;
            List<String> imageTags = new ArrayList<>();
            if (!ListenerUtil.mutListener.listen(23974)) {
                {
                    long _loopCounter370 = 0;
                    while (matcher.find()) {
                        ListenerUtil.loopListener.listen("_loopCounter370", ++_loopCounter370);
                        if (!ListenerUtil.mutListener.listen(23972)) {
                            imageTags.add(matcher.group());
                        }
                        if (!ListenerUtil.mutListener.listen(23973)) {
                            totalMediaItems++;
                        }
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(23987)) {
                {
                    long _loopCounter371 = 0;
                    for (String tag : imageTags) {
                        ListenerUtil.loopListener.listen("_loopCounter371", ++_loopCounter371);
                        Pattern p = Pattern.compile("android-uri=\"([^\"]+)\"");
                        Matcher m = p.matcher(tag);
                        if (!ListenerUtil.mutListener.listen(23986)) {
                            if (m.find()) {
                                String imageUri = m.group(1);
                                if (!ListenerUtil.mutListener.listen(23985)) {
                                    if (!imageUri.equals("")) {
                                        MediaModel mediaModel = mMediaStore.getMediaForPostWithPath(mPost, imageUri);
                                        if (!ListenerUtil.mutListener.listen(23976)) {
                                            if (mediaModel == null) {
                                                if (!ListenerUtil.mutListener.listen(23975)) {
                                                    mIsMediaError = true;
                                                }
                                                continue;
                                            }
                                        }
                                        MediaFile mediaFile = FluxCUtils.mediaFileFromMediaModel(mediaModel);
                                        if (!ListenerUtil.mutListener.listen(23984)) {
                                            if (mediaFile != null) {
                                                if (!ListenerUtil.mutListener.listen(23977)) {
                                                    mPostUploadNotifier.addMediaInfoToForegroundNotification(mediaModel);
                                                }
                                                String mediaUploadOutput;
                                                if (mediaFile.isVideo()) {
                                                    if (!ListenerUtil.mutListener.listen(23979)) {
                                                        mHasVideo = true;
                                                    }
                                                    mediaUploadOutput = uploadVideo(mediaFile);
                                                } else {
                                                    if (!ListenerUtil.mutListener.listen(23978)) {
                                                        mHasImage = true;
                                                    }
                                                    mediaUploadOutput = uploadImage(mediaFile);
                                                }
                                                if (!ListenerUtil.mutListener.listen(23983)) {
                                                    if (mediaUploadOutput != null) {
                                                        if (!ListenerUtil.mutListener.listen(23982)) {
                                                            postContent = postContent.replace(tag, mediaUploadOutput);
                                                        }
                                                    } else {
                                                        if (!ListenerUtil.mutListener.listen(23980)) {
                                                            postContent = postContent.replace(tag, "");
                                                        }
                                                        if (!ListenerUtil.mutListener.listen(23981)) {
                                                            mIsMediaError = true;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return postContent;
        }

        private String uploadImage(MediaFile mediaFile) {
            if (!ListenerUtil.mutListener.listen(23988)) {
                AppLog.i(T.POSTS, "PostUploadHandler > UploadImage: " + mediaFile.getFilePath());
            }
            if (!ListenerUtil.mutListener.listen(23989)) {
                if (mediaFile.getFilePath() == null) {
                    return null;
                }
            }
            Uri imageUri = Uri.parse(mediaFile.getFilePath());
            File imageFile = null;
            if (!ListenerUtil.mutListener.listen(23997)) {
                if (imageUri.toString().contains("content:")) {
                    String[] projection = new String[] { Images.Media._ID, Images.Media.DATA, Images.Media.MIME_TYPE };
                    Cursor cur = mContext.getContentResolver().query(imageUri, projection, null, null, null);
                    if (!ListenerUtil.mutListener.listen(23995)) {
                        if ((ListenerUtil.mutListener.listen(23992) ? (cur != null || cur.moveToFirst()) : (cur != null && cur.moveToFirst()))) {
                            int dataColumn = cur.getColumnIndex(Images.Media.DATA);
                            String thumbData = cur.getString(dataColumn);
                            if (!ListenerUtil.mutListener.listen(23993)) {
                                imageFile = new File(thumbData);
                            }
                            if (!ListenerUtil.mutListener.listen(23994)) {
                                mediaFile.setFilePath(imageFile.getPath());
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(23996)) {
                        SqlUtils.closeCursor(cur);
                    }
                } else {
                    // file is not in media library
                    String path = imageUri.toString().replace("file://", "");
                    if (!ListenerUtil.mutListener.listen(23990)) {
                        imageFile = new File(path);
                    }
                    if (!ListenerUtil.mutListener.listen(23991)) {
                        mediaFile.setFilePath(path);
                    }
                }
            }
            if (!ListenerUtil.mutListener.listen(23999)) {
                // check if the file exists
                if (imageFile == null) {
                    if (!ListenerUtil.mutListener.listen(23998)) {
                        mErrorMessage = mContext.getString(R.string.file_not_found);
                    }
                    return null;
                }
            }
            String fullSizeUrl = uploadImageFile(mediaFile, mSite);
            if (!ListenerUtil.mutListener.listen(24001)) {
                if (fullSizeUrl == null) {
                    if (!ListenerUtil.mutListener.listen(24000)) {
                        mErrorMessage = mContext.getString(R.string.error_media_upload);
                    }
                    return null;
                }
            }
            return mediaFile.getImageHtmlForUrls(fullSizeUrl, null, false);
        }

        @SuppressLint("InlinedApi")
        private String uploadVideo(MediaFile mediaFile) {
            // create temp file for media upload
            String tempFileName = "wp-" + System.currentTimeMillis();
            try {
                if (!ListenerUtil.mutListener.listen(24003)) {
                    mContext.openFileOutput(tempFileName, Context.MODE_PRIVATE);
                }
            } catch (FileNotFoundException e) {
                if (!ListenerUtil.mutListener.listen(24002)) {
                    mErrorMessage = mContext.getResources().getString(R.string.file_error_create);
                }
                return null;
            }
            if (mediaFile.getFilePath() == null) {
                if (!ListenerUtil.mutListener.listen(24004)) {
                    mErrorMessage = mContext.getString(R.string.error_media_upload);
                }
                return null;
            }
            Uri videoUri = Uri.parse(mediaFile.getFilePath());
            File videoFile = null;
            String mimeType = "", xRes = "", yRes = "";
            if (!ListenerUtil.mutListener.listen(24025)) {
                if (videoUri.toString().contains("content:")) {
                    String[] projection = new String[] { Video.Media._ID, Video.Media.DATA, Video.Media.MIME_TYPE, Video.Media.RESOLUTION };
                    Cursor cur = mContext.getContentResolver().query(videoUri, projection, null, null, null);
                    if (!ListenerUtil.mutListener.listen(24023)) {
                        if ((ListenerUtil.mutListener.listen(24007) ? (cur != null || cur.moveToFirst()) : (cur != null && cur.moveToFirst()))) {
                            int dataColumn = cur.getColumnIndex(Video.Media.DATA);
                            int mimeTypeColumn = cur.getColumnIndex(Video.Media.MIME_TYPE);
                            int resolutionColumn = cur.getColumnIndex(Video.Media.RESOLUTION);
                            if (!ListenerUtil.mutListener.listen(24008)) {
                                mediaFile = new MediaFile();
                            }
                            String thumbData = cur.getString(dataColumn);
                            if (!ListenerUtil.mutListener.listen(24009)) {
                                mimeType = cur.getString(mimeTypeColumn);
                            }
                            if (!ListenerUtil.mutListener.listen(24010)) {
                                videoFile = new File(thumbData);
                            }
                            if (!ListenerUtil.mutListener.listen(24011)) {
                                mediaFile.setFilePath(videoFile.getPath());
                            }
                            String resolution = cur.getString(resolutionColumn);
                            if (!ListenerUtil.mutListener.listen(24022)) {
                                if (resolution != null) {
                                    String[] resolutions = resolution.split("x");
                                    if (!ListenerUtil.mutListener.listen(24021)) {
                                        if ((ListenerUtil.mutListener.listen(24018) ? (resolutions.length <= 2) : (ListenerUtil.mutListener.listen(24017) ? (resolutions.length > 2) : (ListenerUtil.mutListener.listen(24016) ? (resolutions.length < 2) : (ListenerUtil.mutListener.listen(24015) ? (resolutions.length != 2) : (ListenerUtil.mutListener.listen(24014) ? (resolutions.length == 2) : (resolutions.length >= 2))))))) {
                                            if (!ListenerUtil.mutListener.listen(24019)) {
                                                xRes = resolutions[0];
                                            }
                                            if (!ListenerUtil.mutListener.listen(24020)) {
                                                yRes = resolutions[1];
                                            }
                                        }
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(24012)) {
                                        // Default resolution
                                        xRes = "640";
                                    }
                                    if (!ListenerUtil.mutListener.listen(24013)) {
                                        yRes = "480";
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(24024)) {
                        SqlUtils.closeCursor(cur);
                    }
                } else {
                    // file is not in media library
                    String filePath = videoUri.toString().replace("file://", "");
                    if (!ListenerUtil.mutListener.listen(24005)) {
                        mediaFile.setFilePath(filePath);
                    }
                    if (!ListenerUtil.mutListener.listen(24006)) {
                        videoFile = new File(filePath);
                    }
                }
            }
            if (videoFile == null) {
                if (!ListenerUtil.mutListener.listen(24026)) {
                    mErrorMessage = mContext.getResources().getString(R.string.error_media_upload);
                }
                return null;
            }
            if (!ListenerUtil.mutListener.listen(24028)) {
                if (TextUtils.isEmpty(mimeType)) {
                    if (!ListenerUtil.mutListener.listen(24027)) {
                        mimeType = MediaUtils.getMediaFileMimeType(videoFile);
                    }
                }
            }
            CountDownLatch countDownLatch = new CountDownLatch(1);
            UploadMediaPayload payload = new UploadMediaPayload(mSite, FluxCUtils.mediaModelFromMediaFile(mediaFile), AppPrefs.isStripImageLocation());
            if (!ListenerUtil.mutListener.listen(24029)) {
                mDispatcher.dispatch(MediaActionBuilder.newUploadMediaAction(payload));
            }
            try {
                if (!ListenerUtil.mutListener.listen(24032)) {
                    mMediaLatchMap.put(mediaFile.getId(), countDownLatch);
                }
                if (!ListenerUtil.mutListener.listen(24033)) {
                    countDownLatch.await();
                }
            } catch (InterruptedException e) {
                if (!ListenerUtil.mutListener.listen(24030)) {
                    AppLog.e(T.POSTS, "PostUploadHandler > CountDownLatch await interrupted for media file: " + mediaFile.getId() + " - " + e);
                }
                if (!ListenerUtil.mutListener.listen(24031)) {
                    mIsMediaError = true;
                }
            }
            MediaModel finishedMedia = mMediaStore.getMediaWithLocalId(mediaFile.getId());
            if ((ListenerUtil.mutListener.listen(24035) ? ((ListenerUtil.mutListener.listen(24034) ? (finishedMedia == null && finishedMedia.getUploadState() == null) : (finishedMedia == null || finishedMedia.getUploadState() == null)) && !finishedMedia.getUploadState().equals(MediaUploadState.UPLOADED.toString())) : ((ListenerUtil.mutListener.listen(24034) ? (finishedMedia == null && finishedMedia.getUploadState() == null) : (finishedMedia == null || finishedMedia.getUploadState() == null)) || !finishedMedia.getUploadState().equals(MediaUploadState.UPLOADED.toString())))) {
                if (!ListenerUtil.mutListener.listen(24036)) {
                    mIsMediaError = true;
                }
                return null;
            }
            if (!TextUtils.isEmpty(finishedMedia.getVideoPressGuid())) {
                return "[wpvideo " + finishedMedia.getVideoPressGuid() + "]\n";
            } else {
                return String.format("<video width=\"%s\" height=\"%s\" controls=\"controls\"><source src=\"%s\" type=\"%s\" />" + "<a href=\"%s\">Click to view video</a>.</video>", xRes, yRes, finishedMedia.getUrl(), mimeType, finishedMedia.getUrl());
            }
        }

        private String uploadImageFile(MediaFile mediaFile, SiteModel site) {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            UploadMediaPayload payload = new UploadMediaPayload(site, FluxCUtils.mediaModelFromMediaFile(mediaFile), AppPrefs.isStripImageLocation());
            if (!ListenerUtil.mutListener.listen(24037)) {
                mDispatcher.dispatch(MediaActionBuilder.newUploadMediaAction(payload));
            }
            try {
                if (!ListenerUtil.mutListener.listen(24040)) {
                    mMediaLatchMap.put(mediaFile.getId(), countDownLatch);
                }
                if (!ListenerUtil.mutListener.listen(24041)) {
                    countDownLatch.await();
                }
            } catch (InterruptedException e) {
                if (!ListenerUtil.mutListener.listen(24038)) {
                    AppLog.e(T.POSTS, "PostUploadHandler > CountDownLatch await interrupted for media file: " + mediaFile.getId() + " - " + e);
                }
                if (!ListenerUtil.mutListener.listen(24039)) {
                    mIsMediaError = true;
                }
            }
            MediaModel finishedMedia = mMediaStore.getMediaWithLocalId(mediaFile.getId());
            if (!ListenerUtil.mutListener.listen(24045)) {
                if ((ListenerUtil.mutListener.listen(24043) ? ((ListenerUtil.mutListener.listen(24042) ? (finishedMedia == null && finishedMedia.getUploadState() == null) : (finishedMedia == null || finishedMedia.getUploadState() == null)) && !finishedMedia.getUploadState().equals(MediaUploadState.UPLOADED.toString())) : ((ListenerUtil.mutListener.listen(24042) ? (finishedMedia == null && finishedMedia.getUploadState() == null) : (finishedMedia == null || finishedMedia.getUploadState() == null)) || !finishedMedia.getUploadState().equals(MediaUploadState.UPLOADED.toString())))) {
                    if (!ListenerUtil.mutListener.listen(24044)) {
                        mIsMediaError = true;
                    }
                    return null;
                }
            }
            String pictureURL = finishedMedia.getUrl();
            if (!ListenerUtil.mutListener.listen(24048)) {
                if (mediaFile.isFeatured()) {
                    if (!ListenerUtil.mutListener.listen(24046)) {
                        mFeaturedImageID = finishedMedia.getMediaId();
                    }
                    if (!ListenerUtil.mutListener.listen(24047)) {
                        if (!mediaFile.isFeaturedInPost()) {
                            return "";
                        }
                    }
                }
            }
            return pictureURL;
        }
    }

    @Override
    public void handleAutoSavePostIfNotDraftResult(@NotNull AutoSavePostIfNotDraftResult result) {
        PostModel post = result.getPost();
        if (!ListenerUtil.mutListener.listen(24055)) {
            if ((ListenerUtil.mutListener.listen(24050) ? ((ListenerUtil.mutListener.listen(24049) ? (result instanceof FetchPostStatusFailed && result instanceof PostAutoSaveFailed) : (result instanceof FetchPostStatusFailed || result instanceof PostAutoSaveFailed)) && result instanceof PostAutoSaved) : ((ListenerUtil.mutListener.listen(24049) ? (result instanceof FetchPostStatusFailed && result instanceof PostAutoSaveFailed) : (result instanceof FetchPostStatusFailed || result instanceof PostAutoSaveFailed)) || result instanceof PostAutoSaved))) {
                if (!ListenerUtil.mutListener.listen(24053)) {
                    /*
             * If we fail to check the status of the post or auto-save fails, we deliberately don't show an error
             * notification since it's not a user initiated action. We'll retry the action later on.
             */
                    mPostUploadNotifier.incrementUploadedPostCountFromForegroundNotification(post);
                }
                if (!ListenerUtil.mutListener.listen(24054)) {
                    finishUpload();
                }
            } else if (result instanceof PostIsDraftInRemote) {
                if (!ListenerUtil.mutListener.listen(24051)) {
                    /*
             * If the post is a draft in remote, we'll update it directly instead of auto-saving it. Please see
             * documentation of `AutoSavePostIfNotDraftUseCase` for details.
             *
             * We opted not to restore the current status after the post is uploaded to avoid its complexity and to
             * replicate `UPLOAD_AS_DRAFT`. We may change this in the future.
             */
                    post.setStatus(PostStatus.DRAFT.toString());
                }
                SiteModel site = mSiteStore.getSiteByLocalId(post.getLocalSiteId());
                if (!ListenerUtil.mutListener.listen(24052)) {
                    mDispatcher.dispatch(PostActionBuilder.newPushPostAction(new RemotePostPayload(post, site)));
                }
            } else {
                throw new IllegalStateException("All AutoSavePostIfNotDraftResult types must be handled");
            }
        }
    }

    /**
     * Has priority 9 on OnPostUploaded events, which ensures that PostUploadHandler is the first to receive
     * and process OnPostUploaded events, before they trickle down to other subscribers.
     */
    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN, priority = 9)
    public void onPostUploaded(OnPostUploaded event) {
        if (!ListenerUtil.mutListener.listen(24056)) {
            // check if the event is related to the PostModel that is being uploaded by PostUploadHandler
            if (!isPostUploading(event.post)) {
                return;
            }
        }
        SiteModel site = mSiteStore.getSiteByLocalId(event.post.getLocalSiteId());
        if (!ListenerUtil.mutListener.listen(24080)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(24075)) {
                    AppLog.w(T.POSTS, "PostUploadHandler > Post upload failed. " + event.error.type + ": " + event.error.message);
                }
                Context context = WordPress.getContext();
                String errorMessage = mUiHelpers.getTextOfUiString(context, UploadUtils.getErrorMessageResIdFromPostError(PostStatus.fromPost(event.post), event.post.isPage(), event.error, mUploadActionUseCase.isEligibleForAutoUpload(site, event.post))).toString();
                String notificationMessage = UploadUtils.getErrorMessage(context, event.post.isPage(), errorMessage, false);
                if (!ListenerUtil.mutListener.listen(24076)) {
                    mPostUploadNotifier.removePostInfoFromForegroundNotification(event.post, mMediaStore.getMediaForPost(event.post));
                }
                if (!ListenerUtil.mutListener.listen(24077)) {
                    mPostUploadNotifier.incrementUploadedPostCountFromForegroundNotification(event.post);
                }
                if (!ListenerUtil.mutListener.listen(24078)) {
                    mPostUploadNotifier.updateNotificationErrorForPost(event.post, site, notificationMessage, 0);
                }
                if (!ListenerUtil.mutListener.listen(24079)) {
                    sFirstPublishPosts.remove(event.post.getId());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(24057)) {
                    mPostUploadNotifier.incrementUploadedPostCountFromForegroundNotification(event.post);
                }
                boolean isFirstTimePublish = sFirstPublishPosts.remove(event.post.getId());
                if (!ListenerUtil.mutListener.listen(24061)) {
                    if (site != null) {
                        if (!ListenerUtil.mutListener.listen(24059)) {
                            mPostUploadNotifier.updateNotificationSuccessForPost(event.post, site, isFirstTimePublish);
                        }
                        if (!ListenerUtil.mutListener.listen(24060)) {
                            mPostMediaHandler.updateMediaWithoutPostId(site, event.post);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(24058)) {
                            AppLog.e(T.POSTS, "Cannot update notification success without a site");
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(24070)) {
                    if (isFirstTimePublish) {
                        if (!ListenerUtil.mutListener.listen(24064)) {
                            if (sCurrentUploadingPostAnalyticsProperties != null) {
                                if (!ListenerUtil.mutListener.listen(24063)) {
                                    sCurrentUploadingPostAnalyticsProperties.put("post_id", event.post.getRemotePostId());
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(24062)) {
                                    sCurrentUploadingPostAnalyticsProperties = new HashMap<>();
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(24065)) {
                            PostUtils.addPostTypeAndPostFormatToAnalyticsProperties(event.post, sCurrentUploadingPostAnalyticsProperties);
                        }
                        if (!ListenerUtil.mutListener.listen(24066)) {
                            sCurrentUploadingPostAnalyticsProperties.put(AnalyticsUtils.HAS_GUTENBERG_BLOCKS_KEY, PostUtils.contentContainsGutenbergBlocks(event.post.getContent()));
                        }
                        if (!ListenerUtil.mutListener.listen(24067)) {
                            sCurrentUploadingPostAnalyticsProperties.put(AnalyticsUtils.HAS_WP_STORIES_BLOCKS_KEY, PostUtils.contentContainsWPStoryGutenbergBlocks(event.post.getContent()));
                        }
                        if (!ListenerUtil.mutListener.listen(24068)) {
                            sCurrentUploadingPostAnalyticsProperties.put(AnalyticsUtils.PROMPT_ID, event.post.getAnsweredPromptId());
                        }
                        if (!ListenerUtil.mutListener.listen(24069)) {
                            AnalyticsUtils.trackWithSiteDetails(Stat.EDITOR_PUBLISHED_POST, mSiteStore.getSiteByLocalId(event.post.getLocalSiteId()), sCurrentUploadingPostAnalyticsProperties);
                        }
                    }
                }
                synchronized (sQueuedPostsList) {
                    if (!ListenerUtil.mutListener.listen(24074)) {
                        {
                            long _loopCounter372 = 0;
                            for (PostModel post : sQueuedPostsList) {
                                ListenerUtil.loopListener.listen("_loopCounter372", ++_loopCounter372);
                                if (!ListenerUtil.mutListener.listen(24073)) {
                                    if (post.getId() == event.post.getId()) {
                                        if (!ListenerUtil.mutListener.listen(24071)) {
                                            // Check if a new version of the post we've just uploaded is in the queue and update its state
                                            post.setRemotePostId(event.post.getRemotePostId());
                                        }
                                        if (!ListenerUtil.mutListener.listen(24072)) {
                                            post.setIsLocalDraft(false);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(24081)) {
            finishUpload();
        }
    }
}
