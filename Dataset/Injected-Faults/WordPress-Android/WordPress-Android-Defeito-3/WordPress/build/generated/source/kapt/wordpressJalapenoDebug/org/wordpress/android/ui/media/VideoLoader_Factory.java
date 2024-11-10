package org.wordpress.android.ui.media;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.utils.AppLogWrapper;
import org.wordpress.android.ui.utils.AuthenticationUtils;
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
public final class VideoLoader_Factory implements Factory<VideoLoader> {
  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<AuthenticationUtils> authenticationUtilsProvider;

  private final Provider<AppLogWrapper> appLogWrapperProvider;

  private final Provider<MediaUtilsWrapper> mediaUtilsWrapperProvider;

  private final Provider<Context> appContextProvider;

  public VideoLoader_Factory(Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<AuthenticationUtils> authenticationUtilsProvider,
      Provider<AppLogWrapper> appLogWrapperProvider,
      Provider<MediaUtilsWrapper> mediaUtilsWrapperProvider, Provider<Context> appContextProvider) {
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.authenticationUtilsProvider = authenticationUtilsProvider;
    this.appLogWrapperProvider = appLogWrapperProvider;
    this.mediaUtilsWrapperProvider = mediaUtilsWrapperProvider;
    this.appContextProvider = appContextProvider;
  }

  @Override
  public VideoLoader get() {
    return newInstance(bgDispatcherProvider.get(), mainDispatcherProvider.get(), authenticationUtilsProvider.get(), appLogWrapperProvider.get(), mediaUtilsWrapperProvider.get(), appContextProvider.get());
  }

  public static VideoLoader_Factory create(Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<AuthenticationUtils> authenticationUtilsProvider,
      Provider<AppLogWrapper> appLogWrapperProvider,
      Provider<MediaUtilsWrapper> mediaUtilsWrapperProvider, Provider<Context> appContextProvider) {
    return new VideoLoader_Factory(bgDispatcherProvider, mainDispatcherProvider, authenticationUtilsProvider, appLogWrapperProvider, mediaUtilsWrapperProvider, appContextProvider);
  }

  public static VideoLoader newInstance(CoroutineDispatcher bgDispatcher,
      CoroutineDispatcher mainDispatcher, AuthenticationUtils authenticationUtils,
      AppLogWrapper appLogWrapper, MediaUtilsWrapper mediaUtilsWrapper, Context appContext) {
    return new VideoLoader(bgDispatcher, mainDispatcher, authenticationUtils, appLogWrapper, mediaUtilsWrapper, appContext);
  }
}
