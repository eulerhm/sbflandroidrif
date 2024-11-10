package org.wordpress.android.ui.stats.refresh.lists.detail;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase;
import org.wordpress.android.ui.stats.refresh.utils.StatsPostProvider;
import org.wordpress.android.ui.stats.refresh.utils.StatsSiteProvider;
import org.wordpress.android.util.NetworkUtilsWrapper;

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
public final class StatsDetailViewModel_Factory implements Factory<StatsDetailViewModel> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<BaseListUseCase> detailUseCaseProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<StatsPostProvider> statsPostProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  public StatsDetailViewModel_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<BaseListUseCase> detailUseCaseProvider,
      Provider<StatsSiteProvider> statsSiteProvider, Provider<StatsPostProvider> statsPostProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.detailUseCaseProvider = detailUseCaseProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.statsPostProvider = statsPostProvider;
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
  }

  @Override
  public StatsDetailViewModel get() {
    return newInstance(mainDispatcherProvider.get(), detailUseCaseProvider.get(), statsSiteProvider.get(), statsPostProvider.get(), networkUtilsWrapperProvider.get());
  }

  public static StatsDetailViewModel_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<BaseListUseCase> detailUseCaseProvider,
      Provider<StatsSiteProvider> statsSiteProvider, Provider<StatsPostProvider> statsPostProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider) {
    return new StatsDetailViewModel_Factory(mainDispatcherProvider, detailUseCaseProvider, statsSiteProvider, statsPostProvider, networkUtilsWrapperProvider);
  }

  public static StatsDetailViewModel newInstance(CoroutineDispatcher mainDispatcher,
      BaseListUseCase detailUseCase, StatsSiteProvider statsSiteProvider,
      StatsPostProvider statsPostProvider, NetworkUtilsWrapper networkUtilsWrapper) {
    return new StatsDetailViewModel(mainDispatcher, detailUseCase, statsSiteProvider, statsPostProvider, networkUtilsWrapper);
  }
}
