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
import org.wordpress.android.ui.stats.refresh.lists.detail.PostDayViewsUseCase;
import org.wordpress.android.ui.stats.refresh.lists.detail.PostHeaderUseCase;
import org.wordpress.android.ui.stats.refresh.lists.detail.PostMonthsAndYearsUseCase;
import org.wordpress.android.ui.stats.refresh.lists.detail.PostRecentWeeksUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase;

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
public final class StatsModule_ProvideDetailUseCasesFactory implements Factory<List<BaseStatsUseCase<?, ?>>> {
  private final StatsModule module;

  private final Provider<PostHeaderUseCase> postHeaderUseCaseProvider;

  private final Provider<PostDayViewsUseCase> postDayViewsUseCaseProvider;

  private final Provider<PostMonthsAndYearsUseCase.PostMonthsAndYearsUseCaseFactory> postMonthsAndYearsUseCaseFactoryProvider;

  private final Provider<PostAverageViewsPerDayUseCase.PostAverageViewsPerDayUseCaseFactory> postAverageViewsPerDayUseCaseFactoryProvider;

  private final Provider<PostRecentWeeksUseCase.PostRecentWeeksUseCaseFactory> postRecentWeeksUseCaseFactoryProvider;

  public StatsModule_ProvideDetailUseCasesFactory(StatsModule module,
      Provider<PostHeaderUseCase> postHeaderUseCaseProvider,
      Provider<PostDayViewsUseCase> postDayViewsUseCaseProvider,
      Provider<PostMonthsAndYearsUseCase.PostMonthsAndYearsUseCaseFactory> postMonthsAndYearsUseCaseFactoryProvider,
      Provider<PostAverageViewsPerDayUseCase.PostAverageViewsPerDayUseCaseFactory> postAverageViewsPerDayUseCaseFactoryProvider,
      Provider<PostRecentWeeksUseCase.PostRecentWeeksUseCaseFactory> postRecentWeeksUseCaseFactoryProvider) {
    this.module = module;
    this.postHeaderUseCaseProvider = postHeaderUseCaseProvider;
    this.postDayViewsUseCaseProvider = postDayViewsUseCaseProvider;
    this.postMonthsAndYearsUseCaseFactoryProvider = postMonthsAndYearsUseCaseFactoryProvider;
    this.postAverageViewsPerDayUseCaseFactoryProvider = postAverageViewsPerDayUseCaseFactoryProvider;
    this.postRecentWeeksUseCaseFactoryProvider = postRecentWeeksUseCaseFactoryProvider;
  }

  @Override
  public List<BaseStatsUseCase<?, ?>> get() {
    return provideDetailUseCases(module, postHeaderUseCaseProvider.get(), postDayViewsUseCaseProvider.get(), postMonthsAndYearsUseCaseFactoryProvider.get(), postAverageViewsPerDayUseCaseFactoryProvider.get(), postRecentWeeksUseCaseFactoryProvider.get());
  }

  public static StatsModule_ProvideDetailUseCasesFactory create(StatsModule module,
      Provider<PostHeaderUseCase> postHeaderUseCaseProvider,
      Provider<PostDayViewsUseCase> postDayViewsUseCaseProvider,
      Provider<PostMonthsAndYearsUseCase.PostMonthsAndYearsUseCaseFactory> postMonthsAndYearsUseCaseFactoryProvider,
      Provider<PostAverageViewsPerDayUseCase.PostAverageViewsPerDayUseCaseFactory> postAverageViewsPerDayUseCaseFactoryProvider,
      Provider<PostRecentWeeksUseCase.PostRecentWeeksUseCaseFactory> postRecentWeeksUseCaseFactoryProvider) {
    return new StatsModule_ProvideDetailUseCasesFactory(module, postHeaderUseCaseProvider, postDayViewsUseCaseProvider, postMonthsAndYearsUseCaseFactoryProvider, postAverageViewsPerDayUseCaseFactoryProvider, postRecentWeeksUseCaseFactoryProvider);
  }

  public static List<BaseStatsUseCase<?, ?>> provideDetailUseCases(StatsModule instance,
      PostHeaderUseCase postHeaderUseCase, PostDayViewsUseCase postDayViewsUseCase,
      PostMonthsAndYearsUseCase.PostMonthsAndYearsUseCaseFactory postMonthsAndYearsUseCaseFactory,
      PostAverageViewsPerDayUseCase.PostAverageViewsPerDayUseCaseFactory postAverageViewsPerDayUseCaseFactory,
      PostRecentWeeksUseCase.PostRecentWeeksUseCaseFactory postRecentWeeksUseCaseFactory) {
    return Preconditions.checkNotNullFromProvides(instance.provideDetailUseCases(postHeaderUseCase, postDayViewsUseCase, postMonthsAndYearsUseCaseFactory, postAverageViewsPerDayUseCaseFactory, postRecentWeeksUseCaseFactory));
  }
}
