package org.wordpress.android.datasets.wrappers;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tJ \u0010\n\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u001e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u0004J\u000e\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u0004J\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\t\u00a8\u0006\u0018"}, d2 = {"Lorg/wordpress/android/datasets/wrappers/ReaderPostTableWrapper;", "", "()V", "getBlogPost", "Lorg/wordpress/android/models/ReaderPost;", "blogId", "", "postId", "excludeTextColumn", "", "getFeedPost", "getNumPostsWithTag", "", "readerTag", "Lorg/wordpress/android/models/ReaderTag;", "getPostsWithTag", "Lorg/wordpress/android/models/ReaderPostList;", "maxRows", "isPostFollowed", "post", "isPostSeen", "setPostSeenStatusInDb", "", "isSeen", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class ReaderPostTableWrapper {
    
    @javax.inject.Inject()
    public ReaderPostTableWrapper() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.models.ReaderPost getBlogPost(long blogId, long postId, boolean excludeTextColumn) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.models.ReaderPost getFeedPost(long blogId, long postId, boolean excludeTextColumn) {
        return null;
    }
    
    public final boolean isPostFollowed(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderPost post) {
        return false;
    }
    
    public final boolean isPostSeen(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderPost post) {
        return false;
    }
    
    public final void setPostSeenStatusInDb(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderPost post, boolean isSeen) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.models.ReaderPostList getPostsWithTag(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderTag readerTag, int maxRows, boolean excludeTextColumn) {
        return null;
    }
    
    public final int getNumPostsWithTag(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderTag readerTag) {
        return 0;
    }
}