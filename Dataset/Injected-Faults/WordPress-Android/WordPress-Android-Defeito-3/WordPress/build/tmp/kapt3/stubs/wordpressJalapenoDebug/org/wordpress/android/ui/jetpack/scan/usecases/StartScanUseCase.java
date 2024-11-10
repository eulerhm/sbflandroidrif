package org.wordpress.android.ui.jetpack.scan.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0013B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u001f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\rH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ#\u0010\u000f\u001a\u00020\u0010*\b\u0012\u0004\u0012\u00020\u000b0\u00112\u0006\u0010\f\u001a\u00020\rH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/usecases/StartScanUseCase;", "", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "scanStore", "Lorg/wordpress/android/fluxc/store/ScanStore;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/fluxc/store/ScanStore;Lkotlinx/coroutines/CoroutineDispatcher;)V", "startScan", "Lkotlinx/coroutines/flow/Flow;", "Lorg/wordpress/android/ui/jetpack/scan/usecases/StartScanUseCase$StartScanState;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "(Lorg/wordpress/android/fluxc/model/SiteModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateScanScanningStateInDb", "", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lorg/wordpress/android/fluxc/model/SiteModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "StartScanState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class StartScanUseCase {
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final org.wordpress.android.fluxc.store.ScanStore scanStore = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    
    @javax.inject.Inject()
    public StartScanUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.ScanStore scanStore, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object startScan(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends org.wordpress.android.ui.jetpack.scan.usecases.StartScanUseCase.StartScanState>> continuation) {
        return null;
    }
    
    private final java.lang.Object updateScanScanningStateInDb(kotlinx.coroutines.flow.FlowCollector<? super org.wordpress.android.ui.jetpack.scan.usecases.StartScanUseCase.StartScanState> $this$updateScanScanningStateInDb, org.wordpress.android.fluxc.model.SiteModel site, kotlin.coroutines.Continuation<? super kotlin.Unit> p2) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/usecases/StartScanUseCase$StartScanState;", "", "()V", "Failure", "ScanningStateUpdatedInDb", "Success", "Lorg/wordpress/android/ui/jetpack/scan/usecases/StartScanUseCase$StartScanState$ScanningStateUpdatedInDb;", "Lorg/wordpress/android/ui/jetpack/scan/usecases/StartScanUseCase$StartScanState$Success;", "Lorg/wordpress/android/ui/jetpack/scan/usecases/StartScanUseCase$StartScanState$Failure;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class StartScanState {
        
        private StartScanState() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/usecases/StartScanUseCase$StartScanState$ScanningStateUpdatedInDb;", "Lorg/wordpress/android/ui/jetpack/scan/usecases/StartScanUseCase$StartScanState;", "model", "Lorg/wordpress/android/fluxc/model/scan/ScanStateModel;", "(Lorg/wordpress/android/fluxc/model/scan/ScanStateModel;)V", "getModel", "()Lorg/wordpress/android/fluxc/model/scan/ScanStateModel;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class ScanningStateUpdatedInDb extends org.wordpress.android.ui.jetpack.scan.usecases.StartScanUseCase.StartScanState {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.fluxc.model.scan.ScanStateModel model = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.jetpack.scan.usecases.StartScanUseCase.StartScanState.ScanningStateUpdatedInDb copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.scan.ScanStateModel model) {
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
            
            public ScanningStateUpdatedInDb(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.fluxc.model.scan.ScanStateModel model) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.scan.ScanStateModel component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.fluxc.model.scan.ScanStateModel getModel() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/usecases/StartScanUseCase$StartScanState$Success;", "Lorg/wordpress/android/ui/jetpack/scan/usecases/StartScanUseCase$StartScanState;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Success extends org.wordpress.android.ui.jetpack.scan.usecases.StartScanUseCase.StartScanState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.jetpack.scan.usecases.StartScanUseCase.StartScanState.Success INSTANCE = null;
            
            private Success() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/usecases/StartScanUseCase$StartScanState$Failure;", "Lorg/wordpress/android/ui/jetpack/scan/usecases/StartScanUseCase$StartScanState;", "()V", "NetworkUnavailable", "RemoteRequestFailure", "Lorg/wordpress/android/ui/jetpack/scan/usecases/StartScanUseCase$StartScanState$Failure$NetworkUnavailable;", "Lorg/wordpress/android/ui/jetpack/scan/usecases/StartScanUseCase$StartScanState$Failure$RemoteRequestFailure;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static abstract class Failure extends org.wordpress.android.ui.jetpack.scan.usecases.StartScanUseCase.StartScanState {
            
            private Failure() {
                super();
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/usecases/StartScanUseCase$StartScanState$Failure$NetworkUnavailable;", "Lorg/wordpress/android/ui/jetpack/scan/usecases/StartScanUseCase$StartScanState$Failure;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class NetworkUnavailable extends org.wordpress.android.ui.jetpack.scan.usecases.StartScanUseCase.StartScanState.Failure {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.jetpack.scan.usecases.StartScanUseCase.StartScanState.Failure.NetworkUnavailable INSTANCE = null;
                
                private NetworkUnavailable() {
                    super();
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/usecases/StartScanUseCase$StartScanState$Failure$RemoteRequestFailure;", "Lorg/wordpress/android/ui/jetpack/scan/usecases/StartScanUseCase$StartScanState$Failure;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class RemoteRequestFailure extends org.wordpress.android.ui.jetpack.scan.usecases.StartScanUseCase.StartScanState.Failure {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.jetpack.scan.usecases.StartScanUseCase.StartScanState.Failure.RemoteRequestFailure INSTANCE = null;
                
                private RemoteRequestFailure() {
                    super();
                }
            }
        }
    }
}