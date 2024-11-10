package org.wordpress.android.ui.deeplinks;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.util.UriUtilsWrapper;

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
public final class DeepLinkUriUtils_Factory implements Factory<DeepLinkUriUtils> {
  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<UriUtilsWrapper> uriUtilsWrapperProvider;

  public DeepLinkUriUtils_Factory(Provider<SiteStore> siteStoreProvider,
      Provider<UriUtilsWrapper> uriUtilsWrapperProvider) {
    this.siteStoreProvider = siteStoreProvider;
    this.uriUtilsWrapperProvider = uriUtilsWrapperProvider;
  }

  @Override
  public DeepLinkUriUtils get() {
    return newInstance(siteStoreProvider.get(), uriUtilsWrapperProvider.get());
  }

  public static DeepLinkUriUtils_Factory create(Provider<SiteStore> siteStoreProvider,
      Provider<UriUtilsWrapper> uriUtilsWrapperProvider) {
    return new DeepLinkUriUtils_Factory(siteStoreProvider, uriUtilsWrapperProvider);
  }

  public static DeepLinkUriUtils newInstance(SiteStore siteStore, UriUtilsWrapper uriUtilsWrapper) {
    return new DeepLinkUriUtils(siteStore, uriUtilsWrapper);
  }
}
