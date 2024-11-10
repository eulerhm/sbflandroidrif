package org.wordpress.android.ui.reader.reblog;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.SiteStore;

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
public final class ReblogUseCase_Factory implements Factory<ReblogUseCase> {
  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public ReblogUseCase_Factory(Provider<SiteStore> siteStoreProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.siteStoreProvider = siteStoreProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public ReblogUseCase get() {
    return newInstance(siteStoreProvider.get(), bgDispatcherProvider.get());
  }

  public static ReblogUseCase_Factory create(Provider<SiteStore> siteStoreProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new ReblogUseCase_Factory(siteStoreProvider, bgDispatcherProvider);
  }

  public static ReblogUseCase newInstance(SiteStore siteStore, CoroutineDispatcher bgDispatcher) {
    return new ReblogUseCase(siteStore, bgDispatcher);
  }
}
