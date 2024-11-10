package org.wordpress.android.ui.reader.actions;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bJ\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ6\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J(\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0018\u001a\u00020\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/ui/reader/actions/ReaderBlogActionsWrapper;", "", "readerUtilsWrapper", "Lorg/wordpress/android/ui/reader/utils/ReaderUtilsWrapper;", "(Lorg/wordpress/android/ui/reader/utils/ReaderUtilsWrapper;)V", "blockBlogFromReaderLocal", "Lorg/wordpress/android/ui/reader/actions/ReaderBlogActions$BlockedBlogResult;", "blogId", "", "feedId", "blockBlogFromReaderRemote", "", "blockedBlogResult", "actionListener", "Lorg/wordpress/android/ui/reader/actions/ReaderActions$ActionListener;", "followBlog", "", "isAskingToFollow", "source", "", "readerTracker", "Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;", "updateBlogInfo", "blogUrl", "infoListener", "Lorg/wordpress/android/ui/reader/actions/ReaderActions$UpdateBlogInfoListener;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderBlogActionsWrapper {
    private final org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper readerUtilsWrapper = null;
    
    @javax.inject.Inject()
    public ReaderBlogActionsWrapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper readerUtilsWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.reader.actions.ReaderBlogActions.BlockedBlogResult blockBlogFromReaderLocal(long blogId, long feedId) {
        return null;
    }
    
    public final void blockBlogFromReaderRemote(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.actions.ReaderBlogActions.BlockedBlogResult blockedBlogResult, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.reader.actions.ReaderActions.ActionListener actionListener) {
    }
    
    @kotlin.Suppress(names = {"LongParameterList"})
    public final boolean followBlog(long blogId, long feedId, boolean isAskingToFollow, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.actions.ReaderActions.ActionListener actionListener, @org.jetbrains.annotations.NotNull()
    java.lang.String source, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker) {
        return false;
    }
    
    public final void updateBlogInfo(long blogId, long feedId, @org.jetbrains.annotations.Nullable()
    java.lang.String blogUrl, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.actions.ReaderActions.UpdateBlogInfoListener infoListener) {
    }
}