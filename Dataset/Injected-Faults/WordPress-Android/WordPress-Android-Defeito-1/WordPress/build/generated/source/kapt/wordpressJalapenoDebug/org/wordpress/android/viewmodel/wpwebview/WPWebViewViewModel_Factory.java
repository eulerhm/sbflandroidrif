package org.wordpress.android.viewmodel.wpwebview;

import androidx.lifecycle.LiveData;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.util.DisplayUtilsWrapper;
import org.wordpress.android.util.NetworkUtilsWrapper;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;
import org.wordpress.android.viewmodel.helpers.ConnectionStatus;

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
public final class WPWebViewViewModel_Factory implements Factory<WPWebViewViewModel> {
  private final Provider<DisplayUtilsWrapper> displayUtilsProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  private final Provider<LiveData<ConnectionStatus>> connectionStatusProvider;

  public WPWebViewViewModel_Factory(Provider<DisplayUtilsWrapper> displayUtilsProvider,
      Provider<NetworkUtilsWrapper> networkUtilsProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<LiveData<ConnectionStatus>> connectionStatusProvider) {
    this.displayUtilsProvider = displayUtilsProvider;
    this.networkUtilsProvider = networkUtilsProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
    this.connectionStatusProvider = connectionStatusProvider;
  }

  @Override
  public WPWebViewViewModel get() {
    return newInstance(displayUtilsProvider.get(), networkUtilsProvider.get(), analyticsTrackerWrapperProvider.get(), connectionStatusProvider.get());
  }

  public static WPWebViewViewModel_Factory create(
      Provider<DisplayUtilsWrapper> displayUtilsProvider,
      Provider<NetworkUtilsWrapper> networkUtilsProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<LiveData<ConnectionStatus>> connectionStatusProvider) {
    return new WPWebViewViewModel_Factory(displayUtilsProvider, networkUtilsProvider, analyticsTrackerWrapperProvider, connectionStatusProvider);
  }

  public static WPWebViewViewModel newInstance(DisplayUtilsWrapper displayUtils,
      NetworkUtilsWrapper networkUtils, AnalyticsTrackerWrapper analyticsTrackerWrapper,
      LiveData<ConnectionStatus> connectionStatus) {
    return new WPWebViewViewModel(displayUtils, networkUtils, analyticsTrackerWrapper, connectionStatus);
  }
}
