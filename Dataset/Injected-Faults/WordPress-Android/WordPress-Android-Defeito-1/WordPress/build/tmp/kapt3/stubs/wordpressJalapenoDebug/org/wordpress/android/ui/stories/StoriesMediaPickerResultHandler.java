package org.wordpress.android.ui.stories;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\"\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J,\u0010\u0012\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\r2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0007J\u001e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0010\u0010\u0016\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\rH\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/stories/StoriesMediaPickerResultHandler;", "", "()V", "_onNavigation", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/mysite/SiteNavigationAction;", "onNavigation", "Landroidx/lifecycle/LiveData;", "getOnNavigation", "()Landroidx/lifecycle/LiveData;", "buildNavigationAction", "data", "Landroid/content/Intent;", "selectedSite", "Lorg/wordpress/android/fluxc/model/SiteModel;", "source", "Lorg/wordpress/android/ui/PagePostCreationSourcesDetail;", "handleMediaPickerResultForStories", "", "activity", "Landroid/app/Activity;", "isWPStoriesMediaBrowserTypeResult", "org.wordpress.android_wordpressJalapenoDebug"})
public final class StoriesMediaPickerResultHandler {
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mysite.SiteNavigationAction>> _onNavigation = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mysite.SiteNavigationAction>> onNavigation = null;
    
    @javax.inject.Inject()
    public StoriesMediaPickerResultHandler() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mysite.SiteNavigationAction>> getOnNavigation() {
        return null;
    }
    
    @java.lang.Deprecated()
    public final boolean handleMediaPickerResultForStories(@org.jetbrains.annotations.NotNull()
    android.content.Intent data, @org.jetbrains.annotations.Nullable()
    android.app.Activity activity, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.fluxc.model.SiteModel selectedSite, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.PagePostCreationSourcesDetail source) {
        return false;
    }
    
    public final boolean handleMediaPickerResultForStories(@org.jetbrains.annotations.NotNull()
    android.content.Intent data, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel selectedSite, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.PagePostCreationSourcesDetail source) {
        return false;
    }
    
    private final org.wordpress.android.ui.mysite.SiteNavigationAction buildNavigationAction(android.content.Intent data, org.wordpress.android.fluxc.model.SiteModel selectedSite, org.wordpress.android.ui.PagePostCreationSourcesDetail source) {
        return null;
    }
    
    private final boolean isWPStoriesMediaBrowserTypeResult(android.content.Intent data) {
        return false;
    }
}