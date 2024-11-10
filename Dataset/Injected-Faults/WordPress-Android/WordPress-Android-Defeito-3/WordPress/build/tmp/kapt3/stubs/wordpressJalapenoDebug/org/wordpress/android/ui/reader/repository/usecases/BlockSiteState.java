package org.wordpress.android.ui.reader.repository.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/usecases/BlockSiteState;", "", "()V", "Failed", "SiteBlockedInLocalDb", "Success", "Lorg/wordpress/android/ui/reader/repository/usecases/BlockSiteState$SiteBlockedInLocalDb;", "Lorg/wordpress/android/ui/reader/repository/usecases/BlockSiteState$Success;", "Lorg/wordpress/android/ui/reader/repository/usecases/BlockSiteState$Failed;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class BlockSiteState {
    
    private BlockSiteState() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/usecases/BlockSiteState$SiteBlockedInLocalDb;", "Lorg/wordpress/android/ui/reader/repository/usecases/BlockSiteState;", "blockedBlogData", "Lorg/wordpress/android/ui/reader/actions/ReaderBlogActions$BlockedBlogResult;", "(Lorg/wordpress/android/ui/reader/actions/ReaderBlogActions$BlockedBlogResult;)V", "getBlockedBlogData", "()Lorg/wordpress/android/ui/reader/actions/ReaderBlogActions$BlockedBlogResult;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SiteBlockedInLocalDb extends org.wordpress.android.ui.reader.repository.usecases.BlockSiteState {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.reader.actions.ReaderBlogActions.BlockedBlogResult blockedBlogData = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.repository.usecases.BlockSiteState.SiteBlockedInLocalDb copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.reader.actions.ReaderBlogActions.BlockedBlogResult blockedBlogData) {
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
        
        public SiteBlockedInLocalDb(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.reader.actions.ReaderBlogActions.BlockedBlogResult blockedBlogData) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.actions.ReaderBlogActions.BlockedBlogResult component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.actions.ReaderBlogActions.BlockedBlogResult getBlockedBlogData() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/usecases/BlockSiteState$Success;", "Lorg/wordpress/android/ui/reader/repository/usecases/BlockSiteState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Success extends org.wordpress.android.ui.reader.repository.usecases.BlockSiteState {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.reader.repository.usecases.BlockSiteState.Success INSTANCE = null;
        
        private Success() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/usecases/BlockSiteState$Failed;", "Lorg/wordpress/android/ui/reader/repository/usecases/BlockSiteState;", "()V", "AlreadyRunning", "NoNetwork", "RequestFailed", "Lorg/wordpress/android/ui/reader/repository/usecases/BlockSiteState$Failed$NoNetwork;", "Lorg/wordpress/android/ui/reader/repository/usecases/BlockSiteState$Failed$RequestFailed;", "Lorg/wordpress/android/ui/reader/repository/usecases/BlockSiteState$Failed$AlreadyRunning;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class Failed extends org.wordpress.android.ui.reader.repository.usecases.BlockSiteState {
        
        private Failed() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/usecases/BlockSiteState$Failed$NoNetwork;", "Lorg/wordpress/android/ui/reader/repository/usecases/BlockSiteState$Failed;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class NoNetwork extends org.wordpress.android.ui.reader.repository.usecases.BlockSiteState.Failed {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.reader.repository.usecases.BlockSiteState.Failed.NoNetwork INSTANCE = null;
            
            private NoNetwork() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/usecases/BlockSiteState$Failed$RequestFailed;", "Lorg/wordpress/android/ui/reader/repository/usecases/BlockSiteState$Failed;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class RequestFailed extends org.wordpress.android.ui.reader.repository.usecases.BlockSiteState.Failed {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.reader.repository.usecases.BlockSiteState.Failed.RequestFailed INSTANCE = null;
            
            private RequestFailed() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/usecases/BlockSiteState$Failed$AlreadyRunning;", "Lorg/wordpress/android/ui/reader/repository/usecases/BlockSiteState$Failed;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class AlreadyRunning extends org.wordpress.android.ui.reader.repository.usecases.BlockSiteState.Failed {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.reader.repository.usecases.BlockSiteState.Failed.AlreadyRunning INSTANCE = null;
            
            private AlreadyRunning() {
                super();
            }
        }
    }
}