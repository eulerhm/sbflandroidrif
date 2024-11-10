package org.wordpress.android.ui.stats.refresh.lists.widget.minified;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u000104H\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0013\u001a\u00020\u00148\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001e\u0010\u0019\u001a\u00020\u001a8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u000e\u0010\u001f\u001a\u00020 X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010!\u001a\u00020\"8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u000e\u0010\'\u001a\u00020(X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010)\u001a\u00020*8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.\u00a8\u00065"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/widget/minified/StatsMinifiedWidgetConfigureFragment;", "Ldagger/android/support/DaggerFragment;", "()V", "analyticsTrackerWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "getAnalyticsTrackerWrapper", "()Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "setAnalyticsTrackerWrapper", "(Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;)V", "appPrefsWrapper", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "getAppPrefsWrapper", "()Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "setAppPrefsWrapper", "(Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;)V", "colorSelectionViewModel", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/configuration/StatsColorSelectionViewModel;", "dataTypeSelectionViewModel", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/configuration/StatsDataTypeSelectionViewModel;", "imageManager", "Lorg/wordpress/android/util/image/ImageManager;", "getImageManager", "()Lorg/wordpress/android/util/image/ImageManager;", "setImageManager", "(Lorg/wordpress/android/util/image/ImageManager;)V", "minifiedWidgetUpdater", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/minified/MinifiedWidgetUpdater;", "getMinifiedWidgetUpdater", "()Lorg/wordpress/android/ui/stats/refresh/lists/widget/minified/MinifiedWidgetUpdater;", "setMinifiedWidgetUpdater", "(Lorg/wordpress/android/ui/stats/refresh/lists/widget/minified/MinifiedWidgetUpdater;)V", "siteSelectionViewModel", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/configuration/StatsSiteSelectionViewModel;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "getSiteStore", "()Lorg/wordpress/android/fluxc/store/SiteStore;", "setSiteStore", "(Lorg/wordpress/android/fluxc/store/SiteStore;)V", "viewModel", "Lorg/wordpress/android/ui/stats/refresh/lists/widget/minified/StatsMinifiedWidgetConfigureViewModel;", "viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getViewModelFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "setViewModelFactory", "(Landroidx/lifecycle/ViewModelProvider$Factory;)V", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class StatsMinifiedWidgetConfigureFragment extends dagger.android.support.DaggerFragment {
    @javax.inject.Inject()
    public androidx.lifecycle.ViewModelProvider.Factory viewModelFactory;
    @javax.inject.Inject()
    public org.wordpress.android.ui.stats.refresh.lists.widget.minified.MinifiedWidgetUpdater minifiedWidgetUpdater;
    @javax.inject.Inject()
    public org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper;
    @javax.inject.Inject()
    public org.wordpress.android.fluxc.store.SiteStore siteStore;
    @javax.inject.Inject()
    public org.wordpress.android.util.image.ImageManager imageManager;
    @javax.inject.Inject()
    public org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper;
    private org.wordpress.android.ui.stats.refresh.lists.widget.minified.StatsMinifiedWidgetConfigureViewModel viewModel;
    private org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsSiteSelectionViewModel siteSelectionViewModel;
    private org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsColorSelectionViewModel colorSelectionViewModel;
    private org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsDataTypeSelectionViewModel dataTypeSelectionViewModel;
    
    public StatsMinifiedWidgetConfigureFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.ViewModelProvider.Factory getViewModelFactory() {
        return null;
    }
    
    public final void setViewModelFactory(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.ViewModelProvider.Factory p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.stats.refresh.lists.widget.minified.MinifiedWidgetUpdater getMinifiedWidgetUpdater() {
        return null;
    }
    
    public final void setMinifiedWidgetUpdater(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.widget.minified.MinifiedWidgetUpdater p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.prefs.AppPrefsWrapper getAppPrefsWrapper() {
        return null;
    }
    
    public final void setAppPrefsWrapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.AppPrefsWrapper p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.SiteStore getSiteStore() {
        return null;
    }
    
    public final void setSiteStore(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.image.ImageManager getImageManager() {
        return null;
    }
    
    public final void setImageManager(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.image.ImageManager p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper getAnalyticsTrackerWrapper() {
        return null;
    }
    
    public final void setAnalyticsTrackerWrapper(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper p0) {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
}