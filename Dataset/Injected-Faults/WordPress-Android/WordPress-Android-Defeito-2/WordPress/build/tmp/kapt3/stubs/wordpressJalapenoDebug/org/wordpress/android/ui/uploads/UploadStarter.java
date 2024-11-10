package org.wordpress.android.ui.uploads;

import java.lang.System;

/**
 * Automatically remote-auto-save or upload all local modifications to posts.
 *
 * Auto-uploads happen when the app is placed in the foreground or when the internet connection is restored. In
 * addition to this, call sites can also request an immediate execution by calling [checkConnectionAndUpload].
 *
 * The method [activateAutoUploading] must be called once, preferably during app creation, for the auto-uploads to work.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0017\u0018\u00002\u00020\u0001Bq\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0001\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0001\u0010\u0012\u001a\u00020\u0011\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018\u00a2\u0006\u0002\u0010\u001aJ\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0016J\u001f\u0010\'\u001a\u00020$2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)H\u0092@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010+J\b\u0010,\u001a\u00020-H\u0016J\u0010\u0010.\u001a\u00020-2\u0006\u0010/\u001a\u00020*H\u0016J \u00100\u001a\u00020$2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u000206H\u0012J\u0019\u00107\u001a\u00020$2\u0006\u0010/\u001a\u00020*H\u0092@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00108R\u000e\u0010\u0010\u001a\u00020\u0011X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\u00020\u001c8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0092\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00069"}, d2 = {"Lorg/wordpress/android/ui/uploads/UploadStarter;", "Lkotlinx/coroutines/CoroutineScope;", "context", "Landroid/content/Context;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "postStore", "Lorg/wordpress/android/fluxc/store/PostStore;", "pageStore", "Lorg/wordpress/android/fluxc/store/PageStore;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "uploadActionUseCase", "Lorg/wordpress/android/ui/uploads/UploadActionUseCase;", "tracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "ioDispatcher", "uploadServiceFacade", "Lorg/wordpress/android/ui/uploads/UploadServiceFacade;", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "connectionStatus", "Landroidx/lifecycle/LiveData;", "Lorg/wordpress/android/viewmodel/helpers/ConnectionStatus;", "(Landroid/content/Context;Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/fluxc/store/PostStore;Lorg/wordpress/android/fluxc/store/PageStore;Lorg/wordpress/android/fluxc/store/SiteStore;Lorg/wordpress/android/ui/uploads/UploadActionUseCase;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/ui/uploads/UploadServiceFacade;Lorg/wordpress/android/util/NetworkUtilsWrapper;Landroidx/lifecycle/LiveData;)V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "job", "Lkotlinx/coroutines/CompletableJob;", "processLifecycleObserver", "Landroidx/lifecycle/LifecycleObserver;", "activateAutoUploading", "", "processLifecycleOwner", "Landroidx/lifecycle/ProcessLifecycleOwner;", "checkConnectionAndUpload", "sites", "", "Lorg/wordpress/android/fluxc/model/SiteModel;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "queueUploadFromAllSites", "Lkotlinx/coroutines/Job;", "queueUploadFromSite", "site", "trackAutoUploadAction", "action", "Lorg/wordpress/android/ui/uploads/UploadActionUseCase$UploadAction;", "status", "", "isPage", "", "upload", "(Lorg/wordpress/android/fluxc/model/SiteModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.wordpress.android_wordpressJalapenoDebug"})
@org.wordpress.android.testing.OpenForTesting()
@javax.inject.Singleton()
public class UploadStarter implements kotlinx.coroutines.CoroutineScope {
    
    /**
     * The Application context
     */
    private final android.content.Context context = null;
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.fluxc.store.PostStore postStore = null;
    private final org.wordpress.android.fluxc.store.PageStore pageStore = null;
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    private final org.wordpress.android.ui.uploads.UploadActionUseCase uploadActionUseCase = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper tracker = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher ioDispatcher = null;
    private final org.wordpress.android.ui.uploads.UploadServiceFacade uploadServiceFacade = null;
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.helpers.ConnectionStatus> connectionStatus = null;
    private final kotlinx.coroutines.CompletableJob job = null;
    
    /**
     * The hook for making this class automatically launch uploads whenever the app is placed in the foreground.
     */
    private final androidx.lifecycle.LifecycleObserver processLifecycleObserver = null;
    
    @javax.inject.Inject()
    public UploadStarter(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PostStore postStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PageStore pageStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.UploadActionUseCase uploadActionUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper tracker, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "IO_THREAD")
    kotlinx.coroutines.CoroutineDispatcher ioDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.UploadServiceFacade uploadServiceFacade, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.helpers.ConnectionStatus> connectionStatus) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlin.coroutines.CoroutineContext getCoroutineContext() {
        return null;
    }
    
    /**
     * Activates the necessary observers for this class to start auto-uploading.
     *
     * This must be called during [org.wordpress.android.WordPress]' creation like so:
     *
     * ```
     * mUploadStarter.activateAutoUploading(ProcessLifecycleOwner.get())
     * ```
     */
    public void activateAutoUploading(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.ProcessLifecycleOwner processLifecycleOwner) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.Job queueUploadFromAllSites() {
        return null;
    }
    
    /**
     * Upload all local drafts from the given [site].
     */
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.Job queueUploadFromSite(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site) {
        return null;
    }
    
    /**
     * If there is an internet connection, uploads all posts with local changes belonging to [sites].
     *
     * This coroutine will suspend until all the [upload] operations have completed. If one of them fails, all query
     * and queuing attempts ([upload]) will be canceled. The exception will be thrown by this method.
     */
    private java.lang.Object checkConnectionAndUpload(java.util.List<? extends org.wordpress.android.fluxc.model.SiteModel> sites, kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    /**
     * This is meant to be used by [checkConnectionAndUpload] only.
     *
     * The method needs to be synchronized from the following reasons. When the app comes to foreground both
     * `queueUploadFromAllSites` and `queueUploadFromSite` are invoked. The problem is that they can run in parallel
     * and `uploadServiceFacade.isPostUploadingOrQueued(it)` might return out-of-date result and a same post is added
     * twice.
     */
    @kotlin.jvm.Synchronized()
    private synchronized java.lang.Object upload(org.wordpress.android.fluxc.model.SiteModel site, kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private void trackAutoUploadAction(org.wordpress.android.ui.uploads.UploadActionUseCase.UploadAction action, java.lang.String status, boolean isPage) {
    }
}