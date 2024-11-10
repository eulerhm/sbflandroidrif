package org.wordpress.android.ui.mysite;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.ui.mysite.cards.CardsBuilder;
import org.wordpress.android.ui.mysite.cards.DomainRegistrationCardShownTracker;
import org.wordpress.android.ui.mysite.cards.dashboard.CardsTracker;
import org.wordpress.android.ui.mysite.cards.dashboard.bloggingprompts.BloggingPromptsCardAnalyticsTracker;
import org.wordpress.android.ui.mysite.cards.quickstart.QuickStartCardBuilder;
import org.wordpress.android.ui.mysite.cards.quickstart.QuickStartRepository;
import org.wordpress.android.ui.mysite.cards.siteinfo.SiteInfoHeaderCardBuilder;
import org.wordpress.android.ui.mysite.dynamiccards.DynamicCardsBuilder;
import org.wordpress.android.ui.mysite.items.SiteItemsBuilder;
import org.wordpress.android.ui.mysite.items.SiteItemsTracker;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.ui.quickstart.QuickStartTracker;
import org.wordpress.android.util.BuildConfigWrapper;
import org.wordpress.android.util.DisplayUtilsWrapper;
import org.wordpress.android.util.FluxCUtilsWrapper;
import org.wordpress.android.util.MediaUtilsWrapper;
import org.wordpress.android.util.NetworkUtilsWrapper;
import org.wordpress.android.util.QuickStartUtilsWrapper;
import org.wordpress.android.util.SnackbarSequencer;
import org.wordpress.android.util.WPMediaUtilsWrapper;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;
import org.wordpress.android.util.config.BloggingPromptsFeatureConfig;
import org.wordpress.android.util.config.LandOnTheEditorFeatureConfig;
import org.wordpress.android.util.config.MySiteDashboardTabsFeatureConfig;
import org.wordpress.android.util.config.QuickStartDynamicCardsFeatureConfig;
import org.wordpress.android.viewmodel.ContextProvider;

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
public final class MySiteViewModel_Factory implements Factory<MySiteViewModel> {
  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  private final Provider<SiteItemsBuilder> siteItemsBuilderProvider;

  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<SelectedSiteRepository> selectedSiteRepositoryProvider;

  private final Provider<WPMediaUtilsWrapper> wpMediaUtilsWrapperProvider;

  private final Provider<MediaUtilsWrapper> mediaUtilsWrapperProvider;

  private final Provider<FluxCUtilsWrapper> fluxCUtilsWrapperProvider;

  private final Provider<ContextProvider> contextProvider;

  private final Provider<SiteIconUploadHandler> siteIconUploadHandlerProvider;

  private final Provider<SiteStoriesHandler> siteStoriesHandlerProvider;

  private final Provider<DisplayUtilsWrapper> displayUtilsWrapperProvider;

  private final Provider<QuickStartRepository> quickStartRepositoryProvider;

  private final Provider<QuickStartCardBuilder> quickStartCardBuilderProvider;

  private final Provider<SiteInfoHeaderCardBuilder> siteInfoHeaderCardBuilderProvider;

  private final Provider<HomePageDataLoader> homePageDataLoaderProvider;

  private final Provider<QuickStartDynamicCardsFeatureConfig> quickStartDynamicCardsFeatureConfigProvider;

  private final Provider<QuickStartUtilsWrapper> quickStartUtilsWrapperProvider;

  private final Provider<SnackbarSequencer> snackbarSequencerProvider;

  private final Provider<CardsBuilder> cardsBuilderProvider;

  private final Provider<DynamicCardsBuilder> dynamicCardsBuilderProvider;

  private final Provider<LandOnTheEditorFeatureConfig> landOnTheEditorFeatureConfigProvider;

  private final Provider<MySiteSourceManager> mySiteSourceManagerProvider;

  private final Provider<CardsTracker> cardsTrackerProvider;

  private final Provider<SiteItemsTracker> siteItemsTrackerProvider;

  private final Provider<DomainRegistrationCardShownTracker> domainRegistrationCardShownTrackerProvider;

  private final Provider<BuildConfigWrapper> buildConfigWrapperProvider;

  private final Provider<MySiteDashboardTabsFeatureConfig> mySiteDashboardTabsFeatureConfigProvider;

  private final Provider<BloggingPromptsFeatureConfig> bloggingPromptsFeatureConfigProvider;

  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  private final Provider<BloggingPromptsCardAnalyticsTracker> bloggingPromptsCardAnalyticsTrackerProvider;

  private final Provider<QuickStartTracker> quickStartTrackerProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  public MySiteViewModel_Factory(Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<SiteItemsBuilder> siteItemsBuilderProvider,
      Provider<AccountStore> accountStoreProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<WPMediaUtilsWrapper> wpMediaUtilsWrapperProvider,
      Provider<MediaUtilsWrapper> mediaUtilsWrapperProvider,
      Provider<FluxCUtilsWrapper> fluxCUtilsWrapperProvider,
      Provider<ContextProvider> contextProvider,
      Provider<SiteIconUploadHandler> siteIconUploadHandlerProvider,
      Provider<SiteStoriesHandler> siteStoriesHandlerProvider,
      Provider<DisplayUtilsWrapper> displayUtilsWrapperProvider,
      Provider<QuickStartRepository> quickStartRepositoryProvider,
      Provider<QuickStartCardBuilder> quickStartCardBuilderProvider,
      Provider<SiteInfoHeaderCardBuilder> siteInfoHeaderCardBuilderProvider,
      Provider<HomePageDataLoader> homePageDataLoaderProvider,
      Provider<QuickStartDynamicCardsFeatureConfig> quickStartDynamicCardsFeatureConfigProvider,
      Provider<QuickStartUtilsWrapper> quickStartUtilsWrapperProvider,
      Provider<SnackbarSequencer> snackbarSequencerProvider,
      Provider<CardsBuilder> cardsBuilderProvider,
      Provider<DynamicCardsBuilder> dynamicCardsBuilderProvider,
      Provider<LandOnTheEditorFeatureConfig> landOnTheEditorFeatureConfigProvider,
      Provider<MySiteSourceManager> mySiteSourceManagerProvider,
      Provider<CardsTracker> cardsTrackerProvider,
      Provider<SiteItemsTracker> siteItemsTrackerProvider,
      Provider<DomainRegistrationCardShownTracker> domainRegistrationCardShownTrackerProvider,
      Provider<BuildConfigWrapper> buildConfigWrapperProvider,
      Provider<MySiteDashboardTabsFeatureConfig> mySiteDashboardTabsFeatureConfigProvider,
      Provider<BloggingPromptsFeatureConfig> bloggingPromptsFeatureConfigProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<BloggingPromptsCardAnalyticsTracker> bloggingPromptsCardAnalyticsTrackerProvider,
      Provider<QuickStartTracker> quickStartTrackerProvider,
      Provider<Dispatcher> dispatcherProvider) {
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
    this.siteItemsBuilderProvider = siteItemsBuilderProvider;
    this.accountStoreProvider = accountStoreProvider;
    this.selectedSiteRepositoryProvider = selectedSiteRepositoryProvider;
    this.wpMediaUtilsWrapperProvider = wpMediaUtilsWrapperProvider;
    this.mediaUtilsWrapperProvider = mediaUtilsWrapperProvider;
    this.fluxCUtilsWrapperProvider = fluxCUtilsWrapperProvider;
    this.contextProvider = contextProvider;
    this.siteIconUploadHandlerProvider = siteIconUploadHandlerProvider;
    this.siteStoriesHandlerProvider = siteStoriesHandlerProvider;
    this.displayUtilsWrapperProvider = displayUtilsWrapperProvider;
    this.quickStartRepositoryProvider = quickStartRepositoryProvider;
    this.quickStartCardBuilderProvider = quickStartCardBuilderProvider;
    this.siteInfoHeaderCardBuilderProvider = siteInfoHeaderCardBuilderProvider;
    this.homePageDataLoaderProvider = homePageDataLoaderProvider;
    this.quickStartDynamicCardsFeatureConfigProvider = quickStartDynamicCardsFeatureConfigProvider;
    this.quickStartUtilsWrapperProvider = quickStartUtilsWrapperProvider;
    this.snackbarSequencerProvider = snackbarSequencerProvider;
    this.cardsBuilderProvider = cardsBuilderProvider;
    this.dynamicCardsBuilderProvider = dynamicCardsBuilderProvider;
    this.landOnTheEditorFeatureConfigProvider = landOnTheEditorFeatureConfigProvider;
    this.mySiteSourceManagerProvider = mySiteSourceManagerProvider;
    this.cardsTrackerProvider = cardsTrackerProvider;
    this.siteItemsTrackerProvider = siteItemsTrackerProvider;
    this.domainRegistrationCardShownTrackerProvider = domainRegistrationCardShownTrackerProvider;
    this.buildConfigWrapperProvider = buildConfigWrapperProvider;
    this.mySiteDashboardTabsFeatureConfigProvider = mySiteDashboardTabsFeatureConfigProvider;
    this.bloggingPromptsFeatureConfigProvider = bloggingPromptsFeatureConfigProvider;
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
    this.bloggingPromptsCardAnalyticsTrackerProvider = bloggingPromptsCardAnalyticsTrackerProvider;
    this.quickStartTrackerProvider = quickStartTrackerProvider;
    this.dispatcherProvider = dispatcherProvider;
  }

  @Override
  public MySiteViewModel get() {
    return newInstance(networkUtilsWrapperProvider.get(), mainDispatcherProvider.get(), bgDispatcherProvider.get(), analyticsTrackerWrapperProvider.get(), siteItemsBuilderProvider.get(), accountStoreProvider.get(), selectedSiteRepositoryProvider.get(), wpMediaUtilsWrapperProvider.get(), mediaUtilsWrapperProvider.get(), fluxCUtilsWrapperProvider.get(), contextProvider.get(), siteIconUploadHandlerProvider.get(), siteStoriesHandlerProvider.get(), displayUtilsWrapperProvider.get(), quickStartRepositoryProvider.get(), quickStartCardBuilderProvider.get(), siteInfoHeaderCardBuilderProvider.get(), homePageDataLoaderProvider.get(), quickStartDynamicCardsFeatureConfigProvider.get(), quickStartUtilsWrapperProvider.get(), snackbarSequencerProvider.get(), cardsBuilderProvider.get(), dynamicCardsBuilderProvider.get(), landOnTheEditorFeatureConfigProvider.get(), mySiteSourceManagerProvider.get(), cardsTrackerProvider.get(), siteItemsTrackerProvider.get(), domainRegistrationCardShownTrackerProvider.get(), buildConfigWrapperProvider.get(), mySiteDashboardTabsFeatureConfigProvider.get(), bloggingPromptsFeatureConfigProvider.get(), appPrefsWrapperProvider.get(), bloggingPromptsCardAnalyticsTrackerProvider.get(), quickStartTrackerProvider.get(), dispatcherProvider.get());
  }

  public static MySiteViewModel_Factory create(
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<SiteItemsBuilder> siteItemsBuilderProvider,
      Provider<AccountStore> accountStoreProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<WPMediaUtilsWrapper> wpMediaUtilsWrapperProvider,
      Provider<MediaUtilsWrapper> mediaUtilsWrapperProvider,
      Provider<FluxCUtilsWrapper> fluxCUtilsWrapperProvider,
      Provider<ContextProvider> contextProvider,
      Provider<SiteIconUploadHandler> siteIconUploadHandlerProvider,
      Provider<SiteStoriesHandler> siteStoriesHandlerProvider,
      Provider<DisplayUtilsWrapper> displayUtilsWrapperProvider,
      Provider<QuickStartRepository> quickStartRepositoryProvider,
      Provider<QuickStartCardBuilder> quickStartCardBuilderProvider,
      Provider<SiteInfoHeaderCardBuilder> siteInfoHeaderCardBuilderProvider,
      Provider<HomePageDataLoader> homePageDataLoaderProvider,
      Provider<QuickStartDynamicCardsFeatureConfig> quickStartDynamicCardsFeatureConfigProvider,
      Provider<QuickStartUtilsWrapper> quickStartUtilsWrapperProvider,
      Provider<SnackbarSequencer> snackbarSequencerProvider,
      Provider<CardsBuilder> cardsBuilderProvider,
      Provider<DynamicCardsBuilder> dynamicCardsBuilderProvider,
      Provider<LandOnTheEditorFeatureConfig> landOnTheEditorFeatureConfigProvider,
      Provider<MySiteSourceManager> mySiteSourceManagerProvider,
      Provider<CardsTracker> cardsTrackerProvider,
      Provider<SiteItemsTracker> siteItemsTrackerProvider,
      Provider<DomainRegistrationCardShownTracker> domainRegistrationCardShownTrackerProvider,
      Provider<BuildConfigWrapper> buildConfigWrapperProvider,
      Provider<MySiteDashboardTabsFeatureConfig> mySiteDashboardTabsFeatureConfigProvider,
      Provider<BloggingPromptsFeatureConfig> bloggingPromptsFeatureConfigProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<BloggingPromptsCardAnalyticsTracker> bloggingPromptsCardAnalyticsTrackerProvider,
      Provider<QuickStartTracker> quickStartTrackerProvider,
      Provider<Dispatcher> dispatcherProvider) {
    return new MySiteViewModel_Factory(networkUtilsWrapperProvider, mainDispatcherProvider, bgDispatcherProvider, analyticsTrackerWrapperProvider, siteItemsBuilderProvider, accountStoreProvider, selectedSiteRepositoryProvider, wpMediaUtilsWrapperProvider, mediaUtilsWrapperProvider, fluxCUtilsWrapperProvider, contextProvider, siteIconUploadHandlerProvider, siteStoriesHandlerProvider, displayUtilsWrapperProvider, quickStartRepositoryProvider, quickStartCardBuilderProvider, siteInfoHeaderCardBuilderProvider, homePageDataLoaderProvider, quickStartDynamicCardsFeatureConfigProvider, quickStartUtilsWrapperProvider, snackbarSequencerProvider, cardsBuilderProvider, dynamicCardsBuilderProvider, landOnTheEditorFeatureConfigProvider, mySiteSourceManagerProvider, cardsTrackerProvider, siteItemsTrackerProvider, domainRegistrationCardShownTrackerProvider, buildConfigWrapperProvider, mySiteDashboardTabsFeatureConfigProvider, bloggingPromptsFeatureConfigProvider, appPrefsWrapperProvider, bloggingPromptsCardAnalyticsTrackerProvider, quickStartTrackerProvider, dispatcherProvider);
  }

  public static MySiteViewModel newInstance(NetworkUtilsWrapper networkUtilsWrapper,
      CoroutineDispatcher mainDispatcher, CoroutineDispatcher bgDispatcher,
      AnalyticsTrackerWrapper analyticsTrackerWrapper, SiteItemsBuilder siteItemsBuilder,
      AccountStore accountStore, SelectedSiteRepository selectedSiteRepository,
      WPMediaUtilsWrapper wpMediaUtilsWrapper, MediaUtilsWrapper mediaUtilsWrapper,
      FluxCUtilsWrapper fluxCUtilsWrapper, ContextProvider contextProvider,
      SiteIconUploadHandler siteIconUploadHandler, SiteStoriesHandler siteStoriesHandler,
      DisplayUtilsWrapper displayUtilsWrapper, QuickStartRepository quickStartRepository,
      QuickStartCardBuilder quickStartCardBuilder,
      SiteInfoHeaderCardBuilder siteInfoHeaderCardBuilder, HomePageDataLoader homePageDataLoader,
      QuickStartDynamicCardsFeatureConfig quickStartDynamicCardsFeatureConfig,
      QuickStartUtilsWrapper quickStartUtilsWrapper, SnackbarSequencer snackbarSequencer,
      CardsBuilder cardsBuilder, DynamicCardsBuilder dynamicCardsBuilder,
      LandOnTheEditorFeatureConfig landOnTheEditorFeatureConfig,
      MySiteSourceManager mySiteSourceManager, CardsTracker cardsTracker,
      SiteItemsTracker siteItemsTracker,
      DomainRegistrationCardShownTracker domainRegistrationCardShownTracker,
      BuildConfigWrapper buildConfigWrapper,
      MySiteDashboardTabsFeatureConfig mySiteDashboardTabsFeatureConfig,
      BloggingPromptsFeatureConfig bloggingPromptsFeatureConfig, AppPrefsWrapper appPrefsWrapper,
      BloggingPromptsCardAnalyticsTracker bloggingPromptsCardAnalyticsTracker,
      QuickStartTracker quickStartTracker, Dispatcher dispatcher) {
    return new MySiteViewModel(networkUtilsWrapper, mainDispatcher, bgDispatcher, analyticsTrackerWrapper, siteItemsBuilder, accountStore, selectedSiteRepository, wpMediaUtilsWrapper, mediaUtilsWrapper, fluxCUtilsWrapper, contextProvider, siteIconUploadHandler, siteStoriesHandler, displayUtilsWrapper, quickStartRepository, quickStartCardBuilder, siteInfoHeaderCardBuilder, homePageDataLoader, quickStartDynamicCardsFeatureConfig, quickStartUtilsWrapper, snackbarSequencer, cardsBuilder, dynamicCardsBuilder, landOnTheEditorFeatureConfig, mySiteSourceManager, cardsTracker, siteItemsTracker, domainRegistrationCardShownTracker, buildConfigWrapper, mySiteDashboardTabsFeatureConfig, bloggingPromptsFeatureConfig, appPrefsWrapper, bloggingPromptsCardAnalyticsTracker, quickStartTracker, dispatcher);
  }
}
