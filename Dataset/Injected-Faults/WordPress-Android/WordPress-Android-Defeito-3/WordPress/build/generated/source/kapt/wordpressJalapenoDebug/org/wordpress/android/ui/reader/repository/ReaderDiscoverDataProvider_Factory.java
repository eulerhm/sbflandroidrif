package org.wordpress.android.ui.reader.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.reader.repository.usecases.FetchDiscoverCardsUseCase;
import org.wordpress.android.ui.reader.repository.usecases.GetDiscoverCardsUseCase;
import org.wordpress.android.ui.reader.repository.usecases.ShouldAutoUpdateTagUseCase;
import org.wordpress.android.ui.reader.utils.ReaderTagWrapper;
import org.wordpress.android.util.EventBusWrapper;

@ScopeMetadata
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
public final class ReaderDiscoverDataProvider_Factory implements Factory<ReaderDiscoverDataProvider> {
  private final Provider<CoroutineDispatcher> ioDispatcherProvider;

  private final Provider<CoroutineDispatcher> mainDispatcherProvider;

  private final Provider<EventBusWrapper> eventBusWrapperProvider;

  private final Provider<ReaderTagWrapper> readerTagWrapperProvider;

  private final Provider<GetDiscoverCardsUseCase> getDiscoverCardsUseCaseProvider;

  private final Provider<ShouldAutoUpdateTagUseCase> shouldAutoUpdateTagUseCaseProvider;

  private final Provider<FetchDiscoverCardsUseCase> fetchDiscoverCardsUseCaseProvider;

  public ReaderDiscoverDataProvider_Factory(Provider<CoroutineDispatcher> ioDispatcherProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<EventBusWrapper> eventBusWrapperProvider,
      Provider<ReaderTagWrapper> readerTagWrapperProvider,
      Provider<GetDiscoverCardsUseCase> getDiscoverCardsUseCaseProvider,
      Provider<ShouldAutoUpdateTagUseCase> shouldAutoUpdateTagUseCaseProvider,
      Provider<FetchDiscoverCardsUseCase> fetchDiscoverCardsUseCaseProvider) {
    this.ioDispatcherProvider = ioDispatcherProvider;
    this.mainDispatcherProvider = mainDispatcherProvider;
    this.eventBusWrapperProvider = eventBusWrapperProvider;
    this.readerTagWrapperProvider = readerTagWrapperProvider;
    this.getDiscoverCardsUseCaseProvider = getDiscoverCardsUseCaseProvider;
    this.shouldAutoUpdateTagUseCaseProvider = shouldAutoUpdateTagUseCaseProvider;
    this.fetchDiscoverCardsUseCaseProvider = fetchDiscoverCardsUseCaseProvider;
  }

  @Override
  public ReaderDiscoverDataProvider get() {
    return newInstance(ioDispatcherProvider.get(), mainDispatcherProvider.get(), eventBusWrapperProvider.get(), readerTagWrapperProvider.get(), getDiscoverCardsUseCaseProvider.get(), shouldAutoUpdateTagUseCaseProvider.get(), fetchDiscoverCardsUseCaseProvider.get());
  }

  public static ReaderDiscoverDataProvider_Factory create(
      Provider<CoroutineDispatcher> ioDispatcherProvider,
      Provider<CoroutineDispatcher> mainDispatcherProvider,
      Provider<EventBusWrapper> eventBusWrapperProvider,
      Provider<ReaderTagWrapper> readerTagWrapperProvider,
      Provider<GetDiscoverCardsUseCase> getDiscoverCardsUseCaseProvider,
      Provider<ShouldAutoUpdateTagUseCase> shouldAutoUpdateTagUseCaseProvider,
      Provider<FetchDiscoverCardsUseCase> fetchDiscoverCardsUseCaseProvider) {
    return new ReaderDiscoverDataProvider_Factory(ioDispatcherProvider, mainDispatcherProvider, eventBusWrapperProvider, readerTagWrapperProvider, getDiscoverCardsUseCaseProvider, shouldAutoUpdateTagUseCaseProvider, fetchDiscoverCardsUseCaseProvider);
  }

  public static ReaderDiscoverDataProvider newInstance(CoroutineDispatcher ioDispatcher,
      CoroutineDispatcher mainDispatcher, EventBusWrapper eventBusWrapper,
      ReaderTagWrapper readerTagWrapper, GetDiscoverCardsUseCase getDiscoverCardsUseCase,
      ShouldAutoUpdateTagUseCase shouldAutoUpdateTagUseCase,
      FetchDiscoverCardsUseCase fetchDiscoverCardsUseCase) {
    return new ReaderDiscoverDataProvider(ioDispatcher, mainDispatcher, eventBusWrapper, readerTagWrapper, getDiscoverCardsUseCase, shouldAutoUpdateTagUseCase, fetchDiscoverCardsUseCase);
  }
}
