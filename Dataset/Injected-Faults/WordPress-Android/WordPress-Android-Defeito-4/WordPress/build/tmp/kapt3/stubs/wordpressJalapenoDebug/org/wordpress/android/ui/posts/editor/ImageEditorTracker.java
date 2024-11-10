package org.wordpress.android.ui.posts.editor;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\u000e\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/ImageEditorTracker;", "", "context", "Landroid/content/Context;", "analyticsTrackerWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "(Landroid/content/Context;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;)V", "trackAddPhoto", "", "uris", "", "Landroid/net/Uri;", "trackEditorAction", "action", "Lorg/wordpress/android/imageeditor/ImageEditor$EditorAction;", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class ImageEditorTracker {
    private final android.content.Context context = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.posts.editor.ImageEditorTracker.Companion Companion = null;
    private static final java.lang.String ACTIONS = "actions";
    private static final java.lang.String NUMBER_OF_IMAGES = "number_of_images";
    
    @javax.inject.Inject()
    public ImageEditorTracker(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper) {
        super();
    }
    
    public final void trackEditorAction(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.imageeditor.ImageEditor.EditorAction action) {
    }
    
    public final void trackAddPhoto(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends android.net.Uri> uris) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/ImageEditorTracker$Companion;", "", "()V", "ACTIONS", "", "NUMBER_OF_IMAGES", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}