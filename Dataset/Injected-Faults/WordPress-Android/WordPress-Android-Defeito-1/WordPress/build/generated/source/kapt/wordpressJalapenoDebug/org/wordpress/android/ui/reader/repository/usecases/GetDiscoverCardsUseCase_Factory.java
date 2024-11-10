package org.wordpress.android.ui.reader.repository.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.datasets.ReaderBlogTableWrapper;
import org.wordpress.android.datasets.ReaderDiscoverCardsTableWrapper;
import org.wordpress.android.datasets.wrappers.ReaderPostTableWrapper;
import org.wordpress.android.fluxc.utils.AppLogWrapper;
import org.wordpress.android.ui.prefs.AppPrefsWrapper;

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
public final class GetDiscoverCardsUseCase_Factory implements Factory<GetDiscoverCardsUseCase> {
  private final Provider<ParseDiscoverCardsJsonUseCase> parseDiscoverCardsJsonUseCaseProvider;

  private final Provider<ReaderDiscoverCardsTableWrapper> readerDiscoverCardsTableWrapperProvider;

  private final Provider<ReaderPostTableWrapper> readerPostTableWrapperProvider;

  private final Provider<ReaderBlogTableWrapper> readerBlogTableWrapperProvider;

  private final Provider<AppLogWrapper> appLogWrapperProvider;

  private final Provider<AppPrefsWrapper> appPrefsWrapperProvider;

  private final Provider<CoroutineDispatcher> ioDispatcherProvider;

  public GetDiscoverCardsUseCase_Factory(
      Provider<ParseDiscoverCardsJsonUseCase> parseDiscoverCardsJsonUseCaseProvider,
      Provider<ReaderDiscoverCardsTableWrapper> readerDiscoverCardsTableWrapperProvider,
      Provider<ReaderPostTableWrapper> readerPostTableWrapperProvider,
      Provider<ReaderBlogTableWrapper> readerBlogTableWrapperProvider,
      Provider<AppLogWrapper> appLogWrapperProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<CoroutineDispatcher> ioDispatcherProvider) {
    this.parseDiscoverCardsJsonUseCaseProvider = parseDiscoverCardsJsonUseCaseProvider;
    this.readerDiscoverCardsTableWrapperProvider = readerDiscoverCardsTableWrapperProvider;
    this.readerPostTableWrapperProvider = readerPostTableWrapperProvider;
    this.readerBlogTableWrapperProvider = readerBlogTableWrapperProvider;
    this.appLogWrapperProvider = appLogWrapperProvider;
    this.appPrefsWrapperProvider = appPrefsWrapperProvider;
    this.ioDispatcherProvider = ioDispatcherProvider;
  }

  @Override
  public GetDiscoverCardsUseCase get() {
    return newInstance(parseDiscoverCardsJsonUseCaseProvider.get(), readerDiscoverCardsTableWrapperProvider.get(), readerPostTableWrapperProvider.get(), readerBlogTableWrapperProvider.get(), appLogWrapperProvider.get(), appPrefsWrapperProvider.get(), ioDispatcherProvider.get());
  }

  public static GetDiscoverCardsUseCase_Factory create(
      Provider<ParseDiscoverCardsJsonUseCase> parseDiscoverCardsJsonUseCaseProvider,
      Provider<ReaderDiscoverCardsTableWrapper> readerDiscoverCardsTableWrapperProvider,
      Provider<ReaderPostTableWrapper> readerPostTableWrapperProvider,
      Provider<ReaderBlogTableWrapper> readerBlogTableWrapperProvider,
      Provider<AppLogWrapper> appLogWrapperProvider,
      Provider<AppPrefsWrapper> appPrefsWrapperProvider,
      Provider<CoroutineDispatcher> ioDispatcherProvider) {
    return new GetDiscoverCardsUseCase_Factory(parseDiscoverCardsJsonUseCaseProvider, readerDiscoverCardsTableWrapperProvider, readerPostTableWrapperProvider, readerBlogTableWrapperProvider, appLogWrapperProvider, appPrefsWrapperProvider, ioDispatcherProvider);
  }

  public static GetDiscoverCardsUseCase newInstance(
      ParseDiscoverCardsJsonUseCase parseDiscoverCardsJsonUseCase,
      ReaderDiscoverCardsTableWrapper readerDiscoverCardsTableWrapper,
      ReaderPostTableWrapper readerPostTableWrapper, ReaderBlogTableWrapper readerBlogTableWrapper,
      AppLogWrapper appLogWrapper, AppPrefsWrapper appPrefsWrapper,
      CoroutineDispatcher ioDispatcher) {
    return new GetDiscoverCardsUseCase(parseDiscoverCardsJsonUseCase, readerDiscoverCardsTableWrapper, readerPostTableWrapper, readerBlogTableWrapper, appLogWrapper, appPrefsWrapper, ioDispatcher);
  }
}
