package org.wordpress.android.ui.media.services;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.MediaStore;

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
public final class MediaDeleteService_MembersInjector implements MembersInjector<MediaDeleteService> {
  private final Provider<Dispatcher> mDispatcherProvider;

  private final Provider<MediaStore> mMediaStoreProvider;

  public MediaDeleteService_MembersInjector(Provider<Dispatcher> mDispatcherProvider,
      Provider<MediaStore> mMediaStoreProvider) {
    this.mDispatcherProvider = mDispatcherProvider;
    this.mMediaStoreProvider = mMediaStoreProvider;
  }

  public static MembersInjector<MediaDeleteService> create(Provider<Dispatcher> mDispatcherProvider,
      Provider<MediaStore> mMediaStoreProvider) {
    return new MediaDeleteService_MembersInjector(mDispatcherProvider, mMediaStoreProvider);
  }

  @Override
  public void injectMembers(MediaDeleteService instance) {
    injectMDispatcher(instance, mDispatcherProvider.get());
    injectMMediaStore(instance, mMediaStoreProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.media.services.MediaDeleteService.mDispatcher")
  public static void injectMDispatcher(MediaDeleteService instance, Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.media.services.MediaDeleteService.mMediaStore")
  public static void injectMMediaStore(MediaDeleteService instance, MediaStore mMediaStore) {
    instance.mMediaStore = mMediaStore;
  }
}
