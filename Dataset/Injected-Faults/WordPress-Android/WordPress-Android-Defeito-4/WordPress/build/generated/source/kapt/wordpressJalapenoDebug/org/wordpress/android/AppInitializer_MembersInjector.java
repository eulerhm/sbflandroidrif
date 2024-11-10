package org.wordpress.android;

import com.android.volley.RequestQueue;
import com.automattic.android.tracks.crashlogging.CrashLogging;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Named;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineScope;
import org.wordpress.android.analytics.Tracker;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.network.rest.wpcom.site.PrivateAtomicCookie;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.MediaStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.fluxc.store.StatsStore;
import org.wordpress.android.fluxc.tools.FluxCImageLoader;
import org.wordpress.android.networking.OAuthAuthenticator;
import org.wordpress.android.support.ZendeskHelper;
import org.wordpress.android.ui.debug.cookies.DebugCookieManager;
import org.wordpress.android.ui.mysite.SelectedSiteRepository;
import org.wordpress.android.ui.mysite.tabs.MySiteDefaultTabExperiment;
import org.wordpress.android.ui.notifications.SystemNotificationsTracker;
import org.wordpress.android.ui.posts.editor.ImageEditorFileUtils;
import org.wordpress.android.ui.posts.editor.ImageEditorTracker;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.stats.refresh.lists.widget.WidgetUpdater;
import org.wordpress.android.ui.stories.media.StoryMediaSaveUploadBridge;
import org.wordpress.android.ui.uploads.UploadStarter;
import org.wordpress.android.util.EncryptedLogging;
import org.wordpress.android.util.config.AppConfig;
import org.wordpress.android.util.experiments.ExPlat;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.workers.WordPressWorkersFactory;

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
public final class AppInitializer_MembersInjector implements MembersInjector<AppInitializer> {
  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<MediaStore> mediaStoreProvider;

  private final Provider<ZendeskHelper> zendeskHelperProvider;

  private final Provider<UploadStarter> uploadStarterProvider;

  private final Provider<WidgetUpdater.StatsWidgetUpdaters> statsWidgetUpdatersProvider;

  private final Provider<StatsStore> statsStoreProvider;

  private final Provider<SystemNotificationsTracker> systemNotificationsTrackerProvider;

  private final Provider<ReaderTracker> readerTrackerProvider;

  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<PrivateAtomicCookie> privateAtomicCookieProvider;

  private final Provider<ImageEditorTracker> imageEditorTrackerProvider;

  private final Provider<StoryMediaSaveUploadBridge> storyMediaSaveUploadBridgeProvider;

  private final Provider<CrashLogging> crashLoggingProvider;

  private final Provider<EncryptedLogging> encryptedLoggingProvider;

  private final Provider<AppConfig> appConfigProvider;

  private final Provider<ImageEditorFileUtils> imageEditorFileUtilsProvider;

  private final Provider<ExPlat> exPlatProvider;

  private final Provider<WordPressWorkersFactory> wordPressWorkerFactoryProvider;

  private final Provider<DebugCookieManager> debugCookieManagerProvider;

  private final Provider<CoroutineScope> appScopeProvider;

  private final Provider<SelectedSiteRepository> selectedSiteRepositoryProvider;

  private final Provider<MySiteDefaultTabExperiment> mySiteDefaultTabExperimentProvider;

  private final Provider<Tracker> trackerProvider;

  private final Provider<RequestQueue> requestQueueProvider;

  private final Provider<FluxCImageLoader> imageLoaderProvider;

  private final Provider<OAuthAuthenticator> oAuthAuthenticatorProvider;

  public AppInitializer_MembersInjector(Provider<Dispatcher> dispatcherProvider,
      Provider<AccountStore> accountStoreProvider, Provider<SiteStore> siteStoreProvider,
      Provider<MediaStore> mediaStoreProvider, Provider<ZendeskHelper> zendeskHelperProvider,
      Provider<UploadStarter> uploadStarterProvider,
      Provider<WidgetUpdater.StatsWidgetUpdaters> statsWidgetUpdatersProvider,
      Provider<StatsStore> statsStoreProvider,
      Provider<SystemNotificationsTracker> systemNotificationsTrackerProvider,
      Provider<ReaderTracker> readerTrackerProvider, Provider<ImageManager> imageManagerProvider,
      Provider<PrivateAtomicCookie> privateAtomicCookieProvider,
      Provider<ImageEditorTracker> imageEditorTrackerProvider,
      Provider<StoryMediaSaveUploadBridge> storyMediaSaveUploadBridgeProvider,
      Provider<CrashLogging> crashLoggingProvider,
      Provider<EncryptedLogging> encryptedLoggingProvider, Provider<AppConfig> appConfigProvider,
      Provider<ImageEditorFileUtils> imageEditorFileUtilsProvider, Provider<ExPlat> exPlatProvider,
      Provider<WordPressWorkersFactory> wordPressWorkerFactoryProvider,
      Provider<DebugCookieManager> debugCookieManagerProvider,
      Provider<CoroutineScope> appScopeProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<MySiteDefaultTabExperiment> mySiteDefaultTabExperimentProvider,
      Provider<Tracker> trackerProvider, Provider<RequestQueue> requestQueueProvider,
      Provider<FluxCImageLoader> imageLoaderProvider,
      Provider<OAuthAuthenticator> oAuthAuthenticatorProvider) {
    this.dispatcherProvider = dispatcherProvider;
    this.accountStoreProvider = accountStoreProvider;
    this.siteStoreProvider = siteStoreProvider;
    this.mediaStoreProvider = mediaStoreProvider;
    this.zendeskHelperProvider = zendeskHelperProvider;
    this.uploadStarterProvider = uploadStarterProvider;
    this.statsWidgetUpdatersProvider = statsWidgetUpdatersProvider;
    this.statsStoreProvider = statsStoreProvider;
    this.systemNotificationsTrackerProvider = systemNotificationsTrackerProvider;
    this.readerTrackerProvider = readerTrackerProvider;
    this.imageManagerProvider = imageManagerProvider;
    this.privateAtomicCookieProvider = privateAtomicCookieProvider;
    this.imageEditorTrackerProvider = imageEditorTrackerProvider;
    this.storyMediaSaveUploadBridgeProvider = storyMediaSaveUploadBridgeProvider;
    this.crashLoggingProvider = crashLoggingProvider;
    this.encryptedLoggingProvider = encryptedLoggingProvider;
    this.appConfigProvider = appConfigProvider;
    this.imageEditorFileUtilsProvider = imageEditorFileUtilsProvider;
    this.exPlatProvider = exPlatProvider;
    this.wordPressWorkerFactoryProvider = wordPressWorkerFactoryProvider;
    this.debugCookieManagerProvider = debugCookieManagerProvider;
    this.appScopeProvider = appScopeProvider;
    this.selectedSiteRepositoryProvider = selectedSiteRepositoryProvider;
    this.mySiteDefaultTabExperimentProvider = mySiteDefaultTabExperimentProvider;
    this.trackerProvider = trackerProvider;
    this.requestQueueProvider = requestQueueProvider;
    this.imageLoaderProvider = imageLoaderProvider;
    this.oAuthAuthenticatorProvider = oAuthAuthenticatorProvider;
  }

  public static MembersInjector<AppInitializer> create(Provider<Dispatcher> dispatcherProvider,
      Provider<AccountStore> accountStoreProvider, Provider<SiteStore> siteStoreProvider,
      Provider<MediaStore> mediaStoreProvider, Provider<ZendeskHelper> zendeskHelperProvider,
      Provider<UploadStarter> uploadStarterProvider,
      Provider<WidgetUpdater.StatsWidgetUpdaters> statsWidgetUpdatersProvider,
      Provider<StatsStore> statsStoreProvider,
      Provider<SystemNotificationsTracker> systemNotificationsTrackerProvider,
      Provider<ReaderTracker> readerTrackerProvider, Provider<ImageManager> imageManagerProvider,
      Provider<PrivateAtomicCookie> privateAtomicCookieProvider,
      Provider<ImageEditorTracker> imageEditorTrackerProvider,
      Provider<StoryMediaSaveUploadBridge> storyMediaSaveUploadBridgeProvider,
      Provider<CrashLogging> crashLoggingProvider,
      Provider<EncryptedLogging> encryptedLoggingProvider, Provider<AppConfig> appConfigProvider,
      Provider<ImageEditorFileUtils> imageEditorFileUtilsProvider, Provider<ExPlat> exPlatProvider,
      Provider<WordPressWorkersFactory> wordPressWorkerFactoryProvider,
      Provider<DebugCookieManager> debugCookieManagerProvider,
      Provider<CoroutineScope> appScopeProvider,
      Provider<SelectedSiteRepository> selectedSiteRepositoryProvider,
      Provider<MySiteDefaultTabExperiment> mySiteDefaultTabExperimentProvider,
      Provider<Tracker> trackerProvider, Provider<RequestQueue> requestQueueProvider,
      Provider<FluxCImageLoader> imageLoaderProvider,
      Provider<OAuthAuthenticator> oAuthAuthenticatorProvider) {
    return new AppInitializer_MembersInjector(dispatcherProvider, accountStoreProvider, siteStoreProvider, mediaStoreProvider, zendeskHelperProvider, uploadStarterProvider, statsWidgetUpdatersProvider, statsStoreProvider, systemNotificationsTrackerProvider, readerTrackerProvider, imageManagerProvider, privateAtomicCookieProvider, imageEditorTrackerProvider, storyMediaSaveUploadBridgeProvider, crashLoggingProvider, encryptedLoggingProvider, appConfigProvider, imageEditorFileUtilsProvider, exPlatProvider, wordPressWorkerFactoryProvider, debugCookieManagerProvider, appScopeProvider, selectedSiteRepositoryProvider, mySiteDefaultTabExperimentProvider, trackerProvider, requestQueueProvider, imageLoaderProvider, oAuthAuthenticatorProvider);
  }

  @Override
  public void injectMembers(AppInitializer instance) {
    injectDispatcher(instance, dispatcherProvider.get());
    injectAccountStore(instance, accountStoreProvider.get());
    injectSiteStore(instance, siteStoreProvider.get());
    injectMediaStore(instance, mediaStoreProvider.get());
    injectZendeskHelper(instance, zendeskHelperProvider.get());
    injectUploadStarter(instance, uploadStarterProvider.get());
    injectStatsWidgetUpdaters(instance, statsWidgetUpdatersProvider.get());
    injectStatsStore(instance, statsStoreProvider.get());
    injectSystemNotificationsTracker(instance, systemNotificationsTrackerProvider.get());
    injectReaderTracker(instance, readerTrackerProvider.get());
    injectImageManager(instance, imageManagerProvider.get());
    injectPrivateAtomicCookie(instance, privateAtomicCookieProvider.get());
    injectImageEditorTracker(instance, imageEditorTrackerProvider.get());
    injectStoryMediaSaveUploadBridge(instance, storyMediaSaveUploadBridgeProvider.get());
    injectCrashLogging(instance, crashLoggingProvider.get());
    injectEncryptedLogging(instance, encryptedLoggingProvider.get());
    injectAppConfig(instance, appConfigProvider.get());
    injectImageEditorFileUtils(instance, imageEditorFileUtilsProvider.get());
    injectExPlat(instance, exPlatProvider.get());
    injectWordPressWorkerFactory(instance, wordPressWorkerFactoryProvider.get());
    injectDebugCookieManager(instance, debugCookieManagerProvider.get());
    injectAppScope(instance, appScopeProvider.get());
    injectSelectedSiteRepository(instance, selectedSiteRepositoryProvider.get());
    injectMySiteDefaultTabExperiment(instance, mySiteDefaultTabExperimentProvider.get());
    injectTracker(instance, trackerProvider.get());
    injectRequestQueue(instance, requestQueueProvider.get());
    injectImageLoader(instance, imageLoaderProvider.get());
    injectOAuthAuthenticator(instance, oAuthAuthenticatorProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.AppInitializer.dispatcher")
  public static void injectDispatcher(AppInitializer instance, Dispatcher dispatcher) {
    instance.dispatcher = dispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.AppInitializer.accountStore")
  public static void injectAccountStore(AppInitializer instance, AccountStore accountStore) {
    instance.accountStore = accountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.AppInitializer.siteStore")
  public static void injectSiteStore(AppInitializer instance, SiteStore siteStore) {
    instance.siteStore = siteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.AppInitializer.mediaStore")
  public static void injectMediaStore(AppInitializer instance, MediaStore mediaStore) {
    instance.mediaStore = mediaStore;
  }

  @InjectedFieldSignature("org.wordpress.android.AppInitializer.zendeskHelper")
  public static void injectZendeskHelper(AppInitializer instance, ZendeskHelper zendeskHelper) {
    instance.zendeskHelper = zendeskHelper;
  }

  @InjectedFieldSignature("org.wordpress.android.AppInitializer.uploadStarter")
  public static void injectUploadStarter(AppInitializer instance, UploadStarter uploadStarter) {
    instance.uploadStarter = uploadStarter;
  }

  @InjectedFieldSignature("org.wordpress.android.AppInitializer.statsWidgetUpdaters")
  public static void injectStatsWidgetUpdaters(AppInitializer instance,
      WidgetUpdater.StatsWidgetUpdaters statsWidgetUpdaters) {
    instance.statsWidgetUpdaters = statsWidgetUpdaters;
  }

  @InjectedFieldSignature("org.wordpress.android.AppInitializer.statsStore")
  public static void injectStatsStore(AppInitializer instance, StatsStore statsStore) {
    instance.statsStore = statsStore;
  }

  @InjectedFieldSignature("org.wordpress.android.AppInitializer.systemNotificationsTracker")
  public static void injectSystemNotificationsTracker(AppInitializer instance,
      SystemNotificationsTracker systemNotificationsTracker) {
    instance.systemNotificationsTracker = systemNotificationsTracker;
  }

  @InjectedFieldSignature("org.wordpress.android.AppInitializer.readerTracker")
  public static void injectReaderTracker(AppInitializer instance, ReaderTracker readerTracker) {
    instance.readerTracker = readerTracker;
  }

  @InjectedFieldSignature("org.wordpress.android.AppInitializer.imageManager")
  public static void injectImageManager(AppInitializer instance, ImageManager imageManager) {
    instance.imageManager = imageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.AppInitializer.privateAtomicCookie")
  public static void injectPrivateAtomicCookie(AppInitializer instance,
      PrivateAtomicCookie privateAtomicCookie) {
    instance.privateAtomicCookie = privateAtomicCookie;
  }

  @InjectedFieldSignature("org.wordpress.android.AppInitializer.imageEditorTracker")
  public static void injectImageEditorTracker(AppInitializer instance,
      ImageEditorTracker imageEditorTracker) {
    instance.imageEditorTracker = imageEditorTracker;
  }

  @InjectedFieldSignature("org.wordpress.android.AppInitializer.storyMediaSaveUploadBridge")
  public static void injectStoryMediaSaveUploadBridge(AppInitializer instance,
      StoryMediaSaveUploadBridge storyMediaSaveUploadBridge) {
    instance.storyMediaSaveUploadBridge = storyMediaSaveUploadBridge;
  }

  @InjectedFieldSignature("org.wordpress.android.AppInitializer.crashLogging")
  public static void injectCrashLogging(AppInitializer instance, CrashLogging crashLogging) {
    instance.crashLogging = crashLogging;
  }

  @InjectedFieldSignature("org.wordpress.android.AppInitializer.encryptedLogging")
  public static void injectEncryptedLogging(AppInitializer instance,
      EncryptedLogging encryptedLogging) {
    instance.encryptedLogging = encryptedLogging;
  }

  @InjectedFieldSignature("org.wordpress.android.AppInitializer.appConfig")
  public static void injectAppConfig(AppInitializer instance, AppConfig appConfig) {
    instance.appConfig = appConfig;
  }

  @InjectedFieldSignature("org.wordpress.android.AppInitializer.imageEditorFileUtils")
  public static void injectImageEditorFileUtils(AppInitializer instance,
      ImageEditorFileUtils imageEditorFileUtils) {
    instance.imageEditorFileUtils = imageEditorFileUtils;
  }

  @InjectedFieldSignature("org.wordpress.android.AppInitializer.exPlat")
  public static void injectExPlat(AppInitializer instance, ExPlat exPlat) {
    instance.exPlat = exPlat;
  }

  @InjectedFieldSignature("org.wordpress.android.AppInitializer.wordPressWorkerFactory")
  public static void injectWordPressWorkerFactory(AppInitializer instance,
      WordPressWorkersFactory wordPressWorkerFactory) {
    instance.wordPressWorkerFactory = wordPressWorkerFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.AppInitializer.debugCookieManager")
  public static void injectDebugCookieManager(AppInitializer instance,
      DebugCookieManager debugCookieManager) {
    instance.debugCookieManager = debugCookieManager;
  }

  @InjectedFieldSignature("org.wordpress.android.AppInitializer.appScope")
  @Named("APPLICATION_SCOPE")
  public static void injectAppScope(AppInitializer instance, CoroutineScope appScope) {
    instance.appScope = appScope;
  }

  @InjectedFieldSignature("org.wordpress.android.AppInitializer.selectedSiteRepository")
  public static void injectSelectedSiteRepository(AppInitializer instance,
      SelectedSiteRepository selectedSiteRepository) {
    instance.selectedSiteRepository = selectedSiteRepository;
  }

  @InjectedFieldSignature("org.wordpress.android.AppInitializer.mySiteDefaultTabExperiment")
  public static void injectMySiteDefaultTabExperiment(AppInitializer instance,
      MySiteDefaultTabExperiment mySiteDefaultTabExperiment) {
    instance.mySiteDefaultTabExperiment = mySiteDefaultTabExperiment;
  }

  @InjectedFieldSignature("org.wordpress.android.AppInitializer.tracker")
  public static void injectTracker(AppInitializer instance, Tracker tracker) {
    instance.tracker = tracker;
  }

  @InjectedFieldSignature("org.wordpress.android.AppInitializer.requestQueue")
  @Named("custom-ssl")
  public static void injectRequestQueue(AppInitializer instance, RequestQueue requestQueue) {
    instance.requestQueue = requestQueue;
  }

  @InjectedFieldSignature("org.wordpress.android.AppInitializer.imageLoader")
  public static void injectImageLoader(AppInitializer instance, FluxCImageLoader imageLoader) {
    instance.imageLoader = imageLoader;
  }

  @InjectedFieldSignature("org.wordpress.android.AppInitializer.oAuthAuthenticator")
  public static void injectOAuthAuthenticator(AppInitializer instance,
      OAuthAuthenticator oAuthAuthenticator) {
    instance.oAuthAuthenticator = oAuthAuthenticator;
  }
}
