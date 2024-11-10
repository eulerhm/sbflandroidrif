package org.wordpress.android.ui.stats.refresh;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.util.Map;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase;
import org.wordpress.android.ui.stats.refresh.lists.StatsListViewModel;

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
public final class StatsModule_ProvideListStatsUseCasesFactory implements Factory<Map<StatsListViewModel.StatsSection, BaseListUseCase>> {
  private final StatsModule module;

  private final Provider<BaseListUseCase> insightsUseCaseProvider;

  private final Provider<BaseListUseCase> dayStatsUseCaseProvider;

  private final Provider<BaseListUseCase> weekStatsUseCaseProvider;

  private final Provider<BaseListUseCase> monthStatsUseCaseProvider;

  private final Provider<BaseListUseCase> yearStatsUseCaseProvider;

  public StatsModule_ProvideListStatsUseCasesFactory(StatsModule module,
      Provider<BaseListUseCase> insightsUseCaseProvider,
      Provider<BaseListUseCase> dayStatsUseCaseProvider,
      Provider<BaseListUseCase> weekStatsUseCaseProvider,
      Provider<BaseListUseCase> monthStatsUseCaseProvider,
      Provider<BaseListUseCase> yearStatsUseCaseProvider) {
    this.module = module;
    this.insightsUseCaseProvider = insightsUseCaseProvider;
    this.dayStatsUseCaseProvider = dayStatsUseCaseProvider;
    this.weekStatsUseCaseProvider = weekStatsUseCaseProvider;
    this.monthStatsUseCaseProvider = monthStatsUseCaseProvider;
    this.yearStatsUseCaseProvider = yearStatsUseCaseProvider;
  }

  @Override
  public Map<StatsListViewModel.StatsSection, BaseListUseCase> get() {
    return provideListStatsUseCases(module, insightsUseCaseProvider.get(), dayStatsUseCaseProvider.get(), weekStatsUseCaseProvider.get(), monthStatsUseCaseProvider.get(), yearStatsUseCaseProvider.get());
  }

  public static StatsModule_ProvideListStatsUseCasesFactory create(StatsModule module,
      Provider<BaseListUseCase> insightsUseCaseProvider,
      Provider<BaseListUseCase> dayStatsUseCaseProvider,
      Provider<BaseListUseCase> weekStatsUseCaseProvider,
      Provider<BaseListUseCase> monthStatsUseCaseProvider,
      Provider<BaseListUseCase> yearStatsUseCaseProvider) {
    return new StatsModule_ProvideListStatsUseCasesFactory(module, insightsUseCaseProvider, dayStatsUseCaseProvider, weekStatsUseCaseProvider, monthStatsUseCaseProvider, yearStatsUseCaseProvider);
  }

  public static Map<StatsListViewModel.StatsSection, BaseListUseCase> provideListStatsUseCases(
      StatsModule instance, BaseListUseCase insightsUseCase, BaseListUseCase dayStatsUseCase,
      BaseListUseCase weekStatsUseCase, BaseListUseCase monthStatsUseCase,
      BaseListUseCase yearStatsUseCase) {
    return Preconditions.checkNotNullFromProvides(instance.provideListStatsUseCases(insightsUseCase, dayStatsUseCase, weekStatsUseCase, monthStatsUseCase, yearStatsUseCase));
  }
}
