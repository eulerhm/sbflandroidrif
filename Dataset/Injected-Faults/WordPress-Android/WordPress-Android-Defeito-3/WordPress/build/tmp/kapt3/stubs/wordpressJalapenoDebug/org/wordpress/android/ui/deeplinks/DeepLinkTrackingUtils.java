package org.wordpress.android.ui.deeplinks;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0002\u0019\u001aB\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ(\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002J\u0018\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\u0014\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u001e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/DeepLinkTrackingUtils;", "", "deepLinkUriUtils", "Lorg/wordpress/android/ui/deeplinks/DeepLinkUriUtils;", "deepLinkHandlers", "Lorg/wordpress/android/ui/deeplinks/handlers/DeepLinkHandlers;", "analyticsUtilsWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsUtilsWrapper;", "(Lorg/wordpress/android/ui/deeplinks/DeepLinkUriUtils;Lorg/wordpress/android/ui/deeplinks/handlers/DeepLinkHandlers;Lorg/wordpress/android/util/analytics/AnalyticsUtilsWrapper;)V", "buildTrackingData", "Lorg/wordpress/android/ui/deeplinks/DeepLinkTrackingUtils$TrackingData;", "uri", "Lorg/wordpress/android/util/UriWrapper;", "source", "Lorg/wordpress/android/ui/deeplinks/DeepLinkTrackingUtils$DeepLinkSource;", "sourceInfo", "", "buildTrackingDataFromNavigateAction", "navigateAction", "Lorg/wordpress/android/ui/deeplinks/DeepLinkNavigator$NavigateAction;", "extractTargetUri", "track", "", "action", "uriWrapper", "DeepLinkSource", "TrackingData", "org.wordpress.android_wordpressJalapenoDebug"})
public final class DeepLinkTrackingUtils {
    private final org.wordpress.android.ui.deeplinks.DeepLinkUriUtils deepLinkUriUtils = null;
    private final org.wordpress.android.ui.deeplinks.handlers.DeepLinkHandlers deepLinkHandlers = null;
    private final org.wordpress.android.util.analytics.AnalyticsUtilsWrapper analyticsUtilsWrapper = null;
    
    @javax.inject.Inject()
    public DeepLinkTrackingUtils(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.deeplinks.DeepLinkUriUtils deepLinkUriUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.deeplinks.handlers.DeepLinkHandlers deepLinkHandlers, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsUtilsWrapper analyticsUtilsWrapper) {
        super();
    }
    
    public final void track(@org.jetbrains.annotations.NotNull()
    java.lang.String action, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction navigateAction, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.UriWrapper uriWrapper) {
    }
    
    private final org.wordpress.android.ui.deeplinks.DeepLinkTrackingUtils.TrackingData buildTrackingDataFromNavigateAction(org.wordpress.android.ui.deeplinks.DeepLinkNavigator.NavigateAction navigateAction, org.wordpress.android.util.UriWrapper uri) {
        return null;
    }
    
    private final org.wordpress.android.ui.deeplinks.DeepLinkTrackingUtils.TrackingData buildTrackingData(org.wordpress.android.util.UriWrapper uri, org.wordpress.android.ui.deeplinks.DeepLinkTrackingUtils.DeepLinkSource source, java.lang.String sourceInfo) {
        return null;
    }
    
    private final org.wordpress.android.util.UriWrapper extractTargetUri(org.wordpress.android.util.UriWrapper uri) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J)\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/DeepLinkTrackingUtils$TrackingData;", "", "source", "Lorg/wordpress/android/ui/deeplinks/DeepLinkTrackingUtils$DeepLinkSource;", "url", "", "sourceInfo", "(Lorg/wordpress/android/ui/deeplinks/DeepLinkTrackingUtils$DeepLinkSource;Ljava/lang/String;Ljava/lang/String;)V", "getSource", "()Lorg/wordpress/android/ui/deeplinks/DeepLinkTrackingUtils$DeepLinkSource;", "getSourceInfo", "()Ljava/lang/String;", "getUrl", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class TrackingData {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.deeplinks.DeepLinkTrackingUtils.DeepLinkSource source = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String url = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String sourceInfo = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.deeplinks.DeepLinkTrackingUtils.TrackingData copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.deeplinks.DeepLinkTrackingUtils.DeepLinkSource source, @org.jetbrains.annotations.NotNull()
        java.lang.String url, @org.jetbrains.annotations.Nullable()
        java.lang.String sourceInfo) {
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
        
        public TrackingData(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.deeplinks.DeepLinkTrackingUtils.DeepLinkSource source, @org.jetbrains.annotations.NotNull()
        java.lang.String url, @org.jetbrains.annotations.Nullable()
        java.lang.String sourceInfo) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.deeplinks.DeepLinkTrackingUtils.DeepLinkSource component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.deeplinks.DeepLinkTrackingUtils.DeepLinkSource getSource() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getUrl() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getSourceInfo() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t\u00a8\u0006\n"}, d2 = {"Lorg/wordpress/android/ui/deeplinks/DeepLinkTrackingUtils$DeepLinkSource;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "EMAIL", "BANNER", "LINK", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum DeepLinkSource {
        /*public static final*/ EMAIL /* = new EMAIL(null) */,
        /*public static final*/ BANNER /* = new BANNER(null) */,
        /*public static final*/ LINK /* = new LINK(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String value = null;
        
        DeepLinkSource(java.lang.String value) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getValue() {
            return null;
        }
    }
}