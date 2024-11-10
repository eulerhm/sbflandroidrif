package org.wordpress.android.ui.reader.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.datasets.wrappers.ReaderTagTableWrapper;
import org.wordpress.android.ui.reader.repository.usecases.ShouldAutoUpdateTagUseCase;
import org.wordpress.android.ui.reader.repository.usecases.tags.FetchFollowedTagsUseCase;
import org.wordpress.android.ui.reader.repository.usecases.tags.FetchInterestTagsUseCase;
import org.wordpress.android.ui.reader.repository.usecases.tags.FollowInterestTagsUseCase;
import org.wordpress.android.ui.reader.repository.usecases.tags.GetFollowedTagsUseCase;
import org.wordpress.android.ui.reader.utils.ReaderUtilsWrapper;

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
public final class ReaderTagRepository_Factory implements Factory<ReaderTagRepository> {
  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  private final Provider<CoroutineDispatcher> ioDispatcherProvider;

  private final Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider;

  private final Provider<FetchInterestTagsUseCase> fetchInterestTagUseCaseProvider;

  private final Provider<FollowInterestTagsUseCase> followInterestTagsUseCaseProvider;

  private final Provider<FetchFollowedTagsUseCase> fetchFollowedTagUseCaseProvider;

  private final Provider<GetFollowedTagsUseCase> getFollowedTagsUseCaseProvider;

  private final Provider<ShouldAutoUpdateTagUseCase> shouldAutoUpdateTagUseCaseProvider;

  private final Provider<ReaderTagTableWrapper> readerTagTableWrapperProvider;

  public ReaderTagRepository_Factory(Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<CoroutineDispatcher> ioDispatcherProvider,
      Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider,
      Provider<FetchInterestTagsUseCase> fetchInterestTagUseCaseProvider,
      Provider<FollowInterestTagsUseCase> followInterestTagsUseCaseProvider,
      Provider<FetchFollowedTagsUseCase> fetchFollowedTagUseCaseProvider,
      Provider<GetFollowedTagsUseCase> getFollowedTagsUseCaseProvider,
      Provider<ShouldAutoUpdateTagUseCase> shouldAutoUpdateTagUseCaseProvider,
      Provider<ReaderTagTableWrapper> readerTagTableWrapperProvider) {
    this.bgDispatcherProvider = bgDispatcherProvider;
    this.ioDispatcherProvider = ioDispatcherProvider;
    this.readerUtilsWrapperProvider = readerUtilsWrapperProvider;
    this.fetchInterestTagUseCaseProvider = fetchInterestTagUseCaseProvider;
    this.followInterestTagsUseCaseProvider = followInterestTagsUseCaseProvider;
    this.fetchFollowedTagUseCaseProvider = fetchFollowedTagUseCaseProvider;
    this.getFollowedTagsUseCaseProvider = getFollowedTagsUseCaseProvider;
    this.shouldAutoUpdateTagUseCaseProvider = shouldAutoUpdateTagUseCaseProvider;
    this.readerTagTableWrapperProvider = readerTagTableWrapperProvider;
  }

  @Override
  public ReaderTagRepository get() {
    return newInstance(bgDispatcherProvider.get(), ioDispatcherProvider.get(), readerUtilsWrapperProvider.get(), fetchInterestTagUseCaseProvider.get(), followInterestTagsUseCaseProvider.get(), fetchFollowedTagUseCaseProvider.get(), getFollowedTagsUseCaseProvider.get(), shouldAutoUpdateTagUseCaseProvider.get(), readerTagTableWrapperProvider.get());
  }

  public static ReaderTagRepository_Factory create(
      Provider<CoroutineDispatcher> bgDispatcherProvider,
      Provider<CoroutineDispatcher> ioDispatcherProvider,
      Provider<ReaderUtilsWrapper> readerUtilsWrapperProvider,
      Provider<FetchInterestTagsUseCase> fetchInterestTagUseCaseProvider,
      Provider<FollowInterestTagsUseCase> followInterestTagsUseCaseProvider,
      Provider<FetchFollowedTagsUseCase> fetchFollowedTagUseCaseProvider,
      Provider<GetFollowedTagsUseCase> getFollowedTagsUseCaseProvider,
      Provider<ShouldAutoUpdateTagUseCase> shouldAutoUpdateTagUseCaseProvider,
      Provider<ReaderTagTableWrapper> readerTagTableWrapperProvider) {
    return new ReaderTagRepository_Factory(bgDispatcherProvider, ioDispatcherProvider, readerUtilsWrapperProvider, fetchInterestTagUseCaseProvider, followInterestTagsUseCaseProvider, fetchFollowedTagUseCaseProvider, getFollowedTagsUseCaseProvider, shouldAutoUpdateTagUseCaseProvider, readerTagTableWrapperProvider);
  }

  public static ReaderTagRepository newInstance(CoroutineDispatcher bgDispatcher,
      CoroutineDispatcher ioDispatcher, ReaderUtilsWrapper readerUtilsWrapper,
      FetchInterestTagsUseCase fetchInterestTagUseCase,
      FollowInterestTagsUseCase followInterestTagsUseCase,
      FetchFollowedTagsUseCase fetchFollowedTagUseCase,
      GetFollowedTagsUseCase getFollowedTagsUseCase,
      ShouldAutoUpdateTagUseCase shouldAutoUpdateTagUseCase,
      ReaderTagTableWrapper readerTagTableWrapper) {
    return new ReaderTagRepository(bgDispatcher, ioDispatcher, readerUtilsWrapper, fetchInterestTagUseCase, followInterestTagsUseCase, fetchFollowedTagUseCase, getFollowedTagsUseCase, shouldAutoUpdateTagUseCase, readerTagTableWrapper);
  }
}
