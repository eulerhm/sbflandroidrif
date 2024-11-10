package org.wordpress.android.ui.posts;

import java.lang.System;

/**
 * This is a temporary class to make the PostListViewModel more manageable. Please feel free to refactor it any way
 * you see fit.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tJ\u0014\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b8\u0002X\u0083\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/posts/PostListFeaturedImageTracker;", "", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "mediaStore", "Lorg/wordpress/android/fluxc/store/MediaStore;", "(Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/fluxc/store/MediaStore;)V", "featuredImageMap", "Ljava/util/HashMap;", "", "", "getFeaturedImageUrl", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "featuredImageId", "invalidateFeaturedMedia", "", "featuredImageIds", "", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PostListFeaturedImageTracker {
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.fluxc.store.MediaStore mediaStore = null;
    @android.annotation.SuppressLint(value = {"UseSparseArrays"})
    private final java.util.HashMap<java.lang.Long, java.lang.String> featuredImageMap = null;
    
    public PostListFeaturedImageTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.MediaStore mediaStore) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getFeaturedImageUrl(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, long featuredImageId) {
        return null;
    }
    
    public final void invalidateFeaturedMedia(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Long> featuredImageIds) {
    }
}