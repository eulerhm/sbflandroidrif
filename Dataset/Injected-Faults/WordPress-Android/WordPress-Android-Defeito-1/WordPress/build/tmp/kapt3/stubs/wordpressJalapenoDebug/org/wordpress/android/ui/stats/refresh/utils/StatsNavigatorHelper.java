package org.wordpress.android.ui.stats.refresh.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J6\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eJ\u0018\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000bH\u0002J(\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH\u0002J(\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH\u0002\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/utils/StatsNavigatorHelper;", "", "()V", "openPostInReaderOrInAppWebView", "", "context", "Landroid/content/Context;", "blogId", "", "itemId", "itemType", "", "itemUrl", "readerTracker", "Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;", "showPostInAppWebView", "showPostInReader", "remoteBlogId", "remoteItemId", "showPostInReaderPreview", "org.wordpress.android_wordpressJalapenoDebug"})
public final class StatsNavigatorHelper {
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.stats.refresh.utils.StatsNavigatorHelper INSTANCE = null;
    
    private StatsNavigatorHelper() {
        super();
    }
    
    @kotlin.Suppress(names = {"LongParameterList"})
    public final void openPostInReaderOrInAppWebView(@org.jetbrains.annotations.NotNull()
    android.content.Context context, long blogId, long itemId, @org.jetbrains.annotations.NotNull()
    java.lang.String itemType, @org.jetbrains.annotations.NotNull()
    java.lang.String itemUrl, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker) {
    }
    
    private final void showPostInReader(android.content.Context context, long remoteBlogId, long remoteItemId, org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker) {
    }
    
    private final void showPostInReaderPreview(android.content.Context context, long remoteBlogId, long remoteItemId, org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker) {
    }
    
    private final void showPostInAppWebView(android.content.Context context, java.lang.String itemUrl) {
    }
}