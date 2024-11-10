package org.wordpress.android.ui.stats.refresh.utils;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.StatsStore;

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
public final class NewsCardHandler_Factory implements Factory<NewsCardHandler> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<StatsStore> statsStoreProvider;

  public NewsCardHandler_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<StatsStore> statsStoreProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.statsStoreProvider = statsStoreProvider;
  }

  @Override
  public NewsCardHandler get() {
    return newInstance(mainDispatcherProvider.get(), statsStoreProvider.get());
  }

  public static NewsCardHandler_Factory create(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<StatsStore> statsStoreProvider) {
    return new NewsCardHandler_Factory(mainDispatcherProvider, statsStoreProvider);
  }

  public static NewsCardHandler newInstance(CoroutineDispatcher mainDispatcher,
      StatsStore statsStore) {
    return new NewsCardHandler(mainDispatcher, statsStore);
  }
}
