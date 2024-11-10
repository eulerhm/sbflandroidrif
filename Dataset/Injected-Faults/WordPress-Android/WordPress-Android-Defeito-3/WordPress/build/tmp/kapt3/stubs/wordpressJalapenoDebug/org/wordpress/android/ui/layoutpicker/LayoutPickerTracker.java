package org.wordpress.android.ui.layoutpicker;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\r\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H&J\u001e\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0005H&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0005H&J\u0018\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005H&J\u0018\u0010\u000f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005H&J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0005H&J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0005H&J\u0018\u0010\u0012\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005H&J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0005H\u0016\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/layoutpicker/LayoutPickerTracker;", "", "filterDeselected", "", "filter", "", "selectedFilters", "", "filterSelected", "trackErrorShown", "message", "trackNoNetworkErrorShown", "trackPreviewLoaded", "template", "mode", "trackPreviewLoading", "trackPreviewModeChanged", "trackPreviewModeTapped", "trackPreviewViewed", "trackThumbnailModeTapped", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract interface LayoutPickerTracker {
    
    public abstract void trackPreviewModeChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String mode);
    
    public abstract void trackThumbnailModeTapped(@org.jetbrains.annotations.NotNull()
    java.lang.String mode);
    
    public abstract void trackPreviewModeTapped(@org.jetbrains.annotations.NotNull()
    java.lang.String mode);
    
    public abstract void trackPreviewLoading(@org.jetbrains.annotations.NotNull()
    java.lang.String template, @org.jetbrains.annotations.NotNull()
    java.lang.String mode);
    
    public abstract void trackPreviewLoaded(@org.jetbrains.annotations.NotNull()
    java.lang.String template, @org.jetbrains.annotations.NotNull()
    java.lang.String mode);
    
    public abstract void trackPreviewViewed(@org.jetbrains.annotations.NotNull()
    java.lang.String template, @org.jetbrains.annotations.NotNull()
    java.lang.String mode);
    
    public abstract void trackNoNetworkErrorShown(@org.jetbrains.annotations.NotNull()
    java.lang.String message);
    
    public abstract void trackErrorShown(@org.jetbrains.annotations.NotNull()
    java.lang.String message);
    
    public abstract void filterSelected(@org.jetbrains.annotations.NotNull()
    java.lang.String filter, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> selectedFilters);
    
    public abstract void filterDeselected(@org.jetbrains.annotations.NotNull()
    java.lang.String filter, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> selectedFilters);
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 3)
    public final class DefaultImpls {
        
        public static void trackThumbnailModeTapped(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.layoutpicker.LayoutPickerTracker $this, @org.jetbrains.annotations.NotNull()
        java.lang.String mode) {
        }
    }
}