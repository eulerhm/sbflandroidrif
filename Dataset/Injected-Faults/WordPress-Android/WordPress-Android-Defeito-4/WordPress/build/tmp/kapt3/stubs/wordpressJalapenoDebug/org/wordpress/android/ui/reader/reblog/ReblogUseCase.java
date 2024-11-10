package org.wordpress.android.ui.reader.reblog;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nJ\u0019\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ#\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/reader/reblog/ReblogUseCase;", "", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/fluxc/store/SiteStore;Lkotlinx/coroutines/CoroutineDispatcher;)V", "convertReblogStateToNavigationEvent", "Lorg/wordpress/android/ui/reader/discover/ReaderNavigationEvents;", "state", "Lorg/wordpress/android/ui/reader/reblog/ReblogState;", "onReblogButtonClicked", "post", "Lorg/wordpress/android/models/ReaderPost;", "(Lorg/wordpress/android/models/ReaderPost;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onReblogSiteSelected", "siteLocalId", "", "(ILorg/wordpress/android/models/ReaderPost;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class ReblogUseCase {
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    
    @javax.inject.Inject()
    public ReblogUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object onReblogButtonClicked(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderPost post, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.reader.reblog.ReblogState> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object onReblogSiteSelected(int siteLocalId, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.models.ReaderPost post, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.reader.reblog.ReblogState> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.ui.reader.discover.ReaderNavigationEvents convertReblogStateToNavigationEvent(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.reblog.ReblogState state) {
        return null;
    }
}