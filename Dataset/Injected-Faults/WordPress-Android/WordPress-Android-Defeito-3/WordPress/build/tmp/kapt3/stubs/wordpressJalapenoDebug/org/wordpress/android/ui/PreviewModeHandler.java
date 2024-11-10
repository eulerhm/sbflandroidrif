package org.wordpress.android.ui;

import java.lang.System;

/**
 * Defines an interface for handling the [PreviewMode]
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0005H&\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/PreviewModeHandler;", "", "onPreviewModeChanged", "", "mode", "Lorg/wordpress/android/ui/PreviewMode;", "selectedPreviewMode", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract interface PreviewModeHandler {
    
    @org.jetbrains.annotations.NotNull()
    public abstract org.wordpress.android.ui.PreviewMode selectedPreviewMode();
    
    public abstract void onPreviewModeChanged(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.PreviewMode mode);
}