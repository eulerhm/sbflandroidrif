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
public final class ActionCardHandler_Factory implements Factory<ActionCardHandler> {
  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<StatsStore> statsStoreProvider;

  private final Provider<StatsSiteProvider> statsSiteProvider;

  public ActionCardHandler_Factory(Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<StatsStore> statsStoreProvider, Provider<StatsSiteProvider> statsSiteProvider) {
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.statsStoreProvider = statsStoreProvider;
    this.statsSiteProvider = statsSiteProvider;
  }

  @Override
  public ActionCardHandler get() {
    return newInstance(mainDispatcherProvider.get(), statsStoreProvider.get(), statsSiteProvider.get());
  }

  public static ActionCardHandler_Factory create(
      Provider<CoroutineDispatcher> mainDispatcherProvider, Provider<StatsStore> statsStoreProvider,
      Provider<StatsSiteProvider> statsSiteProvider) {
    return new ActionCardHandler_Factory(mainDispatcherProvider, statsStoreProvider, statsSiteProvider);
  }

  public static ActionCardHandler newInstance(CoroutineDispatcher mainDispatcher,
      StatsStore statsStore, StatsSiteProvider statsSiteProvider) {
    return new ActionCardHandler(mainDispatcher, statsStore, statsSiteProvider);
  }
}
