package org.wordpress.android.ui.main;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.PostStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.main.utils.MeGravatarLoader;
import org.wordpress.android.ui.photopicker.MediaPickerLauncher;
import org.wordpress.android.util.SnackbarSequencer;
import org.wordpress.android.util.config.QRCodeAuthFlowFeatureConfig;
import org.wordpress.android.util.config.RecommendTheAppFeatureConfig;
import org.wordpress.android.util.config.UnifiedAboutFeatureConfig;

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
public final class MeFragment_MembersInjector implements MembersInjector<MeFragment> {
  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<PostStore> postStoreProvider;

  private final Provider<MeGravatarLoader> meGravatarLoaderProvider;

  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<MediaPickerLauncher> mediaPickerLauncherProvider;

  private final Provider<RecommendTheAppFeatureConfig> recommendTheAppFeatureConfigProvider;

  private final Provider<SnackbarSequencer> sequencerProvider;

  private final Provider<UnifiedAboutFeatureConfig> unifiedAboutFeatureConfigProvider;

  private final Provider<QRCodeAuthFlowFeatureConfig> qrCodeAuthFlowFeatureConfigProvider;

  public MeFragment_MembersInjector(Provider<Dispatcher> dispatcherProvider,
      Provider<AccountStore> accountStoreProvider, Provider<SiteStore> siteStoreProvider,
      Provider<PostStore> postStoreProvider, Provider<MeGravatarLoader> meGravatarLoaderProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<MediaPickerLauncher> mediaPickerLauncherProvider,
      Provider<RecommendTheAppFeatureConfig> recommendTheAppFeatureConfigProvider,
      Provider<SnackbarSequencer> sequencerProvider,
      Provider<UnifiedAboutFeatureConfig> unifiedAboutFeatureConfigProvider,
      Provider<QRCodeAuthFlowFeatureConfig> qrCodeAuthFlowFeatureConfigProvider) {
    this.dispatcherProvider = dispatcherProvider;
    this.accountStoreProvider = accountStoreProvider;
    this.siteStoreProvider = siteStoreProvider;
    this.postStoreProvider = postStoreProvider;
    this.meGravatarLoaderProvider = meGravatarLoaderProvider;
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.mediaPickerLauncherProvider = mediaPickerLauncherProvider;
    this.recommendTheAppFeatureConfigProvider = recommendTheAppFeatureConfigProvider;
    this.sequencerProvider = sequencerProvider;
    this.unifiedAboutFeatureConfigProvider = unifiedAboutFeatureConfigProvider;
    this.qrCodeAuthFlowFeatureConfigProvider = qrCodeAuthFlowFeatureConfigProvider;
  }

  public static MembersInjector<MeFragment> create(Provider<Dispatcher> dispatcherProvider,
      Provider<AccountStore> accountStoreProvider, Provider<SiteStore> siteStoreProvider,
      Provider<PostStore> postStoreProvider, Provider<MeGravatarLoader> meGravatarLoaderProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<MediaPickerLauncher> mediaPickerLauncherProvider,
      Provider<RecommendTheAppFeatureConfig> recommendTheAppFeatureConfigProvider,
      Provider<SnackbarSequencer> sequencerProvider,
      Provider<UnifiedAboutFeatureConfig> unifiedAboutFeatureConfigProvider,
      Provider<QRCodeAuthFlowFeatureConfig> qrCodeAuthFlowFeatureConfigProvider) {
    return new MeFragment_MembersInjector(dispatcherProvider, accountStoreProvider, siteStoreProvider, postStoreProvider, meGravatarLoaderProvider, viewModelFactoryProvider, mediaPickerLauncherProvider, recommendTheAppFeatureConfigProvider, sequencerProvider, unifiedAboutFeatureConfigProvider, qrCodeAuthFlowFeatureConfigProvider);
  }

  @Override
  public void injectMembers(MeFragment instance) {
    injectDispatcher(instance, dispatcherProvider.get());
    injectAccountStore(instance, accountStoreProvider.get());
    injectSiteStore(instance, siteStoreProvider.get());
    injectPostStore(instance, postStoreProvider.get());
    injectMeGravatarLoader(instance, meGravatarLoaderProvider.get());
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectMediaPickerLauncher(instance, mediaPickerLauncherProvider.get());
    injectRecommendTheAppFeatureConfig(instance, recommendTheAppFeatureConfigProvider.get());
    injectSequencer(instance, sequencerProvider.get());
    injectUnifiedAboutFeatureConfig(instance, unifiedAboutFeatureConfigProvider.get());
    injectQrCodeAuthFlowFeatureConfig(instance, qrCodeAuthFlowFeatureConfigProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.MeFragment.dispatcher")
  public static void injectDispatcher(MeFragment instance, Dispatcher dispatcher) {
    instance.dispatcher = dispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.MeFragment.accountStore")
  public static void injectAccountStore(MeFragment instance, AccountStore accountStore) {
    instance.accountStore = accountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.MeFragment.siteStore")
  public static void injectSiteStore(MeFragment instance, SiteStore siteStore) {
    instance.siteStore = siteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.MeFragment.postStore")
  public static void injectPostStore(MeFragment instance, PostStore postStore) {
    instance.postStore = postStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.MeFragment.meGravatarLoader")
  public static void injectMeGravatarLoader(MeFragment instance,
      MeGravatarLoader meGravatarLoader) {
    instance.meGravatarLoader = meGravatarLoader;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.MeFragment.viewModelFactory")
  public static void injectViewModelFactory(MeFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.MeFragment.mediaPickerLauncher")
  public static void injectMediaPickerLauncher(MeFragment instance,
      MediaPickerLauncher mediaPickerLauncher) {
    instance.mediaPickerLauncher = mediaPickerLauncher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.MeFragment.recommendTheAppFeatureConfig")
  public static void injectRecommendTheAppFeatureConfig(MeFragment instance,
      RecommendTheAppFeatureConfig recommendTheAppFeatureConfig) {
    instance.recommendTheAppFeatureConfig = recommendTheAppFeatureConfig;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.MeFragment.sequencer")
  public static void injectSequencer(MeFragment instance, SnackbarSequencer sequencer) {
    instance.sequencer = sequencer;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.MeFragment.unifiedAboutFeatureConfig")
  public static void injectUnifiedAboutFeatureConfig(MeFragment instance,
      UnifiedAboutFeatureConfig unifiedAboutFeatureConfig) {
    instance.unifiedAboutFeatureConfig = unifiedAboutFeatureConfig;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.main.MeFragment.qrCodeAuthFlowFeatureConfig")
  public static void injectQrCodeAuthFlowFeatureConfig(MeFragment instance,
      QRCodeAuthFlowFeatureConfig qrCodeAuthFlowFeatureConfig) {
    instance.qrCodeAuthFlowFeatureConfig = qrCodeAuthFlowFeatureConfig;
  }
}
