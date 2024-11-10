package org.wordpress.android.ui.reader.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0005\u0006\u0007\bB\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016\u0082\u0001\u0004\t\n\u000b\f\u00a8\u0006\r"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/ReaderRepositoryCommunication;", "", "()V", "toString", "", "Error", "Started", "Success", "SuccessWithData", "Lorg/wordpress/android/ui/reader/repository/ReaderRepositoryCommunication$Started;", "Lorg/wordpress/android/ui/reader/repository/ReaderRepositoryCommunication$Success;", "Lorg/wordpress/android/ui/reader/repository/ReaderRepositoryCommunication$SuccessWithData;", "Lorg/wordpress/android/ui/reader/repository/ReaderRepositoryCommunication$Error;", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class ReaderRepositoryCommunication {
    
    private ReaderRepositoryCommunication() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/ReaderRepositoryCommunication$Started;", "Lorg/wordpress/android/ui/reader/repository/ReaderRepositoryCommunication;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Started extends org.wordpress.android.ui.reader.repository.ReaderRepositoryCommunication {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.reader.repository.ReaderRepositoryCommunication.Started INSTANCE = null;
        
        private Started() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/ReaderRepositoryCommunication$Success;", "Lorg/wordpress/android/ui/reader/repository/ReaderRepositoryCommunication;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Success extends org.wordpress.android.ui.reader.repository.ReaderRepositoryCommunication {
        @org.jetbrains.annotations.NotNull()
        public static final org.wordpress.android.ui.reader.repository.ReaderRepositoryCommunication.Success INSTANCE = null;
        
        private Success() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\b\u001a\u00028\u0000H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u0000H\u00c6\u0001\u00a2\u0006\u0002\u0010\nJ\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u00d6\u0003J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001R\u0013\u0010\u0003\u001a\u00028\u0000\u00a2\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0013"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/ReaderRepositoryCommunication$SuccessWithData;", "T", "Lorg/wordpress/android/ui/reader/repository/ReaderRepositoryCommunication;", "data", "(Ljava/lang/Object;)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "copy", "(Ljava/lang/Object;)Lorg/wordpress/android/ui/reader/repository/ReaderRepositoryCommunication$SuccessWithData;", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class SuccessWithData<T extends java.lang.Object> extends org.wordpress.android.ui.reader.repository.ReaderRepositoryCommunication {
        private final T data = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.repository.ReaderRepositoryCommunication.SuccessWithData<T> copy(T data) {
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
        
        public SuccessWithData(T data) {
            super();
        }
        
        public final T component1() {
            return null;
        }
        
        public final T getData() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/ReaderRepositoryCommunication$Error;", "Lorg/wordpress/android/ui/reader/repository/ReaderRepositoryCommunication;", "()V", "NetworkUnavailable", "RemoteRequestFailure", "Lorg/wordpress/android/ui/reader/repository/ReaderRepositoryCommunication$Error$NetworkUnavailable;", "Lorg/wordpress/android/ui/reader/repository/ReaderRepositoryCommunication$Error$RemoteRequestFailure;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class Error extends org.wordpress.android.ui.reader.repository.ReaderRepositoryCommunication {
        
        private Error() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/ReaderRepositoryCommunication$Error$NetworkUnavailable;", "Lorg/wordpress/android/ui/reader/repository/ReaderRepositoryCommunication$Error;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class NetworkUnavailable extends org.wordpress.android.ui.reader.repository.ReaderRepositoryCommunication.Error {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.reader.repository.ReaderRepositoryCommunication.Error.NetworkUnavailable INSTANCE = null;
            
            private NetworkUnavailable() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/reader/repository/ReaderRepositoryCommunication$Error$RemoteRequestFailure;", "Lorg/wordpress/android/ui/reader/repository/ReaderRepositoryCommunication$Error;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class RemoteRequestFailure extends org.wordpress.android.ui.reader.repository.ReaderRepositoryCommunication.Error {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.reader.repository.ReaderRepositoryCommunication.Error.RemoteRequestFailure INSTANCE = null;
            
            private RemoteRequestFailure() {
                super();
            }
        }
    }
}