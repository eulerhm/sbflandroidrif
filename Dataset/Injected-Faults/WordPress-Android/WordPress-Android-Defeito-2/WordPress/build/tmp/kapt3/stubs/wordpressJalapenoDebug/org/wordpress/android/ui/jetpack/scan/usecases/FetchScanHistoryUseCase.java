package org.wordpress.android.ui.jetpack.scan.usecases;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0010B)\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0019\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/usecases/FetchScanHistoryUseCase;", "", "networkUtilsWrapper", "Lorg/wordpress/android/util/NetworkUtilsWrapper;", "scanStore", "Lorg/wordpress/android/fluxc/store/ScanStore;", "scanTracker", "Lorg/wordpress/android/util/analytics/ScanTracker;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/util/NetworkUtilsWrapper;Lorg/wordpress/android/fluxc/store/ScanStore;Lorg/wordpress/android/util/analytics/ScanTracker;Lkotlinx/coroutines/CoroutineDispatcher;)V", "fetch", "Lorg/wordpress/android/ui/jetpack/scan/usecases/FetchScanHistoryUseCase$FetchScanHistoryState;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "(Lorg/wordpress/android/fluxc/model/SiteModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "FetchScanHistoryState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class FetchScanHistoryUseCase {
    private final org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper = null;
    private final org.wordpress.android.fluxc.store.ScanStore scanStore = null;
    private final org.wordpress.android.util.analytics.ScanTracker scanTracker = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    
    @javax.inject.Inject()
    public FetchScanHistoryUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.NetworkUtilsWrapper networkUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.ScanStore scanStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.ScanTracker scanTracker, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetch(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.jetpack.scan.usecases.FetchScanHistoryUseCase.FetchScanHistoryState> continuation) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/usecases/FetchScanHistoryUseCase$FetchScanHistoryState;", "", "()V", "Failure", "Success", "Lorg/wordpress/android/ui/jetpack/scan/usecases/FetchScanHistoryUseCase$FetchScanHistoryState$Success;", "Lorg/wordpress/android/ui/jetpack/scan/usecases/FetchScanHistoryUseCase$FetchScanHistoryState$Failure;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class FetchScanHistoryState {
        
        private FetchScanHistoryState() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/usecases/FetchScanHistoryUseCase$FetchScanHistoryState$Success;", "Lorg/wordpress/android/ui/jetpack/scan/usecases/FetchScanHistoryUseCase$FetchScanHistoryState;", "threatModels", "", "Lorg/wordpress/android/fluxc/model/scan/threat/ThreatModel;", "(Ljava/util/List;)V", "getThreatModels", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Success extends org.wordpress.android.ui.jetpack.scan.usecases.FetchScanHistoryUseCase.FetchScanHistoryState {
            @org.jetbrains.annotations.NotNull()
            private final java.util.List<org.wordpress.android.fluxc.model.scan.threat.ThreatModel> threatModels = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.jetpack.scan.usecases.FetchScanHistoryUseCase.FetchScanHistoryState.Success copy(@org.jetbrains.annotations.NotNull()
            java.util.List<? extends org.wordpress.android.fluxc.model.scan.threat.ThreatModel> threatModels) {
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
            java.util.List<? extends org.wordpress.android.fluxc.model.scan.threat.ThreatModel> threatModels) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.fluxc.model.scan.threat.ThreatModel> component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.fluxc.model.scan.threat.ThreatModel> getThreatModels() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/usecases/FetchScanHistoryUseCase$FetchScanHistoryState$Failure;", "Lorg/wordpress/android/ui/jetpack/scan/usecases/FetchScanHistoryUseCase$FetchScanHistoryState;", "()V", "NetworkUnavailable", "RemoteRequestFailure", "Lorg/wordpress/android/ui/jetpack/scan/usecases/FetchScanHistoryUseCase$FetchScanHistoryState$Failure$NetworkUnavailable;", "Lorg/wordpress/android/ui/jetpack/scan/usecases/FetchScanHistoryUseCase$FetchScanHistoryState$Failure$RemoteRequestFailure;", "org.wordpress.android_wordpressJalapenoDebug"})
        public static abstract class Failure extends org.wordpress.android.ui.jetpack.scan.usecases.FetchScanHistoryUseCase.FetchScanHistoryState {
            
            private Failure() {
                super();
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/usecases/FetchScanHistoryUseCase$FetchScanHistoryState$Failure$NetworkUnavailable;", "Lorg/wordpress/android/ui/jetpack/scan/usecases/FetchScanHistoryUseCase$FetchScanHistoryState$Failure;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class NetworkUnavailable extends org.wordpress.android.ui.jetpack.scan.usecases.FetchScanHistoryUseCase.FetchScanHistoryState.Failure {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.jetpack.scan.usecases.FetchScanHistoryUseCase.FetchScanHistoryState.Failure.NetworkUnavailable INSTANCE = null;
                
                private NetworkUnavailable() {
                    super();
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/jetpack/scan/usecases/FetchScanHistoryUseCase$FetchScanHistoryState$Failure$RemoteRequestFailure;", "Lorg/wordpress/android/ui/jetpack/scan/usecases/FetchScanHistoryUseCase$FetchScanHistoryState$Failure;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class RemoteRequestFailure extends org.wordpress.android.ui.jetpack.scan.usecases.FetchScanHistoryUseCase.FetchScanHistoryState.Failure {
                @org.jetbrains.annotations.NotNull()
                public static final org.wordpress.android.ui.jetpack.scan.usecases.FetchScanHistoryUseCase.FetchScanHistoryState.Failure.RemoteRequestFailure INSTANCE = null;
                
                private RemoteRequestFailure() {
                    super();
                }
            }
        }
    }
}