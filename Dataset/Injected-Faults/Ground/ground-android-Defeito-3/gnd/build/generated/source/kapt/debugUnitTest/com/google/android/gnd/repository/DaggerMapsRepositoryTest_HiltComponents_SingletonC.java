package com.google.android.gnd.repository;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.location.Geocoder;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.hilt.work.HiltWorkerFactory;
import androidx.hilt.work.HiltWrapper_WorkerFactoryModule;
import androidx.hilt.work.WorkerAssistedFactory;
import androidx.hilt.work.WorkerFactoryModule_ProvideFactoryFactory;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.work.ListenableWorker;
import androidx.work.WorkManager;
import androidx.work.WorkerParameters;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gnd.AbstractActivity_MembersInjector;
import com.google.android.gnd.GndApplication;
import com.google.android.gnd.GndApplicationModule_GoogleApiAvailabilityFactory;
import com.google.android.gnd.GndApplicationModule_ProvideGeocoderFactory;
import com.google.android.gnd.GndApplicationModule_ProvideResourcesFactory;
import com.google.android.gnd.GndApplicationModule_ProvideSharedPreferencesFactory;
import com.google.android.gnd.GndApplication_MembersInjector;
import com.google.android.gnd.HiltWrapper_GndApplicationModule;
import com.google.android.gnd.HiltWrapper_TestAuthenticationModule;
import com.google.android.gnd.HiltWrapper_TestLocalDatabaseModule;
import com.google.android.gnd.HiltWrapper_TestRemoteStorageModule;
import com.google.android.gnd.HiltWrapper_TestSchedulersModule;
import com.google.android.gnd.HiltWrapper_TestWorkManagerModule;
import com.google.android.gnd.MainActivity;
import com.google.android.gnd.MainActivity_MembersInjector;
import com.google.android.gnd.MainViewModel;
import com.google.android.gnd.SettingsActivity;
import com.google.android.gnd.TestLocalDatabaseModule_LocalDatabaseProviderFactory;
import com.google.android.gnd.TestScheduler;
import com.google.android.gnd.TestScheduler_Factory;
import com.google.android.gnd.TestWorkManagerModule_ProvideWorkManagerFactory;
import com.google.android.gnd.persistence.local.LocalDataStoreModule_BaseMapDaoFactory;
import com.google.android.gnd.persistence.local.LocalDataStoreModule_FeatureDaoFactory;
import com.google.android.gnd.persistence.local.LocalDataStoreModule_FeatureMutationDaoFactory;
import com.google.android.gnd.persistence.local.LocalDataStoreModule_FieldDaoFactory;
import com.google.android.gnd.persistence.local.LocalDataStoreModule_JobDaoFactory;
import com.google.android.gnd.persistence.local.LocalDataStoreModule_MultipleChoiceDaoFactory;
import com.google.android.gnd.persistence.local.LocalDataStoreModule_OfflineAreaDaoFactory;
import com.google.android.gnd.persistence.local.LocalDataStoreModule_OptionDaoFactory;
import com.google.android.gnd.persistence.local.LocalDataStoreModule_SubmissionDaoFactory;
import com.google.android.gnd.persistence.local.LocalDataStoreModule_SubmissionMutationDaoFactory;
import com.google.android.gnd.persistence.local.LocalDataStoreModule_SurveyDaoFactory;
import com.google.android.gnd.persistence.local.LocalDataStoreModule_TaskDaoFactory;
import com.google.android.gnd.persistence.local.LocalDataStoreModule_TileSetDaoFactory;
import com.google.android.gnd.persistence.local.LocalDataStoreModule_UserDaoFactory;
import com.google.android.gnd.persistence.local.LocalValueStore;
import com.google.android.gnd.persistence.local.room.LocalDatabase;
import com.google.android.gnd.persistence.local.room.RoomLocalDataStore;
import com.google.android.gnd.persistence.local.room.RoomLocalDataStore_Factory;
import com.google.android.gnd.persistence.local.room.RoomLocalDataStore_MembersInjector;
import com.google.android.gnd.persistence.local.room.dao.BaseMapDao;
import com.google.android.gnd.persistence.local.room.dao.FeatureDao;
import com.google.android.gnd.persistence.local.room.dao.FeatureMutationDao;
import com.google.android.gnd.persistence.local.room.dao.FieldDao;
import com.google.android.gnd.persistence.local.room.dao.JobDao;
import com.google.android.gnd.persistence.local.room.dao.MultipleChoiceDao;
import com.google.android.gnd.persistence.local.room.dao.OfflineAreaDao;
import com.google.android.gnd.persistence.local.room.dao.OptionDao;
import com.google.android.gnd.persistence.local.room.dao.SubmissionDao;
import com.google.android.gnd.persistence.local.room.dao.SubmissionMutationDao;
import com.google.android.gnd.persistence.local.room.dao.SurveyDao;
import com.google.android.gnd.persistence.local.room.dao.TaskDao;
import com.google.android.gnd.persistence.local.room.dao.TileSetDao;
import com.google.android.gnd.persistence.local.room.dao.UserDao;
import com.google.android.gnd.persistence.mbtiles.MbtilesFootprintParser;
import com.google.android.gnd.persistence.mbtiles.MbtilesFootprintParser_Factory;
import com.google.android.gnd.persistence.remote.FakeRemoteDataStore;
import com.google.android.gnd.persistence.remote.FakeRemoteDataStore_Factory;
import com.google.android.gnd.persistence.remote.FakeRemoteStorageManager;
import com.google.android.gnd.persistence.remote.FakeRemoteStorageManager_Factory;
import com.google.android.gnd.persistence.remote.RemoteStorageManager;
import com.google.android.gnd.persistence.sync.DataSyncWorkManager;
import com.google.android.gnd.persistence.sync.LocalMutationSyncWorker;
import com.google.android.gnd.persistence.sync.LocalMutationSyncWorker_AssistedFactory;
import com.google.android.gnd.persistence.sync.PhotoSyncWorkManager;
import com.google.android.gnd.persistence.sync.PhotoSyncWorker;
import com.google.android.gnd.persistence.sync.PhotoSyncWorker_AssistedFactory;
import com.google.android.gnd.persistence.sync.TileSetDownloadWorkManager;
import com.google.android.gnd.persistence.sync.TileSetDownloadWorker;
import com.google.android.gnd.persistence.sync.TileSetDownloadWorker_AssistedFactory;
import com.google.android.gnd.persistence.uuid.FakeUuidGenerator;
import com.google.android.gnd.persistence.uuid.FakeUuidGenerator_Factory;
import com.google.android.gnd.persistence.uuid.OfflineUuidGenerator;
import com.google.android.gnd.rx.Schedulers;
import com.google.android.gnd.system.ActivityStreams;
import com.google.android.gnd.system.ApplicationErrorManager;
import com.google.android.gnd.system.GeocodingManager;
import com.google.android.gnd.system.GoogleApiManager;
import com.google.android.gnd.system.LocationManager;
import com.google.android.gnd.system.NotificationManager;
import com.google.android.gnd.system.PermissionsManager;
import com.google.android.gnd.system.SettingsManager;
import com.google.android.gnd.system.auth.FakeAuthenticationManager;
import com.google.android.gnd.system.rx.RxFusedLocationProviderClient;
import com.google.android.gnd.system.rx.RxSettingsClient;
import com.google.android.gnd.ui.MarkerIconFactory;
import com.google.android.gnd.ui.common.AbstractDialogFragment_MembersInjector;
import com.google.android.gnd.ui.common.AbstractFragment_MembersInjector;
import com.google.android.gnd.ui.common.AbstractMapViewerFragment_MembersInjector;
import com.google.android.gnd.ui.common.AbstractView_MembersInjector;
import com.google.android.gnd.ui.common.EphemeralPopups;
import com.google.android.gnd.ui.common.FeatureHelper;
import com.google.android.gnd.ui.common.FeatureHelper_Factory;
import com.google.android.gnd.ui.common.Navigator;
import com.google.android.gnd.ui.common.ViewModelFactory;
import com.google.android.gnd.ui.datacollection.DataCollectionFragment;
import com.google.android.gnd.ui.datacollection.DataCollectionFragment_MembersInjector;
import com.google.android.gnd.ui.datacollection.DataCollectionViewModel;
import com.google.android.gnd.ui.editsubmission.DateFieldViewModel;
import com.google.android.gnd.ui.editsubmission.DateFieldViewModel_Factory;
import com.google.android.gnd.ui.editsubmission.EditSubmissionFragment;
import com.google.android.gnd.ui.editsubmission.EditSubmissionFragment_MembersInjector;
import com.google.android.gnd.ui.editsubmission.EditSubmissionViewModel;
import com.google.android.gnd.ui.editsubmission.EditSubmissionViewModel_Factory;
import com.google.android.gnd.ui.editsubmission.FieldViewFactory;
import com.google.android.gnd.ui.editsubmission.FieldViewFactory_Factory;
import com.google.android.gnd.ui.editsubmission.FieldViewFactory_MembersInjector;
import com.google.android.gnd.ui.editsubmission.MultipleChoiceFieldViewModel;
import com.google.android.gnd.ui.editsubmission.MultipleChoiceFieldViewModel_Factory;
import com.google.android.gnd.ui.editsubmission.NumberFieldViewModel;
import com.google.android.gnd.ui.editsubmission.NumberFieldViewModel_Factory;
import com.google.android.gnd.ui.editsubmission.PhotoFieldViewModel;
import com.google.android.gnd.ui.editsubmission.PhotoFieldViewModel_Factory;
import com.google.android.gnd.ui.editsubmission.TextFieldViewModel;
import com.google.android.gnd.ui.editsubmission.TextFieldViewModel_Factory;
import com.google.android.gnd.ui.editsubmission.TimeFieldViewModel;
import com.google.android.gnd.ui.editsubmission.TimeFieldViewModel_Factory;
import com.google.android.gnd.ui.home.HomeScreenFragment;
import com.google.android.gnd.ui.home.HomeScreenFragment_MembersInjector;
import com.google.android.gnd.ui.home.HomeScreenViewModel;
import com.google.android.gnd.ui.home.HomeScreenViewModel_Factory;
import com.google.android.gnd.ui.home.featuredetails.FeatureDetailsFragment;
import com.google.android.gnd.ui.home.featuredetails.FeatureDetailsViewModel;
import com.google.android.gnd.ui.home.featuredetails.SubmissionListFragment;
import com.google.android.gnd.ui.home.featuredetails.SubmissionListFragment_MembersInjector;
import com.google.android.gnd.ui.home.featuredetails.SubmissionListItemViewModel;
import com.google.android.gnd.ui.home.featuredetails.SubmissionListItemViewModel_Factory;
import com.google.android.gnd.ui.home.featuredetails.SubmissionListViewModel;
import com.google.android.gnd.ui.home.featureselector.FeatureSelectorFragment;
import com.google.android.gnd.ui.home.featureselector.FeatureSelectorFragment_MembersInjector;
import com.google.android.gnd.ui.home.featureselector.FeatureSelectorViewModel;
import com.google.android.gnd.ui.home.featureselector.FeatureSelectorViewModel_Factory;
import com.google.android.gnd.ui.home.mapcontainer.FeatureDataTypeSelectorDialogFragment;
import com.google.android.gnd.ui.home.mapcontainer.FeatureRepositionView;
import com.google.android.gnd.ui.home.mapcontainer.FeatureRepositionViewModel;
import com.google.android.gnd.ui.home.mapcontainer.FeatureRepositionViewModel_Factory;
import com.google.android.gnd.ui.home.mapcontainer.MapContainerFragment;
import com.google.android.gnd.ui.home.mapcontainer.MapContainerFragment_MembersInjector;
import com.google.android.gnd.ui.home.mapcontainer.MapContainerViewModel;
import com.google.android.gnd.ui.home.mapcontainer.MapContainerViewModel_Factory;
import com.google.android.gnd.ui.home.mapcontainer.MapTypeDialogFragment;
import com.google.android.gnd.ui.home.mapcontainer.MapTypeDialogFragment_MembersInjector;
import com.google.android.gnd.ui.home.mapcontainer.PolygonDrawingInfoDialogFragment;
import com.google.android.gnd.ui.home.mapcontainer.PolygonDrawingView;
import com.google.android.gnd.ui.home.mapcontainer.PolygonDrawingViewModel;
import com.google.android.gnd.ui.map.MapProviderModule_ProvidesGoogleMapFragmentFactory;
import com.google.android.gnd.ui.map.gms.GoogleMapsFragment;
import com.google.android.gnd.ui.map.gms.GoogleMapsFragment_MembersInjector;
import com.google.android.gnd.ui.offlinebasemap.OfflineAreasFragment;
import com.google.android.gnd.ui.offlinebasemap.OfflineAreasFragment_MembersInjector;
import com.google.android.gnd.ui.offlinebasemap.OfflineAreasViewModel;
import com.google.android.gnd.ui.offlinebasemap.OfflineAreasViewModel_Factory;
import com.google.android.gnd.ui.offlinebasemap.selector.OfflineAreaSelectorFragment;
import com.google.android.gnd.ui.offlinebasemap.selector.OfflineAreaSelectorFragment_MembersInjector;
import com.google.android.gnd.ui.offlinebasemap.selector.OfflineAreaSelectorViewModel;
import com.google.android.gnd.ui.offlinebasemap.selector.OfflineAreaSelectorViewModel_Factory;
import com.google.android.gnd.ui.offlinebasemap.viewer.OfflineAreaViewerFragment;
import com.google.android.gnd.ui.offlinebasemap.viewer.OfflineAreaViewerFragment_MembersInjector;
import com.google.android.gnd.ui.offlinebasemap.viewer.OfflineAreaViewerViewModel;
import com.google.android.gnd.ui.offlinebasemap.viewer.OfflineAreaViewerViewModel_Factory;
import com.google.android.gnd.ui.offlinebasemap.viewer.OfflineAreaViewerViewModel_MembersInjector;
import com.google.android.gnd.ui.signin.SignInFragment;
import com.google.android.gnd.ui.signin.SignInViewModel;
import com.google.android.gnd.ui.signin.SignInViewModel_Factory;
import com.google.android.gnd.ui.startup.StartupFragment;
import com.google.android.gnd.ui.startup.StartupFragment_MembersInjector;
import com.google.android.gnd.ui.submissiondetails.SubmissionDetailsFragment;
import com.google.android.gnd.ui.submissiondetails.SubmissionDetailsFragment_MembersInjector;
import com.google.android.gnd.ui.submissiondetails.SubmissionDetailsViewModel;
import com.google.android.gnd.ui.submissiondetails.SubmissionDetailsViewModel_Factory;
import com.google.android.gnd.ui.surveyselector.SurveySelectorDialogFragment;
import com.google.android.gnd.ui.surveyselector.SurveySelectorDialogFragment_MembersInjector;
import com.google.android.gnd.ui.surveyselector.SurveySelectorViewModel;
import com.google.android.gnd.ui.surveyselector.SurveySelectorViewModel_Factory;
import com.google.android.gnd.ui.syncstatus.SyncStatusFragment;
import com.google.android.gnd.ui.syncstatus.SyncStatusFragment_MembersInjector;
import com.google.android.gnd.ui.syncstatus.SyncStatusViewModel;
import com.google.android.gnd.ui.syncstatus.SyncStatusViewModel_Factory;
import com.google.android.gnd.ui.tos.TermsOfServiceFragment;
import com.google.android.gnd.ui.tos.TermsOfServiceFragment_MembersInjector;
import com.google.android.gnd.ui.tos.TermsOfServiceViewModel;
import com.google.android.gnd.ui.util.BitmapUtil;
import com.google.android.gnd.ui.util.BitmapUtil_Factory;
import com.google.android.gnd.ui.util.DrawableUtil;
import com.google.android.gnd.ui.util.FileUtil;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_Lifecycle_Factory;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideApplicationFactory;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import java.util.Map;
import java.util.Set;
import javax.annotation.Generated;
import javax.inject.Provider;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DaggerMapsRepositoryTest_HiltComponents_SingletonC extends MapsRepositoryTest_HiltComponents.SingletonC {
  private final ApplicationContextModule applicationContextModule;

  private final DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC = this;

  private Provider<LocalDatabase> localDatabaseProvider;

  private Provider<TestScheduler> testSchedulerProvider;

  private Provider<Schedulers> schedulersProvider;

  private Provider<RoomLocalDataStore> roomLocalDataStoreProvider;

  private Provider<FakeRemoteDataStore> fakeRemoteDataStoreProvider;

  private Provider<NotificationManager> notificationManagerProvider;

  private Provider<WorkManager> provideWorkManagerProvider;

  private Provider<SharedPreferences> provideSharedPreferencesProvider;

  private Provider<LocalValueStore> localValueStoreProvider;

  private Provider<FakeRemoteStorageManager> fakeRemoteStorageManagerProvider;

  private Provider<RemoteStorageManager> bindRemoteStorageManagerProvider;

  private Provider<FakeUuidGenerator> fakeUuidGeneratorProvider;

  private Provider<OfflineUuidGenerator> offlineUuidGeneratorProvider;

  private Provider<UserMediaRepository> userMediaRepositoryProvider;

  private Provider<LocalMutationSyncWorker_AssistedFactory> localMutationSyncWorker_AssistedFactoryProvider;

  private Provider<PhotoSyncWorker_AssistedFactory> photoSyncWorker_AssistedFactoryProvider;

  private Provider<TileSetDownloadWorker_AssistedFactory> tileSetDownloadWorker_AssistedFactoryProvider;

  private Provider<MapsRepository> mapsRepositoryProvider;

  private Provider<ActivityStreams> activityStreamsProvider;

  private Provider<ApplicationErrorManager> applicationErrorManagerProvider;

  private Provider<PermissionsManager> permissionsManagerProvider;

  private Provider<SettingsManager> settingsManagerProvider;

  private Provider<LocationManager> locationManagerProvider;

  private Provider<FakeAuthenticationManager> fakeAuthenticationManagerProvider;

  private Provider<SurveyRepository> surveyRepositoryProvider;

  private Provider<FeatureRepository> featureRepositoryProvider;

  private Provider<Geocoder> provideGeocoderProvider;

  private Provider<GeocodingManager> geocodingManagerProvider;

  private Provider<Navigator> navigatorProvider;

  private Provider<EphemeralPopups> ephemeralPopupsProvider;

  private Provider<MarkerIconFactory> markerIconFactoryProvider;

  private Provider<GoogleApiAvailability> googleApiAvailabilityProvider;

  private DaggerMapsRepositoryTest_HiltComponents_SingletonC(
      ApplicationContextModule applicationContextModuleParam) {
    this.applicationContextModule = applicationContextModuleParam;
    initialize(applicationContextModuleParam);

  }

  public static Builder builder() {
    return new Builder();
  }

  private LocalDatabase localDatabase() {
    return TestLocalDatabaseModule_LocalDatabaseProviderFactory.localDatabaseProvider(ApplicationContextModule_ProvideContextFactory.provideContext(applicationContextModule));
  }

  private OptionDao optionDao() {
    return LocalDataStoreModule_OptionDaoFactory.optionDao(localDatabaseProvider.get());
  }

  private MultipleChoiceDao multipleChoiceDao() {
    return LocalDataStoreModule_MultipleChoiceDaoFactory.multipleChoiceDao(localDatabaseProvider.get());
  }

  private FieldDao fieldDao() {
    return LocalDataStoreModule_FieldDaoFactory.fieldDao(localDatabaseProvider.get());
  }

  private TaskDao taskDao() {
    return LocalDataStoreModule_TaskDaoFactory.taskDao(localDatabaseProvider.get());
  }

  private JobDao jobDao() {
    return LocalDataStoreModule_JobDaoFactory.jobDao(localDatabaseProvider.get());
  }

  private SurveyDao surveyDao() {
    return LocalDataStoreModule_SurveyDaoFactory.surveyDao(localDatabaseProvider.get());
  }

  private FeatureDao featureDao() {
    return LocalDataStoreModule_FeatureDaoFactory.featureDao(localDatabaseProvider.get());
  }

  private FeatureMutationDao featureMutationDao() {
    return LocalDataStoreModule_FeatureMutationDaoFactory.featureMutationDao(localDatabaseProvider.get());
  }

  private SubmissionDao submissionDao() {
    return LocalDataStoreModule_SubmissionDaoFactory.submissionDao(localDatabaseProvider.get());
  }

  private SubmissionMutationDao submissionMutationDao() {
    return LocalDataStoreModule_SubmissionMutationDaoFactory.submissionMutationDao(localDatabaseProvider.get());
  }

  private TileSetDao tileSetDao() {
    return LocalDataStoreModule_TileSetDaoFactory.tileSetDao(localDatabaseProvider.get());
  }

  private UserDao userDao() {
    return LocalDataStoreModule_UserDaoFactory.userDao(localDatabaseProvider.get());
  }

  private OfflineAreaDao offlineAreaDao() {
    return LocalDataStoreModule_OfflineAreaDaoFactory.offlineAreaDao(localDatabaseProvider.get());
  }

  private BaseMapDao baseMapDao() {
    return LocalDataStoreModule_BaseMapDaoFactory.baseMapDao(localDatabaseProvider.get());
  }

  private FileUtil fileUtil() {
    return new FileUtil(ApplicationContextModule_ProvideContextFactory.provideContext(applicationContextModule));
  }

  private RoomLocalDataStore roomLocalDataStore() {
    return injectRoomLocalDataStore(RoomLocalDataStore_Factory.newInstance());
  }

  private NotificationManager notificationManager() {
    return new NotificationManager(ApplicationContextModule_ProvideContextFactory.provideContext(applicationContextModule));
  }

  private SharedPreferences sharedPreferences() {
    return GndApplicationModule_ProvideSharedPreferencesFactory.provideSharedPreferences(ApplicationContextModule_ProvideApplicationFactory.provideApplication(applicationContextModule));
  }

  private LocalValueStore localValueStore() {
    return new LocalValueStore(provideSharedPreferencesProvider.get());
  }

  private UserMediaRepository userMediaRepository() {
    return new UserMediaRepository(ApplicationContextModule_ProvideContextFactory.provideContext(applicationContextModule), bindRemoteStorageManagerProvider.get(), offlineUuidGeneratorProvider.get());
  }

  private PhotoSyncWorkManager photoSyncWorkManager() {
    return new PhotoSyncWorkManager(provideWorkManagerProvider.get(), localValueStoreProvider.get(), userMediaRepositoryProvider.get());
  }

  private LocalMutationSyncWorker localMutationSyncWorker(Context context,
      WorkerParameters params) {
    return new LocalMutationSyncWorker(context, params, roomLocalDataStoreProvider.get(), fakeRemoteDataStoreProvider.get(), notificationManagerProvider.get(), photoSyncWorkManager());
  }

  private LocalMutationSyncWorker_AssistedFactory localMutationSyncWorker_AssistedFactory() {
    return new LocalMutationSyncWorker_AssistedFactory() {
      @Override
      public LocalMutationSyncWorker create(Context context, WorkerParameters params) {
        return singletonC.localMutationSyncWorker(context, params);
      }
    };
  }

  private PhotoSyncWorker photoSyncWorker(Context context2, WorkerParameters workerParams) {
    return new PhotoSyncWorker(context2, workerParams, bindRemoteStorageManagerProvider.get(), notificationManagerProvider.get());
  }

  private PhotoSyncWorker_AssistedFactory photoSyncWorker_AssistedFactory() {
    return new PhotoSyncWorker_AssistedFactory() {
      @Override
      public PhotoSyncWorker create(Context context, WorkerParameters workerParams) {
        return singletonC.photoSyncWorker(context, workerParams);
      }
    };
  }

  private TileSetDownloadWorker tileSetDownloadWorker(Context context3, WorkerParameters params2) {
    return new TileSetDownloadWorker(context3, params2, roomLocalDataStoreProvider.get(), notificationManagerProvider.get());
  }

  private TileSetDownloadWorker_AssistedFactory tileSetDownloadWorker_AssistedFactory() {
    return new TileSetDownloadWorker_AssistedFactory() {
      @Override
      public TileSetDownloadWorker create(Context context, WorkerParameters params) {
        return singletonC.tileSetDownloadWorker(context, params);
      }
    };
  }

  private Map<String, Provider<WorkerAssistedFactory<? extends ListenableWorker>>> mapOfStringAndProviderOfWorkerAssistedFactoryOf(
      ) {
    return ImmutableMap.<String, Provider<WorkerAssistedFactory<? extends ListenableWorker>>>of("com.google.android.gnd.persistence.sync.LocalMutationSyncWorker", (Provider) localMutationSyncWorker_AssistedFactoryProvider, "com.google.android.gnd.persistence.sync.PhotoSyncWorker", (Provider) photoSyncWorker_AssistedFactoryProvider, "com.google.android.gnd.persistence.sync.TileSetDownloadWorker", (Provider) tileSetDownloadWorker_AssistedFactoryProvider);
  }

  private HiltWorkerFactory hiltWorkerFactory() {
    return WorkerFactoryModule_ProvideFactoryFactory.provideFactory(mapOfStringAndProviderOfWorkerAssistedFactoryOf());
  }

  private MapsRepository mapsRepository() {
    return new MapsRepository(localValueStoreProvider.get());
  }

  private Resources resources() {
    return GndApplicationModule_ProvideResourcesFactory.provideResources(ApplicationContextModule_ProvideContextFactory.provideContext(applicationContextModule));
  }

  private ApplicationErrorManager applicationErrorManager() {
    return new ApplicationErrorManager(resources());
  }

  private PermissionsManager permissionsManager() {
    return new PermissionsManager(ApplicationContextModule_ProvideContextFactory.provideContext(applicationContextModule), activityStreamsProvider.get());
  }

  private RxSettingsClient rxSettingsClient() {
    return new RxSettingsClient(ApplicationContextModule_ProvideContextFactory.provideContext(applicationContextModule));
  }

  private SettingsManager settingsManager() {
    return new SettingsManager(activityStreamsProvider.get(), rxSettingsClient());
  }

  private RxFusedLocationProviderClient rxFusedLocationProviderClient() {
    return new RxFusedLocationProviderClient(ApplicationContextModule_ProvideContextFactory.provideContext(applicationContextModule));
  }

  private LocationManager locationManager() {
    return new LocationManager(permissionsManagerProvider.get(), settingsManagerProvider.get(), rxFusedLocationProviderClient());
  }

  private UserRepository userRepository() {
    return new UserRepository(fakeAuthenticationManagerProvider.get(), roomLocalDataStoreProvider.get(), localValueStoreProvider.get(), schedulersProvider.get());
  }

  private SurveyRepository surveyRepository() {
    return new SurveyRepository(userRepository(), roomLocalDataStoreProvider.get(), fakeRemoteDataStoreProvider.get(), localValueStoreProvider.get());
  }

  private DataSyncWorkManager dataSyncWorkManager() {
    return new DataSyncWorkManager(provideWorkManagerProvider.get());
  }

  private FeatureRepository featureRepository() {
    return new FeatureRepository(roomLocalDataStoreProvider.get(), localValueStoreProvider.get(), fakeRemoteDataStoreProvider.get(), surveyRepositoryProvider.get(), dataSyncWorkManager(), fakeAuthenticationManagerProvider.get(), offlineUuidGeneratorProvider.get());
  }

  private Geocoder geocoder() {
    return GndApplicationModule_ProvideGeocoderFactory.provideGeocoder(ApplicationContextModule_ProvideContextFactory.provideContext(applicationContextModule));
  }

  private GeocodingManager geocodingManager() {
    return new GeocodingManager(provideGeocoderProvider.get(), schedulersProvider.get(), resources());
  }

  private EphemeralPopups ephemeralPopups() {
    return new EphemeralPopups(ApplicationContextModule_ProvideApplicationFactory.provideApplication(applicationContextModule));
  }

  private MarkerIconFactory markerIconFactory() {
    return new MarkerIconFactory(ApplicationContextModule_ProvideContextFactory.provideContext(applicationContextModule));
  }

  @SuppressWarnings("unchecked")
  private void initialize(final ApplicationContextModule applicationContextModuleParam) {
    this.localDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<LocalDatabase>(singletonC, 2));
    this.testSchedulerProvider = new SwitchingProvider<>(singletonC, 3);
    this.schedulersProvider = DoubleCheck.provider((Provider) testSchedulerProvider);
    this.roomLocalDataStoreProvider = DoubleCheck.provider(new SwitchingProvider<RoomLocalDataStore>(singletonC, 1));
    this.fakeRemoteDataStoreProvider = DoubleCheck.provider(new SwitchingProvider<FakeRemoteDataStore>(singletonC, 4));
    this.notificationManagerProvider = DoubleCheck.provider(new SwitchingProvider<NotificationManager>(singletonC, 5));
    this.provideWorkManagerProvider = DoubleCheck.provider(new SwitchingProvider<WorkManager>(singletonC, 6));
    this.provideSharedPreferencesProvider = DoubleCheck.provider(new SwitchingProvider<SharedPreferences>(singletonC, 8));
    this.localValueStoreProvider = DoubleCheck.provider(new SwitchingProvider<LocalValueStore>(singletonC, 7));
    this.fakeRemoteStorageManagerProvider = new SwitchingProvider<>(singletonC, 10);
    this.bindRemoteStorageManagerProvider = DoubleCheck.provider((Provider) fakeRemoteStorageManagerProvider);
    this.fakeUuidGeneratorProvider = new SwitchingProvider<>(singletonC, 11);
    this.offlineUuidGeneratorProvider = DoubleCheck.provider((Provider) fakeUuidGeneratorProvider);
    this.userMediaRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<UserMediaRepository>(singletonC, 9));
    this.localMutationSyncWorker_AssistedFactoryProvider = new SwitchingProvider<>(singletonC, 0);
    this.photoSyncWorker_AssistedFactoryProvider = new SwitchingProvider<>(singletonC, 12);
    this.tileSetDownloadWorker_AssistedFactoryProvider = new SwitchingProvider<>(singletonC, 13);
    this.mapsRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<MapsRepository>(singletonC, 14));
    this.activityStreamsProvider = DoubleCheck.provider(new SwitchingProvider<ActivityStreams>(singletonC, 15));
    this.applicationErrorManagerProvider = DoubleCheck.provider(new SwitchingProvider<ApplicationErrorManager>(singletonC, 16));
    this.permissionsManagerProvider = DoubleCheck.provider(new SwitchingProvider<PermissionsManager>(singletonC, 18));
    this.settingsManagerProvider = DoubleCheck.provider(new SwitchingProvider<SettingsManager>(singletonC, 19));
    this.locationManagerProvider = DoubleCheck.provider(new SwitchingProvider<LocationManager>(singletonC, 17));
    this.fakeAuthenticationManagerProvider = DoubleCheck.provider(new SwitchingProvider<FakeAuthenticationManager>(singletonC, 20));
    this.surveyRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<SurveyRepository>(singletonC, 21));
    this.featureRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<FeatureRepository>(singletonC, 22));
    this.provideGeocoderProvider = DoubleCheck.provider(new SwitchingProvider<Geocoder>(singletonC, 24));
    this.geocodingManagerProvider = DoubleCheck.provider(new SwitchingProvider<GeocodingManager>(singletonC, 23));
    this.navigatorProvider = DoubleCheck.provider(new SwitchingProvider<Navigator>(singletonC, 25));
    this.ephemeralPopupsProvider = DoubleCheck.provider(new SwitchingProvider<EphemeralPopups>(singletonC, 26));
    this.markerIconFactoryProvider = DoubleCheck.provider(new SwitchingProvider<MarkerIconFactory>(singletonC, 27));
    this.googleApiAvailabilityProvider = DoubleCheck.provider(new SwitchingProvider<GoogleApiAvailability>(singletonC, 28));
  }

  @Override
  public void injectGndApplication(GndApplication gndApplication) {
    injectGndApplication2(gndApplication);
  }

  @Override
  public void injectTest(MapsRepositoryTest mapsRepositoryTest) {
    injectMapsRepositoryTest(mapsRepositoryTest);
  }

  @Override
  public ActivityRetainedComponentBuilder retainedComponentBuilder() {
    return new ActivityRetainedCBuilder(singletonC);
  }

  @Override
  public ServiceComponentBuilder serviceComponentBuilder() {
    return new ServiceCBuilder(singletonC);
  }

  @CanIgnoreReturnValue
  private RoomLocalDataStore injectRoomLocalDataStore(RoomLocalDataStore instance) {
    RoomLocalDataStore_MembersInjector.injectOptionDao(instance, optionDao());
    RoomLocalDataStore_MembersInjector.injectMultipleChoiceDao(instance, multipleChoiceDao());
    RoomLocalDataStore_MembersInjector.injectFieldDao(instance, fieldDao());
    RoomLocalDataStore_MembersInjector.injectTaskDao(instance, taskDao());
    RoomLocalDataStore_MembersInjector.injectJobDao(instance, jobDao());
    RoomLocalDataStore_MembersInjector.injectSurveyDao(instance, surveyDao());
    RoomLocalDataStore_MembersInjector.injectFeatureDao(instance, featureDao());
    RoomLocalDataStore_MembersInjector.injectFeatureMutationDao(instance, featureMutationDao());
    RoomLocalDataStore_MembersInjector.injectSubmissionDao(instance, submissionDao());
    RoomLocalDataStore_MembersInjector.injectSubmissionMutationDao(instance, submissionMutationDao());
    RoomLocalDataStore_MembersInjector.injectTileSetDao(instance, tileSetDao());
    RoomLocalDataStore_MembersInjector.injectUserDao(instance, userDao());
    RoomLocalDataStore_MembersInjector.injectOfflineAreaDao(instance, offlineAreaDao());
    RoomLocalDataStore_MembersInjector.injectBaseMapDao(instance, baseMapDao());
    RoomLocalDataStore_MembersInjector.injectSchedulers(instance, schedulersProvider.get());
    RoomLocalDataStore_MembersInjector.injectFileUtil(instance, fileUtil());
    return instance;
  }

  @CanIgnoreReturnValue
  private GndApplication injectGndApplication2(GndApplication instance) {
    GndApplication_MembersInjector.injectWorkerFactory(instance, hiltWorkerFactory());
    return instance;
  }

  @CanIgnoreReturnValue
  private MapsRepositoryTest injectMapsRepositoryTest(MapsRepositoryTest instance) {
    MapsRepositoryTest_MembersInjector.injectMapsRepository(instance, mapsRepositoryProvider.get());
    return instance;
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This method is a no-op. For more, see https://dagger.dev/unused-modules.
     */
    @Deprecated
    public Builder hiltWrapper_GndApplicationModule(
        HiltWrapper_GndApplicationModule hiltWrapper_GndApplicationModule) {
      Preconditions.checkNotNull(hiltWrapper_GndApplicationModule);
      return this;
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This method is a no-op. For more, see https://dagger.dev/unused-modules.
     */
    @Deprecated
    public Builder hiltWrapper_TestAuthenticationModule(
        HiltWrapper_TestAuthenticationModule hiltWrapper_TestAuthenticationModule) {
      Preconditions.checkNotNull(hiltWrapper_TestAuthenticationModule);
      return this;
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This method is a no-op. For more, see https://dagger.dev/unused-modules.
     */
    @Deprecated
    public Builder hiltWrapper_TestLocalDatabaseModule(
        HiltWrapper_TestLocalDatabaseModule hiltWrapper_TestLocalDatabaseModule) {
      Preconditions.checkNotNull(hiltWrapper_TestLocalDatabaseModule);
      return this;
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This method is a no-op. For more, see https://dagger.dev/unused-modules.
     */
    @Deprecated
    public Builder hiltWrapper_TestRemoteStorageModule(
        HiltWrapper_TestRemoteStorageModule hiltWrapper_TestRemoteStorageModule) {
      Preconditions.checkNotNull(hiltWrapper_TestRemoteStorageModule);
      return this;
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This method is a no-op. For more, see https://dagger.dev/unused-modules.
     */
    @Deprecated
    public Builder hiltWrapper_TestSchedulersModule(
        HiltWrapper_TestSchedulersModule hiltWrapper_TestSchedulersModule) {
      Preconditions.checkNotNull(hiltWrapper_TestSchedulersModule);
      return this;
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This method is a no-op. For more, see https://dagger.dev/unused-modules.
     */
    @Deprecated
    public Builder hiltWrapper_TestWorkManagerModule(
        HiltWrapper_TestWorkManagerModule hiltWrapper_TestWorkManagerModule) {
      Preconditions.checkNotNull(hiltWrapper_TestWorkManagerModule);
      return this;
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This method is a no-op. For more, see https://dagger.dev/unused-modules.
     */
    @Deprecated
    public Builder hiltWrapper_WorkerFactoryModule(
        HiltWrapper_WorkerFactoryModule hiltWrapper_WorkerFactoryModule) {
      Preconditions.checkNotNull(hiltWrapper_WorkerFactoryModule);
      return this;
    }

    public MapsRepositoryTest_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new DaggerMapsRepositoryTest_HiltComponents_SingletonC(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements MapsRepositoryTest_HiltComponents.ActivityRetainedC.Builder {
    private final DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC;

    private ActivityRetainedCBuilder(
        DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC) {
      this.singletonC = singletonC;
    }

    @Override
    public MapsRepositoryTest_HiltComponents.ActivityRetainedC build() {
      return new ActivityRetainedCImpl(singletonC);
    }
  }

  private static final class ActivityCBuilder implements MapsRepositoryTest_HiltComponents.ActivityC.Builder {
    private final DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonC = singletonC;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public MapsRepositoryTest_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonC, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements MapsRepositoryTest_HiltComponents.FragmentC.Builder {
    private final DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonC = singletonC;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public MapsRepositoryTest_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonC, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements MapsRepositoryTest_HiltComponents.ViewWithFragmentC.Builder {
    private final DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonC = singletonC;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public MapsRepositoryTest_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonC, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements MapsRepositoryTest_HiltComponents.ViewC.Builder {
    private final DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonC = singletonC;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public MapsRepositoryTest_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonC, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements MapsRepositoryTest_HiltComponents.ViewModelC.Builder {
    private final DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelCBuilder(DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonC = singletonC;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public MapsRepositoryTest_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      return new ViewModelCImpl(singletonC, activityRetainedCImpl, savedStateHandle);
    }
  }

  private static final class ServiceCBuilder implements MapsRepositoryTest_HiltComponents.ServiceC.Builder {
    private final DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC;

    private Service service;

    private ServiceCBuilder(DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC) {
      this.singletonC = singletonC;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public MapsRepositoryTest_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonC, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends MapsRepositoryTest_HiltComponents.ViewWithFragmentC {
    private final DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonC = singletonC;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }

    @Override
    public void injectFeatureRepositionView(FeatureRepositionView featureRepositionView) {
      injectFeatureRepositionView2(featureRepositionView);
    }

    @Override
    public void injectPolygonDrawingView(PolygonDrawingView polygonDrawingView) {
      injectPolygonDrawingView2(polygonDrawingView);
    }

    @CanIgnoreReturnValue
    private FeatureRepositionView injectFeatureRepositionView2(FeatureRepositionView instance) {
      AbstractView_MembersInjector.injectViewModelFactory(instance, activityCImpl.viewModelFactory());
      return instance;
    }

    @CanIgnoreReturnValue
    private PolygonDrawingView injectPolygonDrawingView2(PolygonDrawingView instance) {
      AbstractView_MembersInjector.injectViewModelFactory(instance, activityCImpl.viewModelFactory());
      return instance;
    }
  }

  private static final class FragmentCImpl extends MapsRepositoryTest_HiltComponents.FragmentC {
    private final Fragment fragment;

    private final DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonC = singletonC;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragment = fragmentParam;

    }

    private FieldViewFactory fieldViewFactory() {
      return injectFieldViewFactory(FieldViewFactory_Factory.newInstance());
    }

    @Override
    public void injectDataCollectionFragment(DataCollectionFragment dataCollectionFragment) {
      injectDataCollectionFragment2(dataCollectionFragment);
    }

    @Override
    public void injectEditSubmissionFragment(EditSubmissionFragment editSubmissionFragment) {
      injectEditSubmissionFragment2(editSubmissionFragment);
    }

    @Override
    public void injectHomeScreenFragment(HomeScreenFragment homeScreenFragment) {
      injectHomeScreenFragment2(homeScreenFragment);
    }

    @Override
    public void injectFeatureDetailsFragment(FeatureDetailsFragment featureDetailsFragment) {
      injectFeatureDetailsFragment2(featureDetailsFragment);
    }

    @Override
    public void injectSubmissionListFragment(SubmissionListFragment submissionListFragment) {
      injectSubmissionListFragment2(submissionListFragment);
    }

    @Override
    public void injectFeatureSelectorFragment(FeatureSelectorFragment featureSelectorFragment) {
      injectFeatureSelectorFragment2(featureSelectorFragment);
    }

    @Override
    public void injectFeatureDataTypeSelectorDialogFragment(
        FeatureDataTypeSelectorDialogFragment featureDataTypeSelectorDialogFragment) {
      injectFeatureDataTypeSelectorDialogFragment2(featureDataTypeSelectorDialogFragment);
    }

    @Override
    public void injectMapContainerFragment(MapContainerFragment mapContainerFragment) {
      injectMapContainerFragment2(mapContainerFragment);
    }

    @Override
    public void injectMapTypeDialogFragment(MapTypeDialogFragment mapTypeDialogFragment) {
      injectMapTypeDialogFragment2(mapTypeDialogFragment);
    }

    @Override
    public void injectPolygonDrawingInfoDialogFragment(
        PolygonDrawingInfoDialogFragment polygonDrawingInfoDialogFragment) {
      injectPolygonDrawingInfoDialogFragment2(polygonDrawingInfoDialogFragment);
    }

    @Override
    public void injectGoogleMapsFragment(GoogleMapsFragment googleMapsFragment) {
      injectGoogleMapsFragment2(googleMapsFragment);
    }

    @Override
    public void injectOfflineAreasFragment(OfflineAreasFragment offlineAreasFragment) {
      injectOfflineAreasFragment2(offlineAreasFragment);
    }

    @Override
    public void injectOfflineAreaSelectorFragment(
        OfflineAreaSelectorFragment offlineAreaSelectorFragment) {
      injectOfflineAreaSelectorFragment2(offlineAreaSelectorFragment);
    }

    @Override
    public void injectOfflineAreaViewerFragment(
        OfflineAreaViewerFragment offlineAreaViewerFragment) {
      injectOfflineAreaViewerFragment2(offlineAreaViewerFragment);
    }

    @Override
    public void injectSignInFragment(SignInFragment signInFragment) {
      injectSignInFragment2(signInFragment);
    }

    @Override
    public void injectStartupFragment(StartupFragment startupFragment) {
      injectStartupFragment2(startupFragment);
    }

    @Override
    public void injectSubmissionDetailsFragment(
        SubmissionDetailsFragment submissionDetailsFragment) {
      injectSubmissionDetailsFragment2(submissionDetailsFragment);
    }

    @Override
    public void injectSurveySelectorDialogFragment(
        SurveySelectorDialogFragment surveySelectorDialogFragment) {
      injectSurveySelectorDialogFragment2(surveySelectorDialogFragment);
    }

    @Override
    public void injectSyncStatusFragment(SyncStatusFragment syncStatusFragment) {
      injectSyncStatusFragment2(syncStatusFragment);
    }

    @Override
    public void injectTermsOfServiceFragment(TermsOfServiceFragment termsOfServiceFragment) {
      injectTermsOfServiceFragment2(termsOfServiceFragment);
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonC, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }

    @CanIgnoreReturnValue
    private DataCollectionFragment injectDataCollectionFragment2(DataCollectionFragment instance) {
      AbstractFragment_MembersInjector.injectViewModelFactory(instance, activityCImpl.viewModelFactory());
      DataCollectionFragment_MembersInjector.injectNavigator(instance, singletonC.navigatorProvider.get());
      DataCollectionFragment_MembersInjector.injectFeatureHelper(instance, activityCImpl.featureHelper());
      return instance;
    }

    @CanIgnoreReturnValue
    private FieldViewFactory injectFieldViewFactory(FieldViewFactory instance) {
      FieldViewFactory_MembersInjector.injectFragment(instance, fragment);
      FieldViewFactory_MembersInjector.injectViewModelFactory(instance, activityCImpl.viewModelFactory());
      return instance;
    }

    @CanIgnoreReturnValue
    private EditSubmissionFragment injectEditSubmissionFragment2(EditSubmissionFragment instance) {
      AbstractFragment_MembersInjector.injectViewModelFactory(instance, activityCImpl.viewModelFactory());
      EditSubmissionFragment_MembersInjector.injectNavigator(instance, singletonC.navigatorProvider.get());
      EditSubmissionFragment_MembersInjector.injectFieldViewFactory(instance, fieldViewFactory());
      EditSubmissionFragment_MembersInjector.injectPopups(instance, singletonC.ephemeralPopupsProvider.get());
      EditSubmissionFragment_MembersInjector.injectSchedulers(instance, singletonC.schedulersProvider.get());
      EditSubmissionFragment_MembersInjector.injectUserMediaRepository(instance, singletonC.userMediaRepositoryProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private HomeScreenFragment injectHomeScreenFragment2(HomeScreenFragment instance) {
      AbstractFragment_MembersInjector.injectViewModelFactory(instance, activityCImpl.viewModelFactory());
      HomeScreenFragment_MembersInjector.injectAuthenticationManager(instance, singletonC.fakeAuthenticationManagerProvider.get());
      HomeScreenFragment_MembersInjector.injectSchedulers(instance, singletonC.schedulersProvider.get());
      HomeScreenFragment_MembersInjector.injectNavigator(instance, singletonC.navigatorProvider.get());
      HomeScreenFragment_MembersInjector.injectPopups(instance, singletonC.ephemeralPopupsProvider.get());
      HomeScreenFragment_MembersInjector.injectFeatureHelper(instance, activityCImpl.featureHelper());
      HomeScreenFragment_MembersInjector.injectFeatureRepository(instance, singletonC.featureRepositoryProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private FeatureDetailsFragment injectFeatureDetailsFragment2(FeatureDetailsFragment instance) {
      AbstractFragment_MembersInjector.injectViewModelFactory(instance, activityCImpl.viewModelFactory());
      return instance;
    }

    @CanIgnoreReturnValue
    private SubmissionListFragment injectSubmissionListFragment2(SubmissionListFragment instance) {
      AbstractFragment_MembersInjector.injectViewModelFactory(instance, activityCImpl.viewModelFactory());
      SubmissionListFragment_MembersInjector.injectNavigator(instance, singletonC.navigatorProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private FeatureSelectorFragment injectFeatureSelectorFragment2(
        FeatureSelectorFragment instance) {
      AbstractDialogFragment_MembersInjector.injectViewModelFactory(instance, activityCImpl.viewModelFactory());
      FeatureSelectorFragment_MembersInjector.injectPopups(instance, singletonC.ephemeralPopupsProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private FeatureDataTypeSelectorDialogFragment injectFeatureDataTypeSelectorDialogFragment2(
        FeatureDataTypeSelectorDialogFragment instance) {
      AbstractDialogFragment_MembersInjector.injectViewModelFactory(instance, activityCImpl.viewModelFactory());
      return instance;
    }

    @CanIgnoreReturnValue
    private MapContainerFragment injectMapContainerFragment2(MapContainerFragment instance) {
      AbstractFragment_MembersInjector.injectViewModelFactory(instance, activityCImpl.viewModelFactory());
      AbstractMapViewerFragment_MembersInjector.injectMapFragment(instance, MapProviderModule_ProvidesGoogleMapFragmentFactory.providesGoogleMapFragment());
      MapContainerFragment_MembersInjector.injectMapsRepository(instance, singletonC.mapsRepositoryProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private MapTypeDialogFragment injectMapTypeDialogFragment2(MapTypeDialogFragment instance) {
      MapTypeDialogFragment_MembersInjector.injectMapsRepository(instance, singletonC.mapsRepositoryProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private PolygonDrawingInfoDialogFragment injectPolygonDrawingInfoDialogFragment2(
        PolygonDrawingInfoDialogFragment instance) {
      AbstractDialogFragment_MembersInjector.injectViewModelFactory(instance, activityCImpl.viewModelFactory());
      return instance;
    }

    @CanIgnoreReturnValue
    private GoogleMapsFragment injectGoogleMapsFragment2(GoogleMapsFragment instance) {
      GoogleMapsFragment_MembersInjector.injectBitmapUtil(instance, activityCImpl.bitmapUtil());
      GoogleMapsFragment_MembersInjector.injectMarkerIconFactory(instance, singletonC.markerIconFactoryProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private OfflineAreasFragment injectOfflineAreasFragment2(OfflineAreasFragment instance) {
      AbstractFragment_MembersInjector.injectViewModelFactory(instance, activityCImpl.viewModelFactory());
      OfflineAreasFragment_MembersInjector.injectNavigator(instance, singletonC.navigatorProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private OfflineAreaSelectorFragment injectOfflineAreaSelectorFragment2(
        OfflineAreaSelectorFragment instance) {
      AbstractFragment_MembersInjector.injectViewModelFactory(instance, activityCImpl.viewModelFactory());
      AbstractMapViewerFragment_MembersInjector.injectMapFragment(instance, MapProviderModule_ProvidesGoogleMapFragmentFactory.providesGoogleMapFragment());
      OfflineAreaSelectorFragment_MembersInjector.injectNavigator(instance, singletonC.navigatorProvider.get());
      OfflineAreaSelectorFragment_MembersInjector.injectPopups(instance, singletonC.ephemeralPopupsProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private OfflineAreaViewerFragment injectOfflineAreaViewerFragment2(
        OfflineAreaViewerFragment instance) {
      AbstractFragment_MembersInjector.injectViewModelFactory(instance, activityCImpl.viewModelFactory());
      AbstractMapViewerFragment_MembersInjector.injectMapFragment(instance, MapProviderModule_ProvidesGoogleMapFragmentFactory.providesGoogleMapFragment());
      OfflineAreaViewerFragment_MembersInjector.injectNavigator(instance, singletonC.navigatorProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private SignInFragment injectSignInFragment2(SignInFragment instance) {
      AbstractFragment_MembersInjector.injectViewModelFactory(instance, activityCImpl.viewModelFactory());
      return instance;
    }

    @CanIgnoreReturnValue
    private StartupFragment injectStartupFragment2(StartupFragment instance) {
      AbstractFragment_MembersInjector.injectViewModelFactory(instance, activityCImpl.viewModelFactory());
      StartupFragment_MembersInjector.injectGoogleApiManager(instance, activityCImpl.googleApiManagerProvider.get());
      StartupFragment_MembersInjector.injectAuthenticationManager(instance, singletonC.fakeAuthenticationManagerProvider.get());
      StartupFragment_MembersInjector.injectPopups(instance, singletonC.ephemeralPopupsProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private SubmissionDetailsFragment injectSubmissionDetailsFragment2(
        SubmissionDetailsFragment instance) {
      AbstractFragment_MembersInjector.injectViewModelFactory(instance, activityCImpl.viewModelFactory());
      SubmissionDetailsFragment_MembersInjector.injectNavigator(instance, singletonC.navigatorProvider.get());
      SubmissionDetailsFragment_MembersInjector.injectPopups(instance, singletonC.ephemeralPopupsProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private SurveySelectorDialogFragment injectSurveySelectorDialogFragment2(
        SurveySelectorDialogFragment instance) {
      AbstractDialogFragment_MembersInjector.injectViewModelFactory(instance, activityCImpl.viewModelFactory());
      SurveySelectorDialogFragment_MembersInjector.injectPopups(instance, singletonC.ephemeralPopupsProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private SyncStatusFragment injectSyncStatusFragment2(SyncStatusFragment instance) {
      AbstractFragment_MembersInjector.injectViewModelFactory(instance, activityCImpl.viewModelFactory());
      SyncStatusFragment_MembersInjector.injectNavigator(instance, singletonC.navigatorProvider.get());
      SyncStatusFragment_MembersInjector.injectFeatureHelper(instance, activityCImpl.featureHelper());
      return instance;
    }

    @CanIgnoreReturnValue
    private TermsOfServiceFragment injectTermsOfServiceFragment2(TermsOfServiceFragment instance) {
      AbstractFragment_MembersInjector.injectViewModelFactory(instance, activityCImpl.viewModelFactory());
      TermsOfServiceFragment_MembersInjector.injectPopups(instance, singletonC.ephemeralPopupsProvider.get());
      return instance;
    }
  }

  private static final class ViewCImpl extends MapsRepositoryTest_HiltComponents.ViewC {
    private final DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl, View viewParam) {
      this.singletonC = singletonC;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends MapsRepositoryTest_HiltComponents.ActivityC {
    private final DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private Provider<FeatureRepositionViewModel> featureRepositionViewModelProvider;

    private Provider<PolygonDrawingViewModel> polygonDrawingViewModelProvider;

    private Provider<MapContainerViewModel> mapContainerViewModelProvider;

    private Provider<OfflineAreaSelectorViewModel> offlineAreaSelectorViewModelProvider;

    private Provider<SyncStatusViewModel> syncStatusViewModelProvider;

    private Provider<DataCollectionViewModel> dataCollectionViewModelProvider;

    private Provider<OfflineAreasViewModel> offlineAreasViewModelProvider;

    private Provider<OfflineAreaViewerViewModel> offlineAreaViewerViewModelProvider;

    private Provider<MainViewModel> mainViewModelProvider;

    private Provider<SignInViewModel> signInViewModelProvider;

    private Provider<TermsOfServiceViewModel> termsOfServiceViewModelProvider;

    private Provider<HomeScreenViewModel> homeScreenViewModelProvider;

    private Provider<FeatureDetailsViewModel> featureDetailsViewModelProvider;

    private Provider<SurveySelectorViewModel> surveySelectorViewModelProvider;

    private Provider<SubmissionListItemViewModel> submissionListItemViewModelProvider;

    private Provider<SubmissionListViewModel> submissionListViewModelProvider;

    private Provider<SubmissionDetailsViewModel> submissionDetailsViewModelProvider;

    private Provider<EditSubmissionViewModel> editSubmissionViewModelProvider;

    private Provider<PhotoFieldViewModel> photoFieldViewModelProvider;

    private Provider<MultipleChoiceFieldViewModel> multipleChoiceFieldViewModelProvider;

    private Provider<TextFieldViewModel> textFieldViewModelProvider;

    private Provider<NumberFieldViewModel> numberFieldViewModelProvider;

    private Provider<DateFieldViewModel> dateFieldViewModelProvider;

    private Provider<TimeFieldViewModel> timeFieldViewModelProvider;

    private Provider<FeatureSelectorViewModel> featureSelectorViewModelProvider;

    private Provider<GoogleApiManager> googleApiManagerProvider;

    private ActivityCImpl(DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonC = singletonC;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(activityParam);

    }

    private DrawableUtil drawableUtil() {
      return new DrawableUtil(ApplicationContextModule_ProvideApplicationFactory.provideApplication(singletonC.applicationContextModule));
    }

    private PolygonDrawingViewModel polygonDrawingViewModel() {
      return new PolygonDrawingViewModel(singletonC.locationManagerProvider.get(), singletonC.fakeAuthenticationManagerProvider.get(), singletonC.offlineUuidGeneratorProvider.get());
    }

    private TileSetDownloadWorkManager tileSetDownloadWorkManager() {
      return new TileSetDownloadWorkManager(singletonC.provideWorkManagerProvider.get(), singletonC.localValueStoreProvider.get());
    }

    private MbtilesFootprintParser mbtilesFootprintParser() {
      return MbtilesFootprintParser_Factory.newInstance(singletonC.offlineUuidGeneratorProvider.get());
    }

    private OfflineAreaRepository offlineAreaRepository() {
      return new OfflineAreaRepository(tileSetDownloadWorkManager(), singletonC.roomLocalDataStoreProvider.get(), singletonC.surveyRepositoryProvider.get(), mbtilesFootprintParser(), singletonC.fileUtil(), singletonC.schedulersProvider.get(), singletonC.geocodingManagerProvider.get(), singletonC.offlineUuidGeneratorProvider.get());
    }

    private MapContainerViewModel mapContainerViewModel() {
      return MapContainerViewModel_Factory.newInstance(singletonC.resources(), singletonC.surveyRepositoryProvider.get(), singletonC.featureRepositoryProvider.get(), singletonC.locationManagerProvider.get(), offlineAreaRepository());
    }

    private OfflineAreaSelectorViewModel offlineAreaSelectorViewModel() {
      return OfflineAreaSelectorViewModel_Factory.newInstance(offlineAreaRepository(), singletonC.offlineUuidGeneratorProvider.get(), singletonC.resources());
    }

    private SyncStatusViewModel syncStatusViewModel() {
      return SyncStatusViewModel_Factory.newInstance(singletonC.surveyRepositoryProvider.get(), singletonC.featureRepositoryProvider.get(), singletonC.navigatorProvider.get());
    }

    private OfflineAreasViewModel offlineAreasViewModel() {
      return OfflineAreasViewModel_Factory.newInstance(singletonC.navigatorProvider.get(), offlineAreaRepository());
    }

    private OfflineAreaViewerViewModel offlineAreaViewerViewModel() {
      return injectOfflineAreaViewerViewModel(OfflineAreaViewerViewModel_Factory.newInstance(offlineAreaRepository(), ApplicationContextModule_ProvideContextFactory.provideContext(singletonC.applicationContextModule), singletonC.navigatorProvider.get()));
    }

    private TermsOfServiceRepository termsOfServiceRepository() {
      return new TermsOfServiceRepository(singletonC.fakeRemoteDataStoreProvider.get(), singletonC.localValueStoreProvider.get());
    }

    private MainViewModel mainViewModel() {
      return new MainViewModel(singletonC.surveyRepositoryProvider.get(), singletonC.featureRepositoryProvider.get(), singletonC.userRepository(), termsOfServiceRepository(), singletonC.ephemeralPopupsProvider.get(), singletonC.navigatorProvider.get(), singletonC.fakeAuthenticationManagerProvider.get(), singletonC.schedulersProvider.get());
    }

    private SignInViewModel signInViewModel() {
      return SignInViewModel_Factory.newInstance(singletonC.fakeAuthenticationManagerProvider.get());
    }

    private TermsOfServiceViewModel termsOfServiceViewModel() {
      return new TermsOfServiceViewModel(singletonC.navigatorProvider.get(), termsOfServiceRepository());
    }

    private HomeScreenViewModel homeScreenViewModel() {
      return HomeScreenViewModel_Factory.newInstance(singletonC.surveyRepositoryProvider.get(), singletonC.featureRepositoryProvider.get(), singletonC.navigatorProvider.get(), singletonC.userRepository());
    }

    private FeatureHelper featureHelper() {
      return FeatureHelper_Factory.newInstance(singletonC.resources());
    }

    private SubmissionRepository submissionRepository() {
      return new SubmissionRepository(singletonC.roomLocalDataStoreProvider.get(), singletonC.fakeRemoteDataStoreProvider.get(), singletonC.featureRepositoryProvider.get(), singletonC.dataSyncWorkManager(), singletonC.offlineUuidGeneratorProvider.get(), singletonC.fakeAuthenticationManagerProvider.get());
    }

    private FeatureDetailsViewModel featureDetailsViewModel() {
      return new FeatureDetailsViewModel(singletonC.markerIconFactoryProvider.get(), drawableUtil(), featureHelper(), singletonC.featureRepositoryProvider.get(), submissionRepository(), singletonC.userRepository());
    }

    private SurveySelectorViewModel surveySelectorViewModel() {
      return SurveySelectorViewModel_Factory.newInstance(singletonC.surveyRepositoryProvider.get(), singletonC.fakeAuthenticationManagerProvider.get());
    }

    private SubmissionListItemViewModel submissionListItemViewModel() {
      return SubmissionListItemViewModel_Factory.newInstance(ApplicationContextModule_ProvideApplicationFactory.provideApplication(singletonC.applicationContextModule));
    }

    private SubmissionListViewModel submissionListViewModel() {
      return new SubmissionListViewModel(submissionRepository());
    }

    private SubmissionDetailsViewModel submissionDetailsViewModel() {
      return SubmissionDetailsViewModel_Factory.newInstance(submissionRepository(), featureHelper());
    }

    private BitmapUtil bitmapUtil() {
      return BitmapUtil_Factory.newInstance(ApplicationContextModule_ProvideContextFactory.provideContext(singletonC.applicationContextModule));
    }

    private EditSubmissionViewModel editSubmissionViewModel() {
      return EditSubmissionViewModel_Factory.newInstance(singletonC.resources(), submissionRepository(), singletonC.permissionsManagerProvider.get(), bitmapUtil());
    }

    private PhotoFieldViewModel photoFieldViewModel() {
      return PhotoFieldViewModel_Factory.newInstance(singletonC.userMediaRepositoryProvider.get(), singletonC.resources());
    }

    private MultipleChoiceFieldViewModel multipleChoiceFieldViewModel() {
      return MultipleChoiceFieldViewModel_Factory.newInstance(singletonC.resources());
    }

    private TextFieldViewModel textFieldViewModel() {
      return TextFieldViewModel_Factory.newInstance(singletonC.resources());
    }

    private NumberFieldViewModel numberFieldViewModel() {
      return NumberFieldViewModel_Factory.newInstance(singletonC.resources());
    }

    private DateFieldViewModel dateFieldViewModel() {
      return DateFieldViewModel_Factory.newInstance(singletonC.resources());
    }

    private TimeFieldViewModel timeFieldViewModel() {
      return TimeFieldViewModel_Factory.newInstance(singletonC.resources());
    }

    private FeatureSelectorViewModel featureSelectorViewModel() {
      return FeatureSelectorViewModel_Factory.newInstance(featureHelper(), singletonC.resources());
    }

    private Map<Class<? extends ViewModel>, Provider<ViewModel>> mapOfClassOfAndProviderOfViewModel(
        ) {
      return ImmutableMap.<Class<? extends ViewModel>, Provider<ViewModel>>builderWithExpectedSize(25).put(FeatureRepositionViewModel.class, (Provider) featureRepositionViewModelProvider).put(PolygonDrawingViewModel.class, (Provider) polygonDrawingViewModelProvider).put(MapContainerViewModel.class, (Provider) mapContainerViewModelProvider).put(OfflineAreaSelectorViewModel.class, (Provider) offlineAreaSelectorViewModelProvider).put(SyncStatusViewModel.class, (Provider) syncStatusViewModelProvider).put(DataCollectionViewModel.class, (Provider) dataCollectionViewModelProvider).put(OfflineAreasViewModel.class, (Provider) offlineAreasViewModelProvider).put(OfflineAreaViewerViewModel.class, (Provider) offlineAreaViewerViewModelProvider).put(MainViewModel.class, (Provider) mainViewModelProvider).put(SignInViewModel.class, (Provider) signInViewModelProvider).put(TermsOfServiceViewModel.class, (Provider) termsOfServiceViewModelProvider).put(HomeScreenViewModel.class, (Provider) homeScreenViewModelProvider).put(FeatureDetailsViewModel.class, (Provider) featureDetailsViewModelProvider).put(SurveySelectorViewModel.class, (Provider) surveySelectorViewModelProvider).put(SubmissionListItemViewModel.class, (Provider) submissionListItemViewModelProvider).put(SubmissionListViewModel.class, (Provider) submissionListViewModelProvider).put(SubmissionDetailsViewModel.class, (Provider) submissionDetailsViewModelProvider).put(EditSubmissionViewModel.class, (Provider) editSubmissionViewModelProvider).put(PhotoFieldViewModel.class, (Provider) photoFieldViewModelProvider).put(MultipleChoiceFieldViewModel.class, (Provider) multipleChoiceFieldViewModelProvider).put(TextFieldViewModel.class, (Provider) textFieldViewModelProvider).put(NumberFieldViewModel.class, (Provider) numberFieldViewModelProvider).put(DateFieldViewModel.class, (Provider) dateFieldViewModelProvider).put(TimeFieldViewModel.class, (Provider) timeFieldViewModelProvider).put(FeatureSelectorViewModel.class, (Provider) featureSelectorViewModelProvider).build();
    }

    private ViewModelFactory viewModelFactory() {
      return new ViewModelFactory(mapOfClassOfAndProviderOfViewModel());
    }

    private GoogleApiManager googleApiManager() {
      return new GoogleApiManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonC.applicationContextModule), singletonC.googleApiAvailabilityProvider.get(), singletonC.activityStreamsProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final Activity activityParam) {
      this.featureRepositionViewModelProvider = new SwitchingProvider<>(singletonC, activityRetainedCImpl, activityCImpl, 0);
      this.polygonDrawingViewModelProvider = new SwitchingProvider<>(singletonC, activityRetainedCImpl, activityCImpl, 1);
      this.mapContainerViewModelProvider = new SwitchingProvider<>(singletonC, activityRetainedCImpl, activityCImpl, 2);
      this.offlineAreaSelectorViewModelProvider = new SwitchingProvider<>(singletonC, activityRetainedCImpl, activityCImpl, 3);
      this.syncStatusViewModelProvider = new SwitchingProvider<>(singletonC, activityRetainedCImpl, activityCImpl, 4);
      this.dataCollectionViewModelProvider = new SwitchingProvider<>(singletonC, activityRetainedCImpl, activityCImpl, 5);
      this.offlineAreasViewModelProvider = new SwitchingProvider<>(singletonC, activityRetainedCImpl, activityCImpl, 6);
      this.offlineAreaViewerViewModelProvider = new SwitchingProvider<>(singletonC, activityRetainedCImpl, activityCImpl, 7);
      this.mainViewModelProvider = new SwitchingProvider<>(singletonC, activityRetainedCImpl, activityCImpl, 8);
      this.signInViewModelProvider = new SwitchingProvider<>(singletonC, activityRetainedCImpl, activityCImpl, 9);
      this.termsOfServiceViewModelProvider = new SwitchingProvider<>(singletonC, activityRetainedCImpl, activityCImpl, 10);
      this.homeScreenViewModelProvider = new SwitchingProvider<>(singletonC, activityRetainedCImpl, activityCImpl, 11);
      this.featureDetailsViewModelProvider = new SwitchingProvider<>(singletonC, activityRetainedCImpl, activityCImpl, 12);
      this.surveySelectorViewModelProvider = new SwitchingProvider<>(singletonC, activityRetainedCImpl, activityCImpl, 13);
      this.submissionListItemViewModelProvider = new SwitchingProvider<>(singletonC, activityRetainedCImpl, activityCImpl, 14);
      this.submissionListViewModelProvider = new SwitchingProvider<>(singletonC, activityRetainedCImpl, activityCImpl, 15);
      this.submissionDetailsViewModelProvider = new SwitchingProvider<>(singletonC, activityRetainedCImpl, activityCImpl, 16);
      this.editSubmissionViewModelProvider = new SwitchingProvider<>(singletonC, activityRetainedCImpl, activityCImpl, 17);
      this.photoFieldViewModelProvider = new SwitchingProvider<>(singletonC, activityRetainedCImpl, activityCImpl, 18);
      this.multipleChoiceFieldViewModelProvider = new SwitchingProvider<>(singletonC, activityRetainedCImpl, activityCImpl, 19);
      this.textFieldViewModelProvider = new SwitchingProvider<>(singletonC, activityRetainedCImpl, activityCImpl, 20);
      this.numberFieldViewModelProvider = new SwitchingProvider<>(singletonC, activityRetainedCImpl, activityCImpl, 21);
      this.dateFieldViewModelProvider = new SwitchingProvider<>(singletonC, activityRetainedCImpl, activityCImpl, 22);
      this.timeFieldViewModelProvider = new SwitchingProvider<>(singletonC, activityRetainedCImpl, activityCImpl, 23);
      this.featureSelectorViewModelProvider = new SwitchingProvider<>(singletonC, activityRetainedCImpl, activityCImpl, 24);
      this.googleApiManagerProvider = DoubleCheck.provider(new SwitchingProvider<GoogleApiManager>(singletonC, activityRetainedCImpl, activityCImpl, 25));
    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
      injectMainActivity2(mainActivity);
    }

    @Override
    public void injectSettingsActivity(SettingsActivity settingsActivity) {
      injectSettingsActivity2(settingsActivity);
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(ApplicationContextModule_ProvideApplicationFactory.provideApplication(singletonC.applicationContextModule), ImmutableSet.<String>of(), new ViewModelCBuilder(singletonC, activityRetainedCImpl));
    }

    @Override
    public Set<String> getViewModelKeys() {
      return ImmutableSet.<String>of();
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonC, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonC, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonC, activityRetainedCImpl, activityCImpl);
    }

    @CanIgnoreReturnValue
    private OfflineAreaViewerViewModel injectOfflineAreaViewerViewModel(
        OfflineAreaViewerViewModel instance) {
      OfflineAreaViewerViewModel_MembersInjector.injectNavigator(instance, singletonC.navigatorProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private MainActivity injectMainActivity2(MainActivity instance) {
      AbstractActivity_MembersInjector.injectDrawableUtil(instance, drawableUtil());
      MainActivity_MembersInjector.injectActivityStreams(instance, singletonC.activityStreamsProvider.get());
      MainActivity_MembersInjector.injectErrorManager(instance, singletonC.applicationErrorManagerProvider.get());
      MainActivity_MembersInjector.injectViewModelFactory(instance, viewModelFactory());
      MainActivity_MembersInjector.injectSettingsManager(instance, singletonC.settingsManagerProvider.get());
      MainActivity_MembersInjector.injectNavigator(instance, singletonC.navigatorProvider.get());
      MainActivity_MembersInjector.injectUserRepository(instance, singletonC.userRepository());
      MainActivity_MembersInjector.injectPopups(instance, singletonC.ephemeralPopupsProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private SettingsActivity injectSettingsActivity2(SettingsActivity instance) {
      AbstractActivity_MembersInjector.injectDrawableUtil(instance, drawableUtil());
      return instance;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ActivityCImpl activityCImpl;

      private final int id;

      SwitchingProvider(DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC,
          ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl, int id) {
        this.singletonC = singletonC;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.activityCImpl = activityCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.google.android.gnd.ui.home.mapcontainer.FeatureRepositionViewModel 
          return (T) FeatureRepositionViewModel_Factory.newInstance();

          case 1: // com.google.android.gnd.ui.home.mapcontainer.PolygonDrawingViewModel 
          return (T) activityCImpl.polygonDrawingViewModel();

          case 2: // com.google.android.gnd.ui.home.mapcontainer.MapContainerViewModel 
          return (T) activityCImpl.mapContainerViewModel();

          case 3: // com.google.android.gnd.ui.offlinebasemap.selector.OfflineAreaSelectorViewModel 
          return (T) activityCImpl.offlineAreaSelectorViewModel();

          case 4: // com.google.android.gnd.ui.syncstatus.SyncStatusViewModel 
          return (T) activityCImpl.syncStatusViewModel();

          case 5: // com.google.android.gnd.ui.datacollection.DataCollectionViewModel 
          return (T) new DataCollectionViewModel();

          case 6: // com.google.android.gnd.ui.offlinebasemap.OfflineAreasViewModel 
          return (T) activityCImpl.offlineAreasViewModel();

          case 7: // com.google.android.gnd.ui.offlinebasemap.viewer.OfflineAreaViewerViewModel 
          return (T) activityCImpl.offlineAreaViewerViewModel();

          case 8: // com.google.android.gnd.MainViewModel 
          return (T) activityCImpl.mainViewModel();

          case 9: // com.google.android.gnd.ui.signin.SignInViewModel 
          return (T) activityCImpl.signInViewModel();

          case 10: // com.google.android.gnd.ui.tos.TermsOfServiceViewModel 
          return (T) activityCImpl.termsOfServiceViewModel();

          case 11: // com.google.android.gnd.ui.home.HomeScreenViewModel 
          return (T) activityCImpl.homeScreenViewModel();

          case 12: // com.google.android.gnd.ui.home.featuredetails.FeatureDetailsViewModel 
          return (T) activityCImpl.featureDetailsViewModel();

          case 13: // com.google.android.gnd.ui.surveyselector.SurveySelectorViewModel 
          return (T) activityCImpl.surveySelectorViewModel();

          case 14: // com.google.android.gnd.ui.home.featuredetails.SubmissionListItemViewModel 
          return (T) activityCImpl.submissionListItemViewModel();

          case 15: // com.google.android.gnd.ui.home.featuredetails.SubmissionListViewModel 
          return (T) activityCImpl.submissionListViewModel();

          case 16: // com.google.android.gnd.ui.submissiondetails.SubmissionDetailsViewModel 
          return (T) activityCImpl.submissionDetailsViewModel();

          case 17: // com.google.android.gnd.ui.editsubmission.EditSubmissionViewModel 
          return (T) activityCImpl.editSubmissionViewModel();

          case 18: // com.google.android.gnd.ui.editsubmission.PhotoFieldViewModel 
          return (T) activityCImpl.photoFieldViewModel();

          case 19: // com.google.android.gnd.ui.editsubmission.MultipleChoiceFieldViewModel 
          return (T) activityCImpl.multipleChoiceFieldViewModel();

          case 20: // com.google.android.gnd.ui.editsubmission.TextFieldViewModel 
          return (T) activityCImpl.textFieldViewModel();

          case 21: // com.google.android.gnd.ui.editsubmission.NumberFieldViewModel 
          return (T) activityCImpl.numberFieldViewModel();

          case 22: // com.google.android.gnd.ui.editsubmission.DateFieldViewModel 
          return (T) activityCImpl.dateFieldViewModel();

          case 23: // com.google.android.gnd.ui.editsubmission.TimeFieldViewModel 
          return (T) activityCImpl.timeFieldViewModel();

          case 24: // com.google.android.gnd.ui.home.featureselector.FeatureSelectorViewModel 
          return (T) activityCImpl.featureSelectorViewModel();

          case 25: // com.google.android.gnd.system.GoogleApiManager 
          return (T) activityCImpl.googleApiManager();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ViewModelCImpl extends MapsRepositoryTest_HiltComponents.ViewModelC {
    private final DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private ViewModelCImpl(DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam) {
      this.singletonC = singletonC;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public Map<String, Provider<ViewModel>> getHiltViewModelMap() {
      return ImmutableMap.<String, Provider<ViewModel>>of();
    }
  }

  private static final class ActivityRetainedCImpl extends MapsRepositoryTest_HiltComponents.ActivityRetainedC {
    private final DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    @SuppressWarnings("rawtypes")
    private Provider lifecycleProvider;

    private ActivityRetainedCImpl(DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC) {
      this.singletonC = singletonC;

      initialize();

    }

    @SuppressWarnings("unchecked")
    private void initialize() {
      this.lifecycleProvider = DoubleCheck.provider(new SwitchingProvider<Object>(singletonC, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonC, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return (ActivityRetainedLifecycle) lifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC,
          ActivityRetainedCImpl activityRetainedCImpl, int id) {
        this.singletonC = singletonC;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.internal.managers.ActivityRetainedComponentManager.Lifecycle 
          return (T) ActivityRetainedComponentManager_Lifecycle_Factory.newInstance();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends MapsRepositoryTest_HiltComponents.ServiceC {
    private final DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC,
        Service serviceParam) {
      this.singletonC = singletonC;


    }
  }

  private static final class SwitchingProvider<T> implements Provider<T> {
    private final DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC;

    private final int id;

    SwitchingProvider(DaggerMapsRepositoryTest_HiltComponents_SingletonC singletonC, int id) {
      this.singletonC = singletonC;
      this.id = id;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get() {
      switch (id) {
        case 0: // com.google.android.gnd.persistence.sync.LocalMutationSyncWorker_AssistedFactory 
        return (T) singletonC.localMutationSyncWorker_AssistedFactory();

        case 1: // com.google.android.gnd.persistence.local.room.RoomLocalDataStore 
        return (T) singletonC.roomLocalDataStore();

        case 2: // com.google.android.gnd.persistence.local.room.LocalDatabase 
        return (T) singletonC.localDatabase();

        case 3: // com.google.android.gnd.TestScheduler 
        return (T) TestScheduler_Factory.newInstance();

        case 4: // com.google.android.gnd.persistence.remote.FakeRemoteDataStore 
        return (T) FakeRemoteDataStore_Factory.newInstance();

        case 5: // com.google.android.gnd.system.NotificationManager 
        return (T) singletonC.notificationManager();

        case 6: // androidx.work.WorkManager 
        return (T) TestWorkManagerModule_ProvideWorkManagerFactory.provideWorkManager();

        case 7: // com.google.android.gnd.persistence.local.LocalValueStore 
        return (T) singletonC.localValueStore();

        case 8: // android.content.SharedPreferences 
        return (T) singletonC.sharedPreferences();

        case 9: // com.google.android.gnd.repository.UserMediaRepository 
        return (T) singletonC.userMediaRepository();

        case 10: // com.google.android.gnd.persistence.remote.FakeRemoteStorageManager 
        return (T) FakeRemoteStorageManager_Factory.newInstance();

        case 11: // com.google.android.gnd.persistence.uuid.FakeUuidGenerator 
        return (T) FakeUuidGenerator_Factory.newInstance();

        case 12: // com.google.android.gnd.persistence.sync.PhotoSyncWorker_AssistedFactory 
        return (T) singletonC.photoSyncWorker_AssistedFactory();

        case 13: // com.google.android.gnd.persistence.sync.TileSetDownloadWorker_AssistedFactory 
        return (T) singletonC.tileSetDownloadWorker_AssistedFactory();

        case 14: // com.google.android.gnd.repository.MapsRepository 
        return (T) singletonC.mapsRepository();

        case 15: // com.google.android.gnd.system.ActivityStreams 
        return (T) new ActivityStreams();

        case 16: // com.google.android.gnd.system.ApplicationErrorManager 
        return (T) singletonC.applicationErrorManager();

        case 17: // com.google.android.gnd.system.LocationManager 
        return (T) singletonC.locationManager();

        case 18: // com.google.android.gnd.system.PermissionsManager 
        return (T) singletonC.permissionsManager();

        case 19: // com.google.android.gnd.system.SettingsManager 
        return (T) singletonC.settingsManager();

        case 20: // com.google.android.gnd.system.auth.FakeAuthenticationManager 
        return (T) new FakeAuthenticationManager();

        case 21: // com.google.android.gnd.repository.SurveyRepository 
        return (T) singletonC.surveyRepository();

        case 22: // com.google.android.gnd.repository.FeatureRepository 
        return (T) singletonC.featureRepository();

        case 23: // com.google.android.gnd.system.GeocodingManager 
        return (T) singletonC.geocodingManager();

        case 24: // android.location.Geocoder 
        return (T) singletonC.geocoder();

        case 25: // com.google.android.gnd.ui.common.Navigator 
        return (T) new Navigator();

        case 26: // com.google.android.gnd.ui.common.EphemeralPopups 
        return (T) singletonC.ephemeralPopups();

        case 27: // com.google.android.gnd.ui.MarkerIconFactory 
        return (T) singletonC.markerIconFactory();

        case 28: // com.google.android.gms.common.GoogleApiAvailability 
        return (T) GndApplicationModule_GoogleApiAvailabilityFactory.googleApiAvailability();

        default: throw new AssertionError(id);
      }
    }
  }
}
