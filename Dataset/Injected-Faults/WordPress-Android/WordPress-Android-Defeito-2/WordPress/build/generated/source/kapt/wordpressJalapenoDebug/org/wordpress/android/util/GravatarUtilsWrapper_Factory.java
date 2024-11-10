package org.wordpress.android.util;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;

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
public final class GravatarUtilsWrapper_Factory implements Factory<GravatarUtilsWrapper> {
  private final Provider<Context> appContextProvider;

  public GravatarUtilsWrapper_Factory(Provider<Context> appContextProvider) {
    this.appContextProvider = appContextProvider;
  }

  @Override
  public GravatarUtilsWrapper get() {
    return newInstance(appContextProvider.get());
  }

  public static GravatarUtilsWrapper_Factory create(Provider<Context> appContextProvider) {
    return new GravatarUtilsWrapper_Factory(appContextProvider);
  }

  public static GravatarUtilsWrapper newInstance(Context appContext) {
    return new GravatarUtilsWrapper(appContext);
  }
}
