package org.wordpress.android.ui.deeplinks.handlers;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001a\u0010\u0018\u001a\u00020\u0019*\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00160\u001cH\u0002R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u001e"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/handlers/ReaderLinkHandler;", "Lorg/wordpress/android/ui/deeplinks/handlers/DeepLinkHandler;", "intentUtils", "Lorg/wordpress/android/ui/utils/IntentUtils;", "analyticsUtilsWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsUtilsWrapper;", "(Lorg/wordpress/android/ui/utils/IntentUtils;Lorg/wordpress/android/util/analytics/AnalyticsUtilsWrapper;)V", "_toast", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "", "toast", "Landroidx/lifecycle/LiveData;", "getToast", "()Landroidx/lifecycle/LiveData;", "buildNavigateAction", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction;", "uri", "Lorg/wordpress/android/util/UriWrapper;", "shouldHandleUrl", "", "stripHost", "", "stripUrl", "appendReadPath", "", "Ljava/lang/StringBuilder;", "segments", "", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderLinkHandler implements org.wordpress.android.ui.deeplinks.handlers.DeepLinkHandler {
    private final org.wordpress.android.ui.utils.IntentUtils intentUtils = null;
    private final org.wordpress.android.util.analytics.AnalyticsUtilsWrapper analyticsUtilsWrapper = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<java.lang.Integer>> _toast = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Integer>> toast = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.deeplinks.handlers.ReaderLinkHandler.Companion Companion = null;
    private static final java.lang.String DEEP_LINK_HOST_READ = "read";
    private static final java.lang.String DEEP_LINK_HOST_VIEWPOST = "viewpost";
    private static final java.lang.String BLOG_ID = "blogId";
    private static final java.lang.String POST_ID = "postId";
    private static final java.lang.String FEED_ID = "feedId";
    private static final int CUSTOM_DOMAIN_POSITION = 3;
    private static final int BLOGS_FEEDS_PATH_POSITION = 1;
    private static final int POSTS_PATH_POSITION = 3;
    private static final int DATE_URL_SEGMENTS = 3;
    
    @javax.inject.Inject()
    public ReaderLinkHandler(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.utils.IntentUtils intentUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsUtilsWrapper analyticsUtilsWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Integer>> getToast() {
        return null;
    }
    
    /**
     * URIs supported by the Reader are already defined as intent filters in the manifest. Instead of replicating
     * that logic here, we simply check if we can resolve an [Intent] that uses [ReaderConstants.ACTION_VIEW_POST].
     * Since that's a custom action that is only handled by the Reader, we can then assume it supports this URI.
     * Other deeplinks handled:
     * `wordpress://read`
     * `wordpress://viewpost?blogId={blogId}&postId={postId}`
     */
    @java.lang.Override()
    public boolean shouldHandleUrl(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.UriWrapper uri) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction buildNavigateAction(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.UriWrapper uri) {
        return null;
    }
    
    /**
     * URLs handled here
     * `wordpress://read`
     * `wordpress://viewpost?blogId={blogId}&postId={postId}`
     * wordpress.com/read/feeds/feedId/posts/feedItemId
     * wordpress.com/read/blogs/feedId/posts/feedItemId
     * domain.wordpress.com/2.../../../postId
     * domain.wordpress.com/19../../../postId
     */
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String stripUrl(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.UriWrapper uri) {
        return null;
    }
    
    private final java.lang.String stripHost(org.wordpress.android.util.UriWrapper uri) {
        return null;
    }
    
    private final void appendReadPath(java.lang.StringBuilder $this$appendReadPath, java.util.List<java.lang.String> segments) {
    }
    
    /**
     * Toast messages emitted from the handler
     */
    @org.jetbrains.annotations.Nullable()
    public androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.lang.Integer>> toast() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/handlers/ReaderLinkHandler$Companion;", "", "()V", "BLOGS_FEEDS_PATH_POSITION", "", "BLOG_ID", "", "CUSTOM_DOMAIN_POSITION", "DATE_URL_SEGMENTS", "DEEP_LINK_HOST_READ", "DEEP_LINK_HOST_VIEWPOST", "FEED_ID", "POSTS_PATH_POSITION", "POST_ID", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}