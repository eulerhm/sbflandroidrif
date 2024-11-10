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
public final class StatsModule_ProvideFollowersDetailStatsUseCaseFactory implements Factory<BaseListUseCase> {
  private final StatsModule module;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<List<BaseStatsUseCase<?, ?>>> useCasesProvider;

  private final Provider<UiModelMapper> uiModelMapperProvider;

  public StatsModule_ProvideFollowersDetailStatsUseCaseFactory(StatsModule module,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<List<BaseStatsUseCase<?, ?>>> useCasesProvider,
      Provider<UiModelMapper> uiModelMapperProvider) {
    this.module = module;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.useCasesProvider = useCasesProvider;
    this.uiModelMapperProvider = uiModelMapperProvider;
  }

  @Override
  public BaseListUseCase get() {
    return provideFollowersDetailStatsUseCase(module, bgDispatcherProvider.get(), mainDispatcherProvider.get(), statsSiteProvider.get(), useCasesProvider.get(), uiModelMapperProvider.get());
  }

  public static StatsModule_ProvideFollowersDetailStatsUseCaseFactory create(StatsModule module,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<List<BaseStatsUseCase<?, ?>>> useCasesProvider,
      Provider<UiModelMapper> uiModelMapperProvider) {
    return new StatsModule_ProvideFollowersDetailStatsUseCaseFactory(module, bgDispatcherProvider, mainDispatcherProvider, statsSiteProvider, useCasesProvider, uiModelMapperProvider);
  }

  public static BaseListUseCase provideFollowersDetailStatsUseCase(StatsModule instance,
      CoroutineDispatcher bgDispatcher, CoroutineDispatcher mainDispatcher,
      StatsSiteProvider statsSiteProvider, List<BaseStatsUseCase<?, ?>> useCases,
      UiModelMapper uiModelMapper) {
    return Preconditions.checkNotNullFromProvides(instance.provideFollowersDetailStatsUseCase(bgDispatcher, mainDispatcher, statsSiteProvider, useCases, uiModelMapper));
  }
}
