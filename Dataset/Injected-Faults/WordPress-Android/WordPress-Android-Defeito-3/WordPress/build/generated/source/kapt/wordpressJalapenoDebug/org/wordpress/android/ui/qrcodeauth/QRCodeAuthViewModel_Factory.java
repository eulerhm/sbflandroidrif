package org.wordpress.android.ui.qrcodeauth;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.qrcodeauth.QRCodeAuthStore;
import org.wordpress.android.util.NetworkUtilsWrapper;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;

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
public final class QRCodeAuthViewModel_Factory implements Factory<QRCodeAuthViewModel> {
  private final Provider<QRCodeAuthStore> authStoreProvider;

  private final Provider<QRCodeAuthUiStateMapper> uiStateMapperProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<QRCodeAuthValidator> validatorProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  public QRCodeAuthViewModel_Factory(Provider<QRCodeAuthStore> authStoreProvider,
      Provider<QRCodeAuthUiStateMapper> uiStateMapperProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<QRCodeAuthValidator> validatorProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    this.authStoreProvider = authStoreProvider;
    this.uiStateMapperProvider = uiStateMapperProvider;
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.validatorProvider = validatorProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
  }

  @Override
  public QRCodeAuthViewModel get() {
    return newInstance(authStoreProvider.get(), uiStateMapperProvider.get(), networkUtilsWrapperProvider.get(), validatorProvider.get(), analyticsTrackerWrapperProvider.get());
  }

  public static QRCodeAuthViewModel_Factory create(Provider<QRCodeAuthStore> authStoreProvider,
      Provider<QRCodeAuthUiStateMapper> uiStateMapperProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<QRCodeAuthValidator> validatorProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    return new QRCodeAuthViewModel_Factory(authStoreProvider, uiStateMapperProvider, networkUtilsWrapperProvider, validatorProvider, analyticsTrackerWrapperProvider);
  }

  public static QRCodeAuthViewModel newInstance(QRCodeAuthStore authStore,
      QRCodeAuthUiStateMapper uiStateMapper, NetworkUtilsWrapper networkUtilsWrapper,
      QRCodeAuthValidator validator, AnalyticsTrackerWrapper analyticsTrackerWrapper) {
    return new QRCodeAuthViewModel(authStore, uiStateMapper, networkUtilsWrapper, validator, analyticsTrackerWrapper);
  }
}
