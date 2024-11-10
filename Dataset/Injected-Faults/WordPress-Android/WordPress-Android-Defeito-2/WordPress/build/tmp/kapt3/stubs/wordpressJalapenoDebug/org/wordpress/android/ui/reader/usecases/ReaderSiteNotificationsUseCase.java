package org.wordpress.android.ui.reader.usecases;

import java.lang.System;

/**
 * This class handles reader notification events.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u001fB\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J!\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018J\u0018\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0002J\u0016\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\rJ\u0016\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001eR\u0016\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006 "}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderSiteNotificationsUseCase;", "", "dispatcher", "Lorg/wordpress/android/fluxc/Dispatcher;", "readerTracker", "Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;", "readerBlogTableWrapper", "Lorg/wordpress/android/datasets/ReaderBlogTableWrapper;", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "(Lorg/wordpress/android/fluxc/Dispatcher;Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;Lorg/wordpress/android/datasets/ReaderBlogTableWrapper;Lorg/wordpress/android/util/NetworkUtilsWrapper;)V", "continuation", "Lkotlin/coroutines/Continuation;", "", "fetchSubscriptions", "", "onSubscriptionUpdated", "event", "Lorg/wordpress/android/fluxc/store/AccountStore$OnSubscriptionUpdated;", "toggleNotification", "Lorg/wordpress/android/ui/reader/usecases/ReaderSiteNotificationsUseCase$SiteNotificationState;", "blogId", "", "feedId", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "trackEvent", "updateNotificationEnabledForBlogInDb", "isNotificationEnabledForBlog", "updateSubscription", "action", "Lorg/wordpress/android/fluxc/store/AccountStore$AddOrDeleteSubscriptionPayload$SubscriptionAction;", "SiteNotificationState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderSiteNotificationsUseCase {
    private final org.wordpress.android.fluxc.Dispatcher dispatcher = null;
    private final org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker = null;
    private final org.wordpress.android.datasets.ReaderBlogTableWrapper readerBlogTableWrapper = null;
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation;
    
    @javax.inject.Inject()
    public ReaderSiteNotificationsUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.Dispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.datasets.ReaderBlogTableWrapper readerBlogTableWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object toggleNotification(long blogId, long feedId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.reader.usecases.ReaderSiteNotificationsUseCase.SiteNotificationState> continuation) {
        return null;
    }
    
    private final void trackEvent(long blogId, long feedId) {
    }
    
    public final void updateNotificationEnabledForBlogInDb(long blogId, boolean isNotificationEnabledForBlog) {
    }
    
    public final void fetchSubscriptions() {
    }
    
    public final void updateSubscription(long blogId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore.AddOrDeleteSubscriptionPayload.SubscriptionAction action) {
    }
    
    @java.lang.SuppressWarnings(value = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.BACKGROUND)
    public final void onSubscriptionUpdated(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore.OnSubscriptionUpdated event) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderSiteNotificationsUseCase$SiteNotificationState;", "", "()V", "Failed", "Success", "Lorg/wordpress/android/ui/reader/usecases/ReaderSiteNotificationsUseCase$SiteNotificationState$Success;", "Lorg/wordpress/android/ui/reader/usecases/ReaderSiteNotificationsUseCase$SiteNotificationState$Failed;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class SiteNotificationState {
        
        private SiteNotificationState() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderSiteNotificationsUseCase$SiteNotificationState$Success;", "Lorg/wordpress/android/ui/reader/usecases/ReaderSiteNotificationsUseCase$SiteNotificationState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Success extends org.wordpress.android.ui.reader.usecases.ReaderSiteNotificationsUseCase.SiteNotificationState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.reader.usecases.ReaderSiteNotificationsUseCase.SiteNotificationState.Success INSTANCE = null;
            
            private Success() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderSiteNotificationsUseCase$SiteNotificationState$Failed;", "Lorg/wordpress/android/ui/reader/usecases/ReaderSiteNotificationsUseCase$SiteNotificationState;", "()V", "AlreadyRunning", "NoNetwork", "RequestFailed", "Lorg/wordpress/android/ui/reader/usecases/ReaderSiteNotificationsUseCase$SiteNotificationState$Failed$NoNetwork;", "Lorg/wordpress/android/ui/reader/usecases/ReaderSiteNotificationsUseCase$SiteNotificationState$Failed$RequestFailed;", "Lorg/wordpress/android/ui/reader/usecases/ReaderSiteNotificationsUseCase$SiteNotificationState$Failed$AlreadyRunning;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static abstract class Failed extends org.wordpress.android.ui.reader.usecases.ReaderSiteNotificationsUseCase.SiteNotificationState {
            
            private Failed() {
                super();
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderSiteNotificationsUseCase$SiteNotificationState$Failed$NoNetwork;", "Lorg/wordpress/android/ui/reader/usecases/ReaderSiteNotificationsUseCase$SiteNotificationState$Failed;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class NoNetwork extends org.wordpress.android.ui.reader.usecases.ReaderSiteNotificationsUseCase.SiteNotificationState.Failed {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.reader.usecases.ReaderSiteNotificationsUseCase.SiteNotificationState.Failed.NoNetwork INSTANCE = null;
                
                private NoNetwork() {
                    super();
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderSiteNotificationsUseCase$SiteNotificationState$Failed$RequestFailed;", "Lorg/wordpress/android/ui/reader/usecases/ReaderSiteNotificationsUseCase$SiteNotificationState$Failed;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class RequestFailed extends org.wordpress.android.ui.reader.usecases.ReaderSiteNotificationsUseCase.SiteNotificationState.Failed {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.reader.usecases.ReaderSiteNotificationsUseCase.SiteNotificationState.Failed.RequestFailed INSTANCE = null;
                
                private RequestFailed() {
                    super();
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderSiteNotificationsUseCase$SiteNotificationState$Failed$AlreadyRunning;", "Lorg/wordpress/android/ui/reader/usecases/ReaderSiteNotificationsUseCase$SiteNotificationState$Failed;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class AlreadyRunning extends org.wordpress.android.ui.reader.usecases.ReaderSiteNotificationsUseCase.SiteNotificationState.Failed {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.reader.usecases.ReaderSiteNotificationsUseCase.SiteNotificationState.Failed.AlreadyRunning INSTANCE = null;
                
                private AlreadyRunning() {
                    super();
                }
            }
        }
    }
}