package org.wordpress.android.ui.reader.subfilter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00c0\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\u0018\u0000 f2\u00020\u0001:\u0001fBC\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u000fJ\"\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020\u00172\u0006\u0010B\u001a\u0002002\b\u0010C\u001a\u0004\u0018\u000108H\u0002J\b\u0010D\u001a\u00020EH\u0002J\u0006\u0010F\u001a\u00020\u0017J\u0010\u0010G\u001a\u00020@2\u0006\u0010H\u001a\u000200H\u0007J\u0006\u0010I\u001a\u00020@J\u000e\u0010J\u001a\u00020@2\u0006\u0010K\u001a\u00020\u0013J\u0006\u0010L\u001a\u00020@J\b\u0010M\u001a\u00020@H\u0014J\u0010\u0010N\u001a\u00020@2\u0006\u0010O\u001a\u00020PH\u0007J\u0010\u0010N\u001a\u00020@2\u0006\u0010O\u001a\u00020QH\u0007J\u000e\u0010R\u001a\u00020@2\u0006\u0010S\u001a\u00020TJ\u0006\u0010U\u001a\u00020@J\u0010\u0010V\u001a\u00020@2\u0006\u0010W\u001a\u00020\u0017H\u0002J\u0018\u0010X\u001a\u00020@2\u0006\u0010Y\u001a\u00020\u001a2\u0006\u0010Z\u001a\u00020\u001bH\u0007J\u0006\u0010[\u001a\u00020@J\u000e\u0010\\\u001a\u00020@2\u0006\u0010A\u001a\u00020\u0017J\u0006\u0010]\u001a\u00020@J\u0006\u0010^\u001a\u00020@J\u000e\u0010_\u001a\u00020@2\u0006\u0010`\u001a\u000208J$\u0010a\u001a\u00020@2\b\u00107\u001a\u0004\u0018\u0001082\b\u0010`\u001a\u0004\u0018\u0001082\b\u0010b\u001a\u0004\u0018\u00010TJ\u000e\u0010c\u001a\u00020@2\u0006\u0010d\u001a\u00020EJ\u0010\u0010e\u001a\u00020@2\u0006\u0010W\u001a\u00020\u0017H\u0002R\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00120\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0018\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b0\u00190\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170 0\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010!\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\"0\u00120\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120%\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\'R\u001d\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00120%\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\'R\u0017\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00170%\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010\'R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R3\u0010,\u001a$\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b0\u0019j\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b`-0%\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010\'R\u000e\u0010/\u001a\u000200X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00101\u001a\u000200X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u00102\u001a\u0004\u0018\u000103X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u00104R\u0012\u00105\u001a\u0004\u0018\u000100X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u00106R\u0010\u00107\u001a\u0004\u0018\u000108X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u00109\u001a\b\u0012\u0004\u0012\u00020\u001e0%\u00a2\u0006\b\n\u0000\u001a\u0004\b:\u0010\'R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010;\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170 0%\u00a2\u0006\b\n\u0000\u001a\u0004\b<\u0010\'R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010=\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\"0\u00120%\u00a2\u0006\b\n\u0000\u001a\u0004\b>\u0010\'\u00a8\u0006g"}, d2 = {"Lorg/wordpress/android/ui/reader/subfilter/SubFilterViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "bgDispatcher", "appPrefsWrapper", "Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;", "subfilterListItemMapper", "Lorg/wordpress/android/ui/reader/subfilter/SubfilterListItemMapper;", "eventBusWrapper", "Lorg/wordpress/android/util/EventBusWrapper;", "accountStore", "Lorg/wordpress/android/fluxc/store/AccountStore;", "readerTracker", "Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/ui/prefs/AppPrefsWrapper;Lorg/wordpress/android/ui/reader/subfilter/SubfilterListItemMapper;Lorg/wordpress/android/util/EventBusWrapper;Lorg/wordpress/android/fluxc/store/AccountStore;Lorg/wordpress/android/ui/reader/tracker/ReaderTracker;)V", "_bottomSheetEmptyViewAction", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/reader/subfilter/ActionType;", "_bottomSheetUiState", "Lorg/wordpress/android/ui/reader/subfilter/BottomSheetUiState;", "_currentSubFilter", "Lorg/wordpress/android/ui/reader/subfilter/SubfilterListItem;", "_filtersMatchCount", "Ljava/util/HashMap;", "Lorg/wordpress/android/ui/reader/subfilter/SubfilterCategory;", "", "_readerModeInfo", "Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "Lorg/wordpress/android/ui/reader/viewmodels/ReaderModeInfo;", "_subFilters", "", "_updateTagsAndSites", "Ljava/util/EnumSet;", "Lorg/wordpress/android/ui/reader/services/update/ReaderUpdateLogic$UpdateTask;", "bottomSheetEmptyViewAction", "Landroidx/lifecycle/LiveData;", "getBottomSheetEmptyViewAction", "()Landroidx/lifecycle/LiveData;", "bottomSheetUiState", "getBottomSheetUiState", "currentSubFilter", "getCurrentSubFilter", "filtersMatchCount", "Lkotlin/collections/HashMap;", "getFiltersMatchCount", "isFirstLoad", "", "isStarted", "lastKnownUserId", "", "Ljava/lang/Long;", "lastTokenAvailableStatus", "Ljava/lang/Boolean;", "mTagFragmentStartedWith", "Lorg/wordpress/android/models/ReaderTag;", "readerModeInfo", "getReaderModeInfo", "subFilters", "getSubFilters", "updateTagsAndSites", "getUpdateTagsAndSites", "changeSubfilter", "", "subfilterListItem", "requestNewerPosts", "streamTag", "getCurrentSubfilterJson", "", "getCurrentSubfilterValue", "initSubfiltersTracking", "show", "loadSubFilters", "onBottomSheetActionClicked", "action", "onBottomSheetCancelled", "onCleared", "onEventMainThread", "event", "Lorg/wordpress/android/ui/reader/ReaderEvents$FollowedBlogsChanged;", "Lorg/wordpress/android/ui/reader/ReaderEvents$FollowedTagsChanged;", "onSaveInstanceState", "outState", "Landroid/os/Bundle;", "onSubFiltersListButtonClicked", "onSubfilterClicked", "filter", "onSubfilterPageUpdated", "category", "count", "onSubfilterReselected", "onSubfilterSelected", "onUserComesToReader", "setDefaultSubfilter", "setSubfilterFromTag", "tag", "start", "savedInstanceState", "trackOnPageSelected", "tab", "updateSubfilter", "Companion", "org.wordpress.android_wordpressJalapenoDebug"})
public final class SubFilterViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper = null;
    private final org.wordpress.android.ui.reader.subfilter.SubfilterListItemMapper subfilterListItemMapper = null;
    private final org.wordpress.android.util.EventBusWrapper eventBusWrapper = null;
    private final org.wordpress.android.fluxc.store.AccountStore accountStore = null;
    private final org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<org.wordpress.android.ui.reader.subfilter.SubfilterListItem>> _subFilters = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.ui.reader.subfilter.SubfilterListItem>> subFilters = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.reader.subfilter.SubfilterListItem> _currentSubFilter = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.reader.subfilter.SubfilterListItem> currentSubFilter = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<org.wordpress.android.ui.reader.viewmodels.ReaderModeInfo> _readerModeInfo = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.reader.viewmodels.ReaderModeInfo> readerModeInfo = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.reader.subfilter.BottomSheetUiState>> _bottomSheetUiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.reader.subfilter.BottomSheetUiState>> bottomSheetUiState = null;
    private final androidx.lifecycle.MutableLiveData<java.util.HashMap<org.wordpress.android.ui.reader.subfilter.SubfilterCategory, java.lang.Integer>> _filtersMatchCount = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.HashMap<org.wordpress.android.ui.reader.subfilter.SubfilterCategory, java.lang.Integer>> filtersMatchCount = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.reader.subfilter.ActionType>> _bottomSheetEmptyViewAction = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.reader.subfilter.ActionType>> bottomSheetEmptyViewAction = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<java.util.EnumSet<org.wordpress.android.ui.reader.services.update.ReaderUpdateLogic.UpdateTask>>> _updateTagsAndSites = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.util.EnumSet<org.wordpress.android.ui.reader.services.update.ReaderUpdateLogic.UpdateTask>>> updateTagsAndSites = null;
    private java.lang.Long lastKnownUserId;
    private java.lang.Boolean lastTokenAvailableStatus;
    private boolean isStarted = false;
    private boolean isFirstLoad = true;
    private org.wordpress.android.models.ReaderTag mTagFragmentStartedWith;
    @org.jetbrains.annotations.NotNull()
    public static final org.wordpress.android.ui.reader.subfilter.SubFilterViewModel.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SUBFILTER_VM_BASE_KEY = "SUBFILTER_VIEW_MODEL_BASE_KEY";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ARG_CURRENT_SUBFILTER_JSON = "current_subfilter_json";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ARG_IS_FIRST_LOAD = "is_first_load";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TRACK_TAB = "tab";
    
    @javax.inject.Inject()
    public SubFilterViewModel(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.prefs.AppPrefsWrapper appPrefsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.subfilter.SubfilterListItemMapper subfilterListItemMapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.EventBusWrapper eventBusWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.AccountStore accountStore, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.tracker.ReaderTracker readerTracker) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<org.wordpress.android.ui.reader.subfilter.SubfilterListItem>> getSubFilters() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.reader.subfilter.SubfilterListItem> getCurrentSubFilter() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.reader.viewmodels.ReaderModeInfo> getReaderModeInfo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.reader.subfilter.BottomSheetUiState>> getBottomSheetUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.HashMap<org.wordpress.android.ui.reader.subfilter.SubfilterCategory, java.lang.Integer>> getFiltersMatchCount() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.reader.subfilter.ActionType>> getBottomSheetEmptyViewAction() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<java.util.EnumSet<org.wordpress.android.ui.reader.services.update.ReaderUpdateLogic.UpdateTask>>> getUpdateTagsAndSites() {
        return null;
    }
    
    /**
     * Tag may be null for Blog previews for instance.
     */
    public final void start(@org.jetbrains.annotations.Nullable()
    org.wordpress.android.models.ReaderTag mTagFragmentStartedWith, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.models.ReaderTag tag, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    public final void loadSubFilters() {
    }
    
    private final void onSubfilterClicked(org.wordpress.android.ui.reader.subfilter.SubfilterListItem filter) {
    }
    
    @androidx.annotation.VisibleForTesting(otherwise = androidx.annotation.VisibleForTesting.PRIVATE)
    public final void initSubfiltersTracking(boolean show) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.ui.reader.subfilter.SubfilterListItem getCurrentSubfilterValue() {
        return null;
    }
    
    private final java.lang.String getCurrentSubfilterJson() {
        return null;
    }
    
    public final void setSubfilterFromTag(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.models.ReaderTag tag) {
    }
    
    public final void setDefaultSubfilter() {
    }
    
    public final void onSubFiltersListButtonClicked() {
    }
    
    public final void onBottomSheetCancelled() {
    }
    
    private final void changeSubfilter(org.wordpress.android.ui.reader.subfilter.SubfilterListItem subfilterListItem, boolean requestNewerPosts, org.wordpress.android.models.ReaderTag streamTag) {
    }
    
    public final void onSubfilterSelected(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.subfilter.SubfilterListItem subfilterListItem) {
    }
    
    public final void onSubfilterReselected() {
    }
    
    @android.annotation.SuppressLint(value = {"NullSafeMutableLiveData"})
    public final void onSubfilterPageUpdated(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.subfilter.SubfilterCategory category, int count) {
    }
    
    public final void onBottomSheetActionClicked(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.subfilter.ActionType action) {
    }
    
    private final void updateSubfilter(org.wordpress.android.ui.reader.subfilter.SubfilterListItem filter) {
    }
    
    public final void onUserComesToReader() {
    }
    
    public final void trackOnPageSelected(@org.jetbrains.annotations.NotNull()
    java.lang.String tab) {
    }
    
    public final void onSaveInstanceState(@org.jetbrains.annotations.NotNull()
    android.os.Bundle outState) {
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onEventMainThread(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.ReaderEvents.FollowedTagsChanged event) {
    }
    
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.MAIN)
    public final void onEventMainThread(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.ReaderEvents.FollowedBlogsChanged event) {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lorg/wordpress/android/ui/reader/subfilter/SubFilterViewModel$Companion;", "", "()V", "ARG_CURRENT_SUBFILTER_JSON", "", "ARG_IS_FIRST_LOAD", "SUBFILTER_VM_BASE_KEY", "TRACK_TAB", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}