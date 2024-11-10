package org.wordpress.android.ui.media;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.ui.utils.AuthenticationUtils;

@ScopeMetadata("dagger.Reusable")
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
public final class ExoPlayerUtils_Factory implements Factory<ExoPlayerUtils> {
  private final Provider<AuthenticationUtils> authenticationUtilsProvider;

  private final Provider<Context> appContextProvider;

  public ExoPlayerUtils_Factory(Provider<AuthenticationUtils> authenticationUtilsProvider,
      Provider<Context> appContextProvider) {
    this.authenticationUtilsProvider = authenticationUtilsProvider;
    this.appContextProvider = appContextProvider;
  }

  @Override
  public ExoPlayerUtils get() {
    return newInstance(authenticationUtilsProvider.get(), appContextProvider.get());
  }

  public static ExoPlayerUtils_Factory create(
      Provider<AuthenticationUtils> authenticationUtilsProvider,
      Provider<Context> appContextProvider) {
    return new ExoPlayerUtils_Factory(authenticationUtilsProvider, appContextProvider);
  }

  public static ExoPlayerUtils newInstance(AuthenticationUtils authenticationUtils,
      Context appContext) {
    return new ExoPlayerUtils(authenticationUtils, appContext);
  }
}
