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
public final class MediaUtilsWrapper_Factory implements Factory<MediaUtilsWrapper> {
  private final Provider<Context> appContextProvider;

  public MediaUtilsWrapper_Factory(Provider<Context> appContextProvider) {
    this.appContextProvider = appContextProvider;
  }

  @Override
  public MediaUtilsWrapper get() {
    return newInstance(appContextProvider.get());
  }

  public static MediaUtilsWrapper_Factory create(Provider<Context> appContextProvider) {
    return new MediaUtilsWrapper_Factory(appContextProvider);
  }

  public static MediaUtilsWrapper newInstance(Context appContext) {
    return new MediaUtilsWrapper(appContext);
  }
}
