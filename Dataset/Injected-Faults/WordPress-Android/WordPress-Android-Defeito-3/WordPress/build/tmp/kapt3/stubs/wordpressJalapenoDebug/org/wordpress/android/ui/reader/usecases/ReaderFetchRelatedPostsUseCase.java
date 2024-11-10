package org.wordpress.android.ui.reader.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002\u0014\u0015B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007R\"\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\t\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderFetchRelatedPostsUseCase;", "", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "readerPostActionsWrapper", "Lorg/wordpress/android/ui/reader/actions/ReaderPostActionsWrapper;", "(Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/ui/reader/actions/ReaderPostActionsWrapper;)V", "continuations", "", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchRelatedPostsUseCase$RelatedPostsRequest;", "Lkotlin/coroutines/Continuation;", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchRelatedPostsUseCase$FetchRelatedPostsState;", "fetchRelatedPosts", "sourcePost", "Lorg/wordpress/android/models/ReaderPost;", "(Lorg/wordpress/android/models/ReaderPost;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onRelatedPostUpdated", "", "event", "Lorg/wordpress/android/ui/reader/ReaderEvents$RelatedPostsUpdated;", "FetchRelatedPostsState", "RelatedPostsRequest", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderFetchRelatedPostsUseCase {
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final org.wordpress.android.ui.reader.actions.ReaderPostActionsWrapper readerPostActionsWrapper = null;
    private final java.util.Map<org.wordpress.android.ui.reader.usecases.ReaderFetchRelatedPostsUseCase.RelatedPostsRequest, kotlin.coroutines.Continuation<org.wordpress.android.ui.reader.usecases.ReaderFetchRelatedPostsUseCase.FetchRelatedPostsState>> continuations = null;
    
    @javax.inject.Inject()
    public ReaderFetchRelatedPostsUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.actions.ReaderPostActionsWrapper readerPostActionsWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetchRelatedPosts(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderPost sourcePost, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.reader.usecases.ReaderFetchRelatedPostsUseCase.FetchRelatedPostsState> continuation) {
        return null;
    }
    
    @java.lang.SuppressWarnings(value = {"unused"})
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.BACKGROUND)
    public final void onRelatedPostUpdated(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.ReaderEvents.RelatedPostsUpdated event) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderFetchRelatedPostsUseCase$FetchRelatedPostsState;", "", "()V", "AlreadyRunning", "Failed", "Success", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchRelatedPostsUseCase$FetchRelatedPostsState$Success;", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchRelatedPostsUseCase$FetchRelatedPostsState$AlreadyRunning;", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchRelatedPostsUseCase$FetchRelatedPostsState$Failed;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class FetchRelatedPostsState {
        
        private FetchRelatedPostsState() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u00d6\u0003J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderFetchRelatedPostsUseCase$FetchRelatedPostsState$Success;", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchRelatedPostsUseCase$FetchRelatedPostsState;", "localRelatedPosts", "Lorg/wordpress/android/ui/reader/models/ReaderSimplePostList;", "globalRelatedPosts", "(Lorg/wordpress/android/ui/reader/models/ReaderSimplePostList;Lorg/wordpress/android/ui/reader/models/ReaderSimplePostList;)V", "getGlobalRelatedPosts", "()Lorg/wordpress/android/ui/reader/models/ReaderSimplePostList;", "getLocalRelatedPosts", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Success extends org.wordpress.android.ui.reader.usecases.ReaderFetchRelatedPostsUseCase.FetchRelatedPostsState {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.reader.models.ReaderSimplePostList localRelatedPosts = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.reader.models.ReaderSimplePostList globalRelatedPosts = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.usecases.ReaderFetchRelatedPostsUseCase.FetchRelatedPostsState.Success copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.reader.models.ReaderSimplePostList localRelatedPosts, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.reader.models.ReaderSimplePostList globalRelatedPosts) {
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
            
            public Success(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.reader.models.ReaderSimplePostList localRelatedPosts, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.reader.models.ReaderSimplePostList globalRelatedPosts) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.models.ReaderSimplePostList component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.models.ReaderSimplePostList getLocalRelatedPosts() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.models.ReaderSimplePostList component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.models.ReaderSimplePostList getGlobalRelatedPosts() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderFetchRelatedPostsUseCase$FetchRelatedPostsState$AlreadyRunning;", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchRelatedPostsUseCase$FetchRelatedPostsState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class AlreadyRunning extends org.wordpress.android.ui.reader.usecases.ReaderFetchRelatedPostsUseCase.FetchRelatedPostsState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.reader.usecases.ReaderFetchRelatedPostsUseCase.FetchRelatedPostsState.AlreadyRunning INSTANCE = null;
            
            private AlreadyRunning() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderFetchRelatedPostsUseCase$FetchRelatedPostsState$Failed;", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchRelatedPostsUseCase$FetchRelatedPostsState;", "()V", "NoNetwork", "RequestFailed", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchRelatedPostsUseCase$FetchRelatedPostsState$Failed$NoNetwork;", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchRelatedPostsUseCase$FetchRelatedPostsState$Failed$RequestFailed;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static abstract class Failed extends org.wordpress.android.ui.reader.usecases.ReaderFetchRelatedPostsUseCase.FetchRelatedPostsState {
            
            private Failed() {
                super();
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderFetchRelatedPostsUseCase$FetchRelatedPostsState$Failed$NoNetwork;", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchRelatedPostsUseCase$FetchRelatedPostsState$Failed;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class NoNetwork extends org.wordpress.android.ui.reader.usecases.ReaderFetchRelatedPostsUseCase.FetchRelatedPostsState.Failed {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.reader.usecases.ReaderFetchRelatedPostsUseCase.FetchRelatedPostsState.Failed.NoNetwork INSTANCE = null;
                
                private NoNetwork() {
                    super();
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderFetchRelatedPostsUseCase$FetchRelatedPostsState$Failed$RequestFailed;", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchRelatedPostsUseCase$FetchRelatedPostsState$Failed;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class RequestFailed extends org.wordpress.android.ui.reader.usecases.ReaderFetchRelatedPostsUseCase.FetchRelatedPostsState.Failed {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.reader.usecases.ReaderFetchRelatedPostsUseCase.FetchRelatedPostsState.Failed.RequestFailed INSTANCE = null;
                
                private RequestFailed() {
                    super();
                }
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderFetchRelatedPostsUseCase$RelatedPostsRequest;", "", "postId", "", "blogId", "(JJ)V", "getBlogId", "()J", "getPostId", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class RelatedPostsRequest {
        private final long postId = 0L;
        private final long blogId = 0L;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.usecases.ReaderFetchRelatedPostsUseCase.RelatedPostsRequest copy(long postId, long blogId) {
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
        
        public RelatedPostsRequest(long postId, long blogId) {
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
    }
}