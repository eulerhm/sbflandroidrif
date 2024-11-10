package org.wordpress.android.viewmodel.plugins;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.PluginStore;
import org.wordpress.android.fluxc.store.SiteStore;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class PluginBrowserViewModel_Factory implements Factory<PluginBrowserViewModel> {
  private final Provider<Dispatcher> mDispatcherProvider;

  private final Provider<PluginStore> mPluginStoreProvider;

  private final Provider<SiteStore> mSiteStoreProvider;

  public PluginBrowserViewModel_Factory(Provider<Dispatcher> mDispatcherProvider,
      Provider<PluginStore> mPluginStoreProvider, Provider<SiteStore> mSiteStoreProvider) {
    this.mDispatcherProvider = mDispatcherProvider;
    this.mPluginStoreProvider = mPluginStoreProvider;
    this.mSiteStoreProvider = mSiteStoreProvider;
  }

  @Override
  public PluginBrowserViewModel get() {
    return newInstance(mDispatcherProvider.get(), mPluginStoreProvider.get(), mSiteStoreProvider.get());
  }

  public static PluginBrowserViewModel_Factory create(Provider<Dispatcher> mDispatcherProvider,
      Provider<PluginStore> mPluginStoreProvider, Provider<SiteStore> mSiteStoreProvider) {
    return new PluginBrowserViewModel_Factory(mDispatcherProvider, mPluginStoreProvider, mSiteStoreProvider);
  }

  public static PluginBrowserViewModel newInstance(Dispatcher mDispatcher, PluginStore mPluginStore,
      SiteStore mSiteStore) {
    return new PluginBrowserViewModel(mDispatcher, mPluginStore, mSiteStore);
  }
}
