package org.wordpress.android.ui.posts.editor.media;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0092\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001zB\u0083\u0001\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u0012\u0006\u0010\u001a\u001a\u00020\u001b\u0012\b\b\u0001\u0010\u001c\u001a\u00020\u001d\u0012\b\b\u0001\u0010\u001e\u001a\u00020\u001d\u00a2\u0006\u0002\u0010\u001fJ\u001c\u0010E\u001a\u00020F2\f\u0010G\u001a\b\u0012\u0004\u0012\u00020I0H2\u0006\u0010J\u001a\u00020KJ\u0016\u0010E\u001a\u00020F2\u0006\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020MJ\u001c\u0010E\u001a\u00020F2\u0006\u0010J\u001a\u00020K2\f\u0010N\u001a\b\u0012\u0004\u0012\u00020O0HJ\u0006\u0010P\u001a\u00020FJ\u000e\u0010Q\u001a\u00020F2\u0006\u0010R\u001a\u00020SJ\u001c\u0010T\u001a\u00020F2\f\u0010U\u001a\b\u0012\u0004\u0012\u0002020H2\u0006\u0010V\u001a\u00020WJ\u0016\u0010X\u001a\u00020F2\u0006\u0010Y\u001a\u0002022\u0006\u0010V\u001a\u00020WJ\u0014\u0010Z\u001a\u00020F2\f\u0010U\u001a\b\u0012\u0004\u0012\u0002020HJ\u0006\u0010[\u001a\u00020FJ\u0016\u0010\\\u001a\u00020F2\u0006\u0010]\u001a\u00020^2\u0006\u0010_\u001a\u00020WJ\u0006\u0010`\u001a\u00020FJ\u001e\u0010a\u001a\u00020F2\u0006\u0010b\u001a\u00020W2\u0006\u0010c\u001a\u00020W2\u0006\u0010]\u001a\u00020/J\u001e\u0010d\u001a\u00020:2\u0006\u0010e\u001a\u00020f2\u0006\u0010g\u001a\u00020I2\u0006\u0010h\u001a\u00020iJ\u0014\u0010j\u001a\u00020F2\f\u0010U\u001a\b\u0012\u0004\u0012\u0002020HJ\u0006\u0010k\u001a\u00020FJ\u001e\u0010l\u001a\u00020F2\u0006\u0010m\u001a\u00020n2\u0006\u0010o\u001a\u00020W2\u0006\u0010p\u001a\u00020fJ\u0006\u0010q\u001a\u00020FJ\u0014\u0010r\u001a\u00020F2\f\u0010s\u001a\b\u0012\u0004\u0012\u00020^0HJ\u0016\u0010t\u001a\u00020F2\u0006\u0010;\u001a\u00020<2\u0006\u00107\u001a\u000208J\u000e\u0010u\u001a\u00020F2\u0006\u0010]\u001a\u00020/J\u001a\u0010v\u001a\u0004\u0018\u00010I2\u0006\u0010w\u001a\u0002022\u0006\u0010x\u001a\u00020yH\u0007R\u001a\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\"0!X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020&0\"0%X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\'\u001a\b\u0012\u0004\u0012\u00020(0!X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001dX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010)\u001a\u00020*8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b+\u0010,R\u0014\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u00100\u001a\b\u0012\u0004\u0012\u00020201X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u000e\u00107\u001a\u000208X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020:X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020<X\u0082.\u00a2\u0006\u0002\n\u0000R\u001d\u0010=\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\"0>\u00a2\u0006\b\n\u0000\u001a\u0004\b?\u0010@R\u001d\u0010A\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020&0\"0>\u00a2\u0006\b\n\u0000\u001a\u0004\bB\u0010@R\u0017\u0010C\u001a\b\u0012\u0004\u0012\u00020(0>\u00a2\u0006\b\n\u0000\u001a\u0004\bD\u0010@R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006{"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/media/EditorMedia;", "Lkotlinx/coroutines/CoroutineScope;", "updateMediaModelUseCase", "Lorg/wordpress/android/ui/posts/editor/media/UpdateMediaModelUseCase;", "getMediaModelUseCase", "Lorg/wordpress/android/ui/posts/editor/media/GetMediaModelUseCase;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "mediaUtilsWrapper", "Lorg/wordpress/android/util/MediaUtilsWrapper;", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "addLocalMediaToPostUseCase", "Lorg/wordpress/android/ui/posts/editor/media/AddLocalMediaToPostUseCase;", "addExistingMediaToPostUseCase", "Lorg/wordpress/android/ui/posts/editor/media/AddExistingMediaToPostUseCase;", "retryFailedMediaUploadUseCase", "Lorg/wordpress/android/ui/posts/editor/media/RetryFailedMediaUploadUseCase;", "cleanUpMediaToPostAssociationUseCase", "Lorg/wordpress/android/ui/posts/editor/media/CleanUpMediaToPostAssociationUseCase;", "removeMediaUseCase", "Lorg/wordpress/android/ui/posts/editor/media/RemoveMediaUseCase;", "reattachUploadingMediaUseCase", "Lorg/wordpress/android/ui/posts/editor/media/ReattachUploadingMediaUseCase;", "analyticsUtilsWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsUtilsWrapper;", "analyticsTrackerWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "bgDispatcher", "(Lorg/wordpress/android/ui/posts/editor/media/UpdateMediaModelUseCase;Lorg/wordpress/android/ui/posts/editor/media/GetMediaModelUseCase;Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/util/MediaUtilsWrapper;Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/ui/posts/editor/media/AddLocalMediaToPostUseCase;Lorg/wordpress/android/ui/posts/editor/media/AddExistingMediaToPostUseCase;Lorg/wordpress/android/ui/posts/editor/media/RetryFailedMediaUploadUseCase;Lorg/wordpress/android/ui/posts/editor/media/CleanUpMediaToPostAssociationUseCase;Lorg/wordpress/android/ui/posts/editor/media/RemoveMediaUseCase;Lorg/wordpress/android/ui/posts/editor/media/ReattachUploadingMediaUseCase;Lorg/wordpress/android/util/analytics/AnalyticsUtilsWrapper;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_snackBarMessage", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "_toastMessage", "Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "Lorg/wordpress/android/viewmodel/helpers/ToastMessageHolder;", "_uiState", "Lorg/wordpress/android/ui/posts/editor/media/EditorMedia$AddMediaToPostUiState;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "deletedMediaItemIds", "", "", "droppedMediaUris", "Ljava/util/ArrayList;", "Landroid/net/Uri;", "getDroppedMediaUris", "()Ljava/util/ArrayList;", "setDroppedMediaUris", "(Ljava/util/ArrayList;)V", "editorMediaListener", "Lorg/wordpress/android/ui/posts/editor/media/EditorMediaListener;", "job", "Lkotlinx/coroutines/Job;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "snackBarMessage", "Landroidx/lifecycle/LiveData;", "getSnackBarMessage", "()Landroidx/lifecycle/LiveData;", "toastMessage", "getToastMessage", "uiState", "getUiState", "addExistingMediaToEditorAsync", "", "mediaModels", "", "Lorg/wordpress/android/fluxc/model/MediaModel;", "source", "Lorg/wordpress/android/ui/posts/editor/media/AddExistingMediaSource;", "mediaIds", "", "mediaIdList", "", "addFreshlyTakenVideoToEditor", "addGifMediaToPostAsync", "localMediaIds", "", "addNewMediaItemsToEditorAsync", "uriList", "freshlyTaken", "", "addNewMediaToEditorAsync", "mediaUri", "advertiseImageOptimisationAndAddMedia", "cancelAddMediaToEditorActions", "cancelMediaUploadAsync", "localMediaId", "", "delete", "definitelyDeleteBackspaceDeletedMediaItemsAsync", "onMediaDeleted", "showAztecEditor", "showGutenbergEditor", "onMediaUploadError", "listener", "Lorg/wordpress/android/editor/EditorMediaUploadListener;", "media", "error", "Lorg/wordpress/android/fluxc/store/MediaStore$MediaError;", "onPhotoPickerMediaChosen", "purgeMediaToPostAssociationsIfNotInPostAnymoreAsync", "reattachUploadingMediaForAztec", "editPostRepository", "Lorg/wordpress/android/ui/posts/EditPostRepository;", "isAztec", "editorMediaUploadListener", "refreshBlogMedia", "retryFailedMediaAsync", "failedMediaIds", "start", "updateDeletedMediaItemIds", "updateMediaUploadStateBlocking", "uri", "mediaUploadState", "Lorg/wordpress/android/fluxc/model/MediaModel$MediaUploadState;", "AddMediaToPostUiState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class EditorMedia implements kotlinx.coroutines.CoroutineScope {
    private final org.wordpress.android.ui.posts.editor.media.UpdateMediaModelUseCase updateMediaModelUseCase = null;
    private final org.wordpress.android.ui.posts.editor.media.GetMediaModelUseCase getMediaModelUseCase = null;
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.util.MediaUtilsWrapper mediaUtilsWrapper = null;
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final org.wordpress.android.ui.posts.editor.media.AddLocalMediaToPostUseCase addLocalMediaToPostUseCase = null;
    private final org.wordpress.android.ui.posts.editor.media.AddExistingMediaToPostUseCase addExistingMediaToPostUseCase = null;
    private final org.wordpress.android.ui.posts.editor.media.RetryFailedMediaUploadUseCase retryFailedMediaUploadUseCase = null;
    private final org.wordpress.android.ui.posts.editor.media.CleanUpMediaToPostAssociationUseCase cleanUpMediaToPostAssociationUseCase = null;
    private final org.wordpress.android.ui.posts.editor.media.RemoveMediaUseCase removeMediaUseCase = null;
    private final org.wordpress.android.ui.posts.editor.media.ReattachUploadingMediaUseCase reattachUploadingMediaUseCase = null;
    private final org.wordpress.android.util.analytics.AnalyticsUtilsWrapper analyticsUtilsWrapper = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper = null;
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private kotlinx.coroutines.Job job;
    private org.wordpress.android.fluxc.model.SiteModel site;
    private org.wordpress.android.ui.posts.editor.media.EditorMediaListener editorMediaListener;
    private final java.util.List<java.lang.String> deletedMediaItemIds = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.posts.editor.media.EditorMedia.AddMediaToPostUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.posts.editor.media.EditorMedia.AddMediaToPostUiState> uiState = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> _snackBarMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> snackBarMessage = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.viewmodel.Event<org.wordpress.android.viewmodel.helpers.ToastMessageHolder>> _toastMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.viewmodel.helpers.ToastMessageHolder>> toastMessage = null;
    @org.jetbrains.annotations.NotNull()
    private java.util.ArrayList<android.net.Uri> droppedMediaUris;
    
    @javax.inject.Inject()
    public EditorMedia(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.UpdateMediaModelUseCase updateMediaModelUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.GetMediaModelUseCase getMediaModelUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.MediaUtilsWrapper mediaUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.AddLocalMediaToPostUseCase addLocalMediaToPostUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.AddExistingMediaToPostUseCase addExistingMediaToPostUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.RetryFailedMediaUploadUseCase retryFailedMediaUploadUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.CleanUpMediaToPostAssociationUseCase cleanUpMediaToPostAssociationUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.RemoveMediaUseCase removeMediaUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.ReattachUploadingMediaUseCase reattachUploadingMediaUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsUtilsWrapper analyticsUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlin.coroutines.CoroutineContext getCoroutineContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.posts.editor.media.EditorMedia.AddMediaToPostUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> getSnackBarMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.viewmodel.helpers.ToastMessageHolder>> getToastMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<android.net.Uri> getDroppedMediaUris() {
        return null;
    }
    
    public final void setDroppedMediaUris(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<android.net.Uri> p0) {
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.EditorMediaListener editorMediaListener) {
    }
    
    public final void advertiseImageOptimisationAndAddMedia(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends android.net.Uri> uriList) {
    }
    
    public final void addNewMediaToEditorAsync(@org.jetbrains.annotations.NotNull()
    android.net.Uri mediaUri, boolean freshlyTaken) {
    }
    
    public final void addNewMediaItemsToEditorAsync(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends android.net.Uri> uriList, boolean freshlyTaken) {
    }
    
    /**
     * This won't create a MediaModel. It assumes the model was already created.
     */
    public final void addGifMediaToPostAsync(@org.jetbrains.annotations.NotNull()
    int[] localMediaIds) {
    }
    
    public final void addFreshlyTakenVideoToEditor() {
    }
    
    public final void onPhotoPickerMediaChosen(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends android.net.Uri> uriList) {
    }
    
    public final void addExistingMediaToEditorAsync(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.fluxc.model.MediaModel> mediaModels, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.AddExistingMediaSource source) {
    }
    
    public final void addExistingMediaToEditorAsync(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.AddExistingMediaSource source, @org.jetbrains.annotations.NotNull()
    long[] mediaIds) {
    }
    
    public final void addExistingMediaToEditorAsync(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.AddExistingMediaSource source, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Long> mediaIdList) {
    }
    
    public final void cancelMediaUploadAsync(int localMediaId, boolean delete) {
    }
    
    public final void refreshBlogMedia() {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Deprecated()
    public final org.wordpress.android.fluxc.model.MediaModel updateMediaUploadStateBlocking(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.MediaModel.MediaUploadState mediaUploadState) {
        return null;
    }
    
    public final void retryFailedMediaAsync(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Integer> failedMediaIds) {
    }
    
    public final void purgeMediaToPostAssociationsIfNotInPostAnymoreAsync() {
    }
    
    public final void reattachUploadingMediaForAztec(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.EditPostRepository editPostRepository, boolean isAztec, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.editor.EditorMediaUploadListener editorMediaUploadListener) {
    }
    
    public final void definitelyDeleteBackspaceDeletedMediaItemsAsync() {
    }
    
    public final void updateDeletedMediaItemIds(@org.jetbrains.annotations.NotNull()
    java.lang.String localMediaId) {
    }
    
    public final void cancelAddMediaToEditorActions() {
    }
    
    public final void onMediaDeleted(boolean showAztecEditor, boolean showGutenbergEditor, @org.jetbrains.annotations.NotNull()
    java.lang.String localMediaId) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job onMediaUploadError(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.editor.EditorMediaUploadListener listener, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.MediaModel media, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.MediaStore.MediaError error) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u000b\f\rB\u0017\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0082\u0001\u0003\u000e\u000f\u0010\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/media/EditorMedia$AddMediaToPostUiState;", "", "editorOverlayVisibility", "", "progressDialogUiState", "Lorg/wordpress/android/ui/posts/ProgressDialogUiState;", "(ZLorg/wordpress/android/ui/posts/ProgressDialogUiState;)V", "getEditorOverlayVisibility", "()Z", "getProgressDialogUiState", "()Lorg/wordpress/android/ui/posts/ProgressDialogUiState;", "AddingMediaIdle", "AddingMultipleMedia", "AddingSingleMedia", "Lorg/wordpress/android/ui/posts/editor/media/EditorMedia$AddMediaToPostUiState$AddingMultipleMedia;", "Lorg/wordpress/android/ui/posts/editor/media/EditorMedia$AddMediaToPostUiState$AddingSingleMedia;", "Lorg/wordpress/android/ui/posts/editor/media/EditorMedia$AddMediaToPostUiState$AddingMediaIdle;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class AddMediaToPostUiState {
        private final boolean editorOverlayVisibility = false;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.posts.ProgressDialogUiState progressDialogUiState = null;
        
        private AddMediaToPostUiState(boolean editorOverlayVisibility, org.wordpress.android.ui.posts.ProgressDialogUiState progressDialogUiState) {
            super();
        }
        
        public final boolean getEditorOverlayVisibility() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.posts.ProgressDialogUiState getProgressDialogUiState() {
            return null;
        }
        
        /**
         * Adding multiple media items at once can take several seconds on slower devices, so we show a blocking
         * progress dialog in this situation - otherwise the user could accidentally back out of the process
         * before all items were added
         */
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/media/EditorMedia$AddMediaToPostUiState$AddingMultipleMedia;", "Lorg/wordpress/android/ui/posts/editor/media/EditorMedia$AddMediaToPostUiState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class AddingMultipleMedia extends org.wordpress.android.ui.posts.editor.media.EditorMedia.AddMediaToPostUiState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.posts.editor.media.EditorMedia.AddMediaToPostUiState.AddingMultipleMedia INSTANCE = null;
            
            private AddingMultipleMedia() {
                super(false, null);
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/media/EditorMedia$AddMediaToPostUiState$AddingSingleMedia;", "Lorg/wordpress/android/ui/posts/editor/media/EditorMedia$AddMediaToPostUiState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class AddingSingleMedia extends org.wordpress.android.ui.posts.editor.media.EditorMedia.AddMediaToPostUiState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.posts.editor.media.EditorMedia.AddMediaToPostUiState.AddingSingleMedia INSTANCE = null;
            
            private AddingSingleMedia() {
                super(false, null);
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/media/EditorMedia$AddMediaToPostUiState$AddingMediaIdle;", "Lorg/wordpress/android/ui/posts/editor/media/EditorMedia$AddMediaToPostUiState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class AddingMediaIdle extends org.wordpress.android.ui.posts.editor.media.EditorMedia.AddMediaToPostUiState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.posts.editor.media.EditorMedia.AddMediaToPostUiState.AddingMediaIdle INSTANCE = null;
            
            private AddingMediaIdle() {
                super(false, null);
            }
        }
    }
}