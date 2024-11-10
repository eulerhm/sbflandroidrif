package org.wordpress.android.ui.reader.repository.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002!\"B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ/\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018J!\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\rH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001bJ \u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001a\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J+\u0010\u001e\u001a\u00020\u001d*\b\u0012\u0004\u0012\u00020\u000f0\u001f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\rH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\"\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\r\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006#"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/usecases/PostLikeUseCase;", "", "readerPostActionsWrapper", "Lorg/wordpress/android/ui/reader/actions/ReaderPostActionsWrapper;", "readerTracker", "Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "(Lorg/wordpress/android/ui/reader/actions/ReaderPostActionsWrapper;Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;Lorg/wordpress/android/fluxc/store/AccountStore;Lorg/wordpress/android/util/NetworkUtilsWrapper;)V", "continuations", "", "Lorg/wordpress/android/ui/reader/repository/usecases/PostLikeUseCase$PostLikeRequest;", "Lkotlin/coroutines/Continuation;", "Lorg/wordpress/android/ui/reader/repository/usecases/PostLikeUseCase$PostLikeState;", "perform", "Lkotlinx/coroutines/flow/Flow;", "post", "Lorg/wordpress/android/models/ReaderPost;", "isAskingToLike", "", "source", "", "(Lorg/wordpress/android/models/ReaderPost;ZLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "performLikeAndWaitForResult", "request", "(Lorg/wordpress/android/models/ReaderPost;Lorg/wordpress/android/ui/reader/repository/usecases/PostLikeUseCase$PostLikeRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "trackEvent", "", "handleLocalDb", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lorg/wordpress/android/models/ReaderPost;Lorg/wordpress/android/ui/reader/repository/usecases/PostLikeUseCase$PostLikeRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "PostLikeRequest", "PostLikeState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class PostLikeUseCase {
    private final org.wordpress.android.ui.reader.actions.ReaderPostActionsWrapper readerPostActionsWrapper = null;
    private final org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker = null;
    private final org.wordpress.android.fluxc.store.AccountStore accountStore = null;
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final java.util.Map<org.wordpress.android.ui.reader.repository.usecases.PostLikeUseCase.PostLikeRequest, kotlin.coroutines.Continuation<org.wordpress.android.ui.reader.repository.usecases.PostLikeUseCase.PostLikeState>> continuations = null;
    
    @javax.inject.Inject()
    public PostLikeUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.actions.ReaderPostActionsWrapper readerPostActionsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore accountStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object perform(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderPost post, boolean isAskingToLike, @org.jetbrains.annotations.NotNull()
    java.lang.String source, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends org.wordpress.android.ui.reader.repository.usecases.PostLikeUseCase.PostLikeState>> continuation) {
        return null;
    }
    
    private final void trackEvent(org.wordpress.android.ui.reader.repository.usecases.PostLikeUseCase.PostLikeRequest request, org.wordpress.android.models.ReaderPost post, java.lang.String source) {
    }
    
    private final java.lang.Object handleLocalDb(kotlinx.coroutines.flow.FlowCollector<? super org.wordpress.android.ui.reader.repository.usecases.PostLikeUseCase.PostLikeState> $this$handleLocalDb, org.wordpress.android.models.ReaderPost post, org.wordpress.android.ui.reader.repository.usecases.PostLikeUseCase.PostLikeRequest request, kotlin.coroutines.Continuation<? super kotlin.Unit> p3) {
        return null;
    }
    
    private final java.lang.Object performLikeAndWaitForResult(org.wordpress.android.models.ReaderPost post, org.wordpress.android.ui.reader.repository.usecases.PostLikeUseCase.PostLikeRequest request, kotlin.coroutines.Continuation<? super org.wordpress.android.ui.reader.repository.usecases.PostLikeUseCase.PostLikeState> continuation) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0003\u0004\u0005\u0006\u0007B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0005\b\t\n\u000b\f\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/usecases/PostLikeUseCase$PostLikeState;", "", "()V", "AlreadyRunning", "Failed", "PostLikedInLocalDb", "Success", "Unchanged", "Lorg/wordpress/android/ui/reader/repository/usecases/PostLikeUseCase$PostLikeState$Success;", "Lorg/wordpress/android/ui/reader/repository/usecases/PostLikeUseCase$PostLikeState$PostLikedInLocalDb;", "Lorg/wordpress/android/ui/reader/repository/usecases/PostLikeUseCase$PostLikeState$AlreadyRunning;", "Lorg/wordpress/android/ui/reader/repository/usecases/PostLikeUseCase$PostLikeState$Unchanged;", "Lorg/wordpress/android/ui/reader/repository/usecases/PostLikeUseCase$PostLikeState$Failed;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class PostLikeState {
        
        private PostLikeState() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/usecases/PostLikeUseCase$PostLikeState$Success;", "Lorg/wordpress/android/ui/reader/repository/usecases/PostLikeUseCase$PostLikeState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Success extends org.wordpress.android.ui.reader.repository.usecases.PostLikeUseCase.PostLikeState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.reader.repository.usecases.PostLikeUseCase.PostLikeState.Success INSTANCE = null;
            
            private Success() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/usecases/PostLikeUseCase$PostLikeState$PostLikedInLocalDb;", "Lorg/wordpress/android/ui/reader/repository/usecases/PostLikeUseCase$PostLikeState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class PostLikedInLocalDb extends org.wordpress.android.ui.reader.repository.usecases.PostLikeUseCase.PostLikeState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.reader.repository.usecases.PostLikeUseCase.PostLikeState.PostLikedInLocalDb INSTANCE = null;
            
            private PostLikedInLocalDb() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/usecases/PostLikeUseCase$PostLikeState$AlreadyRunning;", "Lorg/wordpress/android/ui/reader/repository/usecases/PostLikeUseCase$PostLikeState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class AlreadyRunning extends org.wordpress.android.ui.reader.repository.usecases.PostLikeUseCase.PostLikeState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.reader.repository.usecases.PostLikeUseCase.PostLikeState.AlreadyRunning INSTANCE = null;
            
            private AlreadyRunning() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/usecases/PostLikeUseCase$PostLikeState$Unchanged;", "Lorg/wordpress/android/ui/reader/repository/usecases/PostLikeUseCase$PostLikeState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Unchanged extends org.wordpress.android.ui.reader.repository.usecases.PostLikeUseCase.PostLikeState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.reader.repository.usecases.PostLikeUseCase.PostLikeState.Unchanged INSTANCE = null;
            
            private Unchanged() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/usecases/PostLikeUseCase$PostLikeState$Failed;", "Lorg/wordpress/android/ui/reader/repository/usecases/PostLikeUseCase$PostLikeState;", "()V", "NoNetwork", "RequestFailed", "Lorg/wordpress/android/ui/reader/repository/usecases/PostLikeUseCase$PostLikeState$Failed$NoNetwork;", "Lorg/wordpress/android/ui/reader/repository/usecases/PostLikeUseCase$PostLikeState$Failed$RequestFailed;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static abstract class Failed extends org.wordpress.android.ui.reader.repository.usecases.PostLikeUseCase.PostLikeState {
            
            private Failed() {
                super();
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/usecases/PostLikeUseCase$PostLikeState$Failed$NoNetwork;", "Lorg/wordpress/android/ui/reader/repository/usecases/PostLikeUseCase$PostLikeState$Failed;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class NoNetwork extends org.wordpress.android.ui.reader.repository.usecases.PostLikeUseCase.PostLikeState.Failed {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.reader.repository.usecases.PostLikeUseCase.PostLikeState.Failed.NoNetwork INSTANCE = null;
                
                private NoNetwork() {
                    super();
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/usecases/PostLikeUseCase$PostLikeState$Failed$RequestFailed;", "Lorg/wordpress/android/ui/reader/repository/usecases/PostLikeUseCase$PostLikeState$Failed;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class RequestFailed extends org.wordpress.android.ui.reader.repository.usecases.PostLikeUseCase.PostLikeState.Failed {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.reader.repository.usecases.PostLikeUseCase.PostLikeState.Failed.RequestFailed INSTANCE = null;
                
                private RequestFailed() {
                    super();
                }
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J1\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/usecases/PostLikeUseCase$PostLikeRequest;", "", "postId", "", "blogId", "isAskingToLike", "", "wpComUserId", "(JJZJ)V", "getBlogId", "()J", "()Z", "getPostId", "getWpComUserId", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class PostLikeRequest {
        private final long postId = 0L;
        private final long blogId = 0L;
        private final boolean isAskingToLike = false;
        private final long wpComUserId = 0L;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.repository.usecases.PostLikeUseCase.PostLikeRequest copy(long postId, long blogId, boolean isAskingToLike, long wpComUserId) {
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
        
        public PostLikeRequest(long postId, long blogId, boolean isAskingToLike, long wpComUserId) {
            super();
        }
        
        public final long component1() {
            return 0L;
        }
        
        public final long getPostId() {
            return 0L;
        }
        
        public final long component2() {
            return 0L;
        }
        
        public final long getBlogId() {
            return 0L;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean isAskingToLike() {
            return false;
        }
        
        public final long component4() {
            return 0L;
        }
        
        public final long getWpComUserId() {
            return 0L;
        }
    }
}