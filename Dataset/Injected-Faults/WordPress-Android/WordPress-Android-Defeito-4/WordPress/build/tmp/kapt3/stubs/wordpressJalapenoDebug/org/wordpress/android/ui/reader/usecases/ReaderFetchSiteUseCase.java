package org.wordpress.android.ui.reader.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0002\u0017\u0018B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J-\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J\u001b\u0010\u0014\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0015\u001a\u00020\tH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016R$\u0010\u0007\u001a\u0018\u0012\u0004\u0012\u00020\t\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0018\u00010\n0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderFetchSiteUseCase;", "", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "readerBlogActionsWrapper", "Lorg/wordpress/android/ui/reader/actions/ReaderBlogActionsWrapper;", "(Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/ui/reader/actions/ReaderBlogActionsWrapper;)V", "continuations", "", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchSiteUseCase$FetchSiteRequestParams;", "Lkotlin/coroutines/Continuation;", "Lorg/wordpress/android/models/ReaderBlog;", "fetchSite", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchSiteUseCase$FetchSiteState;", "blogId", "", "feedId", "blogUrl", "", "(JJLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchSiteAndWaitForResult", "requestParams", "(Lorg/wordpress/android/ui/reader/usecases/ReaderFetchSiteUseCase$FetchSiteRequestParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "FetchSiteRequestParams", "FetchSiteState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderFetchSiteUseCase {
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final org.wordpress.android.ui.reader.actions.ReaderBlogActionsWrapper readerBlogActionsWrapper = null;
    private final java.util.Map<org.wordpress.android.ui.reader.usecases.ReaderFetchSiteUseCase.FetchSiteRequestParams, kotlin.coroutines.Continuation<org.wordpress.android.models.ReaderBlog>> continuations = null;
    
    @javax.inject.Inject()
    public ReaderFetchSiteUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.actions.ReaderBlogActionsWrapper readerBlogActionsWrapper) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetchSite(long blogId, long feedId, @org.jetbrains.annotations.Nullable()
    java.lang.String blogUrl, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.reader.usecases.ReaderFetchSiteUseCase.FetchSiteState> continuation) {
        return null;
    }
    
    private final java.lang.Object fetchSiteAndWaitForResult(org.wordpress.android.ui.reader.usecases.ReaderFetchSiteUseCase.FetchSiteRequestParams requestParams, kotlin.coroutines.Continuation<? super org.wordpress.android.models.ReaderBlog> continuation) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderFetchSiteUseCase$FetchSiteState;", "", "()V", "AlreadyRunning", "Failed", "Success", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchSiteUseCase$FetchSiteState$Success;", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchSiteUseCase$FetchSiteState$AlreadyRunning;", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchSiteUseCase$FetchSiteState$Failed;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class FetchSiteState {
        
        private FetchSiteState() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderFetchSiteUseCase$FetchSiteState$Success;", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchSiteUseCase$FetchSiteState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Success extends org.wordpress.android.ui.reader.usecases.ReaderFetchSiteUseCase.FetchSiteState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.reader.usecases.ReaderFetchSiteUseCase.FetchSiteState.Success INSTANCE = null;
            
            private Success() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderFetchSiteUseCase$FetchSiteState$AlreadyRunning;", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchSiteUseCase$FetchSiteState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class AlreadyRunning extends org.wordpress.android.ui.reader.usecases.ReaderFetchSiteUseCase.FetchSiteState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.reader.usecases.ReaderFetchSiteUseCase.FetchSiteState.AlreadyRunning INSTANCE = null;
            
            private AlreadyRunning() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderFetchSiteUseCase$FetchSiteState$Failed;", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchSiteUseCase$FetchSiteState;", "()V", "NoNetwork", "RequestFailed", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchSiteUseCase$FetchSiteState$Failed$NoNetwork;", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchSiteUseCase$FetchSiteState$Failed$RequestFailed;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static abstract class Failed extends org.wordpress.android.ui.reader.usecases.ReaderFetchSiteUseCase.FetchSiteState {
            
            private Failed() {
                super();
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderFetchSiteUseCase$FetchSiteState$Failed$NoNetwork;", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchSiteUseCase$FetchSiteState$Failed;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class NoNetwork extends org.wordpress.android.ui.reader.usecases.ReaderFetchSiteUseCase.FetchSiteState.Failed {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.reader.usecases.ReaderFetchSiteUseCase.FetchSiteState.Failed.NoNetwork INSTANCE = null;
                
                private NoNetwork() {
                    super();
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderFetchSiteUseCase$FetchSiteState$Failed$RequestFailed;", "Lorg/wordpress/android/ui/reader/usecases/ReaderFetchSiteUseCase$FetchSiteState$Failed;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class RequestFailed extends org.wordpress.android.ui.reader.usecases.ReaderFetchSiteUseCase.FetchSiteState.Failed {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.reader.usecases.ReaderFetchSiteUseCase.FetchSiteState.Failed.RequestFailed INSTANCE = null;
                
                private RequestFailed() {
                    super();
                }
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J)\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0006H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/reader/usecases/ReaderFetchSiteUseCase$FetchSiteRequestParams;", "", "blogId", "", "feedId", "blogUrl", "", "(JJLjava/lang/String;)V", "getBlogId", "()J", "getBlogUrl", "()Ljava/lang/String;", "getFeedId", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class FetchSiteRequestParams {
        private final long blogId = 0L;
        private final long feedId = 0L;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String blogUrl = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.usecases.ReaderFetchSiteUseCase.FetchSiteRequestParams copy(long blogId, long feedId, @org.jetbrains.annotations.Nullable()
        java.lang.String blogUrl) {
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
        
        public FetchSiteRequestParams(long blogId, long feedId, @org.jetbrains.annotations.Nullable()
        java.lang.String blogUrl) {
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
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getBlogUrl() {
            return null;
        }
    }
}