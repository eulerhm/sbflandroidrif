package org.wordpress.android.ui.jetpack.scan.details.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u000fB!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ!\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/details/usecases/IgnoreThreatUseCase;", "", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "scanStore", "Lorg/wordpress/android/fluxc/store/ScanStore;", "ioDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/fluxc/store/ScanStore;Lkotlinx/coroutines/CoroutineDispatcher;)V", "ignoreThreat", "Lorg/wordpress/android/ui/jetpack/scan/details/usecases/IgnoreThreatUseCase$IgnoreThreatState;", "remoteSiteId", "", "threatId", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "IgnoreThreatState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class IgnoreThreatUseCase {
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final org.wordpress.android.fluxc.store.ScanStore scanStore = null;
    private final kotlinx.coroutines.CoroutineDispatcher ioDispatcher = null;
    
    @javax.inject.Inject()
    public IgnoreThreatUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.ScanStore scanStore, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "IO_THREAD")
    kotlinx.coroutines.CoroutineDispatcher ioDispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object ignoreThreat(long remoteSiteId, long threatId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.jetpack.scan.details.usecases.IgnoreThreatUseCase.IgnoreThreatState> continuation) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/details/usecases/IgnoreThreatUseCase$IgnoreThreatState;", "", "()V", "Failure", "Success", "Lorg/wordpress/android/ui/jetpack/scan/details/usecases/IgnoreThreatUseCase$IgnoreThreatState$Success;", "Lorg/wordpress/android/ui/jetpack/scan/details/usecases/IgnoreThreatUseCase$IgnoreThreatState$Failure;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class IgnoreThreatState {
        
        private IgnoreThreatState() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/details/usecases/IgnoreThreatUseCase$IgnoreThreatState$Success;", "Lorg/wordpress/android/ui/jetpack/scan/details/usecases/IgnoreThreatUseCase$IgnoreThreatState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Success extends org.wordpress.android.ui.jetpack.scan.details.usecases.IgnoreThreatUseCase.IgnoreThreatState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.jetpack.scan.details.usecases.IgnoreThreatUseCase.IgnoreThreatState.Success INSTANCE = null;
            
            private Success() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/details/usecases/IgnoreThreatUseCase$IgnoreThreatState$Failure;", "Lorg/wordpress/android/ui/jetpack/scan/details/usecases/IgnoreThreatUseCase$IgnoreThreatState;", "()V", "NetworkUnavailable", "RemoteRequestFailure", "Lorg/wordpress/android/ui/jetpack/scan/details/usecases/IgnoreThreatUseCase$IgnoreThreatState$Failure$NetworkUnavailable;", "Lorg/wordpress/android/ui/jetpack/scan/details/usecases/IgnoreThreatUseCase$IgnoreThreatState$Failure$RemoteRequestFailure;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static abstract class Failure extends org.wordpress.android.ui.jetpack.scan.details.usecases.IgnoreThreatUseCase.IgnoreThreatState {
            
            private Failure() {
                super();
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/details/usecases/IgnoreThreatUseCase$IgnoreThreatState$Failure$NetworkUnavailable;", "Lorg/wordpress/android/ui/jetpack/scan/details/usecases/IgnoreThreatUseCase$IgnoreThreatState$Failure;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class NetworkUnavailable extends org.wordpress.android.ui.jetpack.scan.details.usecases.IgnoreThreatUseCase.IgnoreThreatState.Failure {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.jetpack.scan.details.usecases.IgnoreThreatUseCase.IgnoreThreatState.Failure.NetworkUnavailable INSTANCE = null;
                
                private NetworkUnavailable() {
                    super();
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/details/usecases/IgnoreThreatUseCase$IgnoreThreatState$Failure$RemoteRequestFailure;", "Lorg/wordpress/android/ui/jetpack/scan/details/usecases/IgnoreThreatUseCase$IgnoreThreatState$Failure;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class RemoteRequestFailure extends org.wordpress.android.ui.jetpack.scan.details.usecases.IgnoreThreatUseCase.IgnoreThreatState.Failure {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.jetpack.scan.details.usecases.IgnoreThreatUseCase.IgnoreThreatState.Failure.RemoteRequestFailure INSTANCE = null;
                
                private RemoteRequestFailure() {
                    super();
                }
            }
        }
    }
}