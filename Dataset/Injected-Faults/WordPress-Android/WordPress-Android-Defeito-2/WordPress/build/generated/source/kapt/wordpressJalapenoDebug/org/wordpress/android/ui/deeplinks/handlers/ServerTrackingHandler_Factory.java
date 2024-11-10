package org.wordpress.android.ui.deeplinks.handlers;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.utils.AppLogWrapper;
import org.wordpress.android.viewmodel.ContextProvider;

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
public final class ServerTrackingHandler_Factory implements Factory<ServerTrackingHandler> {
  private final Provider<AppLogWrapper> appLogWrapperProvider;

  private final Provider<ContextProvider> contextProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public ServerTrackingHandler_Factory(Provider<AppLogWrapper> appLogWrapperProvider,
      Provider<ContextProvider> contextProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.appLogWrapperProvider = appLogWrapperProvider;
    this.contextProvider = contextProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public ServerTrackingHandler get() {
    return newInstance(appLogWrapperProvider.get(), contextProvider.get(), bgDispatcherProvider.get());
  }

  public static ServerTrackingHandler_Factory create(Provider<AppLogWrapper> appLogWrapperProvider,
      Provider<ContextProvider> contextProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new ServerTrackingHandler_Factory(appLogWrapperProvider, contextProvider, bgDispatcherProvider);
  }

  public static ServerTrackingHandler newInstance(AppLogWrapper appLogWrapper,
      ContextProvider contextProvider, CoroutineDispatcher bgDispatcher) {
    return new ServerTrackingHandler(appLogWrapper, contextProvider, bgDispatcher);
  }
}
