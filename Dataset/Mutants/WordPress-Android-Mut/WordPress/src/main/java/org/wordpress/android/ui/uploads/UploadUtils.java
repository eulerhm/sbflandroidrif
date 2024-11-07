package org.wordpress.android.ui.uploads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import com.google.android.material.snackbar.Snackbar;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.generated.PostActionBuilder;
import org.wordpress.android.fluxc.model.MediaModel;
import org.wordpress.android.fluxc.model.MediaUploadModel;
import org.wordpress.android.fluxc.model.PostImmutableModel;
import org.wordpress.android.fluxc.model.PostModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.model.post.PostStatus;
import org.wordpress.android.fluxc.persistence.UploadSqlUtils;
import org.wordpress.android.fluxc.store.MediaStore.MediaError;
import org.wordpress.android.fluxc.store.MediaStore.MediaErrorType;
import org.wordpress.android.fluxc.store.PostStore.PostError;
import org.wordpress.android.fluxc.store.UploadStore.UploadError;
import org.wordpress.android.fluxc.utils.MimeTypes;
import org.wordpress.android.ui.ActivityLauncher;
import org.wordpress.android.ui.posts.EditPostActivity;
import org.wordpress.android.ui.posts.PostUtils;
import org.wordpress.android.ui.prefs.AppPrefs;
import org.wordpress.android.ui.uploads.UploadActionUseCase.UploadAction;
import org.wordpress.android.ui.utils.UiString;
import org.wordpress.android.ui.utils.UiString.UiStringRes;
import org.wordpress.android.ui.utils.UiString.UiStringText;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.DateTimeUtils;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.SiteUtils;
import org.wordpress.android.util.SnackbarItem;
import org.wordpress.android.util.SnackbarItem.Action;
import org.wordpress.android.util.SnackbarItem.Info;
import org.wordpress.android.util.SnackbarSequencer;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.UploadWorkerKt;
import org.wordpress.android.util.WPMediaUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class UploadUtils {

    private static final int K_SNACKBAR_WAIT_TIME_MS = 5000;

    private static final MimeTypes MIME_TYPES = new MimeTypes();

    /**
     * Returns a post-type specific error message string.
     */
    @NonNull
    static String getErrorMessage(Context context, boolean isPage, String errorMessage, boolean isMediaError) {
        String baseErrorString;
        if (isPage) {
            if (isMediaError) {
                baseErrorString = context.getString(R.string.error_upload_page_media_param);
            } else {
                baseErrorString = context.getString(R.string.error_upload_page_param);
            }
        } else {
            if (isMediaError) {
                baseErrorString = context.getString(R.string.error_upload_post_media_param);
            } else {
                baseErrorString = context.getString(R.string.error_upload_post_param);
            }
        }
        return String.format(baseErrorString, errorMessage);
    }

    /**
     * Returns an error message string for a failed post upload.
     */
    @NonNull
    public static UiString getErrorMessageResIdFromPostError(PostStatus postStatus, boolean isPage, PostError error, boolean eligibleForAutoUpload) {
        switch(error.type) {
            case UNKNOWN_POST:
                return isPage ? new UiStringRes(R.string.error_unknown_page) : new UiStringRes(R.string.error_unknown_post);
            case UNKNOWN_POST_TYPE:
                return isPage ? new UiStringRes(R.string.error_unknown_page_type) : new UiStringRes(R.string.error_unknown_post_type);
            case UNAUTHORIZED:
                return isPage ? new UiStringRes(R.string.error_refresh_unauthorized_pages) : new UiStringRes(R.string.error_refresh_unauthorized_posts);
            case UNSUPPORTED_ACTION:
            case INVALID_RESPONSE:
            case GENERIC_ERROR:
            default:
                if (!ListenerUtil.mutListener.listen(25015)) {
                    AppLog.w(T.MAIN, "Error message: " + error.message + " ,Error Type: " + error.type);
                }
                if (eligibleForAutoUpload) {
                    switch(postStatus) {
                        case PRIVATE:
                            return isPage ? new UiStringRes(R.string.error_page_not_published_retrying_private) : new UiStringRes(R.string.error_post_not_published_retrying_private);
                        case PUBLISHED:
                            return isPage ? new UiStringRes(R.string.error_page_not_published_retrying) : new UiStringRes(R.string.error_post_not_published_retrying);
                        case SCHEDULED:
                            return isPage ? new UiStringRes(R.string.error_page_not_scheduled_retrying) : new UiStringRes(R.string.error_post_not_scheduled_retrying);
                        case PENDING:
                            return isPage ? new UiStringRes(R.string.error_page_not_submitted_retrying) : new UiStringRes(R.string.error_post_not_submitted_retrying);
                        case UNKNOWN:
                        case DRAFT:
                        case TRASHED:
                            return new UiStringRes(R.string.error_generic_error_retrying);
                    }
                } else {
                    switch(postStatus) {
                        case PRIVATE:
                            return isPage ? new UiStringRes(R.string.error_page_not_published_private) : new UiStringRes(R.string.error_post_not_published_private);
                        case PUBLISHED:
                            return isPage ? new UiStringRes(R.string.error_page_not_published) : new UiStringRes(R.string.error_post_not_published);
                        case SCHEDULED:
                            return isPage ? new UiStringRes(R.string.error_page_not_scheduled) : new UiStringRes(R.string.error_post_not_scheduled);
                        case PENDING:
                            return isPage ? new UiStringRes(R.string.error_page_not_submitted) : new UiStringRes(R.string.error_post_not_submitted);
                        case UNKNOWN:
                        case DRAFT:
                        case TRASHED:
                            return new UiStringRes(R.string.error_generic_error);
                    }
                }
                return new UiStringRes(R.string.error_generic_error);
        }
    }

    /**
     * Returns an error message string for a failed media upload.
     */
    @NonNull
    public static String getErrorMessageFromMediaError(Context context, MediaModel media, MediaError error) {
        String errorMessage = WPMediaUtils.getErrorMessage(context, media, error);
        if (!ListenerUtil.mutListener.listen(25017)) {
            if (errorMessage == null) {
                // In case of a generic or uncaught error, return the message from the API response or the error type
                String msg = error.getApiUserMessageIfAvailable();
                if (!ListenerUtil.mutListener.listen(25016)) {
                    errorMessage = TextUtils.isEmpty(msg) ? error.type.toString() : msg;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25029)) {
            // (like a few got from FluxC) are not capitalized.
            if ((ListenerUtil.mutListener.listen(25022) ? (errorMessage.length() >= 0) : (ListenerUtil.mutListener.listen(25021) ? (errorMessage.length() <= 0) : (ListenerUtil.mutListener.listen(25020) ? (errorMessage.length() < 0) : (ListenerUtil.mutListener.listen(25019) ? (errorMessage.length() != 0) : (ListenerUtil.mutListener.listen(25018) ? (errorMessage.length() == 0) : (errorMessage.length() > 0))))))) {
                String firstLetter = errorMessage.substring(0, 1);
                String restOfMessage = (ListenerUtil.mutListener.listen(25027) ? (errorMessage.length() >= 1) : (ListenerUtil.mutListener.listen(25026) ? (errorMessage.length() <= 1) : (ListenerUtil.mutListener.listen(25025) ? (errorMessage.length() < 1) : (ListenerUtil.mutListener.listen(25024) ? (errorMessage.length() != 1) : (ListenerUtil.mutListener.listen(25023) ? (errorMessage.length() == 1) : (errorMessage.length() > 1)))))) ? errorMessage.substring(1) : "";
                if (!ListenerUtil.mutListener.listen(25028)) {
                    errorMessage = firstLetter.toUpperCase(Locale.getDefault()) + restOfMessage;
                }
            }
        }
        return errorMessage;
    }

    @NonNull
    public static String getErrorMessageFromMedia(Context context, @NonNull MediaModel media) {
        MediaUploadModel uploadModel = UploadSqlUtils.getMediaUploadModelForLocalId(media.getId());
        MediaError error = new MediaError(MediaErrorType.GENERIC_ERROR, null, null);
        if (!ListenerUtil.mutListener.listen(25032)) {
            if (uploadModel != null) {
                MediaError errorFromUploadModel = uploadModel.getMediaError();
                if (!ListenerUtil.mutListener.listen(25031)) {
                    if (errorFromUploadModel != null) {
                        if (!ListenerUtil.mutListener.listen(25030)) {
                            error = errorFromUploadModel;
                        }
                    }
                }
            }
        }
        return getErrorMessageFromMediaError(context, media, error);
    }

    public static boolean isMediaError(UploadError uploadError) {
        return (ListenerUtil.mutListener.listen(25033) ? (uploadError != null || uploadError.mediaError != null) : (uploadError != null && uploadError.mediaError != null));
    }

    public static void handleEditPostModelResultSnackbars(@NonNull final Activity activity, @NonNull final Dispatcher dispatcher, @NonNull View snackbarAttachView, @NonNull Intent data, @NonNull final PostModel post, @NonNull final SiteModel site, @NonNull final UploadAction uploadAction, SnackbarSequencer sequencer, View.OnClickListener publishPostListener, @Nullable OnPublishingCallback onPublishingCallback) {
        boolean hasChanges = data.getBooleanExtra(EditPostActivity.EXTRA_HAS_CHANGES, false);
        if (!ListenerUtil.mutListener.listen(25034)) {
            if (!hasChanges) {
                // if there are no changes, we don't need to do anything
                return;
            }
        }
        boolean uploadNotStarted = data.getBooleanExtra(EditPostActivity.EXTRA_UPLOAD_NOT_STARTED, false);
        if (!ListenerUtil.mutListener.listen(25038)) {
            if ((ListenerUtil.mutListener.listen(25035) ? (uploadNotStarted || !NetworkUtils.isNetworkAvailable(activity)) : (uploadNotStarted && !NetworkUtils.isNetworkAvailable(activity)))) {
                if (!ListenerUtil.mutListener.listen(25036)) {
                    // The network is not available, we can enqueue a request to upload local changes later
                    UploadWorkerKt.enqueueUploadWorkRequestForSite(site);
                }
                if (!ListenerUtil.mutListener.listen(25037)) {
                    // And tell the user about it
                    showSnackbar(snackbarAttachView, getDeviceOfflinePostModelNotUploadedMessage(post, uploadAction), R.string.cancel, v -> {
                        int msgRes = cancelPendingAutoUpload(post, dispatcher);
                        showSnackbar(snackbarAttachView, msgRes, sequencer);
                    }, sequencer);
                }
                return;
            }
        }
        boolean hasFailedMedia = data.getBooleanExtra(EditPostActivity.EXTRA_HAS_FAILED_MEDIA, false);
        if (!ListenerUtil.mutListener.listen(25041)) {
            if (hasFailedMedia) {
                if (!ListenerUtil.mutListener.listen(25040)) {
                    showSnackbar(snackbarAttachView, post.isPage() ? R.string.editor_page_saved_locally_failed_media : R.string.editor_post_saved_locally_failed_media, R.string.button_edit, new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (!ListenerUtil.mutListener.listen(25039)) {
                                ActivityLauncher.editPostOrPageForResult(activity, site, post);
                            }
                        }
                    }, sequencer);
                }
                return;
            }
        }
        PostStatus postStatus = PostStatus.fromPost(post);
        boolean isScheduledPost = postStatus == PostStatus.SCHEDULED;
        if (!ListenerUtil.mutListener.listen(25044)) {
            if (isScheduledPost) {
                if (!ListenerUtil.mutListener.listen(25043)) {
                    // if it's a scheduled post, we only want to show a "Sync" button if it's locally saved
                    if (uploadNotStarted) {
                        if (!ListenerUtil.mutListener.listen(25042)) {
                            showSnackbar(snackbarAttachView, post.isPage() ? R.string.editor_page_saved_locally : R.string.editor_post_saved_locally, R.string.button_sync, publishPostListener, sequencer);
                        }
                    }
                }
                return;
            }
        }
        boolean isPublished = postStatus == PostStatus.PUBLISHED;
        if (!ListenerUtil.mutListener.listen(25050)) {
            if (isPublished) {
                if (!ListenerUtil.mutListener.listen(25049)) {
                    // if it's a published post, we only want to show a "Sync" button if it's locally saved
                    if (uploadNotStarted) {
                        if (!ListenerUtil.mutListener.listen(25048)) {
                            showSnackbar(snackbarAttachView, post.isPage() ? R.string.editor_page_saved_locally : R.string.editor_post_saved_locally, R.string.button_sync, publishPostListener, sequencer);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(25045)) {
                            showSnackbar(snackbarAttachView, post.isPage() ? R.string.editor_uploading_page : R.string.editor_uploading_post, sequencer);
                        }
                        if (!ListenerUtil.mutListener.listen(25047)) {
                            if (onPublishingCallback != null) {
                                if (!ListenerUtil.mutListener.listen(25046)) {
                                    onPublishingCallback.onPublishing(PostUtils.isFirstTimePublish(post));
                                }
                            }
                        }
                    }
                }
                return;
            }
        }
        boolean isDraft = postStatus == PostStatus.DRAFT;
        if (!ListenerUtil.mutListener.listen(25065)) {
            if (isDraft) {
                if (!ListenerUtil.mutListener.listen(25064)) {
                    if (PostUtils.isPublishable(post)) {
                        if (!ListenerUtil.mutListener.listen(25063)) {
                            // if the post is publishable, we offer the PUBLISH button
                            if (uploadNotStarted) {
                                if (!ListenerUtil.mutListener.listen(25062)) {
                                    showSnackbarSuccessAction(snackbarAttachView, R.string.editor_draft_saved_locally, R.string.button_publish, publishPostListener, sequencer);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(25061)) {
                                    if ((ListenerUtil.mutListener.listen(25058) ? (UploadService.hasPendingOrInProgressMediaUploadsForPost(post) && UploadService.isPostUploadingOrQueued(post)) : (UploadService.hasPendingOrInProgressMediaUploadsForPost(post) || UploadService.isPostUploadingOrQueued(post)))) {
                                        if (!ListenerUtil.mutListener.listen(25060)) {
                                            showSnackbar(snackbarAttachView, R.string.editor_uploading_draft, sequencer);
                                        }
                                    } else {
                                        if (!ListenerUtil.mutListener.listen(25059)) {
                                            showSnackbarSuccessAction(snackbarAttachView, R.string.editor_draft_saved_online, R.string.button_publish, publishPostListener, sequencer);
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(25057)) {
                            showSnackbar(snackbarAttachView, R.string.editor_draft_saved_locally, sequencer);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(25056)) {
                    if (uploadNotStarted) {
                        if (!ListenerUtil.mutListener.listen(25055)) {
                            showSnackbar(snackbarAttachView, post.isPage() ? R.string.editor_page_saved_locally : R.string.editor_post_saved_locally, R.string.button_publish, publishPostListener, sequencer);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(25054)) {
                            if ((ListenerUtil.mutListener.listen(25051) ? (UploadService.hasPendingOrInProgressMediaUploadsForPost(post) && UploadService.isPostUploadingOrQueued(post)) : (UploadService.hasPendingOrInProgressMediaUploadsForPost(post) || UploadService.isPostUploadingOrQueued(post)))) {
                                if (!ListenerUtil.mutListener.listen(25053)) {
                                    showSnackbar(snackbarAttachView, post.isPage() ? R.string.editor_uploading_page : R.string.editor_uploading_post, sequencer);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(25052)) {
                                    showSnackbarSuccessAction(snackbarAttachView, post.isPage() ? R.string.editor_page_saved_online : R.string.editor_post_saved_online, R.string.button_publish, publishPostListener, sequencer);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void showSnackbarError(View view, String message, int buttonTitleRes, OnClickListener onClickListener, SnackbarSequencer sequencer) {
        if (!ListenerUtil.mutListener.listen(25066)) {
            sequencer.enqueue(new SnackbarItem(new Info(view, new UiStringText(message), K_SNACKBAR_WAIT_TIME_MS, true), new Action(new UiStringRes(buttonTitleRes), onClickListener), null, null));
        }
    }

    public static void showSnackbarError(View view, String message, SnackbarSequencer sequencer) {
        if (!ListenerUtil.mutListener.listen(25067)) {
            sequencer.enqueue(new SnackbarItem(new Info(view, new UiStringText(message), K_SNACKBAR_WAIT_TIME_MS, true), null, null, null));
        }
    }

    private static void showSnackbar(View view, int messageRes, int buttonTitleRes, OnClickListener onClickListener, SnackbarSequencer sequencer) {
        if (!ListenerUtil.mutListener.listen(25068)) {
            sequencer.enqueue(new SnackbarItem(new Info(view, new UiStringRes(messageRes), K_SNACKBAR_WAIT_TIME_MS, true), new Action(new UiStringRes(buttonTitleRes), onClickListener), null, null));
        }
    }

    public static void showSnackbarSuccessAction(View view, int messageRes, int buttonTitleRes, OnClickListener onClickListener, SnackbarSequencer sequencer) {
        if (!ListenerUtil.mutListener.listen(25069)) {
            sequencer.enqueue(new SnackbarItem(new Info(view, new UiStringRes(messageRes), K_SNACKBAR_WAIT_TIME_MS, true), new Action(new UiStringRes(buttonTitleRes), onClickListener), null, null));
        }
    }

    private static void showSnackbarSuccessAction(View view, String message, int buttonTitleRes, OnClickListener onClickListener, SnackbarSequencer sequencer) {
        if (!ListenerUtil.mutListener.listen(25070)) {
            sequencer.enqueue(new SnackbarItem(new Info(view, new UiStringText(message), K_SNACKBAR_WAIT_TIME_MS, true), new Action(new UiStringRes(buttonTitleRes), onClickListener), null, null));
        }
    }

    public static void showSnackbar(View view, int messageRes, SnackbarSequencer sequencer) {
        if (!ListenerUtil.mutListener.listen(25071)) {
            sequencer.enqueue(new SnackbarItem(new Info(view, new UiStringRes(messageRes), Snackbar.LENGTH_LONG, true), null, null, null));
        }
    }

    public static void showSnackbar(View view, String messageText, SnackbarSequencer sequencer) {
        if (!ListenerUtil.mutListener.listen(25072)) {
            sequencer.enqueue(new SnackbarItem(new Info(view, new UiStringText(messageText), Snackbar.LENGTH_LONG, true), null, null, null));
        }
    }

    public static void publishPost(Activity activity, final PostModel post, SiteModel site, Dispatcher dispatcher) {
        if (!ListenerUtil.mutListener.listen(25073)) {
            publishPost(activity, post, site, dispatcher, null);
        }
    }

    public static void publishPost(Activity activity, final PostModel post, SiteModel site, Dispatcher dispatcher, @Nullable OnPublishingCallback onPublishingCallback) {
        if (!ListenerUtil.mutListener.listen(25075)) {
            // If the post is empty, don't publish
            if (!PostUtils.isPublishable(post)) {
                String message = activity.getString(post.isPage() ? R.string.error_publish_empty_page : R.string.error_publish_empty_post);
                if (!ListenerUtil.mutListener.listen(25074)) {
                    ToastUtils.showToast(activity, message, ToastUtils.Duration.SHORT);
                }
                return;
            }
        }
        boolean isFirstTimePublish = PostUtils.isFirstTimePublish(post);
        if (!ListenerUtil.mutListener.listen(25076)) {
            PostUtils.preparePostForPublish(post, site);
        }
        if (!ListenerUtil.mutListener.listen(25077)) {
            // save the post in the DB so the UploadService will get the latest change
            dispatcher.dispatch(PostActionBuilder.newUpdatePostAction(post));
        }
        if (!ListenerUtil.mutListener.listen(25081)) {
            if (NetworkUtils.isNetworkAvailable(activity)) {
                if (!ListenerUtil.mutListener.listen(25078)) {
                    UploadService.uploadPost(activity, post.getId(), isFirstTimePublish);
                }
                if (!ListenerUtil.mutListener.listen(25080)) {
                    if (onPublishingCallback != null) {
                        if (!ListenerUtil.mutListener.listen(25079)) {
                            onPublishingCallback.onPublishing(isFirstTimePublish);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(25082)) {
            PostUtils.trackSavePostAnalytics(post, site);
        }
    }

    /*
     * returns true if the user has permission to publish the post - assumed to be true for
     * dot.org sites because we can't retrieve their capabilities
     */
    public static boolean userCanPublish(SiteModel site) {
        return (ListenerUtil.mutListener.listen(25083) ? (!SiteUtils.isAccessedViaWPComRest(site) && site.getHasCapabilityPublishPosts()) : (!SiteUtils.isAccessedViaWPComRest(site) || site.getHasCapabilityPublishPosts()));
    }

    public static void onPostUploadedSnackbarHandler(final Activity activity, View snackbarAttachView, boolean isError, boolean isFirstTimePublish, final PostModel post, final String errorMessage, final SiteModel site, final Dispatcher dispatcher, SnackbarSequencer sequencer, @Nullable OnPublishingCallback onPublishingCallback) {
        boolean userCanPublish = userCanPublish(site);
        if (!ListenerUtil.mutListener.listen(25107)) {
            if (isError) {
                if (!ListenerUtil.mutListener.listen(25106)) {
                    if (errorMessage != null) {
                        if (!ListenerUtil.mutListener.listen(25105)) {
                            // RETRY only available for Aztec
                            if (AppPrefs.isAztecEditorEnabled()) {
                                if (!ListenerUtil.mutListener.listen(25104)) {
                                    UploadUtils.showSnackbarError(snackbarAttachView, errorMessage, R.string.retry, new View.OnClickListener() {

                                        @Override
                                        public void onClick(View view) {
                                            Intent intent = UploadService.getRetryUploadServiceIntent(activity, post, false);
                                            if (!ListenerUtil.mutListener.listen(25103)) {
                                                activity.startService(intent);
                                            }
                                        }
                                    }, sequencer);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(25102)) {
                                    UploadUtils.showSnackbarError(snackbarAttachView, errorMessage, sequencer);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(25101)) {
                            UploadUtils.showSnackbar(snackbarAttachView, R.string.editor_draft_saved_locally, sequencer);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(25100)) {
                    if (post != null) {
                        PostStatus status = PostStatus.fromPost(post);
                        int snackbarMessageRes;
                        int snackbarButtonRes = 0;
                        View.OnClickListener publishPostListener = new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {
                                if (!ListenerUtil.mutListener.listen(25084)) {
                                    // jump to Editor Preview mode to show this Post
                                    ActivityLauncher.browsePostOrPage(activity, site, post);
                                }
                            }
                        };
                        switch(status) {
                            case DRAFT:
                                snackbarMessageRes = R.string.editor_draft_saved_online;
                                if (!ListenerUtil.mutListener.listen(25088)) {
                                    if (userCanPublish) {
                                        if (!ListenerUtil.mutListener.listen(25086)) {
                                            publishPostListener = new View.OnClickListener() {

                                                @Override
                                                public void onClick(View v) {
                                                    if (!ListenerUtil.mutListener.listen(25085)) {
                                                        UploadUtils.publishPost(activity, post, site, dispatcher, onPublishingCallback);
                                                    }
                                                }
                                            };
                                        }
                                        if (!ListenerUtil.mutListener.listen(25087)) {
                                            snackbarButtonRes = R.string.button_publish;
                                        }
                                    }
                                }
                                break;
                            case PUBLISHED:
                                if (!ListenerUtil.mutListener.listen(25089)) {
                                    snackbarButtonRes = R.string.button_view;
                                }
                                if (post.isPage()) {
                                    snackbarMessageRes = isFirstTimePublish ? R.string.page_published : R.string.page_updated;
                                } else if (userCanPublish) {
                                    snackbarMessageRes = isFirstTimePublish ? R.string.post_published : R.string.post_updated;
                                } else {
                                    snackbarMessageRes = R.string.post_submitted;
                                }
                                break;
                            case SCHEDULED:
                                if (!ListenerUtil.mutListener.listen(25090)) {
                                    snackbarButtonRes = R.string.button_view;
                                }
                                snackbarMessageRes = post.isPage() ? R.string.page_scheduled : R.string.post_scheduled;
                                break;
                            default:
                                if (!ListenerUtil.mutListener.listen(25091)) {
                                    snackbarButtonRes = R.string.button_view;
                                }
                                snackbarMessageRes = post.isPage() ? R.string.page_updated : R.string.post_updated;
                                break;
                        }
                        if (!ListenerUtil.mutListener.listen(25099)) {
                            if ((ListenerUtil.mutListener.listen(25096) ? (snackbarButtonRes >= 0) : (ListenerUtil.mutListener.listen(25095) ? (snackbarButtonRes <= 0) : (ListenerUtil.mutListener.listen(25094) ? (snackbarButtonRes < 0) : (ListenerUtil.mutListener.listen(25093) ? (snackbarButtonRes != 0) : (ListenerUtil.mutListener.listen(25092) ? (snackbarButtonRes == 0) : (snackbarButtonRes > 0))))))) {
                                if (!ListenerUtil.mutListener.listen(25098)) {
                                    UploadUtils.showSnackbarSuccessAction(snackbarAttachView, snackbarMessageRes, snackbarButtonRes, publishPostListener, sequencer);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(25097)) {
                                    UploadUtils.showSnackbar(snackbarAttachView, snackbarMessageRes, sequencer);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void onMediaUploadedSnackbarHandler(final Activity activity, View snackbarAttachView, boolean isError, final List<MediaModel> mediaList, final SiteModel site, final String messageForUser, SnackbarSequencer sequencer) {
        if (!ListenerUtil.mutListener.listen(25131)) {
            if (isError) {
                if (!ListenerUtil.mutListener.listen(25130)) {
                    if (messageForUser != null) {
                        if (!ListenerUtil.mutListener.listen(25129)) {
                            // RETRY only available for Aztec
                            if ((ListenerUtil.mutListener.listen(25124) ? (mediaList != null || !mediaList.isEmpty()) : (mediaList != null && !mediaList.isEmpty()))) {
                                if (!ListenerUtil.mutListener.listen(25128)) {
                                    UploadUtils.showSnackbarError(snackbarAttachView, messageForUser, R.string.retry, new View.OnClickListener() {

                                        @Override
                                        public void onClick(View view) {
                                            ArrayList<MediaModel> mediaListToRetry = new ArrayList<>();
                                            if (!ListenerUtil.mutListener.listen(25126)) {
                                                mediaListToRetry.addAll(mediaList);
                                            }
                                            Intent retryIntent = UploadService.getUploadMediaServiceIntent(activity, mediaListToRetry, true);
                                            if (!ListenerUtil.mutListener.listen(25127)) {
                                                activity.startService(retryIntent);
                                            }
                                        }
                                    }, sequencer);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(25125)) {
                                    UploadUtils.showSnackbarError(snackbarAttachView, messageForUser, sequencer);
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(25123)) {
                            UploadUtils.showSnackbarError(snackbarAttachView, activity.getString(R.string.error_media_upload), sequencer);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(25109)) {
                    if ((ListenerUtil.mutListener.listen(25108) ? (mediaList == null && mediaList.isEmpty()) : (mediaList == null || mediaList.isEmpty()))) {
                        return;
                    }
                }
                boolean showPostAction = false;
                if (!ListenerUtil.mutListener.listen(25112)) {
                    {
                        long _loopCounter392 = 0;
                        for (MediaModel mediaModel : mediaList) {
                            ListenerUtil.loopListener.listen("_loopCounter392", ++_loopCounter392);
                            if (!ListenerUtil.mutListener.listen(25111)) {
                                showPostAction |= (ListenerUtil.mutListener.listen(25110) ? (MIME_TYPES.isImageType(mediaModel.getMimeType()) && MIME_TYPES.isVideoType(mediaModel.getMimeType())) : (MIME_TYPES.isImageType(mediaModel.getMimeType()) || MIME_TYPES.isVideoType(mediaModel.getMimeType())));
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(25122)) {
                    if (showPostAction) {
                        if (!ListenerUtil.mutListener.listen(25121)) {
                            // show success snackbar for media only items and offer the WRITE POST functionality)
                            UploadUtils.showSnackbarSuccessAction(snackbarAttachView, messageForUser, R.string.media_files_uploaded_write_post, new View.OnClickListener() {

                                @Override
                                public void onClick(View view) {
                                    // WRITE POST functionality: show pre-populated Post
                                    ArrayList<MediaModel> mediaListToInsertInPost = new ArrayList<>();
                                    if (!ListenerUtil.mutListener.listen(25114)) {
                                        mediaListToInsertInPost.addAll(mediaList);
                                    }
                                    Intent writePostIntent = new Intent(activity, EditPostActivity.class);
                                    if (!ListenerUtil.mutListener.listen(25115)) {
                                        writePostIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                    }
                                    if (!ListenerUtil.mutListener.listen(25116)) {
                                        writePostIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    }
                                    if (!ListenerUtil.mutListener.listen(25117)) {
                                        writePostIntent.putExtra(WordPress.SITE, site);
                                    }
                                    if (!ListenerUtil.mutListener.listen(25118)) {
                                        writePostIntent.putExtra(EditPostActivity.EXTRA_IS_PAGE, false);
                                    }
                                    if (!ListenerUtil.mutListener.listen(25119)) {
                                        writePostIntent.putExtra(EditPostActivity.EXTRA_INSERT_MEDIA, mediaListToInsertInPost);
                                    }
                                    if (!ListenerUtil.mutListener.listen(25120)) {
                                        activity.startActivity(writePostIntent);
                                    }
                                }
                            }, sequencer);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(25113)) {
                            // Do not show action for audio/document files until there is a block handling them in GB
                            UploadUtils.showSnackbar(snackbarAttachView, messageForUser, sequencer);
                        }
                    }
                }
            }
        }
    }

    @StringRes
    private static int getDeviceOfflinePostModelNotUploadedMessage(@NonNull final PostModel post, @NonNull final UploadAction uploadAction) {
        if (uploadAction != UploadAction.UPLOAD) {
            return post.isPage() ? R.string.error_publish_page_no_network : R.string.error_publish_no_network;
        } else {
            switch(PostStatus.fromPost(post)) {
                case PUBLISHED:
                case UNKNOWN:
                    return post.isPage() ? R.string.page_waiting_for_connection_publish : R.string.post_waiting_for_connection_publish;
                case DRAFT:
                    return post.isPage() ? R.string.page_waiting_for_connection_draft : R.string.post_waiting_for_connection_draft;
                case PRIVATE:
                    return post.isPage() ? R.string.page_waiting_for_connection_private : R.string.post_waiting_for_connection_private;
                case PENDING:
                    return post.isPage() ? R.string.page_waiting_for_connection_pending : R.string.post_waiting_for_connection_pending;
                case SCHEDULED:
                    return post.isPage() ? R.string.page_waiting_for_connection_scheduled : R.string.post_waiting_for_connection_scheduled;
                case TRASHED:
                    throw new IllegalArgumentException("Trashing posts should be handled in a different code path.");
            }
        }
        throw new RuntimeException("This code should be unreachable. Missing case in switch statement.");
    }

    public static boolean postLocalChangesAlreadyRemoteAutoSaved(PostImmutableModel post) {
        return (ListenerUtil.mutListener.listen(25132) ? (!TextUtils.isEmpty(post.getAutoSaveModified()) || DateTimeUtils.dateFromIso8601(post.getDateLocallyChanged()).before(DateTimeUtils.dateFromIso8601(post.getAutoSaveModified()))) : (!TextUtils.isEmpty(post.getAutoSaveModified()) && DateTimeUtils.dateFromIso8601(post.getDateLocallyChanged()).before(DateTimeUtils.dateFromIso8601(post.getAutoSaveModified()))));
    }

    public static int cancelPendingAutoUpload(PostModel post, Dispatcher dispatcher) {
        if (!ListenerUtil.mutListener.listen(25133)) {
            /*
         * `changesConfirmedContentHashcode` field holds a hashcode of the post content at the time when user pressed
         * updated/publish/sync/submit/.. buttons. Clearing the hashcode will prevent the PostUploadHandler to
         * auto-upload the changes - it'll only remote-auto-save them -> which is exactly what the cancel action is
         * supposed to do.
         */
            post.setChangesConfirmedContentHashcode(0);
        }
        if (!ListenerUtil.mutListener.listen(25134)) {
            dispatcher.dispatch(PostActionBuilder.newUpdatePostAction(post));
        }
        int messageRes = 0;
        if (!ListenerUtil.mutListener.listen(25140)) {
            switch(PostStatus.fromPost(post)) {
                case UNKNOWN:
                case PUBLISHED:
                case PRIVATE:
                    if (!ListenerUtil.mutListener.listen(25135)) {
                        messageRes = R.string.post_waiting_for_connection_publish_cancel;
                    }
                    break;
                case PENDING:
                    if (!ListenerUtil.mutListener.listen(25136)) {
                        messageRes = R.string.post_waiting_for_connection_pending_cancel;
                    }
                    break;
                case SCHEDULED:
                    if (!ListenerUtil.mutListener.listen(25137)) {
                        messageRes = R.string.post_waiting_for_connection_scheduled_cancel;
                    }
                    break;
                case DRAFT:
                    if (!ListenerUtil.mutListener.listen(25138)) {
                        messageRes = R.string.post_waiting_for_connection_draft_cancel;
                    }
                    break;
                case TRASHED:
                    if (!ListenerUtil.mutListener.listen(25139)) {
                        AppLog.e(T.POSTS, "This code should be unreachable. Canceling pending auto-upload on Trashed and Draft posts " + "isn't supported.");
                    }
                    break;
            }
        }
        return messageRes;
    }

    public interface OnPublishingCallback {

        void onPublishing(boolean isFirstTimePublish);
    }
}
