package org.wordpress.android.ui.posts.editor;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ(\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012J.\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0014\b\u0002\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\u0019H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/ui/posts/editor/EditorTracker;", "", "context", "Landroid/content/Context;", "analyticsTrackerWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "(Landroid/content/Context;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;)V", "trackAddMediaEvent", "", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "source", "Lorg/wordpress/android/ui/posts/editor/media/AddExistingMediaSource;", "isVideo", "", "trackAddMediaFromDevice", "isNew", "uri", "Landroid/net/Uri;", "trackEditorEvent", "event", "Lorg/wordpress/android/editor/EditorFragmentAbstract$TrackableEvent;", "editorName", "", "properties", "", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Reusable()
public final class EditorTracker {
    private final android.content.Context context = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper = null;
    
    @javax.inject.Inject()
    public EditorTracker(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper) {
        super();
    }
    
    /**
     * Analytics about media from device
     *
     * @param isNew Whether is a fresh media
     * @param isVideo Whether is a video or not
     * @param uri The URI of the media on the device, or null
     */
    public final void trackAddMediaFromDevice(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, boolean isNew, boolean isVideo, @org.jetbrains.annotations.Nullable()
    android.net.Uri uri) {
    }
    
    /**
     * Analytics about media already available in the blog's library.
     * @param source where the media is being added from
     * @param isVideo if media is a video
     */
    public final void trackAddMediaEvent(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.posts.editor.media.AddExistingMediaSource source, boolean isVideo) {
    }
    
    @kotlin.jvm.JvmOverloads()
    public final void trackEditorEvent(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.editor.EditorFragmentAbstract.TrackableEvent event, @org.jetbrains.annotations.NotNull()
    java.lang.String editorName) {
    }
    
    @kotlin.jvm.JvmOverloads()
    public final void trackEditorEvent(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.editor.EditorFragmentAbstract.TrackableEvent event, @org.jetbrains.annotations.NotNull()
    java.lang.String editorName, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.String> properties) {
    }
}