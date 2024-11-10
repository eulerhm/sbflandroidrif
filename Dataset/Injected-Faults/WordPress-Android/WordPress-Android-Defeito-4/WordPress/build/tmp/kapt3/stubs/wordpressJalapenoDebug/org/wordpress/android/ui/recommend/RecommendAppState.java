package org.wordpress.android.ui.recommend;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/recommend/RecommendAppState;", "", "()V", "ApiFetchedResult", "FetchingApi", "Lorg/wordpress/android/ui/recommend/RecommendAppState$FetchingApi;", "Lorg/wordpress/android/ui/recommend/RecommendAppState$ApiFetchedResult;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class RecommendAppState {
    
    private RecommendAppState() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/recommend/RecommendAppState$FetchingApi;", "Lorg/wordpress/android/ui/recommend/RecommendAppState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class FetchingApi extends org.wordpress.android.ui.recommend.RecommendAppState {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.recommend.RecommendAppState.FetchingApi INSTANCE = null;
        
        private FetchingApi() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004B!\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J)\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001J\u0006\u0010\u0016\u001a\u00020\u0011J\t\u0010\u0017\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t\u00a8\u0006\u0018"}, d2 = {"Lorg/wordpress/android/ui/recommend/RecommendAppState$ApiFetchedResult;", "Lorg/wordpress/android/ui/recommend/RecommendAppState;", "error", "", "(Ljava/lang/String;)V", "message", "link", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getError", "()Ljava/lang/String;", "getLink", "getMessage", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "isError", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class ApiFetchedResult extends org.wordpress.android.ui.recommend.RecommendAppState {
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String error = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String message = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String link = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.recommend.RecommendAppState.ApiFetchedResult copy(@org.jetbrains.annotations.Nullable()
        java.lang.String error, @org.jetbrains.annotations.NotNull()
        java.lang.String message, @org.jetbrains.annotations.NotNull()
        java.lang.String link) {
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
        
        public ApiFetchedResult(@org.jetbrains.annotations.Nullable()
        java.lang.String error, @org.jetbrains.annotations.NotNull()
        java.lang.String message, @org.jetbrains.annotations.NotNull()
        java.lang.String link) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getError() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getMessage() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getLink() {
            return null;
        }
        
        public ApiFetchedResult(@org.jetbrains.annotations.NotNull()
        java.lang.String error) {
            super();
        }
        
        public final boolean isError() {
            return false;
        }
    }
}