package org.wordpress.android.ui.uploads;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH&\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/uploads/VideoOptimizationListener;", "", "onVideoOptimizationCompleted", "", "media", "Lorg/wordpress/android/fluxc/model/MediaModel;", "onVideoOptimizationProgress", "progress", "", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract interface VideoOptimizationListener {
    
    public abstract void onVideoOptimizationCompleted(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.MediaModel media);
    
    public abstract void onVideoOptimizationProgress(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.MediaModel media, float progress);
}