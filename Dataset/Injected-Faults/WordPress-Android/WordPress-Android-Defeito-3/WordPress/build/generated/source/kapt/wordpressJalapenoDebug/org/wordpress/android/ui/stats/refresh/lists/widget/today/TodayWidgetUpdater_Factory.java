package org.wordpress.android.ui.stats.refresh.lists.widget.today;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.ui.stats.refresh.lists.widget.utils.WidgetUtils;
import org.wordpress.android.util.NetworkUtilsWrapper;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;
import org.wordpress.android.viewmodel.ResourceProvider;

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
public final class TodayWidgetUpdater_Factory implements Factory<TodayWidgetUpdater> {
  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<WidgetUtils> widgetUtilsProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  public TodayWidgetUpdater_Factory(Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<SiteStore> siteStoreProvider, Provider<AccountStore> accountStoreProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ResourceProvider> resourceProvider, Provider<WidgetUtils> widgetUtilsProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
    this.siteStoreProvider = siteStoreProvider;
    this.accountStoreProvider = accountStoreProvider;
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.resourceProvider = resourceProvider;
    this.widgetUtilsProvider = widgetUtilsProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
  }

  @Override
  public TodayWidgetUpdater get() {
    return newInstance(appPrefsWrapperProvider.get(), siteStoreProvider.get(), accountStoreProvider.get(), networkUtilsWrapperProvider.get(), resourceProvider.get(), widgetUtilsProvider.get(), analyticsTrackerWrapperProvider.get());
  }

  public static TodayWidgetUpdater_Factory create(Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<SiteStore> siteStoreProvider, Provider<AccountStore> accountStoreProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<ResourceProvider> resourceProvider, Provider<WidgetUtils> widgetUtilsProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    return new TodayWidgetUpdater_Factory(appPrefsWrapperProvider, siteStoreProvider, accountStoreProvider, networkUtilsWrapperProvider, resourceProvider, widgetUtilsProvider, analyticsTrackerWrapperProvider);
  }

  public static TodayWidgetUpdater newInstance(AppPrefsWrapper appPrefsWrapper, SiteStore siteStore,
      AccountStore accountStore, NetworkUtilsWrapper networkUtilsWrapper,
      ResourceProvider resourceProvider, WidgetUtils widgetUtils,
      AnalyticsTrackerWrapper analyticsTrackerWrapper) {
    return new TodayWidgetUpdater(appPrefsWrapper, siteStore, accountStore, networkUtilsWrapper, resourceProvider, widgetUtils, analyticsTrackerWrapper);
  }
}
