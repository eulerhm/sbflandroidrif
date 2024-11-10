package org.wordpress.android.ui.bloggingprompts.onboarding;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B9\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\b\u0001\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\b\u0010%\u001a\u00020&H\u0002J\b\u0010\'\u001a\u00020(H\u0002J\u000e\u0010)\u001a\u00020(2\u0006\u0010*\u001a\u00020+J\u000e\u0010,\u001a\u00020(2\u0006\u0010-\u001a\u00020\u001eR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00110\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001aR\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00160\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001aR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006."}, d2 = {"Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "siteStore", "Lorg/wordpress/android/fluxc/store/SiteStore;", "uiStateMapper", "Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingUiStateMapper;", "selectedSiteRepository", "Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;", "bloggingPromptsStore", "Lorg/wordpress/android/fluxc/store/bloggingprompts/BloggingPromptsStore;", "analyticsTracker", "Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingAnalyticsTracker;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lorg/wordpress/android/fluxc/store/SiteStore;Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingUiStateMapper;Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;Lorg/wordpress/android/fluxc/store/bloggingprompts/BloggingPromptsStore;Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingAnalyticsTracker;Lkotlinx/coroutines/CoroutineDispatcher;)V", "_action", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingAction;", "_snackBarMessage", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "_uiState", "Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingUiState;", "action", "Landroidx/lifecycle/LiveData;", "getAction", "()Landroidx/lifecycle/LiveData;", "getBgDispatcher", "()Lkotlinx/coroutines/CoroutineDispatcher;", "dialogType", "Lorg/wordpress/android/ui/bloggingprompts/onboarding/BloggingPromptsOnboardingDialogFragment$DialogType;", "hasTrackedScreenShown", "", "snackBarMessage", "getSnackBarMessage", "uiState", "getUiState", "onPrimaryButtonClick", "Lkotlinx/coroutines/Job;", "onSecondaryButtonClick", "", "onSiteSelected", "selectedSiteLocalId", "", "start", "type", "org.wordpress.android_wordpressJalapenoDebug"})
public final class BloggingPromptsOnboardingViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final org.wordpress.android.fluxc.store.SiteStore siteStore = null;
    private final org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingUiStateMapper uiStateMapper = null;
    private final org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository = null;
    private final org.wordpress.android.fluxc.store.bloggingprompts.BloggingPromptsStore bloggingPromptsStore = null;
    private final org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingAnalyticsTracker analyticsTracker = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingUiState> uiState = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingAction> _action = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingAction> action = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> _snackBarMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> snackBarMessage = null;
    private org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingDialogFragment.DialogType dialogType;
    private boolean hasTrackedScreenShown = false;
    
    @javax.inject.Inject()
    public BloggingPromptsOnboardingViewModel(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.SiteStore siteStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingUiStateMapper uiStateMapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.bloggingprompts.BloggingPromptsStore bloggingPromptsStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingAnalyticsTracker analyticsTracker, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.CoroutineDispatcher getBgDispatcher() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingAction> getAction() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> getSnackBarMessage() {
        return null;
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.bloggingprompts.onboarding.BloggingPromptsOnboardingDialogFragment.DialogType type) {
    }
    
    private final kotlinx.coroutines.Job onPrimaryButtonClick() {
        return null;
    }
    
    private final void onSecondaryButtonClick() {
    }
    
    public final void onSiteSelected(int selectedSiteLocalId) {
    }
}