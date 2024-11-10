package org.wordpress.android.models.recommend;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.LocaleManagerWrapper;
import org.wordpress.android.util.NetworkUtilsWrapper;
import org.wordpress.android.util.RestClientProvider;
import org.wordpress.android.util.analytics.AnalyticsUtilsWrapper;
import org.wordpress.android.viewmodel.ContextProvider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class RecommendApiCallsProvider_Factory implements Factory<RecommendApiCallsProvider> {
  private final Provider<ContextProvider> contextProvider;

  private final Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<RestClientProvider> restClientProvider;

  private final Provider<LocaleManagerWrapper> localeManagerWrapperProvider;

  public RecommendApiCallsProvider_Factory(Provider<ContextProvider> contextProvider,
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<RestClientProvider> restClientProvider,
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider) {
    this.contextProvider = contextProvider;
    this.analyticsUtilsWrapperProvider = analyticsUtilsWrapperProvider;
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.restClientProvider = restClientProvider;
    this.localeManagerWrapperProvider = localeManagerWrapperProvider;
  }

  @Override
  public RecommendApiCallsProvider get() {
    return newInstance(contextProvider.get(), analyticsUtilsWrapperProvider.get(), networkUtilsWrapperProvider.get(), restClientProvider.get(), localeManagerWrapperProvider.get());
  }

  public static RecommendApiCallsProvider_Factory create(Provider<ContextProvider> contextProvider,
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<RestClientProvider> restClientProvider,
      Provider<LocaleManagerWrapper> localeManagerWrapperProvider) {
    return new RecommendApiCallsProvider_Factory(contextProvider, analyticsUtilsWrapperProvider, networkUtilsWrapperProvider, restClientProvider, localeManagerWrapperProvider);
  }

  public static RecommendApiCallsProvider newInstance(ContextProvider contextProvider,
      AnalyticsUtilsWrapper analyticsUtilsWrapper, NetworkUtilsWrapper networkUtilsWrapper,
      RestClientProvider restClientProvider, LocaleManagerWrapper localeManagerWrapper) {
    return new RecommendApiCallsProvider(contextProvider, analyticsUtilsWrapper, networkUtilsWrapper, restClientProvider, localeManagerWrapper);
  }
}
