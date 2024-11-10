package org.wordpress.android.ui.photopicker;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u001a\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J \u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u000eJ \u0010\u0014\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0015\u001a\u00020\u0016J \u0010\u0017\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u000eJB\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u0012\u001a\u00020\u00132\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u001b\u001a\u00020\u00162\b\b\u0001\u0010\u001c\u001a\u00020\u0016H\u0002J\u001e\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u0013J\u000e\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020!J/\u0010\"\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u00a2\u0006\u0002\u0010#J\u0018\u0010$\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u0018\u0010$\u001a\u00020\u00112\u0006\u0010 \u001a\u00020!2\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ&\u0010%\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u0013J\u0018\u0010&\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u0018\u0010\'\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u001e\u0010(\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2 = {"Lorg/wordpress/android/ui/photopicker/MediaPickerLauncher;", "", "analyticsTrackerWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "(Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;)V", "buildLocalMediaPickerSetup", "Lorg/wordpress/android/ui/mediapicker/MediaPickerSetup;", "browserType", "Lorg/wordpress/android/ui/media/MediaBrowserType;", "buildSitePickerIntent", "Landroid/content/Intent;", "activity", "Landroid/app/Activity;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "buildWPMediaLibraryPickerSetup", "showAudioFilePicker", "", "canMultiselect", "", "showFeaturedImagePicker", "localPostId", "", "showFilePicker", "allowedTypes", "", "Lorg/wordpress/android/ui/mediapicker/MediaType;", "requestCode", "title", "showGifPickerForResult", "allowMultipleSelection", "showGravatarPicker", "fragment", "Landroidx/fragment/app/Fragment;", "showPhotoPickerForResult", "(Landroid/app/Activity;Lorg/wordpress/android/ui/media/MediaBrowserType;Lorg/wordpress/android/fluxc/model/SiteModel;Ljava/lang/Integer;)V", "showSiteIconPicker", "showStockMediaPickerForResult", "showStoriesPhotoPickerForResult", "showStoriesPhotoPickerForResultAndTrack", "viewWPMediaLibraryPickerForResult", "org.wordpress.android_wordpressJalapenoDebug"})
public final class MediaPickerLauncher {
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper = null;
    
    @javax.inject.Inject()
    public MediaPickerLauncher(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper) {
        super();
    }
    
    public final void showFeaturedImagePicker(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.SiteModel site, int localPostId) {
    }
    
    public final void showSiteIconPicker(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    public final void showSiteIconPicker(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment fragment, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    private final android.content.Intent buildSitePickerIntent(android.app.Activity activity, org.wordpress.android.fluxc.model.SiteModel site) {
        return null;
    }
    
    public final void showPhotoPickerForResult(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.media.MediaBrowserType browserType, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.Nullable()
    java.lang.Integer localPostId) {
    }
    
    public final void showStoriesPhotoPickerForResultAndTrack(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    public final void showStoriesPhotoPickerForResult(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    public final void showGravatarPicker(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment fragment) {
    }
    
    public final void showFilePicker(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, boolean canMultiselect, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    public final void showAudioFilePicker(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, boolean canMultiselect, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site) {
    }
    
    @kotlin.Suppress(names = {"LongParameterList"})
    private final void showFilePicker(android.app.Activity activity, org.wordpress.android.fluxc.model.SiteModel site, boolean canMultiselect, java.util.Set<? extends org.wordpress.android.ui.mediapicker.MediaType> allowedTypes, int requestCode, @androidx.annotation.StringRes()
    int title) {
    }
    
    public final void viewWPMediaLibraryPickerForResult(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.media.MediaBrowserType browserType) {
    }
    
    public final void showStockMediaPickerForResult(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, int requestCode, boolean allowMultipleSelection) {
    }
    
    public final void showGifPickerForResult(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, boolean allowMultipleSelection) {
    }
    
    private final org.wordpress.android.ui.mediapicker.MediaPickerSetup buildLocalMediaPickerSetup(org.wordpress.android.ui.media.MediaBrowserType browserType) {
        return null;
    }
    
    private final org.wordpress.android.ui.mediapicker.MediaPickerSetup buildWPMediaLibraryPickerSetup(org.wordpress.android.ui.media.MediaBrowserType browserType) {
        return null;
    }
}