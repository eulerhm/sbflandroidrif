package org.wordpress.android.ui.jetpack;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;

@ScopeMetadata
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
public final class JetpackCapabilitiesUseCase_Factory implements Factory<JetpackCapabilitiesUseCase> {
  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  public JetpackCapabilitiesUseCase_Factory(Provider<SiteStore> siteStoreProvider,
      Provider<Dispatcher> dispatcherProvider, Provider<AppPrefsWrapper> appPrefsWrapperProvider) {
    this.siteStoreProvider = siteStoreProvider;
    this.dispatcherProvider = dispatcherProvider;
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
  }

  @Override
  public JetpackCapabilitiesUseCase get() {
    return newInstance(siteStoreProvider.get(), dispatcherProvider.get(), appPrefsWrapperProvider.get());
  }

  public static JetpackCapabilitiesUseCase_Factory create(Provider<SiteStore> siteStoreProvider,
      Provider<Dispatcher> dispatcherProvider, Provider<AppPrefsWrapper> appPrefsWrapperProvider) {
    return new JetpackCapabilitiesUseCase_Factory(siteStoreProvider, dispatcherProvider, appPrefsWrapperProvider);
  }

  public static JetpackCapabilitiesUseCase newInstance(SiteStore siteStore, Dispatcher dispatcher,
      AppPrefsWrapper appPrefsWrapper) {
    return new JetpackCapabilitiesUseCase(siteStore, dispatcher, appPrefsWrapper);
  }
}
