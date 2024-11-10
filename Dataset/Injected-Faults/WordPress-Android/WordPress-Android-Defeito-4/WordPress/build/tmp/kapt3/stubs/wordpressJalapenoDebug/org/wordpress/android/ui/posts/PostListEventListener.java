package org.wordpress.android.ui.posts;

import java.lang.System;

/**
 * This is a temporary class to make the PostListViewModel more manageable. Please feel free to refactor it any way
 * you see fit.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00ce\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002:\u0001FB\u00d5\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010\u0012\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00110\u0013\u0012\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00110\u0013\u0012\u0018\u0010\u0017\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u0018\u0012\u0004\u0012\u00020\u00110\u0013\u0012\u0018\u0010\u001a\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u0018\u0012\u0004\u0012\u00020\u00110\u0013\u0012\u0018\u0010\u001c\u001a\u0014\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00110\u001d\u0012\f\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u0010\u0012\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020!0\u0010\u00a2\u0006\u0002\u0010#J\u0014\u0010*\u001a\u00020\u00112\n\u0010+\u001a\u00020,\"\u00020\u001bH\u0002J\u0018\u0010-\u001a\u00020\u00112\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020!H\u0002J\u0012\u00101\u001a\u00020!2\b\u0010.\u001a\u0004\u0018\u00010/H\u0002J\b\u00102\u001a\u00020\u0011H\u0003J\u0010\u00103\u001a\u00020\u00112\u0006\u00104\u001a\u000205H\u0007J\u0010\u00103\u001a\u00020\u00112\u0006\u00104\u001a\u000206H\u0007J\u0010\u00103\u001a\u00020\u00112\u0006\u00104\u001a\u000207H\u0007J\u0010\u00103\u001a\u00020\u00112\u0006\u00104\u001a\u000208H\u0007J\u0010\u00103\u001a\u00020\u00112\u0006\u00104\u001a\u000209H\u0007J\u0010\u00103\u001a\u00020\u00112\u0006\u00104\u001a\u00020:H\u0007J\u0010\u0010;\u001a\u00020\u00112\u0006\u00104\u001a\u00020<H\u0007J\u0010\u0010=\u001a\u00020\u00112\u0006\u00104\u001a\u00020>H\u0007J\u0010\u0010?\u001a\u00020\u00112\u0006\u00104\u001a\u00020@H\u0007J\u0010\u0010A\u001a\u00020\u00112\u0006\u00104\u001a\u00020BH\u0007J\u0014\u0010C\u001a\u00020\u00112\n\u0010D\u001a\u00020E\"\u00020\u0019H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010$\u001a\u00020%8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b&\u0010\'R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00110\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020!0\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u001a\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u0018\u0012\u0004\u0012\u00020\u00110\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0017\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u0018\u0012\u0004\u0012\u00020\u00110\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00110\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u001c\u001a\u0014\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00110\u001dX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006G"}, d2 = {"Lorg/wordpress/android/ui/posts/PostListEventListener;", "Landroidx/lifecycle/LifecycleObserver;", "Lkotlinx/coroutines/CoroutineScope;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "postStore", "Lorg/wordpress/android/fluxc/store/PostStore;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "postActionHandler", "Lorg/wordpress/android/ui/posts/PostActionHandler;", "handlePostUpdatedWithoutError", "Lkotlin/Function0;", "", "handlePostUploadedWithoutError", "Lkotlin/Function1;", "Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$LocalId;", "triggerPostUploadAction", "Lorg/wordpress/android/ui/posts/PostUploadAction;", "invalidateUploadStatus", "", "", "invalidateFeaturedMedia", "", "triggerPreviewStateUpdate", "Lkotlin/Function2;", "Lorg/wordpress/android/ui/posts/PostListRemotePreviewState;", "Lorg/wordpress/android/ui/posts/PostInfoType;", "isRemotePreviewingFromPostsList", "", "hasRemoteAutoSavePreviewError", "(Landroidx/lifecycle/Lifecycle;Lorg/wordpress/android/fluxc/Dispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/fluxc/store/PostStore;Lorg/wordpress/android/fluxc/model/SiteModel;Lorg/wordpress/android/ui/posts/PostActionHandler;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "job", "Lkotlinx/coroutines/Job;", "featuredMediaChanged", "featuredImageIds", "", "handleRemoteAutoSave", "post", "Lorg/wordpress/android/fluxc/model/PostModel;", "isError", "isRemotePreviewingFromEditor", "onDestroy", "onEventBackgroundThread", "event", "Lorg/wordpress/android/ui/uploads/PostEvents$PostUploadCanceled;", "Lorg/wordpress/android/ui/uploads/PostEvents$PostUploadStarted;", "Lorg/wordpress/android/ui/uploads/ProgressEvent;", "Lorg/wordpress/android/ui/uploads/UploadService$UploadErrorEvent;", "Lorg/wordpress/android/ui/uploads/UploadService$UploadMediaRetryEvent;", "Lorg/wordpress/android/ui/uploads/UploadService$UploadMediaSuccessEvent;", "onMediaChanged", "Lorg/wordpress/android/fluxc/store/MediaStore$OnMediaChanged;", "onMediaUploaded", "Lorg/wordpress/android/fluxc/store/MediaStore$OnMediaUploaded;", "onPostChanged", "Lorg/wordpress/android/fluxc/store/PostStore$OnPostChanged;", "onPostUploaded", "Lorg/wordpress/android/fluxc/store/PostStore$OnPostUploaded;", "uploadStatusChanged", "localPostIds", "", "Factory", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PostListEventListener implements androidx.lifecycle.LifecycleObserver, kotlinx.coroutines.CoroutineScope {
    private final androidx.lifecycle.Lifecycle lifecycle = null;
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final org.wordpress.android.fluxc.store.PostStore postStore = null;
    private final org.wordpress.android.fluxc.model.SiteModel site = null;
    private final org.wordpress.android.ui.posts.PostActionHandler postActionHandler = null;
    private final kotlin.jvm.functions.Function0<kotlin.Unit> handlePostUpdatedWithoutError = null;
    private final kotlin.jvm.functions.Function1<org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId, kotlin.Unit> handlePostUploadedWithoutError = null;
    private final kotlin.jvm.functions.Function1<org.wordpress.android.ui.posts.PostUploadAction, kotlin.Unit> triggerPostUploadAction = null;
    private final kotlin.jvm.functions.Function1<java.util.List<java.lang.Integer>, kotlin.Unit> invalidateUploadStatus = null;
    private final kotlin.jvm.functions.Function1<java.util.List<java.lang.Long>, kotlin.Unit> invalidateFeaturedMedia = null;
    private final kotlin.jvm.functions.Function2<org.wordpress.android.ui.posts.PostListRemotePreviewState, org.wordpress.android.ui.posts.PostInfoType, kotlin.Unit> triggerPreviewStateUpdate = null;
    private final kotlin.jvm.functions.Function0<java.lang.Boolean> isRemotePreviewingFromPostsList = null;
    private final kotlin.jvm.functions.Function0<java.lang.Boolean> hasRemoteAutoSavePreviewError = null;
    private kotlinx.coroutines.Job job;
    
    public PostListEventListener(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.Lifecycle lifecycle, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PostStore postStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.PostActionHandler postActionHandler, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> handlePostUpdatedWithoutError, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId, kotlin.Unit> handlePostUploadedWithoutError, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.posts.PostUploadAction, kotlin.Unit> triggerPostUploadAction, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.util.List<java.lang.Integer>, kotlin.Unit> invalidateUploadStatus, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.util.List<java.lang.Long>, kotlin.Unit> invalidateFeaturedMedia, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super org.wordpress.android.ui.posts.PostListRemotePreviewState, ? super org.wordpress.android.ui.posts.PostInfoType, kotlin.Unit> triggerPreviewStateUpdate, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<java.lang.Boolean> isRemotePreviewingFromPostsList, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<java.lang.Boolean> hasRemoteAutoSavePreviewError) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlin.coroutines.CoroutineContext getCoroutineContext() {
        return null;
    }
    
    private final void handleRemoteAutoSave(org.wordpress.android.fluxc.model.PostModel post, boolean isError) {
    }
    
    /**
     * Handles the [Lifecycle.Event.ON_DESTROY] event to cleanup the registration for dispatcher and removing the
     * observer for lifecycle.
     */
    @androidx.lifecycle.OnLifecycleEvent(value = androidx.lifecycle.Lifecycle.Event.ON_DESTROY)
    private final void onDestroy() {
    }
    
    /**
     * Has lower priority than the PostUploadHandler and UploadService, which ensures that they already processed this
     * OnPostChanged event. This means we can safely rely on their internal state being up to date.
     */
    @kotlin.Suppress(names = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN, priority = 5)
    public final void onPostChanged(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PostStore.OnPostChanged event) {
    }
    
    @kotlin.Suppress(names = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.BACKGROUND)
    public final void onMediaChanged(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.MediaStore.OnMediaChanged event) {
    }
    
    @kotlin.Suppress(names = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.BACKGROUND)
    public final void onPostUploaded(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PostStore.OnPostUploaded event) {
    }
    
    @kotlin.Suppress(names = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.BACKGROUND)
    public final void onMediaUploaded(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.MediaStore.OnMediaUploaded event) {
    }
    
    @kotlin.Suppress(names = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.BACKGROUND)
    public final void onEventBackgroundThread(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.UploadService.UploadErrorEvent event) {
    }
    
    @kotlin.Suppress(names = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.BACKGROUND)
    public final void onEventBackgroundThread(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.UploadService.UploadMediaSuccessEvent event) {
    }
    
    /**
     * Upload started, reload so correct status on uploading post appears
     */
    @kotlin.Suppress(names = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.BACKGROUND)
    public final void onEventBackgroundThread(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.PostEvents.PostUploadStarted event) {
    }
    
    /**
     * Upload cancelled (probably due to failed media), reload so correct status on uploading post appears
     */
    @kotlin.Suppress(names = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.BACKGROUND)
    public final void onEventBackgroundThread(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.PostEvents.PostUploadCanceled event) {
    }
    
    @kotlin.Suppress(names = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.BACKGROUND)
    public final void onEventBackgroundThread(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.ProgressEvent event) {
    }
    
    @kotlin.Suppress(names = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.BACKGROUND)
    public final void onEventBackgroundThread(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.UploadService.UploadMediaRetryEvent event) {
    }
    
    private final boolean isRemotePreviewingFromEditor(org.wordpress.android.fluxc.model.PostModel post) {
        return false;
    }
    
    private final void uploadStatusChanged(int... localPostIds) {
    }
    
    private final void featuredMediaChanged(long... featuredImageIds) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u00d6\u0001\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00040\u00142\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00040\u00142\u0018\u0010\u0018\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u0019\u0012\u0004\u0012\u00020\u00040\u00142\u0018\u0010\u001b\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u0019\u0012\u0004\u0012\u00020\u00040\u00142\u0018\u0010\u001d\u001a\u0014\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00040\u001e2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u00122\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0\u0012\u00a8\u0006$"}, d2 = {"Lorg/wordpress/android/ui/posts/PostListEventListener$Factory;", "", "()V", "createAndStartListening", "", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "postStore", "Lorg/wordpress/android/fluxc/store/PostStore;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "postActionHandler", "Lorg/wordpress/android/ui/posts/PostActionHandler;", "handlePostUpdatedWithoutError", "Lkotlin/Function0;", "handlePostUploadedWithoutError", "Lkotlin/Function1;", "Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$LocalId;", "triggerPostUploadAction", "Lorg/wordpress/android/ui/posts/PostUploadAction;", "invalidateUploadStatus", "", "", "invalidateFeaturedMedia", "", "triggerPreviewStateUpdate", "Lkotlin/Function2;", "Lorg/wordpress/android/ui/posts/PostListRemotePreviewState;", "Lorg/wordpress/android/ui/posts/PostInfoType;", "isRemotePreviewingFromPostsList", "", "hasRemoteAutoSavePreviewError", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Factory {
        
        @javax.inject.Inject()
        public Factory() {
            super();
        }
        
        public final void createAndStartListening(@org.jetbrains.annotations.NotNull()
        androidx.lifecycle.Lifecycle lifecycle, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
        kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.PostStore postStore, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.posts.PostActionHandler postActionHandler, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<kotlin.Unit> handlePostUpdatedWithoutError, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId, kotlin.Unit> handlePostUploadedWithoutError, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.ui.posts.PostUploadAction, kotlin.Unit> triggerPostUploadAction, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super java.util.List<java.lang.Integer>, kotlin.Unit> invalidateUploadStatus, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super java.util.List<java.lang.Long>, kotlin.Unit> invalidateFeaturedMedia, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function2<? super org.wordpress.android.ui.posts.PostListRemotePreviewState, ? super org.wordpress.android.ui.posts.PostInfoType, kotlin.Unit> triggerPreviewStateUpdate, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<java.lang.Boolean> isRemotePreviewingFromPostsList, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function0<java.lang.Boolean> hasRemoteAutoSavePreviewError) {
        }
    }
}