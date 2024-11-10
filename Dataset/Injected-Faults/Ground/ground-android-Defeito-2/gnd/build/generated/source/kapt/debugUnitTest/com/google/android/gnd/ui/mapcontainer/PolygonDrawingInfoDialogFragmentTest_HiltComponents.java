package com.google.android.gnd.ui.mapcontainer;

import androidx.hilt.lifecycle.ViewModelFactoryModules;
import androidx.hilt.work.HiltWrapper_WorkerFactoryModule;
import com.google.android.gnd.GndApplication_GeneratedInjector;
import com.google.android.gnd.HiltWrapper_GndApplicationModule;
import com.google.android.gnd.HiltWrapper_TestAuthenticationModule;
import com.google.android.gnd.HiltWrapper_TestLocalDatabaseModule;
import com.google.android.gnd.HiltWrapper_TestRemoteStorageModule;
import com.google.android.gnd.HiltWrapper_TestSchedulersModule;
import com.google.android.gnd.HiltWrapper_TestWorkManagerModule;
import com.google.android.gnd.MainActivity_GeneratedInjector;
import com.google.android.gnd.SettingsActivity_GeneratedInjector;
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
import javax.annotation.Generated;
import javax.inject.Singleton;

@Generated("dagger.hilt.processor.internal.root.RootProcessor")
public final class PolygonDrawingInfoDialogFragmentTest_HiltComponents {
  private PolygonDrawingInfoDialogFragmentTest_HiltComponents() {
  }

  @Module(
      subcomponents = ServiceC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ServiceCBuilderModule {
    @Binds
    ServiceComponentBuilder bind(ServiceC.Builder builder);
  }

  @Module(
      subcomponents = ActivityRetainedC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ActivityRetainedCBuilderModule {
    @Binds
    ActivityRetainedComponentBuilder bind(ActivityRetainedC.Builder builder);
  }

  @Module(
      subcomponents = ActivityC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ActivityCBuilderModule {
    @Binds
    ActivityComponentBuilder bind(ActivityC.Builder builder);
  }

  @Module(
      subcomponents = ViewModelC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ViewModelCBuilderModule {
    @Binds
    ViewModelComponentBuilder bind(ViewModelC.Builder builder);
  }

  @Module(
      subcomponents = ViewC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ViewCBuilderModule {
    @Binds
    ViewComponentBuilder bind(ViewC.Builder builder);
  }

  @Module(
      subcomponents = FragmentC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface FragmentCBuilderModule {
    @Binds
    FragmentComponentBuilder bind(FragmentC.Builder builder);
  }

  @Module(
      subcomponents = ViewWithFragmentC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ViewWithFragmentCBuilderModule {
    @Binds
    ViewWithFragmentComponentBuilder bind(ViewWithFragmentC.Builder builder);
  }

  @Component(
      modules = {
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
          ActivityRetainedCBuilderModule.class,
          ServiceCBuilderModule.class,
          TileSetDownloadWorker_HiltModule.class,
          ViewModelModule.class
      }
  )
  @Singleton
  public abstract static class SingletonC implements GndApplication_GeneratedInjector,
      PolygonDrawingInfoDialogFragmentTest_GeneratedInjector,
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
          HiltWrapper_ActivityRetainedComponentManager_LifecycleModule.class,
          ActivityCBuilderModule.class,
          ViewModelCBuilderModule.class
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
          HiltWrapper_ActivityModule.class,
          HiltWrapper_DefaultViewModelFactories_ActivityModule.class,
          FragmentCBuilderModule.class,
          ViewCBuilderModule.class,
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
          FragmentManagerModule.class,
          ViewWithFragmentCBuilderModule.class,
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
