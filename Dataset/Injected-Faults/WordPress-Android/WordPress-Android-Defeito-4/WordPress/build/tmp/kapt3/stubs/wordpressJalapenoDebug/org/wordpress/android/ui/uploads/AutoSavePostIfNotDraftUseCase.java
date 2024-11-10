package org.wordpress.android.ui.uploads;

import java.lang.System;

/**
 * This is a use case that auto-saves a post if it's not a DRAFT in remote and returns various results depending
 * on the remote post status and whether network requests were successful.
 *
 * The reason auto-save is tied to post status is that the `/autosave` REST endpoint will override the changes to
 * a `DRAFT` directly rather than auto-saving it. While doing so, it'll also disable comments for the post due to
 * a bug. Both the fact that the endpoint does something we are not expecting and the bug that results from it is
 * avoided by only calling the `/autosave` endpoint for posts that are not in DRAFT status. We update DRAFTs directly
 * just as the endpoint would have, but that makes the logic more clear on the client side while avoiding the
 * comments getting disabled bug.
 *
 * See p3hLNG-15Z-p2 for more info.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0019\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J\u0016\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001aJ\u0019\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0015H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J\u0010\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\rH\u0007J\u0010\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u0011H\u0007R \u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\f0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001f"}, d2 = {"Lorg/wordpress/android/ui/uploads/AutoSavePostIfNotDraftUseCase;", "", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "postStore", "Lorg/wordpress/android/fluxc/store/PostStore;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/fluxc/store/PostStore;Lkotlinx/coroutines/CoroutineDispatcher;)V", "autoSaveContinuations", "Ljava/util/HashMap;", "Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$RemoteId;", "Lkotlin/coroutines/Continuation;", "Lorg/wordpress/android/fluxc/store/PostStore$OnPostChanged;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "postStatusContinuations", "Lorg/wordpress/android/fluxc/store/PostStore$OnPostStatusFetched;", "autoSavePost", "Lorg/wordpress/android/ui/uploads/AutoSavePostIfNotDraftResult;", "remotePostPayload", "Lorg/wordpress/android/fluxc/store/PostStore$RemotePostPayload;", "(Lorg/wordpress/android/fluxc/store/PostStore$RemotePostPayload;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "autoSavePostOrUpdateDraft", "", "callback", "Lorg/wordpress/android/ui/uploads/OnAutoSavePostIfNotDraftCallback;", "fetchRemotePostStatus", "onPostChanged", "event", "onPostStatusFetched", "org.wordpress.android_wordpressJalapenoDebug"})
public final class AutoSavePostIfNotDraftUseCase {
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.fluxc.store.PostStore postStore = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final kotlinx.coroutines.CoroutineScope coroutineScope = null;
    private final java.util.HashMap<org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId, kotlin.coroutines.Continuation<org.wordpress.android.fluxc.store.PostStore.OnPostStatusFetched>> postStatusContinuations = null;
    private final java.util.HashMap<org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId, kotlin.coroutines.Continuation<org.wordpress.android.fluxc.store.PostStore.OnPostChanged>> autoSaveContinuations = null;
    
    @javax.inject.Inject()
    public AutoSavePostIfNotDraftUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PostStore postStore, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super();
    }
    
    public final void autoSavePostOrUpdateDraft(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PostStore.RemotePostPayload remotePostPayload, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.OnAutoSavePostIfNotDraftCallback callback) {
    }
    
    private final java.lang.Object fetchRemotePostStatus(org.wordpress.android.fluxc.store.PostStore.RemotePostPayload remotePostPayload, kotlin.coroutines.Continuation<? super org.wordpress.android.fluxc.store.PostStore.OnPostStatusFetched> continuation) {
        return null;
    }
    
    private final java.lang.Object autoSavePost(org.wordpress.android.fluxc.store.PostStore.RemotePostPayload remotePostPayload, kotlin.coroutines.Continuation<? super org.wordpress.android.ui.uploads.AutoSavePostIfNotDraftResult> continuation) {
        return null;
    }
    
    @kotlin.Suppress(names = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.BACKGROUND)
    public final void onPostStatusFetched(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PostStore.OnPostStatusFetched event) {
    }
    
    @kotlin.Suppress(names = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN, priority = 9)
    public final void onPostChanged(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PostStore.OnPostChanged event) {
    }
}