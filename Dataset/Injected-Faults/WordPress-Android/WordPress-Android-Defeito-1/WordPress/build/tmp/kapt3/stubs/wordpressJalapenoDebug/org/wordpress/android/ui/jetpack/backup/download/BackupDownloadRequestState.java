package org.wordpress.android.ui.jetpack.backup.download;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0003\u0004\u0005\u0006\u0007B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0005\b\t\n\u000b\f\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState;", "", "()V", "Complete", "Empty", "Failure", "Progress", "Success", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState$Success;", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState$Progress;", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState$Complete;", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState$Empty;", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState$Failure;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class BackupDownloadRequestState {
    
    private BackupDownloadRequestState() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\tJ.\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0012J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u0003H\u00d6\u0001R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState$Success;", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState;", "requestRewindId", "", "rewindId", "downloadId", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)V", "getDownloadId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getRequestRewindId", "()Ljava/lang/String;", "getRewindId", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState$Success;", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Success extends org.wordpress.android.ui.jetpack.backup.download.BackupDownloadRequestState {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String requestRewindId = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String rewindId = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Long downloadId = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.jetpack.backup.download.BackupDownloadRequestState.Success copy(@org.jetbrains.annotations.NotNull()
        java.lang.String requestRewindId, @org.jetbrains.annotations.NotNull()
        java.lang.String rewindId, @org.jetbrains.annotations.Nullable()
        java.lang.Long downloadId) {
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
        java.lang.String requestRewindId, @org.jetbrains.annotations.NotNull()
        java.lang.String rewindId, @org.jetbrains.annotations.Nullable()
        java.lang.Long downloadId) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getRequestRewindId() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getRewindId() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Long component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Long getDownloadId() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\nJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J0\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\u001a\u001a\u00020\u0003H\u00d6\u0001R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState$Progress;", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState;", "rewindId", "", "progress", "", "published", "Ljava/util/Date;", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/util/Date;)V", "getProgress", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getPublished", "()Ljava/util/Date;", "getRewindId", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/util/Date;)Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState$Progress;", "equals", "", "other", "", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Progress extends org.wordpress.android.ui.jetpack.backup.download.BackupDownloadRequestState {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String rewindId = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer progress = null;
        @org.jetbrains.annotations.Nullable()
        private final java.util.Date published = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.jetpack.backup.download.BackupDownloadRequestState.Progress copy(@org.jetbrains.annotations.NotNull()
        java.lang.String rewindId, @org.jetbrains.annotations.Nullable()
        java.lang.Integer progress, @org.jetbrains.annotations.Nullable()
        java.util.Date published) {
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
        
        public Progress(@org.jetbrains.annotations.NotNull()
        java.lang.String rewindId, @org.jetbrains.annotations.Nullable()
        java.lang.Integer progress, @org.jetbrains.annotations.Nullable()
        java.util.Date published) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getRewindId() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Integer getProgress() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.Date component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.Date getPublished() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\bH\u00c6\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\bH\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u000bH\u00c6\u0003JK\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u00c6\u0001J\u0013\u0010\u001d\u001a\u00020\u000b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u00d6\u0003J\t\u0010 \u001a\u00020!H\u00d6\u0001J\t\u0010\"\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0013\u0010\t\u001a\u0004\u0018\u00010\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011\u00a8\u0006#"}, d2 = {"Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState$Complete;", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState;", "rewindId", "", "downloadId", "", "url", "published", "Ljava/util/Date;", "validUntil", "isValid", "", "(Ljava/lang/String;JLjava/lang/String;Ljava/util/Date;Ljava/util/Date;Z)V", "getDownloadId", "()J", "()Z", "getPublished", "()Ljava/util/Date;", "getRewindId", "()Ljava/lang/String;", "getUrl", "getValidUntil", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Complete extends org.wordpress.android.ui.jetpack.backup.download.BackupDownloadRequestState {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String rewindId = null;
        private final long downloadId = 0L;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String url = null;
        @org.jetbrains.annotations.Nullable()
        private final java.util.Date published = null;
        @org.jetbrains.annotations.Nullable()
        private final java.util.Date validUntil = null;
        private final boolean isValid = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.jetpack.backup.download.BackupDownloadRequestState.Complete copy(@org.jetbrains.annotations.NotNull()
        java.lang.String rewindId, long downloadId, @org.jetbrains.annotations.Nullable()
        java.lang.String url, @org.jetbrains.annotations.Nullable()
        java.util.Date published, @org.jetbrains.annotations.Nullable()
        java.util.Date validUntil, boolean isValid) {
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
        
        public Complete(@org.jetbrains.annotations.NotNull()
        java.lang.String rewindId, long downloadId, @org.jetbrains.annotations.Nullable()
        java.lang.String url, @org.jetbrains.annotations.Nullable()
        java.util.Date published, @org.jetbrains.annotations.Nullable()
        java.util.Date validUntil, boolean isValid) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getRewindId() {
            return null;
        }
        
        public final long component2() {
            return 0L;
        }
        
        public final long getDownloadId() {
            return 0L;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getUrl() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.Date component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.Date getPublished() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.Date component5() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.Date getValidUntil() {
            return null;
        }
        
        public final boolean component6() {
            return false;
        }
        
        public final boolean isValid() {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState$Empty;", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Empty extends org.wordpress.android.ui.jetpack.backup.download.BackupDownloadRequestState {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.jetpack.backup.download.BackupDownloadRequestState.Empty INSTANCE = null;
        
        private Empty() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState$Failure;", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState;", "()V", "NetworkUnavailable", "OtherRequestRunning", "RemoteRequestFailure", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState$Failure$NetworkUnavailable;", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState$Failure$RemoteRequestFailure;", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState$Failure$OtherRequestRunning;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class Failure extends org.wordpress.android.ui.jetpack.backup.download.BackupDownloadRequestState {
        
        private Failure() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState$Failure$NetworkUnavailable;", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState$Failure;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class NetworkUnavailable extends org.wordpress.android.ui.jetpack.backup.download.BackupDownloadRequestState.Failure {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.jetpack.backup.download.BackupDownloadRequestState.Failure.NetworkUnavailable INSTANCE = null;
            
            private NetworkUnavailable() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState$Failure$RemoteRequestFailure;", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState$Failure;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class RemoteRequestFailure extends org.wordpress.android.ui.jetpack.backup.download.BackupDownloadRequestState.Failure {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.jetpack.backup.download.BackupDownloadRequestState.Failure.RemoteRequestFailure INSTANCE = null;
            
            private RemoteRequestFailure() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState$Failure$OtherRequestRunning;", "Lorg/wordpress/android/ui/jetpack/backup/download/BackupDownloadRequestState$Failure;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class OtherRequestRunning extends org.wordpress.android.ui.jetpack.backup.download.BackupDownloadRequestState.Failure {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.jetpack.backup.download.BackupDownloadRequestState.Failure.OtherRequestRunning INSTANCE = null;
            
            private OtherRequestRunning() {
                super();
            }
        }
    }
}