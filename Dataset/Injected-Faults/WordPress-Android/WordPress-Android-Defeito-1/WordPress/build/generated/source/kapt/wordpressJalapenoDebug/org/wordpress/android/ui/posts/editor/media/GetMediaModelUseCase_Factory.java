package org.wordpress.android.ui.posts.editor.media;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.MediaStore;
import org.wordpress.android.ui.utils.AuthenticationUtils;
import org.wordpress.android.util.FileProvider;
import org.wordpress.android.util.FluxCUtilsWrapper;
import org.wordpress.android.util.MediaUtilsWrapper;

@ScopeMetadata("dagger.Reusable")
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
public final class GetMediaModelUseCase_Factory implements Factory<GetMediaModelUseCase> {
  private final Provider<FluxCUtilsWrapper> fluxCUtilsWrapperProvider;

  private final Provider<MediaUtilsWrapper> mediaUtilsWrapperProvider;

  private final Provider<MediaStore> mediaStoreProvider;

  private final Provider<FileProvider> fileProvider;

  private final Provider<AuthenticationUtils> authenticationUtilsProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public GetMediaModelUseCase_Factory(Provider<FluxCUtilsWrapper> fluxCUtilsWrapperProvider,
      Provider<MediaUtilsWrapper> mediaUtilsWrapperProvider,
      Provider<MediaStore> mediaStoreProvider, Provider<FileProvider> fileProvider,
      Provider<AuthenticationUtils> authenticationUtilsProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.fluxCUtilsWrapperProvider = fluxCUtilsWrapperProvider;
    this.mediaUtilsWrapperProvider = mediaUtilsWrapperProvider;
    this.mediaStoreProvider = mediaStoreProvider;
    this.fileProvider = fileProvider;
    this.authenticationUtilsProvider = authenticationUtilsProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public GetMediaModelUseCase get() {
    return newInstance(fluxCUtilsWrapperProvider.get(), mediaUtilsWrapperProvider.get(), mediaStoreProvider.get(), fileProvider.get(), authenticationUtilsProvider.get(), bgDispatcherProvider.get());
  }

  public static GetMediaModelUseCase_Factory create(
      Provider<FluxCUtilsWrapper> fluxCUtilsWrapperProvider,
      Provider<MediaUtilsWrapper> mediaUtilsWrapperProvider,
      Provider<MediaStore> mediaStoreProvider, Provider<FileProvider> fileProvider,
      Provider<AuthenticationUtils> authenticationUtilsProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new GetMediaModelUseCase_Factory(fluxCUtilsWrapperProvider, mediaUtilsWrapperProvider, mediaStoreProvider, fileProvider, authenticationUtilsProvider, bgDispatcherProvider);
  }

  public static GetMediaModelUseCase newInstance(FluxCUtilsWrapper fluxCUtilsWrapper,
      MediaUtilsWrapper mediaUtilsWrapper, MediaStore mediaStore, FileProvider fileProvider,
      AuthenticationUtils authenticationUtils, CoroutineDispatcher bgDispatcher) {
    return new GetMediaModelUseCase(fluxCUtilsWrapper, mediaUtilsWrapper, mediaStore, fileProvider, authenticationUtils, bgDispatcher);
  }
}
