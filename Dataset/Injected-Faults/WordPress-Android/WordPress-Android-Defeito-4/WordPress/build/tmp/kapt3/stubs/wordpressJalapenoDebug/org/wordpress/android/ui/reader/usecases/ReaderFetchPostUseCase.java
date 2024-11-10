package org.wordpress.android.ui.reader.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0002\u0017\u0018B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J)\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J\u0019\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\tH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016R\"\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\t\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderFetchPostUseCase;", "", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "readerPostActionsWrapper", "Lorg/wordpress/android/ui/reader/actions/ReaderPostActionsWrapper;", "(Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/ui/reader/actions/ReaderPostActionsWrapper;)V", "continuations", "", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchPostUseCase$FetchPostRequestParams;", "Lkotlin/coroutines/Continuation;", "", "fetchPost", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchPostUseCase$FetchReaderPostState;", "blogId", "", "postId", "isFeed", "", "(JJZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchPostAndWaitForResult", "requestParams", "(Lorg/wordpress/android/ui/reader/usecases/ReaderFetchPostUseCase$FetchPostRequestParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "FetchPostRequestParams", "FetchReaderPostState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderFetchPostUseCase {
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final org.wordpress.android.ui.reader.actions.ReaderPostActionsWrapper readerPostActionsWrapper = null;
    private final java.util.Map<org.wordpress.android.ui.reader.usecases.ReaderFetchPostUseCase.FetchPostRequestParams, kotlin.coroutines.Continuation<java.lang.Integer>> continuations = null;
    
    @javax.inject.Inject()
    public ReaderFetchPostUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.actions.ReaderPostActionsWrapper readerPostActionsWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetchPost(long blogId, long postId, boolean isFeed, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.reader.usecases.ReaderFetchPostUseCase.FetchReaderPostState> continuation) {
        return null;
    }
    
    private final java.lang.Object fetchPostAndWaitForResult(org.wordpress.android.ui.reader.usecases.ReaderFetchPostUseCase.FetchPostRequestParams requestParams, kotlin.coroutines.Continuation<? super java.lang.Integer> continuation) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderFetchPostUseCase$FetchReaderPostState;", "", "()V", "AlreadyRunning", "Failed", "Success", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchPostUseCase$FetchReaderPostState$Success;", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchPostUseCase$FetchReaderPostState$AlreadyRunning;", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchPostUseCase$FetchReaderPostState$Failed;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class FetchReaderPostState {
        
        private FetchReaderPostState() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderFetchPostUseCase$FetchReaderPostState$Success;", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchPostUseCase$FetchReaderPostState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Success extends org.wordpress.android.ui.reader.usecases.ReaderFetchPostUseCase.FetchReaderPostState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.reader.usecases.ReaderFetchPostUseCase.FetchReaderPostState.Success INSTANCE = null;
            
            private Success() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderFetchPostUseCase$FetchReaderPostState$AlreadyRunning;", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchPostUseCase$FetchReaderPostState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class AlreadyRunning extends org.wordpress.android.ui.reader.usecases.ReaderFetchPostUseCase.FetchReaderPostState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.reader.usecases.ReaderFetchPostUseCase.FetchReaderPostState.AlreadyRunning INSTANCE = null;
            
            private AlreadyRunning() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderFetchPostUseCase$FetchReaderPostState$Failed;", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchPostUseCase$FetchReaderPostState;", "()V", "NoNetwork", "NotAuthorised", "PostNotFound", "RequestFailed", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchPostUseCase$FetchReaderPostState$Failed$NoNetwork;", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchPostUseCase$FetchReaderPostState$Failed$NotAuthorised;", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchPostUseCase$FetchReaderPostState$Failed$PostNotFound;", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchPostUseCase$FetchReaderPostState$Failed$RequestFailed;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static abstract class Failed extends org.wordpress.android.ui.reader.usecases.ReaderFetchPostUseCase.FetchReaderPostState {
            
            private Failed() {
                super();
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderFetchPostUseCase$FetchReaderPostState$Failed$NoNetwork;", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchPostUseCase$FetchReaderPostState$Failed;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class NoNetwork extends org.wordpress.android.ui.reader.usecases.ReaderFetchPostUseCase.FetchReaderPostState.Failed {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.reader.usecases.ReaderFetchPostUseCase.FetchReaderPostState.Failed.NoNetwork INSTANCE = null;
                
                private NoNetwork() {
                    super();
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderFetchPostUseCase$FetchReaderPostState$Failed$NotAuthorised;", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchPostUseCase$FetchReaderPostState$Failed;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class NotAuthorised extends org.wordpress.android.ui.reader.usecases.ReaderFetchPostUseCase.FetchReaderPostState.Failed {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.reader.usecases.ReaderFetchPostUseCase.FetchReaderPostState.Failed.NotAuthorised INSTANCE = null;
                
                private NotAuthorised() {
                    super();
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderFetchPostUseCase$FetchReaderPostState$Failed$PostNotFound;", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchPostUseCase$FetchReaderPostState$Failed;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class PostNotFound extends org.wordpress.android.ui.reader.usecases.ReaderFetchPostUseCase.FetchReaderPostState.Failed {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.reader.usecases.ReaderFetchPostUseCase.FetchReaderPostState.Failed.PostNotFound INSTANCE = null;
                
                private PostNotFound() {
                    super();
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderFetchPostUseCase$FetchReaderPostState$Failed$RequestFailed;", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchPostUseCase$FetchReaderPostState$Failed;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class RequestFailed extends org.wordpress.android.ui.reader.usecases.ReaderFetchPostUseCase.FetchReaderPostState.Failed {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.reader.usecases.ReaderFetchPostUseCase.FetchReaderPostState.Failed.RequestFailed INSTANCE = null;
                
                private RequestFailed() {
                    super();
                }
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0006H\u00c6\u0003J\'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0010\u001a\u00020\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t\u00a8\u0006\u0016"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderFetchPostUseCase$FetchPostRequestParams;", "", "blogId", "", "postId", "isFeed", "", "(JJZ)V", "getBlogId", "()J", "()Z", "getPostId", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class FetchPostRequestParams {
        private final long blogId = 0L;
        private final long postId = 0L;
        private final boolean isFeed = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.usecases.ReaderFetchPostUseCase.FetchPostRequestParams copy(long blogId, long postId, boolean isFeed) {
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
        
        public FetchPostRequestParams(long blogId, long postId, boolean isFeed) {
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
        
        public final long getPostId() {
            return 0L;
        }
        
        public final boolean component3() {
            return false;
        }
        
        public final boolean isFeed() {
            return false;
        }
    }
}