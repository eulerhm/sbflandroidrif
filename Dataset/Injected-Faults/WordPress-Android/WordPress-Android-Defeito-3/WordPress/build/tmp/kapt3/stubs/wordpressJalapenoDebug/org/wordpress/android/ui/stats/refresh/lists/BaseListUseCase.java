package org.wordpress.android.ui.stats.refresh.lists;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00aa\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u00aa\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0014\u0010\u0007\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\t0\b\u0012(\u0010\n\u001a$\b\u0001\u0012\u0004\u0012\u00020\f\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\b0\r\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000b\u0012H\u0010\u0010\u001aD\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00110\b\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u0015\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00190\u000b\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001aJ\u0011\u0010:\u001a\u00020\u0017H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010;J!\u0010:\u001a\u00020\u00172\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020=H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010?J\u0006\u0010@\u001a\u00020\u0017J\u0019\u0010A\u001a\u00020\u00172\u0006\u0010B\u001a\u00020CH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010DJ\u0006\u0010E\u001a\u00020\u0017J\u0019\u0010F\u001a\u00020\u00172\u0006\u0010G\u001a\u00020HH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010IJ\u001b\u0010J\u001a\u00020\u00172\b\b\u0002\u0010>\u001a\u00020=H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010KJ\u0017\u0010L\u001a\b\u0012\u0004\u0012\u00020\u000e0\bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010;R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u001b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00110\u001d0\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\u00020\u001f8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b \u0010!R\u0019\u0010\"\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u001c\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R5\u0010\n\u001a$\b\u0001\u0012\u0004\u0012\u00020\f\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\b0\r\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000bX\u0082\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\n\u0002\u0010%R\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00170\'\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000RP\u0010\u0010\u001aD\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00110\b\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u0015\u00a2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00190\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00170+X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020/0.0-X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0.0-X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u00101\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160-X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020/0.0\'\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010)R\u001d\u00104\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0.0\'\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010)R\u0017\u00106\u001a\b\u0012\u0004\u0012\u0002070\'\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010)R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u00109\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\b0-X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006M"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/BaseListUseCase;", "Lkotlinx/coroutines/CoroutineScope;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "mainDispatcher", "statsSiteProvider", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;", "useCases", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase;", "getStatsTypes", "Lkotlin/Function2;", "Lorg/wordpress/android/fluxc/model/SiteModel;", "Lkotlin/coroutines/Continuation;", "Lorg/wordpress/android/fluxc/store/StatsStore$StatsType;", "", "mapUiModel", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$UseCaseModel;", "Lkotlin/ParameterName;", "name", "useCaseModels", "Lkotlin/Function1;", "", "", "showError", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$UiModel;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;Ljava/util/List;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;)V", "blockListData", "Landroidx/lifecycle/MediatorLiveData;", "", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "data", "getData", "()Landroidx/lifecycle/MediatorLiveData;", "Lkotlin/jvm/functions/Function2;", "listSelected", "Landroidx/lifecycle/LiveData;", "getListSelected", "()Landroidx/lifecycle/LiveData;", "mutableListSelected", "Lorg/wordpress/android/viewmodel/SingleLiveEvent;", "mutableNavigationTarget", "Landroidx/lifecycle/MutableLiveData;", "Lorg/wordpress/android/viewmodel/Event;", "Lorg/wordpress/android/ui/stats/refresh/NavigationTarget;", "mutableScrollTo", "mutableSnackbarMessage", "navigationTarget", "getNavigationTarget", "scrollTo", "getScrollTo", "snackbarMessage", "Lorg/wordpress/android/ui/pages/SnackbarMessageHolder;", "getSnackbarMessage", "statsTypes", "loadData", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refresh", "", "forced", "(ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onCleared", "onDateChanged", "selectedSection", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$StatsSection;", "(Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$StatsSection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onListSelected", "onParamChanged", "param", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$UseCaseParam;", "(Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase$UseCaseParam;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refreshData", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refreshTypes", "org.wordpress.android_wordpressJalapenoDebug"})
public final class BaseListUseCase implements kotlinx.coroutines.CoroutineScope {
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final kotlinx.coroutines.CoroutineDispatcher mainDispatcher = null;
    private final org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider = null;
    private final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase<?, ?>> useCases = null;
    private final kotlin.jvm.functions.Function2<org.wordpress.android.fluxc.model.SiteModel, kotlin.coroutines.Continuation<? super java.util.List<? extends org.wordpress.android.fluxc.store.StatsStore.StatsType>>, java.lang.Object> getStatsTypes = null;
    private final kotlin.jvm.functions.Function2<java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseModel>, kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit>, org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.UiModel> mapUiModel = null;
    private final androidx.lifecycle.MediatorLiveData<java.util.Map<org.wordpress.android.fluxc.store.StatsStore.StatsType, org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseModel>> blockListData = null;
    private final androidx.lifecycle.MutableLiveData<java.util.List<org.wordpress.android.fluxc.store.StatsStore.StatsType>> statsTypes = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.UiModel> data = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.stats.refresh.NavigationTarget>> mutableNavigationTarget = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.stats.refresh.NavigationTarget>> navigationTarget = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.Integer> mutableSnackbarMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.ui.pages.SnackbarMessageHolder> snackbarMessage = null;
    private final org.wordpress.android.viewmodel.SingleLiveEvent<kotlin.Unit> mutableListSelected = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<kotlin.Unit> listSelected = null;
    private final androidx.lifecycle.MutableLiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.fluxc.store.StatsStore.StatsType>> mutableScrollTo = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.fluxc.store.StatsStore.StatsType>> scrollTo = null;
    
    public BaseListUseCase(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase<?, ?>> useCases, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super org.wordpress.android.fluxc.model.SiteModel, ? super kotlin.coroutines.Continuation<? super java.util.List<? extends org.wordpress.android.fluxc.store.StatsStore.StatsType>>, ? extends java.lang.Object> getStatsTypes, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseModel>, ? super kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit>, ? extends org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.UiModel> mapUiModel) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlin.coroutines.CoroutineContext getCoroutineContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MediatorLiveData<org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.UiModel> getData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.ui.stats.refresh.NavigationTarget>> getNavigationTarget() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.ui.pages.SnackbarMessageHolder> getSnackbarMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Unit> getListSelected() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<org.wordpress.android.viewmodel.Event<org.wordpress.android.fluxc.store.StatsStore.StatsType>> getScrollTo() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object loadData(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object refreshData(boolean forced, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final java.lang.Object onParamChanged(org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase.UseCaseParam param, kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object refreshTypes(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<? extends org.wordpress.android.fluxc.store.StatsStore.StatsType>> continuation) {
        return null;
    }
    
    private final java.lang.Object loadData(boolean refresh, boolean forced, kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    public final void onCleared() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object onDateChanged(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection selectedSection, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    public final void onListSelected() {
    }
}