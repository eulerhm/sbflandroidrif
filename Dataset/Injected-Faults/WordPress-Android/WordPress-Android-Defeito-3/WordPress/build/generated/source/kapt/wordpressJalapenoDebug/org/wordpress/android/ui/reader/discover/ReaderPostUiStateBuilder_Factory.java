package org.wordpress.android.ui.reader.discover;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.fluxc.store.AccountStore;
import org.wordpress.android.ui.reader.utils.ReaderImageScannerProvider;
import org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper;
import org.wordpress.android.util.DateTimeUtilsWrapper;
import org.wordpress.android.util.GravatarUtilsWrapper;
import org.wordpress.android.util.UrlUtilsWrapper;

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
public final class ReaderPostUiStateBuilder_Factory implements Factory<ReaderPostUiStateBuilder> {
  private final Provider<AccountStore> accountStoreProvider;

  private final Provider<UrlUtilsWrapper> urlUtilsWrapperProvider;

  private final Provider<GravatarUtilsWrapper> gravatarUtilsWrapperProvider;

  private final Provider<DateTimeUtilsWrapper> dateTimeUtilsWrapperProvider;

  private final Provider<ReaderImageScannerProvider> readerImageScannerProvider;

  private final Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider;

  private final Provider<ReaderPostTagsUiStateBuilder> readerPostTagsUiStateBuilderProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public ReaderPostUiStateBuilder_Factory(Provider<AccountStore> accountStoreProvider,
      Provider<UrlUtilsWrapper> urlUtilsWrapperProvider,
      Provider<GravatarUtilsWrapper> gravatarUtilsWrapperProvider,
      Provider<DateTimeUtilsWrapper> dateTimeUtilsWrapperProvider,
      Provider<ReaderImageScannerProvider> readerImageScannerProvider,
      Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider,
      Provider<ReaderPostTagsUiStateBuilder> readerPostTagsUiStateBuilderProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.accountStoreProvider = accountStoreProvider;
    this.urlUtilsWrapperProvider = urlUtilsWrapperProvider;
    this.gravatarUtilsWrapperProvider = gravatarUtilsWrapperProvider;
    this.dateTimeUtilsWrapperProvider = dateTimeUtilsWrapperProvider;
    this.readerImageScannerProvider = readerImageScannerProvider;
    this.readerUtilsWrapperProvider = readerUtilsWrapperProvider;
    this.readerPostTagsUiStateBuilderProvider = readerPostTagsUiStateBuilderProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public ReaderPostUiStateBuilder get() {
    return newInstance(accountStoreProvider.get(), urlUtilsWrapperProvider.get(), gravatarUtilsWrapperProvider.get(), dateTimeUtilsWrapperProvider.get(), readerImageScannerProvider.get(), readerUtilsWrapperProvider.get(), readerPostTagsUiStateBuilderProvider.get(), bgDispatcherProvider.get());
  }

  public static ReaderPostUiStateBuilder_Factory create(Provider<AccountStore> accountStoreProvider,
      Provider<UrlUtilsWrapper> urlUtilsWrapperProvider,
      Provider<GravatarUtilsWrapper> gravatarUtilsWrapperProvider,
      Provider<DateTimeUtilsWrapper> dateTimeUtilsWrapperProvider,
      Provider<ReaderImageScannerProvider> readerImageScannerProvider,
      Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider,
      Provider<ReaderPostTagsUiStateBuilder> readerPostTagsUiStateBuilderProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new ReaderPostUiStateBuilder_Factory(accountStoreProvider, urlUtilsWrapperProvider, gravatarUtilsWrapperProvider, dateTimeUtilsWrapperProvider, readerImageScannerProvider, readerUtilsWrapperProvider, readerPostTagsUiStateBuilderProvider, bgDispatcherProvider);
  }

  public static ReaderPostUiStateBuilder newInstance(AccountStore accountStore,
      UrlUtilsWrapper urlUtilsWrapper, GravatarUtilsWrapper gravatarUtilsWrapper,
      DateTimeUtilsWrapper dateTimeUtilsWrapper,
      ReaderImageScannerProvider readerImageScannerProvider, ReaderUtilsWrapper readerUtilsWrapper,
      ReaderPostTagsUiStateBuilder readerPostTagsUiStateBuilder, CoroutineDispatcher bgDispatcher) {
    return new ReaderPostUiStateBuilder(accountStore, urlUtilsWrapper, gravatarUtilsWrapper, dateTimeUtilsWrapper, readerImageScannerProvider, readerUtilsWrapper, readerPostTagsUiStateBuilder, bgDispatcher);
  }
}
