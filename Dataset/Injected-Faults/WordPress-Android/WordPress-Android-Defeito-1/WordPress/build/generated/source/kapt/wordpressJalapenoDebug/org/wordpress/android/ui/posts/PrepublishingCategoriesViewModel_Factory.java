package org.wordpress.android.ui.posts;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.util.NetworkUtilsWrapper;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;

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
public final class PrepublishingCategoriesViewModel_Factory implements Factory<PrepublishingCategoriesViewModel> {
  private final Provider<GetCategoriesUseCase> getCategoriesUseCaseProvider;

  private final Provider<AddCategoryUseCase> addCategoryUseCaseProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public PrepublishingCategoriesViewModel_Factory(
      Provider<GetCategoriesUseCase> getCategoriesUseCaseProvider,
      Provider<AddCategoryUseCase> addCategoryUseCaseProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.getCategoriesUseCaseProvider = getCategoriesUseCaseProvider;
    this.addCategoryUseCaseProvider = addCategoryUseCaseProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public PrepublishingCategoriesViewModel get() {
    return newInstance(getCategoriesUseCaseProvider.get(), addCategoryUseCaseProvider.get(), analyticsTrackerWrapperProvider.get(), networkUtilsWrapperProvider.get(), bgDispatcherProvider.get());
  }

  public static PrepublishingCategoriesViewModel_Factory create(
      Provider<GetCategoriesUseCase> getCategoriesUseCaseProvider,
      Provider<AddCategoryUseCase> addCategoryUseCaseProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new PrepublishingCategoriesViewModel_Factory(getCategoriesUseCaseProvider, addCategoryUseCaseProvider, analyticsTrackerWrapperProvider, networkUtilsWrapperProvider, bgDispatcherProvider);
  }

  public static PrepublishingCategoriesViewModel newInstance(
      GetCategoriesUseCase getCategoriesUseCase, AddCategoryUseCase addCategoryUseCase,
      AnalyticsTrackerWrapper analyticsTrackerWrapper, NetworkUtilsWrapper networkUtilsWrapper,
      CoroutineDispatcher bgDispatcher) {
    return new PrepublishingCategoriesViewModel(getCategoriesUseCase, addCategoryUseCase, analyticsTrackerWrapper, networkUtilsWrapper, bgDispatcher);
  }
}
