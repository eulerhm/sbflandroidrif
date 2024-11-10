package org.wordpress.android.viewmodel.posts;

import java.lang.System;

/**
 * Class which takes care of dispatching fetch post events while ignoring duplicate requests.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000fJ\b\u0010\u0010\u001a\u00020\u000bH\u0003J\u0010\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/viewmodel/posts/PostFetcher;", "Landroidx/lifecycle/LifecycleObserver;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "(Landroidx/lifecycle/Lifecycle;Lorg/wordpress/android/fluxc/Dispatcher;)V", "ongoingRequests", "Ljava/util/HashSet;", "Lorg/wordpress/android/fluxc/model/LocalOrRemoteId$RemoteId;", "fetchPosts", "", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "remoteItemIds", "", "onDestroy", "onPostChanged", "event", "Lorg/wordpress/android/fluxc/store/PostStore$OnPostChanged;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PostFetcher implements androidx.lifecycle.LifecycleObserver {
    private final androidx.lifecycle.Lifecycle lifecycle = null;
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final java.util.HashSet<org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId> ongoingRequests = null;
    
    public PostFetcher(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.Lifecycle lifecycle, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher) {
        super();
    }
    
    /**
     * Handles the [Lifecycle.Event.ON_DESTROY] event to cleanup the registration for dispatcher and removing the
     * observer for lifecycle.
     */
    @androidx.lifecycle.OnLifecycleEvent(value = androidx.lifecycle.Lifecycle.Event.ON_DESTROY)
    private final void onDestroy() {
    }
    
    public final void fetchPosts(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    java.util.List<org.wordpress.android.fluxc.model.LocalOrRemoteId.RemoteId> remoteItemIds) {
    }
    
    @kotlin.Suppress(names = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.BACKGROUND)
    public final void onPostChanged(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.PostStore.OnPostChanged event) {
    }
}