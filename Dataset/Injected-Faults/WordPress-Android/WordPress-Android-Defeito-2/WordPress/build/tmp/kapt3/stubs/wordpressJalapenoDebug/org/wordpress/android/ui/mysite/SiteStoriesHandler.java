package org.wordpress.android.ui.mysite;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B7\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\u0006\u0010\u001e\u001a\u00020\u001fJ\u001e\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&J\u0010\u0010\'\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020)H\u0007J\u0010\u0010*\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020+H\u0007R\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R+\u0010\u0015\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0012 \u0017*\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00110\u00110\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001d\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00110\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006,"}, d2 = {"Lorg/wordpress/android/ui/mysite/SiteStoriesHandler;", "", "eventBusWrapper", "Lorg/wordpress/android/util/EventBusWrapper;", "resourceProvider", "Lorg/wordpress/android/viewmodel/ResourceProvider;", "storiesTrackerHelper", "Lorg/wordpress/android/ui/stories/StoriesTrackerHelper;", "contextProvider", "Lorg/wordpress/android/viewmodel/ContextProvider;", "selectedSiteRepository", "Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;", "storiesMediaPickerResultHandler", "Lorg/wordpress/android/ui/stories/StoriesMediaPickerResultHandler;", "(Lorg/wordpress/android/util/EventBusWrapper;Lorg/wordpress/android/viewmodel/ResourceProvider;Lorg/wordpress/android/ui/stories/StoriesTrackerHelper;Lorg/wordpress/android/viewmodel/ContextProvider;Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;Lorg/wordpress/android/ui/stories/StoriesMediaPickerResultHandler;)V", "_onNavigation", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/mysite/SiteNavigationAction;", "_onSnackbar", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "onNavigation", "Landroidx/lifecycle/MediatorLiveData;", "kotlin.jvm.PlatformType", "getOnNavigation", "()Landroidx/lifecycle/MediatorLiveData;", "onSnackbar", "Landroidx/lifecycle/LiveData;", "getOnSnackbar", "()Landroidx/lifecycle/LiveData;", "clear", "", "handleStoriesResult", "siteModel", "Lorg/wordpress/android/fluxc/model/SiteModel;", "data", "Landroid/content/Intent;", "source", "Lorg/wordpress/android/ui/PagePostCreationSourcesDetail;", "onEventMainThread", "event", "Lcom/wordpress/stories/compose/frame/StorySaveEvents$StorySaveResult;", "onStorySaveStart", "Lcom/wordpress/stories/compose/frame/StorySaveEvents$StorySaveProcessStart;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SiteStoriesHandler {
    private final org.wordpress.android.util.EventBusWrapper eventBusWrapper = null;
    private final org.wordpress.android.viewmodel.ResourceProvider resourceProvider = null;
    private final org.wordpress.android.ui.stories.StoriesTrackerHelper storiesTrackerHelper = null;
    private final org.wordpress.android.viewmodel.ContextProvider contextProvider = null;
    private final org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository = null;
    private final org.wordpress.android.ui.stories.StoriesMediaPickerResultHandler storiesMediaPickerResultHandler = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> _onSnackbar = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> onSnackbar = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mysite.SiteNavigationAction>> _onNavigation = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mysite.SiteNavigationAction>> onNavigation = null;
    
    @javax.inject.Inject()
    public SiteStoriesHandler(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.EventBusWrapper eventBusWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ResourceProvider resourceProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stories.StoriesTrackerHelper storiesTrackerHelper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.viewmodel.ContextProvider contextProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stories.StoriesMediaPickerResultHandler storiesMediaPickerResultHandler) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> getOnSnackbar() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.mysite.SiteNavigationAction>> getOnNavigation() {
        return null;
    }
    
    public final void clear() {
    }
    
    @org.greenrobot.eventbus.Subscribe(sticky = true, threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onEventMainThread(@org.jetbrains.annotations.NotNull()
    com.wordpress.stories.compose.frame.StorySaveEvents.StorySaveResult event) {
    }
    
    @org.greenrobot.eventbus.Subscribe(sticky = true, threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onStorySaveStart(@org.jetbrains.annotations.NotNull()
    com.wordpress.stories.compose.frame.StorySaveEvents.StorySaveProcessStart event) {
    }
    
    public final void handleStoriesResult(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel siteModel, @org.jetbrains.annotations.NotNull()
    android.content.Intent data, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.PagePostCreationSourcesDetail source) {
    }
}