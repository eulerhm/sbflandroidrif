package org.wordpress.android.ui.pages;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00f2\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u0087\u00012\u00020\u00012\u00020\u0002:\u0002\u0087\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J(\u0010K\u001a\u00020L2\b\b\u0002\u0010M\u001a\u00020N2\b\b\u0002\u0010O\u001a\u00020N2\n\b\u0002\u0010P\u001a\u0004\u0018\u00010NH\u0002J\"\u0010Q\u001a\u00020L2\u0006\u0010R\u001a\u00020S2\u0006\u0010T\u001a\u00020S2\b\u0010U\u001a\u0004\u0018\u00010VH\u0016J\u0012\u0010W\u001a\u00020L2\b\u0010X\u001a\u0004\u0018\u00010YH\u0016J\u0018\u0010Z\u001a\u00020L2\u0006\u0010[\u001a\u00020\\2\u0006\u0010]\u001a\u00020^H\u0016J\b\u0010_\u001a\u00020LH\u0016J\u000e\u0010`\u001a\u00020L2\u0006\u0010a\u001a\u00020NJ\u0018\u0010b\u001a\u00020L2\u0006\u0010c\u001a\u00020d2\u0006\u0010e\u001a\u00020dH\u0002J\u000e\u0010f\u001a\u00020L2\u0006\u0010a\u001a\u00020NJ\u0010\u0010g\u001a\u00020L2\u0006\u0010h\u001a\u00020YH\u0016J\u0010\u0010i\u001a\u00020L2\u0006\u0010j\u001a\u00020SH\u0016J\u000e\u0010k\u001a\u00020L2\u0006\u0010l\u001a\u00020dJ\u001a\u0010m\u001a\u00020L2\u0006\u0010n\u001a\u00020o2\b\u0010X\u001a\u0004\u0018\u00010YH\u0016J\u0018\u0010p\u001a\u00020L2\u0006\u0010q\u001a\u00020r2\u0006\u0010s\u001a\u00020tH\u0002J\u0012\u0010u\u001a\u00020L2\b\u0010v\u001a\u0004\u0018\u00010wH\u0002J\u0010\u0010x\u001a\u00020L2\u0006\u0010q\u001a\u00020rH\u0002J\u0010\u0010y\u001a\u00020L2\u0006\u0010q\u001a\u00020rH\u0002J\u001a\u0010z\u001a\u00020L2\u0006\u0010q\u001a\u00020r2\b\u0010{\u001a\u0004\u0018\u00010|H\u0002J\u0010\u0010}\u001a\u00020L2\u0006\u0010~\u001a\u00020\u007fH\u0002J\u0016\u0010\u0080\u0001\u001a\u00020L*\u00020\t2\u0007\u0010\u0081\u0001\u001a\u00020\u0005H\u0002J\r\u0010\u0082\u0001\u001a\u00020L*\u00020\tH\u0002J\u001f\u0010\u0083\u0001\u001a\u00020L*\u00020\t2\u0006\u0010q\u001a\u00020r2\b\u0010X\u001a\u0004\u0018\u00010YH\u0002J\u0015\u0010\u0084\u0001\u001a\u00020L*\u00020\t2\u0006\u0010q\u001a\u00020rH\u0003J\u0015\u0010\u0085\u0001\u001a\u00020L*\u00020\t2\u0006\u0010q\u001a\u00020rH\u0002J\u0016\u0010\u0086\u0001\u001a\u00020L*\u00020\t2\u0007\u0010\u0081\u0001\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u00020\u000b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R$\u0010\u0012\u001a\u00020\u00138\u0006@\u0006X\u0087.\u00a2\u0006\u0014\n\u0000\u0012\u0004\b\u0014\u0010\u0003\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001e\u0010\u0019\u001a\u00020\u001a8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010!\u001a\u00020\"8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001e\u0010\'\u001a\u00020(8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u000e\u0010-\u001a\u00020.X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u00101\u001a\u0002028\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u001e\u00107\u001a\u0002088\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u001e\u0010=\u001a\u00020>8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u000e\u0010C\u001a\u00020DX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010E\u001a\u00020F8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bG\u0010H\"\u0004\bI\u0010J\u00a8\u0006\u0088\u0001"}, d2 = {"Lorg/wordpress/android/ui/pages/PagesFragment;", "Landroidx/fragment/app/Fragment;", "Lorg/wordpress/android/ui/ScrollableViewInitializedListener;", "()V", "actionMenuItem", "Landroid/view/MenuItem;", "authorSelectionAdapter", "Lorg/wordpress/android/ui/posts/adapters/AuthorSelectionAdapter;", "binding", "Lorg/wordpress/android/databinding/PagesFragmentBinding;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "getDispatcher", "()Lorg/wordpress/android/fluxc/Dispatcher;", "setDispatcher", "(Lorg/wordpress/android/fluxc/Dispatcher;)V", "mlpViewModel", "Lorg/wordpress/android/viewmodel/mlp/ModalLayoutPickerViewModel;", "postStore", "Lorg/wordpress/android/fluxc/store/PostStore;", "getPostStore$annotations", "getPostStore", "()Lorg/wordpress/android/fluxc/store/PostStore;", "setPostStore", "(Lorg/wordpress/android/fluxc/store/PostStore;)V", "previewStateHelper", "Lorg/wordpress/android/ui/posts/PreviewStateHelper;", "getPreviewStateHelper", "()Lorg/wordpress/android/ui/posts/PreviewStateHelper;", "setPreviewStateHelper", "(Lorg/wordpress/android/ui/posts/PreviewStateHelper;)V", "progressDialog", "Landroid/app/ProgressDialog;", "progressDialogHelper", "Lorg/wordpress/android/ui/posts/ProgressDialogHelper;", "getProgressDialogHelper", "()Lorg/wordpress/android/ui/posts/ProgressDialogHelper;", "setProgressDialogHelper", "(Lorg/wordpress/android/ui/posts/ProgressDialogHelper;)V", "remotePreviewLogicHelper", "Lorg/wordpress/android/ui/posts/RemotePreviewLogicHelper;", "getRemotePreviewLogicHelper", "()Lorg/wordpress/android/ui/posts/RemotePreviewLogicHelper;", "setRemotePreviewLogicHelper", "(Lorg/wordpress/android/ui/posts/RemotePreviewLogicHelper;)V", "restorePreviousSearch", "", "swipeToRefreshHelper", "Lorg/wordpress/android/util/helpers/SwipeToRefreshHelper;", "uiHelpers", "Lorg/wordpress/android/ui/utils/UiHelpers;", "getUiHelpers", "()Lorg/wordpress/android/ui/utils/UiHelpers;", "setUiHelpers", "(Lorg/wordpress/android/ui/utils/UiHelpers;)V", "uploadActionUseCase", "Lorg/wordpress/android/ui/uploads/UploadActionUseCase;", "getUploadActionUseCase", "()Lorg/wordpress/android/ui/uploads/UploadActionUseCase;", "setUploadActionUseCase", "(Lorg/wordpress/android/ui/uploads/UploadActionUseCase;)V", "uploadUtilsWrapper", "Lorg/wordpress/android/ui/uploads/UploadUtilsWrapper;", "getUploadUtilsWrapper", "()Lorg/wordpress/android/ui/uploads/UploadUtilsWrapper;", "setUploadUtilsWrapper", "(Lorg/wordpress/android/ui/uploads/UploadUtilsWrapper;)V", "viewModel", "Lorg/wordpress/android/viewmodel/pages/PagesViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "createNewPage", "", "title", "", "content", "template", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onDestroyView", "onNegativeClickedForBasicDialog", "instanceTag", "onPageParentSet", "pageId", "", "parentId", "onPositiveClickedForBasicDialog", "onSaveInstanceState", "outState", "onScrollableViewInitialized", "containerId", "onSpecificPageRequested", "remotePageId", "onViewCreated", "view", "Landroid/view/View;", "previewPage", "activity", "Landroidx/fragment/app/FragmentActivity;", "post", "Lorg/wordpress/android/fluxc/model/PostModel;", "refreshProgressBars", "listState", "Lorg/wordpress/android/viewmodel/pages/PageListViewModel$PageListState;", "setupActions", "setupMlpObservers", "showSnackbarInActivity", "holder", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "showToast", "toastMessageHolder", "Lorg/wordpress/android/viewmodel/helpers/ToastMessageHolder;", "hideSearchList", "myActionMenuItem", "initializeSearchView", "initializeViewModelObservers", "initializeViews", "setupObservers", "showSearchList", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PagesFragment extends androidx.fragment.app.Fragment implements org.wordpress.android.ui.ScrollableViewInitializedListener {
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    private org.wordpress.android.viewmodel.pages.PagesViewModel viewModel;
    private org.wordpress.android.viewmodel.mlp.ModalLayoutPickerViewModel mlpViewModel;
    private org.wordpress.android.util.helpers.SwipeToRefreshHelper swipeToRefreshHelper;
    private android.view.MenuItem actionMenuItem;
    private org.wordpress.android.databinding.PagesFragmentBinding binding;
    
    /**
     * PostStore needs to be injected here as otherwise FluxC doesn't accept emitted events.
     */
    @javax.inject.Inject()
    public org.wordpress.android.fluxc.store.PostStore postStore;
    @javax.inject.Inject()
    public org.wordpress.android.fluxc.Dispatcher dispatcher;
    @javax.inject.Inject()
    public org.wordpress.android.ui.utils.UiHelpers uiHelpers;
    @javax.inject.Inject()
    public org.wordpress.android.ui.posts.RemotePreviewLogicHelper remotePreviewLogicHelper;
    @javax.inject.Inject()
    public org.wordpress.android.ui.posts.PreviewStateHelper previewStateHelper;
    @javax.inject.Inject()
    public org.wordpress.android.ui.posts.ProgressDialogHelper progressDialogHelper;
    @javax.inject.Inject()
    public org.wordpress.android.ui.uploads.UploadActionUseCase uploadActionUseCase;
    @javax.inject.Inject()
    public org.wordpress.android.ui.uploads.UploadUtilsWrapper uploadUtilsWrapper;
    private android.app.ProgressDialog progressDialog;
    private boolean restorePreviousSearch = false;
    private org.wordpress.android.ui.posts.adapters.AuthorSelectionAdapter authorSelectionAdapter;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.pages.PagesFragment.Companion Companion = null;
    
    public PagesFragment() {
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
    public final org.wordpress.android.fluxc.store.PostStore getPostStore() {
        return null;
    }
    
    /**
     * PostStore needs to be injected here as otherwise FluxC doesn't accept emitted events.
     */
    @kotlin.Suppress(names = {"unused"})
    @java.lang.Deprecated()
    public static void getPostStore$annotations() {
    }
    
    public final void setPostStore(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PostStore p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.Dispatcher getDispatcher() {
        return null;
    }
    
    public final void setDispatcher(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.utils.UiHelpers getUiHelpers() {
        return null;
    }
    
    public final void setUiHelpers(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.UiHelpers p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.posts.RemotePreviewLogicHelper getRemotePreviewLogicHelper() {
        return null;
    }
    
    public final void setRemotePreviewLogicHelper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.RemotePreviewLogicHelper p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.posts.PreviewStateHelper getPreviewStateHelper() {
        return null;
    }
    
    public final void setPreviewStateHelper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PreviewStateHelper p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.posts.ProgressDialogHelper getProgressDialogHelper() {
        return null;
    }
    
    public final void setProgressDialogHelper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.ProgressDialogHelper p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.uploads.UploadActionUseCase getUploadActionUseCase() {
        return null;
    }
    
    public final void setUploadActionUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.UploadActionUseCase p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.uploads.UploadUtilsWrapper getUploadUtilsWrapper() {
        return null;
    }
    
    public final void setUploadUtilsWrapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.UploadUtilsWrapper p0) {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    @java.lang.Override()
    public void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    @java.lang.Override()
    public void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    public final void onSpecificPageRequested(long remotePageId) {
    }
    
    private final void onPageParentSet(long pageId, long parentId) {
    }
    
    @android.annotation.SuppressLint(value = {"ClickableViewAccessibility"})
    private final void initializeViews(org.wordpress.android.databinding.PagesFragmentBinding $this$initializeViews, androidx.fragment.app.FragmentActivity activity) {
    }
    
    private final void initializeSearchView(org.wordpress.android.databinding.PagesFragmentBinding $this$initializeSearchView) {
    }
    
    private final void initializeViewModelObservers(org.wordpress.android.databinding.PagesFragmentBinding $this$initializeViewModelObservers, androidx.fragment.app.FragmentActivity activity, android.os.Bundle savedInstanceState) {
    }
    
    private final void showToast(org.wordpress.android.viewmodel.helpers.ToastMessageHolder toastMessageHolder) {
    }
    
    private final void previewPage(androidx.fragment.app.FragmentActivity activity, org.wordpress.android.fluxc.model.PostModel post) {
    }
    
    private final void setupObservers(org.wordpress.android.databinding.PagesFragmentBinding $this$setupObservers, androidx.fragment.app.FragmentActivity activity) {
    }
    
    private final void showSnackbarInActivity(androidx.fragment.app.FragmentActivity activity, org.wordpress.android.ui.pages.SnackbarMessageHolder holder) {
    }
    
    private final void setupActions(androidx.fragment.app.FragmentActivity activity) {
    }
    
    private final void setupMlpObservers(androidx.fragment.app.FragmentActivity activity) {
    }
    
    /**
     * Triggers new page creation
     * @param title the page title
     * @param content the page content
     * @param template the selected layout template
     */
    private final void createNewPage(java.lang.String title, java.lang.String content, java.lang.String template) {
    }
    
    @java.lang.Override()
    public void onCreateOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu, @org.jetbrains.annotations.NotNull()
    android.view.MenuInflater inflater) {
    }
    
    private final void refreshProgressBars(org.wordpress.android.viewmodel.pages.PageListViewModel.PageListState listState) {
    }
    
    private final void hideSearchList(org.wordpress.android.databinding.PagesFragmentBinding $this$hideSearchList, android.view.MenuItem myActionMenuItem) {
    }
    
    private final void showSearchList(org.wordpress.android.databinding.PagesFragmentBinding $this$showSearchList, android.view.MenuItem myActionMenuItem) {
    }
    
    public final void onPositiveClickedForBasicDialog(@org.jetbrains.annotations.NotNull()
    java.lang.String instanceTag) {
    }
    
    public final void onNegativeClickedForBasicDialog(@org.jetbrains.annotations.NotNull()
    java.lang.String instanceTag) {
    }
    
    @java.lang.Override()
    public void onScrollableViewInitialized(int containerId) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/pages/PagesFragment$Companion;", "", "()V", "newInstance", "Lorg/wordpress/android/ui/pages/PagesFragment;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.pages.PagesFragment newInstance() {
            return null;
        }
    }
}