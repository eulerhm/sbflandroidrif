package org.wordpress.android.ui.sitecreation.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.util.UrlUtilsWrapper;

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
public final class CreateSiteUseCase_Factory implements Factory<CreateSiteUseCase> {
  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<UrlUtilsWrapper> urlUtilsWrapperProvider;

  public CreateSiteUseCase_Factory(Provider<Dispatcher> dispatcherProvider,
      Provider<SiteStore> siteStoreProvider, Provider<UrlUtilsWrapper> urlUtilsWrapperProvider) {
    this.dispatcherProvider = dispatcherProvider;
    this.siteStoreProvider = siteStoreProvider;
    this.urlUtilsWrapperProvider = urlUtilsWrapperProvider;
  }

  @Override
  public CreateSiteUseCase get() {
    return newInstance(dispatcherProvider.get(), siteStoreProvider.get(), urlUtilsWrapperProvider.get());
  }

  public static CreateSiteUseCase_Factory create(Provider<Dispatcher> dispatcherProvider,
      Provider<SiteStore> siteStoreProvider, Provider<UrlUtilsWrapper> urlUtilsWrapperProvider) {
    return new CreateSiteUseCase_Factory(dispatcherProvider, siteStoreProvider, urlUtilsWrapperProvider);
  }

  public static CreateSiteUseCase newInstance(Dispatcher dispatcher, SiteStore siteStore,
      UrlUtilsWrapper urlUtilsWrapper) {
    return new CreateSiteUseCase(dispatcher, siteStore, urlUtilsWrapper);
  }
}
