package org.wordpress.android.ui.reader.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 )2\u00020\u0001:\u0005&\'()*B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ/\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J7\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0019H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001aJ7\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0019H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001aJ0\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u001f\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001e*\u0010\u0012\u0004\u0012\u00020\u001f\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001e2\u0006\u0010\u0017\u001a\u00020\u0014H\u0002J0\u0010 \u001a\u0010\u0012\u0004\u0012\u00020\u001f\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001e*\u0010\u0012\u0004\u0012\u00020\u001f\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001e2\u0006\u0010\u001c\u001a\u00020\u0014H\u0002J<\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\u001f\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001e*\u0010\u0012\u0004\u0012\u00020\u001f\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001e2\u0006\u0010\"\u001a\u00020#2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u001fH\u0002J0\u0010%\u001a\u0010\u0012\u0004\u0012\u00020\u001f\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001e*\u0010\u0012\u0004\u0012\u00020\u001f\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001e2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006+"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderCommentsFollowUseCase;", "", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "postSubscribersApiCallsProvider", "Lorg/wordpress/android/ui/reader/utils/PostSubscribersApiCallsProvider;", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "readerTracker", "Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;", "readerPostTableWrapper", "Lorg/wordpress/android/datasets/wrappers/ReaderPostTableWrapper;", "(Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/ui/reader/utils/PostSubscribersApiCallsProvider;Lorg/wordpress/android/fluxc/store/AccountStore;Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;Lorg/wordpress/android/datasets/wrappers/ReaderPostTableWrapper;)V", "getMySubscriptionToPost", "Lkotlinx/coroutines/flow/Flow;", "Lorg/wordpress/android/ui/reader/usecases/ReaderCommentsFollowUseCase$FollowCommentsState;", "blogId", "", "postId", "isInit", "", "(JJZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setEnableByPushNotifications", "enable", "source", "Lorg/wordpress/android/ui/reader/comments/ThreadedCommentsActionSource;", "(JJZLorg/wordpress/android/ui/reader/comments/ThreadedCommentsActionSource;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setMySubscriptionToPost", "subscribe", "addEnablePushNotificationAction", "", "", "addFollowAction", "addFollowActionResult", "result", "Lorg/wordpress/android/ui/reader/usecases/ReaderCommentsFollowUseCase$AnalyticsFollowCommentsActionResult;", "errorMessage", "addFollowActionSource", "AnalyticsFollowCommentsAction", "AnalyticsFollowCommentsActionResult", "AnalyticsFollowCommentsGenericError", "Companion", "FollowCommentsState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderCommentsFollowUseCase {
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final org.wordpress.android.ui.reader.utils.PostSubscribersApiCallsProvider postSubscribersApiCallsProvider = null;
    private final org.wordpress.android.fluxc.store.AccountStore accountStore = null;
    private final org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker = null;
    private final org.wordpress.android.datasets.wrappers.ReaderPostTableWrapper readerPostTableWrapper = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.reader.usecases.ReaderCommentsFollowUseCase.Companion Companion = null;
    private static final java.lang.String FOLLOW_COMMENT_ACTION = "follow_action";
    private static final java.lang.String FOLLOW_COMMENT_ACTION_RESULT = "follow_action_result";
    private static final java.lang.String FOLLOW_COMMENT_ACTION_ERROR = "follow_action_error";
    private static final java.lang.String FOLLOW_COMMENT_ACTION_SOURCE = "source";
    
    @javax.inject.Inject()
    public ReaderCommentsFollowUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.utils.PostSubscribersApiCallsProvider postSubscribersApiCallsProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore accountStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.datasets.wrappers.ReaderPostTableWrapper readerPostTableWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getMySubscriptionToPost(long blogId, long postId, boolean isInit, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends org.wordpress.android.ui.reader.usecases.ReaderCommentsFollowUseCase.FollowCommentsState>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setMySubscriptionToPost(long blogId, long postId, boolean subscribe, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.comments.ThreadedCommentsActionSource source, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends org.wordpress.android.ui.reader.usecases.ReaderCommentsFollowUseCase.FollowCommentsState>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setEnableByPushNotifications(long blogId, long postId, boolean enable, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.comments.ThreadedCommentsActionSource source, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends org.wordpress.android.ui.reader.usecases.ReaderCommentsFollowUseCase.FollowCommentsState>> continuation) {
        return null;
    }
    
    private final java.util.Map<java.lang.String, java.lang.Object> addFollowAction(java.util.Map<java.lang.String, java.lang.Object> $this$addFollowAction, boolean subscribe) {
        return null;
    }
    
    private final java.util.Map<java.lang.String, java.lang.Object> addEnablePushNotificationAction(java.util.Map<java.lang.String, java.lang.Object> $this$addEnablePushNotificationAction, boolean enable) {
        return null;
    }
    
    private final java.util.Map<java.lang.String, java.lang.Object> addFollowActionResult(java.util.Map<java.lang.String, java.lang.Object> $this$addFollowActionResult, org.wordpress.android.ui.reader.usecases.ReaderCommentsFollowUseCase.AnalyticsFollowCommentsActionResult result, java.lang.String errorMessage) {
        return null;
    }
    
    private final java.util.Map<java.lang.String, java.lang.Object> addFollowActionSource(java.util.Map<java.lang.String, java.lang.Object> $this$addFollowActionSource, org.wordpress.android.ui.reader.comments.ThreadedCommentsActionSource source) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0006\u0007\b\t\n\u000b\fB\u0011\b\u0004\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0006\r\u000e\u000f\u0010\u0011\u0012\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderCommentsFollowUseCase$FollowCommentsState;", "", "forcePushNotificationsUpdate", "", "(Z)V", "getForcePushNotificationsUpdate", "()Z", "Failure", "FlagsMappedState", "FollowCommentsNotAllowed", "FollowStateChanged", "Loading", "UserNotAuthenticated", "Lorg/wordpress/android/ui/reader/usecases/ReaderCommentsFollowUseCase$FollowCommentsState$Loading;", "Lorg/wordpress/android/ui/reader/usecases/ReaderCommentsFollowUseCase$FollowCommentsState$FollowStateChanged;", "Lorg/wordpress/android/ui/reader/usecases/ReaderCommentsFollowUseCase$FollowCommentsState$Failure;", "Lorg/wordpress/android/ui/reader/usecases/ReaderCommentsFollowUseCase$FollowCommentsState$FollowCommentsNotAllowed;", "Lorg/wordpress/android/ui/reader/usecases/ReaderCommentsFollowUseCase$FollowCommentsState$UserNotAuthenticated;", "Lorg/wordpress/android/ui/reader/usecases/ReaderCommentsFollowUseCase$FollowCommentsState$FlagsMappedState;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class FollowCommentsState {
        private final boolean forcePushNotificationsUpdate = false;
        
        private FollowCommentsState(boolean forcePushNotificationsUpdate) {
            super();
        }
        
        public boolean getForcePushNotificationsUpdate() {
            return false;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderCommentsFollowUseCase$FollowCommentsState$Loading;", "Lorg/wordpress/android/ui/reader/usecases/ReaderCommentsFollowUseCase$FollowCommentsState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Loading extends org.wordpress.android.ui.reader.usecases.ReaderCommentsFollowUseCase.FollowCommentsState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.reader.usecases.ReaderCommentsFollowUseCase.FollowCommentsState.Loading INSTANCE = null;
            
            private Loading() {
                super(false);
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0006H\u00c6\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\nH\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0006H\u00c6\u0003JQ\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u001c\u001a\u00020\u00062\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u00d6\u0003J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001J\t\u0010!\u001a\u00020\"H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000b\u001a\u00020\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0013\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006#"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderCommentsFollowUseCase$FollowCommentsState$FollowStateChanged;", "Lorg/wordpress/android/ui/reader/usecases/ReaderCommentsFollowUseCase$FollowCommentsState;", "blogId", "", "postId", "isFollowing", "", "isReceivingNotifications", "isInit", "userMessage", "Lorg/wordpress/android/ui/utils/UiString;", "forcePushNotificationsUpdate", "(JJZZZLorg/wordpress/android/ui/utils/UiString;Z)V", "getBlogId", "()J", "getForcePushNotificationsUpdate", "()Z", "getPostId", "getUserMessage", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class FollowStateChanged extends org.wordpress.android.ui.reader.usecases.ReaderCommentsFollowUseCase.FollowCommentsState {
            private final long blogId = 0L;
            private final long postId = 0L;
            private final boolean isFollowing = false;
            private final boolean isReceivingNotifications = false;
            private final boolean isInit = false;
            @org.jetbrains.annotations.Nullable()
            private final org.wordpress.android.ui.utils.UiString userMessage = null;
            private final boolean forcePushNotificationsUpdate = false;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.usecases.ReaderCommentsFollowUseCase.FollowCommentsState.FollowStateChanged copy(long blogId, long postId, boolean isFollowing, boolean isReceivingNotifications, boolean isInit, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.utils.UiString userMessage, boolean forcePushNotificationsUpdate) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            public FollowStateChanged(long blogId, long postId, boolean isFollowing, boolean isReceivingNotifications, boolean isInit, @org.jetbrains.annotations.Nullable()
            org.wordpress.android.ui.utils.UiString userMessage, boolean forcePushNotificationsUpdate) {
                super(false);
            }
            
            public final long component1() {
                return 0L;
            }
            
            public final long getBlogId() {
                return 0L;
            }
            
            public final long component2() {
                return 0L;
            }
            
            public final long getPostId() {
                return 0L;
            }
            
            public final boolean component3() {
                return false;
            }
            
            public final boolean isFollowing() {
                return false;
            }
            
            public final boolean component4() {
                return false;
            }
            
            public final boolean isReceivingNotifications() {
                return false;
            }
            
            public final boolean component5() {
                return false;
            }
            
            public final boolean isInit() {
                return false;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.utils.UiString component6() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final org.wordpress.android.ui.utils.UiString getUserMessage() {
                return null;
            }
            
            public final boolean component7() {
                return false;
            }
            
            @java.lang.Override()
            public boolean getForcePushNotificationsUpdate() {
                return false;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0006H\u00c6\u0003J\'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderCommentsFollowUseCase$FollowCommentsState$Failure;", "Lorg/wordpress/android/ui/reader/usecases/ReaderCommentsFollowUseCase$FollowCommentsState;", "blogId", "", "postId", "error", "Lorg/wordpress/android/ui/utils/UiString;", "(JJLorg/wordpress/android/ui/utils/UiString;)V", "getBlogId", "()J", "getError", "()Lorg/wordpress/android/ui/utils/UiString;", "getPostId", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Failure extends org.wordpress.android.ui.reader.usecases.ReaderCommentsFollowUseCase.FollowCommentsState {
            private final long blogId = 0L;
            private final long postId = 0L;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.utils.UiString error = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.usecases.ReaderCommentsFollowUseCase.FollowCommentsState.Failure copy(long blogId, long postId, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString error) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            public Failure(long blogId, long postId, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.utils.UiString error) {
                super(false);
            }
            
            public final long component1() {
                return 0L;
            }
            
            public final long getBlogId() {
                return 0L;
            }
            
            public final long component2() {
                return 0L;
            }
            
            public final long getPostId() {
                return 0L;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString component3() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.utils.UiString getError() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderCommentsFollowUseCase$FollowCommentsState$FollowCommentsNotAllowed;", "Lorg/wordpress/android/ui/reader/usecases/ReaderCommentsFollowUseCase$FollowCommentsState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class FollowCommentsNotAllowed extends org.wordpress.android.ui.reader.usecases.ReaderCommentsFollowUseCase.FollowCommentsState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.reader.usecases.ReaderCommentsFollowUseCase.FollowCommentsState.FollowCommentsNotAllowed INSTANCE = null;
            
            private FollowCommentsNotAllowed() {
                super(false);
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderCommentsFollowUseCase$FollowCommentsState$UserNotAuthenticated;", "Lorg/wordpress/android/ui/reader/usecases/ReaderCommentsFollowUseCase$FollowCommentsState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class UserNotAuthenticated extends org.wordpress.android.ui.reader.usecases.ReaderCommentsFollowUseCase.FollowCommentsState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.reader.usecases.ReaderCommentsFollowUseCase.FollowCommentsState.UserNotAuthenticated INSTANCE = null;
            
            private UserNotAuthenticated() {
                super(false);
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderCommentsFollowUseCase$FollowCommentsState$FlagsMappedState;", "Lorg/wordpress/android/ui/reader/usecases/ReaderCommentsFollowUseCase$FollowCommentsState;", "flags", "Lorg/wordpress/android/ui/reader/FollowConversationStatusFlags;", "(Lorg/wordpress/android/ui/reader/FollowConversationStatusFlags;)V", "getFlags", "()Lorg/wordpress/android/ui/reader/FollowConversationStatusFlags;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class FlagsMappedState extends org.wordpress.android.ui.reader.usecases.ReaderCommentsFollowUseCase.FollowCommentsState {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.reader.FollowConversationStatusFlags flags = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.usecases.ReaderCommentsFollowUseCase.FollowCommentsState.FlagsMappedState copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.reader.FollowConversationStatusFlags flags) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            public FlagsMappedState(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.reader.FollowConversationStatusFlags flags) {
                super(false);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.FollowConversationStatusFlags component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.FollowConversationStatusFlags getFlags() {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderCommentsFollowUseCase$AnalyticsFollowCommentsAction;", "", "action", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getAction", "()Ljava/lang/String;", "FOLLOW_COMMENTS", "UNFOLLOW_COMMENTS", "ENABLE_PUSH_NOTIFICATION", "DISABLE_PUSH_NOTIFICATION", "org.wordpress.android_wordpressJalapenoDebug"})
    static enum AnalyticsFollowCommentsAction {
        /*public static final*/ FOLLOW_COMMENTS /* = new FOLLOW_COMMENTS(null) */,
        /*public static final*/ UNFOLLOW_COMMENTS /* = new UNFOLLOW_COMMENTS(null) */,
        /*public static final*/ ENABLE_PUSH_NOTIFICATION /* = new ENABLE_PUSH_NOTIFICATION(null) */,
        /*public static final*/ DISABLE_PUSH_NOTIFICATION /* = new DISABLE_PUSH_NOTIFICATION(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String action = null;
        
        AnalyticsFollowCommentsAction(java.lang.String action) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getAction() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderCommentsFollowUseCase$AnalyticsFollowCommentsActionResult;", "", "actionResult", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getActionResult", "()Ljava/lang/String;", "SUCCEEDED", "ERROR", "org.wordpress.android_wordpressJalapenoDebug"})
    static enum AnalyticsFollowCommentsActionResult {
        /*public static final*/ SUCCEEDED /* = new SUCCEEDED(null) */,
        /*public static final*/ ERROR /* = new ERROR(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String actionResult = null;
        
        AnalyticsFollowCommentsActionResult(java.lang.String actionResult) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getActionResult() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderCommentsFollowUseCase$AnalyticsFollowCommentsGenericError;", "", "errorMessage", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getErrorMessage", "()Ljava/lang/String;", "NO_NETWORK", "org.wordpress.android_wordpressJalapenoDebug"})
    static enum AnalyticsFollowCommentsGenericError {
        /*public static final*/ NO_NETWORK /* = new NO_NETWORK(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String errorMessage = null;
        
        AnalyticsFollowCommentsGenericError(java.lang.String errorMessage) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getErrorMessage() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderCommentsFollowUseCase$Companion;", "", "()V", "FOLLOW_COMMENT_ACTION", "", "FOLLOW_COMMENT_ACTION_ERROR", "FOLLOW_COMMENT_ACTION_RESULT", "FOLLOW_COMMENT_ACTION_SOURCE", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}