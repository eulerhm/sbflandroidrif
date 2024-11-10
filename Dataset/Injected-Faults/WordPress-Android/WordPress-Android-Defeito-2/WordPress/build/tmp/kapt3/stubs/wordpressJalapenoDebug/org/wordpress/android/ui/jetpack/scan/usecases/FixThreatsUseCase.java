package org.wordpress.android.ui.jetpack.scan.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0010B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\'\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000eH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/usecases/FixThreatsUseCase;", "", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "scanStore", "Lorg/wordpress/android/fluxc/store/ScanStore;", "ioDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/fluxc/store/ScanStore;Lkotlinx/coroutines/CoroutineDispatcher;)V", "fixThreats", "Lorg/wordpress/android/ui/jetpack/scan/usecases/FixThreatsUseCase$FixThreatsState;", "remoteSiteId", "", "fixableThreatIds", "", "(JLjava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "FixThreatsState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class FixThreatsUseCase {
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final org.wordpress.android.fluxc.store.ScanStore scanStore = null;
    private final kotlinx.coroutines.CoroutineDispatcher ioDispatcher = null;
    
    @javax.inject.Inject()
    public FixThreatsUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.ScanStore scanStore, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "IO_THREAD")
    kotlinx.coroutines.CoroutineDispatcher ioDispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fixThreats(long remoteSiteId, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Long> fixableThreatIds, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.jetpack.scan.usecases.FixThreatsUseCase.FixThreatsState> continuation) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/usecases/FixThreatsUseCase$FixThreatsState;", "", "()V", "Failure", "Success", "Lorg/wordpress/android/ui/jetpack/scan/usecases/FixThreatsUseCase$FixThreatsState$Success;", "Lorg/wordpress/android/ui/jetpack/scan/usecases/FixThreatsUseCase$FixThreatsState$Failure;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class FixThreatsState {
        
        private FixThreatsState() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/usecases/FixThreatsUseCase$FixThreatsState$Success;", "Lorg/wordpress/android/ui/jetpack/scan/usecases/FixThreatsUseCase$FixThreatsState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Success extends org.wordpress.android.ui.jetpack.scan.usecases.FixThreatsUseCase.FixThreatsState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.jetpack.scan.usecases.FixThreatsUseCase.FixThreatsState.Success INSTANCE = null;
            
            private Success() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/usecases/FixThreatsUseCase$FixThreatsState$Failure;", "Lorg/wordpress/android/ui/jetpack/scan/usecases/FixThreatsUseCase$FixThreatsState;", "()V", "NetworkUnavailable", "RemoteRequestFailure", "Lorg/wordpress/android/ui/jetpack/scan/usecases/FixThreatsUseCase$FixThreatsState$Failure$NetworkUnavailable;", "Lorg/wordpress/android/ui/jetpack/scan/usecases/FixThreatsUseCase$FixThreatsState$Failure$RemoteRequestFailure;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static abstract class Failure extends org.wordpress.android.ui.jetpack.scan.usecases.FixThreatsUseCase.FixThreatsState {
            
            private Failure() {
                super();
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/usecases/FixThreatsUseCase$FixThreatsState$Failure$NetworkUnavailable;", "Lorg/wordpress/android/ui/jetpack/scan/usecases/FixThreatsUseCase$FixThreatsState$Failure;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class NetworkUnavailable extends org.wordpress.android.ui.jetpack.scan.usecases.FixThreatsUseCase.FixThreatsState.Failure {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.jetpack.scan.usecases.FixThreatsUseCase.FixThreatsState.Failure.NetworkUnavailable INSTANCE = null;
                
                private NetworkUnavailable() {
                    super();
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/usecases/FixThreatsUseCase$FixThreatsState$Failure$RemoteRequestFailure;", "Lorg/wordpress/android/ui/jetpack/scan/usecases/FixThreatsUseCase$FixThreatsState$Failure;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class RemoteRequestFailure extends org.wordpress.android.ui.jetpack.scan.usecases.FixThreatsUseCase.FixThreatsState.Failure {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.jetpack.scan.usecases.FixThreatsUseCase.FixThreatsState.Failure.RemoteRequestFailure INSTANCE = null;
                
                private RemoteRequestFailure() {
                    super();
                }
            }
        }
    }
}