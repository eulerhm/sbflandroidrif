package org.wordpress.android.ui.reader;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import org.wordpress.android.fluxc.Dispatcher;
import org.wordpress.android.fluxc.network.rest.wpcom.site.PrivateAtomicCookie;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.fluxc.store.SiteStore;
import org.wordpress.android.ui.reader.tracker.ReaderTracker;
import org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper;
import org.wordpress.android.ui.reader.views.ReaderPostDetailsHeaderViewUiStateBuilder;
import org.wordpress.android.ui.utils.UiHelpers;
import org.wordpress.android.util.config.CommentsSnippetFeatureConfig;
import org.wordpress.android.util.config.LikesEnhancementsFeatureConfig;
import org.wordpress.android.util.image.ImageManager;
import org.wordpress.android.viewmodel.ContextProvider;

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
public final class ReaderPostDetailFragment_MembersInjector implements MembersInjector<ReaderPostDetailFragment> {
  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<SiteStore> siteStoreProvider;

  private final Provider<Dispatcher> dispatcherProvider;

  private final Provider<ReaderFileDownloadManager> readerFileDownloadManagerProvider;

  private final Provider<PrivateAtomicCookie> privateAtomicCookieProvider;

  private final Provider<ReaderCssProvider> readerCssProvider;

  private final Provider<ImageManager> imageManagerProvider;

  private final Provider<ReaderPostDetailsHeaderViewUiStateBuilder> postDetailsHeaderViewUiStateBuilderProvider;

  private final Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider;

  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  private final Provider<UiHelpers> uiHelpersProvider;

  private final Provider<ReaderTracker> readerTrackerProvider;

  private final Provider<LikesEnhancementsFeatureConfig> likesEnhancementsFeatureConfigProvider;

  private final Provider<ContextProvider> contextProvider;

  private final Provider<CommentsSnippetFeatureConfig> commentsSnippetFeatureConfigProvider;

  public ReaderPostDetailFragment_MembersInjector(Provider<AccountStore> accountStoreProvider,
      Provider<SiteStore> siteStoreProvider, Provider<Dispatcher> dispatcherProvider,
      Provider<ReaderFileDownloadManager> readerFileDownloadManagerProvider,
      Provider<PrivateAtomicCookie> privateAtomicCookieProvider,
      Provider<ReaderCssProvider> readerCssProvider, Provider<ImageManager> imageManagerProvider,
      Provider<ReaderPostDetailsHeaderViewUiStateBuilder> postDetailsHeaderViewUiStateBuilderProvider,
      Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider, Provider<ReaderTracker> readerTrackerProvider,
      Provider<LikesEnhancementsFeatureConfig> likesEnhancementsFeatureConfigProvider,
      Provider<ContextProvider> contextProvider,
      Provider<CommentsSnippetFeatureConfig> commentsSnippetFeatureConfigProvider) {
    this.accountStoreProvider = accountStoreProvider;
    this.siteStoreProvider = siteStoreProvider;
    this.dispatcherProvider = dispatcherProvider;
    this.readerFileDownloadManagerProvider = readerFileDownloadManagerProvider;
    this.privateAtomicCookieProvider = privateAtomicCookieProvider;
    this.readerCssProvider = readerCssProvider;
    this.imageManagerProvider = imageManagerProvider;
    this.postDetailsHeaderViewUiStateBuilderProvider = postDetailsHeaderViewUiStateBuilderProvider;
    this.readerUtilsWrapperProvider = readerUtilsWrapperProvider;
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.uiHelpersProvider = uiHelpersProvider;
    this.readerTrackerProvider = readerTrackerProvider;
    this.likesEnhancementsFeatureConfigProvider = likesEnhancementsFeatureConfigProvider;
    this.contextProvider = contextProvider;
    this.commentsSnippetFeatureConfigProvider = commentsSnippetFeatureConfigProvider;
  }

  public static MembersInjector<ReaderPostDetailFragment> create(
      Provider<AccountStore> accountStoreProvider, Provider<SiteStore> siteStoreProvider,
      Provider<Dispatcher> dispatcherProvider,
      Provider<ReaderFileDownloadManager> readerFileDownloadManagerProvider,
      Provider<PrivateAtomicCookie> privateAtomicCookieProvider,
      Provider<ReaderCssProvider> readerCssProvider, Provider<ImageManager> imageManagerProvider,
      Provider<ReaderPostDetailsHeaderViewUiStateBuilder> postDetailsHeaderViewUiStateBuilderProvider,
      Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider,
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider,
      Provider<UiHelpers> uiHelpersProvider, Provider<ReaderTracker> readerTrackerProvider,
      Provider<LikesEnhancementsFeatureConfig> likesEnhancementsFeatureConfigProvider,
      Provider<ContextProvider> contextProvider,
      Provider<CommentsSnippetFeatureConfig> commentsSnippetFeatureConfigProvider) {
    return new ReaderPostDetailFragment_MembersInjector(accountStoreProvider, siteStoreProvider, dispatcherProvider, readerFileDownloadManagerProvider, privateAtomicCookieProvider, readerCssProvider, imageManagerProvider, postDetailsHeaderViewUiStateBuilderProvider, readerUtilsWrapperProvider, viewModelFactoryProvider, uiHelpersProvider, readerTrackerProvider, likesEnhancementsFeatureConfigProvider, contextProvider, commentsSnippetFeatureConfigProvider);
  }

  @Override
  public void injectMembers(ReaderPostDetailFragment instance) {
    injectAccountStore(instance, accountStoreProvider.get());
    injectSiteStore(instance, siteStoreProvider.get());
    injectDispatcher(instance, dispatcherProvider.get());
    injectReaderFileDownloadManager(instance, readerFileDownloadManagerProvider.get());
    injectPrivateAtomicCookie(instance, privateAtomicCookieProvider.get());
    injectReaderCssProvider(instance, readerCssProvider.get());
    injectImageManager(instance, imageManagerProvider.get());
    injectPostDetailsHeaderViewUiStateBuilder(instance, postDetailsHeaderViewUiStateBuilderProvider.get());
    injectReaderUtilsWrapper(instance, readerUtilsWrapperProvider.get());
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectUiHelpers(instance, uiHelpersProvider.get());
    injectReaderTracker(instance, readerTrackerProvider.get());
    injectLikesEnhancementsFeatureConfig(instance, likesEnhancementsFeatureConfigProvider.get());
    injectContextProvider(instance, contextProvider.get());
    injectCommentsSnippetFeatureConfig(instance, commentsSnippetFeatureConfigProvider.get());
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostDetailFragment.accountStore")
  public static void injectAccountStore(ReaderPostDetailFragment instance,
      AccountStore accountStore) {
    instance.accountStore = accountStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostDetailFragment.siteStore")
  public static void injectSiteStore(ReaderPostDetailFragment instance, SiteStore siteStore) {
    instance.siteStore = siteStore;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostDetailFragment.dispatcher")
  public static void injectDispatcher(ReaderPostDetailFragment instance, Dispatcher dispatcher) {
    instance.dispatcher = dispatcher;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostDetailFragment.readerFileDownloadManager")
  public static void injectReaderFileDownloadManager(ReaderPostDetailFragment instance,
      ReaderFileDownloadManager readerFileDownloadManager) {
    instance.readerFileDownloadManager = readerFileDownloadManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostDetailFragment.privateAtomicCookie")
  public static void injectPrivateAtomicCookie(ReaderPostDetailFragment instance,
      PrivateAtomicCookie privateAtomicCookie) {
    instance.privateAtomicCookie = privateAtomicCookie;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostDetailFragment.readerCssProvider")
  public static void injectReaderCssProvider(ReaderPostDetailFragment instance,
      ReaderCssProvider readerCssProvider) {
    instance.readerCssProvider = readerCssProvider;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostDetailFragment.imageManager")
  public static void injectImageManager(ReaderPostDetailFragment instance,
      ImageManager imageManager) {
    instance.imageManager = imageManager;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostDetailFragment.postDetailsHeaderViewUiStateBuilder")
  public static void injectPostDetailsHeaderViewUiStateBuilder(ReaderPostDetailFragment instance,
      ReaderPostDetailsHeaderViewUiStateBuilder postDetailsHeaderViewUiStateBuilder) {
    instance.postDetailsHeaderViewUiStateBuilder = postDetailsHeaderViewUiStateBuilder;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostDetailFragment.readerUtilsWrapper")
  public static void injectReaderUtilsWrapper(ReaderPostDetailFragment instance,
      ReaderUtilsWrapper readerUtilsWrapper) {
    instance.readerUtilsWrapper = readerUtilsWrapper;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostDetailFragment.viewModelFactory")
  public static void injectViewModelFactory(ReaderPostDetailFragment instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostDetailFragment.uiHelpers")
  public static void injectUiHelpers(ReaderPostDetailFragment instance, UiHelpers uiHelpers) {
    instance.uiHelpers = uiHelpers;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostDetailFragment.readerTracker")
  public static void injectReaderTracker(ReaderPostDetailFragment instance,
      ReaderTracker readerTracker) {
    instance.readerTracker = readerTracker;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostDetailFragment.likesEnhancementsFeatureConfig")
  public static void injectLikesEnhancementsFeatureConfig(ReaderPostDetailFragment instance,
      LikesEnhancementsFeatureConfig likesEnhancementsFeatureConfig) {
    instance.likesEnhancementsFeatureConfig = likesEnhancementsFeatureConfig;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostDetailFragment.contextProvider")
  public static void injectContextProvider(ReaderPostDetailFragment instance,
      ContextProvider contextProvider) {
    instance.contextProvider = contextProvider;
  }

  @InjectedFieldSignature("org.wordpress.android.ui.reader.ReaderPostDetailFragment.commentsSnippetFeatureConfig")
  public static void injectCommentsSnippetFeatureConfig(ReaderPostDetailFragment instance,
      CommentsSnippetFeatureConfig commentsSnippetFeatureConfig) {
    instance.commentsSnippetFeatureConfig = commentsSnippetFeatureConfig;
  }
}
