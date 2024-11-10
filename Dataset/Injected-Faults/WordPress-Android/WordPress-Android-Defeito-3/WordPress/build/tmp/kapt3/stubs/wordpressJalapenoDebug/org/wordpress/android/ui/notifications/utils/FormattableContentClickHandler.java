package org.wordpress.android.ui.notifications.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u0018\u0010\u0013\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J(\u0010\u0016\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J \u0010\u0018\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0015H\u0002J0\u0010\u0019\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u001bH\u0002J \u0010\u001c\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0015H\u0002J \u0010\u001e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020 H\u0002J \u0010\u001e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u001f\u001a\u00020 H\u0002J\"\u0010#\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010$\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u001f\u001a\u00020 H\u0002R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006%"}, d2 = {"Lorg/wordpress/android/ui/notifications/utils/FormattableContentClickHandler;", "", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "readerTracker", "Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;", "(Lorg/wordpress/android/fluxc/store/SiteStore;Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;)V", "getReaderTracker", "()Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;", "getSiteStore", "()Lorg/wordpress/android/fluxc/store/SiteStore;", "onClick", "", "activity", "Landroidx/fragment/app/FragmentActivity;", "clickedSpan", "Lorg/wordpress/android/fluxc/tools/FormattableRange;", "source", "", "showBackup", "siteId", "", "showBlogPreviewActivity", "postId", "showPostActivity", "showReaderCommentsList", "commentId", "Lorg/wordpress/android/ui/reader/comments/ThreadedCommentsActionSource;", "showReaderPostLikeUsers", "blogId", "showStatsActivityForSite", "rangeType", "Lorg/wordpress/android/fluxc/tools/FormattableRangeType;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "showWebViewActivityForUrl", "url", "org.wordpress.android_wordpressJalapenoDebug"})
public final class FormattableContentClickHandler {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker = null;
    
    @javax.inject.Inject()
    public FormattableContentClickHandler(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.SiteStore getSiteStore() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.reader.tracker.ReaderTracker getReaderTracker() {
        return null;
    }
    
    public final void onClick(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.FragmentActivity activity, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.tools.FormattableRange clickedSpan, @org.jetbrains.annotations.NotNull()
    java.lang.String source) {
    }
    
    private final void showBlogPreviewActivity(androidx.fragment.app.FragmentActivity activity, long siteId, long postId, java.lang.String source) {
    }
    
    private final void showPostActivity(androidx.fragment.app.FragmentActivity activity, long siteId, long postId) {
    }
    
    private final void showStatsActivityForSite(androidx.fragment.app.FragmentActivity activity, long siteId, org.wordpress.android.fluxc.tools.FormattableRangeType rangeType) {
    }
    
    private final void showStatsActivityForSite(androidx.fragment.app.FragmentActivity activity, org.wordpress.android.fluxc.model.SiteModel site, org.wordpress.android.fluxc.tools.FormattableRangeType rangeType) {
    }
    
    private final void showWebViewActivityForUrl(androidx.fragment.app.FragmentActivity activity, java.lang.String url, org.wordpress.android.fluxc.tools.FormattableRangeType rangeType) {
    }
    
    private final void showReaderPostLikeUsers(androidx.fragment.app.FragmentActivity activity, long blogId, long postId) {
    }
    
    private final void showReaderCommentsList(androidx.fragment.app.FragmentActivity activity, long siteId, long postId, long commentId, org.wordpress.android.ui.reader.comments.ThreadedCommentsActionSource source) {
    }
    
    private final void showBackup(androidx.fragment.app.FragmentActivity activity, long siteId) {
    }
}