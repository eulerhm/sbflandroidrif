package org.wordpress.android.ui.posts;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback;
import androidx.core.util.Consumer;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.automattic.android.tracks.crashlogging.CrashLogging;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;
import org.wordpress.android.BuildConfig;
import org.wordpress.android.R;
import org.wordpress.android.WordPress;
import org.wordpress.android.analytics.AnalyticsTracker;
import org.wordpress.android.analytics.AnalyticsTracker.Stat;
import org.wordpress.android.editor.AztecEditorFragment;
import org.wordpress.android.editor.EditorEditMediaListener;
import org.wordpress.android.editor.EditorFragmentAbstract;
import org.wordpress.android.editor.EditorFragmentAbstract.EditorDragAndDropListener;
import org.wordpress.android.editor.EditorFragmentAbstract.EditorFragmentListener;
import org.wordpress.android.editor.EditorFragmentAbstract.EditorFragmentNotAddedException;
import org.wordpress.android.editor.EditorFragmentAbstract.TrackableEvent;
import org.wordpress.android.editor.EditorFragmentActivity;
import org.wordpress.android.editor.EditorImageMetaData;
import org.wordpress.android.editor.EditorImagePreviewListener;
import org.wordpress.android.editor.EditorImageSettingsListener;
import org.wordpress.android.editor.EditorMediaUploadListener;
import org.wordpress.android.editor.EditorMediaUtils;
import org.wordpress.android.editor.EditorThemeUpdateListener;
import org.wordpress.android.editor.ExceptionLogger;
import org.wordpress.android.editor.ImageSettingsDialogFragment;
import org.wordpress.android.editor.gutenberg.DialogVisibility;
import org.wordpress.android.editor.gutenberg.GutenbergEditorFragment;
import org.wordpress.android.editor.gutenberg.GutenbergPropsBuilder;
import org.wordpress.android.editor.gutenberg.GutenbergWebViewAuthorizationData;
import org.wordpress.android.editor.gutenberg.StorySaveMediaListener;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.action.AccountAction;
import org.wordpress.android.fluxc.generated.AccountActionBuilder;
import org.wordpress.android.fluxc.generated.EditorThemeActionBuilder;
import org.wordpress.android.fluxc.generated.MediaActionBuilder;
import org.wordpress.android.fluxc.generated.PostActionBuilder;
import org.wordpress.android.fluxc.generated.SiteActionBuilder;
import org.wordpress.android.fluxc.model.AccountModel;
import org.wordpress.android.fluxc.model.CauseOfOnPostChanged;
import org.wordpress.android.fluxc.model.CauseOfOnPostChanged.RemoteAutoSavePost;
import org.wordpress.android.fluxc.model.EditorTheme;
import org.wordpress.android.fluxc.model.EditorThemeSupport;
import org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId;
import org.wordpress.android.fluxc.model.MediaModel;
import org.wordpress.android.fluxc.model.MediaModel.MediaUploadState;
import org.wordpress.android.fluxc.model.PostImmutableModel;
import org.wordpress.android.fluxc.model.PostModel;
import org.wordpress.android.fluxc.model.SiteModel;
import org.wordpress.android.fluxc.model.post.PostStatus;
import org.wordpress.android.fluxc.network.rest.wpcom.site.PrivateAtomicCookie;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.AccountStore.OnAccountChanged;
import org.wordpress.android.fluxc.store.EditorThemeStore;
import org.wordpress.android.fluxc.store.EditorThemeStore.FetchEditorThemePayload;
import org.wordpress.android.fluxc.store.EditorThemeStore.OnEditorThemeChanged;
import org.wordpress.android.fluxc.store.MediaStore;
import org.wordpress.android.fluxc.store.MediaStore.FetchMediaListPayload;
import org.wordpress.android.fluxc.store.MediaStore.MediaError;
import org.wordpress.android.fluxc.store.MediaStore.MediaErrorType;
import org.wordpress.android.fluxc.store.MediaStore.OnMediaChanged;
import org.wordpress.android.fluxc.store.MediaStore.OnMediaListFetched;
import org.wordpress.android.fluxc.store.MediaStore.OnMediaUploaded;
import org.wordpress.android.fluxc.store.PostStore;
import org.wordpress.android.fluxc.store.PostStore.OnPostChanged;
import org.wordpress.android.fluxc.store.PostStore.OnPostUploaded;
import org.wordpress.android.fluxc.store.PostStore.RemotePostPayload;
import org.wordpress.android.fluxc.store.QuickStartStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.store.SiteStore.FetchPrivateAtomicCookiePayload;
import org.wordpress.android.fluxc.store.SiteStore.OnPrivateAtomicCookieFetched;
import org.wordpress.android.fluxc.store.UploadStore;
import org.wordpress.android.fluxc.store.bloggingprompts.BloggingPromptsStore;
import org.wordpress.android.fluxc.tools.FluxCImageLoader;
import org.wordpress.android.imageeditor.preview.PreviewImageFragment.Companion.EditImageData;
import org.wordpress.android.support.ZendeskHelper;
import org.wordpress.android.ui.ActivityId;
import org.wordpress.android.ui.ActivityLauncher;
import org.wordpress.android.ui.LocaleAwareActivity;
import org.wordpress.android.ui.PrivateAtCookieRefreshProgressDialog;
import org.wordpress.android.ui.PrivateAtCookieRefreshProgressDialog.PrivateAtCookieProgressDialogOnDismissListener;
import org.wordpress.android.ui.RequestCodes;
import org.wordpress.android.ui.Shortcut;
import org.wordpress.android.ui.history.HistoryListItem.Revision;
import org.wordpress.android.ui.media.MediaBrowserActivity;
import org.wordpress.android.ui.media.MediaBrowserType;
import org.wordpress.android.ui.media.MediaPreviewActivity;
import org.wordpress.android.ui.media.MediaSettingsActivity;
import org.wordpress.android.ui.pages.SnackbarMessageHolder;
import org.wordpress.android.ui.photopicker.MediaPickerConstants;
import org.wordpress.android.ui.photopicker.MediaPickerLauncher;
import org.wordpress.android.ui.photopicker.PhotoPickerFragment;
import org.wordpress.android.ui.photopicker.PhotoPickerFragment.PhotoPickerIcon;
import org.wordpress.android.ui.posts.EditPostRepository.UpdatePostResult;
import org.wordpress.android.ui.posts.EditPostRepository.UpdatePostResult.Updated;
import org.wordpress.android.ui.posts.EditPostSettingsFragment.EditPostSettingsCallback;
import org.wordpress.android.ui.posts.FeaturedImageHelper.EnqueueFeaturedImageResult;
import org.wordpress.android.ui.posts.InsertMediaDialog.InsertMediaCallback;
import org.wordpress.android.ui.posts.PostEditorAnalyticsSession.Editor;
import org.wordpress.android.ui.posts.PostEditorAnalyticsSession.Outcome;
import org.wordpress.android.ui.posts.PostUtils.EntryPoint;
import org.wordpress.android.ui.posts.RemotePreviewLogicHelper.PreviewLogicOperationResult;
import org.wordpress.android.ui.posts.RemotePreviewLogicHelper.RemotePreviewType;
import org.wordpress.android.ui.posts.editor.EditorActionsProvider;
import org.wordpress.android.ui.posts.editor.EditorPhotoPicker;
import org.wordpress.android.ui.posts.editor.EditorPhotoPickerListener;
import org.wordpress.android.ui.posts.editor.EditorTracker;
import org.wordpress.android.ui.posts.editor.ImageEditorTracker;
import org.wordpress.android.ui.posts.editor.PostLoadingState;
import org.wordpress.android.ui.posts.editor.PrimaryEditorAction;
import org.wordpress.android.ui.posts.editor.SecondaryEditorAction;
import org.wordpress.android.ui.posts.editor.StorePostViewModel;
import org.wordpress.android.ui.posts.editor.StorePostViewModel.ActivityFinishState;
import org.wordpress.android.ui.posts.editor.StorePostViewModel.UpdateFromEditor;
import org.wordpress.android.ui.posts.editor.StorePostViewModel.UpdateFromEditor.PostFields;
import org.wordpress.android.ui.posts.editor.StoriesEventListener;
import org.wordpress.android.ui.posts.editor.XPostsCapabilityChecker;
import org.wordpress.android.ui.posts.editor.media.AddExistingMediaSource;
import org.wordpress.android.ui.posts.editor.media.EditorMedia;
import org.wordpress.android.ui.posts.editor.media.EditorMediaListener;
import org.wordpress.android.ui.posts.prepublishing.PrepublishingBottomSheetListener;
import org.wordpress.android.ui.posts.prepublishing.home.usecases.PublishPostImmediatelyUseCase;
import org.wordpress.android.ui.posts.reactnative.ReactNativeRequestHandler;
import org.wordpress.android.ui.posts.services.AztecImageLoader;
import org.wordpress.android.ui.posts.services.AztecVideoLoader;
import org.wordpress.android.ui.prefs.AppPrefs;
import org.wordpress.android.ui.prefs.SiteSettingsInterface;
import org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper;
import org.wordpress.android.ui.stories.StoryRepositoryWrapper;
import org.wordpress.android.ui.stories.prefs.StoriesPrefs;
import org.wordpress.android.ui.stories.usecase.LoadStoryFromStoriesPrefsUseCase;
import org.wordpress.android.ui.suggestion.SuggestionActivity;
import org.wordpress.android.ui.suggestion.SuggestionType;
import org.wordpress.android.ui.uploads.PostEvents;
import org.wordpress.android.ui.uploads.ProgressEvent;
import org.wordpress.android.ui.uploads.UploadService;
import org.wordpress.android.ui.uploads.UploadUtils;
import org.wordpress.android.ui.uploads.UploadUtilsWrapper;
import org.wordpress.android.ui.utils.AuthenticationUtils;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.ActivityUtils;
import org.wordpress.android.util.AniUtils;
import org.wordpress.android.util.AppLog;
import org.wordpress.android.util.AppLog.T;
import org.wordpress.android.util.AutolinkUtils;
import org.wordpress.android.util.DateTimeUtilsWrapper;
import org.wordpress.android.util.DisplayUtils;
import org.wordpress.android.util.FluxCUtils;
import org.wordpress.android.util.ListUtils;
import org.wordpress.android.util.LocaleManager;
import org.wordpress.android.util.LocaleManagerWrapper;
import org.wordpress.android.util.MediaUtils;
import org.wordpress.android.util.NetworkUtils;
import org.wordpress.android.util.PermissionUtils;
import org.wordpress.android.util.ReblogUtils;
import org.wordpress.android.util.ShortcutUtils;
import org.wordpress.android.util.SiteUtils;
import org.wordpress.android.util.StorageUtilsProvider.Source;
import org.wordpress.android.util.StringUtils;
import org.wordpress.android.util.ToastUtils;
import org.wordpress.android.util.ToastUtils.Duration;
import org.wordpress.android.util.UrlUtils;
import org.wordpress.android.util.WPMediaUtils;
import org.wordpress.android.util.WPPermissionUtils;
import org.wordpress.android.util.WPUrlUtils;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;
import org.wordpress.android.util.analytics.AnalyticsUtils;
import org.wordpress.android.util.analytics.AnalyticsUtils.BlockEditorEnabledSource;
import org.wordpress.android.util.config.GlobalStyleSupportFeatureConfig;
import org.wordpress.android.util.extensions.AppBarLayoutExtensionsKt;
import org.wordpress.android.util.helpers.MediaFile;
import org.wordpress.android.util.helpers.MediaGallery;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.viewmodel.helpers.ToastMessageHolder;
import org.wordpress.android.viewmodel.storage.StorageUtilsViewModel;
import org.wordpress.android.widgets.AppRatingDialog;
import org.wordpress.android.widgets.WPSnackbar;
import org.wordpress.android.widgets.WPViewPager;
import org.wordpress.aztec.exceptions.DynamicLayoutGetBlockIndexOutOfBoundsException;
import org.wordpress.aztec.util.AztecLog;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;
import static org.wordpress.android.analytics.AnalyticsTracker.Stat.APP_REVIEWS_EVENT_INCREMENTED_BY_PUBLISHING_POST_OR_PAGE;
import static org.wordpress.android.editor.gutenberg.GutenbergEditorFragment.MEDIA_ID_NO_FEATURED_IMAGE_SET;
import static org.wordpress.android.imageeditor.preview.PreviewImageFragment.PREVIEW_IMAGE_REDUCED_SIZE_FACTOR;
import static org.wordpress.android.ui.history.HistoryDetailContainerFragment.KEY_REVISION;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class EditPostActivity extends LocaleAwareActivity implements EditorFragmentActivity, EditorImageSettingsListener, EditorImagePreviewListener, EditorEditMediaListener, EditorDragAndDropListener, EditorFragmentListener, OnRequestPermissionsResultCallback, PhotoPickerFragment.PhotoPickerListener, EditorPhotoPickerListener, EditorMediaListener, EditPostSettingsFragment.EditPostActivityHook, PostSettingsListDialogFragment.OnPostSettingsDialogFragmentListener, HistoryListFragment.HistoryItemClickInterface, EditPostSettingsCallback, PrepublishingBottomSheetListener, PrivateAtCookieProgressDialogOnDismissListener, ExceptionLogger, SiteSettingsInterface.SiteSettingsListener {

    public static final String ACTION_REBLOG = "reblogAction";

    public static final String EXTRA_POST_LOCAL_ID = "postModelLocalId";

    public static final String EXTRA_LOAD_AUTO_SAVE_REVISION = "loadAutosaveRevision";

    public static final String EXTRA_POST_REMOTE_ID = "postModelRemoteId";

    public static final String EXTRA_IS_PAGE = "isPage";

    public static final String EXTRA_IS_PROMO = "isPromo";

    public static final String EXTRA_IS_QUICKPRESS = "isQuickPress";

    public static final String EXTRA_IS_LANDING_EDITOR = "isLandingEditor";

    public static final String EXTRA_IS_LANDING_EDITOR_OPENED_FOR_NEW_SITE = "isLandingEditorOpenedForNewSite";

    public static final String EXTRA_QUICKPRESS_BLOG_ID = "quickPressBlogId";

    public static final String EXTRA_UPLOAD_NOT_STARTED = "savedAsLocalDraft";

    public static final String EXTRA_HAS_FAILED_MEDIA = "hasFailedMedia";

    public static final String EXTRA_HAS_CHANGES = "hasChanges";

    public static final String EXTRA_RESTART_EDITOR = "isSwitchingEditors";

    public static final String EXTRA_INSERT_MEDIA = "insertMedia";

    public static final String EXTRA_IS_NEW_POST = "isNewPost";

    public static final String EXTRA_REBLOG_POST_TITLE = "reblogPostTitle";

    public static final String EXTRA_REBLOG_POST_IMAGE = "reblogPostImage";

    public static final String EXTRA_REBLOG_POST_QUOTE = "reblogPostQuote";

    public static final String EXTRA_REBLOG_POST_CITATION = "reblogPostCitation";

    public static final String EXTRA_PAGE_TITLE = "pageTitle";

    public static final String EXTRA_PAGE_CONTENT = "pageContent";

    public static final String EXTRA_PAGE_TEMPLATE = "pageTemplate";

    public static final String EXTRA_PROMPT_ID = "extraPromptId";

    public static final String EXTRA_ENTRY_POINT = "extraEntryPoint";

    private static final String STATE_KEY_EDITOR_FRAGMENT = "editorFragment";

    private static final String STATE_KEY_DROPPED_MEDIA_URIS = "stateKeyDroppedMediaUri";

    private static final String STATE_KEY_POST_LOCAL_ID = "stateKeyPostModelLocalId";

    private static final String STATE_KEY_POST_REMOTE_ID = "stateKeyPostModelRemoteId";

    private static final String STATE_KEY_POST_LOADING_STATE = "stateKeyPostLoadingState";

    private static final String STATE_KEY_IS_NEW_POST = "stateKeyIsNewPost";

    private static final String STATE_KEY_IS_PHOTO_PICKER_VISIBLE = "stateKeyPhotoPickerVisible";

    private static final String STATE_KEY_HTML_MODE_ON = "stateKeyHtmlModeOn";

    private static final String STATE_KEY_REVISION = "stateKeyRevision";

    private static final String STATE_KEY_EDITOR_SESSION_DATA = "stateKeyEditorSessionData";

    private static final String STATE_KEY_GUTENBERG_IS_SHOWN = "stateKeyGutenbergIsShown";

    private static final String STATE_KEY_MEDIA_CAPTURE_PATH = "stateKeyMediaCapturePath";

    private static final int PAGE_CONTENT = 0;

    private static final int PAGE_SETTINGS = 1;

    private static final int PAGE_PUBLISH_SETTINGS = 2;

    private static final int PAGE_HISTORY = 3;

    private AztecImageLoader mAztecImageLoader;

    enum RestartEditorOptions {

        NO_RESTART, RESTART_SUPPRESS_GUTENBERG, RESTART_DONT_SUPPRESS_GUTENBERG
    }

    private RestartEditorOptions mRestartEditorOption = RestartEditorOptions.NO_RESTART;

    private boolean mShowAztecEditor;

    private boolean mShowGutenbergEditor;

    private List<String> mPendingVideoPressInfoRequests;

    private PostEditorAnalyticsSession mPostEditorAnalyticsSession;

    private boolean mIsConfigChange = false;

    /**
     * The {@link PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    WPViewPager mViewPager;

    private Revision mRevision;

    private EditorFragmentAbstract mEditorFragment;

    private EditPostSettingsFragment mEditPostSettingsFragment;

    private EditorMediaUploadListener mEditorMediaUploadListener;

    private EditorPhotoPicker mEditorPhotoPicker;

    private ProgressDialog mProgressDialog;

    private ProgressDialog mAddingMediaToEditorProgressDialog;

    private boolean mIsNewPost;

    private boolean mIsPage;

    private boolean mIsLandingEditor;

    private boolean mHasSetPostContent;

    private PostLoadingState mPostLoadingState = PostLoadingState.NONE;

    @Nullable
    private Boolean mIsXPostsCapable = null;

    @Nullable
    Consumer<String> mOnGetSuggestionResult;

    // For opening the context menu after permissions have been granted
    private View mMenuView = null;

    private AppBarLayout mAppBarLayout;

    private Toolbar mToolbar;

    private Handler mShowPrepublishingBottomSheetHandler;

    private Runnable mShowPrepublishingBottomSheetRunnable;

    private boolean mHtmlModeMenuStateOn = false;

    @Inject
    Dispatcher mDispatcher;

    @Inject
    AccountStore mAccountStore;

    @Inject
    SiteStore mSiteStore;

    @Inject
    PostStore mPostStore;

    @Inject
    MediaStore mMediaStore;

    @Inject
    UploadStore mUploadStore;

    @Inject
    EditorThemeStore mEditorThemeStore;

    @Inject
    FluxCImageLoader mImageLoader;

    @Inject
    ShortcutUtils mShortcutUtils;

    @Inject
    QuickStartStore mQuickStartStore;

    @Inject
    ImageManager mImageManager;

    @Inject
    UiHelpers mUiHelpers;

    @Inject
    RemotePreviewLogicHelper mRemotePreviewLogicHelper;

    @Inject
    ProgressDialogHelper mProgressDialogHelper;

    @Inject
    FeaturedImageHelper mFeaturedImageHelper;

    @Inject
    ReactNativeRequestHandler mReactNativeRequestHandler;

    @Inject
    EditorMedia mEditorMedia;

    @Inject
    LocaleManagerWrapper mLocaleManagerWrapper;

    @Inject
    EditPostRepository mEditPostRepository;

    @Inject
    PostUtilsWrapper mPostUtils;

    @Inject
    EditorTracker mEditorTracker;

    @Inject
    UploadUtilsWrapper mUploadUtilsWrapper;

    @Inject
    EditorActionsProvider mEditorActionsProvider;

    @Inject
    DateTimeUtilsWrapper mDateTimeUtils;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    ReaderUtilsWrapper mReaderUtilsWrapper;

    @Inject
    protected PrivateAtomicCookie mPrivateAtomicCookie;

    @Inject
    ImageEditorTracker mImageEditorTracker;

    @Inject
    ReblogUtils mReblogUtils;

    @Inject
    AnalyticsTrackerWrapper mAnalyticsTrackerWrapper;

    @Inject
    PublishPostImmediatelyUseCase mPublishPostImmediatelyUseCase;

    @Inject
    XPostsCapabilityChecker mXpostsCapabilityChecker;

    @Inject
    CrashLogging mCrashLogging;

    @Inject
    MediaPickerLauncher mMediaPickerLauncher;

    @Inject
    StoryRepositoryWrapper mStoryRepositoryWrapper;

    @Inject
    LoadStoryFromStoriesPrefsUseCase mLoadStoryFromStoriesPrefsUseCase;

    @Inject
    StoriesPrefs mStoriesPrefs;

    @Inject
    StoriesEventListener mStoriesEventListener;

    @Inject
    UpdateFeaturedImageUseCase mUpdateFeaturedImageUseCase;

    @Inject
    GlobalStyleSupportFeatureConfig mGlobalStyleSupportFeatureConfig;

    @Inject
    ZendeskHelper mZendeskHelper;

    @Inject
    BloggingPromptsStore mBloggingPromptsStore;

    private StorePostViewModel mViewModel;

    private StorageUtilsViewModel mStorageUtilsViewModel;

    private EditorBloggingPromptsViewModel mEditorBloggingPromptsViewModel;

    private SiteModel mSite;

    private SiteSettingsInterface mSiteSettings;

    private boolean mIsJetpackSsoEnabled;

    private boolean mStoryEditingCancelled = false;

    private boolean mNetworkErrorOnLastMediaFetchAttempt = false;

    public static boolean checkToRestart(@NonNull Intent data) {
        return (ListenerUtil.mutListener.listen(11299) ? (data.hasExtra(EditPostActivity.EXTRA_RESTART_EDITOR) || RestartEditorOptions.valueOf(data.getStringExtra(EditPostActivity.EXTRA_RESTART_EDITOR)) != RestartEditorOptions.NO_RESTART) : (data.hasExtra(EditPostActivity.EXTRA_RESTART_EDITOR) && RestartEditorOptions.valueOf(data.getStringExtra(EditPostActivity.EXTRA_RESTART_EDITOR)) != RestartEditorOptions.NO_RESTART));
    }

    private void newPostSetup() {
        if (!ListenerUtil.mutListener.listen(11300)) {
            mIsNewPost = true;
        }
        if (!ListenerUtil.mutListener.listen(11302)) {
            if (mSite == null) {
                if (!ListenerUtil.mutListener.listen(11301)) {
                    showErrorAndFinish(R.string.blog_not_found);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(11304)) {
            if (!mSite.isVisible()) {
                if (!ListenerUtil.mutListener.listen(11303)) {
                    showErrorAndFinish(R.string.error_blog_hidden);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(11305)) {
            // Create a new post
            mEditPostRepository.set(() -> {
                PostModel post = mPostStore.instantiatePostModel(mSite, mIsPage, null, null);
                post.setStatus(PostStatus.DRAFT.toString());
                return post;
            });
        }
        if (!ListenerUtil.mutListener.listen(11306)) {
            mEditPostRepository.savePostSnapshot();
        }
        if (!ListenerUtil.mutListener.listen(11307)) {
            EventBus.getDefault().postSticky(new PostEvents.PostOpenedInEditor(mEditPostRepository.getLocalSiteId(), mEditPostRepository.getId()));
        }
        if (!ListenerUtil.mutListener.listen(11308)) {
            mShortcutUtils.reportShortcutUsed(Shortcut.CREATE_NEW_POST);
        }
    }

    private void newPostFromShareAction() {
        Intent intent = getIntent();
        if (!ListenerUtil.mutListener.listen(11312)) {
            if (isMediaTypeIntent(intent)) {
                if (!ListenerUtil.mutListener.listen(11310)) {
                    newPostSetup();
                }
                if (!ListenerUtil.mutListener.listen(11311)) {
                    setPostMediaFromShareAction();
                }
            } else {
                final String title = intent.getStringExtra(Intent.EXTRA_SUBJECT);
                final String text = intent.getStringExtra(Intent.EXTRA_TEXT);
                String content = migrateToGutenbergEditor(AutolinkUtils.autoCreateLinks(text));
                if (!ListenerUtil.mutListener.listen(11309)) {
                    newPostSetup(title, content);
                }
            }
        }
    }

    private void newReblogPostSetup() {
        Intent intent = getIntent();
        final String title = intent.getStringExtra(EXTRA_REBLOG_POST_TITLE);
        final String quote = intent.getStringExtra(EXTRA_REBLOG_POST_QUOTE);
        final String citation = intent.getStringExtra(EXTRA_REBLOG_POST_CITATION);
        final String image = intent.getStringExtra(EXTRA_REBLOG_POST_IMAGE);
        String content = mReblogUtils.reblogContent(image, quote, title, citation);
        if (!ListenerUtil.mutListener.listen(11313)) {
            newPostSetup(title, content);
        }
    }

    private void newPageFromLayoutPickerSetup(String title, String layoutSlug) {
        String content = mSiteStore.getBlockLayoutContent(mSite, layoutSlug);
        if (!ListenerUtil.mutListener.listen(11314)) {
            newPostSetup(title, content);
        }
    }

    private void newPostSetup(String title, String content) {
        if (!ListenerUtil.mutListener.listen(11315)) {
            mIsNewPost = true;
        }
        if (!ListenerUtil.mutListener.listen(11317)) {
            if (mSite == null) {
                if (!ListenerUtil.mutListener.listen(11316)) {
                    showErrorAndFinish(R.string.blog_not_found);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(11319)) {
            if (!mSite.isVisible()) {
                if (!ListenerUtil.mutListener.listen(11318)) {
                    showErrorAndFinish(R.string.error_blog_hidden);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(11320)) {
            // Create a new post
            mEditPostRepository.set(() -> {
                PostModel post = mPostStore.instantiatePostModel(mSite, mIsPage, title, content, PostStatus.DRAFT.toString(), null, null, false);
                return post;
            });
        }
        if (!ListenerUtil.mutListener.listen(11321)) {
            mEditPostRepository.savePostSnapshot();
        }
        if (!ListenerUtil.mutListener.listen(11322)) {
            EventBus.getDefault().postSticky(new PostEvents.PostOpenedInEditor(mEditPostRepository.getLocalSiteId(), mEditPostRepository.getId()));
        }
        if (!ListenerUtil.mutListener.listen(11323)) {
            mShortcutUtils.reportShortcutUsed(Shortcut.CREATE_NEW_POST);
        }
    }

    private void createPostEditorAnalyticsSessionTracker(boolean showGutenbergEditor, PostImmutableModel post, SiteModel site, boolean isNewPost) {
        if (!ListenerUtil.mutListener.listen(11325)) {
            if (mPostEditorAnalyticsSession == null) {
                if (!ListenerUtil.mutListener.listen(11324)) {
                    mPostEditorAnalyticsSession = new PostEditorAnalyticsSession(showGutenbergEditor ? Editor.GUTENBERG : Editor.CLASSIC, post, site, isNewPost, mAnalyticsTrackerWrapper);
                }
            }
        }
    }

    @Override
    @SuppressWarnings("checkstyle:MethodLength")
    protected void onCreate(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(11326)) {
            super.onCreate(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(11327)) {
            ((WordPress) getApplication()).component().inject(this);
        }
        if (!ListenerUtil.mutListener.listen(11328)) {
            mDispatcher.register(this);
        }
        if (!ListenerUtil.mutListener.listen(11329)) {
            mViewModel = new ViewModelProvider(this, mViewModelFactory).get(StorePostViewModel.class);
        }
        if (!ListenerUtil.mutListener.listen(11330)) {
            mStorageUtilsViewModel = new ViewModelProvider(this, mViewModelFactory).get(StorageUtilsViewModel.class);
        }
        if (!ListenerUtil.mutListener.listen(11331)) {
            mEditorBloggingPromptsViewModel = new ViewModelProvider(this, mViewModelFactory).get(EditorBloggingPromptsViewModel.class);
        }
        if (!ListenerUtil.mutListener.listen(11332)) {
            setContentView(R.layout.new_edit_post_activity);
        }
        if (!ListenerUtil.mutListener.listen(11335)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(11334)) {
                    mSite = (SiteModel) getIntent().getSerializableExtra(WordPress.SITE);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11333)) {
                    mSite = (SiteModel) savedInstanceState.getSerializable(WordPress.SITE);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11336)) {
            mIsLandingEditor = getIntent().getExtras().getBoolean(EXTRA_IS_LANDING_EDITOR);
        }
        if (!ListenerUtil.mutListener.listen(11341)) {
            // set only the editor setting for now.
            if (mSite != null) {
                SiteModel refreshedSite = mSiteStore.getSiteByLocalId(mSite.getId());
                if (!ListenerUtil.mutListener.listen(11338)) {
                    if (refreshedSite != null) {
                        if (!ListenerUtil.mutListener.listen(11337)) {
                            mSite.setMobileEditor(refreshedSite.getMobileEditor());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(11339)) {
                    mSiteSettings = SiteSettingsInterface.getInterface(this, mSite, this);
                }
                if (!ListenerUtil.mutListener.listen(11340)) {
                    // initialize settings with locally cached values, fetch remote on first pass
                    fetchSiteSettings();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11342)) {
            // Check whether to show the visual editor
            PreferenceManager.setDefaultValues(this, R.xml.account_settings, false);
        }
        if (!ListenerUtil.mutListener.listen(11343)) {
            mShowAztecEditor = AppPrefs.isAztecEditorEnabled();
        }
        if (!ListenerUtil.mutListener.listen(11344)) {
            mEditorPhotoPicker = new EditorPhotoPicker(this, this, this, mShowAztecEditor);
        }
        if (!ListenerUtil.mutListener.listen(11347)) {
            // TODO when aztec is the only editor, remove this part and set the overlay bottom margin in xml
            if (mShowAztecEditor) {
                View overlay = findViewById(R.id.view_overlay);
                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) overlay.getLayoutParams();
                if (!ListenerUtil.mutListener.listen(11345)) {
                    layoutParams.bottomMargin = getResources().getDimensionPixelOffset(R.dimen.aztec_format_bar_height);
                }
                if (!ListenerUtil.mutListener.listen(11346)) {
                    overlay.setLayoutParams(layoutParams);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11348)) {
            // Set up the action bar.
            mToolbar = findViewById(R.id.toolbar_main);
        }
        if (!ListenerUtil.mutListener.listen(11349)) {
            setSupportActionBar(mToolbar);
        }
        final ActionBar actionBar = getSupportActionBar();
        if (!ListenerUtil.mutListener.listen(11351)) {
            if (actionBar != null) {
                if (!ListenerUtil.mutListener.listen(11350)) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11352)) {
            mAppBarLayout = findViewById(R.id.appbar_main);
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        Bundle extras = getIntent().getExtras();
        String action = getIntent().getAction();
        boolean isRestarting = checkToRestart(getIntent());
        if (!ListenerUtil.mutListener.listen(11395)) {
            if (savedInstanceState == null) {
                if (!ListenerUtil.mutListener.listen(11388)) {
                    if ((ListenerUtil.mutListener.listen(11371) ? ((ListenerUtil.mutListener.listen(11370) ? ((ListenerUtil.mutListener.listen(11369) ? ((ListenerUtil.mutListener.listen(11368) ? (!getIntent().hasExtra(EXTRA_POST_LOCAL_ID) && Intent.ACTION_SEND.equals(action)) : (!getIntent().hasExtra(EXTRA_POST_LOCAL_ID) || Intent.ACTION_SEND.equals(action))) && Intent.ACTION_SEND_MULTIPLE.equals(action)) : ((ListenerUtil.mutListener.listen(11368) ? (!getIntent().hasExtra(EXTRA_POST_LOCAL_ID) && Intent.ACTION_SEND.equals(action)) : (!getIntent().hasExtra(EXTRA_POST_LOCAL_ID) || Intent.ACTION_SEND.equals(action))) || Intent.ACTION_SEND_MULTIPLE.equals(action))) && NEW_MEDIA_POST.equals(action)) : ((ListenerUtil.mutListener.listen(11369) ? ((ListenerUtil.mutListener.listen(11368) ? (!getIntent().hasExtra(EXTRA_POST_LOCAL_ID) && Intent.ACTION_SEND.equals(action)) : (!getIntent().hasExtra(EXTRA_POST_LOCAL_ID) || Intent.ACTION_SEND.equals(action))) && Intent.ACTION_SEND_MULTIPLE.equals(action)) : ((ListenerUtil.mutListener.listen(11368) ? (!getIntent().hasExtra(EXTRA_POST_LOCAL_ID) && Intent.ACTION_SEND.equals(action)) : (!getIntent().hasExtra(EXTRA_POST_LOCAL_ID) || Intent.ACTION_SEND.equals(action))) || Intent.ACTION_SEND_MULTIPLE.equals(action))) || NEW_MEDIA_POST.equals(action))) && getIntent().hasExtra(EXTRA_IS_QUICKPRESS)) : ((ListenerUtil.mutListener.listen(11370) ? ((ListenerUtil.mutListener.listen(11369) ? ((ListenerUtil.mutListener.listen(11368) ? (!getIntent().hasExtra(EXTRA_POST_LOCAL_ID) && Intent.ACTION_SEND.equals(action)) : (!getIntent().hasExtra(EXTRA_POST_LOCAL_ID) || Intent.ACTION_SEND.equals(action))) && Intent.ACTION_SEND_MULTIPLE.equals(action)) : ((ListenerUtil.mutListener.listen(11368) ? (!getIntent().hasExtra(EXTRA_POST_LOCAL_ID) && Intent.ACTION_SEND.equals(action)) : (!getIntent().hasExtra(EXTRA_POST_LOCAL_ID) || Intent.ACTION_SEND.equals(action))) || Intent.ACTION_SEND_MULTIPLE.equals(action))) && NEW_MEDIA_POST.equals(action)) : ((ListenerUtil.mutListener.listen(11369) ? ((ListenerUtil.mutListener.listen(11368) ? (!getIntent().hasExtra(EXTRA_POST_LOCAL_ID) && Intent.ACTION_SEND.equals(action)) : (!getIntent().hasExtra(EXTRA_POST_LOCAL_ID) || Intent.ACTION_SEND.equals(action))) && Intent.ACTION_SEND_MULTIPLE.equals(action)) : ((ListenerUtil.mutListener.listen(11368) ? (!getIntent().hasExtra(EXTRA_POST_LOCAL_ID) && Intent.ACTION_SEND.equals(action)) : (!getIntent().hasExtra(EXTRA_POST_LOCAL_ID) || Intent.ACTION_SEND.equals(action))) || Intent.ACTION_SEND_MULTIPLE.equals(action))) || NEW_MEDIA_POST.equals(action))) || getIntent().hasExtra(EXTRA_IS_QUICKPRESS)))) {
                        if (!ListenerUtil.mutListener.listen(11380)) {
                            if (getIntent().hasExtra(EXTRA_QUICKPRESS_BLOG_ID)) {
                                // QuickPress might want to use a different blog than the current blog
                                int localSiteId = getIntent().getIntExtra(EXTRA_QUICKPRESS_BLOG_ID, -1);
                                if (!ListenerUtil.mutListener.listen(11379)) {
                                    mSite = mSiteStore.getSiteByLocalId(localSiteId);
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(11381)) {
                            mIsPage = extras.getBoolean(EXTRA_IS_PAGE);
                        }
                        if (!ListenerUtil.mutListener.listen(11387)) {
                            if ((ListenerUtil.mutListener.listen(11382) ? (mIsPage || !TextUtils.isEmpty(extras.getString(EXTRA_PAGE_TITLE))) : (mIsPage && !TextUtils.isEmpty(extras.getString(EXTRA_PAGE_TITLE))))) {
                                if (!ListenerUtil.mutListener.listen(11386)) {
                                    newPageFromLayoutPickerSetup(extras.getString(EXTRA_PAGE_TITLE), extras.getString(EXTRA_PAGE_TEMPLATE));
                                }
                            } else if (Intent.ACTION_SEND.equals(action)) {
                                if (!ListenerUtil.mutListener.listen(11385)) {
                                    newPostFromShareAction();
                                }
                            } else if (ACTION_REBLOG.equals(action)) {
                                if (!ListenerUtil.mutListener.listen(11384)) {
                                    newReblogPostSetup();
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(11383)) {
                                    newPostSetup();
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(11372)) {
                            mEditPostRepository.loadPostByLocalPostId(extras.getInt(EXTRA_POST_LOCAL_ID));
                        }
                        if (!ListenerUtil.mutListener.listen(11378)) {
                            if (mEditPostRepository.hasPost()) {
                                if (!ListenerUtil.mutListener.listen(11376)) {
                                    if (extras.getBoolean(EXTRA_LOAD_AUTO_SAVE_REVISION)) {
                                        if (!ListenerUtil.mutListener.listen(11374)) {
                                            mEditPostRepository.update(postModel -> {
                                                boolean updateTitle = !TextUtils.isEmpty(postModel.getAutoSaveTitle());
                                                if (updateTitle) {
                                                    postModel.setTitle(postModel.getAutoSaveTitle());
                                                }
                                                boolean updateContent = !TextUtils.isEmpty(postModel.getAutoSaveContent());
                                                if (updateContent) {
                                                    postModel.setContent(postModel.getAutoSaveContent());
                                                }
                                                boolean updateExcerpt = !TextUtils.isEmpty(postModel.getAutoSaveExcerpt());
                                                if (updateExcerpt) {
                                                    postModel.setExcerpt(postModel.getAutoSaveExcerpt());
                                                }
                                                return updateTitle || updateContent || updateExcerpt;
                                            });
                                        }
                                        if (!ListenerUtil.mutListener.listen(11375)) {
                                            mEditPostRepository.savePostSnapshot();
                                        }
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(11377)) {
                                    initializePostObject();
                                }
                            } else if (isRestarting) {
                                if (!ListenerUtil.mutListener.listen(11373)) {
                                    newPostSetup();
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(11391)) {
                    if ((ListenerUtil.mutListener.listen(11389) ? (isRestarting || extras.getBoolean(EXTRA_IS_NEW_POST)) : (isRestarting && extras.getBoolean(EXTRA_IS_NEW_POST)))) {
                        if (!ListenerUtil.mutListener.listen(11390)) {
                            // Fixes https://github.com/wordpress-mobile/gutenberg-mobile/issues/2072
                            mIsNewPost = true;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(11394)) {
                    // retrieve Editor session data if switched editors
                    if ((ListenerUtil.mutListener.listen(11392) ? (isRestarting || extras.containsKey(STATE_KEY_EDITOR_SESSION_DATA)) : (isRestarting && extras.containsKey(STATE_KEY_EDITOR_SESSION_DATA)))) {
                        if (!ListenerUtil.mutListener.listen(11393)) {
                            mPostEditorAnalyticsSession = PostEditorAnalyticsSession.fromBundle(extras, STATE_KEY_EDITOR_SESSION_DATA, mAnalyticsTrackerWrapper);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11353)) {
                    mEditorMedia.setDroppedMediaUris(savedInstanceState.getParcelableArrayList(STATE_KEY_DROPPED_MEDIA_URIS));
                }
                if (!ListenerUtil.mutListener.listen(11354)) {
                    mIsNewPost = savedInstanceState.getBoolean(STATE_KEY_IS_NEW_POST, false);
                }
                if (!ListenerUtil.mutListener.listen(11355)) {
                    updatePostLoadingAndDialogState(PostLoadingState.fromInt(savedInstanceState.getInt(STATE_KEY_POST_LOADING_STATE, 0)));
                }
                if (!ListenerUtil.mutListener.listen(11356)) {
                    mRevision = savedInstanceState.getParcelable(STATE_KEY_REVISION);
                }
                if (!ListenerUtil.mutListener.listen(11357)) {
                    mPostEditorAnalyticsSession = PostEditorAnalyticsSession.fromBundle(savedInstanceState, STATE_KEY_EDITOR_SESSION_DATA, mAnalyticsTrackerWrapper);
                }
                if (!ListenerUtil.mutListener.listen(11362)) {
                    // if we have a remote id saved, let's first try that, as the local Id might have changed after FETCH_POSTS
                    if (savedInstanceState.containsKey(STATE_KEY_POST_REMOTE_ID)) {
                        if (!ListenerUtil.mutListener.listen(11360)) {
                            mEditPostRepository.loadPostByRemotePostId(savedInstanceState.getLong(STATE_KEY_POST_REMOTE_ID), mSite);
                        }
                        if (!ListenerUtil.mutListener.listen(11361)) {
                            initializePostObject();
                        }
                    } else if (savedInstanceState.containsKey(STATE_KEY_POST_LOCAL_ID)) {
                        if (!ListenerUtil.mutListener.listen(11358)) {
                            mEditPostRepository.loadPostByLocalPostId(savedInstanceState.getInt(STATE_KEY_POST_LOCAL_ID));
                        }
                        if (!ListenerUtil.mutListener.listen(11359)) {
                            initializePostObject();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(11363)) {
                    mEditorFragment = (EditorFragmentAbstract) fragmentManager.getFragment(savedInstanceState, STATE_KEY_EDITOR_FRAGMENT);
                }
                if (!ListenerUtil.mutListener.listen(11365)) {
                    if (mEditorFragment instanceof EditorMediaUploadListener) {
                        if (!ListenerUtil.mutListener.listen(11364)) {
                            mEditorMediaUploadListener = (EditorMediaUploadListener) mEditorFragment;
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(11367)) {
                    if (mEditorFragment instanceof StorySaveMediaListener) {
                        if (!ListenerUtil.mutListener.listen(11366)) {
                            mStoriesEventListener.setSaveMediaListener((StorySaveMediaListener) mEditorFragment);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11398)) {
            if (mSite == null) {
                if (!ListenerUtil.mutListener.listen(11396)) {
                    ToastUtils.showToast(this, R.string.blog_not_found, ToastUtils.Duration.SHORT);
                }
                if (!ListenerUtil.mutListener.listen(11397)) {
                    finish();
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(11400)) {
            // Ensure we have a valid post
            if (!mEditPostRepository.hasPost()) {
                if (!ListenerUtil.mutListener.listen(11399)) {
                    showErrorAndFinish(R.string.post_not_found);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(11401)) {
            mEditorMedia.start(mSite, this);
        }
        if (!ListenerUtil.mutListener.listen(11402)) {
            startObserving();
        }
        if (!ListenerUtil.mutListener.listen(11404)) {
            if (mHasSetPostContent = mEditorFragment != null) {
                if (!ListenerUtil.mutListener.listen(11403)) {
                    mEditorFragment.setImageLoader(mImageLoader);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11408)) {
            // Ensure that this check happens when mPost is set
            if (savedInstanceState == null) {
                String restartEditorOptionName = getIntent().getStringExtra(EXTRA_RESTART_EDITOR);
                RestartEditorOptions restartEditorOption = restartEditorOptionName == null ? RestartEditorOptions.RESTART_DONT_SUPPRESS_GUTENBERG : RestartEditorOptions.valueOf(restartEditorOptionName);
                if (!ListenerUtil.mutListener.listen(11407)) {
                    mShowGutenbergEditor = (ListenerUtil.mutListener.listen(11406) ? (PostUtils.shouldShowGutenbergEditor(mIsNewPost, mEditPostRepository.getContent(), mSite) || restartEditorOption != RestartEditorOptions.RESTART_SUPPRESS_GUTENBERG) : (PostUtils.shouldShowGutenbergEditor(mIsNewPost, mEditPostRepository.getContent(), mSite) && restartEditorOption != RestartEditorOptions.RESTART_SUPPRESS_GUTENBERG));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11405)) {
                    mShowGutenbergEditor = savedInstanceState.getBoolean(STATE_KEY_GUTENBERG_IS_SHOWN);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11409)) {
            // ok now we are sure to have both a valid Post and showGutenberg flag, let's start the editing session tracker
            createPostEditorAnalyticsSessionTracker(mShowGutenbergEditor, mEditPostRepository.getPost(), mSite, mIsNewPost);
        }
        if (!ListenerUtil.mutListener.listen(11410)) {
            logTemplateSelection();
        }
        if (!ListenerUtil.mutListener.listen(11414)) {
            // Bump post created analytics only once, first time the editor is opened
            if ((ListenerUtil.mutListener.listen(11412) ? ((ListenerUtil.mutListener.listen(11411) ? (mIsNewPost || savedInstanceState == null) : (mIsNewPost && savedInstanceState == null)) || !isRestarting) : ((ListenerUtil.mutListener.listen(11411) ? (mIsNewPost || savedInstanceState == null) : (mIsNewPost && savedInstanceState == null)) && !isRestarting))) {
                if (!ListenerUtil.mutListener.listen(11413)) {
                    AnalyticsUtils.trackEditorCreatedPost(action, getIntent(), mSiteStore.getSiteByLocalId(mEditPostRepository.getLocalSiteId()), mEditPostRepository.getPost());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11419)) {
            if (!mIsNewPost) {
                if (!ListenerUtil.mutListener.listen(11416)) {
                    // created on this device)
                    if (PostUtils.contentContainsWPStoryGutenbergBlocks(mEditPostRepository.getPost().getContent())) {
                        if (!ListenerUtil.mutListener.listen(11415)) {
                            fetchMediaList();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(11417)) {
                    // to prevent the user from tapping RETRY on a Post that is being currently edited
                    UploadService.cancelFinalNotification(this, mEditPostRepository.getPost());
                }
                if (!ListenerUtil.mutListener.listen(11418)) {
                    resetUploadingMediaToFailedIfPostHasNotMediaInProgressOrQueued();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11420)) {
            setTitle(SiteUtils.getSiteNameOrHomeURL(mSite));
        }
        if (!ListenerUtil.mutListener.listen(11421)) {
            mSectionsPagerAdapter = new SectionsPagerAdapter(fragmentManager);
        }
        if (!ListenerUtil.mutListener.listen(11426)) {
            // we need to make sure AT cookie is available when trying to edit post on private AT site
            if ((ListenerUtil.mutListener.listen(11422) ? (mSite.isPrivateWPComAtomic() || mPrivateAtomicCookie.isCookieRefreshRequired()) : (mSite.isPrivateWPComAtomic() && mPrivateAtomicCookie.isCookieRefreshRequired()))) {
                if (!ListenerUtil.mutListener.listen(11424)) {
                    PrivateAtCookieRefreshProgressDialog.Companion.showIfNecessary(fragmentManager);
                }
                if (!ListenerUtil.mutListener.listen(11425)) {
                    mDispatcher.dispatch(SiteActionBuilder.newFetchPrivateAtomicCookieAction(new FetchPrivateAtomicCookiePayload(mSite.getSiteId())));
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11423)) {
                    setupViewPager();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11427)) {
            ActivityId.trackLastActivity(ActivityId.POST_EDITOR);
        }
        if (!ListenerUtil.mutListener.listen(11428)) {
            setupPrepublishingBottomSheetRunnable();
        }
        if (!ListenerUtil.mutListener.listen(11429)) {
            mStoriesEventListener.start(this.getLifecycle(), mSite, mEditPostRepository, this);
        }
        if (!ListenerUtil.mutListener.listen(11430)) {
            // (even in cases when the VM could be re-created like when activity is destroyed in the background)
            mStorageUtilsViewModel.start(savedInstanceState == null);
        }
    }

    private void presentNewPageNoticeIfNeeded() {
        if (!ListenerUtil.mutListener.listen(11432)) {
            if ((ListenerUtil.mutListener.listen(11431) ? (!mIsPage && !mIsNewPost) : (!mIsPage || !mIsNewPost))) {
                return;
            }
        }
        String message = mEditPostRepository.getContent().isEmpty() ? getString(R.string.mlp_notice_blank_page_created) : getString(R.string.mlp_notice_page_created);
        if (!ListenerUtil.mutListener.listen(11433)) {
            mEditorFragment.showNotice(message);
        }
    }

    private void fetchSiteSettings() {
        if (!ListenerUtil.mutListener.listen(11434)) {
            mSiteSettings.init(true);
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPrivateAtomicCookieFetched(OnPrivateAtomicCookieFetched event) {
        if (!ListenerUtil.mutListener.listen(11437)) {
            // if the dialog is not showing by the time cookie fetched it means that it was dismissed and content was loaded
            if (PrivateAtCookieRefreshProgressDialog.Companion.isShowing(getSupportFragmentManager())) {
                if (!ListenerUtil.mutListener.listen(11435)) {
                    setupViewPager();
                }
                if (!ListenerUtil.mutListener.listen(11436)) {
                    PrivateAtCookieRefreshProgressDialog.Companion.dismissIfNecessary(getSupportFragmentManager());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11440)) {
            if (event.isError()) {
                if (!ListenerUtil.mutListener.listen(11438)) {
                    AppLog.e(AppLog.T.EDITOR, "Failed to load private AT cookie. " + event.error.type + " - " + event.error.message);
                }
                if (!ListenerUtil.mutListener.listen(11439)) {
                    WPSnackbar.make(findViewById(R.id.editor_activity), R.string.media_accessing_failed, Snackbar.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void onCookieProgressDialogCancelled() {
        if (!ListenerUtil.mutListener.listen(11441)) {
            WPSnackbar.make(findViewById(R.id.editor_activity), R.string.media_accessing_failed, Snackbar.LENGTH_LONG).show();
        }
        if (!ListenerUtil.mutListener.listen(11442)) {
            setupViewPager();
        }
    }

    // SiteSettingsListener
    @Override
    public void onSaveError(Exception error) {
    }

    @Override
    public void onFetchError(Exception error) {
    }

    @Override
    public void onSettingsUpdated() {
        // Let's hold the value in local variable as listener is too noisy
        boolean isJetpackSsoEnabled = (ListenerUtil.mutListener.listen(11443) ? (mSite.isJetpackConnected() || mSiteSettings.isJetpackSsoEnabled()) : (mSite.isJetpackConnected() && mSiteSettings.isJetpackSsoEnabled()));
        if (!ListenerUtil.mutListener.listen(11448)) {
            if (mIsJetpackSsoEnabled != isJetpackSsoEnabled) {
                if (!ListenerUtil.mutListener.listen(11444)) {
                    mIsJetpackSsoEnabled = isJetpackSsoEnabled;
                }
                if (!ListenerUtil.mutListener.listen(11447)) {
                    if (mEditorFragment instanceof GutenbergEditorFragment) {
                        GutenbergEditorFragment gutenbergFragment = (GutenbergEditorFragment) mEditorFragment;
                        if (!ListenerUtil.mutListener.listen(11445)) {
                            gutenbergFragment.setJetpackSsoEnabled(mIsJetpackSsoEnabled);
                        }
                        if (!ListenerUtil.mutListener.listen(11446)) {
                            gutenbergFragment.updateCapabilities(getGutenbergPropsBuilder());
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onSettingsSaved() {
    }

    @Override
    public void onCredentialsValidated(Exception error) {
    }

    private void setupViewPager() {
        if (!ListenerUtil.mutListener.listen(11449)) {
            // Set up the ViewPager with the sections adapter.
            mViewPager = findViewById(R.id.pager);
        }
        if (!ListenerUtil.mutListener.listen(11450)) {
            mViewPager.setAdapter(mSectionsPagerAdapter);
        }
        if (!ListenerUtil.mutListener.listen(11451)) {
            mViewPager.setOffscreenPageLimit(4);
        }
        if (!ListenerUtil.mutListener.listen(11452)) {
            mViewPager.setPagingEnabled(false);
        }
        if (!ListenerUtil.mutListener.listen(11453)) {
            // to do this if we have a reference to the Tab.
            mViewPager.clearOnPageChangeListeners();
        }
        if (!ListenerUtil.mutListener.listen(11471)) {
            mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

                @Override
                public void onPageSelected(int position) {
                    if (!ListenerUtil.mutListener.listen(11454)) {
                        invalidateOptionsMenu();
                    }
                    if (!ListenerUtil.mutListener.listen(11470)) {
                        if (position == PAGE_CONTENT) {
                            if (!ListenerUtil.mutListener.listen(11467)) {
                                setTitle(SiteUtils.getSiteNameOrHomeURL(mSite));
                            }
                            if (!ListenerUtil.mutListener.listen(11468)) {
                                AppBarLayoutExtensionsKt.setLiftOnScrollTargetViewIdAndRequestLayout(mAppBarLayout, View.NO_ID);
                            }
                            if (!ListenerUtil.mutListener.listen(11469)) {
                                mToolbar.setBackgroundResource(R.drawable.tab_layout_background);
                            }
                        } else if (position == PAGE_SETTINGS) {
                            if (!ListenerUtil.mutListener.listen(11463)) {
                                setTitle(mEditPostRepository.isPage() ? R.string.page_settings : R.string.post_settings);
                            }
                            if (!ListenerUtil.mutListener.listen(11464)) {
                                mEditorPhotoPicker.hidePhotoPicker();
                            }
                            if (!ListenerUtil.mutListener.listen(11465)) {
                                mAppBarLayout.setLiftOnScrollTargetViewId(R.id.settings_fragment_root);
                            }
                            if (!ListenerUtil.mutListener.listen(11466)) {
                                mToolbar.setBackground(null);
                            }
                        } else if (position == PAGE_PUBLISH_SETTINGS) {
                            if (!ListenerUtil.mutListener.listen(11459)) {
                                setTitle(R.string.publish_date);
                            }
                            if (!ListenerUtil.mutListener.listen(11460)) {
                                mEditorPhotoPicker.hidePhotoPicker();
                            }
                            if (!ListenerUtil.mutListener.listen(11461)) {
                                AppBarLayoutExtensionsKt.setLiftOnScrollTargetViewIdAndRequestLayout(mAppBarLayout, View.NO_ID);
                            }
                            if (!ListenerUtil.mutListener.listen(11462)) {
                                mToolbar.setBackground(null);
                            }
                        } else if (position == PAGE_HISTORY) {
                            if (!ListenerUtil.mutListener.listen(11455)) {
                                setTitle(R.string.history_title);
                            }
                            if (!ListenerUtil.mutListener.listen(11456)) {
                                mEditorPhotoPicker.hidePhotoPicker();
                            }
                            if (!ListenerUtil.mutListener.listen(11457)) {
                                mAppBarLayout.setLiftOnScrollTargetViewId(R.id.empty_recycler_view);
                            }
                            if (!ListenerUtil.mutListener.listen(11458)) {
                                mToolbar.setBackground(null);
                            }
                        }
                    }
                }
            });
        }
    }

    private void startObserving() {
        if (!ListenerUtil.mutListener.listen(11472)) {
            mEditorMedia.getUiState().observe(this, uiState -> {
                if (uiState != null) {
                    updateAddingMediaToEditorProgressDialogState(uiState.getProgressDialogUiState());
                    if (uiState.getEditorOverlayVisibility()) {
                        showOverlay(false);
                    } else {
                        hideOverlay();
                    }
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(11473)) {
            mEditorMedia.getSnackBarMessage().observe(this, event -> {
                SnackbarMessageHolder messageHolder = event.getContentIfNotHandled();
                if (messageHolder != null) {
                    WPSnackbar.make(findViewById(R.id.editor_activity), mUiHelpers.getTextOfUiString(this, messageHolder.getMessage()), Snackbar.LENGTH_SHORT).show();
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(11474)) {
            mEditorMedia.getToastMessage().observe(this, event -> {
                ToastMessageHolder contentIfNotHandled = event.getContentIfNotHandled();
                if (contentIfNotHandled != null) {
                    contentIfNotHandled.show(this);
                }
            });
        }
        if (!ListenerUtil.mutListener.listen(11475)) {
            mViewModel.getOnSavePostTriggered().observe(this, unitEvent -> unitEvent.applyIfNotHandled(unit -> {
                updateAndSavePostAsync();
                return null;
            }));
        }
        if (!ListenerUtil.mutListener.listen(11476)) {
            mViewModel.getOnFinish().observe(this, finishEvent -> finishEvent.applyIfNotHandled(activityFinishState -> {
                switch(activityFinishState) {
                    case SAVED_ONLINE:
                        saveResult(true, false);
                        break;
                    case SAVED_LOCALLY:
                        saveResult(true, true);
                        break;
                    case CANCELLED:
                        saveResult(false, true);
                        break;
                }
                removePostOpenInEditorStickyEvent();
                mEditorMedia.definitelyDeleteBackspaceDeletedMediaItemsAsync();
                finish();
                return null;
            }));
        }
        if (!ListenerUtil.mutListener.listen(11477)) {
            mEditPostRepository.getPostChanged().observe(this, postEvent -> postEvent.applyIfNotHandled(post -> {
                mViewModel.savePostToDb(mEditPostRepository, mSite);
                return null;
            }));
        }
        if (!ListenerUtil.mutListener.listen(11478)) {
            mStorageUtilsViewModel.getCheckStorageWarning().observe(this, event -> event.applyIfNotHandled(unit -> {
                mStorageUtilsViewModel.onStorageWarningCheck(getSupportFragmentManager(), Source.EDITOR);
                return null;
            }));
        }
        if (!ListenerUtil.mutListener.listen(11479)) {
            mEditorBloggingPromptsViewModel.getOnBloggingPromptLoaded().observe(this, event -> {
                event.applyIfNotHandled(loadedPrompt -> {
                    mEditPostRepository.updateAsync(postModel -> {
                        postModel.setContent(loadedPrompt.getContent());
                        postModel.setAnsweredPromptId(loadedPrompt.getPromptId());
                        postModel.setTagNames(loadedPrompt.getTag());
                        return true;
                    }, (postModel, result) -> {
                        refreshEditorContent();
                        return null;
                    });
                    return null;
                });
            });
        }
    }

    private void initializePostObject() {
        if (!ListenerUtil.mutListener.listen(11485)) {
            if (mEditPostRepository.hasPost()) {
                if (!ListenerUtil.mutListener.listen(11480)) {
                    mEditPostRepository.savePostSnapshotWhenEditorOpened();
                }
                if (!ListenerUtil.mutListener.listen(11481)) {
                    mEditPostRepository.replace(UploadService::updatePostWithCurrentlyCompletedUploads);
                }
                if (!ListenerUtil.mutListener.listen(11482)) {
                    mIsPage = mEditPostRepository.isPage();
                }
                if (!ListenerUtil.mutListener.listen(11483)) {
                    EventBus.getDefault().postSticky(new PostEvents.PostOpenedInEditor(mEditPostRepository.getLocalSiteId(), mEditPostRepository.getId()));
                }
                if (!ListenerUtil.mutListener.listen(11484)) {
                    mEditorMedia.purgeMediaToPostAssociationsIfNotInPostAnymoreAsync();
                }
            }
        }
    }

    // this method aims at recovering the current state of media items if they're inconsistent within the PostModel.
    private void resetUploadingMediaToFailedIfPostHasNotMediaInProgressOrQueued() {
        boolean useAztec = AppPrefs.isAztecEditorEnabled();
        if (!ListenerUtil.mutListener.listen(11487)) {
            if ((ListenerUtil.mutListener.listen(11486) ? (!useAztec && UploadService.hasPendingOrInProgressMediaUploadsForPost(mEditPostRepository.getPost())) : (!useAztec || UploadService.hasPendingOrInProgressMediaUploadsForPost(mEditPostRepository.getPost())))) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(11488)) {
            mEditPostRepository.updateAsync(postModel -> {
                String oldContent = postModel.getContent();
                if (!AztecEditorFragment.hasMediaItemsMarkedUploading(EditPostActivity.this, oldContent) && // we need to make sure items marked failed are still failed or not as well
                !AztecEditorFragment.hasMediaItemsMarkedFailed(EditPostActivity.this, oldContent)) {
                    return false;
                }
                String newContent = AztecEditorFragment.resetUploadingMediaToFailed(EditPostActivity.this, oldContent);
                if (!TextUtils.isEmpty(oldContent) && newContent != null && oldContent.compareTo(newContent) != 0) {
                    postModel.setContent(newContent);
                    return true;
                }
                return false;
            }, null);
        }
    }

    @Override
    protected void onResume() {
        if (!ListenerUtil.mutListener.listen(11489)) {
            super.onResume();
        }
        if (!ListenerUtil.mutListener.listen(11490)) {
            EventBus.getDefault().register(this);
        }
        if (!ListenerUtil.mutListener.listen(11491)) {
            reattachUploadingMediaForAztec();
        }
        if (!ListenerUtil.mutListener.listen(11492)) {
            // Bump editor opened event every time the activity is resumed, to match the EDITOR_CLOSED event onPause
            PostUtils.trackOpenEditorAnalytics(mEditPostRepository.getPost(), mSite);
        }
        if (!ListenerUtil.mutListener.listen(11493)) {
            mIsConfigChange = false;
        }
    }

    private void reattachUploadingMediaForAztec() {
        if (!ListenerUtil.mutListener.listen(11495)) {
            if (mEditorMediaUploadListener != null) {
                if (!ListenerUtil.mutListener.listen(11494)) {
                    mEditorMedia.reattachUploadingMediaForAztec(mEditPostRepository, mEditorFragment instanceof AztecEditorFragment, mEditorMediaUploadListener);
                }
            }
        }
    }

    @Override
    protected void onPause() {
        if (!ListenerUtil.mutListener.listen(11496)) {
            super.onPause();
        }
        if (!ListenerUtil.mutListener.listen(11497)) {
            EventBus.getDefault().unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(11498)) {
            AnalyticsTracker.track(AnalyticsTracker.Stat.EDITOR_CLOSED);
        }
    }

    @Override
    protected void onStop() {
        if (!ListenerUtil.mutListener.listen(11499)) {
            super.onStop();
        }
        if (!ListenerUtil.mutListener.listen(11503)) {
            if ((ListenerUtil.mutListener.listen(11500) ? (mAztecImageLoader != null || isFinishing()) : (mAztecImageLoader != null && isFinishing()))) {
                if (!ListenerUtil.mutListener.listen(11501)) {
                    mAztecImageLoader.clearTargets();
                }
                if (!ListenerUtil.mutListener.listen(11502)) {
                    mAztecImageLoader = null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11506)) {
            if ((ListenerUtil.mutListener.listen(11504) ? (mShowPrepublishingBottomSheetHandler != null || mShowPrepublishingBottomSheetRunnable != null) : (mShowPrepublishingBottomSheetHandler != null && mShowPrepublishingBottomSheetRunnable != null))) {
                if (!ListenerUtil.mutListener.listen(11505)) {
                    mShowPrepublishingBottomSheetHandler.removeCallbacks(mShowPrepublishingBottomSheetRunnable);
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (!ListenerUtil.mutListener.listen(11510)) {
            if ((ListenerUtil.mutListener.listen(11507) ? (!mIsConfigChange || (mRestartEditorOption == RestartEditorOptions.NO_RESTART)) : (!mIsConfigChange && (mRestartEditorOption == RestartEditorOptions.NO_RESTART)))) {
                if (!ListenerUtil.mutListener.listen(11509)) {
                    if (mPostEditorAnalyticsSession != null) {
                        if (!ListenerUtil.mutListener.listen(11508)) {
                            mPostEditorAnalyticsSession.end();
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11511)) {
            mDispatcher.unregister(this);
        }
        if (!ListenerUtil.mutListener.listen(11512)) {
            mEditorMedia.cancelAddMediaToEditorActions();
        }
        if (!ListenerUtil.mutListener.listen(11513)) {
            removePostOpenInEditorStickyEvent();
        }
        if (!ListenerUtil.mutListener.listen(11515)) {
            if (mEditorFragment instanceof AztecEditorFragment) {
                if (!ListenerUtil.mutListener.listen(11514)) {
                    ((AztecEditorFragment) mEditorFragment).disableContentLogOnCrashes();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11517)) {
            if (mReactNativeRequestHandler != null) {
                if (!ListenerUtil.mutListener.listen(11516)) {
                    mReactNativeRequestHandler.destroy();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11518)) {
            super.onDestroy();
        }
    }

    private void removePostOpenInEditorStickyEvent() {
        PostEvents.PostOpenedInEditor stickyEvent = EventBus.getDefault().getStickyEvent(PostEvents.PostOpenedInEditor.class);
        if (!ListenerUtil.mutListener.listen(11520)) {
            if (stickyEvent != null) {
                if (!ListenerUtil.mutListener.listen(11519)) {
                    // "Consume" the sticky event
                    EventBus.getDefault().removeStickyEvent(stickyEvent);
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!ListenerUtil.mutListener.listen(11521)) {
            super.onSaveInstanceState(outState);
        }
        if (!ListenerUtil.mutListener.listen(11522)) {
            // Saves both post objects so we can restore them in onCreate()
            updateAndSavePostAsync();
        }
        if (!ListenerUtil.mutListener.listen(11523)) {
            outState.putInt(STATE_KEY_POST_LOCAL_ID, mEditPostRepository.getId());
        }
        if (!ListenerUtil.mutListener.listen(11525)) {
            if (!mEditPostRepository.isLocalDraft()) {
                if (!ListenerUtil.mutListener.listen(11524)) {
                    outState.putLong(STATE_KEY_POST_REMOTE_ID, mEditPostRepository.getRemotePostId());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11526)) {
            outState.putInt(STATE_KEY_POST_LOADING_STATE, mPostLoadingState.getValue());
        }
        if (!ListenerUtil.mutListener.listen(11527)) {
            outState.putBoolean(STATE_KEY_IS_NEW_POST, mIsNewPost);
        }
        if (!ListenerUtil.mutListener.listen(11528)) {
            outState.putBoolean(STATE_KEY_IS_PHOTO_PICKER_VISIBLE, mEditorPhotoPicker.isPhotoPickerShowing());
        }
        if (!ListenerUtil.mutListener.listen(11529)) {
            outState.putBoolean(STATE_KEY_HTML_MODE_ON, mHtmlModeMenuStateOn);
        }
        if (!ListenerUtil.mutListener.listen(11530)) {
            outState.putSerializable(WordPress.SITE, mSite);
        }
        if (!ListenerUtil.mutListener.listen(11531)) {
            outState.putParcelable(STATE_KEY_REVISION, mRevision);
        }
        if (!ListenerUtil.mutListener.listen(11532)) {
            outState.putSerializable(STATE_KEY_EDITOR_SESSION_DATA, mPostEditorAnalyticsSession);
        }
        if (!ListenerUtil.mutListener.listen(11533)) {
            // don't call sessionData.end() in onDestroy() if this is an Android config change
            mIsConfigChange = true;
        }
        if (!ListenerUtil.mutListener.listen(11534)) {
            outState.putBoolean(STATE_KEY_GUTENBERG_IS_SHOWN, mShowGutenbergEditor);
        }
        if (!ListenerUtil.mutListener.listen(11535)) {
            outState.putParcelableArrayList(STATE_KEY_DROPPED_MEDIA_URIS, mEditorMedia.getDroppedMediaUris());
        }
        if (!ListenerUtil.mutListener.listen(11537)) {
            if (mEditorFragment != null) {
                if (!ListenerUtil.mutListener.listen(11536)) {
                    getSupportFragmentManager().putFragment(outState, STATE_KEY_EDITOR_FRAGMENT, mEditorFragment);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11538)) {
            // photo capture (see: https://github.com/wordpress-mobile/WordPress-Android/issues/11296)
            outState.putString(STATE_KEY_MEDIA_CAPTURE_PATH, mMediaCapturePath);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (!ListenerUtil.mutListener.listen(11539)) {
            super.onRestoreInstanceState(savedInstanceState);
        }
        if (!ListenerUtil.mutListener.listen(11540)) {
            mHtmlModeMenuStateOn = savedInstanceState.getBoolean(STATE_KEY_HTML_MODE_ON);
        }
        if (!ListenerUtil.mutListener.listen(11542)) {
            if (savedInstanceState.getBoolean(STATE_KEY_IS_PHOTO_PICKER_VISIBLE, false)) {
                if (!ListenerUtil.mutListener.listen(11541)) {
                    mEditorPhotoPicker.showPhotoPicker(mSite);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11543)) {
            // Restore media capture path for orientation changes during photo capture
            mMediaCapturePath = savedInstanceState.getString(STATE_KEY_MEDIA_CAPTURE_PATH, "");
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (!ListenerUtil.mutListener.listen(11544)) {
            super.onConfigurationChanged(newConfig);
        }
        if (!ListenerUtil.mutListener.listen(11545)) {
            mEditorPhotoPicker.onOrientationChanged(newConfig.orientation);
        }
    }

    private PrimaryEditorAction getPrimaryAction() {
        return mEditorActionsProvider.getPrimaryAction(mEditPostRepository.getStatus(), UploadUtils.userCanPublish(mSite), mIsLandingEditor);
    }

    private String getPrimaryActionText() {
        return getString(getPrimaryAction().getTitleResource());
    }

    private SecondaryEditorAction getSecondaryAction() {
        return mEditorActionsProvider.getSecondaryAction(mEditPostRepository.getStatus(), UploadUtils.userCanPublish(mSite));
    }

    @Nullable
    private String getSecondaryActionText() {
        @StringRes
        Integer titleResource = getSecondaryAction().getTitleResource();
        return titleResource != null ? getString(titleResource) : null;
    }

    private boolean shouldSwitchToGutenbergBeVisible(EditorFragmentAbstract editorFragment, SiteModel site) {
        if (!ListenerUtil.mutListener.listen(11547)) {
            // Some guard conditions
            if (!mEditPostRepository.hasPost()) {
                if (!ListenerUtil.mutListener.listen(11546)) {
                    AppLog.w(T.EDITOR, "shouldSwitchToGutenbergBeVisible got a null post parameter.");
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(11549)) {
            if (editorFragment == null) {
                if (!ListenerUtil.mutListener.listen(11548)) {
                    AppLog.w(T.EDITOR, "shouldSwitchToGutenbergBeVisible got a null editorFragment parameter.");
                }
                return false;
            }
        }
        // Check whether the content has blocks.
        boolean hasBlocks = false;
        boolean isEmpty = false;
        try {
            final String content = (String) editorFragment.getContent(mEditPostRepository.getContent());
            if (!ListenerUtil.mutListener.listen(11550)) {
                hasBlocks = PostUtils.contentContainsGutenbergBlocks(content);
            }
            if (!ListenerUtil.mutListener.listen(11551)) {
                isEmpty = TextUtils.isEmpty(content);
            }
        } catch (EditorFragmentNotAddedException e) {
        }
        // don't offer the switch.
        return (ListenerUtil.mutListener.listen(11553) ? (hasBlocks && ((ListenerUtil.mutListener.listen(11552) ? (SiteUtils.isBlockEditorDefaultForNewPost(site) || isEmpty) : (SiteUtils.isBlockEditorDefaultForNewPost(site) && isEmpty)))) : (hasBlocks || ((ListenerUtil.mutListener.listen(11552) ? (SiteUtils.isBlockEditorDefaultForNewPost(site) || isEmpty) : (SiteUtils.isBlockEditorDefaultForNewPost(site) && isEmpty)))));
    }

    /*
     * shows/hides the overlay which appears atop the editor, which effectively disables it
     */
    private void showOverlay(boolean animate) {
        View overlay = findViewById(R.id.view_overlay);
        if (!ListenerUtil.mutListener.listen(11556)) {
            if (animate) {
                if (!ListenerUtil.mutListener.listen(11555)) {
                    AniUtils.fadeIn(overlay, AniUtils.Duration.MEDIUM);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11554)) {
                    overlay.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void hideOverlay() {
        View overlay = findViewById(R.id.view_overlay);
        if (!ListenerUtil.mutListener.listen(11557)) {
            overlay.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPhotoPickerShown() {
        if (!ListenerUtil.mutListener.listen(11558)) {
            // animate in the editor overlay
            showOverlay(true);
        }
        if (!ListenerUtil.mutListener.listen(11560)) {
            if (mEditorFragment instanceof AztecEditorFragment) {
                if (!ListenerUtil.mutListener.listen(11559)) {
                    ((AztecEditorFragment) mEditorFragment).enableMediaMode(true);
                }
            }
        }
    }

    @Override
    public void onPhotoPickerHidden() {
        if (!ListenerUtil.mutListener.listen(11561)) {
            hideOverlay();
        }
        if (!ListenerUtil.mutListener.listen(11563)) {
            if (mEditorFragment instanceof AztecEditorFragment) {
                if (!ListenerUtil.mutListener.listen(11562)) {
                    ((AztecEditorFragment) mEditorFragment).enableMediaMode(false);
                }
            }
        }
    }

    /*
     * called by PhotoPickerFragment when media is selected - may be a single item or a list of items
     */
    @Override
    public void onPhotoPickerMediaChosen(@NotNull final List<? extends Uri> uriList) {
        if (!ListenerUtil.mutListener.listen(11564)) {
            mEditorPhotoPicker.hidePhotoPicker();
        }
        if (!ListenerUtil.mutListener.listen(11565)) {
            mEditorMedia.onPhotoPickerMediaChosen(uriList);
        }
    }

    /*
     * called by PhotoPickerFragment when user clicks an icon to launch the camera, native
     * picker, or WP media picker
     */
    @Override
    public void onPhotoPickerIconClicked(@NonNull PhotoPickerIcon icon, boolean allowMultipleSelection) {
        if (!ListenerUtil.mutListener.listen(11566)) {
            mEditorPhotoPicker.hidePhotoPicker();
        }
        if (!ListenerUtil.mutListener.listen(11579)) {
            if ((ListenerUtil.mutListener.listen(11567) ? (!icon.requiresUploadPermission() && WPMediaUtils.currentUserCanUploadMedia(mSite)) : (!icon.requiresUploadPermission() || WPMediaUtils.currentUserCanUploadMedia(mSite)))) {
                if (!ListenerUtil.mutListener.listen(11569)) {
                    mEditorPhotoPicker.setAllowMultipleSelection(allowMultipleSelection);
                }
                if (!ListenerUtil.mutListener.listen(11578)) {
                    switch(icon) {
                        case ANDROID_CAPTURE_PHOTO:
                            if (!ListenerUtil.mutListener.listen(11570)) {
                                launchCamera();
                            }
                            break;
                        case ANDROID_CAPTURE_VIDEO:
                            if (!ListenerUtil.mutListener.listen(11571)) {
                                launchVideoCamera();
                            }
                            break;
                        case ANDROID_CHOOSE_PHOTO_OR_VIDEO:
                            if (!ListenerUtil.mutListener.listen(11572)) {
                                WPMediaUtils.launchMediaLibrary(this, allowMultipleSelection);
                            }
                            break;
                        case ANDROID_CHOOSE_PHOTO:
                            if (!ListenerUtil.mutListener.listen(11573)) {
                                launchPictureLibrary();
                            }
                            break;
                        case ANDROID_CHOOSE_VIDEO:
                            if (!ListenerUtil.mutListener.listen(11574)) {
                                launchVideoLibrary();
                            }
                            break;
                        case WP_MEDIA:
                            if (!ListenerUtil.mutListener.listen(11575)) {
                                mMediaPickerLauncher.viewWPMediaLibraryPickerForResult(this, mSite, MediaBrowserType.EDITOR_PICKER);
                            }
                            break;
                        case STOCK_MEDIA:
                            final int requestCode = allowMultipleSelection ? RequestCodes.STOCK_MEDIA_PICKER_MULTI_SELECT : RequestCodes.STOCK_MEDIA_PICKER_SINGLE_SELECT_FOR_GUTENBERG_BLOCK;
                            if (!ListenerUtil.mutListener.listen(11576)) {
                                mMediaPickerLauncher.showStockMediaPickerForResult(this, mSite, requestCode, allowMultipleSelection);
                            }
                            break;
                        case GIF:
                            if (!ListenerUtil.mutListener.listen(11577)) {
                                mMediaPickerLauncher.showGifPickerForResult(this, mSite, allowMultipleSelection);
                            }
                            break;
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11568)) {
                    WPSnackbar.make(findViewById(R.id.editor_activity), R.string.media_error_no_permission_upload, Snackbar.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!ListenerUtil.mutListener.listen(11580)) {
            super.onCreateOptionsMenu(menu);
        }
        MenuInflater inflater = getMenuInflater();
        if (!ListenerUtil.mutListener.listen(11581)) {
            inflater.inflate(R.menu.edit_post, menu);
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean showMenuItems = true;
        if (!ListenerUtil.mutListener.listen(11589)) {
            if ((ListenerUtil.mutListener.listen(11587) ? (mViewPager != null || (ListenerUtil.mutListener.listen(11586) ? (mViewPager.getCurrentItem() >= PAGE_CONTENT) : (ListenerUtil.mutListener.listen(11585) ? (mViewPager.getCurrentItem() <= PAGE_CONTENT) : (ListenerUtil.mutListener.listen(11584) ? (mViewPager.getCurrentItem() < PAGE_CONTENT) : (ListenerUtil.mutListener.listen(11583) ? (mViewPager.getCurrentItem() != PAGE_CONTENT) : (ListenerUtil.mutListener.listen(11582) ? (mViewPager.getCurrentItem() == PAGE_CONTENT) : (mViewPager.getCurrentItem() > PAGE_CONTENT))))))) : (mViewPager != null && (ListenerUtil.mutListener.listen(11586) ? (mViewPager.getCurrentItem() >= PAGE_CONTENT) : (ListenerUtil.mutListener.listen(11585) ? (mViewPager.getCurrentItem() <= PAGE_CONTENT) : (ListenerUtil.mutListener.listen(11584) ? (mViewPager.getCurrentItem() < PAGE_CONTENT) : (ListenerUtil.mutListener.listen(11583) ? (mViewPager.getCurrentItem() != PAGE_CONTENT) : (ListenerUtil.mutListener.listen(11582) ? (mViewPager.getCurrentItem() == PAGE_CONTENT) : (mViewPager.getCurrentItem() > PAGE_CONTENT))))))))) {
                if (!ListenerUtil.mutListener.listen(11588)) {
                    showMenuItems = false;
                }
            }
        }
        MenuItem secondaryAction = menu.findItem(R.id.menu_secondary_action);
        MenuItem previewMenuItem = menu.findItem(R.id.menu_preview_post);
        MenuItem viewHtmlModeMenuItem = menu.findItem(R.id.menu_html_mode);
        MenuItem historyMenuItem = menu.findItem(R.id.menu_history);
        MenuItem settingsMenuItem = menu.findItem(R.id.menu_post_settings);
        MenuItem helpMenuItem = menu.findItem(R.id.menu_editor_help);
        if (!ListenerUtil.mutListener.listen(11594)) {
            if ((ListenerUtil.mutListener.listen(11590) ? (secondaryAction != null || mEditPostRepository.hasPost()) : (secondaryAction != null && mEditPostRepository.hasPost()))) {
                if (!ListenerUtil.mutListener.listen(11592)) {
                    secondaryAction.setVisible((ListenerUtil.mutListener.listen(11591) ? (showMenuItems || getSecondaryAction().isVisible()) : (showMenuItems && getSecondaryAction().isVisible())));
                }
                if (!ListenerUtil.mutListener.listen(11593)) {
                    secondaryAction.setTitle(getSecondaryActionText());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11596)) {
            if (previewMenuItem != null) {
                if (!ListenerUtil.mutListener.listen(11595)) {
                    previewMenuItem.setVisible(showMenuItems);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11601)) {
            if (viewHtmlModeMenuItem != null) {
                if (!ListenerUtil.mutListener.listen(11599)) {
                    viewHtmlModeMenuItem.setVisible((ListenerUtil.mutListener.listen(11598) ? (((ListenerUtil.mutListener.listen(11597) ? ((mEditorFragment instanceof AztecEditorFragment) && (mEditorFragment instanceof GutenbergEditorFragment)) : ((mEditorFragment instanceof AztecEditorFragment) || (mEditorFragment instanceof GutenbergEditorFragment)))) || showMenuItems) : (((ListenerUtil.mutListener.listen(11597) ? ((mEditorFragment instanceof AztecEditorFragment) && (mEditorFragment instanceof GutenbergEditorFragment)) : ((mEditorFragment instanceof AztecEditorFragment) || (mEditorFragment instanceof GutenbergEditorFragment)))) && showMenuItems)));
                }
                if (!ListenerUtil.mutListener.listen(11600)) {
                    viewHtmlModeMenuItem.setTitle(mHtmlModeMenuStateOn ? R.string.menu_visual_mode : R.string.menu_html_mode);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11605)) {
            if (historyMenuItem != null) {
                boolean hasHistory = (ListenerUtil.mutListener.listen(11602) ? (!mIsNewPost || mSite.isUsingWpComRestApi()) : (!mIsNewPost && mSite.isUsingWpComRestApi()));
                if (!ListenerUtil.mutListener.listen(11604)) {
                    historyMenuItem.setVisible((ListenerUtil.mutListener.listen(11603) ? (showMenuItems || hasHistory) : (showMenuItems && hasHistory)));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11608)) {
            if (settingsMenuItem != null) {
                if (!ListenerUtil.mutListener.listen(11606)) {
                    settingsMenuItem.setTitle(mIsPage ? R.string.page_settings : R.string.post_settings);
                }
                if (!ListenerUtil.mutListener.listen(11607)) {
                    settingsMenuItem.setVisible(showMenuItems);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11614)) {
            // Set text of the primary action button in the ActionBar
            if (mEditPostRepository.hasPost()) {
                MenuItem primaryAction = menu.findItem(R.id.menu_primary_action);
                if (!ListenerUtil.mutListener.listen(11613)) {
                    if (primaryAction != null) {
                        if (!ListenerUtil.mutListener.listen(11609)) {
                            primaryAction.setTitle(getPrimaryActionText());
                        }
                        if (!ListenerUtil.mutListener.listen(11612)) {
                            primaryAction.setVisible((ListenerUtil.mutListener.listen(11611) ? ((ListenerUtil.mutListener.listen(11610) ? (mViewPager != null || mViewPager.getCurrentItem() != PAGE_HISTORY) : (mViewPager != null && mViewPager.getCurrentItem() != PAGE_HISTORY)) || mViewPager.getCurrentItem() != PAGE_PUBLISH_SETTINGS) : ((ListenerUtil.mutListener.listen(11610) ? (mViewPager != null || mViewPager.getCurrentItem() != PAGE_HISTORY) : (mViewPager != null && mViewPager.getCurrentItem() != PAGE_HISTORY)) && mViewPager.getCurrentItem() != PAGE_PUBLISH_SETTINGS)));
                        }
                    }
                }
            }
        }
        MenuItem switchToGutenbergMenuItem = menu.findItem(R.id.menu_switch_to_gutenberg);
        if (!ListenerUtil.mutListener.listen(11616)) {
            // (see https://github.com/wordpress-mobile/WordPress-Android/issues/9748 for more information)
            if (switchToGutenbergMenuItem != null) {
                boolean switchToGutenbergVisibility = mShowGutenbergEditor ? false : shouldSwitchToGutenbergBeVisible(mEditorFragment, mSite);
                if (!ListenerUtil.mutListener.listen(11615)) {
                    switchToGutenbergMenuItem.setVisible(switchToGutenbergVisibility);
                }
            }
        }
        MenuItem contentInfo = menu.findItem(R.id.menu_content_info);
        if (!ListenerUtil.mutListener.listen(11619)) {
            if (mEditorFragment instanceof GutenbergEditorFragment) {
                if (!ListenerUtil.mutListener.listen(11618)) {
                    contentInfo.setOnMenuItemClickListener((menuItem) -> {
                        try {
                            mEditorFragment.showContentInfo();
                        } catch (EditorFragmentNotAddedException e) {
                            ToastUtils.showToast(WordPress.getContext(), R.string.toast_content_info_failed);
                        }
                        return true;
                    });
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11617)) {
                    // only show the menu item when for Gutenberg
                    contentInfo.setVisible(false);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11624)) {
            if (helpMenuItem != null) {
                if (!ListenerUtil.mutListener.listen(11623)) {
                    if ((ListenerUtil.mutListener.listen(11620) ? (mEditorFragment instanceof GutenbergEditorFragment || showMenuItems) : (mEditorFragment instanceof GutenbergEditorFragment && showMenuItems))) {
                        if (!ListenerUtil.mutListener.listen(11622)) {
                            helpMenuItem.setVisible(true);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(11621)) {
                            helpMenuItem.setVisible(false);
                        }
                    }
                }
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!ListenerUtil.mutListener.listen(11625)) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
        boolean allGranted = WPPermissionUtils.setPermissionListAsked(this, requestCode, permissions, grantResults, true);
        if (!ListenerUtil.mutListener.listen(11632)) {
            if (allGranted) {
                if (!ListenerUtil.mutListener.listen(11631)) {
                    switch(requestCode) {
                        case WPPermissionUtils.EDITOR_MEDIA_PERMISSION_REQUEST_CODE:
                            if (!ListenerUtil.mutListener.listen(11628)) {
                                if (mMenuView != null) {
                                    if (!ListenerUtil.mutListener.listen(11626)) {
                                        super.openContextMenu(mMenuView);
                                    }
                                    if (!ListenerUtil.mutListener.listen(11627)) {
                                        mMenuView = null;
                                    }
                                }
                            }
                            break;
                        case WPPermissionUtils.EDITOR_DRAG_DROP_PERMISSION_REQUEST_CODE:
                            if (!ListenerUtil.mutListener.listen(11629)) {
                                mEditorMedia.addNewMediaItemsToEditorAsync(mEditorMedia.getDroppedMediaUris(), false);
                            }
                            if (!ListenerUtil.mutListener.listen(11630)) {
                                mEditorMedia.getDroppedMediaUris().clear();
                            }
                            break;
                    }
                }
            }
        }
    }

    private boolean handleBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(ImageSettingsDialogFragment.IMAGE_SETTINGS_DIALOG_TAG);
        if (!ListenerUtil.mutListener.listen(11636)) {
            if ((ListenerUtil.mutListener.listen(11633) ? (fragment != null || fragment.isVisible()) : (fragment != null && fragment.isVisible()))) {
                if (!ListenerUtil.mutListener.listen(11635)) {
                    if (fragment instanceof ImageSettingsDialogFragment) {
                        ImageSettingsDialogFragment imFragment = (ImageSettingsDialogFragment) fragment;
                        if (!ListenerUtil.mutListener.listen(11634)) {
                            imFragment.dismissFragment();
                        }
                    }
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(11651)) {
            if (mViewPager.getCurrentItem() == PAGE_PUBLISH_SETTINGS) {
                if (!ListenerUtil.mutListener.listen(11649)) {
                    mViewPager.setCurrentItem(PAGE_SETTINGS);
                }
                if (!ListenerUtil.mutListener.listen(11650)) {
                    invalidateOptionsMenu();
                }
            } else if ((ListenerUtil.mutListener.listen(11641) ? (mViewPager.getCurrentItem() >= PAGE_CONTENT) : (ListenerUtil.mutListener.listen(11640) ? (mViewPager.getCurrentItem() <= PAGE_CONTENT) : (ListenerUtil.mutListener.listen(11639) ? (mViewPager.getCurrentItem() < PAGE_CONTENT) : (ListenerUtil.mutListener.listen(11638) ? (mViewPager.getCurrentItem() != PAGE_CONTENT) : (ListenerUtil.mutListener.listen(11637) ? (mViewPager.getCurrentItem() == PAGE_CONTENT) : (mViewPager.getCurrentItem() > PAGE_CONTENT))))))) {
                if (!ListenerUtil.mutListener.listen(11646)) {
                    if (mViewPager.getCurrentItem() == PAGE_SETTINGS) {
                        if (!ListenerUtil.mutListener.listen(11645)) {
                            mEditorFragment.setFeaturedImageId(mEditPostRepository.getFeaturedImageId());
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(11647)) {
                    mViewPager.setCurrentItem(PAGE_CONTENT);
                }
                if (!ListenerUtil.mutListener.listen(11648)) {
                    invalidateOptionsMenu();
                }
            } else if (mEditorPhotoPicker.isPhotoPickerShowing()) {
                if (!ListenerUtil.mutListener.listen(11644)) {
                    mEditorPhotoPicker.hidePhotoPicker();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11643)) {
                    performWhenNoStoriesBeingSaved(new DoWhenNoStoriesBeingSavedCallback() {

                        @Override
                        public void doWhenNoStoriesBeingSaved() {
                            if (!ListenerUtil.mutListener.listen(11642)) {
                                savePostAndOptionallyFinish(true, false);
                            }
                        }
                    });
                }
            }
        }
        return true;
    }

    interface DoWhenNoStoriesBeingSavedCallback {

        void doWhenNoStoriesBeingSaved();
    }

    private void performWhenNoStoriesBeingSaved(DoWhenNoStoriesBeingSavedCallback callback) {
        if (!ListenerUtil.mutListener.listen(11654)) {
            if (mStoriesEventListener.getStoriesSavingInProgress().isEmpty()) {
                if (!ListenerUtil.mutListener.listen(11653)) {
                    callback.doWhenNoStoriesBeingSaved();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11652)) {
                    // Oops! A story is still being saved, let's wait
                    ToastUtils.showToast(EditPostActivity.this, getString(R.string.toast_edit_story_update_in_progress_title));
                }
            }
        }
    }

    private RemotePreviewLogicHelper.RemotePreviewHelperFunctions getEditPostActivityStrategyFunctions() {
        return new RemotePreviewLogicHelper.RemotePreviewHelperFunctions() {

            @Override
            public boolean notifyUploadInProgress(@NotNull PostImmutableModel post) {
                if (UploadService.hasInProgressMediaUploadsForPost(post)) {
                    if (!ListenerUtil.mutListener.listen(11655)) {
                        ToastUtils.showToast(EditPostActivity.this, getString(R.string.editor_toast_uploading_please_wait), Duration.SHORT);
                    }
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public void notifyEmptyDraft() {
                if (!ListenerUtil.mutListener.listen(11656)) {
                    ToastUtils.showToast(EditPostActivity.this, getString(R.string.error_preview_empty_draft), Duration.SHORT);
                }
            }

            @Override
            public void startUploading(boolean isRemoteAutoSave, @Nullable PostImmutableModel post) {
                if (!ListenerUtil.mutListener.listen(11661)) {
                    if (isRemoteAutoSave) {
                        if (!ListenerUtil.mutListener.listen(11659)) {
                            updatePostLoadingAndDialogState(PostLoadingState.REMOTE_AUTO_SAVING_FOR_PREVIEW, post);
                        }
                        if (!ListenerUtil.mutListener.listen(11660)) {
                            savePostAndOptionallyFinish(false, true);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(11657)) {
                            updatePostLoadingAndDialogState(PostLoadingState.UPLOADING_FOR_PREVIEW, post);
                        }
                        if (!ListenerUtil.mutListener.listen(11658)) {
                            savePostAndOptionallyFinish(false, false);
                        }
                    }
                }
            }

            @Override
            public void notifyEmptyPost() {
                String message = getString(mIsPage ? R.string.error_preview_empty_page : R.string.error_preview_empty_post);
                if (!ListenerUtil.mutListener.listen(11662)) {
                    ToastUtils.showToast(EditPostActivity.this, message, Duration.SHORT);
                }
            }
        };
    }

    // Menu actions
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        int itemId = item.getItemId();
        if (!ListenerUtil.mutListener.listen(11663)) {
            if (itemId == android.R.id.home) {
                return handleBackPressed();
            }
        }
        if (!ListenerUtil.mutListener.listen(11664)) {
            mEditorPhotoPicker.hidePhotoPicker();
        }
        if (!ListenerUtil.mutListener.listen(11692)) {
            if (itemId == R.id.menu_primary_action) {
                if (!ListenerUtil.mutListener.listen(11691)) {
                    performPrimaryAction();
                }
            } else {
                if (!ListenerUtil.mutListener.listen(11669)) {
                    // (unnecessary for Aztec since it supports progress reattachment)
                    if ((ListenerUtil.mutListener.listen(11667) ? (!((ListenerUtil.mutListener.listen(11665) ? (mShowAztecEditor && mShowGutenbergEditor) : (mShowAztecEditor || mShowGutenbergEditor))) || ((ListenerUtil.mutListener.listen(11666) ? (mEditorFragment.isUploadingMedia() && mEditorFragment.isActionInProgress()) : (mEditorFragment.isUploadingMedia() || mEditorFragment.isActionInProgress())))) : (!((ListenerUtil.mutListener.listen(11665) ? (mShowAztecEditor && mShowGutenbergEditor) : (mShowAztecEditor || mShowGutenbergEditor))) && ((ListenerUtil.mutListener.listen(11666) ? (mEditorFragment.isUploadingMedia() && mEditorFragment.isActionInProgress()) : (mEditorFragment.isUploadingMedia() || mEditorFragment.isActionInProgress())))))) {
                        if (!ListenerUtil.mutListener.listen(11668)) {
                            ToastUtils.showToast(this, R.string.editor_toast_uploading_please_wait, Duration.SHORT);
                        }
                        return false;
                    }
                }
                if (!ListenerUtil.mutListener.listen(11690)) {
                    if (itemId == R.id.menu_history) {
                        if (!ListenerUtil.mutListener.listen(11687)) {
                            AnalyticsTracker.track(Stat.REVISIONS_LIST_VIEWED);
                        }
                        if (!ListenerUtil.mutListener.listen(11688)) {
                            ActivityUtils.hideKeyboard(this);
                        }
                        if (!ListenerUtil.mutListener.listen(11689)) {
                            mViewPager.setCurrentItem(PAGE_HISTORY);
                        }
                    } else if (itemId == R.id.menu_preview_post) {
                        if (!ListenerUtil.mutListener.listen(11686)) {
                            if (!showPreview()) {
                                return false;
                            }
                        }
                    } else if (itemId == R.id.menu_post_settings) {
                        if (!ListenerUtil.mutListener.listen(11683)) {
                            if (mEditPostSettingsFragment != null) {
                                if (!ListenerUtil.mutListener.listen(11682)) {
                                    mEditPostSettingsFragment.refreshViews();
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(11684)) {
                            ActivityUtils.hideKeyboard(this);
                        }
                        if (!ListenerUtil.mutListener.listen(11685)) {
                            mViewPager.setCurrentItem(PAGE_SETTINGS);
                        }
                    } else if (itemId == R.id.menu_secondary_action) {
                        return performSecondaryAction();
                    } else if (itemId == R.id.menu_html_mode) {
                        if (!ListenerUtil.mutListener.listen(11681)) {
                            // toggle HTML mode
                            if (mEditorFragment instanceof AztecEditorFragment) {
                                if (!ListenerUtil.mutListener.listen(11680)) {
                                    ((AztecEditorFragment) mEditorFragment).onToolbarHtmlButtonClicked();
                                }
                            } else if (mEditorFragment instanceof GutenbergEditorFragment) {
                                if (!ListenerUtil.mutListener.listen(11679)) {
                                    ((GutenbergEditorFragment) mEditorFragment).onToggleHtmlMode();
                                }
                            }
                        }
                    } else if (itemId == R.id.menu_switch_to_gutenberg) {
                        if (!ListenerUtil.mutListener.listen(11678)) {
                            // (see https://github.com/wordpress-mobile/WordPress-Android/issues/9748 for more information)
                            if (shouldSwitchToGutenbergBeVisible(mEditorFragment, mSite)) {
                                if (!ListenerUtil.mutListener.listen(11674)) {
                                    // let's finish this editing instance and start again, but let GB be used
                                    mRestartEditorOption = RestartEditorOptions.RESTART_DONT_SUPPRESS_GUTENBERG;
                                }
                                if (!ListenerUtil.mutListener.listen(11675)) {
                                    mPostEditorAnalyticsSession.switchEditor(Editor.GUTENBERG);
                                }
                                if (!ListenerUtil.mutListener.listen(11676)) {
                                    mPostEditorAnalyticsSession.setOutcome(Outcome.SAVE);
                                }
                                if (!ListenerUtil.mutListener.listen(11677)) {
                                    mViewModel.finish(ActivityFinishState.SAVED_LOCALLY);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(11673)) {
                                    logWrongMenuState("Wrong state in menu_switch_to_gutenberg: menu should not be visible.");
                                }
                            }
                        }
                    } else if (itemId == R.id.menu_editor_help) {
                        if (!ListenerUtil.mutListener.listen(11672)) {
                            // Display the editor help page -- option should only be available in the GutenbergEditor
                            if (mEditorFragment instanceof GutenbergEditorFragment) {
                                if (!ListenerUtil.mutListener.listen(11670)) {
                                    mAnalyticsTrackerWrapper.track(Stat.EDITOR_HELP_SHOWN, mSite);
                                }
                                if (!ListenerUtil.mutListener.listen(11671)) {
                                    ((GutenbergEditorFragment) mEditorFragment).showEditorHelp();
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private void logWrongMenuState(String logMsg) {
        if (!ListenerUtil.mutListener.listen(11693)) {
            AppLog.w(T.EDITOR, logMsg);
        }
    }

    private void showEmptyPostErrorForSecondaryAction() {
        String message = getString(mIsPage ? R.string.error_publish_empty_page : R.string.error_publish_empty_post);
        if (!ListenerUtil.mutListener.listen(11696)) {
            if ((ListenerUtil.mutListener.listen(11694) ? (getSecondaryAction() == SecondaryEditorAction.SAVE_AS_DRAFT && getSecondaryAction() == SecondaryEditorAction.SAVE) : (getSecondaryAction() == SecondaryEditorAction.SAVE_AS_DRAFT || getSecondaryAction() == SecondaryEditorAction.SAVE))) {
                if (!ListenerUtil.mutListener.listen(11695)) {
                    message = getString(R.string.error_save_empty_draft);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11697)) {
            ToastUtils.showToast(EditPostActivity.this, message, Duration.SHORT);
        }
    }

    private void saveAsDraft() {
        if (!ListenerUtil.mutListener.listen(11698)) {
            mEditPostSettingsFragment.updatePostStatus(PostStatus.DRAFT);
        }
        if (!ListenerUtil.mutListener.listen(11699)) {
            ToastUtils.showToast(EditPostActivity.this, getString(R.string.editor_post_converted_back_to_draft), Duration.SHORT);
        }
        if (!ListenerUtil.mutListener.listen(11700)) {
            mUploadUtilsWrapper.showSnackbar(findViewById(R.id.editor_activity), R.string.editor_uploading_post);
        }
        if (!ListenerUtil.mutListener.listen(11701)) {
            savePostAndOptionallyFinish(false, false);
        }
    }

    private boolean performSecondaryAction() {
        if (!ListenerUtil.mutListener.listen(11703)) {
            if (UploadService.hasInProgressMediaUploadsForPost(mEditPostRepository.getPost())) {
                if (!ListenerUtil.mutListener.listen(11702)) {
                    ToastUtils.showToast(EditPostActivity.this, getString(R.string.editor_toast_uploading_please_wait), Duration.SHORT);
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(11705)) {
            if (isDiscardable()) {
                if (!ListenerUtil.mutListener.listen(11704)) {
                    showEmptyPostErrorForSecondaryAction();
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(11711)) {
            switch(getSecondaryAction()) {
                case SAVE_AS_DRAFT:
                    if (!ListenerUtil.mutListener.listen(11706)) {
                        // Force the new Draft status
                        saveAsDraft();
                    }
                    return true;
                case SAVE:
                    if (!ListenerUtil.mutListener.listen(11707)) {
                        uploadPost(false);
                    }
                    return true;
                case PUBLISH_NOW:
                    if (!ListenerUtil.mutListener.listen(11708)) {
                        mAnalyticsTrackerWrapper.track(Stat.EDITOR_POST_PUBLISH_TAPPED);
                    }
                    if (!ListenerUtil.mutListener.listen(11709)) {
                        mPublishPostImmediatelyUseCase.updatePostToPublishImmediately(mEditPostRepository, mIsNewPost);
                    }
                    if (!ListenerUtil.mutListener.listen(11710)) {
                        checkNoStorySaveOperationInProgressAndShowPrepublishingNudgeBottomSheet();
                    }
                    return true;
                case NONE:
                    throw new IllegalStateException("Switch in `secondaryAction` shouldn't go through the NONE case");
            }
        }
        return false;
    }

    private void refreshEditorContent() {
        if (!ListenerUtil.mutListener.listen(11712)) {
            mHasSetPostContent = false;
        }
        if (!ListenerUtil.mutListener.listen(11713)) {
            fillContentEditorFields();
        }
    }

    private void setPreviewingInEditorSticky(boolean enable, @Nullable PostImmutableModel post) {
        if (!ListenerUtil.mutListener.listen(11718)) {
            if (enable) {
                if (!ListenerUtil.mutListener.listen(11717)) {
                    if (post != null) {
                        if (!ListenerUtil.mutListener.listen(11716)) {
                            EventBus.getDefault().postSticky(new PostEvents.PostPreviewingInEditor(post.getLocalSiteId(), post.getId()));
                        }
                    }
                }
            } else {
                PostEvents.PostPreviewingInEditor stickyEvent = EventBus.getDefault().getStickyEvent(PostEvents.PostPreviewingInEditor.class);
                if (!ListenerUtil.mutListener.listen(11715)) {
                    if (stickyEvent != null) {
                        if (!ListenerUtil.mutListener.listen(11714)) {
                            EventBus.getDefault().removeStickyEvent(stickyEvent);
                        }
                    }
                }
            }
        }
    }

    private void managePostLoadingStateTransitions(PostLoadingState postLoadingState, @Nullable PostImmutableModel post) {
        if (!ListenerUtil.mutListener.listen(11721)) {
            switch(postLoadingState) {
                case NONE:
                    if (!ListenerUtil.mutListener.listen(11719)) {
                        setPreviewingInEditorSticky(false, post);
                    }
                    break;
                case UPLOADING_FOR_PREVIEW:
                case REMOTE_AUTO_SAVING_FOR_PREVIEW:
                case PREVIEWING:
                case REMOTE_AUTO_SAVE_PREVIEW_ERROR:
                    if (!ListenerUtil.mutListener.listen(11720)) {
                        setPreviewingInEditorSticky(true, post);
                    }
                    break;
                case LOADING_REVISION:
                    // nothing to do
                    break;
            }
        }
    }

    private void updatePostLoadingAndDialogState(PostLoadingState postLoadingState) {
        if (!ListenerUtil.mutListener.listen(11722)) {
            updatePostLoadingAndDialogState(postLoadingState, null);
        }
    }

    private void updatePostLoadingAndDialogState(PostLoadingState postLoadingState, @Nullable PostImmutableModel post) {
        if (!ListenerUtil.mutListener.listen(11723)) {
            // We need only transitions, so...
            if (mPostLoadingState == postLoadingState)
                return;
        }
        if (!ListenerUtil.mutListener.listen(11724)) {
            AppLog.d(AppLog.T.POSTS, "Editor post loading state machine: transition from " + mPostLoadingState + " to " + postLoadingState);
        }
        if (!ListenerUtil.mutListener.listen(11725)) {
            // update the state
            mPostLoadingState = postLoadingState;
        }
        if (!ListenerUtil.mutListener.listen(11726)) {
            // take care of exit actions on state transition
            managePostLoadingStateTransitions(postLoadingState, post);
        }
        if (!ListenerUtil.mutListener.listen(11727)) {
            // update the progress dialog state
            mProgressDialog = mProgressDialogHelper.updateProgressDialogState(this, mProgressDialog, mPostLoadingState.getProgressDialogUiState(), mUiHelpers);
        }
    }

    private void toggleHtmlModeOnMenu() {
        if (!ListenerUtil.mutListener.listen(11728)) {
            mHtmlModeMenuStateOn = !mHtmlModeMenuStateOn;
        }
        if (!ListenerUtil.mutListener.listen(11729)) {
            trackPostSessionEditorModeSwitch();
        }
        if (!ListenerUtil.mutListener.listen(11730)) {
            invalidateOptionsMenu();
        }
        if (!ListenerUtil.mutListener.listen(11731)) {
            showEditorModeSwitchedNotice();
        }
    }

    private void showEditorModeSwitchedNotice() {
        String message = getString(mHtmlModeMenuStateOn ? R.string.menu_html_mode_switched_notice : R.string.menu_visual_mode_switched_notice);
        if (!ListenerUtil.mutListener.listen(11732)) {
            mEditorFragment.showNotice(message);
        }
    }

    private void trackPostSessionEditorModeSwitch() {
        boolean isGutenberg = mEditorFragment instanceof GutenbergEditorFragment;
        if (!ListenerUtil.mutListener.listen(11733)) {
            mPostEditorAnalyticsSession.switchEditor(mHtmlModeMenuStateOn ? Editor.HTML : (isGutenberg ? Editor.GUTENBERG : Editor.CLASSIC));
        }
    }

    private void performPrimaryAction() {
        if (!ListenerUtil.mutListener.listen(11738)) {
            switch(getPrimaryAction()) {
                case PUBLISH_NOW:
                    if (!ListenerUtil.mutListener.listen(11734)) {
                        mAnalyticsTrackerWrapper.track(Stat.EDITOR_POST_PUBLISH_TAPPED);
                    }
                    if (!ListenerUtil.mutListener.listen(11735)) {
                        checkNoStorySaveOperationInProgressAndShowPrepublishingNudgeBottomSheet();
                    }
                    return;
                case UPDATE:
                case CONTINUE:
                case SCHEDULE:
                case SUBMIT_FOR_REVIEW:
                    if (!ListenerUtil.mutListener.listen(11736)) {
                        checkNoStorySaveOperationInProgressAndShowPrepublishingNudgeBottomSheet();
                    }
                    return;
                case SAVE:
                    if (!ListenerUtil.mutListener.listen(11737)) {
                        uploadPost(false);
                    }
                    break;
            }
        }
    }

    private void showGutenbergInformativeDialog() {
        if (!ListenerUtil.mutListener.listen(11739)) {
            AppPrefs.setGutenbergInfoPopupDisplayed(mSite.getUrl(), true);
        }
    }

    private void showGutenbergRolloutV2InformativeDialog() {
        if (!ListenerUtil.mutListener.listen(11740)) {
            AppPrefs.setGutenbergInfoPopupDisplayed(mSite.getUrl(), true);
        }
    }

    private void setGutenbergEnabledIfNeeded() {
        if (!ListenerUtil.mutListener.listen(11741)) {
            if (AppPrefs.isGutenbergInfoPopupDisplayed(mSite.getUrl())) {
                return;
            }
        }
        boolean showPopup = AppPrefs.shouldShowGutenbergInfoPopupForTheNewPosts(mSite.getUrl());
        boolean showRolloutPopupPhase2 = AppPrefs.shouldShowGutenbergInfoPopupPhase2ForNewPosts(mSite.getUrl());
        if (!ListenerUtil.mutListener.listen(11745)) {
            if ((ListenerUtil.mutListener.listen(11742) ? (TextUtils.isEmpty(mSite.getMobileEditor()) || !mIsNewPost) : (TextUtils.isEmpty(mSite.getMobileEditor()) && !mIsNewPost))) {
                if (!ListenerUtil.mutListener.listen(11743)) {
                    SiteUtils.enableBlockEditor(mDispatcher, mSite);
                }
                if (!ListenerUtil.mutListener.listen(11744)) {
                    AnalyticsUtils.trackWithSiteDetails(Stat.EDITOR_GUTENBERG_ENABLED, mSite, BlockEditorEnabledSource.ON_BLOCK_POST_OPENING.asPropertyMap());
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11748)) {
            if (showPopup) {
                if (!ListenerUtil.mutListener.listen(11747)) {
                    showGutenbergInformativeDialog();
                }
            } else if (showRolloutPopupPhase2) {
                if (!ListenerUtil.mutListener.listen(11746)) {
                    showGutenbergRolloutV2InformativeDialog();
                }
            }
        }
    }

    private ActivityFinishState savePostOnline(boolean isFirstTimePublish) {
        return mViewModel.savePostOnline(isFirstTimePublish, this, mEditPostRepository, mSite);
    }

    private void onUploadSuccess(MediaModel media) {
        if (!ListenerUtil.mutListener.listen(11756)) {
            if (media != null) {
                if (!ListenerUtil.mutListener.listen(11755)) {
                    // TODO Should this statement check media.getLocalPostId() == mEditPostRepository.getId()?
                    if ((ListenerUtil.mutListener.listen(11749) ? (!media.getMarkedLocallyAsFeatured() || mEditorMediaUploadListener != null) : (!media.getMarkedLocallyAsFeatured() && mEditorMediaUploadListener != null))) {
                        if (!ListenerUtil.mutListener.listen(11752)) {
                            mEditorMediaUploadListener.onMediaUploadSucceeded(String.valueOf(media.getId()), FluxCUtils.mediaFileFromMediaModel(media));
                        }
                        if (!ListenerUtil.mutListener.listen(11754)) {
                            if (PostUtils.contentContainsWPStoryGutenbergBlocks(mEditPostRepository.getContent())) {
                                if (!ListenerUtil.mutListener.listen(11753)) {
                                    // then post the event for StoriesEventListener to process
                                    updateAndSavePostAsync(updatePostResult -> mStoriesEventListener.postStoryMediaUploadedEvent(media));
                                }
                            }
                        }
                    } else if ((ListenerUtil.mutListener.listen(11750) ? (media.getMarkedLocallyAsFeatured() || media.getLocalPostId() == mEditPostRepository.getId()) : (media.getMarkedLocallyAsFeatured() && media.getLocalPostId() == mEditPostRepository.getId()))) {
                        if (!ListenerUtil.mutListener.listen(11751)) {
                            setFeaturedImageId(media.getMediaId(), false, false);
                        }
                    }
                }
            }
        }
    }

    private void onUploadProgress(MediaModel media, float progress) {
        String localMediaId = String.valueOf(media.getId());
        if (!ListenerUtil.mutListener.listen(11758)) {
            if (mEditorMediaUploadListener != null) {
                if (!ListenerUtil.mutListener.listen(11757)) {
                    mEditorMediaUploadListener.onMediaUploadProgress(localMediaId, progress);
                }
            }
        }
    }

    private void launchPictureLibrary() {
        if (!ListenerUtil.mutListener.listen(11759)) {
            WPMediaUtils.launchPictureLibrary(this, mEditorPhotoPicker.getAllowMultipleSelection());
        }
    }

    private void launchVideoLibrary() {
        if (!ListenerUtil.mutListener.listen(11760)) {
            WPMediaUtils.launchVideoLibrary(this, mEditorPhotoPicker.getAllowMultipleSelection());
        }
    }

    private void launchVideoCamera() {
        if (!ListenerUtil.mutListener.listen(11761)) {
            WPMediaUtils.launchVideoCamera(this);
        }
    }

    private void showErrorAndFinish(int errorMessageId) {
        if (!ListenerUtil.mutListener.listen(11762)) {
            ToastUtils.showToast(this, errorMessageId, ToastUtils.Duration.LONG);
        }
        if (!ListenerUtil.mutListener.listen(11763)) {
            finish();
        }
    }

    private void updateAndSavePostAsync() {
        if (!ListenerUtil.mutListener.listen(11765)) {
            if (mEditorFragment == null) {
                if (!ListenerUtil.mutListener.listen(11764)) {
                    AppLog.e(AppLog.T.POSTS, "Fragment not initialized");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(11766)) {
            mViewModel.updatePostObjectWithUIAsync(mEditPostRepository, this::updateFromEditor, null);
        }
    }

    private void updateAndSavePostAsync(final OnPostUpdatedFromUIListener listener) {
        if (!ListenerUtil.mutListener.listen(11768)) {
            if (mEditorFragment == null) {
                if (!ListenerUtil.mutListener.listen(11767)) {
                    AppLog.e(AppLog.T.POSTS, "Fragment not initialized");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(11769)) {
            mViewModel.updatePostObjectWithUIAsync(mEditPostRepository, this::updateFromEditor, (post, result) -> {
                mViewModel.setSavingPostOnEditorExit(false);
                // Ignore the result as we want to invoke the listener even when the PostModel was up-to-date
                if (listener != null) {
                    listener.onPostUpdatedFromUI(result);
                }
                return null;
            });
        }
    }

    /**
     * This method:
     *   1. Shows and hides the editor's progress dialog;
     *   2. Saves the post via {@link EditPostActivity#updateAndSavePostAsync(OnPostUpdatedFromUIListener)};
     *   3. Invokes the listener method parameter
     */
    private void updateAndSavePostAsyncOnEditorExit(@NonNull final OnPostUpdatedFromUIListener listener) {
        if (!ListenerUtil.mutListener.listen(11770)) {
            if (mEditorFragment == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(11771)) {
            mViewModel.setSavingPostOnEditorExit(true);
        }
        if (!ListenerUtil.mutListener.listen(11772)) {
            mViewModel.showSavingProgressDialog();
        }
        if (!ListenerUtil.mutListener.listen(11773)) {
            updateAndSavePostAsync((result) -> listener.onPostUpdatedFromUI(result));
        }
    }

    private UpdateFromEditor updateFromEditor(String oldContent) {
        try {
            // To reduce redundant bridge events emitted to the Gutenberg editor, we get title and content at once
            Pair<CharSequence, CharSequence> titleAndContent = mEditorFragment.getTitleAndContent(oldContent);
            String title = (String) titleAndContent.first;
            String content = (String) titleAndContent.second;
            return new PostFields(title, content);
        } catch (EditorFragmentNotAddedException e) {
            if (!ListenerUtil.mutListener.listen(11774)) {
                AppLog.e(T.EDITOR, "Impossible to save the post, we weren't able to update it.");
            }
            return new UpdateFromEditor.Failed(e);
        }
    }

    @Override
    public void initializeEditorFragment() {
        if (!ListenerUtil.mutListener.listen(11798)) {
            if (mEditorFragment instanceof AztecEditorFragment) {
                AztecEditorFragment aztecEditorFragment = (AztecEditorFragment) mEditorFragment;
                if (!ListenerUtil.mutListener.listen(11775)) {
                    aztecEditorFragment.setEditorImageSettingsListener(EditPostActivity.this);
                }
                if (!ListenerUtil.mutListener.listen(11776)) {
                    aztecEditorFragment.setMediaToolbarButtonClickListener(mEditorPhotoPicker);
                }
                Drawable loadingImagePlaceholder = EditorMediaUtils.getAztecPlaceholderDrawableFromResID(this, org.wordpress.android.editor.R.drawable.ic_gridicons_image, aztecEditorFragment.getMaxMediaSize());
                if (!ListenerUtil.mutListener.listen(11777)) {
                    mAztecImageLoader = new AztecImageLoader(getBaseContext(), mImageManager, loadingImagePlaceholder);
                }
                if (!ListenerUtil.mutListener.listen(11778)) {
                    aztecEditorFragment.setAztecImageLoader(mAztecImageLoader);
                }
                if (!ListenerUtil.mutListener.listen(11779)) {
                    aztecEditorFragment.setLoadingImagePlaceholder(loadingImagePlaceholder);
                }
                Drawable loadingVideoPlaceholder = EditorMediaUtils.getAztecPlaceholderDrawableFromResID(this, org.wordpress.android.editor.R.drawable.ic_gridicons_video_camera, aztecEditorFragment.getMaxMediaSize());
                if (!ListenerUtil.mutListener.listen(11780)) {
                    aztecEditorFragment.setAztecVideoLoader(new AztecVideoLoader(getBaseContext(), loadingVideoPlaceholder));
                }
                if (!ListenerUtil.mutListener.listen(11781)) {
                    aztecEditorFragment.setLoadingVideoPlaceholder(loadingVideoPlaceholder);
                }
                if (!ListenerUtil.mutListener.listen(11785)) {
                    if ((ListenerUtil.mutListener.listen(11783) ? ((ListenerUtil.mutListener.listen(11782) ? (getSite() != null || getSite().isWPCom()) : (getSite() != null && getSite().isWPCom())) || !getSite().isPrivate()) : ((ListenerUtil.mutListener.listen(11782) ? (getSite() != null || getSite().isWPCom()) : (getSite() != null && getSite().isWPCom())) && !getSite().isPrivate()))) {
                        if (!ListenerUtil.mutListener.listen(11784)) {
                            // Add the content reporting for wpcom blogs that are not private
                            aztecEditorFragment.enableContentLogOnCrashes(throwable -> {
                                // Do not log private or password protected post
                                return mEditPostRepository.hasPost() && TextUtils.isEmpty(mEditPostRepository.getPassword()) && !mEditPostRepository.hasStatus(PostStatus.PRIVATE);
                            });
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(11788)) {
                    if ((ListenerUtil.mutListener.listen(11786) ? (mEditPostRepository.hasPost() || AppPrefs.isPostWithHWAccelerationOff(mEditPostRepository.getLocalSiteId(), mEditPostRepository.getId())) : (mEditPostRepository.hasPost() && AppPrefs.isPostWithHWAccelerationOff(mEditPostRepository.getLocalSiteId(), mEditPostRepository.getId())))) {
                        if (!ListenerUtil.mutListener.listen(11787)) {
                            // We need to disable HW Acc. on this post
                            aztecEditorFragment.disableHWAcceleration();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(11797)) {
                    aztecEditorFragment.setExternalLogger(new AztecLog.ExternalLogger() {

                        // prefs to disable HW acceleration for it.
                        private boolean isError8828(@NotNull Throwable throwable) {
                            if (!ListenerUtil.mutListener.listen(11789)) {
                                if (!(throwable instanceof DynamicLayoutGetBlockIndexOutOfBoundsException)) {
                                    return false;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(11790)) {
                                if (!mEditPostRepository.hasPost()) {
                                    return false;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(11791)) {
                                AppPrefs.addPostWithHWAccelerationOff(mEditPostRepository.getLocalSiteId(), mEditPostRepository.getId());
                            }
                            return true;
                        }

                        @Override
                        public void log(@NotNull String s) {
                            if (!ListenerUtil.mutListener.listen(11792)) {
                                AppLog.e(T.EDITOR, s);
                            }
                        }

                        @Override
                        public void logException(@NotNull Throwable throwable) {
                            if (!ListenerUtil.mutListener.listen(11793)) {
                                if (isError8828(throwable)) {
                                    return;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(11794)) {
                                AppLog.e(T.EDITOR, throwable);
                            }
                        }

                        @Override
                        public void logException(@NotNull Throwable throwable, String s) {
                            if (!ListenerUtil.mutListener.listen(11795)) {
                                if (isError8828(throwable)) {
                                    return;
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(11796)) {
                                AppLog.e(T.EDITOR, s);
                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    public void onImageSettingsRequested(EditorImageMetaData editorImageMetaData) {
        if (!ListenerUtil.mutListener.listen(11799)) {
            MediaSettingsActivity.showForResult(this, mSite, editorImageMetaData);
        }
    }

    @Override
    public void onImagePreviewRequested(String mediaUrl) {
        if (!ListenerUtil.mutListener.listen(11800)) {
            MediaPreviewActivity.showPreview(this, mSite, mediaUrl);
        }
    }

    @Override
    public void onMediaEditorRequested(String mediaUrl) {
        String imageUrl = UrlUtils.removeQuery(StringUtils.notNullStr(mediaUrl));
        // device's max width to display a smaller image that can load faster and act as a placeholder.
        int displayWidth = Math.max(DisplayUtils.getWindowPixelWidth(getBaseContext()), DisplayUtils.getWindowPixelHeight(getBaseContext()));
        int margin = getResources().getDimensionPixelSize(R.dimen.preview_image_view_margin);
        int maxWidth = (ListenerUtil.mutListener.listen(11808) ? (displayWidth % ((ListenerUtil.mutListener.listen(11804) ? (margin % 2) : (ListenerUtil.mutListener.listen(11803) ? (margin / 2) : (ListenerUtil.mutListener.listen(11802) ? (margin - 2) : (ListenerUtil.mutListener.listen(11801) ? (margin + 2) : (margin * 2))))))) : (ListenerUtil.mutListener.listen(11807) ? (displayWidth / ((ListenerUtil.mutListener.listen(11804) ? (margin % 2) : (ListenerUtil.mutListener.listen(11803) ? (margin / 2) : (ListenerUtil.mutListener.listen(11802) ? (margin - 2) : (ListenerUtil.mutListener.listen(11801) ? (margin + 2) : (margin * 2))))))) : (ListenerUtil.mutListener.listen(11806) ? (displayWidth * ((ListenerUtil.mutListener.listen(11804) ? (margin % 2) : (ListenerUtil.mutListener.listen(11803) ? (margin / 2) : (ListenerUtil.mutListener.listen(11802) ? (margin - 2) : (ListenerUtil.mutListener.listen(11801) ? (margin + 2) : (margin * 2))))))) : (ListenerUtil.mutListener.listen(11805) ? (displayWidth + ((ListenerUtil.mutListener.listen(11804) ? (margin % 2) : (ListenerUtil.mutListener.listen(11803) ? (margin / 2) : (ListenerUtil.mutListener.listen(11802) ? (margin - 2) : (ListenerUtil.mutListener.listen(11801) ? (margin + 2) : (margin * 2))))))) : (displayWidth - ((ListenerUtil.mutListener.listen(11804) ? (margin % 2) : (ListenerUtil.mutListener.listen(11803) ? (margin / 2) : (ListenerUtil.mutListener.listen(11802) ? (margin - 2) : (ListenerUtil.mutListener.listen(11801) ? (margin + 2) : (margin * 2)))))))))));
        int reducedSizeWidth = (int) ((ListenerUtil.mutListener.listen(11812) ? (maxWidth % PREVIEW_IMAGE_REDUCED_SIZE_FACTOR) : (ListenerUtil.mutListener.listen(11811) ? (maxWidth / PREVIEW_IMAGE_REDUCED_SIZE_FACTOR) : (ListenerUtil.mutListener.listen(11810) ? (maxWidth - PREVIEW_IMAGE_REDUCED_SIZE_FACTOR) : (ListenerUtil.mutListener.listen(11809) ? (maxWidth + PREVIEW_IMAGE_REDUCED_SIZE_FACTOR) : (maxWidth * PREVIEW_IMAGE_REDUCED_SIZE_FACTOR))))));
        String resizedImageUrl = mReaderUtilsWrapper.getResizedImageUrl(mediaUrl, reducedSizeWidth, 0, !SiteUtils.isPhotonCapable(mSite), mSite.isWPComAtomic());
        String outputFileExtension = MimeTypeMap.getFileExtensionFromUrl(imageUrl);
        ArrayList<EditImageData.InputData> inputData = new ArrayList<>(1);
        if (!ListenerUtil.mutListener.listen(11813)) {
            inputData.add(new EditImageData.InputData(imageUrl, StringUtils.notNullStr(resizedImageUrl), outputFileExtension));
        }
        if (!ListenerUtil.mutListener.listen(11814)) {
            ActivityLauncher.openImageEditor(this, inputData);
        }
    }

    /*
     * user clicked OK on a settings list dialog displayed from the settings fragment - pass the event
     * along to the settings fragment
     */
    @Override
    public void onPostSettingsFragmentPositiveButtonClicked(@NonNull PostSettingsListDialogFragment dialog) {
        if (!ListenerUtil.mutListener.listen(11816)) {
            if (mEditPostSettingsFragment != null) {
                if (!ListenerUtil.mutListener.listen(11815)) {
                    mEditPostSettingsFragment.onPostSettingsFragmentPositiveButtonClicked(dialog);
                }
            }
        }
    }

    public interface OnPostUpdatedFromUIListener {

        void onPostUpdatedFromUI(@Nullable UpdatePostResult updatePostResult);
    }

    @Override
    public void onBackPressed() {
        if (!ListenerUtil.mutListener.listen(11817)) {
            handleBackPressed();
        }
    }

    @Override
    public void onHistoryItemClicked(@NonNull Revision revision, @NonNull List<Revision> revisions) {
        if (!ListenerUtil.mutListener.listen(11818)) {
            AnalyticsTracker.track(Stat.REVISIONS_DETAIL_VIEWED_FROM_LIST);
        }
        if (!ListenerUtil.mutListener.listen(11819)) {
            mRevision = revision;
        }
        final long postId = mEditPostRepository.getRemotePostId();
        if (!ListenerUtil.mutListener.listen(11820)) {
            ActivityLauncher.viewHistoryDetailForResult(this, mRevision, getRevisionsIds(revisions), postId, mSite.getSiteId());
        }
    }

    private long[] getRevisionsIds(@NonNull final List<Revision> revisions) {
        final long[] idsArray = new long[revisions.size()];
        if (!ListenerUtil.mutListener.listen(11827)) {
            {
                long _loopCounter200 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(11826) ? (i >= revisions.size()) : (ListenerUtil.mutListener.listen(11825) ? (i <= revisions.size()) : (ListenerUtil.mutListener.listen(11824) ? (i > revisions.size()) : (ListenerUtil.mutListener.listen(11823) ? (i != revisions.size()) : (ListenerUtil.mutListener.listen(11822) ? (i == revisions.size()) : (i < revisions.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter200", ++_loopCounter200);
                    final Revision current = revisions.get(i);
                    if (!ListenerUtil.mutListener.listen(11821)) {
                        idsArray[i] = current.getRevisionId();
                    }
                }
            }
        }
        return idsArray;
    }

    private void loadRevision() {
        if (!ListenerUtil.mutListener.listen(11828)) {
            updatePostLoadingAndDialogState(PostLoadingState.LOADING_REVISION);
        }
        if (!ListenerUtil.mutListener.listen(11829)) {
            mEditPostRepository.saveForUndo();
        }
        if (!ListenerUtil.mutListener.listen(11830)) {
            mEditPostRepository.updateAsync(postModel -> {
                postModel.setTitle(Objects.requireNonNull(mRevision.getPostTitle()));
                postModel.setContent(Objects.requireNonNull(mRevision.getPostContent()));
                return true;
            }, (postModel, result) -> {
                if (result == UpdatePostResult.Updated.INSTANCE) {
                    refreshEditorContent();
                    WPSnackbar.make(mViewPager, getString(R.string.history_loaded_revision), 4000).setAction(getString(R.string.undo), view -> {
                        AnalyticsTracker.track(Stat.REVISIONS_LOAD_UNDONE);
                        RemotePostPayload payload = new RemotePostPayload(mEditPostRepository.getPostForUndo(), mSite);
                        mDispatcher.dispatch(PostActionBuilder.newFetchPostAction(payload));
                        mEditPostRepository.undo();
                        refreshEditorContent();
                    }).show();
                    updatePostLoadingAndDialogState(PostLoadingState.NONE);
                }
                return null;
            });
        }
    }

    private boolean isNewPost() {
        return mIsNewPost;
    }

    private void saveResult(boolean saved, boolean uploadNotStarted) {
        Intent i = getIntent();
        if (!ListenerUtil.mutListener.listen(11831)) {
            i.putExtra(EXTRA_UPLOAD_NOT_STARTED, uploadNotStarted);
        }
        if (!ListenerUtil.mutListener.listen(11832)) {
            i.putExtra(EXTRA_HAS_FAILED_MEDIA, hasFailedMedia());
        }
        if (!ListenerUtil.mutListener.listen(11833)) {
            i.putExtra(EXTRA_IS_PAGE, mIsPage);
        }
        if (!ListenerUtil.mutListener.listen(11834)) {
            i.putExtra(EXTRA_IS_LANDING_EDITOR, mIsLandingEditor);
        }
        if (!ListenerUtil.mutListener.listen(11835)) {
            i.putExtra(EXTRA_HAS_CHANGES, saved);
        }
        if (!ListenerUtil.mutListener.listen(11836)) {
            i.putExtra(EXTRA_POST_LOCAL_ID, mEditPostRepository.getId());
        }
        if (!ListenerUtil.mutListener.listen(11837)) {
            i.putExtra(EXTRA_POST_REMOTE_ID, mEditPostRepository.getRemotePostId());
        }
        if (!ListenerUtil.mutListener.listen(11838)) {
            i.putExtra(EXTRA_RESTART_EDITOR, mRestartEditorOption.name());
        }
        if (!ListenerUtil.mutListener.listen(11839)) {
            i.putExtra(STATE_KEY_EDITOR_SESSION_DATA, mPostEditorAnalyticsSession);
        }
        if (!ListenerUtil.mutListener.listen(11840)) {
            i.putExtra(EXTRA_IS_NEW_POST, mIsNewPost);
        }
        if (!ListenerUtil.mutListener.listen(11841)) {
            setResult(RESULT_OK, i);
        }
    }

    private void setupPrepublishingBottomSheetRunnable() {
        if (!ListenerUtil.mutListener.listen(11842)) {
            mShowPrepublishingBottomSheetHandler = new Handler();
        }
        if (!ListenerUtil.mutListener.listen(11843)) {
            mShowPrepublishingBottomSheetRunnable = () -> {
                Fragment fragment = getSupportFragmentManager().findFragmentByTag(PrepublishingBottomSheetFragment.TAG);
                if (fragment == null) {
                    PrepublishingBottomSheetFragment prepublishingFragment = PrepublishingBottomSheetFragment.newInstance(getSite(), mIsPage, false);
                    prepublishingFragment.show(getSupportFragmentManager(), PrepublishingBottomSheetFragment.TAG);
                }
            };
        }
    }

    private void checkNoStorySaveOperationInProgressAndShowPrepublishingNudgeBottomSheet() {
        if (!ListenerUtil.mutListener.listen(11845)) {
            performWhenNoStoriesBeingSaved(new DoWhenNoStoriesBeingSavedCallback() {

                @Override
                public void doWhenNoStoriesBeingSaved() {
                    if (!ListenerUtil.mutListener.listen(11844)) {
                        showPrepublishingNudgeBottomSheet();
                    }
                }
            });
        }
    }

    private void showPrepublishingNudgeBottomSheet() {
        if (!ListenerUtil.mutListener.listen(11846)) {
            mViewPager.setCurrentItem(PAGE_CONTENT);
        }
        if (!ListenerUtil.mutListener.listen(11847)) {
            ActivityUtils.hideKeyboard(this);
        }
        long delayMs = 100;
        if (!ListenerUtil.mutListener.listen(11848)) {
            mShowPrepublishingBottomSheetHandler.postDelayed(mShowPrepublishingBottomSheetRunnable, delayMs);
        }
    }

    @Override
    public void onSubmitButtonClicked(boolean publishPost) {
        if (!ListenerUtil.mutListener.listen(11849)) {
            uploadPost(publishPost);
        }
        if (!ListenerUtil.mutListener.listen(11851)) {
            if (publishPost) {
                if (!ListenerUtil.mutListener.listen(11850)) {
                    AppRatingDialog.INSTANCE.incrementInteractions(APP_REVIEWS_EVENT_INCREMENTED_BY_PUBLISHING_POST_OR_PAGE);
                }
            }
        }
    }

    private void uploadPost(final boolean publishPost) {
        if (!ListenerUtil.mutListener.listen(11852)) {
            updateAndSavePostAsyncOnEditorExit(((updatePostResult) -> {
                AccountModel account = mAccountStore.getAccount();
                // prompt user to verify e-mail before publishing
                if (!account.getEmailVerified()) {
                    mViewModel.hideSavingProgressDialog();
                    String message = TextUtils.isEmpty(account.getEmail()) ? getString(R.string.editor_confirm_email_prompt_message) : String.format(getString(R.string.editor_confirm_email_prompt_message_with_email), account.getEmail());
                    AlertDialog.Builder builder = new MaterialAlertDialogBuilder(this);
                    builder.setTitle(R.string.editor_confirm_email_prompt_title).setMessage(message).setPositiveButton(android.R.string.ok, (dialog, id) -> {
                        ToastUtils.showToast(EditPostActivity.this, getString(R.string.toast_saving_post_as_draft));
                        savePostAndOptionallyFinish(true, false);
                    }).setNegativeButton(R.string.editor_confirm_email_prompt_negative, (dialog, id) -> mDispatcher.dispatch(AccountActionBuilder.newSendVerificationEmailAction()));
                    builder.create().show();
                    return;
                }
                if (!mPostUtils.isPublishable(mEditPostRepository.getPost())) {
                    mViewModel.hideSavingProgressDialog();
                    // TODO we don't want to show "publish" message when the user clicked on eg. save
                    mEditPostRepository.updateStatusFromPostSnapshotWhenEditorOpened();
                    EditPostActivity.this.runOnUiThread(() -> {
                        String message = getString(mIsPage ? R.string.error_publish_empty_page : R.string.error_publish_empty_post);
                        ToastUtils.showToast(EditPostActivity.this, message, Duration.SHORT);
                    });
                    return;
                }
                mViewModel.showSavingProgressDialog();
                boolean isFirstTimePublish = isFirstTimePublish(publishPost);
                mEditPostRepository.updateAsync(postModel -> {
                    if (publishPost) {
                        // also re-set the published date in case it was SCHEDULED and they want to publish NOW
                        if (postModel.getStatus().equals(PostStatus.SCHEDULED.toString())) {
                            postModel.setDateCreated(mDateTimeUtils.currentTimeInIso8601());
                        }
                        if (mUploadUtilsWrapper.userCanPublish(getSite())) {
                            postModel.setStatus(PostStatus.PUBLISHED.toString());
                        } else {
                            postModel.setStatus(PostStatus.PENDING.toString());
                        }
                        mPostEditorAnalyticsSession.setOutcome(Outcome.PUBLISH);
                    } else {
                        mPostEditorAnalyticsSession.setOutcome(Outcome.SAVE);
                    }
                    AppLog.d(T.POSTS, "User explicitly confirmed changes. Post Title: " + postModel.getTitle());
                    // the user explicitly confirmed an intention to upload the post
                    postModel.setChangesConfirmedContentHashcode(postModel.contentHashcode());
                    return true;
                }, (postModel, result) -> {
                    if (result == Updated.INSTANCE) {
                        ActivityFinishState activityFinishState = savePostOnline(isFirstTimePublish);
                        mViewModel.finish(activityFinishState);
                    }
                    return null;
                });
            }));
        }
    }

    private void savePostAndOptionallyFinish(final boolean doFinish, final boolean forceSave) {
        if (!ListenerUtil.mutListener.listen(11855)) {
            if ((ListenerUtil.mutListener.listen(11853) ? (mEditorFragment == null && !mEditorFragment.isAdded()) : (mEditorFragment == null || !mEditorFragment.isAdded()))) {
                if (!ListenerUtil.mutListener.listen(11854)) {
                    AppLog.e(AppLog.T.POSTS, "Fragment not initialized");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(11856)) {
            updateAndSavePostAsyncOnEditorExit(((updatePostResult) -> {
                // check if the opened post had some unsaved local changes
                boolean isFirstTimePublish = isFirstTimePublish(false);
                // if post was modified during this editing session, save it
                boolean shouldSave = shouldSavePost() || forceSave;
                mPostEditorAnalyticsSession.setOutcome(Outcome.SAVE);
                ActivityFinishState activityFinishState = ActivityFinishState.CANCELLED;
                if (shouldSave) {
                    /*
                 * Remote-auto-save isn't supported on self-hosted sites. We can save the post online (as draft)
                 * only when it doesn't exist in the remote yet. When it does exist in the remote, we can upload
                 * it only when the user explicitly confirms the changes - eg. clicks on save/publish/submit. The
                 * user didn't confirm the changes in this code path.
                 */
                    boolean isWpComOrIsLocalDraft = mSite.isUsingWpComRestApi() || mEditPostRepository.isLocalDraft();
                    if (isWpComOrIsLocalDraft) {
                        activityFinishState = savePostOnline(isFirstTimePublish);
                    } else if (forceSave) {
                        activityFinishState = savePostOnline(false);
                    } else {
                        activityFinishState = ActivityFinishState.SAVED_LOCALLY;
                    }
                }
                // discard post if new & empty
                if (isDiscardable()) {
                    mDispatcher.dispatch(PostActionBuilder.newRemovePostAction(mEditPostRepository.getEditablePost()));
                    mPostEditorAnalyticsSession.setOutcome(Outcome.CANCEL);
                    activityFinishState = ActivityFinishState.CANCELLED;
                }
                if (doFinish) {
                    mViewModel.finish(activityFinishState);
                }
            }));
        }
    }

    private boolean shouldSavePost() {
        boolean hasChanges = mEditPostRepository.postWasChangedInCurrentSession();
        boolean isPublishable = mEditPostRepository.isPostPublishable();
        boolean existingPostWithChanges = (ListenerUtil.mutListener.listen(11857) ? (mEditPostRepository.hasPostSnapshotWhenEditorOpened() || hasChanges) : (mEditPostRepository.hasPostSnapshotWhenEditorOpened() && hasChanges));
        // if post was modified during this editing session, save it
        return (ListenerUtil.mutListener.listen(11859) ? (isPublishable || ((ListenerUtil.mutListener.listen(11858) ? (existingPostWithChanges && isNewPost()) : (existingPostWithChanges || isNewPost())))) : (isPublishable && ((ListenerUtil.mutListener.listen(11858) ? (existingPostWithChanges && isNewPost()) : (existingPostWithChanges || isNewPost())))));
    }

    private boolean isDiscardable() {
        return (ListenerUtil.mutListener.listen(11860) ? (!mEditPostRepository.isPostPublishable() || isNewPost()) : (!mEditPostRepository.isPostPublishable() && isNewPost()));
    }

    private boolean isFirstTimePublish(final boolean publishPost) {
        final PostStatus originalStatus = mEditPostRepository.getStatus();
        return (ListenerUtil.mutListener.listen(11868) ? ((ListenerUtil.mutListener.listen(11866) ? ((ListenerUtil.mutListener.listen(11864) ? (((ListenerUtil.mutListener.listen(11862) ? (((ListenerUtil.mutListener.listen(11861) ? (originalStatus == PostStatus.DRAFT && originalStatus == PostStatus.UNKNOWN) : (originalStatus == PostStatus.DRAFT || originalStatus == PostStatus.UNKNOWN))) || publishPost) : (((ListenerUtil.mutListener.listen(11861) ? (originalStatus == PostStatus.DRAFT && originalStatus == PostStatus.UNKNOWN) : (originalStatus == PostStatus.DRAFT || originalStatus == PostStatus.UNKNOWN))) && publishPost))) && ((ListenerUtil.mutListener.listen(11863) ? (originalStatus == PostStatus.SCHEDULED || publishPost) : (originalStatus == PostStatus.SCHEDULED && publishPost)))) : (((ListenerUtil.mutListener.listen(11862) ? (((ListenerUtil.mutListener.listen(11861) ? (originalStatus == PostStatus.DRAFT && originalStatus == PostStatus.UNKNOWN) : (originalStatus == PostStatus.DRAFT || originalStatus == PostStatus.UNKNOWN))) || publishPost) : (((ListenerUtil.mutListener.listen(11861) ? (originalStatus == PostStatus.DRAFT && originalStatus == PostStatus.UNKNOWN) : (originalStatus == PostStatus.DRAFT || originalStatus == PostStatus.UNKNOWN))) && publishPost))) || ((ListenerUtil.mutListener.listen(11863) ? (originalStatus == PostStatus.SCHEDULED || publishPost) : (originalStatus == PostStatus.SCHEDULED && publishPost))))) && ((ListenerUtil.mutListener.listen(11865) ? (originalStatus == PostStatus.PUBLISHED || mEditPostRepository.isLocalDraft()) : (originalStatus == PostStatus.PUBLISHED && mEditPostRepository.isLocalDraft())))) : ((ListenerUtil.mutListener.listen(11864) ? (((ListenerUtil.mutListener.listen(11862) ? (((ListenerUtil.mutListener.listen(11861) ? (originalStatus == PostStatus.DRAFT && originalStatus == PostStatus.UNKNOWN) : (originalStatus == PostStatus.DRAFT || originalStatus == PostStatus.UNKNOWN))) || publishPost) : (((ListenerUtil.mutListener.listen(11861) ? (originalStatus == PostStatus.DRAFT && originalStatus == PostStatus.UNKNOWN) : (originalStatus == PostStatus.DRAFT || originalStatus == PostStatus.UNKNOWN))) && publishPost))) && ((ListenerUtil.mutListener.listen(11863) ? (originalStatus == PostStatus.SCHEDULED || publishPost) : (originalStatus == PostStatus.SCHEDULED && publishPost)))) : (((ListenerUtil.mutListener.listen(11862) ? (((ListenerUtil.mutListener.listen(11861) ? (originalStatus == PostStatus.DRAFT && originalStatus == PostStatus.UNKNOWN) : (originalStatus == PostStatus.DRAFT || originalStatus == PostStatus.UNKNOWN))) || publishPost) : (((ListenerUtil.mutListener.listen(11861) ? (originalStatus == PostStatus.DRAFT && originalStatus == PostStatus.UNKNOWN) : (originalStatus == PostStatus.DRAFT || originalStatus == PostStatus.UNKNOWN))) && publishPost))) || ((ListenerUtil.mutListener.listen(11863) ? (originalStatus == PostStatus.SCHEDULED || publishPost) : (originalStatus == PostStatus.SCHEDULED && publishPost))))) || ((ListenerUtil.mutListener.listen(11865) ? (originalStatus == PostStatus.PUBLISHED || mEditPostRepository.isLocalDraft()) : (originalStatus == PostStatus.PUBLISHED && mEditPostRepository.isLocalDraft()))))) && ((ListenerUtil.mutListener.listen(11867) ? (originalStatus == PostStatus.PUBLISHED || mEditPostRepository.getRemotePostId() == 0) : (originalStatus == PostStatus.PUBLISHED && mEditPostRepository.getRemotePostId() == 0)))) : ((ListenerUtil.mutListener.listen(11866) ? ((ListenerUtil.mutListener.listen(11864) ? (((ListenerUtil.mutListener.listen(11862) ? (((ListenerUtil.mutListener.listen(11861) ? (originalStatus == PostStatus.DRAFT && originalStatus == PostStatus.UNKNOWN) : (originalStatus == PostStatus.DRAFT || originalStatus == PostStatus.UNKNOWN))) || publishPost) : (((ListenerUtil.mutListener.listen(11861) ? (originalStatus == PostStatus.DRAFT && originalStatus == PostStatus.UNKNOWN) : (originalStatus == PostStatus.DRAFT || originalStatus == PostStatus.UNKNOWN))) && publishPost))) && ((ListenerUtil.mutListener.listen(11863) ? (originalStatus == PostStatus.SCHEDULED || publishPost) : (originalStatus == PostStatus.SCHEDULED && publishPost)))) : (((ListenerUtil.mutListener.listen(11862) ? (((ListenerUtil.mutListener.listen(11861) ? (originalStatus == PostStatus.DRAFT && originalStatus == PostStatus.UNKNOWN) : (originalStatus == PostStatus.DRAFT || originalStatus == PostStatus.UNKNOWN))) || publishPost) : (((ListenerUtil.mutListener.listen(11861) ? (originalStatus == PostStatus.DRAFT && originalStatus == PostStatus.UNKNOWN) : (originalStatus == PostStatus.DRAFT || originalStatus == PostStatus.UNKNOWN))) && publishPost))) || ((ListenerUtil.mutListener.listen(11863) ? (originalStatus == PostStatus.SCHEDULED || publishPost) : (originalStatus == PostStatus.SCHEDULED && publishPost))))) && ((ListenerUtil.mutListener.listen(11865) ? (originalStatus == PostStatus.PUBLISHED || mEditPostRepository.isLocalDraft()) : (originalStatus == PostStatus.PUBLISHED && mEditPostRepository.isLocalDraft())))) : ((ListenerUtil.mutListener.listen(11864) ? (((ListenerUtil.mutListener.listen(11862) ? (((ListenerUtil.mutListener.listen(11861) ? (originalStatus == PostStatus.DRAFT && originalStatus == PostStatus.UNKNOWN) : (originalStatus == PostStatus.DRAFT || originalStatus == PostStatus.UNKNOWN))) || publishPost) : (((ListenerUtil.mutListener.listen(11861) ? (originalStatus == PostStatus.DRAFT && originalStatus == PostStatus.UNKNOWN) : (originalStatus == PostStatus.DRAFT || originalStatus == PostStatus.UNKNOWN))) && publishPost))) && ((ListenerUtil.mutListener.listen(11863) ? (originalStatus == PostStatus.SCHEDULED || publishPost) : (originalStatus == PostStatus.SCHEDULED && publishPost)))) : (((ListenerUtil.mutListener.listen(11862) ? (((ListenerUtil.mutListener.listen(11861) ? (originalStatus == PostStatus.DRAFT && originalStatus == PostStatus.UNKNOWN) : (originalStatus == PostStatus.DRAFT || originalStatus == PostStatus.UNKNOWN))) || publishPost) : (((ListenerUtil.mutListener.listen(11861) ? (originalStatus == PostStatus.DRAFT && originalStatus == PostStatus.UNKNOWN) : (originalStatus == PostStatus.DRAFT || originalStatus == PostStatus.UNKNOWN))) && publishPost))) || ((ListenerUtil.mutListener.listen(11863) ? (originalStatus == PostStatus.SCHEDULED || publishPost) : (originalStatus == PostStatus.SCHEDULED && publishPost))))) || ((ListenerUtil.mutListener.listen(11865) ? (originalStatus == PostStatus.PUBLISHED || mEditPostRepository.isLocalDraft()) : (originalStatus == PostStatus.PUBLISHED && mEditPostRepository.isLocalDraft()))))) || ((ListenerUtil.mutListener.listen(11867) ? (originalStatus == PostStatus.PUBLISHED || mEditPostRepository.getRemotePostId() == 0) : (originalStatus == PostStatus.PUBLISHED && mEditPostRepository.getRemotePostId() == 0)))));
    }

    /**
     * Can be dropped and replaced by mEditorFragment.hasFailedMediaUploads() when we drop the visual editor.
     * mEditorFragment.isActionInProgress() was added to address a timing issue when adding media and immediately
     * publishing or exiting the visual editor. It's not safe to upload the post in this state.
     * See https://github.com/wordpress-mobile/WordPress-Editor-Android/issues/294
     */
    private boolean hasFailedMedia() {
        return (ListenerUtil.mutListener.listen(11869) ? (mEditorFragment.hasFailedMediaUploads() && mEditorFragment.isActionInProgress()) : (mEditorFragment.hasFailedMediaUploads() || mEditorFragment.isActionInProgress()));
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private static final int NUM_PAGES_EDITOR = 4;

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            switch(position) {
                case PAGE_CONTENT:
                    if (mShowGutenbergEditor) {
                        if (!ListenerUtil.mutListener.listen(11870)) {
                            // the GB editor the first time when the remote setting value is still null
                            setGutenbergEnabledIfNeeded();
                        }
                        if (!ListenerUtil.mutListener.listen(11871)) {
                            mXpostsCapabilityChecker.retrieveCapability(mSite, EditPostActivity.this::onXpostsSettingsCapability);
                        }
                        boolean isWpCom = (ListenerUtil.mutListener.listen(11873) ? ((ListenerUtil.mutListener.listen(11872) ? (getSite().isWPCom() && mSite.isPrivateWPComAtomic()) : (getSite().isWPCom() || mSite.isPrivateWPComAtomic())) && mSite.isWPComAtomic()) : ((ListenerUtil.mutListener.listen(11872) ? (getSite().isWPCom() && mSite.isPrivateWPComAtomic()) : (getSite().isWPCom() || mSite.isPrivateWPComAtomic())) || mSite.isWPComAtomic()));
                        GutenbergPropsBuilder gutenbergPropsBuilder = getGutenbergPropsBuilder();
                        GutenbergWebViewAuthorizationData gutenbergWebViewAuthorizationData = new GutenbergWebViewAuthorizationData(mSite.getUrl(), isWpCom, mAccountStore.getAccount().getUserId(), mAccountStore.getAccount().getUserName(), mAccountStore.getAccessToken(), mSite.getSelfHostedSiteId(), mSite.getUsername(), mSite.getPassword(), mSite.isUsingWpComRestApi(), mSite.getWebEditor(), WordPress.getUserAgent(), mIsJetpackSsoEnabled);
                        return GutenbergEditorFragment.newInstance("", "", mIsNewPost, gutenbergWebViewAuthorizationData, gutenbergPropsBuilder, RequestCodes.EDIT_STORY);
                    } else {
                        // If gutenberg editor is not selected, default to Aztec.
                        return AztecEditorFragment.newInstance("", "", AppPrefs.isAztecEditorToolbarExpanded());
                    }
                case PAGE_SETTINGS:
                    return EditPostSettingsFragment.newInstance();
                case PAGE_PUBLISH_SETTINGS:
                    return EditPostPublishSettingsFragment.Companion.newInstance();
                case PAGE_HISTORY:
                    return HistoryListFragment.Companion.newInstance(mEditPostRepository.getId(), mSite);
                default:
                    throw new IllegalArgumentException("Unexpected page type");
            }
        }

        @Override
        @NotNull
        public Object instantiateItem(@NotNull ViewGroup container, int position) {
            Fragment fragment = (Fragment) super.instantiateItem(container, position);
            if (!ListenerUtil.mutListener.listen(11884)) {
                switch(position) {
                    case PAGE_CONTENT:
                        if (!ListenerUtil.mutListener.listen(11874)) {
                            mEditorFragment = (EditorFragmentAbstract) fragment;
                        }
                        if (!ListenerUtil.mutListener.listen(11875)) {
                            mEditorFragment.setImageLoader(mImageLoader);
                        }
                        if (!ListenerUtil.mutListener.listen(11876)) {
                            mEditorFragment.getTitleOrContentChanged().observe(EditPostActivity.this, editable -> {
                                mViewModel.savePostWithDelay();
                            });
                        }
                        if (!ListenerUtil.mutListener.listen(11880)) {
                            if (mEditorFragment instanceof EditorMediaUploadListener) {
                                if (!ListenerUtil.mutListener.listen(11877)) {
                                    mEditorMediaUploadListener = (EditorMediaUploadListener) mEditorFragment;
                                }
                                if (!ListenerUtil.mutListener.listen(11878)) {
                                    // Set up custom headers for the visual editor's internal WebView
                                    mEditorFragment.setCustomHttpHeader("User-Agent", WordPress.getUserAgent());
                                }
                                if (!ListenerUtil.mutListener.listen(11879)) {
                                    reattachUploadingMediaForAztec();
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(11882)) {
                            if (mEditorFragment instanceof StorySaveMediaListener) {
                                if (!ListenerUtil.mutListener.listen(11881)) {
                                    mStoriesEventListener.setSaveMediaListener((StorySaveMediaListener) mEditorFragment);
                                }
                            }
                        }
                        break;
                    case PAGE_SETTINGS:
                        if (!ListenerUtil.mutListener.listen(11883)) {
                            mEditPostSettingsFragment = (EditPostSettingsFragment) fragment;
                        }
                        break;
                }
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return NUM_PAGES_EDITOR;
        }
    }

    private void onXpostsSettingsCapability(boolean isXpostsCapable) {
        if (!ListenerUtil.mutListener.listen(11885)) {
            mIsXPostsCapable = isXpostsCapable;
        }
        if (!ListenerUtil.mutListener.listen(11887)) {
            if (mEditorFragment instanceof GutenbergEditorFragment) {
                if (!ListenerUtil.mutListener.listen(11886)) {
                    ((GutenbergEditorFragment) mEditorFragment).updateCapabilities(getGutenbergPropsBuilder());
                }
            }
        }
    }

    private GutenbergPropsBuilder getGutenbergPropsBuilder() {
        String postType = mIsPage ? "page" : "post";
        int featuredImageId = (int) mEditPostRepository.getFeaturedImageId();
        String languageString = LocaleManager.getLanguage(EditPostActivity.this);
        String wpcomLocaleSlug = languageString.replace("_", "-").toLowerCase(Locale.ENGLISH);
        // If this.mIsXPostsCapable has not been set, default to allowing xPosts.
        boolean enableXPosts = (ListenerUtil.mutListener.listen(11889) ? (mSite.isUsingWpComRestApi() || ((ListenerUtil.mutListener.listen(11888) ? (mIsXPostsCapable == null && mIsXPostsCapable) : (mIsXPostsCapable == null || mIsXPostsCapable)))) : (mSite.isUsingWpComRestApi() && ((ListenerUtil.mutListener.listen(11888) ? (mIsXPostsCapable == null && mIsXPostsCapable) : (mIsXPostsCapable == null || mIsXPostsCapable)))));
        EditorTheme editorTheme = mEditorThemeStore.getEditorThemeForSite(mSite);
        Bundle themeBundle = (editorTheme != null) ? editorTheme.getThemeSupport().toBundle() : null;
        boolean isUnsupportedBlockEditorEnabled = (ListenerUtil.mutListener.listen(11890) ? (mSite.isWPCom() && mIsJetpackSsoEnabled) : (mSite.isWPCom() || mIsJetpackSsoEnabled));
        boolean unsupportedBlockEditorSwitch = (ListenerUtil.mutListener.listen(11891) ? (mSite.isJetpackConnected() || !mIsJetpackSsoEnabled) : (mSite.isJetpackConnected() && !mIsJetpackSsoEnabled));
        boolean isFreeWPCom = (ListenerUtil.mutListener.listen(11892) ? (mSite.isWPCom() || SiteUtils.onFreePlan(mSite)) : (mSite.isWPCom() && SiteUtils.onFreePlan(mSite)));
        boolean isWPComSite = (ListenerUtil.mutListener.listen(11893) ? (mSite.isWPCom() && mSite.isWPComAtomic()) : (mSite.isWPCom() || mSite.isWPComAtomic()));
        return new GutenbergPropsBuilder(SiteUtils.supportsContactInfoFeature(mSite), SiteUtils.supportsLayoutGridFeature(mSite), SiteUtils.supportsTiledGalleryFeature(mSite), SiteUtils.supportsEmbedVariationFeature(mSite, SiteUtils.WP_FACEBOOK_EMBED_JETPACK_VERSION), SiteUtils.supportsEmbedVariationFeature(mSite, SiteUtils.WP_INSTAGRAM_EMBED_JETPACK_VERSION), SiteUtils.supportsEmbedVariationFeature(mSite, SiteUtils.WP_LOOM_EMBED_JETPACK_VERSION), SiteUtils.supportsEmbedVariationFeature(mSite, SiteUtils.WP_SMARTFRAME_EMBED_JETPACK_VERSION), SiteUtils.supportsStoriesFeature(mSite), mSite.isUsingWpComRestApi(), enableXPosts, isUnsupportedBlockEditorEnabled, unsupportedBlockEditorSwitch, !isFreeWPCom, isWPComSite, wpcomLocaleSlug, postType, featuredImageId, themeBundle);
    }

    /**
     * Checks if the theme supports the new gallery block with image blocks.
     * Note that if the editor theme has not been initialized (usually on the first app run)
     * the value returned is null and the `unstable_gallery_with_image_blocks` analytics property will not be reported.
     * @return true if the the supports the new gallery block with image blocks or null if the theme is not initialized.
     */
    private Boolean themeSupportsGalleryWithImageBlocks() {
        EditorTheme editorTheme = mEditorThemeStore.getEditorThemeForSite(mSite);
        if (!ListenerUtil.mutListener.listen(11894)) {
            if (editorTheme == null) {
                return null;
            }
        }
        return editorTheme.getThemeSupport().getGalleryWithImageBlocks();
    }

    // Moved from EditPostContentFragment
    public static final String NEW_MEDIA_POST = "NEW_MEDIA_POST";

    public static final String NEW_MEDIA_POST_EXTRA_IDS = "NEW_MEDIA_POST_EXTRA_IDS";

    private String mMediaCapturePath = "";

    private String getUploadErrorHtml(String mediaId, String path) {
        return String.format(Locale.US, "<span id=\"img_container_%s\" class=\"img_container failed\" data-failed=\"%s\">" + "<progress id=\"progress_%s\" value=\"0\" class=\"wp_media_indicator failed\" " + "contenteditable=\"false\"></progress>" + "<img data-wpid=\"%s\" src=\"%s\" alt=\"\" class=\"failed\"></span>", mediaId, getString(R.string.tap_to_try_again), mediaId, mediaId, path);
    }

    private String migrateLegacyDraft(String content) {
        if (!ListenerUtil.mutListener.listen(11900)) {
            if (content.contains("<img src=\"null\" android-uri=\"")) {
                // And trigger an upload action for the specific image / video
                Pattern pattern = Pattern.compile("<img src=\"null\" android-uri=\"([^\"]*)\".*>");
                Matcher matcher = pattern.matcher(content);
                StringBuffer stringBuffer = new StringBuffer();
                if (!ListenerUtil.mutListener.listen(11897)) {
                    {
                        long _loopCounter201 = 0;
                        while (matcher.find()) {
                            ListenerUtil.loopListener.listen("_loopCounter201", ++_loopCounter201);
                            String stringUri = matcher.group(1);
                            Uri uri = Uri.parse(stringUri);
                            MediaFile mediaFile = FluxCUtils.mediaFileFromMediaModel(mEditorMedia.updateMediaUploadStateBlocking(uri, MediaUploadState.FAILED));
                            if (!ListenerUtil.mutListener.listen(11895)) {
                                if (mediaFile == null) {
                                    continue;
                                }
                            }
                            String replacement = getUploadErrorHtml(String.valueOf(mediaFile.getId()), mediaFile.getFilePath());
                            if (!ListenerUtil.mutListener.listen(11896)) {
                                matcher.appendReplacement(stringBuffer, replacement);
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(11898)) {
                    matcher.appendTail(stringBuffer);
                }
                if (!ListenerUtil.mutListener.listen(11899)) {
                    content = stringBuffer.toString();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11905)) {
            if (content.contains("[caption")) {
                // Convert old legacy post caption formatting to new format, to avoid being stripped by the visual editor
                Pattern pattern = Pattern.compile("(\\[caption[^]]*caption=\"([^\"]*)\"[^]]*].+?)(\\[\\/caption])");
                Matcher matcher = pattern.matcher(content);
                StringBuffer stringBuffer = new StringBuffer();
                if (!ListenerUtil.mutListener.listen(11902)) {
                    {
                        long _loopCounter202 = 0;
                        while (matcher.find()) {
                            ListenerUtil.loopListener.listen("_loopCounter202", ++_loopCounter202);
                            String replacement = matcher.group(1) + matcher.group(2) + matcher.group(3);
                            if (!ListenerUtil.mutListener.listen(11901)) {
                                matcher.appendReplacement(stringBuffer, replacement);
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(11903)) {
                    matcher.appendTail(stringBuffer);
                }
                if (!ListenerUtil.mutListener.listen(11904)) {
                    content = stringBuffer.toString();
                }
            }
        }
        return content;
    }

    private String migrateToGutenbergEditor(String content) {
        return "<!-- wp:paragraph --><p>" + content + "</p><!-- /wp:paragraph -->";
    }

    private void fillContentEditorFields() {
        if (!ListenerUtil.mutListener.listen(11906)) {
            // Needed blog settings needed by the editor
            mEditorFragment.setFeaturedImageSupported(mSite.isFeaturedImageSupported());
        }
        if (!ListenerUtil.mutListener.listen(11910)) {
            // Special actions - these only make sense for empty posts that are going to be populated now
            if (TextUtils.isEmpty(mEditPostRepository.getContent())) {
                String action = getIntent().getAction();
                if (!ListenerUtil.mutListener.listen(11909)) {
                    if (Intent.ACTION_SEND_MULTIPLE.equals(action)) {
                        if (!ListenerUtil.mutListener.listen(11908)) {
                            setPostContentFromShareAction();
                        }
                    } else if (NEW_MEDIA_POST.equals(action)) {
                        if (!ListenerUtil.mutListener.listen(11907)) {
                            mEditorMedia.addExistingMediaToEditorAsync(AddExistingMediaSource.WP_MEDIA_LIBRARY, getIntent().getLongArrayExtra(NEW_MEDIA_POST_EXTRA_IDS));
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11912)) {
            if (mIsPage) {
                if (!ListenerUtil.mutListener.listen(11911)) {
                    setPageContent();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11925)) {
            // Set post title and content
            if (mEditPostRepository.hasPost()) {
                if (!ListenerUtil.mutListener.listen(11918)) {
                    // don't avoid calling setContent() for GutenbergEditorFragment so RN gets initialized
                    if ((ListenerUtil.mutListener.listen(11914) ? (((ListenerUtil.mutListener.listen(11913) ? (!TextUtils.isEmpty(mEditPostRepository.getContent()) && mEditorFragment instanceof GutenbergEditorFragment) : (!TextUtils.isEmpty(mEditPostRepository.getContent()) || mEditorFragment instanceof GutenbergEditorFragment))) || !mHasSetPostContent) : (((ListenerUtil.mutListener.listen(11913) ? (!TextUtils.isEmpty(mEditPostRepository.getContent()) && mEditorFragment instanceof GutenbergEditorFragment) : (!TextUtils.isEmpty(mEditPostRepository.getContent()) || mEditorFragment instanceof GutenbergEditorFragment))) && !mHasSetPostContent))) {
                        if (!ListenerUtil.mutListener.listen(11915)) {
                            mHasSetPostContent = true;
                        }
                        // TODO: Might be able to drop .replaceAll() when legacy editor is removed
                        String content = mEditPostRepository.getContent().replaceAll("\uFFFC", "");
                        if (!ListenerUtil.mutListener.listen(11916)) {
                            // Prepare eventual legacy editor local draft for the new editor
                            content = migrateLegacyDraft(content);
                        }
                        if (!ListenerUtil.mutListener.listen(11917)) {
                            mEditorFragment.setContent(content);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(11923)) {
                    if (!TextUtils.isEmpty(mEditPostRepository.getTitle())) {
                        if (!ListenerUtil.mutListener.listen(11922)) {
                            mEditorFragment.setTitle(mEditPostRepository.getTitle());
                        }
                    } else if (mEditorFragment instanceof GutenbergEditorFragment) {
                        // don't avoid calling setTitle() for GutenbergEditorFragment so RN gets initialized
                        final String title = getIntent().getStringExtra(EXTRA_PAGE_TITLE);
                        if (!ListenerUtil.mutListener.listen(11921)) {
                            if (title != null) {
                                if (!ListenerUtil.mutListener.listen(11920)) {
                                    mEditorFragment.setTitle(title);
                                }
                            } else {
                                if (!ListenerUtil.mutListener.listen(11919)) {
                                    mEditorFragment.setTitle("");
                                }
                            }
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(11924)) {
                    // TODO: postSettingsButton.setText(post.isPage() ? R.string.page_settings : R.string.post_settings);
                    mEditorFragment.setFeaturedImageId(mEditPostRepository.getFeaturedImageId());
                }
            }
        }
    }

    private void launchCamera() {
        if (!ListenerUtil.mutListener.listen(11926)) {
            WPMediaUtils.launchCamera(this, BuildConfig.APPLICATION_ID, mediaCapturePath -> mMediaCapturePath = mediaCapturePath);
        }
    }

    protected void setPostContentFromShareAction() {
        Intent intent = getIntent();
        // Check for shared text
        final String text = intent.getStringExtra(Intent.EXTRA_TEXT);
        final String title = intent.getStringExtra(Intent.EXTRA_SUBJECT);
        if (!ListenerUtil.mutListener.listen(11929)) {
            if (text != null) {
                if (!ListenerUtil.mutListener.listen(11927)) {
                    mHasSetPostContent = true;
                }
                if (!ListenerUtil.mutListener.listen(11928)) {
                    mEditPostRepository.updateAsync(postModel -> {
                        if (title != null) {
                            postModel.setTitle(title);
                        }
                        // Create an <a href> element around links
                        String updatedContent = AutolinkUtils.autoCreateLinks(text);
                        // If editor is Gutenberg, add Gutenberg block around content
                        if (mShowGutenbergEditor) {
                            updatedContent = migrateToGutenbergEditor(updatedContent);
                        }
                        // update PostModel
                        postModel.setContent(updatedContent);
                        mEditPostRepository.updatePublishDateIfShouldBePublishedImmediately(postModel);
                        return true;
                    }, (postModel, result) -> {
                        if (result == UpdatePostResult.Updated.INSTANCE) {
                            mEditorFragment.setTitle(postModel.getTitle());
                            mEditorFragment.setContent(postModel.getContent());
                        }
                        return null;
                    });
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11930)) {
            setPostMediaFromShareAction();
        }
    }

    private void setPostMediaFromShareAction() {
        Intent intent = getIntent();
        if (!ListenerUtil.mutListener.listen(11935)) {
            // Check for shared media
            if (intent.hasExtra(Intent.EXTRA_STREAM)) {
                String action = intent.getAction();
                ArrayList<Uri> sharedUris;
                if (Intent.ACTION_SEND_MULTIPLE.equals(action)) {
                    sharedUris = intent.getParcelableArrayListExtra((Intent.EXTRA_STREAM));
                } else {
                    // For a single media share, we only allow images and video types
                    if (isMediaTypeIntent(intent)) {
                        sharedUris = new ArrayList<>();
                        if (!ListenerUtil.mutListener.listen(11931)) {
                            sharedUris.add(intent.getParcelableExtra(Intent.EXTRA_STREAM));
                        }
                    } else {
                        sharedUris = null;
                    }
                }
                if (!ListenerUtil.mutListener.listen(11934)) {
                    if (sharedUris != null) {
                        if (!ListenerUtil.mutListener.listen(11932)) {
                            // removing this from the intent so it doesn't insert the media items again on each Activity re-creation
                            getIntent().removeExtra(Intent.EXTRA_STREAM);
                        }
                        if (!ListenerUtil.mutListener.listen(11933)) {
                            mEditorMedia.addNewMediaItemsToEditorAsync(sharedUris, false);
                        }
                    }
                }
            }
        }
    }

    private boolean isMediaTypeIntent(Intent intent) {
        String type = intent.getType();
        return (ListenerUtil.mutListener.listen(11937) ? (type != null || ((ListenerUtil.mutListener.listen(11936) ? (type.startsWith("image") && type.startsWith("video")) : (type.startsWith("image") || type.startsWith("video"))))) : (type != null && ((ListenerUtil.mutListener.listen(11936) ? (type.startsWith("image") && type.startsWith("video")) : (type.startsWith("image") || type.startsWith("video"))))));
    }

    private void setFeaturedImageId(final long mediaId, final boolean imagePicked, final boolean isGutenbergEditor) {
        if (!ListenerUtil.mutListener.listen(11944)) {
            if (isGutenbergEditor) {
                EditPostRepository postRepository = getEditPostRepository();
                if (!ListenerUtil.mutListener.listen(11939)) {
                    if (postRepository == null) {
                        return;
                    }
                }
                int postId = getEditPostRepository().getId();
                if (!ListenerUtil.mutListener.listen(11942)) {
                    if (mediaId == MEDIA_ID_NO_FEATURED_IMAGE_SET) {
                        if (!ListenerUtil.mutListener.listen(11941)) {
                            mFeaturedImageHelper.trackFeaturedImageEvent(FeaturedImageHelper.TrackableEvent.IMAGE_REMOVED_GUTENBERG_EDITOR, postId);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(11940)) {
                            mFeaturedImageHelper.trackFeaturedImageEvent(FeaturedImageHelper.TrackableEvent.IMAGE_PICKED_GUTENBERG_EDITOR, postId);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(11943)) {
                    mUpdateFeaturedImageUseCase.updateFeaturedImage(mediaId, postRepository, postModel -> null);
                }
            } else if (mEditPostSettingsFragment != null) {
                if (!ListenerUtil.mutListener.listen(11938)) {
                    mEditPostSettingsFragment.updateFeaturedImage(mediaId, imagePicked);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11946)) {
            if (mEditorFragment instanceof GutenbergEditorFragment) {
                if (!ListenerUtil.mutListener.listen(11945)) {
                    ((GutenbergEditorFragment) mEditorFragment).sendToJSFeaturedImageId((int) mediaId);
                }
            }
        }
    }

    /**
     * Sets the page content
     */
    private void setPageContent() {
        Intent intent = getIntent();
        final String content = intent.getStringExtra(EXTRA_PAGE_CONTENT);
        if (!ListenerUtil.mutListener.listen(11950)) {
            if ((ListenerUtil.mutListener.listen(11947) ? (content != null || !content.isEmpty()) : (content != null && !content.isEmpty()))) {
                if (!ListenerUtil.mutListener.listen(11948)) {
                    mHasSetPostContent = true;
                }
                if (!ListenerUtil.mutListener.listen(11949)) {
                    mEditPostRepository.updateAsync(postModel -> {
                        postModel.setContent(content);
                        mEditPostRepository.updatePublishDateIfShouldBePublishedImmediately(postModel);
                        return true;
                    }, (postModel, result) -> {
                        if (result == UpdatePostResult.Updated.INSTANCE) {
                            mEditorFragment.setContent(postModel.getContent());
                        }
                        return null;
                    });
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!ListenerUtil.mutListener.listen(11951)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (!ListenerUtil.mutListener.listen(11953)) {
            // so placing this here before the check
            if (requestCode == RequestCodes.REMOTE_PREVIEW_POST) {
                if (!ListenerUtil.mutListener.listen(11952)) {
                    updatePostLoadingAndDialogState(PostLoadingState.NONE);
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(11957)) {
            if (resultCode != Activity.RESULT_OK) {
                if (!ListenerUtil.mutListener.listen(11956)) {
                    // for all media related intents, let editor fragment know about cancellation
                    switch(requestCode) {
                        case RequestCodes.MULTI_SELECT_MEDIA_PICKER:
                        case RequestCodes.SINGLE_SELECT_MEDIA_PICKER:
                        case RequestCodes.PHOTO_PICKER:
                        case RequestCodes.STORIES_PHOTO_PICKER:
                        case RequestCodes.STOCK_MEDIA_PICKER_SINGLE_SELECT:
                        case RequestCodes.MEDIA_LIBRARY:
                        case RequestCodes.PICTURE_LIBRARY:
                        case RequestCodes.TAKE_PHOTO:
                        case RequestCodes.VIDEO_LIBRARY:
                        case RequestCodes.TAKE_VIDEO:
                        case RequestCodes.STOCK_MEDIA_PICKER_MULTI_SELECT:
                        case RequestCodes.STOCK_MEDIA_PICKER_SINGLE_SELECT_FOR_GUTENBERG_BLOCK:
                            if (!ListenerUtil.mutListener.listen(11954)) {
                                mEditorFragment.mediaSelectionCancelled();
                            }
                            return;
                        case RequestCodes.EDIT_STORY:
                            if (!ListenerUtil.mutListener.listen(11955)) {
                                mStoryEditingCancelled = true;
                            }
                            return;
                        default:
                            // noop
                            return;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(11961)) {
            if (requestCode == RequestCodes.EDIT_STORY) {
                if (!ListenerUtil.mutListener.listen(11958)) {
                    mStoryEditingCancelled = false;
                }
                if (!ListenerUtil.mutListener.listen(11960)) {
                    if (mEditorFragment instanceof GutenbergEditorFragment) {
                        if (!ListenerUtil.mutListener.listen(11959)) {
                            mEditorFragment.onActivityResult(requestCode, resultCode, data);
                        }
                        return;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12008)) {
            if ((ListenerUtil.mutListener.listen(11964) ? (data != null && (((ListenerUtil.mutListener.listen(11963) ? ((ListenerUtil.mutListener.listen(11962) ? (requestCode == RequestCodes.TAKE_PHOTO && requestCode == RequestCodes.TAKE_VIDEO) : (requestCode == RequestCodes.TAKE_PHOTO || requestCode == RequestCodes.TAKE_VIDEO)) && requestCode == RequestCodes.PHOTO_PICKER) : ((ListenerUtil.mutListener.listen(11962) ? (requestCode == RequestCodes.TAKE_PHOTO && requestCode == RequestCodes.TAKE_VIDEO) : (requestCode == RequestCodes.TAKE_PHOTO || requestCode == RequestCodes.TAKE_VIDEO)) || requestCode == RequestCodes.PHOTO_PICKER))))) : (data != null || (((ListenerUtil.mutListener.listen(11963) ? ((ListenerUtil.mutListener.listen(11962) ? (requestCode == RequestCodes.TAKE_PHOTO && requestCode == RequestCodes.TAKE_VIDEO) : (requestCode == RequestCodes.TAKE_PHOTO || requestCode == RequestCodes.TAKE_VIDEO)) && requestCode == RequestCodes.PHOTO_PICKER) : ((ListenerUtil.mutListener.listen(11962) ? (requestCode == RequestCodes.TAKE_PHOTO && requestCode == RequestCodes.TAKE_VIDEO) : (requestCode == RequestCodes.TAKE_PHOTO || requestCode == RequestCodes.TAKE_VIDEO)) || requestCode == RequestCodes.PHOTO_PICKER))))))) {
                if (!ListenerUtil.mutListener.listen(12007)) {
                    switch(requestCode) {
                        case RequestCodes.MULTI_SELECT_MEDIA_PICKER:
                        case RequestCodes.SINGLE_SELECT_MEDIA_PICKER:
                            if (!ListenerUtil.mutListener.listen(11965)) {
                                handleMediaPickerResult(data);
                            }
                            // handleMediaPickerResult -> addExistingMediaToEditorAndSave
                            break;
                        case RequestCodes.PHOTO_PICKER:
                        case RequestCodes.STOCK_MEDIA_PICKER_SINGLE_SELECT:
                            if (!ListenerUtil.mutListener.listen(11979)) {
                                // user chose a featured image
                                if (data.hasExtra(MediaPickerConstants.EXTRA_MEDIA_ID)) {
                                    long mediaId = data.getLongExtra(MediaPickerConstants.EXTRA_MEDIA_ID, 0);
                                    if (!ListenerUtil.mutListener.listen(11978)) {
                                        setFeaturedImageId(mediaId, true, false);
                                    }
                                } else if (data.hasExtra(MediaPickerConstants.EXTRA_MEDIA_QUEUED_URIS)) {
                                    List<Uri> uris = convertStringArrayIntoUrisList(data.getStringArrayExtra(MediaPickerConstants.EXTRA_MEDIA_QUEUED_URIS));
                                    int postId = getImmutablePost().getId();
                                    if (!ListenerUtil.mutListener.listen(11971)) {
                                        mFeaturedImageHelper.trackFeaturedImageEvent(FeaturedImageHelper.TrackableEvent.IMAGE_PICKED_POST_SETTINGS, postId);
                                    }
                                    if (!ListenerUtil.mutListener.listen(11975)) {
                                        {
                                            long _loopCounter204 = 0;
                                            for (Uri mediaUri : uris) {
                                                ListenerUtil.loopListener.listen("_loopCounter204", ++_loopCounter204);
                                                String mimeType = getContentResolver().getType(mediaUri);
                                                EnqueueFeaturedImageResult queueImageResult = mFeaturedImageHelper.queueFeaturedImageForUpload(postId, getSite(), mediaUri, mimeType);
                                                if (!ListenerUtil.mutListener.listen(11974)) {
                                                    if (queueImageResult == EnqueueFeaturedImageResult.FILE_NOT_FOUND) {
                                                        if (!ListenerUtil.mutListener.listen(11973)) {
                                                            Toast.makeText(this, R.string.file_not_found, Toast.LENGTH_SHORT).show();
                                                        }
                                                    } else if (queueImageResult == EnqueueFeaturedImageResult.INVALID_POST_ID) {
                                                        if (!ListenerUtil.mutListener.listen(11972)) {
                                                            Toast.makeText(this, R.string.error_generic, Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(11977)) {
                                        if (mEditPostSettingsFragment != null) {
                                            if (!ListenerUtil.mutListener.listen(11976)) {
                                                mEditPostSettingsFragment.refreshViews();
                                            }
                                        }
                                    }
                                } else if (data.hasExtra(MediaPickerConstants.EXTRA_MEDIA_URIS)) {
                                    List<Uri> uris = convertStringArrayIntoUrisList(data.getStringArrayExtra(MediaPickerConstants.EXTRA_MEDIA_URIS));
                                    if (!ListenerUtil.mutListener.listen(11970)) {
                                        mEditorMedia.addNewMediaItemsToEditorAsync(uris, false);
                                    }
                                } else if (data.hasExtra(MediaPickerConstants.EXTRA_SAVED_MEDIA_MODEL_LOCAL_IDS)) {
                                    int[] localIds = data.getIntArrayExtra(MediaPickerConstants.EXTRA_SAVED_MEDIA_MODEL_LOCAL_IDS);
                                    int postId = getImmutablePost().getId();
                                    if (!ListenerUtil.mutListener.listen(11967)) {
                                        {
                                            long _loopCounter203 = 0;
                                            for (int localId : localIds) {
                                                ListenerUtil.loopListener.listen("_loopCounter203", ++_loopCounter203);
                                                MediaModel media = mMediaStore.getMediaWithLocalId(localId);
                                                if (!ListenerUtil.mutListener.listen(11966)) {
                                                    mFeaturedImageHelper.queueFeaturedImageForUpload(postId, media);
                                                }
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(11969)) {
                                        if (mEditPostSettingsFragment != null) {
                                            if (!ListenerUtil.mutListener.listen(11968)) {
                                                mEditPostSettingsFragment.refreshViews();
                                            }
                                        }
                                    }
                                }
                            }
                            break;
                        case RequestCodes.STOCK_MEDIA_PICKER_SINGLE_SELECT_FOR_GUTENBERG_BLOCK:
                            if (!ListenerUtil.mutListener.listen(11981)) {
                                if (data.hasExtra(MediaPickerConstants.EXTRA_MEDIA_ID)) {
                                    // pass array with single item
                                    long[] mediaIds = { data.getLongExtra(MediaPickerConstants.EXTRA_MEDIA_ID, 0) };
                                    if (!ListenerUtil.mutListener.listen(11980)) {
                                        mEditorMedia.addExistingMediaToEditorAsync(AddExistingMediaSource.STOCK_PHOTO_LIBRARY, mediaIds);
                                    }
                                }
                            }
                            break;
                        case RequestCodes.MEDIA_LIBRARY:
                        case RequestCodes.PICTURE_LIBRARY:
                            if (!ListenerUtil.mutListener.listen(11982)) {
                                mEditorMedia.advertiseImageOptimisationAndAddMedia(WPMediaUtils.retrieveMediaUris(data));
                            }
                            break;
                        case RequestCodes.TAKE_PHOTO:
                            if (!ListenerUtil.mutListener.listen(11985)) {
                                if (WPMediaUtils.shouldAdvertiseImageOptimization(this)) {
                                    if (!ListenerUtil.mutListener.listen(11984)) {
                                        WPMediaUtils.advertiseImageOptimization(this, this::addLastTakenPicture);
                                    }
                                } else {
                                    if (!ListenerUtil.mutListener.listen(11983)) {
                                        addLastTakenPicture();
                                    }
                                }
                            }
                            break;
                        case RequestCodes.VIDEO_LIBRARY:
                            if (!ListenerUtil.mutListener.listen(11986)) {
                                mEditorMedia.addNewMediaItemsToEditorAsync(WPMediaUtils.retrieveMediaUris(data), false);
                            }
                            break;
                        case RequestCodes.TAKE_VIDEO:
                            if (!ListenerUtil.mutListener.listen(11987)) {
                                mEditorMedia.addFreshlyTakenVideoToEditor();
                            }
                            break;
                        case RequestCodes.MEDIA_SETTINGS:
                            if (!ListenerUtil.mutListener.listen(11989)) {
                                if (mEditorFragment instanceof AztecEditorFragment) {
                                    if (!ListenerUtil.mutListener.listen(11988)) {
                                        mEditorFragment.onActivityResult(AztecEditorFragment.EDITOR_MEDIA_SETTINGS, Activity.RESULT_OK, data);
                                    }
                                }
                            }
                            break;
                        case RequestCodes.STOCK_MEDIA_PICKER_MULTI_SELECT:
                            String key = MediaBrowserActivity.RESULT_IDS;
                            if (!ListenerUtil.mutListener.listen(11991)) {
                                if (data.hasExtra(key)) {
                                    long[] mediaIds = data.getLongArrayExtra(key);
                                    if (!ListenerUtil.mutListener.listen(11990)) {
                                        mEditorMedia.addExistingMediaToEditorAsync(AddExistingMediaSource.STOCK_PHOTO_LIBRARY, mediaIds);
                                    }
                                }
                            }
                            break;
                        case RequestCodes.GIF_PICKER_SINGLE_SELECT:
                        case RequestCodes.GIF_PICKER_MULTI_SELECT:
                            if (!ListenerUtil.mutListener.listen(11993)) {
                                if (data.hasExtra(MediaPickerConstants.EXTRA_SAVED_MEDIA_MODEL_LOCAL_IDS)) {
                                    int[] localIds = data.getIntArrayExtra(MediaPickerConstants.EXTRA_SAVED_MEDIA_MODEL_LOCAL_IDS);
                                    if (!ListenerUtil.mutListener.listen(11992)) {
                                        mEditorMedia.addGifMediaToPostAsync(localIds);
                                    }
                                }
                            }
                            break;
                        case RequestCodes.HISTORY_DETAIL:
                            if (!ListenerUtil.mutListener.listen(11997)) {
                                if (data.hasExtra(KEY_REVISION)) {
                                    if (!ListenerUtil.mutListener.listen(11994)) {
                                        mViewPager.setCurrentItem(PAGE_CONTENT);
                                    }
                                    if (!ListenerUtil.mutListener.listen(11995)) {
                                        mRevision = data.getParcelableExtra(KEY_REVISION);
                                    }
                                    if (!ListenerUtil.mutListener.listen(11996)) {
                                        new Handler().postDelayed(this::loadRevision, getResources().getInteger(R.integer.full_screen_dialog_animation_duration));
                                    }
                                }
                            }
                            break;
                        case RequestCodes.IMAGE_EDITOR_EDIT_IMAGE:
                            List<Uri> uris = WPMediaUtils.retrieveImageEditorResult(data);
                            if (!ListenerUtil.mutListener.listen(11998)) {
                                mImageEditorTracker.trackAddPhoto(uris);
                            }
                            if (!ListenerUtil.mutListener.listen(12000)) {
                                {
                                    long _loopCounter205 = 0;
                                    for (Uri item : uris) {
                                        ListenerUtil.loopListener.listen("_loopCounter205", ++_loopCounter205);
                                        if (!ListenerUtil.mutListener.listen(11999)) {
                                            mEditorMedia.addNewMediaToEditorAsync(item, false);
                                        }
                                    }
                                }
                            }
                            break;
                        case RequestCodes.SELECTED_USER_MENTION:
                            if (!ListenerUtil.mutListener.listen(12003)) {
                                if (mOnGetSuggestionResult != null) {
                                    String selectedMention = data.getStringExtra(SuggestionActivity.SELECTED_VALUE);
                                    if (!ListenerUtil.mutListener.listen(12001)) {
                                        mOnGetSuggestionResult.accept(selectedMention);
                                    }
                                    if (!ListenerUtil.mutListener.listen(12002)) {
                                        // Clear the callback once we have gotten a result
                                        mOnGetSuggestionResult = null;
                                    }
                                }
                            }
                            break;
                        case RequestCodes.FILE_LIBRARY:
                        case RequestCodes.AUDIO_LIBRARY:
                            if (!ListenerUtil.mutListener.listen(12006)) {
                                if (data.hasExtra(MediaPickerConstants.EXTRA_MEDIA_URIS)) {
                                    List<Uri> uriResults = convertStringArrayIntoUrisList(Objects.requireNonNull(data.getStringArrayExtra(MediaPickerConstants.EXTRA_MEDIA_URIS)));
                                    if (!ListenerUtil.mutListener.listen(12005)) {
                                        {
                                            long _loopCounter206 = 0;
                                            for (Uri uri : uriResults) {
                                                ListenerUtil.loopListener.listen("_loopCounter206", ++_loopCounter206);
                                                if (!ListenerUtil.mutListener.listen(12004)) {
                                                    mEditorMedia.addNewMediaToEditorAsync(uri, false);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            break;
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12010)) {
            if (requestCode == JetpackSecuritySettingsActivity.JETPACK_SECURITY_SETTINGS_REQUEST_CODE) {
                if (!ListenerUtil.mutListener.listen(12009)) {
                    fetchSiteSettings();
                }
            }
        }
    }

    private List<Uri> convertStringArrayIntoUrisList(String[] stringArray) {
        List<Uri> uris = new ArrayList<>(stringArray.length);
        if (!ListenerUtil.mutListener.listen(12012)) {
            {
                long _loopCounter207 = 0;
                for (String stringUri : stringArray) {
                    ListenerUtil.loopListener.listen("_loopCounter207", ++_loopCounter207);
                    if (!ListenerUtil.mutListener.listen(12011)) {
                        uris.add(Uri.parse(stringUri));
                    }
                }
            }
        }
        return uris;
    }

    private void addLastTakenPicture() {
        try {
            if (!ListenerUtil.mutListener.listen(12014)) {
                // TODO why do we scan the file twice? Also how come it can result in OOM?
                WPMediaUtils.scanMediaFile(this, mMediaCapturePath);
            }
            File f = new File(mMediaCapturePath);
            Uri capturedImageUri = Uri.fromFile(f);
            if (!ListenerUtil.mutListener.listen(12019)) {
                if (capturedImageUri != null) {
                    if (!ListenerUtil.mutListener.listen(12016)) {
                        mEditorMedia.addNewMediaToEditorAsync(capturedImageUri, true);
                    }
                    final Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    if (!ListenerUtil.mutListener.listen(12017)) {
                        scanIntent.setData(capturedImageUri);
                    }
                    if (!ListenerUtil.mutListener.listen(12018)) {
                        sendBroadcast(scanIntent);
                    }
                } else {
                    if (!ListenerUtil.mutListener.listen(12015)) {
                        ToastUtils.showToast(this, R.string.gallery_error, Duration.SHORT);
                    }
                }
            }
        } catch (RuntimeException | OutOfMemoryError e) {
            if (!ListenerUtil.mutListener.listen(12013)) {
                AppLog.e(T.EDITOR, e);
            }
        }
    }

    private void handleMediaPickerResult(Intent data) {
        // TODO move this to EditorMedia
        ArrayList<Long> ids = ListUtils.fromLongArray(data.getLongArrayExtra(MediaBrowserActivity.RESULT_IDS));
        if (!ListenerUtil.mutListener.listen(12029)) {
            if ((ListenerUtil.mutListener.listen(12025) ? (ids == null && (ListenerUtil.mutListener.listen(12024) ? (ids.size() >= 0) : (ListenerUtil.mutListener.listen(12023) ? (ids.size() <= 0) : (ListenerUtil.mutListener.listen(12022) ? (ids.size() > 0) : (ListenerUtil.mutListener.listen(12021) ? (ids.size() < 0) : (ListenerUtil.mutListener.listen(12020) ? (ids.size() != 0) : (ids.size() == 0))))))) : (ids == null || (ListenerUtil.mutListener.listen(12024) ? (ids.size() >= 0) : (ListenerUtil.mutListener.listen(12023) ? (ids.size() <= 0) : (ListenerUtil.mutListener.listen(12022) ? (ids.size() > 0) : (ListenerUtil.mutListener.listen(12021) ? (ids.size() < 0) : (ListenerUtil.mutListener.listen(12020) ? (ids.size() != 0) : (ids.size() == 0))))))))) {
                if (!ListenerUtil.mutListener.listen(12028)) {
                    if (data.hasExtra(MediaPickerConstants.EXTRA_MEDIA_ID)) {
                        long mediaId = data.getLongExtra(MediaPickerConstants.EXTRA_MEDIA_ID, 0);
                        if (!ListenerUtil.mutListener.listen(12026)) {
                            ids = new ArrayList<>();
                        }
                        if (!ListenerUtil.mutListener.listen(12027)) {
                            ids.add(mediaId);
                        }
                    } else {
                        return;
                    }
                }
            }
        }
        boolean allAreImages = true;
        if (!ListenerUtil.mutListener.listen(12033)) {
            {
                long _loopCounter208 = 0;
                for (Long id : ids) {
                    ListenerUtil.loopListener.listen("_loopCounter208", ++_loopCounter208);
                    MediaModel media = mMediaStore.getSiteMediaWithId(mSite, id);
                    if (!ListenerUtil.mutListener.listen(12032)) {
                        if ((ListenerUtil.mutListener.listen(12030) ? (media != null || !MediaUtils.isValidImage(media.getUrl())) : (media != null && !MediaUtils.isValidImage(media.getUrl())))) {
                            if (!ListenerUtil.mutListener.listen(12031)) {
                                allAreImages = false;
                            }
                            break;
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12046)) {
            // dialog so the user can choose whether to insert them individually or as a gallery
            if ((ListenerUtil.mutListener.listen(12040) ? ((ListenerUtil.mutListener.listen(12039) ? ((ListenerUtil.mutListener.listen(12038) ? (ids.size() >= 1) : (ListenerUtil.mutListener.listen(12037) ? (ids.size() <= 1) : (ListenerUtil.mutListener.listen(12036) ? (ids.size() < 1) : (ListenerUtil.mutListener.listen(12035) ? (ids.size() != 1) : (ListenerUtil.mutListener.listen(12034) ? (ids.size() == 1) : (ids.size() > 1)))))) || allAreImages) : ((ListenerUtil.mutListener.listen(12038) ? (ids.size() >= 1) : (ListenerUtil.mutListener.listen(12037) ? (ids.size() <= 1) : (ListenerUtil.mutListener.listen(12036) ? (ids.size() < 1) : (ListenerUtil.mutListener.listen(12035) ? (ids.size() != 1) : (ListenerUtil.mutListener.listen(12034) ? (ids.size() == 1) : (ids.size() > 1)))))) && allAreImages)) || !mShowGutenbergEditor) : ((ListenerUtil.mutListener.listen(12039) ? ((ListenerUtil.mutListener.listen(12038) ? (ids.size() >= 1) : (ListenerUtil.mutListener.listen(12037) ? (ids.size() <= 1) : (ListenerUtil.mutListener.listen(12036) ? (ids.size() < 1) : (ListenerUtil.mutListener.listen(12035) ? (ids.size() != 1) : (ListenerUtil.mutListener.listen(12034) ? (ids.size() == 1) : (ids.size() > 1)))))) || allAreImages) : ((ListenerUtil.mutListener.listen(12038) ? (ids.size() >= 1) : (ListenerUtil.mutListener.listen(12037) ? (ids.size() <= 1) : (ListenerUtil.mutListener.listen(12036) ? (ids.size() < 1) : (ListenerUtil.mutListener.listen(12035) ? (ids.size() != 1) : (ListenerUtil.mutListener.listen(12034) ? (ids.size() == 1) : (ids.size() > 1)))))) && allAreImages)) && !mShowGutenbergEditor))) {
                if (!ListenerUtil.mutListener.listen(12045)) {
                    showInsertMediaDialog(ids);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(12041)) {
                    // if allowMultipleSelection and gutenberg editor, pass all ids to addExistingMediaToEditor at once
                    mEditorMedia.addExistingMediaToEditorAsync(AddExistingMediaSource.WP_MEDIA_LIBRARY, ids);
                }
                if (!ListenerUtil.mutListener.listen(12044)) {
                    if ((ListenerUtil.mutListener.listen(12042) ? (mShowGutenbergEditor || mEditorPhotoPicker.getAllowMultipleSelection()) : (mShowGutenbergEditor && mEditorPhotoPicker.getAllowMultipleSelection()))) {
                        if (!ListenerUtil.mutListener.listen(12043)) {
                            mEditorPhotoPicker.setAllowMultipleSelection(false);
                        }
                    }
                }
            }
        }
    }

    /*
     * called after user selects multiple photos from WP media library
     */
    private void showInsertMediaDialog(final ArrayList<Long> mediaIds) {
        InsertMediaCallback callback = dialog -> {
            switch(dialog.getInsertType()) {
                case GALLERY:
                    MediaGallery gallery = new MediaGallery();
                    gallery.setType(dialog.getGalleryType().toString());
                    gallery.setNumColumns(dialog.getNumColumns());
                    gallery.setIds(mediaIds);
                    mEditorFragment.appendGallery(gallery);
                    break;
                case INDIVIDUALLY:
                    mEditorMedia.addExistingMediaToEditorAsync(AddExistingMediaSource.WP_MEDIA_LIBRARY, mediaIds);
                    break;
            }
        };
        InsertMediaDialog dialog = InsertMediaDialog.newInstance(callback, mSite);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (!ListenerUtil.mutListener.listen(12047)) {
            ft.add(dialog, "insert_media");
        }
        if (!ListenerUtil.mutListener.listen(12048)) {
            ft.commitAllowingStateLoss();
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAccountChanged(OnAccountChanged event) {
        if (!ListenerUtil.mutListener.listen(12052)) {
            if (event.causeOfChange == AccountAction.SEND_VERIFICATION_EMAIL) {
                if (!ListenerUtil.mutListener.listen(12051)) {
                    if (!event.isError()) {
                        if (!ListenerUtil.mutListener.listen(12050)) {
                            ToastUtils.showToast(this, getString(R.string.toast_verification_email_sent));
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(12049)) {
                            ToastUtils.showToast(this, getString(R.string.toast_verification_email_send_error));
                        }
                    }
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMediaChanged(OnMediaChanged event) {
        if (!ListenerUtil.mutListener.listen(12060)) {
            if (event.isError()) {
                final String errorMessage;
                switch(event.error.type) {
                    case FS_READ_PERMISSION_DENIED:
                        errorMessage = getString(R.string.error_media_insufficient_fs_permissions);
                        break;
                    case NOT_FOUND:
                        errorMessage = getString(R.string.error_media_not_found);
                        break;
                    case AUTHORIZATION_REQUIRED:
                        errorMessage = getString(R.string.error_media_unauthorized);
                        break;
                    case PARSE_ERROR:
                        errorMessage = getString(R.string.error_media_parse_error);
                        break;
                    case MALFORMED_MEDIA_ARG:
                    case NULL_MEDIA_ARG:
                    case GENERIC_ERROR:
                    default:
                        errorMessage = getString(R.string.error_refresh_media);
                        break;
                }
                if (!ListenerUtil.mutListener.listen(12059)) {
                    if (!TextUtils.isEmpty(errorMessage)) {
                        if (!ListenerUtil.mutListener.listen(12058)) {
                            ToastUtils.showToast(EditPostActivity.this, errorMessage, ToastUtils.Duration.SHORT);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(12057)) {
                    if ((ListenerUtil.mutListener.listen(12053) ? (mPendingVideoPressInfoRequests != null || !mPendingVideoPressInfoRequests.isEmpty()) : (mPendingVideoPressInfoRequests != null && !mPendingVideoPressInfoRequests.isEmpty()))) {
                        if (!ListenerUtil.mutListener.listen(12055)) {
                            {
                                long _loopCounter209 = 0;
                                // them again and notify the editor
                                for (String videoId : mPendingVideoPressInfoRequests) {
                                    ListenerUtil.loopListener.listen("_loopCounter209", ++_loopCounter209);
                                    String videoUrl = mMediaStore.getUrlForSiteVideoWithVideoPressGuid(mSite, videoId);
                                    String posterUrl = WPMediaUtils.getVideoPressVideoPosterFromURL(videoUrl);
                                    if (!ListenerUtil.mutListener.listen(12054)) {
                                        mEditorFragment.setUrlForVideoPressId(videoId, videoUrl, posterUrl);
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(12056)) {
                            mPendingVideoPressInfoRequests.clear();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onEditPostPublishedSettingsClick() {
        if (!ListenerUtil.mutListener.listen(12061)) {
            mViewPager.setCurrentItem(PAGE_PUBLISH_SETTINGS);
        }
    }

    @Override
    public void clearFeaturedImage() {
        if (!ListenerUtil.mutListener.listen(12063)) {
            if (mEditorFragment instanceof GutenbergEditorFragment) {
                if (!ListenerUtil.mutListener.listen(12062)) {
                    ((GutenbergEditorFragment) mEditorFragment).sendToJSFeaturedImageId(0);
                }
            }
        }
    }

    @Override
    public void updateFeaturedImage(final long mediaId, final boolean imagePicked) {
        if (!ListenerUtil.mutListener.listen(12064)) {
            setFeaturedImageId(mediaId, imagePicked, true);
        }
    }

    @Override
    public void onAddMediaClicked() {
        if (!ListenerUtil.mutListener.listen(12068)) {
            if (mEditorPhotoPicker.isPhotoPickerShowing()) {
                if (!ListenerUtil.mutListener.listen(12067)) {
                    mEditorPhotoPicker.hidePhotoPicker();
                }
            } else if (WPMediaUtils.currentUserCanUploadMedia(mSite)) {
                if (!ListenerUtil.mutListener.listen(12066)) {
                    mEditorPhotoPicker.showPhotoPicker(mSite);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(12065)) {
                    // show the WP media library instead of the photo picker if the user doesn't have upload permission
                    mMediaPickerLauncher.viewWPMediaLibraryPickerForResult(this, mSite, MediaBrowserType.EDITOR_PICKER);
                }
            }
        }
    }

    @Override
    public void onAddMediaImageClicked(boolean allowMultipleSelection) {
        if (!ListenerUtil.mutListener.listen(12069)) {
            mEditorPhotoPicker.setAllowMultipleSelection(allowMultipleSelection);
        }
        if (!ListenerUtil.mutListener.listen(12070)) {
            mMediaPickerLauncher.viewWPMediaLibraryPickerForResult(this, mSite, MediaBrowserType.GUTENBERG_IMAGE_PICKER);
        }
    }

    @Override
    public void onAddMediaVideoClicked(boolean allowMultipleSelection) {
        if (!ListenerUtil.mutListener.listen(12071)) {
            mEditorPhotoPicker.setAllowMultipleSelection(allowMultipleSelection);
        }
        if (!ListenerUtil.mutListener.listen(12072)) {
            mMediaPickerLauncher.viewWPMediaLibraryPickerForResult(this, mSite, MediaBrowserType.GUTENBERG_VIDEO_PICKER);
        }
    }

    @Override
    public void onAddLibraryMediaClicked(boolean allowMultipleSelection) {
        if (!ListenerUtil.mutListener.listen(12073)) {
            mEditorPhotoPicker.setAllowMultipleSelection(allowMultipleSelection);
        }
        if (!ListenerUtil.mutListener.listen(12076)) {
            if (allowMultipleSelection) {
                if (!ListenerUtil.mutListener.listen(12075)) {
                    mMediaPickerLauncher.viewWPMediaLibraryPickerForResult(this, mSite, MediaBrowserType.EDITOR_PICKER);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(12074)) {
                    mMediaPickerLauncher.viewWPMediaLibraryPickerForResult(this, mSite, MediaBrowserType.GUTENBERG_SINGLE_MEDIA_PICKER);
                }
            }
        }
    }

    @Override
    public void onAddLibraryFileClicked(boolean allowMultipleSelection) {
        if (!ListenerUtil.mutListener.listen(12077)) {
            mEditorPhotoPicker.setAllowMultipleSelection(allowMultipleSelection);
        }
        if (!ListenerUtil.mutListener.listen(12078)) {
            mMediaPickerLauncher.viewWPMediaLibraryPickerForResult(this, mSite, MediaBrowserType.GUTENBERG_SINGLE_FILE_PICKER);
        }
    }

    @Override
    public void onAddLibraryAudioFileClicked(boolean allowMultipleSelection) {
        if (!ListenerUtil.mutListener.listen(12079)) {
            mMediaPickerLauncher.viewWPMediaLibraryPickerForResult(this, mSite, MediaBrowserType.GUTENBERG_SINGLE_AUDIO_FILE_PICKER);
        }
    }

    @Override
    public void onAddPhotoClicked(boolean allowMultipleSelection) {
        if (!ListenerUtil.mutListener.listen(12082)) {
            if (allowMultipleSelection) {
                if (!ListenerUtil.mutListener.listen(12081)) {
                    mMediaPickerLauncher.showPhotoPickerForResult(this, MediaBrowserType.GUTENBERG_IMAGE_PICKER, mSite, mEditPostRepository.getId());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(12080)) {
                    mMediaPickerLauncher.showPhotoPickerForResult(this, MediaBrowserType.GUTENBERG_SINGLE_IMAGE_PICKER, mSite, mEditPostRepository.getId());
                }
            }
        }
    }

    @Override
    public void onCapturePhotoClicked() {
        if (!ListenerUtil.mutListener.listen(12083)) {
            onPhotoPickerIconClicked(PhotoPickerIcon.ANDROID_CAPTURE_PHOTO, false);
        }
    }

    @Override
    public void onAddVideoClicked(boolean allowMultipleSelection) {
        if (!ListenerUtil.mutListener.listen(12086)) {
            if (allowMultipleSelection) {
                if (!ListenerUtil.mutListener.listen(12085)) {
                    mMediaPickerLauncher.showPhotoPickerForResult(this, MediaBrowserType.GUTENBERG_VIDEO_PICKER, mSite, mEditPostRepository.getId());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(12084)) {
                    mMediaPickerLauncher.showPhotoPickerForResult(this, MediaBrowserType.GUTENBERG_SINGLE_VIDEO_PICKER, mSite, mEditPostRepository.getId());
                }
            }
        }
    }

    @Override
    public void onAddDeviceMediaClicked(boolean allowMultipleSelection) {
        if (!ListenerUtil.mutListener.listen(12089)) {
            if (allowMultipleSelection) {
                if (!ListenerUtil.mutListener.listen(12088)) {
                    mMediaPickerLauncher.showPhotoPickerForResult(this, MediaBrowserType.GUTENBERG_MEDIA_PICKER, mSite, mEditPostRepository.getId());
                }
            } else {
                if (!ListenerUtil.mutListener.listen(12087)) {
                    mMediaPickerLauncher.showPhotoPickerForResult(this, MediaBrowserType.GUTENBERG_SINGLE_MEDIA_PICKER, mSite, mEditPostRepository.getId());
                }
            }
        }
    }

    @Override
    public void onAddStockMediaClicked(boolean allowMultipleSelection) {
        if (!ListenerUtil.mutListener.listen(12090)) {
            onPhotoPickerIconClicked(PhotoPickerIcon.STOCK_MEDIA, allowMultipleSelection);
        }
    }

    @Override
    public void onAddGifClicked(boolean allowMultipleSelection) {
        if (!ListenerUtil.mutListener.listen(12091)) {
            onPhotoPickerIconClicked(PhotoPickerIcon.GIF, allowMultipleSelection);
        }
    }

    @Override
    public void onAddFileClicked(boolean allowMultipleSelection) {
        if (!ListenerUtil.mutListener.listen(12092)) {
            mMediaPickerLauncher.showFilePicker(this, allowMultipleSelection, getSite());
        }
    }

    @Override
    public void onAddAudioFileClicked(boolean allowMultipleSelection) {
        if (!ListenerUtil.mutListener.listen(12093)) {
            mMediaPickerLauncher.showAudioFilePicker(this, allowMultipleSelection, getSite());
        }
    }

    @Override
    public void onPerformFetch(String path, boolean enableCaching, Consumer<String> onResult, Consumer<Bundle> onError) {
        if (!ListenerUtil.mutListener.listen(12095)) {
            if (mSite != null) {
                if (!ListenerUtil.mutListener.listen(12094)) {
                    mReactNativeRequestHandler.performGetRequest(path, mSite, enableCaching, onResult, onError);
                }
            }
        }
    }

    @Override
    public void onCaptureVideoClicked() {
        if (!ListenerUtil.mutListener.listen(12096)) {
            onPhotoPickerIconClicked(PhotoPickerIcon.ANDROID_CAPTURE_VIDEO, false);
        }
    }

    @Override
    public void onMediaDropped(final ArrayList<Uri> mediaUris) {
        if (!ListenerUtil.mutListener.listen(12097)) {
            mEditorMedia.setDroppedMediaUris(mediaUris);
        }
        if (!ListenerUtil.mutListener.listen(12100)) {
            if (PermissionUtils.checkAndRequestStoragePermission(this, WPPermissionUtils.EDITOR_DRAG_DROP_PERMISSION_REQUEST_CODE)) {
                if (!ListenerUtil.mutListener.listen(12098)) {
                    mEditorMedia.addNewMediaItemsToEditorAsync(mEditorMedia.getDroppedMediaUris(), false);
                }
                if (!ListenerUtil.mutListener.listen(12099)) {
                    mEditorMedia.getDroppedMediaUris().clear();
                }
            }
        }
    }

    @Override
    public void onRequestDragAndDropPermissions(DragEvent dragEvent) {
        if (!ListenerUtil.mutListener.listen(12101)) {
            requestDragAndDropPermissions(dragEvent);
        }
    }

    @Override
    public void onMediaRetryAllClicked(Set<String> failedMediaIds) {
        if (!ListenerUtil.mutListener.listen(12102)) {
            UploadService.cancelFinalNotification(this, mEditPostRepository.getPost());
        }
        if (!ListenerUtil.mutListener.listen(12103)) {
            UploadService.cancelFinalNotificationForMedia(this, mSite);
        }
        ArrayList<Integer> localMediaIds = new ArrayList<>();
        if (!ListenerUtil.mutListener.listen(12105)) {
            {
                long _loopCounter210 = 0;
                for (String idString : failedMediaIds) {
                    ListenerUtil.loopListener.listen("_loopCounter210", ++_loopCounter210);
                    if (!ListenerUtil.mutListener.listen(12104)) {
                        localMediaIds.add(Integer.valueOf(idString));
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12106)) {
            mEditorMedia.retryFailedMediaAsync(localMediaIds);
        }
    }

    @Override
    public boolean onMediaRetryClicked(final String mediaId) {
        if (!ListenerUtil.mutListener.listen(12108)) {
            if (TextUtils.isEmpty(mediaId)) {
                if (!ListenerUtil.mutListener.listen(12107)) {
                    AppLog.e(T.MEDIA, "Invalid media id passed to onMediaRetryClicked");
                }
                return false;
            }
        }
        MediaModel media = mMediaStore.getMediaWithLocalId(StringUtils.stringToInt(mediaId));
        if (!ListenerUtil.mutListener.listen(12114)) {
            if (media == null) {
                if (!ListenerUtil.mutListener.listen(12109)) {
                    AppLog.e(T.MEDIA, "Can't find media with local id: " + mediaId);
                }
                AlertDialog.Builder builder = new MaterialAlertDialogBuilder(this);
                if (!ListenerUtil.mutListener.listen(12110)) {
                    builder.setTitle(getString(R.string.cannot_retry_deleted_media_item));
                }
                if (!ListenerUtil.mutListener.listen(12111)) {
                    builder.setPositiveButton(R.string.yes, (dialog, id) -> {
                        runOnUiThread(() -> mEditorFragment.removeMedia(mediaId));
                        dialog.dismiss();
                    });
                }
                if (!ListenerUtil.mutListener.listen(12112)) {
                    builder.setNegativeButton(getString(R.string.no), (dialog, id) -> dialog.dismiss());
                }
                AlertDialog dialog = builder.create();
                if (!ListenerUtil.mutListener.listen(12113)) {
                    dialog.show();
                }
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(12121)) {
            if ((ListenerUtil.mutListener.listen(12115) ? (media.getUrl() != null || media.getUploadState().equals(MediaUploadState.UPLOADED.toString())) : (media.getUrl() != null && media.getUploadState().equals(MediaUploadState.UPLOADED.toString())))) {
                if (!ListenerUtil.mutListener.listen(12120)) {
                    // Notify the editor fragment upload was successful and it should replace the local url by the remote url.
                    if (mEditorMediaUploadListener != null) {
                        if (!ListenerUtil.mutListener.listen(12119)) {
                            mEditorMediaUploadListener.onMediaUploadSucceeded(String.valueOf(media.getId()), FluxCUtils.mediaFileFromMediaModel(media));
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(12116)) {
                    UploadService.cancelFinalNotification(this, mEditPostRepository.getPost());
                }
                if (!ListenerUtil.mutListener.listen(12117)) {
                    UploadService.cancelFinalNotificationForMedia(this, mSite);
                }
                if (!ListenerUtil.mutListener.listen(12118)) {
                    mEditorMedia.retryFailedMediaAsync(Collections.singletonList(media.getId()));
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12122)) {
            AnalyticsTracker.track(Stat.EDITOR_UPLOAD_MEDIA_RETRIED);
        }
        return true;
    }

    @Override
    public void onMediaUploadCancelClicked(String localMediaId) {
        if (!ListenerUtil.mutListener.listen(12126)) {
            if (!TextUtils.isEmpty(localMediaId)) {
                if (!ListenerUtil.mutListener.listen(12125)) {
                    mEditorMedia.cancelMediaUploadAsync(StringUtils.stringToInt(localMediaId), true);
                }
            } else {
                if (!ListenerUtil.mutListener.listen(12123)) {
                    // Passed mediaId is incorrect: cancel all uploads for this post
                    ToastUtils.showToast(this, getString(R.string.error_all_media_upload_canceled));
                }
                if (!ListenerUtil.mutListener.listen(12124)) {
                    EventBus.getDefault().post(new PostEvents.PostMediaCanceled(mEditPostRepository.getEditablePost()));
                }
            }
        }
    }

    @Override
    public void onMediaDeleted(String localMediaId) {
        if (!ListenerUtil.mutListener.listen(12128)) {
            if (!TextUtils.isEmpty(localMediaId)) {
                if (!ListenerUtil.mutListener.listen(12127)) {
                    mEditorMedia.onMediaDeleted(mShowAztecEditor, mShowGutenbergEditor, localMediaId);
                }
            }
        }
    }

    @Override
    public void onUndoMediaCheck(final String undoedContent) {
        List<MediaModel> currentlyUploadingMedia = UploadService.getPendingOrInProgressMediaUploadsForPost(mEditPostRepository.getPost());
        List<String> mediaMarkedUploading = AztecEditorFragment.getMediaMarkedUploadingInPostContent(EditPostActivity.this, undoedContent);
        if (!ListenerUtil.mutListener.listen(12136)) {
            {
                long _loopCounter212 = 0;
                // mark that item failed
                for (String mediaId : mediaMarkedUploading) {
                    ListenerUtil.loopListener.listen("_loopCounter212", ++_loopCounter212);
                    boolean found = false;
                    if (!ListenerUtil.mutListener.listen(12131)) {
                        {
                            long _loopCounter211 = 0;
                            for (MediaModel media : currentlyUploadingMedia) {
                                ListenerUtil.loopListener.listen("_loopCounter211", ++_loopCounter211);
                                if (!ListenerUtil.mutListener.listen(12130)) {
                                    if (StringUtils.stringToInt(mediaId) == media.getId()) {
                                        if (!ListenerUtil.mutListener.listen(12129)) {
                                            found = true;
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(12135)) {
                        if (!found) {
                            if (!ListenerUtil.mutListener.listen(12134)) {
                                if (mEditorFragment instanceof AztecEditorFragment) {
                                    if (!ListenerUtil.mutListener.listen(12132)) {
                                        mEditorMedia.updateDeletedMediaItemIds(mediaId);
                                    }
                                    if (!ListenerUtil.mutListener.listen(12133)) {
                                        ((AztecEditorFragment) mEditorFragment).setMediaToFailed(mediaId);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onVideoPressInfoRequested(final String videoId) {
        String videoUrl = mMediaStore.getUrlForSiteVideoWithVideoPressGuid(mSite, videoId);
        if (!ListenerUtil.mutListener.listen(12138)) {
            if (videoUrl == null) {
                if (!ListenerUtil.mutListener.listen(12137)) {
                    AppLog.w(T.EDITOR, "The editor wants more info about the following VideoPress code: " + videoId + " but it's not available in the current site " + mSite.getUrl() + " Maybe it's from another site?");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(12141)) {
            if (videoUrl.isEmpty()) {
                if (!ListenerUtil.mutListener.listen(12140)) {
                    if (PermissionUtils.checkAndRequestCameraAndStoragePermissions(this, WPPermissionUtils.EDITOR_MEDIA_PERMISSION_REQUEST_CODE)) {
                        if (!ListenerUtil.mutListener.listen(12139)) {
                            runOnUiThread(() -> {
                                if (mPendingVideoPressInfoRequests == null) {
                                    mPendingVideoPressInfoRequests = new ArrayList<>();
                                }
                                mPendingVideoPressInfoRequests.add(videoId);
                                mEditorMedia.refreshBlogMedia();
                            });
                        }
                    }
                }
            }
        }
        String posterUrl = WPMediaUtils.getVideoPressVideoPosterFromURL(videoUrl);
        if (!ListenerUtil.mutListener.listen(12142)) {
            mEditorFragment.setUrlForVideoPressId(videoId, videoUrl, posterUrl);
        }
    }

    @Override
    public Map<String, String> onAuthHeaderRequested(String url) {
        Map<String, String> authHeaders = new HashMap<>();
        String token = mAccountStore.getAccessToken();
        if (!ListenerUtil.mutListener.listen(12146)) {
            if ((ListenerUtil.mutListener.listen(12144) ? ((ListenerUtil.mutListener.listen(12143) ? (mSite.isPrivate() || WPUrlUtils.safeToAddWordPressComAuthToken(url)) : (mSite.isPrivate() && WPUrlUtils.safeToAddWordPressComAuthToken(url))) || !TextUtils.isEmpty(token)) : ((ListenerUtil.mutListener.listen(12143) ? (mSite.isPrivate() || WPUrlUtils.safeToAddWordPressComAuthToken(url)) : (mSite.isPrivate() && WPUrlUtils.safeToAddWordPressComAuthToken(url))) && !TextUtils.isEmpty(token)))) {
                if (!ListenerUtil.mutListener.listen(12145)) {
                    authHeaders.put(AuthenticationUtils.AUTHORIZATION_HEADER_NAME, "Bearer " + token);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12150)) {
            if ((ListenerUtil.mutListener.listen(12148) ? ((ListenerUtil.mutListener.listen(12147) ? (mSite.isPrivateWPComAtomic() || mPrivateAtomicCookie.exists()) : (mSite.isPrivateWPComAtomic() && mPrivateAtomicCookie.exists())) || WPUrlUtils.safeToAddPrivateAtCookie(url, mPrivateAtomicCookie.getDomain())) : ((ListenerUtil.mutListener.listen(12147) ? (mSite.isPrivateWPComAtomic() || mPrivateAtomicCookie.exists()) : (mSite.isPrivateWPComAtomic() && mPrivateAtomicCookie.exists())) && WPUrlUtils.safeToAddPrivateAtCookie(url, mPrivateAtomicCookie.getDomain())))) {
                if (!ListenerUtil.mutListener.listen(12149)) {
                    authHeaders.put(AuthenticationUtils.COOKIE_HEADER_NAME, mPrivateAtomicCookie.getCookieContent());
                }
            }
        }
        return authHeaders;
    }

    @Override
    public void onEditorFragmentInitialized() {
        if (!ListenerUtil.mutListener.listen(12156)) {
            // check whether we have media items to insert from the WRITE POST with media functionality
            if (getIntent().hasExtra(EXTRA_INSERT_MEDIA)) {
                if (!ListenerUtil.mutListener.listen(12151)) {
                    // Bump analytics
                    AnalyticsTracker.track(Stat.NOTIFICATION_UPLOAD_MEDIA_SUCCESS_WRITE_POST);
                }
                List<MediaModel> mediaList = (List<MediaModel>) getIntent().getSerializableExtra(EXTRA_INSERT_MEDIA);
                if (!ListenerUtil.mutListener.listen(12152)) {
                    // removing this from the intent so it doesn't insert the media items again on each Activity re-creation
                    getIntent().removeExtra(EXTRA_INSERT_MEDIA);
                }
                if (!ListenerUtil.mutListener.listen(12155)) {
                    if ((ListenerUtil.mutListener.listen(12153) ? (mediaList != null || !mediaList.isEmpty()) : (mediaList != null && !mediaList.isEmpty()))) {
                        if (!ListenerUtil.mutListener.listen(12154)) {
                            mEditorMedia.addExistingMediaToEditorAsync(mediaList, AddExistingMediaSource.WP_MEDIA_LIBRARY);
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12157)) {
            onEditorFinalTouchesBeforeShowing();
        }
    }

    private void onEditorFinalTouchesBeforeShowing() {
        if (!ListenerUtil.mutListener.listen(12158)) {
            refreshEditorContent();
        }
        if (!ListenerUtil.mutListener.listen(12169)) {
            // probably here is best for Gutenberg to start interacting with
            if ((ListenerUtil.mutListener.listen(12159) ? (mShowGutenbergEditor || mEditorFragment instanceof GutenbergEditorFragment) : (mShowGutenbergEditor && mEditorFragment instanceof GutenbergEditorFragment))) {
                if (!ListenerUtil.mutListener.listen(12162)) {
                    refreshEditorTheme();
                }
                List<MediaModel> failedMedia = mMediaStore.getMediaForPostWithState(mEditPostRepository.getPost(), MediaUploadState.FAILED);
                if (!ListenerUtil.mutListener.listen(12168)) {
                    if ((ListenerUtil.mutListener.listen(12163) ? (failedMedia != null || !failedMedia.isEmpty()) : (failedMedia != null && !failedMedia.isEmpty()))) {
                        HashSet<Integer> mediaIds = new HashSet<>();
                        if (!ListenerUtil.mutListener.listen(12166)) {
                            {
                                long _loopCounter213 = 0;
                                for (MediaModel media : failedMedia) {
                                    ListenerUtil.loopListener.listen("_loopCounter213", ++_loopCounter213);
                                    if (!ListenerUtil.mutListener.listen(12165)) {
                                        // featured image isn't in the editor but in the Post Settings fragment, so we want to skip it
                                        if (!media.getMarkedLocallyAsFeatured()) {
                                            if (!ListenerUtil.mutListener.listen(12164)) {
                                                mediaIds.add(media.getId());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(12167)) {
                            ((GutenbergEditorFragment) mEditorFragment).resetUploadingMediaToFailed(mediaIds);
                        }
                    }
                }
            } else if ((ListenerUtil.mutListener.listen(12160) ? (mShowAztecEditor || mEditorFragment instanceof AztecEditorFragment) : (mShowAztecEditor && mEditorFragment instanceof AztecEditorFragment))) {
                final EntryPoint entryPoint = (EntryPoint) getIntent().getSerializableExtra(EXTRA_ENTRY_POINT);
                if (!ListenerUtil.mutListener.listen(12161)) {
                    mPostEditorAnalyticsSession.start(null, themeSupportsGalleryWithImageBlocks(), entryPoint);
                }
            }
        }
    }

    @Override
    public void onEditorFragmentContentReady(ArrayList<Object> unsupportedBlocksList, boolean replaceBlockActionWaiting) {
        final EntryPoint entryPoint = (EntryPoint) getIntent().getSerializableExtra(EXTRA_ENTRY_POINT);
        if (!ListenerUtil.mutListener.listen(12170)) {
            // is still reflecting the actual startup time of the editor
            mPostEditorAnalyticsSession.start(unsupportedBlocksList, themeSupportsGalleryWithImageBlocks(), entryPoint);
        }
        if (!ListenerUtil.mutListener.listen(12171)) {
            presentNewPageNoticeIfNeeded();
        }
        if (!ListenerUtil.mutListener.listen(12174)) {
            // unless the user cancelled editing in which case we should continue as normal and attach the listener
            if ((ListenerUtil.mutListener.listen(12172) ? (!replaceBlockActionWaiting && mStoryEditingCancelled) : (!replaceBlockActionWaiting || mStoryEditingCancelled))) {
                if (!ListenerUtil.mutListener.listen(12173)) {
                    mStoriesEventListener.startListening();
                }
            }
        }
        // Start VM, load prompt and populate Editor with content after edit IS ready.
        final int promptId = getIntent().getIntExtra(EXTRA_PROMPT_ID, -1);
        if (!ListenerUtil.mutListener.listen(12175)) {
            mEditorBloggingPromptsViewModel.start(mSite, promptId);
        }
    }

    @Override
    public void onReplaceStoryEditedBlockActionSent() {
        if (!ListenerUtil.mutListener.listen(12176)) {
            // otherwise these events will miss their target
            mStoriesEventListener.pauseListening();
        }
    }

    @Override
    public void onReplaceStoryEditedBlockActionReceived() {
        if (!ListenerUtil.mutListener.listen(12177)) {
            mStoriesEventListener.startListening();
        }
    }

    private void logTemplateSelection() {
        final String template = getIntent().getStringExtra(EXTRA_PAGE_TEMPLATE);
        if (!ListenerUtil.mutListener.listen(12178)) {
            if (template == null) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(12179)) {
            mPostEditorAnalyticsSession.applyTemplate(template);
        }
    }

    @Override
    public void showUserSuggestions(Consumer<String> onResult) {
        if (!ListenerUtil.mutListener.listen(12180)) {
            showSuggestions(SuggestionType.Users, onResult);
        }
    }

    @Override
    public void showXpostSuggestions(Consumer<String> onResult) {
        if (!ListenerUtil.mutListener.listen(12181)) {
            showSuggestions(SuggestionType.XPosts, onResult);
        }
    }

    private void showSuggestions(SuggestionType type, Consumer<String> onResult) {
        if (!ListenerUtil.mutListener.listen(12182)) {
            mOnGetSuggestionResult = onResult;
        }
        if (!ListenerUtil.mutListener.listen(12183)) {
            ActivityLauncher.viewSuggestionsForResult(this, mSite, type);
        }
    }

    @Override
    public void onGutenbergEditorSetFocalPointPickerTooltipShown(boolean tooltipShown) {
        if (!ListenerUtil.mutListener.listen(12184)) {
            AppPrefs.setGutenbergFocalPointPickerTooltipShown(tooltipShown);
        }
    }

    @Override
    public boolean onGutenbergEditorRequestFocalPointPickerTooltipShown() {
        return AppPrefs.getGutenbergFocalPointPickerTooltipShown();
    }

    @Override
    public void onHtmlModeToggledInToolbar() {
        if (!ListenerUtil.mutListener.listen(12185)) {
            toggleHtmlModeOnMenu();
        }
    }

    @Override
    public void onTrackableEvent(TrackableEvent event) throws IllegalArgumentException {
        if (!ListenerUtil.mutListener.listen(12186)) {
            mEditorTracker.trackEditorEvent(event, mEditorFragment.getEditorName());
        }
        if (!ListenerUtil.mutListener.listen(12190)) {
            switch(event) {
                case ELLIPSIS_COLLAPSE_BUTTON_TAPPED:
                    if (!ListenerUtil.mutListener.listen(12187)) {
                        AppPrefs.setAztecEditorToolbarExpanded(false);
                    }
                    break;
                case ELLIPSIS_EXPAND_BUTTON_TAPPED:
                    if (!ListenerUtil.mutListener.listen(12188)) {
                        AppPrefs.setAztecEditorToolbarExpanded(true);
                    }
                    break;
                case HTML_BUTTON_TAPPED:
                case LINK_ADDED_BUTTON_TAPPED:
                    if (!ListenerUtil.mutListener.listen(12189)) {
                        mEditorPhotoPicker.hidePhotoPicker();
                    }
                    break;
            }
        }
    }

    @Override
    public void onTrackableEvent(TrackableEvent event, Map<String, String> properties) {
        if (!ListenerUtil.mutListener.listen(12191)) {
            mEditorTracker.trackEditorEvent(event, mEditorFragment.getEditorName(), properties);
        }
    }

    @Override
    public void onStoryComposerLoadRequested(ArrayList<Object> mediaFiles, String blockId) {
        if (!ListenerUtil.mutListener.listen(12192)) {
            // we need to save the latest before editing
            updateAndSavePostAsync(updatePostResult -> {
                boolean noSlidesLoaded = mStoriesEventListener.onRequestMediaFilesEditorLoad(EditPostActivity.this, new LocalId(mEditPostRepository.getId()), mNetworkErrorOnLastMediaFetchAttempt, mediaFiles, blockId);
                if (mNetworkErrorOnLastMediaFetchAttempt && noSlidesLoaded) {
                    // try another fetchMedia request
                    fetchMediaList();
                }
            });
        }
    }

    @Override
    public void onRetryUploadForMediaCollection(ArrayList<Object> mediaFiles) {
        if (!ListenerUtil.mutListener.listen(12193)) {
            mStoriesEventListener.onRetryUploadForMediaCollection(this, mediaFiles, mEditorMediaUploadListener);
        }
    }

    @Override
    public void onCancelUploadForMediaCollection(ArrayList<Object> mediaFiles) {
        if (!ListenerUtil.mutListener.listen(12194)) {
            mStoriesEventListener.onCancelUploadForMediaCollection(mediaFiles);
        }
    }

    @Override
    public void onCancelSaveForMediaCollection(ArrayList<Object> mediaFiles) {
        if (!ListenerUtil.mutListener.listen(12195)) {
            mStoriesEventListener.onCancelSaveForMediaCollection(mediaFiles);
        }
    }

    @Override
    public boolean showPreview() {
        PreviewLogicOperationResult opResult = mRemotePreviewLogicHelper.runPostPreviewLogic(this, mSite, Objects.requireNonNull(mEditPostRepository.getPost()), getEditPostActivityStrategyFunctions());
        if (!ListenerUtil.mutListener.listen(12199)) {
            if ((ListenerUtil.mutListener.listen(12197) ? ((ListenerUtil.mutListener.listen(12196) ? (opResult == PreviewLogicOperationResult.MEDIA_UPLOAD_IN_PROGRESS && opResult == PreviewLogicOperationResult.CANNOT_SAVE_EMPTY_DRAFT) : (opResult == PreviewLogicOperationResult.MEDIA_UPLOAD_IN_PROGRESS || opResult == PreviewLogicOperationResult.CANNOT_SAVE_EMPTY_DRAFT)) && opResult == PreviewLogicOperationResult.CANNOT_REMOTE_AUTO_SAVE_EMPTY_POST) : ((ListenerUtil.mutListener.listen(12196) ? (opResult == PreviewLogicOperationResult.MEDIA_UPLOAD_IN_PROGRESS && opResult == PreviewLogicOperationResult.CANNOT_SAVE_EMPTY_DRAFT) : (opResult == PreviewLogicOperationResult.MEDIA_UPLOAD_IN_PROGRESS || opResult == PreviewLogicOperationResult.CANNOT_SAVE_EMPTY_DRAFT)) || opResult == PreviewLogicOperationResult.CANNOT_REMOTE_AUTO_SAVE_EMPTY_POST))) {
                return false;
            } else if (opResult == PreviewLogicOperationResult.OPENING_PREVIEW) {
                if (!ListenerUtil.mutListener.listen(12198)) {
                    updatePostLoadingAndDialogState(PostLoadingState.PREVIEWING, mEditPostRepository.getPost());
                }
            }
        }
        return true;
    }

    @Override
    public Map<String, Double> onRequestBlockTypeImpressions() {
        return AppPrefs.getGutenbergBlockTypeImpressions();
    }

    @Override
    public void onSetBlockTypeImpressions(Map<String, Double> impressions) {
        if (!ListenerUtil.mutListener.listen(12200)) {
            AppPrefs.setGutenbergBlockTypeImpressions(impressions);
        }
    }

    @Override
    public void onContactCustomerSupport() {
        if (!ListenerUtil.mutListener.listen(12201)) {
            EditPostCustomerSupportHelper.INSTANCE.onContactCustomerSupport(mZendeskHelper, this, getSite());
        }
    }

    @Override
    public void onGotoCustomerSupportOptions() {
        if (!ListenerUtil.mutListener.listen(12202)) {
            EditPostCustomerSupportHelper.INSTANCE.onGotoCustomerSupportOptions(this, getSite());
        }
    }

    @Override
    public void onSendEventToHost(String eventName, Map<String, Object> properties) {
        if (!ListenerUtil.mutListener.listen(12203)) {
            AnalyticsUtils.trackBlockEditorEvent(eventName, mSite, properties);
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMediaUploaded(OnMediaUploaded event) {
        if (!ListenerUtil.mutListener.listen(12204)) {
            if (isFinishing()) {
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(12206)) {
            // event for unknown media, ignoring
            if (event.media == null) {
                if (!ListenerUtil.mutListener.listen(12205)) {
                    AppLog.w(AppLog.T.MEDIA, "Media event carries null media object, not recognized");
                }
                return;
            }
        }
        if (!ListenerUtil.mutListener.listen(12214)) {
            if (event.isError()) {
                View view = mEditorFragment.getView();
                if (!ListenerUtil.mutListener.listen(12212)) {
                    if (view != null) {
                        if (!ListenerUtil.mutListener.listen(12211)) {
                            mUploadUtilsWrapper.showSnackbarError(view, getString(R.string.error_media_upload_failed_for_reason, UploadUtils.getErrorMessageFromMedia(this, event.media)));
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(12213)) {
                    mEditorMedia.onMediaUploadError(mEditorMediaUploadListener, event.media, event.error);
                }
            } else if (event.completed) {
                if (!ListenerUtil.mutListener.listen(12210)) {
                    // if the remote url on completed is null, we consider this upload wasn't successful
                    if (event.media.getUrl() == null) {
                        MediaError error = new MediaError(MediaErrorType.GENERIC_ERROR);
                        if (!ListenerUtil.mutListener.listen(12209)) {
                            mEditorMedia.onMediaUploadError(mEditorMediaUploadListener, event.media, error);
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(12208)) {
                            onUploadSuccess(event.media);
                        }
                    }
                }
            } else {
                if (!ListenerUtil.mutListener.listen(12207)) {
                    onUploadProgress(event.media, event.progress);
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMediaListFetched(OnMediaListFetched event) {
        if (!ListenerUtil.mutListener.listen(12216)) {
            if (event != null) {
                if (!ListenerUtil.mutListener.listen(12215)) {
                    mNetworkErrorOnLastMediaFetchAttempt = event.isError();
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPostChanged(OnPostChanged event) {
        if (!ListenerUtil.mutListener.listen(12229)) {
            if (event.causeOfChange instanceof CauseOfOnPostChanged.UpdatePost) {
                if (!ListenerUtil.mutListener.listen(12228)) {
                    if (!event.isError()) {
                        if (!ListenerUtil.mutListener.listen(12227)) {
                            // here update the menu if it's not a draft anymore
                            invalidateOptionsMenu();
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(12225)) {
                            updatePostLoadingAndDialogState(PostLoadingState.NONE);
                        }
                        if (!ListenerUtil.mutListener.listen(12226)) {
                            AppLog.e(AppLog.T.POSTS, "UPDATE_POST failed: " + event.error.type + " - " + event.error.message);
                        }
                    }
                }
            } else if (event.causeOfChange instanceof CauseOfOnPostChanged.RemoteAutoSavePost) {
                if (!ListenerUtil.mutListener.listen(12219)) {
                    if ((ListenerUtil.mutListener.listen(12217) ? (!mEditPostRepository.hasPost() && (mEditPostRepository.getId() != ((RemoteAutoSavePost) event.causeOfChange).getLocalPostId())) : (!mEditPostRepository.hasPost() || (mEditPostRepository.getId() != ((RemoteAutoSavePost) event.causeOfChange).getLocalPostId())))) {
                        if (!ListenerUtil.mutListener.listen(12218)) {
                            AppLog.e(T.POSTS, "Ignoring REMOTE_AUTO_SAVE_POST in EditPostActivity as mPost is null or id of the opened post" + " doesn't match the event.");
                        }
                        return;
                    }
                }
                if (!ListenerUtil.mutListener.listen(12221)) {
                    if (event.isError()) {
                        if (!ListenerUtil.mutListener.listen(12220)) {
                            AppLog.e(T.POSTS, "REMOTE_AUTO_SAVE_POST failed: " + event.error.type + " - " + event.error.message);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(12222)) {
                    mEditPostRepository.loadPostByLocalPostId(mEditPostRepository.getId());
                }
                if (!ListenerUtil.mutListener.listen(12224)) {
                    if (isRemotePreviewingFromEditor()) {
                        if (!ListenerUtil.mutListener.listen(12223)) {
                            handleRemotePreviewUploadResult(event.isError(), RemotePreviewType.REMOTE_PREVIEW_WITH_REMOTE_AUTO_SAVE);
                        }
                    }
                }
            }
        }
    }

    private boolean isRemotePreviewingFromEditor() {
        return (ListenerUtil.mutListener.listen(12232) ? ((ListenerUtil.mutListener.listen(12231) ? ((ListenerUtil.mutListener.listen(12230) ? (mPostLoadingState == PostLoadingState.UPLOADING_FOR_PREVIEW && mPostLoadingState == PostLoadingState.REMOTE_AUTO_SAVING_FOR_PREVIEW) : (mPostLoadingState == PostLoadingState.UPLOADING_FOR_PREVIEW || mPostLoadingState == PostLoadingState.REMOTE_AUTO_SAVING_FOR_PREVIEW)) && mPostLoadingState == PostLoadingState.PREVIEWING) : ((ListenerUtil.mutListener.listen(12230) ? (mPostLoadingState == PostLoadingState.UPLOADING_FOR_PREVIEW && mPostLoadingState == PostLoadingState.REMOTE_AUTO_SAVING_FOR_PREVIEW) : (mPostLoadingState == PostLoadingState.UPLOADING_FOR_PREVIEW || mPostLoadingState == PostLoadingState.REMOTE_AUTO_SAVING_FOR_PREVIEW)) || mPostLoadingState == PostLoadingState.PREVIEWING)) && mPostLoadingState == PostLoadingState.REMOTE_AUTO_SAVE_PREVIEW_ERROR) : ((ListenerUtil.mutListener.listen(12231) ? ((ListenerUtil.mutListener.listen(12230) ? (mPostLoadingState == PostLoadingState.UPLOADING_FOR_PREVIEW && mPostLoadingState == PostLoadingState.REMOTE_AUTO_SAVING_FOR_PREVIEW) : (mPostLoadingState == PostLoadingState.UPLOADING_FOR_PREVIEW || mPostLoadingState == PostLoadingState.REMOTE_AUTO_SAVING_FOR_PREVIEW)) && mPostLoadingState == PostLoadingState.PREVIEWING) : ((ListenerUtil.mutListener.listen(12230) ? (mPostLoadingState == PostLoadingState.UPLOADING_FOR_PREVIEW && mPostLoadingState == PostLoadingState.REMOTE_AUTO_SAVING_FOR_PREVIEW) : (mPostLoadingState == PostLoadingState.UPLOADING_FOR_PREVIEW || mPostLoadingState == PostLoadingState.REMOTE_AUTO_SAVING_FOR_PREVIEW)) || mPostLoadingState == PostLoadingState.PREVIEWING)) || mPostLoadingState == PostLoadingState.REMOTE_AUTO_SAVE_PREVIEW_ERROR));
    }

    private boolean isUploadingPostForPreview() {
        return (ListenerUtil.mutListener.listen(12233) ? (mPostLoadingState == PostLoadingState.UPLOADING_FOR_PREVIEW && mPostLoadingState == PostLoadingState.REMOTE_AUTO_SAVING_FOR_PREVIEW) : (mPostLoadingState == PostLoadingState.UPLOADING_FOR_PREVIEW || mPostLoadingState == PostLoadingState.REMOTE_AUTO_SAVING_FOR_PREVIEW));
    }

    private void updateOnSuccessfulUpload() {
        if (!ListenerUtil.mutListener.listen(12234)) {
            mIsNewPost = false;
        }
        if (!ListenerUtil.mutListener.listen(12235)) {
            invalidateOptionsMenu();
        }
    }

    private boolean isRemoteAutoSaveError() {
        return mPostLoadingState == PostLoadingState.REMOTE_AUTO_SAVE_PREVIEW_ERROR;
    }

    @Nullable
    private void handleRemotePreviewUploadResult(boolean isError, RemotePreviewLogicHelper.RemotePreviewType param) {
        if (!ListenerUtil.mutListener.listen(12243)) {
            // We are in the process of remote previewing a post from the editor
            if ((ListenerUtil.mutListener.listen(12236) ? (!isError || isUploadingPostForPreview()) : (!isError && isUploadingPostForPreview()))) {
                if (!ListenerUtil.mutListener.listen(12240)) {
                    // update post status and preview it in the internal browser
                    updateOnSuccessfulUpload();
                }
                if (!ListenerUtil.mutListener.listen(12241)) {
                    ActivityLauncher.previewPostOrPageForResult(EditPostActivity.this, mSite, mEditPostRepository.getPost(), param);
                }
                if (!ListenerUtil.mutListener.listen(12242)) {
                    updatePostLoadingAndDialogState(PostLoadingState.PREVIEWING, mEditPostRepository.getPost());
                }
            } else if ((ListenerUtil.mutListener.listen(12237) ? (isError && isRemoteAutoSaveError()) : (isError || isRemoteAutoSaveError()))) {
                if (!ListenerUtil.mutListener.listen(12238)) {
                    // We got an error from the uploading or from the remote auto save of a post: show snackbar error
                    updatePostLoadingAndDialogState(PostLoadingState.NONE);
                }
                if (!ListenerUtil.mutListener.listen(12239)) {
                    mUploadUtilsWrapper.showSnackbarError(findViewById(R.id.editor_activity), getString(R.string.remote_preview_operation_error));
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPostUploaded(OnPostUploaded event) {
        final PostModel post = event.post;
        if (!ListenerUtil.mutListener.listen(12251)) {
            if ((ListenerUtil.mutListener.listen(12244) ? (post != null || post.getId() == mEditPostRepository.getId()) : (post != null && post.getId() == mEditPostRepository.getId()))) {
                if (!ListenerUtil.mutListener.listen(12250)) {
                    if (!isRemotePreviewingFromEditor()) {
                        // We are not remote previewing a post: show snackbar and update post status if needed
                        View snackbarAttachView = findViewById(R.id.editor_activity);
                        if (!ListenerUtil.mutListener.listen(12247)) {
                            mUploadUtilsWrapper.onPostUploadedSnackbarHandler(this, snackbarAttachView, event.isError(), event.isFirstTimePublish, post, event.isError() ? event.error.message : null, getSite());
                        }
                        if (!ListenerUtil.mutListener.listen(12249)) {
                            if (!event.isError()) {
                                if (!ListenerUtil.mutListener.listen(12248)) {
                                    mEditPostRepository.set(() -> {
                                        updateOnSuccessfulUpload();
                                        return post;
                                    });
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(12245)) {
                            mEditPostRepository.set(() -> post);
                        }
                        if (!ListenerUtil.mutListener.listen(12246)) {
                            handleRemotePreviewUploadResult(event.isError(), RemotePreviewType.REMOTE_PREVIEW);
                        }
                    }
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ProgressEvent event) {
        if (!ListenerUtil.mutListener.listen(12253)) {
            if (!isFinishing()) {
                // use upload progress rather than optimizer progress since the former includes upload+optimization
                float progress = UploadService.getUploadProgressForMedia(event.media);
                if (!ListenerUtil.mutListener.listen(12252)) {
                    onUploadProgress(event.media, progress);
                }
            }
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(UploadService.UploadMediaRetryEvent event) {
        if (!ListenerUtil.mutListener.listen(12258)) {
            if ((ListenerUtil.mutListener.listen(12255) ? ((ListenerUtil.mutListener.listen(12254) ? (!isFinishing() || event.mediaModelList != null) : (!isFinishing() && event.mediaModelList != null)) || mEditorMediaUploadListener != null) : ((ListenerUtil.mutListener.listen(12254) ? (!isFinishing() || event.mediaModelList != null) : (!isFinishing() && event.mediaModelList != null)) && mEditorMediaUploadListener != null))) {
                if (!ListenerUtil.mutListener.listen(12257)) {
                    {
                        long _loopCounter214 = 0;
                        for (MediaModel media : event.mediaModelList) {
                            ListenerUtil.loopListener.listen("_loopCounter214", ++_loopCounter214);
                            String localMediaId = String.valueOf(media.getId());
                            EditorFragmentAbstract.MediaType mediaType = media.isVideo() ? EditorFragmentAbstract.MediaType.VIDEO : EditorFragmentAbstract.MediaType.IMAGE;
                            if (!ListenerUtil.mutListener.listen(12256)) {
                                mEditorMediaUploadListener.onMediaUploadRetry(localMediaId, mediaType);
                            }
                        }
                    }
                }
            }
        }
    }

    private void refreshEditorTheme() {
        FetchEditorThemePayload payload = new FetchEditorThemePayload(mSite, mGlobalStyleSupportFeatureConfig.isEnabled());
        if (!ListenerUtil.mutListener.listen(12259)) {
            mDispatcher.dispatch(EditorThemeActionBuilder.newFetchEditorThemeAction(payload));
        }
    }

    private void fetchMediaList() {
        if (!ListenerUtil.mutListener.listen(12261)) {
            // do not refresh if there is no network
            if (!NetworkUtils.isNetworkAvailable(this)) {
                if (!ListenerUtil.mutListener.listen(12260)) {
                    mNetworkErrorOnLastMediaFetchAttempt = true;
                }
                return;
            }
        }
        FetchMediaListPayload payload = new FetchMediaListPayload(mSite, MediaStore.DEFAULT_NUM_MEDIA_PER_FETCH, false);
        if (!ListenerUtil.mutListener.listen(12262)) {
            mDispatcher.dispatch(MediaActionBuilder.newFetchMediaListAction(payload));
        }
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void onEditorThemeChanged(OnEditorThemeChanged event) {
        if (!ListenerUtil.mutListener.listen(12263)) {
            if (!(mEditorFragment instanceof EditorThemeUpdateListener))
                return;
        }
        if (!ListenerUtil.mutListener.listen(12264)) {
            if (mSite.getId() != event.getSiteId())
                return;
        }
        EditorTheme editorTheme = event.getEditorTheme();
        if (!ListenerUtil.mutListener.listen(12265)) {
            if (editorTheme == null)
                return;
        }
        EditorThemeSupport editorThemeSupport = editorTheme.getThemeSupport();
        if (!ListenerUtil.mutListener.listen(12266)) {
            ((EditorThemeUpdateListener) mEditorFragment).onEditorThemeUpdated(editorThemeSupport.toBundle());
        }
        if (!ListenerUtil.mutListener.listen(12267)) {
            mPostEditorAnalyticsSession.editorSettingsFetched(editorThemeSupport.isFSETheme(), event.getEndpoint().getValue());
        }
    }

    @Override
    public EditPostRepository getEditPostRepository() {
        return mEditPostRepository;
    }

    @Override
    public SiteModel getSite() {
        return mSite;
    }

    // External Access to the Image Loader
    public AztecImageLoader getAztecImageLoader() {
        return mAztecImageLoader;
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        // this fixes issue with GB editor
        View editorFragmentView = mEditorFragment.getView();
        if (!ListenerUtil.mutListener.listen(12269)) {
            if (editorFragmentView != null) {
                if (!ListenerUtil.mutListener.listen(12268)) {
                    editorFragmentView.requestFocus();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(12271)) {
            // this fixes issue with Aztec editor
            if (mEditorFragment instanceof AztecEditorFragment) {
                if (!ListenerUtil.mutListener.listen(12270)) {
                    ((AztecEditorFragment) mEditorFragment).requestContentAreaFocus();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    // EditorMediaListener
    @Override
    public void appendMediaFiles(@NotNull Map<String, ? extends MediaFile> mediaFiles) {
        if (!ListenerUtil.mutListener.listen(12272)) {
            mEditorFragment.appendMediaFiles((Map<String, MediaFile>) mediaFiles);
        }
    }

    @NotNull
    @Override
    public PostImmutableModel getImmutablePost() {
        return Objects.requireNonNull(mEditPostRepository.getPost());
    }

    @Override
    public void syncPostObjectWithUiAndSaveIt(@Nullable OnPostUpdatedFromUIListener listener) {
        if (!ListenerUtil.mutListener.listen(12273)) {
            updateAndSavePostAsync(listener);
        }
    }

    @Override
    public void advertiseImageOptimization(@NotNull Function0<Unit> listener) {
        if (!ListenerUtil.mutListener.listen(12274)) {
            WPMediaUtils.advertiseImageOptimization(this, listener::invoke);
        }
    }

    @Override
    public void onMediaModelsCreatedFromOptimizedUris(@NotNull Map<Uri, ? extends MediaModel> oldUriToMediaModels) {
    }

    @Override
    public void showVideoDurationLimitWarning(@NonNull String fileName) {
        if (!ListenerUtil.mutListener.listen(12275)) {
            ToastUtils.showToast(this, R.string.error_media_video_duration_exceeds_limit, ToastUtils.Duration.LONG);
        }
    }

    @Override
    public Consumer<Exception> getExceptionLogger() {
        return (Exception e) -> AppLog.e(T.EDITOR, e);
    }

    @Override
    public Consumer<String> getBreadcrumbLogger() {
        return (String s) -> AppLog.e(T.EDITOR, s);
    }

    private void updateAddingMediaToEditorProgressDialogState(ProgressDialogUiState uiState) {
        if (!ListenerUtil.mutListener.listen(12276)) {
            mAddingMediaToEditorProgressDialog = mProgressDialogHelper.updateProgressDialogState(this, mAddingMediaToEditorProgressDialog, uiState, mUiHelpers);
        }
    }

    @Override
    public String getErrorMessageFromMedia(int mediaId) {
        MediaModel media = mMediaStore.getMediaWithLocalId(mediaId);
        if (!ListenerUtil.mutListener.listen(12277)) {
            if (media != null) {
                return UploadUtils.getErrorMessageFromMedia(this, media);
            }
        }
        return "";
    }

    @Override
    public void showJetpackSettings() {
        if (!ListenerUtil.mutListener.listen(12278)) {
            ActivityLauncher.viewJetpackSecuritySettingsForResult(this, mSite);
        }
    }

    @Override
    public LiveData<DialogVisibility> getSavingInProgressDialogVisibility() {
        return mViewModel.getSavingInProgressDialogVisibility();
    }
}
