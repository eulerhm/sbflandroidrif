package org.wordpress.android.util;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.EncryptedLogStore;
import org.wordpress.android.util.analytics.AnalyticsTrackerWrapper;

@ScopeMetadata("javax.inject.Singleton")
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
public final class EncryptedLogging_Factory implements Factory<EncryptedLogging> {
  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<EncryptedLogStore> encryptedLogStoreProvider;

  private final Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider;

  private final Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public EncryptedLogging_Factory(Provider<Dispatcher> dispatcherProvider,
      Provider<EncryptedLogStore> encryptedLogStoreProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.dispatcherProvider = dispatcherProvider;
    this.encryptedLogStoreProvider = encryptedLogStoreProvider;
    this.networkUtilsWrapperProvider = networkUtilsWrapperProvider;
    this.analyticsTrackerWrapperProvider = analyticsTrackerWrapperProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public EncryptedLogging get() {
    return newInstance(dispatcherProvider.get(), encryptedLogStoreProvider.get(), networkUtilsWrapperProvider.get(), analyticsTrackerWrapperProvider.get(), bgDispatcherProvider.get());
  }

  public static EncryptedLogging_Factory create(Provider<Dispatcher> dispatcherProvider,
      Provider<EncryptedLogStore> encryptedLogStoreProvider,
      Provider<NetworkUtilsWrapper> networkUtilsWrapperProvider,
      Provider<AnalyticsTrackerWrapper> analyticsTrackerWrapperProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new EncryptedLogging_Factory(dispatcherProvider, encryptedLogStoreProvider, networkUtilsWrapperProvider, analyticsTrackerWrapperProvider, bgDispatcherProvider);
  }

  public static EncryptedLogging newInstance(Dispatcher dispatcher,
      EncryptedLogStore encryptedLogStore, NetworkUtilsWrapper networkUtilsWrapper,
      AnalyticsTrackerWrapper analyticsTrackerWrapper, CoroutineDispatcher bgDispatcher) {
    return new EncryptedLogging(dispatcher, encryptedLogStore, networkUtilsWrapper, analyticsTrackerWrapper, bgDispatcher);
  }
}
