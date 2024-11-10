package org.wordpress.android.util;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.store.MediaStore;

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
public final class FluxCUtilsWrapper_Factory implements Factory<FluxCUtilsWrapper> {
  private final Provider<Context> appContextProvider;

  private final Provider<MediaStore> mediaStoreProvider;

  public FluxCUtilsWrapper_Factory(Provider<Context> appContextProvider,
      Provider<MediaStore> mediaStoreProvider) {
    this.appContextProvider = appContextProvider;
    this.mediaStoreProvider = mediaStoreProvider;
  }

  @Override
  public FluxCUtilsWrapper get() {
    return newInstance(appContextProvider.get(), mediaStoreProvider.get());
  }

  public static FluxCUtilsWrapper_Factory create(Provider<Context> appContextProvider,
      Provider<MediaStore> mediaStoreProvider) {
    return new FluxCUtilsWrapper_Factory(appContextProvider, mediaStoreProvider);
  }

  public static FluxCUtilsWrapper newInstance(Context appContext, MediaStore mediaStore) {
    return new FluxCUtilsWrapper(appContext, mediaStore);
  }
}
