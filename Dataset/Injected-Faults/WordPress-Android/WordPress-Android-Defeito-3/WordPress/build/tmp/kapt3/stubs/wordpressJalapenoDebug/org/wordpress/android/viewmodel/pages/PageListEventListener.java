package org.wordpress.android.viewmodel.pages;

import java.lang.System;

/**
 * This is a temporary class to make the PagesViewModel more manageable. It was inspired by the PostListEventListener
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00a0\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u00016B\u009d\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0018\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u000f\u0012\u001e\u0010\u0013\u001a\u001a\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0014\u0012\u0018\u0010\u0016\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u0018\u0012\u0004\u0012\u00020\u00120\u0017\u0012\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00120\u0017\u00a2\u0006\u0002\u0010\u001aJ\u0006\u0010!\u001a\u00020\u0012J\u0010\u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020$H\u0007J\u0010\u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020%H\u0007J\u0010\u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020&H\u0007J\u0010\u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\'H\u0007J\u0010\u0010(\u001a\u00020\u00122\u0006\u0010#\u001a\u00020)H\u0007J\u0010\u0010*\u001a\u00020\u00122\u0006\u0010#\u001a\u00020+H\u0007J\u0010\u0010,\u001a\u00020\u00122\u0006\u0010#\u001a\u00020-H\u0007J\u0010\u0010.\u001a\u00020\u00122\u0006\u0010#\u001a\u00020/H\u0007J\u0010\u00100\u001a\u00020\u00122\u0006\u0010#\u001a\u000201H\u0007J!\u00102\u001a\u00020\u00122\u0012\u00103\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001004\"\u00020\u0010H\u0002\u00a2\u0006\u0002\u00105R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\u00020\u001c8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00120\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R&\u0010\u0013\u001a\u001a\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0016\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u0018\u0012\u0004\u0012\u00020\u00120\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00067"}, d2 = {"Lorg/wordpress/android/viewmodel/pages/PageListEventListener;", "Lkotlinx/coroutines/CoroutineScope;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "postStore", "Lorg/wordpress/android/fluxc/store/PostStore;", "eventBusWrapper", "Lorg/wordpress/android/util/EventBusWrapper;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "handleRemoteAutoSave", "Lkotlin/Function2;", "Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$LocalId;", "", "", "handlePostUploadFinished", "Lkotlin/Function3;", "Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$RemoteId;", "invalidateUploadStatus", "Lkotlin/Function1;", "", "handleHomepageSettingsChange", "(Lorg/wordpress/android/fluxc/Dispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/fluxc/store/PostStore;Lorg/wordpress/android/util/EventBusWrapper;Lorg/wordpress/android/fluxc/store/SiteStore;Lorg/wordpress/android/fluxc/model/SiteModel;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "job", "Lkotlinx/coroutines/Job;", "onDestroy", "onEventBackgroundThread", "event", "Lorg/wordpress/android/ui/uploads/PostEvents$PostUploadCanceled;", "Lorg/wordpress/android/ui/uploads/PostEvents$PostUploadStarted;", "Lorg/wordpress/android/ui/uploads/ProgressEvent;", "Lorg/wordpress/android/ui/uploads/UploadService$UploadMediaRetryEvent;", "onMediaChanged", "Lorg/wordpress/android/fluxc/store/MediaStore$OnMediaChanged;", "onMediaUploaded", "Lorg/wordpress/android/fluxc/store/MediaStore$OnMediaUploaded;", "onPostChanged", "Lorg/wordpress/android/fluxc/store/PostStore$OnPostChanged;", "onPostUploaded", "Lorg/wordpress/android/fluxc/store/PostStore$OnPostUploaded;", "onSiteChanged", "Lorg/wordpress/android/fluxc/store/SiteStore$OnSiteChanged;", "uploadStatusChanged", "localPostIds", "", "([Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$LocalId;)V", "Factory", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PageListEventListener implements kotlinx.coroutines.CoroutineScope {
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final org.wordpress.android.fluxc.store.PostStore postStore = null;
    private final org.wordpress.android.util.EventBusWrapper eventBusWrapper = null;
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    private final org.wordpress.android.fluxc.model.SiteModel site = null;
    private final kotlin.jvm.functions.Function2<org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId, java.lang.Boolean, kotlin.Unit> handleRemoteAutoSave = null;
    private final kotlin.jvm.functions.Function3<org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId, java.lang.Boolean, java.lang.Boolean, kotlin.Unit> handlePostUploadFinished = null;
    private final kotlin.jvm.functions.Function1<java.util.List<org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId>, kotlin.Unit> invalidateUploadStatus = null;
    private final kotlin.jvm.functions.Function1<org.wordpress.android.fluxc.model.SiteModel, kotlin.Unit> handleHomepageSettingsChange = null;
    private kotlinx.coroutines.Job job;
    
    public PageListEventListener(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PostStore postStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.EventBusWrapper eventBusWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId, ? super java.lang.Boolean, kotlin.Unit> handleRemoteAutoSave, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function3<? super org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId, ? super java.lang.Boolean, ? super java.lang.Boolean, kotlin.Unit> handlePostUploadFinished, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.util.List<org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId>, kotlin.Unit> invalidateUploadStatus, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.model.SiteModel, kotlin.Unit> handleHomepageSettingsChange) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlin.coroutines.CoroutineContext getCoroutineContext() {
        return null;
    }
    
    /**
     * Handles the onDestroy event to cleanup the registration for dispatcher and cancelling any pending jobs.
     */
    public final void onDestroy() {
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
    
    @java.lang.SuppressWarnings(value = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onSiteChanged(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore.OnSiteChanged event) {
    }
    
    private final void uploadStatusChanged(org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId... localPostIds) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u009e\u0001\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0018\u0010\u0011\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u0013\u0012\u0004\u0012\u00020\u00150\u00122\u0018\u0010\u0016\u001a\u0014\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00150\u00172\u001e\u0010\u0019\u001a\u001a\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00150\u001a2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00150\u0012\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/viewmodel/pages/PageListEventListener$Factory;", "", "()V", "createAndStartListening", "Lorg/wordpress/android/viewmodel/pages/PageListEventListener;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "postStore", "Lorg/wordpress/android/fluxc/store/PostStore;", "eventBusWrapper", "Lorg/wordpress/android/util/EventBusWrapper;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "invalidateUploadStatus", "Lkotlin/Function1;", "", "Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$LocalId;", "", "handleRemoteAutoSave", "Lkotlin/Function2;", "", "handlePostUploadFinished", "Lkotlin/Function3;", "Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$RemoteId;", "handleHomepageSettingsChange", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Factory {
        
        @javax.inject.Inject()
        public Factory() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.viewmodel.pages.PageListEventListener createAndStartListening(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
        kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.PostStore postStore, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.util.EventBusWrapper eventBusWrapper, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.SiteStore siteStore, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super java.util.List<org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId>, kotlin.Unit> invalidateUploadStatus, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function2<? super org.wordpress.android.fluxc.model.LocalOrRemoteId.LocalId, ? super java.lang.Boolean, kotlin.Unit> handleRemoteAutoSave, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function3<? super org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId, ? super java.lang.Boolean, ? super java.lang.Boolean, kotlin.Unit> handlePostUploadFinished, @org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.model.SiteModel, kotlin.Unit> handleHomepageSettingsChange) {
            return null;
        }
    }
}