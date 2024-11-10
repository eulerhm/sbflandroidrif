package org.wordpress.android.ui.posts.editor;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0019\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u001c\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/XPostsCapabilityChecker;", "", "xPostsStore", "Lorg/wordpress/android/fluxc/store/XPostsStore;", "(Lorg/wordpress/android/fluxc/store/XPostsStore;)V", "fetchingReturnsXposts", "", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "(Lorg/wordpress/android/fluxc/model/SiteModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isCapable", "retrieveCapability", "", "callback", "Landroidx/core/util/Consumer;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class XPostsCapabilityChecker {
    private final org.wordpress.android.fluxc.store.XPostsStore xPostsStore = null;
    
    @javax.inject.Inject()
    public XPostsCapabilityChecker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.XPostsStore xPostsStore) {
        super();
    }
    
    public final void retrieveCapability(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    androidx.core.util.Consumer<java.lang.Boolean> callback) {
    }
    
    private final java.lang.Object fetchingReturnsXposts(org.wordpress.android.fluxc.model.SiteModel site, kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object isCapable(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
}