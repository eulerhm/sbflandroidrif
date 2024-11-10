package org.wordpress.android.ui.posts;

import java.lang.System;

/**
 * Injectable wrapper around PostUtils.
 *
 * PostUtils interface is consisted of static methods, which make the client code difficult to test/mock. Main purpose
 * of this wrapper is to make testing easier.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bJ\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\bJ\u000e\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\bJ\u000e\u0010\u0012\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rJ\u0018\u0010\u0013\u001a\u00020\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0015\u001a\u00020\rJ\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\f\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\u0016\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0010\u001a\u00020\bJ\u0010\u0010\u001e\u001a\u00020\u00062\b\u0010\u001f\u001a\u0004\u0018\u00010\u001dJ\u0018\u0010 \u001a\u00020\u00172\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0019\u001a\u00020\u001aR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lorg/wordpress/android/ui/posts/PostUtilsWrapper;", "", "dateProvider", "Lorg/wordpress/android/ui/reader/utils/DateProvider;", "(Lorg/wordpress/android/ui/reader/utils/DateProvider;)V", "contentContainsGutenbergBlocks", "", "postContent", "", "isMediaInGutenbergPostBody", "localMediaId", "isPostCurrentlyBeingEdited", "post", "Lorg/wordpress/android/fluxc/model/PostImmutableModel;", "isPostInConflictWithRemote", "isPublishDateInTheFuture", "dateCreated", "isPublishDateInThePast", "isPublishable", "postHasEdits", "oldPost", "newPost", "preparePostForPublish", "", "Lorg/wordpress/android/fluxc/model/PostModel;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "shouldPublishImmediately", "postStatus", "Lorg/wordpress/android/fluxc/model/post/PostStatus;", "shouldPublishImmediatelyOptionBeAvailable", "status", "trackSavePostAnalytics", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class PostUtilsWrapper {
    private final org.wordpress.android.ui.reader.utils.DateProvider dateProvider = null;
    
    @javax.inject.Inject()
    public PostUtilsWrapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.utils.DateProvider dateProvider) {
        super();
    }
    
    public final boolean isPublishable(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostImmutableModel post) {
        return false;
    }
    
    public final boolean isPostInConflictWithRemote(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostImmutableModel post) {
        return false;
    }
    
    public final boolean isPostCurrentlyBeingEdited(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostImmutableModel post) {
        return false;
    }
    
    public final boolean shouldPublishImmediately(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.post.PostStatus postStatus, @org.jetbrains.annotations.NotNull()
    java.lang.String dateCreated) {
        return false;
    }
    
    public final boolean postHasEdits(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.PostImmutableModel oldPost, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostImmutableModel newPost) {
        return false;
    }
    
    public final boolean isMediaInGutenbergPostBody(@org.jetbrains.annotations.NotNull()
    java.lang.String postContent, @org.jetbrains.annotations.NotNull()
    java.lang.String localMediaId) {
        return false;
    }
    
    public final boolean contentContainsGutenbergBlocks(@org.jetbrains.annotations.NotNull()
    java.lang.String postContent) {
        return false;
    }
    
    public final void trackSavePostAnalytics(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.PostImmutableModel post, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    public final void preparePostForPublish(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.PostModel post, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    public final boolean isPublishDateInTheFuture(@org.jetbrains.annotations.NotNull()
    java.lang.String dateCreated) {
        return false;
    }
    
    public final boolean isPublishDateInThePast(@org.jetbrains.annotations.NotNull()
    java.lang.String dateCreated) {
        return false;
    }
    
    public final boolean shouldPublishImmediatelyOptionBeAvailable(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.post.PostStatus status) {
        return false;
    }
}