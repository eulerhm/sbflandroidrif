package org.wordpress.android.viewmodel.main;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.store.bloggingprompts.BloggingPromptsStore;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository;
import org.wordpress.android.ui.mysite.tabs.MySiteDefaultTabExperiment;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.ui.whatsnew.FeatureAnnouncementProvider;
import org.wordpress.android.util.BuildConfigWrapper;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;
import org.wordpress.android.util.config.BloggingPromptsFeatureConfig;

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
public final class WPMainActivityViewModel_Factory implements Factory<WPMainActivityViewModel> {
  private final Provider<FeatureAnnouncementProvider> featureAnnouncementProvider;

  private final Provider<BuildConfigWrapper> buildConfigWrapperProvider;

  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  private final Provider<QuickStartRepository> quickStartRepositoryProvider;

  private final Provider<SelectedSiteRepository> selectedSiteRepositoryProvider;

  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<MySiteDefaultTabExperiment> mySiteDefaultTabExperimentProvider;

  private final Provider<BloggingPromptsFeatureConfig> bloggingPromptsFeatureConfigProvider;

  private final Provider<BloggingPromptsStore> bloggingPromptsStoreProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  public WPMainActivityViewModel_Factory(
      Provider<FeatureAnnouncementProvider> featureAnnouncementProvider,
      Provider<BuildConfigWrapper> buildConfigWrapperProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<QuickStartRepository> quickStartRepositoryProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<AccountStore> accountStoreProvider, Provider<SiteStore> siteStoreProvider,
      Provider<MySiteDefaultTabExperiment> mySiteDefaultTabExperimentProvider,
      Provider<BloggingPromptsFeatureConfig> bloggingPromptsFeatureConfigProvider,
      Provider<BloggingPromptsStore> bloggingPromptsStoreProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider) {
    this.featureAnnouncementProvider = featureAnnouncementProvider;
    this.buildConfigWrapperProvider = buildConfigWrapperProvider;
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.quickStartRepositoryProvider = quickStartRepositoryProvider;
    this.selectedSiteRepositoryProvider = selectedSiteRepositoryProvider;
    this.accountStoreProvider = accountStoreProvider;
    this.siteStoreProvider = siteStoreProvider;
    this.mySiteDefaultTabExperimentProvider = mySiteDefaultTabExperimentProvider;
    this.bloggingPromptsFeatureConfigProvider = bloggingPromptsFeatureConfigProvider;
    this.bloggingPromptsStoreProvider = bloggingPromptsStoreProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
  }

  @Override
  public WPMainActivityViewModel get() {
    return newInstance(featureAnnouncementProvider.get(), buildConfigWrapperProvider.get(), appPrefsWrapperProvider.get(), analyticsTrackerProvider.get(), quickStartRepositoryProvider.get(), selectedSiteRepositoryProvider.get(), accountStoreProvider.get(), siteStoreProvider.get(), mySiteDefaultTabExperimentProvider.get(), bloggingPromptsFeatureConfigProvider.get(), bloggingPromptsStoreProvider.get(), mainDispatcherProvider.get());
  }

  public static WPMainActivityViewModel_Factory create(
      Provider<FeatureAnnouncementProvider> featureAnnouncementProvider,
      Provider<BuildConfigWrapper> buildConfigWrapperProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<QuickStartRepository> quickStartRepositoryProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<AccountStore> accountStoreProvider, Provider<SiteStore> siteStoreProvider,
      Provider<MySiteDefaultTabExperiment> mySiteDefaultTabExperimentProvider,
      Provider<BloggingPromptsFeatureConfig> bloggingPromptsFeatureConfigProvider,
      Provider<BloggingPromptsStore> bloggingPromptsStoreProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider) {
    return new WPMainActivityViewModel_Factory(featureAnnouncementProvider, buildConfigWrapperProvider, appPrefsWrapperProvider, analyticsTrackerProvider, quickStartRepositoryProvider, selectedSiteRepositoryProvider, accountStoreProvider, siteStoreProvider, mySiteDefaultTabExperimentProvider, bloggingPromptsFeatureConfigProvider, bloggingPromptsStoreProvider, mainDispatcherProvider);
  }

  public static WPMainActivityViewModel newInstance(
      FeatureAnnouncementProvider featureAnnouncementProvider,
      BuildConfigWrapper buildConfigWrapper, AppPrefsWrapper appPrefsWrapper,
      AnalyticsTrackerWrapper analyticsTracker, QuickStartRepository quickStartRepository,
      SelectedSiteRepository selectedSiteRepository, AccountStore accountStore, SiteStore siteStore,
      MySiteDefaultTabExperiment mySiteDefaultTabExperiment,
      BloggingPromptsFeatureConfig bloggingPromptsFeatureConfig,
      BloggingPromptsStore bloggingPromptsStore, CoroutineDispatcher mainDispatcher) {
    return new WPMainActivityViewModel(featureAnnouncementProvider, buildConfigWrapper, appPrefsWrapper, analyticsTracker, quickStartRepository, selectedSiteRepository, accountStore, siteStore, mySiteDefaultTabExperiment, bloggingPromptsFeatureConfig, bloggingPromptsStore, mainDispatcher);
  }
}
