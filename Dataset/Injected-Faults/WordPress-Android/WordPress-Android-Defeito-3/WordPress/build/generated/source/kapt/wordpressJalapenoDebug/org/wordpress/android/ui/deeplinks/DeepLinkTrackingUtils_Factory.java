package org.wordpress.android.ui.deeplinks;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.deeplinks.handlers.DeepLinkHandlers;
import org.wordpress.android.util.analytics.AnalyticsUtilsWrapper;

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
public final class DeepLinkTrackingUtils_Factory implements Factory<DeepLinkTrackingUtils> {
  private final Provider<DeepLinkUriUtils> deepLinkUriUtilsProvider;

  private final Provider<DeepLinkHandlers> deepLinkHandlersProvider;

  private final Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider;

  public DeepLinkTrackingUtils_Factory(Provider<DeepLinkUriUtils> deepLinkUriUtilsProvider,
      Provider<DeepLinkHandlers> deepLinkHandlersProvider,
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider) {
    this.deepLinkUriUtilsProvider = deepLinkUriUtilsProvider;
    this.deepLinkHandlersProvider = deepLinkHandlersProvider;
    this.analyticsUtilsWrapperProvider = analyticsUtilsWrapperProvider;
  }

  @Override
  public DeepLinkTrackingUtils get() {
    return newInstance(deepLinkUriUtilsProvider.get(), deepLinkHandlersProvider.get(), analyticsUtilsWrapperProvider.get());
  }

  public static DeepLinkTrackingUtils_Factory create(
      Provider<DeepLinkUriUtils> deepLinkUriUtilsProvider,
      Provider<DeepLinkHandlers> deepLinkHandlersProvider,
      Provider<AnalyticsUtilsWrapper> analyticsUtilsWrapperProvider) {
    return new DeepLinkTrackingUtils_Factory(deepLinkUriUtilsProvider, deepLinkHandlersProvider, analyticsUtilsWrapperProvider);
  }

  public static DeepLinkTrackingUtils newInstance(DeepLinkUriUtils deepLinkUriUtils,
      DeepLinkHandlers deepLinkHandlers, AnalyticsUtilsWrapper analyticsUtilsWrapper) {
    return new DeepLinkTrackingUtils(deepLinkUriUtils, deepLinkHandlers, analyticsUtilsWrapper);
  }
}
