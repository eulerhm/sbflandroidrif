package org.wordpress.android.util.analytics;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001:\u0003\"#$B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\nJ\u0006\u0010\u0010\u001a\u00020\nJ\u000e\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0015\u001a\u00020\nJ\u000e\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0018\u001a\u00020\nJ\u000e\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u001bJ!\u0010\u001c\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010!R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006%"}, d2 = {"Lorg/wordpress/android/util/analytics/ScanTracker;", "", "scanStore", "Lorg/wordpress/android/fluxc/store/ScanStore;", "analyticsTrackerWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/fluxc/store/ScanStore;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lkotlinx/coroutines/CoroutineDispatcher;)V", "trackOnError", "", "action", "Lorg/wordpress/android/util/analytics/ScanTracker$ErrorAction;", "cause", "Lorg/wordpress/android/util/analytics/ScanTracker$ErrorCause;", "trackOnFixAllThreatsButtonClicked", "trackOnFixAllThreatsConfirmed", "trackOnFixThreatButtonClicked", "signature", "", "trackOnFixThreatConfirmed", "trackOnGetFreeEstimateButtonClicked", "trackOnIgnoreThreatButtonClicked", "trackOnIgnoreThreatConfirmed", "trackOnScanButtonClicked", "trackOnScanHistoryTabSelected", "tab", "Lorg/wordpress/android/ui/jetpack/scan/history/ScanHistoryViewModel$ScanHistoryTabType;", "trackOnThreatItemClicked", "threatId", "", "source", "Lorg/wordpress/android/util/analytics/ScanTracker$OnThreatItemClickSource;", "(JLorg/wordpress/android/util/analytics/ScanTracker$OnThreatItemClickSource;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ErrorAction", "ErrorCause", "OnThreatItemClickSource", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class ScanTracker {
    private final org.wordpress.android.fluxc.store.ScanStore scanStore = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    
    @javax.inject.Inject()
    public ScanTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.ScanStore scanStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super();
    }
    
    public final void trackOnGetFreeEstimateButtonClicked() {
    }
    
    public final void trackOnScanHistoryTabSelected(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.jetpack.scan.history.ScanHistoryViewModel.ScanHistoryTabType tab) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object trackOnThreatItemClicked(long threatId, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.ScanTracker.OnThreatItemClickSource source, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    public final void trackOnScanButtonClicked() {
    }
    
    public final void trackOnIgnoreThreatButtonClicked(@org.jetbrains.annotations.NotNull()
    java.lang.String signature) {
    }
    
    public final void trackOnIgnoreThreatConfirmed(@org.jetbrains.annotations.NotNull()
    java.lang.String signature) {
    }
    
    public final void trackOnFixThreatButtonClicked(@org.jetbrains.annotations.NotNull()
    java.lang.String signature) {
    }
    
    public final void trackOnFixThreatConfirmed(@org.jetbrains.annotations.NotNull()
    java.lang.String signature) {
    }
    
    public final void trackOnFixAllThreatsButtonClicked() {
    }
    
    public final void trackOnFixAllThreatsConfirmed() {
    }
    
    public final void trackOnError(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.ScanTracker.ErrorAction action, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.ScanTracker.ErrorCause cause) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/util/analytics/ScanTracker$OnThreatItemClickSource;", "", "section", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getSection", "()Ljava/lang/String;", "HISTORY", "SCANNER", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum OnThreatItemClickSource {
        /*public static final*/ HISTORY /* = new HISTORY(null) */,
        /*public static final*/ SCANNER /* = new SCANNER(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String section = null;
        
        OnThreatItemClickSource(java.lang.String section) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getSection() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/util/analytics/ScanTracker$ErrorAction;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "FIX_ALL", "FIX", "SCAN", "IGNORE", "FETCH_SCAN_STATE", "FETCH_FIX_THREAT_STATUS", "FETCH_SCAN_HISTORY", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum ErrorAction {
        /*public static final*/ FIX_ALL /* = new FIX_ALL(null) */,
        /*public static final*/ FIX /* = new FIX(null) */,
        /*public static final*/ SCAN /* = new SCAN(null) */,
        /*public static final*/ IGNORE /* = new IGNORE(null) */,
        /*public static final*/ FETCH_SCAN_STATE /* = new FETCH_SCAN_STATE(null) */,
        /*public static final*/ FETCH_FIX_THREAT_STATUS /* = new FETCH_FIX_THREAT_STATUS(null) */,
        /*public static final*/ FETCH_SCAN_HISTORY /* = new FETCH_SCAN_HISTORY(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String value = null;
        
        ErrorAction(java.lang.String value) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getValue() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n\u00a8\u0006\u000b"}, d2 = {"Lorg/wordpress/android/util/analytics/ScanTracker$ErrorCause;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "OFFLINE", "REMOTE", "ALL_THREATS_NOT_FIXED", "OTHER", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum ErrorCause {
        /*public static final*/ OFFLINE /* = new OFFLINE(null) */,
        /*public static final*/ REMOTE /* = new REMOTE(null) */,
        /*public static final*/ ALL_THREATS_NOT_FIXED /* = new ALL_THREATS_NOT_FIXED(null) */,
        /*public static final*/ OTHER /* = new OTHER(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String value = null;
        
        ErrorCause(java.lang.String value) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getValue() {
            return null;
        }
    }
}