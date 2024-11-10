package org.wordpress.android.ui.engagement;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00b0\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001JB;\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0002\u0010\rJ&\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+2\u0006\u0010-\u001a\u00020\u001c2\u0006\u0010.\u001a\u00020\u001c2\u0006\u0010/\u001a\u000200H\u0002J\u001c\u00101\u001a\u00020(2\b\u00102\u001a\u0004\u0018\u00010\u00182\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0002J\"\u00103\u001a\u0002042\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u00105\u001a\u00020\u001c2\u0006\u00106\u001a\u00020\u001cH\u0002J\b\u00107\u001a\u000204H\u0017J(\u00108\u001a\u0002042\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020:2\u0006\u0010>\u001a\u00020:H\u0002J\b\u0010?\u001a\u000204H\u0002J \u0010@\u001a\u0002042\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<2\u0006\u0010A\u001a\u00020<H\u0002J\u001a\u0010B\u001a\u0002042\u0006\u0010C\u001a\u00020D2\b\u0010A\u001a\u0004\u0018\u00010EH\u0002J(\u0010F\u001a\u0002042\u0006\u0010G\u001a\u00020H2\u0006\u00109\u001a\u00020:2\u0006\u0010=\u001a\u00020:2\u0006\u0010>\u001a\u00020:H\u0002J\u000e\u0010I\u001a\u0002042\u0006\u0010\u001d\u001a\u00020\u001eR\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00100\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100 \u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u001d\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00100 \u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\"R\u001d\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00100 \u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\"R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\'\u001a\b\u0012\u0004\u0012\u00020(0 \u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\"\u00a8\u0006K"}, d2 = {"Lorg/wordpress/android/ui/engagement/EngagedPeopleListViewModel;", "Lorg/wordpress/android/viewmodel/ScopedViewModel;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "bgDispatcher", "getLikesHandler", "Lorg/wordpress/android/ui/engagement/GetLikesHandler;", "readerUtilsWrapper", "Lorg/wordpress/android/ui/reader/utils/ReaderUtilsWrapper;", "engagementUtils", "Lorg/wordpress/android/ui/engagement/EngagementUtils;", "analyticsUtilsWrapper", "Lorg/wordpress/android/util/analytics/AnalyticsUtilsWrapper;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/ui/engagement/GetLikesHandler;Lorg/wordpress/android/ui/reader/utils/ReaderUtilsWrapper;Lorg/wordpress/android/ui/engagement/EngagementUtils;Lorg/wordpress/android/util/analytics/AnalyticsUtilsWrapper;)V", "_onNavigationEvent", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/engagement/EngagedListNavigationEvent;", "_onServiceRequestEvent", "Lorg/wordpress/android/ui/engagement/EngagedListServiceRequestEvent;", "_onSnackbarMessage", "Landroidx/lifecycle/MediatorLiveData;", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "_updateLikesState", "Lorg/wordpress/android/ui/engagement/GetLikesUseCase$GetLikesState;", "getLikesJob", "Lkotlinx/coroutines/Job;", "isStarted", "", "listScenario", "Lorg/wordpress/android/ui/engagement/ListScenario;", "onNavigationEvent", "Landroidx/lifecycle/LiveData;", "getOnNavigationEvent", "()Landroidx/lifecycle/LiveData;", "onServiceRequestEvent", "getOnServiceRequestEvent", "onSnackbarMessage", "getOnSnackbarMessage", "uiState", "Lorg/wordpress/android/ui/engagement/EngagedPeopleListViewModel$EngagedPeopleListUiState;", "getUiState", "appendNextPageLoaderIfNeeded", "", "Lorg/wordpress/android/ui/engagement/EngageItem;", "hasMore", "isLoading", "pageInfo", "Lorg/wordpress/android/ui/engagement/GetLikesUseCase$PagingInfo;", "buildUiState", "updateLikesState", "loadRequest", "", "requestPostOrComment", "requestNextPage", "onCleared", "onHeaderClicked", "siteId", "", "siteUrl", "", "postOrCommentId", "commentPostId", "onRefreshData", "onSiteLinkHolderClicked", "source", "onUserProfileHolderClicked", "userProfile", "Lorg/wordpress/android/ui/engagement/EngagedListNavigationEvent$OpenUserProfileBottomSheet$UserProfile;", "Lorg/wordpress/android/ui/engagement/EngagementNavigationSource;", "requestPostOrCommentIfNeeded", "listScenarioType", "Lorg/wordpress/android/ui/engagement/ListScenarioType;", "start", "EngagedPeopleListUiState", "org.wordpress.android_wordpressJalapenoDebug"})
public final class EngagedPeopleListViewModel extends org.wordpress.android.viewmodel.ScopedViewModel {
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final org.wordpress.android.ui.engagement.GetLikesHandler getLikesHandler = null;
    private final org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper readerUtilsWrapper = null;
    private final org.wordpress.android.ui.engagement.EngagementUtils engagementUtils = null;
    private final org.wordpress.android.util.analytics.AnalyticsUtilsWrapper analyticsUtilsWrapper = null;
    private boolean isStarted = false;
    private kotlinx.coroutines.Job getLikesJob;
    private org.wordpress.android.ui.engagement.ListScenario listScenario;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> _onSnackbarMessage = null;
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.engagement.GetLikesUseCase.GetLikesState> _updateLikesState = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.engagement.EngagedListNavigationEvent>> _onNavigationEvent = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.engagement.EngagedListServiceRequestEvent>> _onServiceRequestEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> onSnackbarMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.engagement.EngagedPeopleListViewModel.EngagedPeopleListUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.engagement.EngagedListNavigationEvent>> onNavigationEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.engagement.EngagedListServiceRequestEvent>> onServiceRequestEvent = null;
    
    @javax.inject.Inject()
    public EngagedPeopleListViewModel(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.engagement.GetLikesHandler getLikesHandler, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper readerUtilsWrapper, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.engagement.EngagementUtils engagementUtils, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.analytics.AnalyticsUtilsWrapper analyticsUtilsWrapper) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.pages.SnackbarMessageHolder>> getOnSnackbarMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.engagement.EngagedPeopleListViewModel.EngagedPeopleListUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.engagement.EngagedListNavigationEvent>> getOnNavigationEvent() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.engagement.EngagedListServiceRequestEvent>> getOnServiceRequestEvent() {
        return null;
    }
    
    public final void start(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.engagement.ListScenario listScenario) {
    }
    
    private final void onRefreshData() {
    }
    
    private final void requestPostOrCommentIfNeeded(org.wordpress.android.ui.engagement.ListScenarioType listScenarioType, long siteId, long postOrCommentId, long commentPostId) {
    }
    
    private final void loadRequest(org.wordpress.android.ui.engagement.ListScenario listScenario, boolean requestPostOrComment, boolean requestNextPage) {
    }
    
    private final org.wordpress.android.ui.engagement.EngagedPeopleListViewModel.EngagedPeopleListUiState buildUiState(org.wordpress.android.ui.engagement.GetLikesUseCase.GetLikesState updateLikesState, org.wordpress.android.ui.engagement.ListScenario listScenario) {
        return null;
    }
    
    private final java.util.List<org.wordpress.android.ui.engagement.EngageItem> appendNextPageLoaderIfNeeded(boolean hasMore, boolean isLoading, org.wordpress.android.ui.engagement.GetLikesUseCase.PagingInfo pageInfo) {
        return null;
    }
    
    private final void onUserProfileHolderClicked(org.wordpress.android.ui.engagement.EngagedListNavigationEvent.OpenUserProfileBottomSheet.UserProfile userProfile, org.wordpress.android.ui.engagement.EngagementNavigationSource source) {
    }
    
    private final void onSiteLinkHolderClicked(long siteId, java.lang.String siteUrl, java.lang.String source) {
    }
    
    private final void onHeaderClicked(long siteId, java.lang.String siteUrl, long postOrCommentId, long commentPostId) {
    }
    
    @androidx.annotation.VisibleForTesting(otherwise = androidx.annotation.VisibleForTesting.NONE)
    @java.lang.Override()
    public void onCleared() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u001a\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BW\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0002\u0010\u0010J\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0005H\u00c6\u0003J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J\t\u0010 \u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003J\u0011\u0010\"\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rH\u00c6\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003Ja\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000bH\u00c6\u0001J\u0013\u0010%\u001a\u00020\u00052\b\u0010&\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\'\u001a\u00020\u0003H\u00d6\u0001J\t\u0010(\u001a\u00020)H\u00d6\u0001R\u0019\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\t\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001b\u00a8\u0006*"}, d2 = {"Lorg/wordpress/android/ui/engagement/EngagedPeopleListViewModel$EngagedPeopleListUiState;", "", "numLikes", "", "showLoading", "", "engageItemsList", "", "Lorg/wordpress/android/ui/engagement/EngageItem;", "showEmptyState", "emptyStateTitle", "Lorg/wordpress/android/ui/utils/UiString;", "emptyStateAction", "Lkotlin/Function0;", "", "emptyStateButtonText", "(IZLjava/util/List;ZLorg/wordpress/android/ui/utils/UiString;Lkotlin/jvm/functions/Function0;Lorg/wordpress/android/ui/utils/UiString;)V", "getEmptyStateAction", "()Lkotlin/jvm/functions/Function0;", "getEmptyStateButtonText", "()Lorg/wordpress/android/ui/utils/UiString;", "getEmptyStateTitle", "getEngageItemsList", "()Ljava/util/List;", "getNumLikes", "()I", "getShowEmptyState", "()Z", "getShowLoading", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class EngagedPeopleListUiState {
        private final int numLikes = 0;
        private final boolean showLoading = false;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<org.wordpress.android.ui.engagement.EngageItem> engageItemsList = null;
        private final boolean showEmptyState = false;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.ui.utils.UiString emptyStateTitle = null;
        @org.jetbrains.annotations.Nullable()
        private final kotlin.jvm.functions.Function0<kotlin.Unit> emptyStateAction = null;
        @org.jetbrains.annotations.Nullable()
        private final org.wordpress.android.ui.utils.UiString emptyStateButtonText = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.engagement.EngagedPeopleListViewModel.EngagedPeopleListUiState copy(int numLikes, boolean showLoading, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.engagement.EngageItem> engageItemsList, boolean showEmptyState, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.utils.UiString emptyStateTitle, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function0<kotlin.Unit> emptyStateAction, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.utils.UiString emptyStateButtonText) {
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
        
        public EngagedPeopleListUiState(int numLikes, boolean showLoading, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.engagement.EngageItem> engageItemsList, boolean showEmptyState, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.utils.UiString emptyStateTitle, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function0<kotlin.Unit> emptyStateAction, @org.jetbrains.annotations.Nullable()
        org.wordpress.android.ui.utils.UiString emptyStateButtonText) {
            super();
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getNumLikes() {
            return 0;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean getShowLoading() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.engagement.EngageItem> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<org.wordpress.android.ui.engagement.EngageItem> getEngageItemsList() {
            return null;
        }
        
        public final boolean component4() {
            return false;
        }
        
        public final boolean getShowEmptyState() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.utils.UiString component5() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.utils.UiString getEmptyStateTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> component6() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final kotlin.jvm.functions.Function0<kotlin.Unit> getEmptyStateAction() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.utils.UiString component7() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final org.wordpress.android.ui.utils.UiString getEmptyStateButtonText() {
            return null;
        }
    }
}