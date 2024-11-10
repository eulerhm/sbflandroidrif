package com.google.android.gnd;

import androidx.hilt.lifecycle.ViewModelFactoryModules;
import androidx.hilt.work.HiltWrapper_WorkerFactoryModule;
import com.google.android.gnd.persistence.local.LocalDataStoreModule;
import com.google.android.gnd.persistence.sync.LocalMutationSyncWorker_HiltModule;
import com.google.android.gnd.persistence.sync.PhotoSyncWorker_HiltModule;
import com.google.android.gnd.persistence.sync.TileSetDownloadWorker_HiltModule;
import com.google.android.gnd.ui.common.ViewModelModule;
import com.google.android.gnd.ui.datacollection.DataCollectionFragment_GeneratedInjector;
import com.google.android.gnd.ui.editsubmission.EditSubmissionFragment_GeneratedInjector;
import com.google.android.gnd.ui.home.HomeScreenFragment_GeneratedInjector;
import com.google.android.gnd.ui.home.featuredetails.FeatureDetailsFragment_GeneratedInjector;
import com.google.android.gnd.ui.home.featuredetails.FragmentManagerModule;
import com.google.android.gnd.ui.home.featuredetails.SubmissionListFragment_GeneratedInjector;
import com.google.android.gnd.ui.home.featureselector.FeatureSelectorFragment_GeneratedInjector;
import com.google.android.gnd.ui.home.mapcontainer.FeatureDataTypeSelectorDialogFragment_GeneratedInjector;
import com.google.android.gnd.ui.home.mapcontainer.FeatureRepositionView_GeneratedInjector;
import com.google.android.gnd.ui.home.mapcontainer.MapContainerFragment_GeneratedInjector;
import com.google.android.gnd.ui.home.mapcontainer.MapTypeDialogFragment_GeneratedInjector;
import com.google.android.gnd.ui.home.mapcontainer.PolygonDrawingInfoDialogFragment_GeneratedInjector;
import com.google.android.gnd.ui.home.mapcontainer.PolygonDrawingView_GeneratedInjector;
import com.google.android.gnd.ui.map.MapProviderModule;
import com.google.android.gnd.ui.map.gms.GoogleMapsFragment_GeneratedInjector;
import com.google.android.gnd.ui.offlinebasemap.OfflineAreasFragment_GeneratedInjector;
import com.google.android.gnd.ui.offlinebasemap.selector.OfflineAreaSelectorFragment_GeneratedInjector;
import com.google.android.gnd.ui.offlinebasemap.viewer.OfflineAreaViewerFragment_GeneratedInjector;
import com.google.android.gnd.ui.signin.SignInFragment_GeneratedInjector;
import com.google.android.gnd.ui.startup.StartupFragment_GeneratedInjector;
import com.google.android.gnd.ui.submissiondetails.SubmissionDetailsFragment_GeneratedInjector;
import com.google.android.gnd.ui.surveyselector.SurveySelectorDialogFragment_GeneratedInjector;
import com.google.android.gnd.ui.syncstatus.SyncStatusFragment_GeneratedInjector;
import com.google.android.gnd.ui.tos.TermsOfServiceFragment_GeneratedInjector;
import dagger.Binds;
import dagger.Component;
import dagger.Module;
import dagger.Subcomponent;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.ActivityRetainedComponent;
import dagger.hilt.android.components.FragmentComponent;
import dagger.hilt.android.components.ServiceComponent;
import dagger.hilt.android.components.ViewComponent;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.components.ViewWithFragmentComponent;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_DefaultViewModelFactories_ActivityModule;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_HiltViewModelFactory_ActivityCreatorEntryPoint;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_HiltViewModelFactory_ViewModelModule;
import dagger.hilt.android.internal.managers.ActivityComponentManager;
import dagger.hilt.android.internal.managers.FragmentComponentManager;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedComponentBuilderEntryPoint;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedLifecycleEntryPoint;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_LifecycleModule;
import dagger.hilt.android.internal.managers.ServiceComponentManager;
import dagger.hilt.android.internal.managers.ViewComponentManager;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.HiltWrapper_ActivityModule;
import dagger.hilt.android.scopes.ActivityRetainedScoped;
import dagger.hilt.android.scopes.ActivityScoped;
import dagger.hilt.android.scopes.FragmentScoped;
import dagger.hilt.android.scopes.ServiceScoped;
import dagger.hilt.android.scopes.ViewModelScoped;
import dagger.hilt.android.scopes.ViewScoped;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.internal.GeneratedComponent;
import dagger.hilt.internal.TestSingletonComponent;
import dagger.hilt.migration.DisableInstallInCheck;
import javax.inject.Singleton;

public final class AddFeatureTest_HiltComponents {
  private AddFeatureTest_HiltComponents() {
  }

  @Module(
      subcomponents = ServiceC.class
  )
  @DisableInstallInCheck
  abstract interface ServiceCBuilderModule {
    @Binds
    ServiceComponentBuilder bind(ServiceC.Builder builder);
  }

  @Module(
      subcomponents = ActivityRetainedC.class
  )
  @DisableInstallInCheck
  abstract interface ActivityRetainedCBuilderModule {
    @Binds
    ActivityRetainedComponentBuilder bind(ActivityRetainedC.Builder builder);
  }

  @Module(
      subcomponents = ActivityC.class
  )
  @DisableInstallInCheck
  abstract interface ActivityCBuilderModule {
    @Binds
    ActivityComponentBuilder bind(ActivityC.Builder builder);
  }

  @Module(
      subcomponents = ViewModelC.class
  )
  @DisableInstallInCheck
  abstract interface ViewModelCBuilderModule {
    @Binds
    ViewModelComponentBuilder bind(ViewModelC.Builder builder);
  }

  @Module(
      subcomponents = ViewC.class
  )
  @DisableInstallInCheck
  abstract interface ViewCBuilderModule {
    @Binds
    ViewComponentBuilder bind(ViewC.Builder builder);
  }

  @Module(
      subcomponents = FragmentC.class
  )
  @DisableInstallInCheck
  abstract interface FragmentCBuilderModule {
    @Binds
    FragmentComponentBuilder bind(FragmentC.Builder builder);
  }

  @Module(
      subcomponents = ViewWithFragmentC.class
  )
  @DisableInstallInCheck
  abstract interface ViewWithFragmentCBuilderModule {
    @Binds
    ViewWithFragmentComponentBuilder bind(ViewWithFragmentC.Builder builder);
  }

  @Component(
      modules = {
          ActivityRetainedCBuilderModule.class,
          ServiceCBuilderModule.class,
          ApplicationContextModule.class,
          HiltWrapper_GndApplicationModule.class,
          HiltWrapper_TestAuthenticationModule.class,
          HiltWrapper_TestLocalDatabaseModule.class,
          HiltWrapper_TestRemoteStorageModule.class,
          HiltWrapper_TestSchedulersModule.class,
          HiltWrapper_TestWorkManagerModule.class,
          HiltWrapper_WorkerFactoryModule.class,
          LocalDataStoreModule.class,
          LocalMutationSyncWorker_HiltModule.class,
          MapProviderModule.class,
          PhotoSyncWorker_HiltModule.class,
          TileSetDownloadWorker_HiltModule.class,
          ViewModelModule.class
      }
  )
  @Singleton
  public abstract static class SingletonC implements AddFeatureTest_GeneratedInjector,
      GndApplication_GeneratedInjector,
      HiltWrapper_SetPreferencesRule_SetPreferencesRuleEntryPoint,
      HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedComponentBuilderEntryPoint,
      ServiceComponentManager.ServiceComponentBuilderEntryPoint,
      SingletonComponent,
      TestSingletonComponent {
  }

  @Subcomponent
  @ServiceScoped
  public abstract static class ServiceC implements ServiceComponent,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ServiceComponentBuilder {
    }
  }

  @Subcomponent(
      modules = {
          ActivityCBuilderModule.class,
          ViewModelCBuilderModule.class,
          HiltWrapper_ActivityRetainedComponentManager_LifecycleModule.class
      }
  )
  @ActivityRetainedScoped
  public abstract static class ActivityRetainedC implements ActivityRetainedComponent,
      ActivityComponentManager.ActivityComponentBuilderEntryPoint,
      HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedLifecycleEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ActivityRetainedComponentBuilder {
    }
  }

  @Subcomponent(
      modules = {
          FragmentCBuilderModule.class,
          ViewCBuilderModule.class,
          HiltWrapper_ActivityModule.class,
          HiltWrapper_DefaultViewModelFactories_ActivityModule.class,
          ViewModelFactoryModules.ActivityModule.class
      }
  )
  @ActivityScoped
  public abstract static class ActivityC implements MainActivity_GeneratedInjector,
      SettingsActivity_GeneratedInjector,
      ActivityComponent,
      DefaultViewModelFactories.ActivityEntryPoint,
      HiltWrapper_HiltViewModelFactory_ActivityCreatorEntryPoint,
      FragmentComponentManager.FragmentComponentBuilderEntryPoint,
      ViewComponentManager.ViewComponentBuilderEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ActivityComponentBuilder {
    }
  }

  @Subcomponent(
      modules = HiltWrapper_HiltViewModelFactory_ViewModelModule.class
  )
  @ViewModelScoped
  public abstract static class ViewModelC implements ViewModelComponent,
      HiltViewModelFactory.ViewModelFactoriesEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ViewModelComponentBuilder {
    }
  }

  @Subcomponent
  @ViewScoped
  public abstract static class ViewC implements ViewComponent,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ViewComponentBuilder {
    }
  }

  @Subcomponent(
      modules = {
          ViewWithFragmentCBuilderModule.class,
          FragmentManagerModule.class,
          ViewModelFactoryModules.FragmentModule.class
      }
  )
  @FragmentScoped
  public abstract static class FragmentC implements DataCollectionFragment_GeneratedInjector,
      EditSubmissionFragment_GeneratedInjector,
      HomeScreenFragment_GeneratedInjector,
      FeatureDetailsFragment_GeneratedInjector,
      SubmissionListFragment_GeneratedInjector,
      FeatureSelectorFragment_GeneratedInjector,
      FeatureDataTypeSelectorDialogFragment_GeneratedInjector,
      MapContainerFragment_GeneratedInjector,
      MapTypeDialogFragment_GeneratedInjector,
      PolygonDrawingInfoDialogFragment_GeneratedInjector,
      GoogleMapsFragment_GeneratedInjector,
      OfflineAreasFragment_GeneratedInjector,
      OfflineAreaSelectorFragment_GeneratedInjector,
      OfflineAreaViewerFragment_GeneratedInjector,
      SignInFragment_GeneratedInjector,
      StartupFragment_GeneratedInjector,
      SubmissionDetailsFragment_GeneratedInjector,
      SurveySelectorDialogFragment_GeneratedInjector,
      SyncStatusFragment_GeneratedInjector,
      TermsOfServiceFragment_GeneratedInjector,
      FragmentComponent,
      DefaultViewModelFactories.FragmentEntryPoint,
      ViewComponentManager.ViewWithFragmentComponentBuilderEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends FragmentComponentBuilder {
    }
  }

  @Subcomponent
  @ViewScoped
  public abstract static class ViewWithFragmentC implements FeatureRepositionView_GeneratedInjector,
      PolygonDrawingView_GeneratedInjector,
      ViewWithFragmentComponent,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ViewWithFragmentComponentBuilder {
    }
  }
}
