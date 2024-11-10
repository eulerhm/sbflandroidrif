package org.wordpress.android.workers.weeklyroundup;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0012\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000e"}, d2 = {"Lorg/wordpress/android/workers/weeklyroundup/WeeklyRoundupRepository;", "", "visitsAndViewsStore", "Lorg/wordpress/android/fluxc/store/stats/time/VisitsAndViewsStore;", "(Lorg/wordpress/android/fluxc/store/stats/time/VisitsAndViewsStore;)V", "fetchWeeklyRoundupData", "Lorg/wordpress/android/workers/weeklyroundup/WeeklyRoundupData;", "site", "Lorg/wordpress/android/fluxc/model/SiteModel;", "(Lorg/wordpress/android/fluxc/model/SiteModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLastWeekPeriodData", "Lorg/wordpress/android/fluxc/model/stats/time/VisitsAndViewsModel$PeriodData;", "visitsAndViewsModel", "Lorg/wordpress/android/fluxc/model/stats/time/VisitsAndViewsModel;", "org.wordpress.android_wordpressJalapenoDebug"})
public final class WeeklyRoundupRepository {
    private final org.wordpress.android.fluxc.store.stats.time.VisitsAndViewsStore visitsAndViewsStore = null;
    
    @javax.inject.Inject()
    public WeeklyRoundupRepository(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.stats.time.VisitsAndViewsStore visitsAndViewsStore) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetchWeeklyRoundupData(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.model.SiteModel site, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super org.wordpress.android.workers.weeklyroundup.WeeklyRoundupData> continuation) {
        return null;
    }
    
    private final org.wordpress.android.fluxc.model.stats.time.VisitsAndViewsModel.PeriodData getLastWeekPeriodData(org.wordpress.android.fluxc.model.stats.time.VisitsAndViewsModel visitsAndViewsModel) {
        return null;
    }
}