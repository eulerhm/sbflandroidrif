package org.wordpress.android.ui.jetpack.restore;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0007\u0003\u0004\u0005\u0006\u0007\b\tB\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0007\n\u000b\f\r\u000e\u000f\u0010\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState;", "", "()V", "AwaitingCredentials", "Complete", "Empty", "Failure", "Multisite", "Progress", "Success", "Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState$Success;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState$Progress;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState$Complete;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState$Multisite;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState$Empty;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState$AwaitingCredentials;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState$Failure;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class RestoreRequestState {
    
    private RestoreRequestState() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000bJ.\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0012J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\t\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState$Success;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState;", "requestRewindId", "", "rewindId", "restoreId", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)V", "getRequestRewindId", "()Ljava/lang/String;", "getRestoreId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getRewindId", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState$Success;", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Success extends org.wordpress.android.ui.jetpack.restore.RestoreRequestState {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String requestRewindId = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String rewindId = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Long restoreId = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.jetpack.restore.RestoreRequestState.Success copy(@org.jetbrains.annotations.NotNull()
        java.lang.String requestRewindId, @org.jetbrains.annotations.NotNull()
        java.lang.String rewindId, @org.jetbrains.annotations.Nullable()
        java.lang.Long restoreId) {
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
        java.lang.Long restoreId) {
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
        public final java.lang.Long getRestoreId() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000fJ\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\tH\u00c6\u0003JH\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u00c6\u0001\u00a2\u0006\u0002\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u00d6\u0003J\t\u0010\u001f\u001a\u00020\u0005H\u00d6\u0001J\t\u0010 \u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\f\u00a8\u0006!"}, d2 = {"Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState$Progress;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState;", "rewindId", "", "progress", "", "message", "currentEntry", "published", "Ljava/util/Date;", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/util/Date;)V", "getCurrentEntry", "()Ljava/lang/String;", "getMessage", "getProgress", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getPublished", "()Ljava/util/Date;", "getRewindId", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/util/Date;)Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState$Progress;", "equals", "", "other", "", "hashCode", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Progress extends org.wordpress.android.ui.jetpack.restore.RestoreRequestState {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String rewindId = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Integer progress = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String message = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String currentEntry = null;
        @org.jetbrains.annotations.Nullable()
        private final java.util.Date published = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.jetpack.restore.RestoreRequestState.Progress copy(@org.jetbrains.annotations.NotNull()
        java.lang.String rewindId, @org.jetbrains.annotations.Nullable()
        java.lang.Integer progress, @org.jetbrains.annotations.Nullable()
        java.lang.String message, @org.jetbrains.annotations.Nullable()
        java.lang.String currentEntry, @org.jetbrains.annotations.Nullable()
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
        java.lang.String message, @org.jetbrains.annotations.Nullable()
        java.lang.String currentEntry, @org.jetbrains.annotations.Nullable()
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
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getMessage() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component4() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getCurrentEntry() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.Date component5() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.Date getPublished() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J)\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001J\t\u0010\u0019\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState$Complete;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState;", "rewindId", "", "restoreId", "", "published", "Ljava/util/Date;", "(Ljava/lang/String;JLjava/util/Date;)V", "getPublished", "()Ljava/util/Date;", "getRestoreId", "()J", "getRewindId", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Complete extends org.wordpress.android.ui.jetpack.restore.RestoreRequestState {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String rewindId = null;
        private final long restoreId = 0L;
        @org.jetbrains.annotations.Nullable()
        private final java.util.Date published = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.jetpack.restore.RestoreRequestState.Complete copy(@org.jetbrains.annotations.NotNull()
        java.lang.String rewindId, long restoreId, @org.jetbrains.annotations.Nullable()
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
        
        public Complete(@org.jetbrains.annotations.NotNull()
        java.lang.String rewindId, long restoreId, @org.jetbrains.annotations.Nullable()
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
        
        public final long component2() {
            return 0L;
        }
        
        public final long getRestoreId() {
            return 0L;
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
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState$Multisite;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Multisite extends org.wordpress.android.ui.jetpack.restore.RestoreRequestState {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.jetpack.restore.RestoreRequestState.Multisite INSTANCE = null;
        
        private Multisite() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState$Empty;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Empty extends org.wordpress.android.ui.jetpack.restore.RestoreRequestState {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.jetpack.restore.RestoreRequestState.Empty INSTANCE = null;
        
        private Empty() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0006\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\u0007\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\b\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\nH\u00d6\u0003J\t\u0010\u000b\u001a\u00020\fH\u00d6\u0001J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0005\u00a8\u0006\u000f"}, d2 = {"Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState$AwaitingCredentials;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState;", "isAwaitingCredentials", "", "(Z)V", "()Z", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class AwaitingCredentials extends org.wordpress.android.ui.jetpack.restore.RestoreRequestState {
        private final boolean isAwaitingCredentials = false;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.jetpack.restore.RestoreRequestState.AwaitingCredentials copy(boolean isAwaitingCredentials) {
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
        
        public AwaitingCredentials(boolean isAwaitingCredentials) {
            super();
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final boolean isAwaitingCredentials() {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState$Failure;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState;", "()V", "NetworkUnavailable", "OtherRequestRunning", "RemoteRequestFailure", "Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState$Failure$NetworkUnavailable;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState$Failure$RemoteRequestFailure;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState$Failure$OtherRequestRunning;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class Failure extends org.wordpress.android.ui.jetpack.restore.RestoreRequestState {
        
        private Failure() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState$Failure$NetworkUnavailable;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState$Failure;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class NetworkUnavailable extends org.wordpress.android.ui.jetpack.restore.RestoreRequestState.Failure {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.jetpack.restore.RestoreRequestState.Failure.NetworkUnavailable INSTANCE = null;
            
            private NetworkUnavailable() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState$Failure$RemoteRequestFailure;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState$Failure;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class RemoteRequestFailure extends org.wordpress.android.ui.jetpack.restore.RestoreRequestState.Failure {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.jetpack.restore.RestoreRequestState.Failure.RemoteRequestFailure INSTANCE = null;
            
            private RemoteRequestFailure() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState$Failure$OtherRequestRunning;", "Lorg/wordpress/android/ui/jetpack/restore/RestoreRequestState$Failure;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class OtherRequestRunning extends org.wordpress.android.ui.jetpack.restore.RestoreRequestState.Failure {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.jetpack.restore.RestoreRequestState.Failure.OtherRequestRunning INSTANCE = null;
            
            private OtherRequestRunning() {
                super();
            }
        }
    }
}