package org.wordpress.android.ui.reader.tracker;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions"})
@androidx.annotation.MainThread()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0007\u0018\u0000 G2\u00020\u0001:\u0001GB\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u0013J\u001c\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0007J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\rJ\u0006\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010\u001f\u001a\u00020\u001eJ\u0006\u0010 \u001a\u00020\u001eJ\u000e\u0010!\u001a\u00020\u001e2\u0006\u0010\u001c\u001a\u00020\rJ\u000e\u0010\"\u001a\u00020\u001e2\u0006\u0010\u001c\u001a\u00020\rJ\u000e\u0010#\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020%J\u0016\u0010#\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0014J \u0010#\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020%2\u0010\u0010\'\u001a\f\u0012\u0004\u0012\u00020\u0014\u0012\u0002\b\u00030\fJ\u001e\u0010(\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020%2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010J-\u0010(\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020%2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\b\u0010)\u001a\u0004\u0018\u00010\u001b\u00a2\u0006\u0002\u0010*J5\u0010(\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020%2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\b\u0010)\u001a\u0004\u0018\u00010\u001b2\u0006\u0010&\u001a\u00020\u0014\u00a2\u0006\u0002\u0010+J&\u0010(\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020%2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\u0014J\u001e\u0010,\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020%2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010-\u001a\u00020\u0010J&\u0010,\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020%2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010-\u001a\u00020\u00102\u0006\u0010.\u001a\u00020\u001bJ&\u0010,\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020%2\u0006\u0010\u000f\u001a\u00020\u00142\u0006\u0010-\u001a\u00020\u00142\u0006\u0010/\u001a\u000200J(\u00101\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020%2\u0006\u00102\u001a\u00020\u00142\u0006\u00103\u001a\u00020\u00142\b\u00104\u001a\u0004\u0018\u000105J\u001e\u00106\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020%2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u00107\u001a\u00020\u0010J\u0018\u00108\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020%2\b\u00109\u001a\u0004\u0018\u00010:J \u00108\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020%2\b\u00109\u001a\u0004\u0018\u00010:2\u0006\u0010&\u001a\u00020\u0014J,\u00108\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020%2\b\u00109\u001a\u0004\u0018\u00010:2\u0010\u0010\'\u001a\f\u0012\u0004\u0012\u00020\u0014\u0012\u0002\b\u00030\fH\u0002J:\u0010;\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020%2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010-\u001a\u00020\u00102\b\u00109\u001a\u0004\u0018\u00010:2\u0010\u0010\'\u001a\f\u0012\u0004\u0012\u00020\u0014\u0012\u0002\b\u00030\fJ\u0016\u0010<\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020%2\u0006\u0010=\u001a\u00020\u0014J\u000e\u0010>\u001a\u00020\u001e2\u0006\u0010?\u001a\u00020\u0014J\u000e\u0010@\u001a\u00020\u001e2\u0006\u0010\u0018\u001a\u00020\u0019J\u001e\u0010A\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020%2\u0006\u0010B\u001a\u00020\u00142\u0006\u0010&\u001a\u00020\u0014J\u0016\u0010C\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020%2\u0006\u0010D\u001a\u000200J\u0016\u0010E\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020%2\u0006\u0010F\u001a\u00020\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006H"}, d2 = {"Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;", "", "dateProvider", "Lorg/wordpress/android/ui/reader/utils/DateProvider;", "appPrefsWrapper", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "analyticsTrackerWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "analyticsUtilsWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsUtilsWrapper;", "(Lorg/wordpress/android/ui/reader/utils/DateProvider;Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/util/analytics/AnalyticsUtilsWrapper;)V", "trackers", "", "Lorg/wordpress/android/ui/reader/tracker/ReaderTrackerType;", "Lorg/wordpress/android/ui/reader/tracker/ReaderTrackerInfo;", "blogId", "", "feedId", "getAnalyticsData", "", "", "getSource", "postListType", "Lorg/wordpress/android/ui/reader/ReaderTypes$ReaderPostListType;", "readerTab", "Lorg/wordpress/android/ui/reader/tracker/ReaderTab;", "isRunning", "", "type", "onAppGoesToBackground", "", "onBottomNavigationTabChanged", "setupTrackers", "start", "stop", "track", "stat", "Lorg/wordpress/android/analytics/AnalyticsTracker$Stat;", "source", "properties", "trackBlog", "isFollowed", "(Lorg/wordpress/android/analytics/AnalyticsTracker$Stat;JJLjava/lang/Boolean;)V", "(Lorg/wordpress/android/analytics/AnalyticsTracker$Stat;JJLjava/lang/Boolean;Ljava/lang/String;)V", "trackBlogPost", "postId", "isJetpack", "commentId", "", "trackDeepLink", "action", "host", "uri", "Landroid/net/Uri;", "trackFeedPost", "feedItemId", "trackPost", "post", "Lorg/wordpress/android/models/ReaderPost;", "trackPostComments", "trackQuery", "query", "trackRailcar", "railcarJson", "trackReaderTabIfNecessary", "trackTag", "tag", "trackTagQuantity", "quantity", "trackUri", "interceptedUri", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
@javax.inject.Singleton()
public final class ReaderTracker {
    private final org.wordpress.android.ui.reader.utils.DateProvider dateProvider = null;
    private final org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper = null;
    private final org.wordpress.android.util.analytics.AnalyticsUtilsWrapper analyticsUtilsWrapper = null;
    private final java.util.Map<org.wordpress.android.ui.reader.tracker.ReaderTrackerType, org.wordpress.android.ui.reader.tracker.ReaderTrackerInfo> trackers = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.reader.tracker.ReaderTracker.Companion Companion = null;
    private static final java.lang.String BLOG_ID_KEY = "blog_id";
    private static final java.lang.String POST_ID_KEY = "post_id";
    private static final java.lang.String IS_JETPACK_KEY = "is_jetpack";
    private static final java.lang.String COMMENT_ID_KEY = "comment_id";
    private static final java.lang.String FEED_ID_KEY = "feed_id";
    private static final java.lang.String FEED_ITEM_ID_KEY = "feed_item_id";
    private static final java.lang.String FOLLOW_KEY = "follow";
    private static final java.lang.String TAG_KEY = "tag";
    private static final java.lang.String QUANTITY_KEY = "quantity";
    private static final java.lang.String INTERCEPTED_URI_KEY = "intercepted_uri";
    private static final java.lang.String QUERY_KEY = "query";
    private static final java.lang.String SOURCE_KEY = "source";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SOURCE_FOLLOWING = "following";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SOURCE_DISCOVER = "discover";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SOURCE_LIKED = "liked";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SOURCE_SAVED = "saved";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SOURCE_CUSTOM = "custom";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SOURCE_A8C = "a8c";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SOURCE_P2 = "p2";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SOURCE_SETTINGS = "subscriptions";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SOURCE_SEARCH = "search";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SOURCE_SITE_PREVIEW = "site_preview";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SOURCE_TAG_PREVIEW = "tag_preview";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SOURCE_POST_DETAIL = "post_detail";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SOURCE_POST_DETAIL_COMMENT_SNIPPET = "post_detail_comment_snippet";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SOURCE_COMMENT = "comment";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SOURCE_USER = "user";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SOURCE_STATS = "stats";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SOURCE_NOTIFICATION = "notification";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SOURCE_READER_LIKE_LIST = "reader_like_list";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SOURCE_READER_LIKE_LIST_USER_PROFILE = "reader_like_list_user_profile";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SOURCE_NOTIF_LIKE_LIST_USER_PROFILE = "notif_like_list_user_profile";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SOURCE_USER_PROFILE_UNKNOWN = "user_profile_source_unknown";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SOURCE_ACTIVITY_LOG_DETAIL = "activity_log_detail";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SOURCE_POST_LIST_SAVED_POST_NOTICE = "post_list_saved_post_notice";
    private static final java.lang.String UNKNOWN_VALUE = "unknown";
    
    @javax.inject.Inject()
    public ReaderTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.utils.DateProvider dateProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsUtilsWrapper analyticsUtilsWrapper) {
        super();
    }
    
    public final void setupTrackers() {
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.tracker.ReaderTrackerType type) {
    }
    
    public final void stop(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.tracker.ReaderTrackerType type) {
    }
    
    public final boolean isRunning(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.tracker.ReaderTrackerType type) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.lang.Object> getAnalyticsData() {
        return null;
    }
    
    public final void trackReaderTabIfNecessary(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.tracker.ReaderTab readerTab) {
    }
    
    public final void onAppGoesToBackground() {
    }
    
    public final void onBottomNavigationTabChanged() {
    }
    
    public final void track(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat) {
    }
    
    public final void track(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.NotNull()
    java.lang.String source) {
    }
    
    public final void track(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, ?> properties) {
    }
    
    public final void trackBlog(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, long blogId, long feedId) {
    }
    
    public final void trackBlog(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, long blogId, long feedId, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean isFollowed) {
    }
    
    public final void trackBlog(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, long blogId, long feedId, @org.jetbrains.annotations.NotNull()
    java.lang.String source) {
    }
    
    public final void trackBlog(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, long blogId, long feedId, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean isFollowed, @org.jetbrains.annotations.NotNull()
    java.lang.String source) {
    }
    
    /**
     * The [org.wordpress.android.models.ReaderBlog.fromJson] method within this model class has a logic where it
     * checks whether the blogs 'blogId' is 0, if it is, then it checks whether the 'feedId' is not 0. If both
     * conditions are met then it assigns the 'feedId' to 'blogId'. It does that in order to keep consistency with the
     * '/read/' endpoints.
     *
     * This tracking function captures that and does the opposite in order to make sure that the tracking is done to the
     * absolute. As such, it check whether the 'feedId' is equal to the 'blogId' and if so it assigns 0 to the 'blog_id'
     * tracking property. Else, as should, it assigns the actual 'blogId' to the 'blog_id' tracking property.
     */
    private final long blogId(long blogId, long feedId) {
        return 0L;
    }
    
    public final void trackTag(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.NotNull()
    java.lang.String tag, @org.jetbrains.annotations.NotNull()
    java.lang.String source) {
    }
    
    public final void trackTagQuantity(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, int quantity) {
    }
    
    public final void trackBlogPost(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, long blogId, long postId) {
    }
    
    public final void trackBlogPost(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, long blogId, long postId, boolean isJetpack) {
    }
    
    public final void trackBlogPost(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.NotNull()
    java.lang.String blogId, @org.jetbrains.annotations.NotNull()
    java.lang.String postId, int commentId) {
    }
    
    public final void trackFeedPost(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, long feedId, long feedItemId) {
    }
    
    public final void trackPost(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.models.ReaderPost post) {
    }
    
    public final void trackPost(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.models.ReaderPost post, @org.jetbrains.annotations.NotNull()
    java.lang.String source) {
    }
    
    private final void trackPost(org.wordpress.android.analytics.AnalyticsTracker.Stat stat, org.wordpress.android.models.ReaderPost post, java.util.Map<java.lang.String, ?> properties) {
    }
    
    public final void trackPostComments(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, long blogId, long postId, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.models.ReaderPost post, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, ?> properties) {
    }
    
    /**
     * Track when app launched via deep-linking but then fell back to external browser
     *
     * @param stat The Stat to bump
     * @param interceptedUri The fallback URI the app was started with
     */
    public final void trackUri(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.NotNull()
    java.lang.String interceptedUri) {
    }
    
    public final void trackQuery(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    public final void trackDeepLink(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.NotNull()
    java.lang.String action, @org.jetbrains.annotations.NotNull()
    java.lang.String host, @org.jetbrains.annotations.Nullable()
    android.net.Uri uri) {
    }
    
    public final void trackRailcar(@org.jetbrains.annotations.NotNull()
    java.lang.String railcarJson) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmOverloads()
    public final java.lang.String getSource(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.ReaderTypes.ReaderPostListType postListType) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @kotlin.jvm.JvmOverloads()
    public final java.lang.String getSource(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.ReaderTypes.ReaderPostListType postListType, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.reader.tracker.ReaderTab readerTab) {
        return null;
    }
    
    @kotlin.jvm.JvmStatic()
    public static final void trackTag(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.NotNull()
    java.lang.String tag) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b$\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u0004J\u0018\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\'\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u00060"}, d2 = {"Lorg/wordpress/android/ui/reader/tracker/ReaderTracker$Companion;", "", "()V", "BLOG_ID_KEY", "", "COMMENT_ID_KEY", "FEED_ID_KEY", "FEED_ITEM_ID_KEY", "FOLLOW_KEY", "INTERCEPTED_URI_KEY", "IS_JETPACK_KEY", "POST_ID_KEY", "QUANTITY_KEY", "QUERY_KEY", "SOURCE_A8C", "SOURCE_ACTIVITY_LOG_DETAIL", "SOURCE_COMMENT", "SOURCE_CUSTOM", "SOURCE_DISCOVER", "SOURCE_FOLLOWING", "SOURCE_KEY", "SOURCE_LIKED", "SOURCE_NOTIFICATION", "SOURCE_NOTIF_LIKE_LIST_USER_PROFILE", "SOURCE_P2", "SOURCE_POST_DETAIL", "SOURCE_POST_DETAIL_COMMENT_SNIPPET", "SOURCE_POST_LIST_SAVED_POST_NOTICE", "SOURCE_READER_LIKE_LIST", "SOURCE_READER_LIKE_LIST_USER_PROFILE", "SOURCE_SAVED", "SOURCE_SEARCH", "SOURCE_SETTINGS", "SOURCE_SITE_PREVIEW", "SOURCE_STATS", "SOURCE_TAG_PREVIEW", "SOURCE_USER", "SOURCE_USER_PROFILE_UNKNOWN", "TAG_KEY", "UNKNOWN_VALUE", "isUserProfileSource", "", "source", "trackTag", "", "stat", "Lorg/wordpress/android/analytics/AnalyticsTracker$Stat;", "tag", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @kotlin.jvm.JvmStatic()
        public final void trackTag(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.analytics.AnalyticsTracker.Stat stat, @org.jetbrains.annotations.NotNull()
        java.lang.String tag) {
        }
        
        public final boolean isUserProfileSource(@org.jetbrains.annotations.NotNull()
        java.lang.String source) {
            return false;
        }
    }
}