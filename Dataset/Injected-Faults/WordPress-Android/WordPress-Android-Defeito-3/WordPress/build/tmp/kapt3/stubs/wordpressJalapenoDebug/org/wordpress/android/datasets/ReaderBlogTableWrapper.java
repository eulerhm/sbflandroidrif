package org.wordpress.android.datasets;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\f\u001a\u00020\bJ\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\u000eJ\u0018\u0010\u000f\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bJ\u000e\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bJ\u0016\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/datasets/ReaderBlogTableWrapper;", "", "readerUtilsWrapper", "Lorg/wordpress/android/ui/reader/utils/ReaderUtilsWrapper;", "(Lorg/wordpress/android/ui/reader/utils/ReaderUtilsWrapper;)V", "decrementUnseenCount", "", "blogId", "", "getBlogInfo", "Lorg/wordpress/android/models/ReaderBlog;", "getFeedInfo", "feedId", "getFollowedBlogs", "", "getReaderBlog", "incrementUnseenCount", "isNotificationsEnabled", "", "isSiteFollowed", "setNotificationsEnabledByBlogId", "isEnabled", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderBlogTableWrapper {
    private final org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper readerUtilsWrapper = null;
    
    @javax.inject.Inject()
    public ReaderBlogTableWrapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper readerUtilsWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<org.wordpress.android.models.ReaderBlog> getFollowedBlogs() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.models.ReaderBlog getBlogInfo(long blogId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.models.ReaderBlog getFeedInfo(long feedId) {
        return null;
    }
    
    public final boolean isNotificationsEnabled(long blogId) {
        return false;
    }
    
    public final void setNotificationsEnabledByBlogId(long blogId, boolean isEnabled) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final org.wordpress.android.models.ReaderBlog getReaderBlog(long blogId, long feedId) {
        return null;
    }
    
    public final boolean isSiteFollowed(long blogId, long feedId) {
        return false;
    }
    
    public final void incrementUnseenCount(long blogId) {
    }
    
    public final void decrementUnseenCount(long blogId) {
    }
}