package org.wordpress.android.ui.uploads;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.util.config.Mp4ComposerVideoOptimizationFeatureConfig;

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
public final class MediaUploadHandler_MembersInjector implements MembersInjector<MediaUploadHandler> {
  private final Provider<Dispatcher> mDispatcherProvider;

  private final Provider<SiteStore> mSiteStoreProvider;

  private final Provider<Mp4ComposerVideoOptimizationFeatureConfig> mMp4ComposerVideoOptimizationFeatureConfigProvider;

  public MediaUploadHandler_MembersInjector(Provider<Dispatcher> mDispatcherProvider,
      Provider<SiteStore> mSiteStoreProvider,
      Provider<Mp4ComposerVideoOptimizationFeatureConfig> mMp4ComposerVideoOptimizationFeatureConfigProvider) {
    this.mDispatcherProvider = mDispatcherProvider;
    this.mSiteStoreProvider = mSiteStoreProvider;
    this.mMp4ComposerVideoOptimizationFeatureConfigProvider = mMp4ComposerVideoOptimizationFeatureConfigProvider;
  }

  public static MembersInjector<MediaUploadHandler> create(Provider<Dispatcher> mDispatcherProvider,
      Provider<SiteStore> mSiteStoreProvider,
      Provider<Mp4ComposerVideoOptimizationFeatureConfig> mMp4ComposerVideoOptimizationFeatureConfigProvider) {
    return new MediaUploadHandler_MembersInjector(mDispatcherProvider, mSiteStoreProvider, mMp4ComposerVideoOptimizationFeatureConfigProvider);
  }

  @Override
  public void injectMembers(MediaUploadHandler instance) {
    injectMDispatcher(instance, mDispatcherProvider.get());
    injectMSiteStore(instance, mSiteStoreProvider.get());
    injectMMp4ComposerVideoOptimizationFeatureConfig(instance, mMp4ComposerVideoOptimizationFeatureConfigProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.uploads.MediaUploadHandler.mDispatcher")
  public static void injectMDispatcher(MediaUploadHandler instance, Dispatcher mDispatcher) {
    instance.mDispatcher = mDispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.uploads.MediaUploadHandler.mSiteStore")
  public static void injectMSiteStore(MediaUploadHandler instance, SiteStore mSiteStore) {
    instance.mSiteStore = mSiteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.uploads.MediaUploadHandler.mMp4ComposerVideoOptimizationFeatureConfig")
  public static void injectMMp4ComposerVideoOptimizationFeatureConfig(MediaUploadHandler instance,
      Mp4ComposerVideoOptimizationFeatureConfig mMp4ComposerVideoOptimizationFeatureConfig) {
    instance.mMp4ComposerVideoOptimizationFeatureConfig = mMp4ComposerVideoOptimizationFeatureConfig;
  }
}
