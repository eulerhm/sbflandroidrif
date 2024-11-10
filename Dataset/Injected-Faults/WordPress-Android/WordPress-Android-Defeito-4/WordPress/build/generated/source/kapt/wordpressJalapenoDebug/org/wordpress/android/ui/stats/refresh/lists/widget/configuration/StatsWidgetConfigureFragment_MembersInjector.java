package org.wordpress.android.ui.stats.refresh.lists.widget.configuration;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerFragment_MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.ui.stats.refresh.lists.widget.alltime.AllTimeWidgetUpdater;
import org.wordpress.android.ui.stats.refresh.lists.widget.today.TodayWidgetUpdater;
import org.wordpress.android.ui.stats.refresh.lists.widget.views.ViewsWidgetUpdater;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;
import org.wordpress.android.util.image.ImageManager;

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
public final class StatsWidgetConfigureFragment_MembersInjector implements MembersInjector<StatsWidgetConfigureFragment> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<ViewsWidgetUpdater> viewsWidgetUpdaterProvider;

  private final Provider<AllTimeWidgetUpdater> allTimeWidgetUpdaterProvider;

  private final Provider<TodayWidgetUpdater> todayWidgetUpdaterProvider;

  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  public StatsWidgetConfigureFragment_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<ViewsWidgetUpdater> viewsWidgetUpdaterProvider,
      Provider<AllTimeWidgetUpdater> allTimeWidgetUpdaterProvider,
      Provider<TodayWidgetUpdater> todayWidgetUpdaterProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider, Provider<SiteStore> siteStoreProvider,
      Provider<ImageManager> imageManagerProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.viewsWidgetUpdaterProvider = viewsWidgetUpdaterProvider;
    this.allTimeWidgetUpdaterProvider = allTimeWidgetUpdaterProvider;
    this.todayWidgetUpdaterProvider = todayWidgetUpdaterProvider;
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
    this.siteStoreProvider = siteStoreProvider;
    this.imageManagerProvider = imageManagerProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
  }

  public static MembersInjector<StatsWidgetConfigureFragment> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<ViewsWidgetUpdater> viewsWidgetUpdaterProvider,
      Provider<AllTimeWidgetUpdater> allTimeWidgetUpdaterProvider,
      Provider<TodayWidgetUpdater> todayWidgetUpdaterProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider, Provider<SiteStore> siteStoreProvider,
      Provider<ImageManager> imageManagerProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    return new StatsWidgetConfigureFragment_MembersInjector(androidInjectorProvider, viewModelFactoryProvider, viewsWidgetUpdaterProvider, allTimeWidgetUpdaterProvider, todayWidgetUpdaterProvider, appPrefsWrapperProvider, siteStoreProvider, imageManagerProvider, analyticsTrackerWrapperProvider);
  }

  @Override
  public void injectMembers(StatsWidgetConfigureFragment instance) {
    DaggerFragment_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectViewsWidgetUpdater(instance, viewsWidgetUpdaterProvider.get());
    injectAllTimeWidgetUpdater(instance, allTimeWidgetUpdaterProvider.get());
    injectTodayWidgetUpdater(instance, todayWidgetUpdaterProvider.get());
    injectAppPrefsWrapper(instance, appPrefsWrapperProvider.get());
    injectSiteStore(instance, siteStoreProvider.get());
    injectImageManager(instance, imageManagerProvider.get());
    injectAnalyticsTrackerWrapper(instance, analyticsTrackerWrapperProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsWidgetConfigureFragment.viewModelFactory")
  public static void injectViewModelFactory(StatsWidgetConfigureFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsWidgetConfigureFragment.viewsWidgetUpdater")
  public static void injectViewsWidgetUpdater(StatsWidgetConfigureFragment instance,
      ViewsWidgetUpdater viewsWidgetUpdater) {
    instance.viewsWidgetUpdater = viewsWidgetUpdater;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsWidgetConfigureFragment.allTimeWidgetUpdater")
  public static void injectAllTimeWidgetUpdater(StatsWidgetConfigureFragment instance,
      AllTimeWidgetUpdater allTimeWidgetUpdater) {
    instance.allTimeWidgetUpdater = allTimeWidgetUpdater;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsWidgetConfigureFragment.todayWidgetUpdater")
  public static void injectTodayWidgetUpdater(StatsWidgetConfigureFragment instance,
      TodayWidgetUpdater todayWidgetUpdater) {
    instance.todayWidgetUpdater = todayWidgetUpdater;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsWidgetConfigureFragment.appPrefsWrapper")
  public static void injectAppPrefsWrapper(StatsWidgetConfigureFragment instance,
      AppPrefsWrapper appPrefsWrapper) {
    instance.appPrefsWrapper = appPrefsWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsWidgetConfigureFragment.siteStore")
  public static void injectSiteStore(StatsWidgetConfigureFragment instance, SiteStore siteStore) {
    instance.siteStore = siteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsWidgetConfigureFragment.imageManager")
  public static void injectImageManager(StatsWidgetConfigureFragment instance,
      ImageManager imageManager) {
    instance.imageManager = imageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.widget.configuration.StatsWidgetConfigureFragment.analyticsTrackerWrapper")
  public static void injectAnalyticsTrackerWrapper(StatsWidgetConfigureFragment instance,
      AnalyticsTrackerWrapper analyticsTrackerWrapper) {
    instance.analyticsTrackerWrapper = analyticsTrackerWrapper;
  }
}
