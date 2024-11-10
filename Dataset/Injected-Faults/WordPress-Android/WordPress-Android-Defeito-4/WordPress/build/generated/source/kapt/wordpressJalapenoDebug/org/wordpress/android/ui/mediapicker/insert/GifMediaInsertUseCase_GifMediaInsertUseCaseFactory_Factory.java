package org.wordpress.android.ui.mediapicker.insert;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.util.FluxCUtilsWrapper;
import org.wordpress.android.util.MimeTypeMapUtilsWrapper;
import org.wordpress.android.util.WPMediaUtilsWrapper;

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
public final class GifMediaInsertUseCase_GifMediaInsertUseCaseFactory_Factory implements Factory<GifMediaInsertUseCase.GifMediaInsertUseCaseFactory> {
  private final Provider<Context> contextProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<CoroutineDispatcher> ioDispatcherProvider;

  private final Provider<WPMediaUtilsWrapper> wpMediaUtilsWrapperProvider;

  private final Provider<FluxCUtilsWrapper> fluxCUtilsWrapperProvider;

  private final Provider<MimeTypeMapUtilsWrapper> mimeTypeMapUtilsWrapperProvider;

  public GifMediaInsertUseCase_GifMediaInsertUseCaseFactory_Factory(
      Provider<Context> contextProvider, Provider<Dispatcher> dispatcherProvider,
      Provider<CoroutineDispatcher> ioDispatcherProvider,
      Provider<WPMediaUtilsWrapper> wpMediaUtilsWrapperProvider,
      Provider<FluxCUtilsWrapper> fluxCUtilsWrapperProvider,
      Provider<MimeTypeMapUtilsWrapper> mimeTypeMapUtilsWrapperProvider) {
    this.contextProvider = contextProvider;
    this.dispatcherProvider = dispatcherProvider;
    this.ioDispatcherProvider = ioDispatcherProvider;
    this.wpMediaUtilsWrapperProvider = wpMediaUtilsWrapperProvider;
    this.fluxCUtilsWrapperProvider = fluxCUtilsWrapperProvider;
    this.mimeTypeMapUtilsWrapperProvider = mimeTypeMapUtilsWrapperProvider;
  }

  @Override
  public GifMediaInsertUseCase.GifMediaInsertUseCaseFactory get() {
    return newInstance(contextProvider.get(), dispatcherProvider.get(), ioDispatcherProvider.get(), wpMediaUtilsWrapperProvider.get(), fluxCUtilsWrapperProvider.get(), mimeTypeMapUtilsWrapperProvider.get());
  }

  public static GifMediaInsertUseCase_GifMediaInsertUseCaseFactory_Factory create(
      Provider<Context> contextProvider, Provider<Dispatcher> dispatcherProvider,
      Provider<CoroutineDispatcher> ioDispatcherProvider,
      Provider<WPMediaUtilsWrapper> wpMediaUtilsWrapperProvider,
      Provider<FluxCUtilsWrapper> fluxCUtilsWrapperProvider,
      Provider<MimeTypeMapUtilsWrapper> mimeTypeMapUtilsWrapperProvider) {
    return new GifMediaInsertUseCase_GifMediaInsertUseCaseFactory_Factory(contextProvider, dispatcherProvider, ioDispatcherProvider, wpMediaUtilsWrapperProvider, fluxCUtilsWrapperProvider, mimeTypeMapUtilsWrapperProvider);
  }

  public static GifMediaInsertUseCase.GifMediaInsertUseCaseFactory newInstance(Context context,
      Dispatcher dispatcher, CoroutineDispatcher ioDispatcher,
      WPMediaUtilsWrapper wpMediaUtilsWrapper, FluxCUtilsWrapper fluxCUtilsWrapper,
      MimeTypeMapUtilsWrapper mimeTypeMapUtilsWrapper) {
    return new GifMediaInsertUseCase.GifMediaInsertUseCaseFactory(context, dispatcher, ioDispatcher, wpMediaUtilsWrapper, fluxCUtilsWrapper, mimeTypeMapUtilsWrapper);
  }
}
