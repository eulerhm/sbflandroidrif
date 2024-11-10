package org.wordpress.android.ui.stories.intro;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\u0019\u001a\u00020\u000bJ\u0006\u0010\u001a\u001a\u00020\u000bJ\u0006\u0010\u001b\u001a\u00020\u000bJ\u0006\u0010\u001c\u001a\u00020\u000bJ\u0006\u0010\u001d\u001a\u00020\u000bR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014\u00a8\u0006\u001f"}, d2 = {"Lorg/wordpress/android/ui/stories/intro/StoriesIntroViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "analyticsTrackerWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "appPrefsWrapper", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_onCreateButtonClicked", "Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "", "_onDialogClosed", "_onStoryOpenRequested", "", "isStarted", "", "onCreateButtonClicked", "Landroidx/lifecycle/LiveData;", "getOnCreateButtonClicked", "()Landroidx/lifecycle/LiveData;", "onDialogClosed", "getOnDialogClosed", "onStoryOpenRequested", "getOnStoryOpenRequested", "onBackButtonPressed", "onCreateStoryButtonPressed", "onStoryPreviewTapped1", "onStoryPreviewTapped2", "start", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class StoriesIntroViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper = null;
    private final org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper = null;
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<kotlin.Unit> _onDialogClosed = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<kotlin.Unit> onDialogClosed = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<kotlin.Unit> _onCreateButtonClicked = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<kotlin.Unit> onCreateButtonClicked = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<java.lang.String> _onStoryOpenRequested = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> onStoryOpenRequested = null;
    private boolean isStarted = false;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.stories.intro.StoriesIntroViewModel.Companion Companion = null;
    private static final java.lang.String STORY_URL_1 = "https://wpstories.wordpress.com/2020/12/02/story-demo-01/";
    private static final java.lang.String STORY_URL_2 = "https://wpstories.wordpress.com/2020/12/02/story-demo-02/";
    private static final java.lang.String STORY_FULLSCREEN_URL_PARAMS = "?wp-story-load-in-fullscreen=true&wp-story-play-on-load=true";
    
    @javax.inject.Inject()
    public StoriesIntroViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTrackerWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Unit> getOnDialogClosed() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Unit> getOnCreateButtonClicked() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getOnStoryOpenRequested() {
        return null;
    }
    
    public final void start() {
    }
    
    public final void onBackButtonPressed() {
    }
    
    public final void onCreateStoryButtonPressed() {
    }
    
    public final void onStoryPreviewTapped1() {
    }
    
    public final void onStoryPreviewTapped2() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/stories/intro/StoriesIntroViewModel$Companion;", "", "()V", "STORY_FULLSCREEN_URL_PARAMS", "", "STORY_URL_1", "STORY_URL_2", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}