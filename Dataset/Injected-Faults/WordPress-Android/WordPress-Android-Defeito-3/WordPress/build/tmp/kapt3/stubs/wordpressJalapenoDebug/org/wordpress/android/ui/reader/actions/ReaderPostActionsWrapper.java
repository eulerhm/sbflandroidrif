package org.wordpress.android.ui.reader.actions;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ \u0010\n\u001a\u00020\u000b2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eJ(\u0010\u000f\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ$\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u000e2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017J$\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u000e2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017J\u000e\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/ui/reader/actions/ReaderPostActionsWrapper;", "", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "(Lorg/wordpress/android/fluxc/store/SiteStore;)V", "addToBookmarked", "", "post", "Lorg/wordpress/android/models/ReaderPost;", "bumpPageViewForPost", "performLikeActionLocal", "", "isAskingToLike", "wpComUserId", "", "performLikeActionRemote", "actionListener", "Lorg/wordpress/android/ui/reader/actions/ReaderActions$ActionListener;", "removeFromBookmarked", "requestBlogPost", "blogId", "postId", "requestListener", "Lorg/wordpress/android/ui/reader/actions/ReaderActions$OnRequestListener;", "", "requestFeedPost", "feedId", "requestRelatedPosts", "sourcePost", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class ReaderPostActionsWrapper {
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    
    @javax.inject.Inject()
    public ReaderPostActionsWrapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore) {
        super();
    }
    
    public final void addToBookmarked(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderPost post) {
    }
    
    public final void removeFromBookmarked(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderPost post) {
    }
    
    public final boolean performLikeActionLocal(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.models.ReaderPost post, boolean isAskingToLike, long wpComUserId) {
        return false;
    }
    
    public final void performLikeActionRemote(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.models.ReaderPost post, boolean isAskingToLike, long wpComUserId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.actions.ReaderActions.ActionListener actionListener) {
    }
    
    public final void bumpPageViewForPost(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderPost post) {
    }
    
    public final void requestRelatedPosts(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderPost sourcePost) {
    }
    
    public final void requestFeedPost(long feedId, long postId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.actions.ReaderActions.OnRequestListener<java.lang.String> requestListener) {
    }
    
    public final void requestBlogPost(long blogId, long postId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.actions.ReaderActions.OnRequestListener<java.lang.String> requestListener) {
    }
}