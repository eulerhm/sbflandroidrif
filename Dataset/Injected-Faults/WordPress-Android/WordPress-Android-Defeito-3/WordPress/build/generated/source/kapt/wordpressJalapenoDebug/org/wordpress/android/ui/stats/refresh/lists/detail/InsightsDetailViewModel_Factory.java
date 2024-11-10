package org.wordpress.android.ui.stats.refresh.lists.detail;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.stats.refresh.lists.BaseListUseCase;
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
public final class InsightsDetailViewModel_Factory implements Factory<InsightsDetailViewModel> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<BaseListUseCase> detailUseCaseProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  public InsightsDetailViewModel_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<BaseListUseCase> detailUseCaseProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.detailUseCaseProvider = detailUseCaseProvider;
    this.statsSiteProvider = statsSiteProvider;
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
  }

  @Override
  public InsightsDetailViewModel get() {
    return newInstance(mainDispatcherProvider.get(), detailUseCaseProvider.get(), statsSiteProvider.get(), networkUtilsWrapperProvider.get());
  }

  public static InsightsDetailViewModel_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<BaseListUseCase> detailUseCaseProvider,
      Provider<StatsSiteProvider> statsSiteProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider) {
    return new InsightsDetailViewModel_Factory(mainDispatcherProvider, detailUseCaseProvider, statsSiteProvider, networkUtilsWrapperProvider);
  }

  public static InsightsDetailViewModel newInstance(CoroutineDispatcher mainDispatcher,
      BaseListUseCase detailUseCase, StatsSiteProvider statsSiteProvider,
      NetworkUtilsWrapper networkUtilsWrapper) {
    return new InsightsDetailViewModel(mainDispatcher, detailUseCase, statsSiteProvider, networkUtilsWrapper);
  }
}
