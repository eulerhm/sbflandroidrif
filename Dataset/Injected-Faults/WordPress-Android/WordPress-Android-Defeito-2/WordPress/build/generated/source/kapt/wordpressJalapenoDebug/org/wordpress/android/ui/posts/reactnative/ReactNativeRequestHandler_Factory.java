package org.wordpress.android.ui.posts.reactnative;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.ReactNativeStore;

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
public final class ReactNativeRequestHandler_Factory implements Factory<ReactNativeRequestHandler> {
  private final Provider<ReactNativeStore> reactNativeStoreProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public ReactNativeRequestHandler_Factory(Provider<ReactNativeStore> reactNativeStoreProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.reactNativeStoreProvider = reactNativeStoreProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public ReactNativeRequestHandler get() {
    return newInstance(reactNativeStoreProvider.get(), bgDispatcherProvider.get());
  }

  public static ReactNativeRequestHandler_Factory create(
      Provider<ReactNativeStore> reactNativeStoreProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new ReactNativeRequestHandler_Factory(reactNativeStoreProvider, bgDispatcherProvider);
  }

  public static ReactNativeRequestHandler newInstance(ReactNativeStore reactNativeStore,
      CoroutineDispatcher bgDispatcher) {
    return new ReactNativeRequestHandler(reactNativeStore, bgDispatcher);
  }
}
