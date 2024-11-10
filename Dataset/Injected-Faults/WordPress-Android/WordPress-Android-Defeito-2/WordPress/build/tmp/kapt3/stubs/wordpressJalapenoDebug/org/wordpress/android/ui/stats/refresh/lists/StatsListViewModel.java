package org.wordpress.android.ui.stats.refresh.lists;

import java.lang.System;

@kotlin.Suppress(names = {"TooManyFunctions", "LongParameterList"})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\b&\u0018\u00002\u00020\u0001:\u0002CDBI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u00a2\u0006\u0002\u0010\u0010J\u0006\u00106\u001a\u00020\u001bJ\b\u00107\u001a\u00020\u001bH\u0014J\u000e\u00108\u001a\u00020\u001b2\u0006\u00109\u001a\u00020:J\u0006\u0010;\u001a\u00020\u001bJ\u0006\u0010<\u001a\u00020\u001bJ\u0006\u0010=\u001a\u00020\u001bJ\u0006\u0010>\u001a\u00020\u001bJ\u0006\u0010?\u001a\u00020\u001bJ\u0006\u0010@\u001a\u00020\u001bJ\u0006\u0010A\u001a\u00020\u001bJ\u0006\u0010B\u001a\u00020\u001bR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0084\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0017R\u001a\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0\u001f0\u001eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0\u001f0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0017R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001f\u0010#\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0\u001f\u0018\u00010\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0017R\u001d\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0\u001f0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u0017R\u0019\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010)0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u0004\u0018\u00010,X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001d\u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0\u001f0.\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u00100R#\u00101\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001020\u00148FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b4\u00105\u001a\u0004\b3\u0010\u0017\u00a8\u0006E"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "defaultDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "statsUseCase", "Lorg/wordpress/android/ui/stats/refresh/lists/BaseListUseCase;", "analyticsTracker", "Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;", "dateSelector", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateSelector;", "popupMenuHandler", "Lorg/wordpress/android/ui/stats/refresh/utils/ItemPopupMenuHandler;", "newsCardHandler", "Lorg/wordpress/android/ui/stats/refresh/utils/NewsCardHandler;", "actionCardHandler", "Lorg/wordpress/android/ui/stats/refresh/utils/ActionCardHandler;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/ui/stats/refresh/lists/BaseListUseCase;Lorg/wordpress/android/util/analytics/AnalyticsTrackerWrapper;Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateSelector;Lorg/wordpress/android/ui/stats/refresh/utils/ItemPopupMenuHandler;Lorg/wordpress/android/ui/stats/refresh/utils/NewsCardHandler;Lorg/wordpress/android/ui/stats/refresh/utils/ActionCardHandler;)V", "getDateSelector", "()Lorg/wordpress/android/ui/stats/refresh/utils/StatsDateSelector;", "dateSelectorData", "Landroidx/lifecycle/LiveData;", "Lorg/wordpress/android/ui/stats/refresh/StatsViewModel$DateSelectorUiModel;", "getDateSelectorData", "()Landroidx/lifecycle/LiveData;", "isInitialized", "", "listSelected", "", "getListSelected", "mutableNavigationTarget", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/stats/refresh/NavigationTarget;", "navigationTarget", "getNavigationTarget", "scrollTo", "Lorg/wordpress/android/fluxc/store/StatsStore$StatsType;", "getScrollTo", "scrollToNewCard", "getScrollToNewCard", "selectedDate", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/SelectedDateProvider$SectionChange;", "getSelectedDate", "trackJob", "Lkotlinx/coroutines/Job;", "typesChanged", "Landroidx/lifecycle/MediatorLiveData;", "getTypesChanged", "()Landroidx/lifecycle/MediatorLiveData;", "uiModel", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$UiModel;", "getUiModel", "uiModel$delegate", "Lkotlin/Lazy;", "onAddNewStatsButtonClicked", "onCleared", "onDateChanged", "selectedSection", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$StatsSection;", "onEmptyInsightsButtonClicked", "onListSelected", "onNextDateSelected", "onPreviousDateSelected", "onRetryClick", "onScrolledToBottom", "onTypesChanged", "start", "StatsSection", "UiModel", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class StatsListViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase statsUseCase = null;
    private final org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker = null;
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.ui.stats.refresh.utils.StatsDateSelector dateSelector = null;
    private final org.wordpress.android.ui.stats.refresh.utils.NewsCardHandler newsCardHandler = null;
    private kotlinx.coroutines.Job trackJob;
    private boolean isInitialized = false;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider.SectionChange> selectedDate = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.stats.refresh.NavigationTarget>> mutableNavigationTarget = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.stats.refresh.NavigationTarget>> navigationTarget = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<kotlin.Unit> listSelected = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy uiModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.stats.refresh.StatsViewModel.DateSelectorUiModel> dateSelectorData = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.fluxc.store.StatsStore.StatsType>> typesChanged = null;
    @org.jetbrains.annotations.Nullable()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.fluxc.store.StatsStore.StatsType>> scrollTo = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.fluxc.store.StatsStore.StatsType>> scrollToNewCard = null;
    
    public StatsListViewModel(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineDispatcher defaultDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase statsUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsTrackerWrapper analyticsTracker, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsDateSelector dateSelector, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.stats.refresh.utils.ItemPopupMenuHandler popupMenuHandler, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.stats.refresh.utils.NewsCardHandler newsCardHandler, @org.jetbrains.annotations.Nullable()
    org.wordpress.android.ui.stats.refresh.utils.ActionCardHandler actionCardHandler) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final org.wordpress.android.ui.stats.refresh.utils.StatsDateSelector getDateSelector() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.stats.refresh.lists.sections.granular.SelectedDateProvider.SectionChange> getSelectedDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.stats.refresh.NavigationTarget>> getNavigationTarget() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Unit> getListSelected() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.UiModel> getUiModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.stats.refresh.StatsViewModel.DateSelectorUiModel> getDateSelectorData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.fluxc.store.StatsStore.StatsType>> getTypesChanged() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.fluxc.store.StatsStore.StatsType>> getScrollTo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.fluxc.store.StatsStore.StatsType>> getScrollToNewCard() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    public final void onScrolledToBottom() {
    }
    
    public final void onNextDateSelected() {
    }
    
    public final void onPreviousDateSelected() {
    }
    
    public final void onRetryClick() {
    }
    
    public final void onDateChanged(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection selectedSection) {
    }
    
    public final void onListSelected() {
    }
    
    public final void onEmptyInsightsButtonClicked() {
    }
    
    public final void onAddNewStatsButtonClicked() {
    }
    
    public final void start() {
    }
    
    public final void onTypesChanged() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$StatsSection;", "", "titleRes", "", "(Ljava/lang/String;II)V", "getTitleRes", "()I", "INSIGHTS", "DAYS", "WEEKS", "MONTHS", "YEARS", "DETAIL", "INSIGHT_DETAIL", "TOTAL_LIKES_DETAIL", "TOTAL_COMMENTS_DETAIL", "TOTAL_FOLLOWERS_DETAIL", "ANNUAL_STATS", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum StatsSection {
        /*public static final*/ INSIGHTS /* = new INSIGHTS(0) */,
        /*public static final*/ DAYS /* = new DAYS(0) */,
        /*public static final*/ WEEKS /* = new WEEKS(0) */,
        /*public static final*/ MONTHS /* = new MONTHS(0) */,
        /*public static final*/ YEARS /* = new YEARS(0) */,
        /*public static final*/ DETAIL /* = new DETAIL(0) */,
        /*public static final*/ INSIGHT_DETAIL /* = new INSIGHT_DETAIL(0) */,
        /*public static final*/ TOTAL_LIKES_DETAIL /* = new TOTAL_LIKES_DETAIL(0) */,
        /*public static final*/ TOTAL_COMMENTS_DETAIL /* = new TOTAL_COMMENTS_DETAIL(0) */,
        /*public static final*/ TOTAL_FOLLOWERS_DETAIL /* = new TOTAL_FOLLOWERS_DETAIL(0) */,
        /*public static final*/ ANNUAL_STATS /* = new ANNUAL_STATS(0) */;
        private final int titleRes = 0;
        
        StatsSection(@androidx.annotation.StringRes()
        int titleRes) {
        }
        
        public final int getTitleRes() {
            return 0;
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$UiModel;", "", "()V", "Empty", "Error", "Success", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$UiModel$Success;", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$UiModel$Error;", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$UiModel$Empty;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class UiModel {
        
        private UiModel() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$UiModel$Success;", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$UiModel;", "data", "", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsBlock;", "(Ljava/util/List;)V", "getData", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Success extends org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.UiModel {
            @org.jetbrains.annotations.NotNull()
            private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.StatsBlock> data = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.UiModel.Success copy(@org.jetbrains.annotations.NotNull()
            java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.StatsBlock> data) {
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
            
            public Success(@org.jetbrains.annotations.NotNull()
            java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.StatsBlock> data) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.StatsBlock> component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.StatsBlock> getData() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$UiModel$Error;", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$UiModel;", "message", "", "(I)V", "getMessage", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Error extends org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.UiModel {
            private final int message = 0;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.UiModel.Error copy(int message) {
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
            
            public Error() {
                super();
            }
            
            public Error(int message) {
                super();
            }
            
            public final int component1() {
                return 0;
            }
            
            public final int getMessage() {
                return 0;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\nJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0014\u001a\u00020\u0007H\u00c6\u0003J:\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u00072\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\u000e\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u001d"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$UiModel$Empty;", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$UiModel;", "title", "", "subtitle", "image", "showButton", "", "(ILjava/lang/Integer;Ljava/lang/Integer;Z)V", "getImage", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getShowButton", "()Z", "getSubtitle", "getTitle", "()I", "component1", "component2", "component3", "component4", "copy", "(ILjava/lang/Integer;Ljava/lang/Integer;Z)Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$UiModel$Empty;", "equals", "other", "", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Empty extends org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.UiModel {
            private final int title = 0;
            @org.jetbrains.annotations.Nullable()
            private final java.lang.Integer subtitle = null;
            @org.jetbrains.annotations.Nullable()
            private final java.lang.Integer image = null;
            private final boolean showButton = false;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.UiModel.Empty copy(int title, @org.jetbrains.annotations.Nullable()
            java.lang.Integer subtitle, @org.jetbrains.annotations.Nullable()
            java.lang.Integer image, boolean showButton) {
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
            
            public Empty(int title, @org.jetbrains.annotations.Nullable()
            java.lang.Integer subtitle, @org.jetbrains.annotations.Nullable()
            java.lang.Integer image, boolean showButton) {
                super();
            }
            
            public final int component1() {
                return 0;
            }
            
            public final int getTitle() {
                return 0;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Integer component2() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Integer getSubtitle() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Integer component3() {
                return null;
            }
            
            @org.jetbrains.annotations.Nullable()
            public final java.lang.Integer getImage() {
                return null;
            }
            
            public final boolean component4() {
                return false;
            }
            
            public final boolean getShowButton() {
                return false;
            }
        }
    }
}