package org.wordpress.android.ui.stats.refresh;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.util.List;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.stats.refresh.lists.detail.PostAverageViewsPerDayUseCase;
import org.wordpress.android.ui.stats.refresh.lists.detail.PostMonthsAndYearsUseCase;
import org.wordpress.android.ui.stats.refresh.lists.detail.PostRecentWeeksUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.AnnualSiteStatsUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.FollowersUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.PublicizeUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.insights.usecases.TagsAndCategoriesUseCase;

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
public final class StatsModule_ProvideViewAllInsightsUseCasesFactory implements Factory<List<BaseStatsUseCase<?, ?>>> {
  private final StatsModule module;

  private final Provider<FollowersUseCase.FollowersUseCaseFactory> followersUseCaseFactoryProvider;

  private final Provider<TagsAndCategoriesUseCase.TagsAndCategoriesUseCaseFactory> tagsAndCategoriesUseCaseFactoryProvider;

  private final Provider<PublicizeUseCase.PublicizeUseCaseFactory> publicizeUseCaseFactoryProvider;

  private final Provider<PostMonthsAndYearsUseCase.PostMonthsAndYearsUseCaseFactory> postMonthsAndYearsUseCaseFactoryProvider;

  private final Provider<PostAverageViewsPerDayUseCase.PostAverageViewsPerDayUseCaseFactory> postAverageViewsPerDayUseCaseFactoryProvider;

  private final Provider<PostRecentWeeksUseCase.PostRecentWeeksUseCaseFactory> postRecentWeeksUseCaseFactoryProvider;

  private final Provider<AnnualSiteStatsUseCase.AnnualSiteStatsUseCaseFactory> annualSiteStatsUseCaseFactoryProvider;

  public StatsModule_ProvideViewAllInsightsUseCasesFactory(StatsModule module,
      Provider<FollowersUseCase.FollowersUseCaseFactory> followersUseCaseFactoryProvider,
      Provider<TagsAndCategoriesUseCase.TagsAndCategoriesUseCaseFactory> tagsAndCategoriesUseCaseFactoryProvider,
      Provider<PublicizeUseCase.PublicizeUseCaseFactory> publicizeUseCaseFactoryProvider,
      Provider<PostMonthsAndYearsUseCase.PostMonthsAndYearsUseCaseFactory> postMonthsAndYearsUseCaseFactoryProvider,
      Provider<PostAverageViewsPerDayUseCase.PostAverageViewsPerDayUseCaseFactory> postAverageViewsPerDayUseCaseFactoryProvider,
      Provider<PostRecentWeeksUseCase.PostRecentWeeksUseCaseFactory> postRecentWeeksUseCaseFactoryProvider,
      Provider<AnnualSiteStatsUseCase.AnnualSiteStatsUseCaseFactory> annualSiteStatsUseCaseFactoryProvider) {
    this.module = module;
    this.followersUseCaseFactoryProvider = followersUseCaseFactoryProvider;
    this.tagsAndCategoriesUseCaseFactoryProvider = tagsAndCategoriesUseCaseFactoryProvider;
    this.publicizeUseCaseFactoryProvider = publicizeUseCaseFactoryProvider;
    this.postMonthsAndYearsUseCaseFactoryProvider = postMonthsAndYearsUseCaseFactoryProvider;
    this.postAverageViewsPerDayUseCaseFactoryProvider = postAverageViewsPerDayUseCaseFactoryProvider;
    this.postRecentWeeksUseCaseFactoryProvider = postRecentWeeksUseCaseFactoryProvider;
    this.annualSiteStatsUseCaseFactoryProvider = annualSiteStatsUseCaseFactoryProvider;
  }

  @Override
  public List<BaseStatsUseCase<?, ?>> get() {
    return provideViewAllInsightsUseCases(module, followersUseCaseFactoryProvider.get(), tagsAndCategoriesUseCaseFactoryProvider.get(), publicizeUseCaseFactoryProvider.get(), postMonthsAndYearsUseCaseFactoryProvider.get(), postAverageViewsPerDayUseCaseFactoryProvider.get(), postRecentWeeksUseCaseFactoryProvider.get(), annualSiteStatsUseCaseFactoryProvider.get());
  }

  public static StatsModule_ProvideViewAllInsightsUseCasesFactory create(StatsModule module,
      Provider<FollowersUseCase.FollowersUseCaseFactory> followersUseCaseFactoryProvider,
      Provider<TagsAndCategoriesUseCase.TagsAndCategoriesUseCaseFactory> tagsAndCategoriesUseCaseFactoryProvider,
      Provider<PublicizeUseCase.PublicizeUseCaseFactory> publicizeUseCaseFactoryProvider,
      Provider<PostMonthsAndYearsUseCase.PostMonthsAndYearsUseCaseFactory> postMonthsAndYearsUseCaseFactoryProvider,
      Provider<PostAverageViewsPerDayUseCase.PostAverageViewsPerDayUseCaseFactory> postAverageViewsPerDayUseCaseFactoryProvider,
      Provider<PostRecentWeeksUseCase.PostRecentWeeksUseCaseFactory> postRecentWeeksUseCaseFactoryProvider,
      Provider<AnnualSiteStatsUseCase.AnnualSiteStatsUseCaseFactory> annualSiteStatsUseCaseFactoryProvider) {
    return new StatsModule_ProvideViewAllInsightsUseCasesFactory(module, followersUseCaseFactoryProvider, tagsAndCategoriesUseCaseFactoryProvider, publicizeUseCaseFactoryProvider, postMonthsAndYearsUseCaseFactoryProvider, postAverageViewsPerDayUseCaseFactoryProvider, postRecentWeeksUseCaseFactoryProvider, annualSiteStatsUseCaseFactoryProvider);
  }

  public static List<BaseStatsUseCase<?, ?>> provideViewAllInsightsUseCases(StatsModule instance,
      FollowersUseCase.FollowersUseCaseFactory followersUseCaseFactory,
      TagsAndCategoriesUseCase.TagsAndCategoriesUseCaseFactory tagsAndCategoriesUseCaseFactory,
      PublicizeUseCase.PublicizeUseCaseFactory publicizeUseCaseFactory,
      PostMonthsAndYearsUseCase.PostMonthsAndYearsUseCaseFactory postMonthsAndYearsUseCaseFactory,
      PostAverageViewsPerDayUseCase.PostAverageViewsPerDayUseCaseFactory postAverageViewsPerDayUseCaseFactory,
      PostRecentWeeksUseCase.PostRecentWeeksUseCaseFactory postRecentWeeksUseCaseFactory,
      AnnualSiteStatsUseCase.AnnualSiteStatsUseCaseFactory annualSiteStatsUseCaseFactory) {
    return Preconditions.checkNotNullFromProvides(instance.provideViewAllInsightsUseCases(followersUseCaseFactory, tagsAndCategoriesUseCaseFactory, publicizeUseCaseFactory, postMonthsAndYearsUseCaseFactory, postAverageViewsPerDayUseCaseFactory, postRecentWeeksUseCaseFactory, annualSiteStatsUseCaseFactory));
  }
}
