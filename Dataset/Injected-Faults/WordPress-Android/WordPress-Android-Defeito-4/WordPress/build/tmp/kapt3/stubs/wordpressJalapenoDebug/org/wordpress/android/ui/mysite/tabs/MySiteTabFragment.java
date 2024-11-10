package org.wordpress.android.ui.mysite.tabs;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00f4\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u0090\u00012\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005:\u0002\u0090\u0001B\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020RJ\u000e\u0010S\u001a\u00020P2\u0006\u0010T\u001a\u00020UJ\u0010\u0010V\u001a\u00020P2\u0006\u0010W\u001a\u00020XH\u0002J\b\u0010Y\u001a\u00020PH\u0002J\b\u0010Z\u001a\u00020PH\u0002J\b\u0010[\u001a\u00020PH\u0002J\b\u0010\\\u001a\u00020PH\u0002J\"\u0010]\u001a\u00020P2\u0006\u0010^\u001a\u00020U2\u0006\u0010_\u001a\u00020U2\b\u0010`\u001a\u0004\u0018\u00010aH\u0016J\u0012\u0010b\u001a\u00020P2\b\u0010c\u001a\u0004\u0018\u00010dH\u0016J\u0012\u0010e\u001a\u00020P2\b\u0010f\u001a\u0004\u0018\u00010dH\u0016J\b\u0010g\u001a\u00020PH\u0016J\b\u0010h\u001a\u00020PH\u0016J\u0010\u0010i\u001a\u00020P2\u0006\u0010j\u001a\u00020kH\u0016J\b\u0010l\u001a\u00020PH\u0016J\u0010\u0010m\u001a\u00020P2\u0006\u0010j\u001a\u00020kH\u0016J\b\u0010n\u001a\u00020PH\u0016J\u0010\u0010o\u001a\u00020P2\u0006\u0010p\u001a\u00020dH\u0016J\u0018\u0010q\u001a\u00020P2\u0006\u0010r\u001a\u00020k2\u0006\u0010s\u001a\u00020UH\u0016J\u0010\u0010t\u001a\u00020P2\u0006\u0010s\u001a\u00020UH\u0016J\u000e\u0010u\u001a\u00020P2\u0006\u0010v\u001a\u00020wJ\u001a\u0010x\u001a\u00020P2\u0006\u0010y\u001a\u00020z2\b\u0010f\u001a\u0004\u0018\u00010dH\u0016J\u0010\u0010{\u001a\u00020P2\u0006\u0010Q\u001a\u00020|H\u0002J\u0010\u0010}\u001a\u00020P2\u0006\u0010~\u001a\u00020kH\u0002J3\u0010\u007f\u001a\u00020P2\t\b\u0001\u0010\u0080\u0001\u001a\u00020U2\b\b\u0001\u0010~\u001a\u00020U2\t\b\u0001\u0010\u0081\u0001\u001a\u00020U2\t\b\u0001\u0010\u0082\u0001\u001a\u00020UH\u0002J\u0013\u0010\u0083\u0001\u001a\u00020P2\b\u0010\u0084\u0001\u001a\u00030\u0085\u0001H\u0002J\u0013\u0010\u0086\u0001\u001a\u00020P2\b\u0010\u0087\u0001\u001a\u00030\u0088\u0001H\u0002J\r\u0010\u0089\u0001\u001a\u00020P*\u00020\bH\u0002J\u0017\u0010\u008a\u0001\u001a\u00020P*\u00020\b2\b\u0010\u008b\u0001\u001a\u00030\u008c\u0001H\u0002J\r\u0010\u008d\u0001\u001a\u00020P*\u00020\bH\u0002J\u0017\u0010\u008e\u0001\u001a\u00020P*\u00020\b2\b\u0010f\u001a\u0004\u0018\u00010dH\u0002J\r\u0010\u008f\u0001\u001a\u00020P*\u00020\bH\u0002R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0013\u001a\u00020\u00148\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001e\u0010\u0019\u001a\u00020\u001a8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001e\u0010\u001f\u001a\u00020 8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u000e\u0010%\u001a\u00020&X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\'\u001a\u00020(8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001e\u0010-\u001a\u00020.8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u001e\u00103\u001a\u0002048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u000e\u00109\u001a\u00020:X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010;\u001a\u00020<8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u001e\u0010A\u001a\u00020B8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\u000e\u0010G\u001a\u00020HX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010I\u001a\u00020J8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bK\u0010L\"\u0004\bM\u0010N\u00a8\u0006\u0091\u0001"}, d2 = {"Lorg/wordpress/android/ui/mysite/tabs/MySiteTabFragment;", "Landroidx/fragment/app/Fragment;", "Lorg/wordpress/android/ui/TextInputDialogFragment$Callback;", "Lorg/wordpress/android/ui/posts/QuickStartPromptDialogFragment$QuickStartPromptClickInterface;", "Lorg/wordpress/android/ui/FullScreenDialogFragment$OnConfirmListener;", "Lorg/wordpress/android/ui/FullScreenDialogFragment$OnDismissListener;", "()V", "binding", "Lorg/wordpress/android/databinding/MySiteTabFragmentBinding;", "bloggingPromptsCardAnalyticsTracker", "Lorg/wordpress/android/ui/mysite/cards/dashboard/bloggingprompts/BloggingPromptsCardAnalyticsTracker;", "getBloggingPromptsCardAnalyticsTracker", "()Lorg/wordpress/android/ui/mysite/cards/dashboard/bloggingprompts/BloggingPromptsCardAnalyticsTracker;", "setBloggingPromptsCardAnalyticsTracker", "(Lorg/wordpress/android/ui/mysite/cards/dashboard/bloggingprompts/BloggingPromptsCardAnalyticsTracker;)V", "dialogViewModel", "Lorg/wordpress/android/ui/posts/BasicDialogViewModel;", "dynamicCardMenuViewModel", "Lorg/wordpress/android/ui/mysite/dynamiccards/DynamicCardMenuViewModel;", "htmlCompatWrapper", "Lorg/wordpress/android/util/HtmlCompatWrapper;", "getHtmlCompatWrapper", "()Lorg/wordpress/android/util/HtmlCompatWrapper;", "setHtmlCompatWrapper", "(Lorg/wordpress/android/util/HtmlCompatWrapper;)V", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "getImageManager", "()Lorg/wordpress/android/util/image/ImageManager;", "setImageManager", "(Lorg/wordpress/android/util/image/ImageManager;)V", "mediaPickerLauncher", "Lorg/wordpress/android/ui/photopicker/MediaPickerLauncher;", "getMediaPickerLauncher", "()Lorg/wordpress/android/ui/photopicker/MediaPickerLauncher;", "setMediaPickerLauncher", "(Lorg/wordpress/android/ui/photopicker/MediaPickerLauncher;)V", "mySiteTabType", "Lorg/wordpress/android/ui/mysite/tabs/MySiteTabType;", "quickStartTracker", "Lorg/wordpress/android/ui/quickstart/QuickStartTracker;", "getQuickStartTracker", "()Lorg/wordpress/android/ui/quickstart/QuickStartTracker;", "setQuickStartTracker", "(Lorg/wordpress/android/ui/quickstart/QuickStartTracker;)V", "quickStartUtils", "Lorg/wordpress/android/util/QuickStartUtilsWrapper;", "getQuickStartUtils", "()Lorg/wordpress/android/util/QuickStartUtilsWrapper;", "setQuickStartUtils", "(Lorg/wordpress/android/util/QuickStartUtilsWrapper;)V", "snackbarSequencer", "Lorg/wordpress/android/util/SnackbarSequencer;", "getSnackbarSequencer", "()Lorg/wordpress/android/util/SnackbarSequencer;", "setSnackbarSequencer", "(Lorg/wordpress/android/util/SnackbarSequencer;)V", "swipeToRefreshHelper", "Lorg/wordpress/android/util/helpers/SwipeToRefreshHelper;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelpers", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "uploadUtilsWrapper", "Lorg/wordpress/android/ui/uploads/UploadUtilsWrapper;", "getUploadUtilsWrapper", "()Lorg/wordpress/android/ui/uploads/UploadUtilsWrapper;", "setUploadUtilsWrapper", "(Lorg/wordpress/android/ui/uploads/UploadUtilsWrapper;)V", "viewModel", "Lorg/wordpress/android/ui/mysite/MySiteViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "handleNavigationAction", "", "action", "Lorg/wordpress/android/ui/mysite/SiteNavigationAction;", "handleScrollTo", "scrollTo", "", "handleUploadedItem", "itemUploadedModel", "Lorg/wordpress/android/ui/mysite/SiteIconUploadHandler$ItemUploadedModel;", "initDagger", "initSoftKeyboard", "initTabType", "initViewModels", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onConfirm", "result", "Landroid/os/Bundle;", "onCreate", "savedInstanceState", "onDestroyView", "onDismiss", "onNegativeClicked", "instanceTag", "", "onPause", "onPositiveClicked", "onResume", "onSaveInstanceState", "outState", "onSuccessfulInput", "input", "callbackId", "onTextInputDialogDismissed", "onTrackWithTabSource", "event", "Lorg/wordpress/android/ui/mysite/MySiteViewModel$MySiteTrackWithTabSource;", "onViewCreated", "view", "Landroid/view/View;", "openQuickStartFullScreenDialog", "Lorg/wordpress/android/ui/mysite/SiteNavigationAction$OpenQuickStartFullScreenDialog;", "shareMessage", "message", "showQuickStartDialog", "title", "positiveButtonLabel", "negativeButtonLabel", "showSnackbar", "holder", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "startCropActivity", "imageUri", "Lorg/wordpress/android/util/UriWrapper;", "hideRefreshIndicatorIfNeeded", "loadData", "state", "Lorg/wordpress/android/ui/mysite/MySiteViewModel$State$SiteSelected;", "loadEmptyView", "setupContentViews", "setupObservers", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class MySiteTabFragment extends androidx.fragment.app.Fragment implements org.wordpress.android.ui.TextInputDialogFragment.Callback, org.wordpress.android.ui.posts.QuickStartPromptDialogFragment.QuickStartPromptClickInterface, org.wordpress.android.ui.FullScreenDialogFragment.OnConfirmListener, org.wordpress.android.ui.FullScreenDialogFragment.OnDismissListener {
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    @javax.inject.Inject()
    public org.wordpress.android.util.image.ImageManager imageManager;
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelpers;
    @javax.inject.Inject()
    public org.wordpress.android.ui.mysite.cards.dashboard.bloggingprompts.BloggingPromptsCardAnalyticsTracker bloggingPromptsCardAnalyticsTracker;
    @javax.inject.Inject()
    public org.wordpress.android.util.SnackbarSequencer snackbarSequencer;
    @javax.inject.Inject()
    public org.wordpress.android.ui.photopicker.MediaPickerLauncher mediaPickerLauncher;
    @javax.inject.Inject()
    public org.wordpress.android.ui.uploads.UploadUtilsWrapper uploadUtilsWrapper;
    @javax.inject.Inject()
    public org.wordpress.android.util.QuickStartUtilsWrapper quickStartUtils;
    @javax.inject.Inject()
    public org.wordpress.android.ui.quickstart.QuickStartTracker quickStartTracker;
    @javax.inject.Inject()
    public org.wordpress.android.util.HtmlCompatWrapper htmlCompatWrapper;
    private org.wordpress.android.ui.mysite.MySiteViewModel viewModel;
    private org.wordpress.android.ui.posts.BasicDialogViewModel dialogViewModel;
    private org.wordpress.android.ui.mysite.dynamiccards.DynamicCardMenuViewModel dynamicCardMenuViewModel;
    private org.wordpress.android.util.helpers.SwipeToRefreshHelper swipeToRefreshHelper;
    private org.wordpress.android.ui.mysite.tabs.MySiteTabType mySiteTabType;
    private org.wordpress.android.databinding.MySiteTabFragmentBinding binding;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.mysite.tabs.MySiteTabFragment.Companion Companion = null;
    private static final java.lang.String KEY_LIST_STATE = "key_list_state";
    private static final java.lang.String KEY_NESTED_LISTS_STATES = "key_nested_lists_states";
    private static final java.lang.String TAG_QUICK_START_DIALOG = "TAG_QUICK_START_DIALOG";
    private static final java.lang.String KEY_MY_SITE_TAB_TYPE = "key_my_site_tab_type";
    private static final long CHECK_REFRESH_DELAY = 300L;
    
    public MySiteTabFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.ViewModelProvider.Factory getViewModelFactory() {
        return null;
    }
    
    public final void setViewModelFactory(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.ViewModelProvider.Factory p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.image.ImageManager getImageManager() {
        return null;
    }
    
    public final void setImageManager(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiHelpers getUiHelpers() {
        return null;
    }
    
    public final void setUiHelpers(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.mysite.cards.dashboard.bloggingprompts.BloggingPromptsCardAnalyticsTracker getBloggingPromptsCardAnalyticsTracker() {
        return null;
    }
    
    public final void setBloggingPromptsCardAnalyticsTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.cards.dashboard.bloggingprompts.BloggingPromptsCardAnalyticsTracker p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.SnackbarSequencer getSnackbarSequencer() {
        return null;
    }
    
    public final void setSnackbarSequencer(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.SnackbarSequencer p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.photopicker.MediaPickerLauncher getMediaPickerLauncher() {
        return null;
    }
    
    public final void setMediaPickerLauncher(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.photopicker.MediaPickerLauncher p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.uploads.UploadUtilsWrapper getUploadUtilsWrapper() {
        return null;
    }
    
    public final void setUploadUtilsWrapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.UploadUtilsWrapper p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.QuickStartUtilsWrapper getQuickStartUtils() {
        return null;
    }
    
    public final void setQuickStartUtils(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.QuickStartUtilsWrapper p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.quickstart.QuickStartTracker getQuickStartTracker() {
        return null;
    }
    
    public final void setQuickStartTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.quickstart.QuickStartTracker p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.HtmlCompatWrapper getHtmlCompatWrapper() {
        return null;
    }
    
    public final void setHtmlCompatWrapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.HtmlCompatWrapper p0) {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initSoftKeyboard() {
    }
    
    private final void initDagger() {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initViewModels() {
    }
    
    private final void initTabType() {
    }
    
    private final void setupContentViews(org.wordpress.android.databinding.MySiteTabFragmentBinding $this$setupContentViews, android.os.Bundle savedInstanceState) {
    }
    
    @kotlin.Suppress(names = {"LongMethod"})
    private final void setupObservers(org.wordpress.android.databinding.MySiteTabFragmentBinding $this$setupObservers) {
    }
    
    @kotlin.Suppress(names = {"ComplexMethod", "LongMethod"})
    public final void handleNavigationAction(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.SiteNavigationAction action) {
    }
    
    private final void openQuickStartFullScreenDialog(org.wordpress.android.ui.mysite.SiteNavigationAction.OpenQuickStartFullScreenDialog action) {
    }
    
    private final void handleUploadedItem(org.wordpress.android.ui.mysite.SiteIconUploadHandler.ItemUploadedModel itemUploadedModel) {
    }
    
    private final void startCropActivity(org.wordpress.android.util.UriWrapper imageUri) {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    @java.lang.Override()
    public void onPause() {
    }
    
    @java.lang.Override()
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    @kotlin.Suppress(names = {"ReturnCount", "LongMethod", "ComplexMethod"})
    @java.lang.Override()
    public void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    private final void showQuickStartDialog(@androidx.annotation.StringRes()
    int title, @androidx.annotation.StringRes()
    int message, @androidx.annotation.StringRes()
    int positiveButtonLabel, @androidx.annotation.StringRes()
    int negativeButtonLabel) {
    }
    
    private final void loadData(org.wordpress.android.databinding.MySiteTabFragmentBinding $this$loadData, org.wordpress.android.ui.mysite.MySiteViewModel.State.SiteSelected state) {
    }
    
    private final void loadEmptyView(org.wordpress.android.databinding.MySiteTabFragmentBinding $this$loadEmptyView) {
    }
    
    private final void showSnackbar(org.wordpress.android.ui.pages.SnackbarMessageHolder holder) {
    }
    
    private final void hideRefreshIndicatorIfNeeded(org.wordpress.android.databinding.MySiteTabFragmentBinding $this$hideRefreshIndicatorIfNeeded) {
    }
    
    private final void shareMessage(java.lang.String message) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmStatic()
    public static final org.wordpress.android.ui.mysite.tabs.MySiteTabFragment newInstance(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.tabs.MySiteTabType mySiteTabType) {
        return null;
    }
    
    @java.lang.Override()
    public void onSuccessfulInput(@org.jetbrains.annotations.NotNull()
    java.lang.String input, int callbackId) {
    }
    
    @java.lang.Override()
    public void onTextInputDialogDismissed(int callbackId) {
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
    public void onConfirm(@org.jetbrains.annotations.Nullable()
    android.os.Bundle result) {
    }
    
    @java.lang.Override()
    public void onDismiss() {
    }
    
    public final void handleScrollTo(int scrollTo) {
    }
    
    public final void onTrackWithTabSource(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.MySiteViewModel.MySiteTrackWithTabSource event) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/ui/mysite/tabs/MySiteTabFragment$Companion;", "", "()V", "CHECK_REFRESH_DELAY", "", "KEY_LIST_STATE", "", "KEY_MY_SITE_TAB_TYPE", "KEY_NESTED_LISTS_STATES", "TAG_QUICK_START_DIALOG", "newInstance", "Lorg/wordpress/android/ui/mysite/tabs/MySiteTabFragment;", "mySiteTabType", "Lorg/wordpress/android/ui/mysite/tabs/MySiteTabType;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @kotlin.jvm.JvmStatic()
        public final org.wordpress.android.ui.mysite.tabs.MySiteTabFragment newInstance(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.mysite.tabs.MySiteTabType mySiteTabType) {
            return null;
        }
    }
}