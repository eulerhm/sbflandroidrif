package org.wordpress.android.ui.stats.refresh.lists.sections;

import java.lang.System;

/**
 * Do not override this class directly. Use StatefulUseCase or StatelessUseCase instead.
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003:\u0005JKLMNBE\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00028\u0001\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u00a2\u0006\u0002\u0010\u000eJ\u000e\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\u000bH\u0014J\u000e\u0010*\u001a\b\u0012\u0004\u0012\u00020)0\u000bH\u0014J\u000e\u0010+\u001a\b\u0012\u0004\u0012\u00020)0\u000bH$J#\u0010,\u001a\b\u0012\u0004\u0012\u00020)0\u000b2\u0006\u0010\u0017\u001a\u00028\u00002\u0006\u0010%\u001a\u00028\u0001H$\u00a2\u0006\u0002\u0010-J\u0006\u0010.\u001a\u00020/J\u001f\u00100\u001a\u00020/2\f\u00101\u001a\b\u0012\u0004\u0012\u00028\u000002H\u0084@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00103J!\u00104\u001a\u00020/2\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u000206H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00108J\u001f\u00109\u001a\b\u0012\u0004\u0012\u00028\u0000022\u0006\u00107\u001a\u000206H\u00a4@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010:J\u0013\u0010;\u001a\u0004\u0018\u00018\u0000H\u00a4@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010<J\u000e\u0010=\u001a\u00020/2\u0006\u0010>\u001a\u00020 J\u0019\u0010?\u001a\u00020/2\u0006\u0010@\u001a\u00020\fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010AJ\u0017\u0010B\u001a\u00020/2\n\b\u0002\u0010C\u001a\u0004\u0018\u00018\u0001\u00a2\u0006\u0002\u0010DJ\b\u0010E\u001a\u00020/H\u0002J\u001a\u0010F\u001a\u00020/2\u0012\u0010G\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00010HJ\u0010\u0010I\u001a\u00020/2\u0006\u0010C\u001a\u00020\u0019H\u0002R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\u00020\u00138VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0010\u0010\t\u001a\u00028\u0001X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0016R\u0012\u0010\u0017\u001a\u0004\u0018\u00018\u0000X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0016R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00110\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0\u001f0\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0\u001f0\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001dR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0010\u0010%\u001a\u00028\u0001X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0016R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010\'X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006O"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase;", "DOMAIN_MODEL", "UI_STATE", "Lkotlinx/coroutines/CoroutineScope;", "type", "Lorg/wordpress/android/fluxc/store/StatsStore$StatsType;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "backgroundDispatcher", "defaultUiState", "fetchParams", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$UseCaseParam;", "uiUpdateParams", "(Lorg/wordpress/android/fluxc/store/StatsStore$StatsType;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Ljava/lang/Object;Ljava/util/List;Ljava/util/List;)V", "_liveData", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$UseCaseModel;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "Ljava/lang/Object;", "domainModel", "domainState", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$UseCaseModel$UseCaseState;", "liveData", "Landroidx/lifecycle/LiveData;", "getLiveData", "()Landroidx/lifecycle/LiveData;", "mutableNavigationTarget", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/stats/refresh/NavigationTarget;", "navigationTarget", "getNavigationTarget", "getType", "()Lorg/wordpress/android/fluxc/store/StatsStore$StatsType;", "uiState", "updateJob", "Lkotlinx/coroutines/Job;", "buildEmptyItem", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "buildErrorItem", "buildLoadingItem", "buildUiModel", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/List;", "clear", "", "evaluateState", "state", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$State;", "(Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$State;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetch", "refresh", "", "forced", "(ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchRemoteData", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadCachedData", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "navigateTo", "target", "onParamsChange", "param", "(Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$UseCaseParam;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onUiState", "newState", "(Ljava/lang/Object;)V", "updateState", "updateUiState", "update", "Lkotlin/Function1;", "updateUseCaseState", "State", "StatelessUseCase", "UseCaseMode", "UseCaseModel", "UseCaseParam", "org.wordpress.android_wordpressJalapenoDebug"})
public abstract class BaseStatsUseCase<DOMAIN_MODEL extends java.lang.Object, UI_STATE extends java.lang.Object> implements kotlinx.coroutines.CoroutineScope {
    @org.jetbrains.annotations.NotNull()
    private final org.wordpress.android.fluxc.store.StatsStore.StatsType type = null;
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher backgroundDispatcher = null;
    private final UI_STATE defaultUiState = null;
    private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseParam> fetchParams = null;
    private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseParam> uiUpdateParams = null;
    private org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseModel.UseCaseState domainState = org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseModel.UseCaseState.LOADING;
    private DOMAIN_MODEL domainModel;
    private UI_STATE uiState;
    private kotlinx.coroutines.Job updateJob;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseModel> _liveData = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseModel> liveData = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.stats.refresh.NavigationTarget>> mutableNavigationTarget = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.stats.refresh.NavigationTarget>> navigationTarget = null;
    
    public BaseStatsUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.StatsStore.StatsType type, @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineDispatcher backgroundDispatcher, UI_STATE defaultUiState, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseParam> fetchParams, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseParam> uiUpdateParams) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.wordpress.android.fluxc.store.StatsStore.StatsType getType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlin.coroutines.CoroutineContext getCoroutineContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseModel> getLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.stats.refresh.NavigationTarget>> getNavigationTarget() {
        return null;
    }
    
    /**
     * Fetches data either from a local cache or from remote API
     * @param refresh is true when we want to get the remote data
     * @param forced is true when we want to get fresh data and skip the cache
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetch(boolean refresh, boolean forced, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object onParamsChange(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseParam param, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    protected final java.lang.Object evaluateState(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.State<DOMAIN_MODEL> state, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    /**
     * Trigger this method when the UI state has changed.
     * @param newState
     */
    public final void onUiState(@org.jetbrains.annotations.Nullable()
    UI_STATE newState) {
    }
    
    /**
     * Trigger this method when updating only a part of UI state.
     * @param update function
     */
    public final void updateUiState(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super UI_STATE, ? extends UI_STATE> update) {
    }
    
    private final void updateUseCaseState(org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseModel.UseCaseState newState) {
    }
    
    /**
     * Clears the LiveData value when we switch the current Site so we don't show the old data for a new site
     */
    public final void clear() {
    }
    
    /**
     * Passes a navigation target to the View layer which uses the context to open the correct activity.
     */
    public final void navigateTo(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.NavigationTarget target) {
    }
    
    /**
     * Loads data from a local cache. Returns a null value when the cache is empty.
     */
    @org.jetbrains.annotations.Nullable()
    protected abstract java.lang.Object loadCachedData(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super DOMAIN_MODEL> continuation);
    
    /**
     * Fetches remote data from the endpoint.
     * @param forced is true when we want to get the fresh data
     */
    @org.jetbrains.annotations.Nullable()
    protected abstract java.lang.Object fetchRemoteData(boolean forced, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.State<DOMAIN_MODEL>> continuation);
    
    /**
     * Transforms given domain model and ui state into the UI model
     * @param domainModel domain model coming from FluxC
     * @param uiState contains UI specific data
     * @return a list of block list data
     */
    @org.jetbrains.annotations.NotNull()
    protected abstract java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> buildUiModel(DOMAIN_MODEL domainModel, UI_STATE uiState);
    
    @org.jetbrains.annotations.NotNull()
    protected abstract java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> buildLoadingItem();
    
    @org.jetbrains.annotations.NotNull()
    protected java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> buildErrorItem() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    protected java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> buildEmptyItem() {
        return null;
    }
    
    private final void updateState() {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0004\b\u0002\u0010\u00012\u00020\u0002:\u0004\u0004\u0005\u0006\u0007B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b\u00a8\u0006\f"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$State;", "DOMAIN_MODEL", "", "()V", "Data", "Empty", "Error", "Loading", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$State$Error;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$State$Data;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$State$Empty;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$State$Loading;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class State<DOMAIN_MODEL extends java.lang.Object> {
        
        private State() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u0000*\u0004\b\u0003\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\t\u0010\b\u001a\u00020\u0004H\u00c6\u0003J\u0019\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00030\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u00c6\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u00d6\u0003J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001J\t\u0010\u0010\u001a\u00020\u0004H\u00d6\u0001R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$State$Error;", "DOMAIN_MODEL", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$State;", "error", "", "(Ljava/lang/String;)V", "getError", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Error<DOMAIN_MODEL extends java.lang.Object> extends org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.State<DOMAIN_MODEL> {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String error = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.State.Error<DOMAIN_MODEL> copy(@org.jetbrains.annotations.NotNull()
            java.lang.String error) {
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
            
            public Error(@org.jetbrains.annotations.NotNull()
            java.lang.String error) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getError() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0004\b\u0003\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00028\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\f\u001a\u00028\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\nJ\t\u0010\r\u001a\u00020\u0005H\u00c6\u0003J(\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00030\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001\u00a2\u0006\u0002\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0003\u001a\u00028\u0003\u00a2\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0017"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$State$Data;", "DOMAIN_MODEL", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$State;", "model", "cached", "", "(Ljava/lang/Object;Z)V", "getCached", "()Z", "getModel", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "component2", "copy", "(Ljava/lang/Object;Z)Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$State$Data;", "equals", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Data<DOMAIN_MODEL extends java.lang.Object> extends org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.State<DOMAIN_MODEL> {
            private final DOMAIN_MODEL model = null;
            private final boolean cached = false;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.State.Data<DOMAIN_MODEL> copy(DOMAIN_MODEL model, boolean cached) {
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
            
            public Data(DOMAIN_MODEL model, boolean cached) {
                super();
            }
            
            public final DOMAIN_MODEL component1() {
                return null;
            }
            
            public final DOMAIN_MODEL getModel() {
                return null;
            }
            
            public final boolean component2() {
                return false;
            }
            
            public final boolean getCached() {
                return false;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0003\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003\u00a8\u0006\u0004"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$State$Empty;", "DOMAIN_MODEL", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$State;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Empty<DOMAIN_MODEL extends java.lang.Object> extends org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.State<DOMAIN_MODEL> {
            
            public Empty() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0003\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003\u00a8\u0006\u0004"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$State$Loading;", "DOMAIN_MODEL", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$State;", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class Loading<DOMAIN_MODEL extends java.lang.Object> extends org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.State<DOMAIN_MODEL> {
            
            public Loading() {
                super();
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u001eB;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\u0011\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u00c6\u0003J\u0011\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\tH\u00c6\u0003JA\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\b\b\u0002\u0010\b\u001a\u00020\tH\u00c6\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\u001bH\u00d6\u0001J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001R\u0019\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u001f"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$UseCaseModel;", "", "type", "Lorg/wordpress/android/fluxc/store/StatsStore$StatsType;", "data", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "stateData", "state", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$UseCaseModel$UseCaseState;", "(Lorg/wordpress/android/fluxc/store/StatsStore$StatsType;Ljava/util/List;Ljava/util/List;Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$UseCaseModel$UseCaseState;)V", "getData", "()Ljava/util/List;", "getState", "()Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$UseCaseModel$UseCaseState;", "getStateData", "getType", "()Lorg/wordpress/android/fluxc/store/StatsStore$StatsType;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "UseCaseState", "org.wordpress.android_wordpressJalapenoDebug"})
    public static final class UseCaseModel {
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.fluxc.store.StatsStore.StatsType type = null;
        @org.jetbrains.annotations.Nullable()
        private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> data = null;
        @org.jetbrains.annotations.Nullable()
        private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> stateData = null;
        @org.jetbrains.annotations.NotNull()
        private final org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseModel.UseCaseState state = null;
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseModel copy(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.StatsStore.StatsType type, @org.jetbrains.annotations.Nullable()
        java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> data, @org.jetbrains.annotations.Nullable()
        java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> stateData, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseModel.UseCaseState state) {
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
        
        public UseCaseModel(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.StatsStore.StatsType type, @org.jetbrains.annotations.Nullable()
        java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> data, @org.jetbrains.annotations.Nullable()
        java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> stateData, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseModel.UseCaseState state) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.store.StatsStore.StatsType component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.fluxc.store.StatsStore.StatsType getType() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> getData() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> getStateData() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseModel.UseCaseState component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseModel.UseCaseState getState() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$UseCaseModel$UseCaseState;", "", "(Ljava/lang/String;I)V", "SUCCESS", "ERROR", "LOADING", "EMPTY", "org.wordpress.android_wordpressJalapenoDebug"})
        public static enum UseCaseState {
            /*public static final*/ SUCCESS /* = new SUCCESS() */,
            /*public static final*/ ERROR /* = new ERROR() */,
            /*public static final*/ LOADING /* = new LOADING() */,
            /*public static final*/ EMPTY /* = new EMPTY() */;
            
            UseCaseState() {
            }
        }
    }
    
    /**
     * Stateless use case should be used for the blocks that display just plain data.
     * These blocks don't have only one UI state and it doesn't change.
     */
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u0000*\u0004\b\u0002\u0010\u00012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u0002:\u0001\u0013B-\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\u0002\u0010\fJ\u001b\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\n2\u0006\u0010\u000f\u001a\u00028\u0002H&\u00a2\u0006\u0002\u0010\u0010J#\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\n2\u0006\u0010\u000f\u001a\u00028\u00022\u0006\u0010\u0011\u001a\u00020\u0003H\u0004\u00a2\u0006\u0002\u0010\u0012\u00a8\u0006\u0014"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$StatelessUseCase;", "DOMAIN_MODEL", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$StatelessUseCase$NotUsedUiState;", "type", "Lorg/wordpress/android/fluxc/store/StatsStore$StatsType;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "backgroundDispatcher", "inputParams", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$UseCaseParam;", "(Lorg/wordpress/android/fluxc/store/StatsStore$StatsType;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Ljava/util/List;)V", "buildUiModel", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BlockListItem;", "domainModel", "(Ljava/lang/Object;)Ljava/util/List;", "uiState", "(Ljava/lang/Object;Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$StatelessUseCase$NotUsedUiState;)Ljava/util/List;", "NotUsedUiState", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class StatelessUseCase<DOMAIN_MODEL extends java.lang.Object> extends org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase<DOMAIN_MODEL, org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.StatelessUseCase.NotUsedUiState> {
        
        public StatelessUseCase(@org.jetbrains.annotations.NotNull()
        org.wordpress.android.fluxc.store.StatsStore.StatsType type, @org.jetbrains.annotations.NotNull()
        kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
        kotlinx.coroutines.CoroutineDispatcher backgroundDispatcher, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseParam> inputParams) {
            super(null, null, null, null, null, null);
        }
        
        /**
         * Transforms given domain model into the UI model
         * @param domainModel domain model coming from FluxC
         * @return a list of block list data
         */
        @org.jetbrains.annotations.NotNull()
        public abstract java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> buildUiModel(DOMAIN_MODEL domainModel);
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        protected final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BlockListItem> buildUiModel(DOMAIN_MODEL domainModel, @org.jetbrains.annotations.NotNull()
        org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.StatelessUseCase.NotUsedUiState uiState) {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$StatelessUseCase$NotUsedUiState;", "", "()V", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class NotUsedUiState {
            @org.jetbrains.annotations.NotNull()
            public static final org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.StatelessUseCase.NotUsedUiState INSTANCE = null;
            
            private NotUsedUiState() {
                super();
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$UseCaseMode;", "", "(Ljava/lang/String;I)V", "BLOCK", "BLOCK_DETAIL", "VIEW_ALL", "org.wordpress.android_wordpressJalapenoDebug"})
    public static enum UseCaseMode {
        /*public static final*/ BLOCK /* = new BLOCK() */,
        /*public static final*/ BLOCK_DETAIL /* = new BLOCK_DETAIL() */,
        /*public static final*/ VIEW_ALL /* = new VIEW_ALL() */;
        
        UseCaseMode() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0001\u0003B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0001\u0004\u00a8\u0006\u0005"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$UseCaseParam;", "", "()V", "SelectedDateParam", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$UseCaseParam$SelectedDateParam;", "org.wordpress.android_wordpressJalapenoDebug"})
    public static abstract class UseCaseParam {
        
        private UseCaseParam() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$UseCaseParam$SelectedDateParam;", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$UseCaseParam;", "statsSection", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$StatsSection;", "(Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$StatsSection;)V", "getStatsSection", "()Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$StatsSection;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.wordpress.android_wordpressJalapenoDebug"})
        public static final class SelectedDateParam extends org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseParam {
            @org.jetbrains.annotations.NotNull()
            private final org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection statsSection = null;
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseParam.SelectedDateParam copy(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection statsSection) {
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
            
            public SelectedDateParam(@org.jetbrains.annotations.NotNull()
            org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection statsSection) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection getStatsSection() {
                return null;
            }
        }
    }
}