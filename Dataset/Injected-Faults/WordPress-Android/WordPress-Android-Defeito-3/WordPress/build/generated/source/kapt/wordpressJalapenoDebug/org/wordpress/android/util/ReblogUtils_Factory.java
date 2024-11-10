package org.wordpress.android.util;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;

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
public final class ReblogUtils_Factory implements Factory<ReblogUtils> {
  private final Provider<UrlUtilsWrapper> urlUtilsProvider;

  public ReblogUtils_Factory(Provider<UrlUtilsWrapper> urlUtilsProvider) {
    this.urlUtilsProvider = urlUtilsProvider;
  }

  @Override
  public ReblogUtils get() {
    return newInstance(urlUtilsProvider.get());
  }

  public static ReblogUtils_Factory create(Provider<UrlUtilsWrapper> urlUtilsProvider) {
    return new ReblogUtils_Factory(urlUtilsProvider);
  }

  public static ReblogUtils newInstance(UrlUtilsWrapper urlUtils) {
    return new ReblogUtils(urlUtils);
  }
}
