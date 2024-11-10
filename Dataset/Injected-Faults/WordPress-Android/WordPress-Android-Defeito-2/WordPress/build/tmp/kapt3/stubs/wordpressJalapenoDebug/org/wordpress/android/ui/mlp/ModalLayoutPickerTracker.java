package org.wordpress.android.ui.mlp;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u000e\b\u0007\u0018\u00002\u00020\u0001:\u0001\u0019B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\fH\u0016J\u001e\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\fH\u0016J\u0010\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\nH\u0016J\u0010\u0010\u0010\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\nH\u0016J\u0018\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\nH\u0016J\u0018\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\nH\u0016J\u0010\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\nH\u0016J\u0010\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\nH\u0016J\u0018\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\nH\u0016J\u0010\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\nH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/ui/mlp/ModalLayoutPickerTracker;", "Lorg/wordpress/android/ui/layoutpicker/LayoutPickerTracker;", "tracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "(Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;)V", "getTracker", "()Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "filterDeselected", "", "filter", "", "selectedFilters", "", "filterSelected", "trackErrorShown", "message", "trackNoNetworkErrorShown", "trackPreviewLoaded", "template", "mode", "trackPreviewLoading", "trackPreviewModeChanged", "trackPreviewModeTapped", "trackPreviewViewed", "trackThumbnailModeTapped", "PROPERTY", "org.wordpress.android_wordpressJalapenoDebug"})
@javax.inject.Singleton()
public final class ModalLayoutPickerTracker implements org.wordpress.android.ui.layoutpicker.LayoutPickerTracker {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper tracker = null;
    
    @javax.inject.Inject()
    public ModalLayoutPickerTracker(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper tracker) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper getTracker() {
        return null;
    }
    
    @java.lang.Override()
    public void trackPreviewModeChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String mode) {
    }
    
    @java.lang.Override()
    public void trackThumbnailModeTapped(@org.jetbrains.annotations.NotNull()
    java.lang.String mode) {
    }
    
    @java.lang.Override()
    public void trackPreviewModeTapped(@org.jetbrains.annotations.NotNull()
    java.lang.String mode) {
    }
    
    @java.lang.Override()
    public void trackPreviewLoading(@org.jetbrains.annotations.NotNull()
    java.lang.String template, @org.jetbrains.annotations.NotNull()
    java.lang.String mode) {
    }
    
    @java.lang.Override()
    public void trackPreviewLoaded(@org.jetbrains.annotations.NotNull()
    java.lang.String template, @org.jetbrains.annotations.NotNull()
    java.lang.String mode) {
    }
    
    @java.lang.Override()
    public void trackPreviewViewed(@org.jetbrains.annotations.NotNull()
    java.lang.String template, @org.jetbrains.annotations.NotNull()
    java.lang.String mode) {
    }
    
    @java.lang.Override()
    public void trackNoNetworkErrorShown(@org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    @java.lang.Override()
    public void trackErrorShown(@org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    @java.lang.Override()
    public void filterSelected(@org.jetbrains.annotations.NotNull()
    java.lang.String filter, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> selectedFilters) {
    }
    
    @java.lang.Override()
    public void filterDeselected(@org.jetbrains.annotations.NotNull()
    java.lang.String filter, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> selectedFilters) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/mlp/ModalLayoutPickerTracker$PROPERTY;", "", "key", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getKey", "()Ljava/lang/String;", "TEMPLATE", "PREVIEW_MODE", "LOCATION", "FILTER", "SELECTED_FILTERS", "org.wordpress.android_wordpressJalapenoDebug"})
    static enum PROPERTY {
        /*public static final*/ TEMPLATE /* = new TEMPLATE(null) */,
        /*public static final*/ PREVIEW_MODE /* = new PREVIEW_MODE(null) */,
        /*public static final*/ LOCATION /* = new LOCATION(null) */,
        /*public static final*/ FILTER /* = new FILTER(null) */,
        /*public static final*/ SELECTED_FILTERS /* = new SELECTED_FILTERS(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String key = null;
        
        PROPERTY(java.lang.String key) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getKey() {
            return null;
        }
    }
}