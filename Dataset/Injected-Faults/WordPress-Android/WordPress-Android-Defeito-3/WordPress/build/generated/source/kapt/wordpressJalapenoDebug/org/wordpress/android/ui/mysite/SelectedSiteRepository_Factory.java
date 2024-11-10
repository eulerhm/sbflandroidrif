package org.wordpress.android.ui.mysite;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;
import org.wordpress.android.ui.prefs.SiteSettingsInterfaceWrapper;

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
public final class SelectedSiteRepository_Factory implements Factory<SelectedSiteRepository> {
  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<SiteSettingsInterfaceWrapper.Factory> siteSettingsInterfaceFactoryProvider;

  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  public SelectedSiteRepository_Factory(Provider<Dispatcher> dispatcherProvider,
      Provider<SiteSettingsInterfaceWrapper.Factory> siteSettingsInterfaceFactoryProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider) {
    this.dispatcherProvider = dispatcherProvider;
    this.siteSettingsInterfaceFactoryProvider = siteSettingsInterfaceFactoryProvider;
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
  }

  @Override
  public SelectedSiteRepository get() {
    return newInstance(dispatcherProvider.get(), siteSettingsInterfaceFactoryProvider.get(), appPrefsWrapperProvider.get());
  }

  public static SelectedSiteRepository_Factory create(Provider<Dispatcher> dispatcherProvider,
      Provider<SiteSettingsInterfaceWrapper.Factory> siteSettingsInterfaceFactoryProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider) {
    return new SelectedSiteRepository_Factory(dispatcherProvider, siteSettingsInterfaceFactoryProvider, appPrefsWrapperProvider);
  }

  public static SelectedSiteRepository newInstance(Dispatcher dispatcher,
      SiteSettingsInterfaceWrapper.Factory siteSettingsInterfaceFactory,
      AppPrefsWrapper appPrefsWrapper) {
    return new SelectedSiteRepository(dispatcher, siteSettingsInterfaceFactory, appPrefsWrapper);
  }
}
