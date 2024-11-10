package org.wordpress.android.ui.posts.editor.media;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.utils.AuthenticationUtils;
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
public final class CopyMediaToAppStorageUseCase_Factory implements Factory<CopyMediaToAppStorageUseCase> {
  private final Provider<MediaUtilsWrapper> mediaUtilsWrapperProvider;

  private final Provider<AuthenticationUtils> authenticationUtilsProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public CopyMediaToAppStorageUseCase_Factory(Provider<MediaUtilsWrapper> mediaUtilsWrapperProvider,
      Provider<AuthenticationUtils> authenticationUtilsProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.mediaUtilsWrapperProvider = mediaUtilsWrapperProvider;
    this.authenticationUtilsProvider = authenticationUtilsProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public CopyMediaToAppStorageUseCase get() {
    return newInstance(mediaUtilsWrapperProvider.get(), authenticationUtilsProvider.get(), bgDispatcherProvider.get());
  }

  public static CopyMediaToAppStorageUseCase_Factory create(
      Provider<MediaUtilsWrapper> mediaUtilsWrapperProvider,
      Provider<AuthenticationUtils> authenticationUtilsProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new CopyMediaToAppStorageUseCase_Factory(mediaUtilsWrapperProvider, authenticationUtilsProvider, bgDispatcherProvider);
  }

  public static CopyMediaToAppStorageUseCase newInstance(MediaUtilsWrapper mediaUtilsWrapper,
      AuthenticationUtils authenticationUtils, CoroutineDispatcher bgDispatcher) {
    return new CopyMediaToAppStorageUseCase(mediaUtilsWrapper, authenticationUtils, bgDispatcher);
  }
}
