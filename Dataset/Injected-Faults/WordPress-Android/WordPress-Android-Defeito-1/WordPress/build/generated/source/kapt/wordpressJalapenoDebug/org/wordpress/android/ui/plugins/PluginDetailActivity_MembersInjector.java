package org.wordpress.android.ui.plugins;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.PluginStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.util.image.ImageManager;

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
public final class PluginDetailActivity_MembersInjector implements MembersInjector<PluginDetailActivity> {
  private final Provider<PluginStore> mPluginStoreProvider;

  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<Dispatcher> mDispatcherProvider;

  private final Provider<ImageManager> mImageManagerProvider;

  public PluginDetailActivity_MembersInjector(Provider<PluginStore> mPluginStoreProvider,
      Provider<SiteStore> mSiteStoreProvider, Provider<Dispatcher> mDispatcherProvider,
      Provider<ImageManager> mImageManagerProvider) {
    this.mPluginStoreProvider = mPluginStoreProvider;
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mDispatcherProvider = mDispatcherProvider;
    this.mImageManagerProvider = mImageManagerProvider;
  }

  public static MembersInjector<PluginDetailActivity> create(
      Provider<PluginStore> mPluginStoreProvider, Provider<SiteStore> mSiteStoreProvider,
      Provider<Dispatcher> mDispatcherProvider, Provider<ImageManager> mImageManagerProvider) {
    return new PluginDetailActivity_MembersInjector(mPluginStoreProvider, mSiteStoreProvider, mDispatcherProvider, mImageManagerProvider);
  }

  @Override
  public void injectMembers(PluginDetailActivity instance) {
    injectMPluginStore(instance, mPluginStoreProvider.get());
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMDispatcher(instance, mDispatcherProvider.get());
    injectMImageManager(instance, mImageManagerProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.plugins.PluginDetailActivity.mPluginStore")
  public static void injectMPluginStore(PluginDetailActivity instance, PluginStore mPluginStore) {
    instance.mPluginStore = mPluginStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.plugins.PluginDetailActivity.mSiteStore")
  public static void injectMSiteStore(PluginDetailActivity instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.plugins.PluginDetailActivity.mDispatcher")
  public static void injectMDispatcher(PluginDetailActivity instance, Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.plugins.PluginDetailActivity.mImageManager")
  public static void injectMImageManager(PluginDetailActivity instance,
      ImageManager mImageManager) {
    instance.mImageManager = mImageManager;
  }
}
