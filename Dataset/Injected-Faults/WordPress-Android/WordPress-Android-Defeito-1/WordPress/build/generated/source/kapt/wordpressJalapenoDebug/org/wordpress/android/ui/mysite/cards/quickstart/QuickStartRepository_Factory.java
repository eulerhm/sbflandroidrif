package org.wordpress.android.ui.mysite.cards.quickstart;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.DynamicCardStore;
import org.wordpress.android.fluxc.store.QuickStartStore;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.ui.quickstart.QuickStartTracker;
import org.wordpress.android.ui.utils.HtmlMessageUtils;
import org.wordpress.android.util.BuildConfigWrapper;
import org.wordpress.android.util.EventBusWrapper;
import org.wordpress.android.util.HtmlCompatWrapper;
import org.wordpress.android.util.QuickStartUtilsWrapper;
import org.wordpress.android.util.config.MySiteDashboardTabsFeatureConfig;
import org.wordpress.android.util.config.QuickStartDynamicCardsFeatureConfig;
import org.wordpress.android.util.config.QuickStartExistingUsersV2FeatureConfig;
import org.wordpress.android.viewmodel.ContextProvider;
import org.wordpress.android.viewmodel.ResourceProvider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class QuickStartRepository_Factory implements Factory<QuickStartRepository> {
  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<QuickStartStore> quickStartStoreProvider;

  private final Provider<QuickStartUtilsWrapper> quickStartUtilsWrapperProvider;

  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  private final Provider<SelectedSiteRepository> selectedSiteRepositoryProvider;

  private final Provider<ResourceProvider> resourceProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<EventBusWrapper> eventBusProvider;

  private final Provider<DynamicCardStore> dynamicCardStoreProvider;

  private final Provider<HtmlCompatWrapper> htmlCompatProvider;

  private final Provider<QuickStartDynamicCardsFeatureConfig> quickStartDynamicCardsFeatureConfigProvider;

  private final Provider<ContextProvider> contextProvider;

  private final Provider<HtmlMessageUtils> htmlMessageUtilsProvider;

  private final Provider<QuickStartTracker> quickStartTrackerProvider;

  private final Provider<BuildConfigWrapper> buildConfigWrapperProvider;

  private final Provider<MySiteDashboardTabsFeatureConfig> mySiteDashboardTabsFeatureConfigProvider;

  private final Provider<QuickStartExistingUsersV2FeatureConfig> quickStartForExistingUsersV2FeatureConfigProvider;

  public QuickStartRepository_Factory(Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<QuickStartStore> quickStartStoreProvider,
      Provider<QuickStartUtilsWrapper> quickStartUtilsWrapperProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<ResourceProvider> resourceProvider, Provider<Dispatcher> dispatcherProvider,
      Provider<EventBusWrapper> eventBusProvider,
      Provider<DynamicCardStore> dynamicCardStoreProvider,
      Provider<HtmlCompatWrapper> htmlCompatProvider,
      Provider<QuickStartDynamicCardsFeatureConfig> quickStartDynamicCardsFeatureConfigProvider,
      Provider<ContextProvider> contextProvider,
      Provider<HtmlMessageUtils> htmlMessageUtilsProvider,
      Provider<QuickStartTracker> quickStartTrackerProvider,
      Provider<BuildConfigWrapper> buildConfigWrapperProvider,
      Provider<MySiteDashboardTabsFeatureConfig> mySiteDashboardTabsFeatureConfigProvider,
      Provider<QuickStartExistingUsersV2FeatureConfig> quickStartForExistingUsersV2FeatureConfigProvider) {
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.quickStartStoreProvider = quickStartStoreProvider;
    this.quickStartUtilsWrapperProvider = quickStartUtilsWrapperProvider;
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
    this.selectedSiteRepositoryProvider = selectedSiteRepositoryProvider;
    this.resourceProvider = resourceProvider;
    this.dispatcherProvider = dispatcherProvider;
    this.eventBusProvider = eventBusProvider;
    this.dynamicCardStoreProvider = dynamicCardStoreProvider;
    this.htmlCompatProvider = htmlCompatProvider;
    this.quickStartDynamicCardsFeatureConfigProvider = quickStartDynamicCardsFeatureConfigProvider;
    this.contextProvider = contextProvider;
    this.htmlMessageUtilsProvider = htmlMessageUtilsProvider;
    this.quickStartTrackerProvider = quickStartTrackerProvider;
    this.buildConfigWrapperProvider = buildConfigWrapperProvider;
    this.mySiteDashboardTabsFeatureConfigProvider = mySiteDashboardTabsFeatureConfigProvider;
    this.quickStartForExistingUsersV2FeatureConfigProvider = quickStartForExistingUsersV2FeatureConfigProvider;
  }

  @Override
  public QuickStartRepository get() {
    return newInstance(bgDispatcherProvider.get(), quickStartStoreProvider.get(), quickStartUtilsWrapperProvider.get(), appPrefsWrapperProvider.get(), selectedSiteRepositoryProvider.get(), resourceProvider.get(), dispatcherProvider.get(), eventBusProvider.get(), dynamicCardStoreProvider.get(), htmlCompatProvider.get(), quickStartDynamicCardsFeatureConfigProvider.get(), contextProvider.get(), htmlMessageUtilsProvider.get(), quickStartTrackerProvider.get(), buildConfigWrapperProvider.get(), mySiteDashboardTabsFeatureConfigProvider.get(), quickStartForExistingUsersV2FeatureConfigProvider.get());
  }

  public static QuickStartRepository_Factory create(
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<QuickStartStore> quickStartStoreProvider,
      Provider<QuickStartUtilsWrapper> quickStartUtilsWrapperProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<ResourceProvider> resourceProvider, Provider<Dispatcher> dispatcherProvider,
      Provider<EventBusWrapper> eventBusProvider,
      Provider<DynamicCardStore> dynamicCardStoreProvider,
      Provider<HtmlCompatWrapper> htmlCompatProvider,
      Provider<QuickStartDynamicCardsFeatureConfig> quickStartDynamicCardsFeatureConfigProvider,
      Provider<ContextProvider> contextProvider,
      Provider<HtmlMessageUtils> htmlMessageUtilsProvider,
      Provider<QuickStartTracker> quickStartTrackerProvider,
      Provider<BuildConfigWrapper> buildConfigWrapperProvider,
      Provider<MySiteDashboardTabsFeatureConfig> mySiteDashboardTabsFeatureConfigProvider,
      Provider<QuickStartExistingUsersV2FeatureConfig> quickStartForExistingUsersV2FeatureConfigProvider) {
    return new QuickStartRepository_Factory(bgDispatcherProvider, quickStartStoreProvider, quickStartUtilsWrapperProvider, appPrefsWrapperProvider, selectedSiteRepositoryProvider, resourceProvider, dispatcherProvider, eventBusProvider, dynamicCardStoreProvider, htmlCompatProvider, quickStartDynamicCardsFeatureConfigProvider, contextProvider, htmlMessageUtilsProvider, quickStartTrackerProvider, buildConfigWrapperProvider, mySiteDashboardTabsFeatureConfigProvider, quickStartForExistingUsersV2FeatureConfigProvider);
  }

  public static QuickStartRepository newInstance(CoroutineDispatcher bgDispatcher,
      QuickStartStore quickStartStore, QuickStartUtilsWrapper quickStartUtilsWrapper,
      AppPrefsWrapper appPrefsWrapper, SelectedSiteRepository selectedSiteRepository,
      ResourceProvider resourceProvider, Dispatcher dispatcher, EventBusWrapper eventBus,
      DynamicCardStore dynamicCardStore, HtmlCompatWrapper htmlCompat,
      QuickStartDynamicCardsFeatureConfig quickStartDynamicCardsFeatureConfig,
      ContextProvider contextProvider, HtmlMessageUtils htmlMessageUtils,
      QuickStartTracker quickStartTracker, BuildConfigWrapper buildConfigWrapper,
      MySiteDashboardTabsFeatureConfig mySiteDashboardTabsFeatureConfig,
      QuickStartExistingUsersV2FeatureConfig quickStartForExistingUsersV2FeatureConfig) {
    return new QuickStartRepository(bgDispatcher, quickStartStore, quickStartUtilsWrapper, appPrefsWrapper, selectedSiteRepository, resourceProvider, dispatcher, eventBus, dynamicCardStore, htmlCompat, quickStartDynamicCardsFeatureConfig, contextProvider, htmlMessageUtils, quickStartTracker, buildConfigWrapper, mySiteDashboardTabsFeatureConfig, quickStartForExistingUsersV2FeatureConfig);
  }
}
