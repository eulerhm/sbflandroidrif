package org.wordpress.android.ui.uploads;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.MediaStore;

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
public final class PostMediaHandler_Factory implements Factory<PostMediaHandler> {
  private final Provider<MediaStore> mediaStoreProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  public PostMediaHandler_Factory(Provider<MediaStore> mediaStoreProvider,
      Provider<Dispatcher> dispatcherProvider) {
    this.mediaStoreProvider = mediaStoreProvider;
    this.dispatcherProvider = dispatcherProvider;
  }

  @Override
  public PostMediaHandler get() {
    return newInstance(mediaStoreProvider.get(), dispatcherProvider.get());
  }

  public static PostMediaHandler_Factory create(Provider<MediaStore> mediaStoreProvider,
      Provider<Dispatcher> dispatcherProvider) {
    return new PostMediaHandler_Factory(mediaStoreProvider, dispatcherProvider);
  }

  public static PostMediaHandler newInstance(MediaStore mediaStore, Dispatcher dispatcher) {
    return new PostMediaHandler(mediaStore, dispatcher);
  }
}
