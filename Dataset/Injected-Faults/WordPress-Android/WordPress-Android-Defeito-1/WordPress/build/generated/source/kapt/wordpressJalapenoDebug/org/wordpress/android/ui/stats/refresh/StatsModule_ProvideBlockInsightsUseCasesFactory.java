package org.wordpress.android.ui.stats.refresh;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.util.List;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.ActionCardGrowUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.ActionCardReminderUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.ActionCardScheduleUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.AllTimeStatsUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.AnnualSiteStatsUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.CommentsUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.FollowerTotalsUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.FollowersUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.LatestPostSummaryUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.ManagementControlUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.ManagementNewsCardUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.MostPopularInsightsUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.PostingActivityUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.PublicizeUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TagsAndCategoriesUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TodayStatsUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TotalCommentsUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TotalFollowersUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TotalLikesUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.ViewsAndVisitorsUseCase;
import org.wordpress.android.util.config.StatsRevampV2FeatureConfig;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("javax.inject.Named")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class StatsModule_ProvideBlockInsightsUseCasesFactory implements Factory<List<BaseStatsUseCase<?, ?>>> {
  private final StatsModule module;

  private final Provider<StatsRevampV2FeatureConfig> statsRevampV2FeatureConfigProvider;

  private final Provider<ViewsAndVisitorsUseCase.ViewsAndVisitorsUseCaseFactory> viewsAndVisitorsUseCaseFactoryProvider;

  private final Provider<AllTimeStatsUseCase> allTimeStatsUseCaseProvider;

  private final Provider<LatestPostSummaryUseCase> latestPostSummaryUseCaseProvider;

  private final Provider<TodayStatsUseCase> todayStatsUseCaseProvider;

  private final Provider<FollowersUseCase.FollowersUseCaseFactory> followersUseCaseFactoryProvider;

  private final Provider<CommentsUseCase> commentsUseCaseProvider;

  private final Provider<MostPopularInsightsUseCase> mostPopularInsightsUseCaseProvider;

  private final Provider<TagsAndCategoriesUseCase.TagsAndCategoriesUseCaseFactory> tagsAndCategoriesUseCaseFactoryProvider;

  private final Provider<PublicizeUseCase.PublicizeUseCaseFactory> publicizeUseCaseFactoryProvider;

  private final Provider<PostingActivityUseCase> postingActivityUseCaseProvider;

  private final Provider<FollowerTotalsUseCase> followerTotalsUseCaseProvider;

  private final Provider<TotalLikesUseCase.TotalLikesUseCaseFactory> totalLikesUseCaseFactoryProvider;

  private final Provider<TotalCommentsUseCase.TotalCommentsUseCaseFactory> totalCommentsUseCaseFactoryProvider;

  private final Provider<TotalFollowersUseCase.TotalFollowersUseCaseFactory> totalFollowersUseCaseFactoryProvider;

  private final Provider<AnnualSiteStatsUseCase.AnnualSiteStatsUseCaseFactory> annualSiteStatsUseCaseFactoryProvider;

  private final Provider<ManagementControlUseCase> managementControlUseCaseProvider;

  private final Provider<ManagementNewsCardUseCase> managementNewsCardUseCaseProvider;

  private final Provider<ActionCardGrowUseCase> actionCardGrowUseCaseProvider;

  private final Provider<ActionCardReminderUseCase> actionCardReminderUseCaseProvider;

  private final Provider<ActionCardScheduleUseCase> actionCardScheduleUseCaseProvider;

  public StatsModule_ProvideBlockInsightsUseCasesFactory(StatsModule module,
      Provider<StatsRevampV2FeatureConfig> statsRevampV2FeatureConfigProvider,
      Provider<ViewsAndVisitorsUseCase.ViewsAndVisitorsUseCaseFactory> viewsAndVisitorsUseCaseFactoryProvider,
      Provider<AllTimeStatsUseCase> allTimeStatsUseCaseProvider,
      Provider<LatestPostSummaryUseCase> latestPostSummaryUseCaseProvider,
      Provider<TodayStatsUseCase> todayStatsUseCaseProvider,
      Provider<FollowersUseCase.FollowersUseCaseFactory> followersUseCaseFactoryProvider,
      Provider<CommentsUseCase> commentsUseCaseProvider,
      Provider<MostPopularInsightsUseCase> mostPopularInsightsUseCaseProvider,
      Provider<TagsAndCategoriesUseCase.TagsAndCategoriesUseCaseFactory> tagsAndCategoriesUseCaseFactoryProvider,
      Provider<PublicizeUseCase.PublicizeUseCaseFactory> publicizeUseCaseFactoryProvider,
      Provider<PostingActivityUseCase> postingActivityUseCaseProvider,
      Provider<FollowerTotalsUseCase> followerTotalsUseCaseProvider,
      Provider<TotalLikesUseCase.TotalLikesUseCaseFactory> totalLikesUseCaseFactoryProvider,
      Provider<TotalCommentsUseCase.TotalCommentsUseCaseFactory> totalCommentsUseCaseFactoryProvider,
      Provider<TotalFollowersUseCase.TotalFollowersUseCaseFactory> totalFollowersUseCaseFactoryProvider,
      Provider<AnnualSiteStatsUseCase.AnnualSiteStatsUseCaseFactory> annualSiteStatsUseCaseFactoryProvider,
      Provider<ManagementControlUseCase> managementControlUseCaseProvider,
      Provider<ManagementNewsCardUseCase> managementNewsCardUseCaseProvider,
      Provider<ActionCardGrowUseCase> actionCardGrowUseCaseProvider,
      Provider<ActionCardReminderUseCase> actionCardReminderUseCaseProvider,
      Provider<ActionCardScheduleUseCase> actionCardScheduleUseCaseProvider) {
    this.module = module;
    this.statsRevampV2FeatureConfigProvider = statsRevampV2FeatureConfigProvider;
    this.viewsAndVisitorsUseCaseFactoryProvider = viewsAndVisitorsUseCaseFactoryProvider;
    this.allTimeStatsUseCaseProvider = allTimeStatsUseCaseProvider;
    this.latestPostSummaryUseCaseProvider = latestPostSummaryUseCaseProvider;
    this.todayStatsUseCaseProvider = todayStatsUseCaseProvider;
    this.followersUseCaseFactoryProvider = followersUseCaseFactoryProvider;
    this.commentsUseCaseProvider = commentsUseCaseProvider;
    this.mostPopularInsightsUseCaseProvider = mostPopularInsightsUseCaseProvider;
    this.tagsAndCategoriesUseCaseFactoryProvider = tagsAndCategoriesUseCaseFactoryProvider;
    this.publicizeUseCaseFactoryProvider = publicizeUseCaseFactoryProvider;
    this.postingActivityUseCaseProvider = postingActivityUseCaseProvider;
    this.followerTotalsUseCaseProvider = followerTotalsUseCaseProvider;
    this.totalLikesUseCaseFactoryProvider = totalLikesUseCaseFactoryProvider;
    this.totalCommentsUseCaseFactoryProvider = totalCommentsUseCaseFactoryProvider;
    this.totalFollowersUseCaseFactoryProvider = totalFollowersUseCaseFactoryProvider;
    this.annualSiteStatsUseCaseFactoryProvider = annualSiteStatsUseCaseFactoryProvider;
    this.managementControlUseCaseProvider = managementControlUseCaseProvider;
    this.managementNewsCardUseCaseProvider = managementNewsCardUseCaseProvider;
    this.actionCardGrowUseCaseProvider = actionCardGrowUseCaseProvider;
    this.actionCardReminderUseCaseProvider = actionCardReminderUseCaseProvider;
    this.actionCardScheduleUseCaseProvider = actionCardScheduleUseCaseProvider;
  }

  @Override
  public List<BaseStatsUseCase<?, ?>> get() {
    return provideBlockInsightsUseCases(module, statsRevampV2FeatureConfigProvider.get(), viewsAndVisitorsUseCaseFactoryProvider.get(), allTimeStatsUseCaseProvider.get(), latestPostSummaryUseCaseProvider.get(), todayStatsUseCaseProvider.get(), followersUseCaseFactoryProvider.get(), commentsUseCaseProvider.get(), mostPopularInsightsUseCaseProvider.get(), tagsAndCategoriesUseCaseFactoryProvider.get(), publicizeUseCaseFactoryProvider.get(), postingActivityUseCaseProvider.get(), followerTotalsUseCaseProvider.get(), totalLikesUseCaseFactoryProvider.get(), totalCommentsUseCaseFactoryProvider.get(), totalFollowersUseCaseFactoryProvider.get(), annualSiteStatsUseCaseFactoryProvider.get(), managementControlUseCaseProvider.get(), managementNewsCardUseCaseProvider.get(), actionCardGrowUseCaseProvider.get(), actionCardReminderUseCaseProvider.get(), actionCardScheduleUseCaseProvider.get());
  }

  public static StatsModule_ProvideBlockInsightsUseCasesFactory create(StatsModule module,
      Provider<StatsRevampV2FeatureConfig> statsRevampV2FeatureConfigProvider,
      Provider<ViewsAndVisitorsUseCase.ViewsAndVisitorsUseCaseFactory> viewsAndVisitorsUseCaseFactoryProvider,
      Provider<AllTimeStatsUseCase> allTimeStatsUseCaseProvider,
      Provider<LatestPostSummaryUseCase> latestPostSummaryUseCaseProvider,
      Provider<TodayStatsUseCase> todayStatsUseCaseProvider,
      Provider<FollowersUseCase.FollowersUseCaseFactory> followersUseCaseFactoryProvider,
      Provider<CommentsUseCase> commentsUseCaseProvider,
      Provider<MostPopularInsightsUseCase> mostPopularInsightsUseCaseProvider,
      Provider<TagsAndCategoriesUseCase.TagsAndCategoriesUseCaseFactory> tagsAndCategoriesUseCaseFactoryProvider,
      Provider<PublicizeUseCase.PublicizeUseCaseFactory> publicizeUseCaseFactoryProvider,
      Provider<PostingActivityUseCase> postingActivityUseCaseProvider,
      Provider<FollowerTotalsUseCase> followerTotalsUseCaseProvider,
      Provider<TotalLikesUseCase.TotalLikesUseCaseFactory> totalLikesUseCaseFactoryProvider,
      Provider<TotalCommentsUseCase.TotalCommentsUseCaseFactory> totalCommentsUseCaseFactoryProvider,
      Provider<TotalFollowersUseCase.TotalFollowersUseCaseFactory> totalFollowersUseCaseFactoryProvider,
      Provider<AnnualSiteStatsUseCase.AnnualSiteStatsUseCaseFactory> annualSiteStatsUseCaseFactoryProvider,
      Provider<ManagementControlUseCase> managementControlUseCaseProvider,
      Provider<ManagementNewsCardUseCase> managementNewsCardUseCaseProvider,
      Provider<ActionCardGrowUseCase> actionCardGrowUseCaseProvider,
      Provider<ActionCardReminderUseCase> actionCardReminderUseCaseProvider,
      Provider<ActionCardScheduleUseCase> actionCardScheduleUseCaseProvider) {
    return new StatsModule_ProvideBlockInsightsUseCasesFactory(module, statsRevampV2FeatureConfigProvider, viewsAndVisitorsUseCaseFactoryProvider, allTimeStatsUseCaseProvider, latestPostSummaryUseCaseProvider, todayStatsUseCaseProvider, followersUseCaseFactoryProvider, commentsUseCaseProvider, mostPopularInsightsUseCaseProvider, tagsAndCategoriesUseCaseFactoryProvider, publicizeUseCaseFactoryProvider, postingActivityUseCaseProvider, followerTotalsUseCaseProvider, totalLikesUseCaseFactoryProvider, totalCommentsUseCaseFactoryProvider, totalFollowersUseCaseFactoryProvider, annualSiteStatsUseCaseFactoryProvider, managementControlUseCaseProvider, managementNewsCardUseCaseProvider, actionCardGrowUseCaseProvider, actionCardReminderUseCaseProvider, actionCardScheduleUseCaseProvider);
  }

  public static List<BaseStatsUseCase<?, ?>> provideBlockInsightsUseCases(StatsModule instance,
      StatsRevampV2FeatureConfig statsRevampV2FeatureConfig,
      ViewsAndVisitorsUseCase.ViewsAndVisitorsUseCaseFactory viewsAndVisitorsUseCaseFactory,
      AllTimeStatsUseCase allTimeStatsUseCase, LatestPostSummaryUseCase latestPostSummaryUseCase,
      TodayStatsUseCase todayStatsUseCase,
      FollowersUseCase.FollowersUseCaseFactory followersUseCaseFactory,
      CommentsUseCase commentsUseCase, MostPopularInsightsUseCase mostPopularInsightsUseCase,
      TagsAndCategoriesUseCase.TagsAndCategoriesUseCaseFactory tagsAndCategoriesUseCaseFactory,
      PublicizeUseCase.PublicizeUseCaseFactory publicizeUseCaseFactory,
      PostingActivityUseCase postingActivityUseCase, FollowerTotalsUseCase followerTotalsUseCase,
      TotalLikesUseCase.TotalLikesUseCaseFactory totalLikesUseCaseFactory,
      TotalCommentsUseCase.TotalCommentsUseCaseFactory totalCommentsUseCaseFactory,
      TotalFollowersUseCase.TotalFollowersUseCaseFactory totalFollowersUseCaseFactory,
      AnnualSiteStatsUseCase.AnnualSiteStatsUseCaseFactory annualSiteStatsUseCaseFactory,
      ManagementControlUseCase managementControlUseCase,
      ManagementNewsCardUseCase managementNewsCardUseCase,
      ActionCardGrowUseCase actionCardGrowUseCase,
      ActionCardReminderUseCase actionCardReminderUseCase,
      ActionCardScheduleUseCase actionCardScheduleUseCase) {
    return Preconditions.checkNotNullFromProvides(instance.provideBlockInsightsUseCases(statsRevampV2FeatureConfig, viewsAndVisitorsUseCaseFactory, allTimeStatsUseCase, latestPostSummaryUseCase, todayStatsUseCase, followersUseCaseFactory, commentsUseCase, mostPopularInsightsUseCase, tagsAndCategoriesUseCaseFactory, publicizeUseCaseFactory, postingActivityUseCase, followerTotalsUseCase, totalLikesUseCaseFactory, totalCommentsUseCaseFactory, totalFollowersUseCaseFactory, annualSiteStatsUseCaseFactory, managementControlUseCase, managementNewsCardUseCase, actionCardGrowUseCase, actionCardReminderUseCase, actionCardScheduleUseCase));
  }
}
