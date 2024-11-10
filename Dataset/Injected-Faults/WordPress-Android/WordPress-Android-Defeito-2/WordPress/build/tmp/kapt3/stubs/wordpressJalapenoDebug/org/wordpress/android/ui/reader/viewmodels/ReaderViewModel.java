package org.wordpress.android.ui.reader.viewmodels;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00a6\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u0000 f2\u00020\u0001:\u0003fghB[\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u00a2\u0006\u0002\u0010\u0015J\b\u0010<\u001a\u00020\u0019H\u0002J\u0006\u0010=\u001a\u00020\u0019J\b\u0010>\u001a\u00020\u0019H\u0002J\u0006\u0010?\u001a\u00020\u0019J\u0006\u0010@\u001a\u00020\u0019J\b\u0010A\u001a\u00020BH\u0002J\b\u0010C\u001a\u00020\u0019H\u0002J\u0019\u0010D\u001a\u00020\u00192\u0006\u0010E\u001a\u00020FH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010GJ\b\u0010H\u001a\u00020)H\u0002J\b\u0010I\u001a\u00020)H\u0002J\b\u0010J\u001a\u00020\u0019H\u0002J\b\u0010K\u001a\u00020\u0019H\u0014J\u0006\u0010L\u001a\u00020\u0019J\u000e\u0010M\u001a\u00020\u00192\u0006\u0010N\u001a\u00020OJ\u0006\u0010P\u001a\u00020\u0019J\u000e\u0010Q\u001a\u00020\u00192\u0006\u0010R\u001a\u00020)J\u0006\u0010S\u001a\u00020\u0019J\u0006\u0010T\u001a\u00020\u0019J\u0006\u0010U\u001a\u00020\u0019J\u0006\u0010V\u001a\u00020\u0019J\u0010\u0010W\u001a\u00020\u00192\b\u0010X\u001a\u0004\u0018\u00010YJ\u0010\u0010Z\u001a\u00020\u00192\u0006\u0010N\u001a\u00020[H\u0007J\u000e\u0010\\\u001a\u00020\u00192\u0006\u0010]\u001a\u00020YJ\u0006\u0010^\u001a\u00020\u0019J\b\u0010_\u001a\u00020\u0019H\u0002J\b\u0010`\u001a\u00020)H\u0002J\u0010\u0010a\u001a\u00020\u00192\u0006\u0010b\u001a\u00020YH\u0002J\u0010\u0010c\u001a\u00020\u00192\u0006\u0010d\u001a\u00020)H\u0002J\f\u0010e\u001a\u00020)*\u00020YH\u0002R\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u00180\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u00180\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180%\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\'R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020)X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u00180%\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\'R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u00180%\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010\'R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180%\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010\'R\u001d\u00101\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180%\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010\'R\u001d\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180%\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010\'R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u00105\u001a\u0004\u0018\u000106X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u00107\u001a\b\u0012\u0004\u0012\u00020\"0%\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010\'R\u001d\u00109\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180%\u00a2\u0006\b\n\u0000\u001a\u0004\b:\u0010\'R\u000e\u0010;\u001a\u00020)X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006i"}, d2 = {"Lorg/wordpress/android/ui/reader/viewmodels/ReaderViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "bgDispatcher", "appPrefsWrapper", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "dateProvider", "Lorg/wordpress/android/ui/reader/utils/DateProvider;", "loadReaderTabsUseCase", "Lorg/wordpress/android/ui/reader/usecases/LoadReaderTabsUseCase;", "readerTracker", "Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "quickStartRepository", "Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartRepository;", "selectedSiteRepository", "Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;", "snackbarSequencer", "Lorg/wordpress/android/util/SnackbarSequencer;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;Lorg/wordpress/android/ui/reader/utils/DateProvider;Lorg/wordpress/android/ui/reader/usecases/LoadReaderTabsUseCase;Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;Lorg/wordpress/android/fluxc/store/AccountStore;Lorg/wordpress/android/ui/mysite/cards/quickstart/QuickStartRepository;Lorg/wordpress/android/ui/mysite/SelectedSiteRepository;Lorg/wordpress/android/util/SnackbarSequencer;)V", "_closeReaderInterests", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "", "_quickStartPromptEvent", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderViewModel$QuickStartReaderPrompt;", "_selectTab", "Lorg/wordpress/android/ui/reader/viewmodels/TabNavigation;", "_showReaderInterests", "_showSearch", "_showSettings", "_uiState", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderViewModel$ReaderUiState;", "_updateTags", "closeReaderInterests", "Landroidx/lifecycle/LiveData;", "getCloseReaderInterests", "()Landroidx/lifecycle/LiveData;", "initialized", "", "isQuickStartPromptShown", "quickStartPromptEvent", "getQuickStartPromptEvent", "selectTab", "getSelectTab", "showReaderInterests", "getShowReaderInterests", "showSearch", "getShowSearch", "showSettings", "getShowSettings", "trackReaderTabJob", "Lkotlinx/coroutines/Job;", "uiState", "getUiState", "updateTags", "getUpdateTags", "wasPaused", "autoSwitchToDiscoverTab", "bookmarkTabRequested", "checkAndStartQuickStartFollowSiteTaskNextStep", "completeQuickStartFollowSiteTaskIfNeeded", "dismissQuickStartSnackbarIfNeeded", "getFollowSiteTask", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;", "hideQuickStartFocusPointIfNeeded", "initializeTabSelection", "tagList", "Lorg/wordpress/android/models/ReaderTagList;", "(Lorg/wordpress/android/models/ReaderTagList;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isSearchSupported", "isSettingsSupported", "loadTabs", "onCleared", "onCloseReaderInterests", "onQuickStartEventReceived", "event", "Lorg/wordpress/android/ui/quickstart/QuickStartEvent;", "onQuickStartPromptDismissed", "onScreenInBackground", "isChangingConfigurations", "onScreenInForeground", "onSearchActionClicked", "onSettingsActionClicked", "onShowReaderInterests", "onTagChanged", "selectedTag", "Lorg/wordpress/android/models/ReaderTag;", "onTagsUpdated", "Lorg/wordpress/android/ui/reader/ReaderEvents$FollowedTagsChanged;", "selectedTabChange", "tag", "start", "startQuickStartFollowSiteTaskDiscoverTabStep", "tagsRequireUpdate", "trackReaderTabShownIfNecessary", "it", "updateContentUiState", "showQuickStartFocusPoint", "isDefaultSelectedTab", "Companion", "QuickStartReaderPrompt", "ReaderUiState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class ReaderViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper = null;
    private final org.wordpress.android.ui.reader.utils.DateProvider dateProvider = null;
    private final org.wordpress.android.ui.reader.usecases.LoadReaderTabsUseCase loadReaderTabsUseCase = null;
    private final org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker = null;
    private final org.wordpress.android.fluxc.store.AccountStore accountStore = null;
    private final org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository quickStartRepository = null;
    private final org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository = null;
    private final org.wordpress.android.util.SnackbarSequencer snackbarSequencer = null;
    private boolean initialized = false;
    private boolean wasPaused = false;
    private kotlinx.coroutines.Job trackReaderTabJob;
    private boolean isQuickStartPromptShown = false;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.reader.viewmodels.ReaderViewModel.ReaderUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.reader.viewmodels.ReaderViewModel.ReaderUiState> uiState = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> _updateTags = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> updateTags = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.reader.viewmodels.TabNavigation>> _selectTab = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.reader.viewmodels.TabNavigation>> selectTab = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> _showSearch = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> showSearch = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> _showSettings = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> showSettings = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> _showReaderInterests = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> showReaderInterests = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> _closeReaderInterests = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> closeReaderInterests = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.reader.viewmodels.ReaderViewModel.QuickStartReaderPrompt>> _quickStartPromptEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.reader.viewmodels.ReaderViewModel.QuickStartReaderPrompt>> quickStartPromptEvent = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.reader.viewmodels.ReaderViewModel.Companion Companion = null;
    private static final long QUICK_START_DISCOVER_TAB_STEP_DELAY = 2000L;
    private static final int QUICK_START_PROMPT_DURATION = 5000;
    
    @javax.inject.Inject()
    public ReaderViewModel(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.utils.DateProvider dateProvider, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.usecases.LoadReaderTabsUseCase loadReaderTabsUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore accountStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository quickStartRepository, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.mysite.SelectedSiteRepository selectedSiteRepository, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.SnackbarSequencer snackbarSequencer) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.reader.viewmodels.ReaderViewModel.ReaderUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> getUpdateTags() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.reader.viewmodels.TabNavigation>> getSelectTab() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> getShowSearch() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> getShowSettings() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> getShowReaderInterests() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<kotlin.Unit>> getCloseReaderInterests() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.reader.viewmodels.ReaderViewModel.QuickStartReaderPrompt>> getQuickStartPromptEvent() {
        return null;
    }
    
    public final void start() {
    }
    
    private final void loadTabs() {
    }
    
    private final java.lang.Object initializeTabSelection(org.wordpress.android.models.ReaderTagList tagList, kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    public final void onTagChanged(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.models.ReaderTag selectedTag) {
    }
    
    public final void onCloseReaderInterests() {
    }
    
    public final void onShowReaderInterests() {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    private final boolean tagsRequireUpdate() {
        return false;
    }
    
    public final void selectedTabChange(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderTag tag) {
    }
    
    public final void bookmarkTabRequested() {
    }
    
    public final void onSearchActionClicked() {
    }
    
    public final void onSettingsActionClicked() {
    }
    
    private final boolean isDefaultSelectedTab(org.wordpress.android.models.ReaderTag $this$isDefaultSelectedTab) {
        return false;
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onTagsUpdated(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.ReaderEvents.FollowedTagsChanged event) {
    }
    
    public final void onScreenInForeground() {
    }
    
    public final void onScreenInBackground(boolean isChangingConfigurations) {
    }
    
    private final boolean isSearchSupported() {
        return false;
    }
    
    private final boolean isSettingsSupported() {
        return false;
    }
    
    private final void trackReaderTabShownIfNecessary(org.wordpress.android.models.ReaderTag it) {
    }
    
    public final void onQuickStartPromptDismissed() {
    }
    
    public final void onQuickStartEventReceived(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.quickstart.QuickStartEvent event) {
    }
    
    private final void checkAndStartQuickStartFollowSiteTaskNextStep() {
    }
    
    private final void autoSwitchToDiscoverTab() {
    }
    
    private final void startQuickStartFollowSiteTaskDiscoverTabStep() {
    }
    
    public final void completeQuickStartFollowSiteTaskIfNeeded() {
    }
    
    public final void dismissQuickStartSnackbarIfNeeded() {
    }
    
    private final void hideQuickStartFocusPointIfNeeded() {
    }
    
    private final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask getFollowSiteTask() {
        return null;
    }
    
    private final void updateContentUiState(boolean showQuickStartFocusPoint) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0001\u000fB+\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n\u0082\u0001\u0001\u0010\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/reader/viewmodels/ReaderViewModel$ReaderUiState;", "", "searchMenuItemUiState", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderViewModel$ReaderUiState$ContentUiState$MenuItemUiState;", "settingsMenuItemUiState", "appBarExpanded", "", "tabLayoutVisible", "(Lorg/wordpress/android/ui/reader/viewmodels/ReaderViewModel$ReaderUiState$ContentUiState$MenuItemUiState;Lorg/wordpress/android/ui/reader/viewmodels/ReaderViewModel$ReaderUiState$ContentUiState$MenuItemUiState;ZZ)V", "getAppBarExpanded", "()Z", "getSearchMenuItemUiState", "()Lorg/wordpress/android/ui/reader/viewmodels/ReaderViewModel$ReaderUiState$ContentUiState$MenuItemUiState;", "getSettingsMenuItemUiState", "getTabLayoutVisible", "ContentUiState", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderViewModel$ReaderUiState$ContentUiState;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class ReaderUiState {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.reader.viewmodels.ReaderViewModel.ReaderUiState.ContentUiState.MenuItemUiState searchMenuItemUiState = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.reader.viewmodels.ReaderViewModel.ReaderUiState.ContentUiState.MenuItemUiState settingsMenuItemUiState = null;
        private final boolean appBarExpanded = false;
        private final boolean tabLayoutVisible = false;
        
        private ReaderUiState(org.wordpress.android.ui.reader.viewmodels.ReaderViewModel.ReaderUiState.ContentUiState.MenuItemUiState searchMenuItemUiState, org.wordpress.android.ui.reader.viewmodels.ReaderViewModel.ReaderUiState.ContentUiState.MenuItemUiState settingsMenuItemUiState, boolean appBarExpanded, boolean tabLayoutVisible) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public org.wordpress.android.ui.reader.viewmodels.ReaderViewModel.ReaderUiState.ContentUiState.MenuItemUiState getSearchMenuItemUiState() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public org.wordpress.android.ui.reader.viewmodels.ReaderViewModel.ReaderUiState.ContentUiState.MenuItemUiState getSettingsMenuItemUiState() {
            return null;
        }
        
        public final boolean getAppBarExpanded() {
            return false;
        }
        
        public final boolean getTabLayoutVisible() {
            return false;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u0002#$B3\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0002\u0010\fJ\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\bH\u00c6\u0003J\t\u0010\u0019\u001a\u00020\nH\u00c6\u0003J\t\u0010\u001a\u001a\u00020\nH\u00c6\u0003JA\u0010\u001b\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\nH\u00c6\u0001J\u0013\u0010\u001c\u001a\u00020\b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u00d6\u0003J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001J\t\u0010!\u001a\u00020\"H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\t\u001a\u00020\nX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u000b\u001a\u00020\nX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006%"}, d2 = {"Lorg/wordpress/android/ui/reader/viewmodels/ReaderViewModel$ReaderUiState$ContentUiState;", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderViewModel$ReaderUiState;", "tabUiStates", "", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderViewModel$ReaderUiState$ContentUiState$TabUiState;", "readerTagList", "Lorg/wordpress/android/models/ReaderTagList;", "shouldUpdateViewPager", "", "searchMenuItemUiState", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderViewModel$ReaderUiState$ContentUiState$MenuItemUiState;", "settingsMenuItemUiState", "(Ljava/util/List;Lorg/wordpress/android/models/ReaderTagList;ZLorg/wordpress/android/ui/reader/viewmodels/ReaderViewModel$ReaderUiState$ContentUiState$MenuItemUiState;Lorg/wordpress/android/ui/reader/viewmodels/ReaderViewModel$ReaderUiState$ContentUiState$MenuItemUiState;)V", "getReaderTagList", "()Lorg/wordpress/android/models/ReaderTagList;", "getSearchMenuItemUiState", "()Lorg/wordpress/android/ui/reader/viewmodels/ReaderViewModel$ReaderUiState$ContentUiState$MenuItemUiState;", "getSettingsMenuItemUiState", "getShouldUpdateViewPager", "()Z", "getTabUiStates", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "", "hashCode", "", "toString", "", "MenuItemUiState", "TabUiState", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class ContentUiState extends org.wordpress.android.ui.reader.viewmodels.ReaderViewModel.ReaderUiState {
            @org.jetbrains.annotations.NotNull()
            private final java.util.List<org.wordpress.android.ui.reader.viewmodels.ReaderViewModel.ReaderUiState.ContentUiState.TabUiState> tabUiStates = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.models.ReaderTagList readerTagList = null;
            private final boolean shouldUpdateViewPager = false;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.reader.viewmodels.ReaderViewModel.ReaderUiState.ContentUiState.MenuItemUiState searchMenuItemUiState = null;
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.reader.viewmodels.ReaderViewModel.ReaderUiState.ContentUiState.MenuItemUiState settingsMenuItemUiState = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.viewmodels.ReaderViewModel.ReaderUiState.ContentUiState copy(@org.jetbrains.annotations.NotNull()
            java.util.List<org.wordpress.android.ui.reader.viewmodels.ReaderViewModel.ReaderUiState.ContentUiState.TabUiState> tabUiStates, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.models.ReaderTagList readerTagList, boolean shouldUpdateViewPager, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.reader.viewmodels.ReaderViewModel.ReaderUiState.ContentUiState.MenuItemUiState searchMenuItemUiState, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.reader.viewmodels.ReaderViewModel.ReaderUiState.ContentUiState.MenuItemUiState settingsMenuItemUiState) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            public ContentUiState(@org.jetbrains.annotations.NotNull()
            java.util.List<org.wordpress.android.ui.reader.viewmodels.ReaderViewModel.ReaderUiState.ContentUiState.TabUiState> tabUiStates, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.models.ReaderTagList readerTagList, boolean shouldUpdateViewPager, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.reader.viewmodels.ReaderViewModel.ReaderUiState.ContentUiState.MenuItemUiState searchMenuItemUiState, @org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.reader.viewmodels.ReaderViewModel.ReaderUiState.ContentUiState.MenuItemUiState settingsMenuItemUiState) {
                super(null, null, false, false);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.reader.viewmodels.ReaderViewModel.ReaderUiState.ContentUiState.TabUiState> component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.reader.viewmodels.ReaderViewModel.ReaderUiState.ContentUiState.TabUiState> getTabUiStates() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.models.ReaderTagList component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.models.ReaderTagList getReaderTagList() {
                return null;
            }
            
            public final boolean component3() {
                return false;
            }
            
            public final boolean getShouldUpdateViewPager() {
                return false;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.viewmodels.ReaderViewModel.ReaderUiState.ContentUiState.MenuItemUiState component4() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.reader.viewmodels.ReaderViewModel.ReaderUiState.ContentUiState.MenuItemUiState getSearchMenuItemUiState() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.reader.viewmodels.ReaderViewModel.ReaderUiState.ContentUiState.MenuItemUiState component5() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public org.wordpress.android.ui.reader.viewmodels.ReaderViewModel.ReaderUiState.ContentUiState.MenuItemUiState getSettingsMenuItemUiState() {
                return null;
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\f\u001a\u00020\rH\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/reader/viewmodels/ReaderViewModel$ReaderUiState$ContentUiState$TabUiState;", "", "label", "Lorg/wordpress/android/ui/utils/UiString;", "(Lorg/wordpress/android/ui/utils/UiString;)V", "getLabel", "()Lorg/wordpress/android/ui/utils/UiString;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class TabUiState {
                @org.jetbrains.annotations.NotNull()
                private final org.wordpress.android.ui.utils.UiString label = null;
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.reader.viewmodels.ReaderViewModel.ReaderUiState.ContentUiState.TabUiState copy(@org.jetbrains.annotations.NotNull()
                org.wordpress.android.ui.utils.UiString label) {
                    return null;
                }
                
                @java.lang.Override()
                public boolean equals(@org.jetbrains.annotations.Nullable()
                java.lang.Object other) {
                    return false;
                }
                
                @java.lang.Override()
                public int hashCode() {
                    return 0;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public java.lang.String toString() {
                    return null;
                }
                
                public TabUiState(@org.jetbrains.annotations.NotNull()
                org.wordpress.android.ui.utils.UiString label) {
                    super();
                }
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.utils.UiString component1() {
                    return null;
                }
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.utils.UiString getLabel() {
                    return null;
                }
            }
            
            @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/reader/viewmodels/ReaderViewModel$ReaderUiState$ContentUiState$MenuItemUiState;", "", "isVisible", "", "showQuickStartFocusPoint", "(ZZ)V", "()Z", "getShowQuickStartFocusPoint", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
            public static final class MenuItemUiState {
                private final boolean isVisible = false;
                private final boolean showQuickStartFocusPoint = false;
                
                @org.jetbrains.annotations.NotNull()
                public final org.wordpress.android.ui.reader.viewmodels.ReaderViewModel.ReaderUiState.ContentUiState.MenuItemUiState copy(boolean isVisible, boolean showQuickStartFocusPoint) {
                    return null;
                }
                
                @java.lang.Override()
                public boolean equals(@org.jetbrains.annotations.Nullable()
                java.lang.Object other) {
                    return false;
                }
                
                @java.lang.Override()
                public int hashCode() {
                    return 0;
                }
                
                @org.jetbrains.annotations.NotNull()
                @java.lang.Override()
                public java.lang.String toString() {
                    return null;
                }
                
                public MenuItemUiState(boolean isVisible, boolean showQuickStartFocusPoint) {
                    super();
                }
                
                public final boolean component1() {
                    return false;
                }
                
                public final boolean isVisible() {
                    return false;
                }
                
                public final boolean component2() {
                    return false;
                }
                
                public final boolean getShowQuickStartFocusPoint() {
                    return false;
                }
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0005H\u00c6\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0017\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001R\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u001a"}, d2 = {"Lorg/wordpress/android/ui/reader/viewmodels/ReaderViewModel$QuickStartReaderPrompt;", "", "task", "Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;", "shortMessagePrompt", "", "iconId", "duration", "(Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;III)V", "getDuration", "()I", "getIconId", "getShortMessagePrompt", "getTask", "()Lorg/wordpress/android/fluxc/store/QuickStartStore$QuickStartTask;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class QuickStartReaderPrompt {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task = null;
        private final int shortMessagePrompt = 0;
        private final int iconId = 0;
        private final int duration = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.reader.viewmodels.ReaderViewModel.QuickStartReaderPrompt copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task, @androidx.annotation.StringRes()
        int shortMessagePrompt, @androidx.annotation.DrawableRes()
        int iconId, int duration) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public QuickStartReaderPrompt(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask task, @androidx.annotation.StringRes()
        int shortMessagePrompt, @androidx.annotation.DrawableRes()
        int iconId, int duration) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.store.QuickStartStore.QuickStartTask getTask() {
            return null;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int getShortMessagePrompt() {
            return 0;
        }
        
        public final int component3() {
            return 0;
        }
        
        public final int getIconId() {
            return 0;
        }
        
        public final int component4() {
            return 0;
        }
        
        public final int getDuration() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/reader/viewmodels/ReaderViewModel$Companion;", "", "()V", "QUICK_START_DISCOVER_TAB_STEP_DELAY", "", "QUICK_START_PROMPT_DURATION", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}