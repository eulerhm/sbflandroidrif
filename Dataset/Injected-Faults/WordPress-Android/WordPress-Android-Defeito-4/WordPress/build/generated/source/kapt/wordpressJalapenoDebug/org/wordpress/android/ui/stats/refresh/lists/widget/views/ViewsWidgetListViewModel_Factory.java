package org.wordpress.android.ui.stats.refresh.lists.widget.views;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.store.stats.time.VisitsAndViewsStore;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.ui.stats.refresh.lists.sections.granular.usecases.OverviewMapper;
import org.wordpress.android.ui.stats.refresh.utils.StatsDateFormatter;
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
public final class ViewsWidgetListViewModel_Factory implements Factory<ViewsWidgetListViewModel> {
  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<VisitsAndViewsStore> visitsAndViewsStoreProvider;

  private final Provider<OverviewMapper> overviewMapperProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<StatsDateFormatter> statsDateFormatterProvider;

  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  public ViewsWidgetListViewModel_Factory(Provider<SiteStore> siteStoreProvider,
      Provider<VisitsAndViewsStore> visitsAndViewsStoreProvider,
      Provider<OverviewMapper> overviewMapperProvider, Provider<ResourceProvider> resourceProvider,
      Provider<StatsDateFormatter> statsDateFormatterProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider) {
    this.siteStoreProvider = siteStoreProvider;
    this.visitsAndViewsStoreProvider = visitsAndViewsStoreProvider;
    this.overviewMapperProvider = overviewMapperProvider;
    this.resourceProvider = resourceProvider;
    this.statsDateFormatterProvider = statsDateFormatterProvider;
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
  }

  @Override
  public ViewsWidgetListViewModel get() {
    return newInstance(siteStoreProvider.get(), visitsAndViewsStoreProvider.get(), overviewMapperProvider.get(), resourceProvider.get(), statsDateFormatterProvider.get(), appPrefsWrapperProvider.get());
  }

  public static ViewsWidgetListViewModel_Factory create(Provider<SiteStore> siteStoreProvider,
      Provider<VisitsAndViewsStore> visitsAndViewsStoreProvider,
      Provider<OverviewMapper> overviewMapperProvider, Provider<ResourceProvider> resourceProvider,
      Provider<StatsDateFormatter> statsDateFormatterProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider) {
    return new ViewsWidgetListViewModel_Factory(siteStoreProvider, visitsAndViewsStoreProvider, overviewMapperProvider, resourceProvider, statsDateFormatterProvider, appPrefsWrapperProvider);
  }

  public static ViewsWidgetListViewModel newInstance(SiteStore siteStore,
      VisitsAndViewsStore visitsAndViewsStore, OverviewMapper overviewMapper,
      ResourceProvider resourceProvider, StatsDateFormatter statsDateFormatter,
      AppPrefsWrapper appPrefsWrapper) {
    return new ViewsWidgetListViewModel(siteStore, visitsAndViewsStore, overviewMapper, resourceProvider, statsDateFormatter, appPrefsWrapper);
  }
}
