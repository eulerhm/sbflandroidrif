package org.wordpress.android.ui.stats.refresh.lists.sections.insights.management;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J2\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002J9\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000f0\u000eH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014J\u0017\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\nH\u0002\u00a2\u0006\u0002\u0010\u0018R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0019"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementMapper;", "", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "statsRevampV2FeatureConfig", "Lorg/wordpress/android/util/config/StatsRevampV2FeatureConfig;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lorg/wordpress/android/util/config/StatsRevampV2FeatureConfig;)V", "buildInsightModel", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementViewModel$InsightListItem$InsightModel;", "type", "Lorg/wordpress/android/fluxc/store/StatsStore$InsightType;", "addedInsightTypes", "", "onClick", "Lkotlin/Function1;", "", "buildUIModel", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/management/InsightsManagementViewModel$InsightListItem;", "addedTypes", "(Ljava/util/Set;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toName", "", "insightType", "(Lorg/wordpress/android/fluxc/store/StatsStore$InsightType;)Ljava/lang/Integer;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class InsightsManagementMapper {
    private final kotlinx.coroutines.CoroutineDispatcher bgDispatcher = null;
    private final org.wordpress.android.util.config.StatsRevampV2FeatureConfig statsRevampV2FeatureConfig = null;
    
    @javax.inject.Inject()
    public InsightsManagementMapper(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.StatsRevampV2FeatureConfig statsRevampV2FeatureConfig) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object buildUIModel(@org.jetbrains.annotations.NotNull()
    java.util.Set<? extends org.wordpress.android.fluxc.store.StatsStore.InsightType> addedTypes, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.store.StatsStore.InsightType, kotlin.Unit> onClick, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.insights.management.InsightsManagementViewModel.InsightListItem>> continuation) {
        return null;
    }
    
    private final org.wordpress.android.ui.stats.refresh.lists.sections.insights.management.InsightsManagementViewModel.InsightListItem.InsightModel buildInsightModel(org.wordpress.android.fluxc.store.StatsStore.InsightType type, java.util.Set<? extends org.wordpress.android.fluxc.store.StatsStore.InsightType> addedInsightTypes, kotlin.jvm.functions.Function1<? super org.wordpress.android.fluxc.store.StatsStore.InsightType, kotlin.Unit> onClick) {
        return null;
    }
    
    @kotlin.Suppress(names = {"ComplexMethod"})
    private final java.lang.Integer toName(org.wordpress.android.fluxc.store.StatsStore.InsightType insightType) {
        return null;
    }
}