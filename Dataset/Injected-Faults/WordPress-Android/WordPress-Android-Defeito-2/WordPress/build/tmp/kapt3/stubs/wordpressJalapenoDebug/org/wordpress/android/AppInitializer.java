package org.wordpress.android;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00c6\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u0000 \u00e6\u00012\u00020\u0001:\b\u00e5\u0001\u00e6\u0001\u00e7\u0001\u00e8\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\n\u0010\u00c6\u0001\u001a\u00030\u00c7\u0001H\u0002J\n\u0010\u00c8\u0001\u001a\u00030\u00c9\u0001H\u0002J\b\u0010\u00ca\u0001\u001a\u00030\u00c9\u0001J\u0014\u0010\u00cb\u0001\u001a\u00030\u00c9\u00012\b\u0010\u00cc\u0001\u001a\u00030\u00cd\u0001H\u0002J\n\u0010\u00ce\u0001\u001a\u00030\u00c9\u0001H\u0002J\n\u0010\u00cf\u0001\u001a\u00030\u00c9\u0001H\u0002J\b\u0010\u00d0\u0001\u001a\u00030\u00c9\u0001J\u0014\u0010\u00d1\u0001\u001a\u00030\u00c9\u00012\b\u0010\u00d2\u0001\u001a\u00030\u0087\u0001H\u0002J\n\u0010\u00d3\u0001\u001a\u00030\u00c9\u0001H\u0002J\n\u0010\u00d4\u0001\u001a\u00030\u00c9\u0001H\u0002J\n\u0010\u00d5\u0001\u001a\u00030\u00c9\u0001H\u0002J\n\u0010\u00d6\u0001\u001a\u00030\u00c9\u0001H\u0002J\u0014\u0010\u00d7\u0001\u001a\u00030\u00c9\u00012\b\u0010\u00d8\u0001\u001a\u00030\u00d9\u0001H\u0007J\n\u0010\u00da\u0001\u001a\u00030\u00c9\u0001H\u0007J\n\u0010\u00db\u0001\u001a\u00030\u00c9\u0001H\u0007J\u0014\u0010\u00dc\u0001\u001a\u00030\u00c9\u00012\b\u0010\u00d8\u0001\u001a\u00030\u00dd\u0001H\u0007J\u0014\u0010\u00de\u0001\u001a\u00030\u00c9\u00012\b\u0010\u00d8\u0001\u001a\u00030\u00df\u0001H\u0007J\u0014\u0010\u00e0\u0001\u001a\u00030\u00c9\u00012\b\u0010\u00cc\u0001\u001a\u00030\u00cd\u0001H\u0002J\n\u0010\u00e1\u0001\u001a\u00030\u00c9\u0001H\u0002J\n\u0010\u00e2\u0001\u001a\u00030\u00c9\u0001H\u0002J\n\u0010\u00e3\u0001\u001a\u00030\u00c9\u0001H\u0002J\b\u0010\u00e4\u0001\u001a\u00030\u00c9\u0001R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001e\u0010\r\u001a\u00020\u000e8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R$\u0010\u0013\u001a\u00020\u00148\u0006@\u0006X\u0087.\u00a2\u0006\u0014\n\u0000\u0012\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u001b\u001a\u00060\u001cR\u00020\u0000X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u001d\u001a\u00020\u001e8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u000e\u0010#\u001a\u00020$X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010%\u001a\u00020&8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010(\"\u0004\b)\u0010*R\u001e\u0010+\u001a\u00020,8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001e\u00101\u001a\u0002028\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u001e\u00107\u001a\u0002088\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u001e\u0010=\u001a\u00020>8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u001e\u0010C\u001a\u00020D8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\u001e\u0010I\u001a\u00020J8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bK\u0010L\"\u0004\bM\u0010NR\u001e\u0010O\u001a\u00020P8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010TR\u001e\u0010U\u001a\u00020V8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bW\u0010X\"\u0004\bY\u0010ZR\u001e\u0010[\u001a\u00020\\8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b]\u0010^\"\u0004\b_\u0010`R\u001e\u0010a\u001a\u00020b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bc\u0010d\"\u0004\be\u0010fR\u001e\u0010g\u001a\u00020h8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bi\u0010j\"\u0004\bk\u0010lR\u001e\u0010m\u001a\u00020n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bo\u0010p\"\u0004\bq\u0010rR$\u0010s\u001a\u00020t8\u0006@\u0006X\u0087.\u00a2\u0006\u0014\n\u0000\u0012\u0004\bu\u0010\u0016\u001a\u0004\bv\u0010w\"\u0004\bx\u0010yR\u001e\u0010z\u001a\u00020{8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b|\u0010}\"\u0004\b~\u0010\u007fR$\u0010\u0080\u0001\u001a\u00030\u0081\u00018\u0006@\u0006X\u0087.\u00a2\u0006\u0012\n\u0000\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001\"\u0006\b\u0084\u0001\u0010\u0085\u0001R\u0010\u0010\u0086\u0001\u001a\u00030\u0087\u0001X\u0082\u000e\u00a2\u0006\u0002\n\u0000R$\u0010\u0088\u0001\u001a\u00030\u0089\u00018\u0006@\u0006X\u0087.\u00a2\u0006\u0012\n\u0000\u001a\u0006\b\u008a\u0001\u0010\u008b\u0001\"\u0006\b\u008c\u0001\u0010\u008d\u0001R$\u0010\u008e\u0001\u001a\u00030\u008f\u00018\u0006@\u0006X\u0087.\u00a2\u0006\u0012\n\u0000\u001a\u0006\b\u0090\u0001\u0010\u0091\u0001\"\u0006\b\u0092\u0001\u0010\u0093\u0001R$\u0010\u0094\u0001\u001a\u00030\u0095\u00018\u0006@\u0006X\u0087.\u00a2\u0006\u0012\n\u0000\u001a\u0006\b\u0096\u0001\u0010\u0097\u0001\"\u0006\b\u0098\u0001\u0010\u0099\u0001R,\u0010\u009c\u0001\u001a\u00070\u009b\u0001R\u00020\u00002\f\u0010\u009a\u0001\u001a\u00070\u009b\u0001R\u00020\u0000@BX\u0086.\u00a2\u0006\n\n\u0000\u001a\u0006\b\u009d\u0001\u0010\u009e\u0001R$\u0010\u009f\u0001\u001a\u00030\u00a0\u00018\u0006@\u0006X\u0087.\u00a2\u0006\u0012\n\u0000\u001a\u0006\b\u00a1\u0001\u0010\u00a2\u0001\"\u0006\b\u00a3\u0001\u0010\u00a4\u0001R$\u0010\u00a5\u0001\u001a\u00030\u00a6\u00018\u0006@\u0006X\u0087.\u00a2\u0006\u0012\n\u0000\u001a\u0006\b\u00a7\u0001\u0010\u00a8\u0001\"\u0006\b\u00a9\u0001\u0010\u00aa\u0001R \u0010\u00ab\u0001\u001a\u00030\u00ac\u0001X\u0086\u000e\u00a2\u0006\u0012\n\u0000\u001a\u0006\b\u00ad\u0001\u0010\u00ae\u0001\"\u0006\b\u00af\u0001\u0010\u00b0\u0001R \u0010\u00b1\u0001\u001a\u00030\u00ac\u0001X\u0086\u000e\u00a2\u0006\u0012\n\u0000\u001a\u0006\b\u00b2\u0001\u0010\u00ae\u0001\"\u0006\b\u00b3\u0001\u0010\u00b0\u0001R$\u0010\u00b4\u0001\u001a\u00030\u00b5\u00018\u0006@\u0006X\u0087.\u00a2\u0006\u0012\n\u0000\u001a\u0006\b\u00b6\u0001\u0010\u00b7\u0001\"\u0006\b\u00b8\u0001\u0010\u00b9\u0001R$\u0010\u00ba\u0001\u001a\u00030\u00bb\u00018\u0006@\u0006X\u0087.\u00a2\u0006\u0012\n\u0000\u001a\u0006\b\u00bc\u0001\u0010\u00bd\u0001\"\u0006\b\u00be\u0001\u0010\u00bf\u0001R$\u0010\u00c0\u0001\u001a\u00030\u00c1\u00018\u0006@\u0006X\u0087.\u00a2\u0006\u0012\n\u0000\u001a\u0006\b\u00c2\u0001\u0010\u00c3\u0001\"\u0006\b\u00c4\u0001\u0010\u00c5\u0001\u00a8\u0006\u00e9\u0001"}, d2 = {"Lorg/wordpress/android/AppInitializer;", "Landroidx/lifecycle/LifecycleObserver;", "wellSqlInitializer", "Lorg/wordpress/android/WellSqlInitializer;", "application", "Landroid/app/Application;", "(Lorg/wordpress/android/WellSqlInitializer;Landroid/app/Application;)V", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "getAccountStore", "()Lorg/wordpress/android/fluxc/store/AccountStore;", "setAccountStore", "(Lorg/wordpress/android/fluxc/store/AccountStore;)V", "appConfig", "Lorg/wordpress/android/util/config/AppConfig;", "getAppConfig", "()Lorg/wordpress/android/util/config/AppConfig;", "setAppConfig", "(Lorg/wordpress/android/util/config/AppConfig;)V", "appScope", "Lkotlinx/coroutines/CoroutineScope;", "getAppScope$annotations", "()V", "getAppScope", "()Lkotlinx/coroutines/CoroutineScope;", "setAppScope", "(Lkotlinx/coroutines/CoroutineScope;)V", "applicationLifecycleMonitor", "Lorg/wordpress/android/AppInitializer$ApplicationLifecycleMonitor;", "crashLogging", "Lcom/automattic/android/tracks/crashlogging/CrashLogging;", "getCrashLogging", "()Lcom/automattic/android/tracks/crashlogging/CrashLogging;", "setCrashLogging", "(Lcom/automattic/android/tracks/crashlogging/CrashLogging;)V", "credentialsClient", "Lcom/google/android/gms/common/api/GoogleApiClient;", "debugCookieManager", "Lorg/wordpress/android/ui/debug/cookies/DebugCookieManager;", "getDebugCookieManager", "()Lorg/wordpress/android/ui/debug/cookies/DebugCookieManager;", "setDebugCookieManager", "(Lorg/wordpress/android/ui/debug/cookies/DebugCookieManager;)V", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "getDispatcher", "()Lorg/wordpress/android/fluxc/Dispatcher;", "setDispatcher", "(Lorg/wordpress/android/fluxc/Dispatcher;)V", "encryptedLogging", "Lorg/wordpress/android/util/EncryptedLogging;", "getEncryptedLogging", "()Lorg/wordpress/android/util/EncryptedLogging;", "setEncryptedLogging", "(Lorg/wordpress/android/util/EncryptedLogging;)V", "exPlat", "Lorg/wordpress/android/util/experiments/ExPlat;", "getExPlat", "()Lorg/wordpress/android/util/experiments/ExPlat;", "setExPlat", "(Lorg/wordpress/android/util/experiments/ExPlat;)V", "imageEditorFileUtils", "Lorg/wordpress/android/ui/posts/editor/ImageEditorFileUtils;", "getImageEditorFileUtils", "()Lorg/wordpress/android/ui/posts/editor/ImageEditorFileUtils;", "setImageEditorFileUtils", "(Lorg/wordpress/android/ui/posts/editor/ImageEditorFileUtils;)V", "imageEditorTracker", "Lorg/wordpress/android/ui/posts/editor/ImageEditorTracker;", "getImageEditorTracker", "()Lorg/wordpress/android/ui/posts/editor/ImageEditorTracker;", "setImageEditorTracker", "(Lorg/wordpress/android/ui/posts/editor/ImageEditorTracker;)V", "imageLoader", "Lorg/wordpress/android/fluxc/tools/FluxCImageLoader;", "getImageLoader", "()Lorg/wordpress/android/fluxc/tools/FluxCImageLoader;", "setImageLoader", "(Lorg/wordpress/android/fluxc/tools/FluxCImageLoader;)V", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "getImageManager", "()Lorg/wordpress/android/util/image/ImageManager;", "setImageManager", "(Lorg/wordpress/android/util/image/ImageManager;)V", "mediaStore", "Lorg/wordpress/android/fluxc/store/MediaStore;", "getMediaStore", "()Lorg/wordpress/android/fluxc/store/MediaStore;", "setMediaStore", "(Lorg/wordpress/android/fluxc/store/MediaStore;)V", "mySiteDefaultTabExperiment", "Lorg/wordpress/android/ui/mysite/tabs/MySiteDefaultTabExperiment;", "getMySiteDefaultTabExperiment", "()Lorg/wordpress/android/ui/mysite/tabs/MySiteDefaultTabExperiment;", "setMySiteDefaultTabExperiment", "(Lorg/wordpress/android/ui/mysite/tabs/MySiteDefaultTabExperiment;)V", "oAuthAuthenticator", "Lorg/wordpress/android/networking/OAuthAuthenticator;", "getOAuthAuthenticator", "()Lorg/wordpress/android/networking/OAuthAuthenticator;", "setOAuthAuthenticator", "(Lorg/wordpress/android/networking/OAuthAuthenticator;)V", "privateAtomicCookie", "Lorg/wordpress/android/fluxc/network/rest/wpcom/site/PrivateAtomicCookie;", "getPrivateAtomicCookie", "()Lorg/wordpress/android/fluxc/network/rest/wpcom/site/PrivateAtomicCookie;", "setPrivateAtomicCookie", "(Lorg/wordpress/android/fluxc/network/rest/wpcom/site/PrivateAtomicCookie;)V", "readerTracker", "Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;", "getReaderTracker", "()Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;", "setReaderTracker", "(Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;)V", "requestQueue", "Lcom/android/volley/RequestQueue;", "getRequestQueue$annotations", "getRequestQueue", "()Lcom/android/volley/RequestQueue;", "setRequestQueue", "(Lcom/android/volley/RequestQueue;)V", "selectedSiteRepository", "Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;", "getSelectedSiteRepository", "()Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;", "setSelectedSiteRepository", "(Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;)V", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "getSiteStore", "()Lorg/wordpress/android/fluxc/store/SiteStore;", "setSiteStore", "(Lorg/wordpress/android/fluxc/store/SiteStore;)V", "startDate", "", "statsStore", "Lorg/wordpress/android/fluxc/store/StatsStore;", "getStatsStore", "()Lorg/wordpress/android/fluxc/store/StatsStore;", "setStatsStore", "(Lorg/wordpress/android/fluxc/store/StatsStore;)V", "statsWidgetUpdaters", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/WidgetUpdater$StatsWidgetUpdaters;", "getStatsWidgetUpdaters", "()Lorg/wordpress/android/ui/stats/refresh/lists/widget/WidgetUpdater$StatsWidgetUpdaters;", "setStatsWidgetUpdaters", "(Lorg/wordpress/android/ui/stats/refresh/lists/widget/WidgetUpdater$StatsWidgetUpdaters;)V", "storyMediaSaveUploadBridge", "Lorg/wordpress/android/ui/stories/media/StoryMediaSaveUploadBridge;", "getStoryMediaSaveUploadBridge", "()Lorg/wordpress/android/ui/stories/media/StoryMediaSaveUploadBridge;", "setStoryMediaSaveUploadBridge", "(Lorg/wordpress/android/ui/stories/media/StoryMediaSaveUploadBridge;)V", "<set-?>", "Lorg/wordpress/android/AppInitializer$StoryNotificationTrackerProvider;", "storyNotificationTrackerProvider", "getStoryNotificationTrackerProvider", "()Lorg/wordpress/android/AppInitializer$StoryNotificationTrackerProvider;", "systemNotificationsTracker", "Lorg/wordpress/android/ui/notifications/SystemNotificationsTracker;", "getSystemNotificationsTracker", "()Lorg/wordpress/android/ui/notifications/SystemNotificationsTracker;", "setSystemNotificationsTracker", "(Lorg/wordpress/android/ui/notifications/SystemNotificationsTracker;)V", "tracker", "Lorg/wordpress/android/analytics/Tracker;", "getTracker", "()Lorg/wordpress/android/analytics/Tracker;", "setTracker", "(Lorg/wordpress/android/analytics/Tracker;)V", "updateSelectedSite", "Lorg/wordpress/android/util/RateLimitedTask;", "getUpdateSelectedSite", "()Lorg/wordpress/android/util/RateLimitedTask;", "setUpdateSelectedSite", "(Lorg/wordpress/android/util/RateLimitedTask;)V", "updateSiteList", "getUpdateSiteList", "setUpdateSiteList", "uploadStarter", "Lorg/wordpress/android/ui/uploads/UploadStarter;", "getUploadStarter", "()Lorg/wordpress/android/ui/uploads/UploadStarter;", "setUploadStarter", "(Lorg/wordpress/android/ui/uploads/UploadStarter;)V", "wordPressWorkerFactory", "Lorg/wordpress/android/workers/WordPressWorkersFactory;", "getWordPressWorkerFactory", "()Lorg/wordpress/android/workers/WordPressWorkersFactory;", "setWordPressWorkerFactory", "(Lorg/wordpress/android/workers/WordPressWorkersFactory;)V", "zendeskHelper", "Lorg/wordpress/android/support/ZendeskHelper;", "getZendeskHelper", "()Lorg/wordpress/android/support/ZendeskHelper;", "setZendeskHelper", "(Lorg/wordpress/android/support/ZendeskHelper;)V", "createAndVerifyWpDb", "", "createNotificationChannelsOnSdk26", "", "deferredInit", "enableHttpResponseCache", "context", "Landroid/content/Context;", "enableLogRecording", "flushHttpCache", "init", "initAnalytics", "elapsedTimeOnCreate", "initAnalyticsExperimentPropertiesIfNeeded", "initEmojiCompat", "initWorkManager", "initWpDb", "onAccountChanged", "event", "Lorg/wordpress/android/fluxc/store/AccountStore$OnAccountChanged;", "onAppComesFromBackground", "onAppGoesToBackground", "onAuthenticationChanged", "Lorg/wordpress/android/fluxc/store/AccountStore$OnAuthenticationChanged;", "onUnexpectedError", "Lorg/wordpress/android/fluxc/utils/ErrorUtils$OnUnexpectedError;", "removeWpComUserRelatedData", "sanitizeMediaUploadStateForSite", "setWebViewDataDirectorySuffixOnAndroidP", "setupCredentialsClient", "wordPressComSignOut", "ApplicationLifecycleMonitor", "Companion", "MemoryAndConfigChangeMonitor", "StoryNotificationTrackerProvider", "org.wordpress.android_wordpressJalapenoDebug"})
@javax.inject.Singleton()
public final class AppInitializer implements androidx.lifecycle.LifecycleObserver {
    private final android.app.Application application = null;
    @javax.inject.Inject()
    public org.wordpress.android.fluxc.Dispatcher dispatcher;
    @javax.inject.Inject()
    public org.wordpress.android.fluxc.store.AccountStore accountStore;
    @javax.inject.Inject()
    public org.wordpress.android.fluxc.store.SiteStore siteStore;
    @javax.inject.Inject()
    public org.wordpress.android.fluxc.store.MediaStore mediaStore;
    @javax.inject.Inject()
    public org.wordpress.android.support.ZendeskHelper zendeskHelper;
    @javax.inject.Inject()
    public org.wordpress.android.ui.uploads.UploadStarter uploadStarter;
    @javax.inject.Inject()
    public org.wordpress.android.ui.stats.refresh.lists.widget.WidgetUpdater.StatsWidgetUpdaters statsWidgetUpdaters;
    @javax.inject.Inject()
    public org.wordpress.android.fluxc.store.StatsStore statsStore;
    @javax.inject.Inject()
    public org.wordpress.android.ui.notifications.SystemNotificationsTracker systemNotificationsTracker;
    @javax.inject.Inject()
    public org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker;
    @javax.inject.Inject()
    public org.wordpress.android.util.image.ImageManager imageManager;
    @javax.inject.Inject()
    public org.wordpress.android.fluxc.network.rest.wpcom.site.PrivateAtomicCookie privateAtomicCookie;
    @javax.inject.Inject()
    public org.wordpress.android.ui.posts.editor.ImageEditorTracker imageEditorTracker;
    @javax.inject.Inject()
    public org.wordpress.android.ui.stories.media.StoryMediaSaveUploadBridge storyMediaSaveUploadBridge;
    @javax.inject.Inject()
    public com.automattic.android.tracks.crashlogging.CrashLogging crashLogging;
    @javax.inject.Inject()
    public org.wordpress.android.util.EncryptedLogging encryptedLogging;
    @javax.inject.Inject()
    public org.wordpress.android.util.config.AppConfig appConfig;
    @javax.inject.Inject()
    public org.wordpress.android.ui.posts.editor.ImageEditorFileUtils imageEditorFileUtils;
    @javax.inject.Inject()
    public org.wordpress.android.util.experiments.ExPlat exPlat;
    @javax.inject.Inject()
    public org.wordpress.android.workers.WordPressWorkersFactory wordPressWorkerFactory;
    @javax.inject.Inject()
    public org.wordpress.android.ui.debug.cookies.DebugCookieManager debugCookieManager;
    @javax.inject.Inject()
    public kotlinx.coroutines.CoroutineScope appScope;
    @javax.inject.Inject()
    public org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository;
    @javax.inject.Inject()
    public org.wordpress.android.ui.mysite.tabs.MySiteDefaultTabExperiment mySiteDefaultTabExperiment;
    @javax.inject.Inject()
    public org.wordpress.android.analytics.Tracker tracker;
    @javax.inject.Inject()
    public com.android.volley.RequestQueue requestQueue;
    @javax.inject.Inject()
    public org.wordpress.android.fluxc.tools.FluxCImageLoader imageLoader;
    @javax.inject.Inject()
    public org.wordpress.android.networking.OAuthAuthenticator oAuthAuthenticator;
    private org.wordpress.android.AppInitializer.ApplicationLifecycleMonitor applicationLifecycleMonitor;
    private org.wordpress.android.AppInitializer.StoryNotificationTrackerProvider storyNotificationTrackerProvider;
    private com.google.android.gms.common.api.GoogleApiClient credentialsClient;
    private long startDate;
    
    /**
     * Update site list in a background task. (WPCOM site list, and eventually self hosted multisites)
     */
    @org.jetbrains.annotations.NotNull()
    private org.wordpress.android.util.RateLimitedTask updateSiteList;
    
    /**
     * Update site information in a background task.
     */
    @org.jetbrains.annotations.NotNull()
    private org.wordpress.android.util.RateLimitedTask updateSelectedSite;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.AppInitializer.Companion Companion = null;
    private static final int SECONDS_BETWEEN_SITE_UPDATE = 3600;
    private static final int SECONDS_BETWEEN_BLOGLIST_UPDATE = 900;
    private static final int MAX_LOG_COUNT = 5;
    private static final long HTTP_CACHE_SIZE = 5242880L;
    private static final int KILOBYTES_IN_BYTES = 1024;
    private static final double MEMORY_CACHE_RATIO = 0.25;
    private static final int DEFAULT_TIMEOUT = 120;
    @org.jetbrains.annotations.Nullable()
    @android.annotation.SuppressLint(value = {"StaticFieldLeak"})
    private static android.content.Context context;
    private static boolean initialized = false;
    private static org.wordpress.android.util.BitmapLruCache bitmapCache;
    private static org.wordpress.android.networking.OAuthAuthenticator sOAuthAuthenticator;
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy restClientUtils$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy restClientUtilsV1_1$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy restClientUtilsV1_2$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy restClientUtilsV1_3$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy restClientUtilsV2$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy restClientUtilsV0$delegate = null;
    
    /**
     * Device's default User-Agent string.
     * E.g.:
     * "Mozilla/5.0 (Linux; Android 6.0; Android SDK built for x86_64 Build/MASTER; wv)
     * AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/44.0.2403.119 Mobile
     * Safari/537.36"
     */
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy defaultUserAgent$delegate = null;
    
    /**
     * User-Agent string when making HTTP connections, for both API traffic and WebViews. Appends
     * "wp-android/version" to WebView's default User-Agent string for the webservers to get the full feature list
     * of the browser and serve content accordingly, e.g.:
     * "Mozilla/5.0 (Linux; Android 6.0; Android SDK built for x86_64 Build/MASTER; wv)
     * AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/44.0.2403.119 Mobile
     * Safari/537.36 wp-android/4.7"
     * Note that app versions prior to 2.7 simply used "wp-android" as the user agent
     */
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy userAgent$delegate = null;
    
    @javax.inject.Inject()
    public AppInitializer(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.WellSqlInitializer wellSqlInitializer, @org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.Dispatcher getDispatcher() {
        return null;
    }
    
    public final void setDispatcher(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.AccountStore getAccountStore() {
        return null;
    }
    
    public final void setAccountStore(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.SiteStore getSiteStore() {
        return null;
    }
    
    public final void setSiteStore(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.MediaStore getMediaStore() {
        return null;
    }
    
    public final void setMediaStore(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.MediaStore p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.support.ZendeskHelper getZendeskHelper() {
        return null;
    }
    
    public final void setZendeskHelper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.support.ZendeskHelper p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.uploads.UploadStarter getUploadStarter() {
        return null;
    }
    
    public final void setUploadStarter(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.uploads.UploadStarter p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.lists.widget.WidgetUpdater.StatsWidgetUpdaters getStatsWidgetUpdaters() {
        return null;
    }
    
    public final void setStatsWidgetUpdaters(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.widget.WidgetUpdater.StatsWidgetUpdaters p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.StatsStore getStatsStore() {
        return null;
    }
    
    public final void setStatsStore(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.StatsStore p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.notifications.SystemNotificationsTracker getSystemNotificationsTracker() {
        return null;
    }
    
    public final void setSystemNotificationsTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.notifications.SystemNotificationsTracker p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.reader.tracker.ReaderTracker getReaderTracker() {
        return null;
    }
    
    public final void setReaderTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.tracker.ReaderTracker p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.image.ImageManager getImageManager() {
        return null;
    }
    
    public final void setImageManager(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.network.rest.wpcom.site.PrivateAtomicCookie getPrivateAtomicCookie() {
        return null;
    }
    
    public final void setPrivateAtomicCookie(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.network.rest.wpcom.site.PrivateAtomicCookie p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.posts.editor.ImageEditorTracker getImageEditorTracker() {
        return null;
    }
    
    public final void setImageEditorTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.ImageEditorTracker p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stories.media.StoryMediaSaveUploadBridge getStoryMediaSaveUploadBridge() {
        return null;
    }
    
    public final void setStoryMediaSaveUploadBridge(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stories.media.StoryMediaSaveUploadBridge p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.automattic.android.tracks.crashlogging.CrashLogging getCrashLogging() {
        return null;
    }
    
    public final void setCrashLogging(@org.jetbrains.annotations.NotNull()
    com.automattic.android.tracks.crashlogging.CrashLogging p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.EncryptedLogging getEncryptedLogging() {
        return null;
    }
    
    public final void setEncryptedLogging(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.EncryptedLogging p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.config.AppConfig getAppConfig() {
        return null;
    }
    
    public final void setAppConfig(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.AppConfig p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.posts.editor.ImageEditorFileUtils getImageEditorFileUtils() {
        return null;
    }
    
    public final void setImageEditorFileUtils(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.ImageEditorFileUtils p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.experiments.ExPlat getExPlat() {
        return null;
    }
    
    public final void setExPlat(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.experiments.ExPlat p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.workers.WordPressWorkersFactory getWordPressWorkerFactory() {
        return null;
    }
    
    public final void setWordPressWorkerFactory(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.workers.WordPressWorkersFactory p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.debug.cookies.DebugCookieManager getDebugCookieManager() {
        return null;
    }
    
    public final void setDebugCookieManager(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.debug.cookies.DebugCookieManager p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.CoroutineScope getAppScope() {
        return null;
    }
    
    @javax.inject.Named(value = "APPLICATION_SCOPE")
    @java.lang.Deprecated()
    public static void getAppScope$annotations() {
    }
    
    public final void setAppScope(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.mysite.SelectedSiteRepository getSelectedSiteRepository() {
        return null;
    }
    
    public final void setSelectedSiteRepository(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.SelectedSiteRepository p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.mysite.tabs.MySiteDefaultTabExperiment getMySiteDefaultTabExperiment() {
        return null;
    }
    
    public final void setMySiteDefaultTabExperiment(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.tabs.MySiteDefaultTabExperiment p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.analytics.Tracker getTracker() {
        return null;
    }
    
    public final void setTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.analytics.Tracker p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.android.volley.RequestQueue getRequestQueue() {
        return null;
    }
    
    @javax.inject.Named(value = "custom-ssl")
    @java.lang.Deprecated()
    public static void getRequestQueue$annotations() {
    }
    
    public final void setRequestQueue(@org.jetbrains.annotations.NotNull()
    com.android.volley.RequestQueue p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.tools.FluxCImageLoader getImageLoader() {
        return null;
    }
    
    public final void setImageLoader(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.tools.FluxCImageLoader p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.networking.OAuthAuthenticator getOAuthAuthenticator() {
        return null;
    }
    
    public final void setOAuthAuthenticator(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.networking.OAuthAuthenticator p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.AppInitializer.StoryNotificationTrackerProvider getStoryNotificationTrackerProvider() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.RateLimitedTask getUpdateSiteList() {
        return null;
    }
    
    public final void setUpdateSiteList(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.RateLimitedTask p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.RateLimitedTask getUpdateSelectedSite() {
        return null;
    }
    
    public final void setUpdateSelectedSite(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.RateLimitedTask p0) {
    }
    
    public final void init() {
    }
    
    private final void initWorkManager() {
    }
    
    private final void enableLogRecording() {
    }
    
    private final void sanitizeMediaUploadStateForSite() {
    }
    
    private final void setupCredentialsClient() {
    }
    
    private final void createNotificationChannelsOnSdk26() {
    }
    
    private final void initAnalytics(long elapsedTimeOnCreate) {
    }
    
    /**
     * Application.onCreate is called before any activity, service, or receiver - it can be called while the app
     * is in background by a sticky service or a receiver, so we don't want Application.onCreate to make network request
     * or other heavy tasks.
     *
     *
     * This deferredInit method is called when a user starts an activity for the first time, ie. when he sees a
     * screen for the first time. This allows us to have heavy calls on first activity startup instead of app startup.
     */
    public final void deferredInit() {
    }
    
    private final void initWpDb() {
    }
    
    private final boolean createAndVerifyWpDb() {
        return false;
    }
    
    /**
     * Sign out from wpcom account.
     * Note: This method must not be called on UI Thread.
     */
    public final void wordPressComSignOut() {
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onAccountChanged(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore.OnAccountChanged event) {
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onAuthenticationChanged(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore.OnAuthenticationChanged event) {
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onUnexpectedError(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.utils.ErrorUtils.OnUnexpectedError event) {
    }
    
    private final void removeWpComUserRelatedData(android.content.Context context) {
    }
    
    private final void setWebViewDataDirectorySuffixOnAndroidP() {
    }
    
    private final void enableHttpResponseCache(android.content.Context context) {
    }
    
    private final void flushHttpCache() {
    }
    
    private final void initEmojiCompat() {
    }
    
    private final void initAnalyticsExperimentPropertiesIfNeeded() {
    }
    
    @kotlin.Suppress(names = {"unused"})
    @androidx.lifecycle.OnLifecycleEvent(value = androidx.lifecycle.Lifecycle.Event.ON_START)
    public final void onAppComesFromBackground() {
    }
    
    @kotlin.Suppress(names = {"unused"})
    @androidx.lifecycle.OnLifecycleEvent(value = androidx.lifecycle.Lifecycle.Event.ON_STOP)
    public final void onAppGoesToBackground() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u000fJ\b\u0010\u0011\u001a\u00020\u000fH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\u00068BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\tR\u0010\u0010\r\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/AppInitializer$ApplicationLifecycleMonitor;", "", "(Lorg/wordpress/android/AppInitializer;)V", "applicationOpenedDate", "Ljava/util/Date;", "connectionReceiverRegistered", "", "firstActivityResumed", "getFirstActivityResumed", "()Z", "setFirstActivityResumed", "(Z)V", "isPushNotificationPingNeeded", "lastPingDate", "onAppComesFromBackground", "", "onAppGoesToBackground", "updatePushNotificationTokenIfNotLimited", "org.wordpress.android_wordpressJalapenoDebug"})
    public final class ApplicationLifecycleMonitor {
        private java.util.Date lastPingDate;
        private java.util.Date applicationOpenedDate;
        private boolean connectionReceiverRegistered = false;
        private boolean firstActivityResumed = true;
        
        public ApplicationLifecycleMonitor() {
            super();
        }
        
        public final boolean getFirstActivityResumed() {
            return false;
        }
        
        public final void setFirstActivityResumed(boolean p0) {
        }
        
        private final boolean isPushNotificationPingNeeded() {
            return false;
        }
        
        /**
         * Check if user has valid credentials, and at least 2 minutes have passed since the last ping, then try to
         * update the PN token.
         */
        private final void updatePushNotificationTokenIfNotLimited() {
        }
        
        public final void onAppGoesToBackground() {
        }
        
        /**
         * This method is called when:
         * 1. the app starts (but it's not opened by a service or a broadcast receiver, i.e. an activity is resumed)
         * 2. the app was in background and is now foreground
         */
        public final void onAppComesFromBackground() {
        }
    }
    
    /**
     * Uses ComponentCallbacks2 is used for memory-related event handling and configuration changes
     */
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0004H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH\u0016\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/AppInitializer$MemoryAndConfigChangeMonitor;", "Landroid/content/ComponentCallbacks2;", "(Lorg/wordpress/android/AppInitializer;)V", "onConfigurationChanged", "", "newConfig", "Landroid/content/res/Configuration;", "onLowMemory", "onTrimMemory", "level", "", "org.wordpress.android_wordpressJalapenoDebug"})
    final class MemoryAndConfigChangeMonitor implements android.content.ComponentCallbacks2 {
        
        public MemoryAndConfigChangeMonitor() {
            super();
        }
        
        @java.lang.Override()
        public void onConfigurationChanged(@org.jetbrains.annotations.NotNull()
        android.content.res.Configuration newConfig) {
        }
        
        @java.lang.Override()
        public void onLowMemory() {
        }
        
        @java.lang.Override()
        public void onTrimMemory(int level) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/AppInitializer$StoryNotificationTrackerProvider;", "Lcom/wordpress/stories/compose/NotificationTrackerProvider;", "(Lorg/wordpress/android/AppInitializer;)V", "trackDismissedNotification", "", "storyNotificationType", "Lcom/wordpress/stories/compose/frame/StoryNotificationType;", "trackShownNotification", "trackTappedNotification", "translateNotificationTypes", "Lorg/wordpress/android/push/NotificationType;", "org.wordpress.android_wordpressJalapenoDebug"})
    public final class StoryNotificationTrackerProvider implements com.wordpress.stories.compose.NotificationTrackerProvider {
        
        public StoryNotificationTrackerProvider() {
            super();
        }
        
        private final org.wordpress.android.push.NotificationType translateNotificationTypes(com.wordpress.stories.compose.frame.StoryNotificationType storyNotificationType) {
            return null;
        }
        
        @java.lang.Override()
        public void trackShownNotification(@org.jetbrains.annotations.NotNull()
        com.wordpress.stories.compose.frame.StoryNotificationType storyNotificationType) {
        }
        
        @java.lang.Override()
        public void trackTappedNotification(@org.jetbrains.annotations.NotNull()
        com.wordpress.stories.compose.frame.StoryNotificationType storyNotificationType) {
        }
        
        @java.lang.Override()
        public void trackDismissedNotification(@org.jetbrains.annotations.NotNull()
        com.wordpress.stories.compose.frame.StoryNotificationType storyNotificationType) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u00106\u001a\u00020\u000eJ\u0006\u00107\u001a\u000208R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R \u0010\u000f\u001a\u0004\u0018\u00010\u00108\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0015\u001a\u00020\u00168FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u001d\u001a\u00020\u001e8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b!\u0010\u001a\u001a\u0004\b\u001f\u0010 R\u001b\u0010\"\u001a\u00020\u001e8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b$\u0010\u001a\u001a\u0004\b#\u0010 R\u001b\u0010%\u001a\u00020\u001e8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\'\u0010\u001a\u001a\u0004\b&\u0010 R\u001b\u0010(\u001a\u00020\u001e8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b*\u0010\u001a\u001a\u0004\b)\u0010 R\u001b\u0010+\u001a\u00020\u001e8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b-\u0010\u001a\u001a\u0004\b,\u0010 R\u001b\u0010.\u001a\u00020\u001e8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b0\u0010\u001a\u001a\u0004\b/\u0010 R\u0010\u00101\u001a\u0004\u0018\u000102X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u00103\u001a\u00020\u00168FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b5\u0010\u001a\u001a\u0004\b4\u0010\u0018\u00a8\u00069"}, d2 = {"Lorg/wordpress/android/AppInitializer$Companion;", "", "()V", "DEFAULT_TIMEOUT", "", "HTTP_CACHE_SIZE", "", "KILOBYTES_IN_BYTES", "MAX_LOG_COUNT", "MEMORY_CACHE_RATIO", "", "SECONDS_BETWEEN_BLOGLIST_UPDATE", "SECONDS_BETWEEN_SITE_UPDATE", "bitmapCache", "Lorg/wordpress/android/util/BitmapLruCache;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "defaultUserAgent", "", "getDefaultUserAgent", "()Ljava/lang/String;", "defaultUserAgent$delegate", "Lkotlin/Lazy;", "initialized", "", "restClientUtils", "Lorg/wordpress/android/networking/RestClientUtils;", "getRestClientUtils", "()Lorg/wordpress/android/networking/RestClientUtils;", "restClientUtils$delegate", "restClientUtilsV0", "getRestClientUtilsV0", "restClientUtilsV0$delegate", "restClientUtilsV1_1", "getRestClientUtilsV1_1", "restClientUtilsV1_1$delegate", "restClientUtilsV1_2", "getRestClientUtilsV1_2", "restClientUtilsV1_2$delegate", "restClientUtilsV1_3", "getRestClientUtilsV1_3", "restClientUtilsV1_3$delegate", "restClientUtilsV2", "getRestClientUtilsV2", "restClientUtilsV2$delegate", "sOAuthAuthenticator", "Lorg/wordpress/android/networking/OAuthAuthenticator;", "userAgent", "getUserAgent", "userAgent$delegate", "getBitmapCache", "updateContextLocale", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final android.content.Context getContext() {
            return null;
        }
        
        public final void setContext(@org.jetbrains.annotations.Nullable()
        android.content.Context p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.networking.RestClientUtils getRestClientUtils() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.networking.RestClientUtils getRestClientUtilsV1_1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.networking.RestClientUtils getRestClientUtilsV1_2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.networking.RestClientUtils getRestClientUtilsV1_3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.networking.RestClientUtils getRestClientUtilsV2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.networking.RestClientUtils getRestClientUtilsV0() {
            return null;
        }
        
        /**
         * Device's default User-Agent string.
         * E.g.:
         * "Mozilla/5.0 (Linux; Android 6.0; Android SDK built for x86_64 Build/MASTER; wv)
         * AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/44.0.2403.119 Mobile
         * Safari/537.36"
         */
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getDefaultUserAgent() {
            return null;
        }
        
        /**
         * User-Agent string when making HTTP connections, for both API traffic and WebViews. Appends
         * "wp-android/version" to WebView's default User-Agent string for the webservers to get the full feature list
         * of the browser and serve content accordingly, e.g.:
         * "Mozilla/5.0 (Linux; Android 6.0; Android SDK built for x86_64 Build/MASTER; wv)
         * AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/44.0.2403.119 Mobile
         * Safari/537.36 wp-android/4.7"
         * Note that app versions prior to 2.7 simply used "wp-android" as the user agent
         */
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getUserAgent() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.util.BitmapLruCache getBitmapCache() {
            return null;
        }
        
        /**
         * Update locale of the static context when language is changed.
         */
        public final void updateContextLocale() {
        }
    }
}