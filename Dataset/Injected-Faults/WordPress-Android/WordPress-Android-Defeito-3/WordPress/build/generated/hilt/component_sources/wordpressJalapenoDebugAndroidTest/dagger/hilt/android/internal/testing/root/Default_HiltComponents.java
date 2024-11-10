package dagger.hilt.android.internal.testing.root;

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
import dagger.hilt.android.flags.FragmentGetContextFix;
import dagger.hilt.android.flags.HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule;
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
import org.wordpress.android.BaseWordPressTest;
import org.wordpress.android.InitializationRule;
import org.wordpress.android.UserAgentTest_GeneratedInjector;
import org.wordpress.android.WordPressDebug_GeneratedInjector;
import org.wordpress.android.e2e.BlockEditorTests_GeneratedInjector;
import org.wordpress.android.e2e.ContactUsTests_GeneratedInjector;
import org.wordpress.android.e2e.EditorTests_GeneratedInjector;
import org.wordpress.android.e2e.LoginTests_GeneratedInjector;
import org.wordpress.android.e2e.ReaderTests_GeneratedInjector;
import org.wordpress.android.e2e.SignUpTests_GeneratedInjector;
import org.wordpress.android.e2e.StatsTests_GeneratedInjector;
import org.wordpress.android.modules.AppComponent;
import org.wordpress.android.modules.AppConfigModule;
import org.wordpress.android.modules.ApplicationModule;
import org.wordpress.android.modules.CrashLoggingModule;
import org.wordpress.android.modules.DummyInterceptorModule;
import org.wordpress.android.modules.ExperimentModule;
import org.wordpress.android.modules.FluxCModule;
import org.wordpress.android.modules.HiltWrapper_ViewModelModule;
import org.wordpress.android.modules.InterceptorModuleTest;
import org.wordpress.android.modules.LegacyModule;
import org.wordpress.android.modules.LoginAnalyticsModule;
import org.wordpress.android.modules.LoginModule;
import org.wordpress.android.modules.SupportModule;
import org.wordpress.android.modules.ThreadModule;
import org.wordpress.android.modules.TrackerTestModule;
import org.wordpress.android.networking.AuthenticatorRequestTest_GeneratedInjector;
import org.wordpress.android.networking.GravatarApiTest_GeneratedInjector;
import org.wordpress.android.networking.WPNetworkImageViewTest_GeneratedInjector;
import org.wordpress.android.ui.main.WPMainActivity_GeneratedInjector;
import org.wordpress.android.ui.notifications.NotesParseTest_GeneratedInjector;
import org.wordpress.android.ui.notifications.NotificationsUtilsTest_GeneratedInjector;
import org.wordpress.android.ui.posts.PostUtilsTest_GeneratedInjector;
import org.wordpress.android.ui.prefs.accountsettings.module.AccountSettingsModule;
import org.wordpress.android.ui.qrcodeauth.QRCodeAuthActivity_GeneratedInjector;
import org.wordpress.android.ui.qrcodeauth.QRCodeAuthFragment_GeneratedInjector;
import org.wordpress.android.ui.qrcodeauth.QRCodeAuthViewModel_HiltModules;
import org.wordpress.android.ui.screenshots.JPScreenshotTest_GeneratedInjector;
import org.wordpress.android.ui.screenshots.WPScreenshotTest_GeneratedInjector;
import org.wordpress.android.ui.sitecreation.SiteCreationActivity_GeneratedInjector;
import org.wordpress.android.ui.sitecreation.SiteCreationMainVM_HiltModules;
import org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsFragment_GeneratedInjector;
import org.wordpress.android.ui.sitecreation.domains.SiteCreationDomainsViewModel_HiltModules;
import org.wordpress.android.ui.sitecreation.previews.SiteCreationPreviewFragment_GeneratedInjector;
import org.wordpress.android.ui.sitecreation.previews.SitePreviewViewModel_HiltModules;
import org.wordpress.android.ui.sitecreation.sitename.SiteCreationSiteNameFragment_GeneratedInjector;
import org.wordpress.android.ui.sitecreation.sitename.SiteCreationSiteNameViewModel_HiltModules;
import org.wordpress.android.ui.sitecreation.theme.HomePagePickerFragment_GeneratedInjector;
import org.wordpress.android.ui.sitecreation.theme.HomePagePickerViewModel_HiltModules;
import org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsFragment_GeneratedInjector;
import org.wordpress.android.ui.sitecreation.verticals.SiteCreationIntentsViewModel_HiltModules;
import org.wordpress.android.ui.stats.intro.StatsNewFeatureIntroViewModel_HiltModules;
import org.wordpress.android.ui.stats.intro.StatsNewFeaturesIntroDialogFragment_GeneratedInjector;
import org.wordpress.android.ui.stats.refresh.LineChartMarkerView_GeneratedInjector;
import org.wordpress.android.ui.stats.refresh.StatsActivity_GeneratedInjector;
import org.wordpress.android.ui.stats.refresh.StatsFragment_GeneratedInjector;
import org.wordpress.android.ui.stats.refresh.StatsModule;
import org.wordpress.android.ui.stats.refresh.StatsViewModel_HiltModules;
import org.wordpress.android.ui.stats.refresh.lists.detail.InsightsDetailFragment_GeneratedInjector;
import org.wordpress.android.ui.stats.refresh.lists.detail.InsightsDetailViewModel_HiltModules;
import org.wordpress.android.ui.stats.refresh.lists.detail.StatsDetailActivity_GeneratedInjector;
import org.wordpress.android.ui.suggestion.SuggestionSourceSubcomponent;
import org.wordpress.android.util.AutolinkUtilsTest_GeneratedInjector;
import org.wordpress.android.util.SupportedBlocksProviderTest_GeneratedInjector;
import org.wordpress.android.util.UploadWorkerTest_GeneratedInjector;
import org.wordpress.android.util.UrlUtilsTest_GeneratedInjector;
import org.wordpress.android.util.WPUrlUtilsTest_GeneratedInjector;

public final class Default_HiltComponents {
  private Default_HiltComponents() {
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
          AccountSettingsModule.class,
          AppConfigModule.class,
          ApplicationContextModule.class,
          ApplicationModule.class,
          CrashLoggingModule.class,
          ActivityRetainedCBuilderModule.class,
          ServiceCBuilderModule.class,
          DummyInterceptorModule.class,
          ExperimentModule.class,
          FluxCModule.class,
          HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule.class,
          HiltWrapper_ViewModelModule.class,
          InterceptorModuleTest.class,
          LegacyModule.class,
          LoginAnalyticsModule.class,
          LoginModule.class,
          StatsModule.class,
          SuggestionSourceSubcomponent.SuggestionSourceModule.class,
          SupportModule.class,
          ThreadModule.class,
          TrackerTestModule.class
      }
  )
  @Singleton
  public abstract static class SingletonC implements FragmentGetContextFix.FragmentGetContextFixEntryPoint,
      HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedComponentBuilderEntryPoint,
      ServiceComponentManager.ServiceComponentBuilderEntryPoint,
      SingletonComponent,
      TestSingletonComponent,
      BaseWordPressTest.AndroidInjectorEntryPoint,
      InitializationRule.AppInitializerEntryPoint,
      UserAgentTest_GeneratedInjector,
      WordPressDebug_GeneratedInjector,
      BlockEditorTests_GeneratedInjector,
      ContactUsTests_GeneratedInjector,
      EditorTests_GeneratedInjector,
      LoginTests_GeneratedInjector,
      ReaderTests_GeneratedInjector,
      SignUpTests_GeneratedInjector,
      StatsTests_GeneratedInjector,
      AppComponent,
      AuthenticatorRequestTest_GeneratedInjector,
      GravatarApiTest_GeneratedInjector,
      WPNetworkImageViewTest_GeneratedInjector,
      NotesParseTest_GeneratedInjector,
      NotificationsUtilsTest_GeneratedInjector,
      PostUtilsTest_GeneratedInjector,
      JPScreenshotTest_GeneratedInjector,
      WPScreenshotTest_GeneratedInjector,
      AutolinkUtilsTest_GeneratedInjector,
      SupportedBlocksProviderTest_GeneratedInjector,
      UploadWorkerTest_GeneratedInjector,
      UrlUtilsTest_GeneratedInjector,
      WPUrlUtilsTest_GeneratedInjector {
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
          HiltWrapper_ActivityRetainedComponentManager_LifecycleModule.class,
          HomePagePickerViewModel_HiltModules.KeyModule.class,
          InsightsDetailViewModel_HiltModules.KeyModule.class,
          QRCodeAuthViewModel_HiltModules.KeyModule.class,
          SiteCreationDomainsViewModel_HiltModules.KeyModule.class,
          SiteCreationIntentsViewModel_HiltModules.KeyModule.class,
          SiteCreationMainVM_HiltModules.KeyModule.class,
          SiteCreationSiteNameViewModel_HiltModules.KeyModule.class,
          SitePreviewViewModel_HiltModules.KeyModule.class,
          StatsNewFeatureIntroViewModel_HiltModules.KeyModule.class,
          StatsViewModel_HiltModules.KeyModule.class
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
          HiltWrapper_DefaultViewModelFactories_ActivityModule.class
      }
  )
  @ActivityScoped
  public abstract static class ActivityC implements ActivityComponent,
      DefaultViewModelFactories.ActivityEntryPoint,
      HiltWrapper_HiltViewModelFactory_ActivityCreatorEntryPoint,
      FragmentComponentManager.FragmentComponentBuilderEntryPoint,
      ViewComponentManager.ViewComponentBuilderEntryPoint,
      GeneratedComponent,
      WPMainActivity_GeneratedInjector,
      QRCodeAuthActivity_GeneratedInjector,
      SiteCreationActivity_GeneratedInjector,
      StatsActivity_GeneratedInjector,
      StatsDetailActivity_GeneratedInjector {
    @Subcomponent.Builder
    abstract interface Builder extends ActivityComponentBuilder {
    }
  }

  @Subcomponent(
      modules = {
          HiltWrapper_HiltViewModelFactory_ViewModelModule.class,
          HomePagePickerViewModel_HiltModules.BindsModule.class,
          InsightsDetailViewModel_HiltModules.BindsModule.class,
          QRCodeAuthViewModel_HiltModules.BindsModule.class,
          SiteCreationDomainsViewModel_HiltModules.BindsModule.class,
          SiteCreationIntentsViewModel_HiltModules.BindsModule.class,
          SiteCreationMainVM_HiltModules.BindsModule.class,
          SiteCreationSiteNameViewModel_HiltModules.BindsModule.class,
          SitePreviewViewModel_HiltModules.BindsModule.class,
          StatsNewFeatureIntroViewModel_HiltModules.BindsModule.class,
          StatsViewModel_HiltModules.BindsModule.class
      }
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
      GeneratedComponent,
      LineChartMarkerView_GeneratedInjector {
    @Subcomponent.Builder
    abstract interface Builder extends ViewComponentBuilder {
    }
  }

  @Subcomponent(
      modules = ViewWithFragmentCBuilderModule.class
  )
  @FragmentScoped
  public abstract static class FragmentC implements FragmentComponent,
      DefaultViewModelFactories.FragmentEntryPoint,
      ViewComponentManager.ViewWithFragmentComponentBuilderEntryPoint,
      GeneratedComponent,
      QRCodeAuthFragment_GeneratedInjector,
      SiteCreationDomainsFragment_GeneratedInjector,
      SiteCreationPreviewFragment_GeneratedInjector,
      SiteCreationSiteNameFragment_GeneratedInjector,
      HomePagePickerFragment_GeneratedInjector,
      SiteCreationIntentsFragment_GeneratedInjector,
      StatsNewFeaturesIntroDialogFragment_GeneratedInjector,
      StatsFragment_GeneratedInjector,
      InsightsDetailFragment_GeneratedInjector {
    @Subcomponent.Builder
    abstract interface Builder extends FragmentComponentBuilder {
    }
  }

  @Subcomponent
  @ViewScoped
  public abstract static class ViewWithFragmentC implements ViewWithFragmentComponent,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ViewWithFragmentComponentBuilder {
    }
  }
}
