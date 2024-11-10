package org.wordpress.android.viewmodel.pages;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.PageStore;
import org.wordpress.android.fluxc.store.PostStore;
import org.wordpress.android.fluxc.store.SiteOptionsStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.utils.AppLogWrapper;
import org.wordpress.android.ui.posts.PostModelUploadStatusTracker;
import org.wordpress.android.ui.posts.PreviewStateHelper;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.ui.uploads.UploadStarter;
import org.wordpress.android.util.EventBusWrapper;
import org.wordpress.android.util.NetworkUtilsWrapper;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;

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
public final class PagesViewModel_Factory implements Factory<PagesViewModel> {
  private final Provider<PageStore> pageStoreProvider;

  private final Provider<PostStore> postStoreProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<ActionPerformer> actionPerformerProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsProvider;

  private final Provider<EventBusWrapper> eventBusWrapperProvider;

  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<PreviewStateHelper> previewStateHelperProvider;

  private final Provider<UploadStarter> uploadStarterProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider;

  private final Provider<AutoSaveConflictResolver> autoSaveConflictResolverProvider;

  private final Provider<PostModelUploadStatusTracker> uploadStatusTrackerProvider;

  private final Provider<PageListEventListener.Factory> pageListEventListenerFactoryProvider;

  private final Provider<SiteOptionsStore> siteOptionsStoreProvider;

  private final Provider<AppLogWrapper> appLogWrapperProvider;

  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<AppPrefsWrapper> prefsProvider;

  private final Provider<CoroutineDispatcher> uiDispatcherProvider;

  private final Provider<CoroutineDispatcher> defaultDispatcherProvider;

  public PagesViewModel_Factory(Provider<PageStore> pageStoreProvider,
      Provider<PostStore> postStoreProvider, Provider<Dispatcher> dispatcherProvider,
      Provider<ActionPerformer> actionPerformerProvider,
      Provider<NetworkUtilsWrapper> networkUtilsProvider,
      Provider<EventBusWrapper> eventBusWrapperProvider, Provider<SiteStore> siteStoreProvider,
      Provider<PreviewStateHelper> previewStateHelperProvider,
      Provider<UploadStarter> uploadStarterProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<AutoSaveConflictResolver> autoSaveConflictResolverProvider,
      Provider<PostModelUploadStatusTracker> uploadStatusTrackerProvider,
      Provider<PageListEventListener.Factory> pageListEventListenerFactoryProvider,
      Provider<SiteOptionsStore> siteOptionsStoreProvider,
      Provider<AppLogWrapper> appLogWrapperProvider, Provider<AccountStore> accountStoreProvider,
      Provider<AppPrefsWrapper> prefsProvider, Provider<CoroutineDispatcher> uiDispatcherProvider,
      Provider<CoroutineDispatcher> defaultDispatcherProvider) {
    this.pageStoreProvider = pageStoreProvider;
    this.postStoreProvider = postStoreProvider;
    this.dispatcherProvider = dispatcherProvider;
    this.actionPerformerProvider = actionPerformerProvider;
    this.networkUtilsProvider = networkUtilsProvider;
    this.eventBusWrapperProvider = eventBusWrapperProvider;
    this.siteStoreProvider = siteStoreProvider;
    this.previewStateHelperProvider = previewStateHelperProvider;
    this.uploadStarterProvider = uploadStarterProvider;
    this.analyticsTrackerProvider = analyticsTrackerProvider;
    this.autoSaveConflictResolverProvider = autoSaveConflictResolverProvider;
    this.uploadStatusTrackerProvider = uploadStatusTrackerProvider;
    this.pageListEventListenerFactoryProvider = pageListEventListenerFactoryProvider;
    this.siteOptionsStoreProvider = siteOptionsStoreProvider;
    this.appLogWrapperProvider = appLogWrapperProvider;
    this.accountStoreProvider = accountStoreProvider;
    this.prefsProvider = prefsProvider;
    this.uiDispatcherProvider = uiDispatcherProvider;
    this.defaultDispatcherProvider = defaultDispatcherProvider;
  }

  @Override
  public PagesViewModel get() {
    return newInstance(pageStoreProvider.get(), postStoreProvider.get(), dispatcherProvider.get(), actionPerformerProvider.get(), networkUtilsProvider.get(), eventBusWrapperProvider.get(), siteStoreProvider.get(), previewStateHelperProvider.get(), uploadStarterProvider.get(), analyticsTrackerProvider.get(), autoSaveConflictResolverProvider.get(), uploadStatusTrackerProvider.get(), pageListEventListenerFactoryProvider.get(), siteOptionsStoreProvider.get(), appLogWrapperProvider.get(), accountStoreProvider.get(), prefsProvider.get(), uiDispatcherProvider.get(), defaultDispatcherProvider.get());
  }

  public static PagesViewModel_Factory create(Provider<PageStore> pageStoreProvider,
      Provider<PostStore> postStoreProvider, Provider<Dispatcher> dispatcherProvider,
      Provider<ActionPerformer> actionPerformerProvider,
      Provider<NetworkUtilsWrapper> networkUtilsProvider,
      Provider<EventBusWrapper> eventBusWrapperProvider, Provider<SiteStore> siteStoreProvider,
      Provider<PreviewStateHelper> previewStateHelperProvider,
      Provider<UploadStarter> uploadStarterProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerProvider,
      Provider<AutoSaveConflictResolver> autoSaveConflictResolverProvider,
      Provider<PostModelUploadStatusTracker> uploadStatusTrackerProvider,
      Provider<PageListEventListener.Factory> pageListEventListenerFactoryProvider,
      Provider<SiteOptionsStore> siteOptionsStoreProvider,
      Provider<AppLogWrapper> appLogWrapperProvider, Provider<AccountStore> accountStoreProvider,
      Provider<AppPrefsWrapper> prefsProvider, Provider<CoroutineDispatcher> uiDispatcherProvider,
      Provider<CoroutineDispatcher> defaultDispatcherProvider) {
    return new PagesViewModel_Factory(pageStoreProvider, postStoreProvider, dispatcherProvider, actionPerformerProvider, networkUtilsProvider, eventBusWrapperProvider, siteStoreProvider, previewStateHelperProvider, uploadStarterProvider, analyticsTrackerProvider, autoSaveConflictResolverProvider, uploadStatusTrackerProvider, pageListEventListenerFactoryProvider, siteOptionsStoreProvider, appLogWrapperProvider, accountStoreProvider, prefsProvider, uiDispatcherProvider, defaultDispatcherProvider);
  }

  public static PagesViewModel newInstance(PageStore pageStore, PostStore postStore,
      Dispatcher dispatcher, ActionPerformer actionPerformer, NetworkUtilsWrapper networkUtils,
      EventBusWrapper eventBusWrapper, SiteStore siteStore, PreviewStateHelper previewStateHelper,
      UploadStarter uploadStarter, AnalyticsTrackerWrapper analyticsTracker,
      AutoSaveConflictResolver autoSaveConflictResolver,
      PostModelUploadStatusTracker uploadStatusTracker,
      PageListEventListener.Factory pageListEventListenerFactory, SiteOptionsStore siteOptionsStore,
      AppLogWrapper appLogWrapper, AccountStore accountStore, AppPrefsWrapper prefs,
      CoroutineDispatcher uiDispatcher, CoroutineDispatcher defaultDispatcher) {
    return new PagesViewModel(pageStore, postStore, dispatcher, actionPerformer, networkUtils, eventBusWrapper, siteStore, previewStateHelper, uploadStarter, analyticsTracker, autoSaveConflictResolver, uploadStatusTracker, pageListEventListenerFactory, siteOptionsStore, appLogWrapper, accountStore, prefs, uiDispatcher, defaultDispatcher);
  }
}
