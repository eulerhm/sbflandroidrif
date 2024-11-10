package org.wordpress.android.ui.stats.refresh.lists.widget.minified;

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
public final class StatsMinifiedWidgetConfigureFragment_MembersInjector implements MembersInjector<StatsMinifiedWidgetConfigureFragment> {
  private final Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider;

  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<MinifiedWidgetUpdater> minifiedWidgetUpdaterProvider;

  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  public StatsMinifiedWidgetConfigureFragment_MembersInjector(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<MinifiedWidgetUpdater> minifiedWidgetUpdaterProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider, Provider<SiteStore> siteStoreProvider,
      Provider<ImageManager> imageManagerProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    this.androidInjectorProvider = androidInjectorProvider;
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.minifiedWidgetUpdaterProvider = minifiedWidgetUpdaterProvider;
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
    this.siteStoreProvider = siteStoreProvider;
    this.imageManagerProvider = imageManagerProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
  }

  public static MembersInjector<StatsMinifiedWidgetConfigureFragment> create(
      Provider<DispatchingAndroidInjector<Object>> androidInjectorProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<MinifiedWidgetUpdater> minifiedWidgetUpdaterProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider, Provider<SiteStore> siteStoreProvider,
      Provider<ImageManager> imageManagerProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider) {
    return new StatsMinifiedWidgetConfigureFragment_MembersInjector(androidInjectorProvider, viewModelFactoryProvider, minifiedWidgetUpdaterProvider, appPrefsWrapperProvider, siteStoreProvider, imageManagerProvider, analyticsTrackerWrapperProvider);
  }

  @Override
  public void injectMembers(StatsMinifiedWidgetConfigureFragment instance) {
    DaggerFragment_MembersInjector.injectAndroidInjector(instance, androidInjectorProvider.get());
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectMinifiedWidgetUpdater(instance, minifiedWidgetUpdaterProvider.get());
    injectAppPrefsWrapper(instance, appPrefsWrapperProvider.get());
    injectSiteStore(instance, siteStoreProvider.get());
    injectImageManager(instance, imageManagerProvider.get());
    injectAnalyticsTrackerWrapper(instance, analyticsTrackerWrapperProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.widget.minified.StatsMinifiedWidgetConfigureFragment.viewModelFactory")
  public static void injectViewModelFactory(StatsMinifiedWidgetConfigureFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.widget.minified.StatsMinifiedWidgetConfigureFragment.minifiedWidgetUpdater")
  public static void injectMinifiedWidgetUpdater(StatsMinifiedWidgetConfigureFragment instance,
      MinifiedWidgetUpdater minifiedWidgetUpdater) {
    instance.minifiedWidgetUpdater = minifiedWidgetUpdater;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.widget.minified.StatsMinifiedWidgetConfigureFragment.appPrefsWrapper")
  public static void injectAppPrefsWrapper(StatsMinifiedWidgetConfigureFragment instance,
      AppPrefsWrapper appPrefsWrapper) {
    instance.appPrefsWrapper = appPrefsWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.widget.minified.StatsMinifiedWidgetConfigureFragment.siteStore")
  public static void injectSiteStore(StatsMinifiedWidgetConfigureFragment instance,
      SiteStore siteStore) {
    instance.siteStore = siteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.widget.minified.StatsMinifiedWidgetConfigureFragment.imageManager")
  public static void injectImageManager(StatsMinifiedWidgetConfigureFragment instance,
      ImageManager imageManager) {
    instance.imageManager = imageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.stats.refresh.lists.widget.minified.StatsMinifiedWidgetConfigureFragment.analyticsTrackerWrapper")
  public static void injectAnalyticsTrackerWrapper(StatsMinifiedWidgetConfigureFragment instance,
      AnalyticsTrackerWrapper analyticsTrackerWrapper) {
    instance.analyticsTrackerWrapper = analyticsTrackerWrapper;
  }
}
