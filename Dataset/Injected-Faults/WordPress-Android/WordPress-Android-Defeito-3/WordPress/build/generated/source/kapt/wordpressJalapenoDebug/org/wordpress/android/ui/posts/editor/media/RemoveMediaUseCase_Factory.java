package org.wordpress.android.ui.posts.editor.media;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.MediaStore;
import org.wordpress.android.ui.uploads.UploadServiceFacade;
import org.wordpress.android.util.MediaUtilsWrapper;

@ScopeMetadata
@QualifierMetadata("javax.inject.Named")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class RemoveMediaUseCase_Factory implements Factory<RemoveMediaUseCase> {
  private final Provider<MediaStore> mediaStoreProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<MediaUtilsWrapper> mediaUtilsProvider;

  private final Provider<UploadServiceFacade> uploadServiceProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public RemoveMediaUseCase_Factory(Provider<MediaStore> mediaStoreProvider,
      Provider<Dispatcher> dispatcherProvider, Provider<MediaUtilsWrapper> mediaUtilsProvider,
      Provider<UploadServiceFacade> uploadServiceProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.mediaStoreProvider = mediaStoreProvider;
    this.dispatcherProvider = dispatcherProvider;
    this.mediaUtilsProvider = mediaUtilsProvider;
    this.uploadServiceProvider = uploadServiceProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public RemoveMediaUseCase get() {
    return newInstance(mediaStoreProvider.get(), dispatcherProvider.get(), mediaUtilsProvider.get(), uploadServiceProvider.get(), bgDispatcherProvider.get());
  }

  public static RemoveMediaUseCase_Factory create(Provider<MediaStore> mediaStoreProvider,
      Provider<Dispatcher> dispatcherProvider, Provider<MediaUtilsWrapper> mediaUtilsProvider,
      Provider<UploadServiceFacade> uploadServiceProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new RemoveMediaUseCase_Factory(mediaStoreProvider, dispatcherProvider, mediaUtilsProvider, uploadServiceProvider, bgDispatcherProvider);
  }

  public static RemoveMediaUseCase newInstance(MediaStore mediaStore, Dispatcher dispatcher,
      MediaUtilsWrapper mediaUtils, UploadServiceFacade uploadService,
      CoroutineDispatcher bgDispatcher) {
    return new RemoveMediaUseCase(mediaStore, dispatcher, mediaUtils, uploadService, bgDispatcher);
  }
}
