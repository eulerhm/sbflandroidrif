package org.wordpress.android.networking;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.utils.AuthenticationUtils;

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
public final class GlideRequestFactory_Factory implements Factory<GlideRequestFactory> {
  private final Provider<AuthenticationUtils> authenticationUtilsProvider;

  public GlideRequestFactory_Factory(Provider<AuthenticationUtils> authenticationUtilsProvider) {
    this.authenticationUtilsProvider = authenticationUtilsProvider;
  }

  @Override
  public GlideRequestFactory get() {
    return newInstance(authenticationUtilsProvider.get());
  }

  public static GlideRequestFactory_Factory create(
      Provider<AuthenticationUtils> authenticationUtilsProvider) {
    return new GlideRequestFactory_Factory(authenticationUtilsProvider);
  }

  public static GlideRequestFactory newInstance(AuthenticationUtils authenticationUtils) {
    return new GlideRequestFactory(authenticationUtils);
  }
}
