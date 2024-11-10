package org.wordpress.android.ui.posts;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00b0\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00b5\u00012\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u0007:\u0002\u00b5\u0001B\u0005\u00a2\u0006\u0002\u0010\bJ\b\u0010v\u001a\u00020\u0018H\u0016J\b\u0010w\u001a\u00020BH\u0016J\b\u0010x\u001a\u00020yH\u0002J\b\u0010z\u001a\u00020yH\u0002J\u0010\u0010{\u001a\u00020y2\u0006\u0010|\u001a\u00020}H\u0002J&\u0010~\u001a\u00020y2\u0007\u0010\u007f\u001a\u00030\u0080\u00012\b\u0010\u0081\u0001\u001a\u00030\u0080\u00012\t\u0010\u0082\u0001\u001a\u0004\u0018\u00010}H\u0014J\u0015\u0010\u0083\u0001\u001a\u00020y2\n\u0010\u0084\u0001\u001a\u0005\u0018\u00010\u0085\u0001H\u0016J\u0013\u0010\u0086\u0001\u001a\u00020>2\b\u0010\u0087\u0001\u001a\u00030\u0088\u0001H\u0016J\u0013\u0010\u0089\u0001\u001a\u00020y2\b\u0010\u008a\u0001\u001a\u00030\u008b\u0001H\u0016J\u0013\u0010\u008c\u0001\u001a\u00020y2\b\u0010\u008a\u0001\u001a\u00030\u008b\u0001H\u0016J\u0011\u0010\u008d\u0001\u001a\u00020y2\u0006\u0010|\u001a\u00020}H\u0014J\u0012\u0010\u008e\u0001\u001a\u00020>2\u0007\u0010\u008f\u0001\u001a\u00020@H\u0016J\u0013\u0010\u0090\u0001\u001a\u00020y2\b\u0010\u008a\u0001\u001a\u00030\u008b\u0001H\u0016J\t\u0010\u0091\u0001\u001a\u00020yH\u0016J\u0013\u0010\u0092\u0001\u001a\u00020y2\b\u0010\u0093\u0001\u001a\u00030\u0085\u0001H\u0016J\u0013\u0010\u0094\u0001\u001a\u00020y2\b\u0010\u0095\u0001\u001a\u00030\u0080\u0001H\u0016J\u0017\u0010\u0096\u0001\u001a\u00020y2\f\u0010\u0097\u0001\u001a\u00070>j\u0003`\u0098\u0001H\u0016J\u0011\u0010\u0099\u0001\u001a\u00020y2\u0006\u0010|\u001a\u00020}H\u0002J\t\u0010\u009a\u0001\u001a\u00020yH\u0002J\t\u0010\u009b\u0001\u001a\u00020yH\u0002J\u0013\u0010\u009c\u0001\u001a\u00020y2\b\u0010\u009d\u0001\u001a\u00030\u009e\u0001H\u0002J\u001e\u0010\u009f\u0001\u001a\u00020y2\n\b\u0001\u0010\u00a0\u0001\u001a\u00030\u0080\u00012\u0007\u0010\u00a1\u0001\u001a\u00020@H\u0002J\u001e\u0010\u00a2\u0001\u001a\u0004\u0018\u00010@2\b\u0010\u00a3\u0001\u001a\u00030\u00a4\u00012\u0007\u0010\u00a1\u0001\u001a\u00020@H\u0002J \u0010\u00a5\u0001\u001a\u00020y*\u00020\n2\b\u0010\u00a6\u0001\u001a\u00030\u0080\u00012\u0007\u0010\u00a7\u0001\u001a\u00020>H\u0002J\r\u0010\u00a8\u0001\u001a\u00020y*\u00020\nH\u0002J!\u0010\u00a9\u0001\u001a\u00020y*\u00020\n2\b\u0010\u00aa\u0001\u001a\u00030\u00ab\u00012\b\u0010\u00ac\u0001\u001a\u00030\u00ad\u0001H\u0002J\u0017\u0010\u00ae\u0001\u001a\u00020y*\u00020\n2\b\u0010\u00af\u0001\u001a\u00030\u00b0\u0001H\u0002J\r\u0010\u00b1\u0001\u001a\u00020y*\u00020\nH\u0002J\r\u0010\u00b2\u0001\u001a\u00020y*\u00020\nH\u0002J\u0016\u0010\u00b3\u0001\u001a\u00020y*\u00020\n2\u0007\u0010\u00b4\u0001\u001a\u00020>H\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u00020\f8\u0000@\u0000X\u0081.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0011\u001a\u00020\u00128\u0000@\u0000X\u0081.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0017\u001a\u00020\u00188\u0000@\u0000X\u0081.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001e\u0010\u001d\u001a\u00020\u001e8\u0000@\u0000X\u0081.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u000e\u0010#\u001a\u00020$X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\'\u001a\u00020(X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010)\u001a\u00020*8\u0000@\u0000X\u0081.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u0010\u0010/\u001a\u0004\u0018\u000100X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u00101\u001a\u0002028\u0000@\u0000X\u0081.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u001e\u00107\u001a\u0002088\u0000@\u0000X\u0081.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u000e\u0010=\u001a\u00020>X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020@X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020BX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010C\u001a\u00020D8\u0000@\u0000X\u0081.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\u001e\u0010I\u001a\u00020J8\u0000@\u0000X\u0081.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bK\u0010L\"\u0004\bM\u0010NR\u001e\u0010O\u001a\u00020P8\u0000@\u0000X\u0081.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010TR\u001e\u0010U\u001a\u00020V8\u0000@\u0000X\u0081.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bW\u0010X\"\u0004\bY\u0010ZR\u000e\u0010[\u001a\u00020@X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\\\u001a\u00020]8\u0000@\u0000X\u0081.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b^\u0010_\"\u0004\b`\u0010aR\u001e\u0010b\u001a\u00020c8\u0000@\u0000X\u0081.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bd\u0010e\"\u0004\bf\u0010gR\u001e\u0010h\u001a\u00020i8\u0000@\u0000X\u0081.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bj\u0010k\"\u0004\bl\u0010mR\u000e\u0010n\u001a\u00020oX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010p\u001a\u00020q8\u0000@\u0000X\u0081.\u00a2\u0006\u000e\n\u0000\u001a\u0004\br\u0010s\"\u0004\bt\u0010u\u00a8\u0006\u00b6\u0001"}, d2 = {"Lorg/wordpress/android/ui/posts/PostsListActivity;", "Lorg/wordpress/android/ui/LocaleAwareActivity;", "Lorg/wordpress/android/ui/posts/EditPostSettingsFragment$EditPostActivityHook;", "Lorg/wordpress/android/ui/posts/prepublishing/PrepublishingBottomSheetListener;", "Lorg/wordpress/android/ui/posts/BasicFragmentDialog$BasicDialogPositiveClickInterface;", "Lorg/wordpress/android/ui/posts/BasicFragmentDialog$BasicDialogNegativeClickInterface;", "Lorg/wordpress/android/ui/posts/BasicFragmentDialog$BasicDialogOnDismissByOutsideTouchInterface;", "Lorg/wordpress/android/ui/ScrollableViewInitializedListener;", "()V", "binding", "Lorg/wordpress/android/databinding/PostListActivityBinding;", "bloggingRemindersViewModel", "Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewModel;", "getBloggingRemindersViewModel$org_wordpress_android_wordpressJalapenoDebug", "()Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewModel;", "setBloggingRemindersViewModel$org_wordpress_android_wordpressJalapenoDebug", "(Lorg/wordpress/android/ui/bloggingreminders/BloggingRemindersViewModel;)V", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "getDispatcher$org_wordpress_android_wordpressJalapenoDebug", "()Lorg/wordpress/android/fluxc/Dispatcher;", "setDispatcher$org_wordpress_android_wordpressJalapenoDebug", "(Lorg/wordpress/android/fluxc/Dispatcher;)V", "editPostRepository", "Lorg/wordpress/android/ui/posts/EditPostRepository;", "getEditPostRepository$org_wordpress_android_wordpressJalapenoDebug", "()Lorg/wordpress/android/ui/posts/EditPostRepository;", "setEditPostRepository$org_wordpress_android_wordpressJalapenoDebug", "(Lorg/wordpress/android/ui/posts/EditPostRepository;)V", "mediaPickerLauncher", "Lorg/wordpress/android/ui/photopicker/MediaPickerLauncher;", "getMediaPickerLauncher$org_wordpress_android_wordpressJalapenoDebug", "()Lorg/wordpress/android/ui/photopicker/MediaPickerLauncher;", "setMediaPickerLauncher$org_wordpress_android_wordpressJalapenoDebug", "(Lorg/wordpress/android/ui/photopicker/MediaPickerLauncher;)V", "onPageChangeListener", "Landroidx/viewpager/widget/ViewPager$OnPageChangeListener;", "postListCreateMenuViewModel", "Lorg/wordpress/android/viewmodel/posts/PostListCreateMenuViewModel;", "postsPagerAdapter", "Lorg/wordpress/android/ui/posts/PostsPagerAdapter;", "previewStateHelper", "Lorg/wordpress/android/ui/posts/PreviewStateHelper;", "getPreviewStateHelper$org_wordpress_android_wordpressJalapenoDebug", "()Lorg/wordpress/android/ui/posts/PreviewStateHelper;", "setPreviewStateHelper$org_wordpress_android_wordpressJalapenoDebug", "(Lorg/wordpress/android/ui/posts/PreviewStateHelper;)V", "progressDialog", "Landroid/app/ProgressDialog;", "progressDialogHelper", "Lorg/wordpress/android/ui/posts/ProgressDialogHelper;", "getProgressDialogHelper$org_wordpress_android_wordpressJalapenoDebug", "()Lorg/wordpress/android/ui/posts/ProgressDialogHelper;", "setProgressDialogHelper$org_wordpress_android_wordpressJalapenoDebug", "(Lorg/wordpress/android/ui/posts/ProgressDialogHelper;)V", "remotePreviewLogicHelper", "Lorg/wordpress/android/ui/posts/RemotePreviewLogicHelper;", "getRemotePreviewLogicHelper$org_wordpress_android_wordpressJalapenoDebug", "()Lorg/wordpress/android/ui/posts/RemotePreviewLogicHelper;", "setRemotePreviewLogicHelper$org_wordpress_android_wordpressJalapenoDebug", "(Lorg/wordpress/android/ui/posts/RemotePreviewLogicHelper;)V", "restorePreviousSearch", "", "searchActionButton", "Landroid/view/MenuItem;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "getSiteStore$org_wordpress_android_wordpressJalapenoDebug", "()Lorg/wordpress/android/fluxc/store/SiteStore;", "setSiteStore$org_wordpress_android_wordpressJalapenoDebug", "(Lorg/wordpress/android/fluxc/store/SiteStore;)V", "snackbarSequencer", "Lorg/wordpress/android/util/SnackbarSequencer;", "getSnackbarSequencer$org_wordpress_android_wordpressJalapenoDebug", "()Lorg/wordpress/android/util/SnackbarSequencer;", "setSnackbarSequencer$org_wordpress_android_wordpressJalapenoDebug", "(Lorg/wordpress/android/util/SnackbarSequencer;)V", "storiesMediaPickerResultHandler", "Lorg/wordpress/android/ui/stories/StoriesMediaPickerResultHandler;", "getStoriesMediaPickerResultHandler$org_wordpress_android_wordpressJalapenoDebug", "()Lorg/wordpress/android/ui/stories/StoriesMediaPickerResultHandler;", "setStoriesMediaPickerResultHandler$org_wordpress_android_wordpressJalapenoDebug", "(Lorg/wordpress/android/ui/stories/StoriesMediaPickerResultHandler;)V", "systemNotificationTracker", "Lorg/wordpress/android/ui/notifications/SystemNotificationsTracker;", "getSystemNotificationTracker$org_wordpress_android_wordpressJalapenoDebug", "()Lorg/wordpress/android/ui/notifications/SystemNotificationsTracker;", "setSystemNotificationTracker$org_wordpress_android_wordpressJalapenoDebug", "(Lorg/wordpress/android/ui/notifications/SystemNotificationsTracker;)V", "toggleViewLayoutMenuItem", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelpers$org_wordpress_android_wordpressJalapenoDebug", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelpers$org_wordpress_android_wordpressJalapenoDebug", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "uploadActionUseCase", "Lorg/wordpress/android/ui/uploads/UploadActionUseCase;", "getUploadActionUseCase$org_wordpress_android_wordpressJalapenoDebug", "()Lorg/wordpress/android/ui/uploads/UploadActionUseCase;", "setUploadActionUseCase$org_wordpress_android_wordpressJalapenoDebug", "(Lorg/wordpress/android/ui/uploads/UploadActionUseCase;)V", "uploadUtilsWrapper", "Lorg/wordpress/android/ui/uploads/UploadUtilsWrapper;", "getUploadUtilsWrapper$org_wordpress_android_wordpressJalapenoDebug", "()Lorg/wordpress/android/ui/uploads/UploadUtilsWrapper;", "setUploadUtilsWrapper$org_wordpress_android_wordpressJalapenoDebug", "(Lorg/wordpress/android/ui/uploads/UploadUtilsWrapper;)V", "viewModel", "Lorg/wordpress/android/ui/posts/PostListMainViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory$org_wordpress_android_wordpressJalapenoDebug", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory$org_wordpress_android_wordpressJalapenoDebug", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "getEditPostRepository", "getSite", "initBloggingReminders", "", "initSearchFragment", "loadIntentData", "intent", "Landroid/content/Intent;", "onActivityResult", "requestCode", "", "resultCode", "data", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "onDismissByOutsideTouch", "instanceTag", "", "onNegativeClicked", "onNewIntent", "onOptionsItemSelected", "item", "onPositiveClicked", "onResume", "onSaveInstanceState", "outState", "onScrollableViewInitialized", "containerId", "onSubmitButtonClicked", "publishPost", "Lorg/wordpress/android/ui/posts/PublishPost;", "restartWhenSiteHasChanged", "setupActionBar", "setupActions", "showSnackBar", "holder", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "updateMenuIcon", "iconRes", "menuItem", "updateMenuTitle", "title", "Lorg/wordpress/android/ui/utils/UiString;", "initCreateMenuViewModel", "tabIndex", "actionsShownByDefault", "initSearchView", "initViewModel", "initPreviewState", "Lorg/wordpress/android/ui/posts/PostListRemotePreviewState;", "currentBottomSheetPostId", "Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$LocalId;", "loadViewState", "state", "Lorg/wordpress/android/ui/posts/PostListMainViewState;", "setupContent", "setupFabEvents", "toggleSearch", "isExpanded", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PostsListActivity extends org.wordpress.android.ui.LocaleAwareActivity implements org.wordpress.android.ui.posts.EditPostSettingsFragment.EditPostActivityHook, org.wordpress.android.ui.posts.prepublishing.PrepublishingBottomSheetListener, org.wordpress.android.ui.posts.BasicFragmentDialog.BasicDialogPositiveClickInterface, org.wordpress.android.ui.posts.BasicFragmentDialog.BasicDialogNegativeClickInterface, org.wordpress.android.ui.posts.BasicFragmentDialog.BasicDialogOnDismissByOutsideTouchInterface, org.wordpress.android.ui.ScrollableViewInitializedListener {
    @javax.inject.Inject()
    public org.wordpress.android.fluxc.store.SiteStore siteStore;
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelpers;
    @javax.inject.Inject()
    public org.wordpress.android.ui.posts.RemotePreviewLogicHelper remotePreviewLogicHelper;
    @javax.inject.Inject()
    public org.wordpress.android.ui.posts.PreviewStateHelper previewStateHelper;
    @javax.inject.Inject()
    public org.wordpress.android.ui.posts.ProgressDialogHelper progressDialogHelper;
    @javax.inject.Inject()
    public org.wordpress.android.fluxc.Dispatcher dispatcher;
    @javax.inject.Inject()
    public org.wordpress.android.ui.uploads.UploadActionUseCase uploadActionUseCase;
    @javax.inject.Inject()
    public org.wordpress.android.util.SnackbarSequencer snackbarSequencer;
    @javax.inject.Inject()
    public org.wordpress.android.ui.uploads.UploadUtilsWrapper uploadUtilsWrapper;
    @javax.inject.Inject()
    public org.wordpress.android.ui.notifications.SystemNotificationsTracker systemNotificationTracker;
    @javax.inject.Inject()
    public org.wordpress.android.ui.posts.EditPostRepository editPostRepository;
    @javax.inject.Inject()
    public org.wordpress.android.ui.photopicker.MediaPickerLauncher mediaPickerLauncher;
    @javax.inject.Inject()
    public org.wordpress.android.ui.stories.StoriesMediaPickerResultHandler storiesMediaPickerResultHandler;
    @javax.inject.Inject()
    public org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewModel bloggingRemindersViewModel;
    private org.wordpress.android.fluxc.model.SiteModel site;
    private org.wordpress.android.databinding.PostListActivityBinding binding;
    private org.wordpress.android.ui.posts.PostListMainViewModel viewModel;
    private org.wordpress.android.viewmodel.posts.PostListCreateMenuViewModel postListCreateMenuViewModel;
    private org.wordpress.android.ui.posts.PostsPagerAdapter postsPagerAdapter;
    private android.view.MenuItem searchActionButton;
    private android.view.MenuItem toggleViewLayoutMenuItem;
    private boolean restorePreviousSearch = false;
    private android.app.ProgressDialog progressDialog;
    private androidx.viewpager.widget.ViewPager.OnPageChangeListener onPageChangeListener;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.posts.PostsListActivity.Companion Companion = null;
    private static final java.lang.String BLOGGING_REMINDERS_FRAGMENT_TAG = "blogging_reminders_fragment_tag";
    private static final java.lang.String ACTIONS_SHOWN_BY_DEFAULT = "actions_shown_by_default";
    private static final java.lang.String TAB_INDEX = "tab_index";
    
    public PostsListActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.SiteStore getSiteStore$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    public final void setSiteStore$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.ViewModelProvider.Factory getViewModelFactory$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    public final void setViewModelFactory$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.ViewModelProvider.Factory p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiHelpers getUiHelpers$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    public final void setUiHelpers$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.posts.RemotePreviewLogicHelper getRemotePreviewLogicHelper$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    public final void setRemotePreviewLogicHelper$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.RemotePreviewLogicHelper p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.posts.PreviewStateHelper getPreviewStateHelper$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    public final void setPreviewStateHelper$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PreviewStateHelper p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.posts.ProgressDialogHelper getProgressDialogHelper$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    public final void setProgressDialogHelper$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.ProgressDialogHelper p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.Dispatcher getDispatcher$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    public final void setDispatcher$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.uploads.UploadActionUseCase getUploadActionUseCase$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    public final void setUploadActionUseCase$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.UploadActionUseCase p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.SnackbarSequencer getSnackbarSequencer$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    public final void setSnackbarSequencer$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.SnackbarSequencer p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.uploads.UploadUtilsWrapper getUploadUtilsWrapper$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    public final void setUploadUtilsWrapper$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.UploadUtilsWrapper p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.notifications.SystemNotificationsTracker getSystemNotificationTracker$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    public final void setSystemNotificationTracker$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.notifications.SystemNotificationsTracker p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.posts.EditPostRepository getEditPostRepository$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    public final void setEditPostRepository$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.EditPostRepository p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.photopicker.MediaPickerLauncher getMediaPickerLauncher$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    public final void setMediaPickerLauncher$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.photopicker.MediaPickerLauncher p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stories.StoriesMediaPickerResultHandler getStoriesMediaPickerResultHandler$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    public final void setStoriesMediaPickerResultHandler$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stories.StoriesMediaPickerResultHandler p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewModel getBloggingRemindersViewModel$org_wordpress_android_wordpressJalapenoDebug() {
        return null;
    }
    
    public final void setBloggingRemindersViewModel$org_wordpress_android_wordpressJalapenoDebug(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewModel p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.wordpress.android.fluxc.model.SiteModel getSite() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.wordpress.android.ui.posts.EditPostRepository getEditPostRepository() {
        return null;
    }
    
    @java.lang.Override()
    protected void onNewIntent(@org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
    }
    
    private final void restartWhenSiteHasChanged(android.content.Intent intent) {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupActionBar() {
    }
    
    private final void setupContent(org.wordpress.android.databinding.PostListActivityBinding $this$setupContent) {
    }
    
    private final void initCreateMenuViewModel(org.wordpress.android.databinding.PostListActivityBinding $this$initCreateMenuViewModel, int tabIndex, boolean actionsShownByDefault) {
    }
    
    private final void initViewModel(org.wordpress.android.databinding.PostListActivityBinding $this$initViewModel, org.wordpress.android.ui.posts.PostListRemotePreviewState initPreviewState, org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId currentBottomSheetPostId) {
    }
    
    private final void initBloggingReminders() {
    }
    
    private final void setupActions() {
    }
    
    private final void setupFabEvents(org.wordpress.android.databinding.PostListActivityBinding $this$setupFabEvents) {
    }
    
    private final void loadViewState(org.wordpress.android.databinding.PostListActivityBinding $this$loadViewState, org.wordpress.android.ui.posts.PostListMainViewState state) {
    }
    
    private final void showSnackBar(org.wordpress.android.ui.pages.SnackbarMessageHolder holder) {
    }
    
    private final void loadIntentData(android.content.Intent intent) {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onCreateOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu) {
        return false;
    }
    
    private final void initSearchFragment() {
    }
    
    private final void initSearchView(org.wordpress.android.databinding.PostListActivityBinding $this$initSearchView) {
    }
    
    private final void toggleSearch(org.wordpress.android.databinding.PostListActivityBinding $this$toggleSearch, boolean isExpanded) {
    }
    
    @java.lang.Override()
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    @java.lang.Override()
    public void onPositiveClicked(@org.jetbrains.annotations.NotNull()
    java.lang.String instanceTag) {
    }
    
    @java.lang.Override()
    public void onNegativeClicked(@org.jetbrains.annotations.NotNull()
    java.lang.String instanceTag) {
    }
    
    @java.lang.Override()
    public void onDismissByOutsideTouch(@org.jetbrains.annotations.NotNull()
    java.lang.String instanceTag) {
    }
    
    private final void updateMenuIcon(@androidx.annotation.DrawableRes()
    int iconRes, android.view.MenuItem menuItem) {
    }
    
    private final android.view.MenuItem updateMenuTitle(org.wordpress.android.ui.utils.UiString title, android.view.MenuItem menuItem) {
        return null;
    }
    
    @java.lang.Override()
    public void onSubmitButtonClicked(boolean publishPost) {
    }
    
    @java.lang.Override()
    public void onScrollableViewInitialized(int containerId) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final android.content.Intent buildIntent(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final android.content.Intent buildIntent(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PostListType postListType, boolean actionsShownByDefault, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.push.NotificationType notificationType) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J4\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/posts/PostsListActivity$Companion;", "", "()V", "ACTIONS_SHOWN_BY_DEFAULT", "", "BLOGGING_REMINDERS_FRAGMENT_TAG", "TAB_INDEX", "buildIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "postListType", "Lorg/wordpress/android/ui/posts/PostListType;", "actionsShownByDefault", "", "notificationType", "Lorg/wordpress/android/push/NotificationType;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final android.content.Intent buildIntent(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final android.content.Intent buildIntent(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.posts.PostListType postListType, boolean actionsShownByDefault, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.push.NotificationType notificationType) {
            return null;
        }
    }
}