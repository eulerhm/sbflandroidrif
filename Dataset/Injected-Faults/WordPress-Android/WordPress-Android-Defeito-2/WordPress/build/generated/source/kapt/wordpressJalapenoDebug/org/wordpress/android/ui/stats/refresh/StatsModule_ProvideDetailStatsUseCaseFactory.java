package org.wordpress.android.ui.stats.refresh;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.util.List;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.StatsStore;
import org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase;
import org.wordpress.android.ui.stats.refresh.lists.UiModelMapper;
import org.wordpress.android.ui.stats.refresh.lists.sections.BaseStatsUseCase;
import org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider;

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
public final class StatsModule_ProvideDetailStatsUseCaseFactory implements Factory<BaseListUseCase> {
  private final StatsModule module;

  private final Provider<StatsStore> statsStoreProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<List<BaseStatsUseCase<?, ?>>> useCasesProvider;

  private final Provider<UiModelMapper> uiModelMapperProvider;

  public StatsModule_ProvideDetailStatsUseCaseFactory(StatsModule module,
      Provider<StatsStore> statsStoreProvider, Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<List<BaseStatsUseCase<?, ?>>> useCasesProvider,
      Provider<UiModelMapper> uiModelMapperProvider) {
    this.module = module;
    this.statsStoreProvider = statsStoreProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.useCasesProvider = useCasesProvider;
    this.uiModelMapperProvider = uiModelMapperProvider;
  }

  @Override
  public BaseListUseCase get() {
    return provideDetailStatsUseCase(module, statsStoreProvider.get(), bgDispatcherProvider.get(), mainDispatcherProvider.get(), statsSiteProvider.get(), useCasesProvider.get(), uiModelMapperProvider.get());
  }

  public static StatsModule_ProvideDetailStatsUseCaseFactory create(StatsModule module,
      Provider<StatsStore> statsStoreProvider, Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<List<BaseStatsUseCase<?, ?>>> useCasesProvider,
      Provider<UiModelMapper> uiModelMapperProvider) {
    return new StatsModule_ProvideDetailStatsUseCaseFactory(module, statsStoreProvider, bgDispatcherProvider, mainDispatcherProvider, statsSiteProvider, useCasesProvider, uiModelMapperProvider);
  }

  public static BaseListUseCase provideDetailStatsUseCase(StatsModule instance,
      StatsStore statsStore, CoroutineDispatcher bgDispatcher, CoroutineDispatcher mainDispatcher,
      StatsSiteProvider statsSiteProvider, List<BaseStatsUseCase<?, ?>> useCases,
      UiModelMapper uiModelMapper) {
    return Preconditions.checkNotNullFromProvides(instance.provideDetailStatsUseCase(statsStore, bgDispatcher, mainDispatcher, statsSiteProvider, useCases, uiModelMapper));
  }
}
