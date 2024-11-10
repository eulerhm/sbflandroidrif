package org.wordpress.android.util.analytics;

import java.lang.System;

/**
 * Injectable wrapper around AnalyticsUtils.
 *
 * AnalyticsUtils interface is consisted of static methods, which make the client code difficult to test/mock.
 * Main purpose of this wrapper is to make testing easier.
 */
@java.lang.SuppressWarnings(value = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J0\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00062\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0007J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007J\u001e\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J*\u0010\u0017\u001a\u00020\u000e2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cJ>\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001f2\b\u0010\u001b\u001a\u0004\u0018\u00010!2\u0014\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010#J0\u0010$\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0016\u0010\"\u001a\u0012\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010#J&\u0010%\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u00072\u0006\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020(J\u0016\u0010*\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u0007J\u000e\u0010+\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\u0007J\u000e\u0010-\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020.J\u0016\u0010/\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020.2\u0006\u00100\u001a\u00020\u0007J\u000e\u00101\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0007J\u0006\u00102\u001a\u00020\u000eJ\u001e\u00103\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001fJ(\u00104\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u00105\u001a\u00020\u00072\b\u00106\u001a\u0004\u0018\u000107J8\u00104\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u00105\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u00108\u001a\u00020\u00072\b\u00109\u001a\u0004\u0018\u00010\u0007J.\u0010:\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u001b\u001a\u0004\u0018\u00010!2\u0014\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010#R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006;"}, d2 = {"Lorg/wordpress/android/util/analytics/AnalyticsUtilsWrapper;", "", "appContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getMediaProperties", "", "", "isVideo", "", "mediaURI", "Lorg/wordpress/android/util/UriWrapper;", "path", "trackBlogPreviewedByUrl", "", "source", "trackCommentActionWithSiteDetails", "stat", "Lorg/wordpress/android/analytics/AnalyticsTracker$Stat;", "actionSource", "Lorg/wordpress/android/util/analytics/AnalyticsUtils$AnalyticsCommentActionSource;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "trackEditorCreatedPost", "action", "intent", "Landroid/content/Intent;", "post", "Lorg/wordpress/android/fluxc/model/PostImmutableModel;", "trackFollowCommentsWithReaderPostDetails", "blogId", "", "postId", "Lorg/wordpress/android/models/ReaderPost;", "properties", "", "trackInviteLinksAction", "trackLikeListFetchedMore", "listType", "nextPage", "", "perPage", "trackLikeListOpened", "trackRailcarRender", "railcarJson", "trackRecommendAppEngaged", "Lorg/wordpress/android/util/analytics/AnalyticsUtils$RecommendAppSource;", "trackRecommendAppFetchFailed", "error", "trackUserProfileShown", "trackUserProfileSiteShown", "trackWithBlogPostDetails", "trackWithDeepLinkData", "host", "data", "Landroid/net/Uri;", "url", "sourceInfo", "trackWithReaderPostDetails", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class AnalyticsUtilsWrapper {
    private final android.content.Context appContext = null;
    
    @javax.inject.Inject()
    public AnalyticsUtilsWrapper(@org.jetbrains.annotations.NotNull()
    android.content.Context appContext) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.lang.Object> getMediaProperties(boolean isVideo, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.util.UriWrapper mediaURI, @org.jetbrains.annotations.Nullable()
    java.lang.String path) {
        return null;
    }
    
    public final void trackEditorCreatedPost(@org.jetbrains.annotations.Nullable()
    java.lang.String action, @org.jetbrains.annotations.NotNull()
    android.content.Intent intent, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.PostImmutableModel post) {
    }
    
    public final void trackInviteLinksAction(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.Nullable()
    java.util.Map<java.lang.String, ? extends java.lang.Object> properties) {
    }
    
    public final void trackUserProfileShown(@org.jetbrains.annotations.NotNull()
    java.lang.String source) {
    }
    
    public final void trackUserProfileSiteShown() {
    }
    
    public final void trackBlogPreviewedByUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String source) {
    }
    
    public final void trackLikeListOpened(@org.jetbrains.annotations.NotNull()
    java.lang.String source, @org.jetbrains.annotations.NotNull()
    java.lang.String listType) {
    }
    
    public final void trackLikeListFetchedMore(@org.jetbrains.annotations.NotNull()
    java.lang.String source, @org.jetbrains.annotations.NotNull()
    java.lang.String listType, int nextPage, int perPage) {
    }
    
    public final void trackWithReaderPostDetails(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.models.ReaderPost post, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, ? extends java.lang.Object> properties) {
    }
    
    public final void trackFollowCommentsWithReaderPostDetails(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, long blogId, long postId, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.models.ReaderPost post, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, ? extends java.lang.Object> properties) {
    }
    
    public final void trackWithDeepLinkData(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.NotNull()
    java.lang.String action, @org.jetbrains.annotations.NotNull()
    java.lang.String host, @org.jetbrains.annotations.Nullable()
    android.net.Uri data) {
    }
    
    @kotlin.Suppress(names = {"LongParameterList"})
    public final void trackWithDeepLinkData(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.NotNull()
    java.lang.String action, @org.jetbrains.annotations.NotNull()
    java.lang.String host, @org.jetbrains.annotations.NotNull()
    java.lang.String source, @org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.Nullable()
    java.lang.String sourceInfo) {
    }
    
    public final void trackRailcarRender(@org.jetbrains.annotations.NotNull()
    java.lang.String railcarJson) {
    }
    
    public final void trackWithBlogPostDetails(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, long blogId, long postId) {
    }
    
    public final void trackRecommendAppEngaged(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsUtils.RecommendAppSource source) {
    }
    
    public final void trackRecommendAppFetchFailed(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsUtils.RecommendAppSource source, @org.jetbrains.annotations.NotNull()
    java.lang.String error) {
    }
    
    public final void trackCommentActionWithSiteDetails(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsUtils.AnalyticsCommentActionSource actionSource, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site) {
    }
}