package org.wordpress.android.ui.reader.usecases;

import java.lang.System;

/**
 * This class handles reader blog follow click events.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002 !B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ)\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J\'\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0016H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001bJ3\u0010\u001c\u001a\u00020\u001d*\b\u0012\u0004\u0012\u00020\u001a0\u001e2\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001fR\"\u0010\r\u001a\u0016\u0012\u0004\u0012\u00020\u000f\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00100\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\""}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderSiteFollowUseCase;", "", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "readerBlogActionsWrapper", "Lorg/wordpress/android/ui/reader/actions/ReaderBlogActionsWrapper;", "readerBlogTableWrapper", "Lorg/wordpress/android/datasets/ReaderBlogTableWrapper;", "readerUtilsWrapper", "Lorg/wordpress/android/ui/reader/utils/ReaderUtilsWrapper;", "readerTracker", "Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;", "(Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/ui/reader/actions/ReaderBlogActionsWrapper;Lorg/wordpress/android/datasets/ReaderBlogTableWrapper;Lorg/wordpress/android/ui/reader/utils/ReaderUtilsWrapper;Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;)V", "continuations", "", "Lorg/wordpress/android/ui/reader/usecases/ReaderSiteFollowUseCase$Param;", "Lkotlin/coroutines/Continuation;", "", "followSiteAndWaitForResult", "param", "isAskingToFollow", "source", "", "(Lorg/wordpress/android/ui/reader/usecases/ReaderSiteFollowUseCase$Param;ZLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toggleFollow", "Lkotlinx/coroutines/flow/Flow;", "Lorg/wordpress/android/ui/reader/usecases/ReaderSiteFollowUseCase$FollowSiteState;", "(Lorg/wordpress/android/ui/reader/usecases/ReaderSiteFollowUseCase$Param;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "performAction", "", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lorg/wordpress/android/ui/reader/usecases/ReaderSiteFollowUseCase$Param;ZLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "FollowSiteState", "Param", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderSiteFollowUseCase {
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final org.wordpress.android.ui.reader.actions.ReaderBlogActionsWrapper readerBlogActionsWrapper = null;
    private final org.wordpress.android.datasets.ReaderBlogTableWrapper readerBlogTableWrapper = null;
    private final org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper readerUtilsWrapper = null;
    private final org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker = null;
    private final java.util.Map<org.wordpress.android.ui.reader.usecases.ReaderSiteFollowUseCase.Param, kotlin.coroutines.Continuation<java.lang.Boolean>> continuations = null;
    
    @javax.inject.Inject()
    public ReaderSiteFollowUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.actions.ReaderBlogActionsWrapper readerBlogActionsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.datasets.ReaderBlogTableWrapper readerBlogTableWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper readerUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object toggleFollow(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.usecases.ReaderSiteFollowUseCase.Param param, @org.jetbrains.annotations.NotNull()
    java.lang.String source, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends org.wordpress.android.ui.reader.usecases.ReaderSiteFollowUseCase.FollowSiteState>> continuation) {
        return null;
    }
    
    private final java.lang.Object performAction(kotlinx.coroutines.flow.FlowCollector<? super org.wordpress.android.ui.reader.usecases.ReaderSiteFollowUseCase.FollowSiteState> $this$performAction, org.wordpress.android.ui.reader.usecases.ReaderSiteFollowUseCase.Param param, boolean isAskingToFollow, java.lang.String source, kotlin.coroutines.Continuation<? super kotlin.Unit> p4) {
        return null;
    }
    
    private final java.lang.Object followSiteAndWaitForResult(org.wordpress.android.ui.reader.usecases.ReaderSiteFollowUseCase.Param param, boolean isAskingToFollow, java.lang.String source, kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderSiteFollowUseCase$FollowSiteState;", "", "()V", "AlreadyRunning", "Failed", "FollowStatusChanged", "Success", "Lorg/wordpress/android/ui/reader/usecases/ReaderSiteFollowUseCase$FollowSiteState$FollowStatusChanged;", "Lorg/wordpress/android/ui/reader/usecases/ReaderSiteFollowUseCase$FollowSiteState$Success;", "Lorg/wordpress/android/ui/reader/usecases/ReaderSiteFollowUseCase$FollowSiteState$AlreadyRunning;", "Lorg/wordpress/android/ui/reader/usecases/ReaderSiteFollowUseCase$FollowSiteState$Failed;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class FollowSiteState {
        
        private FollowSiteState() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0006H\u00c6\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0017\u001a\u00020\u00062\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\u001bH\u00d6\u0001J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r\u00a8\u0006\u001e"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderSiteFollowUseCase$FollowSiteState$FollowStatusChanged;", "Lorg/wordpress/android/ui/reader/usecases/ReaderSiteFollowUseCase$FollowSiteState;", "blogId", "", "feedId", "following", "", "showEnableNotification", "deleteNotificationSubscription", "(JJZZZ)V", "getBlogId", "()J", "getDeleteNotificationSubscription", "()Z", "getFeedId", "getFollowing", "getShowEnableNotification", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class FollowStatusChanged extends org.wordpress.android.ui.reader.usecases.ReaderSiteFollowUseCase.FollowSiteState {
            private final long blogId = 0L;
            private final long feedId = 0L;
            private final boolean following = false;
            private final boolean showEnableNotification = false;
            private final boolean deleteNotificationSubscription = false;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.usecases.ReaderSiteFollowUseCase.FollowSiteState.FollowStatusChanged copy(long blogId, long feedId, boolean following, boolean showEnableNotification, boolean deleteNotificationSubscription) {
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
            
            public FollowStatusChanged(long blogId, long feedId, boolean following, boolean showEnableNotification, boolean deleteNotificationSubscription) {
                super();
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
            
            public final long getFeedId() {
                return 0L;
            }
            
            public final boolean component3() {
                return false;
            }
            
            public final boolean getFollowing() {
                return false;
            }
            
            public final boolean component4() {
                return false;
            }
            
            public final boolean getShowEnableNotification() {
                return false;
            }
            
            public final boolean component5() {
                return false;
            }
            
            public final boolean getDeleteNotificationSubscription() {
                return false;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderSiteFollowUseCase$FollowSiteState$Success;", "Lorg/wordpress/android/ui/reader/usecases/ReaderSiteFollowUseCase$FollowSiteState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Success extends org.wordpress.android.ui.reader.usecases.ReaderSiteFollowUseCase.FollowSiteState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.reader.usecases.ReaderSiteFollowUseCase.FollowSiteState.Success INSTANCE = null;
            
            private Success() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderSiteFollowUseCase$FollowSiteState$AlreadyRunning;", "Lorg/wordpress/android/ui/reader/usecases/ReaderSiteFollowUseCase$FollowSiteState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class AlreadyRunning extends org.wordpress.android.ui.reader.usecases.ReaderSiteFollowUseCase.FollowSiteState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.reader.usecases.ReaderSiteFollowUseCase.FollowSiteState.AlreadyRunning INSTANCE = null;
            
            private AlreadyRunning() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderSiteFollowUseCase$FollowSiteState$Failed;", "Lorg/wordpress/android/ui/reader/usecases/ReaderSiteFollowUseCase$FollowSiteState;", "()V", "NoNetwork", "RequestFailed", "Lorg/wordpress/android/ui/reader/usecases/ReaderSiteFollowUseCase$FollowSiteState$Failed$NoNetwork;", "Lorg/wordpress/android/ui/reader/usecases/ReaderSiteFollowUseCase$FollowSiteState$Failed$RequestFailed;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static abstract class Failed extends org.wordpress.android.ui.reader.usecases.ReaderSiteFollowUseCase.FollowSiteState {
            
            private Failed() {
                super();
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderSiteFollowUseCase$FollowSiteState$Failed$NoNetwork;", "Lorg/wordpress/android/ui/reader/usecases/ReaderSiteFollowUseCase$FollowSiteState$Failed;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class NoNetwork extends org.wordpress.android.ui.reader.usecases.ReaderSiteFollowUseCase.FollowSiteState.Failed {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.reader.usecases.ReaderSiteFollowUseCase.FollowSiteState.Failed.NoNetwork INSTANCE = null;
                
                private NoNetwork() {
                    super();
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderSiteFollowUseCase$FollowSiteState$Failed$RequestFailed;", "Lorg/wordpress/android/ui/reader/usecases/ReaderSiteFollowUseCase$FollowSiteState$Failed;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class RequestFailed extends org.wordpress.android.ui.reader.usecases.ReaderSiteFollowUseCase.FollowSiteState.Failed {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.reader.usecases.ReaderSiteFollowUseCase.FollowSiteState.Failed.RequestFailed INSTANCE = null;
                
                private RequestFailed() {
                    super();
                }
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0006H\u00c6\u0003J\'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0006H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderSiteFollowUseCase$Param;", "", "blogId", "", "feedId", "blogName", "", "(JJLjava/lang/String;)V", "getBlogId", "()J", "getBlogName", "()Ljava/lang/String;", "getFeedId", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Param {
        private final long blogId = 0L;
        private final long feedId = 0L;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String blogName = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.usecases.ReaderSiteFollowUseCase.Param copy(long blogId, long feedId, @org.jetbrains.annotations.NotNull()
        java.lang.String blogName) {
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
        
        public Param(long blogId, long feedId, @org.jetbrains.annotations.NotNull()
        java.lang.String blogName) {
            super();
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
        
        public final long getFeedId() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getBlogName() {
            return null;
        }
    }
}