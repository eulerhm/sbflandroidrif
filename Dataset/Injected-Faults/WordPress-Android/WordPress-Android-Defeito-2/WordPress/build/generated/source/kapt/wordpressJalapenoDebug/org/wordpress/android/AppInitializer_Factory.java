package org.wordpress.android;

import android.app.Application;
import com.android.volley.RequestQueue;
import com.automattic.android.tracks.crashlogging.CrashLogging;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
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

@ScopeMetadata("javax.inject.Singleton")
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
public final class AppInitializer_Factory implements Factory<AppInitializer> {
  private final Provider<WellSqlInitializer> wellSqlInitializerProvider;

  private final Provider<Application> applicationProvider;

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

  public AppInitializer_Factory(Provider<WellSqlInitializer> wellSqlInitializerProvider,
      Provider<Application> applicationProvider, Provider<Dispatcher> dispatcherProvider,
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
    this.wellSqlInitializerProvider = wellSqlInitializerProvider;
    this.applicationProvider = applicationProvider;
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

  @Override
  public AppInitializer get() {
    AppInitializer instance = newInstance(wellSqlInitializerProvider.get(), applicationProvider.get());
    AppInitializer_MembersInjector.injectDispatcher(instance, dispatcherProvider.get());
    AppInitializer_MembersInjector.injectAccountStore(instance, accountStoreProvider.get());
    AppInitializer_MembersInjector.injectSiteStore(instance, siteStoreProvider.get());
    AppInitializer_MembersInjector.injectMediaStore(instance, mediaStoreProvider.get());
    AppInitializer_MembersInjector.injectZendeskHelper(instance, zendeskHelperProvider.get());
    AppInitializer_MembersInjector.injectUploadStarter(instance, uploadStarterProvider.get());
    AppInitializer_MembersInjector.injectStatsWidgetUpdaters(instance, statsWidgetUpdatersProvider.get());
    AppInitializer_MembersInjector.injectStatsStore(instance, statsStoreProvider.get());
    AppInitializer_MembersInjector.injectSystemNotificationsTracker(instance, systemNotificationsTrackerProvider.get());
    AppInitializer_MembersInjector.injectReaderTracker(instance, readerTrackerProvider.get());
    AppInitializer_MembersInjector.injectImageManager(instance, imageManagerProvider.get());
    AppInitializer_MembersInjector.injectPrivateAtomicCookie(instance, privateAtomicCookieProvider.get());
    AppInitializer_MembersInjector.injectImageEditorTracker(instance, imageEditorTrackerProvider.get());
    AppInitializer_MembersInjector.injectStoryMediaSaveUploadBridge(instance, storyMediaSaveUploadBridgeProvider.get());
    AppInitializer_MembersInjector.injectCrashLogging(instance, crashLoggingProvider.get());
    AppInitializer_MembersInjector.injectEncryptedLogging(instance, encryptedLoggingProvider.get());
    AppInitializer_MembersInjector.injectAppConfig(instance, appConfigProvider.get());
    AppInitializer_MembersInjector.injectImageEditorFileUtils(instance, imageEditorFileUtilsProvider.get());
    AppInitializer_MembersInjector.injectExPlat(instance, exPlatProvider.get());
    AppInitializer_MembersInjector.injectWordPressWorkerFactory(instance, wordPressWorkerFactoryProvider.get());
    AppInitializer_MembersInjector.injectDebugCookieManager(instance, debugCookieManagerProvider.get());
    AppInitializer_MembersInjector.injectAppScope(instance, appScopeProvider.get());
    AppInitializer_MembersInjector.injectSelectedSiteRepository(instance, selectedSiteRepositoryProvider.get());
    AppInitializer_MembersInjector.injectMySiteDefaultTabExperiment(instance, mySiteDefaultTabExperimentProvider.get());
    AppInitializer_MembersInjector.injectTracker(instance, trackerProvider.get());
    AppInitializer_MembersInjector.injectRequestQueue(instance, requestQueueProvider.get());
    AppInitializer_MembersInjector.injectImageLoader(instance, imageLoaderProvider.get());
    AppInitializer_MembersInjector.injectOAuthAuthenticator(instance, oAuthAuthenticatorProvider.get());
    return instance;
  }

  public static AppInitializer_Factory create(
      Provider<WellSqlInitializer> wellSqlInitializerProvider,
      Provider<Application> applicationProvider, Provider<Dispatcher> dispatcherProvider,
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
    return new AppInitializer_Factory(wellSqlInitializerProvider, applicationProvider, dispatcherProvider, accountStoreProvider, siteStoreProvider, mediaStoreProvider, zendeskHelperProvider, uploadStarterProvider, statsWidgetUpdatersProvider, statsStoreProvider, systemNotificationsTrackerProvider, readerTrackerProvider, imageManagerProvider, privateAtomicCookieProvider, imageEditorTrackerProvider, storyMediaSaveUploadBridgeProvider, crashLoggingProvider, encryptedLoggingProvider, appConfigProvider, imageEditorFileUtilsProvider, exPlatProvider, wordPressWorkerFactoryProvider, debugCookieManagerProvider, appScopeProvider, selectedSiteRepositoryProvider, mySiteDefaultTabExperimentProvider, trackerProvider, requestQueueProvider, imageLoaderProvider, oAuthAuthenticatorProvider);
  }

  public static AppInitializer newInstance(WellSqlInitializer wellSqlInitializer,
      Application application) {
    return new AppInitializer(wellSqlInitializer, application);
  }
}
