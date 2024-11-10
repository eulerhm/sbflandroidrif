package org.wordpress.android.ui.stats.refresh;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.util.List;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase;
import org.wordpress.android.ui.stats.refresh.lists.sections.granular.GranularUseCaseFactory;
import org.wordpress.android.ui.stats.refresh.utils.StatsDateSelector;
import org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider;

@ScopeMetadata
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
public final class StatsViewAllViewModelFactory_Builder_Factory implements Factory<StatsViewAllViewModelFactory.Builder> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<List<GranularUseCaseFactory>> granularFactoriesProvider;

  private final Provider<List<BaseStatsUseCase<?, ?>>> insightsUseCasesProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<StatsDateSelector.Factory> dateSelectorFactoryProvider;

  public StatsViewAllViewModelFactory_Builder_Factory(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<List<GranularUseCaseFactory>> granularFactoriesProvider,
      Provider<List<BaseStatsUseCase<?, ?>>> insightsUseCasesProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<StatsDateSelector.Factory> dateSelectorFactoryProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.granularFactoriesProvider = granularFactoriesProvider;
    this.insightsUseCasesProvider = insightsUseCasesProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.dateSelectorFactoryProvider = dateSelectorFactoryProvider;
  }

  @Override
  public StatsViewAllViewModelFactory.Builder get() {
    return newInstance(mainDispatcherProvider.get(), bgDispatcherProvider.get(), granularFactoriesProvider.get(), insightsUseCasesProvider.get(), statsSiteProvider.get(), dateSelectorFactoryProvider.get());
  }

  public static StatsViewAllViewModelFactory_Builder_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<List<GranularUseCaseFactory>> granularFactoriesProvider,
      Provider<List<BaseStatsUseCase<?, ?>>> insightsUseCasesProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<StatsDateSelector.Factory> dateSelectorFactoryProvider) {
    return new StatsViewAllViewModelFactory_Builder_Factory(mainDispatcherProvider, bgDispatcherProvider, granularFactoriesProvider, insightsUseCasesProvider, statsSiteProvider, dateSelectorFactoryProvider);
  }

  public static StatsViewAllViewModelFactory.Builder newInstance(CoroutineDispatcher mainDispatcher,
      CoroutineDispatcher bgDispatcher, List<GranularUseCaseFactory> granularFactories,
      List<BaseStatsUseCase<?, ?>> insightsUseCases, StatsSiteProvider statsSiteProvider,
      StatsDateSelector.Factory dateSelectorFactory) {
    return new StatsViewAllViewModelFactory.Builder(mainDispatcher, bgDispatcher, granularFactories, insightsUseCases, statsSiteProvider, dateSelectorFactory);
  }
}
