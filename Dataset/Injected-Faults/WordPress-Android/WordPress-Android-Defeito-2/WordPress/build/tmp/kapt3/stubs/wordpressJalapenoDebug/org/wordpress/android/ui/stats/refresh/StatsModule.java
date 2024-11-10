package org.wordpress.android.ui.stats.refresh;

import java.lang.System;

/**
 * Module that provides use cases for Stats.
 */
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u00da\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u00c3\u0001\u0010\u0003\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0005\u00a2\u0006\u0002\b\u00060\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200H\u0007JI\u00101\u001a\u0002022\b\b\u0001\u00103\u001a\u0002042\b\b\u0001\u00105\u001a\u0002042\u0006\u00106\u001a\u0002072\u001b\b\u0001\u00108\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0005\u00a2\u0006\u0002\b\u00060\u00042\u0006\u00109\u001a\u00020:H\u0007J3\u0010;\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0005\u00a2\u0006\u0002\b\u00060\u00042\u0006\u0010!\u001a\u00020\"2\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020?H\u0007JI\u0010@\u001a\u0002022\u0006\u0010A\u001a\u00020B2\b\b\u0001\u00103\u001a\u0002042\b\b\u0001\u00105\u001a\u0002042\u0006\u00106\u001a\u0002072\u0013\b\u0001\u0010C\u001a\r\u0012\t\u0012\u00070D\u00a2\u0006\u0002\b\u00060\u00042\u0006\u00109\u001a\u00020:H\u0007JQ\u0010E\u001a\u0002022\u0006\u0010A\u001a\u00020B2\b\b\u0001\u00103\u001a\u0002042\b\b\u0001\u00105\u001a\u0002042\u0006\u00106\u001a\u0002072\u001b\b\u0001\u00108\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0005\u00a2\u0006\u0002\b\u00060\u00042\u0006\u00109\u001a\u00020:H\u0007JC\u0010F\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0005\u00a2\u0006\u0002\b\u00060\u00042\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020PH\u0007JI\u0010Q\u001a\u0002022\b\b\u0001\u00103\u001a\u0002042\b\b\u0001\u00105\u001a\u0002042\u0006\u00106\u001a\u0002072\u001b\b\u0001\u00108\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0005\u00a2\u0006\u0002\b\u00060\u00042\u0006\u00109\u001a\u00020:H\u0007J3\u0010R\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0005\u00a2\u0006\u0002\b\u00060\u00042\u0006\u0010#\u001a\u00020$2\u0006\u0010S\u001a\u00020T2\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J[\u0010U\u001a\r\u0012\t\u0012\u00070D\u00a2\u0006\u0002\b\u00060\u00042\u0006\u0010V\u001a\u00020W2\u0006\u0010X\u001a\u00020Y2\u0006\u0010Z\u001a\u00020[2\u0006\u0010\\\u001a\u00020]2\u0006\u0010^\u001a\u00020_2\u0006\u0010`\u001a\u00020a2\u0006\u0010b\u001a\u00020c2\u0006\u0010d\u001a\u00020e2\u0006\u0010f\u001a\u00020gH\u0007JQ\u0010h\u001a\u0002022\u0006\u0010A\u001a\u00020B2\b\b\u0001\u00103\u001a\u0002042\b\b\u0001\u00105\u001a\u0002042\u0006\u00106\u001a\u0002072\u001b\b\u0001\u00108\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0005\u00a2\u0006\u0002\b\u00060\u00042\u0006\u00109\u001a\u00020:H\u0007JA\u0010i\u001a\u0002022\b\b\u0001\u00103\u001a\u0002042\b\b\u0001\u00105\u001a\u0002042\u0006\u00106\u001a\u0002072\u0013\b\u0001\u0010C\u001a\r\u0012\t\u0012\u00070D\u00a2\u0006\u0002\b\u00060\u00042\u0006\u00109\u001a\u00020:H\u0007J#\u0010j\u001a\r\u0012\t\u0012\u00070D\u00a2\u0006\u0002\b\u00060\u00042\u0006\u0010\u001f\u001a\u00020k2\u0006\u0010V\u001a\u00020WH\u0007JF\u0010l\u001a\u000e\u0012\u0004\u0012\u00020n\u0012\u0004\u0012\u0002020m2\b\b\u0001\u0010o\u001a\u0002022\b\b\u0001\u0010p\u001a\u0002022\b\b\u0001\u0010q\u001a\u0002022\b\b\u0001\u0010r\u001a\u0002022\b\b\u0001\u0010s\u001a\u000202H\u0007JI\u0010t\u001a\u0002022\u0006\u0010A\u001a\u00020B2\b\b\u0001\u00103\u001a\u0002042\b\b\u0001\u00105\u001a\u0002042\u0006\u00106\u001a\u0002072\u0013\b\u0001\u0010C\u001a\r\u0012\t\u0012\u00070D\u00a2\u0006\u0002\b\u00060\u00042\u0006\u00109\u001a\u00020:H\u0007J\u0012\u0010u\u001a\u00020v2\b\b\u0001\u0010w\u001a\u00020xH\u0007JS\u0010y\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0005\u00a2\u0006\u0002\b\u00060\u00042\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020P2\u0006\u0010%\u001a\u00020&H\u0007JA\u0010z\u001a\u0002022\b\b\u0001\u00103\u001a\u0002042\b\b\u0001\u00105\u001a\u0002042\u0006\u00106\u001a\u0002072\u0013\b\u0001\u0010C\u001a\r\u0012\t\u0012\u00070D\u00a2\u0006\u0002\b\u00060\u00042\u0006\u00109\u001a\u00020:H\u0007J+\u0010{\u001a\r\u0012\t\u0012\u00070D\u00a2\u0006\u0002\b\u00060\u00042\u0006\u0010|\u001a\u00020}2\u0006\u0010X\u001a\u00020Y2\u0006\u0010\\\u001a\u00020]H\u0007JI\u0010~\u001a\u0002022\u0006\u0010A\u001a\u00020B2\b\b\u0001\u00103\u001a\u0002042\b\b\u0001\u00105\u001a\u0002042\u0006\u00106\u001a\u0002072\u0013\b\u0001\u0010C\u001a\r\u0012\t\u0012\u00070D\u00a2\u0006\u0002\b\u00060\u00042\u0006\u00109\u001a\u00020:H\u0007JI\u0010\u007f\u001a\u0002022\u0006\u0010A\u001a\u00020B2\b\b\u0001\u00103\u001a\u0002042\b\b\u0001\u00105\u001a\u0002042\u0006\u00106\u001a\u0002072\u0013\b\u0001\u0010C\u001a\r\u0012\t\u0012\u00070D\u00a2\u0006\u0002\b\u00060\u00042\u0006\u00109\u001a\u00020:H\u0007\u00a8\u0006\u0080\u0001"}, d2 = {"Lorg/wordpress/android/ui/stats/refresh/StatsModule;", "", "()V", "provideBlockInsightsUseCases", "", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/BaseStatsUseCase;", "Lkotlin/jvm/JvmSuppressWildcards;", "statsRevampV2FeatureConfig", "Lorg/wordpress/android/util/config/StatsRevampV2FeatureConfig;", "viewsAndVisitorsUseCaseFactory", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/ViewsAndVisitorsUseCase$ViewsAndVisitorsUseCaseFactory;", "allTimeStatsUseCase", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/AllTimeStatsUseCase;", "latestPostSummaryUseCase", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/LatestPostSummaryUseCase;", "todayStatsUseCase", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/TodayStatsUseCase;", "followersUseCaseFactory", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/FollowersUseCase$FollowersUseCaseFactory;", "commentsUseCase", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/CommentsUseCase;", "mostPopularInsightsUseCase", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/MostPopularInsightsUseCase;", "tagsAndCategoriesUseCaseFactory", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/TagsAndCategoriesUseCase$TagsAndCategoriesUseCaseFactory;", "publicizeUseCaseFactory", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/PublicizeUseCase$PublicizeUseCaseFactory;", "postingActivityUseCase", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/PostingActivityUseCase;", "followerTotalsUseCase", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/FollowerTotalsUseCase;", "totalLikesUseCaseFactory", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/TotalLikesUseCase$TotalLikesUseCaseFactory;", "totalCommentsUseCaseFactory", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/TotalCommentsUseCase$TotalCommentsUseCaseFactory;", "totalFollowersUseCaseFactory", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/TotalFollowersUseCase$TotalFollowersUseCaseFactory;", "annualSiteStatsUseCaseFactory", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/AnnualSiteStatsUseCase$AnnualSiteStatsUseCaseFactory;", "managementControlUseCase", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/ManagementControlUseCase;", "managementNewsCardUseCase", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/ManagementNewsCardUseCase;", "actionCardGrowUseCase", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/ActionCardGrowUseCase;", "actionCardReminderUseCase", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/ActionCardReminderUseCase;", "actionCardScheduleUseCase", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/ActionCardScheduleUseCase;", "provideCommentsDetailStatsUseCase", "Lorg/wordpress/android/ui/stats/refresh/lists/BaseListUseCase;", "bgDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "mainDispatcher", "statsSiteProvider", "Lorg/wordpress/android/ui/stats/refresh/utils/StatsSiteProvider;", "useCases", "uiModelMapper", "Lorg/wordpress/android/ui/stats/refresh/lists/UiModelMapper;", "provideCommentsDetailUseCases", "authorsCommentsUseCase", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/AuthorsCommentsUseCase;", "postsCommentsUseCase", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/PostsCommentsUseCase;", "provideDayStatsUseCase", "statsStore", "Lorg/wordpress/android/fluxc/store/StatsStore;", "useCasesFactories", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/GranularUseCaseFactory;", "provideDetailStatsUseCase", "provideDetailUseCases", "postHeaderUseCase", "Lorg/wordpress/android/ui/stats/refresh/lists/detail/PostHeaderUseCase;", "postDayViewsUseCase", "Lorg/wordpress/android/ui/stats/refresh/lists/detail/PostDayViewsUseCase;", "postMonthsAndYearsUseCaseFactory", "Lorg/wordpress/android/ui/stats/refresh/lists/detail/PostMonthsAndYearsUseCase$PostMonthsAndYearsUseCaseFactory;", "postAverageViewsPerDayUseCaseFactory", "Lorg/wordpress/android/ui/stats/refresh/lists/detail/PostAverageViewsPerDayUseCase$PostAverageViewsPerDayUseCaseFactory;", "postRecentWeeksUseCaseFactory", "Lorg/wordpress/android/ui/stats/refresh/lists/detail/PostRecentWeeksUseCase$PostRecentWeeksUseCaseFactory;", "provideFollowersDetailStatsUseCase", "provideFollowersDetailUseCases", "followerTypesUseCase", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/insights/usecases/FollowerTypesUseCase;", "provideGranularUseCaseFactories", "postsAndPagesUseCaseFactory", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/usecases/PostsAndPagesUseCase$PostsAndPagesUseCaseFactory;", "referrersUseCaseFactory", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/usecases/ReferrersUseCase$ReferrersUseCaseFactory;", "clicksUseCaseFactory", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/usecases/ClicksUseCase$ClicksUseCaseFactory;", "countryViewsUseCaseFactory", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/usecases/CountryViewsUseCase$CountryViewsUseCaseFactory;", "videoPlaysUseCaseFactory", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/usecases/VideoPlaysUseCase$VideoPlaysUseCaseFactory;", "searchTermsUseCaseFactory", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/usecases/SearchTermsUseCase$SearchTermsUseCaseFactory;", "authorsUseCaseFactory", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/usecases/AuthorsUseCase$AuthorsUseCaseFactory;", "overviewUseCaseFactory", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/usecases/OverviewUseCase$OverviewUseCaseFactory;", "fileDownloadsUseCaseFactory", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/usecases/FileDownloadsUseCase$FileDownloadsUseCaseFactory;", "provideInsightsUseCase", "provideLikesDetailStatsUseCase", "provideLikesDetailUseCases", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/usecases/TotalLikesDetailUseCase$TotalLikesGranularUseCaseFactory;", "provideListStatsUseCases", "", "Lorg/wordpress/android/ui/stats/refresh/lists/StatsListViewModel$StatsSection;", "insightsUseCase", "dayStatsUseCase", "weekStatsUseCase", "monthStatsUseCase", "yearStatsUseCase", "provideMonthStatsUseCase", "provideSharedPrefs", "Landroid/content/SharedPreferences;", "context", "Landroid/content/Context;", "provideViewAllInsightsUseCases", "provideViewsAndVisitorsDetailUseCase", "provideViewsAndVisitorsDetailUseCases", "viewsAndVisitorsGranularUseCaseFactory", "Lorg/wordpress/android/ui/stats/refresh/lists/sections/granular/usecases/ViewsAndVisitorsDetailUseCase$ViewsAndVisitorsGranularUseCaseFactory;", "provideWeekStatsUseCase", "provideYearStatsUseCase", "org.wordpress.android_wordpressJalapenoDebug"})
@dagger.Module()
public final class StatsModule {
    
    public StatsModule() {
        super();
    }
    
    /**
     * Provides a list of use cases for the Insights screen in Stats. Modify this method when you want to add more
     * blocks to the Insights screen.
     */
    @org.jetbrains.annotations.NotNull()
    @kotlin.Suppress(names = {"LongParameterList"})
    @javax.inject.Named(value = "BlockInsightsUseCases")
    @javax.inject.Singleton()
    @dagger.Provides()
    public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase<?, ?>> provideBlockInsightsUseCases(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.util.config.StatsRevampV2FeatureConfig statsRevampV2FeatureConfig, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.ViewsAndVisitorsUseCase.ViewsAndVisitorsUseCaseFactory viewsAndVisitorsUseCaseFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.AllTimeStatsUseCase allTimeStatsUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.LatestPostSummaryUseCase latestPostSummaryUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TodayStatsUseCase todayStatsUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.FollowersUseCase.FollowersUseCaseFactory followersUseCaseFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.CommentsUseCase commentsUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.MostPopularInsightsUseCase mostPopularInsightsUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TagsAndCategoriesUseCase.TagsAndCategoriesUseCaseFactory tagsAndCategoriesUseCaseFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.PublicizeUseCase.PublicizeUseCaseFactory publicizeUseCaseFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.PostingActivityUseCase postingActivityUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.FollowerTotalsUseCase followerTotalsUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TotalLikesUseCase.TotalLikesUseCaseFactory totalLikesUseCaseFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TotalCommentsUseCase.TotalCommentsUseCaseFactory totalCommentsUseCaseFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TotalFollowersUseCase.TotalFollowersUseCaseFactory totalFollowersUseCaseFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.AnnualSiteStatsUseCase.AnnualSiteStatsUseCaseFactory annualSiteStatsUseCaseFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.ManagementControlUseCase managementControlUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.ManagementNewsCardUseCase managementNewsCardUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.ActionCardGrowUseCase actionCardGrowUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.ActionCardReminderUseCase actionCardReminderUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.ActionCardScheduleUseCase actionCardScheduleUseCase) {
        return null;
    }
    
    /**
     * Provides a list of use cases for the View all screen in Stats. Modify this method when you want to add more
     * blocks to the Insights screen.
     */
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "ViewAllInsightsUseCases")
    @javax.inject.Singleton()
    @dagger.Provides()
    public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase<?, ?>> provideViewAllInsightsUseCases(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.FollowersUseCase.FollowersUseCaseFactory followersUseCaseFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TagsAndCategoriesUseCase.TagsAndCategoriesUseCaseFactory tagsAndCategoriesUseCaseFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.PublicizeUseCase.PublicizeUseCaseFactory publicizeUseCaseFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.detail.PostMonthsAndYearsUseCase.PostMonthsAndYearsUseCaseFactory postMonthsAndYearsUseCaseFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.detail.PostAverageViewsPerDayUseCase.PostAverageViewsPerDayUseCaseFactory postAverageViewsPerDayUseCaseFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.detail.PostRecentWeeksUseCase.PostRecentWeeksUseCaseFactory postRecentWeeksUseCaseFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.AnnualSiteStatsUseCase.AnnualSiteStatsUseCaseFactory annualSiteStatsUseCaseFactory) {
        return null;
    }
    
    /**
     * Provides a list of use case factories that build use cases for the Time stats screen based on the given
     * granularity (Day, Week, Month, Year).
     */
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "GranularUseCaseFactories")
    @javax.inject.Singleton()
    @dagger.Provides()
    public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.granular.GranularUseCaseFactory> provideGranularUseCaseFactories(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.PostsAndPagesUseCase.PostsAndPagesUseCaseFactory postsAndPagesUseCaseFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.ReferrersUseCase.ReferrersUseCaseFactory referrersUseCaseFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.ClicksUseCase.ClicksUseCaseFactory clicksUseCaseFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.CountryViewsUseCase.CountryViewsUseCaseFactory countryViewsUseCaseFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.VideoPlaysUseCase.VideoPlaysUseCaseFactory videoPlaysUseCaseFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.SearchTermsUseCase.SearchTermsUseCaseFactory searchTermsUseCaseFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.AuthorsUseCase.AuthorsUseCaseFactory authorsUseCaseFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.OverviewUseCase.OverviewUseCaseFactory overviewUseCaseFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.FileDownloadsUseCase.FileDownloadsUseCaseFactory fileDownloadsUseCaseFactory) {
        return null;
    }
    
    /**
     * Provides a list of use cases for the Post detail screen in Stats. Modify this method when you want to add more
     * blocks to the post detail screen.
     */
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BlockDetailUseCases")
    @javax.inject.Singleton()
    @dagger.Provides()
    public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase<?, ?>> provideDetailUseCases(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.detail.PostHeaderUseCase postHeaderUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.detail.PostDayViewsUseCase postDayViewsUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.detail.PostMonthsAndYearsUseCase.PostMonthsAndYearsUseCaseFactory postMonthsAndYearsUseCaseFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.detail.PostAverageViewsPerDayUseCase.PostAverageViewsPerDayUseCaseFactory postAverageViewsPerDayUseCaseFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.detail.PostRecentWeeksUseCase.PostRecentWeeksUseCaseFactory postRecentWeeksUseCaseFactory) {
        return null;
    }
    
    /**
     * Provides a singleton usecase that represents the Insights screen. It consists of list of use cases that build
     * the insights blocks.
     */
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "InsightsUseCase")
    @javax.inject.Singleton()
    @dagger.Provides()
    public final org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase provideInsightsUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.StatsStore statsStore, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BlockInsightsUseCases")
    java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase<?, ?>> useCases, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.UiModelMapper uiModelMapper) {
        return null;
    }
    
    /**
     * Provides a singleton usecase that represents the Day stats screen.
     * @param useCasesFactories build the use cases for the DAYS granularity
     */
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "DayStatsUseCase")
    @javax.inject.Singleton()
    @dagger.Provides()
    public final org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase provideDayStatsUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.StatsStore statsStore, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "GranularUseCaseFactories")
    java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.granular.GranularUseCaseFactory> useCasesFactories, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.UiModelMapper uiModelMapper) {
        return null;
    }
    
    /**
     * Provides a singleton usecase that represents the Week stats screen.
     * @param useCasesFactories build the use cases for the WEEKS granularity
     */
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "WeekStatsUseCase")
    @javax.inject.Singleton()
    @dagger.Provides()
    public final org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase provideWeekStatsUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.StatsStore statsStore, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "GranularUseCaseFactories")
    java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.granular.GranularUseCaseFactory> useCasesFactories, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.UiModelMapper uiModelMapper) {
        return null;
    }
    
    /**
     * Provides a singleton usecase that represents the Month stats screen.
     * @param useCasesFactories build the use cases for the MONTHS granularity
     */
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "MonthStatsUseCase")
    @javax.inject.Singleton()
    @dagger.Provides()
    public final org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase provideMonthStatsUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.StatsStore statsStore, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "GranularUseCaseFactories")
    java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.granular.GranularUseCaseFactory> useCasesFactories, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.UiModelMapper uiModelMapper) {
        return null;
    }
    
    /**
     * Provides a singleton usecase that represents the Year stats screen.
     * @param useCasesFactories build the use cases for the YEARS granularity
     */
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "YearStatsUseCase")
    @javax.inject.Singleton()
    @dagger.Provides()
    public final org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase provideYearStatsUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.StatsStore statsStore, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "GranularUseCaseFactories")
    java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.granular.GranularUseCaseFactory> useCasesFactories, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.UiModelMapper uiModelMapper) {
        return null;
    }
    
    /**
     * Provides all list stats use cases
     */
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "ListStatsUseCases")
    @javax.inject.Singleton()
    @dagger.Provides()
    public final java.util.Map<org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel.StatsSection, org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase> provideListStatsUseCases(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "InsightsUseCase")
    org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase insightsUseCase, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "DayStatsUseCase")
    org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase dayStatsUseCase, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "WeekStatsUseCase")
    org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase weekStatsUseCase, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "MonthStatsUseCase")
    org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase monthStatsUseCase, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "YearStatsUseCase")
    org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase yearStatsUseCase) {
        return null;
    }
    
    /**
     * Provides a singleton usecase that represents the Year stats screen.
     * @param useCases build the use cases for the YEARS granularity
     */
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BlockDetailUseCase")
    @javax.inject.Singleton()
    @dagger.Provides()
    public final org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase provideDetailStatsUseCase(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.fluxc.store.StatsStore statsStore, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BlockDetailUseCases")
    java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase<?, ?>> useCases, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.UiModelMapper uiModelMapper) {
        return null;
    }
    
    /**
     * Provides a singleton usecase that represents Views And Visitors Details screen.
     * Update this method when you want to add more blocks to this detail screen.
     * @param useCases build the use cases for the YEARS granularity
     */
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BlockViewsAndVisitorsUseCases")
    @javax.inject.Singleton()
    @dagger.Provides()
    public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.granular.GranularUseCaseFactory> provideViewsAndVisitorsDetailUseCases(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.ViewsAndVisitorsDetailUseCase.ViewsAndVisitorsGranularUseCaseFactory viewsAndVisitorsGranularUseCaseFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.ReferrersUseCase.ReferrersUseCaseFactory referrersUseCaseFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.CountryViewsUseCase.CountryViewsUseCaseFactory countryViewsUseCaseFactory) {
        return null;
    }
    
    /**
     * Provides a singleton usecase that represents the Year stats screen.
     * @param useCases build the use cases for the YEARS granularity
     */
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "ViewsAndVisitorsUseCase")
    @javax.inject.Singleton()
    @dagger.Provides()
    public final org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase provideViewsAndVisitorsDetailUseCase(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BlockViewsAndVisitorsUseCases")
    java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.granular.GranularUseCaseFactory> useCasesFactories, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.UiModelMapper uiModelMapper) {
        return null;
    }
    
    /**
     * Provides a list of use cases for the Total Likes detail screen in Stats. Modify this method when you want to
     * add more blocks to the likes detail screen.
     */
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "LikesDetailUseCases")
    @javax.inject.Singleton()
    @dagger.Provides()
    public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.granular.GranularUseCaseFactory> provideLikesDetailUseCases(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.TotalLikesDetailUseCase.TotalLikesGranularUseCaseFactory totalLikesUseCaseFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.PostsAndPagesUseCase.PostsAndPagesUseCaseFactory postsAndPagesUseCaseFactory) {
        return null;
    }
    
    /**
     * Provides a singleton usecase that represents the Likes detail screen.
     * @param useCases build the use cases for the DAYS granularity
     */
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "LikesDetailUseCase")
    @javax.inject.Singleton()
    @dagger.Provides()
    public final org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase provideLikesDetailStatsUseCase(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "LikesDetailUseCases")
    java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.granular.GranularUseCaseFactory> useCasesFactories, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.UiModelMapper uiModelMapper) {
        return null;
    }
    
    /**
     * Provides a list of use cases for the Total Comments detail screen in Stats. Modify this method when you want to
     * add more blocks to the comments detail screen.
     */
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "CommentsDetailUseCases")
    @javax.inject.Singleton()
    @dagger.Provides()
    public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase<?, ?>> provideCommentsDetailUseCases(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TotalCommentsUseCase.TotalCommentsUseCaseFactory totalCommentsUseCaseFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.AuthorsCommentsUseCase authorsCommentsUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.PostsCommentsUseCase postsCommentsUseCase) {
        return null;
    }
    
    /**
     * Provides a singleton usecase that represents the Comments detail screen.
     * @param useCases build the use cases for the DAYS granularity
     */
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "CommentsDetailUseCase")
    @javax.inject.Singleton()
    @dagger.Provides()
    public final org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase provideCommentsDetailStatsUseCase(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "CommentsDetailUseCases")
    java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase<?, ?>> useCases, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.UiModelMapper uiModelMapper) {
        return null;
    }
    
    /**
     * Provides a list of use cases for the Total Followers detail screen in Stats. Modify this method when you want to
     * add more blocks to the followers detail screen.
     */
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "FollowersDetailUseCases")
    @javax.inject.Singleton()
    @dagger.Provides()
    public final java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase<?, ?>> provideFollowersDetailUseCases(@org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TotalFollowersUseCase.TotalFollowersUseCaseFactory totalFollowersUseCaseFactory, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.FollowerTypesUseCase followerTypesUseCase, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.FollowersUseCase.FollowersUseCaseFactory followersUseCaseFactory) {
        return null;
    }
    
    /**
     * Provides a singleton usecase that represents the Followers detail screen.
     * @param useCases build the use cases
     */
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "FollowersDetailUseCase")
    @javax.inject.Singleton()
    @dagger.Provides()
    public final org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase provideFollowersDetailStatsUseCase(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "BG_THREAD")
    kotlinx.coroutines.CoroutineDispatcher bgDispatcher, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "UI_THREAD")
    kotlinx.coroutines.CoroutineDispatcher mainDispatcher, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider statsSiteProvider, @org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "FollowersDetailUseCases")
    java.util.List<org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase<?, ?>> useCases, @org.jetbrains.annotations.NotNull()
    org.wordpress.android.ui.stats.refresh.lists.UiModelMapper uiModelMapper) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Singleton()
    @dagger.Provides()
    public final android.content.SharedPreferences provideSharedPrefs(@org.jetbrains.annotations.NotNull()
    @dagger.hilt.android.qualifiers.ApplicationContext()
    android.content.Context context) {
        return null;
    }
}