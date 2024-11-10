package org.wordpress.android.ui.reader;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import org.wordpress.android.ui.reader.usecases.ReaderCommentsFollowUseCase;

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
public final class ReaderFollowCommentsHandler_Factory implements Factory<ReaderFollowCommentsHandler> {
  private final Provider<ReaderCommentsFollowUseCase> readerCommentsFollowUseCaseProvider;

  private final Provider<CoroutineDispatcher> bgDispatcherProvider;

  public ReaderFollowCommentsHandler_Factory(
      Provider<ReaderCommentsFollowUseCase> readerCommentsFollowUseCaseProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    this.readerCommentsFollowUseCaseProvider = readerCommentsFollowUseCaseProvider;
    this.bgDispatcherProvider = bgDispatcherProvider;
  }

  @Override
  public ReaderFollowCommentsHandler get() {
    return newInstance(readerCommentsFollowUseCaseProvider.get(), bgDispatcherProvider.get());
  }

  public static ReaderFollowCommentsHandler_Factory create(
      Provider<ReaderCommentsFollowUseCase> readerCommentsFollowUseCaseProvider,
      Provider<CoroutineDispatcher> bgDispatcherProvider) {
    return new ReaderFollowCommentsHandler_Factory(readerCommentsFollowUseCaseProvider, bgDispatcherProvider);
  }

  public static ReaderFollowCommentsHandler newInstance(
      ReaderCommentsFollowUseCase readerCommentsFollowUseCase, CoroutineDispatcher bgDispatcher) {
    return new ReaderFollowCommentsHandler(readerCommentsFollowUseCase, bgDispatcher);
  }
}
