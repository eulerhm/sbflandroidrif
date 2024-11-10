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
import org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase;
import org.wordpress.android.ui.stats.refresh.lists.UiModelMapper;
import org.wordpress.android.ui.stats.refresh.lists.sections.granular.GranularUseCaseFactory;
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
public final class StatsModule_ProvideLikesDetailStatsUseCaseFactory implements Factory<BaseListUseCase> {
  private final StatsModule module;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<List<GranularUseCaseFactory>> useCasesFactoriesProvider;

  private final Provider<UiModelMapper> uiModelMapperProvider;

  public StatsModule_ProvideLikesDetailStatsUseCaseFactory(StatsModule module,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<List<GranularUseCaseFactory>> useCasesFactoriesProvider,
      Provider<UiModelMapper> uiModelMapperProvider) {
    this.module = module;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.useCasesFactoriesProvider = useCasesFactoriesProvider;
    this.uiModelMapperProvider = uiModelMapperProvider;
  }

  @Override
  public BaseListUseCase get() {
    return provideLikesDetailStatsUseCase(module, bgDispatcherProvider.get(), mainDispatcherProvider.get(), statsSiteProvider.get(), useCasesFactoriesProvider.get(), uiModelMapperProvider.get());
  }

  public static StatsModule_ProvideLikesDetailStatsUseCaseFactory create(StatsModule module,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<List<GranularUseCaseFactory>> useCasesFactoriesProvider,
      Provider<UiModelMapper> uiModelMapperProvider) {
    return new StatsModule_ProvideLikesDetailStatsUseCaseFactory(module, bgDispatcherProvider, mainDispatcherProvider, statsSiteProvider, useCasesFactoriesProvider, uiModelMapperProvider);
  }

  public static BaseListUseCase provideLikesDetailStatsUseCase(StatsModule instance,
      CoroutineDispatcher bgDispatcher, CoroutineDispatcher mainDispatcher,
      StatsSiteProvider statsSiteProvider, List<GranularUseCaseFactory> useCasesFactories,
      UiModelMapper uiModelMapper) {
    return Preconditions.checkNotNullFromProvides(instance.provideLikesDetailStatsUseCase(bgDispatcher, mainDispatcher, statsSiteProvider, useCasesFactories, uiModelMapper));
  }
}
